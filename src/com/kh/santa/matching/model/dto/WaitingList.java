package com.kh.santa.matching.model.dto;

public class WaitingList {
	
	private String wlIdx;
	private String mbIdx;
	private String memberIdx;
	
	public String getWlIdx() {
		return wlIdx;
	}
	public void setWlIdx(String wlIdx) {
		this.wlIdx = wlIdx;
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
		return "WaitingList [wlIdx=" + wlIdx + ", mbIdx=" + mbIdx + ", memberIdx=" + memberIdx + "]";
	}
	
	

}
