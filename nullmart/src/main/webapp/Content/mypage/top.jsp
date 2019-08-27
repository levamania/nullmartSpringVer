<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<c:if test="${empty login}">
	<%
		response.sendRedirect("/null/LoginUIServlet");
	%>
</c:if>
<%--
username - 회원정보
userid - 회원정보
password - 회원 패스워드
gotomyinfo - 나의 정보 확인 페이지 이동 
regdate - 가입일 
goordereval - 주문평가
goaddrinfo - 주소록
goorderlist - 주문 리스트 
.topbtn_box - 페이지 위의 버튼 제어 박스 
 --%>
<style type="text/css">
html,body{
	max-width:90%;height: 100%;
	margin: auto;
}
a{
	text-decoration: none;
}
.btn_parent{
	position: relative;
	width: 1000px;
	height: 240px;
}
.topbtn_box {
	width: 320px;
	height: 160px;
	padding: 10px;
	margin: 25px;
	display: inline-block;
	vertical-align: top;
}
.topbtn_right{
	display: inline-block;
	margin: 10px;
}
.left_menu{
	display: inline-block;
	width: 180px;
	height: 500px;
	background-color: gray;
}

</style>

<!-- <div style="padding-left: 50px; padding-right: 50px;"> -->
<script src="/null/Content/api/jquery/jquery-3.4.1.js"></script>
<jsp:include page="/Content/statics/top/top.jsp" flush="true" ></jsp:include>
<div >
	<div id="top_mypage"
		style="width: 1050px; text-align: center; font-size: 20px; font-weight: bolder; margin-bottom: 20px;">마이페이지</div>

	<div class="btn_parent">
		<div class="topbtn_box" style="border: 1px solid red; position: absolute; left: 100px; ">

			<div style="display: inline-block ;">
				<img src="/null/Content/img/mypage/Signup(checked).PNG">
			</div>
			<div style="display: inline-block;">
				<p>
					<span id="username"><b>${login.username} 회원님 반갑습니다.</b></span>
				</p> 
				<p style="font-size: 14px;">
					<img src="/null/Content/img/mypage/mypage_arrow.PNG" height="15" width="15">
					아이디:${login.userid}<span id="userid"></span>
				</p>
				<p style="font-size: 14px;">
					<img src="/null/Content/img/mypage/mypage_arrow.PNG" height="15" width="15">
					이메일:${login.email1}@${login.email2}<span id="userpassword"></span>
				</p>
				<input
						type="button" value="내정보 확인" id="gomyinfo">
				<!-- <p style="font-size: 12px;">
					&nbsp;&nbsp;가입일: <span id="regdate">2019-07-25</span> &nbsp;&nbsp;<input
						type="button" value="내정보 확인" id="gomyinfo">
				</p> -->

			</div>

		</div>



		<div class="topbtn_box" style="border: 1px solid gray; position: absolute; right: 50px; ">

			<div style="text-align: center">현황~~</div>
			<div  class="topbtn_right" style=";">
				<p style="margin-left: 12px;">
					<img src="/null/Content/img/mypage/signup(userinfo)2.PNG" height="50" width="50">
				</p>
				<p>
					<input type="button" value="상품후기 " id="goordereval">
				</p>
			</div>
			<div class="topbtn_right" style="">
				<p style="margin-left: 12px;">
					<img src="/null/Content/img/mypage/truck.PNG" height="50" width="50">
				</p>
				<p>
					<input type="button" value="배송지 관리" id="goaddrinfo">
				</p>
			</div>
			<div class="topbtn_right" style="">

				<p style="margin-left: 12px;">
					<img src="/null/Content/img/mypage/product.PNG" height="50" width="50">
				</p>
				<p>
					<input type="button" value="주문현황" id="goorderlist">
				</p>
			</div>

		</div>
	</div>
</div>
<div style="display: inline-block; border-bottom: 1px solid red; width: 1090px;"></div>
<div class ="left_menu">
	<p style="font-weight: bolder; font-size: 20px;">쇼핑내역</p>
	<p><a href="/null/OrderInfoServlet">주문현황 조회</a></p>
	<p><a href="returnAS.jsp">반품/교환/AS</a></p><br>
	<p style="font-weight: bolder; font-size: 20px;">쇼핑수첩</p>
	<p><a href="/null/OrderEvalListServelt">나의 상품후기</a></p><br>
	<p style="font-weight: bolder; font-size: 20px;">개인정보</p>
	<p><a href="/null/AddrListServlet">배송주소록관리</a></p>
	<p><a href="/null/Content/mypage/modifyaccountcheck.jsp">개인정보수정</a></p>
	<p><a href="/null/ModifyPasswordServlet">비밀번호변경</a></p>
</div>
	<!-- <script src="/null/Content/mypage/js/top.js"></script> -->
	<script src="/null/Content/mypage/js/toppage.js?ver=2"></script>