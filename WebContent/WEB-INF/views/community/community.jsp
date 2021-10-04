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
                                    <img src="${contextPath}/resources/img/community/rank1.jpg" alt="">

                                    <div class="content">
                                        <h3>${member.userId}</h3>
                                        <form action="/mypage/anotherBoard?anotherIdx=${member.memberIdx}"
                                            method="POST">
                                            <button><i class="fas fa-home"></i></button>
                                        </form>
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
                    <span>인기게시글</span>
                </div>
                <div class="hot_box_bottom">
                <c:set var="i" value="1"/>
                <c:forEach items='${res}' var='objectArr' varStatus="status">
                    <div class="hot${i}">
                        <img id="recent_img" class="hot_open${i}" src="${contextPath}/file/${objectArr[1].savePath}${objectArr[1].renameFileName}">
                        <div class="hot_modal${i}">
                            <div class="modal_content">
                                <img class="hot_photo${i}" src="${contextPath}/file/${objectArr[1].savePath}${objectArr[1].renameFileName}">
                                <div class="desc">
                                    <div class="desc_header">
                                        <p>${objectArr[0].boardComment}</p>
                                        <button class="btn_close">&times;</button>
                                    </div>
                                    <div class="time">${objectArr[0].uploadTime} ,</div>
                                    <div class="region">${objectArr[0].mtRegion} ,</div>
                                    <div class="mtname">${objectArr[0].mtName}</div>
                                    <div class="desc_content">
                                        <form action="/community/insertComment" method="post">
                                       		<input type="text"name="content"><input type="hidden" name="boardIdx" value="${objectArr[0].memBoardIdx}">
                                       		<button type="submit" >작성하기 </button>
                                   		</form>
                                        <div class="button_wrap">
                                            <div class="heart">

                                                <!-- 로그인되었을 때만 하트 보이게 처리 -->
                                                <c:if test="${not empty authentication}">
                                                    <i class="fas fa-heart"></i>
                                                </c:if>

                                            </div>
                                        </div>
                                        <div class="comment_wrap">
                                            <div class="comment" id="1">
                                                
                                                <c:forEach items='${objectArr[2]}' var='comment'>
                                                
                                                <a>${comment.nickname}</a>  ${comment.content}
                                                </c:forEach>
                                      
                                            </div>
                                        </div>
                                        <div class="comment_wrap">
                                            <div class="comment" id="1">
                                                <c:forEach items='${objectArr[2]}' var='comment'>
                                                
                                                <a>${comment.nickname}</a>  ${comment.content}
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="overlay"></div>
                    </div>
                    	<c:set var="i" value="${i + 1}"/> 
                    </c:forEach>

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
                                                    <div class="userID"><a href="#"> aaaa </a></div>
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
                                                    <div class="userID"><a href="#"> aaaa </a></div>
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
                                                    <div class="userID"><a href="#"> aaaa </a></div>
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
                                                    <div class="userID"><a href="#"> aaaa </a></div>
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
                                                    <div class="userID"><a href="#"> aaaa </a></div>
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
                                                    <div class="userID"><a href="#"> aaaa </a></div>
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
                                                    <div class="userID"><a href="#"> aaaa </a></div>
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
                                                    <div class="userID"><a href="#"> aaaa </a></div>
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
            $('.hot_modal1, .overlay').addClass('active')
        })

        $('.hot_open2').click(function () {
            $('.hot_modal2, .overlay').addClass('active')
        })

        $('.hot_open3').click(function () {
            $('.hot_modal3, .overlay').addClass('active')
        })

        $('.hot_open4').click(function () {
            $('.hot_modal4, .overlay').addClass('active')
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
            $('.hot_modal1,.hot_modal2,.hot_modal3,.hot_modal4, .modal1, .modal2, .modal3, .modal4, .modal5, .modal6, .modal7, .modal8, .overlay')
                .removeClass(
                    'active')
        })
        $('.heart .fas, .comment_heart .fas').click(function () {
            $(this).toggleClass('active')
        })
    </script>
</body>

</html>