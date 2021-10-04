package com.kh.santa.matching.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;


public class CreateMatchingBoard {
	
	private String brdName;
	private int memberVolume;
	private String brdContent;
	
	
	HttpServletRequest request;
	private Map<String,String> failedValidation = new HashMap<String,String>();
	
	public CreateMatchingBoard(HttpServletRequest request) {
		this.request = request;
		this.brdName = request.getParameter("brdName");
		this.memberVolume = Integer.parseInt(request.getParameter("memberVolume"));
		this.brdContent = request.getParameter("brdContent");
	}
	
	public boolean test() {
		
		boolean isFailed = false;
		
		// 방 제목은 40자 이하
		if(brdName == null || !Pattern.matches("^[^ ]{1,}$", brdName) ||brdName.length() > 40) {
			failedValidation.put("brdName",brdName);
			isFailed = true;
		}
		
		// 인원수 3명-7명
		if(memberVolume < 3 || memberVolume > 7) {
			failedValidation.put("memberVolume", String.valueOf(memberVolume));
			isFailed = true;
		}
		
		// 3일 이후 선택 가능
		/*
		 * if(datepicker.compareTo(new Date(System.currentTimeMillis()+259200000l)) >=
		 * 0) { failedValidation.put("datepicker", String.valueOf(datepicker)); isFailed
		 * = true; }
		 */
		
		// 게시글 200자 이하
		if(brdContent.length() > 200) {
			failedValidation.put("brdContent", brdContent);
			isFailed = true;
		}
		
		// 데이터 입력양식에 맞지 않으면 false 반환
		if(isFailed) {
			request.getSession().setAttribute("boardValid", failedValidation);
			request.getSession().setAttribute("createBoard", this);
			return false;
		} else {
			request.getSession().removeAttribute("boardValid");
			request.getSession().removeAttribute("createBoard");
			return true;
		}
	}

	public String getBrdName() {
		return brdName;
	}

	public int getMemberVolume() {
		return memberVolume;
	}

	public String getBrdContent() {
		return brdContent;
	}

	@Override
	public String toString() {
		return "createMatchingBoard [brdName=" + brdName + ", memberVolume=" + memberVolume + ", brdContent=" + brdContent + "]";
	}

	
	
	

}
