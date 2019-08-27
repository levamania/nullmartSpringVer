<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="top menu">
	<c:if test="${empty login}">
		<span class="static_menu" id="login">로그인</span>
		<span class="static_menu" id="signup" >회원가입</span>
	</c:if>
	<c:if test="${!empty login}">
		<span class="static_menu" id="logout">로그아웃</span>
		<span class="static_menu" id="mypage">마이페이지</span> 
	</c:if>
	<span class="static_menu" id="cart">장바구니</span> <span
		class="static_menu" id="order">주문배송</span>
</div>