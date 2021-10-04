package com.kh.santa.mypage.model.dto;

public class LIkedMemberBoard {

	private String likedBoardIdx;
	private String memberIdx;
	private String memBoardIdx;
	public String getLikedBoardIdx() {
		return likedBoardIdx;
	}
	public void setLikedBoardIdx(String likedBoardIdx) {
		this.likedBoardIdx = likedBoardIdx;
	}
	public String getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(String memberIdx) {
		this.memberIdx = memberIdx;
	}
	public String getMemBoardIdx() {
		return memBoardIdx;
	}
	public void setMemBoardIdx(String memBoardIdx) {
		this.memBoardIdx = memBoardIdx;
	}
	@Override
	public String toString() {
		return "LIkedMemberBoard [likedBoardIdx=" + likedBoardIdx + ", memberIdx=" + memberIdx + ", memBoardIdx="
				+ memBoardIdx + "]";
	}
	
	
}
