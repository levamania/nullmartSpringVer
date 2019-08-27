<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" style="text/css"
	href="/null/Content/order/css/order_body.css">
<script src="/null/Content/order/js/order_body.js"></script>

<div class="body cart">
	<div class="container" id="cart_logo">
		<div class="content">
			<div>장바구니</div>
		</div>
		<div class="content">
			<div class="phase">
				<div class="img">
				</div>
				<div class="text">01</div>
				<div class="text">장바구니</div>
			</div>
			<div class="phase">
				<div class="img">
					<img src="/null/Content/img/cart/basket.png">
				</div>
				<div class="text">02</div>
				<div class="text">주문서작성/결제</div>
			</div>
			<div class="phase">
				<div class="img"></div>
				<div class="text">03</div>
				<div class="text">결제완료</div>
			</div>
			<div class="phase">
				<div class="img"></div>
			</div>
			<div id="line"></div>
		</div>
	</div>
	<div class="container" id="cart_list">
		<div class="content">
			<div>상품명</div>
			<div class="calc">
				<div>옵션</div>
				<div>판매가</div>
				<div>수량</div>
				<div>주문금액</div>
			</div>
		</div>
		<c:set var="TOTAL" value="0" />
		<c:forEach var="ITEM" items="${KEY_SET}">
			<div class="content">
				<div class="item">
					<span>${ITEM}.</span>
					<span class="pname"></span>
				</div>
				<div class="calc">
					<!-- 변수설정 -->
					<c:set var="SUM" value="0" />
					<c:set var="COLOR" value="INITIAL" />

					<c:forEach var="ATOM" items="${PCODE_MAPPED[ITEM]}" 	varStatus="stat">
						<div class="group">
							<div class="option">
								<c:choose>
									<c:when test="${COLOR!= ATOM.PCOLOR}">
										<div>
											<c:choose>
												<c:when test="${ATOM.PCOLOR=='WHITE'}">
													<span class="head"
														style="color:${ATOM.PCOLOR}; background-color:black;">${ATOM.PCOLOR}</span>
												</c:when>
												<c:otherwise>
													<span class="head" style="color:${ATOM.PCOLOR};">${ATOM.PCOLOR}</span>
												</c:otherwise>
											</c:choose>
											/ <span>${ATOM.PSIZE}</span> / <span class ="name">${ATOM.PNAME}</span>
										</div>
										<c:set var="COLOR" value="${ATOM.PCOLOR}" />
									</c:when>
									<c:otherwise>
										<div>
											<span style="opacity: 0; color:${ATOM.PCOLOR}">${ATOM.PCOLOR}</span>
											/ <span>${ATOM.PSIZE }</span> / <span class ="name">${ATOM.PNAME}</span>
										</div>
									</c:otherwise>
								</c:choose>
								<div class="cno">${ATOM.CNO}</div>
							</div>
							<div class="sell num">${ATOM.PPRICE}</div>
							<div class="count">
								<div class="figure">
									<span class="estate">"<span>${ATOM.PAMOUNT}</span>" ea</span>
									<span style="display: none" class="origin">${ATOM.PAMOUNT}</span>
								</div>
							</div>
							<div class="decart num">${ATOM.PPRICE * ATOM.PAMOUNT}</div>
							<c:set var="SUM" value="${SUM+ATOM.PPRICE * ATOM.PAMOUNT}" />
							<div class="math multi">X</div>
							<div class="math equal">=</div>
						</div>
					</c:forEach>
				</div>
				<div class="sum">
					<div>
						합계 <span class="small_sum num">${SUM}</span>
					</div>
					<c:set var="TOTAL" value="${TOTAL + SUM}" />
					<div></div>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="container" id="cart_total">
		<div class="content">
			<div>
				<div>주문금액</div>
				<div id="temp" class="num">${TOTAL}</div>
				<div></div>
			</div>
			<div>
				<div>배송비</div>
				<div>2,500원</div>
				<div></div>
			</div>
			<div>
				<div>결정예정금액</div>
				<div id="final" class="num">${TOTAL + 2500}</div>
			</div>
		</div>
	</div>
</div>
<div style="display: none" id="login">${login.userid}</div>