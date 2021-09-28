package com.kh.santa.mountainInfo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
      
	   mountain.setMtIdx(String.valueOf(rset.getInt("mtidx")));
	   System.out.println(mountain.getMtIdx());
	   mountain.setMountainName(rset.getString("mname"));
	   System.out.println(mountain.getMountainName());

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
	
}
