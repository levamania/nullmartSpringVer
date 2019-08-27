package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Generated;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dto.MemberDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.service.ProductService;
import com.util.ConfigGuide;
import com.util.MapParamInputer;

@Controller 
public class MainServlet {
	Logger logger = LoggerFactory.getLogger(MainServlet.class);
	
	@RequestMapping(value = "/main")
	public String Main(HttpSession session )  {
		MemberDTO member = new MemberDTO();
		member.setUserid("broth59");
		session.setAttribute("login", member);
		//with model
		ProductService service = new ProductService();
		//with jsp
		return "/Content/main/main";
	}

}
