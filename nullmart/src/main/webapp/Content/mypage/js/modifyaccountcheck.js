$(document).ready(function() {
	$("form").on("submit",function(evet){
		event.preventDefault();
		var id = $("#id");
		var password = $("#passwordvalue");
		var reg = /^[A-Za-z0-9~;!:]{4,12}$/;
		if(!reg.test(password.val())){
			alert("패스워드의 형식 확인");
			password.focus();
			return false;
		}
		
		$.post("/null/CheckPasswordServlet", {pwd:password.val(),userid:id.val()}, function(data, textStatus, req) {
			if(data==0){
				alert("비밀번호가 틀립니다.")
				password.val("");
				password.focus();
				return ;
			}else{
				$(location).attr("href","/null/ModifyAccountInfo?userid="+id.val());
			}
		}).fail(function(xhr,status,e) {
			console.log("erorr",e);
			console.log("status",status);
		});
		
	});
});