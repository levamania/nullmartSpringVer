/**
 * 
 */
$(document).ready(function() {
	var pname = $("#pname");
	var pcode = $("#pcode");
	var pamount = $("#pamount");
	var pprice = $("#pprice");
	var checkpcode = $("#checkpcode");
	var submitBtn = $("input[type=submit]");
	submitBtn.prop("disabled",true);
	
	var ppriceReg  = /^\d{1,12}$/;//숫자 1~12
	var pnameReg = /^[가-힝A-Za-z0-9]{1,20}$/;//특수문자x
	var pcodeReg = /^[0-9A-Za-z]{1,10}$/;//영어 숫자만
	
	var sizes = [];
	for(var i=200;i<=300;i+=5){
		sizes.push(i);
	}
	$('#psize').empty();
	 
	for(var i = 0; i < sizes.length; i++){                
		var option = $("<option>"+sizes[i]+"</option>");
	    $('#psize').append(option);
	 }
	function checkReg(tag, reg,mesg){
		if(!reg.test(tag.val())){
			if(tag.val().length==0){
				alert("필수 입력입니다.")
			}else{
				alert(mesg);
				tag.val("");
			}
			tag.focus();
			return true;
		}else{
			return false;
		}
	}
	
	//pcode, pname keyup 이벤트시
	pcode.on("keyup",function(){
		if(!submitBtn.prop("disabled")){
			submitBtn.prop("disabled",true);
		}
	});
	pname.on("keyup",function(){
		if(!submitBtn.prop("disabled")){
			submitBtn.prop("disabled",true);
		}
	});
	//check버튼으로 pname, pcode 찾기만 일치 
	checkpcode.on("click",function(){
		if(!(pname.val()+pcode.val())){
			alert("pname, pcode를 입력하세요");
			return;
		}else if(pname.val().length==0){
			if(checkReg(pcode,pcodeReg,"pcode 입력 확인")){
				return false;
			}
		}else if(pcode.val().length==0){
			if(checkReg(pname,pnameReg,"pname 입력 확인")){
				return false;
			}
			
		}else {
			if(checkReg(pcode,pcodeReg,"pcode 입력 확인")){
				return false;
			}
			if(checkReg(pname,pnameReg,"pname 입력 확인")){
				return false;
			}
			
		}
		$.ajax({
			type: "get",
			url: "/null/InputStockServlet",
			data:{pname:pname.val(),pcode:pcode.val()},
			dataType: "text",
			success: function(data,status,xhr){
				console.log(data);
				if(data=="pnamenone"){
					alert("일치하는 상품명이 없습니다.");
					pname.focus();
					return;
				}else if(data=="pcodenone"){
					alert("일치하는 상품코드가 없습니다.");
					pcode.focus();
					return;
				}else if(data=="bothnosearch"){
					alert("상품명과 상품코드 모두 일치하지 않습니다.")
					pname.focus();
					return;
				}else if(data=="pnamenosearch"){
					alert("일치하는 상품명이 없습니다.");
					pname.focus();
					return;
				}else if(data=="pcodenosearch"){
					alert("일치하는 상품코드가 없습니다.");
					pcode.focus();
					return;
				}else if(data=="eachnotequal"){
					alert("입력한 상품명 상품코드가 서로 일치 하지 않습니다.");
					return;
				}else if(data=="both"){
					if(submitBtn.prop("disabled")){
						submitBtn.prop("disabled",false);
						return;
					}
					
				}else{
					if(pname.val().length==0){
						pname.val(data);
					}else if(pcode.val().length==0){
						pcode.val(data);
					}
					
					if(submitBtn.prop("disabled")){
						submitBtn.prop("disabled",false);
						return;
					}
				}
			},
			error: function(xhr,status,e){
				console.log("error: ",e);
				console.log("status: ",status);
			}
			
		});
	});
	
	//submit event
	
	$("form").on("submit",function(){
		if(checkReg(pamount, pcodeReg, "개수 입력 형식을 확인하세요")){
			return false;
		}
		if(checkReg(pprice, ppriceReg, "가격 입력 형식을 확인하세요")){
			return false;
		}
		
	});
	
});