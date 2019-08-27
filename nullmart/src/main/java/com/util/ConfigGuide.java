package com.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigGuide {
	
	private static Logger logger = LoggerFactory.getLogger(ConfigGuide.class);
	

	public static void setPath(ServletRequest request) {
		
		//config 설정
		ServletContext application = request.getServletContext();
		
		HashMap<String, Object> config_map = 
				MapParamInputer.set("deploy_path",application.getRealPath(""));
		
		//배포 경로 기록
		ObjectMapper mapper = new ObjectMapper();		
		
		String path = "C:/NullMart";
		boolean checker = true;
		try {
			File null_dir = new File(path);
			if(!null_dir.isDirectory())checker = null_dir.mkdir();
			File server = new File(path+"/server");
			if(checker&&!server.isDirectory())checker = server.mkdir();
			File config = new File(server, "config.json");
			if(checker&&!config.isFile())checker = config.createNewFile();
			if(checker)mapper.writeValue(config, config_map);
			
		}catch (IOException e) {
			logger.debug("경로 저장에 실패하였습니다.", "debug");
			e.printStackTrace();
		}
	}//end set
	
	public static String getPath() throws JsonParseException, JsonMappingException, IOException{
		File file = new File("C:/NullMart/server/config.json");
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> map = mapper.readValue(file, HashMap.class);
		return map.get("deploy_path");
	}
	
	
	
}
