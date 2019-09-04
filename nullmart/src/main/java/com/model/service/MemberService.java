package com.model.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.MemberDTO;
import com.model.dao.MemberDAO;



@Service
public class MemberService {
	
	@Autowired
	MemberDAO dao;

	

	public MemberDTO login(Map<String, String> map) {
		MemberDTO dto = dao.login( map);
		return dto;
	
 }



	public int idPwCheck(String userid) {
		
		return dao.idPwCheck(userid);
	}







	public MemberDTO SearchID(Map<String, String> map) {
		MemberDTO dto = dao.SearchID(map);
		return dto;
	}



	public int UpdatePw(Map<String, String> map) {
		int n = dao.UpdatePw(map);
		return n;
	}



	public int memberAdd(Map<String, String> map) {
		int n = dao.memberAdd(map);
		return n;
	}



	public int emailCheck(Map<String, String> map) {
		int n = dao.emailCheck(map);
		return n;
	}



	public int idCheck(Map<String, String> map) {
		int n = dao.idCheck(map);
		return n;
	}



	public int searchPwPhone(Map<String, String> map) {
		int n = dao.searchPwPhone(map);
		return n;
	}



	public int UpdatePwPhone(Map<String, String> map) {
		int n = dao.UpdatePwPhone(map);
		return n;
	}




}