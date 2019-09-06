package com.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.dto.UploadDTO;



@Controller 
public class FileUploder {
	@Autowired
	ServletContext ctx;

	//Get
	@RequestMapping(value="/",method=RequestMethod.GET) 
	public String xxx() {

		return"uploadForm";
	}
	@RequestMapping(value="/upload",method=RequestMethod.POST) 
	public String xxx2(UploadDTO dto ) {

//		String theText = dto.getTheText();
		CommonsMultipartFile theFile = dto.getTheFile();
		System.out.println(dto);
		//String s1 = map.get("first");
//		String s2 = map.get("stylemid");
//		String s3 = map.get("stylebot");
	//	String s4 = map.get("profile_pt");
		String s1 = dto.getStylemidSelect();
		String s2 = dto.getStylebotSelect();
		
		long size = theFile.getSize();
		String name = theFile.getName();
		String OriginalFilename = theFile.getOriginalFilename();
		String contentType = theFile.getContentType();
		System.out.println("size:"+size+"name:"+name+"OriginalFilename:"+OriginalFilename+"contentType:"+contentType);
		String st =  ctx.getRealPath("/Content/img/shoes/"+s1+"/"+s2);
		//File f = new File(st+"/"+OriginalFilename);
		File f = new File(OriginalFilename);
		String abs = System.getProperty("user.dir");
		
		System.out.println(abs);
		  //File f = new File("C://upload",OriginalFilename); f.mkdir();
		 ///폴더 생성
		
		try {
			theFile.transferTo(f);           //예외처리필요
		} catch (IllegalStateException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}//예외처리 필요
				
		return"uploadInfo";
	}
	
	
	
}
