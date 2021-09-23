<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
<link rel="stylesheet" href="./match/openroom.css">
</head>
<body>
    
    <header>
        <div class="logo">
            <a href="">로고</a>
            <div class="log_Pw">
                <a class="logPw" href="">회원가입</a>
                <a class="logPw" href="">로그인</a> 
            </div>
         </div>
        
        <div class="photo">
            <a class="prof" href="">산타산타</a>
            <a class="prof" href="">사진</a>
        </div>
    </header>

    <nav >
        <div class="nav_div">
            <button class="nav_but">1</button>
            <button class="nav_but">2</button>
            <button class="nav_but">3</button>
        </div>
    </nav>
    <div class="team">
        <a href="">팀원모집</a>
        <a href="">유저목록</a>
         <a id="a_bell" href=""><img id="bell" src="/resources/img/match/bell.jpg" alt=""></a>
    </div>
    
    <div classid="plan_info">
       
       <div class="wrap">
        <section class="inwrap" id="room_info">
                <div>
                    <h3><label for="name">방장명</label></h3>
                    <span class="box int_name">
                        <input type="text" id="n">
                    </span>    
                </div>
                <div>
                    <h3><label for="name">방제목</label></h3>
                    <span class="box int_name">
                        <input type="text" id="ni">
                    </span>    
                </div>
                <div>
                    <h3><label for="name">내용</label></h3>
                    <span class="box int_name">
                        <input type="text" id="content">
                    </span>    
                </div>
                
                <hr id="hr">
                
                <div id="two_anchor">
                    <a href="">취소</a> 
                </div>
                <div id="two_anchor2">   
                    <a href="">방개설</a>        
                </div>
         </section>
         <aside id="the_day">
            <div>
                <p>등산일정</p>
                <input type="date">
            </div>
        </aside>
         
         </div> 
         
        
        
    </div>
</body>
</html>