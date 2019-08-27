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
 

$().ready(()=>{
	var initial = 1000+(screen.availWidth-1000)/2+screen.availWidth*0.02;
	$("#floating_bar").css({"left":initial+"px"});
	
	//초기포지션 설정
	var indent = 30;
	var block = 210-indent;
	if($(".block").length!=0)block+=toNum($(".block").css("height"));
	$("#floating_bar").css({"top":block+indent});
	
	
	//플로팅바 포지션 설정하기
	$(window).scroll(function(){
		if($(this).scrollTop()>=block){
			$("#floating_bar").css({"position":"fixed", "top":indent});
		}else{
			$("#floating_bar").css({"position":"absolute", "top":block+indent});
		}
	})
	
	//바디 이미지 설정
	$("#floating_bar>#body img").on("click",function(){
		var address = $(this).siblings("span").text();
		location.href="/null/ProductServlet?pCode="+address;
	})
	
	
	
	
	
	
	var scroll_count = 0;
	function toUpperYard(){
		var curr_top = $(window).scrollTop();
		var speed = 30;
		if(curr_top<600){
			var breaking = 0.85;
			scroll_count++;
			speed -= breaking * scroll_count;
			if(speed<0)speed = 0.5;
		}
		
		curr_top -= speed;
		if(curr_top>0){
			scroll(0,curr_top);
			setTimeout(toUpperYard, 0);
		}
	}
	
	
	//tail 설정
	$("#floating_bar>#tail").on("click",function(){toUpperYard();scroll_count = 0;})
	
	
})