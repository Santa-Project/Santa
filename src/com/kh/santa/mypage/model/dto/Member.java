package com.kh.santa.mypage.model.dto;

import java.sql.Date;

public class Member {

	private String memberIdx;
	private String memUserid;
	private String memEmail;
	private String memPassword;
	private String memUsername;
	private String memNickname;
	private String memPhone;
	private String memSex;
	private String memAddress;
	private Date memRegisterDatetime;
	private String memProfileContent;
	private String memPhoto;
	private String socialLogin;
	private String grade;
	public String getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(String memberIdx) {
		this.memberIdx = memberIdx;
	}
	public String getMemUserid() {
		return memUserid;
	}
	public void setMemUserid(String memUserid) {
		this.memUserid = memUserid;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemPassword() {
		return memPassword;
	}
	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}
	public String getMemUsername() {
		return memUsername;
	}
	public void setMemUsername(String memUsername) {
		this.memUsername = memUsername;
	}
	public String getMemNickname() {
		return memNickname;
	}
	public void setMemNickname(String memNickname) {
		this.memNickname = memNickname;
	}
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public String getMemSex() {
		return memSex;
	}
	public void setMemSex(String memSex) {
		this.memSex = memSex;
	}
	public String getMemAddress() {
		return memAddress;
	}
	public void setMemAddress(String memAddress) {
		this.memAddress = memAddress;
	}
	public Date getMemRegisterDatetime() {
		return memRegisterDatetime;
	}
	public void setMemRegisterDatetime(Date memRegisterDatetime) {
		this.memRegisterDatetime = memRegisterDatetime;
	}
	public String getMemProfileContent() {
		return memProfileContent;
	}
	public void setMemProfileContent(String memProfileContent) {
		this.memProfileContent = memProfileContent;
	}
	public String getMemPhoto() {
		return memPhoto;
	}
	public void setMemPhoto(String memPhoto) {
		this.memPhoto = memPhoto;
	}
	public String getSocialLogin() {
		return socialLogin;
	}
	public void setSocialLogin(String socialLogin) {
		this.socialLogin = socialLogin;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "Member [memberIdx=" + memberIdx + ", memUserid=" + memUserid + ", memEmail=" + memEmail
				+ ", memPassword=" + memPassword + ", memUsername=" + memUsername + ", memNickname=" + memNickname
				+ ", memPhone=" + memPhone + ", memSex=" + memSex + ", memAddress=" + memAddress
				+ ", memRegisterDatetime=" + memRegisterDatetime + ", memProfileContent=" + memProfileContent
				+ ", memPhoto=" + memPhoto + ", socialLogin=" + socialLogin + ", grade=" + grade + "]";
	}
	
	
	
}
