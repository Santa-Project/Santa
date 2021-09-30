<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
<link rel="stylesheet" href="${contextPath}/resources/css/mountainInfo/mt_info_detail.css">

<!-- 지도 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e86272e6f7ebfc43f0ce43288f6e1280"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e86272e6f7ebfc43f0ce43288f6e1280&libraries=services,clusterer,drawing"></script>


</head>
<body>
<!-- <header> -->
<!-- <header id="header"></header>
<script type="text/javascript">   
$(document).ready( function() {
	$("#header").load("/header.html");  //헤더 인클루드
								});
</script> -->

<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<section>
	<div class="wrap_search">
		<form class="search">
			<input type="text" placeholder="산 또는 키워드 입력입력하세요.">
			<button><i class="fas fa-search"></i></button>
		</form>
	</div>
	<div class="title">
		<div class="mt_name"><h1>${mountain.mountainName}</h1></div>
      
      	<div class="like">
        <c:if test="${not empty authentication}">
        <!-- 
        click event 발생 시 ♡ 또는 ♥ 로 변경
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
		<p><b>등산로 코스 정보</b> :  
		청춘의 끓는 피다 청춘의 피가 뜨거운지라 인간의 동산에는 사랑의 풀이 돋고 이상의 꽃이 피고 희망의 놀이
		풀이 없으면 인간은 사막이다 오아이스도 없는 사막이다 보이는 끝까지 찾아다녀도 목숨이 있는 때까지 방황하여도 보이는 
		것은 거친 모래뿐일 것이다낙원을 장식하는 천자만홍이 어디 있으며 인생을 풍부하게 하는 온갖 과실이 어디 있으랴? 이상!
		청춘의 끓는 피다 청춘의 피가 뜨거운지라 인간의 동산에는 사랑의 풀이 돋고 이상의 꽃이 피고 희망의 놀이
		풀이 없으면 인간은 사막이다 오아이스도 없는 사막이다 보이는 끝까지 찾아다녀도 목숨이 있는 때까지 방황하여도 보이는 
		것은 거친 모래뿐일 것이다낙원을 장식하는 천자만홍이 어디 있으며 인생을 풍부하게 하는 온갖 과실이 어디 있으랴? 이상!
		청춘의 끓는 피다 청춘의 피가 뜨거운지라 인간의 동산에는 사랑의 풀이 돋고 이상의 꽃이 피고 희망의 놀이
		풀이 없으면 인간은 사막이다 오아이스도 없는 사막이다 보이는 끝까지 찾아다녀도 목숨이 있는 때까지 방황하여도 보이는 
		것은 거친 모래뿐일 것이다낙원을 장식하는 천자만홍이 어디 있으며 인생을 풍부하게 하는 온갖 과실이 어디 있으랴? 이상!</p>
		<p><b>접근</b> : 
		길을 찾아 주며 그들을 행복스럽고 평화스러운 곳으로 인도하겠다는 커다란 이상을 품었기 때문이다 
		그러므로 그들은 길지 아니한 목숨을 사는가 싶이 살았으며 그들의 그림자는 천고에 사라지지 않는 것이다 이것은 현저하게
		길을 찾아 주며 그들을 행복스럽고 평화스러운 곳으로 인도하겠다는 커다란 이상을 품었기 때문이다 
		그러므로 그들은 길지 아니한 목숨을 사는가 싶이 살았으며 그들의 그림자는 천고에 사라지지 않는 것이다 이것은 현저하게</p>
		
		</div>
		<div class="other_content">
			<img id="mt_photo" src="${contextPath}/resources/img/${mountainName}.jpg">
			<div class="hashtag">
				<a>#산타</a>
				<a>#북한산</a>
				<a>#화강암</a>
			</div>
		</div>
	</div>
	<div class="content_bottom">
		<div class="recommended_trip">
			<div class="rt_title">산 위치</div>
			<div class="wrap_map">
				<div id="map"></div>
			</div>
			<div class="distance_and_time">거리와 시간</div>
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
								<div><img id="photo" src="${contextPath }/resources/img/${mountainName}.jpg"></div>
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