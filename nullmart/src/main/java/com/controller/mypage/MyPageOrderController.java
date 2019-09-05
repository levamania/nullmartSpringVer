package com.controller.mypage;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dto.EvalDTO;
import com.dto.MemberDTO;
import com.dto.OrderDTO;
import com.dto.OrderEvalListDTO;
import com.model.service.MyPageService;
import com.util.SearchOrderCalDate;

@Controller
public class MyPageOrderController {
	@Autowired
	private MyPageService service;

//	@Autowired
//	private ServletContext servletContext;
	
	/*
	 * 주문한 상품에 대한 검색
	 * day: 검색에 필요한 날짜, null일 경우 15일 default값으로 
	 * orderList: 상품 검색 리스트
	 * selectDays: 검색한 날짜 반환 
	 * */
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
		//System.out.println(servletContext.getRealPath("Content/img/"));
		List<OrderDTO> list = service.getOrderList(map);
		
		session.setAttribute("orderlist", list);
		session.setAttribute("selectDays", selectDays);
		
		mav.addObject("orderlist", list);
		mav.addObject("selectDays", selectDays);
		mav.setViewName("/Content/mypage/orderinfo");
		return mav;
	}
	
	/*
	 * ajax 응답 요청
	 * pcode 검색
	 * pcode 반환
	 * */
	@RequestMapping(value = "/mypage/searchPcode",method = RequestMethod.POST)
	@ResponseBody
	public String searchPcode(String scode) {
		String[] scodes = scode.split("/");
		String pname = scodes[2];
		String pcode= service.searchPcode(pname);
		if(pcode==null) {
			pcode="0";
		}
		return pcode;
	}
	
	/*로그인 form으로 이동 */
	@RequestMapping(value = "/loginForm")
	public String goLoginForm() {
		System.out.println("goLoginForm");
		return "/Content/account/loginForm";
	}
	
	/*
	 * 작성한 상품후기 리스트 이동
	 * orderevallist: 상품 구매 후기 리스트 페이지
	 * */
	@RequestMapping(value = "/mypage/orderEvalList")
	public String orderEvalList(HttpSession session) {
		MemberDTO member = (MemberDTO)session.getAttribute("login");
		String userid = member.getUserid();
		List<OrderEvalListDTO> orderEvalList = service.getOrderEvalList(userid);
		session.setAttribute("orderevallist",orderEvalList);
		return "/Content/mypage/orderevallist";
	}
	
	/*
	 * 상품후기 작성 등록 
	 * EvalDTO eval: 상품 후기 작성 내용 객체 
	 * 완료후 상품 후기 리스트 페이지로 
	 * */
	@RequestMapping(value = "/mypage/orderEval")
	public String orderEval(EvalDTO eval) {
		eval.setEvalno(eval.getOno()+"EVAL");
		System.out.println(eval);
		int num = service.addEval(eval);
		return "redirect:/mypage/orderEvalList";
	}
	
	/*
	 * 상품 후기 수정 
	 * ono: 주문 번호 
	 * scode: 재고 번호 
	 * modifyordereval.jsp: 상품 후기 수정 페이지 
	 * */
	@RequestMapping(value = "/mypage/modifyOrderEval",method = RequestMethod.GET)
	public String modifyOrderEval(@RequestParam String ono,HttpServletRequest request) {
		EvalDTO eval = service.searchEvalByOno(ono);
		String scode = service.searchScodeByOno(ono);
		System.out.println(scode);
		request.setAttribute("scode", scode);
		request.setAttribute("eval", eval);
		
		return "forward:/Content/mypage/modifyordereval.jsp";
	}
	
	/*
	 * 상품 후기 수정 완료
	 * EvalDTO: 상품 후기 정보 저장 객체 
	 * orderEvalList: 상품후기 리스트 페이지
	 * */
	@RequestMapping(value = "/mypage/modifyOrderEval",method = RequestMethod.POST)
	public String modifyOrderEvalConfirm(EvalDTO eval) {
		int num = service.updateEval(eval);
		return "redirect:/mypage/orderEvalList";
	}
}
