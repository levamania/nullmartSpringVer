package com.model.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.MemberDTO;

@Repository
public class MemberDAO {

@Autowired
SqlSessionTemplate template;
	
	public MemberDTO login(Map<String, String> map) {
		MemberDTO dto = template.selectOne("MemberMapper.login", map);
		return dto;
	}

	public int idPwCheck(String userid) {
		return template.selectOne("MemberMapper.idPwCheck", userid);
	}

	public MemberDTO SearchID(Map<String, String> map) {
		MemberDTO dto = template.selectOne("MemberMapper.SearchID", map);
		return dto;
	}

	public int UpdatePw(Map<String, String> map) {
		int n = template.update("MemberMapper.updatePw", map);
		return n;
	}



}
