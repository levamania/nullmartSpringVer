package com.controller.mypage;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.MemberDTO;
import com.dto.OrderDTO;
import com.exception.ModifyUserInfoException;
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
		return "/Content/mypage/modifyaccountinfo";
	}
	
	//개인정보 수정 작업
	//ajax 포스트 처리 
	@RequestMapping(value = "/mypage/modifyAccountConfirm")
	@ResponseBody
	public String goModifyAccountConfirm(@RequestParam Map<String, String> member,HttpSession session) {
		System.out.println(member);
		try {
			service.modifyAccountInfo(member);
			MemberDTO login = service.searchMemberById(member.get("userid"));
			System.out.println(login);
			session.setAttribute("login", login);
			return "0";
		} catch (ModifyUserInfoException e) {
			System.out.println(e.getMessage());
			return "1";
		}
	}
	
	//임시 개인정보 이동 페이지 
	//추후 삭제 필요 
	@RequestMapping(value = "/go")
	public String goModifyAccountCheckPage(HttpSession session) {
		//가성의 로그인 세션 생성
		String userid1="asd123";
		String passwd="asd123";
		String username="이쑤신";
		String sex="XY";
		String email1="asd123";
		String email2="naver.com";
		String post="06097";
		String addr1="서울 강남구 광평로 61 (일원동)";
		String addr2="서울 강남구 일원동 722-1";
		String addr3="222호";
		String phone1="010";
		String phone2="7845";
		String phone3="5433";
		MemberDTO dto = new MemberDTO(userid1, passwd, username, sex, email1, email2, post, addr1, addr2, addr3, phone1, phone2, phone3);
		session.setAttribute("login", dto);
		return "/Content/mypage/modifyaccountcheck";
	}
	
}
