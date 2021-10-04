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
		// - authentication Attribute가 없을 경우 : 로그인 안된 상태
		// - authentication Attribute가 있을 경우 : 로그인 된 상태
		Member member = (Member) session.getAttribute("authentication");
		
		// 로그인이 안된 유저
		if(member == null) {
			
			if(uriArr[2].equals("logout")) {
				throw new HandlableException(ErrorCode.REDIRECT_LOGIN_PAGE);
			}
			
			return;
		}
		
		switch(uriArr[2]) {
		case "loginform":
			//HandlableException으로 던지면 ExceptionHandler에서 받아서 back Attribute 추가 -> result.jsp에서 뒤로가기 
			throw new HandlableException(ErrorCode.REDIRECT_PREVIOUS_PAGE_NO_MESSAGE);
		case "login":
			throw new HandlableException(ErrorCode.REDIRECT_PREVIOUS_PAGE_NO_MESSAGE);
		case "kakaoLogin":
			throw new HandlableException(ErrorCode.REDIRECT_PREVIOUS_PAGE_NO_MESSAGE);
		case "joinform":
			throw new HandlableException(ErrorCode.REDIRECT_PREVIOUS_PAGE_NO_MESSAGE);
		case "join":
			throw new HandlableException(ErrorCode.REDIRECT_PREVIOUS_PAGE_NO_MESSAGE);
		case "joinImpl":
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
		// - authentication Attribute가 없을 경우 : 로그인 안된 상태
		// - authentication Attribute가 있을 경우 : 로그인 된 상태
		Member member = (Member) session.getAttribute("authentication");
		
		if(member == null) {
			throw new HandlableException(ErrorCode.REDIRECT_LOGIN_PAGE);
		}
		
		switch(uriArr[uriArr.length-1]) {
		case "waitingList" : 
			if(httpRequest.getParameter("leaderIdx").equals(member.getMemberIdx())) return;
			throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
		case "accept" :
			if(httpRequest.getParameter("leaderIdx").equals(member.getMemberIdx())) return;
			throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
		case "reject" :
			if(httpRequest.getParameter("leaderIdx").equals(member.getMemberIdx())) return;
			throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
		case "createNotice" :
			if(httpRequest.getParameter("leaderIdx").equals(member.getMemberIdx())) return;
			throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
		case "application" :
			if(httpRequest.getParameter("leaderIdx").equals(member.getMemberIdx())) {
			throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
			}
		}
		
	}

	private void communityAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpReponse, String[] uriArr) {
		
		//세션 반아오기
		HttpSession session = httpRequest.getSession();
		
		//세션에 저장된 "authentication" Attribute를 받아오기
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
		
		// 다른사람 게시글 (anotherBoard)는 모든 유저가 사용 가능
		if(uriArr[2].equals("anotherBoard")) return;
		
		//세션 반아오기
		HttpSession session = httpRequest.getSession();
		
		//세션에 저장된 "authentication" Attribute를 받아오기
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
