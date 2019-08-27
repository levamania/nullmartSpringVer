<%@page import="java.util.List"%>
<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="/null/Content/product/css/information.css">
<form action="" method="post" onsubmit="return false">
<div class="body" id="product_info">
	<div id="product_img">
		<img src="/null/Content/img/shoes/${product.STYLEMID}/${product.STYLEBOT}/${product.PIMAGE}.jpg">
	</div>
	<div id="option">
		<div class="title">
			<span style="font-size: 32px; font-weight: bolder; font-family: Maplestroy";>
				${product.PCODE} ${product.PNAME} 
			</span> 
			<span style="font-size: 12px; color: gray;">스타일 코드: ${SCODE[0]}</span>
		</div>
		<div class="content">
				<%
					NumberFormat nbf = NumberFormat.getCurrencyInstance();
				    List<Object> prices = (List<Object>)request.getAttribute("PPRICE");
 					String formatted_Price = nbf.format(Integer.parseInt(prices.get(0).toString()));		
				%>
			<div>판매가</div>
			<div><%=formatted_Price%></div>
		</div>
		<div class="content">
			<div>배송비</div>
			<div>${(DELIVERFEE_YN[0]=='Y')?'2,500원':'무료 배송'}</div>
		</div>
		<div class="content">
			<div>색상</div>
			<div id="colors">
				<c:forEach var="item" items="${PCOLOR}">
					<div class="color" style="background-color:${item}"></div>
				</c:forEach>
			</div>
		</div>
		<div class="content" >
			<div>사이즈</div>
			<div id="sizes">
				<c:forEach var="item" items="${PSIZE}">
					<div class="size">${item}</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<div id="payment">
		<div id="total">총 결제 금액&nbsp;<span id="total_price" style="color:red;font-size:25px;">0</span>&nbsp;원</div>
		<div>찜하기</div>
		<div>장바구니</div>
		<div>바로구매</div>
	</div>
	
	<!-- 리소스 -->
	<div class="content	 reposit">
		<div style="flex:30"></div>
		<div style="flex:25; text-align: center"></div>
		<div style="flex:30"></div>
		<div style="flex:10"></div>
	</div>
	
	<div class="layout style"></div>
	<div class="layout position">
	<div id="pop_up">
		<div id="close"></div>	
		<div id="content">
			<div></div>
			<div>상품을 담았습니다</div>
		</div>
		<div id="redirector">
			<div>쇼핑 계속하기</div>
			<div>장바구니 보러가기</div>
		</div>
	</div>
	</div>

	
</div>
</form>