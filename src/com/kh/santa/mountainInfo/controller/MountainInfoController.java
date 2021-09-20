package com.kh.santa.mountainInfo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.santa.common.wrapper.RequestWrapper;

/**
 * Servlet implementation class MountainInfoController
 */
@WebServlet("/mountainInfo/*")
public class MountainInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		case "mtInfoMain" :
			mtInfoMain(request,response);
			break;
		case "mtInfoDetail" :
			mtInfoDetail(request,response);
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

	private void mtInfoMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/mountainInfo/mtInfoMain").forward(request, response);
		
	}

	private void mtInfoDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/mountainInfo/mtInfoDetail").forward(request, response);
		
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
