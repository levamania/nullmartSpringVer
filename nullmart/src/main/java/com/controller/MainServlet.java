package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.service.ProductService;
import com.util.ConfigGuide;
import com.util.MapParamInputer;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	Logger logger = LoggerFactory.getLogger(MainServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//배포정보 저장
		ConfigGuide.setPath(request);
		
		//with model
		ProductService service = new ProductService();
		//with jsp
		RequestDispatcher dis = request.getRequestDispatcher("/Content/main/main.jsp");
			//stacking data
			//shooting data
			dis.forward(request, response);
					
	}

}
