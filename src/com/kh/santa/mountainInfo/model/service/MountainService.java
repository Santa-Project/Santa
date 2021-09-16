package com.kh.santa.mountainInfo.model.service;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.mountainInfo.model.dao.MountainDao;

public class MountainService {

	private MountainDao mountainDao = new MountainDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	
	
}

