package com.controller.mypage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.service.MyPageService;

@WebServlet("/CheckPasswordServlet")
public class CheckPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		String userid = request.getParameter("userid");
		String password = request.getParameter("pwd");
		PrintWriter out = response.getWriter();
		MyPageService service = new MyPageService();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("password", password);
		int num = service.searchPwdById(map);
		out.print(num);
	}

}
