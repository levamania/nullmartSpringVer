<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SearchPw</title>
<script src="/null/Content/account/jquery-3.4.1.js"></script>
<jsp:include page="/Content/statics/top/top.jsp" flush="true" />
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!--   <link rel="stylesheet" href="/resources/demos/style.css"> -->
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">


$(document).ready(function(){
	

	
	
	$('#emailSelect').change(function(){
		   $("#emailSelect option:selected").each(function () {
				
				if($(this).val()== '1'){ //직접입력일 경우
					 $("#email2").val('');                        //값 초기화
					 $("#email2").attr("disabled",false); //활성화
				}else{ //직접입력이 아닐경우
					 $("#email2").val($(this).text());      //선택값 입력
					 $("#email2").attr("disabled",true); //비활성화
				}
		   });
		});
	$("#btn1").on("click", function(event) {
		
		var id = $("#userid");
		var un = $("#username");
		var em1 = $("#email1");
		var em2 = $("#email2");
		

		if (id.val() == "") {
			alert("아이디를 입력하시오")
			id.focus();
			return false;
		}
		if (un.val() == "") {
			alert("이름을 입력하시오")
			un.focus();
			return false;
		}
		if (em1.val() == "") {
			alert("이메일을 입력하시오")
			em1.focus();
			return false;
		}
		if (em2.val() == "") {
			alert("이메일을 입력하시오")
			em2.focus();
			return false;
		}
	
			$.ajax({
			
			
			type : "get",
			url : "/null/searchPwMail",
			data : {
				
				userid : id.val(),
				username : un.val(),
				email1 : em1.val(),
				email2 : em2.val()
			},
			dataType : "text",
			success : function(data, status, xhr) {

				if ( data=="0") {

				alert("正しい情報を入力してください。");
					return false;
				} else if(data=="1"){
					alert("メールが転送されました。")
				
					$(location).attr("href","/null/Content/account/loginForm.jsp");
					 


				}
			},
			error : function(xhr, status, e) {
			}
	

	});
			return false;

	});
$("#btn2").on("click", function(event) {
		
		var id = $("#userid2");
		var un = $("#username2");
		var p1 = $("#phone1");
		var p2 = $("#phone2");
		var p3 = $("#phone3");
		

		if (id.val() == "") {
			alert("아이디를 입력하시오")
			id.focus();
			return false;
		}
		if (un.val() == "") {
			alert("이름을 입력하시오")
			un.focus();
			return false;
		}
		if (p1.val() == "") {
			alert("이메일을 입력하시오")
			p1.focus();
			return false;
		}
		if (p2.val() == "") {
			alert("이메일을 입력하시오")
			p2.focus();
			return false;
		}
		if (p3.val() == "") {
			alert("이메일을 입력하시오")
			p3.focus();
			return false;
		}
	
			$.ajax({
			
			
			type : "get",
			url : "/null/phoneSearchPw",
			data : {
				
				userid : id.val(),
				username : un.val(),
				phone1 : p1.val(),
				phone2 : p2.val(),
				phone3 : p3.val(),
			},
			dataType : "text",
			success : function(data, status, xhr) {

				if ( data=="0") {

				alert("正しい情報を入力してください。");
					return false;
				} else if(data=="1"){
					alert("SMSで転送されました。")
				
					$(location).attr("href","/null/sendSms?phone1="+p1.val()+"&phone2="+p2.val()+"&phone3="+p3.val());
					 


				}
			},
			error : function(xhr, status, e) {
			}
	

	});
			return false;

	});
	
	
	
	$( "#tabs" ).tabs();
});





</script>
<style type="text/css">
 body {
	max-width: 80%; height: 100%;
	margin: auto;

	
}
</style>
</head>

<body>

		<div align="center" style="display: block;">
			<br> 
			<br> <b style="font-size: 200%">비밀번호 찾기</b>
		
		<br>
		
			<br> 
			<br> <a style="font-size: 80%;color: gray">비밀번호를 잊어버리셨습니까?
                        <br>
                        그럼 찾아보세요!</a>
		<br> 
		<br>
		</div>
		
		<div id="tabs" style="width: 0100%;" align="center" >
	
  <ul style="background-color: white;" >
  
    <li><a href="#tabs-1" style="background-color: white;border-color: red;border: 1px;color: black;outline: none;">E-mail로 찾기</a></li>
    <li><a href="#tabs-2"style="background-color: white;border-color: red;border: 1px;color: black;outline: none">SMS로 찾기</a></li>
  
  </ul>
  <form action="/null/Content/account/loginForm.jsp" method="get" id="search" >
  <div align="center" style="display: block; width: 1500px;">
   <div id="tabs-1">
		<a style="font-size: 80%;color: gray">email로 찾기</a>
		
		<hr>
		<table>
<tr>
<td width="100" height="35"><a  style="font-size:60%">ID</a></td>
<td  width="200" height="35" ><input type="text" style="width:200px;height:50%;font-size:60%" id="userid" placeholder="英文、数字の使用4〜12字" name="userid"></td>

</tr>
 
 <tr>
<td width="100" height="35"><a  style="font-size:60%">お名前</a></td>
<td width="200" height="35" colspan="0"><input type="text" style="width:200px;height:50%" id="username" name="username"></td>
</tr>

 <tr>
<td width="100" height="35"><a  style="font-size:60%">이메일</a></td>
<td width="100" height="35" colspan="0"><input type="text" style="width:70px;height:50%" id="email1" name="email1"><a>@</a>
<input type="text" style="width:100px;height:50%;font-size:60%" id="email2" name="email2" placeholder="예) null.com" value="naver.com"></td>
<td><select  id="emailSelect"><option value="naver.com" id="naver">naver.com</option>
<option value="softbank.jp" id="softbank">softbank.jp</option>
<option value="daum.net" id="daum">daum.net</option>
<option value="hanmail.net">hanmail.net</option> 
				<option value="hotmail.com">hotmail.com</option> 
				<option value="nate.com">nate.com</option> 
				<option value="yahoo.co.kr">yahoo.co.kr</option> 
				<option value="empas.com">empas.com</option> 
				<option value="dreamwiz.com">dreamwiz.com</option> 
				<option value="freechal.com">freechal.com</option> 
				<option value="lycos.co.kr">lycos.co.kr</option> 
				<option value="korea.com">korea.com</option> 
				<option value="gmail.com">gmail.com</option> 
				<option value="hanmir.com">hanmir.com</option> 
				<option value="paran.com">paran.com</option>
</select>
</td>
<td><button type="submit"
			style="width: 60pt; height: 10pt; font-size: 60%; background-color: red; border-color: red; color: white; border-style: hidden;"
			id="btn1">요청</button> </td>
</tr>

			</table>
			</div>
			</div>
			</form>
			<!-- tab2222222222222222222222222222222222222222222222222222222222 -->
			
			<form action="/null/loginFrom.jsp" method="get" id="search2" >
			   <div id="tabs-2">
		<a style="font-size: 80%;color: gray">SMS로 찾기</a>
		
		<hr>
		<table>
<tr>
<td width="100" height="35"><a  style="font-size:60%">ID</a></td>
<td  width="200" height="35" ><input type="text" style="width:200px;height:50%;font-size:60%" id="userid2" placeholder="英文、数字の使用4〜12字" name="userid"></td>

</tr>
 
 <tr>
<td width="100" height="35"><a  style="font-size:60%">お名前</a></td>
<td width="200" height="35" colspan="0"><input type="text" style="width:200px;height:50%" id="username2" name="username"></td>
</tr>

 <tr>
<td width="100" height="35"><a  style="font-size:60%">전화번호</a></td>
<td width="200" height="35" colspan="0"><input type="text" style="width:55px;height:50%;font-size:60%" id="phone1" name="phone1"><a>-</a>
<input type="text" style="width:55px;height:50%;font-size:60%" id="phone2" name="phone2" ><a>-</a>
<input type="text" style="width:55px;height:50%;font-size:60%" id="phone3" name="phone3" ></td>


<td><button type="submit"
			style="width: 60pt; height: 10pt; font-size: 60%; background-color: red; border-color: red; color: white; border-style: hidden;"
			id="btn2">요청</button> </td>
</tr>

			</table>
			</div>
			</div>
	
			</form>
			
			
			

	<br>
	<hr>



</body>
</html>