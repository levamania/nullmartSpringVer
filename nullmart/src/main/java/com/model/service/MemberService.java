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

}