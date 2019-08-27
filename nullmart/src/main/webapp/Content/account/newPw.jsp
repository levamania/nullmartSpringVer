<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SearchPw</title>
<script src="/null/Content/account/jquery-3.4.1.js"></script>
<jsp:include page="/Content/statics/top/top.jsp" flush="true" />
<script src="/null/Content/api/jquery/jquery-3.4.1.js" ></script>
<script type="text/javascript">


$(document).ready(function(){
	
	$("form").on("submit", function(event) {
		
		    var re1 = /^[a-zA-Z0-9~!;:]{4,12}$/ 
			var opw= $("#oldpasswd");
			var pw = $("#passwd");
			var pw2 =$("#passwd2");

		if (opw.val() == "") {
			alert("기존 패스워드를 입력하세요.")
			opw.focus();
			return false;
		}
		if (pw.val() == "") {
			alert("새로운 패스워드를 입력하세요")
			pw.focus();
			return false;
		}
		if (pw2.val() == "") {
			alert("새로운 패스워드를 입력하세요")
			pw2.focus();
			return false;
		}
		if (!check(re1, pw, "패스워드는 4~12자의 영문 대소문자, 숫자, 특수문자(~,!,;,:)로 입력")) {
			return false;
		}

		if (opw.val() = pw.val()) {
			alert("기존 비밀번호와 같습니다. 다시 확인해 주세요.");
			
			opw.focus();
			return false;
			 
		}
		if (pw.val() != pw2.val()) {
			alert("새로운 비밀번호가 다릅니다. 다시 확인해 주세요.");
			
			pw.focus();
			return false;
			 
		}
	
			$.ajax({
			
			
			type : "get",
			url : "/null/UpdatePwServlet",
			data : {
				
				oldpasswd : opw.val(),
				passwd : pw.val(),
				
				
			},
			dataType : "text",
			success : function(data, status, xhr) {
				console.log('확인');
				console.log(data);
				if (data == 1) {
					alert("비밀번호가 변경되었습니다.")
					
					$(location).attr("href","/null/UpdatePwServlet?oldpasswd="+opw.val()+"&passwd="+pw.val());
				} 
			},
			error : function(xhr, status, e) {
				console.log("error", e);
				console.log("status", status);
			}
	

	});
			/* return false; */

	});
});





</script>
<style type="text/css">
 body {
	max-width: 80%; height: 150%;
	margin: center;
	
	
}
</style>
</head>

<body>

<form action="/null/LoginUIServlet" method="get" id="search" >
	 <div  style="font-size: 100%;color: gray;display: block; width: 1500px;" align="center">
			<br> 
			<br> <b style="font-size: 200%;color: black">새로운 비밀번호</b>
	
		<br>
		
		
			<br> 
			<br> <a >새로운 비밀번호를
                        <br>
                        입력하세요!</a>
		<br> 
		<br>
		<a style="font-size:60%;color: red">*</a><a style="font-size:60%;color: black;align-self: auto; " >필수 입력사항</a>
		
		<hr> 
		<table>
<tr>
<td width="150" height="35"><a style="font-size:60%;color: red">*</a><a  style="font-size:60%">기존 비밀번호</a></td>
<td  width="300" height="35" ><input type="password" style="width:250px;height:50%;font-size:60%" id="oldpasswd" placeholder="영문,숫자,특수문자사용 4~12자" name="oldpasswd"></td>

</tr>
 
 <tr>
<td width="150" height="35"><a style="font-size:60%;color: red">*</a><a  style="font-size:60%">새 비밀번호</a></td>
<td width="300" height="35" colspan="0"><input type="password" style="width:250px;height:50%" id="passwd" name="passwd"></td>
</tr>

 <tr>
<td width="150" height="35"><a style="font-size:60%;color: red">*</a><a  style="font-size:60%">새 비밀번호 확인</a></td>
<td width="300" height="35" colspan="0"><input type="password" style="width:250px;height:50%" id="passwd2" name="passwd2"></td>

</tr>
<tr><td><button type="submit"
			style="width: 80pt; height: 15pt; font-size: 70%; background-color: red; border-color: red; color: white; border-style: hidden;"
			>비밀번호 변경하기</button> </td>
			</tr>
			</table>
			</div>
			
	</form>

	<br>
	<hr>



</body>
</html>