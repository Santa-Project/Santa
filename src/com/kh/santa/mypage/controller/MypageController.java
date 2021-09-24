package com.kh.santa.mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.santa.common.wrapper.RequestWrapper;
import com.kh.santa.mypage.model.dto.Member;
import com.kh.santa.mypage.model.dto.MemberBoard;
import com.kh.santa.mypage.model.service.MyBoardService;
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
		default :
			break;
		}
		
	}
	
	//게시글 작성
	private void insertBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FileUtil util = new FileUtil();
		MultiPartParams params = util.fileUpload(request);
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		MemberBoard board = new MemberBoard();
		board.setMemberIdx(member.getMemberIdx());
		board.setMtMountain(params.getParameter("writefile"));
		board.setMtRegion(params.getParameter("region"));
		board.setBoardComment(params.getParameter("writetext"));
		
		System.out.println(member);
		System.out.println(board);
		
		List<FileDTO> fileDTOs = params.getFilesInfo(); 
		myboardService.insertBoard(board,fileDTOs);
		response.sendRedirect("/mypage/mypageBoard");
		
	}

	private void mypageBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/mypage/mypageBoard").forward(request, response);
	}
	
	private void mypageWriteBoard(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		request.getRequestDispatcher("/mypage/mypageWriteBoard").forward(request, response);
	} //이거만 왱 안되죠

	private void mypageFollow(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		request.getRequestDispatcher("/mypage/mypageFollow").forward(request, response);
	}
	
	private void mypageFollower(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/mypage/mypageFollower").forward(request, response);
	}
	
	private void mypageMemberEdit(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		request.getRequestDispatcher("/mypage/mypageMemberEdit").forward(request, response);
	}

	



	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
