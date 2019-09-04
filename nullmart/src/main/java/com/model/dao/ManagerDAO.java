package com.model.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.ManagerDTO;
import com.dto.MemberDTO;
<<<<<<< HEAD
=======

>>>>>>> branch 'sandwich' of https://github.com/levamania/nullmartSpringVer.git
@Repository
public class ManagerDAO {
<<<<<<< HEAD
@Autowired
SqlSessionTemplate template;
=======

	@Autowired
	SqlSessionTemplate template;
>>>>>>> branch 'sandwich' of https://github.com/levamania/nullmartSpringVer.git
	
<<<<<<< HEAD
	
	public int ManagerIdPwCheck(String userid) {
		int n = template.selectOne("ManagerMapper.ManagerIdPwCheck", userid);
		return n;
	}
=======
>>>>>>> branch 'sandwich' of https://github.com/levamania/nullmartSpringVer.git

<<<<<<< HEAD

	public ManagerDTO login(Map<String, String> map) {
		ManagerDTO dto = template.selectOne("ManagerMapper.masterLogin", map);
=======
	public ManagerDTO managerLogin(Map<String, String> map) {
		ManagerDTO dto = template.selectOne("ManagerMapper.managerLogin", map);
>>>>>>> branch 'sandwich' of https://github.com/levamania/nullmartSpringVer.git
		return dto;
	}



}
