package com.kh.santa.matching.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.matching.model.dao.MatchingBoardDao;
import com.kh.santa.matching.model.dto.MatchingBoard;
import com.kh.santa.matching.model.dto.OpenRoom;

public class MatchingBoardService {


	private  MatchingBoardDao matchingBoardDao = new MatchingBoardDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	public ArrayList<MatchingBoard> getMatchingList() {
		Connection conn = template.getConnection();
		ArrayList<MatchingBoard> list = new ArrayList<MatchingBoard>();
		try {
			list = matchingBoardDao.list(conn);
			
		}finally {
			template.close(conn);
		}
		
		return list;
	}

	public MatchingBoard getContentView(int mbIdx) {
		Connection conn = template.getConnection();
		MatchingBoard content_view = new MatchingBoard();
		try {
			content_view = matchingBoardDao.selectBoardByIdx(mbIdx, conn);
			
		}finally {
			template.close(conn);
		}
		
		return content_view;
	}

	public ArrayList<OpenRoom> getopenRoom() {
		Connection conn = template.getConnection();
		ArrayList<OpenRoom> open_room = new ArrayList<OpenRoom>(); 
		try {
			open_room = matchingBoardDao.roomContent(conn);
			
		}finally {
			template.close(conn);
		}
		
		return open_room;
	}

	public void setMatchingBoard(MatchingBoard dto) {
		Connection conn = template.getConnection();
		try {
			/* matchingBoardDao.insertMatchingBoard(dto,conn); */
			template.commit(conn);
		}catch(Exception e){
			template.rollback(conn);
			throw e;
		}finally {
			template.close(conn);
		}
		
	}


	
	
}
