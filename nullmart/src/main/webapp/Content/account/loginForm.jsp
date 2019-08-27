<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<script src="/null/Content/account/jquery-3.4.1.js"></script>
<jsp:include page="/Content/statics/top/top.jsp" flush="true" />
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!--   <link rel="stylesheet" href="/resources/demos/style.css"> -->
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
</head>
<body>
	<script type="text/javascript">
	
	$(document).ready(function() {
		
         

		$( "#tabs" ).tabs();

		//id 비밀번호 일치 불일치 유효성 검사
		//var = loginFunction = function
				
			$("#memberLogin").on("submit", function() {
				
				var id = $("#userid");
				var pw = $("#passwd");

			
			
				$.ajax({
					type : "get",
					url : "/null/IdPwCheckServlet",
					data : {
						userid : id.val(),
						passwd : pw.val()
					},
					dataType : "text",
					
					
					
					success : function(data, status, xhr) {
						
						if (data == 0) {
							alert("아이디또는 비밀번호가 일치하지 않습니다.");
							

							$("#userid").focus();
							return false;
						} else {
							alert("로그인이 되었습니다.")
							
							$("#userid").focus();

							

						}
					},
					error : function(xhr, status, e) {
						console.log("error", e);
						console.log("status", status);
					}
				});
			
			});
				
				//masterid 비밀번호 일치 불일치 유효성 검사
				$('#masterLogin').on("submit", function() {
					var mid = $("#masteruserid");
					var mpw = $("#masterpasswd");
					$.ajax({
						type : "get",
						url : "/null/ManagerIdPwCheckServlet",
						data : {
							masteruserid : mid.val(),
							masterpasswd : mpw.val()
						},
						dataType : "text",
						success : function(data, status, xhr) {
						
							if (data == 0) {
								alert("아이디또는 비밀번호가 일치하지 않습니다.");
								
								$("#masteruserid").focus();
								return false;
							} else {
								alert("로그인이 되었습니다.")
								
								$("#masteruserid").focus();

								

							}
						},
						error : function(xhr, status, e) {
							console.log("error", e);
							console.log("status", status);
						}
					});

				});
			
				

				function check(re, what, message) {
					if (re.test(what.val())) {
						return true;
					}
					alert(message);
					what.value = "";
					what.focus();
					return false;

				}

			

			//기본 유효성검사

			$("#memberLogin").on("submit", function(event) {

				var re = /^[a-zA-Z0-9]{4,12}$/
				var re1 = /^[a-zA-Z0-9~!;:]{4,12}$/

				var id = $("#userid");
				var pw = $("#passwd");

				if (id.val() == "") {
					alert("아이디를 입력하시오")
					id.focus();
					return false;
				}
				
				if (pw.val() == "") {
					alert("패스워드를 입력하시오")
					pw.focus();
					return false;
				}

				if (!check(re, id, "아이디는 4~12자의 영문 대소문자와 숫자로만 입력하시오")) {
					return false;

				}

				if (!check(re1, pw, "패스워드는 4~12자로 입력하시오")) {
					return false;
				}

			}); 
			//기본 유효성검사

			$("#masterLogin").on("submit", function(event) {

				var mre = /^[a-zA-Z0-9]{4,12}$/
				var mre1 = /^[a-zA-Z0-9~!;:]{4,12}$/

				var mid = $("#masteruserid");
				var mpw = $("#masterpasswd");

				if (mid.val() == "") {
					alert("아이디를 입력하시오")
					mid.focus();
					return false;
				}
				if (mpw.val() == "") {
					alert("패스워드를 입력하시오")
					mpw.focus();
					return false;
				}

				if (!check(mre, mid, "아이디는 4~12자의 영문 대소문자와 숫자로만 입력하시오")) {
					return false;

				}

				if (!check(mre1, mpw, "패스워드는 4~12자로 입력하시오")) {
					return false;
				}

			});

		});
		
	</script>






	<div id="tabs" style="width: 0100%;" align="center" >
	
  <ul style="background-color: white;" >
  
    <li><a href="#tabs-1" style="background-color: white;border-color: red;border: 1px;color: black;outline: none;">회원 로그인</a></li>
    <li><a href="#tabs-2"style="background-color: white;border-color: red;border: 1px;color: black;outline: none">관리자 로그인</a></li>
  
  </ul>
 
	<form name="memberLogin" action="/null/LoginServlet" method="get" id="memberLogin">
		 <div id="tabs-1">
		<div align="center" style="font-size: 200%">
			<br> <br> <b >회원 로그인</b>
		</div>
		<br> <br>
		<hr>
		<div align="center">
			<table border="0" id="btn_group"
				style="text-align: center; align-self: center">
				<tr>
					<td width="100" height="35"><a
						style="font-size: 60%; color: red">* </a><a
						style="font-size: 60%">아이디</a></td>
					<td width="200" height="35"><input type="text"
						style="width: 150px; height: 80%; font-size: 70%" id="userid"
						placeholder="영문,숫자사용 4~12자" name="userid"></td>
					<br>
				</tr>
				<tr>
					<td width="100" height="35"><a
						style="font-size: 60%; color: red">* </a><a
						style="font-size: 60%">패스워드</a></td>
					<td width="200" height="35"><input type="password"
						style="width: 150px; height: 80%; font-size: 70%" id="passwd"
						placeholder="영문,숫자,특수문자사용 4~12자" name="passwd"></td>
					<br>
				</tr>
			</table>
			<br> <input type="submit" value="  로그인 " id="loginbtn" class="test_btn1"
				style="width: 53pt; height: 15pt; font-size: 76%; background-color: red; border-color: red; color: white; border-style: hidden;" />
			<input type="reset" value="다시입력" class="test_btn1"
				style="width: 53pt; height: 15pt; font-size: 76%; background-color: red; border-color: red; color: white; border-style: hidden;">
			<br> <br>
			<hr>
		</div>
	</div>
	</form>
	<div id="tabs-2">
   <form name="masterLogin" action="/null/ManagerLoginServlet" method="get" id="masterLogin">
		 <div id="tabs-1">
		<div align="center" style="font-size: 200%">
			<br> <br> <b>관리자 로그인</b>
		</div>
		<br> <br>
		<hr>
		<div align="center">
			<table border="0" id="btn_group"
				style="text-align: center; align-self: center">
				<tr>
					<td width="100" height="35"><a
						style="font-size: 60%; color: red">* </a><a
						style="font-size: 60%">아이디</a></td>
					<td width="200" height="35"><input type="text"
						style="width: 150px; height: 80%; font-size: 70%" id="masteruserid"
						placeholder="영문,숫자사용 4~12자" name="masteruserid"></td>
					<br>
				</tr>
				<tr>
					<td width="100" height="35"><a
						style="font-size: 60%; color: red">* </a><a
						style="font-size: 60%">패스워드</a></td>
					<td width="200" height="35"><input type="password"
						style="width: 150px; height: 80%; font-size: 70%" id="masterpasswd"
						placeholder="영문,숫자,특수문자사용 4~12자" name="masterpasswd"></td>
					<br>
				</tr>
			</table>
			<br> <input type="submit" value="  로그인 " class="test_btn1" id=""
				style="width: 53pt; height: 15pt; font-size: 76%; background-color: red; border-color: red; color: white; border-style: hidden;" />
			<input type="reset" value="다시입력" class="test_btn1"
				style="width: 53pt; height: 15pt; font-size: 76%; background-color: red; border-color: red; color: white; border-style: hidden;">
			<br> <br>
			<hr>
		</div>
	</div>
	</form>
  </div>

	<div align="center">
		<button type="button"
			style="width: 68pt; height: 15pt; font-size: 76%; background-color: white; border-color: white; color: black; border-style: hidden;"
			onclick="location.href='searchId.jsp' ">아이디찾기</button>
		<button type="button"
			style="width: 68pt; height: 15pt; font-size: 76%; background-color: white; border-color: white; color: black; border-style: hidden;"
			onclick="location.href='/null/Content/account/searchPw.jsp' ">비밀번호찾기</button>
	</div>
	</div>
	<br>
	<br>
	<br>
	<hr>
</body>
</html>