<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<script src="/null/Content/account/jquery-3.4.1.js"></script>
<jsp:include page="/Content/statics/top/top.jsp" flush="true" />
</head>
<body>
	<script src="/null/Content/api/jquery/jquery-3.4.1.js"></script>
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
			//id 비밀번호 일치 불일치 유효성 검사
			$('#check').on("submit", function() {
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
						console.log('확인');
						console.log(data);
						if (data == 0) {
							alert("아이디또는 비밀번호가 일치하지 않습니다.");
							/* window.open("idCheck.jsp","idCheck","width=100,height=50,resizable=no,scrollbars=no"); */

							$("#userid").focus();
							return false;
						} else {
							alert("로그인이 되었습니다.")
							/* window.open("idCheckPass.jsp","idCheck","width=100,height=50,resizable=no,scrollbars=no"); */
							$("#userid").focus();

							return false;

						}
					},
					error : function(xhr, status, e) {
						console.log("error", e);
						console.log("status", status);
					}
				});

			});

			//이메일 change
			$('#emailSelect').change(function() {
				$("#emailSelect option:selected").each(function() {

					if ($(this).val() == '1') { //직접입력일 경우
						$("#email2").val(''); //값 초기화
						$("#email2").attr("disabled", false); //활성화
					} else { //직접입력이 아닐경우
						$("#email2").val($(this).text()); //선택값 입력
						$("#email2").attr("disabled", true); //비활성화
					}
				});
			});

			//기본 유효성검사

			$("#check").on("submit", function(event) {

				var re = /^[a-zA-Z0-9]{4,12}$/
				var re1 = /^[a-zA-Z0-9~!;:]{4,12}$/

				var id = $("#userid");
				var pw = $("#passwd");

				if (id.val() == "") {
					alert("아이디를 입력하시오")
					username.focus();
					return false;
				}
				if (pw.val() == "") {
					alert("패스워드를 입력하시오")
					username.focus();
					return false;
				}

				if (!check(re, id, "아이디는 4~12자의 영문 대소문자와 숫자로만 입력하시오")) {
					return false;

				}

				if (!check(re1, pw, "패스워드는 4~12자로 입력하시오")) {
					return false;
				}

			});

		});
	</script>






	<form name="login" action="/null/LoginServlet" method="get" id="check">
		<div align="center" style="font-size: 200%">
			<br> <br> <b>로그인</b>
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
			<br> <input type="submit" value="  로그인 " class="test_btn1"
				style="width: 53pt; height: 15pt; font-size: 76%; background-color: red; border-color: red; color: white; border-style: hidden;" />
			<input type="reset" value="다시입력" class="test_btn1"
				style="width: 53pt; height: 15pt; font-size: 76%; background-color: red; border-color: red; color: white; border-style: hidden;">
			<br> <br>
			<hr>
		</div>
	</form>


	<div align="center">
		<button type="button"
			style="width: 68pt; height: 15pt; font-size: 76%; background-color: white; border-color: white; color: black; border-style: hidden;"
			onclick="location.href='searchId.jsp' ">아이디찾기</button>
		<button type="button"
			style="width: 68pt; height: 15pt; font-size: 76%; background-color: white; border-color: white; color: black; border-style: hidden;"
			onclick="location.href='/null/Content/account/searchPw.jsp' ">비밀번호찾기</button>
	</div>
	<br>
	<hr>
</body>
</html>