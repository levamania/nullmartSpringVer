package com.model.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;

import com.dto.MemberDTO;
import com.model.dao.MemberDAO;




public class MemberService {

	public void memberAdd(MemberDTO dto) {
		SqlSession session = MySqlSessionFactory.getSession();
		MemberDAO dao = new MemberDAO();  
		try {
			dao.memberAdd(session,dto);
			session.commit();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			session.rollback();
			
		} finally {
			session.close();
		}
		
		
	}

	public int idCheck(String userid) {
		SqlSession session = MySqlSessionFactory.getSession();
		MemberDAO dao = new MemberDAO();  
		int num=0;
		try {
			num =dao.idCheck(session,userid);
			
			
		} finally {
			session.close();
		}
		return num;
	}

	public MemberDTO login(HashMap<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSession();
		MemberDAO dao = new MemberDAO();  
		MemberDTO dto = null;
		try {
			dto =dao.login(session,map);
		} finally {
			session.close();
		}
		return dto;
	}

	public int IdPwCheck(HashMap<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSession();
		MemberDAO dao = new MemberDAO();
		int num =0;
		try {
			num = dao.IdPwCheck(session,map);
		} finally {
		 session.close();
		}
		return num;
	}

	public int SearchPw(HashMap<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSession();
		MemberDAO dao= new MemberDAO();
		int num=0;
		try {
			num = dao.SearchPw(session,map);
		} finally {
			session.close();
		}
		
		
		return num;
	}

	public int updatePw(HashMap<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSession();
		MemberDAO dao = new MemberDAO();
		int num=0;
		try {
			num=dao.updatePw(session,map);
			session.commit();
		} finally {
			session.close();
		}return num;
	}

	public int UpdateNewPw(HashMap<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSession();
		MemberDAO dao = new MemberDAO();
		int num=0;
		try {
			num=dao.UpdateNewPw(session,map);
			session.commit();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			session.rollback();
		
		} finally {
			session.close();
			// TODO: handle finally clause
		}
		return num;
	}

	public MemberDTO SearchID(HashMap<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSession();
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		try {
			dto=dao.SearchID(session,map);
		} finally {
			session.close();
		}
		return dto;
	}

	public int emailCheck(HashMap<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSession();
		MemberDAO dao = new MemberDAO();
		int num = 0;
		try {
			num=dao.emailCheck(session,map);
		} finally {
			session.close();
		}
		return num;
	}

	

}
