package com.contoller.account;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.MemberDTO;
import com.model.service.MemberService;


@WebServlet("/EmailCheckServlet")
public class EmailCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=utf-8");
		
		
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		MemberService service = new MemberService();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email1", email1);
		map.put("email2", email2);
		
		
		int num= service.emailCheck(map);
		
	
		
		
		
		
		 
		 PrintWriter out = response.getWriter(); out.print(num);
		 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
