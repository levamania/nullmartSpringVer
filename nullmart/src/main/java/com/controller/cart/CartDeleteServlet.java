package com.controller.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.model.service.CartService;
import com.util.LoginIndicator;
import com.util.MapParamInputer;


@WebServlet("/CartDeleteServlet")
public class CartDeleteServlet extends HttpServlet {
	
	Logger logger = LoggerFactory.getLogger(CartDeleteServlet.class);
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//setting
		PrintWriter out = response.getWriter();
		//수용
		String userid = LoginIndicator.check(request, response);

		HashMap<String, Object> reposit 
				= MapParamInputer.set("CNO",request.getParameter("CNO") ,"PAMOUNT",request.getParameter("PAMOUNT"),
													 "SCODE",request.getParameter("SCODE"));
		logger.debug("mesg{임시저장:"+reposit+"}");
		
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		list.add(reposit);
		
		//with model
		CartService ser = new CartService();
			//삭제와 동시에 재고에 다시 쌓기
		int result = ser.deleteCart(list);
		
		if(result==list.size()) {
			out.print("success");
		}else{
			out.print("fail");
		}
		
		
		
		//with jsp
		
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	 
		doGet(request, response);
	}

}
