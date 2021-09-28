package com.kh.santa.main.model.service;


import java.sql.Connection;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.mypage.model.dao.MemberDao;
import com.kh.santa.mypage.model.dto.Member;

public class MainService {

	private JDBCTemplate template = JDBCTemplate.getInstance();
	private MemberDao memberDao = new MemberDao();
	
	public Member memberAuthenticate(String userId, String password) {
		
		Member member = null;
		Connection conn = template.getConnection();
		
		try {
			member = memberDao.memberAuthenticate(userId, password, conn);
		} finally {
			template.close(conn);
		}
		
		return member;
	}

	public Member searchByKakaoId(String kakaoId) {

		Member member = null;
		Connection conn = template.getConnection();
		
		try {
			member = memberDao.memberAuthenticateByKakaoId(kakaoId, conn);
		} finally {
			template.close(conn);
		}
		
		return member;
	}

	public void createMemberWithKakao(Member member, String kakaoId) {
		
		Connection conn = template.getConnection();
		
		try {
			//회원 테이블에 instance추가
			memberDao.insertMemberWithKakao(member, conn);
			//소셜로그인 테이블에 instance추가
			memberDao.insertSocialLoginKakao(kakaoId, conn);
			
			template.commit(conn);
		} catch(Exception e){
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}

	public Member selectMemberById(String userId) {
		
		Member member = null;
		Connection conn = template.getConnection();
		
		try {
			member = memberDao.selectMemberById(userId, conn);
		} finally {
			template.close(conn);
		}
		
		return member;
	}

}
