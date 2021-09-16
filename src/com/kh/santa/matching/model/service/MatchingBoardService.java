package com.kh.santa.matching.model.service;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.matching.model.dao.MatchingBoardDao;

public class MatchingBoardService {


	private MatchingBoardDao matchingBoardDao = new MatchingBoardDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	
}
