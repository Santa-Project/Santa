package com.kh.santa.matching.model.dto;

public class ConfirmList {
	
	private String clIdx;
	private String mbIdx;
	private String memberIdx;
	
	public String getClIdx() {
		return clIdx;
	}
	public void setClIdx(String clIdx) {
		this.clIdx = clIdx;
	}
	public String getMbIdx() {
		return mbIdx;
	}
	public void setMbIdx(String mbIdx) {
		this.mbIdx = mbIdx;
	}
	public String getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(String memberIdx) {
		this.memberIdx = memberIdx;
	}
	@Override
	public String toString() {
		return "ConfirmList [clIdx=" + clIdx + ", mbIdx=" + mbIdx + ", memberIdx=" + memberIdx + "]";
	}
	
	

}
