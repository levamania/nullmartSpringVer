<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="/null/Content/api/jquery/jquery-3.4.1.js"></script>
<script type="text/javascript">
var address = "/null/productListing/work?source=input&refresh=true&searchedWord=ê공용©:여성:남성";
$().ready( function(){
	$.ajax({
		url : address,
		method : "get",
		type : "text",
		success : function(data, status, xhr){
			setTimeout(() => {location.href = "/null/main"}, 5000);
		},
		error : function(error){
			console.log(error);
		}
	})
	
}); 
</script>
</head>
<body>
	이미지 다운로드 중...
</body>
</html>