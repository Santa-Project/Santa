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
			<form>
				<input type="text" placeholder="산 또는 키워드 입력입력하세요.">
				<button><i class="fas fa-search"></i></button>
			</form>
		</div>
		<div class="search_nav"></div>
		<div class="level"></div>
		<div class="round_trip_time"></div>
	</div>
	<div class="main">
	<c:if test="true">	<!-- 지역으로 산 찾기(main default) -->
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
				<tbody>
					<tr>
						<td>1</td>
						<td>산 img</td>
						<td>산 이름</td>
						<td colspan="2">산 주소</td>
					</tr>
					
					<tr>
						<td>2</td>
						<td>산 img</td>
						<td>산 이름</td>
						<td colspan="2">산 주소</td>
					</tr>
					
					<tr>
						<td>3</td>
						<td>산 img</td>
						<td>산 이름</td>
						<td colspan="2">산 주소</td>
					</tr>
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
	<c:if test="false">	<!-- ((태마별로 산 찾기)) -->
		<div class="title">태마별로 산 찾기</div>
		<div class="theme_name"></div>
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