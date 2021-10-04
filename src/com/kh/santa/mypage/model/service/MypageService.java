package com.kh.santa.mypage.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.common.exception.DataAccessException;
import com.kh.santa.common.file.FileDTO;
import com.kh.santa.mountainInfo.model.dao.MountainDao;
import com.kh.santa.mountainInfo.model.dto.Mountain;
import com.kh.santa.mountainInfo.model.dto.MountainWishlist;
import com.kh.santa.mypage.model.dao.MemberDao;
import com.kh.santa.mypage.model.dto.Member;
import com.kh.santa.mypage.model.dto.MemberBoardComment;

public class MypageService {

	private MemberDao memberDao = new MemberDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	private MountainDao mountainDao = new MountainDao();
	
	//회원 산리스트 조회
	public List<MountainWishlist> selectMountainWishlist(String memberIdx) {
		
		List<MountainWishlist> wishlist = null;
		   Connection conn = template.getConnection();
		      try {
		    	  wishlist = memberDao.selectMountainWishlist(memberIdx,conn);
		         template.commit(conn);
		      }finally {
		         template.close(conn);
		      }
		      return wishlist;
	   }
	
	
	//회원 산리스트 추가
	  public void insertMountainWishlist(String memberIdx, Mountain mountain) {
	      Connection conn =template.getConnection();
	      
	       try {
	    	   mountainDao.insertMountainWishlist(memberIdx,mountain,conn);
	          template.commit(conn);
	       }catch (DataAccessException e) {
	          template.rollback(conn);
	          throw e;
	       }finally {
	          template.close(conn);
	       }
	   }
	
	//회원 산리스트 삭제
	  public void deleteMountainWishlist(String memberIdx, String mtIdx) {
	      Connection conn =template.getConnection();
	      
	       try {
	    	   mountainDao.deleteMountainWishlist(memberIdx,mtIdx,conn);
	          template.commit(conn);
	       }catch (DataAccessException e) {
	          template.rollback(conn);
	          throw e;
	       }finally {
	          template.close(conn);
	       }
	   }
	
	
	
	//memeber아이디로 회원찾기
	 public Member selectMemberByMemberIdx(String memberIdx) {
		   
		   Connection conn = template.getConnection();
		      Member member = null;
		      try {
		    	  member = memberDao.selectMemberByIdx(memberIdx,conn);
		         template.commit(conn);
		      }finally {
		         template.close(conn);
		      }
		      return member;
	   }
	 
	 //회원탈퇴
	public void leaveSanta(String memberIdx) {

		Connection conn = template.getConnection();
	      try {
	    	  memberDao.leaveSanta(memberIdx,conn);
	         template.commit(conn);
	      }catch (DataAccessException e) {
	             template.rollback(conn);
	             throw e;
	      }finally {
	         template.close(conn);
	      }
	}

	//마이페이지 수정
	public void editMember(Member member,String newNickname) {
		
		Connection conn = template.getConnection();
	      try {
	    	  memberDao.editMember(member,newNickname,conn);
	    	  memberDao.updateNicknameForComment(member, newNickname, conn);
	         template.commit(conn);
	      }catch (DataAccessException e) {
	             template.rollback(conn);
	             throw e;
	      }finally {
	         template.close(conn);
	      }
		
	}

	//마이페이지 수정(비번변경안함)
	public void editMemberExclusionPassword(Member member,String newNickname) {
		
		Connection conn = template.getConnection();
	      try {
	    	  memberDao.editMember(member,newNickname,conn);
	    	  memberDao.updateNicknameForComment(member, newNickname, conn);
	         template.commit(conn);
	      }catch (DataAccessException e) {
	             template.rollback(conn);
	             throw e;
	      }finally {
	         template.close(conn);
	      }
		
	}
	public void updateprofile(Member member, List<FileDTO> fileDTOs) {
		
		Connection conn = template.getConnection();
		
		try {
				memberDao.updateprofile(member,fileDTOs,conn);
			    template.commit(conn);
	    }catch (DataAccessException e) {
	           template.rollback(conn);
	           throw e;
	    }finally {
	       template.close(conn);
	    }
		
	}
	
}
