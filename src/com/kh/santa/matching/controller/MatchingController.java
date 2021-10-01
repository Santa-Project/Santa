package com.kh.santa.matching.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.santa.common.wrapper.RequestWrapper;
import com.kh.santa.matching.model.dto.MatchingBoard;
import com.kh.santa.matching.model.dto.OpenRoom;
import com.kh.santa.matching.model.service.MatchingBoardService;

/**
 * Servlet implementation class MatchingController
 */
@WebServlet("/matching/*")
public class MatchingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
			collectTeam(request,response);
			break;
		case "openRoom" :
			openRoom(request,response);
			break;
		case "list" :
			list(request,response);
			break;
		case "write_view" :
			write_view(request,response);
			break;
		case "reply_view" :
			reply_view(request,response);
			break;
		case "content_view" :
			content_view(request,response);

		case "collectTeam_chat" :
			collectTeam_chat(request,response);
			break;
		default :
			break;
		}
	}

	
	
	private void content_view(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		String mbIdx = request.getParameter("bId");
		System.out.println("content_view가 호출되었습니다  ");
		MatchingBoard dto = matchingBoardService.getContentView(Integer.parseInt(mbIdx));
		request.setAttribute("content_view", dto);
		request.getRequestDispatcher("/match/content_view").forward(request, response);
		
	}

	private void reply_view(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		request.getRequestDispatcher("/match/reply_view").forward(request, response);
		
	}

	private void write_view(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		System.out.println("write_view가 호출되었습니다  ");
		request.getRequestDispatcher("/match/write_view").forward(request, response);
		
	
	}

	private void list(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		System.out.println("list가 호출되었습니다  ");
		ArrayList<MatchingBoard> dtos = matchingBoardService.getMatchingList();
		request.setAttribute("list", dtos);
		request.getRequestDispatcher("/match/list").forward(request, response);

	}

	private void openRoom(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		MatchingBoard dto = new MatchingBoard();
		System.out.println("openRoom가 호출되었습니다  ");
		
		dto.setMemberIdx(request.getParameter("memberIdx"));
		dto.setBrdName(request.getParameter("room_title"));
		System.out.println(request.getParameter("the_day"));
		
		matchingBoardService.setMatchingBoard(dto);
		request.setAttribute("open_room",dto);
		
		request.getRequestDispatcher("/match/openRoom").forward(request, response);

	}

	private void collectTeam_chat(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.getRequestDispatcher("/match/collectTeam_chat").forward(request, response);
	}

	private void collectTeam(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		request.getRequestDispatcher("/match/collectTeam").forward(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}