package com.model.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD

=======
>>>>>>> branch 'sandwich' of https://github.com/levamania/nullmartSpringVer.git
import com.dto.ManagerDTO;

import com.model.dao.ManagerDAO;




@Service
public class ManagerService {

<<<<<<< HEAD
	@Autowired
	ManagerDAO dao;
=======
@Autowired
ManagerDAO dao;
>>>>>>> branch 'sandwich' of https://github.com/levamania/nullmartSpringVer.git




<<<<<<< HEAD
	public int ManagerIdPwCheck(String userid) {

		return dao.ManagerIdPwCheck(userid);
=======


	public ManagerDTO managerLogin(Map<String, String> map) {
		ManagerDTO dto = dao.managerLogin(map);
		return dto;
>>>>>>> branch 'sandwich' of https://github.com/levamania/nullmartSpringVer.git
	}

<<<<<<< HEAD



	public ManagerDTO login(Map<String, String> map) {
		ManagerDTO dto = dao.login(map);
		return dto;
	}

=======
>>>>>>> branch 'sandwich' of https://github.com/levamania/nullmartSpringVer.git
	

}
