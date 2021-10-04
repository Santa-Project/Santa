<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
    <link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
    <link rel="stylesheet" href="${contextPath }/resources/css/match/userlist.css">
	<!-- 달력 -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <!-- 토글버튼 -->
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.3/jquery.min.js"></script>
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
                <div class="alarm_btn"><a href="#"><i class="fas fa-bell"></i></a></div>
                <div class="alarm_sub">
                    <c:forEach items="${matchingAlarmList }" var="matchingAlarm" varStatus="status">
                        <a href="#">방장 : ${matchingAlarm[1] }<br>${matchingAlarm[0].msg }</a>
                    </c:forEach>
                </div>
            </div>
        </div>
        
        <div class="board">
            <div class="userlist_wrapper">
            <c:forEach items="${wlList }" var="waitingList" varStatus="status">
                <div class="userlist">
                    <span>${ waitingList[1].nickname}</span>
                    <form action="/matching/collectTeam/accept">
                    	<input type="hidden" name="leaderIdx" value="${matchingAlarm[0].senderIdx }">
                 		<input type="hidden" name="memberIdx" value="${waitingList[1].memberIdx }">
                 		<input type="hidden" name="wlIdx" value="${waitingList[0].wlIdx }">
                 		<input type="hidden" name="mbIdx" value="${waitingList[0].mbIdx }">
                 		<input type="hidden" name="mtDate" value="${matchingBoard.mtDate }">
                 		<input type="hidden" name="memVolume" value="${matchingBoard.memVolume }">
                 		<input type="hidden" name="matchedMemCnt" value="${matchingBoard.matchedMemCnt }">
                 		<button id="accept">승인</button>
                    </form>
                    <form action="/matching/collectTeam/reject">
                    	<input type="hidden" name="leaderIdx" value="${matchingAlarm[0].senderIdx }">
                 		<input type="hidden" name="memberIdx" value="${waitingList[1].memberIdx }">
                 		<input type="hidden" name="wlIdx" value="${waitingList[0].wlIdx }">
                 		<input type="hidden" name="mbIdx" value="${waitingList[0].mbIdx }">
                 		<button id="reject">거절</button>
                    </form>
                </div>
            </c:forEach>
                
            </div>
        </div>

    </section>

</body>

</html>