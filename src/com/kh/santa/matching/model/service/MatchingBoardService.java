package com.kh.santa.matching.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.matching.model.dao.MatchingBoardDao;
import com.kh.santa.matching.model.dto.FindingMember;
import com.kh.santa.matching.model.dto.MatchingAlarm;
import com.kh.santa.matching.model.dto.MatchingBoard;
import com.kh.santa.matching.model.dto.MatchingCompleteList;
import com.kh.santa.matching.model.dto.WaitingList;
import com.kh.santa.mountainInfo.model.dao.MountainDao;
import com.kh.santa.mypage.model.dao.MemberDao;
import com.kh.santa.mypage.model.dto.Member;

public class MatchingBoardService {


	private MatchingBoardDao matchingBoardDao = new MatchingBoardDao();
	private MemberDao memberDao = new MemberDao();
	private MountainDao mountainDao = new MountainDao();
	
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	public List<Object[]> getMatchingBoardList() {
		Connection conn = template.getConnection();
		List<Object[]> matchingBoardList = new ArrayList<Object[]>();
		List<MatchingBoard> mbList = null;
		
		try {
			mbList = matchingBoardDao.selectMatchingBoardList(conn);
			
			for (MatchingBoard mb : mbList) {
				if(mb.getMbIdx()==null) {
					return null;
				}
				
				Object[] mbListAndLeader = new Object[3];
				mbListAndLeader[0] = mb;
				mbListAndLeader[1] = memberDao.selectNicknameByIdx(mb.getMemberIdx(), conn);
				
				if(mb.getMatchStatus().equalsIgnoreCase("F")) {
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
			
			if(mb.getMatchStatus().equalsIgnoreCase("F")) {
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
		List<MatchingAlarm> matchingAlarmList = new ArrayList<MatchingAlarm>();
		Connection conn = template.getConnection();
		
		try {
			//MATCHING_COMPLETE_LIST(list_idx-매칭완료리스트idx,member_idx-매칭된 회원idx,mb_idx-매칭게시판idx)
			//MATCHING_ALARM rejected_mem_idx O/X
			
			//매칭완료 된 리스트 중에서 기간이 지난 리스트는 제외해야되니까 
			//mbIdx가져올때 mt_date(등산예정일)도 가져와서 매칭완료리스트 테이블에 추가하고 Dao단에서 데이터 불러올때 현재날짜가 등산예정일 이전인 데이터만 불러오게 하기
			
			List<MatchingCompleteList> mclList = matchingBoardDao.selectMatchingCompleteListByMemberIdx(memberIdx,conn);
			
			// 방장인 경우 보낸 알림 리스트
			List<MatchingAlarm> leaderMA = matchingBoardDao.selectMatchingAlarmListLeader(memberIdx, conn);
			matchingAlarmList.addAll(leaderMA);
			
			// 매칭 성사 또는 매칭완료 게시글 알림
			for (MatchingCompleteList mcl : mclList) {
				List<MatchingAlarm> matchingSucess = matchingBoardDao.selectMatchingAlarmList(mcl.getMbIdx(),conn);
				matchingAlarmList.addAll(matchingSucess);
			}
			
			// 거절 알림 리스트
			List<MatchingAlarm> matchingRejected = matchingBoardDao.selectMatchingAlarmListRejected(memberIdx, conn);
			matchingAlarmList.addAll(matchingRejected);
			
			
			if(matchingAlarmList.size()!=0) {
				Collections.sort(matchingAlarmList);
				for (MatchingAlarm matchingAlarm : matchingAlarmList) {
					Object[] oArr = new Object[2];
					String leaderNickname = memberDao.selectNicknameByIdx(matchingAlarm.getSenderIdx(), conn);
					oArr[0] = matchingAlarm;
					oArr[1] = leaderNickname;
					maList.add(oArr);
				}
			}
			
			
			
		}finally {
			template.close(conn);
		}
		
		return maList;
		
	}
	
	public void applyForMatching(String memberIdx, String mbIdx) {
		
		Connection conn = template.getConnection();
		
		try {
			
			matchingBoardDao.insertWaitingList(memberIdx,mbIdx,conn);
			
			template.commit(conn);
		} catch(Exception e){
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		
	}

	public void rejectMatching(String wlIdx, String mbIdx, String memberIdx, String leaderIdx) {
		
		Connection conn = template.getConnection();
		
		try {
			
			// 1. waitingList에서 delete
			matchingBoardDao.deleteWaitingListByWlIdx(wlIdx, conn);
			// 2. matchingAlarm 추가 (msg : 매칭 신청이 거절되었습니다.)
			String msg = "매칭 신청이 거절되었습니다.";
			matchingBoardDao.insertMatchingAlarmReject(memberIdx,mbIdx,msg,leaderIdx,conn);
			template.commit(conn);
		} catch(Exception e){
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		
		
		
	}

	public void confirmMatching(String wlIdx, MatchingBoard mb, String memberIdx) {
		
		Connection conn = template.getConnection();
		
		try {
			
			// 1. waitingList에서 delete
			matchingBoardDao.deleteWaitingListByWlIdx(wlIdx, conn);
			// 2. matchingCompleteList에 추가
			matchingBoardDao.insertMatchingCompleteList(memberIdx,mb,conn);
			// 3. matchingAlarm 추가 (msg : 매칭이 완료되었습니다.)
			String msg = "매칭이 완료되었습니다.";
			matchingBoardDao.insertMatchingAlarm(mb.getMemberIdx(),mb.getMbIdx(),msg,conn);
			// 4. matchingBoard에 matchedMemCnt + 1
			int matchedMemCnt = mb.getMatchedMemCnt() + 1;
			matchingBoardDao.updateMatchingBoard(mb,matchedMemCnt,conn);
			
			template.commit(conn);
		} catch(Exception e){
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}

	public List<Object[]> getWlList(String mbIdx) {
		List<Object[]> oList = new ArrayList<Object[]>();;
		List<WaitingList> wlList = null;
		
		Connection conn = template.getConnection();
		
		try {
			
			wlList = matchingBoardDao.selectWaitingListByMbIdx(mbIdx,conn);
			if(wlList!=null) {
				for (WaitingList wl : wlList) {
					Object[] wlArr = new Object[2];
					Member mem = memberDao.selectMemberByIdx(wl.getMemberIdx(), conn);
					wlArr[0] = wl;
					wlArr[1] = mem;
					oList.add(wlArr);
				}
			}
			
		}finally {
			template.close(conn);
		}
		
		return oList;
		
	}

	public List<Member> getMemberList(String mbIdx) {
		List<Member> memberList = new ArrayList<Member>();
		
		Connection conn = template.getConnection();
		
		try {
			
			List<MatchingCompleteList> mclList = matchingBoardDao.selectMatchingCompleteListByMbIdx(mbIdx, conn);
			
			for (MatchingCompleteList mcl : mclList) {
				Member member = memberDao.selectMemberByIdx(mcl.getMemberIdx(), conn);
				memberList.add(member);
			}
			
		}finally {
			template.close(conn);
		}
		
		return memberList;
	}

	


	
	
}
