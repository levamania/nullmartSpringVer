$().ready(()=>{
	//왜 이터레이팅으로 이벤트 설정이 안되는가?
	$(".header>.bo").on("click",()=>location.href="#board_content");
	$(".header>.ev").on("click",()=>location.href="#eval_content");
	$(".header>.in").on("click",()=>location.href="#info_content");


	//문자열 컨버팅
	$(".wrap>div:first-child").each(function(){
		if($(this).text().length>20){
			$(this).text($(this).text().substring(0,10)+"...");			
		}
	})
	
	$(".eval").each(function(){
		var value = Number.parseInt($(this).text());
		var yellow_star = `<img class="star" src="/null/Content/img/product/yello_star.png">`;
		var gray_star = `<img class="star" src="/null/Content/img/product/gray_star.png">`;
		
		$(this).text("");
		for(var i=0;i<5;i++){
			if(i<value){
				$(this).append(yellow_star);				
			}else{
				$(this).append(gray_star);
			}
		}
	})
	
	
	

	//상품 후기 이벤트
	var paging_quan = toNum($("#paging").attr("data-page")); 
	$(".wrap>div:first-child").on("click",function(){
		$(this).parent().next().toggleClass("leo");
	})

	//페이징 이벤트 부여
	
	function addPaging(){	
	
		$("#paging>.no").off("click");
		$("#paging>.no").filter((idx,ele)=>{
			var result = true;
			if($(ele).hasClass("dead"))result = false;
			return result;
		}).on("click",function(){
			var contents = $(".wrap");
			var num = Number.parseInt($(this).text())-1;
			contents.each(function(){
				if(!$(this).hasClass("leo"))$(this).addClass("leo");
			})
			contents.slice(num*paging_quan, num*paging_quan+paging_quan).removeClass("leo");
			$(this).siblings().removeClass("dead");
			$(this).addClass("dead");
			
			//organ leo toggle
			$(".organ").removeClass("leo");
			addPaging();
		})
		
	}
	addPaging();
	$("#paging>div:contains('1')").trigger("click");
	
	
	//페이징 화살표 처리
	var unit = 5;
	var max_length = $("#paging>.no").length;
	var count = 0;
	
		//visible 설정
	$($("#paging>.no").slice(unit,$("#paging>.no").length)).css("display","none");
	
	
		//페이징 애로우 버튼 활성화
	function addArrow(){
		var visible = $("#paging>.no").filter((idx,ele)=>{
			var bool = false;
			if($(ele).css("display")=="flex")bool= true;
			return bool;
		});
		
		if($(visible[0]).text()!="1" && visible[0]!=undefined){
			$("#left").css("display","flex");
		}else{
			$("#left").css("display","none");			
		}
		
		if($(visible[visible.length-1]).text()!=max_length){
			$("#right").css("display","flex");
		}else{
			$("#right").css("display","none");			
		}
	}
	addArrow();
	
		//애로우 버튼 이벤트 설정
	$(".arrow").on("click",function(){
		var noes = $(".no");
		var visible = $("#paging>.no").filter((idx,ele)=>{if($(ele).attr("display","flex"))return true});
		
		if($(this).attr("id")=="right"){
			count++;			
		}else{
			count--;
		}
		
		visible.css("display", "none");
		noes.slice(count*unit, count*unit+unit).css("display","flex");
		
		addArrow();
	});
	
})