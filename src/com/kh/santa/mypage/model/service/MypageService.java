package com.kh.santa.mypage.model.service;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.mypage.model.dao.MemberDao;

public class MypageService {

	private MemberDao memberDao = new MemberDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
}
