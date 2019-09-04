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
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.service.ProductService;
import com.model.service.RankingService;

@Controller
@RequestMapping(value = "/ranking", produces = "text/plain;charset=utf-8")
public class RankingController{
	
	@Autowired
	RankingService service;
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	@ResponseBody
	public String provide_rankingInfo() {
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
		return string;
	}

}
