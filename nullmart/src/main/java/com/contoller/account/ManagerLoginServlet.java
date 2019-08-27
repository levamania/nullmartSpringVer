package com.contoller.account;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.ManagerDTO;

import com.model.dao.ManagerDAO;
import com.model.service.ManagerService;


@WebServlet("/ManagerLoginServlet")
public class ManagerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String masteruserid = request.getParameter("masteruserid");
		//언젠간 고치는걸로
		if(masteruserid==null) {
			response.sendRedirect("/null/Content/account/loginForm.jsp");
			
		}else {
			
		String masterpasswd = request.getParameter("masterpasswd");
		System.out.println(masteruserid+masterpasswd);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("masteruserid", masteruserid);
		map.put("masterpasswd", masterpasswd);
		ManagerService service =  new ManagerService();
		ManagerDTO dto = service.masterLogin(map);

	System.out.println("dto값:"+dto);
		
		
		String nextPage = null;
		if (dto == null) {
			nextPage = "LoginUIServlet";

		} else {
			nextPage = "/null/Content/admin/adminMain.jsp";
			
			  HttpSession session = request.getSession(); session.setAttribute("login", dto);
			  session.setMaxInactiveInterval(60);
			 
		}

		response.sendRedirect(nextPage);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
