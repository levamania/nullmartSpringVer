package com.controller.mypage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.exception.ModifyUserInfoException;
import com.model.service.MyPageService;

/**
 * Servlet implementation class ModifyAccountConfirmServlet
 */
@WebServlet("/ModifyAccountConfirmServlet")
public class ModifyAccountConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		MyPageService service = new MyPageService();
		String userid = request.getParameter("userid");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String post = request.getParameter("post");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String addr3 = request.getParameter("addr3");
		
		HashMap<String, String> member = new HashMap<String, String>();
		member.put("userid",userid );
		member.put("email1",email1 );
		member.put("email2",email2 );
		member.put("phone1",phone1 );
		member.put("phone2",phone2 );
		member.put("phone3",phone3 );
		member.put("post",post );
		member.put("addr1",addr1 );
		member.put("addr2",addr2 );
		member.put("addr3",addr3 );
		
		try {
			service.modifyAccountInfo(member);
			MemberDTO login = service.searchMemberById(userid);
			HttpSession session = request.getSession();
			session.setAttribute("login", login);
			out.print(0);
		} catch (ModifyUserInfoException e) {
			System.out.println(e.getMessage());
			out.print(1);
		}
		
				
	}

}
