package com.kh.santa.mypage.model.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.mypage.model.dto.MemberBoard;

public class MyBoardDao {

	JDBCTemplate template = JDBCTemplate.getInstance();
	
		//게시판 등록 기능..
		public int insertBoard(MemberBoard board, Connection conn) {

			PreparedStatement pstm = null;
			int res = 0;
			
			String sql = "INSERT INTO member_board(board_idx, member_idx, mt_region, mt_mountain, board_comment,board_picture)" + 
					"VALUES(SC_BOARD_IDX.nextval,?,?,?,?,?)";
			
				try {
					pstm =conn.prepareStatement(sql);
					pstm.setString(1, board.getMemberIdx());
					pstm.setString(2, board.getMtRegion());
					pstm.setString(3, board.getMtMountain());	
					pstm.setString(4, board.getBoardComment());
					pstm.setString(5, board.getBoardPicture());
					
					res = pstm.executeUpdate(); //int로 반환시 res에 담아줌
					
					System.out.println(res);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					template.close(pstm);
				}
				return res;
		}
		
		
		
		
		
		
		
}