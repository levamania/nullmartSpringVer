//top

// top - menu
	$().ready(function(){
		$("#login").on("click",()=>location.href="/null/Content/account/loginForm.jsp");
		$("#logout").on("click",()=>location.href="/null/LogoutServlet");
		$("#signup").on("click",()=>location.href="/null/Content/account/signup_first.jsp");
		$("#mypage").on("click",()=>location.href="/null/OrderInfoServlet");
		$("#cart").on("click",()=>location.href="/null/CartServlet");
		$("#order").on("click",()=>location.href="/null/CartServlet");
	});

// top - search & logo
	$().ready(function(){
		// logo event -> main page
		$("#search>#logo").on("click",()=>{
										location.href = "/null/MainServlet"
									  });

		function inspector(){
			  var searchedWord = $("#search input").val().trim();
			  var regEx = /^\S{2,15}/;
			  if(regEx.test(searchedWord)){
				  location.href = "/null/ProductListingServlet?source=input&searchedWord="+searchedWord;
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
				url:"/null/RankingInfoServlet",
				dataType:"text",
				success:function(data,status,xhr){
					ranking_list = data.split(":");
					$("#search>#searched>span").text(ranking_list[0]);
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
			ranking_trigger();
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
																			"<a href='/null/ProductListingServlet?searchedWord="+ranking_list[idx]+"' style='font-size:12px;color:gray;text-decoration:none'>"+
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
						      					 	$(this).children("div").toggleClass("active")
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
											var href = "/null/ProductListingServlet?source=menu&searchedWord=" + $(this).text();  
											$(this).attr("href",href);
										})
});