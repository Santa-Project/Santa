package com.kh.santa.main.model.service;


import java.sql.Connection;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.mypage.model.dao.MemberDao;
import com.kh.santa.mypage.model.dto.Member;

public class MainService {

	private JDBCTemplate template = JDBCTemplate.getInstance();
	private MemberDao memberDao = new MemberDao();
	
	public Member memberAuthenticate(String userId, String password) {
		
		Member member = null;
		Connection conn = template.getConnection();
		
		try {
			member = memberDao.memberAuthenticate(userId, password, conn);
		} finally {
			template.close(conn);
		}
		
		return member;
	}

	public Object selectMemberById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
