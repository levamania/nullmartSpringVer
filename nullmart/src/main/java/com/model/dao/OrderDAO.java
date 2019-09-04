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

	public List<HashMap<String, Object>> selectBook(HashMap<String, Object> reposit) {
		String table = reposit.get("TABLE").toString();
		List<HashMap<String, Object>> result = null;
		if(table.equals("DELIVINFO")) {
			result = session.selectList("selectBook", reposit);			
		}else if(table.equals("ORDERTABLE")) {
			result = session.selectList("selectBook_recent", reposit);
		}
		return result;
	}

	public int insertOrder(HashMap<String, Object> reposit) {
		return session.insert("insertOrder", reposit);
	}

	
	//평가 자료
	public List<HashMap<String, Object>> selectEvaluatedes(HashMap<String, Object> reposit) {
		return session.selectList("selectEvaluatedes", reposit);
	}
}
