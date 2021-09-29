package com.kh.santa.mypage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.santa.common.wrapper.RequestWrapper;
import com.kh.santa.main.model.service.MainService;
import com.kh.santa.mypage.model.dto.Follow;
import com.kh.santa.mypage.model.dto.Member;
import com.kh.santa.mypage.model.dto.MemberBoard;
import com.kh.santa.mypage.model.dto.MemberBoardComment;
import com.kh.santa.mypage.model.service.FollowingService;
import com.kh.santa.mypage.model.service.MyBoardService;
import com.kh.santa.mypage.model.service.MypageService;
import com.kh.santa.common.file.FileUtil;
import com.kh.santa.common.file.MultiPartParams;

import oracle.sql.DATE;

import com.kh.santa.common.file.FileDTO;

/**
 * Servlet implementation class MypageController
 */
@WebServlet("/mypage/*")
public class MypageController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   MyBoardService myboardService = new MyBoardService();
   FollowingService followingService = new FollowingService();
   MainService mainService = new MainService();
   MypageService mypageService = new MypageService();
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MypageController() {
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
      case "mypageBoard" :
         mypageBoard(request,response);
         break;
      case "mypageWriteBoard" :
         mypageWriteBoard(request,response);
         break;
      case "mypageFollow" :
         mypageFollow(request,response);
         break;
      case "mypageFollower" :
         mypageFollower(request,response);
         break;
      case "mypageMemberEdit" :
         mypageMemberEdit(request,response);
         break;

      case "insertBoard" :
         insertBoard(request,response);
         break;
      case "deleteBoard" :
    	  deleteBoard(request,response);
          break;
      case "insertComment" :
    	  insertComment(request,response);
          break;
      case "deleteComment" :
    	  deleteComment(request,response);
          break;
      case "insertFollow" :
    	  insertFollow(request,response);
          break;
      case "deleteFollow" :
    	  deleteFollow(request,response);
          break;
      case "anotherBoard" :
    	  anotherBoard(request,response);
          break;
          
      case "editMember" :
    	  editMember(request,response);
          break;
      case "leaveSanta" :
    	  leaveSanta(request,response);
          break;
      default :
         break;
      }
      
   }


   //게시판 및 댓글 출력
   private void mypageBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
         Member member = (Member) request.getSession().getAttribute("authentication"); //세션조회
         List<MemberBoard> boardList = myboardService.selectBoardDetail(member.getMemberIdx()); //조회한 세션값으로 게시판 불러오기
         
         List<Object[]> res = new ArrayList<Object[]>();
        
         for (MemberBoard memberBoard : boardList) {
            String boardIdx = memberBoard.getBoardIdx(); //게시판번호 불러오기
            FileDTO file =  myboardService.selectBoardFile(boardIdx); //게시판번호로 파일찾기
            List<MemberBoardComment> commentList = myboardService.selectBoardComent(boardIdx); //게시판번호로 댓글찾기
            Object[] ob = new Object[] {memberBoard, file,commentList}; //객체에 담아주기
            res.add(ob);
         }
         request.getSession().setAttribute("res", res);
         
      request.getRequestDispatcher("/mypage/mypageBoard").forward(request, response);
   }

   //게시글 작성페이지
   private void mypageWriteBoard(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
	      
      request.getRequestDispatcher("/mypage/mypageWriteBoard").forward(request, response);
   } 
   
   //게시글 작성post
   private void insertBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      FileUtil util = new FileUtil();
      MultiPartParams params = util.fileUpload(request);
      
      Member member = (Member) request.getSession().getAttribute("authentication");
      MemberBoard board = new MemberBoard();
      board.setMemberIdx(member.getMemberIdx());
      board.setMtMountain(params.getParameter("mountlist"));
      board.setMtRegion(params.getParameter("region"));
      board.setBoardComment(params.getParameter("writetext"));
      
      List<FileDTO> fileDTOs = params.getFilesInfo(); 
      myboardService.insertBoard(board,fileDTOs);
      response.sendRedirect("/mypage/mypageBoard");
   }
   
   //게시글 삭제
	private void deleteBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardIdx =request.getParameter("deleteboard");
		myboardService.deleteBoard(boardIdx);
		request.getRequestDispatcher("/mypage/mypageBoard").forward(request, response);
	}

   //댓글 작성
   private void insertComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   	
	   Member member = (Member) request.getSession().getAttribute("authentication");
	   String boardIdx =request.getParameter("boardIdx");
	   String content = request.getParameter("content");
	   MemberBoardComment comment = new MemberBoardComment();
	   comment.setNickname(member.getNickname());
	   comment.setMemberIdx(member.getMemberIdx());
	   comment.setBoardIdx(boardIdx);
	   comment.setContent(content);
	   myboardService.insertComment(comment);
	   mypageBoard(request,response);
   }

   //댓글 삭제
	private void deleteComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commentIdx =request.getParameter("commentIdx");
		System.out.println(commentIdx);
		myboardService.deleteComment(commentIdx);
		
		response.sendRedirect("/mypage/mypageBoard");
	}
	
	   
   //다른사람 페이지
	private void anotherBoard(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		   
		   String anotherIdx = request.getParameter("anotherIdx");
	       List<MemberBoard> boardList = myboardService.selectBoardDetail(anotherIdx); //조회한 세션값으로 게시판 불러오기
	       
	       List<Object[]> others = new ArrayList<Object[]>();
	      
	       for (MemberBoard memberBoard : boardList) {
	          String boardIdx = memberBoard.getBoardIdx(); //게시판번호 불러오기
	          FileDTO file =  myboardService.selectBoardFile(boardIdx); //게시판번호로 파일찾기
	          List<MemberBoardComment> commentList = myboardService.selectBoardComent(boardIdx); //게시판번호로 댓글찾기
	          Object[] ob = new Object[] {memberBoard, file,commentList}; //객체에 담아주기
	          others.add(ob);
	       }
	       request.setAttribute("others", others);
		
		 request.getRequestDispatcher("/mypage/anotherBoard").forward(request, response);
	}
	
   //팔로우
   private void mypageFollow(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
	   
	  Member member = (Member) request.getSession().getAttribute("authentication");
	  List<Member> followList = followingService.FollowList(member.getMemberIdx()); 
	  request.setAttribute("followList", followList); 
      request.getRequestDispatcher("/mypage/mypageFollow").forward(request, response); 
   }
   
   //팔로워
   private void mypageFollower(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
	  Member member = (Member) request.getSession().getAttribute("authentication");
	  List<Member> followerList = followingService.FollowerList(member.getMemberIdx());
	  request.setAttribute("followerList", followerList);
      request.getRequestDispatcher("/mypage/mypageFollower").forward(request, response);
      
   }
   
   //팔로우 삭제
   private void deleteFollow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	   Member member = (Member) request.getSession().getAttribute("authentication");
	   Follow follow = new Follow();
	   follow.setMemberIdx(member.getMemberIdx());			//내 member_idx랑 
	   follow.setFollowId(request.getParameter("deletefollow"));	//follow_idx가지고옴
	   followingService.deleteFollow(follow);
	   request.getRequestDispatcher("/mypage/mypageFollow").forward(request, response);
   }
   
   //팔로우 추가
	private void insertFollow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  Member member = (Member) request.getSession().getAttribute("authentication");
	  Follow follow = new Follow();
	  follow.setFollowId(request.getParameter("deletefollow"));
	  follow.setMemberIdx(member.getMemberIdx());
	  followingService.insertFollow(follow);
	  request.getRequestDispatcher("/mypage/mypageFollow").forward(request, response);
	}

   
   //마이페이지 수정페이지
   private void mypageMemberEdit(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {

      request.getRequestDispatcher("/mypage/mypageMemberEdit").forward(request, response);
   }
   
   //마이페이지 수정post
   private void editMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   Member member = (Member) request.getSession().getAttribute("authentication");
	   member.setUserPassword(request.getParameter("editpass"));
	   member.setNickname(request.getParameter("editnickname"));
	   member.setPhone(request.getParameter("editphone"));
	   member.setEmail(request.getParameter("editemail"));
	   member.setAddress(request.getParameter("editaddress"));
	   member.setPhoto(request.getParameter("editeimg"));
	   mypageService.editMember(member);
	   request.getRequestDispatcher("/mypage/mypageBoard").forward(request, response);
   }

	//회원탈퇴
	private void leaveSanta(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		mypageService.leaveSanta(member.getMemberIdx());
	    response.sendRedirect("/main/main");
	}
   
   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}