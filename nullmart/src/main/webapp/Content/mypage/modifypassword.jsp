<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<c:if test="${empty login}">
	<%
		response.sendRedirect("/null/LoginUIServlet");
	%>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호변경</title>
<link rel="stylesheet" type="text/css" href="/null/Content/mypage/css/modifypassword.css?ver=2">
</head>
<body>
<script src="/null/Content/api/jquery/jquery-3.4.1.js"></script>
<jsp:include page="top.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	function check(re, what, message) {
		if (re.test(what.val())) {
			return true;
		}
		alert(message);
		what.value = "";
		what.focus();
		return false;
		console.log("작동 테스트");
	}
$('form').on("submit",function(event){
var re = /(?=.*\d{1,50})(?=.*[~`!@#$%\^&*;:()-+=]{1,50})(?=.*[a-zA-Z]{2,50}).{8,20}$/;
var pw = $('#newpwd');
if(!check(re,pw,"패스워드는 숫자, 특문 각 1회 이상, 영문은 2개 이상 사용하여 8자리 이상 입력")) {
	return false;
}
});
});

</script>
<div id="body">
	<div id="body_title">비밀번호 변경</div>
	<div id="body_top">
		<img src="/null/Content/img/mypage/lock.PNG" width="100" height="100" class="body_top_content">
		<div class="body_top_content">
			<p> * 쉬운 비밀번호나 자주 쓰는 사이트의 비밀번호가 같은 경우, 도용되기 쉬우므로 고객님의 정보보호를 위해 비밀번호는 정기적으로 변경하여 주시기 바랍니다.</p>
			<p> * 아이디와 주민등록번호, 생일, 전화번호 등 개인정보와 관련된 숫자, 연속된 숫자, 반복된 문자 등 다른 사람이 쉽게 알아 낼 수 있는 비밀번호는 개인정보 유출의 위험이 높으므로 사용을 자제해 주시기 바랍니다.</p>
			<p> * 패스워드는 숫자,특수문자 각 1회이상,영문자 2개이상을 사용하여 8자리 이상입력해주시기 바랍니다 .</p>
		</div>
	</div>
	<input type="hidden" id="pwd" value="${pwd}">
	<form action="/null/ModifyPasswordServlet" method="post">
	<input type="hidden" name="userid" value="${login.userid}">
	<div id="body_middle">
		<div class="body_middle_content">
			<div class="body_middle_title">기본비밀번호</div>
			<div><input type="password" size="40" id="oldpwd"></div>
			<span id="check" style="font-size: 14px; font-weight: bold; padding-left: 20px;">기존 비밀번호가 다릅니다.</span>
		</div>
		<div class="body_middle_content">
			<div class="body_middle_title">새비밀번호</div>
			<div><input type="password" size="40" id="newpwd" name="passwd"></div>
			<span id="newcheck" style="font-size: 14px; font-weight: bold; padding-left: 20px;">새 비밀번호를 확인해주세요.</span>
		</div>
		<div class="body_middle_content">
			<div class="body_middle_title">새비밀번호확인</div>
			<div><input type="password" size="40" id="checkpwd"></div>
		</div>
		<div id="btn">
			<input type="submit" value="변경" style="height: 30pt; width:60pt; font-size: 20px; background-color: red; margin: 20px;">
		</div>
	</div>
	
	</form>
</div>
<script src="/null/Content/mypage/js/modifypassword.js?ver=2"></script>
</body>
</html>