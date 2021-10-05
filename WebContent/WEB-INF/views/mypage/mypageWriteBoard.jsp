<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<link rel="stylesheet" href="${contextPath}/resources/css/mypage/mypage.css">

</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

    <div class="mypage_header">
        <div class="my_nav_item_margin1"></div>
        <div class="my_nav_item_margin2">
			<div id="my_nav_item1"><a href="/mypage/mypageBoard">게시물</a></div>
            <div id="my_nav_item2"><a href="/mypage/mypageFollow">팔로우</a></div>
            <div id="my_nav_item3"><a href="/mypage/mypageFollower">팔로워</a></div>
            <div id="my_nav_item4"><a href="/mypage/mypageMemberEdit">마이페이지 수정</a></div>
        </div>
        <div class="my_nav_item_margin3"></div>
    </div>

<section id="section1">
 <div class="mypage_section">
        <div class="mypage_profile">
            <div class="my_profile_padding">
                <div class="my_profile1">
                        <img id="selfie" src="http://localhost:7070/file/${authentication.profilePhoto}">
                        <div id="my_introduce">
                            <div id="my_introduce_id_padding">
                           		<div id="my_introduce_id">${authentication.nickname} (${authentication.userId})</div>
                           		<button id="my_introduce_edit" class="my_introdue_button">edit</button>   <!-- 누르면 true  -->
                           	</div>
                           		<div style="margin-left:13px;">
                            		<div id="my_introduce_comment">${authentication.profileContent}</div>	
                            </div>
                        </div>
                </div>
                
                <div class="my_profile2">
                    <div id="my_wish_mountian"><i class="fas fa-thumbtack" style="margin-right: 15px;"></i> 희망 산 리스트</div>
                    <div id="my_wish_mountian_list">
                        <div id="my_wish_mountian_list_items" >
                            <ul>
                            <c:forEach items='${wishlist}' var='wishlist' varStatus="status">
                                <li id="my_wish_mountian_list_item">
                                	<i class="fas fa-map-marker-alt" style="margin-right: 10px;"></i>${wishlist.mtName}
                                	<form action ="/mypage/deleteMountainwish" method="post" >
                                	<input type="hidden" name="deletewish" value="${wishlist.mtIdx}">
                                	<button type="submit" style="color:red; margin-left: 10px;"><i class="far fa-minus-square"></i></button>
                                	</form>
                                </li>
                            </c:forEach>
                            </ul>
                        </div>
                     <c:if test="true">
                         <form action ="/mypage/insertMountainwish" id="my_wish_input" method="post">
                                <select  id='mountain_name'name="insertwish">
                                     <c:forEach items="${mountainList}" var="mountain" varStatus="status">
	                					<option value="${mountain.mtIdx}">${mountain.mtName}</option>
	               					 </c:forEach>
                                </select >
                                <button type="submit"id="search_button">+</button>
                            </form>
                        </c:if>
					</div> 
                </div> 
            </div>
        </div>
        
                <div class="mypage_board">
            <div class="board_write_form">
                <div class="board_write_title">
                    <div id="board_write_title_item">게시글 작성하기</div></div>
                <div class="board_write_content">
                    <form class="form-horizontal" action="/mypage/insertBoard" method="post" enctype="multipart/form-data" >

                        <div class="form-group">
                            <label for="inputEmail3" class="col-sm-2 control-label">산이름</label>
                            <div class="col-xs-3">
                            	<select class="form-control" id='mountain_name'name='mountlist'>
                                     <c:forEach items="${mountainList}" var="mountain" varStatus="status">
	                					<option value="${mountain.mtName}">${mountain.mtName}</option>
	               					 </c:forEach>
                                </select >
                            </div> 
                        </div>
                        <div class="form-group">
                            <label for="inputEmail3" class="col-sm-2 control-label">지역</label>
                            <div class="col-xs-3">
                            	<select class="form-control" name="region">
                            	<c:forEach items="${seoulcity}" var="seoulcity" >
                            		<option value="${seoulcity}">${seoulcity}</option>
                            	</c:forEach>
                            	</select>
                            </div> 
                        </div>
                        
                        <div class="form-group">
                          <label for="inputPassword3" class="col-sm-2 control-label">내용</label>
                          <div class="col-sm-10">
                            <textarea class="form-control" rows="15" name="writetext"></textarea>
                          </div>
                        </div>

                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">이미지</label>
                            <div>
                                <input type="file" name="writefile">
                            </div>
                          </div>
                        <div class="form-group">
                          <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default" value="전송">작성하기</button>
                             <a class="btn btn-default" href="/mypage/mypageBoard">취소</a>
                          </div>
                        </div>
                      </form>
                </div>
            </div>
        </div>
         </div>
</section>
<script type="text/javascript" src="${contextPath}/resources/js/mypageUpload.js"></script>
<script type="text/javascript">



</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>


</body>
</html>