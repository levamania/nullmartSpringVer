package com.controller.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.MemberDTO;
import com.exception.CustomException;
import com.model.service.CartService;
import com.model.service.OrderService;
import com.util.ComparatorFactory;
import com.util.LoginIndicator;
import com.util.MapParamInputer;
import com.util.QueryUtil;


@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	CartService cser;
	@Autowired
	OrderService oser;
	
	
	@ModelAttribute("phone_list")
	public List<String> getPhone_list(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("010");list.add("011");list.add("016");list.add("017");list.add("020");list.add("030");
		return list;
		}
	
	@RequestMapping("/ui")
	@SuppressWarnings("unchecked")
	public String diplayUI(Model model, HttpSession session,
										   @RequestParam("pcodes") String pcodes) {
		// 세션 처리
		MemberDTO member = (MemberDTO) session.getAttribute("login");
		String userid = member.getUserid();
		
		//수용	
		List<String> pcode_list = Arrays.asList(pcodes.split(":"));

		// 유틸 셋팅
		QueryUtil query = new QueryUtil();
		// with model
		List<HashMap<String, Object>> cart_list = cser.selectCartList(MapParamInputer.set("USERID", userid, "PCODE_LIST", pcode_list));
		HashMap<String, Object> pcode_mapped = null;
		Set<String> key_set = null;
		List<String> cno_list = null;
		if(cart_list.size()!=0) {
			pcode_mapped = query.bind(cart_list, "PCODE");
			key_set = pcode_mapped.keySet();
			cno_list = (List<String>)query.extractColumn(cart_list).get("CNO");
		}

		// with jsp
		model.addAttribute("MEMBER",member);
		
		model.addAttribute("CART_LIST", cart_list);
		model.addAttribute("KEY_SET", key_set);
		model.addAttribute("CNO_LIST", cno_list);
		model.addAttribute("PCODE_MAPPED", pcode_mapped);

		return "/Content/order/order";
	}
	
	@RequestMapping("/book")
	public String displayBook(Model model, HttpSession session) {
		// 세션 처리
		MemberDTO member = (MemberDTO) session.getAttribute("login");
		String userid = member.getUserid();
		
		//with model
		List<HashMap<String, Object>> book = oser.selectBook(MapParamInputer.set("USERID", userid, "TABLE","DELIVINFO"));
		book = book.stream().sorted(ComparatorFactory.generate("DELIVNAME")).collect(Collectors.toList());
		List<HashMap<String, Object>> book_recent = oser.selectBook(MapParamInputer.set("USERID", userid, "TABLE","ORDERTABLE"));
		
		//with jsp
		model.addAttribute("BOOK", book);
		model.addAttribute("BOOK_RECENT", book_recent);
	
		
		return "/Content/order/parts/my_book";
	}

	@RequestMapping("/pay")
	public String payOrder(@RequestParam HashMap<String, Object> reposit) {
		System.out.println(reposit);
		
		/*
		cnos
		user=고명진, user_phone1=010, user_phone2=9938, user_phone3=2134, 
		user_email1=broth59, user_email2=naver.com, order_deliver=post, order_name=이수신, 
		copy=on, order_phone1=011, order_phone2=1122, order_phone3=3345, 
		order_telephone1=02, order_telephone2=1234, order_telephone3=5678, 
		order_postcode=13524, order_address1=경기 성남시 분당구 대왕판교로606번길 45 (삼평동), order_address2=경기 성남시 분당구 삼평동 653, order_address3=, 
		order_mesg=, payment=credit}
		*/
		reposit.replace("cnos", Arrays.asList(reposit.get("cnos").toString().split(",")));
		System.out.println(reposit.get("cnos"));
		
		oser.insertOrder(reposit);
		return "/Content/order/order_complete";
	}
}
