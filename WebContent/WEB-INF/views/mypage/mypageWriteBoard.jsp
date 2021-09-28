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
                        <div id="selfie">
                            <!--  <img src="/img.png" alt="사진영역" > -->
                        </div>
                        <div id="my_introduce">
                            <div id="my_introduce_id_padding">
                           <div id="my_introduce_id">${member.nickname}(${member.userId})</div>
                            <button class="hidden1" id="my_introduce_edite" >edit</button>
                            <button class="hidden1" id="my_introduce_edite" >저장</button>
                            </div>
                            <div id="my_introduce_comment">${member.profileContent}</div>
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
                         <form action ="/" id="my_wish_input"  class="hidden1" >
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
        
                <div class="mypage_board">
            <div class="board_write_form">
                <div class="board_write_title">
                    <div id="board_write_title_item">게시글 작성하기</div></div>
                <div class="board_write_content">
                    <form class="form-horizontal" action="/mypage/insertBoard" method="post" enctype="multipart/form-data" >

                        <div class="form-group">
                            <label for="inputEmail3" class="col-sm-2 control-label">산이름</label>
                            <div class="col-xs-3">
                            	<select class="form-control" name="mountlist">
                            		<option value="지리산">지리산</option>
								    <option value="한라산">한라산</option>
								    <option value="백두산">백두산</option>
                            	</select>
                            </div> 
                        </div>
                        <div class="form-group">
                            <label for="inputEmail3" class="col-sm-2 control-label">지역</label>
                            <div class="col-xs-3">
                            	<select class="form-control" name="region">
                            		<option value="강남구">강남구</option>
								    <option value="관악구">관악구</option>
								    <option value="마포구">마포구</option>
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


<%@ include file="/WEB-INF/views/common/footer.jsp" %>


</body>
</html>