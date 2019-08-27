package com.controller.mypage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.service.MyPageService;
import com.util.SessionCheckInterface;


@WebServlet("/DeleteAddrServlet")
public class DeleteAddrServlet extends HttpServlet implements SessionCheckInterface{
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "AddrListServlet";
		HttpSession session =request.getSession();
		sessionCheck(session, request, response, url, SessionCheckInterface.SERVLET, ()->{
			String[] strDelivnos = request.getParameter("delivnos").split("-");
			List<String> delivnos = Arrays.asList(strDelivnos);
			MyPageService service = new MyPageService();
			service.deleteDelivnos(delivnos);
		});
	}

}
