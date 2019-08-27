<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- script -->
<script type="text/javascript" src="/null/Content/api/jquery/jquery-3.4.1.js"></script>
<jsp:include page="/Content/product/js/information.jsp" flush="true"/>
<!--  style -->
<link rel="stylesheet" type="text/css" href="/null/Content/product/css/product.css">
</head>
<body>
<!-- 고정 탑 -->
<jsp:include page="/Content/statics/top/top.jsp" flush="true"/>
<!-- 상품 정보 -->
<jsp:include page="/Content/product/parts/information.jsp" flush="true"/>
<!-- 상품 게시글 -->
<jsp:include page="/Content/product/parts/board.jsp" flush="true"/>
<!--고정 바텀 -->
<jsp:include page="/Content/statics/bottom/bottom.jsp" flush="true"/>
</body>
</html>