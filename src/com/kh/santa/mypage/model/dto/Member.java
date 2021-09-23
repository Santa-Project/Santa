package com.kh.santa.mypage.model.dto;

import java.sql.Date;

public class Member {

	private String memberIdx;
	private String userId;
	private String email;
	private String userPassword;
	private String username;
	private String nickname;
	private String phone;
	private String gender;
	private String address;
	private Date registerDatetime;
	private String profileContent;
	private String photo;
	private String socialLogin;
	private String grade;
	public String getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(String memberIdx) {
		this.memberIdx = memberIdx;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getRegisterDatetime() {
		return registerDatetime;
	}
	public void setRegisterDatetime(Date registerDatetime) {
		this.registerDatetime = registerDatetime;
	}
	public String getProfileContent() {
		return profileContent;
	}
	public void setProfileContent(String profileContent) {
		this.profileContent = profileContent;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
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
		return "Member [memberIdx=" + memberIdx + ", userId=" + userId + ", email=" + email + ", userPassword=" + userPassword
				+ ", username=" + username + ", nickname=" + nickname + ", phone=" + phone + ", gender=" + gender
				+ ", address=" + address + ", registerDatetime=" + registerDatetime + ", profileContent="
				+ profileContent + ", photo=" + photo + ", socialLogin=" + socialLogin + ", grade=" + grade + "]";
	}
	

	
	
	
	
}
