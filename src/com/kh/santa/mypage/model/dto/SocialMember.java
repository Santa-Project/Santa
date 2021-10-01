package com.kh.santa.mypage.model.dto;

public class SocialMember {
	
	private String socialIdx;
	private String memberIdx;
	private String kakaoId;
	
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
	public String getKakaoId() {
		return kakaoId;
	}
	public void setKakaoId(String kakaoId) {
		this.kakaoId = kakaoId;
	}
	@Override
	public String toString() {
		return "SocialMember [socialIdx=" + socialIdx + ", memberIdx=" + memberIdx + ", kakaoId=" + kakaoId + "]";
	}
	
}
