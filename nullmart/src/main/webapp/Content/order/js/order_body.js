function toWon(price){
		if(typeof price=="number"){
			price = price.toString();
		}
	
		var arr = new Array();
		for(var i=0;i<price.length;i++){
			arr.push(price.charAt(i));
		}
		for(var i=1;price.length-i*3>0;i++){
			arr.splice(price.length-i*3,1,","+arr[price.length-i*3]);
		}
		var string ="";
		for(var i of arr){
			string+=i;
		}	
		return string+" 원"; 
	}

//숫자변환 함수
function toNum(price){
	var regEx = /\d{1,100}/g;
	var x = price.match(regEx);
	var string = "";
	for(var i of x){
		string+=i;
	}
	return Number.parseInt(string);
}


//빈공간 채우는 함수
function fill_blank(){
	var html = `<div class='content' style='height:100px;'>
							<div>담겨진 상품이 없습니다!</div>
					  </div>`;
	if($("#cart_list").find(".name").length==0){
		$("#cart_list").append(html).append($(".last"));
	}
}


$().ready(()=>{

	//자동 스크롤 함수
	var distance = 0;
  		$(".top1, .top2").each(function(){
  			distance += toNum($(this).css("height"));
  		})
	var position = 0;
	function scroller(){	
		if (position < distance){
	    	position+=10;
	    	scroll(0,position);
	    	clearTimeout(timer);
	    	var timer = setTimeout(scroller,0); timer;
	    }
	 }
	scroller();
	
	
	//css 설정
	function gap_sizing(){
		$(".group .head").parents(".group").css({"margin-top":"5px"});		
	}
	gap_sizing();
		//원화설정
	$(".num").each(function(){
		$(this).text(toWon($(this).text()));
	})
	fill_blank();
	
		//상품이름 설정
	$(".content").each(function(){
		$(this).find(".pname").text($(this).find(".name").eq(0).text());
	})
	
	
	
	
	//
	
	
	
})//end ready

