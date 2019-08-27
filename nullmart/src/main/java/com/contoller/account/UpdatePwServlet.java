package com.contoller.account;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.service.MemberService;

/**
 * Servlet implementation class UpdatePwServlet
 */
@WebServlet("/UpdatePwServlet")
public class UpdatePwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String oldPasswd = request.getParameter("oldPasswd");
		String Passwd = request.getParameter("passwd");
		String userid = request.getParameter("userid");
		
		MemberService service = new MemberService();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("oldPasswd", oldPasswd);
		map.put("Passwd", Passwd);
		System.out.println(Passwd+oldPasswd);
		
		int n = service.UpdateNewPw(map);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
