
$().ready(()=>{
	//주문자 변경
		//기존 유저정보
	var user_info = {
								"name": $("#customer #user").val(),
								"phone1":(function(){
													var phone1;
													$("#customer select[name='user_phone1']").children().each(function(){
															if($(this).prop("selected")){
															phone1 = $(this).text();
													}})
													return phone1;		
												})(),	
								"phone2": $("#customer input[name='user_phone2']").val(),
								"phone3": $("#customer input[name='user_phone3']").val(),
								"email1": $("#customer input[name='user_email1']").val(),
								"email2": $("#customer input[name='user_email2']").val()
		
							}
		//attr설정
	$("#customer input[type='text']").prop("readonly",true);	
	$("#customer select").prop("disabled",true);	
	
	$("#customer input[type='checkbox']").on("click",function(){
		var inputes = $("#customer input[type='text']");
		var select = $("#customer select");
		if($(this).prop("checked")){
			inputes.prop("readonly",false);	
			select.prop("disabled",false);	
		}else{
			$("#customer #user").val(user_info["name"]);
			$("#customer select[name='user_phone1']").children(":contains('"+user_info["phone1"]+"')").prop("selected", true);
			$("#customer input[name='user_phone2']").val(user_info["phone2"]);
			$("#customer input[name='user_phone3']").val(user_info["phone3"]);
			$("#customer input[name='user_email1']").val(user_info["email1"]);
			$("#customer input[name='user_email2']").val(user_info["email2"]);
			inputes.prop("readonly",true);	
			select.prop("disabled",true);	
		}
	})
	
	//고객 정보 카피
	var order_name = $("#address input[name='order_name']");
	var order_phone1= $("#address select[name='order_phone1']"); 
	var order_phone2= $("#address input[name='order_phone2']"); 
	var order_phone3= $("#address input[name='order_phone3']"); 

	$("#same_radio").on("click",function(){
		var user_phone1 = user_info["phone1"];
		
		order_name.val($("#customer #user").val());
		order_phone1.children(":contains('"+user_phone1+"')").prop("selected", true);
		order_phone2.val($("#customer input[name='user_phone2']").val());
		order_phone3.val($("#customer input[name='user_phone3']").val());
	});
	//배송정보 초기화
	$("#new_radio").on("click",function(){
		order_name.val("");
		order_phone1.children(":contains('"+"010"+"')").prop("selected", true);
		order_phone2.val("");
		order_phone3.val("");
	});
	
	//내 주소록 설정
	$("#book").on("click",function(){
		window.open("http://localhost:8090/null/order/book","", "width=700px, height=400px");
	})

	//Fetching opener return 
	$(window).on("message",function(){
		var record = event.data;
		if(event.origin=="http://localhost:8090"){
			$("#address input, #address select").each(function(){
				var name = $(this).attr("name");
				var me = $(this);
				$.each(record, function(key,value){
					if(name==key){
						me.val(value);
					}
					
				})
			})	

		}
	})
	
	
	/*************유효성 검사***************/
	
	function check(ele,regex,mesg){
		if(!regex.test(ele.val())){
			ele.focus();
			throw mesg;
		}
	}
	
	var namingEx = /[가-힣]{3,10}/;
	var phoningEx = /\d{4}/;
	
	var telelingEx1 = /\d{3}/;
	var telelingEx2 = /\d{4}/;
	
	var emailingEx1 = /^[a-z][a-z0-9]{4,15}$/;
	var emailingEx2 = /^[a-z][a-z]{4,10}[.](com|co.kr|org|net)$/;
	
	var messagingEx = /([a-zA-z가-힣]|[* - . \\ /]){0,40}/;
	
	
	
	//결제
	$("#decision").on("click",function(){
		var form = document.form1;
		form.action = "/null/order/pay";

		var cnos = "";
		$("input[name='cno']").each(function(){cnos += $(this).val()+","})
		cnos = cnos.substr(0, cnos.length-1);
		$("#cnos").val(cnos);

		try{
			//주문고객
			check($("#customer #user"), namingEx, "고객 이름을 확인하세요");
			check($("#customer input[name='user_phone2']"), phoningEx, "휴대폰번호를 확인하세요");
			check($("#customer input[name='user_phone3']"), phoningEx, "휴대폰번호를 확인하세요");
			check($("#customer input[name='user_email1']"), emailingEx1, "이메일을 확인하세요");
			check($("#customer input[name='user_email2']"), emailingEx2, "이메일을 확인하세요");
			
			//수령고객
			check($("#address input[name='order_name']"), namingEx, "수령인 이름을 확인하세요");
			check($("#address input[name='order_phone2']"), phoningEx, "휴대폰번호를 확하세요");
			check($("#address input[name='order_phone3']"), phoningEx, "휴대폰번호를 확하세요");
			check($("#address input[name='order_telephone2']"), telelingEx1, "전화번호를 확하세요");
			check($("#address input[name='order_telephone3']"), telelingEx2, "전화번호를 확하세요");
	
			check($("#address input[name='order_postcode']"), /\d{4,5}/, "우편번호를 선택해주세요");	
			check($("#address input[name='order_mesg']"), messagingEx, "배송 메세지를 확인해주세요, 40자 이내입니다.");
			
			//diabled 해제
			var limiter = $("#customer input[type='checkbox']"); 
			if(!limiter.prop("checked"))limiter.trigger("click");
			
			form.submit();
		}catch (error) {
			alert(error);
		}
			
	})
	
	
	//
	
	
	
	
	
})

