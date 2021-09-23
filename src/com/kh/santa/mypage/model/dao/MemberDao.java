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
			pstm.setString(1, userId);;
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
	
	
	
	
	
}
