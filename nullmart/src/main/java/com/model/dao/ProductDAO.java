package com.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.ProductDTO;
import com.dto.StockDTO;

@Repository
public class ProductDAO {
	@Autowired
	SqlSessionTemplate session;
	
	public Set<String> getKeyset( HashMap<String, Object> map) {
		List<HashMap<String, Object>> temp = session.selectList("keyset", map);
		return temp.get(0).keySet();
	}

	//products info -- product column data list
	public List<String> getCategory( HashMap<String, Object> map) {
		return session.selectList("getCategory", map);
	}

	//product info -- 스톡 리스트
	public List<HashMap<String, Object>> selectProduct_info( HashMap<String, Object> map) {
		return session.selectList("selectProduct_info",map);
	}
	
	//product -- 제품(모델) 리스트
	public List<HashMap<String, Object>> selectProductList( HashMap<String, Object> reposit) {
		return session.selectList("selectProductList", reposit);
	}
	
	
	//DML - UPDATE
	public int updateProducts( HashMap<Object, Object> hashMap) {
		return session.update("updateProducts", hashMap);
	}

	public int insertProduct(HashMap<String, Object> reposit) {
		return session.insert("insertProduct",reposit);
	}

	
}//end class
