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
	
	(fetch)
	수락 거절
	
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
                        <a href="#">${matchingAlarm[1] } ${matchingAlarm[0].msg }</a>
                    </c:forEach>
                </div>
            </div>
        </div>
        
        <div class="board">
            <div class="userlist_wrapper">
            <c:forEach items="${wlList }" var="waitingList" varStatus="status">
                <div class="userlist">
                    <span>waitingList[1].nickname</span>
                    <button>승인</button>
                    <button>거절</button>
                </div>
            </c:forEach>
                
            </div>
        </div>

    </section>


</body>

</html>