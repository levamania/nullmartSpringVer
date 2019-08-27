package com.controller.product;

import java.io.IOException;


import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dto.ProductDTO;
import com.dto.StockDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.service.ProductService;
import com.util.QueryUtil;
import com.util.MapParamInputer;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private Logger logger = LoggerFactory.getLogger(ProductServlet.class);
	private String key;
	
	private static int serial = 0;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//셋팅
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		//propriating
		String source = request.getParameter("source");
		if(source==null)source = "item_text";
		String pCode = request.getParameter("pCode");
		logger.debug("mesg{pCode:"+pCode+"}","debug");
				
		HashMap<String, Object> reposit = MapParamInputer.set("PCODE",pCode);

		//set util
		QueryUtil query = new QueryUtil();

		//with model
		ProductService service = new ProductService();				
		List<HashMap<String, Object>> stock_list = service.selectProduct_info(reposit);
		logger.debug("mesg{"+stock_list+"}","debug");
			//색깔별로 사이즈, 수량 , 가격 맵핑
		HashMap<String, Object> color_mapped =  query.bind(stock_list, "PCOLOR", new String[]{"PSIZE","PAMOUNT","PPRICE"});
			//map TO JSON으로 파싱하기
		JSONObject json = new JSONObject(color_mapped);
		logger.debug("mesg{json:"+json+"}");
		
		if(source.equals("item_size")) {
			logger.debug("mesg: stock_list"+stock_list+"","debug");
			out.print(json);//ajax 응답
		}else if(source.equals("item_text")){
			//정렬된 컬럼별 리스트 Get & Stack
			HashMap<String, Object> colums =  query.extractColumn(stock_list, request);
			
			//해당 상품 검색메소드를 통해 호출
			List<HashMap<String, Object>> temp_products = service.selectProductList(reposit);
			logger.debug("mesg{temp_products:"+temp_products+"}");
				//중복 제거
			HashMap<String, Object> product =  query.unoverlap(temp_products, "PCODE").get(0);
				
				//쿠키에 저장	
			ObjectMapper mapper = new ObjectMapper();	
			List<Cookie> cook = new ArrayList<Cookie>();
			boolean reiteration = false;
			
			Cookie[] cookies = request.getCookies();
			for(Cookie c : cookies) {
				if(c.getName().contains("Product")) {
					cook.add(c);
					HashMap<String,String> tep = mapper.readValue(URLDecoder.decode(c.getValue() , "utf-8"), HashMap.class) ;
					if(tep.get("PCODE").equals(product.get("PCODE")))reiteration = true;
				}
			}
								
			if(!reiteration) {
				
				if(cook.size()>=4) {
					Collections.sort(cook, (c1,c2)->c1.getName().compareTo(c2.getName()));				
					cook.get(0).setMaxAge(0);	
					// 쿠키 삭제시 같은 패스 설정과 다시 쿠키를 관리자에게 넘겨줘 수명에따라 관리시킨다.
					cook.get(0).setPath("/");	
					response.addCookie(cook.get(0));	
					
				}
				
				HashMap<String, String> rio =
						MapParamInputer.set("STYLEMID",product.get("STYLEMID"),"STYLEBOT",product.get("STYLEBOT"),
														  "PIMAGE",product.get("PIMAGE"),"PNAME",product.get("PNAME"),
														  "PCODE",product.get("PCODE"));
				Cookie cookie  = new Cookie("Product"+(++serial), URLEncoder.encode(mapper.writeValueAsString(rio), "utf-8")); 				
				cookie.setPath("/");
				response.addCookie(cookie);
			}
				
			//WITH JSP
			RequestDispatcher dis = request.getRequestDispatcher("/Content/product/product.jsp");
				//정보저장
				request.setAttribute("product", product);
				request.setAttribute("json", json);
				//사출
				dis.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
