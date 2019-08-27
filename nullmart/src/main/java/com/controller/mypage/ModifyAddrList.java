package com.controller.mypage;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class ModifyAddrList
 */
@WebServlet("/ModifyAddrList")
public class ModifyAddrList extends HttpServlet implements SessionCheckInterface{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String url = "modifyaddr.jsp";
		sessionCheck(session, request, response, url,SessionCheckInterface.DISPATCHER, ()->{
			String delivno  = request.getParameter("delivno");
			MyPageService service = new MyPageService();
			RegAddrDTO dto = service.searchByNo(delivno);
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("delivno", String.valueOf(dto.getDelivno()));
			map.put("delivname", dto.getDelivname());
			map.put("delivperson", dto.getDelivperson());
			map.put("address1", dto.getAddress1());
			map.put("address2", dto.getAddress2());
			map.put("post",dto.getPost());
			map.put("userid",dto.getUserid());
			String phone11 = null;
			String phone12 = null;
			String phone13 = null;
			String phone21 = null;
			String phone22 = null;
			String phone23 = null;
			if(dto.getPhone1().equals("없음")) {
				phone11 = "";
				phone12 = "";
				phone13 = "";
			}else {
				String[] phone1 = dto.getPhone1().split("-");
				phone11=phone1[0];
				phone12=phone1[1];
				phone13=phone1[2];
			}
			String[] phone2 = dto.getPhone2().split("-");
			phone21=phone2[0];
			phone22=phone2[1];
			phone23=phone2[2];
			
			map.put("phone11", phone11);
			map.put("phone12", phone12);
			map.put("phone13", phone13);
			map.put("phone21", phone21);
			map.put("phone22", phone22);
			map.put("phone23", phone23);
			
			request.setAttribute("addr", map);
		});
	}

}
