<%@page import="com.dto.ProductDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	List<ProductDTO> pList = (List<ProductDTO>)request.getAttribute("pList");
%>
<c:if test="${source!='menu'}">
	<div class="body result">
		<div id="result">
			<span class="result word">"${searchedWord}"</span><span id="string1">검색 결과</span><br>
			 <span class="result quantity">${(empty items_size)?0:items_size}</span><span	id="string2">개 상품</span><br>
			 ${prev_stack}
		</div>
	</div>
</c:if>




