<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
    <div class="top_header">
        <div class="top_header1"></div>
        <div class="top_header2">
            <div class="logo"><a href="/main/main"><img src="/resources/img/Santa_Logo.png" alt=""></a></div>
        </div>
        <div class="top_header3">
         <div class="login_wrapper1"></div>
            <div class="login_wrapper2">
	            <c:if test="${empty authentication}">
	                <div class="login"><a href="/main/loginform">LOGIN</a></div>
	                <div class="join"><a href="/main/joinform">JOIN</a></div>
	            </c:if>
	            <c:if test="${not empty authentication}">
	            	<div class="wrap_mypage">
	            		<div class="mypage"><a href="/mypage/mypageBoard"><i class="fas fa-user-circle"></i></a></div>
	                	<div class="user_id">${authentication.userId}</div>
	            	</div>
	            	<div class="logout"><a href="/main/logout">logout</a></div>
	            </c:if>
            </div>
        </div>
    </div>
    <div class="bottom_header">
        <div class="bottom_header1"></div>
        <div class="bottom_header2">
            <div class="nav1"><a href="/mountainInfo/mtInfoMain">등산정보</a></div>
            <div class="nav2"><a href="/matching/collectTeam">등산매칭</a></div>
            <div class="nav3"><a href="/community/community">커뮤니티</a></div>
        </div>
        <div class="bottom_header3"></div>
    </div>
</header>