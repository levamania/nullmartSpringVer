<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="/null/Content/order/css/order_complete_body.css">
<script>
//숫자변환 함수
function toNum(price){
	var regEx = /\d{1,100}/g;
	var x = price.match(regEx);
	var string = "";
	for(var i of x){
		string+=i;
	}
	return Number.parseInt(string);
}
//자동 스크롤 함수
var distance = 0;
		$(".top1, .top2").each(function(){
			distance += toNum($(this).css("height"));
		})
var position = 0;
function scroller(){	
	if (position < distance){
    	position+=10;
    	scroll(0,position);
    	clearTimeout(timer);
    	var timer = setTimeout(scroller,0); timer;
    }
 }
scroller();
</script>
<div class="body" id="result">
	<div id="title">주문 결과</div>
	<div id="order_statement">
		<div>주문번호</div>
		<div>${completed.order_serial}</div>
		<div>주문금액</div>
		<div>${completed.order_price}원</div>
		<div>배송비</div>
		<div>${completed.deliver_fee}원</div>
		<div>총금액</div>
		<div>${completed.total_price}원</div>
	</div>

	<div id="redirector">
		<div id="mypage">마이 페이지</div>
		<div id="mainpage">계속 쇼핑하기</div>
	</div>
	<script>
		$("#mypage").on("click",()=>location.href="/null/maypage/orderInfo");
		$("#mainpage").on("click",()=>location.href="/null/main");
	</script>

	<div id="caller_info">
		<div></div>
	</div>
	<div id="order_info">
		<div></div>
	</div>
</div>