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
                <h1 class="logo"><a href="/main/main">SANTA</a></h1>
            </header>
     <!--해당 영역 중심으로 맞춤-->
        <div id="wrap2">
			<div class="logo-wrap">
				<img />
			</div>
	
	
		<section class="login-section">
			<form action="/main/id_request">
				<div class="login-wrap">
					<input placeholder="Username" type="text">
				</div>
				
				<div class="login-wrap email-wrap">	
					<input placeholder="email" type="email">
				</div>
				
				<br>
				<br>
				<div class="login-button">
					<button>search</button>
				</div>
			</from>
		</section>
		
		<br>
		<section class="Social-sign-in">
			
			<br>

            <a href="/main/joinform">아직 회원이 아니신가요?</a>  
		</section>
		
		
		<footer>
		</footer>
		
		</div>
		</div>
	</div>
</body>
</html>