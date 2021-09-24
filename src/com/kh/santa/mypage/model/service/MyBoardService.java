package com.kh.santa.mypage.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.mypage.model.dao.MyBoardDao;
import com.kh.santa.mypage.model.dto.MemberBoard;
import com.kh.santa.common.exception.DataAccessException;
import com.kh.santa.common.file.FileDTO;

public class MyBoardService {

	private MyBoardDao myboardDao = new MyBoardDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	

	public void insertBoard(MemberBoard board, List<FileDTO> fileDTOs) {
		Connection conn =template.getConnection();
		
		try {
			myboardDao.insertBoard(board,conn);
			
			for (FileDTO fileDTO : fileDTOs) {
				myboardDao.insertFile(fileDTO,conn);
			}
		}catch (DataAccessException e) {
			template.rollback(conn);
			throw e;
		}finally {
			template.close(conn);
		}
	}
	
	
	
	
}
	
