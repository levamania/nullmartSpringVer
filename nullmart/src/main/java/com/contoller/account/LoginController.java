package com.contoller.account;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		session.setAttribute("login",dto);
		return "redirect:/main";
	}
	
	@RequestMapping("/pwcheck")
	@ResponseBody
	public String loginCheck(@RequestParam Map<String, String> map) {
		String userid = map.get("userid");
		String passwd = map.get("passwd");
		int idCount = service.selectById(userid);
		if(idCount==0) {
			//아이디 없음
			return "0";
		}
		
		MemberDTO dto = service.login(map);
		if(dto==null) {
			//패스워드 없음
			return "1";
		}else {
			//통과
			return "2";
		}
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {

		session.invalidate();
		return "redirect:/main";
	}

}
