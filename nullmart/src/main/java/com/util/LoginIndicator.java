package com.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.exception.CustomException;

@WebServlet("/LoginIndicator")
public class LoginIndicator extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public static String check(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60 * 60);
		MemberDTO member = (MemberDTO) session.getAttribute("login");
		if (member != null) {
			return member.getUserid();
		} else {
			try {
				PrintWriter out = response.getWriter();
				out.print("invaild_login");
				session.setAttribute("warnning", "로그인이 필요합니다.");
				response.sendRedirect("/null/LoginUIServlet");
			} catch (IOException e) {
				e.printStackTrace();
			}
			throw new CustomException("로그인필요");
		}
	}

}
