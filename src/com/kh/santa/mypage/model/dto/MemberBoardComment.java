package com.kh.santa.mypage.model.dto;

import oracle.sql.DATE;

public class MemberBoardComment {

	private String commentIdx;
	private String memBoardIdx;
	private String memberIdx;
	private String content;
	private DATE commentDatetime;
	public String getCommentIdx() {
		return commentIdx;
	}
	public void setCommentIdx(String commentIdx) {
		this.commentIdx = commentIdx;
	}
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public DATE getCommentDatetime() {
		return commentDatetime;
	}
	public void setCommentDatetime(DATE commentDatetime) {
		this.commentDatetime = commentDatetime;
	}
	@Override
	public String toString() {
		return "MemberBoardComment [commentIdx=" + commentIdx + ", memBoardIdx=" + memBoardIdx + ", memberIdx="
				+ memberIdx + ", content=" + content + ", commentDatetime=" + commentDatetime + "]";
	}
	
	
	
}
