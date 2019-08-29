package com.contoller.account;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.ManagerDTO;
import com.dto.MemberDTO;
import com.model.service.ManagerService;
import com.model.service.MemberService;

@Controller
public class LoginController {

	@Autowired
	MemberService service;
	
	@Autowired
	ManagerService mgrService;
	

	@RequestMapping(value = "/IdPwCheck")
	@ResponseBody
	public int idPwCheck(@RequestParam Map<String, String> map) {
		System.out.println(map);
		int num = service.IdPwCheck(map);
		System.out.println(num);
		return num;
	}
	
	//메니저로그인
	@RequestMapping(value = "/managerLogin")
	public String managerLogin(@RequestParam Map<String,String> map,HttpSession session) {
		//System.out.println(map);
		ManagerDTO dto = mgrService.managerLogin(map);
		
		if(dto!=null) {
			session.setAttribute("login",dto);
					return "redirect:/Content/admin/adminMain.jsp";
					
		}else {
			
			return "/Content/account/loginForm";
		}
	}
	//일반회원 로그인
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
	
	//로그아웃
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {

		session.invalidate();
		return "redirect:/main";
	}

}
