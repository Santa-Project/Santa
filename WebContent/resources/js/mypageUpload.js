/**
 * img정규표현
 */
$("#boardimg").on("change",function(e){
	
	if(!e.type.match("image*")){
		alert("이미지만 첨부할 수 있습니다");
		$("#boardimg").val('');
		return;
	}
	
	if(e.size >사이즈 ){
		alert("사이즈 이하만 가능합니다");
		$('#boardimg').val('');
		return;
	}
})