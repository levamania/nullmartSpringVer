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


import com.model.service.ManagerService;



@WebServlet("/ManagerIdPwCheckServlet")
public class ManagerIdPwCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=utf-8");
		
		
		String masteruserid = request.getParameter("masteruserid");
		String masterpasswd = request.getParameter("masterpasswd");
		ManagerService service = new ManagerService();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("masteruserid", masteruserid);
		map.put("masterpasswd", masterpasswd);
        int num = service.ManagerIdPwCheck(map);

        PrintWriter out = response.getWriter(); out.print(num);
		
	
		
		 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
