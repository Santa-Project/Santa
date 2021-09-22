package com.kh.santa.main.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.santa.common.exception.HandlableException;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/main/*")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String[] uriArr = request.getRequestURI().split("/");

		switch (uriArr[uriArr.length - 1]) {
		case "main":
			main(request, response);
			break;
		case "loginform":
			loginform(request, response);
			break;
		case "login":
			login(request, response);
			break;
		case "joinform":
			joinform(request, response);
			break;
		case "join":
			join(request, response);
			break;
		case "finding_id":
			finding_id(request, response);
			break;
		case "id_request":
			id_request(request, response);
			break;
		case "finding_pw":
			finding_pw(request, response);
			break;
		case "password_request":
			password_request(request, response);
			break;
		case "notice1":
			request.getRequestDispatcher("/main/notice1").forward(request, response);
		case "notice2":
			request.getRequestDispatcher("/main/notice2").forward(request, response);
		case "novice_guide":
			request.getRequestDispatcher("/main/novice_guide").forward(request, response);


		default:
			break;

		}
	}

	private void main(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/main/main").forward(request, response);

	}

	private void loginform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/main/loginform").forward(request, response);

	}
	
	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//로그인 실패
		if(!false) {
			request.setAttribute("msg", "로그인에 실패하였습니다.");
			request.setAttribute("url", "/main/loginform");
			request.getRequestDispatcher("/WEB-INF/views/common/result.jsp").forward(request, response);
		}
		
		response.sendRedirect("/main/main");

	}
	
	private void joinform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/main/joinform").forward(request, response);

	}
	
	private void join(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*
		 * //회원가입 실패 if(!false) { request.setAttribute("msg", "회원가입에 실패하였습니다.");
		 * request.setAttribute("url", "/main/joinform");
		 * request.getRequestDispatcher("/common/result").forward(request, response); }
		 */
		
		request.setAttribute("msg", "회원가입에 성공하였습니다.");
		request.setAttribute("url", "/main/loginform");
		request.getRequestDispatcher("/common/result").forward(request, response);

	}

	private void finding_id(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/main/finding_id").forward(request, response);

	}
	
	private void id_request(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/main/finding_id");
	}

	private void finding_pw(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/main/finding_pw").forward(request, response);

	}
	
	private void password_request(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/main/password_request");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
