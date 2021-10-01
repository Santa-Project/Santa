<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="${contextPath}/resources/css/match/main.css">
<link rel="stylesheet"
	href="${contextPath}/resources/css/match/openroom.css">
<link rel="stylesheet"
	href="${contextPath}/resources/css/match/openroom2.css">
<link rel="stylesheet"
	href="${contextPath}/resources/css/common/header.css">
<link rel="stylesheet"
	href="${contextPath}/resources/css/match/main.css">
<link rel="stylesheet" href="./match/openroom.css">
<meta charset="UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<div>


		<div class="photo">
			<a class="prof" href="">산타산타</a> <a class="prof" href="">사진</a>
		</div>
	</div>

	<nav>
		<div class="nav_div">
			<button class="nav_but">1</button>
			<button class="nav_but">2</button>
			<button class="nav_but">3</button>
		</div>
	</nav>
	<div class="team">
		<a id="collectTeam" href="">팀원모집</a> <a id="userList href="">유저목록</a>
		<a id="a_bell" href=""><img id="bell"
			src="/resources/img/match/bell.jpg" alt=""></a>
	</div>

	<div class="wrap">
						<section class="inwrap" id="room_info">
						<form action="/matching/collectTeam" method="POST">
						<div>
								<h3>
									<label for="name">방장명</label>
								</h3>
								<span class="box int_name"> <input type="hidden" id="n" name="memberIdx" value="${authentication.memberIdx }">${authentication.nickname }
								</span>
							</div>
							<div>
								<h3>
									<label for="name">방제목</label>
								</h3>
								<span class="box int_name"> <input type="text" id="ni" name="room_title">
								</span>
							</div>
							<div>

								<h3>
									<label for="name">내용</label>
								</h3>
								<span class="box int_name"> <input type="text" name="content"
									id="content">
								</span>
							</div>

							<hr id="hr">

							<div id="two_anchor">
								<button href="">취소</button>
							</div>
							<div id="two_anchor2">
								<button>방개설</button>
							</div>
						</section>
						<aside id="the_day">
							<div>
								<p>등산일정</p>
								<input type="date" name="the_day">
							</div>
						</aside>

					</div>
						
						</form>
							
</body>
</html>