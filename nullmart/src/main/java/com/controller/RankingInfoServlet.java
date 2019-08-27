package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.service.ProductService;
import com.model.service.RankingService;

@Controller
public class RankingInfoServlet{
	
	@Autowired
	RankingService service;
	
	@RequestMapping(value = "/RankingInfoServlet", method = RequestMethod.GET)
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//model 연동
		List<String> list = service.selectRankingInfoServlet();
			//data가공
			String string = ""; int count = 1; 
			for(String str:list) {
				if(count==list.size()) {
					string = string+str;
					break;
				}
				string = string+str+":";
				count++;
			}
		//출력
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(string);
	}
	@RequestMapping(value = "/RankingInfoServlet", method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
