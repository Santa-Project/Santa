package com.kh.santa.main.model.service;


import java.sql.Connection;
import java.util.List;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.common.http.HttpConnector;
import com.kh.santa.common.http.RequestParams;
import com.kh.santa.common.mail.MailSender;
import com.kh.santa.mountainInfo.model.dao.MountainDao;
import com.kh.santa.mountainInfo.model.dto.Mountain;
import com.kh.santa.mypage.model.dao.MemberDao;
import com.kh.santa.mypage.model.dto.Member;

public class MainService {

	private JDBCTemplate template = JDBCTemplate.getInstance();
	private MemberDao memberDao = new MemberDao();
	private MountainDao mountainDao = new MountainDao();
	
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

	public boolean checkMemberById(String userId) {
		Connection conn = template.getConnection();
		
		try {
			return memberDao.checkMemberById(userId, conn);
		} finally {
			template.close(conn);
		}
	}

	public void authenticateEmail(Member member, String persistToken) {
		
		HttpConnector conn = new HttpConnector();
		
		String queryString = conn.urlEncodedForm(RequestParams.builder()
													.param("mail-template", "join-auth-email")
													.param("persistToken", persistToken)
													.param("userId", member.getUserId())
													.build());
		
		String mailTemplate = conn.get("http://localhost:7070/mail?"+queryString);
		MailSender sender = new MailSender();
		sender.sendEmail(member.getEmail(), "환영합니다." + member.getUserId() + "님", mailTemplate);
		
	}

	public void insertMemberAndPreperence(Member member, List<Mountain> mountainList) {
		Connection conn = template.getConnection();
		try {
			// 회원가입처리
			memberDao.insertMember(member, conn);
			
			// 산 위시리스트 추가
			// 방금 가입한 회원의 아이디로 정보를 다시 조회
			Member m = memberDao.selectMemberById(member.getUserId(), conn);
			
			for (Mountain mountain : mountainList) {
				mountainDao.insertMountainWishlist(m.getMemberIdx(), mountain, conn);
				mountain = mountainDao.selectMountainBymtIdx(mountain.getMtIdx(), conn);
				int addLike = mountain.getLikedMountainCnt() + 1;
				System.out.println(addLike);
				mountainDao.updateMountainLike(addLike, mountain, conn);
			}
			
			// 회원가입 이후 자동 로그인처리(안함)
			// dao를 통해 사용자 정보를 받아서 해당 정보로 로그인 처리 진행
			// System.out.println(member.getUserId() + "의 로그인처리 로직이 동작했습니다.");
			
			template.commit(conn);
			
		} catch(Exception e){
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		
	}

	public void insertMember(Member member) {
		Connection conn = template.getConnection();
		try {
			// 회원가입처리
			memberDao.insertMember(member, conn);
			
			template.commit(conn);
			
		} catch(Exception e){
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		
	}

	public String findingId(String username, String email) {
		String foundId = "";
		Connection conn = template.getConnection();
		
		try {
			foundId = memberDao.selectMemberByNameAndEmail(username,email,conn);
		} finally {
			template.close(conn);
		}
		
		return foundId;
	}

	public String findingPassword(String id, String email) {
		String foundPw = "";
		Connection conn = template.getConnection();
		
		try {
			foundPw = memberDao.selectMemberByIdAndEmail(id,email,conn);
		} finally {
			template.close(conn);
		}
		
		return foundPw;
	}

}
