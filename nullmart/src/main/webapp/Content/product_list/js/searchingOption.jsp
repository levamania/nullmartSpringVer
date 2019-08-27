<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
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
		return string; 
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

//리파짓 설정하기
var reposit ={ "HELPER": [] }; //아무값이 없으면 JSON파싱불가 in JACKSON

$().ready(()=>{
function push_atom(ele){
	//객체에 저장
	var category = $(ele).parents(".category_option").attr("id").toUpperCase();
	if($(ele).parent(".deeper").length!=0)category = "STYLEBOT";
	var text = $(ele).text();
	
	//리스트가 없을 경우
	if(reposit[category]==undefined){
		reposit[category] = Array.of();
	}
	//사이즈 셀렉트
	if(category=="PSIZE"&&reposit[category].length==2){
		//배열 정렬
		reposit[category].sort();
		
		var small_one = Number.parseInt(reposit[category].shift());
		var large_one = Number.parseInt(reposit[category].pop());
		var size = Number.parseInt(text);
		
		//사이즈가 크거나 작을 경우
		if(size<small_one){
			//배열 채우기
			reposit[category].unshift(size);
			reposit[category].push(large_one);
			//small one 해제
			$("#psize>.value>div:contains('"+small_one.toString()+"')").trigger("click");						
		}else if(size>large_one){
			reposit[category].unshift(small_one);
			reposit[category].push(size);
			//large one 해제
			$("#psize>.value>div:contains('"+large_one.toString()+"')").trigger("click");
		}else{ //바깥이 아닌 사이가 선택되었을 경우
			//최소에서 ele까지 카운트
			var s_count = 0;
			var e_count = 0;
			
			var less = $("#psize>.value>div:contains('"+small_one.toString()+"')");
			var more = $("#psize>.value>div:contains('"+large_one.toString()+"')");
			
			less.nextAll().each(function(){
				if($(this).text()==size)return false;
				s_count++;
			})
			more.prevAll().each(function(){
				if($(this).text()==size)return false;
				e_count++;
			})
			// 간격구별
			if(s_count>e_count){
				reposit[category].unshift(less.text());
				reposit[category].push(size);
				more.trigger("click");
			}else if(s_count<=e_count){
				reposit[category].unshift(size);
				reposit[category].push(more.text());
				less.trigger("click");
			}
		
		}//end size-argo
		
	}else{
		//인풋값 정제
		if($(ele).siblings("input").length!=0){ //형제중에 인풋태그가 있을때
			var arr = text.match(/\d{1,10}/g);
			var str = "";
			for(var i of arr){
				str += i;
			}
			reposit[category].push(str);
			
		}else{
			reposit[category].push(text);
			reposit[category].sort();
		}
	}

	//화면구현
	var html = "<div class='optical'>"+
							"<div>"+text+"</div><span style='display:none;'>"+category+"</span>"+
							"<div></div>"+
				"</div>";
	$("#collection>.value").append(html);
	
	// 정렬
	var order_list = ["STYLETOP","STYLEMID","STYLEBOT","PSIZE","PCOLOR"];
	var position = 0;
	for(var order of order_list){
		if(reposit[order]!=undefined){
			for(var i=0;i<reposit[order].length;i++){
				while(true){
					var lox = $("#collection>.value");
					var focus = lox.children().eq(position);
					if(focus.children(":first-child").text()==reposit[order][i]
						&& focus.children("span").text()==order){
						position++;break;
					}
					lox.append(focus);
				}
				
			}
		}
	}
	
	
	//min_price , max_price 정렬
	if(reposit['MAX_PRICE']!=undefined && reposit['MIN_PRICE']!=undefined){
		var container = $("#collection>.value");
		var min = $("#collection>.value").find("span:contains('MIN_PRICE')").parents(".optical");
		var max = $("#collection>.value").find("span:contains('MAX_PRICE')").parents(".optical");
	
		while(true){
			if(min.next()[0]==max[0])break;
			container.prepend(min.next());
			container.append(min.prev());
		}
		
		
	}
	
	//삭제 기능 추가
	add_delete();
}



//리파짓 atom 삭제 추가 기능
function add_delete(){
	$("#collection .optical").on("click",function(){
		//객체에서 제거
		var id = $(this).find("span").text();
		var text = $(this).find("div:first-child").text();
		if(id.includes("PRICE")){
			var num = "";
			for(var o of text.match(/\d{1,10}/g)){
				num += o;
			}
			text = num;
		}
		var count =0;
		if(reposit[id]!=undefined){			
			for(var atom of  reposit[id]){
				if(atom==text)break;
				count++;
			}
			reposit[id].splice(count,1);
			//속성 삭제
			if(reposit[id].length==0){
				delete reposit[id];
			}	
		}
		
		//푸쉬 해제
		var temp = "";
		if(id=="STYLEBOT"){
			temp =".DEEPER";			
		}else{
			temp = "#"+id;
		}
		var x = $(temp.toLowerCase()).find(".button:contains('"+text+"')")
				.add(temp.toLowerCase());
		x.removeClass("pushed");
 		
		x.find("input").val("");
 		x.find("span").text("");
		
		//자기삭제
 		$(this).remove();
	})
}


	//css 설정
		//선택 설정
function buttonSet(){
	$(".button").off("click");	//클릭을 중복 해주면 큰일이 생긴다!
	$(".button").on("click",function(){
		var id = $(this).parents(".category_option").attr("id");
		if($(this).parent(".deeper").length!=0)id="stylebot";
		if(id!="collection"){
			//리파짓에 설정 저장하기
			if($(this).hasClass("pushed")){
				var text = $(this).text();
				//id 와 문자열이 같은 optical 클릭
				$("#collection .optical>span:contains('"+id.toUpperCase()+"')")
				.siblings(":contains('"+text+"')").parent(".optical").trigger("click");

			}else{
				//의미없는 값 방지: 공백 div 입력 방지 
				if($(this).text().trim().length!=0){
					//&& category확장시 스타일입력 방지
					if($("#stylemid .deeper").css("display")!="none" 
							&& $(this).parents("#stylemid").length!=0){
						if($(this).parent(".deeper").length!=0){

							push_atom(this);		
							$(this).toggleClass("pushed");							
						}	
					}else{
						push_atom(this);		
						$(this).toggleClass("pushed");						
					}
				}
			}
		}
	});
}//end fuc
buttonSet();

	//가격 입력 설정
	$(".price input").on("keyup",function(){
		//원화로 변경
		if($(this.length!=0)){
			//숫자검열
			if(!/\d{1,10}/g.test($(this).val())){
				$(this).val("");
			}else{
				$(this).val(toWon(toNum($(this).val())));							
			}
		}
		
	})
		//집중시 내부값 지워지고 옵티컬 해제
		.on("focus",function(){
			//초기화
			var id = $(this).parents(".category_option").attr("id");
			var text = $(this).next("span").text();
			//id 와 문자열이 같은 optical 클릭
			$("#collection .optical>span:contains('"+id.toUpperCase()+"')")
			.siblings(":contains('"+text+"')").parent(".optical").trigger("click");
			
		})
	
		//최소가격
		.filter("[id='min']").on("blur",function(){
			var ele = $(this).next();
			//내부 스팬에 가격 설정
			$(this).next().text($(this).val()+"원~");

			//의미없는 값 입력 방지
			if($(this).val().length!=0){
				//optical 생성
				push_atom(ele);					
			}
			
		})
			.on("keyup",function(){
				//수치 제한 => 최대값이 있을시 최대값보다 크게 설정 불가능
				var max =  reposit["MAX_PRICE"];
				if(max!=undefined){
					if(toNum($(this).val())>max[0]){
						$(this).val(toWon(max[0]));
					}
					
				}
			
			})
		//최대가격 
		.end().filter("[id='max']").on("blur",function(){
			var ele = $(this).next();
			//내부 스팬에 가격 설정
			$(this).next().text("~"+$(this).val()+"원");
			//optical 생성
			if($(this).val().length!=0){
				push_atom(ele);	
			}
		})
			.on("keyup",function(){
				//최대값 제한
				var max_value = 500000;
				if(toNum($(this).val())>max_value){
					$(this).val(toWon(max_value));
				}
				
				var min =  reposit["MIN_PRICE"];
				if(min!=undefined){
					if(toNum($(this).val())<min[0]){
						$(this).val(toWon(min[0]));
					}
				}
			})

		//카테고리 확장 버튼 설정
	var prev_clicked = null;
	var sol = Array.of(${BINDING});
	var binding = null;
	if(sol.length!=0){	
		[binding] = sol;
	}
	$("#stylemid .value>div:nth-child(2n)").on("click",function(){
		//css
		var style = $("#stylemid .value>div");
		var deeper = $("#stylemid .deeper"); 
		var partner = $(this).prev();
		
		if(deeper.css("display")=="none"){
			prev_clicked = this;
			style.css({"margin-top":"10px"});
			deeper.css({"display":"flex"});
			//형제 선택됨 제거
			if(partner.hasClass("overed")){
				partner.trigger("click");
			}
			//기존 디퍼 삭제
			deeper.children("div").remove();
			
			//consisting deeper
			
				$.each(binding,function(key, value){
					if(key==partner.text()){
						for(var o of value){
							deeper.append("<div class='button'>"+o["STYLEBOT"]+"</div>");
						}
					}
				})
				//이벤트 부여
 				buttonSet();
			
		
		}else{
			if(prev_clicked!=this){
				prev_clicked = this;
				style.css({"margin-top":"10px"});
				deeper.css({"display":"none"});
				$(this).click();
				$(this).siblings().removeClass("overed");
				partner.toggleClass("overed");
			}else{
				style.css({"margin-top":"0"});
				deeper.css({"display":"none"});
			}
		}
		
	})
		.on("mouseover mouseout",function(){
			if($("#stylemid .deeper").css("display")=="none"){
				$(this).prev().toggleClass("overed");
			}
		});
	
	//전체해제 설정
	$("#collection #purge").on("click",function(){
		$("#collection .optical").trigger("click");
	})
	
	//상세검색 버튼 설정
	$("#find").on("click",function(){
		var form = document.specific;
		var input = specific.querySelector("[name='selected_atoms']");
		//값 설정
		input.value = JSON.stringify(reposit);
		form.action = "/null/SpecificFilter";
		form.submit();
	})
	
	
	//페이지 시작시 리스팅 셋업에 따라 클릭
	var [clicked] = Array.of(${clicked});
	if(clicked!=undefined){
	$(".category_option").add(".deeper").each(function(){
		var head = $(this).attr("id").toUpperCase();
		var list = clicked[head];
		if(list!=null){
			if(head=="MIN_PRICE" || head=="MAX_PRICE"){
				$(this).find("input").val(list[0]);
				$(this).find("input").trigger("keyup").trigger("blur");
			}else if(head=="STYLEBOT"){
				$.each(binding, function(key,value){		
					for(var atom of list){
						for(var name of value){
							
						if(atom==name["STYLEBOT"]){	
							var top = $("#stylemid").find(".button:contains('"+key+"')");
							if(!top.hasClass("overed"))	top.next().trigger("mouseover").trigger("click");
							top.end().find(".deeper>div:contains('"+name["STYLEBOT"]+"')").trigger("click");
						}
						
						}
					}
				})//end fuc
			}else{
				for(var atom of list){				
					$(this).find(".button").each(function(){
						if($(this).text()==atom){
							$(this).trigger("click");
						}	
					})		
			}
					
			}//for
		}//list 
	})
	}//end if


	 	
	  	//자동 스크롤 함수
	  	var distance =0;
	  	var temp =null;
	  		$(".body").each(function(){
	  			var height = toNum($(this).css("height"));
	  			var margin = toNum($(this).css("margin-top"))+toNum($(this).css("margin-bottom"));
	  			distance += height+margin;
	  		});	
	  	distance -= toNum($(".bottom").css("height")); 	
	  	distance -= toNum($(".searched_product").css("height")); 	
	  	
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
	
})
</script>