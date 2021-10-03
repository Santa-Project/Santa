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
                <button class="matching_btn1" id="matching_btn"><a href="#">팀원모집</a></button>
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
            <c:forEach items="${userList }" var="user" varStatus="status">
                <div class="userlist1">
                    <span>${user[2]}</span>
                    <p>user[1].mtName</p>
                    <p>user[1].mtRegion</p>
                    <p>user[0].mtDate</p>
                    <p>user[0].brdDate</p>
                    <button>초대</button>
                </div>
             </c:forEach>   
                <div class="paging">
                    <a href="#"><span>&LT</span></a>
                    <a href="#"><span>1</span></a>
                    <a href="#"><span>2</span></a>
                    <a href="#"><span>3</span></a>
                    <a href="#"><span>4</span></a>
                    <a href="#"><span>5</span></a>
                    <a href="#"><span>&GT</span></a>
                </div>
                
            </div>
        </div>

    </section>


</body>

</html>