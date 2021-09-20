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
            <h1 class="logo"><a href="santa_main.html">SANTA</a></h1>
    </header>
    
    <content>

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
            <h3 class="join_title"><label for="address">주소</label></h3>
            <span class="box">
                <input type="text" id="address" class="int" maxlength="100" placeholder="주소 입력">
            </span>
            <br>
            <span class="box">
                <input type="text" id="detailaddress" class="int" maxlength="100" placeholder="상세주소 입력">
            </span>
        </div>

        <!-- 즐겨찾는 산/가고싶은 산(선택) -->
        <div>
            <h3><label>즐겨찾는 산/가고싶은 산(선택)</label></h3>
            <span class="box">
             <form  action="" method="">
              <select multiple>
                <option>value1</option>
                <option>value2</option>
                <option>value3</option>
                <option>value4</option>
                <option>value5</option>
              </select>
            </form>
            </span>
        </div>  
       
        <!-- join-btn -->
        <div class="join-btn">
            <button type="button" id="btnJoin">
                <span>가입하기</span>
            </button>
        </div>
          
    </content>  
</body>
</html>