package com.controller.mypage;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.MemberDTO;
import com.model.service.MyPageService;

@Controller
public class MyPageAccountController {
	@Autowired
	private MyPageService service;
	
	//password check 응답 
	//json객체 전달 받음
	@RequestMapping(value = "/mypage/checkpassword",method=RequestMethod.POST)
	@ResponseBody
	public String checkpassword(@RequestParam Map<String,String> map) {
		int num = service.searchPwdById(map);
		return num==0?"failure":"success";
	}
	
	
	//패스워드 유효성 검사 후 개인정보 검색
	//개인 정보 수정 페이지로 이동 
	@RequestMapping(value = "/mypage/modifyAccount", method = RequestMethod.GET)
	public String goModifyAccount(HttpServletRequest request,String userid) {
		System.out.println(userid);
		MemberDTO member = service.searchMemberById(userid);
		request.setAttribute("member", member);
		System.out.println(member);
		return "/Content/mypage/modifyaccountinfo";
	}
	
	//
	
}
