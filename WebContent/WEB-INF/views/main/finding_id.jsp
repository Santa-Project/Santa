<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="${contextPath }/resources/css/main/style_finding_id_pw.css">

</head>

<body>

	<div class="allwrap">
    <!--해당 영역 중심으로 맞춤-->
	    <div id="wrap1">
            <header class="header">
                <h1 class="logo"><a href="/main/main">SANTA</a></h1>
            </header>
     <!--해당 영역 중심으로 맞춤-->
        <div id="wrap2">
			<div class="logo-wrap">
				<img />
			</div>
	
	
		<section class="search-section">
		<c:if test="${empty found_id}">
			<form action="/main/idRequest" method="post">
				<div class="search-wrap username-wrap">
					<input placeholder="Username" type="text" name="username">
				</div>
				
				<div class="search-wrap email-wrap">	
					<input placeholder="email" type="email" name="email">
				</div>
				
				<br>
				<br>
				<div class="search-button">
					<button>search</button>
				</div>
			</from>
		</c:if>
		<c:if test="${not empty found_id}">
				<div class="found_id">
					ID : [ ${found_id } ]
				</div>
		</c:if>
		</section>
		
		<br>
		<section class="Social-sign-in">
		
			<c:if test="${not empty found_id}">
				<a href="/main/loginform">로그인</a> 
			</c:if>
			
			<br>
			
			<div id="search-wrqp">
				<a href="/main/findingPw">비밀번호 찾기</a>
				<c:if test="${empty found_id}">
				 / <a href="/main/joinform">아직 회원이 아니신가요?</a> 
				 </c:if>
			</div>
			 
		</section>
		
		
		<footer>
		</footer>
		
		</div>
		</div>
	</div>
</body>
</html>