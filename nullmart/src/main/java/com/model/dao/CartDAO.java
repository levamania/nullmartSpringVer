package com.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

@Repository
public class CartDAO {
	@Autowired
	 SqlSessionTemplate session;
	
	//QUERY
	public List<HashMap<String, Object>> selectCartList( HashMap<Object, Object> reposit) {
		return session.selectList("selectCartList",reposit);
	}

	//DML -- INSERT
	public int stackProduct( List<HashMap<String, Object>> reposits) {
		return session.insert("stackProduct", reposits);
	}

	public int deleteCart( List<HashMap<String, Object>> list) {
		return session.delete("deleteCart",list);
	}

	public int upadateCart( List<HashMap<String, Object>> list) {
		return session.update("updateCart", list);
	}

	

}
