package com.contoller.account;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.service.MemberService;

/**
 * Servlet implementation class SearchPwServlet
 */
@WebServlet("/SearchPwServlet")
public class SearchPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/plain;charset=utf-8");
		String userid = request.getParameter("userid");
		String username = request.getParameter("username");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		System.out.println(userid+username+email1+email2);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("username", username);
		map.put("email1", email1);
		map.put("email2", email2);
		MemberService service = new MemberService();
		int num = service.SearchPw(map);
		PrintWriter out = response.getWriter();
		out.print(num);
		
		
		/*
		 * String nextpage = null; if(num==1) { RequestDispatcher dis=
		 * request.getRequestDispatcher("/SendMailServlet"); dis.forward(request,
		 * response); }
		 */	
		/*
		 * else if(num==0){ nextpage = "/null/Content/account/searchPw.jsp";
		 * response.sendRedirect(nextpage); }
		 */
		
		
		/*
		 * String nextpage = null; if (num ==1) { nextpage = "SendMailServlet.java";
		 * }else {
		 * 
		 * response.sendRedirect(nextpage);
		 * 
		 * }
		 */
				
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
