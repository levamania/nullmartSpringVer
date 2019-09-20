<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
		<div class="pool" >
			<div>상품이미지가 없습니다.</div>
<!-- 			<img src="/null/Content/img/cart/down.png"> -->
		</div>
	</div>

	<div id="eval_content">
		<div class="header">
			<div data-target="board" class="bo">상품 정보</div>
			<div data-target="eval" class="ev active">상품 후기</div>
			<div data-target="info" class="in">배송/AS 안내</div>
		</div>
		<div class="pool">
			<div class="starry record">
				<div>상품 만족도</div>
				<div>${score_avg/5*100} <span>&nbsp;&nbsp;%</span></div>
				<div class="eval">${score_avg}</div>
				<div >${score_avg} / 5</div>
			</div>
		
			<div class="index record">
				<div>내용</div>
				<div>상품 만족도</div>
				<div>작성자</div>
			</div>
			<c:forEach var="RECORD" items="${eval_list}" varStatus="stat">
				<div class="wrap record leo">
					<div>${RECORD.EVALCONTENT}</div>
					<div class="eval">${RECORD.ORDERSCORE}</div>
					<div>${RECORD.USERID}</div>
				</div>
				<div class="organ">
					<div class="summing">
						<span>색상 만족도</span><span class="eval">${RECORD.ORDERSATIS}</span> 
						<span>배송 만족도</span><span class="eval">${RECORD.FASTDELIVERY}</span>
					</div>
					<div class="text_area">
						<span>구매한 상품: ${RECORD.SCODE}</span><br><br>
						<span>${RECORD.EVALCONTENT}</span>
					</div>
				</div>
			</c:forEach>
			<c:if test="${empty eval_list}">
				<div class="record" style="justify-content: center; border-top-width: 0;">상품의 후기가 없습니다.</div>
			</c:if>
		</div>
		
		<fmt:parseNumber  var="unit" value="5"  integerOnly="true"/>
		
		<div id="paging" data-page = ${unit }>
			<div id="left" class="arrow"></div>
			<c:set var="length" value="${fn: length(eval_list)/unit}"/>
			<c:forEach var="no" begin="1" end="${(length%1>0)?length+1:length}" varStatus="stat">
				<div class = "no" >${no}</div>
			</c:forEach>
			<div id="right" class="arrow"></div>
		</div>
	</div>



	<div id="info_content">
		<div class="header">
			<div data-target="board" class="bo">상품 정보</div>
			<div data-target="eval" class="ev">상품 후기</div>
			<div data-target="info" class="in active">배송 안내</div>
		</div>
		<div class="pool">
			<div><span>배송 안내</span></div>
			<div>
				<span>배송비</span>
				<ul>
					<li>기본 배송비 2,500원</li>
					<li>특정 상품 무료배송(무료배송 상품 포함시 전체 무료 배송)</li>
				</ul>
			</div>
			<div>
				<span>평균 배송일</span>
				<ul>
					<li>평일 4시 이전 주문 당일 출고됩니다. (온라인 발송에 한함)</li>
					<li>결제 완료 후 평균 2일 소요됩니다. (주말 및 공휴일 제외)</li>
					<li>택배사의 사정에 따라 다소 지연될 수 있습니다. (대한통운 1588-1255)</li>
					<li>오프라인 매장 발송은 2~3일 더 소요될 수 있습니다.</li>
				</ul>
			</div>
		</div>
	</div>

</div>