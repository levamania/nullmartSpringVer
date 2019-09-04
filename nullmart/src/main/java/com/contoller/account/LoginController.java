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
	
<<<<<<< HEAD
	@RequestMapping("/ManagerIdPwCheck")
	@ResponseBody
	public String ManagerIdPwCheck(@RequestParam Map<String, String> map) {
		String userid = map.get("masteruserid");
		String passwd = map.get("masterpasswd");
		int idCount = mgrService.ManagerIdPwCheck(userid);
		if(idCount==0) {
			//아이디 없음
			return "0";
		}
=======
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
>>>>>>> branch 'sandwich' of https://github.com/levamania/nullmartSpringVer.git
		
<<<<<<< HEAD
		ManagerDTO mgrDto = mgrService.login(map);
		if(mgrDto==null) {
			//패스워드 없음
			return "1";
=======
		if(dto!=null) {
			session.setAttribute("login",dto);
					return "redirect:/main";
					
>>>>>>> branch 'sandwich' of https://github.com/levamania/nullmartSpringVer.git
		}else {
			//통과
			return "2";
		}
	}
	
<<<<<<< HEAD
	@RequestMapping("/idPwCheck")
	@ResponseBody
	public String loginCheck(@RequestParam Map<String, String> map) {
		String userid = map.get("userid");
		String passwd = map.get("passwd");
		int idCount = service.idPwCheck(userid);
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
	//일반login
	@RequestMapping(value = "/login")
	public String login(@RequestParam Map<String, String> map,HttpSession session) {
		System.out.println(map+"map값");
		MemberDTO dto = service.login(map);
		session.setAttribute("login", dto);
		ManagerDTO manager = (ManagerDTO)session.getAttribute("managerLogin");
		if(manager!=null) {
			session.removeAttribute("managerLogin");
		}
		return "redirect:/main";
	}
	//관리자login
		@RequestMapping(value = "/managerLogin")
		public String managerLogin(@RequestParam Map<String, String> map,HttpSession session) {
			
			ManagerDTO dto = mgrService.login(map);
			session.setAttribute("managerLogin", dto);
			MemberDTO member = (MemberDTO)session.getAttribute("login");
			if(member!=null) {
				session.removeAttribute("login");
			}
			return "redirect:/Content/admin/adminMain.jsp";
		}
	
=======
	//로그아웃
>>>>>>> branch 'sandwich' of https://github.com/levamania/nullmartSpringVer.git
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {

		session.invalidate();
		return "redirect:/main";
	}
	
	@RequestMapping(value = "/mgrLogout")
	public String mgrLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/main";
	}
	@RequestMapping(value = "/phoneSearchPw")
	@ResponseBody
	public String phoneSearchPw(@RequestParam Map<String, String> map) {
		int n = service.searchPwPhone(map);
		if(n==0) {
			return "0";
		}else {
			return "1";
		}
		
	}

}
