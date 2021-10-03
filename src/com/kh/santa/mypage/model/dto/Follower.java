package com.kh.santa.mypage.model.dto;

public class Follower {

	private String followerIdx;
	private String memberIdx;
	private String followerId;
	
	public String getFollowerIdx() {
		return followerIdx;
	}
	public void setFollowerIdx(String followerIdx) {
		this.followerIdx = followerIdx;
	}
	public String getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(String memberIdx) {
		this.memberIdx = memberIdx;
	}
	public String getFollowerId() {
		return followerId;
	}
	public void setFollowerId(String followerId) {
		this.followerId = followerId;
	}
	@Override
	public String toString() {
		return "Follower [followerIdx=" + followerIdx + ", memberIdx=" + memberIdx + ", followerId=" + followerId + "]";
	}
	
	
}
