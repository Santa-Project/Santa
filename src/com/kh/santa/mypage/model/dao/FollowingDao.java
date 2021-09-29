package com.kh.santa.mypage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.common.exception.DataAccessException;
import com.kh.santa.mypage.model.dto.Follow;
import com.kh.santa.mypage.model.dto.Follower;
import com.kh.santa.mypage.model.dto.Member;
import com.kh.santa.mypage.model.dto.MemberBoard;

public class FollowingDao {

	JDBCTemplate template = JDBCTemplate.getInstance();
	
	//팔로우 추가 (상대방 마이페이지에서)
	public void insertFollow(Follow follow, Connection conn) { //내가 추가

	      PreparedStatement pstm = null;
	      
	      String sql = "INSERT INTO follow(follow_idx,member_idx,follow_id) VALUES(sc_follow_idx.nextval,?,?)";
	      
	         try {
	            pstm =conn.prepareStatement(sql);
	            pstm.setString(1, follow.getMemberIdx());
	            pstm.setString(2, follow.getFollowId()); 
	            pstm.executeUpdate(); 
	         } catch (SQLException e) {
	            throw new DataAccessException(e);
	         }finally {
	            template.close(pstm);
	         }
	}
	
	public void insertFollower(Follow follow, Connection conn) { //상대방이 추가

	      PreparedStatement pstm = null;
	      							//
	      String sql = "INSERT INTO follower(follower_idx,member_idx,follower_id) VALUES(sc_follower_idx.nextval,?,?)";
	      
	         try {
	            pstm =conn.prepareStatement(sql);
	            pstm.setString(1, follow.getFollowId());   //여기서 follower_idx와 member_idx가 바껴서 들어감
	            pstm.setString(2, follow.getMemberIdx());  //내가 follower가 됨
	            pstm.executeUpdate(); 
	         } catch (SQLException e) {
	            throw new DataAccessException(e);
	         }finally {
	            template.close(pstm);
	         }
	}
	
	
	// 팔로우 해제 (상대방 마이페이지 or 내 마이페이지 팔로우화면)
	public void deleteFollow(Follow follow, Connection conn) { //내가 언팔
		
		PreparedStatement pstm = null;
		
		try {
			String query = "DELETE FROM follow WHERE member_idx = ? and follow_id = ? "; 
			pstm = conn.prepareStatement(query);
			pstm.setString(1, follow.getMemberIdx());
			pstm.setString(2, follow.getFollowId());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
           throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}
	}
	
	public void deleteFollower(Follow follow, Connection conn) { //상대방이 언팔
		
		PreparedStatement pstm = null;
		
		try {							//
			String query = "DELETE FROM follower WHERE member_idx = ? and follower_id = ? ";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, follow.getFollowId());  //여기서  follower_idx와 member_idx가 바껴서 들어감
			pstm.setString(2, follow.getMemberIdx());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
           throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}
	}
	
	

	public List<Member> FollowList(String memberIdx,Connection conn){ //내 팔로우 리스트 가져오기
		
		List<Member> followList = new ArrayList<Member>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {

			String query = "SELECT * FROM member WHERE member_idx in (select follow_id from follow where member_idx = ?)"; 
			pstm = conn.prepareStatement(query);
			pstm.setString(1, memberIdx); 
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Member member = convertRowTofollowMember(rset);
				followList.add(member);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close( rset, pstm);
		}
		return followList;
	}

	
	public List<Member> FollowerList(String memberIdx,Connection conn){ //내 팔로워 리스트 가져오기
		
		List<Member> followerList = new ArrayList<Member>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {																				//
			String query = "SELECT * FROM member WHERE member_idx in (select follower_id from follower where member_idx = ?)";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, memberIdx);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Member member = convertRowTofollowMember(rset);
				followerList.add(member);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close( rset, pstm);
		}
		return followerList;
	}
	
	private Member convertRowTofollowMember(ResultSet rset) throws SQLException {
		
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
