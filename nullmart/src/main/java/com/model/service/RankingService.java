package com.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.model.dao.RankingDAO;

public class RankingService {
	private RankingDAO dao;

	public RankingService() {
		dao = new RankingDAO();
	}

	// ranking-information
	public List<String> selectRankingInfoServlet() {
		List<String> list = null;
		SqlSession session = null;
		try {
			session = MySqlSessionFactory.getSession();
			list = dao.selectRankingInfoServlet(session);
			session.commit();
		} finally {
			if (session != null)	session.close();
		}
		return list;
	}

	//add word to rankinglist
	public int insertRanking(String word) {
		int result = 0;
		SqlSession session = null;
		try {
			session = MySqlSessionFactory.getSession();
			result = dao.insertRanking(session,word);
			session.commit();
		}finally {
			if (session != null)	session.close();
		}
		return result;
	}
	//upadate rankinglist
	public int updateRanking(String word) {
		int result = 0;
		SqlSession session = null;
		try {
			session = MySqlSessionFactory.getSession();
			result = dao.updateRanking(session,word);
			session.commit();
		}finally {
			if (session != null)	session.close();
		}
		return result;
	}
	
}//end class
