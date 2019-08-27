<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:if test="${empty login}">
	<%
		response.sendRedirect("/null/LoginUIServlet");
	%>
</c:if>
<%
	String ordername = request.getParameter("ordername");
	String ono = request.getParameter("ono");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 상품후기 작성</title>
<link rel="stylesheet" type="text/css" href="/null/Content/mypage/css/ordereval.css?ver=1">
</head>
<body>
<script src="/null/Content/api/jquery/jquery-3.4.1.js"></script>
<jsp:include page="top.jsp"></jsp:include>
<input type="hidden" name="ono" value="">
<div id="body">
	<form action="/null/OrderEvalServlet" method="post">
	<div id="eval_title">나의상품후기작성</div>
	<div id="eval_body">
		<div class="eval_item">
			<div class="eval_head">상품이름&nbsp;&nbsp;</div>
			<div class="eval_content">
				<%=ordername%>
			</div>
		</div>
		<div class="eval_item">
			<div class="eval_head" style="padding-right: 7px;">상품평가&nbsp;&nbsp;</div>
			<div class="eval_content">
				<span class="star-input">
				<span class="input">
    			<input type="radio" name="star-input" value="1" id="p1">
    			<label for="p1">1</label>
    			<input type="radio" name="star-input" value="2" id="p2">
    			<label for="p2">2</label>
    			<input type="radio" name="star-input" value="3" id="p3">
    			<label for="p3">3</label>
    			<input type="radio" name="star-input" value="4" id="p4">
    			<label for="p4">4</label>
    			<input type="radio" name="star-input" value="5" id="p5">
    			<label for="p5">5</label>
  				</span>
  				<!-- <output for="star-input"><b>0</b>점</output> -->
  				<span>별을 클릭하여 상품의 만족도를 알려주세요</span>
  				<input type="hidden" id="start_score" name="orderscore" value="0">
  				<input type="hidden" name="ono" value="<%=ono%>">
				</span>
			</div>
			<div class="eval_content"></div>
		</div>
		<div class="eval_item">
			<div class="eval_head" style="padding-right: 30px;">만족도평가</div>
			<div class="eval_content">
				<div>튼튼했나요?</div>
				<span class="radio_content"><input type="radio" name="ordersatis" value="1" checked="checked">별로예요</span>
				<span class="radio_content"><input type="radio" name="ordersatis" value="3">보통이에요</span>
				<span class="radio_content"><input type="radio" name="ordersatis" value="5">튼튼해요</span>
				<div>배송이 빨랐나요?</div>
				<span class="radio_content"><input type="radio" name="fastdelivery" value="1" checked="checked">별로예요</span>
				<span class="radio_content"><input type="radio" name="fastdelivery" value="3">보통이에요</span>
				<span class="radio_content"><input type="radio" name="fastdelivery" value="5">빨라요</span>
			</div>

		</div>
		<div class="eval_item">
			<textarea rows="22" cols="122" maxlength="200" name="evalcontent" id="evalcontent"></textarea>
		</div>

		<div style="display: inline-flex; width: 900px; align-items: center; justify-content: center; margin-bottom:15px;">
			<input type="submit" value="저장 " style="height: 30pt; width:60pt; font-size: 20px; background-color: red;">
			<input type="button" value="취소 " style="height: 30pt; width:60pt; font-size: 20px; background-color: rgba(219, 228, 247, 0.6);" id="cancel">
		</div>

	</div>
</form>
</div>
<script src="/null/Content/mypage/js/ordereval.js?ver=1"></script>
<script src="/null/Content/mypage/js/jquery-1.11.3.min.js"></script>
</body>
</html>
