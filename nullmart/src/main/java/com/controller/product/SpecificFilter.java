package com.controller.product;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.ConfigGuide;
import com.util.Language;
import com.util.WordInspector;

@WebServlet("/SpecificFilter")
public class SpecificFilter extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String selected_atoms = request.getParameter("selected_atoms");
		//유틸 셋팅
		WordInspector inspector = new WordInspector(new File(ConfigGuide.getPath()+"/Content/configuration/subsitution_dictionary.json"));
		
		//검색단어 가공 - json 파싱
		ObjectMapper mapper = new ObjectMapper();
		String transed = inspector.render(selected_atoms, Language.English);
		HashMap<String, Object> atom_lists = mapper.readValue(transed, HashMap.class);
			//루핑용 카피
		HashMap<String, Object> copy = (HashMap<String, Object>)atom_lists.clone();

		HttpSession session = request.getSession();
			//클릭된 셋팅저장
			if(session.getAttribute("clicked")!=null)session.removeAttribute("clicked");
			session.setAttribute("clicked", copy);
			//이전 스택
		HashMap<String, Object> prev_stack = (HashMap<String,Object>)session.getAttribute("prev_stack");
			//갈무리된 검색 조건
		HashMap<String, Object> established =  null;
		if(session.getAttribute("basic_setup")==null) {
			HashMap<String, Object> set_up = (HashMap<String, Object>)prev_stack.get("listing_setup"); //기존 셋업과 합쳐야한다.
			established = set_up;
			session.setAttribute("basic_setup",  (HashMap<String, Object>)set_up.clone());
		}else {
			established = (HashMap<String, Object>)session.getAttribute("basic_setup");
		}
		
		//합치기
		for(String key : copy.keySet()) {
			for(String infe : established.keySet()) {
				if(established.get(infe)!=null) {
					List<String> lit_main  = (List<String>)atom_lists.get(key);
					List<String> lit_sub  = (List<String>)established.get(infe);
					if(key.equals(infe)) {
						if(!lit_main.get(0).equals(lit_sub.get(0)))lit_main.addAll(lit_sub);				
					}else {
						atom_lists.put(infe, lit_sub);
					}
				}
			}
		}
		System.out.println(atom_lists);
		prev_stack.remove("listing_setup"); //기존 셋업삭제
		prev_stack.put("listing_setup", atom_lists);//셋팅 재설정 완료
		
		//디스패치
		RequestDispatcher dis = request.getRequestDispatcher("/ProductListingServlet");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
