package com.kh.santa.mypage.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.common.exception.DataAccessException;
import com.kh.santa.mypage.model.dao.MemberDao;
import com.kh.santa.mypage.model.dto.Member;

public class MypageService {

	private MemberDao memberDao = new MemberDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	
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

	public void editMember(Member member) {
		
		Connection conn = template.getConnection();
	      try {
	    	  memberDao.editMember(member,conn);
	         template.commit(conn);
	      }catch (DataAccessException e) {
	             template.rollback(conn);
	             throw e;
	      }finally {
	         template.close(conn);
	      }
		
	}
	
}
