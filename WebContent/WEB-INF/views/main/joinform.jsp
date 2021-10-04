<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
   	<%@ include file="/WEB-INF/views/include/head.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
    
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
	                <input type="text" id="id" name="id"class="int" maxlength="20" 
	                <c:if test="${not empty param.err and empty joinValid.id}">
                		value="${joinForm.id }"
                	</c:if>
                	required/>
	            </span>
	            <button type="button" id="btnIdCheck">check</button>
	            <span id="idCheck" class="valid-msg">
	            <c:if test="${not empty param.err and not empty joinValid.id}">
	              	아이디는 영문 또는 숫자 조합의 문자열입니다.
	            </c:if>
              	</span>
	        </div>
	
	        <!-- PW1 -->
	        <div>
	            <h3 class="join_title"><label for="pw1">비밀번호</label></h3>
	            <span class="box">
	            <input type="password" id="pw1" name="password" class="int" maxlength="20" 
	            <c:if test="${not empty param.err and empty joinValid.password}">
	            	value="${joinForm.password}"
                </c:if>
	            required/>
	            </span>
	            <span id="pwCheck" class="valid-msg">
	            <c:if test="${not empty param.err and not empty joinValid.password}">
	              	비밀번호는 영어, 숫자,특수문자(#?!@$ %^&*-) 조합의 8글자 이상의 문자열입니다.
	            </c:if>
              	</span>
	        </div>
	
	        <!-- PW2 -->
	        <div>
	            <h3 class="join_title"><label for="pw2">비밀번호 확인</label></h3>
	            <span class="box">
	            <input type="password" id="pw2" name="verifyPassword" class="int" maxlength="20" required/>
	            </span>
	            <span id="vpwCheck" class="valid-msg">
	            <c:if test="${not empty param.err and not empty joinValid.verifyPassword}">
	              	입력하신 비밀번호와 일치하지 않습니다.
	            </c:if>
              	</span>
	        </div>
	
	        <!-- NAME -->
	        <div>
	            <h3 class="join_title"><label for="name">이름</label></h3>
	            <span class="box">
	                <input type="text" id="name" name="name" class="int" maxlength="20" 
	                <c:if test="${not empty param.err and empty joinValid.name}">
                		value="${joinForm.name}"
                	</c:if>
	                required/>
	            </span>
	            <span id="nameCheck" class="valid-msg">
	            <c:if test="${not empty param.err and not empty joinValid.name}">
	              	이름은 영문 또는 한글 조합입니다.
	            </c:if>
              	</span>
	            
	
	        </div>
	
	        <!-- NICKNAME -->
	        <div>
	            <h3 class="join_title"><label for="name">닉네임</label></h3>
	            <span class="box">
	                <input type="text" id="nickname" name="nickname" class="int" maxlength="20"
	                <c:if test="${not empty param.err and empty joinValid.nickname}">
                		value="${joinForm.nickname}"
                	</c:if>
	                required/>
	            </span>
	            <span id="nicknameCheck" class="valid-msg">
	            <c:if test="${not empty param.err and not empty joinValid.nickname}">
	              	닉네임은 영문이나 한글 또는 숫자 조합의 문자열입니다.
	            </c:if>
              	</span>
	        </div>
	
	        <!-- GENDER -->
	        <div>
	            <h3 class="join_title"><label for="gender">성별</label></h3>
	            <span class="box_2">
	                <select id="gender" name="gender" size="3" required>
	                    <option disabled>성별</option>
	                    <option value="M">남자</option>
	                    <option value="F">여자</option>
	                </select>  
	            </span>
	            <span id="genderCheck" class="valid-msg"></span>
	        </div>
	
	        <!-- MOBILE -->
	        <div>
	            <h3 class="join_title"><label for="phoneNo">휴대전화</label></h3>
	            <span class="box">
	                <input type="text" id="mobile" name="mobile" class="int" maxlength="16" placeholder="전화번호 입력" 
	                <c:if test="${not empty param.err}">
                		value="${joinForm.mobile}"
                	</c:if>
	                required/>
	            </span> 
	            <span id="mobileCheck" class="valid-msg">
	            <c:if test="${not empty param.err and not empty joinValid.mobile}">
	              	전화번호는 '-'을 포함하지 않은 숫자(9~11) 조합입니다.
	            </c:if>
	            </span>
	        </div>
	
	        <!-- EMAIL -->
	        <div>
	            <h3 class="join_title"><label for="email">이메일</label></h3>
	            <span class="box">
	                <input type="email" id="email" name="email" class="int" maxlength="100" placeholder="이메일 입력" 
	                <c:if test="${not empty param.err}">
                		value="${joinForm.email}"
                	</c:if>
	                required/>
	            </span>
	            <span id="emailCheck" class="valid-msg">
	            <c:if test="${not empty param.err and not empty joinValid.email}">
	              	유효한 이메일을 입력해주세요.
	            </c:if>
	            </span>
	        </div>
	
	         <!-- ADDRESS -->
	        <div>
	            <h3 class="join_title"><label for="address">주소</label>
	            	<input type="button" id="searchAddr" value="주소검색">
	            	<input type="text" id="zipNo" name="zipNo" readonly="readonly" 
	            	<c:if test="${not empty param.err}">
                		value="${joinForm.zipNo}"
                	</c:if>
	            	>
	            </h3>
	            <span class="box">
					<input type="text" id="roadAddrPart1" class="int" name="roadAddrPart1" placeholder="주소" readonly="readonly" 
					<c:if test="${not empty param.err}">
                		value="${joinForm.roadAddrPart1}"
                	</c:if>
					>
	            </span>
	            <br>
	            <span class="box">
	                <input type="text" id="detailaddress" class="int" name="detailaddress" maxlength="100" placeholder="상세주소" readonly="readonly"
	                <c:if test="${not empty param.err}">
                		value="${joinForm.detailaddress}"
                	</c:if>
	                >
	            </span>
	        </div>
	
	        <!-- 즐겨찾는 산/가고싶은 산(선택) -->
	        <div>
	            <h3><label>즐겨찾는 산/가고싶은 산(선택)</label></h3>
	            <span class="box_2">
	              <select class="mt_wishlist" name="preference" size="5" multiple >
	                <c:forEach items="${mountainList }" var="mountain" varStatus="status">
	                	<option value="${mountain.mtIdx}">${mountain.mtName}</option>
	                </c:forEach>
	              </select>
	            </span>
	        </div>  
	       
	        <!-- join-btn -->
	        <div class="join-btn">
	            <button id="btnJoin">
	                <span>가입하기</span>
	            </button>
	        </div>
        </form>  
    </content>  
    
<script type="text/javascript">
//아이디 중복 체크
let confirmId = '';
document.querySelector('#btnIdCheck').addEventListener('click', function(){

	let userId = document.querySelector('#id').value;
	   
	if(!userId){
		document.querySelector("#idCheck").style.color = "red";
		document.querySelector("#idCheck").innerHTML = "아이디를 입력하지 않았습니다.";
		return;
	}

	fetch("/main/idCheck?id=" + userId)
	.then(function(response){
		if(response.ok){
			console.dir(response);
			return response.text()
		} else {
			throw new Error(response.status);
		}
	})
	.then(function(text){
		console.dir(text);
		if(text == 'available'){
			confirmId = userId;
			document.querySelector('#id').value = userId;
			document.querySelector("#idCheck").style.color = "black";
			document.querySelector("#idCheck").innerHTML = "사용 가능한 아이디 입니다.";
		} else {
			document.querySelector('#id').value = "";
			document.querySelector("#idCheck").style.color = "red";
			document.querySelector("#idCheck").innerHTML = "사용 불가능한 아이디 입니다.";
			
		}
	})
	.catch(function(error){
		document.querySelector("#idCheck").innerHTML = "응답에 실패했습니다. 상태코드 : " + error;
	})
});

//조소api
var pop;

document.querySelector("#searchAddr").addEventListener('click', function(){
	//경로는 시스템에 맞게 수정하여 사용
	//호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	pop = window.open("/main/joinform/address","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
})
var jusoCallBack = function(roadFullAddr,roadAddrPart1,engAddr, jibunAddr, zipNo, addrDetail){
	document.form.roadAddrPart1.value = roadAddrPart1;
	document.form.zipNo.value = zipNo;
	document.form.detailaddress.value = addrDetail;
}

//제출   
document.querySelector("#btnJoin").addEventListener('click', function(e){
	
	let flg = true;
	
	document.querySelector('#idCheck').innerHTML = "";
	document.querySelector('#pwCheck').innerHTML = "";
	document.querySelector('#vpwCheck').innerHTML = "";
	document.querySelector('#nameCheck').innerHTML = "";
	document.querySelector('#nicknameCheck').innerHTML = "";
	document.querySelector('#genderCheck').innerHTML = "";
	document.querySelector('#mobileCheck').innerHTML = "";
	document.querySelector('#emailCheck').innerHTML = "";
	
	let userId = document.querySelector('#id').value;
	console.dir(confirmId);
	console.dir(userId);
	if(confirmId != userId){
		e.preventDefault();
		document.querySelector("#idCheck").style.color = "red";
		document.querySelector('#idCheck').innerHTML = "아이디 중복 검사를 하지 않았습니다.";
		document.querySelector('#id').focus();
		flg = false;
	}
	
	let gender = document.querySelector('#gender').value;
	if(!gender){
		document.querySelector("#genderCheck").style.color = "red";
		document.querySelector('#genderCheck').innerHTML = "성별을 선택하세요.";
		flg = false;
	}
	
	if(!flg){
		return;
	}
	
	document.querySelector("#form").submit();
	
})
		




</script>
</body>
</html>