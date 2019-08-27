/**
 * 
 */

/*input text 변수
delivname 배송지명
delivperson 수취인
phone1 - 전화번호
phone11
phone12
phone13
phone21 - 휴대전화
phone22 
phone23 
sample4_postcode - 우편번호
sample4_roadAddress - 도로명
sample4_jibunAddress - 지번
submitbtn - 서브밋 버튼
regAddrForm - form id
userid - 회원 아이디*/

$(document).ready(function(){
	
	var addrphone1 = $("#addrphone11").val();
	var addrphone2= $("#addrphone21").val();
	$("#phone11").children().each(function(){
		if(addrphone1==$(this).val()){
			$(this).attr("selected","selected");
		}
	});
	$("#phone21").children().each(function(){
		if(addrphone2==$(this).val()){
			$(this).attr("selected","selected");
		}
	});
	
	var delivno = $("#delivno");
	var delivname = $('#delivname');
	var delivperson = $("#delivperson");
	var phone11 = $('#phone11');
	var phone12 = $('#phone12');
	var phone13 = $('#phone13');
	var phone21 = $('#phone21');
	var phone22 = $('#phone22');
	var phone23 = $('#phone23');
	var post = $('#sample4_postcode');
	var address1 = $('#sample4_roadAddress');
	var address2 = $('#sample4_jibunAddress');
	var userid = $("#userid");
	//정규식
	//한글영문숫자만 가능 
	var regDelivname = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|\*]{2,10}$/;
	var regDelivperson  = /^[가-힣]{2,4}$/;
	var regNumber = /^\d{3,4}$/;
	
	$("#submitbtn").on("click",function(event){
		
		
		if(delivname.val().length==0){
			alert("배송지명 입력!!!");
			delivname.focus();
			return false;
		}
		if(delivperson.val().length==0){
			alert("수령인 입력!!!");
			delivperson.focus();
			return false;
		}
		if(!phone12.val().length==0){
			if(phone13.val().length==0){
				alert("전화번호 입력!!!");
				phone13.focus();
				return false;
			}
		}
		if(!phone13.val().length==0){
			if(phone12.val().length==0){
				alert("전화번호 입력!!!");
				phone12.focus();
				return false;
			}
		}
		if(phone22.val().length==0){
			alert("휴대폰 입력!!!");
			phone22.focus();
			return false;
		}
		if(phone23.val().length==0){
			alert("휴대폰 입력!!!");
			phone23.focus();
			return false;
		}
		if(post.val().length==0){
			alert("우편번호 검색!!!");
			delivperson.focus();
			return false;
		}
		
		if(!regDelivname.test(delivname.val())){
			alert("2~10의 한글,영문, 숫자만 가능합니다.");
			delivname.focus();
			return false;
		}
		if(!regDelivperson.test(delivperson.val())){
			alert("2~4의 한글만 가능 합니다.");
			delivperson.focus();
			return false;
		}
		
		if(phone12.val().length!=0&&!regNumber.test(phone12.val())){
			alert("3~4의 숫자만 가능 합니다.");
			phone12.focus();
			return false;
		}
		
		if(phone13.val().length!=0&&!regNumber.test(phone13.val())){
			alert("3~4의 숫자만 가능 합니다.");
			phone13.focus();
			return false;
		}
		if(!regNumber.test(phone22.val())){
			alert("3~4의 숫자만 가능 합니다.");
			phone22.focus();
			return false;
		}
		
		if(!regNumber.test(phone23.val())){
			alert("3~4의 숫자만 가능 합니다.");
			phone23.focus();
			return false;
		}
		console.log(1);
		console.log(phone12.val());
		if(!phone12.val().length==0){
			if(!regNumber.test(phone12.val())){
				alert("3~4의 숫자만 가능 합니다.");
				phone12.focus();
				return false;
			}
		}
		
		if(phone13.val()){
			if(!regNumber.test(phone13.val())){
				alert("3~4의 숫자만 가능 합니다.");
				phone13.focus();
				return false;
			}
		}
		var phone1="";
		if(phone11.val().length+phone12.val().length+phone13.val().length){
			phone1 = phone11.val()+"-"+phone12.val()+"-"+phone13.val();
		}
		
		
		var phone2 = phone21.val()+"-"+phone22.val()+"-"+phone23.val();
		var queryString = "/null/ModifyAddrConfirmSerlvet?delivno="+delivno.val()+"&delivname="+delivname.val()+"&delivperson="+delivperson.val()+"&phone1="+phone1+"&phone2="+phone2;
		queryString+="&post="+post.val()+"&address1="+address1.val()+"&address2="+address2.val()+"&userid="+userid.val();
		console.log(queryString);
		$(location).attr("href",queryString);
	});
});