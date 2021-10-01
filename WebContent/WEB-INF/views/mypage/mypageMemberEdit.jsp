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
                    <div id="board_write_title_item">회원정보 수정</div></div>
                <div class="board_write_content">
                    <div class="member_edit_form">
                    <form class="mapage_info_edit" action="/mypage/editMember" method="post">
                        <table class="mypage_info_edit_form" style="table-layout:fixed">
                            <tr>
                               <td >아이디</td>
                                <td>${authentication.userId}
                                </td>
                            </tr>
                            <tr>
                                <td>현재 비밀번호</td>
                                <td><input type="password" value="${authentication.userPassword}"></td>
                            </tr>
                            <tr>
                                <td>변경할 비밀번호</td>
                                <td><input type="password" name="editpass" >
                                	<c:if test="${not empty param.err and not empty joinValid.password}">
	              						비밀번호는 영어, 숫자,특수문자(#?!@$ %^&*-) 조합의 8글자 이상의 문자열입니다.
	            					</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>비밀번호 확인</td>
                                <td><input type="password">
                               		<c:if test="${not empty param.err and not empty joinValid.verifyPassword}">
	              						입력하신 비밀번호와 일치하지 않습니다.
	           						</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>변경할 닉네임</td>
                                <td><input type="text" name='editnickname' value="${authentication.nickname}"></td>
                            </tr>
                            <tr>
                                <td>성별</td>
                                <td>
                                ${authentication.gender}
                                </td>
                            </tr>
                            <tr>
                                <td>변경할 연락처</td>
                                <td><input type="tel" name='editphone'  value="${authentication.phone}">
                                	<c:if test="${not empty param.err and not empty joinValid.mobile}">
	              					전화번호는 '-'을 포함하지 않은 숫자(9~11) 조합입니다.
	           					</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>변경할 이메일</td>
                                <td><input type="email"  name='editmail'  value="${authentication.email}">
                               		<c:if test="${not empty param.err and not empty joinValid.email}">
              						유효한 이메일을 입력해주세요.
            						</c:if>
                                </td>
                            </tr>
                            <tr >
                                <td rowspan ="3">변경할 주소</td>
                                <td>
                                	<input type="button" onClick="goPopup();" value="주소검색">
                                	<input type="text" id="zipNo" name="zipNo" readonly="readonly"
                                	<c:if test="${not empty param.err}">
				                		value="${joinForm.zipNo}"
				                	</c:if>
				                	>
	            				</td>
                            </tr>
            				<tr>
	            				<td>
	            					<input type="text" id="roadAddrPart1" class="int" name="editaddress" placeholder="주소" readonly="readonly" value="${authentication.address}"
	            					<c:if test="${not empty param.err}">
                						value="${joinForm.roadAddrPart1}"
                					</c:if>
									>
					 			</td>
           					</tr>
            				<tr>
	            				<td>
	            					<input type="text" id="detailaddress" class="int" name="" maxlength="100" placeholder="상세주소" readonly="readonly"
									<c:if test="${not empty param.err}">
									   value="${joinForm.detailaddress}"
				                	</c:if>
					                >
	            				</td>
                            </tr>
                        </table>
                        <div class="mypage_edit_button">
                        <button type="submit"style=" width:70px;">수정하기</button>
                        <a id="mypage_edit_exit" style="border-color: white; background-color: lightgray; width:80px;" href="/mypage/leaveSanta"> 회원탈퇴 </a>
                        </div>
                        
                    </form>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
			var pop;
			
			var goPopup = function(){
				//경로는 시스템에 맞게 수정하여 사용
				//호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
				pop = window.open("/main/joinform/address","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
			}
			var jusoCallBack = function(roadFullAddr,roadAddrPart1,engAddr, jibunAddr, zipNo, addrDetail){
			 document.form.roadAddrPart1.value = roadAddrPart1;
			 document.form.zipNo.value = zipNo;
			 document.form.detailaddress.value = addrDetail;
		 	 pop.close();
			}
			
			
			
			
			
		</script>
         </div>
</section>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>


</body>
</html>