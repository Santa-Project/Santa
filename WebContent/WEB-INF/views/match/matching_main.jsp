<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/views/include/head.jsp" %>
    <link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
    <link rel="stylesheet" href="${contextPath }/resources/css/match/main.css">
</head>

<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <section>
        <div class="matching_nav">
            <div class="matching_nav1"></div>
            <div class="matching_nav2">
                <button class="matching_btn1" id="matching_btn"><a href="/matching/collectTeam/main">팀원모집</a></button>
                <button class="matching_btn2" id="matching_btn"><a href="#">유저목록</a></button>
            </div>
            <div class="matching_nav3">
                <div class="alarm_btn">
                    <a href="#"><i class="fas fa-bell"></i></a>
                    <div class="alarm_sub">
                    <c:forEach items="${matchingAlarmList }" var="matchingAlarm" varStatus="status">
                        <a href="#">${matchingAlarm[1] } ${matchingAlarm[0].msg }</a>
                    </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="search_box">
            <div class="search_box1">
            </div>
            <div class="search_box2">
                <input class="input" placeholder="원하는 매칭을 찾아보세요">
                <a href="#"><i class="fas fa-search"></i></a>
            </div>
            <div class="search_box3"></div>
        </div>
        <div class="search_board">
            <table border="1" width="1200" cellpadding="20" cellspacing="10">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>진행상태</th>
                        <th>등산정보</th>
                        <th>등산일정</th>
                        <th>방장명</th>
                        <th>게시일</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${matchingBoardList}" var="matchingBoard" varStatus="status">
                		
                    <form action="/matching/collectTeam/matchingBoard">
                    	<input type="hidden" name="mbIdx" value="${matchingBoard[0].mbIdx }">
	                    <tr height="30">
	                    	<td><button>게시글 순서</button></td>
	                        <td><button>${matchingBoard[0].matchStatus }</button></td>
	                        <td><button>${matchingBoard[0].brdName }</button></td>
	                        <td><button>${matchingBoard[0].mtDate }</button></td>
	                        <td><button>${matchingBoard[1] }</button></td>
	                        <td><button>${matchingBoard[0].brdDate }</button></td>
	                    </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <form action="/matching/collectTeam/createBoardForm">
        	<button class="create_btn">방 만들기</button>
		</form>
		
    </section>

</body>
</html>