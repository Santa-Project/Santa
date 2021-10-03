<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${contextPath}/resources/css/main/style_main.css">
<meta charset="UTF-8">
<title>SANTA</title>
<!-- fontawesome cdn -->
<script src="https://kit.fontawesome.com/6fd6b71dc1.js"></script>

<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>

<div class="container">
		<div class="video-frame">
			<!-- <video src="images/video-path.mp4" autoplay muted loop poster="images/placeholder-path.JPG"></video> -->
			<video src="/resources/img/main/newmain/video-path.mp4" autoplay muted poster="/resources/img/main/newmain/placeholder-path.JPG" id="video1"></video>
		</div>
		<div class="logo">
			<img src="/resources/img/main/newmain/logo.png">
		</div>
		
	    <c:if test="${empty authentication}">
		    <div class="login_wrapper">
		        <a class="btn-login" href="/main/loginform">LOGIN</a>
		        <a class="btn-join" href="/main/joinform">JOIN</a>
		    </div>
	    </c:if>
	    <c:if test="${not empty authentication}">
	        <div class="mypage_wrapper">
		        <a class="btn-mypage" href="/mypage/mypageBoard"><i class="fas fa-user-circle"></i></a>
		        <a class="btn-userid">${authentication.userId}</a>
		        <a class="btn-logout" href="/main/logout">LOGOUT</a>
	    	</div>
	    </c:if>
		
		<div class="heading">
			<h1>산 타기 전에<br><span>산타</span> 하자!</h1>
			<p>
				산타에서 "등산의 모든 것"을 한 번에 쉽게 즐길 수 있습니다.<br>
				등산에 필요한 정보를 얻을 수 있고,<br>
				함께 등산 가기 위한 동행을 구할 수 있을 뿐만 아니라<br>
				즐거웠던 등산 경험을 산타 회원들과 나눌 수도 있습니다.<br>
				<br>
				등산을 즐기기 전에 아래 버튼을 클릭하여<br>
				안전사고 예방수칙, 건강한 등산 문화를 위한 약속,<br>
				그리고 초보자 가이드를 확인해보세요!<br>
			</p>
			<a class="btn-1" href="/main/notice1">안전사고 예방</a>
			<a class="btn-2" href="/main/notice2">등산문화</a>
			<a class="btn-2" href="/main/novice_guide">초보자 가이드</a>
		</div>

		<div class="trigger">
			<span></span>
			<span></span>
			<span></span>
		</div>
		<div class="modal-gnb">
			<div class="gnb">
				<a href="/mountainInfo/mtInfoMain">등산정보</a>
				<a href="/matching/collectTeam">등산매칭</a>
				<a href="/community/community">커뮤니티</a>
			</div>
		</div>
	</div>

	<script>
		// Trigger
		$('.trigger').click(function(){
			$(this).toggleClass('active')
			$('.modal-gnb').fadeToggle() 
		})

		//다음 video 연속 재생
		// $(function(){
		// 	$("#video2").bind("ended", function() {
    	// document.getElementById("video1").play();
    	// });

    	// 	$("#video1").bind("ended", function() {
    	// document.getElementById("video2").play();
    	// });
    	// });

		$("#video1").on("ended", function() {
			$("#video1").attr("src", "/resources/img/main/newmain/video-tree.mp4");{
				$("#video1").on("ended", function() {
					$("#video1").attr("src", "/resources/img/main/newmain/video-river.mp4");
			})}
		});
		
		
	</script>

</body>
</html>