package com.kh.santa.mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.santa.common.wrapper.RequestWrapper;
import com.kh.santa.mountainInfo.model.dto.Mountain;
import com.kh.santa.mountainInfo.model.dto.MountainWishlist;
import com.kh.santa.mountainInfo.model.service.MountainService;
import com.kh.santa.mypage.model.dto.Follow;
import com.kh.santa.mypage.model.dto.Member;
import com.kh.santa.mypage.model.dto.MemberBoard;
import com.kh.santa.mypage.model.dto.MemberBoardComment;
import com.kh.santa.mypage.model.service.FollowingService;
import com.kh.santa.mypage.model.service.MyBoardService;
import com.kh.santa.mypage.model.service.MypageService;
import com.kh.santa.common.file.FileUtil;
import com.kh.santa.common.file.MultiPartParams;


import com.kh.santa.common.file.FileDTO;

/**
 * Servlet implementation class MypageController
 */
@WebServlet("/mypage/*")
public class MypageController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private MyBoardService myboardService = new MyBoardService();
   private FollowingService followingService = new FollowingService();
   private MypageService mypageService = new MypageService();
   private MountainService mountainService = new MountainService();
   private List<Mountain> mountainList = mountainService.searchAllMtIdxAndMtName();
   private List<MountainWishlist> wishlist = new ArrayList<MountainWishlist>();
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
      //case "mypagePassEdit" :
    	//  mypagePassEdit(request,response);
        //   break;
      case "mypageMemberEdit" :
         mypageMemberEdit(request,response);
         break;
      case "anotherBoard" :
          anotherBoard(request,response);
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
      case "like" :
    	  like(request,response);
           break; 
          
      case "following" :
         following(request,response);
          break;
      case "deleteFollowTopage" :
    	  deleteFollowTopage(request,response); 
          break;
    	  
      case "insertMountainwish" :
    	  insertMountainwish(request,response); 
          break;
      case "deleteMountainwish" :
    	  deleteMountainwish(request,response); 
          break;
          
      case "editPass" :
    	  editPass(request,response);
           break;
      case "editMember" :
         editMember(request,response);
          break;
      case "editprofile" :
    	  editprofile(request,response);
           break;
      case "leaveSanta" :
         leaveSanta(request,response);
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
   //s
   //???????????? ?????????
   private void anotherBoard(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
          
		  String anotherIdx = request.getParameter("anotherIdx"); //????????????????????? ???????????? (mypageBoard.jsp??????) ?????????
	      Member anotherMember = mypageService.selectMemberByMemberIdx(anotherIdx); //(????????? ??? ????????? ???????????? ??????) ?????????????????? ????????????
	      request.setAttribute("anotherMember", anotherMember); //??????????????????

          List<MemberBoard> boardList = myboardService.selectBoardDetail(anotherIdx); //????????? ??????????????? ????????? ????????????
          List<Object[]> others = new ArrayList<Object[]>();
          
          
        	  for (MemberBoard memberBoard : boardList) {
                  String boardIdx = memberBoard.getMemBoardIdx(); //??????????????? ????????????
                  FileDTO file =  myboardService.selectBoardFile(boardIdx); //?????????????????? ????????????
                  List<MemberBoardComment> commentList = myboardService.selectBoardComent(boardIdx); //?????????????????? ????????????
                  Object[] ob = new Object[] {memberBoard, file,commentList}; //????????? ????????????
                  others.add(ob); 
               }
          
          
          request.setAttribute("others", others);
          System.out.println(anotherMember.getMemberIdx());
          wishlist = mypageService.selectMountainWishlist(anotherMember.getMemberIdx());
          request.setAttribute("wishlist", wishlist);
          request.getRequestDispatcher("/mypage/anotherBoard").forward(request, response); //????????? ?????? ???????????????????????????..
   }
   
   //????????? ???????????????
   private void mypageWriteBoard(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
	   String[] seoulcity = {"?????????","?????????","?????????","?????????","?????????","?????????","?????????","?????????","?????????","?????????","????????????","?????????",
"????????????","?????????","?????????","?????????","?????????","?????????","?????????","????????????","?????????","?????????","?????????","??????","?????????"};
	   request.setAttribute("seoulcity", seoulcity);
	   
	   request.setAttribute("mountainList", mountainList);
	   Member member = (Member) request.getSession().getAttribute("authentication");
       wishlist = mypageService.selectMountainWishlist(member.getMemberIdx());
       request.setAttribute("wishlist", wishlist);
      request.getRequestDispatcher("/mypage/mypageWriteBoard").forward(request, response);
   } 
   
   //????????? ??????post
   private void insertBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      FileUtil util = new FileUtil();
      MultiPartParams params = util.fileUpload(request);
      
      Member member = (Member) request.getSession().getAttribute("authentication");
      MemberBoard board = new MemberBoard();
      board.setMemberIdx(member.getMemberIdx());
      board.setMtName(params.getParameter("mountlist"));
      board.setMtRegion(params.getParameter("region"));
      board.setBoardComment(params.getParameter("writetext"));
      
      List<FileDTO> fileDTOs = params.getFilesInfo(); 
      myboardService.insertBoard(board,fileDTOs);
      response.sendRedirect("/mypage/mypageBoard");
   }
   
   //????????? ??????
   private void deleteBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String boardIdx =request.getParameter("deleteboard");
      myboardService.deleteBoard(boardIdx);
      request.getRequestDispatcher("/mypage/mypageBoard").forward(request, response);
   }

   //?????? ??????
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
      mypageBoard(request,response);
   }

   //?????? ??????
   private void deleteComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String commentIdx =request.getParameter("commentIdx");
      myboardService.deleteComment(commentIdx);
      response.sendRedirect("/mypage/mypageBoard");
   }
   
   //?????????
   private void like(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = (Member)request.getSession().getAttribute("authentication");
		String memberIdx = member.getMemberIdx();
		String memboardIdx = request.getParameter("membaordIdx");
		String like = request.getParameter("boardlike");
		System.out.println(memboardIdx);
		System.out.println(like);
		if(like.equals("true")) {
			myboardService.updateBoardLike(memboardIdx);
			request.setAttribute("boardlike", true);
		} else {
			myboardService.removeBoardLike(memboardIdx);
			request.setAttribute("boardlike", false);
		}
		response.sendRedirect("/mypage/mypageBoard");
	}
      
   //?????????
   private void mypageFollow(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
      
     Member member = (Member) request.getSession().getAttribute("authentication");
     List<Member> followList = followingService.FollowList(member.getMemberIdx()); 
     request.setAttribute("followList", followList); 
     request.setAttribute("mountainList", mountainList);
     wishlist = mypageService.selectMountainWishlist(member.getMemberIdx());
     request.setAttribute("wishlist", wishlist);
      request.getRequestDispatcher("/mypage/mypageFollow").forward(request, response); 
   }
   
   //?????????
   private void mypageFollower(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
     Member member = (Member) request.getSession().getAttribute("authentication");
     List<Member> followerList = followingService.FollowerList(member.getMemberIdx());
     request.setAttribute("followerList", followerList);
     request.setAttribute("mountainList", mountainList);
     wishlist = mypageService.selectMountainWishlist(member.getMemberIdx());
     request.setAttribute("wishlist", wishlist);
      request.getRequestDispatcher("/mypage/mypageFollower").forward(request, response);
      
   }
   

   
   //????????? ??????,??????(???????????? ???????????????)
   private void following(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     Member member = (Member) request.getSession().getAttribute("authentication");
     String following= request.getParameter("follow");
     Follow follow = new Follow();
     follow.setFollowId(request.getParameter("anoterIdx"));
     follow.setMemberIdx(member.getMemberIdx()); 
     if(following.equals(following)) { //true?????? ?????????
    	 followingService.insertFollow(follow);
     }else{
    	 followingService.deleteFollow(follow);
     }
     anotherBoard(request,response);
   }
   
   //????????? ??????
   private void deleteFollowTopage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      Member member = (Member) request.getSession().getAttribute("authentication");
      Follow follow = new Follow();
      follow.setMemberIdx(member.getMemberIdx());         		  //??? member_idx??? 
      follow.setFollowId(request.getParameter("deletefollow"));   //follow_idx????????????
      followingService.deleteFollow(follow);
      request.getRequestDispatcher("/mypage/mypageFollow").forward(request, response);
   }
   
   //??????????????? ?????? ??????????????????
	private void mypagePassEdit(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
	   request.setAttribute("mountainList", mountainList);
	   request.setAttribute("wishlist", wishlist);
	   request.getRequestDispatcher("/mypage/mypagePassEdit").forward(request, response);
	}
   

	//??????????????? ????????? post
	private void editPass(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		String inputPassword= request.getParameter("password");
		if(member.getUserPassword().equals(inputPassword)) { //?????????????????? ?????????
			request.getRequestDispatcher("/mypage/mypageMemberEdit").forward(request, response);
		}else{
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();

			out.println("<script language='javascript'>");
			out.println("history.back()");
			out.println("</script>");

			out.flush();
			request.getRequestDispatcher("/mypage/mypagePassEdit").forward(request, response);
		}
		
	}

	
   //??????????????? ???????????????
   private void mypageMemberEdit(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
	   request.setAttribute("mountainList", mountainList);
	   Member member = (Member) request.getSession().getAttribute("authentication");
       wishlist = mypageService.selectMountainWishlist(member.getMemberIdx());
       request.setAttribute("wishlist", wishlist);
      request.getRequestDispatcher("/mypage/mypageMemberEdit").forward(request, response);
   }
   
   //??????????????? ??????post
   private void editMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
      Member member = (Member) request.getSession().getAttribute("authentication");
      String newNickname = request.getParameter("editnickname");
      member.setPhone(request.getParameter("editphone"));
      member.setEmail(request.getParameter("editmail"));
      String address = "(" + request.getParameter("zipNo") + ")" + request.getParameter("roadAddrPart1") + request.getParameter("detailaddress");
      member.setAddress(address);
      
      if(request.getParameter("editpass")==member.getUserPassword()) { //????????? //????????? ?????? ?????????
    	  member.setUserPassword(request.getParameter("editpass"));  
    	  mypageService.editMember(member,newNickname);
      }else {//???????????? ???????????????
    	  mypageService.editMemberExclusionPassword(member,newNickname);
      }
      request.getRequestDispatcher("/mypage/mypageBoard").forward(request, response);
      
   }
   
   //????????? ??????post
	private void editprofile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FileUtil util = new FileUtil();
	    MultiPartParams params = util.fileUpload(request);
	    Member member = (Member) request.getSession().getAttribute("authentication");
	    member.setMemberIdx(member.getMemberIdx());
	    
	    member.setProfileContent(params.getParameter("profilecomment"));
	    
		List<FileDTO> fileDTOs = params.getFilesInfo(); 
		mypageService.updateprofile(member,fileDTOs);
		 mypageBoard(request,response);
		 System.out.println(member);

	}

   
   

   //???????????? ??????
   private void insertMountainwish(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
	   Member member = (Member) request.getSession().getAttribute("authentication");
	   String memberIdx = member.getMemberIdx();
	   String mtIdx = request.getParameter("insertwish");
	   
	   Mountain mountain = new Mountain();
	   mountain = mountainService.searchMountain(mtIdx); 
	   
	   mypageService.insertMountainWishlist(memberIdx, mountain);
	   mypageBoard(request,response);
}
   
   //???????????? ??????
	private void deleteMountainwish(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		 Member member = (Member) request.getSession().getAttribute("authentication");
		   String memberIdx = member.getMemberIdx();
		   String mtIdx = request.getParameter("deletewish");
		   mypageService.deleteMountainWishlist(memberIdx,mtIdx);
		   mypageBoard(request,response);
	}

   
   
   //????????????
   private void leaveSanta(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
      
      Member member = (Member) request.getSession().getAttribute("authentication");
      mypageService.leaveSanta(member.getMemberIdx());
      wishlist = mypageService.selectMountainWishlist(member.getMemberIdx());
      request.setAttribute("wishlist", wishlist);
      request.getSession().removeAttribute("authentication");
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