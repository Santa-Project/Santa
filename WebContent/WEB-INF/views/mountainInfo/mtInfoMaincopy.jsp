<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
<link rel="stylesheet" href="${contextPath}/resources/css/mountainInfo/mtinfomain.css">

<meta charset="UTF-8">
<title>산정보 메인</title>
</head>

<body>
<!-- <header> -->
<%@ include file="/WEB-INF/views/common/header.jsp" %> 
<div class = "allwrap">	
	<div class="side">
		<form action="/mountainInfo/mtInfoMain" method="get">	
			
			<!-- 검색창 -->
			<div class="search">
					<input  type="text" placeholder="산이름을 입력입력하세요."name="searchinput">
					<button type = "submit" ><i class="fas fa-search"></i></button>
			</div>
			
	
			<div class="wraplevel">
				<div class="level-title">난이도</div>
				<div class="level-list">
					<input type="radio" name="melvel" value="상급">&nbsp;상급<br><br>
	            	<input type="radio" name="melvel" value="중급">&nbsp;중급<br><br>	
	            	<input type="radio" name="melvel" value="초급">&nbsp;초급<br><br>
				</div>
			</div>
			
			<div class="wraptime">
				<div class="rt-title">왕복</div>
				<div class="rt-list">
					<input type="radio" name="mtime" value="1시간 이상">&nbsp;1시간 이상<br><br>
	            	<input type="radio" name="mtime" value="3시간 이상">&nbsp;3시간 이상<br><br>
	            	<input type="radio" name="mtime" value="5시간 이상">&nbsp;5시간 이상<br><br>
	            	<input type="radio" name="mtime" value="7시간 이상">&nbsp;7시간 이상<br><br>
				</div>
			</div>
		</form>
	</div>
	
<!-- 디비 접속 -->	
<%@include file="dbconn.jsp" %>  
<div class="contents">
	<table>
		<tr>
			<th width="100" align="center">산이름</th>
			<th width="350" align="center">지역</th>
			<th width="100" align="center">고도</th>
			<th width="100" align="center">등반수준</th>
			<th width="100" align="center">시간</th>
			<th width="400" align="center">이미지</th>	
		</tr>
	
		<%String searchinput = request.getParameter("searchinput");
		if(searchinput == null) {
			searchinput = "개화산";
		}
		String melvel = request.getParameter("melvel");
		String mtime = request.getParameter("mtime");
		%>
		
		
		<%
			ResultSet rs = null;  
			PreparedStatement pstmt = null;
			
			String mname = null;
			String mregion = null;
			String mhigh = null;
			String mlevel = null;
			String img = null;
			String mmap = null;
			
			String[] category = new String[100];  //좌표 카테고리
			String[] categorym = new String[100]; //manme카테고리
			int cnt = 0;		
			
			try{
				String sql = "select * from MOUNTAIN where MNAME='" + searchinput + "' or MLEVEL='" + melvel + "' or MTIME='" + mtime + "'";
				pstmt = connection.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					mname = rs.getString("MNAME");	
					mregion = rs.getString("REGION");	
					mhigh = rs.getString("MHIGH");
					mlevel = rs.getString("MLEVEL");	
					mtime = rs.getString("MTIME");	
					img = rs.getString("IMG");	
					mmap = rs.getString("MMAP");
							
					category[cnt] = rs.getString("MMAP");
					categorym[cnt] = rs.getString("MNAME");
					cnt++;
					
		%>
		<tr>
			<td align="center"><%=mname  %></td>
			<td align="center"><%=mregion  %></td>
			<td align="center"><%=mhigh  %>m</td>
			<td align="center"><%=mlevel %></td>
			<td align="center"><%=mtime %></td>
			<td height="20"><a onclick = showPrice()><img class = "img" src="${contextPath}/resources<%=img %>" name = <%=mname  %>></a></td>
		</tr>
		
		
		
		<script>
		var imgname = document.querySelector(".img").name;
		function showPrice(){
		location.href = 'http://localhost:7070/santatest/mountainInfo/mtInfoDetail?searchinput=' + imgname;
		}
		</script>
		
		
		
		<%
					}
				
			}catch(SQLException e){
				out.println("members테이블 select오류가 발생했습니다.");
				out.println("SQLException : " + e.getMessage());
				e.printStackTrace();
			}finally{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(connection != null) connection.close();				
			}
		%>	
	</table>
	
<br><br><br><br>
<div id="map"></div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bbda277c882224ab79cec03a792fe074"></script>
	<script>

	var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
    mapOption = { 
        center: new kakao.maps.LatLng(<%=category[0]%>), // 지도의 중심좌표
        level: 11 // 지도의 확대 레벨
    };

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
 
	// 마커를 표시할 위치와 title 객체 배열입니다 
	var positions = [
	
	
 	 {
 		content: '<div><%=categorym[0]%></div>',
        latlng: new kakao.maps.LatLng(<%=category[0]%>)
    }, 
     {
    	content: '<div><%=categorym[1]%></div>',
        latlng: new kakao.maps.LatLng(<%=category[1]%>)
    },
    {
    	content: '<div><%=categorym[2]%></div>',
        latlng: new kakao.maps.LatLng(<%=category[2]%>)
    },
    {
    	content: '<div><%=categorym[3]%></div>',
        latlng: new kakao.maps.LatLng(<%=category[3]%>)
    },
    {
    	content: '<div><%=categorym[4]%></div>',
        latlng: new kakao.maps.LatLng(<%=category[4]%>)
    },
    {
    	content: '<div><%=categorym[5]%></div>',
        latlng: new kakao.maps.LatLng(<%=category[5]%>)
    },
    {
    	content: '<div><%=categorym[6]%></div>',
        latlng: new kakao.maps.LatLng(<%=category[6]%>)
    },
    {
    	content: '<div><%=categorym[7]%></div>',
        latlng: new kakao.maps.LatLng(<%=category[7]%>)
    },
    {
    	content: '<div><%=categorym[8]%></div>',
        latlng: new kakao.maps.LatLng(<%=category[8]%>)
    },
    {
    	content: '<div><%=categorym[9]%></div>',
        latlng: new kakao.maps.LatLng(<%=category[9]%>)
    },
    {
    	content: '<div><%=categorym[10]%></div>',
        latlng: new kakao.maps.LatLng(<%=category[10]%>)
    },
    {
    	content: '<div><%=categorym[11]%></div>',
        latlng: new kakao.maps.LatLng(<%=category[11]%>)
    },
    {
    	content: '<div><%=categorym[12]%></div>',
        latlng: new kakao.maps.LatLng(<%=category[12]%>)
    },
    {
    	content: '<div><%=categorym[13]%></div>',
        latlng: new kakao.maps.LatLng(<%=category[13]%>)
    },
    {
    	content: '<div><%=categorym[14]%></div>',
        latlng: new kakao.maps.LatLng(<%=category[14]%>)
    },
    {
    	content: '<div><%=categorym[15]%></div>',
        latlng: new kakao.maps.LatLng(<%=category[15]%>)
    },
    {
    	content: '<div><%=categorym[16]%></div>',
        latlng: new kakao.maps.LatLng(<%=category[16]%>)
    },
    {
    	content: '<div><%=categorym[17]%></div>',
        latlng: new kakao.maps.LatLng(<%=category[17]%>)
    },
    {
    	content: '<div><%=categorym[18]%></div>',
        latlng: new kakao.maps.LatLng(<%=category[18]%>)
    },
    {
    	content: '<div><%=categorym[19]%></div>',
        latlng: new kakao.maps.LatLng(<%=category[19]%>)
    },
    {
    	content: '<div><%=categorym[20]%></div>',
        latlng: new kakao.maps.LatLng(<%=category[20]%>)
    },
    {
    	content: '<div><%=categorym[21]%></div>',
        latlng: new kakao.maps.LatLng(<%=category[21]%>)
    }  
	];
	// 마커 이미지의 이미지 주소입니다
	var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
	for (var i = 0; i < positions.length; i ++) {
	    // 마커를 생성합니다
	    var marker = new kakao.maps.Marker({
	        map: map, // 마커를 표시할 지도
	        position: positions[i].latlng // 마커의 위치
	    });
	    // 마커에 표시할 인포윈도우를 생성합니다 
	    var infowindow = new kakao.maps.InfoWindow({
	        content: positions[i].content // 인포윈도우에 표시할 내용
	    });
	    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
	    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
	    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
	    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
	    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
	}
	// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
	function makeOverListener(map, marker, infowindow) {
	    return function() {
	        infowindow.open(map, marker);
	    };
	}
	// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
	function makeOutListener(infowindow) {
	    return function() {
	        infowindow.close();
	    };
	}
</script>
</div>
</div>	
</body>
</html>