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
	public String diplayUI(Model model, HttpSession session,
										   @RequestParam("pcodes") String pcodes) {
		// 세션 처리
		MemberDTO member = (MemberDTO) session.getAttribute("login");
		String userid = member.getUserid();
		
		//수용	
		List<String> pcode_list = Arrays.asList(pcodes.split(":"));

		// 유틸 셋팅
		QueryUtil util = new QueryUtil();
		// with model
		List<HashMap<String, Object>> cart_list = cser.selectCartList(MapParamInputer.set("USERID", userid, "PCODE_LIST", pcode_list));
		HashMap<String, Object> pcode_mapped = null;
		Set<String> key_set = null;
		if(cart_list.size()!=0) {
			pcode_mapped = util.bind(cart_list, "PCODE");
			key_set = pcode_mapped.keySet();
		}

		// with jsp
		model.addAttribute("MEMBER",member);
		
		model.addAttribute("CART_LIST", cart_list);
		model.addAttribute("KEY_SET", key_set);
		model.addAttribute("PCODE_MAPPED", pcode_mapped);

		return "/Content/order/order";
	}
	
	@RequestMapping("/book")
	public String displayBook(Model model, HttpSession session) {
		// 세션 처리
		MemberDTO member = (MemberDTO) session.getAttribute("login");
		String userid = member.getUserid();
		
		//with model
		List<HashMap<String, Object>> book = oser.selectBook(MapParamInputer.set("USERID", userid));
		book = book.stream().sorted(ComparatorFactory.generate("DELIVNAME")).collect(Collectors.toList());
		
		//with jsp
		model.addAttribute("BOOK", book);
	
		
		return "/Content/order/parts/my_book";
	}

}
