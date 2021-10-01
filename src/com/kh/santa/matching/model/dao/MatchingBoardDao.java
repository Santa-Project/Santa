
package com.kh.santa.matching.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.common.exception.DataAccessException;
import com.kh.santa.matching.model.dto.MatchingBoard;
import com.kh.santa.matching.model.dto.MatchingBoardComment;
import com.kh.santa.matching.model.dto.OpenRoom;

public class MatchingBoardDao {
	
	
	JDBCTemplate template = JDBCTemplate.getInstance();
	
	public void write(String bName, String bTitle, String bContent) {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			String query = "insert into matching_board (MB_IDX, MEMBER_IDX, MT_IDX, MATCH_STATUS, BRD_NAME, MT_DATE, BRD_DATE, MEMBER_VOLUME, MATCHED_MEM_CNT) values (sc_matching_idx.nextval, ?, ?, ?, 0, matching_board_seq.currval, 0, 0 )";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			int rn = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	}
	
	public ArrayList<MatchingBoard> list(Connection connection) {
		
		ArrayList<MatchingBoard> dtos = new ArrayList<MatchingBoard>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			
			String query = "select MB_IDX, MEMBER_IDX, MT_IDX, MATCH_STATUS, BRD_NAME, MT_DATE, BRD_DATE, MEMBER_VOLUME, MATCHED_MEM_CNT from matching_board order by MEMBER_VOLUME desc, MATCHED_MEM_CNT asc";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				int bId = resultSet.getInt("MB_IDX");
				String bTitle = resultSet.getString("BRD_NAME");
				String bName = resultSet.getString("MEMBER_IDX");
				Date bDate = resultSet.getDate("MT_DATE");
				int bHit = resultSet.getInt("MATCHED_MEM_CNT");
				MatchingBoard dto = new MatchingBoard();
				dto.setMbIdx(bId);
				dto.setBrdName(bTitle);
				dto.setMemberIdx(bName);
				dto.setMtDate(bDate);
				dto.setMatchedMemCnt(bHit);
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(resultSet, preparedStatement);
		}
		return dtos;
	}
	
	public MatchingBoard contentView(String strID) {
		// TODO Auto-generated method stub
		
		upHit(strID);
		ArrayList<MatchingBoard> dtos = new ArrayList<MatchingBoard>();

		MatchingBoard dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			String query = "select * from matching_board where MB_IDX = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(strID));
			resultSet = preparedStatement.executeQuery();
	
			if(resultSet.next()) {
				int MB_IDX = resultSet.getInt("MB_IDX");
				String MEMBER_IDX = resultSet.getString("MEMBER_IDX");
				String MT_IDX = resultSet.getString("MT_IDX");
				String MATCH_STATUS = resultSet.getString("MATCH_STATUS");
				String BRD_NAME = resultSet.getString("BRD_NAME");
				Date MT_DATE = resultSet.getDate("MT_DATE");
				Date BRD_DATE = resultSet.getDate("BRD_DATE");
				int MEMBER_VOLUME = resultSet.getInt("MEMBER_VOLUME");
				int MATCHED_MEM_CNT = resultSet.getInt("MATCHED_MEM_CNT");
				
				dto = new MatchingBoard();
				dto.setBrdName(BRD_NAME);
				dto.setMtIdx(MT_IDX);
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	public void modify(String bId, String bName, String bTitle, String bContent) {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			String query = "update matching_board set mtIdx = ?, brdName = ?, matchStatus = ? where mbIdx = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4, Integer.parseInt(bId));
			int rn = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	public void delete(String bId) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			
			String query = "delete from matching_board where mbIdx = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(bId));
			int rn = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	public MatchingBoard reply_view(String str) {
		// TODO Auto-generated method stub
		MatchingBoard dto = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			
			
			String query = "select * from matching_board where mbIdx = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(str));
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int MB_IDX = resultSet.getInt("MB_IDX");
				String MEMBER_IDX = resultSet.getString("MEMBER_IDX");
				String MT_IDX = resultSet.getString("MT_IDX");
				String MATCH_STATUS = resultSet.getString("MATCH_STATUS");
				String BRD_NAME = resultSet.getString("BRD_NAME");
				Date MT_DATE = resultSet.getDate("MT_DATE");
				Date BRD_DATE = resultSet.getDate("BRD_DATE");
				int MEMBER_VOLUME = resultSet.getInt("MEMBER_VOLUME");
				int MATCHED_MEM_CNT = resultSet.getInt("MATCHED_MEM_CNT");
				
				dto = new MatchingBoard();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return dto;
	}
	
	public void reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep, String bIndent) {
		// TODO Auto-generated method stub
		
		replyShape(bGroup, bStep);
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
		
			String query = "insert into matching_board (mbIdx, memberIdx, mtIdx, matchStatus, brdName, mtDate, memberVolume) values (sc_board_idx_seq.nextval, ?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4, Integer.parseInt(bGroup));
			preparedStatement.setInt(5, Integer.parseInt(bStep) + 1);
			preparedStatement.setInt(6, Integer.parseInt(bIndent) + 1);
			
			int rn = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	}
	
	private void replyShape( String strGroup, String strStep) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			String query = "update matching_board set memberVolume = memberVolume + 1 where matchedMemCnt = ? and mtIdx > ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(strGroup));
			preparedStatement.setInt(2, Integer.parseInt(strStep));
			
			int rn = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	private void upHit( String bId) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			String query = "update matching_board set memberVolume = memberVolume + 1 where mbIdx = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bId);
			
			int rn = preparedStatement.executeUpdate();
					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

	public MatchingBoard selectBoardByIdx(int mbIdx,Connection conn) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		String query = "select MB_IDX, MEMBER_IDX, MT_IDX, MATCH_STATUS, BRD_NAME, MT_DATE, BRD_DATE, MEMBER_VOLUME, MATCHED_MEM_CNT from matching_board where MB_IDX=? order by MEMBER_VOLUME desc, MATCHED_MEM_CNT asc";
		MatchingBoard dto = new MatchingBoard();
			try {
			
			
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, mbIdx);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				
				int bId = resultSet.getInt("MB_IDX");
				String bTitle = resultSet.getString("BRD_NAME");
				String bName = resultSet.getString("MEMBER_IDX");
				Date bDate = resultSet.getDate("MT_DATE");
				int bHit = resultSet.getInt("MATCHED_MEM_CNT");
				
				dto.setMbIdx(bId);
				dto.setBrdName(bTitle);
				dto.setMemberIdx(bName);
				dto.setMtDate(bDate);
				dto.setMatchedMemCnt(bHit);
			}
			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(resultSet, preparedStatement);
		}
		return dto;
	}

	public ArrayList<OpenRoom> roomContent(Connection conn) {
		ArrayList<OpenRoom> dtos = new ArrayList<OpenRoom>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			
			String query = "select leader, room_Title, room_Content, mt_Date from OPEN_ROOM";
			preparedStatement = conn.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				String bName = resultSet.getString("leader");
				String bTitle = resultSet.getString("room_Title");
				String bContent = resultSet.getString("room_Content");
				Date bDate = resultSet.getDate("mt_Date");
				OpenRoom dto = new OpenRoom();
				dto.setLeader(bName);
				dto.setRoom_Title(bTitle);
				dto.setRoom_Content(bContent);
				dto.setMt_Date(bDate);
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(resultSet, preparedStatement);
		}
		return dtos;
	}

	
	
}