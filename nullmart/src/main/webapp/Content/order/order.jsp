<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- script -->
<script type="text/javascript" src="/null/Content/api/jquery/jquery-3.4.1.js"></script>
<!-- style -->
</head>
<body>
	<!-- 탑 -->
	<jsp:include page="/Content/statics/top/top.jsp" flush="true" />
	<!-- 주문상품 -->
	<jsp:include page="/Content/order/parts/order_body.jsp" flush="true" />
	<!-- 개인정보 입력창 -->
	<jsp:include page="/Content/order/parts/order_address.jsp" flush="true" />
	<!-- 바텀 -->
	<jsp:include page="/Content/statics/bottom/bottom.jsp" flush="true" />
</body>
</body>
</html>