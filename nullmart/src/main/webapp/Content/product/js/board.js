$().ready(()=>{
	//왜 이터레이팅으로 이벤트 설정이 안되는가?
	$(".header>.bo").on("click",()=>location.href="#board_content");
	$(".header>.ev").on("click",()=>location.href="#eval_content");
	$(".header>.in").on("click",()=>location.href="#info_content");


	//문자열 컨버팅


	//상품 후기 이벤트
	$(".content>div:first-child").on("click",function(){
		$(this).parent().next().toggleClass("leo");
	})

	$("#paging>div").on("click",function(){
		
	})
})