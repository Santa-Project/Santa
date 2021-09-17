<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file = "/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
<link rel="stylesheet" href="${contextPath}/resources/css/main/style_main.css">

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- <header> -->
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div id="allwrap">
        <article class="article1">
            <video id="mp4" src="/resources/img/main/mountain2.mp4" muted autoplay loop></video>
        </article>
     <div class="wrap2">   
      <article class="article2">
            <img src="/resources/img/main/work11.png" alt="산 이미지"> 
      </article>
            <div class="search">
                <input class="input1" type="text" placeholder="&#61442;"/>
            </div>
    </div>       
                        
<!--work영역-->        
        <section class="work-section">
            <ul class="work-list">
                <li>
                    <a href="santa_desc1.html">
                        <div class="info">
                            <h3>안전사고</h3>
                            <span>페이지 이동</span>
                        </div>
                        <img src="/resources/img/main/work12.png" alt="안전사고 이미지">
                    </a>
                </li>
                <li>
                    <a href="santa_desc2.html">
                        <div class="info">
                            <h3>등산문화 개선</h3>
                            <span>페이지 이동</span>
                        </div>
                        <img src="/resources/img/main/work02.png" alt="등산문화 이미지">
                    </a>
                </li>
            </ul>            
        </section>
        
        
         <article class="article3">
           <ul class="article-list2">
                <li>
                    <a href="santa_desc3.html">
                        <div class="info2">
                            <h3>초보자 가이드</h3>
                            <span>페이지 이동</span>
                        </div>
                        <img src="/resources/img/main/next_mountain.png" alt="초보자 이미지">
                    </a>
                </li>
            </ul>
        </article>

   </div>



<!-- <footer> -->
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 



</body>
</html>