package com.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.dto.ProductDTO;
import com.dto.StockDTO;
import com.dto.StockJoinProductDTO;
import com.util.CreatePaging;
import com.util.SessionCheckInterface;

public class AdminDAO {

	public int searchPname(SqlSession session, String pname) {
		return session.selectOne("com.dto.Admin.searchPname", pname);
	}

	public int searchPcode(SqlSession session, String pcode) {
		return session.selectOne("com.dto.Admin.searchPcode", pcode);
	}

	public String searchPcodeByPname(SqlSession session, String pname) {
		return session.selectOne("com.dto.Admin.searchPcodeByPname", pname);
	}

	public String searchPnameByPcode(SqlSession session, String pcode) {
		return session.selectOne("com.dto.Admin.searchPnameByPcode", pcode);
	}

	public int insertStock(SqlSession session, StockDTO stock) {
		return session.insert("com.dto.Admin.insertStock", stock);
	}

	public int searchSCode(SqlSession session, String scode) {
		return session.selectOne("com.dto.Admin.searchSCode", scode);
	}

	public int updateStock(SqlSession session, StockDTO stock) {
		return session.update("com.dto.Admin.updateStock", stock);
	}

	public List<String> searchStyleTop(SqlSession session) {
		return session.selectList("com.dto.Admin.searchStyleTop");
	}

	public List<String> searchStyleMid(SqlSession session) {
		return session.selectList("com.dto.Admin.searchStyleMid");
	}

	public List<String> searchStyleBot(SqlSession session) {
		return session.selectList("com.dto.Admin.searchStyleBot");
	}

	public List<String> searchPname(SqlSession session) {
		return session.selectList("com.dto.Admin.searchPname");
	}

	public ProductDTO searchProduct(SqlSession session, String pname) {
		return session.selectOne("com.dto.Admin.searchProduct", pname);
	}

	public List<StockJoinProductDTO> searchStock(SqlSession session, HashMap<String, String> map) {
		List<StockJoinProductDTO> list = null;
		int offset = Integer.parseInt(map.get("offset"));
		int limit = Integer.parseInt(map.get("limit"));
		list = session.selectList("com.dto.Admin.searchStock", map,new RowBounds(offset, limit));
		return list;
	}

	public static int searchCount(SqlSession session, HashMap<String, String> map) {
		return session.selectOne("com.dto.Admin.searchCount", map);
	}

	

	

	
}
