package com.kh.santa.mypage.model.dto;

import java.sql.Date;


public class MemberBoardComment {

	private String commentIdx;
	private String boardIdx;
	private String memberIdx;
	private String content;
	private Date commentDatetime;
	private String userId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCommentIdx() {
		return commentIdx;
	}
	public void setCommentIdx(String commentIdx) {
		this.commentIdx = commentIdx;
	}
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCommentDatetime() {
		return commentDatetime;
	}
	public void setCommentDatetime(Date commentDatetime) {
		this.commentDatetime = commentDatetime;
	}
	@Override
	public String toString() {
		return "MemberBoardComment [commentIdx=" + commentIdx + ", boardIdx=" + boardIdx + ", memberIdx="
				+ memberIdx + ", content=" + content + ", commentDatetime=" + commentDatetime + ", userId=" + userId
				+ "]";
	}
	
	
}
