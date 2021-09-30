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
		String columns = "member_idx,user_id,email,user_password,username,nickname,phone,gender,address,register_datetime,social_login,grade";
		
		try {
			String query = "select " + columns + " from member where user_id = ? and user_password = ? ";
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			pstm.setString(2, password);
			rset = pstm.executeQuery(); // -- 쿼리 조회 결과를 참조할 주소값을 받음
			
			// 5. ResultSet에 저장된 데이터를 DTO에 옮겨닮기
			if(rset.next()) {
				member = convertRowToMember(columns, rset);
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset,pstm);
		}
		return member;
	}

	public Member memberAuthenticateByKakaoId(String kakaoId, Connection conn) {
		Member member = null;
		PreparedStatement pstm = null;
		String columns = "member_idx,user_id,email,user_password,username,nickname,phone,gender,address,register_datetime,social_login,grade";
		
		ResultSet rset = null;
		
		
		try {
			String query = " select " + columns + " from member where member_idx = (select member_idx from social_member where kakao_id = ?) ";
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, kakaoId);
			rset = pstm.executeQuery(); // -- 쿼리 조회 결과를 참조할 주소값을 받음
			
			// 5. ResultSet에 저장된 데이터를 DTO에 옮겨닮기
			if(rset.next()) {
				member = convertRowToMember(columns,rset);
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset,pstm);
		}
		return member;
	}

	public Member selectMemberById(String userId, Connection conn) {
		Member member = null;
		PreparedStatement pstm = null;
		String columns = "member_idx,user_id,email,user_password,username,nickname,phone,gender,address,register_datetime,social_login,grade";
		
		ResultSet rset = null;
		
		try {
			String query = " select " + columns + " from member where user_id = ? ";
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			rset = pstm.executeQuery(); // -- 쿼리 조회 결과를 참조할 주소값을 받음
			
			// 5. ResultSet에 저장된 데이터를 DTO에 옮겨닮기
			if(rset.next()) {
				member = convertRowToMember(columns, rset);
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset,pstm);
		}
		return member;
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
	
	public void insertMember(Member member, Connection conn) {
		
		PreparedStatement pstm = null;
		String columns = "member_idx,user_id,email,user_password,username,nickname,phone,gender,address,register_datetime,social_login,grade";
		
		String query = "insert into member ( "
				+ columns
				+ " ) values(sc_member_idx.nextval, ?, ?, ? , ? , ? , ? , ?, ? ,sysdate,'N','US00') ";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, member.getUserId());
			pstm.setString(2, member.getEmail());
			pstm.setString(3, member.getUserPassword());
			pstm.setString(4, member.getUsername());
			pstm.setString(5, member.getNickname());
			pstm.setString(6, member.getPhone());
			pstm.setString(7, member.getGender());
			pstm.setString(8, member.getAddress());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}

	public void insertMemberWithKakao(Member member, Connection conn) {
		
		PreparedStatement pstm = null;
		String columns = "member_idx,user_id,email,user_password,username,nickname,phone,gender,address,register_datetime,social_login,grade";
		
		String query = "insert into member "
				+ columns
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
		String columns = "member_idx,user_id,email,user_password,username,nickname,phone,gender,address,register_datetime,social_login,grade";
		
		ResultSet rset = null;
		
		try {
			String query = "select " + columns + " from member "
					+ " join (select member_idx, count(follower_id) as cnt "
					+ " from follower group by member_idx order by cnt desc)  "
					+ " using(member_idx) where rownum < 10 ";
			
			
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery(); // -- 쿼리 조회 결과를 참조할 주소값을 받음
			
			// 5. ResultSet에 저장된 데이터를 DTO에 옮겨닮기
			for (int i = 0; i < memberArr.length; i++) {
				if(rset.next()) {
					memberArr[i] = convertRowToMember(columns,rset);
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

	public boolean checkMemberById(String userId, Connection conn) {
		PreparedStatement pstm = null;
		String columns = "user_id";
		
		ResultSet rset = null;
		
		try {
			String query = " select " + columns + " from member where user_id = ? ";
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			rset = pstm.executeQuery(); // -- 쿼리 조회 결과를 참조할 주소값을 받음
			
			// 5. ResultSet에 저장된 데이터를 DTO에 옮겨닮기
			if(rset.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset,pstm);
		}
		return false;
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
	
	private Member convertRowToMember(String columns, ResultSet rset) throws SQLException {
		Member member = new Member();
		
		String[] columnsArr = columns.split(",");
		
		for (String column : columnsArr) {
			
			switch(column) {
			case "member_idx":
				member.setMemberIdx(rset.getString("member_idx"));
				break;
			case "user_id":
				member.setUserId(rset.getString("user_id"));
				break;
			case "email":
				member.setEmail(rset.getString("email"));
				break;
			case "user_password":
				member.setUserPassword(rset.getString("user_password"));
				break;
			case "username":
				member.setUsername(rset.getString("username"));
				break;
			case "nickname":
				member.setNickname(rset.getString("nickname"));
				break;
			case "phone":
				member.setPhone(rset.getString("phone"));
				break;
			case "gender":
				member.setGender(rset.getString("gender"));
				break;
			case "address":
				member.setAddress(rset.getString("address"));
				break;
			case "register_datetime":
				member.setRegisterDatetime(rset.getDate("register_datetime"));
				break;
			case "profile_content":
				member.setProfileContent(rset.getString("profile_content"));
				break;
			case "photo":
				member.setPhoto(rset.getString("photo"));
				break;
			case "social_login":
				member.setSocialLogin(rset.getString("social_login"));
				break;
			case "grade":
				member.setGrade(rset.getString("grade"));
				break;
			default: break;
			}
			
		}
		
		return member;
	}

	public String selectMemberByNameAndEmail(String username, String email, Connection conn) {
		String foundId = "";
		
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = " select user_id from member where username = ? and email = ? ";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, username);
			pstm.setString(2, email);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				foundId = rset.getString("user_id");
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		
		return foundId;
	}

	public String selectMemberByIdAndEmail(String id, String email, Connection conn) {
String foundPw = "";
		
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = " select user_password from member where user_id = ? and email = ? ";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, id);
			pstm.setString(2, email);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				foundPw = rset.getString("user_password");
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		
		return foundPw;
	}
}
