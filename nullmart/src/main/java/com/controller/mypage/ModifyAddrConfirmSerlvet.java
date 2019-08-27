package com.controller.mypage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.RegAddrDTO;
import com.model.service.MyPageService;
import com.util.SessionCheckInterface;

/**
 * Servlet implementation class ModifyAddrConfirmSerlvet
 */
@WebServlet("/ModifyAddrConfirmSerlvet")
public class ModifyAddrConfirmSerlvet extends HttpServlet implements SessionCheckInterface{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="AddrListServlet";
		HttpSession session = request.getSession();
		sessionCheck(session, request, response, url, SessionCheckInterface.SERVLET, ()->{
			String delivname = request.getParameter("delivname");
			String delivperson = request.getParameter("delivperson");
			String phone1 = request.getParameter("phone1");
			int delivno = Integer.parseInt(request.getParameter("delivno"));
			if(phone1.length()<5) {
				phone1="없음";
			}
			String phone2 = request.getParameter("phone2");
			String post = request.getParameter("post");
			String address1 = request.getParameter("address1");
			String address2 = request.getParameter("address2");
			String userid = request.getParameter("userid");
			MyPageService service = new MyPageService();
			RegAddrDTO dto  = new RegAddrDTO(delivno, delivname, delivperson, phone1, phone2, post, address1, address2, userid);;
			service.modifyAddr(dto);
		});
	}

}
