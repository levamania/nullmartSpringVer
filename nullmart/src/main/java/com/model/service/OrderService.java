package com.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exception.CustomException;
import com.model.dao.OrderDAO;

@Service
public class OrderService {
	@Autowired
	private OrderDAO dao;
	@Autowired
	private CartService cser;

	//BOOK - QUERY
	public List<HashMap<String, Object>> selectBook(HashMap<String, Object> reposit) {
		return dao.selectBook(reposit);
	}

	@Transactional
	public String insertOrder(HashMap<String, Object> reposit, List<HashMap<String, Object>> list) {
		int order_result = dao.insertOrder(reposit);
		int cart_result = cser.deleteCart(list, true);
		String order_serial = "nope";
		if(order_result==cart_result) {
			reposit.put("TABLE", "ORDERTABLE");
			order_serial = dao.selectBook(reposit).get(0).get("ORDER_TRANSACTION").toString();
		}else {
			throw new CustomException("주문레코드 수와 카드 레코드 수가 일치하지 않습니다");
		}
		return order_serial;
	}

	//평가 자료 
	public List<HashMap<String, Object>> selectEvaluatedes(HashMap<String, Object> reposit) {
		return dao.selectEvaluatedes(reposit);
	}

	
	
	
}
