<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form action="reply.do" method="post">
			<input type="hidden" name="bId" value="${reply_view.mbIdx}">
			<input type="hidden" name="bGroup" value="${reply_view.memberIdx}">
			<input type="hidden" name="bStep" value="${reply_view.memberVolume}">
			<input type="hidden" name="bIndent" value="${reply_view.matchedMemCnt}">
			<tr>
				<td> 번호 </td>
				<td> ${reply_view.mbIdx} </td>
			</tr>
			<tr>
				<td> 히트 </td>
				<td> ${reply_view.matchedMemCnt} </td>
			</tr>
			<tr>
				<td> 이름 </td>
				<td> <input type="text" name="bName" value="${reply_view.memberIdx}"></td>
			</tr>
			<tr>
				<td> 제목 </td>
				<td> <input type="text" name="bTitle" value="${reply_view.mtIdx}"></td>
			</tr>
			<tr>
				<td> 내용 </td>
				<td> <textarea rows="10"  name="bContent">${reply_view.matchStatus}</textarea></td>
			</tr>
			<tr >
				<td colspan="2"><input type="submit" value="답변"> <a href="list.do" >목록</a></td>
			</tr>
		</form>
	</table>
	
</body>
</html>