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

//page 처리
$(document).ready(function(){
	var cur = $("#cur");
	var startCur = $("#startCur");
	var endCur= $("#endCur");
	var atags = $("#group_a>a");
	var prevGroup = $("#prevGroup");
	var nextGroup = $("#nextGroup");
	var groupindecator = $("#groupindecator");
	
	/*
	 * 그룹 설정 이벤트 
	 * */
	
	prevGroup.on("click",function(event){
		event.preventDefault();
		groupindecator.val("1");
		$("form").submit();
	});
	
	nextGroup.on("click",function(){
		event.preventDefault();
		groupindecator.val("2");
		$("form").submit();
	});
	
	// 번호 a태그 설정
	// 현재 페이지는 a 태그 비 활성
	atags.each(function(idx,a){
		if($(a).text()==cur.val()){
			$(this).removeAttr("href");
		}else{
			// 태그 클릭시
			// click atag의 값을 cur에 할당
			$(this).on("click",function(event){
				event.preventDefault();
				cur.val($(this).text());
				$("form").submit();
			});
		}
		
	});
	
});

//화면 스크롤
$(document).ready(function(){
	var body = $("#body");
	var group_a = $("#group_a");
	var userid = $("#userid");
	if(group_a.length==1){
		var topValue = body.offset().top;
		$('html,body').animate({scrollTop : userid.offset().top},400);
	}
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