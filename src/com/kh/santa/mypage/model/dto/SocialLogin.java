package com.kh.santa.mypage.model.dto;

public class SocialLogin {
	
	private String slIdx;
	private String memberIdx;
	private String kakaoId;
	
	public String getSlIdx() {
		return slIdx;
	}
	public void setSlIdx(String slIdx) {
		this.slIdx = slIdx;
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
		return "SocialMember [slIdx=" + slIdx + ", memberIdx=" + memberIdx + ", kakaoId=" + kakaoId + "]";
	}
	
	
	
}
