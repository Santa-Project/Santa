package com.kh.santa.matching.model.dto;

import java.sql.Date;

public class MatchingAlarm implements Comparable<MatchingAlarm> {
	
	private String maIdx;
	private String mbIdx;
	private String msg;
	private Date sendDate;
	private String senderIdx;
	private String rejectedMemIdx;
	
	public String getMaIdx() {
		return maIdx;
	}

	public void setMaIdx(String maIdx) {
		this.maIdx = maIdx;
	}

	public String getMbIdx() {
		return mbIdx;
	}

	public void setMbIdx(String mbIdx) {
		this.mbIdx = mbIdx;
	}

	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getSenderIdx() {
		return senderIdx;
	}

	public void setSenderIdx(String senderIdx) {
		this.senderIdx = senderIdx;
	}

	public String getRejectedMemIdx() {
		return rejectedMemIdx;
	}

	public void setRejectedMemIdx(String rejectedMemIdx) {
		this.rejectedMemIdx = rejectedMemIdx;
	}

	@Override
	public String toString() {
		return "MatchingAlarm [maIdx=" + maIdx + ", mbIdx=" + mbIdx + ", msg=" + msg + ", sendDate=" + sendDate
				+ ", senderIdx=" + senderIdx + ", rejectedMemIdx=" + rejectedMemIdx + "]";
	}

	@Override
	public int compareTo(MatchingAlarm o) {
		if(Integer.parseInt(o.maIdx) != Integer.parseInt(maIdx)) {
			return Integer.parseInt(o.maIdx) - Integer.parseInt(maIdx);
		}
		
		return Integer.parseInt(mbIdx) - Integer.parseInt(o.mbIdx);
		
	}
	
	
	
	
	
	
}
