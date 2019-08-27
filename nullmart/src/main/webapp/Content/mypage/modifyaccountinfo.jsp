<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보수정</title>
<style type="text/css">
	#body{
		position: relative;
		display: inline-block;
		vertical-align: top;
	}
	#body_content{
		display: inline-block;
		position: absolute;
		top: 65px;
		width:890px;
		height: 140px;
		border-top: 1px solid red;
	}
	#body_bottom{
		display: inline-block;
		position: absolute;
		width:890px;
		height: 100px;
		top: 350px;
		padding-top: 20px;
	}
	.info_title{
		display: inline-block;
		width: 200px;
		height: 34px;
		font-size: 12px;
		font-weight: bold;
		padding-top: 12px;
		padding-left: 30px;
		margin-right:20px;
		background-color: #F6F6F6;
	}
	#address_top{
		display:inline-block; 
		height: 60px; 
		width: 200px;left:0px; 
		position:absolute; 
		background-color: #F6F6F6;
		padding-top: 25px;
		padding-left: 30px;
	}
	#address_body{
		display: inline-block; 
		width: 600px; position: 
		absolute; left: 250px; 
		padding-top: 15px;
	}
	.info_line{
		border-bottom: 1px solid #EAEAEA;
	}
	.info_content{
	
	}
</style>
</head>
<body>
<script src="/null/Content/api/jquery/jquery-3.4.1.js"></script>
<jsp:include page="top.jsp"></jsp:include>
<div id="body">
<div style="height: 30px; margin-top: 4px; font-weight: bold;">개인정보수정<p>기본정보</p></div>
<form action="/null/ModifyAccountConfirmServlet" method="post">
<div id="body_content">
	<input type="hidden" id="phone1_selected" value="${member.phone1}">
	<div class="info_line"><span class="info_title">이름</span><span class="info_content">${member.username}</span></div>
	<div class="info_line"><span class="info_title">아이디</span><span class="info_content">${member.userid}</span></div>
	<div class="info_line">
		<span class="info_title">*이메일</span>
		<span class="info_content">
			<input type="text" value="${member.email1}" name="email1" id="email1">&nbsp;@
			<input type="text" value="${member.email2}" name="email2" id="email2">&nbsp;
			<select style="height: 20pt" id="email3" name="email3">
				<option value="1" selected="selected">직접입력</option>
				<option value="naver.com" >naver.com</option> 
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
		</span>
	</div>
	<div class="info_line">
		<span class="info_title">휴대번호</span>
		<span class="info_content">
		 <select id="phone1_selected" style="width: 70pt;" name="phone1">
		 	<option>010</option>
			<option>011</option>
			<option>016</option>
			<option>017</option>
			<option>018</option>
			<option>019</option>
		 </select>&nbsp;-
		 <input type="text" value="${member.phone2}" id="phone2" name="phone2">&nbsp;-
		 <input type="text" value="${member.phone3}" id="phone3" name="phone3">
		 </span>
	</div>
	<div class="info_line" >
		<div id="address_top">
			<span style="font-size: 12px; font-weight: bold;">배송지주소</span>
		</div>
		<div id="address_body">
				&nbsp;<input type="text" name="post" id="sample4_postcode" placeholder="우편번호" size="20" value="${member.post}" readonly="readonly">
				&nbsp;<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" ><br>
				&nbsp;<input type="text" name="addr1" id="sample4_roadAddress" placeholder="도로명주소" size="20" value="${member.addr1}" readonly="readonly">
				&nbsp;<input type="text" name="addr2" id="sample4_jibunAddress" placeholder="지번주소" size="20" value="${member.addr2}" readonly="readonly">
				&nbsp;<input type="text" name="addr3" id="addr3" placeholder="세부주소" size="20" value="${member.addr3}">
				<span id="guide" style="color:#999"></span>
		</div>
	</div>
</div>

<div id="body_bottom" align="center">
<input type="hidden" value="${member.userid}" id="id" name="userid">
<input type="hidden" value="${member.username}"  id="name" name="username">
<input type="submit" value="저장" style="30pt; width: 60pt; font-size: 18px; font-weight: bold; background-color: red;">
</div>
</form>
</div>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/null/Content/mypage/js/addressAPI.js"></script>
<script src="/null/Content/mypage/js/modifyaccountinfo.js"></script>
</body>
</html>