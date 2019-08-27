package com.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model.dao.RankingDAO;

@Service
public class RankingService {
	@Autowired
	private RankingDAO dao;

	// ranking-information
	public List<String> selectRankingInfoServlet() {
		return dao.selectRankingInfoServlet();
	}

	//add word to rankinglist
	public int insertRanking(String word) {
		return dao.insertRanking(word);
	}
	//upadate rankinglist
	public int updateRanking(String word) {
		return dao.updateRanking(word);
	}
	
}//end class
