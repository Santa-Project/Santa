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
        <div class="my_nav_item_margin2"></div>
        <div class="my_nav_item_margin3"></div>
    </div>

<section id="section1">

 <div class="mypage_section">
        <div class="mypage_profile">
            <div class="my_profile_padding">
                <div class="my_profile1">
                        <div id="selfie">
                            <!--  <img src="/img.png" alt="사진영역" > -->
                        </div>
                        <div id="my_introduce">
                            <div id="my_introduce_id_padding">
                            <c:set var='others'/>
                            <div id="my_introduce_id">You_Ri</div>

                            	<c:if test="true">
                            		<form>
                            			<button style="color:green" id='following'>follow</button>
                            			<input type='hidden' name='insertfollow' value='상대방idx'>
                            		</form>
                            	</c:if>
                            	<c:if test="false">
		                           	<form>
		                            	<button style="color:red"  id='unfollowing'>unfollow</button>
		                            	<input type='hidden' name='deletefollow' value='상대방idx'>
		                           	</form>
                            	</c:if>
                            </div>
                            <div id="my_introduce_comment">상쾌한 북한산~♡ 아 좋당~~~   최대 30글자 허용</div>
                        </div>
                </div>
                
                <div class="my_profile2">
                    <div id="my_wish_mountian"><i class="fas fa-thumbtack" style="margin-right: 15px;"></i> 희망 산 리스트</div>
                    <div id="my_wish_mountian_list">
                        <div id="my_wish_mountian_list_item" >
                            <ul>
                                <li><i class="fas fa-map-marker-alt" style="margin-right: 10px;"></i>시베리아<button style="margin-left: 10px;"><i class="far fa-minus-square"></i></button></li>
                                <li><i class="fas fa-map-marker-alt" style="margin-right: 10px;"></i>한라산<button style="margin-left: 10px;"><i class="far fa-minus-square"></i></button></li>
                                <li><i class="fas fa-map-marker-alt" style="margin-right: 10px;"></i>백두산<button style="margin-left: 10px;"><i class="far fa-minus-square"></i></button></li>
                                <li><i class="fas fa-map-marker-alt" style="margin-right: 10px;"></i>지리산<button style="margin-left: 10px;"><i class="far fa-minus-square"></i></button></li>
                            </ul>
                        </div>
					</div> 
                </div> 
            </div>
        </div>
        
        <div class="mypage_board2">
            <div class="board_write_form2">
                <div class="board_write_title2">
                    <div id="board_write_title_item">게시글</div></div>
                <div class="board_write_content2">
					
					<c:forEach items='${others}' var='objectArr' varStatus="status"> <!-- 다른사람 게시판 -->
                    <div class="board_content_item">
                        <div class="board_content_padding">
                            <div class="board_content_picture">

                                <img class="board_content_picture_item" src="http://localhost:7070/file/${objectArr[1].savePath}${objectArr[1].renameFileName}">
                            </div>
                            <div class="board_content_side">
                                <div class="board_content_side_ment_padding">
                                    <div id="board_content_side_ment" >${objectArr[0].boardComment}
                                    </div>
                                </div>
                                <div class="board_content_side_item">
                                
                                <c:if test="true">
                                <button class="board_content_side_item1" style="color:red "><i class="fas fa-heart"></i>${objectArr[0].liked}</button>
                                   </c:if>
                          		 <c:if test="false">
                                    <button class="board_content_side_item1" style="color:red "><i class="far fa-heart"></i>${objectArr[0].liked}</button>
                           		</c:if>
                                
                                    <div class="board_content_side_item1">${objectArr[0].mtRegion}</div>
                                    <div class="board_content_side_item1">${objectArr[0].mtMountain}</div>
                                </div>
                                <div class="board_content_side_rep_input">
                                <div class="input_id" style="color:green">${authentication.nickname}</div><!-- 내닉네임 -->
                                <form action="/mypage/insertComment" method="post">
                                    <input type="text" id="input_text"name="content"><input type="hidden" name="boardIdx" value="${objectArr[0].boardIdx}">
                                    <button type="submit" id="input_text_submit"> <i class="fas fa-plus"></i></button>
                                </form>
                                </div>
                                <div class="board_content_side_rep">
                                
                                <c:forEach items='${objectArr[2]}' var='comment'>
	                                    <div class="board_content_side_rep_item">
	                                    
	                                    	<c:if test="${comment.nickname != sessionScope.nickname}" > <!-- 남이 쓴 댓글 아이디 클릭시 -->
		                                        <form id="idForm" action="/mypage/anotherBoard" method="post">
			                                        <label>
			                                        <input type="hidden" name="anotherIdx" value="${comment.memberIdx}">
													<button type="submit" class="input_id">${comment.nickname}</button>
													</label>
		                                        </form>
	                                        </c:if>
	                                        <c:if test="${comment.nickname == sessionScope.nickname}" > <!-- 내가 쓴 댓글 아이디 클릭시 -->
	                                        	<a class="input_id" href="mypage/mypageBoard">${comment.nickname}</a>
	                                        </c:if>
	                                        
		                                    <div class="input_content">${comment.content}</div>
		                                    <c:if test="${comment.nickname == sessionScope.nickname}" > <!-- 내가 쓴 댓글 삭제 -->
		                                        <form action="/mypage/deleteComment" method="post">
			                                        <a style="color:red; margin-left: 10px;" class="far fa-minus-square" onclick="if(!confirm('댓글을 삭제하시겠습니까?')){return false;}"></a>
			                                        <input type="hidden" name="deletecomment" value="${comment.commentIdx}">
		                                    	</form>
	                                    	</c:if>
	                                    </div>
									</c:forEach>
                                    
                                </div>
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

