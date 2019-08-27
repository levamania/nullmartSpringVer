package com.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dto.ProductDTO;
import com.dto.StockDTO;
import com.model.dao.ProductDAO;

public class ProductService implements ModelService {
	ProductDAO dao;
	//DAO get
	public ProductService(){
		dao = new ProductDAO();
	}
		
	@Override
	public Set<String> getKeyset(HashMap<String, Object> map) {
		Set<String> set = null;
		SqlSession session = null;
		try {
			session = MySqlSessionFactory.getSession();
			set = dao.getKeyset(session,map);
			session.commit();
		}finally {
			if(session!=null)session.close();
		}
		return set;
	}

	@Override
	//products info - style, name 
	public List<String> getCategory(HashMap<String, Object> map) {
		List<String> list = null;
		SqlSession session = null;
		try {
			session = MySqlSessionFactory.getSession();
			list = dao.getCategory(session,map);
			session.commit();
		}finally {
			if(session!=null)session.close();
		}
		return list;
	}
	// a product info - size, price, color 
		public List<HashMap<String, Object>> selectProduct_info(HashMap<String, Object> map) {
			List<HashMap<String, Object>> list = null;
			SqlSession session = null;
			try {
				session = MySqlSessionFactory.getSession();
				list = dao.selectProduct_info(session,map);
				session.commit();
			}finally {
				if(session!=null)session.close();
			}
			return list;
		}
	
	

	//product list - searching
	public List<HashMap<String, Object>> selectProductList(HashMap<String, Object> reposit) {
		List<HashMap<String, Object>> list = null;
		SqlSession session = null;
		try {
			session = MySqlSessionFactory.getSession();
			list = dao.selectProductList(session,reposit);
			session.commit();
		}finally {
			if(session!=null)session.close();
		}
		return list;
	}

	
	//DML - UPDATE
	public int updateProducts(HashMap<Object, Object> hashMap) {
		SqlSession session = null;
		int result = 0;
		try {
			session = MySqlSessionFactory.getSession();
			result = dao.updateProducts(session,hashMap);
			session.commit();
		}finally {
			if(session!=null)session.close();
		}
		return result;
	}

}//end class
