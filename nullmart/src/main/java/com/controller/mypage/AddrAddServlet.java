package com.controller.mypage;

import java.io.IOException;
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



@WebServlet("/AddrAddServlet")
public class AddrAddServlet extends HttpServlet implements SessionCheckInterface{
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "AddrListServlet";
		HttpSession session =request.getSession();
		
		sessionCheck(session, request, response, url, SessionCheckInterface.SERVLET, ()->{
			MemberDTO member = (MemberDTO)(session).getAttribute("login");
			String userid = member.getUserid();
			String delivname = request.getParameter("delivname");
			String delivperson = request.getParameter("delivperson");
			String phone1 = request.getParameter("phone1");
			if(phone1.length()<5) {
				phone1="없음";
			}
			String phone2 = request.getParameter("phone2");
			String post = request.getParameter("post");
			String address1 = request.getParameter("address1");
			String address2 = request.getParameter("address2");
			
			MyPageService service = new MyPageService();
			RegAddrDTO dto  = new RegAddrDTO(0, delivname, delivperson, phone1, phone2, post, address1, address2, userid);
			service.addAddr(dto);
		});
	}

}
