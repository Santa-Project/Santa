package com.kh.santa.main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.santa.main.model.service.MainService;
import com.kh.santa.mountainInfo.model.dto.Mountain;
import com.kh.santa.mountainInfo.model.service.MountainService;
import com.kh.santa.mypage.model.dto.Member;



/**
 * Servlet implementation class MainController
 */
@WebServlet("/main/*")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MainService mainService = new MainService();
	private MountainService mountainService = new MountainService();

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
		case "kakaoLogin":
			kakaoLogin(request, response);
			break;
		case "logout":
			logout(request, response);
			break;
		case "joinform":
			joinform(request, response);
			break;
		case "id-check":
			idCheck(request, response);
			break;
		case "address":
			address(request, response);
			break;
		case "join":
			join(request, response);
			break;
		case "joinImpl":
			joinImpl(request, response);
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
	
	private void kakaoLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String kakaoId = request.getParameter("id");
		
		Member member = mainService.searchByKakaoId(kakaoId);
		
		//회원이 아닌 경우
		if(member == null) {
			//회원추가 진행
			//회원추가 시 에러 발생 코드 생성?
			member = new Member();
			member.setNickname(request.getParameter("nickname")); // 필수
			System.out.println(request.getParameter("nickname"));
			if(request.getParameter("email") != null) {
				member.setEmail(request.getParameter("email"));
			} else {
				member.setEmail("-");
			}
			if(request.getParameter("gender") != null) {
				String gender = request.getParameter("gender").substring(0, 1).toUpperCase();
				System.out.println(gender);
				member.setGender(gender);
			} else {
				member.setGender("-");
			}
			/* 카카오 프로필 사진은 url로 넘어옴(데이터베이스에 사진 type은 blob)
			 * if(request.getParameter("photo") != null) {
			 * member.setPhoto(request.getParameter("photo")); } else {
			 * member.setPhoto("-"); }
			 */
			mainService.createMemberWithKakao(member, kakaoId);
			//추가 후 memberDTO 받아오기
			member = mainService.searchByKakaoId(kakaoId);
		};
		
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
		List<Mountain> mountainList = mountainService.searchAllMtIdxAndMtName();
		request.setAttribute("mountainList", mountainList);
		request.getRequestDispatcher("/main/joinform").forward(request, response);
	}
	
	private void idCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("id");
		System.out.println(userId);
		
		if(!mainService.checkMemberById(userId)) {
			response.getWriter().print("available");
		} else {
			response.getWriter().print("disable");
		}
	}
	
	private void address(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/main/addrPopup").forward(request, response);
	}
	
	private void join(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String gender = request.getParameter("gender");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String address = "(" + request.getParameter("zipNo") + ")" + request.getParameter("roadAddrPart1") + request.getParameter("detailaddress");
		System.out.println(address);
		Member member = new Member();
		member.setUserId(id);
		member.setUserPassword(password);
		member.setUsername(name);
		member.setNickname(nickname);
		member.setGender(gender);
		member.setPhone(mobile);
		member.setEmail(email);
		member.setAddress(address);
		
		
		List<Mountain> mountainList = new ArrayList<Mountain>();
		
		String[] mtIdxArr = request.getParameterValues("preference");
		/* String mtName = request.getParameter("산이름"); */
		
		
		for (String mtIdx : mtIdxArr) {
			Mountain mountain = mountainService.searchMountain(mtIdx);
			mountainList.add(mountain);
		}
		
		
		String persistToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("persistUser", member);
		request.getSession().setAttribute("persistPreperence", mountainList);
		request.getSession().setAttribute("persist-token", persistToken);
		
		mainService.authenticateEmail(member, persistToken);
		
		request.setAttribute("msg", "회원가입을 위한 이메일이 발송되었습니다.");
		request.setAttribute("url", "/main/loginform");
		request.getRequestDispatcher("/common/result").forward(request,response);
	}
	
	private void joinImpl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Member member = (Member)session.getAttribute("persistUser");
		List<Mountain> mountainList = (List<Mountain>)session.getAttribute("persistPreperence");
		
		mainService.insertMemberAndPreperence(member, mountainList);
		
		
		// 같은 persistUser값이 두 번 DB에 입력되지 않도록 사용자 정보와 인증을 만료시킴
		session.removeAttribute("persistPreperence");
		session.removeAttribute("persistUser");
		session.removeAttribute("persist-token");
		response.sendRedirect("/main/loginform");
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
