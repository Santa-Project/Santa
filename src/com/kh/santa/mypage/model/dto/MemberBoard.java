package com.kh.santa.mypage.model.dto;

import java.sql.Date;

import oracle.sql.DATE;

public class MemberBoard {

	private String boardIdx;
	private String memberIdx;
	private int liked;
	private Date uploadDatetime;
	private String mtRegion;
	private String mtMountain;
	private String boardComment;
	public String getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(String boardIdx) {
		this.boardIdx = boardIdx;
	}
	public String getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(String memberIdx) {
		this.memberIdx = memberIdx;
	}

	public int getLiked() {
		return liked;
	}
	public void setLiked(int liked) {
		this.liked = liked;
	}
	public Date getUploadDatetime() {
		return uploadDatetime;
	}
	public void setUploadDatetime(Date uploadDatetime) {
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
	public String getBoardComment() {
		return boardComment;
	}
	public void setBoardComment(String boardComment) {
		this.boardComment = boardComment;
	}
	@Override
	public String toString() {
		return "MemberBoard [boardIdx=" + boardIdx + ", memberIdx=" + memberIdx + 
				 ", liked=" + liked + ", uploadDatetime=" + uploadDatetime + ", mtRegion=" + mtRegion + ", mtMountain="
				+ mtMountain + ", boardComment=" + boardComment + "]";
	}
	
	
	
	
}
