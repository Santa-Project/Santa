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

import com.kh.santa.main.validator.IdCheck;
import com.kh.santa.main.validator.JoinForm;
import com.kh.santa.matching.validator.CreateMatchingBoard;


public class ValidatorFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ValidatorFilter() {
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
			
			String redirectURI = null;
			
			switch (uriArr[1]) {
				case "main":
					redirectURI = memberValidation(httpRequest, httpReponse, uriArr);
					break;
				case "matching":
					redirectURI = boardValidation(httpRequest, httpReponse, uriArr);
					break;
				default:
					break;
			}
			
			if(redirectURI != null) {
				httpReponse.sendRedirect(redirectURI);
				return;
			}
		}
		
		chain.doFilter(request, response);
	}

	private String memberValidation(HttpServletRequest httpRequest, HttpServletResponse httpReponse, String[] uriArr) {
		
		String redirectURI = null;
		
		switch(uriArr[2]) {
			case "join":
				JoinForm joinForm = new JoinForm(httpRequest);
				if(!joinForm.test()) {
					redirectURI = "/main/joinform?err=1";
				}
				break;
			case "idCheck":
				IdCheck idCheck = new IdCheck(httpRequest);
				if(!idCheck.test()) {
					redirectURI = "/main/joinform?err=1";
				}
				break;
			default:
				break;
		}
		return redirectURI;
		
	}
	
	private String boardValidation(HttpServletRequest httpRequest, HttpServletResponse httpReponse, String[] uriArr) {
		
		String redirectURI = null;
		
		switch(uriArr[uriArr.length-1]) {
			case "createBoard":
				CreateMatchingBoard createMatchingBoard = new CreateMatchingBoard(httpRequest);
				if(!createMatchingBoard.test()) {
					redirectURI = "/matching/collectTeam/createBoardForm?err=1";
				}
				break;
			default:
				break;
		}
		return redirectURI;
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
