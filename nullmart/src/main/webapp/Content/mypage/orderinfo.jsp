<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:if test="${empty login}">
	<%
		response.sendRedirect("/null/loginForm");
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
<!-- submit-->
<form action="/null/mypage/orderInfo" method="get">
<div id="body">
<div id="top_box" >
	<input type="hidden" id="selectDays"  value="${selectDays}">
	<p style="font-weight:bolder; font-size: 20px;">주문현황 조회</p>
	<div id="order_search">쇼핑몰 구매 내력
		<button type="button" class="dateValue">오늘</button>
		<button type="button" class="dateValue">15일</button>
		<button type="button" class="dateValue">1개월</button>
		<button type="button" class="dateValue">3개월</button>
		<input type="date" id="date1" >~
		<input type="date" id="date2" >
		<input type="hidden" id="searchDate" name="searchDate" value="">
		<!-- <input type="hidden" id="date1Value" value="">
		<input type="hidden" id="date2Value" value=""> -->
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
		<span class="order_list_content"><a href="#" class="scode">${order.scode}</a></span>
		<span class="order_list_content">${order.paymethod}</span>
		<span class="order_list_content">${order.order_date}</span>
	</div>
	</c:forEach>
	<!--페이징 -->
	<c:choose>
			<c:when test="${empty page}">
				<input type="hidden" id="cur" name="cur" value="1">
				<input type="hidden" id="startCur" name="startCur" value="1">
				<input type="hidden" id="endCur" name="endCur" value="0">
			</c:when>
			<c:otherwise>
				<input type="hidden" id="cur" name="cur" value="${page.cur}">
				<input type="hidden" id="startCur" name="startCur" value="${page.startCur}">
				<input type="hidden" id="endCur" name="endCur" value="${page.endCur}">
			</c:otherwise>
			</c:choose>
	<input type="hidden"  name="groupindecator" id="groupindecator" value="0">
	<c:if test="${!empty page}">
			<div id="group_a">
				<c:if test="${page.cur >= page.cols}">
					<c:if test="${page.endCur>page.cols}">
						<span><a><img src="/null/Content/img/mypage/left.PNG" width="24" height="auto"  id="prevGroup"></a></span>&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>
				</c:if>
				<c:forEach items="${page.nums}" var="num">
					<a href="">${num}</a>&nbsp;&nbsp;&nbsp;&nbsp;
				</c:forEach>
				<c:choose>
					<c:when test="${page.maxPage - page.cur> page.cols}">
						<span><a><img src="/null/Content/img/mypage/right.PNG" width="24" height="auto"  id="nextGroup"></a></span>
					</c:when>
					<c:when test="${page.cur eq page.endCur}">
						<span><a><img src="/null/Content/img/mypage/right.PNG" width="24" height="auto"  id="nextGroup"></a></span>
					</c:when>
				</c:choose>
			</div>
		</c:if>
	</c:if>
	<c:if test="${empty orderlist}">
		<div id="empty_body">
			<span>주문 내역이 없습니다.</span>
		</div>
	</c:if>
</div>
</div>
</form>
<script src="/null/Content/mypage/js/orderinfo.js?ver=4"></script> 
</body>
</html>