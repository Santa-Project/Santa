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
		case "main" :
			matchingMain(request,response);
			break;
		case "matchingBoard" :
			matchingBoard(request,response);
			break;
		case "application" :
			application(request,response);
			break;
		case "createNotice" :
			notice(request,response);
			break;
		case "waitingList" :
			waitingList(request,response);
			break;
		case "accept" :
			accept(request,response);
			break;
		case "reject" :
			reject(request,response);
			break;
		case "createBoardForm" :
			createMatchingBoard(request,response);
			break;
		case "createBoard" :
			createMatchingBoardDo(request,response);
			break;
		default :
			break;
		}
	}
	
	
	
	private void matchingMain(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		getNotice(request);
		
		// ?????????????????????
		List<Object[]> matchingBoardList = matchingBoardService.getMatchingBoardList();
		System.out.println(matchingBoardList);
		request.getSession().setAttribute("matchingBoardList", matchingBoardList);
		
		request.getRequestDispatcher("/match/matching_main").forward(request, response);
		
	}

	private void matchingBoard(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		
		getNotice(request);
		
		String mbIdx = request.getParameter("mbIdx");
		System.out.println(mbIdx);
		System.out.println("matchingBoard??? ?????????????????????  ");
		
		Object[] matchingBoard = matchingBoardService.getMatchingBoard(mbIdx);
		List<Member> memberList = matchingBoardService.getMemberList(mbIdx);
		
		String memberIdx = ((Member)request.getSession().getAttribute("authentication")).getMemberIdx();
		
		//????????? ??????
		if(matchingBoardService.getLeaderIdx(mbIdx).equals(memberIdx)) {
			request.setAttribute("leader", "Y");
		}
		
		
		//????????? ??????
		for (Member member : memberList) {
			if(member.getMemberIdx().equals(memberIdx)){
				request.setAttribute("matched", "Y");
				return;
			}
		}
		System.out.println(request.getAttribute("matched"));
		//?????? ??????(???) ??????
		if(request.getAttribute("matched") == null && request.getAttribute("leader") == null) {
			request.setAttribute("notyet", "Y");
		}
		
		
		
		request.setAttribute("matchingBoard", matchingBoard);
		request.setAttribute("memberList", memberList);
		request.getRequestDispatcher("/match/matchingBoard").forward(request, response);
		
	}
	
	//???????????? - ??????
	private void application(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
	
		// memberIdx
		String memberIdx = ((Member)request.getSession().getAttribute("authentication")).getMemberIdx();
		// mbIdx
		String mbIdx = request.getParameter("mbIdx");
		System.out.println(matchingBoardService.checkDuplicateApply(memberIdx,mbIdx));
		if(matchingBoardService.checkDuplicateApply(memberIdx,mbIdx)) {
			request.setAttribute("msg", "?????? ?????????????????????.");
			request.setAttribute("url", "/matching/collectTeam/matchingBoard?mbIdx=" + mbIdx);
			request.getRequestDispatcher("/common/result").forward(request, response);
			
		} else {

			matchingBoardService.applyForMatching(memberIdx,mbIdx);
			
			matchingBoard(request,response);
		}
		
	}
	
	private void notice(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		
		//MATCHING_ALARM(ma_idx-??????idx,mb_idx-???????????????idx,msg-????????? ?????????,time-????????????,sender-????????? ???:??????)
		//mbIdx(???????????? idx) ??? & memberIdx(??????Idx) ???  ????????????
		String mbIdx = request.getParameter("mbIdx");
		String msg = request.getParameter("msg");
		String memberIdx = request.getParameter("leaderIdx");
		
		matchingBoardService.sendNotice(mbIdx,msg,memberIdx);
		
		response.sendRedirect("/matching/collectTeam/matchingBoard?mbIdx=" + mbIdx);
	}
	
	private void waitingList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		
		getNotice(request);
		
		MatchingBoard mb = new MatchingBoard();
		
		String mbIdx = request.getParameter("mbIdx");
		mb.setMbIdx(mbIdx);
		mb.setMtDate((Date)(request.getAttribute("mtDate")));
		mb.setMemVolume(Integer.parseInt(request.getParameter("memVolume")));
		mb.setMatchedMemCnt(Integer.parseInt(request.getParameter("matchedMemCnt")));
		//waitingList??? ????????? ????????? & ????????? idx ????????????
		List<Object[]> wlList = matchingBoardService.getWlList(mbIdx);
		
		if(wlList!=null) {
			request.setAttribute("wlList", wlList);
		}
		
		MatchingBoard matchingBoard = matchingBoardService.getMatchingBoardDTO(mbIdx);
		
		request.setAttribute("matchingBoard", matchingBoard);
		
		request.getRequestDispatcher("/match/waitingList").forward(request, response);
	}
	
	// ????????? ???????????? ??????
	private void accept(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		
		//matchingBoard(mbIdx,mtDate,memberVolume,matchedMemCnt), memberIdx ??????
		String memberIdx = request.getParameter("memberIdx"); // ?????????idx
		String wlIdx = request.getParameter("wlIdx"); // ????????????idx
		MatchingBoard mb = new MatchingBoard();
		String leaderIdx = ((Member)request.getSession().getAttribute("authentication")).getMemberIdx(); // ??????(sender)idx
		mb.setMemberIdx(leaderIdx);
		mb.setMbIdx(request.getParameter("mbIdx"));
		mb.setMtDate(Date.valueOf(request.getParameter("mtDate")));
		mb.setMemVolume(Integer.parseInt(request.getParameter("memVolume")));
		mb.setMatchedMemCnt(Integer.parseInt(request.getParameter("matchedMemCnt")));
		
		if(mb.getMatchedMemCnt() + 1 > mb.getMemVolume()) {
			request.setAttribute("msg", "??????????????? ??????????????????.");
			request.setAttribute("url", "/matching/waitingList");
			request.getRequestDispatcher("/common/result").forward(request, response);
		}
		System.out.println(mb.getMbIdx());
		matchingBoardService.confirmMatching(wlIdx, mb, memberIdx);
		
		waitingList(request,response);
		
	}
		
	// ????????? ???????????? ??????
	private void reject(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
	
		//wlIdx, mbIdx, memberIdx ??????
		String wlIdx = request.getParameter("wlIdx");
		String mbIdx = request.getParameter("mbIdx");
		String leaderIdx = ((Member)request.getSession().getAttribute("authentication")).getMemberIdx(); // ??????(sender)idx
		String memberIdx = request.getParameter("memberIdx"); // ?????????idx
		
		matchingBoardService.rejectMatching(wlIdx, mbIdx, memberIdx, leaderIdx);
		
		waitingList(request,response);
		
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
		
		response.sendRedirect("/matching/collectTeam/main");
	}
	
	private void getNotice(HttpServletRequest request){
		
		String memberIdx = ((Member)request.getSession().getAttribute("authentication")).getMemberIdx();
		System.out.println(memberIdx);
		//Object[0]=>MatchingAlarm object, Object[1]=>???????????????
		List<Object[]> matchingAlarmList = matchingBoardService.getNotice(memberIdx);
		if(matchingAlarmList!=null) {
			request.setAttribute("matchingAlarmList", matchingAlarmList);
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}