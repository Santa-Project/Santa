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
import com.kh.santa.mountainInfo.model.dto.Mountain;
import com.kh.santa.mountainInfo.model.dto.MountainWishlist;
import com.kh.santa.mountainInfo.model.service.MountainService;
import com.kh.santa.mypage.model.dto.Member;
import com.kh.santa.mypage.model.dto.MemberBoard;
import com.kh.santa.mypage.model.dto.MemberBoardComment;
import com.kh.santa.mypage.model.service.MyBoardService;
import com.kh.santa.mypage.model.service.MypageService;

/**
 * Servlet implementation class CommunityController
 */
@WebServlet("/community/*")
public class CommunityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommunityService communityService = new CommunityService();
     MyBoardService  myboardService = new MyBoardService();


     private MypageService mypageService = new MypageService();
     private MountainService mountainService = new MountainService();
     private List<Mountain> mountainList = mountainService.searchAllMtIdxAndMtName();
     private List<MountainWishlist> wishlist = new ArrayList<MountainWishlist>();
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


	//????????? ??? ?????? ??????
	   private void mypageBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      
	         Member member = (Member) request.getSession().getAttribute("authentication"); //????????????
	         String memberIdx =member.getMemberIdx();
	         List<MemberBoard> boardList = myboardService.selectBoardDetail(memberIdx); //????????? ??????????????? ????????? ????????????
	         List<Object[]> res = new ArrayList<Object[]>();
	        
	         for (MemberBoard memberBoard : boardList) {
	            String boardIdx = memberBoard.getMemBoardIdx(); //??????????????? ????????????
	            FileDTO file =  myboardService.selectBoardFile(boardIdx); //?????????????????? ????????????
	            List<MemberBoardComment> commentList = myboardService.selectBoardComent(boardIdx); //?????????????????? ????????????
	            Object[] ob = new Object[] {memberBoard, file,commentList}; //????????? ????????????
	            res.add(ob);
	            
				/*
				 * if(!myboardService.likeBoardlist(memberIdx,boardIdx)) { //????????? ????????? ?????????
				 * request.setAttribute("boardlike", true); } else {
				 * request.setAttribute("boardlike", false); }
				 */
	         }
	         request.getSession().setAttribute("res", res);
	         
	         request.setAttribute("mountainList", mountainList);
	         wishlist = mypageService.selectMountainWishlist(member.getMemberIdx());
	         request.setAttribute("wishlist", wishlist);
	      request.getRequestDispatcher("/mypage/mypageBoard").forward(request, response);
	   }

	private void community(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//????????????
		Member[] memberArr = communityService.selectPopularMember();
//			for (int i = 0; i < memberArr.length; i++) {
//				System.out.println(memberArr[i]); 
//			}
        request.setAttribute("memberArr", memberArr);
        
        //??????????????? 
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
	    communityService.insertComment(comment);
		
	    
	    mypageBoard(request,response);
		/*
		 * request.getRequestDispatcher("/community/community").forward(request,
		 * response);
		 */
		
	}
	
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
