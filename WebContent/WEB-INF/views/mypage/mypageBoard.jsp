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
        <c:if test="false">
            <div id="my_nav_item1"><a href="mypageBoard.html">게시물</a></div>
            <div id="my_nav_item2"><a href="mypageFollow.html">팔로우</a></div>
            <div id="my_nav_item3"><a href="mypageFollower.html">팔로워</a></div>
            <div id="my_nav_item4"><a href="mypageMemberEdit.html">마이페이지 수정</a></div>
        </c:if>
        <c:if test="true">
            <div id="my_nav_item1"><a href="mypageBoard.html">게시물</a></div>
            <div id="my_nav_item2"><a href="mypageFollow.html">팔로우</a></div>
            <div id="my_nav_item3"><a href="mypageFollower.html">팔로워</a></div>
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
                            <img src="/img.png" alt="사진영역"  style="width:170px; height:170px;">
                        </div>
                        <div id="my_introduce">
                            <div id="my_introduce_id_padding">
                            <div id="my_introduce_id"><a href="">You_Ri</a></div>
                            <c:if test="true">
                            </c:if>
                           <c:if test="false">
                            <input type="file" id="selfie_button">
                            <button class="hidden1" id="my_introduce_edite" >comment</button>
                            </c:if>
                            </div>
                            <div id="my_introduce_comment">상쾌한 북한산~♡ 아 좋당~~~   최대 30글자 허용</div>
                        </div>
                </div>
                
                <div class="my_profile2">
                    <div id="my_wish_mountian"><i class="fas fa-thumbtack"></i> 희망 산 리스트</div>
                    <div id="my_wish_mountian_list">
                        <oi id="my_wish_mountian_list_item">
                            <li>시베리아</li>
                            <li>한라산</li>
                            <li>백두산</li>
                            <li>최대 다섯개 허용</li>
                            <li>js로 개수만큼 생성</li>
                        </oi>
                    </div>
                    <div class="hidden1">
                    <c:if test="true">
                     </c:if>
                     <c:if test="false">
                        <input type="search" id="search_bar">
                        <button type="submit"id="search_button">+</button>
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
                    <div class="board_add">
                     <c:if test="true">
                     </c:if>
                     <c:if test="false">
                    <div class="hidden1">
                        <i class="fas fa-plus"></i><a href="mypageWriteBoard copy.html">   게시글 작성하기</a>
                    </div>
                    </c:if>
                    </div>
                    <div class="board_content_item">
                        <div class="board_content_padding">
                            <div class="board_content_picture">
                                <div class="board_content_picture_item"></div>
                                <button></button>
                                <button></button>
                            </div>
                            <div>글
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div class="board_content">
                        <div class="board_content_padding">
                            
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