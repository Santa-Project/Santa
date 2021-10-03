package com.kh.santa.mountainInfo.model.dto;

public class MountainHash {
	
	private String tagIdx;
	private String mtIdx;
	
	public String getTagIdx() {
		return tagIdx;
	}
	public void setTagIdx(String tagIdx) {
		this.tagIdx = tagIdx;
	}
	public String getMtIdx() {
		return mtIdx;
	}
	public void setMtIdx(String mtIdx) {
		this.mtIdx = mtIdx;
	}
	
	@Override
	public String toString() {
		return "MountainHash [tagIdx=" + tagIdx + ", mtIdx=" + mtIdx + "]";
	}
	
	
	
}
