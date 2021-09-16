package com.kh.santa.mypage.model.dto;

public class MemberBoardHashTag {

	private String tagIdx;
	private String memBoardIdx;
	private String name;
	public String getTagIdx() {
		return tagIdx;
	}
	public void setTagIdx(String tagIdx) {
		this.tagIdx = tagIdx;
	}
	public String getMemBoardIdx() {
		return memBoardIdx;
	}
	public void setMemBoardIdx(String memBoardIdx) {
		this.memBoardIdx = memBoardIdx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "MemberBoardHashTag [tagIdx=" + tagIdx + ", memBoardIdx=" + memBoardIdx + ", name=" + name + "]";
	}
	
	
	
}
