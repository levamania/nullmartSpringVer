package com.model.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.velocity.Template;
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

	public int memberAdd(Map<String, String> map) {
		int n =template.insert("MemberMapper.memberAdd", map);
		return n;
	}

	public int emailCheck(Map<String, String> map) {
		int n = template.selectOne("MemberMapper.emailCheck", map);
		return n;
	}

	public int idCheck(Map<String, String> map) {
		int n = template.selectOne("MemberMapper.idCheck", map);
		return n;
	}

	public int searchPwPhone(Map<String, String> map) {
		int n = template.selectOne("MemberMapper.searchPwPhone", map);
		return n;
	}

	public int UpdatePwPhone(Map<String, String> map) {
	int n = template.update("MemberMapper.UpdatePwPhone", map);
		return n;
	}



}
