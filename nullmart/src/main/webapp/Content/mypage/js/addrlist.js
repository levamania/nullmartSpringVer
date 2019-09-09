/**
 * modifybtns: 수정버튼 클래스 
 */
$(document).ready(function(){
	var delivno=0;
	$(".modifybtns").each(function(idx,btn){
		$(this).on("click",function(){
			delivno=$(this).prev().val();
			$(location).attr("href","/null/mypage/modifyAddr?delivno="+delivno);
		});
	});
	
	
});

$(document).ready(function(){
	var checks = $(".checks");
	$("#allcheck").on("click",function(){
		checks.each(function(idx,check){
			$(this).prop("checked",true);
		});
	});
	$("#delete").on("click",function(){
		var queryString = "/null/mypage/deleteAddr?delivnos=";
		checks.each(function(idx,check){
			if($(this).prop("checked")){
				queryString +=$(this).val()+"-";
			}
		});
		$(location).attr("href",queryString);
	});
});

$(document).ready(function(){
	$("#goaddradd").on("click",function(){
		$(location).attr("href","/null/Content/mypage/regaddr.jsp");
	});
});

//화면 스크롤
$(document).ready(function(){
	var addrListSize = $("#addrListSize").val();
	var horizentalBar = $("#horizentalBar");
	var head_title = $("#head_title");
	if(addrListSize==0){
		$('html,body').animate({scrollTop : horizentalBar.offset().top},400);
	}else{
		$('html,body').animate({scrollTop : head_title.offset().top},400);
	}
	
});