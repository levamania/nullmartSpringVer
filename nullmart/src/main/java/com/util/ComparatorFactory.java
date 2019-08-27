package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dto.ProductDTO;

public class ComparatorFactory {

	private static Logger logger;
	
	/**
	 * 스트림에서 로컬변수를 사용하지 못하기에 생성한 메소드
	 * 
	 * @param order_criteria
	 * @param direction
	 * @return
	 */
	
	public static Comparator<HashMap<String, Object>> generate(String order_criteria, String direction){
		String temp = direction.toLowerCase();
		int reversal = 1;
		if(temp.equals("desc"))reversal = -1;
		return implicit_generate(order_criteria,reversal);
	}
	//오버로딩용 -- 기준입력 없을시 자동오름차순
	public static Comparator<HashMap<String, Object>> generate(String order_criteria){
		int reversal = 1;
		return implicit_generate(order_criteria,reversal);
	}
	
	
	
	/**
	 * 
	 * 해쉬맵 리스트의 특정컬럼과 정렬방법(오름차순,내림차순)을 넣어주면
	 * 스트림에서 사용할 comparator를 생산하는 메소드
	 * 
	 * @param order_criteria
	 * @param reversal
	 * @return
	 */
	
	private static  Comparator<HashMap<String, Object>> implicit_generate(String order_criteria, int reversal){
		//로그 셋팅
		logger = LoggerFactory.getLogger(ComparatorFactory.class);
		//정렬 기준 가공
		
		Comparator<HashMap<String, Object>> comparator = null;
		
		comparator = (h1,h2)->{
			Object alpha = h1.get(order_criteria);
			Object beta = h2.get(order_criteria);
			
			int result = 0;
			
			if(alpha instanceof Date) {
				Date tem1 = (Date)alpha; Date tem2 = (Date)beta;
				result = tem1.compareTo(tem2);
			}else if(alpha instanceof Integer || alpha instanceof Float || alpha instanceof Double) {
				result = Integer.compare((Integer)alpha,(Integer)beta);
			}else {
				result = alpha.toString().compareTo(beta.toString());
			}
			
			return result * reversal; 
				
		};
		
		return comparator;
	}
}
