package com.kh.santa.mountainInfo.model.dto;

public class MountainWishlist {

	private String mtIdx;
	private String memberIdx;
	private String mountainName;
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
	public String getMountainName() {
		return mountainName;
	}
	public void setMountainName(String mountainName) {
		this.mountainName = mountainName;
	}
	@Override
	public String toString() {
		return "MountainWishlist [mtIdx=" + mtIdx + ", memberIdx=" + memberIdx + ", mountainName=" + mountainName + "]";
	}
	
	
	
}
