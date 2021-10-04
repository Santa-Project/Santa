package com.kh.santa.matching.model.dto;


public class MatchingCompleteList {
	
	private String mclIdx;
	private String memberIdx;
	private String mbIdx;
	public String getMclIdx() {
		return mclIdx;
	}
	public void setMclIdx(String mclIdx) {
		this.mclIdx = mclIdx;
	}
	public String getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(String memberIdx) {
		this.memberIdx = memberIdx;
	}
	public String getMbIdx() {
		return mbIdx;
	}
	public void setMbIdx(String mbIdx) {
		this.mbIdx = mbIdx;
	}
	@Override
	public String toString() {
		return "MatchingCompleteList [mclIdx=" + mclIdx + ", memberIdx=" + memberIdx + ", mbIdx=" + mbIdx + "]";
	}
	
	
	
	
}
