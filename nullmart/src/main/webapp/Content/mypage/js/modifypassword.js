/**
 * 
 */
$(document).ready(function() {
	var pwd = $("#pwd");
	var check = $("#check");//기존 비밀번호 결과 
	var oldpwd = $("#oldpwd");
	var newpwd = $("#newpwd"); //새로운 비밀번호 
	var checkpwd = $("#checkpwd"); // 새로운 비밀번호 확인
	var newcheck = $("#newcheck"); //새로운 비밀번호 결과 
	
	oldpwd.on("keyup",function(){
		if(pwd.val()==oldpwd.val()){
			check.text("");
		}else{
			check.text("기존 비밀번호가 다릅니다.");
		}
	});
	
	
	newpwd.on("keyup",function(){
		if($(this).val().length==0){
			newcheck.text("새 비밀번호를 확인해주세요");
		}else if($(this).val()!=checkpwd.val()){
			newcheck.text("새 비밀번호를 확인해주세요");
		}else{
			newcheck.text("");
		}
	});
	
	checkpwd.on("keyup",function(){
		if($(this).val().length==0){
			newcheck.text("새 비밀번호를 확인해주세요");
		}else if($(this).val()!=newpwd.val()){
			newcheck.text("새 비밀번호를 확인해주세요");
		}else{
			newcheck.text("");
		}
	});
	
	$("form").on("submit",function(){
		console.log(check.text());
		var pwdReg = /^[A-Za-z0-9~;!:]{4,12}$/;
		if(check.text()){
			alert("기존 비밀번호를 확인하세요");
			oldpwd.focus();
			return false;
		}
		if(newcheck.text()){
			alert("새로운 비밀번호를 확인하세요");
			newpwd.focus();
			return false;
		}
		if(!pwdReg.test(newpwd.val())){
			alert("잘못된 비밀번호 형식입니다.");
			newpwd.val("");
			checkpwd.val("");
			newcheck.text("새 비밀번호를 확인해주세요.");
			newpwd.focus();
			return false;
		}
	});
});