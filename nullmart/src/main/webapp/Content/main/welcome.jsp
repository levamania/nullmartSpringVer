<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="/null/Content/api/jquery/jquery-3.4.1.js"></script>
<script type="text/javascript">
var address = "/null/productListing/work?source=input&refresh=true&searchedWord=공용:남성:여성";
$().ready( function(){
	console.log("ajax");
	$.ajax({
		url: address,
		method: "get",
		type:"text",
		success: function(data, status, xhr){
			console.log(status);
			setTimeout(() => {location.href = "/null/main"}, 5000);
		},
		error: function(error){
			console.log(error);
		}
	})
	
}); 
</script>
</head>

</html>