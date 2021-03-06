/**
 * 
 */

//스크롤 함수 
function response_scroll(){
	var bodyContent = $("#body_content").offset();
	var offsetValue =  bodyContent.top;
	$('html, body').animate({scrollTop : offsetValue}, 400);
	
}
//ajax에 요청에 따른 callback method
function response_callback(){
	//화면 스크롤 
	response_scroll();
	$.ajax({
		type:"post",
		url:"/null/admin/searchProduct",
		data:{pcode:$("#pcode").val()},
		dataType: "json",
		success:function(data,status,xhr){
			imageSet(data);

		},
		error:function(xhr,status,error){
			console.log(status);
			console.log(error);
		}
		
	});

};

function imageSet(product){
	//src="/null/Content/img/shoes/tennis/sneakers/SNE1.jpg
	var response_styletop = product.STYLETOP;
	var response_stylemid = product.STYLEMID;
	var response_stylebot = product.STYLEBOT;
	
	var response_pimage = product.PIMAGE;
	var response_pname = product.PNAME;
	var response_pcode = product.PCODE;
	
	var image_pname = $("#image_pname");
	var image_pcode = $("#image_pcode");
	var image_src = $("#image_src");
	var image_top = $("#image_top");
	var image_mid = $("#image_mid");
	var image_bot = $("#image_bot");
	
	var src ="/null/Content/img/shoes/"+response_stylemid+"/"+response_stylebot+"/"+response_pimage;
	var init_explain = $("#init_explain");
	var pImage = $("#pImage");
	pImage.attr("src",src);
	image_pname.text(response_pname);
	image_pcode.text(response_pcode);
	image_top.text(response_styletop);
	image_mid.text(response_stylemid);
	image_bot.text(response_stylebot);
	
	image_src.attr("src",src);
	init_explain.hide();
	pImage.show();
}
function response_init(){
	var init_explain = $("#init_explain");
	var pImage = $("#pImage");
	init_explain.show();
	pImage.hide();
	$('html, body').animate({scrollTop : 0}, 400);
}
$(document).ready(function() {
	
	$("#pname").on("keyup",function(){
		if(event.keyCode==13){
			$("#checkpcode").trigger("click");
		}
	})
	$("#pprice").on("keyup",function(){
		if(event.keyCode==13){
			document.sus.submit();	
		}
	})
	
	//검색 이미지 초기화
	response_init();
	
	var pname = $("#pname");
	var pcode = $("#pcode");
	var pamount = $("#pamount");
	var pprice = $("#pprice");
	var pcolor=$("#pcolor")
	var psize=$("#psize");
	var deliverfee_yn=$(".deliverfee_yn");
	
	var checkpcode = $("#checkpcode");
	var submitBtn = $("#submitbtn");
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
			response_init();
			submitBtn.prop("disabled",true);
		}
	});
	pname.on("keyup",function(){
		if(!submitBtn.prop("disabled")){
			response_init();
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
			url: "/null/admin/checkpcode",
			type: "get",
			dataType: "text",
			data:{pname:pname.val(),pcode:pcode.val()},
			success: function(data,status,xhr){
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
						response_callback();
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
						response_callback();
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
//	var pname = $("#pname");
//	var pcode = $("#pcode");
//	var pamount = $("#pamount");
//	var pprice = $("#pprice");
//	var pcolor = $("pcolor");
//	var psize = $("#psize");
//	var pcolor;
//	var psize;
//	var deliverfee_yn;
	submitBtn.on("click",function(){
		
		if(checkReg(pamount, pcodeReg, "개수 입력 형식을 확인하세요")){
			return false;
		}
		if(checkReg(pprice, ppriceReg, "가격 입력 형식을 확인하세요")){
			return false;
		}
		$.ajax({
			url:"/null/admin/inputStock",
			type:"post",
			dataType:"text",
			data:{pname:pname.val(),pcode:pcode.val(),pamount:pamount.val(),
				pprice:pprice.val(),pcolor:pcolor.val(),psize:psize.val(),
				deliverfee_yn:deliverfee_yn.val()},
			success:function(data,stauts,xhr){
				if(data==0){
					alert("저장 실패!!");
				}else{
					alert("저장 성공!!");
					$("form").submit();
				}
			},
			error:function(xhr,status,error){
				console.log(status);
				console.log(error);
			}
		});
	});
	//submit event
	
	$("form").on("submit",function(){
		
		
	});
	
});
//layer pop
$(document).ready(function(){
	var pImage = $("#pImage");
	pImage.on("mouseover",function(){
		$(this).attr("width","100px");
		$(this).attr("height","100px");
	});
	pImage.on("mouseout",function(){
		$(this).attr("width","40px");
		$(this).attr("height","40px");
	});
	$('.btn-example').click(function(){
        var $href = $(this).attr('href');
        layer_popup($href);
    });
    function layer_popup(el){

        var $el = $(el);        //레이어의 id를 $el 변수에 저장
        var isDim = $el.prev().hasClass('dimBg');   //dimmed 레이어를 감지하기 위한 boolean 변수

        isDim ? $('.dim-layer').fadeIn() : $el.fadeIn();

        var $elWidth = ~~($el.outerWidth()),
            $elHeight = ~~($el.outerHeight()),
            docWidth = $(document).width(),
            docHeight = $(document).height();

        // 화면의 중앙에 레이어를 띄운다.
        if ($elHeight < docHeight || $elWidth < docWidth) {
            $el.css({
                marginTop: -$elHeight /2,
                marginLeft: -$elWidth/2
            })
        } else {
            $el.css({top: 0, left: 0});
        }

        $el.find('a.btn-layerClose').click(function(){
            isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
            return false;
        });

        $('.layer .dimBg').click(function(){
            $('.dim-layer').fadeOut();
            return false;
        });

    }
});

// 가격 - 통화 처리 
$(document).ready(function(){
	var pprice = $("#pprice");
	var tempPrice = $("#tempPrice");
	tempPrice.on("keyup",function(){
		  var temp = $(this).val().replace(/[^0-9]/g, '');
		  $(this).val(temp.replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));
		  pprice.val($(this).val().replace(",",""));
	});
	
});