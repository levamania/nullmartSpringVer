package com.controller.mypage;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dto.MemberDTO;
import com.dto.OrderDTO;
import com.model.service.MyPageService;
import com.util.SearchOrderCalDate;

@Controller
public class MyPageOrderController {
	@Autowired
	private MyPageService service;
	
	@RequestMapping(value = "/mypage/orderInfo", method = RequestMethod.GET)
	public ModelAndView goOrderInfo(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "day" ,required = false, defaultValue = "15일") String day) {
		System.out.println("goOrderInfo");
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String selectDays = "";
		MemberDTO login = (MemberDTO)session.getAttribute("login");
		String userid = login.getUserid();
		HashMap<String, String> map = new HashMap<String,String>();
		System.out.println(day);
		if(day==null ||day.equals("15일")) {
			System.out.println(23123);
			map= SearchOrderCalDate.getDate("15", SearchOrderCalDate.DAYS);
			selectDays="15일";
		}else if(day.equals("오늘")) {
			map = SearchOrderCalDate.getDate("", SearchOrderCalDate.TODAY);
			selectDays="오늘";
		}else if((day.equals("1개월")||day.equals("3개월"))){
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
		System.out.println(map);
		List<OrderDTO> list = service.getOrderList(map);
		System.out.println(list);
		
		session.setAttribute("orderlist", list);
		session.setAttribute("selectDays", selectDays);
		
		mav.addObject("orderlist", list);
		mav.addObject("selectDays", selectDays);
		mav.setViewName("/Content/mypage/orderinfo");
		return mav;
	}
	
	@RequestMapping(value = "/loginForm")
	public String goLoginForm() {
		System.out.println("goLoginForm");
		return "/Content/account/loginForm";
	}
}
