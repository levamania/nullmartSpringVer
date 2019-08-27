package com.controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.StockJoinProductDTO;
import com.model.service.AdminService;
import com.util.CreatePaging;
import com.util.SearchOrderCalDate;


@WebServlet("/SearchStockServlet")
public class SearchStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//pname=&pcode=&pregitdate=&searchDate=2019-08-262019-08-31
		/*
		 * searchStock.jsp에서 post 방식으로 파라미터 전송
		 * pname: 상품명
		 * pcode: 상품코드
		 * searchDate: 등록날짜
		 * styletop: 대분류
		 * stylemid: 중분류
		 * stylebot: 소분류
		 * 
		 * */
		
		HashMap<String, String> map = new HashMap<String, String>();
		AdminService service = new AdminService();
		
		String pname=request.getParameter("pname");
		String pcode=request.getParameter("pcode");
		String styletop = request.getParameter("styletop");
		String stylemid = request.getParameter("stylemid");
		String stylebot = request.getParameter("stylebot");
		String searchDate = request.getParameter("searchDate");
		int cur = Integer.parseInt(request.getParameter("cur"));
		int startCur = Integer.parseInt(request.getParameter("startCur"));
		int endCur = Integer.parseInt(request.getParameter("endCur"));
		//날짜 계산용 mod 오늘 15일 1개월 3개월 1년 구분용
		int mod=99;
		
		//searchoption
		//이전 페이지에 설정한 옵션을 그대로 다시 설정 
		HashMap<String, String> searchoption = new HashMap<String, String>();
		searchoption.put("pname", pname);
		searchoption.put("pcode", pcode);
		searchoption.put("styletop", styletop);
		searchoption.put("stylemid", stylemid);
		searchoption.put("stylebot", stylebot);
		searchoption.put("searchDate", searchDate);
		
		request.setAttribute("searchoption", searchoption);
		//날짜 확인
		if(!searchDate.equals("")) {
			if(searchDate.length()>15) {
				//2019-08-202019-08-28
				String start = searchDate.substring(0, 10);
				String end = searchDate.substring(10);
				map.put("start", start);
				map.put("end", end);
			}else {
				if(searchDate.equals("오늘")) {
					map = SearchOrderCalDate.getDate("",SearchOrderCalDate.TODAY);
				}else if(searchDate.equals("15일")) {
					map = SearchOrderCalDate.getDate("15", SearchOrderCalDate.DAYS);
				}else if(searchDate.equals("1개월")) {
					map = SearchOrderCalDate.getDate("1", SearchOrderCalDate.MONTHS);
				}else if(searchDate.equals("3개월")) {
					map = SearchOrderCalDate.getDate("3", SearchOrderCalDate.MONTHS);
				}else if(searchDate.equals("1년")) {
					map = SearchOrderCalDate.getDate("1", SearchOrderCalDate.YEAR);
				}else if(searchDate.equals("전체일")) {
					map.put("start", null);
					map.put("end", null);
				}
			}
			
		}
		
		//상품명으로 검색
		if(!pname.equals("")) {
			map.put("pname", pname);
			map.put("styletop", null);
			map.put("stylemid", null);
			map.put("stylebot", null);
		//스타일로 검색
		}else {
			map.put("pname", null);
			if(styletop.equals("select")) {
				styletop=null;
			}
			if(stylemid.equals("select")) {
				stylemid=null;
			}
			if(stylebot.equals("select")) {
				stylebot=null;
			}
			map.put("styletop", styletop);
			map.put("stylemid", stylemid);
			map.put("stylebot", stylebot);
			
		}
		int maxColumn = service.searchCount(map);
		CreatePaging page = new CreatePaging(3, 4, maxColumn);
		page.setCur(cur, startCur, endCur);
		//rowbound 객체의 시작 인덱스 
		String offset = String.valueOf(page.getCurColumn());
		//roubound offset 
		String limit = String.valueOf(page.getRows());
		
		//map 객체에 값 전달
		map.put("offset", offset);
		map.put("limit", limit);
		System.out.println(page);
		List<StockJoinProductDTO> list = service.searchStock(map);
		//검색 리스트 
		request.setAttribute("orders", list);
		//페이지 정보 
		request.setAttribute("page", page);
		//검색 옵션
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Content/admin/searchStock.jsp");
		dispatcher.forward(request, response);
		list.stream().forEach(System.out::println);
	}

}
