
package com.kh.santa.matching.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.common.exception.DataAccessException;
import com.kh.santa.matching.model.dto.MatchingAlarm;
import com.kh.santa.matching.model.dto.MatchingBoard;
import com.kh.santa.matching.model.dto.MatchingCompleteList;

public class MatchingBoardDao {
	
	
	JDBCTemplate template = JDBCTemplate.getInstance();
		
	public MatchingBoard selectMatchingBoardByIdx(String mbIdx,Connection conn) {
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String columns = "MB_IDX,MEMBER_IDX,MT_IDX,MATCH_STATUS,BRD_NAME,MT_DATE,BRD_DATE,MEMBER_VOLUME,MATCHED_MEM_CNT,BRD_CONTENT";
		MatchingBoard matchingBoard = null;
			try {
			
			String query = "select " + columns + " from matching_board where mb_idx = ?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, mbIdx);
			rset = pstm.executeQuery();
			
			if (rset.next()) {
				
				matchingBoard = convertRowToMatchingBoard(columns,rset);
			}
			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return matchingBoard;
	}

	public List<MatchingBoard> selectMatchingBoardList(Connection conn) {
		List<MatchingBoard> matchingBoardList = new ArrayList<MatchingBoard>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String columns = "MB_IDX,MEMBER_IDX,MT_IDX,MATCH_STATUS,BRD_NAME,MT_DATE,BRD_DATE,MEMBER_VOLUME,MATCHED_MEM_CNT,BRD_CONTENT";
		
		try {
			
			
			String query = "select " + columns + " from matching_board order by mb_idx desc";
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				matchingBoardList.add(convertRowToMatchingBoard(columns,rset));
				
			}
			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return matchingBoardList;
	}

	private MatchingBoard convertRowToMatchingBoard(String columns, ResultSet rset) throws SQLException {
		MatchingBoard matchingBoard = new MatchingBoard();
		
		String[] columnsArr = columns.split(",");
		
		for (String column : columnsArr) {
			
			switch(column) {
			case "MB_IDX":
				matchingBoard.setMbIdx(rset.getString("MB_IDX"));
				break;
			case "MEMBER_IDX":
				matchingBoard.setMemberIdx(rset.getString("MEMBER_IDX"));
				break;
			case "MT_IDX":
				matchingBoard.setMtIdx(rset.getString("MT_IDX"));
				break;
			case "MATCH_STATUS":
				matchingBoard.setMatchStatus(rset.getString("MATCH_STATUS"));
				break;
			case "BRD_NAME":
				matchingBoard.setBrdName(rset.getString("BRD_NAME"));
				break;
			case "MT_DATE":
				matchingBoard.setMtDate(rset.getDate("MT_DATE"));
				break;
			case "BRD_DATE":
				matchingBoard.setBrdDate(rset.getDate("BRD_DATE"));
				break;
			case "MEMBER_VOLUME":
				matchingBoard.setMemberVolume(rset.getInt("MEMBER_VOLUME"));
				break;
			case "MATCHED_MEM_CNT":
				matchingBoard.setMatchedMemCnt(rset.getInt("MATCHED_MEM_CNT"));
				break;
			case "BRD_CONTENT":
				matchingBoard.setBrdContent(rset.getString("BRD_CONTENT"));
				break;
			default: break;
			}
			
		}
		
		return matchingBoard;
	}

	public void insertMatchingBoard(MatchingBoard matchingBoard, Connection conn) {
		
		PreparedStatement pstm = null;
		String columns = "MB_IDX,MEMBER_IDX,MT_IDX,BRD_NAME,MT_DATE,MEMBER_VOLUME,BRD_CONTENT";
		
		String query = "insert into matching_board ( "
				+ columns
				+ " ) values(sc_mb_idx.nextval, ?, ?, ?, ?, ?, ? ) ";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, matchingBoard.getMemberIdx());
			pstm.setString(2, matchingBoard.getMtIdx());
			pstm.setString(3, matchingBoard.getBrdName());
			pstm.setDate(4, matchingBoard.getMtDate());
			pstm.setInt(5, matchingBoard.getMemberVolume());
			pstm.setString(6, matchingBoard.getBrdContent());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}

	public void insertNotice(String mbIdx, String msg, String memberIdx, Connection conn) {
		PreparedStatement pstm = null;
		String columns = "ma_idx,brd_idx,msg,sender";
		
		String query = "insert into MATCHING_ALARM ( "
				+ columns
				+ " ) values(sc_ma_idx.nextval, ?, ?, ?) ";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, mbIdx);
			pstm.setString(2, msg);
			pstm.setString(3, memberIdx);
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}

	public List<MatchingCompleteList> selectMatchingCompleteListByMemberIdx(String memberIdx, Connection conn) {
		List<MatchingCompleteList> mclList = new ArrayList<MatchingCompleteList>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String columns = "list_idx,member_idx,mb_idx";
		
		try {
			
			String query = "select " + columns + " from MATCHING_COMPLETE_LIST where member_idx = ? ";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, memberIdx);
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				mclList.add(convertRowToMatchingCompleteList(columns, rset));
				
			}
			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return mclList;
	}

	private MatchingCompleteList convertRowToMatchingCompleteList(String columns, ResultSet rset) throws SQLException {
		MatchingCompleteList matchingCompleteList = new MatchingCompleteList();
		
		String[] columnsArr = columns.split(",");
		
		for (String column : columnsArr) {
			
			switch(column) {
			case "list_idx":
				matchingCompleteList.setListIdx(rset.getString("list_idx"));
				break;
			case "member_idx":
				matchingCompleteList.setMemberIdx(rset.getString("member_idx"));
				break;
			case "mb_idx":
				matchingCompleteList.setMbIdx(rset.getString("mb_idx"));
				break;
			default: break;
			}
			
		}
		
		return matchingCompleteList;
	}

	public MatchingAlarm selectMatchingAlarm(String mbIdx, Connection conn) {
		MatchingAlarm matchingAlarm = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String columns = "ma_idx,mb_idx,msg,sender";
		
		try {
			
			String query = "select " + columns + " from MATCHING_ALARM where mb_idx = ? ";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, mbIdx);
			rset = pstm.executeQuery();
			
			if (rset.next()) {
				
				matchingAlarm = new MatchingAlarm();
				matchingAlarm.setMaIdx(rset.getString("ma_idx")); 
				matchingAlarm.setMbIdx(rset.getString("mb_idx")); 
				matchingAlarm.setMsg(rset.getString("msg")); 
				matchingAlarm.setSender(rset.getString("sender")); 
				
			}
			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return matchingAlarm;
	}
	
}