package com.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

public class RankingDAO {

	public List<String> selectRankingInfoServlet(SqlSession session) {
		return session.selectList("selectRankingInfoServlet",null,new RowBounds(1, 10));
	}

	public int insertRanking(SqlSession session, String word) {
		return session.insert("insertRanking",word);
	}

	public int updateRanking(SqlSession session, String word) {
		return session.insert("updateRanking",word);
	}

}//end class
