package com.kh.santa.mountainInfo.model.dto;

public class Mountain {
	
	private String mtIdx;
	private String mountainName;
	private String info;
	private String region;
	private int likedMountainCnt;
	private String level;
	private String totalDistance;
	private String totalTime;
	private String traffic;
	private String recommendRoute;
	
	public String getMtIdx() {
		return mtIdx;
	}
	public void setMtIdx(String mtIdx) {
		this.mtIdx = mtIdx;
	}
	public String getMountainName() {
		return mountainName;
	}
	public void setMountainName(String mountainName) {
		this.mountainName = mountainName;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getLikedMountainCnt() {
		return likedMountainCnt;
	}
	public void setLikedMountainCnt(int likedMountainCnt) {
		this.likedMountainCnt = likedMountainCnt;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getTotalDistance() {
		return totalDistance;
	}
	public void setTotalDistance(String totalDistance) {
		this.totalDistance = totalDistance;
	}
	public String getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	public String getTraffic() {
		return traffic;
	}
	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
	public String getRecommendRoute() {
		return recommendRoute;
	}
	public void setRecommendRoute(String recommendRoute) {
		this.recommendRoute = recommendRoute;
	}
	@Override
	public String toString() {
		return "Mountain [mtIdx=" + mtIdx + ", mountainName=" + mountainName + ", info=" + info + ", region=" + region
				+ ", likedMountainCnt=" + likedMountainCnt + ", level=" + level + ", totalDistance=" + totalDistance
				+ ", totalTime=" + totalTime + ", traffic=" + traffic + ", recommendRoute=" + recommendRoute + "]";
	}
	
	
	
	
	
	
	
}
