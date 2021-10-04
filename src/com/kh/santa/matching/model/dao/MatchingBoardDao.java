
package com.kh.santa.matching.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.common.exception.DataAccessException;
import com.kh.santa.matching.model.dto.FindingMember;
import com.kh.santa.matching.model.dto.MatchingAlarm;
import com.kh.santa.matching.model.dto.MatchingBoard;
import com.kh.santa.matching.model.dto.MatchingCompleteList;
import com.kh.santa.matching.model.dto.WaitingList;

public class MatchingBoardDao {
	
	
	JDBCTemplate template = JDBCTemplate.getInstance();
		
	public MatchingBoard selectMatchingBoardByIdx(String mbIdx,Connection conn) {
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String columns = "mb_idx,member_idx,mt_idx,match_status,brd_name,mt_date,brd_date,mem_volume,matched_mem_cnt,brd_content";

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
		String columns = "mb_idx,member_idx,mt_idx,match_status,brd_name,mt_date,brd_date,mem_volume,matched_mem_cnt,brd_content";
		
		try {
			
			
			String query = "select " + columns + " from matching_board order by mb_idx desc";
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				
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
			case "mb_idx":
				matchingBoard.setMbIdx(rset.getString("mb_idx"));
				break;
			case "member_idx":
				matchingBoard.setMemberIdx(rset.getString("member_idx"));
				break;
			case "mt_idx":
				matchingBoard.setMtIdx(rset.getString("mt_idx"));
				break;
			case "match_status":
				matchingBoard.setMatchStatus(rset.getString("match_status"));
				break;
			case "brd_name":
				matchingBoard.setBrdName(rset.getString("brd_name"));
				break;
			case "mt_date":
				matchingBoard.setMtDate(rset.getDate("mt_date"));
				break;
			case "brd_date":
				matchingBoard.setBrdDate(rset.getDate("brd_date"));
				break;
			case "mem_volume":
				matchingBoard.setMemVolume(rset.getInt("mem_volume"));
				break;
			case "matched_mem_cnt":
				matchingBoard.setMatchedMemCnt(rset.getInt("matched_mem_cnt"));
				break;
			case "brd_content":
				matchingBoard.setBrdContent(rset.getString("brd_content"));
				break;
			default: break;
			}
			
		}
		
		return matchingBoard;
	}

	public void insertMatchingBoard(MatchingBoard matchingBoard, Connection conn) {
		
		PreparedStatement pstm = null;
		String columns = "mb_idx,member_idx,mt_idx,brd_name,mt_date,mem_volume,brd_content";
		
		String query = "insert into matching_board ( "
				+ columns
				+ " ) values(sc_mb_idx.nextval, ?, ?, ?, ?, ?, ? ) ";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, matchingBoard.getMemberIdx());
			pstm.setString(2, matchingBoard.getMtIdx());
			pstm.setString(3, matchingBoard.getBrdName());
			pstm.setDate(4, matchingBoard.getMtDate());
			pstm.setInt(5, matchingBoard.getMemVolume());
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
		String columns = "ma_idx,mb_idx,msg,sender_idx";
		
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
		String columns = "mcl_idx,member_idx,mb_idx";
		
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
			case "mcl_idx":
				matchingCompleteList.setMclIdx(rset.getString("mcl_idx"));
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

	

	public void insertWaitingList(String memberIdx, String mbIdx, Connection conn) {
		PreparedStatement pstm = null;
		String columns = "wl_idx,mb_idx,member_idx";
		
		String query = "insert into WAITING_LIST ( "
				+ columns
				+ " ) values(sc_wl_idx.nextval, ?, ?) ";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, mbIdx);
			pstm.setString(2, memberIdx);
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}

	public void deleteWaitingListByWlIdx(String wlIdx, Connection conn) {
		PreparedStatement pstm = null;
		
		String query = "delete from waiting_list where wl_idx = ? ";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, wlIdx);
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}

	public void insertMatchingCompleteList(String memberIdx, MatchingBoard mb, Connection conn) {
		PreparedStatement pstm = null;
		
		String query = "insert into matching_complete_list values (sc_mcl_idx.nextval, ?, ?)";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, memberIdx);
			pstm.setString(2, mb.getMtIdx());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}

	public void updateMatchingBoard(MatchingBoard mb, int matchedMemCnt, Connection conn) {
		PreparedStatement pstm = null;
		
		String query = "update matching_board set matched_mem_cnt = ? where mb_idx = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, matchedMemCnt);
			pstm.setString(2, mb.getMbIdx());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}

	public List<WaitingList> selectWaitingListByMbIdx(String mbIdx, Connection conn) {
		List<WaitingList> wlList = new ArrayList<WaitingList>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			
			String query = "select wl_idx, mb_idx, member_idx from waiting_list where mb_idx = ? ";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, mbIdx);
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				WaitingList wl = new WaitingList();
				
				wl.setWlIdx(rset.getString("wl_idx"));
				wl.setMbIdx(rset.getString("mb_idx"));
				wl.setMemberIdx(rset.getString("member_idx"));
				
				wlList.add(wl);
				
			}
			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return wlList;
	}

	public void insertMatchingAlarmReject(String memberIdx, String mbIdx, String msg, String leaderIdx, Connection conn) {
		PreparedStatement pstm = null;
		
		String query = "insert into matching_alarm (ma_idx, mb_idx, msg, sender_idx, rejected_mem_idx) values (sc_ma_idx.nextval, ?, ?, ?, ?)";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, mbIdx);
			pstm.setString(2, msg);
			pstm.setString(3, leaderIdx);
			pstm.setString(4, memberIdx);
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}
	
	public void insertMatchingAlarm(String leaderIdx, String mbIdx, String msg, Connection conn) {
		PreparedStatement pstm = null;
		
		String query = "insert into matching_alarm (ma_idx, mb_idx, msg, sender_idx) values (sc_ma_idx.nextval, ?, ?, ?)";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, mbIdx);
			pstm.setString(2, msg);
			pstm.setString(3, leaderIdx);
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}
	
	

	public List<MatchingCompleteList> selectMatchingCompleteListByMbIdx(String mbIdx, Connection conn) {
		List<MatchingCompleteList> mclList = new ArrayList<MatchingCompleteList>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String columns = "mcl_idx,member_idx,mb_idx";
		
		try {
			
			String query = "select " + columns + " from matching_complete_list where mb_idx = ? ";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, mbIdx);
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

	public List<FindingMember> selectFindingMemberList(Connection conn) {
		List<FindingMember> findingMemberList = new ArrayList<FindingMember>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String columns = "fm_idx,member_idx,mt_idx,matching_status,brd_name,mt_date,brd_date";
		
		try {
			
			String query = "select " + columns + " from finding_member order by fm_idx desc";
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				findingMemberList.add(convertRowToFindingMember(columns,rset));
				
			}
			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return findingMemberList;
	}
	
	private FindingMember convertRowToFindingMember(String columns, ResultSet rset) throws SQLException {
		FindingMember findingMember = new FindingMember();
		
		String[] columnsArr = columns.split(",");
		
		for (String column : columnsArr) {
			
			switch(column) {
			case "fm_idx":
				findingMember.setFmIdx(rset.getString("fm_idx"));
				break;
			case "member_idx":
				findingMember.setMemberIdx(rset.getString("member_idx"));
				break;
			case "mt_idx":
				findingMember.setMtIdx(rset.getString("mt_idx"));
				break;
			case "matching_status":
				findingMember.setMatchingStatus(rset.getString("matching_status"));
				break;
			case "brd_name":
				findingMember.setBrdName(rset.getString("brd_name"));
				break;
			case "mt_date":
				findingMember.setMtDate(rset.getDate("mt_date"));
				break;
			case "brd_date":
				findingMember.setBrdDate(rset.getDate("brd_date"));
				break;
			default: break;
			}
			
		}
		
		return findingMember;
	}
	
	// 매칭 성사 또는 매칭완료 게시글 알림
	public List<MatchingAlarm> selectMatchingAlarmList(String mbIdx, Connection conn) {
		List<MatchingAlarm> maList = new ArrayList<MatchingAlarm>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String columns = "ma_idx,mb_idx,msg,send_date,sender_idx,rejected_mem_idx";
		String query = "select " + columns + " from matching_alarm "
				+ " where mb_idx = ? and rejected_mem_idx is null and (send_date + interval '7' day) > sysdate";
		
		try {
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, mbIdx);
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				MatchingAlarm matchingAlarm = new MatchingAlarm();
				matchingAlarm.setMaIdx(rset.getString("ma_idx")); 
				matchingAlarm.setMbIdx(rset.getString("mb_idx")); 
				matchingAlarm.setMsg(rset.getString("msg")); 
				matchingAlarm.setSendDate(rset.getDate("send_date")); 
				matchingAlarm.setSenderIdx(rset.getString("sender_idx"));
				maList.add(matchingAlarm);
				
			}
			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return maList;
	}
	
	//방장인 경우 보낸 알림 리스트
	public List<MatchingAlarm> selectMatchingAlarmListLeader(String memberIdx, Connection conn) {
		List<MatchingAlarm> maList = new ArrayList<MatchingAlarm>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String columns = "ma_idx,mb_idx,msg,send_date,sender_idx,rejected_mem_idx";
		String query = "select " + columns + " from matching_alarm "
				+ " where sender_idx = ? and (send_date + interval '7' day) > sysdate";
		
		try {
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, memberIdx);
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				MatchingAlarm matchingAlarm = new MatchingAlarm();
				matchingAlarm.setMaIdx(rset.getString("ma_idx")); 
				matchingAlarm.setMbIdx(rset.getString("mb_idx")); 
				matchingAlarm.setMsg(rset.getString("msg")); 
				matchingAlarm.setSendDate(rset.getDate("send_date")); 
				matchingAlarm.setSenderIdx(rset.getString("sender_idx"));
				maList.add(matchingAlarm);
				
			}
			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return maList;
	}
	
	//거절 알림 리스트
	public List<MatchingAlarm> selectMatchingAlarmListRejected(String memberIdx, Connection conn) {
		List<MatchingAlarm> maList = new ArrayList<MatchingAlarm>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String columns = "ma_idx,mb_idx,msg,send_date,sender_idx,rejected_mem_idx";
		String query = "select " + columns + " from matching_alarm "
				+ " where rejected_mem_idx = ? and (send_date + interval '7' day) > sysdate";
		
		try {
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, memberIdx);
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				MatchingAlarm matchingAlarm = new MatchingAlarm();
				matchingAlarm.setMaIdx(rset.getString("ma_idx")); 
				matchingAlarm.setMbIdx(rset.getString("mb_idx")); 
				matchingAlarm.setMsg(rset.getString("msg")); 
				matchingAlarm.setSendDate(rset.getDate("send_date")); 
				matchingAlarm.setSenderIdx(rset.getString("sender_idx"));
				matchingAlarm.setRejectedMemIdx(rset.getString("rejected_mem_idx"));
				maList.add(matchingAlarm);
				
			}
			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return maList;
	}

	public boolean checkMemberIdxAndMbIdx(String memberIdx, String mbIdx, Connection conn) {
		Boolean flg = false;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String columns = "wl_idx";
			try {
			
			String query = "select " + columns + " from waiting_list where member_idx = ? and mb_idx = ?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, memberIdx);
			pstm.setString(2, mbIdx);
			rset = pstm.executeQuery();
			
			if (rset.next()) {
				
				flg = true;
			}
			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return flg;
	}
	
}