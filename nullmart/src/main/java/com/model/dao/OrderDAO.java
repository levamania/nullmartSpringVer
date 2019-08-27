package com.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAO {
	@Autowired
	private SqlSessionTemplate session;

	public List<HashMap<String, Object>> selectBook(HashMap<Object, Object> reposit) {
		return session.selectList("selectBook", reposit);
	}
}
