<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
<link rel="stylesheet" href="${contextPath}/resources/css/mountainInfo/mt_info_detail.css">
<script src="${contextPath}/resources/js/imageslider.js"></script>
<meta charset="UTF-8">
<title>산정보 상세정보</title>

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

</head>
<body>


<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<section>
	<div class="wrap_search">
		<form class="search" action="/mountainInfo/mtInfoDetail" method="get">	
			<input type="text" placeholder="산 또는 키워드 입력입력하세요.">
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
				String sql = "select  *  from MOUNTAIN a full outer join mhashtag b on a.MNAME = b.MNAME where a.MNAME=b.MNAME and a.MNAME='" + searchinput + "'"; 
				pstmt = connection.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					mname = rs.getString("MNAME");
					mmap = rs.getString("MMAP");
					mlevel = rs.getString("MLEVEL");	
					mhigh = rs.getString("MHIGH");
					info = rs.getString("INFO");	
					dinfo = rs.getString("DETAILINFO");
					traffic = rs.getString("TRAFFIC");	
					trip = rs.getString("TRIP");	
					img = rs.getString("IMG");	
					rimg1 = rs.getString("RIMG1");
					rimg2 = rs.getString("RIMG2");
					rimg3 = rs.getString("RIMG3");
					rimg4 = rs.getString("RIMG4");
					
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
		<div class="mt_name"><h1>${mountain.mountainName}</h1></div>
      
      	<div class="like">
        <c:if test="${not empty authentication}">
        <!-- click event 발생 시 ♡ 또는 ♥ 로 변경 /mtInfo/like?like=like or /mtInfo/like?like=dislike 로 변경 -->
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
				<p><b><%=mname  %></b> :  <%=info  %></p><br>
				<p><b><span style="font: italic bold 1em;">상세정보</span></b> :  <%=dinfo  %></p><br>
				<p><b><span>추천 트립</span></b> : <%=trip  %></p><br>
				<p><b><span>교통 정보</span></b> : <%=traffic  %></p>
				
			</div>
			
			
			
				<div class="other_content">
				<img src="${contextPath}/resources<%=img %>"/><br>
				
		<form action="/mountainInfo/inserthashtag" method="post">
			<select name = "mname">
				<option  name = "mname" value = "<%=mname  %>"><%=mname %></option>
			</select>
			<input type="text" name="hashtag" size = '5'  placeholder="해쉬태그"/>
			<input class = "submitname" type="submit" name="<%=mname %>" value="등록" />
		</form>	
			<br>
			#<%=hashtagcategory[0]  %>&nbsp;&nbsp;#<%=hashtagcategory[1]  %>&nbsp;&nbsp;#<%=hashtagcategory[2]  %>&nbsp;&nbsp;#<%=hashtagcategory[3]  %>&nbsp;&nbsp;#<%=hashtagcategory[4]  %>&nbsp;&nbsp;
			</div>
		</div>
		
		
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
			<div class="photos_contents">
				<div class="wrap_photos_content">
					<c:forEach items="${searchedMbList}" var="memBoard" varStatus="status">
						<div>
							<!-- <img src=${memBoard.picture}>  -->
							<img class="photo" src="${contextPath }/resources/img/${mountainName}.jpg">
							<div class="user_id">${memBoard.memberIdx}</div>
						</div>
						<!-- 모달창 만들기 -->
						<div class="modal">
							<div>
								<div>&lt;</div>
								<!-- <img src=${memBoard.picture}>  -->
								<%-- <div><img id="photo" src="${contextPath }/resources/img/${mountainName}.jpg"></div> --%>
						<img id="photo" src="${contextPath}/resources<%=rimg1%>" alt="산이미지">
						<img id="photo" src="${contextPath}/resources<%=rimg2%>" alt="산이미지">
						<img id="photo" src="${contextPath}/resources<%=rimg3%>" alt="산이미지">
						<img id="photo" src="${contextPath}/resources<%=rimg4%>" alt="산이미지">
								<div>&gt;</div>
							</div>
						</div>
					</c:forEach>
				</div>
					
				
				<div class="slide">
					<div class='arrow'>
						<button class='arrow-left'><i class="fas fa-angle-double-left"></i></button>
					</div>
					<div class='btn_slide'>
						<button data-slide-idx = '0' data-slide-img='mt1'></button>
						<button data-slide-idx = '1' data-slide-img='mt2'></button>
						<button data-slide-idx = '2' data-slide-img='mt3'></button>
						<button data-slide-idx = '3' data-slide-img='mt4'></button>
					</div>	
					<div class='arrow'>
						<button class='arrow-right'><i class="fas fa-angle-double-right"></i></button>
					</div>
				</div>
			</div>
				
		</div>
	</div>
	
</section>

<!-- <footer> -->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>


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
	
	/* 해당 산 idx를 넘겨받아 산 정보 페이지 뿌리기
	산 idx로 산 이름 받아와서 (서브쿼리)
	회원게시글 테이블에서 좋아요 내림차순으로 12위까지 뽑아서 (게시글번호, 게시글사진, 회원번호)
	회원정보 테이블에서  member_idx로 mem_username 가져오고 (join 회원정보 테이블 using(member_idx))
	차례로 div, img, user_id 생성 (forEach) */
	
	
	/* 모달창 */
	$('.photo').click(function(e){
		$('.modal').addClass('active');
	});
	
	$('.modal').click(function(e){
		$('.modal').removeClass('active');
	});
	
	
	
	
	let cnt = 0;

	document.querySelector('.arrow-left').addEventListener('click',function(e){
		document.querySelectorAll(".btn_slide>button").forEach(function(e){
			e.style.backgroundColor = 'black';
		})
		
		$('button[data-slide-idx="0"]').css('backgroundColor','red');
		
		moveSlide(0);
	});

	document.querySelector('.arrow-right').addEventListener('click',function(e){
		document.querySelectorAll(".btn_slide>button").forEach(function(e){
			e.style.backgroundColor = 'black';
		})
		
		$('button[data-slide-idx="3"]').css('backgroundColor','red');
		
		moveSlide(3);
	});

	document.querySelectorAll(".btn_slide>button").forEach(function(e){
		e.addEventListener('click',function(event){
			
			console.dir(e);
			console.dir(event);
			
			let dataSet = event.target.dataset;
			
			let slideIdx = dataSet.slideIdx;
			
			document.querySelectorAll(".btn_slide>button").forEach(function(e){
				e.style.backgroundColor = 'black';
			})
			
			event.target.style.backgroundColor = 'red';
			
			moveSlide(slideIdx);
		})
	})

	let moveSlide = function(slideIdx){
		$('.wrap_photos_content').css("transform",'translateX(' + (slideIdx * -100) + '%)');
		$('.wrap_photos_content').css("transitionDuration",'0.5s');
	}
})();



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