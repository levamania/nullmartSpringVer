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
<%--
	class
	mid_box: 페이지의 전체 중간 div
	click_div: click 이벤트로 탭이동
	
	id
	head_title: 페이지의 title 변경용  
	tab_parent: 탭 최상위 div 위치 지정용 
 --%>
<html>
<head>
<meta charset="UTF-8">
<title>반품/교환/AS</title>
<style type="text/css">
	.mid_box{
		display: inline-block;
		vertical-align: top;
	}
	.click_div{
		display: inline-block;
		position:absolute;
		width: 150px;
		height: 30px;
		border: 1px solid #FFD9EC;
		padding-top:5px;
		background-color: #F6F6F6;
	}
	#tab_parent{
		position: relative;
	}
	.content{
		width:900px;
		height:400px;
		background-color: #F6F6F6;
		border: 2px solid red;
		z-index: 2;
		position: absolute;
		top: 36px;
	}
</style>

</head>
<body>
<script src="/null/Content/api/jquery/jquery-3.4.1.js"></script>
<jsp:include page="top.jsp"></jsp:include>
<div class="mid_box">
<p style="font-weight:bolder; font-size: 20px;" id="head_title">반품/교환/AS</p>
<div id="tab_parent">
	<div class="click_div" style="left: 0px;" align="center" id="return_div">반품</div>
	<div class="click_div" style="left: 150px;" align="center">교환</div>
	<div class="click_div" style="left: 300px;" align="center">AS</div>
	<div id="exchange_content" class="content">
		<h2>&nbsp;&nbsp;교환 주의사항</h2>
		<h3>&nbsp;&nbsp; 교환 안내</h3><pre>
  - 제품을 받으신 날부터 14일 이내에 접수해주시기 바랍니다.
  - 접수 시 왕복 택배비가 부과됩니다. 
  - 접수 후 25일 이내에 상품이 교환지로 도착하지 않을 경우 접수가 취소됩니다.
    (배송 지연 제외)</pre>
    <h3>&nbsp;&nbsp; 교환 택배비 안내</h3><pre>
  - 박스 포장 안내
  - 왕복 택배비 : 최초 배송비(2,500원) + 반품배송비(2,500원) = 총 5,000원이 부과됩니다.
  - 지정택배(대한통운) 외 타택배 이용 시 추가로 발생되는 금액은 고객님께서 부담해주셔야 합니다.</pre>
	</div>	
	<div id="as_content" class="content">
		<h2>&nbsp;&nbsp;AS신청 안내</h2>
		<pre>
  - 수선을 원하는 내용을 자세하게(사진 첨부 가능) 기재해주면 처리 시 도움이 될 수 있습니다.
  - 수선 접수 시 왕복 택배비(5000원)가 부과됩니다.
  - 지정택배(대한통운) 외 타택배 이용 시 추가로 발생되는 금액은 고객님께서 부담해주셔야 합니다.
  - 접수 없이 상품을 NULL-MART로 보내실 경우 확인이 어려워 반송되거나 처리가 늦어질 수 있습니다.
  - 접수 완료 후 15일 이내 상품 도착하지 않을 경우 접수가 취소 됩니다.
  - 브랜드 박스의 훼손이 없도록 택배 박스 및 타 박스로 포장하여 발송해주시기 바랍니다.
  - 오프라인 매장에서도 신청 가능합니다. (매장 방문 접수 시 택배비 무료)
		</pre>
	</div>
	<div id="return_content" class="content">
		<h2>&nbsp;&nbsp;반품 주의사항</h2>
		<h3>&nbsp;&nbsp; 반품 안내</h3><pre>
  - 제품을 받으신 날부터 7일 이내(상품불량인 경우 30일)에 접수해주시기 바랍니다.
  - 접수 시 왕복 택배비가 부과됩니다. 
    (단, 상품 불량, 오배송의 경우 택배비를 환불해드립니다.)
  - 접수 후 14일 이내에 상품이 반품지로 도착하지 않을 경우 접수가 취소됩니다.
    (배송 지연 제외)
  - 매장에 방문하여 반품 접수 시 최대 10개 미만 상품만 접수 가능합니다. 
    (대량 반품은 온라인 접수만 가능, 단순 변심의 경우 택배비 고객부담)</pre>
    <h3>&nbsp;&nbsp; 반품 택배비 안내</h3><pre>
  - 박스 포장 안내
  - 왕복 택배비 : 최초 배송비(2,500원) + 반품배송비(2,500원) = 총 5,000원이 부과됩니다.(선결제 또는 환불금액에서 차감 선택)
  - 단, 보내주신 상품이 불량 또는 오배송으로 확인 될 경우 택배비를 환불해드립니다.(선택하신 결제수단으로 택배비 환불)
  - 지정택배(대한통운) 외 타택배 이용 시 추가로 발생되는 금액은 고객님께서 부담해주셔야 합니다.</pre>
	</div>
</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$(".click_div").each(function(idx,div){
			$(div).css("border-bottom","2px solid red");
			$(div).css("border-top","1px solid #FFD9EC");
			$(div).css("border-left","1px solid #FFD9EC");
			$(div).css("border-right","1px solid #FFD9EC");
			$(div).css("z-index","1");
		});
		
		$("#return_div").css("border-bottom","1px solid #F6F6F6");
		$("#return_div").css("border-top","2px solid red");
		$("#return_div").css("border-left","2px solid red");
		$("#return_div").css("border-right","2px solid red");
		$("#return_div").css("z-index","10");
		
		$(".click_div").each(function(idx,div){
			$(div).on("click",function(){
				$(".click_div").not(this).each(function(idx,div){
					$(div).css("border-bottom","2px solid red");
					$(div).css("border-top","1px solid #FFD9EC");
					$(div).css("border-left","1px solid #FFD9EC");
					$(div).css("border-right","1px solid #FFD9EC");
					$(div).css("z-index","1");
				});
				var name = $(div).text();
				if(name=='반품'){
					$("#return_content").css("z-index","5");
					$("#exchange_content").css("z-index","3");
					$("#as_content").css("z-index","3");
				}else if(name =='교환'){
					$("#return_content").css("z-index","3");
					$("#exchange_content").css("z-index","5");
					$("#as_content").css("z-index","3");
				}else if(name =='AS'){
					$("#return_content").css("z-index","3");
					$("#exchange_content").css("z-index","3");
					$("#as_content").css("z-index","5");
				}
				$(div).css("border-bottom","1px solid #F6F6F6");
				$(div).css("border-top","2px solid red");
				$(div).css("border-left","2px solid red");
				$(div).css("border-right","2px solid red");
				$(div).css("z-index","10");
			});
		});
	});
</script>
</body>
</html>