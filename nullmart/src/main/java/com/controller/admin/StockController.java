package com.controller.admin;

import java.awt.JobAttributes;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.ProductDTO;
import com.dto.StockDTO;
import com.dto.StockJoinProductDTO;
import com.model.service.AdminService;
import com.util.CreatePaging;
import com.util.SearchOrderCalDate;

@Controller
public class StockController {
	@Autowired
	AdminService service;

	/*
	 * 임시 이동 주소 inputStock: 재고 등록 페이지
	 */
	@RequestMapping(value = "/gostock")
	public String inputStock() {
		return "/Content/admin/inputStock";
	}

	/*
	 * pname, pcode ajax로 검색 조회 버튼으로 검색 검색 결과에 따라 특정 기능 수행
	 */
	@RequestMapping(value = "/admin/checkpcode" ,method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String searchProduct(String pname,String pcode)  {
		if(pcode.equals("")) {
			pcode = service.searchPcodeByPname(pname);
			if(pcode==null) {
				return "pnamenone";
			}else {
				return pcode;
			}
			
		}else if(pname.equals("")) {
			
			pname = service.searchPnameByPcode(pcode);
			System.out.println(pname);
			if(pname==null) {
				return "pcodenone";
			}else {
				return pname;
			}
		}else {
			
			String searchPcode = service.searchPcodeByPname(pname);
			String searchPname = service.searchPnameByPcode(pcode);
			if(searchPcode==null && searchPname==null) {
				return "bothnosearch";
			}else if(searchPcode==null) {
				return "pnamenosearch";
			}else if(searchPname==null) {
				return "pcodenosearch";
			}else {
				if(!pcode.equals(searchPcode)||!pname.equals(searchPname)) {
					return "eachnotequal";
				}
				else {
					return "both";
				}
			}
			
		}
	}

	/*
	 * 재고 등록 처리
	 * 임시 페이지로 이동 
	 */
	@RequestMapping(value = "/admin/inputStock", method = RequestMethod.POST)
	public String addStock(@RequestParam Map<String,String> map) {
		String pName = map.get("pname");
		String pCode = map.get("pcode");
		String pSize = map.get("psize");
		String pColor = map.get("pcolor");
		String pAmount = map.get("pamount");
		String pPrice = map.get("pprice");
		String deliverFee_YN = map.get("deliverfee_yn");
		String sCode = pColor+"/"+pSize+"/"+pName;
		StockDTO stock = new StockDTO(sCode, pCode, pSize, pColor, Integer.parseInt(pPrice), Integer.parseInt(pAmount), deliverFee_YN);
		service.insertStock(stock);
		return "redirect:/gostock";
	}
	
	/*
	 * 상품 검색 페이지 초기화 
	 * SimpleJSON으로 각 설정 파일 반환
	 * ajax로 json 객체 받음
	 * 
	 * */
	@RequestMapping(value = "/admin/initSearchPage", produces = "text/plain;charset=utf-8")
	public void initSearchPage(HttpServletResponse response,@RequestParam(required = false) String pname) throws IOException {
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (pname != null) { 
			ProductDTO product = service.searchProduct(pname);
			if(product==null) {
				out.print(0);
			}else {
				JSONObject productInfo = new JSONObject();
				productInfo.put("pcode", product.getpCode());
				productInfo.put("styletop", product.getStyleTop());
				productInfo.put("stylemid", product.getStyleMid());
				productInfo.put("stylebot", product.getStyleBot());
				productInfo.put("pregitdate", product.getpRegitDate());
				out.print(productInfo);
			}
		}else {
			//JSONArray를  저장할 jsonobject
			JSONObject jsonObject = new JSONObject();
			Map<String, List<String>> map = service.getSearchStockOptions();
			//style
			List<String> styletopList = map.get("styletop");
			List<String> stylemidList = map.get("stylemid");
			List<String> stylebotList = map.get("stylebot");
			System.out.println(stylebotList);
			//keyword
			List<String> keywordList = service.searchPname();
			//분류에 따른 select option 검색 
			
			
			JSONArray keywords = new JSONArray();
			JSONArray styletops = new JSONArray();
			JSONArray stylemids = new JSONArray();
			JSONArray stylebots = new JSONArray();
			
			styletopList.stream().forEach(s->{
				JSONObject obj = new JSONObject();
				obj.put("option", s);
				styletops.add(obj);
			});
			
			stylemidList.stream().forEach(s->{
				JSONObject obj = new JSONObject();
				obj.put("option", s);
				stylemids.add(obj);
			});
			
			stylebotList.stream().forEach(s->{
				JSONObject obj = new JSONObject();
				obj.put("option", s);
				stylebots.add(obj);
			});

			keywordList.stream().forEach(s -> {
				JSONObject obj = new JSONObject();
				obj.put("keyword", s);
				keywords.add(obj);
			});

			jsonObject.put("keywords", keywords);
			jsonObject.put("styletops", styletops);
			jsonObject.put("stylemids", stylemids);
			jsonObject.put("stylebots", stylebots);
			out.print(jsonObject);
		}
	}
	
	/*검색
	 * 조건에 맞춰 상품 검색
	 * 날짜, 분류, 상품명으로 검색 
	 * */

	@RequestMapping(value = "/admin/searchStockByCondition")
	public String searchStockBycondition(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> map = new HashMap<String, String>();
		String pname=request.getParameter("pname");
		String pcode=request.getParameter("pcode");
		String styletop = request.getParameter("styletop");
		String stylemid = request.getParameter("stylemid");
		String stylebot = request.getParameter("stylebot");
		String searchDate = request.getParameter("searchDate");
		//이전 이후 그룹 선택용 
		String groupindecator = request.getParameter("groupindecator");
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
		System.out.println(page);
		List<StockJoinProductDTO> list = service.searchStock(map);
		//검색 리스트 
		request.setAttribute("orders", list);
		//페이지 정보 
		request.setAttribute("page", page);
		//검색 옵션
		
		list.stream().forEach(System.out::println);
		
		return "forward:/Content/admin/searchStock.jsp";
	}

	/*
	 * 
	 * */
	@RequestMapping(value = "/admin/searchProduct", method = RequestMethod.POST)
	public  void getProduct(@RequestParam String pcode, HttpServletResponse response) throws IOException{
		response.setContentType("text/plain;charset=utf-8");
		JSONObject jsonObject = new JSONObject();
		HashMap<String, String> map= service.searchProductToMap(pcode);
		Set<String> keys = map.keySet();
		keys.stream().forEach(k->{
			String value = map.get(k);
			jsonObject.put(k, value);
		});
		System.out.println(jsonObject);
		response.getWriter().print(jsonObject);
	}
}

