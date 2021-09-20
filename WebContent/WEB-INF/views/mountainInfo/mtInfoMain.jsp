<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
<link rel="stylesheet" href="${contextPath}/resources/css/mountainInfo/mt_info_main.css">

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e86272e6f7ebfc43f0ce43288f6e1280"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e86272e6f7ebfc43f0ce43288f6e1280&libraries=services,clusterer,drawing"></script>


</head>
<body>
<!-- <header> -->
<%@ include file="/WEB-INF/views/common/header.jsp" %> 

<section>
	<div class="side">
		<div class="search">
			<form action="/mountainInfo/searchKeyword" method="post">
				<input type="text" placeholder="산 또는 키워드 입력입력하세요.">
				<button><i class="fas fa-search"></i></button>
			</form>
		</div>
		<div class="search_nav">
			<a href="/mountainInfo/searchNearby"><i class="fas fa-angle-double-right"></i> 가까운 산 찾기</a>
			<a href="/mountainInfo/searchByRegion"><i class="fas fa-angle-double-right"></i> 지역으로 산 찾기</a>
			<a href="/mountainInfo/searchByPopularity"><i class="fas fa-angle-double-right"></i> 인기순으로 산 찾기</a>
		</div>
		<form class="form_level_distance" action="/mountainInfo/searchByLevelDistance" method="post">
			<div class="wrap_level">
				<div class="level-title">난이도</div>
				<div class="level-list">
					<label><input type="checkbox" multiple="multiple" name="level" value='top'>상</label>
					<label><input type="checkbox" multiple="multiple" name="level" value='medium'>중</label>
					<label><input type="checkbox" multiple="multiple" name="level" value='low'>하</label>
				</div>
			</div>
			<div class="wrap_round_trip_time">
				<div class="rt-title">왕복</div>
				<div class="rt-list">
					<label><input type="checkbox" multiple="multiple" name="round_trip_time" value='1'>1시간 이내</label>
					<label><input type="checkbox" multiple="multiple" name="round_trip_time" value='2'>2시간 이내</label>
					<label><input type="checkbox" multiple="multiple" name="round_trip_time" value='3'>3시간 이내</label>
				</div>
			</div>
			<div class="submit"><button>검색</button></div>
		</form>
	</div>
	<div class="main">
	<c:if test="true">	<!-- 지역으로 산 찾기(main default) -->
		<!-- 1. 산 위도, 경도 , 지역(구) json
			2. 지도에 구 별로(지역 위경도 json파일) 산 숫자표시
		-->
	
		<div class="title">지역으로 산 찾기</div>
		<div class="map">
			<div id="map"></div>
		</div>
		<div class="hashtag">
			<div>
				<span>#남산</span>
				<span>#야경</span>
				<span>#중구</span>
			</div>
		</div>
		<div class="mountain_list">
			<table class="mt_list_table">
				<tbody class="mtList">
					<c:forEach items="${searchedMtList}" var="mountain" varStatus="status">
						<tr>
							<td><span>${status.index +1}</span></td>
							<td><img src="${contextPath}/resources/img/${mountain.mountainName}.jpg"></td>
							<td><span>${mountain.mountainName}</span></td>
							<td colspan="2"><span>${mountain.region}</span></td>
						</tr>
					</c:forEach>
				</tbody>
				
			</table>
		</div>
	</c:if>
	<c:if test="false">	<!-- 가까운산 찾기(접속위치로) -->
		<div class="title">가까운산 찾기</div>
		<div class="map"></div>
		<div class="hashtag"></div>
		<div class="mountain_list"></div>
	</c:if>
	<c:if test="false">	<!-- 인기순으로 산 찾기 -->
		<div class="title">인기순으로 산 찾기</div>
		<div class="mountain_list"></div>
	</c:if>
	<c:if test="false">	<!-- ((해쉬태그로 산 찾기)) -->
		<div class="title">해쉬태그로 산 찾기</div>
		<div class="hashtag_name"></div>
		<div class="mountain_list"></div>
	</c:if>
	</div>
	
</section>

<!-- <footer> -->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>

<script type="text/javascript">
var container = document.getElementById('map');
var options = {
	center: new kakao.maps.LatLng(37.55138202753834, 126.98824805488653),
	level: 5,
	keyboardShortcuts: true
};

var map = new kakao.maps.Map(container, options);
map.addControl(new kakao.maps.MapTypeControl(),kakao.maps.ControlPosition.BOTTOMLEFT);
map.addControl(new kakao.maps.ZoomControl(),kakao.maps.ControlPosition.BOTTOMRIGHT);

</script>
</body>
</html>