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
                         <img id="selfie" src="http://localhost:7070/file/${anotherMember.photo}">
                        <div id="my_introduce">
                            <div id="my_introduce_id_padding">
                            <div id="my_introduce_id">${anotherMember.nickname} (${anotherMember.userId})</div>

                            	<c:if test="false">
                            		<form action ="/mypage/insertFollow" method="post">
                            			<button style="background-color:rgb(110, 180, 110);" id='following'>follow</button>
                            			<input type='hidden' name='insertfollow' value='${anotherMember.memberIdx}'>
                            		</form>
                            	</c:if>
                            	<c:if test="true">
		                           	<form action ="/mypage/deleteFollow" method="post">
		                            	<button style="background-color:lightgrey"  id='following'>unfollow</button>
		                            	<input type='hidden' name='deletefollow' value='${anotherMember.memberIdx}'>
		                           	</form>
                            	</c:if>
                            </div>
                            <div id="my_introduce_comment">${anotherMember.profileContent}</div>
                        </div>
                </div>
                
                <div class="my_profile2">
                    <div id="my_wish_mountian"><i class="fas fa-thumbtack" style="margin-right: 15px;"></i> 희망 산 리스트</div>
                    <div id="my_wish_mountian_list" >
                        <div id="my_wish_mountian_list_items" >
                            <ul id="my_wish_mountian_list_item">
                            <c:forEach items='${wishlist}' var='wishlist' varStatus="status">
                                <li><i class="fas fa-map-marker-alt" style="margin-right: 10px;"></i>${wishlist.mountainName}<button style="color:red; margin-left: 10px;"><i class="far fa-minus-square"></i></button></li>
                            </c:forEach>
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
					
					<c:forEach items="${others}" var='others' varStatus="status"> <!-- 다른사람 게시판 -->

                      <div class="board_content_item">
                        <div class="board_content_padding">
                            <div class="board_content_picture">
                                <img class="board_content_picture_item" src="http://localhost:7070/file/${others[1].savePath}${others[1].renameFileName}">
                            </div>
                            <div class="board_content_side">
                                <div class="board_content_side_ment_padding">
                                    <div id="board_content_side_ment" >${others[0].boardComment}
                                    </div>
                                </div>
                                <div class="board_content_side_item">
                                
                                <c:if test="true">
                                <button class="board_content_side_item1" style="color:red "><i class="fas fa-heart"></i>${others[0].liked}</button>
                                   </c:if>
                                 <c:if test="false">
                                    <button class="board_content_side_item1" style="color:red "><i class="far fa-heart"></i>${others[0].liked}</button>
                                 </c:if>
                                    <div class="board_content_side_item1">${objectArr[0].uploadDatetime} ,</div>
                                    <div class="board_content_side_item1">${objectArr[0].mtRegion} ,</div>
                                    <div class="board_content_side_item1">${objectArr[0].mtMountain}</div>
                                </div>
                                <div class="board_content_side_rep_input">
                                   <div class="input_id" style="color:green">${authentication.nickname}</div>
                                   <form action="/mypage/insertComment" method="post">
                                       <input type="text" id="input_text"name="content"><input type="hidden" name="boardIdx" value="${others[0].boardIdx}">
                                       <button type="submit" id="input_text_submit"> <i class="fas fa-plus"></i></button>
                                   </form>
                                </div>
                                
                                <div class="board_content_side_rep">
                                
                                   <c:forEach items='${others[2]}' var='comment'>
                                       <div class="board_content_side_rep_item">
                                       
                                          <c:if test="${comment.nickname != authentication.nickname}" > <!-- 남이 쓴 댓글 아이디 클릭시 -->
                                              <form id="idForm" action="/mypage/anotherBoard" method="post">
                                                 <label>
                                                 <input type="hidden" name="anotherIdx" value="${comment.memberIdx}">
                                       <button type="submit" class="input_id">${comment.nickname}</button>
                                       </label>
                                              </form>
                                           </c:if>
                                           <c:if test="${comment.nickname == authentication.nickname}" > <!-- 내가 쓴 댓글 아이디 클릭시 -->
                                              <a class="input_id" style="color:green" href="/mypage/mypageBoard">${comment.nickname}</a>
                                           </c:if>
                                           
                                          <div class="input_content">${comment.content}</div>
                                          <c:if test="${comment.nickname == authentication.nickname}" > <!-- 내가 쓴 댓글 삭제 -->
                                                 <form action="/mypage/deleteComment" method="post" >
                                                  <label>
                                                       <input type="hidden" name="commentIdx" value="${comment.commentIdx}">
			                                          	<button style="color:red; margin-left: 10px;" type="submit" id="delete" >
			                                           <i class="far fa-minus-square" ></i></button>
                                                 </label>
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

