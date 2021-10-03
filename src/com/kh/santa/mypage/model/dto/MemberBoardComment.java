package com.kh.santa.mypage.model.dto;

import java.sql.Date;


public class MemberBoardComment {

	private String commentIdx;
	private String nickname;
	private String memberIdx;
	private String memBoardIdx;
	private String content;
	private Date commentDatetime;
	
	public String getCommentIdx() {
		return commentIdx;
	}
	public void setCommentIdx(String commentIdx) {
		this.commentIdx = commentIdx;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(String memberIdx) {
		this.memberIdx = memberIdx;
	}
	public String getMemBoardIdx() {
		return memBoardIdx;
	}
	public void setMemBoardIdx(String memBoardIdx) {
		this.memBoardIdx = memBoardIdx;
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
		return "MemberBoardComment [commentIdx=" + commentIdx + ", nickname=" + nickname + ", memberIdx=" + memberIdx
				+ ", memBoardIdx=" + memBoardIdx + ", content=" + content + ", commentDatetime=" + commentDatetime
				+ "]";
	}
	
	
	
}
