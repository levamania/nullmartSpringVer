<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>관리자페이지</title>
<link rel="stylesheet" type="text/css" href="/null/Content/admin/css/searchStock.css?ver=4" >
<link rel="stylesheet" href="/null/Content/api/jquery/jquery-ui/jquery-ui.css">
</head>
<script src="/null/Content/api/jquery/jquery-3.4.1.js"></script>
<script src="/null/Content/api/jquery/jquery-ui/jquery-ui.js"></script>
<body>
<header>
	<jsp:include page="top.jsp"/>
</header>
<form action="/null/SearchStockServlet" method="post">
<div id="container">
	<nav>
		<jsp:include page="left.jsp"/>
	</nav>
	<section>
		<div>
			상품 검색하기
		</div>
		<div id="input_pname">
			<div>상품명<input id="pname" name="pname"></div>
			<div>상품코드<input id="pcode" name="pcode" type="text" readonly="readonly"></div>
			<div><input type="button" value="조회" id="submitbtn"></div>
		</div>
		<div id="selections">
			<div>대분류
				<select id="styletop" name="styletop">
				</select>
			</div>
			<div>중분류
				<select id="stylemid" name="stylemid">
				</select>
			</div>
			<div>소분류
				<select id="stylebot" name="stylebot">
				</select>
				<input type="hidden" id="pregitdate" name="pregitdate">
			</div>
		</div>
		<div id="order_search">
			<div>
				<button type="button" class="dateValue">오늘</button>
				<button type="button" class="dateValue">15일</button>
				<button type="button" class="dateValue">1개월</button>
				<button type="button" class="dateValue">3개월</button>
				<button type="button" class="dateValue">1년</button>
				<button type="button" class="dateValue">전체</button>
			</div>
			<div>
				<input class="input_date" type="date" id="date1" >~
				<input class="input_date" type="date" id="date2" >
				<input type="hidden" id="searchDate" name="searchDate" value="">
				<c:choose>
					<c:when test="${empty searchoption}">
						<input type="hidden" id="isSearchOption" name="isSearchOption" value="0">
					</c:when>
					<c:otherwise>
						<input type="hidden" id="isSearchOption" name="isSearchOption" value="1">
						<input type="hidden" id="hpcode"  name="hpcode" value="${searchoption.pcode}">
						<input type="hidden" id="hpname"  name="hpname" value="${searchoption.pname}">
						<input type="hidden" id="hstyletop"  name="hstyletop" value="${searchoption.styletop}">
						<input type="hidden" id="hstylemid"  name="hstylemid" value="${searchoption.stylemid}">
						<input type="hidden" id="hstylebot"  name="hstylebot" value="${searchoption.stylebot}">
						<input type="hidden" id="hsearchDate"  name="hsearchDate" value="${searchoption.searchDate}">
					</c:otherwise>
				</c:choose>
				
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
				<div class="input_date" style="width: 150px;">상품 등록날짜입력</div>
			</div>
		</div>
		
		<div id="search_title">
			<div>재고코드</div>
			<div>상품코드</div>
			<div>상품명</div>
			<div>가격</div>
			<div>수량</div>
			<div>등록날짜</div>
		</div>
		<c:if test="${empty orders}">
			<div id="empty_body">
			상품검색 결과 없음</div>
		</c:if>
		<c:if test="${!empty orders}">
			<c:forEach items="${orders}" var="order">
				<div class="search_body">
					<div>${order.sCode}</div>
					<div>${order.pCode}</div>
					<div>${order.pName}</div>
					<div>${order.pPrice}</div>
					<div>${order.pAmount}</div>
					<div>${order.pRegitDate}</div>
				</div>
			</c:forEach>
		</c:if>
		<c:if test="${!empty page}">
			<div id="group_a">
				<c:forEach items="${page.nums}" var="num">
					<a href="">${num}</a>&nbsp;&nbsp;&nbsp;&nbsp;
				</c:forEach>
			</div>
		</c:if>
	</section>
</div>

</form>
<script src="/null/Content/admin/js/searchStock.js?ver=5"></script>
</body>
</html>