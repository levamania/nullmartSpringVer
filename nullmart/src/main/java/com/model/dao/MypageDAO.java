package com.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.EvalDTO;
import com.dto.MemberDTO;
import com.dto.OrderDTO;
import com.dto.OrderEvalListDTO;
import com.dto.RegAddrDTO;

@Repository
public class MypageDAO {
	@Autowired
	private SqlSessionTemplate template;
	public int regAddrTotal(SqlSession session) {
		return template.selectOne("myPage.regAddrTotal");
	}

	public void insertAddr(RegAddrDTO dto) {
		template.insert("myPage.insertAddr", dto);
	}

	public List<RegAddrDTO> getAddrList(String userid) {
		return template.selectList("myPage.getAddrList", userid);
	}

	public RegAddrDTO searchByNo(String delivno) {
		return template.selectOne("myPage.searchByNo", delivno);
	}

	public void modifyAddr(RegAddrDTO dto) {
		template.insert("myPage.modifyAddr", dto);
	}

	public void deleteDelivnos(List<String> delivnos) {
		template.delete("myPage.deleteDelivnos",delivnos);
	}

	public int searchPwdById(Map<String, String> map) {
		return template.selectOne("myPage.searchPwdById", map);
	}
	
	public MemberDTO searchMemberById(String userid) {
		System.out.println(userid);
		return template.selectOne("myPage.searchMemberById", userid);
	}

	public void modifyAccountInfo(Map<String, String> member) {
		template.update("myPage.modifyAccountInfo", member);
	}

	public List<OrderDTO> getOrderList(HashMap<String, String> map) {
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

	public String searchPassword(String userid) {
		return template.selectOne("myPage.searchPassword", userid);
	}

	public int updatePwd(Map<String, String> map) {
		return template.update("myPage.updatePwd", map);
	}

}
