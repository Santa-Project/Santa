package com.kh.santa.mypage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.common.exception.DataAccessException;
import com.kh.santa.common.file.FileDTO;
import com.kh.santa.mountainInfo.model.dto.MountainWishlist;
import com.kh.santa.mypage.model.dto.Member;

public class MemberDao {

	JDBCTemplate template = JDBCTemplate.getInstance();

	public Member memberAuthenticate(String userId, String password, Connection conn) {
		
		Member member = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String columns = "member_idx,user_id,email,user_password,username,nickname,phone,gender,address,register_datetime,social_login,profile_photo,PROFILE_CONTENT";
		
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

	public String memberAuthenticateByKakaoId(String kakaoId, Connection conn) {
		String memberIdx = null;
		PreparedStatement pstm = null;
		
		ResultSet rset = null;
		
		
		try {
			String query = " select member_idx from social_login where kakao_id = ? ";
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, kakaoId);
			rset = pstm.executeQuery(); // -- 쿼리 조회 결과를 참조할 주소값을 받음
			
			// 5. ResultSet에 저장된 데이터를 DTO에 옮겨닮기
			if(rset.next()) {
				memberIdx = rset.getString("member_idx");
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset,pstm);
		}
		return memberIdx;
	}

	public Member selectMemberById(String userId, Connection conn) {
		Member member = null;
		PreparedStatement pstm = null;
		String columns = "member_idx,user_id,email,user_password,username,nickname,phone,gender,address,register_datetime,social_login,profile_photo,PROFILE_CONTENT";
		
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
	
	public Member selectMemberByIdx(String memberIdx, Connection conn) {
		Member member = null;
		PreparedStatement pstm = null;
		String columns = "member_idx,user_id,email,user_password,username,nickname,phone,gender,address,register_datetime,social_login,profile_photo,PROFILE_CONTENT";
		
		ResultSet rset = null;
		
		try {
			String query = " select " + columns + " from member where member_idx = ? ";
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, memberIdx);
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
		
		String query = "insert into social_login "
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
		String columns = "member_idx,user_id,email,user_password,username,nickname,phone,gender,address";
		
		String query = "insert into member ( "
				+ columns
				+ " ) values(sc_member_idx.nextval, ?, ?, ? , ? , ? , ? , ?, ? ) ";
		
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
		String columns = "member_idx,user_id,email,user_password,username,nickname,phone,gender,address,profile_photo";
		
		String query = "insert into member ( "
				+ columns
				+ " ) values(sc_member_idx.nextval, 'kakao' || sc_kakao_idx.nextval, "
				+ "	?,'-',?, 'kakao' || sc_kakao_idx.currval ,'-',?,'-', ?) ";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, member.getEmail());
			pstm.setString(2, member.getUsername());
			pstm.setString(3, member.getGender());
			pstm.setString(4, member.getProfilePhoto());
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
		String columns = "member_idx,user_id,email,user_password,username,nickname,phone,gender,address,register_datetime,social_login,profile_photo,PROFILE_CONTENT";
		
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
			case "profile_photo":
				member.setProfilePhoto(rset.getString("profile_photo"));
				break;
			case "social_login":
				member.setSocialLogin(rset.getString("social_login"));
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
	
	
	

	   // member_idx로 멤버 찾기
	public Member selectMemberByMemberIdx(String memberIdx, Connection conn) {
		Member member = null;
		PreparedStatement pstm = null;
		String columns = "member_idx,user_id,email,username,nickname,phone,gender,address,register_datetime,profile_content,social_login,profile_photo,PROFILE_CONTENT";
		
		ResultSet rset = null;
		
		try {
			String query = " select " + columns + " from member where member_idx = ? ";
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, memberIdx);
			rset = pstm.executeQuery(); 
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
	
	public void editMember(Member member,String nickname, Connection conn) {
		
		PreparedStatement pstm = null;
		
		String query = "UPDATE member SET " +
						"USER_PASSWORD = ?, NICKNAME = ?, PHONE = ?, EMAIL = ?, ADDRESS =?"+
					   " WHERE member_idx = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, member.getUserPassword());
			pstm.setString(2, nickname);
			pstm.setString(3, member.getPhone()); 
			pstm.setString(4, member.getEmail());
			pstm.setString(5, member.getAddress());
			pstm.setString(6, member.getMemberIdx());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}
	
	
	//마이페이지 수정(비번 변경안할시)
	public void editMemberExclusionPassword(Member member,String nickname, Connection conn) {
		
		PreparedStatement pstm = null;
		
		String query = "UPDATE member SET " +
						" NICKNAME = ?, PHONE = ?, EMAIL = ?, ADDRESS =?"+ 
					   " WHERE member_idx = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, nickname);
			pstm.setString(2, member.getPhone()); 
			pstm.setString(3, member.getEmail());
			pstm.setString(4, member.getAddress());
			pstm.setString(5, member.getMemberIdx());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}	
	}

	//프로필 수정 (사진,코멘트)
	public void updateprofile(Member member,List<FileDTO> fileDTOs, Connection conn) {
		
		PreparedStatement pstm = null;
		
		//사진경로
		String photoPath = fileDTOs.get(0).getSavePath() + fileDTOs.get(0).getRenameFileName();
		
		String query = "UPDATE member SET " +
						" PROFILE_CONTENT = ?, PROFILE_PHOTO = ? WHERE member_idx = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, member.getProfileContent());
			pstm.setString(2, photoPath);
			pstm.setString(3, member.getMemberIdx()); 
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}	
	}

	//회원탈퇴
	public void leaveSanta(String memberIdx, Connection conn) {
		
		 PreparedStatement pstm = null;
			
			try {
				String query = "DELETE CASCADE FROM member WHERE member_idx = ? "; 
				pstm = conn.prepareStatement(query);
				pstm.setString(1, memberIdx);
				pstm.executeUpdate();
				
			} catch (SQLException e) {
	          throw new DataAccessException(e);
			}finally {
				template.close(pstm);
			}
	}
	
	
	//특정회원의 산 위시리스트 조회
	public List<MountainWishlist> selectMountainWishlist(String memberIdx, Connection conn) {
	      
	      List<MountainWishlist> mountainWishlist = new ArrayList<MountainWishlist>();
	      PreparedStatement pstm = null;
	      ResultSet rset =null;
	      String columns= "mt_idx, member_idx, mt_name";
	      String sql ="SELECT "+columns+" FROM mountain_wishlist WHERE member_idx = ? order by mt_idx desc";
	      
	      try {
	         pstm =conn.prepareStatement(sql);
	         pstm.setString(1, memberIdx);
	         rset = pstm.executeQuery();
	         
	         while(rset.next()) {
	        	 MountainWishlist mountainWish = convertRowToMountainWishlist(columns.split(","),rset);
	            mountainWishlist.add(mountainWish);
	         }
	      } catch (SQLException e) {
	         throw new DataAccessException(e);
	      }finally{
	         template.close(rset,pstm);
	      }
	      return mountainWishlist;
	   }
	
	 private MountainWishlist convertRowToMountainWishlist(String[] columns,ResultSet rset) throws SQLException {
		 MountainWishlist whishlist = new MountainWishlist();
	      for(int i=0; i<columns.length; i++) {
	         
	      String column=columns[i].toLowerCase();
	      column=column.trim();
	       switch(column) {
	       case "mt_idx": whishlist.setMtIdx(rset.getString("mt_idx"));break;
	       case "member_idx": whishlist.setMemberIdx(rset.getString("member_idx"));break;
	       case "mt_name": whishlist.setMtName(rset.getString("mt_name"));break;
	         }
	      }
	      return whishlist;
	   }

	public String selectNicknameByIdx(String memberIdx, Connection conn) {
		String leaderNickName = "";
		PreparedStatement pstm = null;
		
		ResultSet rset = null;
		
		try {
			String query = " select nickname from member where member_idx = ? ";
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, memberIdx);
			rset = pstm.executeQuery(); 
			if(rset.next()) {
				leaderNickName = rset.getString("nickname");
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset,pstm);
		}
		return leaderNickName;
	}

	public Member selectMemberByNickname(String nickname, Connection conn) {
		Member member = null;
		PreparedStatement pstm = null;
		String columns = "member_idx,user_id,email,user_password,username,nickname,phone,gender,address,register_datetime,social_login";
		
		ResultSet rset = null;
		
		try {
			String query = " select " + columns + " from member where nickname = ? ";
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, nickname);
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

	//닉네임 변경시 댓글작성한 닉네임도 업데이트
	public void updateNicknameForComment(Member member,String newNickname, Connection conn) {
		
		PreparedStatement pstm = null;
		
		String query = "UPDATE member_board_comment SET " +
						" NICKNAME = ?"+ 
					   " WHERE member_idx = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, newNickname);
			pstm.setString(2, member.getMemberIdx()); 
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}	
		
	}
	
	
	
}