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
                        <img id="selfie" src="http://localhost:7070/file/${authentication.photo}">
                        <div id="my_introduce">
                            <div id="my_introduce_id_padding">
                           		<div id="my_introduce_id">${authentication.nickname} (${authentication.userId})</div>
                           		<button id="my_introduce_edit" class="my_introdue_button">edit</button>   <!-- 누르면 true  -->
                           	</div>
                           	<div style="margin-left:13px;">
                            	<c:if test="true">
                            		<form action ="/mypage/editprofile" method="post" enctype="multipart/form-data" >
	                            		<input id="my_introduce_photo" class="my_introdue_button"  name="profilephoto" type="file"><!--사진파일 -->
	                            		<button id="my_introduce_save" class="my_introdue_button" type="submit">저장</button> <!-- post  -->
	                            		<button id="my_introduce_cancel" class="my_introdue_button">취소</button>   <!-- 누르면 false  -->
                            			<input type="text" name="profilecomment" value="${authentication.profileContent}">
                            		</form><!--컨트롤러단에서 if(사진==null)이라면 coment만 변경, else 둘다변경  -->
                            	</c:if>
                            	<c:if test="false">
                            		<div id="my_introduce_comment">${authentication.profileContent}</div>		
                            	</c:if>
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
                                	<i class="fas fa-map-marker-alt" style="margin-right: 10px;"></i>${wishlist.mountainName}
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
	                					<option value="${mountain.mtIdx}">${mountain.mountainName}</option>
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
                    <div id="board_write_title_item">FOLLOWER</div></div>
                <div class="board_write_content">
                
                 <c:forEach items='${followerList}' var='f'>
                    <div class="follow_padding">
                        <div class="follow_member">
                            <div id="follow_member_selfie"><!-- img로 변경 ,경로 urc= ${f.userId} --></div>
                         </div>
                         <div class="follow_member2">
                        <div class="follow_member_id_padding">
	                        <form id="idForm" action="/mypage/anotherBoard" method="post">
		                         <input type="hidden" name="anotherIdx" value="${f.memberIdx}">
		                         <button type="submit" id="follow_member_id" >${f.nickname} (${f.userId})</button> 
	                        </form>
                        </div>
                       </div>
                     </div>
                 </c:forEach>
                     
                </div>
            </div>
        </div>
         </div>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>


</body>
</html>