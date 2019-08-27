package com.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.model.dao.CartDAO;
import com.util.MapParamInputer;

@Service
public class CartService {
	@Autowired
	 CartDAO dao ;

	// SELECT CARTLIST
	public List<HashMap<String, Object>> selectCartList(HashMap<Object, Object> reposit) {
		return dao.selectCartList(reposit);
	}

	// DML -- 복합 UPDATE
	@Transactional
	public int stackProduct(List<HashMap<String, Object>> reposits) {
		int result = dao.stackProduct(reposits);
		ProductService service = new ProductService();
		service.updateProducts(MapParamInputer.set("list", reposits, "direction", "minus"));
		return result;
	}

	@Transactional
	public int deleteCart(List<HashMap<String, Object>> list) {
		int result = dao.deleteCart(list);
		ProductService service = new ProductService();
		service.updateProducts(MapParamInputer.set("list", list, "direction", "plus"));
		return result;
	}

	@Transactional
	public int updateCart(List<HashMap<String, Object>> list) {
		int result = dao.upadateCart(list);
		ProductService service = new ProductService();
		if (result != service.updateProducts(MapParamInputer.set("list", list, "direction", "plus")))result = 0;
		return result;
	}

}
