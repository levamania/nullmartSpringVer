package com.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.EvalDTO;
import com.dto.MemberDTO;
import com.dto.OrderDTO;
import com.dto.OrderEvalListDTO;
import com.dto.RegAddrDTO;
import com.exception.ModifyUserInfoException;
import com.model.dao.MypageDAO;

@Service
public class MyPageService {
	@Autowired
	private MypageDAO mypageDAO;
	
	public void addAddr(RegAddrDTO dto) {
		mypageDAO.insertAddr(dto);
	}
	public List<RegAddrDTO> getAddrList(String userid) {
		List<RegAddrDTO> list= mypageDAO.getAddrList(userid);
		return list;
	}
	public RegAddrDTO searchByNo(String delivno) {
		RegAddrDTO dto = mypageDAO.searchByNo(delivno);
		return dto;
	}
	public void modifyAddr(RegAddrDTO dto) {
		mypageDAO.modifyAddr(dto);
			
	}
	public void deleteDelivnos(List<String> delivnos) {
		mypageDAO.deleteDelivnos(delivnos);
		
	}
	public int searchPwdById(Map<String, String> map) {
		int num = mypageDAO.searchPwdById(map);
		return num;
	}
	public MemberDTO searchMemberById(String userid) {
		MemberDTO member =mypageDAO.searchMemberById(userid);
		return member;
	}
	
	@Transactional
	public void modifyAccountInfo(Map<String, String> member) throws ModifyUserInfoException {
		try {
			mypageDAO.modifyAccountInfo(member);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			String errorMesg = getClass().getSimpleName()+": "+"modifyAccountInfo method Error";
			throw new ModifyUserInfoException(errorMesg);
		}
	}
	
	public List<OrderDTO> getOrderList(HashMap<String, String> map) {
		List<OrderDTO> list = mypageDAO.getOrderList(map);
		return list;
	}
//	public List<OrderEvalListDTO> getOrderEvalList(String userid) {
//		List<OrderEvalListDTO> list = null;
//		SqlSession session = MySqlSessionFactory.getSession();
//		try {
//			list=mypageDAO.getOrderEvalList(session,userid);
//		} finally {
//			session.close();
//		}
//		return list;
//	}
//	public int addEval(EvalDTO eval) {
//		SqlSession session = MySqlSessionFactory.getSession();
//		int num =0;
//		try {
//			num = mypageDAO.addEval(session,eval);
//			session.commit();
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			session.rollback();
//		}finally {
//			session.close();
//		}
//		return num;
//	}
//	public EvalDTO searchEvalByOno(String ono) {
//		EvalDTO eval = null;
//		SqlSession session = MySqlSessionFactory.getSession();
//		try {
//			eval = mypageDAO.searchEvalByOno(session,ono);
//		} finally {
//			session.close();
//		}
//		return eval;
//	}
//	public String searchOrdernameByOno(String ono) {
//		String ordername=null;
//		SqlSession session = MySqlSessionFactory.getSession();
//		try {
//			ordername=mypageDAO.searchOrdernameByOno(session,ono);
//		} finally {
//			session.close();
//		}
//		return ordername;
//	}
//	public int updateEval(EvalDTO eval) {
//		SqlSession session = MySqlSessionFactory.getSession();
//		int num =0;
//		try {
//			num = mypageDAO.updateEval(session,eval);
//			session.commit();
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			session.rollback();
//		}finally {
//			session.close();
//		}
//		return num;
//	}
	public String searchPassword(String userid) {
		String pwd=mypageDAO.searchPassword(userid);
		return pwd;
	}
	
	@Transactional
	public int updatePwd(Map<String, String> map) {
		int num =0;
		try {
			num = mypageDAO.updatePwd(map);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return num;
	}
	
}
