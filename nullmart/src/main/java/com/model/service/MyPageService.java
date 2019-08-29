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
	public List<OrderEvalListDTO> getOrderEvalList(String userid) {
		List<OrderEvalListDTO> list = mypageDAO.getOrderEvalList(userid);
		return list;
	}
	public int addEval(EvalDTO eval) {
		return mypageDAO.addEval(eval);
	}
	public EvalDTO searchEvalByOno(String ono) {
		EvalDTO eval = mypageDAO.searchEvalByOno(ono);
		return eval;
	}
	
	public String searchScodeByOno(String ono) {
		String scode=mypageDAO.searchScodeByOno(ono);
		return scode;
	}
	public int updateEval(EvalDTO eval) {
		int num =mypageDAO.updateEval(eval);
		return num;
	}
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
