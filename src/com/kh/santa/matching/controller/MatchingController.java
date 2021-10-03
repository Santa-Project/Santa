package com.kh.santa.matching.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.santa.common.wrapper.RequestWrapper;
import com.kh.santa.matching.model.dto.MatchingBoard;
import com.kh.santa.matching.model.service.MatchingBoardService;
import com.kh.santa.mountainInfo.model.dto.Mountain;
import com.kh.santa.mountainInfo.model.service.MountainService;
import com.kh.santa.mypage.model.dto.Member;

/**
 * Servlet implementation class MatchingController
 */
@WebServlet("/matching/*")
public class MatchingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MountainService mountainService = new MountainService();
    private MatchingBoardService matchingBoardService = new MatchingBoardService();
    
    public MatchingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestWrapper wrappedRequest = (RequestWrapper)request;
		String[] uriArr = wrappedRequest.getRequestURIArray();
		
		switch(uriArr[uriArr.length-1]){
		case "collectTeam" :
			/* collectTeam(request,response); */
			matching_main(request,response);
			break;
		case "matchingBoard" :
			matchingBoard(request,response);
			break;
		case "createMatchingBoard" :
			createMatchingBoard(request,response);
			break;
		case "createMatchingBoard.do" :
			createMatchingBoardDo(request,response);
			break;
		case "create_notice" :
			notice(request,response);
			break;
		case "application" :
			application(request,response);
			break;
		case "managingTeam" :
			waitingList(request,response);
			break;
		case "accept" :
			confirm(request,response);
			break;
		case "reject" :
			reject(request,response);
			break;
			
		case "userList" :
			userlist(request,response);
			break;
		default :
			break;
		}
	}
	
	
	private void matching_main(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		getNotice(request);
		
		// 매칭보드리스트
		List<Object[]> matchingBoardList = matchingBoardService.getMatchingBoardList();
		System.out.println(matchingBoardList);
		request.getSession().setAttribute("matchingBoardList", matchingBoardList);
		
		request.getRequestDispatcher("/match/matching_main").forward(request, response);
		
	}
	
	
	private void getNotice(HttpServletRequest request){
		
		String memberIdx = ((Member)request.getSession().getAttribute("authentication")).getMemberIdx();
		System.out.println(memberIdx);
		//Object[0]=>MatchingAlarm object, Object[1]=>방장닉네임
		List<Object[]> matchingAlarmList = matchingBoardService.getNotice(memberIdx);
		if(matchingAlarmList!=null) {
			request.setAttribute("matchingAlarmList", matchingAlarmList);
		}
	}

	private void matchingBoard(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		
		getNotice(request);
		
		String mbIdx = request.getParameter("mbIdx");
		System.out.println(mbIdx);
		System.out.println("matchingBoard가 호출되었습니다  ");
		
		Object[] matchingBoard = matchingBoardService.getMatchingBoard(mbIdx);
		List<Member> memberList = matchingBoardService.getMemberList(mbIdx);
		
		request.setAttribute("matchingBoard", matchingBoard);
		request.setAttribute("memberList", memberList);
		request.getRequestDispatcher("/match/matchingBoard").forward(request, response);
		
	}
	
	private void createMatchingBoard(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		
		getNotice(request);
		
		List<Mountain> mountainList = mountainService.searchAllMtIdxAndMtName();
		request.setAttribute("mountainList", mountainList);
		request.getRequestDispatcher("/match/createMatchingBoard").forward(request, response);
		
	}
	
	private void createMatchingBoardDo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		
		MatchingBoard matchingBoard = new MatchingBoard();
		matchingBoard.setMemberIdx(request.getParameter("memberIdx"));
		matchingBoard.setMtIdx(request.getParameter("mt"));
		matchingBoard.setBrdName(request.getParameter("brdName"));
		matchingBoard.setMemVolume(Integer.parseInt(request.getParameter("memberVolume")));
		matchingBoard.setMtDate(Date.valueOf(request.getParameter("mtDate")));
		matchingBoard.setBrdContent(request.getParameter("brdContent"));
		
		matchingBoardService.createMatchingBoard(matchingBoard);
		
		matching_main(request, response);
	}
	
	private void notice(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		
		//MATCHING_ALARM(ma_idx-알림idx,mb_idx-매칭게시판idx,msg-작성된 메세지,time-작성시간,sender-보내는 이:방장)
		//mbIdx(매칭보드 idx) 값 & memberIdx(방장Idx) 값  받아오기
		String mbIdx = request.getParameter("mbIdx");
		String msg = request.getParameter("msg");
		String memberIdx = request.getParameter("memberIdx");
		
		matchingBoardService.sendNotice(mbIdx,msg,memberIdx);
		
		matchingBoard(request,response);
	}
	
	//팀원모집 - 지원
	private void application(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
	
		// memberIdx
		String memberIdx = ((Member)request.getSession().getAttribute("authentication")).getMemberIdx();
		// mbIdx
		String mbIdx = request.getParameter("mbIdx");
		
		matchingBoardService.applyForMatching(memberIdx,mbIdx);
		
		matchingBoard(request,response);
	}
	
	
	private void waitingList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		
		getNotice(request);
		
		MatchingBoard mb = new MatchingBoard();
		
		String mbIdx = request.getParameter("mbIdx");
		mb.setMbIdx(mbIdx);
		mb.setMtDate(Date.valueOf(request.getParameter("mtDate")));
		mb.setMemVolume(Integer.parseInt(request.getParameter("memberVolume")));
		mb.setMatchedMemCnt(Integer.parseInt(request.getParameter("matchedMemCnt")));
		//waitingList와 지원자 닉네임 & 지원자 idx 받아오기
		List<Object[]> wlList = matchingBoardService.getWlList(mbIdx);
		
		if(wlList!=null) {
			request.setAttribute("wlList", wlList);
		}
		
		request.getRequestDispatcher("/match/managingTeam").forward(request, response);
		
		
		
		
	}
	
	// 방장의 대기목록 컨펌
	private void confirm(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		
		//matchingBoard(mbIdx,mtDate,memberVolume,matchedMemCnt), memberIdx 받기
		String memberIdx = request.getParameter("memberIdx"); // 지원자idx
		String wlIdx = request.getParameter("wlIdx"); // 대기목록idx
		MatchingBoard mb = new MatchingBoard();
		String leaderIdx = ((Member)request.getSession().getAttribute("authentication")).getMemberIdx(); // 방장(sender)idx
		mb.setMemberIdx(leaderIdx);
		mb.setMbIdx(request.getParameter("mbIdx"));
		mb.setMtDate(Date.valueOf(request.getParameter("mtDate")));
		mb.setMemVolume(Integer.parseInt(request.getParameter("memberVolume")));
		mb.setMatchedMemCnt(Integer.parseInt(request.getParameter("matchedMemCnt")));
		
		if(mb.getMatchedMemCnt() + 1 > mb.getMemVolume()) {
			request.setAttribute("msg", "모집인원을 초과했습니다.");
			request.setAttribute("url", "/matching/waitingList");
			request.getRequestDispatcher("/common/result").forward(request, response);
		}
		
		matchingBoardService.confirmMatching(wlIdx, mb, memberIdx);
		
		waitingList(request,response);
		
	}
	
	// 방장의 대기목록 거절
	private void reject(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
	
		//wlIdx, mbIdx, memberIdx 받기
		String wlIdx = request.getParameter("wlIdx");
		String mbIdx = request.getParameter("mbIdx");
		String leaderIdx = ((Member)request.getSession().getAttribute("authentication")).getMemberIdx(); // 방장(sender)idx
		String memberIdx = request.getParameter("memberIdx"); // 지원자idx
		
		matchingBoardService.rejectMatching(wlIdx, mbIdx, memberIdx, leaderIdx);
		
		waitingList(request,response);
		
	}
	
	private void userlist(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		getNotice(request);
		
		// user리스트
		List<Object[]> userList = matchingBoardService.getUserList();
		
		request.getSession().setAttribute("userList", userList);
		
		request.getRequestDispatcher("/match/userList").forward(request, response);
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}