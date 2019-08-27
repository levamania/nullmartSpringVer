package com.model.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dto.ProductDTO;
import com.dto.StockDTO;
import com.dto.StockJoinProductDTO;
import com.model.dao.AdminDAO;
import com.util.CreatePaging;

public class AdminService {
	private AdminDAO adminDAO=null;
	
	public AdminService() {
		adminDAO=new AdminDAO();
	}

	

	public String searchPcodeByPname(String pname) {
		SqlSession session = MySqlSessionFactory.getSession();
		String pcode = null;
		try {
			pcode = adminDAO.searchPcodeByPname(session,pname);
		} finally {
			session.close();
		}
		return pcode;
	}

	public String searchPnameByPcode(String pcode) {
		SqlSession session = MySqlSessionFactory.getSession();
		String pname = null;
		try {
			pname = adminDAO.searchPnameByPcode(session,pcode);
		} finally {
			session.close();
		}
		return pname;
	}



	public int insertStock(StockDTO stock) {
		SqlSession session = MySqlSessionFactory.getSession();
		
		int num = 0;
		try {
			String scode = stock.getsCode();
			int check = adminDAO.searchSCode(session,scode);
			if(check==0) {
				num = adminDAO.insertStock(session,stock);
			}else {
				num = adminDAO.updateStock(session,stock);
			}
			
			session.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			session.rollback();
		}finally {
			session.close();
		}
		return num;
	}



	public HashMap<String, List<String>> getSearchStockOptions() {
		HashMap<String, List<String>> map =new HashMap<String, List<String>>();
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			List<String> styleTop = adminDAO.searchStyleTop(session);
			List<String> styleMid = adminDAO.searchStyleMid(session);
			List<String> styleBot = adminDAO.searchStyleBot(session);
			map.put("styletop", styleTop);
			map.put("stylemid", styleMid);
			map.put("stylebot",styleBot);
		} finally {
			session.close();
		}
		return map;
	}



	public List<String> searchPname() {
		List<String> pnames = null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			pnames = adminDAO.searchPname(session);
		} finally {
			session.close();
		}
		return pnames;
	}



	public ProductDTO searchProduct(String pname) {
		ProductDTO product =null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			product = adminDAO.searchProduct(session,pname);
		} finally {
			session.close();
		}
		return product;
	}



	public List<StockJoinProductDTO> searchStock(HashMap<String, String> map) {
		List<StockJoinProductDTO> list = null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			list =  adminDAO.searchStock(session,map);
		} finally {
			session.close();
		}
		return list;
	}


	/*
	 * 페이징 처리를 위한 select 결과값 개수 확인
	 * */
	public int searchCount(HashMap<String, String> map) {
		int num=0;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			num = AdminDAO.searchCount(session,map);
		} finally {
			session.close();
		}
		return num;
	}

	
}
