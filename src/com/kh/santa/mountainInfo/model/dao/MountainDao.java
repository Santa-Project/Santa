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
	  String query = "select * from mountain where mtidx = ?";
	  
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
	
   private Mountain convertRowToMountain(ResultSet rset) throws SQLException {
	   Mountain mountain = new Mountain();
      
	   mountain.setMtIdx(String.valueOf(rset.getInt("MTIDX")));
	   mountain.setMountainName(rset.getString("MNAME"));
	   mountain.setLikedMountainCnt(rset.getInt("LIKED_MOUNTAIN_CNT"));

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
			pstm.setString(1, mountain.getMtIdx());
			pstm.setString(2, memberIdx);
			pstm.setString(3, mountain.getMountainName());
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
		String query = "select * from mountain";
	
		try {
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Mountain mountain = new Mountain();
				
				mountain.setMtIdx(String.valueOf(rset.getInt("mtidx")));
				mountain.setMountainName(rset.getString("mname"));
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
		
		String query = "update mountain set liked_mountain_cnt = ? where mtidx = ?";
		
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
