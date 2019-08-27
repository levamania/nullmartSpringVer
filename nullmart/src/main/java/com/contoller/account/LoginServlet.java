package com.contoller.account;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.model.service.MemberService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userid = request.getParameter("userid");
		//언젠간 고치는걸로
		if(userid==null) {
			response.sendRedirect("/null/Content/account/loginForm.jsp");
		}else {
			
		String passwd = request.getParameter("passwd");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("passwd", passwd);
		MemberService service = new MemberService();
		MemberDTO dto = service.login(map);
	
		
		
		String nextPage = null;
		if (dto == null) {
			nextPage = "LoginUIServlet";

		} else {
			nextPage = "MainServlet";
			
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
