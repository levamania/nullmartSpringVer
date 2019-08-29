<%@page import="com.dto.ProductDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${source!='menu'}">
	<div class="body result">
		<div id="result">
			<div><span class="result word">"${searchedWord}"</span><span id="string1">검색 결과</span></div>
			<div><span class="result quantity">${(empty items_size)?0:items_size}</span><span	id="string2">개 상품</span></div>
		</div>
	</div>
</c:if>




