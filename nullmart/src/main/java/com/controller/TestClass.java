package com.controller;



import java.lang.reflect.Field;

import com.dto.ProductDTO;
import com.dto.StockDTO;


public class TestClass {

	public static void main(String[] args) {
		System.out.println(StockDTO.class.getFields().length);
		for(Field f: StockDTO.class.getFields()) {
			System.out.println(f);
		}
	}

}
