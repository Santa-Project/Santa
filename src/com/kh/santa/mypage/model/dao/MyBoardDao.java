package com.kh.santa.mypage.model.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.common.file.FileDTO;
import com.kh.santa.mypage.model.dto.MemberBoard;

public class MyBoardDao {

	JDBCTemplate template = JDBCTemplate.getInstance();
	
	public void insertBoard(MemberBoard board, Connection conn) {

		PreparedStatement pstm = null;
		
		String sql = "INSERT INTO member_board(board_idx, member_idx, mt_region, mt_mountain, board_comment)" + 
				"VALUES(SC_BOARD_IDX.nextval,?,?,?,?)";
		
			try {
				pstm =conn.prepareStatement(sql);
				pstm.setString(1, board.getMemberIdx());
				pstm.setString(2, board.getMtRegion());
				pstm.setString(3, board.getMtMountain());	
				pstm.setString(4, board.getBoardComment());
				pstm.executeUpdate(); 
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				template.close(pstm);
			}
	}
	
	
	public void insertFile(FileDTO fileDTO, Connection conn) {
		String sql ="INSERT INTO FILE_INFO "+
				"(FL_IDX,board_idx,ORIGIN_FILE_NAME,RENAME_FILE_NAME,SAVE_PATH) "+
				"VALUES(SC_FILE_IDX.NEXTVAL,SC_BOARD_IDX.CURRVAL,?,?,?)";
		
		PreparedStatement pstm = null;
		
		try {
			pstm =conn.prepareStatement(sql);
			pstm.setString(1, fileDTO.getOriginFileName());
			pstm.setString(2, fileDTO.getRenameFileName());
			pstm.setString(3, fileDTO.getSavePath());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			template.close(pstm);
		}
	}
	
	
		
		
		
		
}