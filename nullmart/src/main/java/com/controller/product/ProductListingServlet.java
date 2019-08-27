package com.controller.product;

import java.io.File;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.exception.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.service.ProductService;
import com.model.service.RankingService;
import com.util.ComparatorFactory;
import com.util.ConfigGuide;
import com.util.Language;
import com.util.MapParamInputer;
import com.util.QueryUtil;
import com.util.WordInspector;



@WebServlet("/ProductListingServlet")
public class ProductListingServlet extends HttpServlet {
	
	private static Logger logger = LoggerFactory.getLogger(ProductListingServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션 처리
		HttpSession session = request.getSession();
		HashMap<String, Object> prev_stack = 
				(HashMap<String,Object>)session.getAttribute("prev_stack");
			//info from 현재 페이지 정보
		HashMap<String, Object> listing_setup = null;
		String back_word = "default";
		
		if(prev_stack!=null) {
			back_word = (String)prev_stack.get("back_word");
			listing_setup = (HashMap<String, Object>)prev_stack.get("listing_setup");
			session.removeAttribute("prev_stack");
		}
		logger.debug("mesg{listing_setup}"+listing_setup,"debug");

			//매 페이지 마다 갱신
		session.removeAttribute("prev_stack");
				
		//쿼리 스트링 수용
		String searchedWord = request.getParameter("searchedWord");
		if(searchedWord==null)searchedWord="default";
		String source = request.getParameter("source");
		if(source==null)source="other"; 
		String ordering_info = request.getParameter("ordering_info");
		if(ordering_info==null)ordering_info="PREGITDATE:DESC";
		
			//페이징 정보
		String p_temp1 = request.getParameter("cur_page");
		String p_temp2 = request.getParameter("paging_quantity");
		if(p_temp1==null)p_temp1="1";
		if(p_temp2==null)p_temp2="20";
		int cur_page = Integer.parseInt(p_temp1);
		int paging_quantity = Integer.parseInt(p_temp2);
		
		//setting
		RequestDispatcher dis = null;
		HashMap<String, List<String>> words_map = null;
		List<HashMap<String, Object>> pList = null;
		
		WordInspector inspector =	new WordInspector(new File(ConfigGuide.getPath()+"Content/configuration/subsitution_dictionary.json"));
		HashMap<String,Object> reposit = null;	
		try {
			ProductService service = new ProductService();
			
			if(!back_word.equals(searchedWord)) {
				//검증되고 번역된 단어얻기
				words_map = inspector.translate(searchedWord);
				//repository of category or name
				reposit = inspector.auto_categorize(service,words_map.get("searching"),Arrays.asList(new String[]{"PRODUCT"}));
				
				//기본 셋팅 삭제하기
				if(session.getAttribute("basic_setup")!=null)session.removeAttribute("basic_setup");					
				if(session.getAttribute("clicked")!=null)session.removeAttribute("clicked");					
				 
				//처음 검색지표
				session.setAttribute("basic", "baisc");
				
			}else {
				reposit = listing_setup;
				//계속된 검색 지표
				session.removeAttribute("basic");
			}
			logger.debug("mesg{reposit:}"+reposit,"debug");
			//list searching through category
			boolean empty_locator = false;
			for(Object obj  :reposit.values()) {
				if(obj!=null)empty_locator = true;
			}
			
			if(empty_locator) { //유효한 검색어가 없을시동작안함
				List<HashMap<String, Object>> raw_list = service.selectProductList(reposit);
				//중복 제거
				QueryUtil query = new QueryUtil();
				raw_list = query.unoverlap(raw_list, "PCODE");
				logger.debug("mesg {raw_list="+raw_list+"}", "debug");
				//정렬 기준 선택
				String order_criteria = ordering_info.split(":")[0];
				String direction = ordering_info.split(":")[1];
				Comparator<HashMap<String, Object>> comparator = ComparatorFactory.generate(order_criteria, direction);
			
				//페이징 처리
				pList = raw_list.stream().sorted(comparator) //정렬
						   .skip((cur_page-1)*paging_quantity).limit(paging_quantity).collect(Collectors.toList()); //페이징->리스트
				//페이지 갯수 저장
				request.setAttribute("page_size", Math.round((raw_list.size()/paging_quantity)+1));
				request.setAttribute("items_size", raw_list.size());

				//extract column
				List<HashMap<String, Object>> repo = new ArrayList<HashMap<String,Object>>();
				for(HashMap<String, Object> indiv :raw_list) {
					repo.addAll(service.selectProduct_info(indiv));
				}
					//조건
				if(session.getAttribute("basic")!=null) {
					session.setAttribute("basic_repo", repo);
					session.setAttribute("basic_raw", raw_list);
				}else {
					repo = (List<HashMap<String, Object>>)session.getAttribute("basic_repo");
					raw_list = (List<HashMap<String, Object>>)session.getAttribute("basic_raw");
				}
				
				//선택된 제품 컬럼 정보 중복제거하여 저장
				query.extractColumn(repo,request); 
				HashMap<String, Object> cols = query.extractColumn(raw_list);
				ObjectMapper mapper = new ObjectMapper();
				for(String key : cols.keySet()) {
					String smep = mapper.writeValueAsString((List<String>)cols.get(key));
					String ne = "";
					ne = inspector.render(smep, Language.Korean);
					request.setAttribute(key, mapper.readValue(ne, List.class));
				}
				//클릭된 리스팅셋업 저장
				HashMap<String, Object> clicked = (HashMap<String, Object>)session.getAttribute("clicked");
				if(clicked!=null) {
					request.setAttribute("clicked", new JSONObject(inspector.render(clicked, Language.Korean) ));					
				}
				
				//스타일 미드에 스타일 봇 바인딩
				HashMap<String, Object> binded = query.bind(raw_list, "STYLEMID",new String[] {"STYLEBOT"});
					//한글로 번역 & json 파싱 => 저장
				JSONObject sonsang = new JSONObject(inspector.render(binded, Language.Korean));
				request.setAttribute("BINDING", sonsang);
				
				//inserting keyword to ranking
				if(source.equals("input")) {
					RankingService ser = new RankingService();
					for(String word: words_map.get("ranking")) {
						if(ser.updateRanking(word)==0)ser.insertRanking(word); 
					}
				}//end_rank
			}// end_ser
		
		} catch (CustomException e) {
			logger.debug("mesg{"+e.getMessage()+"}", "debug");
		} catch(IOException e){
			System.out.println("경고: 파일이 없데요!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("너 또 왜!");
			e.printStackTrace();
		}
		//with jsp
		dis = request.getRequestDispatcher("/Content/product_list/productList.jsp");
			//statcking 
				//세션 처리용
			session.setAttribute("prev_stack", 
					MapParamInputer.setOb("listing_setup", (reposit==listing_setup)?listing_setup:reposit ,"back_word",searchedWord)  );
				//화면 구현용
			request.setAttribute("searchedWord", searchedWord);
			request.setAttribute("source", source);
				
			request.setAttribute("pList", pList);
			request.setAttribute("cur_page", cur_page);
			request.setAttribute("paging_quantity", paging_quantity);
			
			request.setAttribute("ordering_info", ordering_info);
			//shooting
			dis.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
