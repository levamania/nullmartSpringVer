package com.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.model.service.ProductService;

import oracle.sql.BLOB;

@Controller
public class MultiTester {
	
	@Autowired
	ProductService pser;
	
	@RequestMapping("/ins")
	public String profile(@RequestParam(value = "report", required = false) CommonsMultipartFile file,
											HttpServletRequest request) {
		HashMap<String, Object> reposit = new HashMap<String, Object>();
		reposit.put("name", file.getName());
		reposit.put("bytes",file.getBytes());
		
		Object obj = new Object();
		BLOB blob  = (BLOB)obj;
		blob.get
		
		return null;
	}
}
