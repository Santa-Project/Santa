<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="${contextPath}/resources/css/main/style_login3.css">

<meta charset="UTF-8">
<title>Insert title here</title>

<style>
    :root{
    --body-background-color: #fff;
    --border-gray-color : #dadada;
    --naver-green-color: #94af76;;
    --naver-green-border-color: #94af76;;
    }
</style>
</head>

<body>                 
	<div class="allwrap">
	    <!--해당 영역 중심으로 맞춤-->
		    <div id="wrap1">
	            <header class="header">
	                <h1 class="logo"><a href="santa_main.html">SANTA</a></h1>
	            </header>
	     <!--해당 영역 중심으로 맞춤-->
	        <div id="wrap2">
				<div class="logo-wrap">
					<img />
				</div>
		
			<section class="login-section">
				<div class="login-wrap">
					<input placeholder="Username" type="text">
				</div>
				
				<div class="login-wrap password-wrap">	
					<input placeholder="Password" type="password">
				</div>
				
				<div class="login-button">
					<button>Sign in</button>
				</div>
			</section>
			
			<section class="Social-sign-in">
				<h2>Social sign in</h2>
				<ul class="Social-button-list">
					<li><button><span>Kakao</span></button></li>
					<li><button><span>Google</span></button></li>
				</ul>
				<br>
	            <a href="santa_missedid.html">아이디/비밀번호 찾기</a>
	            <br>
	            <a href="santa_signin.html">아직 회원이 아니신가요?</a>  
			</section>
			
			<footer>
			</footer>
			
			</div>
			</div>
		</div>
</body>
</html>