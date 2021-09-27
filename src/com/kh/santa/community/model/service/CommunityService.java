package com.kh.santa.community.model.service;

import java.sql.Connection;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.mypage.model.dao.FollowerDao;
import com.kh.santa.mypage.model.dao.MemberDao;
import com.kh.santa.mypage.model.dto.Member;

public class CommunityService {
	
	private JDBCTemplate template = JDBCTemplate.getInstance();
	private MemberDao memberDao = new MemberDao();

	public Member[] selectPopularMember() {
		Member[] memberArr;
		Connection conn = template.getConnection();
		
		try {
			
			memberArr = memberDao.selectMemberTop10(conn);
		
		} finally {
			
			template.close(conn);
		
		}

		
		
		
		return memberArr;
	}
	
}
