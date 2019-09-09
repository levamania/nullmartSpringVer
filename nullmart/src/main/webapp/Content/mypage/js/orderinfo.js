/**
 * 
 */

//dateValue button css 속성 부여 

$(document).ready(function() {
	var searchDate = $('#searchDate');
	var selectDays = $("#selectDays");
	//컨트롤러 부터 넘오온 날짜 값이 있을 경우  searchDate에 설정 
	if(selectDays){
		searchDate.val(selectDays.val());
	}
	var date1 =$('#date1');
	var date2 =$('#date2');
	var date1Value=$("#date1Value");
	var date2Value=$("#date2Value");
	var createDate = function(day){
		var d = new Date();
		var oneMonth = 30*24*60*60*1000;
		var twoWeek = 15*24*60*60*1000;
		var threeMonth = 3*oneMonth;
		if (day =='15일'){
			d = new Date(d.getTime()-twoWeek);
		}else if (day == '1개월'){
			d = new Date(d.getTime()-oneMonth);
		}else if (day ==  '3개월'){
			d = new Date(d.getTime()-threeMonth);
		}
		var str = ""+d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
		return str;
	};
	
	var dateBtns = $('button').filter('.dateValue');
	dateBtns.each(function(idx,button){
		$(button).on("click",function(){
			dateBtns.each(function(idx,button) {
				if($(button).prop("style")){
					$(this).removeAttr("style");
				}
			});
			date1.val("");
			date2.val("");
			$(this).css("background-color","red");
			searchDate.val($(this).text());
		});
	});
	
	$("#date1").on("change",function(){
		dateBtns.each(function(idx,button) {
			if($(button).prop("style")){
				$(this).removeAttr("style");
				if(searchDate.val().length>0){
					searchDate.val("");
				}
			}
		});
	});
	$("#date2").on("change",function(){
		dateBtns.each(function(idx,button) {
			if($(button).prop("style")){
				$(this).removeAttr("style");
				if(searchDate.val().length>0){
					searchDate.val("");
				}
			}
		});
	});
	
	$("#searchBtn").on("click",function(){
		//page 설정 초기화 - 조회 버튼 설정 시 
		$("#cur").val("1");
		$("#startCur").val("1");
		$("#endCur").val("0");
		$("form").submit();
		if(searchDate.val()){
			console.log($("#searchDate").val());
			$("form").submit();
		}else if(date1.val()+date2.val()){
			if(date1.val()==0){
				alert("첫번째 날짜를 입력하세요.");
				date1.focus();
			}else if(date2.val()==0){
				alert("두번째 날짜를 입력하세요.");
				date2.focus();
			}else if(date1.val()>date2.val()){
				alert("두번째 범위 날짜는 첫번재 범위 날짜보다 낮을 수 없습니다.")
			}else{
				searchDate.val(date1.val()+":"+date2.val());
				$("form").submit();
			}
		}else{
			alert("날짜 입력이 필요합니다.!!!");
		}
	});
});

//page 처리
$(document).ready(function(){
	var cur = $("#cur");
	var startCur = $("#startCur");
	var endCur= $("#endCur");
	var atags = $("#group_a>a");
	var prevGroup = $("#prevGroup");
	var nextGroup = $("#nextGroup");
	var groupindecator = $("#groupindecator");
	
	/*
	 * 그룹 설정 이벤트 
	 * */
	
	prevGroup.on("click",function(event){
		event.preventDefault();
		groupindecator.val("1");
		$("form").submit();
	});
	
	nextGroup.on("click",function(){
		event.preventDefault();
		groupindecator.val("2");
		$("form").submit();
	});
	
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


$(document).ready(function(){
	var selectDays = $("#selectDays");
	$(".dateValue").each(function(idx,button){
		if($(this).text()==selectDays.val()){
			$(this).css("background-color","red");
		}
	});
});

//화면 스크롤
$(document).ready(function(){
	var top_box = $("#top_box");
	var group_a = $("#group_a");
	var horizentalBar = $("#horizentalBar");
	if(group_a.length==1){
		var topValue = top_box.offset().top;
		$('html,body').animate({scrollTop : topValue},400);
	}else{
		$('html,body').animate({scrollTop : horizentalBar.offset().top},400);
	}
});

/*
 * 판매 페이지로 이동
 * ajax로 scode로 pcode 확인 
 * /null/product/UI?pCode의 파라미터 전송 
 *
 * */
$(document).ready(function(){
	var scodes= $(".scode");
	var pcode="";
	scodes.each(function(idx,scode){
		$(this).on("click",function(event){
			event.preventDefault();
			$.ajax({
				type:"post",
				url:"/null/mypage/searchPcode",
				data: {scode:$(this).text()},
				dataType:"text",
				success:function(data,status,xhr){
					if(data!="0"){
						window.open("/null/product/UI?pCode="+data,"_blank");
					}else{
						alert("이미 단종된 상품입니다.");
					}
					
				},
				error:function(xhr,status,error){
					console.log(status);
					console.log(error);
				}
			});
		});
	});
});



