package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.service.ProductService;
import com.model.service.RankingService;

@WebServlet("/RankingInfoServlet")
public class RankingInfoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//model 연동
		RankingService service = new RankingService();
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
