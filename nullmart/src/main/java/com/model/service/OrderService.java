package com.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.dao.OrderDAO;

@Service
public class OrderService {
	@Autowired
	private OrderDAO dao;

	//BOOK - QUERY
	public List<HashMap<String, Object>> selectBook(HashMap<Object, Object> reposit) {
		return dao.selectBook(reposit);
	}

	public int insertOrder(HashMap<String, Object> reposit) {
		return dao.insertOrder(reposit);
	}

	
	
	
}
