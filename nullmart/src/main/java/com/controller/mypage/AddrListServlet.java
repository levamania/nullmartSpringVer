package com.controller.mypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.dto.RegAddrDTO;
import com.model.service.MyPageService;
import com.util.SessionCheckInterface;



@WebServlet("/AddrListServlet")
public class AddrListServlet extends HttpServlet implements SessionCheckInterface{
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "addrlist.jsp";
		
		HttpSession session = request.getSession();
		
		sessionCheck(session, request, response, url, SessionCheckInterface.REDIRECT, ()->{
			
			MemberDTO member = (MemberDTO)session.getAttribute("login");
			String userid = member.getUserid();
			MyPageService service = new MyPageService();
			List<RegAddrDTO> regAddrDTOs = service.getAddrList(userid);
			session.setAttribute("addrList", regAddrDTOs);
		});
		
	}

}
