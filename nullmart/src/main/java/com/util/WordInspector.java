package com.util;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.exception.CustomException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.service.ModelService;

public class WordInspector {
	
	private File dictionary;	
	private ObjectMapper mapper;
	private HashMap<String, Object> diction_reposit;
		
	public WordInspector(File dictionary) {
		this.dictionary = dictionary;
		this.mapper = new ObjectMapper();
	}
	
	private List<String> censor(String input) throws CustomException{
		//word_inspecting -- excepting special literal
		Pattern pattern = Pattern.compile("[가-힣A-Za-z]{1,10}");
		Matcher matcher = pattern.matcher(input);
				
		ArrayList<String> list = null; //1차 유효성 검사에서 살아남은 아이들
				
		while(matcher.find()) {
			String word = input.substring(matcher.start(), matcher.end());
			if(word.length()!=0) {
				if(list==null)list = new ArrayList<String>();
				list.add(word);
				}
			}
				
		if(list==null)throw new CustomException("검색가능 단어 없음");
		
		return list;
					
	}
	
	
	/**
	 *  입력한 단어를 JSON 형식으로 작성된 파일에 맞추어
	 *  번역한다
	 * 
	 * @param input 번역이 필요한 단어
	 * @return 번역된 맵과 번역되지 않은 맵 두개를 리턴
	1 * @throws IOException
	 */
	public  HashMap<String, List<String>>  translate(String input) throws IOException {
		List<String> list = this.censor(input);
			
		//file loading
		File dictionary = this.dictionary;
		HashMap<String, Object> buff = mapper.readValue(dictionary, HashMap.class);
		
		//collection copy
		ArrayList<String>copy = new ArrayList<String>();
		ArrayList<String>forRank = new ArrayList<String>();
		for(String atom: list) {copy.add(atom); forRank.add(atom);}

		for(String key : buff.keySet()) {
			List<String> word_list = (List<String>)buff.get(key); 
			String formal = word_list.get(0);
			for(String pip:word_list) {
				for(String searched:list) {
					if(searched.equals(pip)) {
						copy.remove(searched);
						copy.add(key);
						forRank.remove(searched);
						forRank.add(formal);
					}
				}	
			}	
		}
		
		return MapParamInputer.set("searching", copy, "ranking", forRank);
	}//end method

	public String render(String raw, Language target_language) throws JsonParseException, JsonMappingException, IOException{

		//json to JSONObject
		diction_reposit = mapper.readValue(dictionary, HashMap.class);
		String regEx = "\\w";
		
		if(target_language == Language.English) {
			HashMap<String, Object> eng_ver = new HashMap<String, Object>();
			regEx = "[가-힣]";
			for(String key:diction_reposit.keySet()) {
				List<String> list = (List<String>)diction_reposit.get(key);
				eng_ver.put(list.get(0), Arrays.asList(new String[] {key}));//한글:영어로 대체
			}
			diction_reposit = eng_ver;
		}
		
		String draft = raw;
		Pattern pattern = Pattern.compile(regEx+"{1,10}");
		Matcher matcher = pattern.matcher(draft);
		
//		Function<MatchResult, String> replacer = (finded)->{
//				String atom = draft.substring(finded.start(), finded.end());
//				List<String> word_list = (List<String>)diction_reposit.get(atom);
//				String replacment = atom;
//				if(word_list!=null)replacment = word_list.get(0);
//				return replacment;
//			};		
		//function을 인자로 받는 메소드가 버젼이 달라 없으므로 대체한다.
		StringBuffer buffer = new StringBuffer();
		while(matcher.find()) {
			String atom = new String(draft.substring(matcher.start(), matcher.end()));
			List<String> word_list = (List<String>)diction_reposit.get(atom);
			if(word_list!=null) {
				matcher.appendReplacement(buffer, word_list.get(0));
			}
		}
		matcher.appendTail(buffer);
		return (buffer.length()==0)?draft:new String(buffer);
	}

	public HashMap<String, Object> render(HashMap<String, Object> raw, Language target_language) throws JsonParseException, JsonMappingException, IOException{
		String censored = this.render(mapper.writeValueAsString(raw), target_language);
		return mapper.readValue(censored, HashMap.class);
	}
	
	/**
	 * 입력된 단어들을 테이블에서 검색가능하도록 카테고리를 붙여주는 작업을 한다.
	 * 먼저 테이블에서 각 컬럼명을 가져오고 그 컬럼명으로 컬럼의 데이터를 가져온다. 
	 * 입력된 단어와 가져온 데이터가 매칭될 경우 생성한 리스트에 넣고 해당 컬럼의 이름을 키값으로 하여 맵에 저장한다.
	 * 
	 * @param service
	 * @param word_list
	 * @param tables
	 * @return
	 */
	public HashMap<String,Object> auto_categorize(ModelService service, List<String> word_list, List<String> tables ){
		HashMap<String, Object> harry = new HashMap<String, Object>();
		Set<String> categories = service.getKeyset(MapParamInputer.set("TABLES",tables));
		for(String key: categories) {
			List<String> single = service.getCategory(MapParamInputer.set("HEAD",key,"TABLES",tables));
			List<String> lone = new ArrayList<String>();
			for(String atom : single) {
				for(String word : word_list) {
					if(atom.contains(word))lone.add(word);
				}
			}
			harry.put(key, (lone.size()!=0)?lone:null );
		}
		return harry;
	}
}//end class
