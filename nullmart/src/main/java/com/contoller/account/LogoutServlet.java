package com.contoller.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.ManagerDTO;
import com.dto.MemberDTO;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		MemberDTO dto =(MemberDTO)session.getAttribute("login");
		ManagerDTO mdto = (ManagerDTO)session.getAttribute("masterLogin");
		String nextPage = null;
		if (dto==null&&mdto==null) {
			nextPage="LoginUIServlet";
			session.setAttribute("mesg", "로그인이 필요합니다");
			
		}else {
			
			nextPage="MainServlet";
			session.invalidate();
		}
	
		response.sendRedirect(nextPage);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
