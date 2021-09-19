package com.kh.santa.common.code;

public enum ErrorCode {
	
	DATABASE_ACESS_ERROR("데이터베이스와 통신 중 에러가 발생하였습니다."),
	FAILED_VALIDATED_ERROR("데이터의 양식이 적합하지 않습니다."),
	MAIL_SEND_FAIL_ERROR("이메일 발송 중 에러가 발생하였습니다."),
	HTTP_CONNECT_ERROR("HTTP 통신 중 에러가 발생하였습니다."),
	AUTHENTICATION_FAILED_ERROR("유효하지 않은 인증입니다."),
	UNAUTHORIZED_PAGE("접근 권한이 없는 페이지 입니다."),
	UNAUTHORIZED_USER("접근 권한이 없는 사용자 입니다."),
	REDIRECT_PREVIOUS_PAGE_NO_MESSAGE(""),
	REDIRECT_LOGIN_PAGE_NO_MESSAGE("","/main/loginform"),
	REDIRECT_LOGIN_PAGE("로그인 후 이용가능합니다.","/main/loginform"),
	FAILED_FILE_UPLOAD_ERROR("파일업로드에 실패했습니다.");
	
	public final String MSG;
	public final String URL;
	
	ErrorCode(String msg) {
		this.MSG = msg;
		this.URL = "/main";
	}
	
	ErrorCode(String msg, String url) {
		this.MSG = msg;
		this.URL = url;
	}
	
}
