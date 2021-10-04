<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 입력값 DB저장하기</title>
</head>
<body>
	<%@ include file="dbconn.jsp" %>
	
	<%
		request.setCharacterEncoding("UTF-8");
	
		String mname = request.getParameter("mname");
		String hashtag = request.getParameter("hashtag");
		
		PreparedStatement pstmt = null;
		
		 String sql = "insert into MOUNTAIN_HASHTAG values (?,?)";
		
		try{
				
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, mname);
			pstmt.setString(2, hashtag);
			
			
			pstmt.executeUpdate();
			response.sendRedirect("http://localhost:7070/mountainInfo/mtInfoDetail");
			
			
		}catch(SQLException e){
			out.println("MOUNTAIN_HASHTAG테이블에 삽입 실패되었습니다.");
			out.println("SQLException : " + e.getMessage());
			e.printStackTrace(); //개발이후에는 주석처리
		}finally {
			if(pstmt != null) 
				pstmt.close();
			if(connection != null) 
				connection.close();
		}	
		
	%>	
</body>
</html>