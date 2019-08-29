package com.model.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.dto.ManagerDTO;

import com.model.dao.ManagerDAO;




@Service
public class ManagerService {

	@Autowired
	ManagerDAO dao;




	public int ManagerIdPwCheck(String userid) {

		return dao.ManagerIdPwCheck(userid);
	}




	public ManagerDTO login(Map<String, String> map) {
		ManagerDTO dto = dao.login(map);
		return dto;
	}

	

}
