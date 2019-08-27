package com.controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.model.service.MyPageService;
import com.util.SessionCheckInterface;

/**
 * Servlet implementation class ModifyAccountInfo
 */
@WebServlet("/ModifyAccountInfo")
public class ModifyAccountInfo extends HttpServlet implements SessionCheckInterface {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "modifyaccountinfo.jsp";
		HttpSession session = request.getSession();
		sessionCheck(session, request, response, url, SessionCheckInterface.DISPATCHER, () -> {
			String userid = request.getParameter("userid");
			MyPageService service = new MyPageService();
			MemberDTO member = service.searchMemberById(userid);
			request.setAttribute("member", member);
		});
	}

}
