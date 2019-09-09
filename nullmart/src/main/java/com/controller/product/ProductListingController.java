package com.controller.product;

import java.io.File;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Blob;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

@Controller
@RequestMapping(value = "/productListing")
@SuppressWarnings("unchecked")
public class ProductListingController {
	@Autowired
	private ServletContext context;
	@Autowired
	private ProductService pService;
	@Autowired
	private RankingService rser;

	@RequestMapping(value = "/specificFilter")
	public String filterCondition(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String selected_atoms = request.getParameter("selected_atoms");
		// 유틸 셋팅
		WordInspector inspector = new WordInspector(
				new File(context.getRealPath("/Content/configuration/subsitution_dictionary.json")));

		// 검색단어 가공 - json 파싱
		ObjectMapper mapper = new ObjectMapper();
		String transed = inspector.render(selected_atoms, Language.English);
		HashMap<String, Object> atom_lists = mapper.readValue(transed, HashMap.class);
		// 루핑용 카피
		HashMap<String, Object> copy = (HashMap<String, Object>) atom_lists.clone();

		HttpSession session = request.getSession();
		// 클릭된 셋팅저장
		if (session.getAttribute("clicked") != null)
			session.removeAttribute("clicked");
		session.setAttribute("clicked", copy);
		// 이전 스택
		HashMap<String, Object> prev_stack = (HashMap<String, Object>) session.getAttribute("prev_stack");
		// 갈무리된 검색 조건
		HashMap<String, Object> established = null;
		if (session.getAttribute("basic_setup") == null) {
			HashMap<String, Object> set_up = (HashMap<String, Object>) prev_stack.get("listing_setup"); // 기존 셋업과 합쳐야한다.
			established = set_up;
			session.setAttribute("basic_setup", (HashMap<String, Object>) set_up.clone());
		} else {
			established = (HashMap<String, Object>) session.getAttribute("basic_setup");
		}

		// 합치기
		for (String key : copy.keySet()) {
			for (String infe : established.keySet()) {
				if (established.get(infe) != null) {
					List<String> lit_main = (List<String>) atom_lists.get(key);
					List<String> lit_sub = (List<String>) established.get(infe);
					if (key.equals(infe)) {
						if (!lit_main.get(0).equals(lit_sub.get(0)))
							lit_main.addAll(lit_sub);
					} else {
						atom_lists.put(infe, lit_sub);
					}
				}
			}
		}
		prev_stack.remove("listing_setup"); // 기존 셋업삭제
		prev_stack.put("listing_setup", atom_lists);// 셋팅 재설정 완료

		// 디스패치
		return "forward:/productListing/work";
	}

	private static Logger logger = LoggerFactory.getLogger(ProductListingController.class);

	@RequestMapping(value = "/work")
	public  String work(HttpServletRequest request, HttpServletResponse response, Model model,
										  @RequestParam(defaultValue = "false", name = "refresh") boolean refresh) throws ServletException, IOException {
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
		HashMap<String, List<String>> words_map = null;
		List<HashMap<String, Object>> pList = null;
		
		WordInspector inspector =	new WordInspector(new File(context.getRealPath("Content/configuration/subsitution_dictionary.json")));
		HashMap<String,Object> reposit = null;	
		try {
			
			if(!back_word.equals(searchedWord)||refresh) {
				//검증되고 번역된 단어얻기
				words_map = inspector.translate(searchedWord);
				//repository of category or name
				reposit = inspector.auto_categorize(pService,words_map.get("searching"),Arrays.asList(new String[]{"PRODUCT"}));
				
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
				List<HashMap<String, Object>> raw_list = pService.selectProductList(reposit);
				//중복 제거
				QueryUtil query = new QueryUtil();
				raw_list = query.unoverlap(raw_list, "PCODE");
				logger.debug("mesg {raw_list="+raw_list+"}", "debug");
				//정렬 기준 선택
				String order_criteria = ordering_info.split(":")[0];
				String direction = ordering_info.split(":")[1];
					
				//페이징 처리
				pList = raw_list.stream().sorted(ComparatorFactory.generate(order_criteria, direction)) //정렬
						   .skip((cur_page-1)*paging_quantity).limit(paging_quantity).collect(Collectors.toList()); //페이징->리스트
				//페이지 갯수 저장
				model.addAttribute("page_size", (raw_list.size()%paging_quantity>0)?Math.floor((raw_list.size()/paging_quantity)+1):raw_list.size()/paging_quantity );
				model.addAttribute("items_size", raw_list.size());

				Iterator<HashMap<String, Object>> ite = raw_list.iterator();
				
				//상품 이미지 다운
				Callable<String> run = ()->{
				while(ite.hasNext()) {
					try {
					HashMap<String, Object> product =  ite.next();	
					if(product.get("PIMAGE_BYTES")==null)throw new Exception("응 없어");
					
					String styletop = product.get("STYLETOP").toString();
					String stylemid = product.get("STYLEMID").toString();
					String stylebot = product.get("STYLEBOT").toString();
					String pimage = product.get("PIMAGE").toString();
						
					String path = "Content/img/shoes/"+stylemid+"/"+stylebot+"/"+pimage;
					File file = Paths.get(context.getRealPath(path)).toFile();
					
					if(!file.exists()) {
					FileChannel outChannel = 
							FileChannel.open(Paths.get(context.getRealPath(path)), 
											 StandardOpenOption.CREATE, StandardOpenOption.WRITE);
						
					Blob pimage_bytes = (Blob)product.get("PIMAGE_BYTES");
			
					byte[] bs =  pimage_bytes.getBytes(1L, (int)pimage_bytes.length());
					ByteBuffer buff = ByteBuffer.allocate(bs.length);
					buff.put(bs);
					buff.flip();
					outChannel.write(buff);
					
					outChannel.close();
					}	
					}catch (Exception e) {
						logger.debug(e.getMessage());
					}
					
				}
					return "완료";
				};
				
				int processCnt = Runtime.getRuntime().availableProcessors();
				ExecutorService manager = Executors.newFixedThreadPool(processCnt);
				List<Callable<String>> tasks = new ArrayList<Callable<String>>();
				
				for(int i = 0; i<raw_list.size(); i++) {
					tasks.add(run);
				}
				manager.invokeAll(tasks);
				/*상세 검색 관련*/
				
				
				//extract column
				List<HashMap<String, Object>> repo = new ArrayList<HashMap<String,Object>>();
				for(HashMap<String, Object> indiv :raw_list) {
					repo.addAll(pService.selectProduct_info(indiv));
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
					model.addAttribute(key, mapper.readValue(ne, List.class));
				}
				//클릭된 리스팅셋업 저장
				HashMap<String, Object> clicked = (HashMap<String, Object>)session.getAttribute("clicked");
				if(clicked!=null) {
					model.addAttribute("clicked", new JSONObject(inspector.render(clicked, Language.Korean) ));					
				}
				
				//스타일 미드에 스타일 봇 바인딩
				raw_list = raw_list.stream().sorted(ComparatorFactory.generate("STYLEBOT")).collect(Collectors.toList());
				HashMap<String, Object> binded = query.bind(raw_list, "STYLEMID",new String[] {"STYLEBOT"});
					//한글로 번역 & json 파싱 => 저장
				JSONObject sonsang = new JSONObject(inspector.render(binded, Language.Korean));
				model.addAttribute("BINDING", sonsang);
				
				//inserting keyword to ranking
				if(source.equals("input")) {
					for(String word: words_map.get("ranking")) {
						if(rser.updateRanking(word)==0)rser.insertRanking(word); 
					}
				}//end_rank
			}// end_ser
		
		} catch (CustomException e) {
			logger.debug("mesg{"+e.getMessage()+"}", "debug");
		} catch(IOException e){
			logger.debug("mesg{"+e.getMessage()+"}", "debug");
			e.printStackTrace();
		} catch (Exception e) {
			logger.debug("mesg{"+e.getMessage()+"}", "debug");
			e.printStackTrace();
		}
		//with jsp
				//세션 처리용
			session.setAttribute("prev_stack", 
					MapParamInputer.setOb("listing_setup", (reposit==listing_setup)?listing_setup:reposit ,"back_word",searchedWord)  );
				//화면 구현용
			model.addAttribute("searchedWord", searchedWord);
			model.addAttribute("source", source);
				
			model.addAttribute("pList", pList);
			model.addAttribute("cur_page", cur_page);
			model.addAttribute("paging_quantity", paging_quantity);
			
			model.addAttribute("ordering_info", ordering_info);
			model.addAttribute("refresh", refresh);
		
			return "/Content/product_list/productList";
	}

}
