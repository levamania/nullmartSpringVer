package com.controller.product;

import java.io.IOException;


import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.ProductDTO;
import com.dto.StockDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.service.ProductService;
import com.util.QueryUtil;
import com.util.MapParamInputer;

@Controller
@RequestMapping("/product")
public class ProductController {
	private Logger logger = LoggerFactory.getLogger(ProductController.class);
	private String key;
	@Autowired
	private ProductService service;
	
	@RequestMapping(value = "/UI")
	public String displayUI(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		//셋팅
		response.setContentType("text/html;charset=utf-8");

		//propriating
		String source = request.getParameter("source");
		if(source==null)source = "item_text";
		String pCode = request.getParameter("pCode");
		logger.debug("mesg{pCode:"+pCode+"}","debug");
				
		HashMap<String, Object> reposit = MapParamInputer.set("PCODE",pCode);

		//set util
		QueryUtil query = new QueryUtil();

		//with model	
		List<HashMap<String, Object>> stock_list = service.selectProduct_info(reposit);
		logger.debug("mesg{"+stock_list+"}","debug");
			//색깔별로 사이즈, 수량 , 가격 맵핑
		HashMap<String, Object> color_mapped =  query.bind(stock_list, "PCOLOR", new String[]{"PSIZE","PAMOUNT","PPRICE", "DELIVERFEE_YN"});
			//map TO JSON으로 파싱하기
		JSONObject json = new JSONObject(color_mapped);
		logger.debug("mesg{json:"+json+"}");
		
		if(source.equals("item_size")) {
			logger.debug("mesg: stock_list"+stock_list+"","debug");
			request.setAttribute("item_sizes", json);
			return "forward:/product/provide_size";
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
					System.out.println(c.getName());
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
				Cookie cookie  = new Cookie("Product"+System.currentTimeMillis(), URLEncoder.encode(mapper.writeValueAsString(rio), "utf-8")); 				
				cookie.setPath("/");
				response.addCookie(cookie);
			}
				
			//WITH JSP
			model.addAttribute("product", product);
			model.addAttribute("json", json);
				
			return "/Content/product/product";
		}else {
			return "/main";
		}
	}//end display UI

	
	@ResponseBody
	@RequestMapping("/provide_size")
	public String provide_size(HttpServletRequest request){
		return request.getAttribute("item_sizes").toString();
	}
}
