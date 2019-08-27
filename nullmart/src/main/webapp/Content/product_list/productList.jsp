<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- script -->
<script type="text/javascript" src="/null/Content/api/jquery/jquery-3.4.1.js"></script>
<!-- style -->
<link rel="stylesheet" type="text/css" href="/null/Content/product_list/css/productList.css">
</head>
<body>
<!-- 고정 탑 -->
<jsp:include page="/Content/statics/top/top.jsp" flush="true"/>
<!-- 프로덕트 리스트 -->
	<!-- 검색결과창 -->
<jsp:include page="/Content/product_list/parts/searchResult.jsp" flush="true"/>
	<!-- 상세검색옵션 -->
<jsp:include page="/Content/product_list/parts/searchingOption.jsp" flush="true"/>
	<!-- 상품 리스트 -->
<jsp:include page="/Content/product_list/parts/searchedProducts.jsp" flush="true"/>
<!--고정 바텀 -->
<jsp:include page="/Content/statics/bottom/bottom.jsp" flush="true"/>

<!-- 툴바 -->
<jsp:include page="/Content/statics/floating_bar/floating_bar.jsp" flush="true"/>
</body>
</html>