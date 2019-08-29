package com.model.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.ManagerDTO;
import com.dto.MemberDTO;
@Repository
public class ManagerDAO {
@Autowired
SqlSessionTemplate template;
	
	
	public int ManagerIdPwCheck(String userid) {
		int n = template.selectOne("ManagerMapper.ManagerIdPwCheck", userid);
		return n;
	}


	public ManagerDTO login(Map<String, String> map) {
		ManagerDTO dto = template.selectOne("ManagerMapper.masterLogin", map);
		return dto;
	}



}
