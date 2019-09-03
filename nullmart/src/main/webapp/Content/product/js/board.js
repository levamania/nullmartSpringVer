$().ready(()=>{
	//왜 이터레이팅으로 이벤트 설정이 안되는가?
	$(".header>.bo").on("click",()=>location.href="#board_content");
	$(".header>.ev").on("click",()=>location.href="#eval_content");
	$(".header>.in").on("click",()=>location.href="#info_content");


	//문자열 컨버팅


	//상품 후기 이벤트
	
	$(".wrap>div:first-child").on("click",function(){
		$(this).parent().next().toggleClass("leo");
	})

	$("#paging>.no").filter((idx,ele)=>{
		var result = true;
		if($(this).hasClass("dead"))result = false;
		return result;
	}).on("click",function(){
		var contents = $(".wrap");
		var num = Number.parseInt($(this).text())-1;
		contents.each(function(){
			if(!$(this).hasClass("leo"))$(this).addClass("leo");
		})
		contents.slice(num*6, num*6+6).removeClass("leo");
		$(this).removeClass("dead");
		$(this).addClass("dead");
		
	})
	$("#paging>div:contains('1')").trigger("click");
	
	
	//페이징 처리
	var unit = 5;
	var max_length = $("#paging>.no").length;
	var count = 0;
	
		//visible 설정
	
	
		//페이징 애로우 버튼 활성화
	function addArrow(){
		var visible = $("#paging>.no").filter((idx,ele)=>{if($(ele).attr("display","inline-flex"))return true});
		
		console.log($(visible[0]).text()=="1");
		if($(visible[0]).text()!="1")$("#left").css("display","lnline-flex");
		if($(visible[visible.length-1]).text()!=max_length)$("right").css("display","lnline-flex")
		
	}
	addArrow();
	
		//애로우 버튼 이벤트 설정
	$(".arrow").on("click",function(){
		var noes = $(".no");
		var visible = $("#paging>.no").filter((idx,ele)=>{if($(ele).attr("display","inline-flex"))return true});
		
		if($(this).attr("id")=="right"){
			count++;			
		}else{
			count--;
		}
		
		visible.css("display", "none");
		noes.slice(count*unit, count*unit+unit).css("display","inline-flex");
		
		addArrow();
	})
	
})