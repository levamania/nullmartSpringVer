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
<title>배송주소록관리</title>
<style type="text/css">
	#top_title{
		display: inline-block;
		vertical-align: top;
		position: relative;
	}
	#addr_title{
		position:absolute;
		top:60px;
		display: inline-block;
		font-size: 14px;
		padding-top: 8px;
		width:900px;
		height: 30px;
		border-top: 1px solid red;
		background-color: #F6F6F6;
	}
	#addr_list{
		position:absolute;
		top: 100px;
		width:900px;
		height: 30px;
		font-size: 14px;
		
	}.btns{
		font-size: 18px;
		width: 80pt;
		height: 26pt;
		padding: 2px;
	}
</style>
</head>
<body>
<script src="/null/Content/api/jquery/jquery-3.4.1.js"></script>
<jsp:include page="top.jsp"></jsp:include>
<div id="top_title">
<p style="font-weight:bolder; font-size: 20px;" id="head_title">배송주소록관리<input id="goaddradd" type="button" value="배송지 등록" style="position: absolute;left: 800px;"></p>
<div id="addr_title">
<table>
	<tr align="center">   
		<td width="50px;"></td> <td width="100px;">배송지명</td><td width="100px;">수령인</td>
		<td width="150px;">전화번호</td><td width="150px;">휴대전화</td><td width="200px;">배송지주소</td><td width="100px;">수정</td>
	</tr>
</table>
</div>
<div id="addr_list">
<c:set var="listSize" value="${fn:length(addrList)}"></c:set>
<c:choose>
<c:when test="${listSize==0}">
<div style="display: inline-block; width: 900px; height: 200px; padding-top: 70px;" align="center">등록된 주소가 없습니다.</div>
</c:when>
<c:when test="${listSize>0}">
<c:forEach var="addr" items="${addrList}">
<table >
	<tr align="center">   
		<td width="50px;"><input type="checkbox" id="$checkbtn{addr.delivno}" class="checks" value="${addr.delivno}"></td><td width="100px;">${addr.delivname}</td><td width="100px;">${addr.delivperson}</td>
		<td width="150px;">${addr.phone1}</td><td width="150px;">${addr.phone2}</td><td width="200px;">${addr.address1}</td><td width="100px;"><input type="hidden"  value="${addr.delivno}" class="delivnos"><input  class="modifybtns" type="button" value="수정"></td>
	</tr>
</table>
<div style="display: inline-block; border-bottom: 1px solid #F6F6F6; width: 900px;"></div>
</c:forEach>
<table>
	<tr align="center">   
		<td width="850px"><input type="button" value="전체선택" style="background-color: red;" class="btns" id="allcheck"><input type="button" value="선택삭제" style="background-color: #D4F4FA" class="btns" id="delete"></td>
	</tr>
</table>
</c:when>
</c:choose>

</div>
</div>

<script src="js/addrlist.js"></script>
</body>
</html>