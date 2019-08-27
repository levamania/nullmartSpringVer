package com.model.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dto.ManagerDTO;

import com.model.dao.ManagerDAO;





public class ManagerService {



	public ManagerDTO masterLogin(HashMap<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSession();
		ManagerDAO dao = new ManagerDAO();  
		ManagerDTO dto = null;
		try {
			dto =dao.masterLogin(session,map);
		} finally {
			session.close();
		}
		return dto;
	}

	public int ManagerIdPwCheck(HashMap<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSession();
		ManagerDAO dao = new ManagerDAO();
		int num = 0;
		try {
			num=dao.ManagerIdPwCheck(session,map);
		} finally {
			session.close();
		
		}
		return num;
	}

	

}
