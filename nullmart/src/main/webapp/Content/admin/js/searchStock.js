/**
 * 
 */

// select 태그 초기화
function initSelector(selector,options){
	selector.append($("<option >select</option>"));
	for(var optionObj of options){
		var optionName = optionObj.option;
		var option = $("<option>"+optionName+"</option>");
		selector.append(option);
	}
}

// select 태그 삭제
function selectEmpt(selector){
	selector.empty();
	selector.append($("<option>select</option>"));
}


// css background red 값 제거
function dateValueRemoveCss(){
	$(".dateValue").each(function(idx,button){
		$(this).css("background-color","");
	});
}

// 특정 값에 맞는 selector option 설정
function selectfunc(selectors,msg){
		selectors.each(function(){
		if($(this).text()==msg){
			$(this).prop("selected",true);
		}else{
			$(this).prop("selected",false);
		}
	});
}

// 페이지 로딩시 실행 함수
// ajax로 상품이름을 가져옴
// 상품명 입력시 자동으로 키워드 검색
$(document).ready(function(){
	var keywords = [];
	var keyupflag = false;
	var pname = $("#pname");
	
	var pcode = $("#pcode");
	var styletop = $("#styletop");
	var stylemid = $("#stylemid");
	var stylebot = $("#stylebot");
	var pregitdate = $("#pregitdate");
	
	var submitbtn = $("input[type=submit]");
	
	// 정규식 객체
	var pnameReg =/^[가-힝A-Za-z0-9]{1,20}$/;
	
	// 검색 조전 유지용
	var isSearchOption = $("#isSearchOption");
	console.log(isSearchOption.val());
	function searchInitSelector(){
		$.ajax({
			type: "get",
			url: "/null/GetInitSearchStockServlet",
			dataType: "json",
			success: function(data,status,xhr){
				
					initSelector(styletop,data.styletops);
					initSelector(stylemid,data.stylemids);
					initSelector(stylebot,data.stylebots);
					for(var value of data.keywords){
						keywords.push(value.keyword);
					}
					
					$(function(){
						$("#pname").autocomplete({
							source:keywords
						});
					});
					//조회 후 페이지를 로딩한 것이면?
					if(isSearchOption.val()==1){
						 var hpcode = $("#hpcode");
						 var hpname = $("#hpname");
						 var hstyletop = $("#hstyletop");
						 var hstylemid = $("#hstylemid");
						 var hstylebot = $("#hstylebot");
						 var hsearchDate = $("#hsearchDate");
						 //상품명으로 찾는것인지?
						 if(hpcode.val().length!=0){
							 selectEmpt(styletop);
							 selectEmpt(stylemid);
							 selectEmpt(stylebot);
						 }else{
							 //style selector
							 console.log(hstylebot.val());
							 selectfunc($("#styletop>option"),hstyletop.val());
							 selectfunc($("#stylemid>option"),hstylemid.val());
							 selectfunc($("#stylebot>option"),hstylebot.val());
						 }
						 
						 $("#searchDate").val(hsearchDate.val());
						 //날짜 선택 
						 if(hsearchDate.val().length>=15){
							 //input date 설정
							 var d1 = hsearchDate.val().substring(0, 10);
							 var d2 = hsearchDate.val().substring(10);
							 $("#date1").val(d1);
							 $("#date2").val(d2);
						 }else{
							 //날짜 버튼 설정 
							 $(".dateValue").each(function(idx,btn){
								 if($(btn).text()==hsearchDate.val()){
									 $(this).css("background-color","red");
								 }else{
									 $(this).css("background-color","");
								 }
							 });
						 }
						 
					}
			},
			error: function(xhr,status,e){
				console.log("error: ",e);
				console.log("status: ",status);
			}
		});
	}
	
	searchInitSelector();
	// pnanme keyup event
	// keyup 이벤트 후 입력된 값있으면 외부 변수(keyupflag)설정
	pname.on("keyup",function(){
		if(pname.val().length!=0){
			
			keyupflag=true;
		}else{
			initInput();
		}
	});
	
	// input tag 초기화
	function initInput(){
		keywords=[];
		styletop.empty();
		stylemid.empty();
		stylebot.empty();
		pname.val("");
		pcode.val("");
		searchInitSelector();
		keyupflag=false;
	}
	// pname blur event
	// 외부 변수에 따라 ajax. 호출
	pname.on("blur",function(){
		if(keyupflag){
			
			// ajax json 응답 처리
			$.ajax({
				type: "get",
				url: "/null/GetInitSearchStockServlet",
				data: {pname:pname.val()},
				dataType: "json",
				success: function(data,status,xhr){
					if(data==0){
						alert("상품명을 찾 을 수 없습니다.");
						initInput();
					}else{
						pcode.val(data.pcode);
						selectEmpt(styletop);
						selectEmpt(stylemid);
						selectEmpt(stylebot);
					}
					
				},
				error: function(xhr,status,e){
					console.log("error: ",e);
					console.log("status: ",status);
				}
			});
		}
		
	});
	
});




// 날짜 버튼 클릭시 css 설정
// dateValue 설정
$(document).ready(function() {
	var pname = $("#pname");
	var pcode = $("#pcode");
	var styletop = $("#styletop");
	var stylemid = $("#stylemid");
	var stylebot = $("#stylebot");
	var pregitdate = $("#pregitdate");
	
	var searchDate = $("#searchDate");
	var date1 = $("#date1");
	var date2 = $("#date2");
	searchDate.val("");
	
	// 날짜 입력 버튼 클릭
	$(".dateValue").each(function(idx,button) {
		$(this).on("click",function(){
			dateValueRemoveCss();
			date1.val("");
			date2.val("");
			$(this).css("background-color","red");
			var date= $(this).text();
			searchDate.val(date);
		});
	});
	
	// 날짜 date input 태그 유효성
	date1.on("change",function(){
		dateValueRemoveCss();
		if(searchDate.val()){
			searchDate.val("");
		}
		if(date2.val()!=0){
			if(date1.val()>date2.val()){
				alert("첫번째 날짜가 두번째 날짜보다 늦을 수 없습니다.");
				$(this).val("");
				$(this).focus();
				return;
			}
			searchDate.val(date1.val()+date2.val());
		}
	});
	// 날짜 date input 태그 유효성
	date2.on("change",function(){
		dateValueRemoveCss();
		if(searchDate.val()){
			searchDate.val("");
		}
		if(date1.val()!=0){
			if(date1.val()>date2.val()){
				alert("첫번째 날짜가 두번째 날짜보다 늦을 수 없습니다.");
				$(this).val("");
				$(this).focus();
				return;
			}
			searchDate.val(date1.val()+date2.val());
		}
	});
});

// 입력 태그 value값 확인
function sumValue(...tags){
	var sum="";
	for(var tag of tags){
		sum+=tag.val();
	}
	return sum;
}

//submit button event
$(document).ready(function() {
	var submitbtn = $("#submitbtn");
	submitbtn.on("click",function(){
		$("#cur").val("1");
		$("#startCur").val("1");
		$("#endCur").val("0");
		$("form").submit();
	});
});

// submit 유효성 검사
// searchDate, pname, styletop,stylemid, sytlebot중 하나라도 없다면
$(document).ready(function(){
	var pname = $("#pname");
	var pcode = $("#pcode");
	var styletop = $("#styletop");
	var stylemid = $("#stylemid");
	var stylebot = $("#stylebot");
	var pregitdate = $("#pregitdate");
	var searchDate = $("#searchDate");
	
	// submit event 검사
	$("form").on("submit",function(){
		var outdata=sumValue(pname,styletop,stylemid,stylebot,searchDate);
		if(outdata=='selectselectselect'){
			alert("검색할 상품명, 스타일, 등록일 지정해주세요");
			return false;
		}
	});
	
});

// page 처리
$(document).ready(function(){
	var cur = $("#cur");
	var startCur = $("#startCur");
	var endCur= $("#endCur");
	var atags = $("#group_a>a");
	
	// 번호 a태그 설정
	// 현재 페이지는 a 태그 비 활성
	atags.each(function(idx,a){
		if($(a).text()==cur.val()){
			$(this).removeAttr("href");
		}else{
			// 태그 클릭시
			// click atag의 값을 cur에 할당
			$(this).on("click",function(event){
				event.preventDefault();
				cur.val($(this).text());
				$("form").submit();
			});
		}
		
	});
});


