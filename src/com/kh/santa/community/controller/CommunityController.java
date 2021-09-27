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
		default :
			break;
		}
	}

	private void community(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member[] memberArr = communityService.selectPopularMember();
		
		for (int i = 0; i < memberArr.length; i++) {

			System.out.println(memberArr[i]);
		}

		
        request.setAttribute("memberArr", memberArr);
        
        
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
