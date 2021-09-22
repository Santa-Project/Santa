<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file = "/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
<link rel="stylesheet" href="${contextPath}/resources/css/main/style_notice3.css">

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- <header> -->
<%@ include file="/WEB-INF/views/common/header.jsp" %>

    
    
    <div class="cfixed">
        <aside class="right-side">
            <ul>
               <li><a href="/main/notice1"><b>산행 안전 사고</b></a></li>
               <li><a href="/main/notice2"><b>등산 문화 개선</b></a></li>
               <li><a href="/main/novice_guide"><b>초보자 가이드</b></a></li>
           </ul>            
        </aside>

        <section class="content">
            <br> 
            <h1>산행 안전 사고</h1>
            <br> 
            <img src="/resources/img/main/work01.png" alt="">
            <br>   
            <br>      
            <h3>위급 상황 시 행동 요령(3C)</h3>
            <br>
            <h5>-  1단계 : 위급상황을 인식하고 어떻게 행동할지를 결정하는 것(Check)<br>
            -  2단계 : 도움을 요청하는 것(Call)<br>
            -  3단계 : 응급의료기관에 인계할 때까지 적절한 처치를 하는 것(Care)<br>
            <br>
            </h5>
            <br>             
            <br>       
            <h4>구조 요청 시 꼭 알려야 할 정보</h4>
            - 응급 상황이 발생한 정확한 장소<br>
            - 무슨 일이 일어났는지<br>
            - 부상자의 상태 정도<br>
            - 전화 거는 사람의 이름, 연락처<br>
            - 얼마나 많은 사람이 다쳤는지<br>
            - 응급처치는 어떻게 하고 있는지<br>
            <br>                      

            <img src="/resources/img/main/work03.png" alt="">
            <br>
            <br>
            <strong>ㆍ 주변에 응급환자 발생시</strong>
            <br>
            <br>
            - 우선 당황하지 말고 침착하게 행동해야 한다.&nbsp;<br>
            - 당황하게 되면 평소에 잘 알고 있던 응급처치조차 제대로 하지 못하고 환자를 더욱더 불안하게 할 수 있다.&nbsp;<br>
            - 혼자서 모든 것을 해결하려는 것은 잘못이다. 환자 상태가 나쁘거나 급할 수록 주변의 도움을 청해야 한다.&nbsp;<br>
            - 소방서에서 운영하는 119 구급대는 응급환자 신고접수 후 5분 안에 현장에 출동하여 도움을 주고 있다. 전국 어디서나 119로 전화 가능하다.&nbsp;<br>
            - 추락사고 현장에서 무리하게 환자를 옮기려고 서두르게 되면 손상을 악화 시킬 수 있으니 구급대를 기다린다.&nbsp;<br>

        </section>

    </div>



<!-- <footer> -->
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 



</body>
</html>