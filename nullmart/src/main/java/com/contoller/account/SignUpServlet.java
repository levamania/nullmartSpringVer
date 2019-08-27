package com.contoller.account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.MemberDTO;
import com.model.service.MemberService;



@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		String username = request.getParameter("username");
		String sex = request.getParameter("sex");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String post = request.getParameter("post");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String addr3 = request.getParameter("addr3");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");

		MemberDTO dto = new MemberDTO(userid, passwd, username, sex, email1, email2,post, addr1, addr2, addr3, phone1, phone2, phone3);
		MemberService service = new MemberService();
		service.memberAdd(dto);
		
		
		/*
		 * String nextpage = null; nextpage ="/null/Content/account/signup_last.jsp";
		 * response.sendRedirect(nextpage);
		 */
				
		
		
		  RequestDispatcher dis =
		  request.getRequestDispatcher("/Content/account/signup_last.jsp");
		  dis.forward(request,response);
		 
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
