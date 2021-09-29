package com.kh.santa.main.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class JoinForm {
	
	private String id;
	private String password;
	private String verifyPassword;
	private String name;
	private String nickname;
	private String mobile;
	private String email;
	private String zipNo;
	private String roadAddrPart1;
	private String detailaddress;
	
	
	HttpServletRequest request;
	private Map<String,String> failedValidation = new HashMap<String,String>();
	
	public JoinForm(HttpServletRequest request) {
		this.request = request;
		this.id = request.getParameter("id");
		this.password = request.getParameter("password");
		this.verifyPassword = request.getParameter("verifyPassword");
		this.name = request.getParameter("name");
		this.nickname = request.getParameter("nickname");
		this.mobile = request.getParameter("mobile");
		this.email = request.getParameter("email");
		this.zipNo = request.getParameter("zipNo");
		this.roadAddrPart1 = request.getParameter("roadAddrPart1");
		this.detailaddress = request.getParameter("detailaddress");
	}
	
	public boolean test() {
		
		boolean isFailed = false;
		
		// 아이디가 영문 또는 숫자 조합의 문자열인지
		if(!Pattern.matches(".*[a-zA-Z0-9]{1,}", id)) {
			failedValidation.put("id",id);
			isFailed = true;
		}
		
		// 비밀번호가 영어, 숫자, 특수문자 조합의 8자리 이상의 문자열인지 확인
		if(!Pattern.matches("^(?=.*[^ㄱ-ㅣ가-힣])(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$", password)) {
			failedValidation.put("password", password);
			isFailed = true;
		}
		
		if(!verifyPassword.equals(password)) {
			
			failedValidation.put("verifyPassword", verifyPassword);
			isFailed = true;
		}
		
		if(!Pattern.matches(".*[a-zA-Z가-힣]{2,}", name)) {
			failedValidation.put("name", name);
			isFailed = true;
		}
		
		if(!Pattern.matches(".*[a-zA-Z가-힣0-9]{1,}", nickname)) {
			failedValidation.put("nickname", nickname);
			isFailed = true;
		}
		
		
		if(!Pattern.matches("\\d{9,11}", mobile)) { 
			failedValidation.put("mobile", mobile); 
			isFailed = true; 
		}
		
		if(!Pattern.matches("^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$", email)) { 
			failedValidation.put("email", email); 
			isFailed = true; 
		}
		
		
		if(isFailed) {
			request.getSession().setAttribute("joinValid", failedValidation);
			request.getSession().setAttribute("joinForm", this);
			return false;
		} else {
			request.getSession().removeAttribute("joinValid");
			request.getSession().removeAttribute("joinForm");
			return true;
		}
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getNickname() {
		return nickname;
	}

	public String getVerifyPassword() {
		return verifyPassword;
	}

	public String getMobile() {
		return mobile;
	}
	
	public String getEmail() {
		return email;
	}

	public String getZipNo() {
		return zipNo;
	}

	public String getRoadAddrPart1() {
		return roadAddrPart1;
	}

	public String getDetailaddress() {
		return detailaddress;
	}

	@Override
	public String toString() {
		return "JoinForm [id=" + id + ", password=" + password + ", verifyPassword=" + verifyPassword + ", name=" + name
				+ ", nickname=" + nickname + ", mobile=" + mobile + ", email=" + email + ", zipNo=" + zipNo
				+ ", roadAddrPart1=" + roadAddrPart1 + ", detailaddress=" + detailaddress + "]";
	}
	
	

}
