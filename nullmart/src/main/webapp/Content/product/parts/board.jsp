<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" type="text/css"
	href="/null/Content/product/css/board.css">
<script src="/null/Content/product/js/board.js"></script>
<script src="/null/Content/api/ckeditor/ckeditor.js"></script>

<div class="body" id="board">
	<div id="board_content">
		<div class="header">
			<div data-target="board" class="bo active">상품 정보</div>
			<div data-target="eval" class="ev">상품 후기</div>
			<div data-target="info" class="in">배송/AS 안내</div>
		</div>
		<div class="pool"></div>
	</div>

	<div id="eval_content">
		<div class="header">
			<div data-target="board" class="bo">상품 정보</div>
			<div data-target="eval" class="ev active">상품 후기</div>
			<div data-target="info" class="in">배송/AS 안내</div>
		</div>
		<div class="pool">
			<div class="index record">
				<div>내용</div>
				<div>상품 만족도</div>
				<div>작성자</div>
			</div>
			<c:forEach var="RECORD" items="${eval_list}" varStatus="stat">
				<c:choose>
					<c:when test="${stat.count<=6}">
						<div class="content record">
					</c:when>
					<c:otherwise>
						<div class="content record leo">
					</c:otherwise>
				</c:choose>
				<div>${RECORD.EVALCONTENT}</div>
				<div>${RECORD.ORDERSCORE}</div>
				<div>${RECORD.USERID}</div>
		</div>
		<div class="organ">
			<div class="summing">
				<span>상품 만족도</span><span>${RECORD.ORDERSATIS}</span> <span>배송
					만족도</span><span>${RECORD.FASTDELIVERY}</span>
			</div>
			<div class="text_area">${RECORD.EVALCONTENT}</div>
		</div>
		</c:forEach>
		<c:if test="${empty eval_list}">
			<div>상품의 후기가 없습니다.</div>
		</c:if>
	</div>
	<div id="paging">
		<c:forEach var="no" begin="1" end="${fn: length(eval_list) /6+1}">
			<div>${no}</div>
		</c:forEach>
	</div>
</div>



<div id="info_content">
	<div class="header">
		<div data-target="board" class="bo">상품 정보</div>
		<div data-target="eval" class="ev">상품 후기</div>
		<div data-target="info" class="in active">배송/AS 안내</div>
	</div>
	<div class="pool"></div>
</div>

</div>