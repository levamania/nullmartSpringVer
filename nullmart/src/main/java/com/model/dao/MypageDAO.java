package com.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.dto.EvalDTO;
import com.dto.MemberDTO;
import com.dto.OrderDTO;
import com.dto.OrderEvalListDTO;
import com.dto.RegAddrDTO;


public class MypageDAO {
	@Autowired
	private SqlSessionTemplate template;
	public int regAddrTotal(SqlSession session) {
		
		return template.selectOne("myPage.regAddrTotal");
	}

	public void insertAddr(SqlSession session, RegAddrDTO dto) {
		template.insert("myPage.insertAddr", dto);
	}

	public List<RegAddrDTO> getAddrList(SqlSession session, String userid) {
		return template.selectList("myPage.getAddrList", userid);
	}

	public RegAddrDTO searchByNo(SqlSession session, String delivno) {
		return template.selectOne("myPage.searchByNo", delivno);
	}

	public void modifyAddr(SqlSession session, RegAddrDTO dto) {
		template.insert("myPage.modifyAddr", dto);
	}

	public void deleteDelivnos(SqlSession session, List<String> delivnos) {
		template.delete("myPage.deleteDelivnos",delivnos);
	}

	public int searchPwdById(SqlSession session, HashMap<String, String> map) {
		return template.selectOne("myPage.searchPwdById", map);
	}
	
	public MemberDTO searchMemberById(SqlSession session,String userid) {
		return template.selectOne("myPage.searchMemberById", userid);
	}

	public void modifyAccountInfo(SqlSession session, HashMap<String, String> member) {
		template.update("myPage.modifyAccountInfo", member);
	}

	public List<OrderDTO> getOrderList(SqlSession session, HashMap<String, String> map) {
		return template.selectList("myPage.getOrderList", map);
	}

	public List<OrderEvalListDTO> getOrderEvalList(SqlSession session, String userid) {
		return template.selectList("myPage.getOrderEvalList", userid);
	}

	public int addEval(SqlSession session, EvalDTO eval) {
		return template.insert("myPage.addEval", eval);
	}

	public EvalDTO searchEvalByOno(SqlSession session, String ono) {
		return template.selectOne("myPage.searchEvalByOno", ono);
	}

	public String searchOrdernameByOno(SqlSession session, String ono) {
		return template.selectOne("myPage.searchOrdernameByOno",ono);
	}

	public int updateEval(SqlSession session, EvalDTO eval) {
		return template.update("myPage.updateEval", eval);
	}

	public String searchPassword(SqlSession session, String userid) {
		return template.selectOne("myPage.searchPassword", userid);
	}

	public int updatePwd(SqlSession session, HashMap<String, String> map) {
		return template.update("myPage.updatePwd", map);
	}

}
