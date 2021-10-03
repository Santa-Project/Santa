<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
게시글 번호 : ${matchingBoard[0].mbIdx } <br>
게시판 제목 : ${matchingBoard[0].brdName } <br>
게시글 내용: ${matchingBoard[0].brdContent } <br>
방장의 닉네임 : ${matchingBoard[1] } <br>
게시일 : ${matchingBoard[0].brdDate } <br>
산이름 : ${matchingBoard[2] } <br>
등산일정 : ${matchingBoard[0].mtDate } <br>
매칭된 인원 : ${matchingBoard[0].matchedMemCnt } <br>
모집인원 : ${matchingBoard[0].memberVolume } <br>
진행상태 : ${matchingBoard[3] }<br>

알림글 작성 : /matching/notice
-> ${matchingBoard[0].mbIdx } mbIdx(매칭보드Idx) 값 넘김
-> input으로 받은 msg 값 넘김
-> ${matchingBoard[0].memberIdx } memberIdx(방장Idx) 값 넘김
</body>
</html>