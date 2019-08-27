package com.controller.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.service.CartService;
import com.util.MapParamInputer;

@WebServlet("/CartUpdateServlet")
public class CartUpdateServlet extends HttpServlet {
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		//수용
		String temp = request.getParameter("list");
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, Object>> list = mapper.readValue(temp, new TypeReference<List<HashMap<String, Object>>>() {});
		System.out.println(list);
		//with model
		CartService service = new CartService();
		int result = service.updateCart(list);
		if(list.size()==result) {
			out.print("success");
		}else {
			out.print("fail");
		}
		
		
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
