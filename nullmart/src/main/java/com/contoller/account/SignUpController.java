package com.contoller.account;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.ManagerDTO;
import com.dto.MemberDTO;
import com.model.service.ManagerService;
import com.model.service.MemberService;

@Controller
public class SignUpController {

	@Autowired
	MemberService service;
	
	//일반 SignUp
	@RequestMapping(value = "/SignUp")
	public String login(@RequestParam Map<String, String> map) {

		int n = service.memberAdd(map);

		return "/Content/account/signup_last";
	}
	//아이디중복체크
	@RequestMapping(value = "/idCheck")
	@ResponseBody
	public String idCheck(@RequestParam Map<String, String>map) {
		int n = service.idCheck(map);
		if(n==1) {
			return "1";
		}else {
			return "0";
		}
	
	}
	
	//이메일 중복체크

	@RequestMapping(value = "/emailCheck")
	@ResponseBody
	public String emailCheck(@RequestParam Map<String, String>map) {
		int n = service.emailCheck(map);
		System.out.println("번호는?"+n);
		if(n==1) {
			return "1";
		}else {
			return "0";
		}
		
	}


}
