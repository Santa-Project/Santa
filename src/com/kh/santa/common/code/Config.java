package com.kh.santa.common.code;

public enum Config {
	
	//DOMAIN("http://www.pclass.com"),
	DOMAIN("http://localhost:7070"),
	SMTP_AUTHENTICATION_ID("projectteamyong@gmail.com"),
	SMTP_AUTHENTICATION_PASSWORD("santa1234!"),
	//COMPANY_EMAIL("projectteamyong.gmail.com"), //공통이메일
	//UPLOAD_PATH("C:\\CODE\\upload\\"); 운영서버
	UPLOAD_PATH("C:\\CODE\\upload\\"); //개발서버
	
	public final String DESC;
	
	private Config(String desc) {
		this.DESC = desc;
	}

}
