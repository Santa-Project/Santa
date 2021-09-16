package com.kh.santa.mypage.model.dto;

public class Follow {
	
	private String follow_idx;
	private String memberIdx;
	private String follow_id;
	public String getFollow_idx() {
		return follow_idx;
	}
	public void setFollow_idx(String follow_idx) {
		this.follow_idx = follow_idx;
	}
	public String getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(String memberIdx) {
		this.memberIdx = memberIdx;
	}
	public String getFollow_id() {
		return follow_id;
	}
	public void setFollow_id(String follow_id) {
		this.follow_id = follow_id;
	}
	@Override
	public String toString() {
		return "Follow [follow_idx=" + follow_idx + ", memberIdx=" + memberIdx + ", follow_id=" + follow_id + "]";
	}
	
	
	
}
