package com.kh.santa.matching.model.dto;

import java.sql.Date;

public class FindingMember {
	
	private String fmIdx;
	private String memberIdx;
	private String mtIdx;
	private String matchingStatus;
	private String brdName;
	private Date mtDate;
	private Date brdDate;
	
	public String getFmIdx() {
		return fmIdx;
	}
	public void setFmIdx(String fmIdx) {
		this.fmIdx = fmIdx;
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
	public String getMatchingStatus() {
		return matchingStatus;
	}
	public void setMatchingStatus(String matchingStatus) {
		this.matchingStatus = matchingStatus;
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
	@Override
	public String toString() {
		return "FindingMember [fmIdx=" + fmIdx + ", memberIdx=" + memberIdx + ", mtIdx=" + mtIdx + ", matchingStatus="
				+ matchingStatus + ", brdName=" + brdName + ", mtDate=" + mtDate + ", brdDate=" + brdDate + "]";
	}
	
	
	
	
	
}
