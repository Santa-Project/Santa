package com.kh.santa.common.code.member;

// enum(enumerated typed) : 열거형
// 서로 연관된 상수들의 집합
// 서로 연관된 상수들을 하나의 묶음으로 다루기 위한 enum만의 문법적 형식과 메서드를 제공해준다.
public enum MemberGrade {
	
	// 회원등급코드가 US00이면 등급명은 'boardMaster'
	
	// 내부적으로 enum은 class이다.
	// US00("boardMaster","user") -> public static final MemberGrade US00 = new MemberGrade("boardMaster","user");
	// public이기 때문에 어디에서나 접근이 가능하고, static이기 때문에 언제나 접근이 가능한 인스턴스에
	US00("generalUser","user"), // 일반유저
	US10("boardMaster","user"), // boardMaster -> 팀 모집 게시물 생성 유저
	US01("fmUser","user"), //fmUser -> 회원찾기 게시물 생성 유저
	US11("boardMaster&fmUser","user"); // boardMaster&fmUser -> 팀 모집 게시물 생성하고 회원찾기 게시물 생성한 유저
	
	public final String DESC;
	public final String ROLE;
	
	private MemberGrade(String desc,String role) {
		this.DESC = desc;
		this.ROLE = role;
	}
	

}
