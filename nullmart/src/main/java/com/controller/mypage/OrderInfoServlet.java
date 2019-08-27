package com.controller.mypage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
import com.model.service.MyPageService;
import com.util.SearchOrderCalDate;
import com.util.SessionCheckInterface;


@WebServlet("/OrderInfoServlet")
public class OrderInfoServlet extends HttpServlet implements SessionCheckInterface{
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "orderinfo.jsp";
		HttpSession session = request.getSession();
		
		sessionCheck(session, request, response, url, REDIRECT,()->{
			MyPageService service = new MyPageService();
			String selectDays = "";
			MemberDTO login = (MemberDTO)session.getAttribute("login");
			String userid = login.getUserid();
			HashMap<String, String> map = new HashMap<String,String>();
			String day = request.getParameter("day");
			if(day==null ||day.equals("15일")) {
				map= SearchOrderCalDate.getDate("15", SearchOrderCalDate.DAYS);
				selectDays="15일";
			}else if(day.equals("오늘")) {
				map = SearchOrderCalDate.getDate("", SearchOrderCalDate.TODAY);
				selectDays="오늘";
			}
			else if((day.equals("1개월")||day.equals("3개월"))){
					map = SearchOrderCalDate.getDate(day.substring(0,1),SearchOrderCalDate.MONTHS);
					if(day.equals("1개월")) {
						selectDays="1개월";
					}else {
						selectDays="3개월";
					}
			}else {
				String[] values = day.split(":");
				map.put("start",values[0]);
				map.put("end",values[1]);
				selectDays="0";
			}
			
			map.put("userid", userid);
			List<OrderDTO> list = service.getOrderList(map);
			session.setAttribute("orderlist", list);
			session.setAttribute("selectDays", selectDays);

		});
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
