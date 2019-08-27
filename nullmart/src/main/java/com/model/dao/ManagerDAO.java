package com.model.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.dto.ManagerDTO;
import com.dto.MemberDTO;

public class ManagerDAO {

	
	public ManagerDTO masterLogin(SqlSession session, HashMap<String, String> map) {
		ManagerDTO dto = session.selectOne("ManagerMapper.masterLogin", map);
		return dto;
	}

	public int ManagerIdPwCheck(SqlSession session, HashMap<String, String> map) {
		int num = session.selectOne("ManagerMapper.ManagerIdPwCheck", map);
		return num;
	}



}
