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
        <c:if test="true">
            <div id="my_nav_item1"><a href="/test_st/mypage/mypageBoard">게시물</a></div>
            <div id="my_nav_item2"><a href="/test_st/mypage/mypageFollow">팔로우</a></div>
            <div id="my_nav_item3"><a href="/test_st/mypage/mypageFollower">팔로워</a></div>
            <div id="my_nav_item4"><a href="/test_st/mypage/mypageMemberEdit">마이페이지 수정</a></div>
        </c:if>
        <c:if test="false">
        </c:if>
        </div>
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
                            <div id="my_introduce_id"><a href="">You_Ri</a></div>
                            <c:if test="true">
                            	<c:if test="true">
                            	<button style="color:green">follow/언팔</button>
                            	</c:if>
                            </c:if>
                           <c:if test="false">
                            <button class="hidden1" id="my_introduce_edite" >edit</button>
                            <button class="hidden1" id="my_introduce_edite" >저장</button>
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
                    <c:if test="false">
                     </c:if>
                     <c:if test="true">
                         <form action ="/마운틴인포에서 db찾으러감 a_서블릿/쿠키 참고" id="my_wish_input"  class="hidden1" >
                                <input type="text" name="search" id="search" list="lang" style="width: 100px;height:20px ;">
                                <datalist id='mountain_name'>
                                    <option value="지리산"/>
                                    <option value="어쩌고"/>
                                    <option value="저쩌고"/>
                                    <option value="블라블라"/>
                                    <option value="등등등"/>
                                </datalist>
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
                        	<i class="fas fa-plus"></i><a href="/test_st/mypage/mypageWriteBoard"> 게시글 작성하기</a>
                    	</div>
                    </c:if>

                    <div class="board_content_item">
                        <div class="board_content_padding">
                            <div class="board_content_picture">
                                <div class="board_content_picture_item"></div>
                                <button></button>
                                <button></button>
                            </div>
                            <div class="board_content_side">
                                <div class="board_content_side_ment_padding">
                                    <div id="board_content_side_ment">인왕산...!!
                                    </div>
                                </div>
                                <div class="board_content_side_item">
                                
                                <c:if test="true">
                                <button class="board_content_side_item1" style="color:red "><i class="fas fa-heart"></i>100</button>
                                   </c:if>
                          		 <c:if test="false">
                                    <button class="board_content_side_item1" style="color:red "><i class="far fa-heart"></i>100</button>
                           		</c:if>
                                
                                    <div class="board_content_side_item1">서울특별시 서대문구,</div>
                                    <div class="board_content_side_item1">인왕산</div>
                                </div>
                                <div class="board_content_side_rep_input">
                                    <a class="input_id" style="color:green">You_Ri</a>
                                    <input type="text" id="input_text">
                                    <button id="input_text_submit"> <i class="fas fa-plus"></i></button>
                                </div>
                                <div class="board_content_side_rep">
                                    <div class="board_content_side_rep_item">
                                        <a class="input_id">chuna</a>
                                        <div class="input_content">우와 경치 좋다!</div>
                                    </div>
                                    <div class="board_content_side_rep_item">
                                        <a class="input_id">chuna</a>
                                        <div class="input_content">오오</div>
                                    </div>
                                    <div class="board_content_side_rep_item">
                                        <a class="input_id">chuna</a>
                                        <div class="input_content">저기 어디야?</div>
                                    </div>
                                    <div class="board_content_side_rep_item">
                                        <a class="input_id">chuna</a>
                                        <div class="input_content">우와 경치 좋다!</div>
                                    </div>
                                    <div class="board_content_side_rep_item">
                                        <a class="input_id">chuna</a>
                                        <div class="input_content">우와 경치 좋다!</div>
                                    </div>
                                    <div class="board_content_side_rep_item">
                                        <a class="input_id">chuna</a>
                                        <div class="input_content">우와 경치 좋다!</div>
                                    </div>
                                    <div class="board_content_side_rep_item">
                                        <a class="input_id">chuna</a>
                                        <div class="input_content">우와 경치 좋다!</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    
                    <div class="board_content_item">
                        <div class="board_content_padding">
                            <div class="board_content_picture">
                                <div class="board_content_picture_item"></div>
                                <button></button>
                                <button></button>
                            </div>
                            <div class="board_content_side">
                                <div class="board_content_side_ment_padding">
                                    <div id="board_content_side_ment">인왕산...!!
                                    </div>
                                </div>
                                <div class="board_content_side_item">
                                
                                <c:if test="true">
                                <button class="board_content_side_item1" style="color:red "><i class="fas fa-heart"></i></button>
                                   </c:if>
                          		 <c:if test="false">
                                    <button class="board_content_side_item1" style="color:red "><i class="far fa-heart"></i></button>
                           		</c:if>
                                
                                    <div class="board_content_side_item1">서울특별시 서대문구,</div>
                                    <div class="board_content_side_item1">인왕산</div>
                                </div>
                                <div class="board_content_side_rep_input">
                                    <div class="input_id"><a style="color:green">You_Ri</a></div>
                                    <input type="text" id="input_text">
                                    <button id="input_text_submit"> <i class="fas fa-plus"></i></button>
                                </div>
                                <div class="board_content_side_rep">
                                    <div class="board_content_side_rep_item">
                                        <div class="input_id"><a>chuna</a></div>
                                        <div class="input_content">우와 경치 좋다!</div>
		                                 <c:if test="true">
		                                	<button class="input_liked" style="color:red "><i class="fas fa-heart"></i></button>
		                                 </c:if>
		                          		 <c:if test="false">
		                                    <button class="input_liked" style="color:red "><i class="far fa-heart"></i></button>
		                           	     </c:if>
                                    </div>
                                    <div class="board_content_side_rep_item">
                                        <div class="input_id"><a>suna</a></div>
                                        <div class="input_content">오오</div>
		                                 <c:if test="true">
		                                	<button class="input_liked" style="color:red "><i class="fas fa-heart"></i></button>
		                                 </c:if>
		                          		 <c:if test="false">
		                                    <button class="input_liked" style="color:red "><i class="far fa-heart"></i></button>
		                           	     </c:if>
                                    </div>
                                    <div class="board_content_side_rep_item">
                                        <div class="input_id">una</div>
                                        <div class="input_content">저기 어디야?</div>
                                        <c:if test="true">
		                                	<button class="input_liked" style="color:red "><i class="fas fa-heart"></i></button>
		                                 </c:if>
		                          		 <c:if test="false">
		                                    <button class="input_liked" style="color:red "><i class="far fa-heart"></i></button>
		                           	     </c:if>
                                    </div>
                                    <div class="board_content_side_rep_item">
                                        <div class="input_id"><a>chuna</a></div>
                                        <div class="input_content">우와 경치 좋다!</div>
                                        <c:if test="true">
		                                	<button class="input_liked" style="color:red "><i class="fas fa-heart"></i></button>
		                                 </c:if>
		                          		 <c:if test="false">
		                                    <button class="input_liked" style="color:red "><i class="far fa-heart"></i></button>
		                           	     </c:if>
                                    </div>
                                    <div class="board_content_side_rep_item">
                                        <div class="input_id"><a>chuna</a></div>
                                        <div class="input_content">우와 경치 좋다!</div>
                                        <c:if test="true">
		                                	<button class="input_liked" style="color:red "><i class="fas fa-heart"></i></button>
		                                 </c:if>
		                          		 <c:if test="false">
		                                    <button class="input_liked" style="color:red "><i class="far fa-heart"></i></button>
		                           	     </c:if>
                                    </div>
                                    <div class="board_content_side_rep_item">
                                        <div class="input_id"><a>chuna</a></div>
                                        <div class="input_content">우와 경치 좋다!</div>
                                        <c:if test="true">
		                                	<button class="input_liked" style="color:red "><i class="fas fa-heart"></i></button>
		                                 </c:if>
		                          		 <c:if test="false">
		                                    <button class="input_liked" style="color:red "><i class="far fa-heart"></i></button>
		                           	     </c:if>
                                    </div>
                                    <div class="board_content_side_rep_item">
                                        <div class="input_id"><a>chuna</a></div>
                                        <div class="input_content">우와 경치 좋다!</div>
                                        <c:if test="true">
		                                	<button class="input_liked" style="color:red "><i class="fas fa-heart"></i></button>
		                                 </c:if>
		                          		 <c:if test="false">
		                                    <button class="input_liked" style="color:red "><i class="far fa-heart"></i></button>
		                           	     </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    
                </div>
            </div>
        </div>
         </div>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>



</body>
</html>