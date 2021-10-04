package com.kh.santa.mypage.model.dto;

public class Follow {
	
	private String followIdx;
	private String memberIdx;
	private String followId;
	
	public String getFollowIdx() {
		return followIdx;
	}
	public void setFollowIdx(String followIdx) {
		this.followIdx = followIdx;
	}
	public String getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(String memberIdx) {
		this.memberIdx = memberIdx;
	}
	public String getFollowId() {
		return followId;
	}
	public void setFollowId(String followId) {
		this.followId = followId;
	}
	@Override
	public String toString() {
		return "Follow [followIdx=" + followIdx + ", memberIdx=" + memberIdx + ", followId=" + followId + "]";
	}
	
}
