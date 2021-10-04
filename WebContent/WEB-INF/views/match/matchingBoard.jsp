<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>  
	<link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
    <link rel="stylesheet" href="${contextPath }/resources/css/match/matchingBoard.css">

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
알림글 작성 : /matching/notice
-> ${matchingBoard[0].mbIdx } mbIdx(매칭보드Idx) 값 넘김
-> input으로 받은 msg 값 넘김
-> ${matchingBoard[0].memberIdx } memberIdx(방장Idx) 값 넘김

대기리스트 관리 페이지 이동 버튼 필요
GET:/matching/collectTeam/waitingList

(팀 멤버 지원)
GET:/matching/collectTeam/application

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
            <div class="section_top">
                <div class="section_left">
                    <div class="board_idx">
                        <h3>방 번호 :</h3>
                        <p>${matchingBoard[0].mbIdx }</p>
                    </div>
                    <div class="board_name">
                        <h3>방 제목 :</h3>
                        <p>${matchingBoard[0].brdName }</p>
                    </div>
                    <div class="board_master">
                        <h3>방장 : </h3>
                        <p>${matchingBoard[1] }</p>
                    </div>
                    <div class="mt_name">
                        <h3>산 이름 :</h3>
                        <p>${matchingBoard[2] }</p>
                    </div>
                    <div class="mt_date">
                        <h3>일정 :</h3>
                        <p>${matchingBoard[0].mtDate }</p>
                    </div>
                </div>
                <div class="section_right">
                    <div class="board_date">
                        <h3>게시일 :</h3>
                        <p>${matchingBoard[0].brdDate }</p>
                    </div>
                    <div class="board_state">
                        <h3>진행 상태 :</h3>
                        <p>${matchingBoard[3] }</p>
                    </div>
                    <div class="make_notice">
                    	<button>알림 보내기</button>
                	</div>
                </div>
                
            </div>
            <div class="section_bottom">
                <h2>참가 인원</h2>
                <div class="member_wrapper">
                <c:forEach items="${memberList }" var="member" varStatus="status">
                <div class="member">
                        <img src="" alt="">
                        <h3>${member.nickname }</h3>
                </div>
                </c:forEach>
                </div>
            </div>
        </div>
<script>
    var check = $("input[type='checkbox']");
check.click(function(){
	$("p").toggle();
});
</script>


</body>

</html>