<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum=1, user-scalable=no">
    <title>santa_main</title>
    
    <link rel="stylesheet" href="${contextPath}/resources/css/main/style_join.css">
</head>
<body>

	<header class="header">
            <h1 class="logo"><a href="/main/main">SANTA</a></h1>
    </header>
    
    <content>
    	<form name="form" id="form" action="/main/join" method="post">
			<!-- ID -->
	        <div>
	            <h3 class="join_title">
	                <label for="id">아이디</label>
	            </h3>
	            <span class="box">
	                <input type="text" id="id" class="int" maxlength="20">
	            </span>
	        </div>
	
	        <!-- PW1 -->
	        <div>
	            <h3 class="join_title"><label for="pw1">비밀번호</label></h3>
	            <span class="box">
	            <input type="password" id="pw1" class="int" maxlength="20">
	            </span>
	        </div>
	
	        <!-- PW2 -->
	        <div>
	            <h3 class="join_title"><label for="pw2">비밀번호 확인</label></h3>
	            <span class="box">
	            <input type="password" id="pw2" class="int" maxlength="20">
	            </span>
	        </div>
	
	        <!-- NAME -->
	        <div>
	            <h3 class="join_title"><label for="name">이름</label></h3>
	            <span class="box">
	                <input type="text" id="name" class="int" maxlength="20">
	            </span>
	
	        </div>
	
	        <!-- NICKNAME -->
	        <div>
	            <h3 class="join_title"><label for="name">닉네임</label></h3>
	            <span class="box">
	                <input type="text" id="nickname" class="int" maxlength="20">
	            </span>
	        </div>
	
	        <!-- GENDER -->
	        <div>
	            <h3 class="join_title"><label for="gender">성별</label></h3>
	            <span class="box">
	                <select id="gender">
	                    <option>성별</option>
	                    <option value="M">남자</option>
	                    <option value="F">여자</option>
	                </select>  
	            </span>
	        </div>
	
	        <!-- MOBILE -->
	        <div>
	            <h3 class="join_title"><label for="phoneNo">휴대전화</label></h3>
	            <span class="box">
	                <input type="tel" id="mobile" class="int" maxlength="16" placeholder="전화번호 입력">
	            </span> 
	        </div>
	
	        <!-- EMAIL -->
	        <div>
	            <h3 class="join_title"><label for="email">이메일</label></h3>
	            <span class="box">
	                <input type="text" id="email" class="int" maxlength="100" placeholder="이메일 입력">
	            </span>
	        </div>
	
	         <!-- ADDRESS -->
	        <div>
	            <h3 class="join_title"><label for="address">주소</label>
	            	<input type="button" onClick="goPopup();" value="주소검색">
	            	<input type="text" id="zipNo" name="zipNo" readonly="readonly">
	            </h3>
	            <span class="box">
					<input type="text" id="roadAddrPart1" class="int" name="roadAddrPart1" placeholder="주소" readonly="readonly">
	            </span>
	            <br>
	            <span class="box">
	                <input type="text" id="detailaddress" class="int" name="" maxlength="100" placeholder="상세주소" readonly="readonly">
	            </span>
	        </div>
	
	        <!-- 즐겨찾는 산/가고싶은 산(선택) -->
	        <div>
	            <h3><label>즐겨찾는 산/가고싶은 산(선택)</label></h3>
	            <span class="box">
	              <select class="mt_wishlist" multiple>
	                <option>value1</option>
	                <option>value2</option>
	                <option>value3</option>
	                <option>value4</option>
	                <option>value5</option>
	              </select>
	            </span>
	        </div>  
	       
	        <!-- join-btn -->
	        <div class="join-btn">
	            <button type="button" id="btnJoin">
	                <span>가입하기</span>
	            </button>
	        </div>
        </form>  
    </content>  
    
<script type="text/javascript">
	var pop;
	
	var goPopup = function(){
		//경로는 시스템에 맞게 수정하여 사용
		//호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		pop = window.open("/main/joinform/address","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	}
	var jusoCallBack = function(roadFullAddr,roadAddrPart1,engAddr, jibunAddr, zipNo, addrDetail){
	 document.form.roadAddrPart1.value = roadAddrPart1;
	 document.form.zipNo.value = zipNo;
	 document.form.detailaddress.value = addrDetail;
 	 pop.close();
}
</script>
</body>
</html>