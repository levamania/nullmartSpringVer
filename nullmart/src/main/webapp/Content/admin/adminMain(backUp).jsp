<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>관리자페이지</title>
<style type="text/css">

*{
	box-sizing: border-box;
}


#container{
	display: flex;
	flex-direction: row;
	align-items: stretch;
}

nav{
	display: inline-flex;
	flex-basis: 100px;
	flex-grow: 1;
	flex-shrink: 0;
}

section{
	display:flex;
	flex-direction: column;
	flex-grow: 12;
	border-left: 1px solid lightgray;
}
hr{
        background-color: white;
        border-color : lightgray;
        border-style :ridge;
        height:1px;
      }
</style>
<header>
	<jsp:include page="top.jsp"/>
</header> 
</head>
<script src="/null/Content/api/jquery/jquery-3.4.1.js"></script>

<body>
<header>
	<jsp:include page="top.jsp"/>
</header>
<form action="/null/SearchStockServlet" method="get">
<div id="container">
	<nav>
		<jsp:include page="left.jsp"/>
	</nav>
	<section>
	<div align="center" style="background-color: white">
		<br>
		<br>
		<b style="font-size: 30px">관리자님 환영합니다</b>
		<br>
		<br>
		<br>
		<hr>
		<br>
		<br>

		</div>
	</section>
</div>



</form>

</body>
</html>