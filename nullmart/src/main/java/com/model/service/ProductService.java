package com.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.dao.ProductDAO;

@Service
public class ProductService implements ModelService {
	@Autowired
	ProductDAO dao;

	@Override
	public Set<String> getKeyset(HashMap<String, Object> map) {
		return dao.getKeyset(map);
	}

	@Override
	// products info - style, name
	public List<String> getCategory(HashMap<String, Object> map) {
		return dao.getCategory( map);
	}

	// a product info - size, price, color
	public List<HashMap<String, Object>> selectProduct_info(HashMap<String, Object> map) {
		return dao.selectProduct_info(map);
	}

	// product list - searching
	public List<HashMap<String, Object>> selectProductList(HashMap<String, Object> reposit) {
		return dao.selectProductList(reposit);
	}

	// DML - UPDATE
	public int updateProducts(HashMap<Object, Object> hashMap) {
		return dao.updateProducts( hashMap);
	}

	public int insertProduct(HashMap<String, Object> reposit) {
		return dao.insertProduct(reposit);
	}

}// end class
