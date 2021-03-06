//top

// top - menu
	$().ready(function(){
		$("#login").on("click",()=>location.href="/null/Content/account/loginForm.jsp");
		$("#logout").on("click",()=>location.href="/null/logout");
		$("#signup").on("click",()=>location.href="/null/Content/account/signup_first.jsp");
		$("#mypage").on("click",()=>location.href="/null/mypage/orderInfo");
		$("#managerpage").on("click",()=>location.href="/null/Content/admin/adminMain.jsp");
		$("#managerlogout").on("click",()=>location.href="/null/mgrLogout");
		$("#cart").on("click",()=>location.href="/null/cart/UI");
		$("#order").on("click",()=>location.href="/null/cart/UI");
	});

// top - search & logo
	$().ready(function(){
		// logo event -> main page
		$("#search>#logo").on("click",()=>{
										location.href = "/null/main"
									  });

		function inspector(){
			  var searchedWord = $("#search input").val().trim();
			  var regEx = /^\S{2,15}/;
			  if(regEx.test(searchedWord)){
				  location.href = "/null/productListing/work?source=input&refresh=true&searchedWord="+searchedWord;
			  }else{
					  alert("2글자 이상 입력해주세요");
			  }
		}
		
		//input event -> searching
		$("#search input").on("keyup",()=>{if(event.keyCode==13)inspector()})
		// icon evnet -> searching
		$("#search #icon").on("click",()=>inspector());
		
		// searched word ranking
		var ranking = 1;
		var ranking_list = [];
		var setting = 2000;
			// ranking_info
			$.ajax({
				tyep:"post",
				url:"/null/ranking/info",
				dataType:"text",
				success:function(data,status,xhr){
					ranking_list = data.split(":");
					$("#search>#searched>span").text(1+" "+ranking_list[0]);
					$("#input>input").attr("placeholder","\""+ranking_list[Math.floor(Math.random()*ranking_list.length)]+"\"를 입력해 보세요");
					ranking_trigger();
				},
				error:function(error){
					console.log(error);
				}
			});
		// 시간에 따라 검색어 변경
			var ranking_animation;	
			function ranking_trigger(){	
			 	ranking_animation = setInterval(() => {
														$("#search>#searched>span").text((ranking+1)+" "+ranking_list[ranking]);
														ranking++;
											
														if(ranking==10||ranking==ranking_list.length)ranking = 0;
								   				}, setting);
			}
			
		//탭전환시 animation  제거
		$("html").on("mouseenter",function(){
							if(ranking_animation==null)ranking_trigger();
						})
						.on("mouseleave",function(){
							if(ranking_animation!=null){
								clearInterval(ranking_animation);
								ranking_animation=null;
							}
						})
		
		
		// 검색어 확장
		$("#search>#searched").on("mouseenter",function(event){
													event.stopPropagation();
													//배열 가공
													var string = "<div style=\""+
																		"font-weight=bold;font-size:14px;color:red;margin:0 0 10px 10px ;"+
																		"\">"+
																		"인기 검색어"+
																		"</div>"+
																		"<hr style='postion:relative;width:80%;left:10%;border:1px red solid'>";
													for(var idx in ranking_list){
														var rank = Number.parseInt(idx)+1;
														string += "<div style='margin:0 0 0 10px;'>"+
																			"<a href='/null/productListing/work?searchedWord="+ranking_list[idx]+"' style='font-size:12px;color:gray;text-decoration:none'>"+
																				rank+"  "+ranking_list[idx]+
																			"</a>" +
																		"</div>"
														if(idx+1!=ranking_list.length)string += "<br>";
													}
													$(this).children("div").toggleClass("active")
															  .css({"text-align":"left","padding":"5px"})
															  .html(string);
											 })
						      				 .on("mouseleave",function(event){
						      					 	event.stopPropagation();
						      					 	$(this).children("div").toggleClass("active");
						      				 });
											 
	});
	
// 가로바
	$().ready(function(){
		// category - popup
		$(".category").on("click",()=>{
					  	event.stopPropagation();
						$(".view_all_menu").toggleClass("active");
						$(".category").toggleClass("active");				
					  })
					  .on("mouseleave",()=>{
						if($(".view_all_menu").attr("class").includes("active")){
							$(".view_all_menu").toggleClass("active");
							$(".category").toggleClass("active");							  
						}
					  }); 
									
		// style mid - popup
		$(".style-mid_menu>.item").on("mouseenter", function() {
									$(this).toggleClass("active")
									.css({"background-image":"url('/null/Content/img/main/menu_background.png')"
										 ,"background-repeat":"no-repeat" 
										 ,"background-size": "contain"
									     ,"background-position": "center bottom"})
									.children("a").css({"color":"red"});
									})
									.on("mouseleave",function(){
										$(this).toggleClass("active").css({"background-image":"none"})
										.children("a").css({"color":"black"});
									});
		//style mid - anchor setting
		$("#horizentalBar a").each(function(){
											var href = "/null/productListing/work?source=menu&searchedWord=" + $(this).text()+"&refresh=true";  
											$(this).attr("href",href);
										});

		//이미지 다운 요청	
		$("#frog").on("click", function(){
				$.ajax({
					url : "/null/productListing/work?source=input&refresh=true&searchedWord=ê공용©:여성:남성",
					method : "get",
					type : "text",
					success : function(data, status, xhr){
						console.log("success");
					},
					error : function(error){
						alert("DB연동 실패, CONNECTION 연결 확인 요망");
					}
				});
		});
		

		
		// 비동기적 이미지 로딩 설정
		var count = 1;
		$("img.lich").each(function(){
			
			var src = $(this).attr("data-src");
			var draft = $(this);
			var img = new Image();
			img.onload=_=>draft.attr("src",src);
			img.onerror=(error)=>{
				if(count==1){
					$("#frog").trigger("click");
					count++;
				}	
				setTimeout( _=>img.src=src, 100);					
			}
			
			img.src = src;		 		
		})
		
});