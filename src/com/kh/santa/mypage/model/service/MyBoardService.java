package com.kh.santa.mypage.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.mypage.model.dao.MemberDao;
import com.kh.santa.mypage.model.dao.MyBoardDao;
import com.kh.santa.mypage.model.dto.LikedMemberBoard;
import com.kh.santa.mypage.model.dto.Member;
import com.kh.santa.mypage.model.dto.MemberBoard;
import com.kh.santa.mypage.model.dto.MemberBoardComment;
import com.kh.santa.common.exception.DataAccessException;
import com.kh.santa.common.file.FileDTO;
import com.kh.santa.mountainInfo.model.dto.Mountain;

public class MyBoardService {

	   private JDBCTemplate template = JDBCTemplate.getInstance();
   private MyBoardDao myboardDao = new MyBoardDao();
   private MemberDao memberDao = new MemberDao();
   
   

   public void insertBoard(MemberBoard board, List<FileDTO> fileDTOs) {
      Connection conn =template.getConnection();
      try {
         myboardDao.insertBoard(board,conn);
         for (FileDTO fileDTO : fileDTOs) {
            myboardDao.insertFile(fileDTO,conn);
         }
         template.commit(conn);
      }catch (DataAccessException e) {
         template.rollback(conn);
         throw e;
      }finally {
         template.close(conn);
      }
   }
   
   public void deleteBoard(String boardIdx) {
         Connection conn = template.getConnection();
         try {
            myboardDao.deleteBoardToComment(boardIdx,conn);
            template.commit(conn);
            myboardDao.deleteFile(boardIdx,conn);
            template.commit(conn);
            myboardDao.deleteBoard(boardIdx,conn);
            template.commit(conn);
         }finally {
            template.close(conn);
         }
   }
   
   public void insertComment(MemberBoardComment comment) {
      Connection conn =template.getConnection();
      
       try {
         myboardDao.insertComment(comment,conn);
          template.commit(conn);
       }catch (DataAccessException e) {
          template.rollback(conn);
          throw e;
       }finally {
          template.close(conn);
       }
   }
   
    public void deleteComment(String commentIdx) {
            Connection conn = template.getConnection();
            try {
               myboardDao.deleteComment(conn,commentIdx);
               template.commit(conn);
            }catch (DataAccessException e) {
                template.rollback(conn);
                throw e;
            }finally {
               template.close(conn);
            }
      }
   
   public List<MemberBoard> selectBoardDetail(String memberIdx) {
      
      Connection conn = template.getConnection();
      List<MemberBoard> boardList = null;
      
      try {
         boardList = myboardDao.selectBoardDetail(memberIdx,conn);
         template.commit(conn);
      }finally {
         template.close(conn);
      }
      
      return boardList;
   }
   
   //???????????? ?????? ??????
   public FileDTO selectBoardFile(String boardIdx) {
      
      Connection conn = template.getConnection();
      FileDTO fileDTO =null;
      try {
         fileDTO = myboardDao.selectFileDTOs(boardIdx, conn);
         template.commit(conn);
      }finally {
         template.close(conn);
      }
      
      return fileDTO;
   }
   
   //???????????? ?????? ?????????
   public List<MemberBoardComment> selectBoardComent(String boardIdx) {
      
      Connection conn = template.getConnection();
      List<MemberBoardComment> comment = null;
      
      try {
         comment = myboardDao.selectBoardComent(boardIdx,conn);
         template.commit(conn);
      }finally {
         template.close(conn);
      }
      return comment;
   }

   //?????????
   public void updateBoardLike(String memBoardIdx) {
	   Connection conn = template.getConnection();
	   MemberBoard memberBoard = null;
		try {
			memberBoard = myboardDao.selectBoardtoIdx(memBoardIdx, conn); //idx??? ????????? ?????? (????????? ??????)
			int addLike = memberBoard.getLiked()+1;
			myboardDao.updateBoardLike(addLike, memberBoard, conn); //update????????? (+1)
			template.commit(conn);
		} catch(Exception e){
			template.rollback(conn);
			throw e;
		}finally {
			template.close(conn);
		}
   }
   
  //????????????
   public void removeBoardLike(String memBoardIdx) {
		Connection conn = template.getConnection();
		MemberBoard memberBoard = null;
		try {
			memberBoard = myboardDao.selectBoardtoIdx(memBoardIdx, conn);
			int addLike = memberBoard.getLiked()-1;
			myboardDao.updateBoardLike(addLike, memberBoard, conn);
			template.commit(conn);
		} catch(Exception e){
			template.rollback(conn);
			throw e;
		}finally {
			template.close(conn);
		}
	}
   
   //????????? ??????????????? ??????
   public boolean likeBoardlist(String memberIdx, String memBoardIdx) {
	   Connection conn = template.getConnection();
	   LikedMemberBoard likeList = new LikedMemberBoard();
	   boolean istrue = true;
		try {
			likeList= myboardDao.likeBoardlist(memberIdx, memBoardIdx, conn);
			istrue =!(likeList.getLikedBoardIdx()==null);
		} catch(Exception e){
			template.rollback(conn);
			throw e;
		}finally {
			template.close(conn);
		}
		return istrue;
   }
   
   
   
}
   