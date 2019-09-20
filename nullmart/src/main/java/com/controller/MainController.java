package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dto.MemberDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.service.ProductService;
import com.util.ConfigGuide;
import com.util.Language;
import com.util.MapParamInputer;
import com.util.QueryUtil;
import com.util.WordInspector;

@Controller 
public class MainController {
	Logger logger = LoggerFactory.getLogger(MainController.class);
	@Autowired
	private ProductService pser;
	@Autowired
	private ServletContext context;
	@Autowired
	private QueryUtil query;
			
	@RequestMapping(value = "/main")
	public String Main(Model model ) throws JsonParseException, JsonMappingException, IOException  {
	WordInspector inspector =
				new WordInspector(new File(context.getRealPath("Content/configuration/subsitution_dictionary.json")));;  
		
		String[][] category = new String[][] {{"STYLEMID","tennis"}, {"STYLEMID", "shoe"}};
		List<HashMap<String,Object>> whole_list = new ArrayList<>();
		
		for(String[] atom : category) {
			List<HashMap<String,Object>> featured_list =
					pser.selectProductList(MapParamInputer.set(atom[0], Arrays.asList(atom[1]) ));
			featured_list = query.unoverlap(featured_list, "PCODE");
			whole_list.add(MapParamInputer.set("featured_list", featured_list, "STYLEMID", atom[1],
										 "TITLE", inspector.render(atom[1], Language.Korean)));
		}
		
		model.addAttribute("WHOLE_LIST", whole_list);
		
		return "/Content/main/main";
	}

}
