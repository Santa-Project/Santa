package com.kh.santa.mountainInfo.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.santa.common.db.JDBCTemplate;
import com.kh.santa.mountainInfo.model.dao.MountainDao;
import com.kh.santa.mountainInfo.model.dto.Mountain;

public class MountainService {

	private MountainDao mountainDao = new MountainDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	public Mountain searchMountain(String mtIdx) {
		Mountain mountain = null;
		Connection conn = template.getConnection();
		
		try {
			
			mountain = mountainDao.selectMountainBymtIdx(mtIdx,conn);

		} finally {
			template.close(conn);
		}

		return mountain;
	}

	public Boolean checkMountainWishlist(String memberIdx, String mtIdx) {
		Connection conn = template.getConnection();

		try {

			return mountainDao.checkMountainWishlist(memberIdx, mtIdx, conn);

		} finally {
			template.close(conn);
		}

	}

	public void addMountainWishlist(String memberIdx, Mountain mountain) {
		Connection conn = template.getConnection();

		try {
			mountainDao.insertMountainWishlist(memberIdx, mountain, conn);
			mountain = mountainDao.selectMountainBymtIdx(mountain.getMtIdx(), conn);
			int addLike = mountain.getLikedMountainCnt() + 1;
			mountainDao.updateMountainLike(addLike, mountain, conn);
			System.out.println(addLike);
			template.commit(conn);
		} catch(Exception e){
			template.rollback(conn);
			throw e;
		}finally {
			template.close(conn);
		}
  
	}

	public void removeMountainWishlist(String memberIdx, Mountain mountain) {
		Connection conn = template.getConnection();

		try {

			mountainDao.deleteMountainWishlist(memberIdx, mountain.getMtIdx(), conn);
			mountain = mountainDao.selectMountainBymtIdx(mountain.getMtIdx(), conn);
			int minusLike = mountain.getLikedMountainCnt() - 1;
			mountainDao.updateMountainLike(minusLike, mountain, conn);
			System.out.println(minusLike);
			template.commit(conn);
		} catch(Exception e){
			template.rollback(conn);
			throw e;
		}finally {
			template.close(conn);
		}
	}

	public List<Mountain> searchAllMtIdxAndMtName() {
		List<Mountain> mountainList = null;
		Connection conn = template.getConnection();
		
		try {
			
			mountainList = mountainDao.searchAllMtIdxAndMtName(conn);

		} finally {
			template.close(conn);
		}

		return mountainList;
	}
}

