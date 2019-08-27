<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지</title>
<link rel="stylesheet" type="text/css" href="/null/Content/admin/css/inputStock.css?ver=1">
</head>
<script src="/null/Content/api/jquery/jquery-3.4.1.js"></script>
<body>
<div><jsp:include page="top.jsp"/></div>
<form action="/null/InputStockServlet" method="post">
<div id="body">
	<div id="body_left"><jsp:include page="left.jsp"/></div>
	<div id="body_content">
		<div>
			<span>상품 정보 입력</span>
		</div>
		<table>
			<tr height="100px;" style="border-bottom: 1px solid red;">
				<td width="200px;" style="margin-left: 20px;"><div class="content_title">상품명</div></td>
				<td>
					<div class="content_input">
						<input type="text" size="20" name="pname" id="pname">
					</div>
				</td>
				<td width="200px;"><div class="content_title">상품코드</div></td>
				<td>
					<div class="content_input">
						<input type="text" size="10" name="pcode" id="pcode">
					</div>
				</td>
				<td colspan="2">
					<div class="content_input">
						<input type="button" value="확인" id="checkpcode" style="margin-right: 20px;">
						<p style="font-size: 12px;">상품명과 상품코드는 확인 하나만 입력 후  검색을 통해 자동 입력 됩니다.입력 후 저장 버튼이 활 성화 됩니다.</p>
					</div>
				</td>
			</tr >
			<tr height="100px;">
				<td>
					<div class="content_title">상품사이즈</div>
				</td>
				<td>
					<div class="content_input">
						<select id="psize" name="psize" style="width: 70px;">
						</select>
					</div>
					
				</td>
				<td><div class="content_title">상품색상</div></td>
				<td>
					<div class="content_input">
						<select id="pcolor" name="pcolor" >
						<option>WHITE</option>
						<option>RED</option>
						<option>BLUE</option>
						<option>GREEN</option>
						<option>BLACK</option>
					</select>
					</div>
				</td>
				<td><div class="content_title">수량</div></td>
				<td>
					<div class="content_input">
						<input type="text" name="pamount" id="pamount" size="10">
					</div>
				</td>
			</tr >
			<tr height="100px;">
				<td ><div class="content_title">상품가격</div></td>
				<td>
					<div class="content_input">
						<input type="text" id="pprice" name="pprice">
					</div>
				</td>
				<td><div class="content_title">배송비: </div></td>
				<td colspan="3" align="center" >
					<div class="content_input" style="justify-content: justify-content: space-between;">
						<input type="radio" name="deliverfee_yn" value="Y" checked="checked">배송비 포함 
						<input type="radio" name="deliverfee_yn" value="N" style="margin-left: 20px;"> 배송비 미포함
					</div>
				</td>
				
			</tr>
			<tr height="100px;">
				<td colspan="6" align="center">
					<div><input type="submit" value="저장" id="submitbtn"></div>
				</td>
				
			</tr>
		</table>

	</div>
	
</div>
</form>
<script src="/null/Content/admin/js/inputStock.js?ver=1"></script>
</body>
</html>