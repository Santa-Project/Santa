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
import com.kh.santa.matching.model.dto.MatchingAlarm;
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
		case "notice" :
			notice(request,response);
			break;
		default :
			break;
		}
	}
	
	
	private void matching_main(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		//MATCHING_COMPLETE_LIST(list_idx-매칭완료리스트idx,member_idx-매칭된 회원idx,mb_idx-매칭게시판idx)
		
		List<Object[]> matchingBoardList = matchingBoardService.getMatchingBoardList();
		
		String memberIdx = ((Member)request.getSession().getAttribute("authentication")).getMemberIdx();
		
		//Object[0]=>MatchingAlarm object, Object[1]=>방장닉네임
		List<Object[]> matchingAlarmList = matchingBoardService.getNotice(memberIdx);
		if(matchingAlarmList!=null) {
			request.setAttribute("matchingAlarmList", matchingAlarmList);
		}
		
		request.setAttribute("matchingBoardList", matchingBoardList);
		
		request.getRequestDispatcher("/match/matching_main").forward(request, response);

		
	}
	
	
	private void matchingBoard(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		String mbIdx = request.getParameter("mbIdx");
		System.out.println(mbIdx);
		System.out.println("matchingBoard가 호출되었습니다  ");
		
		Object[] matchingBoard = matchingBoardService.getMatchingBoard(mbIdx);
		
		request.setAttribute("matchingBoard", matchingBoard);
		request.getRequestDispatcher("/match/matchingBoard").forward(request, response);
		
	}
	
	private void createMatchingBoard(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		List<Mountain> mountainList = mountainService.searchAllMtIdxAndMtName();
		request.setAttribute("mountainList", mountainList);
		request.getRequestDispatcher("/match/createMatchingBoard").forward(request, response);
		
	}
	
	private void createMatchingBoardDo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		
		MatchingBoard matchingBoard = new MatchingBoard();
		matchingBoard.setMemberIdx(request.getParameter("memberIdx"));
		matchingBoard.setMtIdx(request.getParameter("mt"));
		matchingBoard.setBrdName(request.getParameter("brdName"));
		matchingBoard.setMemberVolume(Integer.parseInt(request.getParameter("memberVolume")));
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
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}