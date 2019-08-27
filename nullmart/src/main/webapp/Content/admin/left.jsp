<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<style type="text/css">
	#left_top{
		display: inline-block;
		width: 150px;
	}
	summary{
		font-size: 20px;
		font-weight: bold;
	}
	.left_content{
		font-size: 18px;
		font-weight: bold;
		padding-left: 40px;
	}
	.left_content>a{
		text-decoration: none;
	}
</style>


<script src="/null/Content/admin/js/left.js"></script>
<div id="left_top">
	<details>
		<summary>
			상품관리
		</summary>
			<div class="left_content"><a href="" id="searchproduct">상품검색</a></div>
			<div class="left_content"><a href="inputProduct.jsp" id="inputProduct">상품등록</a></div>
			<div class="left_content"><a href="" id="addstock">재고등록</a></div>
	</details>	
</div>
