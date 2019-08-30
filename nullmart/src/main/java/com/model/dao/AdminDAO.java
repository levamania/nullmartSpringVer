package com.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.ProductDTO;
import com.dto.StockDTO;
import com.dto.StockJoinProductDTO;
import com.util.CreatePaging;
import com.util.SessionCheckInterface;

@Repository
public class AdminDAO {
	@Autowired
	SqlSessionTemplate template;

	public int searchPname(SqlSession session, String pname) {
		return template.selectOne("com.dto.Admin.searchPname", pname);
	}

	public int searchPcode(SqlSession session, String pcode) {
		return template.selectOne("com.dto.Admin.searchPcode", pcode);
	}

	public String searchPcodeByPname(String pname) {
		return template.selectOne("com.dto.Admin.searchPcodeByPname", pname);
	}

	public String searchPnameByPcode(String pcode) {
		return template.selectOne("com.dto.Admin.searchPnameByPcode", pcode);
	}

	public int insertStock(StockDTO stock) {
		return template.insert("com.dto.Admin.insertStock", stock);
	}

	public int searchSCode(String scode) {
		return template.selectOne("com.dto.Admin.searchSCode", scode);
	}

	public int updateStock(StockDTO stock) {
		return template.update("com.dto.Admin.updateStock", stock);
	}

	public List<String> searchStyleTop(SqlSession session) {
		return template.selectList("com.dto.Admin.searchStyleTop");
	}

	public List<String> searchStyleMid(SqlSession session) {
		return template.selectList("com.dto.Admin.searchStyleMid");
	}

	public List<String> searchStyleBot(SqlSession session) {
		return template.selectList("com.dto.Admin.searchStyleBot");
	}

	public List<String> searchPname(SqlSession session) {
		return template.selectList("com.dto.Admin.searchPname");
	}

	public ProductDTO searchProduct(SqlSession session, String pname) {
		return template.selectOne("com.dto.Admin.searchProduct", pname);
	}

	public List<StockJoinProductDTO> searchStock(SqlSession session, HashMap<String, String> map) {
		List<StockJoinProductDTO> list = null;
		int offset = Integer.parseInt(map.get("offset"));
		int limit = Integer.parseInt(map.get("limit"));
		list = template.selectList("com.dto.Admin.searchStock", map,new RowBounds(offset, limit));
		return list;
	}

	public  int searchCount(SqlSession session, HashMap<String, String> map) {
		return template.selectOne("com.dto.Admin.searchCount", map);
	}

	

	

	
}
