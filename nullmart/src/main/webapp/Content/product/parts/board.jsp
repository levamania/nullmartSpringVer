<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="/null/Content/product/css/board.css">
<script src="/null/Content/product/js/board.js"></script>
<script src="/null/Content/api/ckeditor/ckeditor.js"></script>

<div class="body" id="board">
	<div id="board_content">
		<div class="header">
			<div data-target="board" class="bo active">상품 정보</div>
			<div data-target="eval" class="ev">상품 후기</div>
			<div data-target="info" class="in">배송/AS 안내</div>
		</div>
		<div class="pool">

		</div>
	</div>

	<div id="eval_content">
		<div class="header">
			<div data-target="board" class="bo">상품 정보</div>
			<div data-target="eval" class="ev active">상품 후기</div>
			<div data-target="info" class="in">배송/AS 안내</div>
		</div>
		<div class="pool"></div>
	</div>

	<script type="text/javascript">
		
	</script>

	<div id="info_content">
		<div class="header">
			<div data-target="board" class="bo">상품 정보</div>
			<div data-target="eval" class="ev">상품 후기</div>
			<div data-target="info" class="in active">배송/AS 안내</div>
		</div>
		<div class="pool"></div>
	</div>

</div>
