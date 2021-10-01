package com.kh.santa.mountainInfo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.santa.common.wrapper.RequestWrapper;
import com.kh.santa.mountainInfo.model.dto.Mountain;
import com.kh.santa.mountainInfo.model.service.MountainService;
import com.kh.santa.mypage.model.dto.Member;

/**
 * Servlet implementation class MountainInfoController
 */
@WebServlet("/mountainInfo/*")
public class MountainInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MountainService mountainService = new MountainService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MountainInfoController() {
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
		case "inserthashtag" :
			inserthashtag(request,response);
			break;	
		case "mtInfoMain" :
			mtInfoMain(request,response);
			break;
		case "mtInfoDetail" :
			mtInfoDetail(request,response);
			break;
		case "like" :
			like(request,response);
			break;
		case "searchKeyword" :
			searchKeyword(request,response);
			break;
		case "searchNearby" :
			mtInfoDetail(request,response);
			break;
		case "searchByRegion" :
			mtInfoMain(request,response);
			break;
		case "searchByPopularity" :
			mtInfoDetail(request,response);
			break;
		case "searchByLevelDistance" :
			searchByLevelDistance(request,response);
			break;
		case "searchByHashtag" :
			searchByHashtag(request,response);
			break;
		default :
			break;
		}
	}
	
	private void inserthashtag(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/mountainInfo/inserthashtag").forward(request, response);
		
	}

	private void mtInfoMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/mountainInfo/mtInfoMain").forward(request, response);
		
	}

	private void mtInfoDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Mountain mountain = null;

		if(request.getParameter("mtIdx") != null) {
			String mtIdx  = request.getParameter("mtIdx");
			mountain = mountainService.searchMountain(mtIdx);
			request.getSession().setAttribute("mountain", mountain);
		} else {
			mountain = (Mountain) request.getSession().getAttribute("mountain");
		}

		Member member = (Member)request.getSession().getAttribute("authentication");

		if(member != null) {
	         
			String memberIdx = member.getMemberIdx();

			if(mountainService.checkMountainWishlist(memberIdx,mountain.getMtIdx())) {
				request.getSession().setAttribute("like", true);
			} else{
				request.getSession().setAttribute("like", false);
			};

		}

		request.getRequestDispatcher("/mountainInfo/mtInfoDetail").forward(request, response);

		
	}
	
	private void like(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = (Member)request.getSession().getAttribute("authentication");
		String memberIdx = member.getMemberIdx();
		Mountain mountain = (Mountain)request.getSession().getAttribute("mountain");

		String like = request.getParameter("like");

		if(like.equals("true")) {
			// mountain_wishlist에 추가
			mountainService.addMountainWishlist(memberIdx,mountain);
			request.getSession().setAttribute("like", true);
		} else {
			mountainService.removeMountainWishlist(memberIdx, mountain);
			request.getSession().setAttribute("like", false);
		}
		
		response.sendRedirect("/mountainInfo/mtInfoDetail");

		
	}
	
	private void searchKeyword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/mountainInfo/mtInfoMain");
		
	}

	private void searchNearby(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/mountainInfo/mtInfoMain");
		
	}
	
	private void searchByRegion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/mountainInfo/mtInfoMain");
		
	}

	private void searchByPopularity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/mountainInfo/mtInfoMain");
		
	}
	
	private void searchByLevelDistance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/mountainInfo/mtInfoMain");
		
	}

	private void searchByHashtag(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/mountainInfo/mtInfoMain");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
