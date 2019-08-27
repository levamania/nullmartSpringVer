package com.controller.mypage;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.model.service.MyPageService;
import com.util.SessionCheckInterface;


@WebServlet("/ModifyPasswordServlet")
public class ModifyPasswordServlet extends HttpServlet implements SessionCheckInterface{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url ="modifypassword.jsp";
		HttpSession session = request.getSession();
		
		sessionCheck(session, request, response, url, DISPATCHER, ()->{
			MyPageService service = new MyPageService();
			MemberDTO login = (MemberDTO)session.getAttribute("login");
			String userid = login.getUserid();
			String pwd = service.searchPassword(userid);
			System.out.println(pwd);
			request.setAttribute("pwd", pwd);
		});
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "LogoutServlet";
		HttpSession session = request.getSession();
		
		sessionCheck(session, request, response, url, SERVLET, ()->{
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("userid", userid);
			map.put("passwd", passwd);
			MyPageService service = new MyPageService();
			int num = service.updatePwd(map);
		});
	}

}
