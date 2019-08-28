package com.contoller.account;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.MemberDTO;
import com.model.service.MemberService;

@Controller
public class LoginController {

	@Autowired
	MemberService service;
	
	@RequestMapping(value = "/login")
	public String login(@RequestParam Map<String,String> map,HttpSession session) {
		//System.out.println(map);
		MemberDTO dto = service.login(map);
		
		if(dto!=null) {
			session.setAttribute("login",dto);
					return "redirect:/main";
					
		}else {
			
			return "/Content/account/loginForm";
		}
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {

		session.invalidate();
		return "redirect:/main";
	}

}
