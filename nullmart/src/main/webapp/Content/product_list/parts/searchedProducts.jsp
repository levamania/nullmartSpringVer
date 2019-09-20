<%@page import="java.io.File"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.dto.ProductDTO"%>
<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${!empty basic_setup || !empty pList}">
<jsp:include page="/Content/product_list/js/productList.jsp"/>
	<!-- 검색됨 -->
	<div class="body searched_product">
		<div id="order_info">
			<span class="order">신상품순<span>PREGITDATE:DESC</span></span> <span
				class="order">베스트 상품순<span>POPULARITY:DESC</span></span> <span
				class="order">낮은 가격순<span>MIN_PRICE:ASC</span></span> <span
				class="order">높은 가격순<span>MIN_PRICE:DESC</span></span> <select
				id="paging_quantity" name="paging_quantity">
				<option value="20">20개씩 보기</option>
				<option value="40">40개씩 보기</option>
				<option value="60">60개씩 보기</option>
			</select>
		</div>
		<hr style="border: 1px black solid; margin: 0;">
		<div id="other_info"></div> 
		<hr style="border: 1px gray solid; margin: 0;">
		<div id="searched_list" data-page="1">
			<c:choose>
				<c:when test="${!empty pList}">
					<!-- 	검색된 상품들 -->
					<c:forEach var="item" items="${pList}" varStatus="stat">
						<div class="product">
							<input type="hidden" name="pCode" value=" ${item.PCODE }">
							<img class="lich" src="/null/Content/img/common/loaded.gif"
							  data-src = "/null/Content/img/shoes/${item.STYLEMID}/${item.STYLEBOT}/${item.PIMAGE}">
							<br>
							<div class="item name">${item.PNAME}</div>
							<br>
							<div class="item price">
								<!-- 가격 원화 표시 -->
								<%
									NumberFormat nbf = NumberFormat.getCurrencyInstance();
													HashMap<String, Object> item = (HashMap<String, Object>) pageContext.getAttribute("item");
													int price = Integer.parseInt(item.get("MIN_PRICE").toString());
													String formatted_Price = nbf.format(price);
								%>
								<%=formatted_Price%>
							</div>
							<br>
							<div class="size_info">
								주문 가능 사이즈 <span style="display: none">${item.PCODE}</span>
							</div>
						</div>
						<c:if test="${stat.count%5==0}">
							<br>
						</c:if>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div id="null">상품이 없습니다 !</div>
				</c:otherwise>
			</c:choose>
		</div>
		<c:if test="${! empty pList }">
			<!-- 	페이징 -->
			<c:set var="unit" value="5" />
			<fmt:parseNumber var="phase" value="${cur_page/unit-0.1}"  integerOnly="true"/>
			
			<div id="paging">
				<c:if test="${phase*unit+1 != 1}">
					<div class="arrow" id="left"></div>
				</c:if>
 
				<c:forEach var="count" begin="${phase*unit+1}" end="${(page_size<unit*(1+phase))?page_size:unit*(1+phase)}">
					<c:choose>
						<c:when test="${count==cur_page}">
							<div class="page active" id="page${count}">${count}</div>
						</c:when>
						<c:otherwise>
							<div class="page" id="page${count}">${count}</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				<c:if test="${page_size > unit*(1+phase)}">
					<div class="arrow" id="right"></div>
				</c:if>
			</div>
			
		</c:if>
		
	</div>
	<div id="loading"> 
		<img src="/null/Content/img/common/loading.gif">
	</div>
</c:if>
