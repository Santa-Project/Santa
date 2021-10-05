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
<script src="${contextPath}/resources/libs/jquery-3.5.1.min.js"></script>
<script src="${contextPath}/resources/libs/jquery.easing.1.3.js"></script>
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
                           		<button id="my_introduce_edit" class="my_introdue_button2">edit</button>   <!-- 누르면 true  -->
                           	</div>
                           	<div >
                            	<form id="profileEdit"  method="post" enctype="multipart/form-data" action ="/mypage/editprofile" >
                            		<input id="my_introduce_photo" class="my_introdue_button"  name="profilephoto" type="file"><!--사진파일 -->
                            		<button id="my_introduce_save" class="my_introdue_button" type="submit">저장</button> <!-- post  -->
                            		<button id="my_introduce_cancel" class="my_introdue_button" onclick="history.back();">취소</button>   <!-- 누르면 false  -->
                           			<input type="text" id="inputedit" name="profilecomment" value="${authentication.profileContent}">
                           		</form><!--컨트롤러단에서 if(사진==null)이라면 coment만 변경, else 둘다변경  -->
                           			
                            </div>
                            <div id="my_introduce_comment">${authentication.profileContent}</div>	
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
        
        <div class="mypage_board2">
            <div class="board_write_form2">
                <div class="board_write_title2">
                    <div id="board_write_title_item">게시글</div></div>
                <div class="board_write_content2">
                    <c:if test="false">
                    </c:if>
                    <c:if test="true">
                       <div class="board_add">
                           <i class="fas fa-plus"></i><a href="/mypage/mypageWriteBoard"> 게시글 작성</a>
                       </div>
                    </c:if>
               
               <c:forEach items='${res}' var='objectArr' varStatus="status">
                    <div class="board_content_item">
                        <div class="board_content_padding">
                            <div class="board_content_picture">
                                <img class="board_content_picture_item" src="http://localhost:7070/file/${objectArr[1].savePath}${objectArr[1].renameFileName}">
                                <form action="/mypage/deleteBoard" method="post">
                                   <input type='hidden' name='deleteboard' value="${objectArr[0].memBoardIdx}" >
                                   <input type='submit' style="border-color:white; border-radius: 20%;" value='삭제'>
                               </form>
                            </div>
                            <div class="board_content_side">
                                <div class="board_content_side_ment_padding">
                                    <div id="board_content_side_ment" >${objectArr[0].boardComment}
                                    </div>
                                </div>
                                <div class="board_content_side_item">
                                <span class="like">
                                 <c:if test="${not empty authentication}">
		                                <c:if test="${!boardlike}">
                               				<i class="fas fa-heart" style="color:red "></i> ${objectArr[0].liked}
	                                    </c:if>
		                                <c:if test="${boardlike}">
		                                    <i class="far fa-heart" style="color:red "></i> ${objectArr[0].liked}
		                                </c:if>
                                 </c:if>
                                 </span>
                                    <div class="board_content_side_item1">${objectArr[0].uploadTime} ,</div>
                                    <div class="board_content_side_item1">${objectArr[0].mtRegion} ,</div>
                                    <div class="board_content_side_item1">${objectArr[0].mtName}</div>
                                </div>
                                <div class="board_content_side_rep_input">
                                   <div class="input_id" style="color:green">${authentication.nickname}</div>
                                   <form action="/mypage/insertComment" method="post">
                                       <input type="text" id="input_text"name="content"><input type="hidden" name="boardIdx" value="${objectArr[0].memBoardIdx}">
                                       <button type="submit" id="input_text_submit"> <i class="fas fa-plus"></i></button>
                                   </form>
                                </div>
                                
                                <div class="board_content_side_rep">
                                
                                   <c:forEach items='${objectArr[2]}' var='comment'>
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



<script type="text/javascript">


$("#my_introduce_edit").click(function(){
	$('.my_introdue_button, #inputedit').addClass('active');
	document.querySelector("#my_introduce_comment").style.display ='none';
})

/* document.querySelector("#my_introduce_edit").addEventListener('click',function(e){
	console.dir("edit활성")
	$('.my_introdue_button, #inputedit').addClass('active');
}) */

document.querySelector("#my_introduce_save").addEventListener('click',function(e){
	
	$('#profileEdit').submit();
	$('#profileEdit').action("/mypage/editprofile");
	document.querySelector("#my_introduce_comment").style.display ='inline';
})

document.querySelector("#my_introduce_cancel").addEventListener('click',function(e){
	$('.my_introdue_button').removeClass('active');
	document.querySelector("#my_introduce_comment").style.display= 'inline';
});







/* 좋아요 */
	(function(){
	
		var form = document.createElement("form"); /* <form method="post" action="/mypage/like"> */
		form.setAttribute("method", "post");  
		document.body.appendChild(form);
		
		if(${not empty authentication}) {
			document.querySelector(".like").addEventListener('click',function(e){
				
				let membaordIdx = document.createElement("input");
				membaordIdx.name = "membaordIdx";
				membaordIdx.setAttribute("value","${objectArr[0].memBoardIdx}");
				membaordIdx.setAttribute("type","hidden");
				form.appendChild(membaordIdx); 
				/* <input name="memboardIdx" value="${objectArr[0].memBoardIdx}" type="hidden"> */
				
				let like = document.createElement("input");
				like.name = "boardlike";
				like.setAttribute("type","hidden");
				form.appendChild(like);
				/* <input type="hidden" name="boardlike" value="${!sessionScope.boardlike}"> */
				
				if("${boardlike}"){
					like.setAttribute("value","${!boardlike}");

				} else {
					like.setAttribute("value","${boardlike}");
				}
				
				form.setAttribute("action", "/mypage/like"); //요청 보낼 주소
				form.submit();
		
			})
		}
		

	})();









</script>

</html>
