<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<Script>
	//원화로 바꾸는 함수
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
	
	
	
	//가져온 데이터 가공
	var map = ${json};
	
	//색상,사이즈로 가격 얻는 함수
	function getPrice(color, size){
		var price = 0;
		for(var info of map[color]){
			if(info["PSIZE"]==size)price = info["PPRICE"];
		}
		return price;
	}
	
	//색상 클릭시 => 색상에 존재하는 색상정보, 사이즈 클릭시 => 사이즈와 색상정보를 넘겨 상응하는 레코드의 가격정보
	
	function createReposit(color,size){	
		if(color.length!=0||size.length!=0){
			var price = getPrice(color,size);
			console.log(price);
			var ele = $("#product_info>.reposit").clone();
			//콘텐츠 설정
			var code = color+"/"+size+"/"+"${product.PNAME}";
			var confirm = true;
			//새로 저장할 상품과 이전의 상품이 같은지 확인
			$("#option>.content.reposit>div:first-child").each(function(){
				if($(this).text()==code){
					alert("이미 선택한 상품입니다.");
					confirm = false;
				}
			});
			//reposit 생성
			if(confirm){			
		        ele.children("div:eq(0)").text(code)//size.text())
		           .end().children("div:eq(1)").html("<div id='plus'>+</div><input value='1' ><div id='minus'>-</div>")
  		             .end().children("div:eq(2)").text( toWon(price)+"("+toWon(price-${PPRICE[0]})+"원+)")
 		                  	  .append("<div id='for_calc'>X<div>").children().text(price).end()                    
		           .end().children("div:eq(3)").html("<div class='delete'></div>");
				$("#option").append(ele);
			}

			//reposit delete 기능 추가
			setDelete_reposit(ele);
			//reposit input 유효성 검사기능 추가
			setInput_corrector(ele);
			//reposit input couter 기능 추가
			setCounter(ele);
						
			//총합계 설정
			setTotal_price();
		}//end if
	}//end function
	

	//color 선택
	$(".color").on("click",function(){
					 	//색상 재선택시 사이즈s에 있던 이벤트 재거
					    $(".content #sizes>div").off("click");
					 	//선택한 색깔을 활성화 시켜 선택했다는 것을 보여줌
 						$(this).toggleClass("active")
 								  .siblings().removeClass("active");//다른 색상의 active제거, unique active
 						//이전에 사이즈 선택으로 활성화 되었이던 사이즈 버튼들 비활성화
 						$(".content #sizes>div").removeClass("active");
 						//색상에 맞는 사이즈 리스트  활성화
 						var color = document.querySelector(".color.active").style.backgroundColor.toUpperCase();//rgb값만이 나오기에
 						for(var info of map[color]){
 							if(info["PSIZE"]!=0){
					   	 		$(".content #sizes>div").each(function(){
					    			if($(this).text()==info["PSIZE"])$(this).toggleClass("active");
					    		})
					   		}
 						}
 						event_push();
				   })
	//최초 상품 리스트 요청시 첫번째 색상 선택됨
	$(".color:first-child").trigger("click");
	
	//사이즈 선택 이벤트 함수( 색선택시 마다 다른 사이즈에 이벤트를 부여하고 지워야 하므로 한번의 실행으로는 구현 불가, 함수로 여러번 실행시킨다.)
	function event_push(){
	//활성화 되어있는 사이즈클릭시 푸쉬클래스를 토글하고 
	//색상과 사이즈 정보를 합쳐 REPOSIT생성
	$(".content #sizes>div.active")
		.on("click",function(){
			$(this).toggleClass("pushed");//푸쉬 토글 후 리토글 필요
			var color = document.querySelector(".color.active").style.backgroundColor.toUpperCase();
			var size = Number.parseInt($(this).text());
			createReposit(color,size);			    
		});			
	}
	//reposit 삭제버튼 설정
	function setDelete_reposit(ele){
		var superior = ele;
		ele.find(".delete")
			.on("click",function(){
			  //해당 버튼의 부모=리파짓 통째로 삭제
			  superior.remove();
			  //총합 다시 구하기
			  setTotal_price();

		});
	}
	//reposit input 버튼 설정
	function setCounter(ele){
		var superior = ele;
		ele.find("#plus, #minus")
			.on("click",function(){
				var input = $(this).siblings("input");
				var curr_num = Number.parseInt(input.val());
				if($(this).text()=="-"){
					curr_num-=1;
				}else{curr_num+=1}
				
				//0이하 제거
				if(curr_num==0)curr_num=1;
				input.val(curr_num);
				//가격재설정
				setTotal_price(ele);
			})
	}
	
	//reposit input 유효성 설정
	var prev_val=1;
	function setInput_corrector(ele){
		ele.find("input")
			.on("keyup",function(){
			var text = $(this).val();
			var regEx = /\d{1,5}/.test(text)&&input>0;
			if(regEx){
				var input = Number.parseInt(text);
				prev_val=input;	
		 	}else{
		 		$(this).val(prev_val);
		 	}
		});
	}
	
	
	
	//총합계 설정
	function setTotal_price(){
		$("#total_price").each(function(){
			var total_price = 0;
			$("#product_info>#option>.content.reposit").each(function(){
				total_price += Number.parseInt($(this).find("input").val().trim()) *
									   Number.parseInt($(this).find("#for_calc").text());
			});
			$(this).text(toWon(total_price));
		})
	}

//-----------------------------------결제-----------------------------------------//	
	
	//로그인 체커
	function login_checker(fuc){
		if(${!empty login}){
			fuc();
		}else{
			alert("로그인이 필요합니다");
			location.href="/null/LoginServlet";	
		}
	}
	
	//결제버튼 설정
	$("#payment>div")
		.eq(1).on("click",function(){location.href="/null"})
		//장바구니
		.end().eq(2).on("click",function(){
				if($("#option>.content.reposit").length!=0){
						login_checker(function(){
							//무엇을 전달해야 하는가? 리파짓의 상품코드, 수량, 가격
							var info = new Array();
							var count = 1;
							$("#option>.content.reposit").each(function(){
								var PCODE = "${product.PCODE}";
								var SCODE = $(this).children("div:first-child").text().trim();
								var PAMOUNT = $(this).find("input").val().trim();
								var PPRICE = $(this).find("#for_calc").text().trim();
								
								 info.push( { "PCODE":PCODE,"SCODE":SCODE, "PAMOUNT":PAMOUNT, "PPRICE":PPRICE });
							})
							
							$.ajax({
								//전달셋팅
								method:"post",
								url:"/null/StackProductServlet",
								data: {
									list:JSON.stringify(info)
								},
							
								//수용셋팅
								type:"text",
								success:function(data){
									if(data=="success"){
										$("#product_info>.layout").css({display:"flex"});
									}else{
										var [son] = JSON.parse(data); //destructing
										let warnning = ` \n 죄송합니다. 상품이 부족합니다.
													   \n *상품: `+son["SCODE"]+`  *선택하신 수량: `+son["PICK_QUAN"]+`  *부족한 수량: `+son["DIFFER"];
										alert(warnning);
										
									}
								},
								error:function(staus,xhr,error){
									console.log(error);
								}
							});//and ajax
					
						})//end fuc
				}else{
					alert("상품을 선택해 주세요");
				}
				
		 })// end on
		//구매하기
		.end().eq(3).on("click",()=>location.href="/null/OrderServlet");
	
	//장바구니 선택時 팝업 설정
	$("#pop_up")
		//닫기
		.find("#close, #redirector>div:nth-child(1)")
			.on("click",function(){
				$(".layout").css({"display":"none"});
				$(".reposit .delete").trigger("click");			
			})
		//리다이렉트
		.end().find("#redirector>div:nth-child(2)")
				.on("click",function(){
				location.href="/null/CartServlet";
			  })
			
			  
})
</Script>