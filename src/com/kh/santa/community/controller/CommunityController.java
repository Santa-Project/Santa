package com.kh.santa.community.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.santa.common.file.FileDTO;
import com.kh.santa.common.wrapper.RequestWrapper;
import com.kh.santa.community.model.service.CommunityService;
import com.kh.santa.mypage.model.dto.Member;
import com.kh.santa.mypage.model.dto.MemberBoard;
import com.kh.santa.mypage.model.dto.MemberBoardComment;
import com.kh.santa.mypage.model.service.MyBoardService;

/**
 * Servlet implementation class CommunityController
 */
@WebServlet("/community/*")
public class CommunityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommunityService communityService = new CommunityService();
     MyBoardService  myboardService = new MyBoardService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityController() {
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
		case "community" :
			community(request,response); 
			break;
		case "insertComment" :
			insertComment(request,response); 
			break;
		default :
			break;
		}
	}



	private void community(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//인기유저
		Member[] memberArr = communityService.selectPopularMember();
//			for (int i = 0; i < memberArr.length; i++) {
//				System.out.println(memberArr[i]); 
//			}
        request.setAttribute("memberArr", memberArr);
        
        //인기게시판 
        List<Object[]> res = new ArrayList<Object[]>();
        List<MemberBoard> boardList = communityService.selectBoardTop4();
        
        for (MemberBoard memberBoard : boardList) { 
    	String boardIdx = memberBoard.getMemBoardIdx();
        FileDTO file =communityService.selectFileDTOsTop4(boardIdx);
        List<MemberBoardComment> commentList = communityService.selectBoardComentTop4(boardIdx);
        Object[] ob = new Object[] {memberBoard, file,commentList};
        res.add(ob);
		}
        request.getSession().setAttribute("res", res);
        request.getRequestDispatcher("/community/community").forward(request, response);
        
        
        }

	
	
	
	private void insertComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Member member = (Member) request.getSession().getAttribute("authentication");
	    String boardIdx =request.getParameter("boardIdx");
	    String content = request.getParameter("content");
	    MemberBoardComment comment = new MemberBoardComment();
	    comment.setNickname(member.getNickname());
	    comment.setMemberIdx(member.getMemberIdx());
	    comment.setMemBoardIdx(boardIdx);
	    comment.setContent(content);
	    myboardService.insertComment(comment);
	    request.getRequestDispatcher("/community/community").forward(request, response);
	}
	
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
