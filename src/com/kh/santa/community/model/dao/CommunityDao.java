package com.kh.santa.community.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.common.exception.DataAccessException;
import com.kh.santa.common.file.FileDTO;
import com.kh.santa.mypage.model.dto.MemberBoard;
import com.kh.santa.mypage.model.dto.MemberBoardComment;

public class CommunityDao {

	JDBCTemplate template = JDBCTemplate.getInstance();
	
	public List<MemberBoard> selectBoardTop4(Connection conn) {
		
		List<MemberBoard> BoardArr = new ArrayList<MemberBoard>();
		PreparedStatement pstm = null;
		String columns = "mem_board_idx,member_idx,liked,upload_time,mt_region,mt_name,board_comment";
		
		ResultSet rset = null;
		
		try { 
			String query = "select " + columns + " from member_board "
					+ " where rownum<5 order by liked desc ";
				
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery(); 
			
			while(rset.next()) {
				 MemberBoard board = convertRowToBoard(columns.split(","),rset); 
				 BoardArr.add(board);
	         }
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset,pstm);
		}
		return BoardArr;
	}
	
	  //파일조회
	   public FileDTO selectFileDTOsTop4(String boardIdx, Connection conn) {
	      
	      FileDTO file = null;
	      PreparedStatement pstm = null;
	      ResultSet rset =null;
	      String columns ="board_idx,save_path,rename_file_name";
	      
	      String sql ="SELECT " +columns
	            + " FROM file_info WHERE board_idx = ?";
	      
	      try {
	         pstm =conn.prepareStatement(sql);
	         pstm.setString(1, boardIdx);
	         rset = pstm.executeQuery();
	         
	         while(rset.next()){
	            file= convertRowToFileDTO(columns.split(","),rset);
	         }
	      } catch (SQLException e) {
	         throw new DataAccessException(e);
	      }finally{
	         template.close(rset,pstm);
	      }
	      
	      return file;
	   }
	   
	   //게시판댓글조회
	   public List<MemberBoardComment> selectBoardComentTop4(String boardIdx, Connection conn) {

	      List<MemberBoardComment> comments = new ArrayList<MemberBoardComment>();
	      PreparedStatement pstm = null;
	      ResultSet rset =null;
	      String columns ="comment_idx,board_idx,member_idx,nickname,content";
	      
	      String sql ="SELECT " +columns
	            + " FROM member_board_comment WHERE board_idx=? "; 
	      
	      try {
	         pstm =conn.prepareStatement(sql);
	         pstm.setString(1, boardIdx);
	         rset = pstm.executeQuery();
	         
	         while(rset.next()){
	            comments.add(convertRowToComment(columns.split(","),rset));
	         }
	      } catch (SQLException e) {
	         throw new DataAccessException(e);
	      }finally{
	         template.close(rset,pstm);
	      }
	      
	      return comments;
	   }
	   
	  
	   private FileDTO convertRowToFileDTO(String[] columns,ResultSet rset) throws SQLException {
	      FileDTO fileDTO = new FileDTO();
	      for(int i=0; i<columns.length; i++) {
	         
	      String column=columns[i].toLowerCase();
	      column=column.trim();
	       switch(column) {
	       case "fl_idx": fileDTO.setFlIdx(rset.getString("fl_idx"));break;
	       case "mem_board_idx": fileDTO.setMemBoardIdx(rset.getString("mem_board_idx"));break;
	       case "save_path": fileDTO.setSavePath(rset.getString("save_path"));break;
	       case "origin_file_name": fileDTO.setOriginFileName(rset.getString("origin_file_name"));break;
	       case "rename_file_name": fileDTO.setRenameFileName(rset.getString("rename_file_name"));break;
	       case "reg_date": fileDTO.setRegDate(rset.getDate("reg_date"));break;
	         }
	      }
	      return fileDTO;
	   }
	   
	   private MemberBoardComment convertRowToComment(String[] columns,ResultSet rset) throws SQLException {
	      MemberBoardComment comment = new MemberBoardComment();
	      for(int i=0; i<columns.length; i++) {
	         
	      String column=columns[i].toLowerCase();
	      column=column.trim();
	       switch(column) {
	       case "comment_idx": comment.setCommentIdx(rset.getString("comment_idx"));break;
	       case "mem_board_idx": comment.setMemBoardIdx(rset.getString("mem_board_idx"));break;
	       case "member_idx": comment.setMemberIdx(rset.getString("member_idx"));break;
	       case "content": comment.setContent(rset.getString("content"));break;
	       case "comment_datetime": comment.setCommentDatetime(rset.getDate("comment_datetime"));break;
	       case "nickname": comment.setNickname(rset.getString("nickname"));break;
	         }
	      }
	      return comment;
	   }
	   
	 private MemberBoard convertRowToBoard(String[] columns,ResultSet rset) throws SQLException{
	      
	      MemberBoard board = new MemberBoard();
	      for(int i=0; i<columns.length; i++) {
		         
		      String column=columns[i].toLowerCase();
		      column=column.trim();
		 switch(column) {
	      case "board_idx": board.setBoardIdx(rset.getString("board_idx"));break;
	      case "member_idx": board.setMemberIdx(rset.getString("member_idx"));break;
	      case "liked": board.setLiked(rset.getInt("liked"));break;
	      case "upload_time": board.setUploadTime(rset.getDate("upload_time"));break;
	      case "mt_region": board.setMtRegion(rset.getString("mt_region"));break;
	      case "mt_name": board.setMtName(rset.getString("mt_name"));break;
	      case "board_comment": board.setBoardComment(rset.getString("board_comment"));break;
	      
	      }
	   }
	   
	      return board;
	 }
}
