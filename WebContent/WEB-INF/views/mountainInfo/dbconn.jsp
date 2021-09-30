<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
		
	<% 	
		Connection connection = null;
		
		String url = "jdbc:oracle:thin:@santadb_high?TNS_ADMIN=C:/CODE/Wallet_SANTADB";
		String user = "ADMIN";  
		String password = "Santasanta1234!!"; 
		String driver = "oracle.jdbc.driver.OracleDriver";
				
		Class.forName(driver);  
		connection = DriverManager.getConnection(url, user, password);
	%>