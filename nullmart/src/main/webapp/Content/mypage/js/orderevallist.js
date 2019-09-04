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
				var scode = $(this).parents("div.body_contentblock").children(":nth-child(5)").val();
				$(location).attr("href","/null/Content/mypage/ordereval.jsp?ono="+ono+"&scode="+scode);
			});
			
		}else{
			$(this).val("수정");
			$(this).css("background-color","gray");
			$(this).on("click",function(){
				var ono = $(this).parents("div.body_contentblock").children(":nth-child(3)").val();
				$(location).attr("href","/null/mypage/modifyOrderEval?ono="+ono);
			});
		}
	});
});