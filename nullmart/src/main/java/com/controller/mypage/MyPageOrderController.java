package com.controller.mypage;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.util.CreatePaging;
import com.util.SearchOrderCalDate;

@Controller
public class MyPageOrderController {
	
	private static Logger logger = LoggerFactory.getLogger(MyPageService.class);
	
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
	public ModelAndView goOrderInfo(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value = "searchDate" ,required = false, defaultValue = "15일") String searchDate,
			@RequestParam(value = "cur",required = false, defaultValue = "1") int cur,
			@RequestParam(value = "startCur",required = false, defaultValue = "1") int startCur,
			@RequestParam(value = "endCur",required = false, defaultValue = "0") int endCur) {
		//이전 이후 그룹 선택용 
		String groupindecator = request.getParameter("groupindecator");
		/*
		 * int cur = Integer.parseInt(request.getParameter("cur")); int startCur =
		 * Integer.parseInt(request.getParameter("startCur")); int endCur =
		 * Integer.parseInt(request.getParameter("endCur"));
		 */
		
		System.out.println("goOrderInfo");
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String selectDays = "";
		MemberDTO login = (MemberDTO)session.getAttribute("login");
		String userid = login.getUserid();
		HashMap<String, String> map = new HashMap<String,String>();
		map.put("userid", userid);
		System.out.println(selectDays);
		if(searchDate==null ||searchDate.equals("15일")) {
			System.out.println(23123);
			map= SearchOrderCalDate.getDate("15", SearchOrderCalDate.DAYS);
			selectDays="15일";
		}else if(searchDate.equals("오늘")) {
			map = SearchOrderCalDate.getDate("", SearchOrderCalDate.TODAY);
			selectDays="오늘";
		}else if((searchDate.equals("1개월")||searchDate.equals("3개월"))){
				map = SearchOrderCalDate.getDate(searchDate.substring(0,1),SearchOrderCalDate.MONTHS);
				if(searchDate.equals("1개월")) {
					selectDays="1개월";
				}else {
					selectDays="3개월";
				}
		}else {
			String[] values = searchDate.split(":");
			map.put("start",values[0]);
			map.put("end",values[1]);
			selectDays="0";
		}
		//page 처리용 작업
		int maxColumn = service.searchCount(map);
		CreatePaging page = new CreatePaging(3, 4, maxColumn);
		if(groupindecator.equals("0")) {
			page.setCur(cur, startCur, endCur);
		}else if(groupindecator.equals("1")) {
			cur=startCur;
			page.setCur(cur, startCur, endCur);
		}else {
			cur=endCur;
			page.setCur(cur, startCur, endCur);
		}
		//rowbound 객체의 시작 인덱스 
		String offset = String.valueOf(page.getCurColumn());
		//roubound offset 
		String limit = String.valueOf(page.getRows());
				
		//map 객체에 값 전달
		map.put("offset", offset);
		map.put("limit", limit);
		
		
		//System.out.println(servletContext.getRealPath("Content/img/"));
		List<OrderDTO> list = service.getOrderList(map);
		
//		session.setAttribute("orderlist", list);
//		session.setAttribute("selectDays", selectDays);
		
		mav.addObject("orderlist", list);
		mav.addObject("selectDays", selectDays);
		mav.addObject("page",page);
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
