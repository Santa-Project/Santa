package com.kh.santa.matching.model.dto;

import oracle.sql.DATE;

public class FindingMember {
	
	private String mftIdx;
	private String memberIdx;
	private String chatListIdx;
	private String matchingStatus;
	private String brdName;
	private DATE brdSearch;
	private DATE brdDate;
	private String region;
	public String getMftIdx() {
		return mftIdx;
	}
	public void setMftIdx(String mftIdx) {
		this.mftIdx = mftIdx;
	}
	public String getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(String memberIdx) {
		this.memberIdx = memberIdx;
	}
	public String getChatListIdx() {
		return chatListIdx;
	}
	public void setChatListIdx(String chatListIdx) {
		this.chatListIdx = chatListIdx;
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
	public DATE getBrdSearch() {
		return brdSearch;
	}
	public void setBrdSearch(DATE brdSearch) {
		this.brdSearch = brdSearch;
	}
	public DATE getBrdDate() {
		return brdDate;
	}
	public void setBrdDate(DATE brdDate) {
		this.brdDate = brdDate;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	@Override
	public String toString() {
		return "FindingMember [mftIdx=" + mftIdx + ", memberIdx=" + memberIdx + ", chatListIdx=" + chatListIdx
				+ ", matchingStatus=" + matchingStatus + ", brdName=" + brdName + ", brdSearch=" + brdSearch
				+ ", brdDate=" + brdDate + ", region=" + region + "]";
	}
	
	
	
}
