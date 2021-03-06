package com.kh.santa.matching.model.dto;

import java.sql.Date;

public class MatchingBoard {

	private String mbIdx;
	private String memberIdx;
	private String mtIdx;
	private String matchStatus;
	private String brdName;
	private Date mtDate;
	private Date brdDate;
	private int memVolume;
	private int matchedMemCnt;
	private String brdContent;
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
	public String getMtIdx() {
		return mtIdx;
	}
	public void setMtIdx(String mtIdx) {
		this.mtIdx = mtIdx;
	}
	public String getMatchStatus() {
		return matchStatus;
	}
	public void setMatchStatus(String matchStatus) {
		this.matchStatus = matchStatus;
	}
	public String getBrdName() {
		return brdName;
	}
	public void setBrdName(String brdName) {
		this.brdName = brdName;
	}
	public Date getMtDate() {
		return mtDate;
	}
	public void setMtDate(Date mtDate) {
		this.mtDate = mtDate;
	}
	public Date getBrdDate() {
		return brdDate;
	}
	public void setBrdDate(Date brdDate) {
		this.brdDate = brdDate;
	}
	public int getMemVolume() {
		return memVolume;
	}
	public void setMemVolume(int memVolume) {
		this.memVolume = memVolume;
	}
	public int getMatchedMemCnt() {
		return matchedMemCnt;
	}
	public void setMatchedMemCnt(int matchedMemCnt) {
		this.matchedMemCnt = matchedMemCnt;
	}
	public String getBrdContent() {
		return brdContent;
	}
	public void setBrdContent(String brdContent) {
		this.brdContent = brdContent;
	}
	@Override
	public String toString() {
		return "MatchingBoard [mbIdx=" + mbIdx + ", memberIdx=" + memberIdx + ", mtIdx=" + mtIdx + ", matchStatus="
				+ matchStatus + ", brdName=" + brdName + ", mtDate=" + mtDate + ", brdDate=" + brdDate + ", memVolume="
				+ memVolume + ", matchedMemCnt=" + matchedMemCnt + ", brdContent=" + brdContent + "]";
	}
	
	
	
	
}