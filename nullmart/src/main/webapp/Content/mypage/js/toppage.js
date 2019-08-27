$(document).ready(function(){
	$("#gomyinfo").on("click",function(){
		$(location).attr("href","/null/Content/mypage/modifyaccountcheck.jsp");
	});
	$("#goaddrinfo").on("click",function(){
		$(location).attr("href","/null/AddrListServlet");
	});
	
	$("#goorderlist").on("click",function(){
		$(location).attr("href","/null/OrderInfoServlet");
	});
	$("#goordereval").on("click",function(){
		$(location).attr("href","/null/OrderEvalListServelt");
	});
});


