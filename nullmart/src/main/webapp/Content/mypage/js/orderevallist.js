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

$(document).ready(function(){
	var scodes= $(".scode");
	var pcode="";
	scodes.each(function(idx,scode){
		$(this).on("click",function(event){
			event.preventDefault();
			$.ajax({
				type:"post",
				url:"/null/mypage/searchPcode",
				data: {scode:$(this).text()},
				dataType:"text",
				success:function(data,status,xhr){
					if(data!="0"){
						window.open("/null/product/UI?pCode="+data,"_blank");
					}else{
						alert("이미 단종된 상품입니다.");
					}
					
				},
				error:function(xhr,status,error){
					console.log(status);
					console.log(error);
				}
			});
		});
	});
});