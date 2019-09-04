<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		function check(re, what, message) {
			if (re.test(what.val())) {
				return true;
			}
			alert(message);
			what.value = "";
			what.focus();
			return false;

		}

         

		$( "#tabs" ).tabs();

		//id 비밀번호 일치 불일치 유효성 검사
		//var = loginFunction = function
				
			$("#memberBtn").on("click", function() {
				
<<<<<<< HEAD
				var re = /^[a-zA-Z0-9]{4,12}$/
					var re1 = /^[a-zA-Z0-9~!;:@#]{4,12}$/

					var id = $("#userid");
					var pw = $("#passwd");

					if (id.val() == "") {
						alert("아이디를 입력하시오")
						id.focus();
						return ;
					}
					
					if (pw.val() == "") {
						alert("패스워드를 입력하시오")
						pw.focus();
						return ;
					}

					if (!check(re, id, "아이디는 4~12자의 영문 대소문자와 숫자로만 입력하시오")) {
						return ;

					}

					if (!check(re1, pw, "패스워드는 4~12자로 입력하시오")) {
						return ;
					}

				

			
			
=======
				var id = $("#userid");
				var pw = $("#passwd");
>>>>>>> branch 'sandwich' of https://github.com/levamania/nullmartSpringVer.git
				$.ajax({
					type : "get",
<<<<<<< HEAD
					url : "/null/idPwCheck",
=======
					url : "/null/IdPwCheck",
>>>>>>> branch 'sandwich' of https://github.com/levamania/nullmartSpringVer.git
					data : {
						userid : id.val(),
						passwd : pw.val()
					},
					dataType : "text",
					success : function(data, status, xhr) {
<<<<<<< HEAD
						
						if (data == "0") {
							alert("아이디가 없습니다.");
							id.val("");
							id.focus();
							return false;
						} else if(data =="1"){
							alert("패스워드가 틀립니다.");
							pw.val("");
							pw.focus();
						} else{
							alert("로그인이 되었습니다.")
							$("#memberLogin").submit();

							

=======
						if (data==0) {
							alert("아이디또는 비밀번호가 일치하지 않습니다.");
							console.log(data);

							$("#userid").focus();
							
						} else if(data==1){
							alert("로그인이 되었습니다.");
							console.log(data);
							$("#userid").focus();							
>>>>>>> branch 'sandwich' of https://github.com/levamania/nullmartSpringVer.git
						}
					},
					error : function(xhr, status, e) {
						console.log("error", e);
						console.log("status", status);
					}
				});
			
			});
				
				//masterid 비밀번호 일치 불일치 유효성 검사
				$("#managerBtn").on("click", function() {
					
					var mre = /^[a-zA-Z0-9]{4,12}$/
						var mre1 = /^[a-zA-Z0-9~!;:@#]{4,12}$/

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
					$.ajax({
						type : "get",
						url : "/null/ManagerIdPwCheck",
						data : {
							masteruserid : mid.val(),
							masterpasswd : mpw.val()
						},
						dataType : "text",
						success : function(data, status, xhr) {
						
							if (data == "0") {
								alert("아이디가 없습니다.");
								mid.val("");
								mid.focus();
								return false;
							} else if(data =="1"){
								alert("패스워드가 틀립니다.");
								mpw.val("");
								mpw.focus();
							} else{
								alert("로그인이 되었습니다.")
								$("#managerLogin").submit();

							}
						},
						error : function(xhr, status, e) {
							console.log("error", e);
							console.log("status", status);
						}
					});

				});

		});
		
	</script>






	<div id="tabs" style="width: 0100%;" align="center" >
	
  <ul style="background-color: white;" >
  
    <li><a href="#tabs-1" style="background-color: white;border-color: red;border: 1px;color: black;outline: none;">회원 로그인</a></li>
    <li><a href="#tabs-2"style="background-color: white;border-color: red;border: 1px;color: black;outline: none">관리자 로그인</a></li>
  
  </ul>
 
	<form name="memberLogin" action="/null/login" method="get" id="memberLogin">
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
						placeholder="영문,숫자사용 4~12자" name="userid" required></td>
					<br>
				</tr>
				<tr>
					<td width="100" height="35"><a
						style="font-size: 60%; color: red">* </a><a
						style="font-size: 60%">패스워드</a></td>
					<td width="200" height="35"><input type="password"
						style="width: 150px; height: 80%; font-size: 70%" id="passwd"
						placeholder="영문,숫자,특수문자사용 4~12자" name="passwd" required></td>
					<br>
				</tr>
			</table>
			<br> <input type="button" value="  로그인 " id="memberBtn" class="test_btn1"
				style="width: 53pt; height: 15pt; font-size: 76%; background-color: red; border-color: red; color: white; border-style: hidden;" />
			<input type="reset" value="다시입력" class="test_btn1"
				style="width: 53pt; height: 15pt; font-size: 76%; background-color: red; border-color: red; color: white; border-style: hidden;">
			<br> <br>
			<hr>
		</div>
	</div>
	</form>
	<div id="tabs-2">
<<<<<<< HEAD
   <form name="managerLogin" action="/null/managerLogin" method="get" id="managerLogin">
=======
   <form name="masterLogin" action="/null/managerLogin" method="get" id="masterLogin">
>>>>>>> branch 'sandwich' of https://github.com/levamania/nullmartSpringVer.git
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
						style="font-size: 60%; color: red">* </a>
						<a style="font-size: 60%">아이디</a></td>
					<td width="200" height="35">
						<c:if test="${empty managerLogin}">
							<input type="text" style="width: 150px; height: 80%; font-size: 70%" id="masteruserid"
							placeholder="영문,숫자사용 4~12자" name="masteruserid" >
						</c:if>
						<c:if test="${!empty managerLogin}">
							<span>${managerLogin.masteruserid}</span>
						</c:if>
					</td>
					<br>
				</tr>
				<tr>
					<td width="100" height="35"><a
						style="font-size: 60%; color: red">* </a>
						<c:if test="${empty managerLogin}">
							<a style="font-size: 60%">패스워드</a>
						</c:if>
						<c:if test="${!empty managerLogin}">
							<a style="font-size: 60%">로그인 상태</a>
						</c:if>
						</td>
					<td width="200" height="35">
						<c:if test="${empty managerLogin}">
							<input type="password" style="width: 150px; height: 80%; font-size: 70%" id="masterpasswd"
							placeholder="영문,숫자,특수문자사용 4~12자" name="masterpasswd">
						</c:if>
						<c:if test="${!empty managerLogin}">
							<span>관리자님 환영합니다.</span>
						</c:if>
					</td>
					<br>
				</tr>
			</table>
<<<<<<< HEAD
			<br>
				<c:if test="${empty managerLogin}">
					<input type="button" value="  로그인 " class="test_btn1" id="managerBtn"
					style="width: 53pt; height: 15pt; font-size: 76%; background-color: red; border-color: red; color: white; border-style: hidden;" />
					<input type="reset" value="다시입력" class="test_btn1"
					style="width: 53pt; height: 15pt; font-size: 76%; background-color: red; border-color: red; color: white; border-style: hidden;">
				</c:if>
				<c:if test="${!empty managerLogin}">
							
				</c:if>
			 
=======
			<br> <input type="button" value="  로그인 " class="test_btn1" id=""
				style="width: 53pt; height: 15pt; font-size: 76%; background-color: red; border-color: red; color: white; border-style: hidden;" />
			<input type="reset" value="다시입력" class="test_btn1"
				style="width: 53pt; height: 15pt; font-size: 76%; background-color: red; border-color: red; color: white; border-style: hidden;">
>>>>>>> branch 'sandwich' of https://github.com/levamania/nullmartSpringVer.git
			<br> <br>
			<hr>
		</div>
	</div>
	</form>
  </div>
	<c:if test="${empty managerLogin}">
	<div align="center">
		<button type="button"
			style="width: 68pt; height: 15pt; font-size: 76%; background-color: white; border-color: white; color: black; border-style: hidden;"
			onclick="location.href='searchId.jsp' ">아이디찾기</button>
		<button type="button"
			style="width: 68pt; height: 15pt; font-size: 76%; background-color: white; border-color: white; color: black; border-style: hidden;"
			onclick="location.href='/null/Content/account/searchPw.jsp' ">비밀번호찾기</button>
	</div>
	</c:if>
	
	</div>
	<br>
	<br>
	<br>
	<hr>
</body>
</html>