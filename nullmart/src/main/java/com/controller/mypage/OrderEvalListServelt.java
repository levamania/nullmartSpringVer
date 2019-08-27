package com.controller.mypage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.dto.OrderDTO;
import com.dto.OrderEvalListDTO;
import com.model.service.MyPageService;
import com.util.SessionCheckInterface;

@WebServlet("/OrderEvalListServelt")
public class OrderEvalListServelt extends HttpServlet implements SessionCheckInterface{
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="orderevallist.jsp";
		
		HttpSession session = request.getSession();
		sessionCheck(session, request, response, url, REDIRECT, ()->{
			MyPageService service = new MyPageService();
			MemberDTO member = (MemberDTO)session.getAttribute("login");
			String userid = member.getUserid();
			List<OrderEvalListDTO> orderEvalList = service.getOrderEvalList(userid);
			System.out.println(orderEvalList);
			session.setAttribute("orderevallist",orderEvalList);
		});
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
