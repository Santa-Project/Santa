package com.kh.santa.main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.santa.main.model.service.MainService;
import com.kh.santa.mypage.model.dto.Member;



/**
 * Servlet implementation class MainController
 */
@WebServlet("/main/*")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MainService mainService = new MainService();

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
		case "logout":
			logout(request, response);
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
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		// 1. 시스템에서 문제가 생겨서 (DB가 뻗었다던가... 외부 API서버가 죽었다던가...)
		// 	  예외가 발생하는 경우. => 예외처리를 service 단에서 처리
		Member member = mainService.memberAuthenticate(userId, password);
		
		//로그인 실패
		// 2. 사용자가 잘못된 아이디나 비밀번호를 입력한 경우.
		// 	    사용자에게 아이디나 비밀번호가 틀렸음을 알림, login-form으로 redirect
		if(member == null) {
			response.sendRedirect("/main/loginform?err=1");
			return;
		}
		
		request.getSession().setAttribute("authentication", member);
		response.sendRedirect("/main/main");

	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getSession().removeAttribute("authentication");
		response.sendRedirect("/main/main");

	}
	
	private void joinform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/main/joinform").forward(request, response);

	}
	
	private void join(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		//회원가입 실패 
		if(1> 0) { 
		request.setAttribute("msg", "회원가입에 실패하였습니다.");
		request.setAttribute("url", "/main/joinform");
		request.getRequestDispatcher("/common/result").forward(request, response); 
		}
		
		
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
