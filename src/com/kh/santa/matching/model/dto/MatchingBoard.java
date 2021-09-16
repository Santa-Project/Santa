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
	private int memberVolume;
	private int matchedMemCnt;
	
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
	public int getMemberVolume() {
		return memberVolume;
	}
	public void setMemberVolume(int memberVolume) {
		this.memberVolume = memberVolume;
	}
	public int getMatchedMemCnt() {
		return matchedMemCnt;
	}
	public void setMatchedMemCnt(int matchedMemCnt) {
		this.matchedMemCnt = matchedMemCnt;
	}
	@Override
	public String toString() {
		return "MatchingBoard [mbIdx=" + mbIdx + ", memberIdx=" + memberIdx + ", mtIdx=" + mtIdx + ", matchStatus="
				+ matchStatus + ", brdName=" + brdName + ", mtDate=" + mtDate + ", brdDate=" + brdDate
				+ ", memberVolume=" + memberVolume + ", matchedMemCnt=" + matchedMemCnt + "]";
	}
	
	
	
	
}
