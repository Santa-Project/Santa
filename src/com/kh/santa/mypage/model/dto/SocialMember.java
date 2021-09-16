package com.kh.santa.mypage.model.dto;

public class SocialMember {
	
	private String socialIdx;
	private String memberIdx;
	private String snsNickname;
	private String snsPhoto;
	private String snsPhone;
	public String getSocialIdx() {
		return socialIdx;
	}
	public void setSocialIdx(String socialIdx) {
		this.socialIdx = socialIdx;
	}
	public String getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(String memberIdx) {
		this.memberIdx = memberIdx;
	}
	public String getSnsNickname() {
		return snsNickname;
	}
	public void setSnsNickname(String snsNickname) {
		this.snsNickname = snsNickname;
	}
	public String getSnsPhoto() {
		return snsPhoto;
	}
	public void setSnsPhoto(String snsPhoto) {
		this.snsPhoto = snsPhoto;
	}
	public String getSnsPhone() {
		return snsPhone;
	}
	public void setSnsPhone(String snsPhone) {
		this.snsPhone = snsPhone;
	}
	@Override
	public String toString() {
		return "SocialMember [socialIdx=" + socialIdx + ", memberIdx=" + memberIdx + ", snsNickname=" + snsNickname
				+ ", snsPhoto=" + snsPhoto + ", snsPhone=" + snsPhone + "]";
	}
	
	
}
