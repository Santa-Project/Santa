package com.kh.santa.matching.model.dto;

import java.sql.Date;

public class OpenRoom {
	private String leader;
	private String room_Title;
	private String room_Content;
	private Date mt_Date;
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getRoom_Title() {
		return room_Title;
	}
	public void setRoom_Title(String room_Title) {
		this.room_Title = room_Title;
	}
	public String getRoom_Content() {
		return room_Content;
	}
	public void setRoom_Content(String room_Content) {
		this.room_Content = room_Content;
	}
	public Date getMt_Date() {
		return mt_Date;
	}
	public void setMt_Date(Date mt_Date) {
		this.mt_Date = mt_Date;
	}
	
	
	
	
	
}
