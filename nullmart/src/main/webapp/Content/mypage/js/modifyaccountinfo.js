/**
 * 
 */

$(document).ready(function() {
	var phone1_selected = $("#phone1_selected");
	var options = $("#phone1_selected option");
	var email2 = $("#email2");
	
	options.each(function(idx,select){
		if($(select).prop("selected")){
			$(select).removeAttr("selected");
		}
	});
	options.each(function(idx,select){
		if($(select).val()==phone1_selected.val()){
			$(select).attr("selected","selected");
		}
	});
	
	$("#email3").on("change",function(){
		if($(this).val()!=1){
			email2.val($(this).val());
		}
	});
	
});

$(document).ready(function() {
	function emptyCheck(tag,mesg) {
		if(tag.val().length==0){
			alert(mesg);
			tag.focus();
			return true;
		}else{
			return false;
		}
	}
	
	function regCheck(tag,reg,mesg){
		if(!reg.test(tag.val())){
			alert(mesg);
			tag.focus();
			return true;
		}else{
			return false;
		}
	} 
	
	
	
	var userid = $("#id");
	var email1 = $("#email1");
	var email2 = $("#email2");
	var phone2 = $("#phone2");
	var phone3 = $("#phone3");
	var post = $("#sample4_postcode");
	var addr1 = $("#sample4_roadAddress");
	var addr2 = $("#sample4_jibunAddress");
	var addr3 = $("#addr3");
	
	var phone2Reg = /^\d{3,4}$/;
	var phone3Reg = /^\d{4}$/;
	var addr3Reg = /^[0-9가-힝a-zA-Z-_]{2,12}$/;
	
	$("form").on("submit",function(event){
		var phone1 = null;
		var options = $("#phone1_selected option");
		options.each(function(i, element) {
			if($(this).prop("selected")){
				phone1=$(this);
			}
		});
		event.preventDefault();
		if(emptyCheck(email1, "이메일 입력 필요")){
			return false;
		}
		
		
		if(emptyCheck(email2, "이메일 입력 필요")){
			return false;
		}
		if(emptyCheck(phone2, "전화번호 입력")){
			return false;
		}
		
		if(regCheck(phone2,phone2Reg,"숫자 3~4만 가능")){
			return false;
		}
		
		if(emptyCheck(phone3, "전화번호 입력")){
			return false;
		}
		if(regCheck(phone3,phone3Reg,"숫자 4자리만 가능")){
			return false;
		}
		if(emptyCheck(post, "우편번호입력")){
			return false;
		}
		if(emptyCheck(addr3, "세부주소 입력")){
			return false;
		}
		if(regCheck(addr3,addr3Reg,"영문,숫자,한글-_2에서 12글자 가능")){
			return false;
		}
		
		console.log(phone1.val());
		accountInfo={
				userid: userid.val(),
				email1: email1.val(),
				email2: email2.val(),
				phone1: phone1.val(),
				phone2: phone2.val(),
				phone3: phone3.val(),
				post: 	post.val(),
				addr1:	addr1.val(),
				addr2:	addr2.val(),
				addr3: 	addr3.val()
		}
		
		$.post("/null/ModifyAccountConfirmServlet",accountInfo,function(data,textStatus,req){
			if(data==0){
				alert("변경이 완료 되었습니다.");
				$(location).attr("href","/null/MainServlet");
			}else{
				alert("변경 실패");
				$(location).attr("href","/null/ModifyAccountInfo?userid="+userid.val());
			}
		}).fail(function(xhr,status,e) {
			console.log("erorr",e);
			console.log("status",status);
		});
		
	});
	
});