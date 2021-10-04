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
			
		<c:if test="${empty found_pw}">
		<form action="/main/pw_request" method="post">
			<div class="search-wrap id-wrap">
				<input placeholder="id" type="id" name="id">
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
		<c:if test="${not empty found_pw}">
				<div class="found_pw">
					PASSWORD : [ ${found_pw } ]
				</div>
		</c:if>
		</section>
		
		<br>
		<section class="Social-sign-in">
		
			<c:if test="${empty found_pw}">
			<div id="search-wrqp">
				<a href="/main/finding_id">아이디 찾기</a> / <a href="/main/joinform">아직 회원이 아니신가요?</a>  
			</div>
			</c:if>		
			
			<c:if test="${not empty found_pw}">
				<a href="/main/loginform">로그인</a> 
			</c:if>
			
		</section>
		
		
		<footer>
		</footer>
		
		</div>
		</div>
	</div>
</body>
</html>