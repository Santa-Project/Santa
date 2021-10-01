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
                <button class="matching_btn1" id="matching_btn"><a href="/matching/collectTeam">팀원모집</a></button>
                <button class="matching_btn2" id="matching_btn"><a href="#">유저목록</a></button>
            </div>
            <div class="matching_nav3">
                <div class="alarm_btn"><a href="#"><i class="fas fa-bell"></i></a></div>
                <div class="alarm_sub">
                    <a href="#">AAAA님이 1번방으로 초대했습니다.</a>
                    <a href="#">AAAA님이 1번방으로 초대했습니다.</a>
                    <a href="#">AAAA님이 1번방으로 초대했습니다.</a>
                    <a href="#">AAAA님이 1번방으로 초대했습니다.</a>
                    <a href="#">AAAA님이 1번방으로 초대했습니다.</a>
                    <a href="#">AAAA님이 1번방으로 초대했습니다.</a>
                </div>
            </div>
        </div>
    	
        <div class="make_board">
        <form class="createBoard">
            <div class="section_top">
                <div class="section_left">
                    <div class="mt_choice">
                        <p>산 선택</p>
                 		<select class="mt" name="mt" id="">
	             		<c:forEach items="${mountainList }" var="mountain" varStatus="status">
	             			<option value="${mountain.mtIdx}">${mountain.mountainName}</option>
	             		</c:forEach>
	             		</select>
                    </div>
                    <div class="board_master"><p>방장 이름</p> <p>${authentication.nickname }</p>
                    <input type="hidden" name="memberIdx" value="${authentication.memberIdx}"> </div>
                    <div class="board_name">방 제목<input name="brdName" type="text" ></div>
                </div>
                <div class="section_right">
                    <div class="member_count">모집 인원
                        <input name="memberVolume"type="number" min="2" max="6" >
                    </div>
                    <div class="calendar">등산 일정
                        <p>Date: <input name="mtDate" type="text" id="datepicker"></p>
                    </div>
                </div>
            </div>
            <div class="section_bottom">
                <div class="content">내용 <input name="brdContent" type="text"></div>
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
            $("#datepicker").datepicker();
        });
        
        document.querySelector("#createTeam").addEventListener("click",function(){
        	document.querySelector(".createBoard").action = "/matching/createMatchingBoard.do";
        	document.querySelector(".createBoard").submit();
        })
        
        document.querySelector("#cancle").addEventListener("click",function(){
        	document.querySelector(".createBoard").action = "/matching/collectTeam";
        	document.querySelector(".createBoard").submit();
        })
        
        
    </script>

</body>
</html>