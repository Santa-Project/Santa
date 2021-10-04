<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/match/createMatchingBoard.css">
</head>

<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <section>
        <div class="matching_nav">
            <div class="matching_nav1"></div>
            <div class="matching_nav2">
                <button class="matching_btn1" id="matching_btn"><a href="/matching/collectTeam/main">팀원모집</a></button>
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
    	
        <div class="make_board">
        <form class="createBoard">
            <div class="section_top">
                <div class="section_left">
                    <div class="mt_choice">
                        <p>산 선택</p>
                 		<select class="mt" name="mt" id="selectedMountain">
	             		<c:forEach items="${mountainList }" var="mountain" varStatus="status">
	             			<option value="${mountain.mtIdx}">${mountain.mtName}</option>
	             		</c:forEach>
	             		</select>
                    </div>
                    <div class="board_master"><p>방장 이름</p> <p>${authentication.nickname }</p>
                    <input type="hidden" name="memberIdx" value="${authentication.memberIdx}"> </div>
                    <div class="board_name"><p>방 제목</p><input name="brdName" type="text" id="brdName" ></div>
                </div>
                <div class="section_right">
                    <div class="member_count">
                    	<p>모집 인원</p>
                        <input name="memberVolume"type="number" value="3" min="3" max="7" id="memberVolume" >
                    </div>
                    <div class="calendar">
                        <p>등산 일정: </p><input name="mtDate" type="date" id="datepicker">
                    </div>
                </div>
            </div>
            <div class="section_bottom">
                <div class="content">내용 <input name="brdContent" type="text" id="brdContent"></div>
            </div>
        </form>
        </div>

        <div class="yes_btn" id="bottom_btn">
            <button id="createTeam">팀 개설</button>
        </div>

        <div class="no_btn" id="bottom_btn">
            <button id="cancle">취소</button>
        </div>
		
    </section>
    <script>
        $(function () {
            $("#datepicker").datepicker({
            	minDate : 3
            });
        });
        
        document.querySelector(".yes_btn").addEventListener("click",function(){
        	document.querySelector(".createBoard").action = "/matching/collectTeam/createBoard";
        	document.querySelector(".createBoard").submit();
        })
        
        document.querySelector(".no_btn").addEventListener("click",function(){
        	history.back();
        })
        
        
    </script>

</body>
</html>