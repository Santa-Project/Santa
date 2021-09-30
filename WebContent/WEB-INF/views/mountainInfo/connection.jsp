<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DB연결테스트</title>
</head>
<body>
	<%
		Connection connection = null;
		String url = "jdbc:oracle:thin:@santadb_high?TNS_ADMIN=C:/CODE/Wallet_SANTADB";
		String id = "ADMIN";  
		String pw = "Santasanta1234!!";  
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		try{
			Class.forName(driver);  
			connection = DriverManager.getConnection(url, id, pw);
			out.println("데이터베이스 연결성공");
		}catch(Exception e){
			out.println("데이터베이스 연결실패.<br/>");
			out.println(e.getMessage()); 
			e.printStackTrace();  
		}finally{
			if(connection != null){
				connection.close();
			}
		}	
	%>
</body>
</html>