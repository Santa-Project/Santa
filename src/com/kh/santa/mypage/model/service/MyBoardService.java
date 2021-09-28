package com.kh.santa.mypage.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.mypage.model.dao.MyBoardDao;
import com.kh.santa.mypage.model.dto.MemberBoard;
import com.kh.santa.mypage.model.dto.MemberBoardComment;
import com.kh.santa.common.exception.DataAccessException;
import com.kh.santa.common.file.FileDTO;

public class MyBoardService {

   private MyBoardDao myboardDao = new MyBoardDao();
   private JDBCTemplate template = JDBCTemplate.getInstance();
   

   public void insertBoard(MemberBoard board, List<FileDTO> fileDTOs) {
      Connection conn =template.getConnection();
      int res = 0;
      try {
         myboardDao.insertBoard(board,conn);
         for (FileDTO fileDTO : fileDTOs) {
        	 res=  myboardDao.insertFile(fileDTO,conn);
            System.out.println(res);
         }
         template.commit(conn);
      }catch (DataAccessException e) {
         template.rollback(conn);
         throw e;
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
   
   //게시판에 사진 하나
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
   
   //게시판에 댓글 여러개
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

   
   
}
   