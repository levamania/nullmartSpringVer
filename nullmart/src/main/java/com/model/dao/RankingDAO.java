package com.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RankingDAO {
	@Autowired
	private SqlSessionTemplate session;

	public List<String> selectRankingInfoServlet() {
		return session.selectList("selectRankingInfoServlet",null,new RowBounds(1, 10));
	}

	public int insertRanking(String word) {
		return session.insert("insertRanking",word);
	}

	public int updateRanking(String word) {
		return session.insert("updateRanking",word);
	}

}//end class
