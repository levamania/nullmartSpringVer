package com.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.dto.ProductDTO;
import com.dto.StockDTO;

public class ProductDAO {

	public Set<String> getKeyset(SqlSession session, HashMap<String, Object> map) {
		List<HashMap<String, Object>> temp = session.selectList("keyset", map);
		return temp.get(0).keySet();
	}

	//products info -- product column data list
	public List<String> getCategory(SqlSession session, HashMap<String, Object> map) {
		return session.selectList("getCategory", map);
	}

	//product info -- 스톡 리스트
	public List<HashMap<String, Object>> selectProduct_info(SqlSession session, HashMap<String, Object> map) {
		return session.selectList("selectProduct_info",map);
	}
	
	//product -- 제품(모델) 리스트
	public List<HashMap<String, Object>> selectProductList(SqlSession session, HashMap<String, Object> reposit) {
		return session.selectList("selectProductList", reposit);
	}
	
	
	//DML - UPDATE
	public int updateProducts(SqlSession session, HashMap<Object, Object> hashMap) {
		return session.update("updateProducts", hashMap);
	}

	
}//end class
