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
<title>주문현황 조회</title>
<link rel="stylesheet" href="/null/Content/mypage/css/orderinfo.css?ver=2">
</head>
<body>
<script src="/null/Content/api/jquery/jquery-3.4.1.js"></script>
<jsp:include page="top.jsp"></jsp:include>
<div id="body">
<div id="top_box" >
	<input type="hidden" id="selectDays" value="${selectDays}">
	<p style="font-weight:bolder; font-size: 20px;">주문현황 조회</p>
	<div id="order_search">쇼핑몰 구매 내력
		<button type="button" class="dateValue">오늘</button>
		<button type="button" class="dateValue">15일</button>
		<button type="button" class="dateValue">1개월</button>
		<button type="button" class="dateValue">3개월</button>
		<input type="date" id="date1" >~
		<input type="date" id="date2" >
		<input type="hidden" id="searchDate" value="">
		<input type="hidden" id="date1Value" value="">
		<input type="hidden" id="date2Value" value="">
		<button type="button" id="searchBtn">조회</button>
	</div>
</div>
<div id="order_top">
	<span class="order_top_content">주문번호</span>
	<span class="order_top_content">주문내용</span>
	<span class="order_top_content">결제방법</span>
	<span class="order_top_content">주문날짜</span>
</div>
<div id="order_body">
	<c:if test="${not empty orderlist}">
	<c:forEach var="order" items="${orderlist}">
	<div class="order_list">
		<span class="order_list_content">${order.ono}</span>
		<span class="order_list_content">${order.ordername}</span>
		<span class="order_list_content">${order.paymethod}</span>
		<span class="order_list_content">${order.order_date}</span>
	</div>
	</c:forEach>
	<%-- <table >
		<c:forEach var="order" items="${orderlist}">
		<tr style="font-weight: bold; " height="40px;" align="center">
			<td width="200px;">${order.ono}</td>
			<td width="200px;">${order.ordername}</td>
			<td width="200px;">${order.paymethod}</td>
			<td width="200px;">${order.order_date}</td>
		</tr>
		</c:forEach>
	</table>  --%>
	</c:if>
	<c:if test="${empty orderlist}">
		<div id="empty_body">
			<span>주문 내역이 없습니다.</span>
		</div>
	</c:if>
	<%-- <c:forEach var="order" items="${orderlist}"><div class="order_list_content">${order.ono}</div><div class="order_list_content">${order.ordername}</div><div class="order_list_content">${order.paymethod}</div><div class="order_list_content">${order.order_date}</div></c:forEach> --%>
</div>
</div>
<script src="/null/Content/mypage/js/orderinfo.js?ver=3"></script> 
</body>
</html>