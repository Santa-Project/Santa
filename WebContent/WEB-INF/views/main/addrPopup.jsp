<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>도로명 주소</title>
<%
request.setCharacterEncoding("UTF-8"); //해당시스템의 인코딩타입이 UTF-8일 경우
//한글이 깨지는 경우 주석 제거
String inputYn = request.getParameter("inputYn"); 
String roadFullAddr = request.getParameter("roadFullAddr"); 
String roadAddrPart1 = request.getParameter("roadAddrPart1"); 
String engAddr = request.getParameter("engAddr"); 
String jibunAddr = request.getParameter("jibunAddr"); 
String zipNo = request.getParameter("zipNo");
String addrDetail = request.getParameter("addrDetail"); 
%>
</head>
<script language="javascript">
// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("주소입력화면 소스"도 동일하게 적용시켜야 합니다.)
document.domain = "localhost:7070";
function init(){
	var url = location.href;
	var confmKey = "devU01TX0FVVEgyMDIxMTAwMTE5MTk0NzExMTcxNDA="; // 연계싞청시 부여받은 승인키 입력(테스트용 승인키 : TESTJUSOGOKR) 
	var resultType = "1"; // 도로명주소 검색결과 화면 출력유형, 1 : 도로명, 2 : 도로명+지번+상세보기(관련지번, 관할주민센터), 3 : 도로명+상세보기(상세건물명), 4 : 도로명+지번+상세보기(관련지번, 관할주민센터, 상세건물명) 
	var inputYn= "<%=inputYn%>";
	if(inputYn != "Y"){
		 document.form.confmKey.value = confmKey;
		 document.form.returnUrl.value = url;
		 document.form.resultType.value = resultType;
		 document.form.action="https://www.juso.go.kr/addrlink/addrLinkUrl.do"; //인터넷망(행정망의 경우 별도 문의)
		 document
		 document.form.submit();
	}else{
		window.opener.jusoCallBack("<%=roadFullAddr%>","<%=roadAddrPart1%>","<%=engAddr%>", "<%=jibunAddr%>","<%=zipNo%>","<%=addrDetail%>");
		opener.pop.close();
	}
}
</script>
<body onload="init();">
<form id="form" name="form" method="post">
<input type="hidden" id="confmKey" name="confmKey" value=""/>
<input type="hidden" id="returnUrl" name="returnUrl" value=""/>
<input type="hidden" id="resultType" name= "resultType" value=""/>
</form> 
</body>
</html>
