<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <%@include file= "/WEB-INF/views/include/head.jsp"%>
    <link rel="stylesheet" href="${contextPath}/resources/css/common/header.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/community/community.css">
</head>

<body>
    <%@include file= "/WEB-INF/views/common/header.jsp"%>

    <section>
        <div class="search_wrapper">
            <div class="searchbox1">
                <div class="search">
                    <input type="text" placeholder="검색어를 입력하세요">
                    <a href=""><i class="fas fa-search"></i></a>
                </div>
            </div>
            <div class="searchbox2">
                <div class="search_content">
                    <h1>소통하고 싶은 유저와 원하는 게시물을 검색해보세요!</h1>
                </div>
            </div>
        </div>
        <div class="rank_user">
            <div class="rank_user1"></div>
            <div class="rank_user2">
                <div class="rank_box_top">
                    <span>명예의 전당</span>
                </div>
                <div class="rank_box_bottom">
                    <c:forEach items='${memberArr}' var='member' varStatus="status">
                        <c:if test="${status.index < 3}">
                            <div class="rank1">
                                <div class="rank_title">
                                    <i class="fas fa-trophy"> ${status.count}위</i>
                                </div>
                                <div class="rank_card">
                                    <%-- <img src="${member.photo}" alt=""> --%>
                                    <img src="${contextPath}/resources/img/community/rank1.jpg" alt="">

                                    <div class="content">
                                        <h3>${member.userId}</h3>
                                        <form action="/mypage/anotherBoard?anotherIdx=${member.memberIdx}"
                                            method="POST">
                                            <button><i class="fas fa-home"></i></button>
                                        </form><!-- <i class="fas fa-home"></i> -->
                                        <%-- <a href="/mypage/anotherBoard?anotherIdx=${member.memberidx}"><i class="fas fa-home"></i></a> --%>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                    <div class="rank4">
                        <c:forEach items='${memberArr}' var='member' varStatus='status'>
                            <c:if test='${status.index >= 3 }'>
                                <span><i class="fas fa-medal"></i><a href="?userid=${member.userId}">${status.count}위
                                        ${member.userId}</a></span>
                            </c:if>
                        </c:forEach>
                    </div>

                </div>
            </div>
            <div class="rank_user3">
                <div class="mypage_wrapper">
                    <a href="/mypage/mypageBoard"><i class="fas fa-users"></i><span>My Page</span></a>
                </div>
            </div>

        </div>
        <div class="hot_board">
            <div class="hot_user1"></div>
            <div class="hot_user2">
                <div class="hot_box_top">
                    <span>인기 유저 & 게시글</span>
                </div>
                <div class="hot_box_bottom">
                    <div class="hot1">
                        <div class="hot_content_box">
                            <div class="hot_profile">
                                <a href=""><img src="/resources/img/community/mt1.jpg"></a>
                            </div>
                            <div class="hot_content">
                                <h3>산할아버지</h3>
                                <span>#매주등산 #등산후막걸리 #등린이 #등산친구구함</span>
                            </div>
                        </div>
                        <div class="hot_photo">
                            <div class="hot_photo_wrapper">
                                <div class="hot_photo1">
                                    <img src="http://localhost:7070/file/${objectArr[1].savePath}${objectArr[1].renameFileName}" class="hot_open1">
                                    <div class="hotmodal1">
                                        <div class="modal_content">
                                            <div class="hotphoto1"></div>
                                            <div class="desc">
                                                <div class="desc_header">
                                                    <p>${objectArr[0].boardComment}</p>
                                                    <button class="btn_close">&times;</button>
                                                </div>
                                                <div class="desc_content">
												    <form action="/mypage/insertComment" method="post"></form>
												    
												    <div class="button_wrap">
												        <div class="heart">
												            <i class="fas fa-heart"></i>
												        </div>
												        <!-- <button type="submit" id="input_text_submit">작성하기 핫1</button> -->
												        
												        <form action="/mypage/insertComment" method="post">
	                                    					<input type="text" id="input_text"name="content" placeholder="댓글을 입력하세요"><input type="hidden" name="boardIdx" value="${objectArr[0].boardIdx}">
	                                  					 	<button type="submit" id="input_text_submit">작성하기 핫1</button>
	                                					</form>
												    </div>
												    
												    <div class="comment_wrap">
												        <div class="comment" id="1">
															<c:forEach items='${objectArr[2]}' var='comment'>
															    <div class="board_content_side_rep_item">
															
															        <c:if test="${comment.nickname != authentication.nickname}">
															            <!-- 남이 쓴 댓글 아이디 클릭시 -->
															            <form id="idForm" action="/mypage/anotherBoard" method="post">
															                <label>
															                    <input type="hidden" name="anotherIdx" value="${comment.memberIdx}">
															                    <button type="submit" class="input_id">${comment.nickname}</button>
															                </label>
															            </form>
															        </c:if>
															        <c:if test="${comment.nickname == authentication.nickname}">
															            <!-- 내가 쓴 댓글 아이디 클릭시 -->
															            <a class="input_id" href="/mypage/mypageBoard">${comment.nickname}</a>
															        </c:if>
															
															        <div class="input_content">${comment.content}${comment.commentIdx}</div>
															        <c:if test="${comment.nickname == authentication.nickname}">
															            <!-- 내가 쓴 댓글 삭제 -->
															            <form action="/mypage/deleteComment" method="post">
															                <label>
															                    <input type="hidden" name="commentIdx" value="${comment.commentIdx}">
															                    ${comment.commentIdx}
															                    <button style="color:red; margin-left: 10px;" type="submit" id="delete">
															                        <i class="far fa-minus-square"></i></button>
															                </label>
															            </form>
															        </c:if>
															    </div>
															</c:forEach>
												        </div>
												    </div>
												</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="overlay"></div>
                                </div>
                                <div class="hot_photo2">
                                    <img src="/resources/img/community/mt1.jpg" class="hot_open2">
                                    <div class="hotmodal2">
                                        <div class="modal_content">
                                            <div class="hotphoto2"></div>
                                            <div class="desc">
                                                <div class="desc_header">
                                                    <h3>작성한 제목</h3>
                                                    <p>작성한 글 내용 들어갈 자리</p>
                                                    <button class="btn_close">&times;</button>
                                                </div>
                                                <div class="desc_content">
                                                    <input type="text" placeholder="댓글을 입력하세요.">
                                                    <div class="button_wrap">
                                                        <div class="heart">
                                                            <i class="fas fa-heart"></i>
                                                        </div>
                                                        <button>작성하기 핫2</button>
                                                    </div>
                                                    <div class="comment_wrap">
                                                        <div class="comment" id="1">
                                                            <div class="userID"><a href="#"> JM Yoon</a></div>
                                                            <div class="comment_cont">GOOOOOD</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="overlay"></div>
                                </div>
                                <div class="hot_photo3">
                                    <img src="/resources/img/community/mt1.jpg" class="hot_open3">
                                    <div class="hotmodal3">
                                        <div class="modal_content">
                                            <div class="hotphoto3"></div>
                                            <div class="desc">
                                                <div class="desc_header">
                                                    <h3>작성한 제목</h3>
                                                    <p>작성한 글 내용 들어갈 자리</p>
                                                    <button class="btn_close">&times;</button>
                                                </div>
                                                <div class="desc_content">
                                                    <input type="text" placeholder="댓글을 입력하세요.">
                                                    <div class="button_wrap">
                                                        <div class="heart">
                                                            <i class="fas fa-heart"></i>
                                                        </div>
                                                        <button>작성하기 핫3</button>
                                                    </div>
                                                    <div class="comment_wrap">
                                                        <div class="comment" id="1">
                                                            <div class="userID"><a href="#"> JM Yoon</a></div>
                                                            <div class="comment_cont">GOOOOOD</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="overlay"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="hot2">
                        <div class="hot_content_box">
                            <div class="hot_profile">
                                <a href=""><img src="/resources/img/community/mt1.jpg"></a>
                            </div>
                            <div class="hot_content">
                                <h3>산할아버지</h3>
                                <span>#매주등산 #등산후막걸리 #등린이 #등산친구구함</span>
                            </div>
                        </div>
                        <div class="hot_photo">
                            <div class="hot_photo_wrapper">
                                <div class="hot_photo4">
                                    <img src="/resources/img/community/mt1.jpg" class="hot_open4">
                                    <div class="hotmodal4">
                                        <div class="modal_content">
                                            <div class="hotphoto4"></div>
                                            <div class="desc">
                                                <div class="desc_header">
                                                    <h3>작성한 제목</h3>
                                                    <p>작성한 글 내용 들어갈 자리</p>
                                                    <button class="btn_close">&times;</button>
                                                </div>
                                                <div class="desc_content">
                                                    <input type="text" placeholder="댓글을 입력하세요.">
                                                    <div class="button_wrap">
                                                        <div class="heart">
                                                            <i class="fas fa-heart"></i>
                                                        </div>
                                                        <button>작성하기 핫4</button>
                                                    </div>
                                                    <div class="comment_wrap">
                                                        <div class="comment" id="1">
                                                            <div class="userID"><a href="#"> JM Yoon</a></div>
                                                            <div class="comment_cont">GOOOOOD</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="overlay"></div>
                                </div>
                                <div class="hot_photo5">
                                    <img src="/resources/img/community/mt1.jpg" class="hot_open5">
                                    <div class="hotmodal5">
                                        <div class="modal_content">
                                            <div class="hotphoto5"></div>
                                            <div class="desc">
                                                <div class="desc_header">
                                                    <h3>작성한 제목</h3>
                                                    <p>작성한 글 내용 들어갈 자리</p>
                                                    <button class="btn_close">&times;</button>
                                                </div>
                                                <div class="desc_content">
                                                    <input type="text" placeholder="댓글을 입력하세요.">
                                                    <div class="button_wrap">
                                                        <div class="heart">
                                                            <i class="fas fa-heart"></i>
                                                        </div>
                                                        <button>작성하기 핫5</button>
                                                    </div>
                                                    <div class="comment_wrap">
                                                        <div class="comment" id="1">
                                                            <div class="userID"><a href="#"> JM Yoon</a></div>
                                                            <div class="comment_cont">GOOOOOD</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="overlay"></div>
                                </div>
                                <div class="hot_photo6">
                                    <img src="/resources/img/community/mt1.jpg" class="hot_open6">
                                    <div class="hotmodal6">
                                        <div class="modal_content">
                                            <div class="hotphoto6"></div>
                                            <div class="desc">
                                                <div class="desc_header">
                                                    <h3>작성한 제목</h3>
                                                    <p>작성한 글 내용 들어갈 자리</p>
                                                    <button class="btn_close">&times;</button>
                                                </div>
                                                <div class="desc_content">
                                                    <input type="text" placeholder="댓글을 입력하세요.">
                                                    <div class="button_wrap">
                                                        <div class="heart">
                                                            <i class="fas fa-heart"></i>
                                                        </div>
                                                        <button>작성하기 핫6</button>
                                                    </div>
                                                    <div class="comment_wrap">
                                                        <div class="comment" id="1">
                                                            <div class="userID"><a href="#"> JM Yoon</a></div>
                                                            <div class="comment_cont">GOOOOOD</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="overlay"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="hot3">
                        <div class="hot_content_box">
                            <div class="hot_profile">
                                <a href=""><img src="/resources/img/community/mt1.jpg"></a>
                            </div>
                            <div class="hot_content">
                                <h3>산할아버지</h3>
                                <span>#매주등산 #등산후막걸리 #등린이 #등산친구구함</span>
                            </div>
                        </div>
                        <div class="hot_photo">
                            <div class="hot_photo_wrapper">
                                <div class="hot_photo7">
                                    <img src="/resources/img/community/mt1.jpg" class="hot_open7">
                                    <div class="hotmodal7">
                                        <div class="modal_content">
                                            <div class="hotphoto7"></div>
                                            <div class="desc">
                                                <div class="desc_header">
                                                    <h3>작성한 제목</h3>
                                                    <p>작성한 글 내용 들어갈 자리</p>
                                                    <button class="btn_close">&times;</button>
                                                </div>
                                                <div class="desc_content">
                                                    <input type="text" placeholder="댓글을 입력하세요.">
                                                    <div class="button_wrap">
                                                        <div class="heart">
                                                            <i class="fas fa-heart"></i>
                                                        </div>
                                                        <button>작성하기 핫7</button>
                                                    </div>
                                                    <div class="comment_wrap">
                                                        <div class="comment" id="1">
                                                            <div class="userID"><a href="#"> JM Yoon</a></div>
                                                            <div class="comment_cont">GOOOOOD</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="overlay"></div>
                                </div>
                                <div class="hot_photo8">
                                    <img src="/resources/img/community/mt1.jpg" class="hot_open8">
                                    <div class="hotmodal8">
                                        <div class="modal_content">
                                            <div class="hotphoto8"></div>
                                            <div class="desc">
                                                <div class="desc_header">
                                                    <h3>작성한 제목</h3>
                                                    <p>작성한 글 내용 들어갈 자리</p>
                                                    <button class="btn_close">&times;</button>
                                                </div>
                                                <div class="desc_content">
                                                    <input type="text" placeholder="댓글을 입력하세요.">
                                                    <div class="button_wrap">
                                                        <div class="heart">
                                                            <i class="fas fa-heart"></i>
                                                        </div>
                                                        <button>작성하기 핫8</button>
                                                    </div>
                                                    <div class="comment_wrap">
                                                        <div class="comment" id="1">
                                                            <div class="userID"><a href="#"> JM Yoon</a></div>
                                                            <div class="comment_cont">GOOOOOD</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="overlay"></div>
                                </div>
                                <div class="hot_photo9">
                                    <img src="/resources/img/community/mt1.jpg" class="hot_open9">
                                    <div class="hotmodal9">
                                        <div class="modal_content">
                                            <div class="hotphoto9"></div>
                                            <div class="desc">
                                                <div class="desc_header">
                                                    <h3>작성한 제목</h3>
                                                    <p>작성한 글 내용 들어갈 자리</p>
                                                    <button class="btn_close">&times;</button>
                                                </div>
                                                <div class="desc_content">
                                                    <input type="text" placeholder="댓글을 입력하세요.">
                                                    <div class="button_wrap">
                                                        <div class="heart">
                                                            <i class="fas fa-heart"></i>
                                                        </div>
                                                        <button>작성하기 핫9</button>
                                                    </div>
                                                    <div class="comment_wrap">
                                                        <div class="comment" id="1">
                                                            <div class="userID"><a href="#"> JM Yoon</a></div>
                                                            <div class="comment_cont">GOOOOOD</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="overlay"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="hot4">
                        <div class="hot_content_box">
                            <div class="hot_profile">
                                <a href=""><img src="/resources/img/community/mt1.jpg"></a>
                            </div>
                            <div class="hot_content">
                                <h3>산할아버지</h3>
                                <span>#매주등산 #등산후막걸리 #등린이 #등산친구구함</span>
                            </div>
                        </div>
                        <div class="hot_photo">
                            <div class="hot_photo_wrapper">
                                <div class="hot_photo10">
                                    <img src="/resources/img/community/mt1.jpg" class="hot_open10">
                                    <div class="hotmodal10">
                                        <div class="modal_content">
                                            <div class="hotphoto10"></div>
                                            <div class="desc">
                                                <div class="desc_header">
                                                    <h3>작성한 제목</h3>
                                                    <p>작성한 글 내용 들어갈 자리</p>
                                                    <button class="btn_close">&times;</button>
                                                </div>
                                                <div class="desc_content">
                                                    <input type="text" placeholder="댓글을 입력하세요.">
                                                    <div class="button_wrap">
                                                        <div class="heart">
                                                            <i class="fas fa-heart"></i>
                                                        </div>
                                                        <button>작성하기 핫10</button>
                                                    </div>
                                                    <div class="comment_wrap">
                                                        <div class="comment" id="1">
                                                            <div class="userID"><a href="#"> JM Yoon</a></div>
                                                            <div class="comment_cont">GOOOOOD</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="overlay"></div>
                                </div>
                                <div class="hot_photo11">
                                    <img src="/resources/img/community/mt1.jpg" class="hot_open11">
                                    <div class="hotmodal11">
                                        <div class="modal_content">
                                            <div class="hotphoto11"></div>
                                            <div class="desc">
                                                <div class="desc_header">
                                                    <h3>작성한 제목</h3>
                                                    <p>작성한 글 내용 들어갈 자리</p>
                                                    <button class="btn_close">&times;</button>
                                                </div>
                                                <div class="desc_content">
                                                    <input type="text" placeholder="댓글을 입력하세요.">
                                                    <div class="button_wrap">
                                                        <div class="heart">
                                                            <i class="fas fa-heart"></i>
                                                        </div>
                                                        <button>작성하기 핫11</button>
                                                    </div>
                                                    <div class="comment_wrap">
                                                        <div class="comment" id="1">
                                                            <div class="userID"><a href="#"> JM Yoon</a></div>
                                                            <div class="comment_cont">GOOOOOD</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="overlay"></div>
                                </div>
                                <div class="hot_photo12">
                                    <img src="/resources/img/community/mt1.jpg" class="hot_open12">
                                    <div class="hotmodal12">
                                        <div class="modal_content">
                                            <div class="hotphoto12"></div>
                                            <div class="desc">
                                                <div class="desc_header">
                                                    <h3>작성한 제목</h3>
                                                    <p>작성한 글 내용 들어갈 자리</p>
                                                    <button class="btn_close">&times;</button>
                                                </div>
                                                <div class="desc_content">
                                                    <input type="text" placeholder="댓글을 입력하세요.">
                                                    <div class="button_wrap">
                                                        <div class="heart">
                                                            <i class="fas fa-heart"></i>
                                                        </div>
                                                        <button>작성하기 핫12</button>
                                                    </div>
                                                    <div class="comment_wrap">
                                                        <div class="comment" id="1">
                                                            <div class="userID"><a href="#"> JM Yoon</a></div>
                                                            <div class="comment_cont">GOOOOOD</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="overlay"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="hot_user3"></div>
        </div>
        <div class="recent_board">
            <div class="recent_b1"></div>
            <div class="recent_b2">
                <div class="recent_box_top">
                    <span>최근 게시글</span>
                </div>
                <div class="recent_box_bottom">
                    <div class="recent_box_bottom_first">
                        <div class="recent1">
                            <img src="/resources/img/community/mt1.jpg" class="btn_open1" id="recent_img">
                            <div class="modal1">
                                <div class="modal_content">
                                    <div class="photo1"></div>
                                    <div class="desc">
                                        <div class="desc_header">
                                            <h3>작성한 제목</h3>
                                            <p>작성한 글 내용 들어갈 자리</p>
                                            <button class="btn_close">&times;</button>
                                        </div>
                                        <div class="desc_content">
                                            <input type="text" placeholder="댓글을 입력하세요.">
                                            <div class="button_wrap">
                                                <div class="heart">

                                                    <!-- 로그인되었을 때만 하트 보이게 처리 -->
                                                    <c:if test="${not empty authentication}">
                                                        <i class="fas fa-heart"></i>
                                                    </c:if>

                                                </div>
                                                <button>작성하기 11</button>
                                            </div>
                                            <div class="comment_wrap">
                                                <div class="comment" id="1">
                                                    <div class="userID"><a href="#"> JM Yoon</a></div>
                                                    <div class="comment_cont">GOOOOOD</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="overlay"></div>
                        </div>
                        <div class="recent2">
                            <img src="/resources/img/community/mt2.jpg" class="btn_open2" id="recent_img">
                            <div class="modal2">
                                <div class="modal_content">
                                    <div class="photo2"></div>
                                    <div class="desc">
                                        <div class="desc_header">
                                            <h3>작성한 제목</h3>
                                            <p>작성한 글 내용 들어갈 자리</p>
                                            <button class="btn_close">&times;</button>
                                        </div>
                                        <div class="desc_content">
                                            <input type="text" placeholder="댓글을 입력하세요.">
                                            <div class="button_wrap">
                                                <div class="heart"><i class="fas fa-heart"></i></div>
                                                <button>작성하기 22</button>
                                            </div>
                                            <div class="comment_wrap">
                                                <div class="comment" id="1">
                                                    <div class="userID"><a href="#"> JM Yoon</a></div>
                                                    <div class="comment_cont">GOOOOOD</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="overlay"></div>
                        </div>
                        <div class="recent3">
                            <img src="/resources/img/community/mt3.jpg" class="btn_open3" id="recent_img">
                            <div class="modal3">
                                <div class="modal_content">
                                    <div class="photo3"></div>
                                    <div class="desc">
                                        <div class="desc_header">
                                            <h3>작성한 제목</h3>
                                            <p>작성한 글 내용 들어갈 자리</p>
                                            <button class="btn_close">&times;</button>
                                        </div>
                                        <div class="desc_content">
                                            <input type="text" placeholder="댓글을 입력하세요.">
                                            <div class="button_wrap">
                                                <div class="heart"><i class="fas fa-heart"></i></div>
                                                <button>작성하기 33</button>
                                            </div>
                                            <div class="comment_wrap">
                                                <div class="comment" id="1">
                                                    <div class="userID"><a href="#"> JM Yoon</a></div>
                                                    <div class="comment_cont">GOOOOOD</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="overlay"></div>
                        </div>
                        <div class="recent4">
                            <img src="/resources/img/community/mt4.jpg" class="btn_open4" id="recent_img">
                            <div class="modal4">
                                <div class="modal_content">
                                    <div class="photo4"></div>
                                    <div class="desc">
                                        <div class="desc_header">
                                            <h3>작성한 제목</h3>
                                            <p>작성한 글 내용 들어갈 자리</p>
                                            <button class="btn_close">&times;</button>
                                        </div>
                                        <div class="desc_content">
                                            <input type="text" placeholder="댓글을 입력하세요.">
                                            <div class="button_wrap">
                                                <div class="heart"><i class="fas fa-heart"></i></div>
                                                <button>작성하기 44</button>
                                            </div>
                                            <div class="comment_wrap">
                                                <div class="comment" id="1">
                                                    <div class="userID"><a href="#"> JM Yoon</a></div>
                                                    <div class="comment_cont">GOOOOOD</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="overlay"></div>
                        </div>
                    </div>
                    <div class="recent_box_bottom_second">
                        <div class="recent5">
                            <img src="/resources/img/community/mt5.jpg" class="btn_open5" id="recent_img">
                            <div class="modal5">
                                <div class="modal_content">
                                    <div class="photo5"></div>
                                    <div class="desc">
                                        <div class="desc_header">
                                            <h3>작성한 제목</h3>
                                            <p>작성한 글 내용 들어갈 자리</p>
                                            <button class="btn_close">&times;</button>
                                        </div>
                                        <div class="desc_content">
                                            <input type="text" placeholder="댓글을 입력하세요.">
                                            <div class="button_wrap">
                                                <div class="heart"><i class="fas fa-heart"></i></div>
                                                <button>작성하기 55</button>
                                            </div>
                                            <div class="comment_wrap">
                                                <div class="comment" id="1">
                                                    <div class="userID"><a href="#"> JM Yoon</a></div>
                                                    <div class="comment_cont">GOOOOOD</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="overlay"></div>
                        </div>
                        <div class="recent6">
                            <img src="/resources/img/community/mt6.jpg" class="btn_open6" id="recent_img">
                            <div class="modal6">
                                <div class="modal_content">
                                    <div class="photo6"></div>
                                    <div class="desc">
                                        <div class="desc_header">
                                            <h3>작성한 제목</h3>
                                            <p>작성한 글 내용 들어갈 자리</p>
                                            <button class="btn_close">&times;</button>
                                        </div>
                                        <div class="desc_content">
                                            <input type="text" placeholder="댓글을 입력하세요.">
                                            <div class="button_wrap">
                                                <div class="heart"><i class="fas fa-heart"></i></div>
                                                <button>작성하기 66</button>
                                            </div>
                                            <div class="comment_wrap">
                                                <div class="comment" id="1">
                                                    <div class="userID"><a href="#"> JM Yoon</a></div>
                                                    <div class="comment_cont">GOOOOOD</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="overlay"></div>
                        </div>
                        <div class="recent7">
                            <img src="/resources/img/community/mt7.jpg" class="btn_open7" id="recent_img">
                            <div class="modal7">
                                <div class="modal_content">
                                    <div class="photo7"></div>
                                    <div class="desc">
                                        <div class="desc_header">
                                            <h3>작성한 제목</h3>
                                            <p>작성한 글 내용 들어갈 자리</p>
                                            <button class="btn_close">&times;</button>
                                        </div>
                                        <div class="desc_content">
                                            <input type="text" placeholder="댓글을 입력하세요.">
                                            <div class="button_wrap">
                                                <div class="heart"><i class="fas fa-heart"></i></div>
                                                <button>작성하기 77</button>
                                            </div>
                                            <div class="comment_wrap">
                                                <div class="comment" id="1">
                                                    <div class="userID"><a href="#"> JM Yoon</a></div>
                                                    <div class="comment_cont">GOOOOOD</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="overlay"></div>
                        </div>
                        <div class="recent8">
                            <img src="/resources/img/community/mt8.jpg" class="btn_open8" id="recent_img">
                            <div class="modal8">
                                <div class="modal_content">
                                    <div class="photo8"></div>
                                    <div class="desc">
                                        <div class="desc_header">
                                            <h3>작성한 제목</h3>
                                            <p>작성한 글 내용 들어갈 자리</p>
                                            <button class="btn_close">&times;</button>
                                        </div>
                                        <div class="desc_content">
                                            <input type="text" placeholder="댓글을 입력하세요.">
                                            <div class="button_wrap">
                                                <div class="heart"><i class="fas fa-heart"></i></div>
                                                <button>작성하기 88</button>
                                            </div>
                                            <div class="comment_wrap">
                                                <div class="comment" id="1">
                                                    <div class="userID"><a href="#"> JM Yoon</a></div>
                                                    <div class="comment_cont">GOOOOOD</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="overlay"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="recent_b3"></div>
        </div>
        <div class="top_button">
            <a href="#"><i class="fas fa-arrow-circle-up"></i>
                <h1>TOP</h1>
            </a>
        </div>
    </section>

    <%@include file= "/WEB-INF/views/common/footer.jsp"%>

    <script>

        $('.hot_open1').click(function () {
            $('.hotmodal1, .overlay').addClass('active')
        })

        $('.hot_open2').click(function () {
            $('.hotmodal2, .overlay').addClass('active')
        })

        $('.hot_open3').click(function () {
            $('.hotmodal3, .overlay').addClass('active')
        })

        $('.hot_open4').click(function () {
            $('.hotmodal4, .overlay').addClass('active')
        })

        $('.hot_open5').click(function () {
            $('.hotmodal5, .overlay').addClass('active')
        })

        $('.hot_open6').click(function () {
            $('.hotmodal6, .overlay').addClass('active')
        })

        $('.hot_open7').click(function () {
            $('.hotmodal7, .overlay').addClass('active')
        })

        $('.hot_open8').click(function () {
            $('.hotmodal8, .overlay').addClass('active')
        })

        $('.hot_open9').click(function () {
            $('.hotmodal9, .overlay').addClass('active')
        })

        $('.hot_open10').click(function () {
            $('.hotmodal10, .overlay').addClass('active')
        })

        $('.hot_open11').click(function () {
            $('.hotmodal11, .overlay').addClass('active')
        })

        $('.hot_open12').click(function () {
            $('.hotmodal12, .overlay').addClass('active')
        })

        $('.btn_open1').click(function () {
            $('.modal1, .overlay').addClass('active')
        })

        $('.btn_open2').click(function () {
            $('.modal2, .overlay').addClass('active')
        })

        $('.btn_open3').click(function () {
            $('.modal3, .overlay').addClass('active')
        })

        $('.btn_open4').click(function () {
            $('.modal4, .overlay').addClass('active')
        })

        $('.btn_open5').click(function () {
            $('.modal5, .overlay').addClass('active')
        })

        $('.btn_open6').click(function () {
            $('.modal6, .overlay').addClass('active')
        })

        $('.btn_open7').click(function () {
            $('.modal7, .overlay').addClass('active')
        })

        $('.btn_open8').click(function () {
            $('.modal8, .overlay').addClass('active')
        })

        $('.btn_close, .overlay').click(function () {
            $('.hotmodal1,.hotmodal2,.hotmodal3,.hotmodal4,.hotmodal5,.hotmodal6,.hotmodal7,.hotmodal8,.hotmodal9,.hotmodal10, .hotmodal11, .hotmodal12, .modal1, .modal2, .modal3, .modal4, .modal5, .modal6, .modal7, .modal8, .overlay')
                .removeClass(
                    'active')
        })
        $('.heart .fas, .comment_heart .fas').click(function () {
            $(this).toggleClass('active')
        })
    </script>
</body>

</html>