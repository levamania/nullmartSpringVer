package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.util.ConfigGuide;

@WebFilter("/CharEncoder")
public class CharEncoder implements Filter {

    public CharEncoder() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//문자 인코딩
		request.setCharacterEncoding("utf-8");
		//배포 경로 설정
		ConfigGuide.setPath(request);
		chain.doFilter(request, response);
		
	}

	public void init(FilterConfig fConfig) throws ServletException {	}

}
