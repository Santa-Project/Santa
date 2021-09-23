package com.kh.santa.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.santa.common.code.ErrorCode;
import com.kh.santa.common.code.member.MemberGrade;
import com.kh.santa.common.exception.HandlableException;
import com.kh.santa.mypage.model.dto.Member;

public class AuthorizationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthorizationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpReponse = (HttpServletResponse) response;
		String[] uriArr = httpRequest.getRequestURI().split("/");
		
		
		if(uriArr.length != 0) {
			switch (uriArr[1]) {
				case "main":
					mainAuthorize(httpRequest, httpReponse, uriArr);
					break;
				case "matching":
					matchingAuthorize(httpRequest, httpReponse, uriArr);
					break;
				case "community":
					communityAuthorize(httpRequest, httpReponse, uriArr);
					break;
				case "mypage":
					mypageAuthorize(httpRequest, httpReponse, uriArr);
					break;
				default:
					break;
			}
		}
		
		chain.doFilter(request, response);
	}
	
	private void mainAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpReponse, String[] uriArr) {
		
		//세션 반아오기
		HttpSession session = httpRequest.getSession();
		
		//세션에 저장된 "authentication" Attribute를 받아오기
		// * login 시 controller에서 setAttribute("authentication")
		// - 입력받은 id와 pw로 member정보가 db에 있을 경우 member dto에 정보를 받아오고 authentication value에 저장
		// * logout 시 controller에서 removeAttribute("authentication")
		// - authentication Attribute 삭제
		// - authentication Attribute가 없을 경우 : 로그인 안된 상태
		// - authentication Attribute가 있을 경우 : 로그인 된 상태
		Member member = (Member) session.getAttribute("authentication");
		
		// 로그인이 안된 유저
		if(member == null) {
			
			if(uriArr[2].equals("logout")) {
				throw new HandlableException(ErrorCode.REDIRECT_PREVIOUS_PAGE_NO_MESSAGE);
			}
			
			return;
		}
		
		switch(uriArr[2]) {
		case "loginform":
			//HandlableException으로 던지면 ExceptionHandler에서 받아서 back Attribute 추가 -> result.jsp에서 뒤로가기 
			throw new HandlableException(ErrorCode.REDIRECT_PREVIOUS_PAGE_NO_MESSAGE);
		case "login":
			throw new HandlableException(ErrorCode.REDIRECT_PREVIOUS_PAGE_NO_MESSAGE);
		case "joinform":
			throw new HandlableException(ErrorCode.REDIRECT_PREVIOUS_PAGE_NO_MESSAGE);
		case "join":
			throw new HandlableException(ErrorCode.REDIRECT_PREVIOUS_PAGE_NO_MESSAGE);
		case "finding_id":
			throw new HandlableException(ErrorCode.REDIRECT_PREVIOUS_PAGE_NO_MESSAGE);
		case "id_request":
			throw new HandlableException(ErrorCode.REDIRECT_PREVIOUS_PAGE_NO_MESSAGE);
		case "finding_password":
			throw new HandlableException(ErrorCode.REDIRECT_PREVIOUS_PAGE_NO_MESSAGE);
		case "password_request":
			throw new HandlableException(ErrorCode.REDIRECT_PREVIOUS_PAGE_NO_MESSAGE);
		default:
			break;
		}
		
		
	}
	
	private void matchingAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpReponse, String[] uriArr) {
		
		//세션 반아오기
		HttpSession session = httpRequest.getSession();
		
		//세션에 저장된 "authentication" Attribute를 받아오기
		// * login 시 controller에서 setAttribute("authentication")
		// - 입력받은 id와 pw로 member정보가 db에 있을 경우 member dto에 정보를 받아오고 authentication value에 저장
		// * logout 시 controller에서 removeAttribute("authentication")
		// - authentication Attribute 삭제
		// - authentication Attribute가 없을 경우 : 로그인 안된 상태
		// - authentication Attribute가 있을 경우 : 로그인 된 상태
		Member member = (Member) session.getAttribute("authentication");
		
		if(member == null) {
			throw new HandlableException(ErrorCode.REDIRECT_LOGIN_PAGE);
		}
		
		switch(uriArr[2]) {
		case "collectTeam":
			collectTeam(httpRequest, httpReponse, uriArr, member);
			break;
		case "findingTeam":
			findingTeam(httpRequest, httpReponse, uriArr, member);
			break;
			
		}
		
		
		
		
	}
	
	private void collectTeam(HttpServletRequest httpRequest, HttpServletResponse httpReponse, String[] uriArr, Member member) {
		
		MemberGrade userGrade = MemberGrade.valueOf(member.getGrade());
		
		if(userGrade.DESC.equals("boardMaster")) return;
		
		switch(uriArr[3]) {
		case "modify_board_form":
			throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
		case "delete_board_form":
			throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
		case "invite":
			throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
		case "managingTeam":
			throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
		case "accept":
			throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
		case "reject":
			throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
		case "create_notice":
			throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
		default:
			break;
		}
		
	}

	private void findingTeam(HttpServletRequest httpRequest, HttpServletResponse httpReponse, String[] uriArr, Member member) {
		
		MemberGrade userGrade = MemberGrade.valueOf(member.getGrade());
		
		// fmUser인 경우 findingTeam 기능들에 접근할 권한 가짐
		if(userGrade.DESC.equals("fmUser")) return;
		if(userGrade.DESC.equals("boardMaster&fmUser")) return;
		
		/*
		 * findingMember 게시판 생성한 유저 
		 * collectTeam 게시판 생성하고 findingMember 게시판 생성한 유저
		 */
		
		
		// fmUser가 아닌 경우 findingTeam의 아래 url로 접근 시 권한 없음
		switch(uriArr[3]) {
		case "modify_board_form":
			throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
		case "delete_board_form":
			throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
		case "managingRequest":
			throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
		case "accept":
			throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
		case "reject":
			throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
		default:
			break;
		}
		
		

		
	}

	private void communityAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpReponse, String[] uriArr) {
		
		//세션 반아오기
		HttpSession session = httpRequest.getSession();
		
		//세션에 저장된 "authentication" Attribute를 받아오기
		// * login 시 controller에서 setAttribute("authentication")
		// - 입력받은 id와 pw로 member정보가 db에 있을 경우 member dto에 정보를 받아오고 authentication value에 저장
		// * logout 시 controller에서 removeAttribute("authentication")
		// - authentication Attribute 삭제
		// - authentication Attribute가 없을 경우 : 로그인 안된 상태
		// - authentication Attribute가 있을 경우 : 로그인 된 상태
		Member member = (Member) session.getAttribute("authentication");
		
		// 로그인한 회원들은 community 모든 기능에 접근이 가능한 권한을 가짐
		if(member != null) return;
		
		// 로그인 안 한 유저들은 community 기능에서 like, inesert_following, comment url로 들어오는 기능 사용 불가
		switch(uriArr[2]) {
		case "like":
			//HandlableException으로 던지면 ExceptionHandler에서 받아서 back Attribute 추가 -> result.jsp에서 뒤로가기 
			throw new HandlableException(ErrorCode.REDIRECT_LOGIN_PAGE);
		case "insert_following":
			throw new HandlableException(ErrorCode.REDIRECT_LOGIN_PAGE);
		case "comment":
			throw new HandlableException(ErrorCode.REDIRECT_LOGIN_PAGE);
		default:
			break;
		}
		
	}

	private void mypageAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpReponse, String[] uriArr) {
		
		// showBoard는 모든 유저가 사용 가능
		if(uriArr[2].equals("showBoard")) return;
		
		//세션 반아오기
		HttpSession session = httpRequest.getSession();
		
		//세션에 저장된 "authentication" Attribute를 받아오기
		// * login 시 controller에서 setAttribute("authentication")
		// - 입력받은 id와 pw로 member정보가 db에 있을 경우 member dto에 정보를 받아오고 authentication value에 저장
		// * logout 시 controller에서 removeAttribute("authentication")
		// - authentication Attribute 삭제
		// - authentication Attribute가 없을 경우 : 로그인 안된 상태
		// - authentication Attribute가 있을 경우 : 로그인 된 상태
		Member member = (Member) session.getAttribute("authentication");
		
		// 로그인 안 된 사용자가 /mypage/* (showboard제외) url로 접근 시 권한 제어
		if(member == null) {
			throw new HandlableException(ErrorCode.REDIRECT_LOGIN_PAGE_NO_MESSAGE);
		}
				
		
	}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
