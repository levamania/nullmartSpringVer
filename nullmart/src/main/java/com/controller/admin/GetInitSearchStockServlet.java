package com.controller.admin;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dto.ProductDTO;
import com.model.service.AdminService;

@WebServlet("/GetInitSearchStockServlet")
public class GetInitSearchStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		AdminService service = new AdminService();
		// 검색 키워드 생성
		
		String pname = request.getParameter("pname");
		if (pname != null) { 
			ProductDTO product = service.searchProduct(pname);
			if(product==null) {
				out.print(0);
			}else {
				JSONObject productInfo = new JSONObject();
				productInfo.put("pcode", product.getpCode());
				productInfo.put("styletop", product.getStyleTop());
				productInfo.put("stylemid", product.getStyleMid());
				productInfo.put("stylebot", product.getStyleBot());
				productInfo.put("pregitdate", product.getpRegitDate());
				out.print(productInfo);
			}
		}else {
			//JSONArray를  저장할 jsonobject
			JSONObject jsonObject = new JSONObject();
			Map<String, List<String>> map = service.getSearchStockOptions();
			//style
			List<String> styletopList = map.get("styletop");
			List<String> stylemidList = map.get("stylemid");
			List<String> stylebotList = map.get("stylebot");
			System.out.println(stylebotList);
			//keyword
			List<String> keywordList = service.searchPname();
			//분류에 따른 select option 검색 
			
			
			JSONArray keywords = new JSONArray();
			JSONArray styletops = new JSONArray();
			JSONArray stylemids = new JSONArray();
			JSONArray stylebots = new JSONArray();
			
			styletopList.stream().forEach(s->{
				JSONObject obj = new JSONObject();
				obj.put("option", s);
				styletops.add(obj);
			});
			
			stylemidList.stream().forEach(s->{
				JSONObject obj = new JSONObject();
				obj.put("option", s);
				stylemids.add(obj);
			});
			
			stylebotList.stream().forEach(s->{
				JSONObject obj = new JSONObject();
				obj.put("option", s);
				stylebots.add(obj);
			});

			keywordList.stream().forEach(s -> {
				JSONObject obj = new JSONObject();
				obj.put("keyword", s);
				keywords.add(obj);
			});

			jsonObject.put("keywords", keywords);
			jsonObject.put("styletops", styletops);
			jsonObject.put("stylemids", stylemids);
			jsonObject.put("stylebots", stylebots);
			out.print(jsonObject);
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
