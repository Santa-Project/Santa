<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
<link rel="stylesheet" href="${contextPath}/resources/css/mountainInfo/mt_info_detail.css">
<link rel="stylesheet" href="${contextPath}/resources/css/mountainInfo/imageslider.css">

<meta charset="UTF-8">
<title>산정보 상세정보</title>

<script src="${contextPath}/resources/libs/jquery-3.5.1.min.js"></script>
<script src="${contextPath}/resources/libs/jquery.easing.1.3.js"></script>
<script src="${contextPath}/resources/js/imageslider.js"></script>

<!-- 이미지 슬라이더 스크립트 -->
<script>
$(document).ready(function() {
	
	var $imageSlider = $("#imageSlider1").imageSlider({
		startIndex : 4
	});	

	
	$imageSlider.on("change", function(event){
		console.log("event.oldIndex : " + event.oldIndex + ", event.newIndex : " + event.newIndex);
	});
});	
</script>

<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

<!-- 이미지슬라이더 css -->
<style type="text/css">
.image-slider .slider-body ul.index-nav li a {
    zoom : 0.7;
    float: left;
    font-size: 14pt;
    width: 29px;
    height: 27px;
    line-height: 27px;
    text-align: center;
    vertical-align: middle;
    text-decoration: none;
    background: url("../resources/img/mountaininfo/button_dot.png") no-repeat;
}
.image-slider .slider-body ul.index-nav li a.select {
    margin-top : -15px;
    background: url("../resources/img/mountaininfo/button_dot.png"") no-repeat 0 -27px;
}
</style>

</head>

<body>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<div class="section">
	<div class="wrap_search">
		<form class="search" action="/mountainInfo/mtInfoDetail" method="get">	
			<input type="text" placeholder="산 또는 키워드 입력입력하세요."name="searchinput">
			<button><i class="fas fa-search"></i></button>
		</form>
	</div>
	
<%@include file="dbconn.jsp" %>
	<% String searchinput = request.getParameter("searchinput");
				if(searchinput == null) {
						searchinput = "개화산";
					}
	%>
		
	<%
		ResultSet rs = null;   
		PreparedStatement pstmt = null;
		
		String mname = null;	
		String mmap = null;
		String mlevel = null;
		String mhigh = null;
		String info = null;
		String dinfo = null;
		String traffic = null;
		String trip = null;
		String img = null;
		String rimg1 = null;
		String rimg2 = null;
		String rimg3 = null;
		String rimg4 = null;
		
		String[] hashtagcategory = new String[100]; //mhashcategory카테고리
		int cnt = 0;
		 
		try{
			//mhashtag에 산테이블의 mname 기본값으로 들어가야함(db에서 지우지 말 것)
			String sql = "select  *  from MOUNTAIN a full outer join MOUNTAIN_HASHTAG b on a.MT_NAME = b.MT_NAME where a.MT_NAME=b.MT_NAME and a.MT_NAME='" + searchinput + "'"; 
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mname = rs.getString("MT_NAME");
				mmap = rs.getString("MT_MAP");
				mlevel = rs.getString("MT_LEVEL");	
				mhigh = rs.getString("MT_HIGH");
				info = rs.getString("MT_INFO");	
				dinfo = rs.getString("MT_DETAIL_INFO");
				traffic = rs.getString("MT_TRAFFIC");	
				trip = rs.getString("MT_TRIP");	
				img = rs.getString("MT_IMG");	
				rimg1 = rs.getString("MT_SLIDE_IMG1");
				rimg2 = rs.getString("MT_SLIDE_IMG2");
				rimg3 = rs.getString("MT_SLIDE_IMG3");
				rimg4 = rs.getString("MT_SLIDE_IMG4");
				
				hashtagcategory[cnt] = rs.getString("HASHTAG");
				cnt++;
	%>
			
	<%
			}
		}catch(SQLException e){
			out.println("MOUNTAIN테이블 select오류가 발생했습니다.");
			out.println("SQLException : " + e.getMessage());
			e.printStackTrace();
		}finally{
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(connection != null) connection.close();				
		}
		
		for(int i=0;i<hashtagcategory.length;i++) {
			if(hashtagcategory[i] == null){
			hashtagcategory[i] = "";
			}
		} 
	%>	

	<div class="title">
		<div class="mt_name"><h1><%=mname  %></h1></div>
		<div class="like">
        <c:if test="${not empty authentication}">
        <!--click event 발생 시 ♡ 또는 ♥ 로 변경
        /mtInfo/like?like=like or /mtInfo/like?like=dislike 로 변경 -->
        <c:if test="${!sessionScope.like}">
           <i class="far fa-heart" id="blackHeart" ></i>
        </c:if>
        <c:if test="${sessionScope.like}">
           <i class="fas fa-heart" id="redHeart"></i>
        </c:if>
		</c:if>
     	</div>
	</div>
	
	<div class="content_top">
		<div class="text_content">
			<p>  <%=info  %></p><br><br>
			<span><b>상세정보</b></span> <p>:  <%=dinfo  %></p><br><br>
			<span><b>추천 트립</b></span> <p>: <%=trip  %></p><br><br>
			<span><b>교통 정보</b></span> <p>: <%=traffic  %></p>
		</div>
		<div class="other_content">
			<img src="${contextPath}/resources<%=img %>"/><br>
			<form action="/mountainInfo/inserthashtag" method="get">
				<select name = "mname">
					<option  name = "mname" value = "<%=mname  %>"><%=mname %></option>
				</select>
				<input type="text" name="hashtag" size = '5'  placeholder="해쉬태그"/>
				<button class = "submitname" type="submit" name="<%=mname %>" onclick = showdetail()><i class="fas fa-arrow-circle-right"></i></button>
				
			</form>	
			<br>
			#<%=hashtagcategory[0]  %>&nbsp;&nbsp;#<%=hashtagcategory[1]  %>&nbsp;&nbsp;#<%=hashtagcategory[2]  %>&nbsp;&nbsp;#<%=hashtagcategory[3]  %>&nbsp;&nbsp;#<%=hashtagcategory[4]  %>&nbsp;&nbsp;
			</div>
	</div>
		
<script>
	var imgname = document.querySelector(".img").name;
	function showdetail(){
	location.href = 'http://localhost:7070/mountainInfo/mtInfoDetail?searchinput=' + imgname;
	}
</script>
		
		
		
	<div class="content_bottom">
		<div class="recommended_trip">
			<div class="rt_title">산 위치</div>
			<div class="wrap_map">
				<div id="map" style="width:85%;height:320px;"></div>
						
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bbda277c882224ab79cec03a792fe074"></script>
<script>
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = { 
	        center: new kakao.maps.LatLng(<%=mmap  %>), // 지도의 중심좌표
	        level: 10 // 지도의 확대 레벨
	    };
	
	var map = new kakao.maps.Map(mapContainer, mapOption);
	
	// 마커가 표시될 위치입니다 
	var markerPosition  = new kakao.maps.LatLng(<%=mmap  %>); 
	
	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
	    position: markerPosition
	});
	
	// 마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);
	
	var iwContent = '<div style="padding:5px;"><%=mname  %>!<br>고도 : <%=mhigh %>m<br><a href="https://map.kakao.com/link/map/Hello World!,33.450701,126.570667" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/Hello World!,33.450701,126.570667" style="color:blue" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
	    iwPosition = new kakao.maps.LatLng(<%=mmap  %>); //인포윈도우 표시 위치입니다
	
	// 인포윈도우를 생성합니다
	var infowindow = new kakao.maps.InfoWindow({
	    position : iwPosition, 
	    content : iwContent 
	});
	  
	// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
	 infowindow.open(map, marker); 
			
</script>		
			</div>
		</div>
			
			
<!-- 슬라이드 구현 -->
	<div class="photos"> 
		<div class="photos_title">Photos</div>
			<div class="image-slider" id="imageSlider1">
				<div class="slider-body">
					<div class="image-list">
						<img src="${contextPath}/resources<%=rimg1%>" alt="산 이미지">
						<img src="${contextPath}/resources<%=rimg2%>" alt="산 이미지">
						<img src="${contextPath}/resources<%=rimg3%>" alt="산 이미지">
						<img src="${contextPath}/resources<%=rimg4%>" alt="산 이미지">
					</div>
					<ul class="index-nav">
						<li><a href="" alt="1">1</a></li>
						<li><a href="" alt="2">2</a></li>
						<li><a href="" alt="3">3</a></li>
						<li><a href="" alt="4">4</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>


<script type="text/javascript">
	(function(){
	
		var form = document.createElement("form");
		form.setAttribute("method", "post");  //get 방식
		document.body.appendChild(form);
	
		if("${not empty authentication}"){
			document.querySelector(".like").addEventListener('click',function(){
	
				if("${sessionScope.like}"){
					form.setAttribute("action", `/mountainInfo/like?like=${!sessionScope.like}`); //요청 보낼 주소
					form.submit();
	
				} else {
					form.setAttribute("action", `/mountainInfo/like?like=${sessionScope.like}`); //요청 보낼 주소
					form.submit();
				}
	
			})
		}
		})

	})();
</script>
</div>
</body>

</html>