package com.kh.santa.mypage.model.dto;

import java.sql.Date;

public class MemberBoard {

	private String memBoardIdx;
	private String memberIdx;
	private int liked;
	private Date uploadTime;
	private String mtRegion;
	private String mtName;
	private String boardComment;
	
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
	public int getLiked() {
		return liked;
	}
	public void setLiked(int liked) {
		this.liked = liked;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getMtRegion() {
		return mtRegion;
	}
	public void setMtRegion(String mtRegion) {
		this.mtRegion = mtRegion;
	}
	public String getMtName() {
		return mtName;
	}
	public void setMtName(String mtName) {
		this.mtName = mtName;
	}
	public String getBoardComment() {
		return boardComment;
	}
	public void setBoardComment(String boardComment) {
		this.boardComment = boardComment;
	}
	@Override
	public String toString() {
		return "MemberBoard [memBoardIdx=" + memBoardIdx + ", memberIdx=" + memberIdx + ", liked=" + liked
				+ ", uploadTime=" + uploadTime + ", mtRegion=" + mtRegion + ", mtName=" + mtName + ", boardComment="
				+ boardComment + "]";
	}
	
	
	
	
}
