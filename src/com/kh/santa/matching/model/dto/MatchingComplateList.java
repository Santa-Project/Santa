package com.kh.santa.matching.model.dto;

public class MatchingComplateList {
	
	private String listIdx;
	private String memberIdx;
	private String mbIdx;
	public String getListIdx() {
		return listIdx;
	}
	public void setListIdx(String listIdx) {
		this.listIdx = listIdx;
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
		return "MatchingComplateList [listIdx=" + listIdx + ", memberIdx=" + memberIdx + ", mbIdx=" + mbIdx + "]";
	}
	
	
	
}
