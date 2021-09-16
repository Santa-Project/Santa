package com.kh.santa.matching.model.dto;

import oracle.sql.DATE;

public class MatchingBoardComment {
	
	private String comIdx;
	private String mbIdx;
	private String content;
	private DATE rdate;
	
	public String getComIdx() {
		return comIdx;
	}
	public void setComIdx(String comIdx) {
		this.comIdx = comIdx;
	}
	public String getMbIdx() {
		return mbIdx;
	}
	public void setMbIdx(String mbIdx) {
		this.mbIdx = mbIdx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public DATE getRdate() {
		return rdate;
	}
	public void setRdate(DATE rdate) {
		this.rdate = rdate;
	}
	@Override
	public String toString() {
		return "MatchingBoardComment [comIdx=" + comIdx + ", mbIdx=" + mbIdx + ", content=" + content + ", rdate="
				+ rdate + "]";
	}
	
	
	
}
