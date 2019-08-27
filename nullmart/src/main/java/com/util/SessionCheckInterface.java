package com.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;

public interface SessionCheckInterface {
	public static final int DISPATCHER = 0;
	public static final int REDIRECT = 1;
	public static final int SERVLET = 2;
	public static final int TEST=3;
	default void sessionCheck(HttpSession session, HttpServletRequest request, HttpServletResponse response, String url,
			int mode, DoWork doWork) throws IOException, ServletException {

		String loginUrl = "/null/LoginUIServlet";
		MemberDTO login = (MemberDTO) (session.getAttribute("login"));
		if (login == null) {
			response.sendRedirect(loginUrl);
		} else {
			doWork.work();
			switch (mode) {
			case DISPATCHER:
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Content/mypage/"+url);
				dispatcher.forward(request, response);
				break;
			case REDIRECT:
				response.sendRedirect("/null/Content/mypage/"+url);
				break;
			case SERVLET:
				response.sendRedirect("/null/"+url);
				break;
			case TEST:
				break;
			}
			
				
		}
	}
	
	interface DoWork{
		public void work();
	}

}
