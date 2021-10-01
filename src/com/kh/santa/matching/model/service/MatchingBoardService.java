package com.kh.santa.matching.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.matching.model.dao.MatchingBoardDao;
import com.kh.santa.matching.model.dto.MatchingAlarm;
import com.kh.santa.matching.model.dto.MatchingBoard;
import com.kh.santa.matching.model.dto.MatchingCompleteList;
import com.kh.santa.mountainInfo.model.dao.MountainDao;
import com.kh.santa.mypage.model.dao.MemberDao;

public class MatchingBoardService {


	private MatchingBoardDao matchingBoardDao = new MatchingBoardDao();
	private MemberDao memberDao = new MemberDao();
	private MountainDao mountainDao = new MountainDao();
	
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	public List<Object[]> getMatchingBoardList() {
		Connection conn = template.getConnection();
		List<Object[]> matchingBoardList = new ArrayList<Object[]>();
		List<MatchingBoard> mbList = new ArrayList<MatchingBoard>();
		
		try {
			mbList = matchingBoardDao.selectMatchingBoardList(conn);
			
			for (MatchingBoard mb : mbList) {
				Object[] mbListAndLeader = new Object[3];
				mbListAndLeader[0] = mb;
				mbListAndLeader[1] = memberDao.selectNicknameByIdx(mb.getMemberIdx(), conn);
				
				if(mb.getMatchStatus().equalsIgnoreCase("N")) {
					mbListAndLeader[2] = false;
				} else {
					mbListAndLeader[2] = true;
				}
				
				matchingBoardList.add(mbListAndLeader);
			}
			
		}finally {
			template.close(conn);
		}
		
		return matchingBoardList;
	}
	
	public Object[] getMatchingBoard(String mbIdx) {
		Connection conn = template.getConnection();
		Object[] matchingBoard = new Object[4];
		MatchingBoard mb = new MatchingBoard();
		String leaderNickName = null;
		String mountainName = null;
		
		try {
			mb = matchingBoardDao.selectMatchingBoardByIdx(mbIdx, conn);
			leaderNickName = memberDao.selectNicknameByIdx(mb.getMemberIdx(), conn);
			mountainName = mountainDao.selectselectMountainNameBymtIdx(mb.getMtIdx(), conn);
			matchingBoard[0] = mb;
			matchingBoard[1] = leaderNickName;
			matchingBoard[2] = mountainName;
			
			if(mb.getMatchStatus().equalsIgnoreCase("N")) {
				matchingBoard[3] = false;
			} else {
				matchingBoard[3] = true;
			}
			
		}finally {
			template.close(conn);
		}
		
		return matchingBoard;
	}

	public void createMatchingBoard(MatchingBoard matchingBoard) {
		Connection conn = template.getConnection();
		
		try {
			
			matchingBoardDao.insertMatchingBoard(matchingBoard, conn);
			
			template.commit(conn);
		} catch(Exception e){
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}

	public void sendNotice(String mbIdx, String msg, String memberIdx) {
		Connection conn = template.getConnection();
		
		try {
			
			matchingBoardDao.insertNotice(mbIdx, msg, memberIdx, conn);
			
			template.commit(conn);
		} catch(Exception e){
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		
	}

	public List<Object[]> getNotice(String memberIdx) {
		List<Object[]> maList = new ArrayList<Object[]>();
		List<MatchingCompleteList> mclList = null;
		Connection conn = template.getConnection();
		
		try {
			//MATCHING_COMPLETE_LIST(list_idx-매칭완료리스트idx,member_idx-매칭된 회원idx,mb_idx-매칭게시판idx)
			
			//매칭완료 된 리스트 중에서 기간이 지난 리스트는 제외해야되니까 
			//mbIdx가져올때 mt_date(등산예정일)도 가져와서 매칭완료리스트 테이블에 추가하고 Dao단에서 데이터 불러올때 현재날짜가 등산예정일 이전인 데이터만 불러오게 하기
			
			mclList = matchingBoardDao.selectMatchingCompleteListByMemberIdx(memberIdx,conn);
			if(mclList!=null) {
				for (MatchingCompleteList mcl : mclList) {
					Object[] maArr = new Object[2];
					MatchingAlarm ma = matchingBoardDao.selectMatchingAlarm(mcl.getMbIdx(),conn);
					String leaderNickName = memberDao.selectNicknameByIdx(mcl.getMemberIdx(), conn);
					maArr[0] = ma;
					maArr[1] = leaderNickName;
					maList.add(maArr);
				}
			}
			
		}finally {
			template.close(conn);
		}
		
		return maList;
		
	}


	
	
}
