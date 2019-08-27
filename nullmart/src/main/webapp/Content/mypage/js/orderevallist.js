/**
 * 
 */
$(document).ready(function(){
	$(".evalbtns").each(function(idx,btn){
		if($(this).next().val()=='없음'){
			$(this).val("등록");
			$(this).css("background-color","red");
			$(this).on("click",function(){
				var ono = $(this).parents("div.body_contentblock").children(":nth-child(3)").val();
				var ordername = $(this).parents("div.body_contentblock").children(":nth-child(5)").val();
				$(location).attr("href","/null/Content/mypage/ordereval.jsp?ono="+ono+"&ordername="+ordername);
			});
			
		}else{
			$(this).val("수정");
			$(this).css("background-color","gray");
			$(this).on("click",function(){
				var ono = $(this).parents("div.body_contentblock").children(":nth-child(3)").val();
				$(location).attr("href","/null/ModifyOrderEvalServlet?ono="+ono);
			});
		}
	});
});