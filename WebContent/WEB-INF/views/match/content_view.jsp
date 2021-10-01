<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form action="modify.do" method="post">
			<input type="hidden" name="bId" value="${content_view.mbIdx}">
	   
			<tr>
				<td>번호</td>
				<td>방이름</td>
				<td>멤버인덱스</td>
				<td>등산일정</td>
				<td>매칭수</td>
				
			</tr>
			<tr>
				<td>${content_view.mbIdx}</td>
				<td>${content_view.brdName }</td>
				<td>${content_view.memberIdx }</td>
				<td>${content_view.mtDate}</td>
				<td>${content_view.matchedMemCnt}</td>
			</tr>
			
		</form>
	</table>
	
</body>
</html>