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
<title>배송지수정</title>
<!-- 
input text 변수
delivname 배송지명
delivperson 수취인
phone1 - 전화번호
phone11
phone12
phone13
phone21 - 휴대전화
phone22 
phone23 
sample4_postcode - 우편번호
sample4_roadAddress - 도로명
sample4_jibunAddress - 지번
submitbtn - 서브밋 버튼
regAddrForm - form id
userid - 회원 아이디

 -->
<style type="text/css">
	#top_title{
		display:inline-block;
		position: absolute;
		width: 900px;
		height: 90px;
		/* border-bottom: 1px solid red; */
	}
	.top_content{
		display: inline-block;
		position: absolute;
		top: 60px;
	}
	.table_title{
		background-color: #EAEAEA;
		border-bottom: 2px solid #D5D5D5;
	}
	.btns{
		font-size: 18px;
		width: 45pt;
		height: 26pt;
		padding: 2px;
	}
</style>
</head>
<body>
<c:set var="addr" value="${addr}"></c:set>
<input type="hidden" value="${addr.delivno}" id="delivno">
<input type="hidden" value="${addr.phone11}" id="addrphone11">
<input type="hidden" value="${addr.phone21}" id="addrphone21">
<input type="hidden" value="${addr.userid}" id="userid">
<script src="/null/Content/api/jquery/jquery-3.4.1.js"></script>
<jsp:include page="top.jsp" ></jsp:include>
<div style="display: inline-block; vertical-align: top; position: relative; ">
<div id="top_title">
<p style="font-weight:bolder; font-size: 20px;" id="head_title">배송주소록관리</p>
	<span class="top_content" style="font-weight: bold; font-size: 14px; ">배송지정보</span>
	<span class="top_content" style="left:700px;"> * 필수 입력항목</span>
</div>
<div style="position: absolute; top: 90px;">
<form action="../../AddrAddServlet" id="regAddrForm" method="get">
	<table style="font-size: 14px; border-top: 1px solid red; width: 900px;" >
	<tr height="30px" >
		<td width="200px" height="35px"class="table_title" > &nbsp;* 배송지명 </td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" size="40" id="delivname" value="${addr.delivname}"></td>
	</tr>
	<tr>
		<td class="table_title" height="35px"> &nbsp;*수령인</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" size="40" id="delivperson" value="${addr.delivperson}"></td>
	</tr>
	<tr>
		<td class="table_title" height="35px"> &nbsp;&nbsp;전화번호</td>
		<td>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id="phone11">
				    <option value="02">02</option>
				    <option value="010">010</option>
				    <option value="031">031</option>
				    <option value="032">032</option>
				    <option value="033">033</option>
				    <option value="041">041</option>
				    <option value="042">042</option>
				    <option value="043">043</option>
				    <option value="051">051</option>
				    <option value="052">052</option>
				    <option value="053">053</option>
				    <option value="054">054</option>
				    <option value="055">055</option>
				    <option value="061">061</option>
				    <option value="062">062</option>
				    <option value="063">063</option>
				    <option value="064">064</option>
				    <option value="070">070</option>
				    <option value="080">080</option>
				    <option value="0303">0303</option>
				    <option value="0502">0502</option>
				    <option value="0504">0504</option>
				    <option value="0505">0505</option>
				    <option value="0506">0506</option>
				    <option value="0507">0507</option>
				    <option value="0508">0508</option>
			</select>
			-<input type="text" size="13" id="phone12" value="${addr.phone12}">-<input type="text" size="13" id="phone13" value="${addr.phone13}">
		</td>
	</tr> 
	<tr>
		<td class="table_title" height="35px">&nbsp;*휴대전화</td>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<select id="phone21">
				<option>010</option>
				<option>011</option>
				<option>016</option>
				<option>017</option>
				<option>018</option>
				<option>019</option>
			</select>
			-<input type="text" size="13" id="phone22" value="${addr.phone22}">-<input type="text" size="13" id="phone23" value="${addr.phone23}">
		</td>
	</tr>
	<tr>
		<td class="table_title" height="35px" rowspan="2">&nbsp;*배송지주소</td>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="post" id="sample4_postcode" placeholder="우편번호" size="30" value="${addr.post}">
<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" ></td><tr><td>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="addr" id="sample4_roadAddress" placeholder="도로명주소" size="30" value="${addr.address1}">
<input type="text" name="addr2" id="sample4_jibunAddress" placeholder="지번주소" size="30" value="${addr.address2}"><span id="guide" style="color:#999"></span></td><tr>
	</tr>
	<tr>
		<td colspan="2" align="center" height="60px" style="border-top: 1px solid red;">
			<input class="btns" type="button" value="저장" style="background-color: red;" id="submitbtn">
			<input class="btns" type="reset" value="취소" style="background-color: #F6F6F6;">
		</td>
	</tr>
</table>
</form>
</div>
</div>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/null/Content/mypage/js/addressAPI.js"></script>
<script src="/null/Content/mypage/js/modifyaddr.js"></script>
</body>
</html>