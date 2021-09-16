package com.kh.santa.mountainInfo.model.dto;

public class MountainHash {
	
	private String tagIdx;
	private String mtIdx;
	private String name;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "MountainHash [tagIdx=" + tagIdx + ", mtIdx=" + mtIdx + ", name=" + name + "]";
	}
	
	
	
}
