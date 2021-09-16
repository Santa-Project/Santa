package com.kh.santa.mypage.model.dto;

import oracle.sql.DATE;

public class MemberBoard {
	
	private String memBoardIdx;
	private String memberIdx;
	private String picture;
	private String tagIdx;
	private int liked;
	private DATE uploadDatetime;
	private String mtRegion;
	private String mtMountain;
	
	public String getMemBoardIdx() {
		return memBoardIdx;
	}
	public void setMemBoardIdx(String memBoardIdx) {
		this.memBoardIdx = memBoardIdx;
	}
	public String getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(String memberIdx) {
		this.memberIdx = memberIdx;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getTagIdx() {
		return tagIdx;
	}
	public void setTagIdx(String tagIdx) {
		this.tagIdx = tagIdx;
	}
	public int getLiked() {
		return liked;
	}
	public void setLiked(int liked) {
		this.liked = liked;
	}
	public DATE getUploadDatetime() {
		return uploadDatetime;
	}
	public void setUploadDatetime(DATE uploadDatetime) {
		this.uploadDatetime = uploadDatetime;
	}
	public String getMtRegion() {
		return mtRegion;
	}
	public void setMtRegion(String mtRegion) {
		this.mtRegion = mtRegion;
	}
	public String getMtMountain() {
		return mtMountain;
	}
	public void setMtMountain(String mtMountain) {
		this.mtMountain = mtMountain;
	}
	@Override
	public String toString() {
		return "MemberBoard [memBoardIdx=" + memBoardIdx + ", memberIdx=" + memberIdx + ", picture=" + picture
				+ ", tagIdx=" + tagIdx + ", liked=" + liked + ", uploadDatetime=" + uploadDatetime + ", mtRegion="
				+ mtRegion + ", mtMountain=" + mtMountain + "]";
	}
	
	
	
	
}
