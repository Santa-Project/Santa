<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.2.0/css/all.min.css" integrity="sha512-6c4nX2tn5KbzeBJo9Ywpa0Gkt+mzCzJBrE1RB6fmpcsoN+b/w/euwIMuQKNyUoU/nToKN3a8SgNOtPrbW12fug==" crossorigin="anonymous" /> 
<<<<<<< HEAD
<link rel="stylesheet" href="./css/match/chat.css">
<link rel="stylesheet" href="${contextPath}/resources/css/match/main.css">

</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	 <div>
                
               <div class="photo">
            <a class="prof" href="">산타산타</a>
           
        </div>


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
        <div class="bell">
              <a id="a_bell" href=""><img id="bell" src="/resources/img/match/bell.jpg" alt=""></a>
            <a id="a_dots" href=""><img id="dots" src="./resources/img/main/3dots.jpg" alt="">점</a>
        </div>
    </div>
    <div id="search">
        <button id="search_button">검색</button>
        <input id="search_text" type="text" name="" id="">
    </div>
    <section id="list">
        <table >
            <thead>
                <tr>
                    <th class="td1">No.</th>
                    <th class="td2">진행상태</th>
                    <th class="td3">등산정보</th>
                    <th class="td4">등산일정</th>
                    <th class="td5">방장명</th>
                    <th class="td6">게시일</th>
                </tr>
            </thead>
            <tbody>
                <tr class="altRow">
                    <td class="td1"></td>
                    <td class="td2"></td>
                    <td class="td3"></td>
                    <td class="td4"></td>
                    <td class="td5"></td>
                    <td class="td6"></td>
                
                </tr>
                <tr class="altRow">
                    <td class="td1"></td>
                    <td class="td2"></td>
                    <td class="td3"></td>
                    <td class="td4"></td>
                    <td class="td5"></td>
                    <td class="td6"></td>
                </tr>
                <tr class="altRow">
                    <td class="td1"></td>
                    <td class="td2"></td>
                    <td class="td3"></td>
                    <td class="td4"></td>
                    <td class="td5"></td>
                    <td class="td6"></td>
                </tr>
            </tbody>
        </table>

    </section>
    <div id="chat">
        <div class="container">
            <img  alt="Avatar">
            <p>Hello. How are you today?</p>
            <span class="time-right">11:00</span>
          </div>
          
          <div class="container darker">
            <img  alt="Avatar" class="right">
            <p>Hey! I'm fine. Thanks for asking!</p>
            <span class="time-left">11:01</span>
          </div>
          
          <div class="container">
            <img  alt="Avatar">
            <p>Sweet! So, what do you wanna do today?</p>
            <span class="time-right">11:02</span>
          </div>
          
          <div class="container darker">
            <img  alt="Avatar" class="right">
            <p>Nah, I dunno. Play soccer.. or learn more coding perhaps?</p>
            <span class="time-left">11:05</span>
          </div>
    </div>
    <li>
        <div id="divPaging">
            <div>◀</div>
            <div><BUtton></BUtton></div>
            <div><BUtton></BUtton></div>
            <div><BUtton></BUtton></div>
            <div><BUtton></BUtton></div>
            <div><BUtton></BUtton></div>
            <div>▶</div> 
            <div id="openRoomBtn">
                <button><a href="/matching/openRoom">방개설</a></button>
=======
<link rel="stylesheet" href="/css/all.css">
<link rel="stylesheet" href="./css/chat.css">

</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	 <div>
                
               <div class="photo">
            <a class="prof" href="">산타산타</a>
            <a class="prof" href="">사진</a>
        </div>


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
        <div class="bell">
              <a id="a_bell" href=""><img id="bell" src="/resources/img/match/bell.jpg" alt=""></a>
            <a id="a_dots" href=""><img id="dots" src="./resources/img/main/3dots.jpg" alt="">점</a>
        </div>
    </div>
    <div id="search">
        <button id="search_button">검색</button>
        <input id="search_text" type="text" name="" id="">
    </div>
    <section id="list">
        <table >
            <thead>
                <tr>
                    <th class="td1">No.</th>
                    <th class="td2">진행상태</th>
                    <th class="td3">등산정보</th>
                    <th class="td4">등산일정</th>
                    <th class="td5">방장명</th>
                    <th class="td6">게시일</th>
                </tr>
            </thead>
            <tbody>
                <tr class="altRow">
                    <td class="td1"></td>
                    <td class="td2"></td>
                    <td class="td3"></td>
                    <td class="td4"></td>
                    <td class="td5"></td>
                    <td class="td6"></td>
                
                </tr>
                <tr class="altRow">
                    <td class="td1"></td>
                    <td class="td2"></td>
                    <td class="td3"></td>
                    <td class="td4"></td>
                    <td class="td5"></td>
                    <td class="td6"></td>
                </tr>
                <tr class="altRow">
                    <td class="td1"></td>
                    <td class="td2"></td>
                    <td class="td3"></td>
                    <td class="td4"></td>
                    <td class="td5"></td>
                    <td class="td6"></td>
                </tr>
            </tbody>
        </table>

    </section>
    <div id="chat">
        <div class="container">
            <img  alt="Avatar">
            <p>Hello. How are you today?</p>
            <span class="time-right">11:00</span>
          </div>
          
          <div class="container darker">
            <img  alt="Avatar" class="right">
            <p>Hey! I'm fine. Thanks for asking!</p>
            <span class="time-left">11:01</span>
          </div>
          
          <div class="container">
            <img  alt="Avatar">
            <p>Sweet! So, what do you wanna do today?</p>
            <span class="time-right">11:02</span>
          </div>
          
          <div class="container darker">
            <img  alt="Avatar" class="right">
            <p>Nah, I dunno. Play soccer.. or learn more coding perhaps?</p>
            <span class="time-left">11:05</span>
          </div>
    </div>
    <li>
        <div id="divPaging">
            <div>◀</div>
            <div><BUtton></BUtton></div>
            <div><BUtton></BUtton></div>
            <div><BUtton></BUtton></div>
            <div><BUtton></BUtton></div>
            <div><BUtton></BUtton></div>
            <div>▶</div> 
            <div id="openRoomBtn">
                <button>방개설</button>
>>>>>>> branch 'main' of https://github.com/Santa-Project/Santa.git
            </div> 
        </div>
    </li>
        
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>