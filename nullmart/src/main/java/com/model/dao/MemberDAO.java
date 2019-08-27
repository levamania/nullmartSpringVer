package com.model.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.dto.MemberDTO;

public class MemberDAO {

	public void memberAdd(SqlSession session, MemberDTO dto) {
		int n = session.insert("MemberMapper.memberAdd",dto);
		
	}

	public int idCheck(SqlSession session, String userid) {
		int num = session.selectOne("MemberMapper.idCheck",userid);
		return num;
	}

	public MemberDTO login(SqlSession session, HashMap<String, String> map) {
		MemberDTO dto = session.selectOne("MemberMapper.login", map);
		return dto;
	}

	public int IdPwCheck(SqlSession session, HashMap<String, String> map) {
		int num = session.selectOne("MemberMapper.IdPwCheck", map);
		return num;
	}

	public int SearchPw(SqlSession session, HashMap<String, String> map) {
		int num = session.selectOne("MemberMapper.SearchPw", map);
		return num;
	}

	public int updatePw(SqlSession session, HashMap<String, String> map) {
		int n = session.update("MemberMapper.updatePw", map);
	return n;	
	}

	public int UpdateNewPw(SqlSession session, HashMap<String, String> map) {
		int n = session.update("MemberMapper.UpdateNewPw", map);
		return n;
	}

	public MemberDTO SearchID(SqlSession session, HashMap<String, String> map) {
		MemberDTO dto = session.selectOne("MemberMapper.SearchID", map);
		return dto;
	}

	public int emailCheck(SqlSession session, HashMap<String, String> map) {
		int num = session.selectOne("MemberMapper.emailCheck", map);
		return num;
	}



}
