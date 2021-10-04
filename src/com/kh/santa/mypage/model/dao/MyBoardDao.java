package com.kh.santa.mypage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.common.exception.DataAccessException;
import com.kh.santa.common.file.FileDTO;
import com.kh.santa.mountainInfo.model.dto.Mountain;
import com.kh.santa.mypage.model.dto.LIkedMemberBoard;
import com.kh.santa.mypage.model.dto.MemberBoard;
import com.kh.santa.mypage.model.dto.MemberBoardComment;

public class MyBoardDao {

   JDBCTemplate template = JDBCTemplate.getInstance();
   
   //게시판작성
   public int insertBoard(MemberBoard board, Connection conn) {

      PreparedStatement pstm = null;
      int res =0;
      
      String sql = "INSERT INTO member_board(mem_board_idx, member_idx, mt_region, mt_name, board_comment) VALUES(sc_board_idx.nextval,?,?,?,?)";
      
         try {
            pstm =conn.prepareStatement(sql);
            pstm.setString(1, board.getMemberIdx());
            pstm.setString(2, board.getMtRegion());
            pstm.setString(3, board.getMtName());   
            pstm.setString(4, board.getBoardComment());
           res =  pstm.executeUpdate(); 
         } catch (SQLException e) {
            throw new DataAccessException(e);
         }finally {
            template.close(pstm);
         }
         return res;
   }
   
   //파일넣기
   public int insertFile(FileDTO fileDTO, Connection conn) {
      String sql ="INSERT INTO FILE_INFO(fl_idx,mem_board_idx,origin_file_name,rename_file_name,save_path) VALUES(sc_file_idx.nextval,sc_board_idx.currval,?,?,?)"; 
      
      PreparedStatement pstm = null;
      int res =0;
      
      try {
         pstm =conn.prepareStatement(sql);
         pstm.setString(1, fileDTO.getOriginFileName());
         pstm.setString(2, fileDTO.getRenameFileName());
         pstm.setString(3, fileDTO.getSavePath());
        res= pstm.executeUpdate();
      } catch (SQLException e) {
         throw new DataAccessException(e);
      }finally{
         template.close(pstm);
      }
      return res;
   }
   
   //댓글,사진,게시판 삭제
   public void deleteBoard(String boardIdx,Connection conn) {
      
      PreparedStatement pstm = null;
      
      try {
         String query = "DELETE FROM member_board WHERE mem_board_idx = ? "; 
         pstm = conn.prepareStatement(query);
         pstm.setString(1, boardIdx);
         pstm.executeUpdate();
         
      } catch (SQLException e) {
          throw new DataAccessException(e);
      }finally {
         template.close(pstm);
      }
   }
   
   //사진 삭제
   public void deleteFile(String boardIdx, Connection conn) {
      
         PreparedStatement pstm = null;
         
         try {
            String query = "DELETE FROM file_info WHERE mem_board_idx = ? "; 
            pstm = conn.prepareStatement(query);
            pstm.setString(1, boardIdx);
            pstm.executeUpdate();
            
         } catch (SQLException e) {
             throw new DataAccessException(e);
         }finally {
            template.close(pstm);
         }
      
   }
   //댓글삭제 (게시판 전체삭제용) 
   public void deleteBoardToComment(String boardidx, Connection conn) {
       
      PreparedStatement pstm = null;
         
         try {
            String query = "DELETE FROM member_board_comment WHERE mem_board_idx = ? "; 
            pstm = conn.prepareStatement(query);
            pstm.setString(1, boardidx);
            pstm.executeUpdate();
         } catch (SQLException e) {
             throw new DataAccessException(e);
         }finally {
            template.close(pstm);
         }
   }
   //댓글 삭제
   public void deleteComment(Connection conn,String commentIdx) {
       
      PreparedStatement pstm = null;
         
         try {
            String query = "DELETE FROM member_board_comment WHERE COMMENT_IDX = ? "; 
            pstm = conn.prepareStatement(query);
            pstm.setString(1, commentIdx);
            pstm.executeUpdate();
         } catch (SQLException e) {
             throw new DataAccessException(e);
         }finally {
            template.close(pstm);
         }
   }


   //댓글 추가
   public void insertComment(MemberBoardComment comment, Connection conn) {
      
      PreparedStatement pstm = null;
         
         String sql = "INSERT INTO member_board_comment(comment_idx, mem_board_idx, content, nickname, member_idx) VALUES(sc_comment_idx.nextval,?,?,?,?)";
         
            try {
               pstm =conn.prepareStatement(sql);
               pstm.setString(1, comment.getMemBoardIdx());
               pstm.setString(2, comment.getContent());
               pstm.setString(3, comment.getNickname());
               pstm.setString(4, comment.getMemberIdx());
               pstm.executeUpdate(); 
            } catch (SQLException e) {
               throw new DataAccessException(e);
            }finally {
               template.close(pstm);
            }
   }
   
   //게시판조회
   public List<MemberBoard> selectBoardDetail(String memberIdx, Connection conn) {
      
      List<MemberBoard> boardList = new ArrayList<MemberBoard>();
      PreparedStatement pstm = null;
      ResultSet rset =null;
      
      String sql ="SELECT * FROM member_board WHERE member_idx = ? order by UPLOAD_TIME desc";
      
      try {
         pstm =conn.prepareStatement(sql);
         pstm.setString(1, memberIdx);
         rset = pstm.executeQuery();
         
         while(rset.next()) {
            MemberBoard board = convertRowToBoard(rset);
            boardList.add(board);
         }
          
      } catch (SQLException e) {
         throw new DataAccessException(e);
      }finally{
         template.close(rset,pstm);
      }
      return boardList;
   }
   
   //파일조회
   public FileDTO selectFileDTOs(String boardIdx, Connection conn) {
      
      FileDTO file = null;
      PreparedStatement pstm = null;
      ResultSet rset =null;
      String columns ="mem_board_idx,save_path,rename_file_name";
      
      String sql ="SELECT " +columns
            + " FROM file_info WHERE mem_board_idx = ? ORDER BY mem_board_idx ASC";
      
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
   public List<MemberBoardComment> selectBoardComent(String boardIdx, Connection conn) {

      List<MemberBoardComment> comments = new ArrayList<MemberBoardComment>();
      PreparedStatement pstm = null;
      ResultSet rset =null;
      String columns ="comment_idx,mem_board_idx,member_idx,nickname,content";
      
      String sql ="SELECT " +columns
            + " FROM member_board_comment WHERE mem_board_idx=? order by COMMENT_DATETIME desc"; 
      
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
   
   //좋아요
   public void updateBoardLike(int updatedlike, MemberBoard memberBoard, Connection conn) {
		PreparedStatement pstm = null;
		
		String query = "update member_board set liked = ? where mem_board_idx = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, updatedlike);
			pstm.setString(2, memberBoard.getMemBoardIdx());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}
   
   //mem_board_idx로 게시글찾기
   public MemberBoard selectBoardtoIdx(String memboardIdx, Connection conn) {
	    
	   PreparedStatement pstm = null;
	   MemberBoard memberboard = new MemberBoard();
	   ResultSet rset = null;
	   
		String query = "select * from member_board where mem_board_idx = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, memboardIdx);
			rset= pstm.executeQuery();
			 if(rset.next()) {
				 memberboard = convertRowToBoard(rset);
				}
			 
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return memberboard;
   }
   
   
   //좋아요리스트 추가
	public void insertLikeBoardlist(String memberIdx, String memBoardIdx, Connection conn) {

		PreparedStatement pstm = null;

		String query = "insert into liked_member_board values(SC_likeboard_IDX.nextval,?,?)";

		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, memberIdx);
			pstm.setString(2, memBoardIdx);
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}

	}
	
	//좋아요리스트 삭제
	public void deleteLikeBoardlist(String memberIdx, String memBoardIdx, Connection conn) {

		PreparedStatement pstm = null;

		String query = "delete from liked_member_board where member_idx = ? and MEM_BOARD_IDX = ?";

		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, memberIdx);
			pstm.setString(2, memBoardIdx);
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}
   
   
	//좋아요 리스트 있는지 반환
	public LIkedMemberBoard likeBoardlist(String memberIdx, String memBoardIdx, Connection conn) {

		PreparedStatement pstm = null;
		LIkedMemberBoard likelist = new LIkedMemberBoard();
		 ResultSet rset = null;
		   
		String query = "delete from liked_member_board where member_idx = ? and MEM_BOARD_IDX = ?";

		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, memberIdx);
			pstm.setString(2, memBoardIdx);
			pstm.executeUpdate();
			rset= pstm.executeQuery();
			 if(rset.next()) {
				 likelist = convertRowToLikeBoard(rset);
				}
			 
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return likelist;
	}
   
   
     private LIkedMemberBoard convertRowToLikeBoard(ResultSet rset) throws SQLException{
    	 LIkedMemberBoard likelist = new LIkedMemberBoard();
    	 likelist.setMemBoardIdx(rset.getString("mem_board_idx"));
    	 likelist.setMemberIdx(rset.getString("member_idx"));
    	 likelist.setLikedBoardIdx(rset.getString("LIKED_BOARD_IDX"));
		return likelist;
	}

	//colums가져오기
   private MemberBoard convertRowToBoard(ResultSet rset) throws SQLException{
      
      MemberBoard board = new MemberBoard();
      board = new MemberBoard();
      board.setMemBoardIdx(rset.getString("mem_board_idx"));
      board.setMemberIdx(rset.getString("member_idx"));
      board.setLiked(rset.getInt("liked"));
      board.setUploadTime(rset.getDate("UPLOAD_TIME"));
      board.setMtRegion(rset.getString("mt_region"));
      board.setMtName(rset.getString("mt_name"));
      board.setBoardComment(rset.getString("board_comment"));
      return board;
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

}