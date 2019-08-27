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
<title>나의 상품후기 작성</title>
<link rel="stylesheet" type="text/css" href="/null/Content/mypage/css/orderevallist.css?ver=1">
</head>
<body>
<script src="/null/Content/api/jquery/jquery-3.4.1.js"></script>
<jsp:include page="top.jsp"></jsp:include>
<div id="body">
	<div style="font-weight: bolder; margin-top: 10px;">나의 상품 후기 작성</div>
	<div id="head_title">
		<div class="head_content"><img src="/null/Content/img/mypage/comment.PNG" width="100" height="100"></div>
		<div class="head_content">
			<pre>
* 상품 후기를 작성해주시면 발도장 10개를 드립니다.
* 상품 후기를 작성해주시면 최대 2000포인트 (일반후기: 500~1,000P, 사진후기: 1,000~2,000P)를 적립해드립니다.
* 후기 포인트는 등록일 이후 최대 3일 이내에 적립해드립니다. (주말 및 공휴일 제외)
* 용품 및 액세서리에 대한 후기는 포인트 지급이 제외됩니다.
* 직접 촬영한 사진이 아닐 경우 포토 후기에 대한 포인트 지급이 제외됩니다.
* 허위, 과대광고, 문의 등 후기 내용과 관련 없는 글은 통보 없이 삭제될 수 있습니다.
* 구매일 또는 구매확정일로부터 30일이 지난 상품에 대한 후기는 포인트 지급이 되지 않습니다.
* 포인트는 멤버십회원에게만 지급됩니다.
			</pre>
		</div>
	</div>
	<div id="body_title" style="border-top: 1px solid red; font-size: 14px; font-weight: bold;">
		<span class="body_item" style="width: 200;">주문일</span>
		<span class="body_item">주문번호</span>
		<span class="body_item">상품명</span>
		<span class="body_item">구매후기</span>
	</div>
	<div id="body_content">
		<c:if test="${not empty orderevallist}">
		<c:forEach var="ordereval" items="${orderevallist}">
		<div class="body_contentblock">
			<span class="body_contentinner">${ordereval.order_date}</span>
			<span class="body_contentinner">${ordereval.ono}</span><input type="hidden" value="${ordereval.ono}">
			<span class="body_contentinner">${ordereval.ordername}</span><input type="hidden" value="${ordereval.ordername}">
			<span class="body_contentinner"><input type="button" class="evalbtns" value="수정"><input type="hidden" value="${ordereval.evalno}"></span>
		</div>
		</c:forEach>
		</c:if>
		<c:if test="${empty orderevallist}">
		<div id="body_emptyblock">
			<div>주문 내역이 없습니다.</div>
		</div>
		</c:if>
	</div>
	
</div>
<script src="/null/Content/mypage/js/orderevallist.js?ver=1"></script>
</body>
</html>