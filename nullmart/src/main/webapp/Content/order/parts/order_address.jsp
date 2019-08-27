<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" style="text/css" href="/null/Content/order/css/order_address.css">
<script src="/null/Content/order/js/order_address.js"></script>
	
<div class="body" >
	<form name="form1">
	<span class="title">주문 고객 정보</span>
	<div id="customer" class="box">
		<div class="boxer name">
			<div class="head"><div class="star">*</div><div>주문하시는 분</div></div>
			<div class="context" style="width:90%;">
				<input  id="user" name="user"  type="text"  value="${MEMBER.username}" readonly="readonly" style="width:10%;"> <input type="checkbox"> 주문자 변경
			</div>
		</div>
		<div class="boxer phone">
			<div class="head"><div class="star">*</div>휴대폰 번호</div>
			<div class="context">
				<select name="user_phone1" >
					<c:forEach var="phone_number" items="${phone_list}">
						<c:if test="${MEMBER.phone1==phone_number}">
						<option selected>${phone_number}</option>						
						</c:if>
						<option>${phone_number}</option>
					</c:forEach>
				</select>
				-<input type="text" name="user_phone2"  value="${MEMBER.phone2}">-<input type="text" name="user_phone3" value="${MEMBER.phone3}">
			</div>
		</div>
		<div class="boxer">
			<div class="head"><div class="star">*</div>이메일 주소</div>
			<div class="context">
				<input type="text" name="user_email1" value="${MEMBER.email1}"> 
				@ <input type="text" name="user_email2"  value="${MEMBER.email2}">
			</div>
		</div>
	</div>

	<span class="title">배송지 정보</span>
	<div id="address" class="box">
		<div class="boxer">
			<div class="head">배송방법</div>
			<div class="context">
				<input type="radio" name="order_deliver" checked="checked" value="post"> <span>일반택배</span>
				<input type="radio" name="order_deliver" value="manual"> <span>매장수령</span>
				</div>
		</div>
		<div class="boxer name">
			<div class="head"><div class="star">*</div>이름</div>
			<div class="context">
				<input type="text"  name="order_name" style="width:10%;" >
				<input type="radio" name="copy" id="same_radio"> 주문자와 동일 <input type="radio" name="copy" checked="checked" id="new_radio"> 신규 입력 <input type="radio" name="copy"> 최근 배송지
				<span id="book">내 주소록</span>
			</div>
		</div>
		<div class="boxer phone">
			<div class="head"><div class="star">*</div>휴대폰 번호</div>
			<div class="context">
				<select name="order_phone1">
					<c:forEach var="phone_number" items="${phone_list}">
						<option>${phone_number}</option>
					</c:forEach>
				</select>
				- <input type="text" name="order_phone2"> - <input type="text" name="order_phone3" re>
			</div>
		</div>
		<div class="boxer bell">
			<div class="head">전화 번호</div>
			<div class="context">
				<select name="order_telephone1">
					<option>02</option>
					<option>031</option>
					<option>032</option>
					<option>051</option>
					<option>041</option>
					<option>042</option>
				</select>
				- <input type="text" name="order_telephone2"> - <input type="text" name="order_telephone3"> 
			</div>
		</div>
		<div class="boxer address">
			<div class="head"><div class="star">*</div>주소</div>
			<div class="context">
				<input type="text" id="order_postcode" name="order_postcode" placeholder="우편번호" style="width: 15%;" readonly>
				<div onclick="execDaumPostcode()">우편번호 찾기</div><br>
				<input type="text" id="order_address" name="order_address" style="width: 30%;" readonly >
				<span id="guide" style="color:#999;display:none"></span>
				<input type="text" id="order_detailAddress" name="order_detailAddress"  placeholder="상세주소" style="width: 25%;">
			</div>
		</div>
		<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
		<script>
		    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
		    function execDaumPostcode() {
		        new daum.Postcode({
		            oncomplete: function(data) {
		                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
		
		                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
		                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
		                var addr = ''; // 도로명 주소 변수
		                var extraAddr = ''; // 참고 항목 변수
		
		             	//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
		                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
		                    addr = data.roadAddress;
		                } else { // 사용자가 지번 주소를 선택했을 경우(J)
		                    addr = data.jibunAddress;
		                }
		                
		                if (data.userSelectedType === 'R'){
		                	
		                	 // 법정동명이 있을 경우 추가한다. (법정리는 제외)
		               		 // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
		                	if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		                   		 extraAddr += data.bname;
		               		 }
		               		 // 건물명이 있고, 공동주택일 경우 추가한다.
		               		 if(data.buildingName !== '' && data.apartment === 'Y'){
		                   		extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                	}
		                } else{
		   	             // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
			                extraAddr = ' (' + extraAddr + ')';
		                }
		
		                // 우편번호와 주소 정보를 해당 필드에 넣는다.
		                document.getElementById('order_postcode').value = data.zonecode;
		                document.getElementById("order_address").value = addr;
		                
		                var guideTextBox = document.getElementById("guide");
		                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
		                if(data.autoRoadAddress) {
		                    var expRoadAddr = data.autoRoadAddress + extraAddr;
		                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
		                    guideTextBox.style.display = 'block';
		
		                } else if(data.autoJibunAddress) {
		                    var expJibunAddr = data.autoJibunAddress;
		                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
		                    guideTextBox.style.display = 'block';
		                } else {
		                    guideTextBox.innerHTML = '';
		                    guideTextBox.style.display = 'none';
		                }
		            }
		        }).open();
		    }
		</script>
		<div class="boxer request">
			<div class="head">배송시 요청사항</div>
			<div class="context"><input type="text"  name="order_mesg" placeholder="배송 메세지는 40자 이내로 입력해주세요"></div>
		</div>
	</div>
	
	<span class="title">결제수탄 선택</span>
	<div id="payment" class="box">
		<div class="boxer">
			<input class="method" type="radio" name="payment" checked="checked" value="credit"><span>신용카드</span>
			<input class="method" type="radio" name="payment" value="bankbook"><span>계좌이체</span>
			<input class="method" type="radio" name="payment" value="cellphone"><span>휴대폰</span>
		</div>
	</div>
	
	<div id="decision" class="box">
		<div>결제하기</div>
	</div>
	</form>	
</div>