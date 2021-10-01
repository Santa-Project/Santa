package com.kh.santa.matching.model.dto;

import oracle.sql.DATE;

public class MatchingAlarm {
	
	private String maIdx;
	private String mbIdx;
	private String msg;
	private DATE time;
	private String sender;
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
	public DATE getTime() {
		return time;
	}
	public void setTime(DATE time) {
		this.time = time;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	@Override
	public String toString() {
		return "MatchingAlarm [maIdx=" + maIdx + ", mbIdx=" + mbIdx + ", msg=" + msg + ", time=" + time + ", sender="
				+ sender + "]";
	}
	
	
	
	
	
}
