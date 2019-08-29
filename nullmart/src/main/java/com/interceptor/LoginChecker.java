package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dto.MemberDTO;
import com.model.service.MemberService;
@Component
public class LoginChecker implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean result = true;
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO)session.getAttribute("login");
		if(member==null) {
//			response.sendRedirect("/null/main");
			member = new MemberDTO();
			member.setUserid("broth59");
			member.setUsername( "고명진");
			member.setPhone1( "010");
			member.setPhone2("9938");
			member.setPhone3("2134");
			member.setEmail1("broth59");
			member.setEmail2("naver.com");
			session.setAttribute("login", member);
		}else {
		
		}
		return result;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
}
