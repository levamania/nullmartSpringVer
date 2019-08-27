package com.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
/*
 * 날짜 계산 클래스
 * 상수 필드에 맞는 값을 계산
 * HashMap<String,String> 타입의 start, end 반환
 * */
public class SearchOrderCalDate {
	public static final int TODAY=0;
	public static final int DAYS=1;
	public static final int MONTHS=2;
	public static final int YEAR=3;
	public static HashMap<String,String> getDate(String date, int mode) {
		HashMap<String, String> map = new HashMap<String, String>();
		LocalDate currentDate = LocalDate.now();
		currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String start = "";
		String end= currentDate.toString();
		switch (mode) {
		case TODAY:
			start=null;
			break;
		case DAYS:
			start= currentDate.minusDays(Integer.parseInt(date)).toString();
			break;
		case MONTHS:
			start = currentDate.minusMonths(Integer.parseInt(date)).toString();
			break;
		case YEAR:
			start = currentDate.minusYears(Integer.parseInt(date)).toString();
		default:
			break;
		}
		map.put("start", start);
		map.put("end", end);
		return map;
	}
}
