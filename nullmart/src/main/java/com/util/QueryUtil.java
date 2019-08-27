package com.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dto.ProductDTO;

public class QueryUtil {
	
	private Logger logger = LoggerFactory.getLogger(QueryUtil.class);
	//stream용 변수
	private String key;
	
	/**
	 * 
	 * 		db에서 가져온 리스트를 한 컬럼의 데이터로 맵핑하는 메소드이다.
	 *		기준을 삼을 컬럼의 데이터는 정렬이 필요하므로 맵퍼에서 정렬을 하던, ComparatorGenarator을 통해 정렬을 하도록하자
	 * 
	 * @param list DB에서 hashmap으로 리턴된 List
	 * @param main 바인딩 기준
	 * @param sub 기준에 맞춰 바인딩될 정보들
	 * @return 
	 */
	public HashMap<String,Object> bind(List<HashMap<String,Object>> list,String main, String[] sub){
		List<HashMap<String,Object>> temp_list = new ArrayList<HashMap<String,Object>>();
		for(HashMap<String,Object> atom : list) {
			temp_list.add(atom);
		}
		//구분자 추가
		temp_list.add(MapParamInputer.set(main,"UNTOUCHABLE"));
		
		HashMap<String, Object> mapper = new HashMap<String, Object>();
		ArrayList<HashMap<String, Object>> binding = null;
		String prev = "initial";
		for(HashMap<String, Object> stock :temp_list) {
			//색상={"size:amount",..} 맵만들기
			String curr = stock.get(main).toString();
			HashMap<String, Object> info = new HashMap<String, Object>(); //자동형변환을 기대
			for(String str:sub) {
				info.put(str, stock.get(str)); 
			}
			
			if(!curr.equals(prev)) {
				if(binding!=null) {
					mapper.put(prev, binding);
				}
				binding = new ArrayList<>();
				prev = curr;
			}
			binding.add(info);
			
		}
		return mapper;
	}
	/**
	 * 
	 *		맵핑될 서브파라미터를 지정하지 않았을 경우 자동적으로 모든 서브 파라미터를 
	 *		맵핑하는 bind 오버로딩용 메소드
	 * 
	 * @param list
	 * @param main
	 * @return
	 */
	public HashMap<String,Object> bind(List<HashMap<String,Object>> list,String main){
		//self instance
		QueryUtil query = new QueryUtil();
		//column head만 가져옴
		Set<String> set =  query.extractColumn(list).keySet();
		String [] string_array = new String[set.size()];
		int count = 0;
		//문자열 배열에 헤드 저장
		for(String string : set) {
			string_array[count]=string; count++;
		}
		
		return query.bind(list, main, string_array);
	}
	
	
	/**
	 *
	 * 지정된 컬럼데이터를 기준으로 한 레코드만 남기는 메소드
	 * 
	 * @param list 해쉬맵 리스트 from DB
	 * @param criteria 중복제거 기준 컬럼
	 * @return
	 */
	//지정된 기준의 데이터의 중복을 제거하는 메소드
	public List<HashMap<String,Object>> unoverlap(List<HashMap<String,Object>>  list, String criteria) {
		ArrayList<HashMap<String,Object>> temp = new ArrayList<HashMap<String,Object>>(); 
		String prev = "inital";
		//중복 제거
		for(HashMap<String,Object> map : list) {
			String curr = map.get(criteria).toString();
			if(!prev.equals(curr)) {
				prev = curr;
				temp.add(map);
			}
		}
		return (List<HashMap<String,Object>>)temp;	
	}
	/**
	 * 가져온 해쉬맵 리스트에서 컬럼별로 분리하여 컬럼의 데이터를 중복제거해
	 * 다시 맵핑하는 메소드
	 * 
	 * 
	 * @param list 해쉬맵 리스트 from DB
	 * @return column 별칭: 중복제거와 정렬된 column데이터 리스트
	 */
	public HashMap<String,Object> extractColumn(List<HashMap<String,Object>> list){		
		HashMap<String, Object> map = new HashMap<String, Object>();
		Set<String> column_set =  list.get(0).keySet();
		for(String temp_key: column_set) {
			this.key = temp_key;
			List<Object> temp_list = list.stream().map(m->m.get(this.key))
												   .sorted((o1,o2)->{
													   Object alpha = o1;
													   int result = 0;
													   if(alpha instanceof Date) {
														     Date date1 = (Date)alpha;
															 result =  ((Date) alpha).compareTo((Date)o2); 
														  }else if(alpha instanceof Integer || alpha instanceof Float || alpha instanceof Double) {
															  result =  Integer.compare((Integer)alpha,(Integer)o2);
														  }else {
															  result = alpha.toString().compareTo(o2.toString());
														  }
													   return result;
												   }).distinct().collect(Collectors.toList());
			map.put(temp_key, temp_list);
		}
		
		return map;
	}
	/**
	 * request를 넘겨줬을때 자동으로 리스트를 저장하는 extractColumn 오버로딩용 메소드
	 * 
	 * @param list
	 * @param request
	 * @return
	 */
	public HashMap<String,Object> extractColumn(List<HashMap<String,Object>> list, HttpServletRequest request){		
		HashMap<String, Object> map = new HashMap<String, Object>();
		Set<String> column_set =  list.get(0).keySet();
		for(String temp_key: column_set) {
			this.key = temp_key;
			List<Object> temp_list = list.stream().map(m->m.get(this.key))
												   .sorted((o1,o2)->{
													   Object alpha = o1;
													   int result = 0;
													   if(alpha instanceof Date) {
														     Date date1 = (Date)alpha;
															 result =  ((Date) alpha).compareTo((Date)o2); 
														  }else if(alpha instanceof Integer || alpha instanceof Float || alpha instanceof Double) {
															  result =  Integer.compare((Integer)alpha,(Integer)o2);
														  }else {
															  result = alpha.toString().compareTo(o2.toString());
														  }
													   return result;
												   }).distinct().collect(Collectors.toList());
			map.put(temp_key, temp_list);
			request.setAttribute(temp_key, temp_list);
		}
		
		return map;
	}
}
