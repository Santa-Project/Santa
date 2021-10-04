package com.kh.santa.mountainInfo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.common.exception.DataAccessException;
import com.kh.santa.mountainInfo.model.dto.Mountain;

public class MountainDao {

	JDBCTemplate template = JDBCTemplate.getInstance();
	
	public Mountain selectMountainBymtIdx(String mtIdx, Connection conn) {
	  Mountain mountain = null;
	  PreparedStatement pstm = null;
	  ResultSet rset = null;
	  String query = "select * from mountain where mt_idx = ?";
	  
	  try {
		 pstm = conn.prepareStatement(query);
		 pstm.setInt(1, Integer.parseInt(mtIdx));
		 rset = pstm.executeQuery();
		 
		 if(rset.next()) {
		    mountain = convertRowToMountain(rset);
		 }
	     
	  } catch (SQLException e) {
		  throw new DataAccessException(e);
	  } finally {
		  template.close(rset, pstm);
	  }
	  
	  return mountain;
	}
	
	public String selectselectMountainNameBymtIdx(String mtIdx, Connection conn) {
		String mountainName = "";
		PreparedStatement pstm = null;
		ResultSet rset = null;
		  
		try {
			String query = "select mt_name from mountain where mt_idx = ?";
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, Integer.parseInt(mtIdx));
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				mountainName = rset.getString("mt_name");
			}
		     
		  } catch (SQLException e) {
			  throw new DataAccessException(e);
		  } finally {
			  template.close(rset, pstm);
		  }
		  
		  return mountainName;
		}
	
   private Mountain convertRowToMountain(ResultSet rset) throws SQLException {
	   Mountain mountain = new Mountain();
      
	   mountain.setMtIdx(String.valueOf(rset.getInt("mt_idx")));
	   mountain.setMtName(rset.getString("mt_name"));
	   mountain.setLikedMountainCnt(rset.getInt("liked_mountain_cnt"));

	   return mountain;
   }
   
	public Boolean checkMountainWishlist(String memberIdx, String mtIdx, Connection conn) {

		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select * from mountain_wishlist where member_idx = ? and mt_idx = ?";
		      
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, memberIdx);
			pstm.setString(2, mtIdx);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				return true;
			}
		
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return false;
   }
	
	public void insertMountainWishlist(String memberIdx, Mountain mountain, Connection conn) {

		PreparedStatement pstm = null;

		String query = "insert into mountain_wishlist values(?,?,?)";

		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, memberIdx);
			pstm.setString(2, mountain.getMtName());
			pstm.setString(3, mountain.getMtIdx());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}

	}
	
	public void deleteMountainWishlist(String memberIdx, String mtIdx, Connection conn) {

		PreparedStatement pstm = null;

		String query = "delete from mountain_wishlist where member_idx = ? and mt_idx = ?";

		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, memberIdx);
			pstm.setString(2, mtIdx);
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}

	public List<Mountain> searchAllMtIdxAndMtName(Connection conn) {
		List<Mountain> mountainList = new ArrayList<Mountain>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select mt_idx, mt_name from mountain";
	
		try {
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Mountain mountain = new Mountain();
				
				mountain.setMtIdx(String.valueOf(rset.getInt("mt_idx")));
				mountain.setMtName(rset.getString("mt_name"));
				mountainList.add(mountain);
			}
		  
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return mountainList;
	}

	public void updateMountainLike(int updatedlike, Mountain mountain, Connection conn) {
		PreparedStatement pstm = null;
		
		String query = "update mountain set liked_mountain_cnt = ? where mt_idx = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, updatedlike);
			pstm.setString(2, mountain.getMtIdx());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}
	
}
