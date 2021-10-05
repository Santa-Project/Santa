<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/match/createMatchingBoard.css">

	<!-- 달력 -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    
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
                        <a href="#">From : ${matchingAlarm[1] }<br>To : ${authentication.nickname }<br>${matchingAlarm[0].msg }</a>
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
                    <div class="board_name"><p>방 제목</p>
	                    <input name="brdName" type="text" id="brdName" 
	                    <c:if test="${not empty param.err and empty boardValid.brdName}">
	                		value="${createBoard.brdName }"
	                	</c:if>
	                	<c:if test="${not empty param.err and not empty boardValid.brdName}">
		              		placeholder="방 제목은 1~40자 문자열 입니다."
		            	</c:if>
                    	required/>
                    </div>
                </div>
                <div class="section_right">
                    <div class="member_count">
                    	<p>모집 인원</p>
                        <input name="memberVolume"type="number" value="3" min="3" max="7" id="memberVolume" 
                        <c:if test="${not empty param.err and empty boardValid.memberVolume}">
                		value="${createBoard.memberVolume }"
	                	</c:if>
	                	<c:if test="${not empty param.err and not empty boardValid.memberVolume}">
		              		placeholder="인원수는 최소 3명 최대 7명 입니다."
		            	</c:if>
	            		required/>
                    </div>
                    <div class="calendar">
                        <p>등산 일정: </p>
                        <input name="mtDate" type="text" id="datepicker" required/>
                    </div>
                </div>
            </div>
            <div class="section_bottom">
                <div class="content">내용 
                <input name="brdContent" type="text" id="brdContent"
                <c:if test="${not empty param.err and empty boardValid.brdContent}">
              		value="${createBoard.brdContent }"
               	</c:if>
               	<c:if test="${not empty param.err and not empty boardValid.brdContent}">
              		placeholder="3일 이후로 선택 가능"
            	</c:if>
           		required/>
                </div>
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
        (function () {
            $("#datepicker").datepicker({
            	minDate : 3,
            	dateFormat : 'yy-mm-dd'
            });
        })();
        
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