/**
 * 
 */

//dateValue button css 속성 부여 

$(document).ready(function() {
	var searchDate = $('#searchDate');
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
			date1Value.val("");
			date2Value.val("");
			console.log(createDate($(this).text()));
			$(this).css("background-color","red");
			//searchDate.val(createDate($(this).text()));
			searchDate.val($(this).text());
		});
	});
	$(".datepicker").each(function(idx,item){
		$(item).on("change",function(){
			dateBtns.each(function(idx,button) {
				if($(button).prop("style")){
					$(this).removeAttr("style");
					if(searchDate.val().length>0){
						searchDate.val("");
					}
				}
			});
			var str = ""+searchDate.val();
			str+=$(this).val();
			searchDate.val(str);
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
		date1Value.val($(this).val());
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
		date2Value.val($(this).val());
	});
	
	$("#searchBtn").on("click",function(){
		console.log(searchDate.val());
		console.log(date1Value.val());
		if(searchDate.val()){
			$(location).attr("href","/null/mypage/orderInfo?day="+searchDate.val());
		}else if(date1Value.val()+date2Value.val()){
			if(date1Value.val()==0){
				alert("첫번째 날짜를 입력하세요.");
				date1.focus();
			}else if(date2Value.val()==0){
				alert("두번째 날짜를 입력하세요.");
				date2.focus();
			}else if(date1Value.val()>date2Value.val()){
				alert("두번째 범위 날짜는 첫번재 범위 날짜보다 낮을 수 없습니다.")
			}else{
				$(location).attr("href","/null/mypage/orderInfo?day="+date1Value.val()+":"+date2Value.val());
			}
		}else{
			alert("날짜 입력이 필요합니다.!!!");
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

