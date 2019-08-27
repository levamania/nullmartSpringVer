package com.controller.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

import com.exception.CustomException;
import com.model.service.CartService;
import com.model.service.ProductService;
import com.util.LoginIndicator;
import com.util.MapParamInputer;
import com.util.SessionCheckInterface;

@WebServlet("/StackProductServlet")
public class StackProductServlet extends HttpServlet {
	
	private static Logger logger = LoggerFactory.getLogger(StackProductServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//셋팅
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//세션 처리
		try {
		
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
	    ProductService ser = new ProductService();
	    JSONArray array = new JSONArray();
	    Boolean stock_loss = false;
	    for(HashMap<String, Object> reposit : reposits) {
	    	HashMap<String, Object> dto = ser.selectProduct_info(reposit).get(0);//한개 확정
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
	    	CartService service = new CartService();
	    	int result = service.stackProduct(reposits);
	    	if(result!=0) {
	    		out.print("success");
	    	}
	    }else {
	    	 logger.debug("mesg{어레이:"+array+"}");
	    	out.print(array);
	    }
	    
		}catch (CustomException e) {
			System.out.println("로그인필요");
		}
        
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
