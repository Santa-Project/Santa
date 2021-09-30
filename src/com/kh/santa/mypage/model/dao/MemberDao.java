package com.kh.santa.mypage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.common.exception.DataAccessException;
import com.kh.santa.mypage.model.dto.Member;

public class MemberDao {

	JDBCTemplate template = JDBCTemplate.getInstance();

	public Member memberAuthenticate(String userId, String password, Connection conn) {
		
		Member member = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			String query = "select * from member where user_id = ? and user_password = ? ";
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			pstm.setString(2, password);
			rset = pstm.executeQuery(); // -- 쿼리 조회 결과를 참조할 주소값을 받음
			
			// 5. ResultSet에 저장된 데이터를 DTO에 옮겨닮기
			if(rset.next()) {
				member = convertRowToMember(rset);
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset,pstm);
		}
		return member;
	}

	private Member convertRowToMember(ResultSet rset) throws SQLException {
		
		Member member = new Member();
		member.setMemberIdx(rset.getString("member_idx"));
		member.setUserId(rset.getString("user_id"));
		member.setEmail(rset.getString("email"));
		member.setUserPassword(rset.getString("user_password"));
		member.setUsername(rset.getString("username"));
		member.setNickname(rset.getString("nickname"));
		member.setPhone(rset.getString("phone"));
		member.setGender(rset.getString("gender"));
		member.setAddress(rset.getString("address"));
		member.setRegisterDatetime(rset.getDate("register_datetime"));
		member.setProfileContent(rset.getString("profile_content"));
		/* member.setPhoto(rset.getString("photo")); */
		member.setSocialLogin(rset.getString("social_login"));
		member.setGrade(rset.getString("grade"));
		
		return member;
	}

	public Member memberAuthenticateByKakaoId(String kakaoId, Connection conn) {
		Member member = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			String query = "select * from member where member_idx = (select member_idx from social_member where kakao_id = ?) ";
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, kakaoId);
			rset = pstm.executeQuery(); // -- 쿼리 조회 결과를 참조할 주소값을 받음
			
			// 5. ResultSet에 저장된 데이터를 DTO에 옮겨닮기
			if(rset.next()) {
				member = convertRowToMember(rset);
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset,pstm);
		}
		return member;
	}

	public Member selectMemberById(String userId, Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertSocialLoginKakao(String kakaoId, Connection conn) {
		
		PreparedStatement pstm = null;
		
		String query = "insert into social_member "
				+ " values(sc_social_idx.nextval,sc_member_idx.currval,?)";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, kakaoId);
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}

	public void insertMemberWithKakao(Member member, Connection conn) {
		
		PreparedStatement pstm = null;
		
		String query = "insert into member"
				+ " (MEMBER_IDX,USER_ID,EMAIL,USER_PASSWORD,USERNAME,NICKNAME,PHONE,GENDER,ADDRESS,REGISTER_DATETIME,SOCIAL_LOGIN,GRADE) "
				+ " values(sc_member_idx.nextval, 'kakao' || sc_kakao_idx.nextval, "
				+ "	?,'-','-',?,'-',?,'-',sysdate,'Y','US00') ";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, member.getEmail());
			pstm.setString(2, member.getNickname());
			pstm.setString(3, member.getGender());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}

	public Member[] selectMemberTop10(Connection conn) {
		
		Member[] memberArr = new Member[9];
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			String query = "select * from member "
					+ "join (select member_idx, count(follower_id) as cnt "
					+ "from follower group by member_idx order by cnt desc)  "
					+ "using(member_idx) where rownum < 10 ";
			
			
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery(); // -- 쿼리 조회 결과를 참조할 주소값을 받음
			
			// 5. ResultSet에 저장된 데이터를 DTO에 옮겨닮기
			for (int i = 0; i < memberArr.length; i++) {
				if(rset.next()) {
					memberArr[i] = convertRowToMember(rset);
				}
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset,pstm);
		}
		return memberArr;
	}

	public void leaveSanta(String memberIdx, Connection conn) {
		
		 PreparedStatement pstm = null;
			
			try {
				String query = "DELETE FROM member WHERE member_idx = ? "; 
				pstm = conn.prepareStatement(query);
				pstm.setString(1, memberIdx);
				pstm.executeUpdate();
				
			} catch (SQLException e) {
	          throw new DataAccessException(e);
			}finally {
				template.close(pstm);
			}
	}

	
	
	
	
	public void editMember(Member member, Connection conn) {
		
		PreparedStatement pstm = null;
		
		String query = "UPDATE member SET " +
						"USER_PASSWORD = ?, NICKNAME = ?, PHONE = ?, EMAIL = ?, ADDRESS =?, PHOTO =?, MEMBER_IDX=?"+
					   " WHERE member_idx = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, member.getUserPassword());
			pstm.setString(2, member.getNickname());
			pstm.setString(3, member.getPhone()); 
			pstm.setString(4, member.getEmail());
			pstm.setString(5, member.getAddress());
			pstm.setString(6, member.getPhoto());
			pstm.setString(6, member.getMemberIdx());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}
	
}
