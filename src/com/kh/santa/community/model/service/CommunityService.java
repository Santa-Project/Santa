package com.kh.santa.community.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.common.exception.DataAccessException;
import com.kh.santa.common.file.FileDTO;
import com.kh.santa.community.model.dao.CommunityDao;
import com.kh.santa.mypage.model.dao.MemberDao;
import com.kh.santa.mypage.model.dao.MyBoardDao;
import com.kh.santa.mypage.model.dto.Member;
import com.kh.santa.mypage.model.dto.MemberBoard;
import com.kh.santa.mypage.model.dto.MemberBoardComment;

public class CommunityService {
	
	private JDBCTemplate template = JDBCTemplate.getInstance();
	private MemberDao memberDao = new MemberDao();
	private CommunityDao communityDao = new CommunityDao();
	private MyBoardDao myboardDao = new MyBoardDao();

	public Member[] selectPopularMember() {
		Member[] memberArr;
		Connection conn = template.getConnection();
		try {
			memberArr = memberDao.selectMemberTop10(conn);
		} finally {
			template.close(conn);
		}
		return memberArr;
	}
	
	public List<MemberBoard> selectBoardTop4() {
		List<MemberBoard> boardArr;
		Connection conn = template.getConnection();
		try {
			boardArr = communityDao.selectBoardTop4(conn);
		} finally {
			template.close(conn);
		}
		return boardArr;
	}
	
	   public FileDTO selectFileDTOsTop4(String boardIdx) {
		   FileDTO fileDTO =null;
		   Connection conn = template.getConnection();
			try {
				fileDTO = communityDao.selectFileDTOsTop4(boardIdx,conn);
			} finally {
				template.close(conn);
			}
			return fileDTO;
	   
	   
	   }

	public List<MemberBoardComment> selectBoardComentTop4(String boardIdx) {
		 List<MemberBoardComment> comment = null;
		   Connection conn = template.getConnection();
			try {
				comment = communityDao.selectBoardComentTop4(boardIdx,conn);
			} finally {
				template.close(conn);
			}
			return comment;
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
	
	
	
}
