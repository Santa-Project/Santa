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
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
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
				<form action="/main/login" method="post">
					<div class="login-wrap">
						<input placeholder="User Id" type="text" name="userId">
					</div>
					
					<div class="login-wrap password-wrap">	
						<input placeholder="Password" type="password" name="password">
					</div>
					
					<div class="login-button">
						<button>Sign in</button>
					</div>
				</form>
			</section>
			
			<section class="Social-sign-in">
				<h2>Social sign in</h2>
				<ul class="Social-button-list">
					<!-- 카카오 로그인 -->
					
					<li><button onclick='kakaoLogin();'><img src="${contextPath}/resources/img/main/kakao_login.png" width="465px" height="56px"></button></li>
					
					<!-- 구글 로그인 -->
					<li><a href="#">Google</a></li>
					
				</ul>
				<br>
	            <a href="/main/finding_id">아이디 찾기</a>
	            <br>
	            <a href="/main/finding_pw">비밀번호 찾기</a>
	            <br>
	            <a href="/main/joinform">아직 회원이 아니신가요?</a>  
			</section>
			
			<footer>
			</footer>
			
			</div>
			</div>
		</div>
	
		
<script>
Kakao.init('e86272e6f7ebfc43f0ce43288f6e1280'); //발급받은 키 중 javascript키를 사용해준다.
console.log(Kakao.isInitialized()); // sdk초기화여부판단
//카카오로그인
function kakaoLogin() {
	var form = document.createElement("form");

    form.setAttribute("method", "Post");  //Post 방식

    form.setAttribute("action", "/main/kakaoLogin"); //요청 보낼 주소
    
    document.body.appendChild(form);
	
	console.log(Kakao);
	if(!Kakao.Auth.getAccessToken()){
		// 첫 로그인
		Kakao.Auth.login({
			scope:'profile_nickname,profile_image,account_email,gender',
		    success: async function (response) {
		    	
		    	await Kakao.API.request({
		        	url: '/v2/user/me',
		        	success: function (res) {
		      			console.log(res);
		      			
		      			let id = document.createElement("input");
		      			id.name = "id";
		      			id.setAttribute('value',res.id);
		      			id.setAttribute('type','hidden');
		      			form.appendChild(id);
		      			
		      			// 닉네임
		      			let kakao_nickname = res.kakao_account.profile.nickname;
		      			let nickname = document.createElement("input");
		      			nickname.name = "nickname";
		      			nickname.setAttribute('value',kakao_nickname);
		      			nickname.setAttribute('type','hidden');
		      			form.appendChild(nickname);
		      			
		      			// 성별
		      			let kakao_gender;
		      			if(!res.kakao_account.gender_needs_agreement){
		      				kakao_gender = res.kakao_account.gender;
		      				let gender = document.createElement("input");
		      				gender.name = "gender";
		      				gender.setAttribute('value',kakao_gender);
		      				gender.setAttribute('type','hidden');
			      			form.appendChild(gender);
		      			}
		      			
		      			// 이메일
		      			let kakao_email;
		      			if(!res.kakao_account.email_needs_agreement){
		      				kakao_email = res.kakao_account.email;
		      				let email = document.createElement("input");
		      				email.name = "email";
		      				email.setAttribute('value',kakao_email);
		      				email.setAttribute('type','hidden');
			      			form.appendChild(email);
		      			}
		      			
		      			/* // 프로필 사진 url
		      			let kakao_photo;
		      			if(!res.kakao_account.profile_image_needs_agreement){
		      				kakao_photo = res.kakao_account.profile.profile_image_url;
		      				let photo = document.createElement("input");
		      				photo.name = "photo";
		      				photo.setAttribute('value',kakao_photo);
		      				photo.setAttribute('type','hidden');
			      			form.appendChild(photo);
		      			} */
		      			
		      			
		      			
		        	}
		        });
		    	
		    	
		    	form.submit();
		    },
		    fail: function (error) {
		    	console.log(error);
		    }
		})
	} else {
		// 카카오 로그인 이력이 있는 경우
		(async function(){
			await Kakao.API.request({
				url: '/v2/user/me',
	        	success: function (res) {
	        		let id = document.createElement("input");
	      			id.name = "id";
	      			id.setAttribute('value',res.id);
	      			id.setAttribute('type','hidden');
	      			form.appendChild(id);
	        	}
	        });
			
			form.submit();
		})();
        	
	}
}
	
    
/* //카카오로그아웃  
function kakaoLogout() {
    if (Kakao.Auth.getAccessToken()) {
      Kakao.API.request({
        url: '/v1/user/unlink',
        success: function (response) {
        	console.log(response)
        },
        fail: function (error) {
          console.log(error)
        },
      })
      Kakao.Auth.setAccessToken(undefined)
    }
  } */  
</script>
</body>
</html>