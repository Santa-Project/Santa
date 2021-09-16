<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${contextPath}/resources/css/match/match.css">


</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	 <div>
           
            
            <div class="photo">
                <a class="prof" href="">산타산타</a>
                <a class="prof" href="">사진</a>
            </div>
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
            <a href="">종</a>
            <a href="">점</a>
        </div>
    </div>
    <div id="search">
        <input type="button" name="" id="">
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
            </div> 
        </div> 
       
    </li>
        
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>