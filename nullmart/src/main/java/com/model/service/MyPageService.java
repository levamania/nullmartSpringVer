package com.model.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dto.EvalDTO;
import com.dto.MemberDTO;
import com.dto.OrderDTO;
import com.dto.OrderEvalListDTO;
import com.dto.RegAddrDTO;
import com.exception.ModifyUserInfoException;
import com.model.dao.MypageDAO;

public class MyPageService {
	private MypageDAO mypageDAO;
	public MyPageService() {
		mypageDAO = new MypageDAO();
	}
	public void addAddr(RegAddrDTO dto) {
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			/*
			 * int num = mypageDAO.regAddrTotal(session)+1; dto.setDelivno(num);
			 */
			mypageDAO.insertAddr(session,dto);
			session.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			session.rollback();
		}finally {
			session.close();
		}
	}
	public List<RegAddrDTO> getAddrList(String userid) {
		List<RegAddrDTO> list= null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			list = mypageDAO.getAddrList(session,userid);
		} finally {
			session.close();
		}
		return list;
	}
	public RegAddrDTO searchByNo(String delivno) {
		RegAddrDTO dto =null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			dto = mypageDAO.searchByNo(session,delivno);
		} finally {
			session.close();
		}
		return dto;
	}
	public void modifyAddr(RegAddrDTO dto) {
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			mypageDAO.modifyAddr(session,dto);
			session.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			session.rollback();
		}finally {
			session.close();
		}
	}
	public void deleteDelivnos(List<String> delivnos) {
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			mypageDAO.deleteDelivnos(session,delivnos);
			session.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			session.rollback();
		}finally {
			session.close();
		}
	}
	public int searchPwdById(HashMap<String, String> map) {
		int num = 0;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			num = mypageDAO.searchPwdById(session,map);
		} finally {
			session.close();
		}
		return num;
	}
	public MemberDTO searchMemberById(String userid) {
		MemberDTO member = null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			member = mypageDAO.searchMemberById(session,userid);
		} finally {
			session.close();
		}
		return member;
	}
	public void modifyAccountInfo(HashMap<String, String> member) throws ModifyUserInfoException {
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			mypageDAO.modifyAccountInfo(session,member);
			session.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			session.rollback();
			String errorMesg = getClass().getSimpleName()+": "+"modifyAccountInfo method Error";
			throw new ModifyUserInfoException(errorMesg);
		}finally {
			session.close();
		}
	}
	
	public List<OrderDTO> getOrderList(HashMap<String, String> map) {
		List<OrderDTO> list = null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			list=mypageDAO.getOrderList(session,map);
		} finally {
			session.close();
		}
		return list;
	}
	public List<OrderEvalListDTO> getOrderEvalList(String userid) {
		List<OrderEvalListDTO> list = null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			list=mypageDAO.getOrderEvalList(session,userid);
		} finally {
			session.close();
		}
		return list;
	}
	public int addEval(EvalDTO eval) {
		SqlSession session = MySqlSessionFactory.getSession();
		int num =0;
		try {
			num = mypageDAO.addEval(session,eval);
			session.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			session.rollback();
		}finally {
			session.close();
		}
		return num;
	}
	public EvalDTO searchEvalByOno(String ono) {
		EvalDTO eval = null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			eval = mypageDAO.searchEvalByOno(session,ono);
		} finally {
			session.close();
		}
		return eval;
	}
	public String searchOrdernameByOno(String ono) {
		String ordername=null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			ordername=mypageDAO.searchOrdernameByOno(session,ono);
		} finally {
			session.close();
		}
		return ordername;
	}
	public int updateEval(EvalDTO eval) {
		SqlSession session = MySqlSessionFactory.getSession();
		int num =0;
		try {
			num = mypageDAO.updateEval(session,eval);
			session.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			session.rollback();
		}finally {
			session.close();
		}
		return num;
	}
	public String searchPassword(String userid) {
		String pwd=null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			pwd=mypageDAO.searchPassword(session,userid);
		} finally {
			session.close();
		}
		return pwd;
	}
	public int updatePwd(HashMap<String, String> map) {
		
		SqlSession session = MySqlSessionFactory.getSession();
		int num =0;
		try {
			num = mypageDAO.updatePwd(session, map);
			session.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			session.rollback();
		}finally {
			session.close();
		}
		return num;
	}
	
}
