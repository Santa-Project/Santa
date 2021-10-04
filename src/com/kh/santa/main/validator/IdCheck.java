package com.kh.santa.main.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class IdCheck {
	private String id;
	
	HttpServletRequest request;
	private Map<String,String> failedValidation = new HashMap<String,String>();

	public IdCheck(HttpServletRequest request) {
		this.request = request;
		this.id = request.getParameter("id");
	}
	
	public boolean test() {
		// 아이디 시작은 영문으로만, '_'를 제외한 특수문자 안되며 영문, 숫자, '_'으로만 이루어진 5 ~ 12자 이하
		if(!Pattern.matches("^[a-zA-Z]{1}[a-zA-Z0-9]{4,11}$", id)) {
			failedValidation.put("id",id);
			request.getSession().setAttribute("idValid", failedValidation);
			request.getSession().setAttribute("idCheck", this);
			return false;
		} else {
			request.getSession().removeAttribute("idValid");
			request.getSession().removeAttribute("idCheck");
			return true;
		}

	}
	
	public String getId() {
		return id;
	}
	
}
