package com.controller.mypage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dto.MemberDTO;
import com.dto.RegAddrDTO;
import com.model.service.MyPageService;

@Controller
public class MyPageAddrController {
	@Autowired
	MyPageService service;
	/*
	 * 배송지 리스트 출력
	 * session: member 객체 반환
	 * addrList: 배송지 리스트 출력 페이지 
	 * */
	@RequestMapping(value = "/mypage/addrList",method = RequestMethod.GET)
	public String goAddrList(HttpSession session) {
		MemberDTO member = (MemberDTO)session.getAttribute("login");
		String userid = member.getUserid();
		List<RegAddrDTO> regAddrDTOs = service.getAddrList(userid);
		session.setAttribute("addrList", regAddrDTOs);
		return "/Content/mypage/addrlist";
	}
	
	/*
	 * 배송지 등록
	 * regAddrDTO: 배송지 정보 저장
	 * session: userid 반환
	 * addrList request 매핑: 배송지 리스트 출력 요청 
	 * */
	@RequestMapping(value = "/mypage/regAddr")
	public String processRegAddr(RegAddrDTO regAddrDTO,HttpSession session) {
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("login");
		regAddrDTO.setUserid(memberDTO.getUserid());
		System.out.println("regAddrDTO"+regAddrDTO);
		service.addAddr(regAddrDTO);
		return "redirect:/mypage/addrList";
	}
	
	
	/*
	 * 배송지 변경 
	 * delivno: 배송지 번호
	 * modifyaddr.jsp 배송지 변경 폼
	 * */
	@RequestMapping(value = "/mypage/modifyAddr")
	public ModelAndView modifyAddr(@RequestParam String delivno) {
		ModelAndView mav = new ModelAndView();
		RegAddrDTO dto = service.searchByNo(delivno);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("delivno", String.valueOf(dto.getDelivno()));
		map.put("delivname", dto.getDelivname());
		map.put("delivperson", dto.getDelivperson());
		map.put("address1", dto.getAddress1());
		map.put("address2", dto.getAddress2());
		map.put("post",dto.getPost());
		map.put("userid",dto.getUserid());
		String phone11 = null;
		String phone12 = null;
		String phone13 = null;
		String phone21 = null;
		String phone22 = null;
		String phone23 = null;
		if(dto.getPhone1().equals("없음")) {
			phone11 = "";
			phone12 = "";
			phone13 = "";
		}else {
			String[] phone1 = dto.getPhone1().split("-");
			phone11=phone1[0];
			phone12=phone1[1];
			phone13=phone1[2];
		}
		String[] phone2 = dto.getPhone2().split("-");
		phone21=phone2[0];
		phone22=phone2[1];
		phone23=phone2[2];
		
		map.put("phone11", phone11);
		map.put("phone12", phone12);
		map.put("phone13", phone13);
		map.put("phone21", phone21);
		map.put("phone22", phone22);
		map.put("phone23", phone23);
		mav.addObject("addr", map);
		mav.setViewName("/Content/mypage/modifyaddr");
		return mav;
	}
	
	/*
	 * 배송지 정보 변경 
	 * dto : 배송지 정보 저장
	 * addrList로 이동 
	 * */
	@RequestMapping(value = "/mypage/modifyAddrConfirm")
	public String modifyAddrConfirm(RegAddrDTO dto) {
		service.modifyAddr(dto);
		return "redirect:/mypage/addrList";
	}
	
	/*
	 * 배송지 삭제 
	 * 
	 * addrList로 이동 
	 * */
	@RequestMapping(value = "/mypage/deleteAddr")
	public String deleteAddr(@RequestParam String delivnos) {
		String[] strDelivnos = delivnos.split("-");
		List<String> delivnoList = Arrays.asList(strDelivnos);
		service.deleteDelivnos(delivnoList);
		return "redirect:/mypage/addrList";
	}
}
