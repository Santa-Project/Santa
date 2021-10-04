package com.kh.santa.mountainInfo.model.dto;

public class MountainWishlist {

	private String mtIdx;
	private String memberIdx;
	private String mtName;
	public String getMtIdx() {
		return mtIdx;
	}
	public void setMtIdx(String mtIdx) {
		this.mtIdx = mtIdx;
	}
	public String getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(String memberIdx) {
		this.memberIdx = memberIdx;
	}
	public String getMtName() {
		return mtName;
	}
	public void setMtName(String mtName) {
		this.mtName = mtName;
	}
	@Override
	public String toString() {
		return "MountainWishlist [mtIdx=" + mtIdx + ", memberIdx=" + memberIdx + ", mtName=" + mtName + "]";
	}
	
	
	
	
	
	
}
