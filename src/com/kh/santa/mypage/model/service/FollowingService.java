package com.kh.santa.mypage.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.common.exception.DataAccessException;
import com.kh.santa.mypage.model.dao.FollowingDao;
import com.kh.santa.mypage.model.dto.Follow;
import com.kh.santa.mypage.model.dto.Member;

public class FollowingService {

	   private FollowingDao followingDao = new FollowingDao();
	   private JDBCTemplate template = JDBCTemplate.getInstance();
	
	   
	   public  List<Member> FollowList(String memberIdx) { 
		   
		   Connection conn = template.getConnection();
		   List<Member> followList = null;
		      
	      try {
	    	 followList = followingDao.FollowList(memberIdx,conn);
	         template.commit(conn);
	      }catch (DataAccessException e) {
	             template.rollback(conn);
	             throw e;
	      }finally {
	         template.close(conn);
	      }
	      return followList;
	   }
	   
	   public  List<Member> FollowerList(String memberIdx) { 
		   
		   Connection conn = template.getConnection();
		   List<Member> followerList = null;
		      
	      try {
	    	  followerList = followingDao.FollowerList(memberIdx,conn);
	         template.commit(conn);
	      }catch (DataAccessException e) {
	             template.rollback(conn);
	             throw e;
	      }finally {
	         template.close(conn);
	      }
	      return followerList;
	   }
	 
	   
   public void insertFollow(Follow follow) {
		
		Connection conn = template.getConnection();
	      try {
	    	  followingDao.insertFollow(follow,conn);
	    	  followingDao.insertFollower(follow,conn);//
	    	  template.commit(conn);
	      }catch (DataAccessException e) {
	             template.rollback(conn);
	             throw e;
	      }finally {
	         template.close(conn);
	      }
	}
	   
	public void deleteFollow(Follow follow) {
		
		Connection conn = template.getConnection();
	      try {
	    	  followingDao.deleteFollower(follow,conn);
	    	  followingDao.deleteFollow(follow,conn);
	    	  template.commit(conn);
	      }catch (DataAccessException e) {
	             template.rollback(conn);
	             throw e;
	      }finally {
	         template.close(conn);
	      }
	}
	   
	   
	
}
