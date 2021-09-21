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
                    <input type="text" placeholder="      검색어를 입력하세요">
                    <a href=""><i class="fas fa-search"></i></a>
                </div>
            </div>
            <div class="searchbox2">
                <div class="search_content"><h1>소통하고 싶은 유저와 원하는 게시물을 검색해보세요!</h1></div>
            </div>
        </div>
        <div class="rank_user">
            <div class="rank_user1"></div>
            <div class="rank_user2">
                <div class="rank_box_top">
                    <span>명예의 전당</span>
                </div>
                <div class="rank_box_bottom">
                    <div class="rank1">
                        <div class="rank_title">
                            <i class="fas fa-trophy"> 1위</i>
                        </div>
                        <div class="rank_card">
                            <img src="/resources/img/community/rank1.jpg" alt="">
                            <div class="content">
                                <h3>@umhonggil</h3>
                                <a href="#"><i class="fas fa-home"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="rank2">
                        <div class="rank_title">
                            <i class="fas fa-trophy"> 2위</i>
                        </div>
                        <div class="rank_card">
                            <img src="/resources/img/community/rank1.jpg" alt="">
                            <div class="content">
                                <h3>@honggil</h3>
                                <a href="#"><i class="fas fa-home"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="rank3">
                        <div class="rank_title">
                            <i class="fas fa-trophy"> 3위</i>
                        </div>
                        <div class="rank_card">
                            <img src="/resources/img/community/rank1.jpg" alt="">
                            <div class="content">
                                <h3>@umhong</h3>
                                <a href="#"><i class="fas fa-home"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="rank4">
                        <span><i class="fas fa-medal"></i> 4위 user</span>
                        <span><i class="fas fa-medal"></i> 5위 user</span>
                        <span><i class="fas fa-medal"></i> 6위 user</span>
                        <span><i class="fas fa-medal"></i> 7위 user</span>
                        <span><i class="fas fa-medal"></i> 8위 user</span>
                        <span><i class="fas fa-medal"></i> 9위 user</span>
                    </div>
                </div>
            </div>
            <div class="rank_user3">
                <div class="mypage_wrapper">
                    <a href=""><i class="fas fa-users"></i><span>My Page</span></a>
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
                                <span>#매주등산 #등산후막걸리 <hr> #등린이 #등산친구구함</span>
                            </div>
                        </div>
                        <div class="hot_photo">
                            <div class="hot_photo_wrapper">
                                <a href="">
                                    <img src="/resources/img/community/mt1.jpg">
                                    <img src="/resources/img/community/mt2.jpg">
                                    <img src="/resources/img/community/mt3.jpg">
                                </a>
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
                                <span>#매주등산 #등산후막걸리 <hr> #등린이 #등산친구구함</span>
                            </div>
                        </div>
                        <div class="hot_photo">
                            <div class="hot_photo_wrapper">
                                <a href="">
                                    <img src="/resources/img/community/mt1.jpg">
                                    <img src="/resources/img/community/mt2.jpg">
                                    <img src="/resources/img/community/mt3.jpg">
                                </a>
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
                                <span>#매주등산 #등산후막걸리 <hr> #등린이 #등산친구구함</span>
                            </div>
                        </div>
                        <div class="hot_photo">
                            <div class="hot_photo_wrapper">
                                <a href="">
                                    <img src="/resources/img/community/mt1.jpg">
                                    <img src="/resources/img/community/mt2.jpg">
                                    <img src="/resources/img/community/mt3.jpg">
                                </a>
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
                                <span>#매주등산 #등산후막걸리 <hr> #등린이 #등산친구구함</span>
                            </div>
                        </div>
                        <div class="hot_photo">
                            <div class="hot_photo_wrapper">
                                <a href="">
                                    <img src="/resources/img/community/mt1.jpg">
                                    <img src="/resources/img/community/mt2.jpg">
                                    <img src="/resources/img/community/mt3.jpg">
                                </a>
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
                            <img src="/resources/img/community/mt1.jpg" class="btn_open" id="recent_img">
                            <div class="modal">
                                <div class="modal_content">
                                    <div class="photo"></div>
                                    <div class="desc">
                                        <div class="desc_header">
                                            <h3>작성한 제목</h3>
                                            <p>작성한 글 내용 들어갈 자리</p>
                                            <button class="btn_close">&times;</button>
                                        </div>
                                        <div class="desc_content">
                                            <input type="text" placeholder="댓글을 입력하세요.">
                                            <button>작성?1111?</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="overlay"></div>
                        </div>
                        <div class="recent2">
                            <img src="/resources/img/community/mt2.jpg" class="btn_open" id="recent_img">
                            <div class="modal">
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
                                            <button>작성!!22</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="overlay"></div>
                        </div>
                        <div class="recent3">
                            <img src="/resources/img/community/mt3.jpg" class="btn_open" id="recent_img">
                            <div class="modal">
                                <div class="modal_content">
                                    <div class="photo"></div>
                                    <div class="desc">
                                        <div class="desc_header">
                                            <h3>작성한 제목</h3>
                                            <p>작성한 글 내용 들어갈 자리</p>
                                            <button class="btn_close">&times;</button>
                                        </div>
                                        <div class="desc_content">
                                            <input type="text" placeholder="댓글을 입력하세요.">
                                            <button>작성하기333</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="overlay"></div>
                        </div>
                        <div class="recent4">
                            <img src="/resources/img/community/mt4.jpg" class="btn_open" id="recent_img">
                            <div class="modal">
                                <div class="modal_content">
                                    <div class="photo"></div>
                                    <div class="desc">
                                        <div class="desc_header">
                                            <h3>작성한 제목</h3>
                                            <p>작성한 글 내용 들어갈 자리</p>
                                            <button class="btn_close">&times;</button>
                                        </div>
                                        <div class="desc_content">
                                            <input type="text" placeholder="댓글을 입력하세요.">
                                            <button>작성하기444</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="overlay"></div>
                        </div>
                    </div>
                    <div class="recent_box_bottom_second">
                        <div class="recent5">
                            <img src="/resources/img/community/mt5.jpg" class="btn_open" id="recent_img">
                            <div class="modal">
                                <div class="modal_content">
                                    <div class="photo"></div>
                                    <div class="desc">
                                        <div class="desc_header">
                                            <h3>작성한 제목</h3>
                                            <p>작성한 글 내용 들어갈 자리</p>
                                            <button class="btn_close">&times;</button>
                                        </div>
                                        <div class="desc_content">
                                            <input type="text" placeholder="댓글을 입력하세요.">
                                            <button>작성하기555</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="overlay"></div>
                        </div>
                        <div class="recent6">
                            <img src="/resources/img/community/mt6.jpg" class="btn_open" id="recent_img">
                            <div class="modal">
                                <div class="modal_content">
                                    <div class="photo"></div>
                                    <div class="desc">
                                        <div class="desc_header">
                                            <h3>작성한 제목</h3>
                                            <p>작성한 글 내용 들어갈 자리</p>
                                            <button class="btn_close">&times;</button>
                                        </div>
                                        <div class="desc_content">
                                            <input type="text" placeholder="댓글을 입력하세요.">
                                            <button>작성하666</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="overlay"></div>
                        </div>
                        <div class="recent7">
                            <img src="/resources/img/community/mt7.jpg" class="btn_open" id="recent_img">
                            <div class="modal">
                                <div class="modal_content">
                                    <div class="photo"></div>
                                    <div class="desc">
                                        <div class="desc_header">
                                            <h3>작성한 제목</h3>
                                            <p>작성한 글 내용 들어갈 자리</p>
                                            <button class="btn_close">&times;</button>
                                        </div>
                                        <div class="desc_content">
                                            <input type="text" placeholder="댓글을 입력하세요.">
                                            <button>작성7777</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="overlay"></div>
                        </div>
                        <div class="recent8">
                            <img src="/resources/img/community/mt8.jpg" class="btn_open" id="recent_img">
                            <div class="modal">
                                <div class="modal_content">
                                    <div class="photo"></div>
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
                                                <button>작성 88</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="overlay8"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="recent_b3"></div>
        </div>
        <div class="top_button">
            <a href="#"><i class="fas fa-arrow-circle-up"></i>
                <h1>TOP</h1></a>
        </div>
    </section>
    
<%@include file= "/WEB-INF/views/common/footer.jsp"%>

	<script>
	    $('.btn_open').click(function(){
	        $('.modal, .overlay').addClass('active')
	    })
	    $('.btn_close, .overlay').click(function(){
	        $('.modal, .overlay').removeClass('active')
	    })
	    $('.heart .fas').click(function(){
        $(this).toggleClass('active')
    })
	</script>
</body>
</html>