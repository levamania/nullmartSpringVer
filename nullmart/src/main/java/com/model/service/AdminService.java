package com.model.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.ProductDTO;
import com.dto.StockDTO;
import com.dto.StockJoinProductDTO;
import com.dto.Stock_ProductDTO;
import com.dto.StyleBotAndPamountDTO;
import com.model.dao.AdminDAO;
import com.util.CreatePaging;

@Service
public class AdminService {
	@Autowired
	private AdminDAO adminDAO;

	public String searchPcodeByPname(String pname) {

		return adminDAO.searchPcodeByPname(pname);
	}

	public String searchPnameByPcode(String pcode) {

		return adminDAO.searchPnameByPcode(pcode);
	}

	@Transactional
	public int insertStock(StockDTO stock) {
		int num = 0;
		try {
			String scode = stock.getsCode();
			int check = adminDAO.searchSCode(scode);
			if (check == 0) {
				num = adminDAO.insertStock(stock);
			} else {
				num = adminDAO.updateStock(stock);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return num;
	}

	public HashMap<String, List<String>> getSearchStockOptions() {
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();

		List<String> styleTop = adminDAO.searchStyleTop();
		List<String> styleMid = adminDAO.searchStyleMid();
		List<String> styleBot = adminDAO.searchStyleBot();
		map.put("styletop", styleTop);
		map.put("stylemid", styleMid);
		map.put("stylebot", styleBot);

		return map;
	}

	public List<String> searchPname() {
		List<String> pnames = adminDAO.searchPname();

		return pnames;
	}

	public ProductDTO searchProduct(String pname) {
		ProductDTO product = adminDAO.searchProduct(pname);
		return product;
	}

	public List<StockJoinProductDTO> searchStock(HashMap<String, String> map) {
		List<StockJoinProductDTO> list = adminDAO.searchStock(map);
		return list;
	}

	/*
	 * 페이징 처리를 위한 select 결과값 개수 확인
	 */
	public int searchCount(HashMap<String, String> map) {
		int num = adminDAO.searchCount(map);

		return num;
	}

	public HashMap<String, String> searchProductToMap(String pcode) {
		return adminDAO.searchProductToMap(pcode);
	}

	public List<StyleBotAndPamountDTO> adminMainChart() {
		return adminDAO.adminMainChart();
	}


//	public Object adminMainChart(Stock_ProductDTO dto) {
//		
//		return adminDAO.adminMainChart(dto);
//	}



}
