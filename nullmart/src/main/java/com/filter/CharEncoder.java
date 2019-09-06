package com.filter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.jws.WebService;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.sound.midi.Patch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.util.ConfigGuide;

@Component
public class CharEncoder implements Filter{
	@Autowired
	private ServletContext application;

    public CharEncoder() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//문자 인코딩
		request.setCharacterEncoding("utf-8");
		chain.doFilter(request, response);
		
	}

	public void init(FilterConfig fConfig) throws ServletException {	}

}
