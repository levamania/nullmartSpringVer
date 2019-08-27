package com.controller.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dto.MemberDTO;
import com.exception.CustomException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.service.CartService;
import com.model.service.ProductService;
import com.util.LoginIndicator;
import com.util.MapParamInputer;
import com.util.QueryUtil;

@Controller("CartServlet")
@RequestMapping(value = "/Cart")
public class CartServlet {

	private  Logger logger = LoggerFactory.getLogger(StackProductServlet.class);
	@Autowired
	private CartService cser;
	@Autowired
	private ProductService pser;
	
	//DISPLAY UI
	@RequestMapping(value = "/UI")
	public void displayCartUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 세션 처리
		MemberDTO member = (MemberDTO) request.getSession().getAttribute("login");
		String userid = member.getUserid();

		// 유틸 셋팅
		QueryUtil util = new QueryUtil();
		// with model
		List<HashMap<String, Object>> cart_list = cser.selectCartList(MapParamInputer.set("USERID", userid));
		HashMap<String, Object> pcode_mapped = null;
		Set<String> key_set = null;
		if (cart_list.size() != 0) {
			pcode_mapped = util.bind(cart_list, "PCODE");
			key_set = pcode_mapped.keySet();
		}

		// with jsp
		RequestDispatcher dis = request.getRequestDispatcher("/Content/cart/cart.jsp");
		// 저장
		request.setAttribute("CART_LIST", cart_list);
		request.setAttribute("KEY_SET", key_set);
		request.setAttribute("PCODE_MAPPED", pcode_mapped);
		// 사출
		dis.forward(request, response);
	}
	
	//CART UPDATE
	@RequestMapping(value = "/update")
	protected void updateCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		//수용
		String temp = request.getParameter("list");
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, Object>> list = mapper.readValue(temp, new TypeReference<List<HashMap<String, Object>>>() {});
		System.out.println(list);
		//with model
		int result = cser.updateCart(list);
		if(list.size()==result) {
			out.print("success");
		}else {
			out.print("fail");
		}
	}//end upadate
	
	//DELETE PRODUCT
	@RequestMapping(value = "/delete")
	protected void deleteCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// setting
		PrintWriter out = response.getWriter();
		// 수용
		HashMap<String, Object> reposit = MapParamInputer.set("CNO", request.getParameter("CNO"), "PAMOUNT",
				request.getParameter("PAMOUNT"), "SCODE", request.getParameter("SCODE"));
		logger.debug("mesg{임시저장:" + reposit + "}");

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		list.add(reposit);

		// with model
		// 삭제와 동시에 재고에 다시 쌓기
		int result = cser.deleteCart(list);

		if (result == list.size()) {
			out.print("success");
		} else {
			out.print("fail");
		}
	}//end delete
	
	//STACK PRODUCT
	@RequestMapping(value = "/stackProduct")
	public void stackProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//셋팅
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//세션 처리
		String userid = LoginIndicator.check(request, response);
		//수용
			//string to json
		String temp = request.getParameter("list");
		JSONParser parser = new JSONParser();
		JSONArray json = null;
		try {
			json = (JSONArray)parser.parse(temp);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		logger.debug("mesg{리파짓:"+json+"}");
			//가공 & with model
		List<HashMap<String, Object>> reposits = new ArrayList<HashMap<String,Object>>();
	
	    ListIterator<JSONObject> ite = json.listIterator();	
	    while(ite.hasNext()) {
	    	String string_map = ite.next().toJSONString();
	    	String tem = string_map.substring(1, string_map.length()-1);
	    	HashMap<String, Object> map = new HashMap<String, Object>();
	    	for(String entry: tem.split(",")) {
	    		String key = entry.split(":")[0].substring(1, entry.split(":")[0].length()-1);
	    		String value = entry.split(":")[1].substring(1, entry.split(":")[1].length()-1);
	    		if(key.equals("SCODE")) {
	    			char [] charset = value.toCharArray();
	    			String s = "";
	    			for(char c : charset) {
	    				if(c!='\\')s+=c;
	    			}
	    			value = s;		
	    			map.put("PNAME",s.split("/")[2]);
	    			map.put("PSIZE",s.split("/")[1]);
	    			map.put("PCOLOR",s.split("/")[0]);
	    		}
	    		map.put(key,value);	    			
	    	}
	    	map.put("USERID", userid);
	    	reposits.add(map);
	    }
	    logger.debug("mesg{리파짓:"+reposits+"}");
	    //상품 재고 재확인
	    JSONArray array = new JSONArray();
	    Boolean stock_loss = false;
	    for(HashMap<String, Object> reposit : reposits) {
	    	HashMap<String, Object> dto = pser.selectProduct_info(reposit).get(0);//한개 확정
	    	int differ =  Integer.parseInt(dto.get("PAMOUNT").toString())-Integer.parseInt(reposit.get("PAMOUNT").toString());
	    	  logger.debug("mesg{차이:"+differ+"}");
	    	if(differ<0) {
	    		JSONObject son = new JSONObject();
	    		son.put("SCODE", reposit.get("SCODE"));
	    		son.put("PICK_QUAN", reposit.get("PAMOUNT"));
	    		son.put("STOCK_QUAN", dto.get("PAMOUNT"));
	    		son.put("DIFFER", Math.abs(differ));
	    		array.add(son);
	    		
	    		stock_loss = true;
	    	}	
	    }
	    
	    if(!stock_loss) {
	    	logger.debug("mesg{"+reposits+"}","debug");
	    	int result = cser.stackProduct(reposits);
	    	if(result!=0) {
	    		out.print("success");
	    	}
	    }else {
	    	 logger.debug("mesg{어레이:"+array+"}");
	    	out.print(array);
	    }
	}//end stack


}//end class
