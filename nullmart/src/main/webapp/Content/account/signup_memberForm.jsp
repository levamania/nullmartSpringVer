<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberForm</title>
<script src="/null/Content/account/jquery-3.4.1.js"></script>
<jsp:include page="/Content/statics/top/top.jsp" flush="true"/>
<script src="/null/Content/api/jquery/jquery-3.4.1.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
		function check(re, what, message) {
			if (re.test(what.val())) {
				return true;
			}
			alert(message);
			what.value = "";
			what.focus();
			return false;
			console.log("작동 테스트");
		}
		//id중복체크
		$('#idcheck').on("click",function(){
			var id = $("#userid");
			$.ajax({
				type : "get",
				url : "/null/idCheck",
				data : {userid:id.val()},
				dataType : "text",
				success : function (data,status,xhr){

					if(data=="1"&&id.val("nullmart")){
						alert("ID重複です。");
						$("#userid").focus();
						return false;
					}else{
                        alert("使用可能です。");
						$("#userid").focus();
						return false;
						
					}
				},
				error:function(xhr,status,e){
					console.log("error",e);
					console.log("status",status);
				}
			});
					
			
		});
		//email 중복체크
		$('#emailcheck').on("click",function(){
			var em1 = $("#email1");
			var em2 = $("#email2");
			$.ajax({
				type : "get",
				url : "/null/emailCheck",
				data : {email1:em1.val(),email2:em2.val()},
				dataType : "text",
				success : function (data,status,xhr){
					
					if(data=="1"){
						alert("メール重複です。");
						

						$("#email1").focus();
						return false;
					}else {
						
                        alert("使用可能です。");
						$("#email1").focus();
						return false;
						
					}
				},
				error:function(xhr,status,e){
					console.log("error",e);
					console.log("status",status);
				}
			});
					
			
		});
		
		//이메일 change
		$('#emailSelect').change(function(){
   $("#emailSelect option:selected").each(function () {
		
		if($(this).val()== '1'){ //직접입력일 경우
			 $("#email2").val('');                        //값 초기화
			 $("#email2").attr("disabled",false); //활성화
		}else{ //직접입력이 아닐경우
			 $("#email2").val($(this).text());      //선택값 입력
			 $("#email2").attr("disabled",true); //비활성화
		}
   });
});

		
		
		//기본 유효성검사
		
		$("form").on("submit",function(event){
		/* event.preventDefault(); */
				var re = /^[a-zA-Z0-9]{4,12}$/ 
				var re1 = /^[a-zA-Z0-9~!;:]{4,12}$/ 
				var re2 = /^[0-9]{0,12}$/
				var re3 = /^[a-zA-Z가-힝]{0,10}$/
				var re4 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/
				
			    var re5 = /(?=.*\d{1,50})(?=.*[~`!@#$%\^&*;:()-+=]{1,50})(?=.*[a-zA-Z]{2,50}).{8,20}$/;

					
				var id = $("#userid");
				var pw = $("#passwd");
				var pw2 =$("#passwd2");
			
				if (!check(re, id, "IDは4~12字の英大文字・小文字と数字で入力してください。")) {
					return false;

				}
				
	

				if (!check(re5, pw, "パスワードは、数字、特殊文字1回以上、英文は2つ以上使用して8桁以上入力してください。")) {
					return false;
				}

				if (pw.val() != pw2.val()) {
					alert("パスワードが違います。 もう一度確認をお願いします。");
					
					pw2.focus();
					return false;
					 
				}
				var username = $("#username");

				if(username.val()==""){
					alert("名前を入力していません。")
					username.focus();
					return false;
				}
				if (!check(re3, username, "ハングルまたは英文のみ入力できます。")) {
					return false;
				}
				
				
				 var radio = $(".radio");
				if($(':radio[name="sex"]:checked').length<1){
					alert('性別を選んでください。');
					radio.focus();
					return false;
				}
				

				var email1 = $("#email1");
				var email2 = $("#email2");
				
				if (email1.val()=="") {
					alert("メールを入力してください。");
					email1.focus();
					return false;
				}
				if (email2.val()=="") {
					alert("メールを入力してください。");
					email2.focus();
					return false;
				}
				 if (!check(re4,email2, "メールの形式に合わせて作成してください。")) {
					return false;

				 }
				var po = $("#sample4_postcode");
				var ad1 = $("#sample4_roadAddress");
				var ad2 = $("#sample4_jibunAddress");
				var ad3 = $("#addr3");
				if (po.val() == "") {
					alert("郵便番号を喪失しました。");
					po.focus();
					return false;
				}
				if (ad1.val() == "") {
					alert("正確な住所を入力してください。");
					ad1.focus();
					return false;
				}
				if (ad2.val() == "") {
					alert("正確な住所を入力してください。");
					ad2.focus();
					return false;
				}
				if (ad3.val() == "") {
					alert("詳細住所を入力してください。");
					ad3.focus();
					return false;
				}
				var phone1 = $('#phone1');
				
				var phone2 = $("#phone2");
				var phone3 = $("#phone3");
				if (phone1.val()=="") {
					alert("電話番号を入力してください。");
					phone1.focus();
					return false;
				}
				if (phone2.val()=="") {
					alert("電話番号を入力してください。");
					phone2.focus();
					return false;
				}
				if (phone3.val()=="") {
					alert("電話番号を入力してください。");
					phone3.focus();
					return false;
				}
				if(phone2.val().length<3){
					alert("4座で入力してください。")
					phone2.focus();
					return false;
				}
				if(phone3.val().length<3){
					alert("4座で入力してください。")
					phone3.focus();
					return false;
				}
				
				if (!check(re2, phone2, "数字だけ入力できます。")) {
					return false;

				}
				if (!check(re2, phone3, "数字だけ入力できます。")) {
					return false;

				} 
				 var answer = $('#answer');
				 var reload = $('#reLoad');
				  if(answer.val()==""){
						alert("セキュリティ文字を入力していません。")
						answer.focus();
						return false;
					}
				  
			
			
		});
		
		
	});
	/* 
	  * Captcha Image 요청
	  * [주의] IE의 경우 CaptChaImg.jsp 호출시 매번 변하는 임의의 값(의미없는 값)을 파라미터로 전달하지 않으면
	  * '새로고침'버튼을 클릭해도 CaptChaImg.jsp가 호출되지 않는다. 즉, 이미지가 변경되지 않는 문제가 발생한다. 
	  *  그러나 크롭의 경우에는 파라미터 전달 없이도 정상 호출된다.
	  */
	 function changeCaptcha() {
	  //IE에서 '새로고침'버튼을 클릭시 CaptChaImg.jsp가 호출되지 않는 문제를 해결하기 위해 "?rand='+ Math.random()" 추가 
	  $('#catpcha').html('<img src="/null/captcha?rand='+ Math.random() + '"/>');
	 }



	 function winPlayer(objUrl) {
	  $('#audiocatpch').html(' <bgsound src="' + objUrl + '">');
	 }
	 
	 /* 
	  * Captcha Audio 요청
	  * [주의] IE의 경우 CaptChaAudio.jsp 호출시 매번 매번 변하는 임의의 값(의미없는 값)을 파라미터로 전달하지 않으면
	  * '새로고침'된 이미지의 문자열을 읽지 못하고 최초 화면 로드시 로딩된 이미지의 문자열만 읽는 문제가 발생한다. 
	  * 이 문제의 원인도 결국 매번 변하는 파라미터 없이는 CaptChaAudio.jsp가 호출되지 않기 때문이다. 
	  * 그러나 크롭의 경우에는 파라미터 전달 없이도 정상 호출된다.  
	  */
	 function audioCaptcha() {

	   var uAgent = navigator.userAgent;
	   var soundUrl = '/null/audioCaptcha';
	   if (uAgent.indexOf('Trident') > -1 || uAgent.indexOf('MSIE') > -1) {
	       //IE일 경우 호출
	       winPlayer(soundUrl+'?agent=msie&rand='+ Math.random());
	   } else if (!!document.createElement('audio').canPlayType) {
	       //Chrome일 경우 호출
	       try { new Audio(soundUrl).play(); } catch(e) { winPlayer(soundUrl); }
	   } else window.open(soundUrl, '', 'width=1,height=1');
	 }
	 
	 //화면 호출시 가장 먼저 호출되는 부분 
	 $(document).ready(function() {
	  
	  changeCaptcha(); //Captcha Image 요청
	  
	  $('#reLoad').click(function(){ changeCaptcha(); }); //'새로고침'버튼의 Click 이벤트 발생시 'changeCaptcha()'호출
	  $('#soundOn').click(function(){ audioCaptcha(); }); //'음성듣기'버튼의 Click 이벤트 발생시 'audioCaptcha()'호출
	  
	
	  
	  
	  //'확인' 버튼 클릭시
	  $('#frmSubmit').click(function(){
		  var reload = $('#reLoad');
		  var answer = $('#answer');
		  
	      if ( !answer.val() ) {
	           alert('イメージに見える数字を入力してください。');
	      } else {
	    	  $.ajax({
	               url: '/null/captchaConfirm',
	               type: 'POST',
	               dataType: 'text',
	               data: 'answer=' + answer.val(),
	               async: false,  
	               success: function(data) {
	            	   console.log(data);
	            	   if(data=="0"){
	            		   alert("入力値が一致します。");    
	            		  return false
	            	   }else if(data=="1"){            		   
	            		  
	                       alert("入力値が一致しません。 もう一度入力しなければなりません。");
	                        reload.click();
	                       answer.val(''); 
	                       return false
	                       
	            	   }
	            	   
	              }
	         });
	      }
	  });
	 });
</script>
<!-- <script>

$(document).ready(function(){
	
function validate() {
	var re = /^[a-zA-Z0-9]{4,12}$/ 
	var re1 = /^[a-zA-Z0-9~!;:]{4,12}$/ 
	var re2 = /^[0-9]{0,12}$/
	var re3 = /^[a-zA-Z가-힝]{0,10}$/
	var re4 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/
	

	var id = document.getElementById("userid");
	var pw = document.getElementById("passwd");

	if (!check(re, id, "아이디는 4~12자의 영문 대소문자와 숫자로만 입력")) {
		return false;

	}
	

	if (!check(re1, pw, "패스워드는 4~12자의 영문 대소문자, 숫자, 특수문자(~,!,;,:)로 입력")) {
		return false;
	}

	if (join.passwd.value != join.passwd2.value) {
		alert("비밀번호가 다릅니다. 다시 확인해 주세요.");
		join.passwd2.value = "";
		join.passwd2.focus();
		return false;
	}
	var username = document.getElementById("username");

	if(join.username.value==""){
		alert("이름을 입력하지 않았습니다.")
		join.username.focus();
		return false;
	}
	if (!check(re3, username, "한글 또는 영문만 입력 가능합니다.")) {
		return false;
	}
	
	var radio = document.getElementByClass("radio");
	if(join.radio.value==""){
		alert("성별을 입력하지 않았습니다.")
		join.radio.focus();
		return false;
	}
	

	var email1 = document.getElementById("email1");
	var email2 = document.getElementById("email2");
	
	if (join.email1.value=="") {
		alert("이메일을 입력해 주세요");
		join.email1.focus();
		return false;
	}
	if (join.email2.value=="") {
		alert("이메일을 입력해 주세요");
		join.email2.focus();
		return false;
	}
	 if (!check(re4,email2, "email 형식에 맞게 작성해주세요")) {
		return false;

	 }
	var po = document.getElementById("sample4_postcode");
	var ad1 = document.getElementById("sample4_roadAddress");
	var ad2 = document.getElementById("sample4_jibunAddress");
	var ad3 = document.getElementById("addr3");
	if (join.sample4_postcode.value == "") {
		alert("우편번호 상실했습니다");
		join.sample4_postcode.focus();
		return false;
	}
	if (join.sample4_roadAddress.value == "") {
		alert("정확한 주소를 입력해주세요");
		join.sample4_roadAddress.focus();
		return false;
	}
	if (join.sample4_jibunAddress.value == "") {
		alert("정확한 주소를 입력해주세요");
		join.sample4_jibunAddress.focus();
		return false;
	}
	if (join.addr3.value == "") {
		alert("상세주소를 입력하세요. ");
		join.addr3.focus();
		return false;
	}
	var phone1 = document.getElementById("phone1");
	var phone2 = document.getElementById("phone2");
	var phone3 = document.getElementById("phone3");
	if (join.phone1.value=="") {
		alert("전화번호 입력하시오");
		join.phone1.focus();
		return false;
	}
	if (join.phone2.value=="") {
		alert("전화번호 입력하시오");
		join.phone2.focus();
		return false;
	}
	if (join.phone3.value=="") {
		alert("전화번호 입력하시오");
		join.phone3.focus();
		return false;
	}
	if (!check(re2, phone1, "숫자만 입력가능합니다.")) {
		return false;

	}
	if (!check(re2, phone2, "숫자만 입력가능합니다.")) {
		return false;

	}
	if (!check(re2, phone3, "숫자만 입력가능합니다.")) {
		return false;

	}


}

function check(re, what, message) {
	if (re.test(what.value)) {
		return true;
	}
	alert(message);
	what.value = "";
	what.focus();
	//return false;
}
function check(re1, what, message) {
	if (re1.test(what.value)) {
		return true;
	}
	alert(message);
	what.value = "";
	what.focus();
	//return false;
}
function check(re2, what, message) {
	if (re2.test(what.value)) {
		return true;
	}
	alert(message);
	what.value = "";
	what.focus();
	//return false;
}
function check(re3, what, message) {
	if (re3.test(what.value)) {
		return true;
	}
	alert(message);
	what.value = "";
	what.focus();
	//return false;
}
 function check(re4, what, message) {
	if (re4.test(what.value)) {
		return true;
	}
	alert(message);
	what.value = "";
	what.focus(); 
	//return false;
 }

});
 
 </script>
 <script>
 /*
 function Check(){
    var tmp = $("#context").val().replace(/\s|　/gi, '');

    var email = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/)
    var id= RegExp(/^[a-zA-Z0-9]{4,12}$/)
    var pass= RegExp(/^[a-zA-Z0-9]{4,12}$/)
    var named= RegExp(/^[가-힣]+$/)
    var fmt = RegExp(/^\d{6}[1234]\d{6}$/)  //포멧 설정
    var buf = new Array(13);




    //아이디 공백 확인

    if($("#ID").val() == ""){
     alert("아이디 입력바람");
         $("#ID").focus();
         return false;
    }

    //아이디 유효성검사
     if(!id.test($("#ID").val())){
         alert("형식에 맞게 입력해주세요");
         $("#ID").val("");
         // idCheck.value = "";
         $("#ID").focus();
         return false;
    }
 
 
   //비밀번호 공백 확인
   if($("#pass").val() == ""){
         alert("패스워드 입력바람");
         $("#pass").focus();
         return false;
    }


    //아이디 비밀번호 같음 확인
       if($("#ID").val() == $("#pass").val()){
         alert("아이디와 비밀번호가 같습니다");
         $("#pass").val("");
         $("#pass").focus();
          return false;
    }


     //비밀번호 유효성검사
     if(!pass.test($("#pass").val())){
         alert("형식에 맞게 입력해주세요");
         $("#pass").val("");
         $("#pass").focus();
          return false;
    }


    //비밀번호 확인란 공백 확인
     if($("#passch").val() == ""){
         alert("패스워드 확인란을 입력해주세요");
         $("#passch").focus();
         return false;
    }

    //비밀번호 서로확인
     if($("#pass").val() != $("#passch").val()){
         alert("비밀번호가 상이합니다");
         $("#pass").val("");
         $("#passch").val("");
         $("#pass").focus();
         return false;
    }


    //이메일 공백 확인
     if($("#email").val() == ""){
         alert("이메일을 입력해주세요");
         $("#email").focus();
         return false;
    }


    //이메일 유효성 검사
    if(!email.test($("#email").val())){
         alert("이메일형식에 맞게 입력해주세요")
         $("#email").val("");
         $("#email").focus();
         return false;
    }


    //이름 공백 검사
    if($("#names").val() == ""){
         alert("이름을 입력해주세요");
         $("#names").focus();
         return false;
    }


    //이름 유효성 검사
    if(!named.test($("#names").val())){
         alert("이름형식에 맞게 입력해주세요")
         $("#names").val("");
         $("#names").focus();
         return false;
    }


    if(($("#jumin1").val() == "") || ($("#jumin2").val() == "")){
         alert("주민등록번호를 입력해주세요");
         $("#jumin1").focus();
         return false;
    }
    if(aaa() ==false){
         return false;
    }


} 
*/







</script> -->
<style type="text/css">
 body {
	max-width: 80%; height: 100%;
	margin: auto;
	
}
    .vi{
       /*  border-radius: 5px;
        background-color: #2e5b86;
        border-style: solid;
        border-color: #5d5d5d;
        border-width: 2px;
        color: #FFFFFF;
        margin-bottom: 5px;
        padding:15px;
        width:400px */
    }
    .align-left { text-align: left; }
    .align-center { text-align: center; }
    .align-justify { text-align: justify; }
    .margin-center {
        margin-left:auto;
        margin-right:auto;
    }
   .test_btn1{
            margin-right:-4px;
        }
        #test_btn2{
            margin-left:-3px;
        }
        #btn_group button{
            
            border: 1px solid lightgray;
            background-color: white;
            color: black;
            padding: 1px;
        }#table{
        font-size:8px;
        margin-center:auto;
        
        }
        
       body {
        
    }
    img {
       
    }
    #ver1 {vertical-align: base;}
    #ver2 {vertical-align: sub;}
    #ver3 {vertical-align: super;}
    #ver4 {vertical-align: text-top;}
    #ver5 {vertical-align: text-bottom;}
    #ver6 {vertical-align: top;}
    #ver7 {vertical-align: middle;}
    #ver8 {vertical-align: bottom;}
    #ver9 {vertical-align: 50%;}
    #ver10 {vertical-align: 10pt;}
    
    .line{border-bottom:1px solid lightgray;
          border-top:1px solid lightgray};


    





</style>

</head>
<body >
<form action="/null/SignUp" method="get" >

<div id="" class="align-center vi" style="font-size:25px">
<b> </b><br>
<b> </b><br>
<b   style="color:black">NULL-MART 온라인 회원가입</b><br>

<b> </b><br><br>
<img src="../img/account/signup(check).PNG" width="45" height="auto;" id="ver4">&nbsp;&nbsp;&nbsp;<a style="font-size:30%;" >  &nbsp;&nbsp;&nbsp;</a> 
<img src="../img/account/Signup(complite)2.PNG" width="45" height="auto;" id="ver4">&nbsp;&nbsp;&nbsp;<a style="font-size:30%;" > &nbsp;&nbsp;&nbsp;</a> 
<img src="../img/account/signup(userinfo).PNG" width="45" height="auto;" id="ver4">&nbsp;&nbsp;&nbsp;<a style="font-size:30%;" > &nbsp;&nbsp;&nbsp;</a> 
</div> <br>
<br>
<b style="font-size:80%">회원기본정보</b><div align="right"><a  style="font-size:60%;color:red" >* </a><a  style="font-size:60%">필수 입력정보</a></div>
<hr>
<br>
<table border="0" id="btn_group" style="text-align: left;align-self: left" >
<tr>
<td width="100" height="35"><a  style="font-size:60%;color:red" >* </a><a  style="font-size:60%">ID</a></td>
<td  width="200" height="35" ><input type="text" style="width:200px;height:50%;font-size:60%" id="userid" placeholder="英文、数字の使用4〜12字" name="userid"></td>
<td  width="100" height="35"><button class="test_btn1" style="width:33pt;height:11pt;font-size:56%" id="idcheck" type="button">중복확인</button></td>
</tr>
 <tr>
<td width="100" height="35"><a  style="font-size:60%;color:red" >* </a><a  style="font-size:60%">비밀번호</a></td>
<td width="200" height="35" colspan="0"><input type="password" style="width:200px;height:50%;font-size:60%" id="passwd"  placeholder="英文、数字、特殊文字使用、8〜20字" name="passwd"></td>
</tr>
<tr>
<td width="100" height="35"><a  style="font-size:60%;color:red" >* </a><a  style="font-size:60%">비밀번호확인</a></td>
<td width="200" height="35" ><input type="password" style="width:200px;height:50%" id="passwd2"></td>
</tr>
 <tr>
<td width="100" height="35"><a  style="font-size:60%;color:red" >* </a><a  style="font-size:60%">お名前</a></td>
<td width="200" height="35" colspan="0"><input type="text" style="width:200px;height:50%" id="username" name="username"></td>
</tr>
<tr>
<td width="100" height="35"><a  style="font-size:60%;color:white" >*  </a><a  style="font-size:60%">성별</a></td>
<td width="100" height="35"><a style="font-size:60%">男</a><input type="radio" id="check1"style="height: 15px" name="sex" value="XY" class="radio">
<a style="font-size:60%">女</a><input type="radio" id="check2" style="height: 15px;"name="sex" value="XX" class="radio"></td>
</tr>
 <tr>
<td width="100" height="35"><a  style="font-size:60%;color:red" >* </a><a  style="font-size:60%">이메일</a></td>
<td width="100" height="35" colspan="0"><input type="text" style="width:70px;height:50%" id="email1" name="email1"><a>@</a>
<input type="text" style="width:100px;height:50%;font-size:60%" id="email2" name="email2" placeholder="예) null.com"  value="naver.com"></td>
<td><select  id="emailSelect"><option value="naver.com" id="naver">naver.com</option>
<option value="softbank.jp" id="softbank">softbank.jp</option>
<option value="daum.net" id="daum">daum.net</option>
</select>

<button class="test_btn1" style="width:33pt;height:11pt;font-size:56%" id="emailcheck">중복확인</button>
</tr>
<tr>
<td width="100" height="35"><a  style="font-size:60%;color:red" >* </a><a  style="font-size:60%">우편번호</a></td>
<td width="200"height:50%" >
<input style="width:70px;height:50%;font-size: 70%" type="text" name="post" id="sample4_postcode" placeholder="郵便番号">
<button class="test_btn1" style="width:55pt;height:11pt;font-size:56%" id="post"  onclick="sample4_execDaumPostcode()" type="button">우편번호찾기</button></td></tr>
<tr>
<td width="100" height="35"><a  style="font-size:60%;color:red" >* </a><a  style="font-size:60%">주소</a></td>
<td width="200" height="35"><input style="width: 80%;height:50%;font-size:60%" type="text" name="addr1" id="sample4_roadAddress" placeholder="道路名住所"></td>
<td width="200" height="35"><input style="width: 80%;height:50%;font-size:60%" type="text" name="addr2" id="sample4_jibunAddress" placeholder="地番住所" >
<span id="guide" style="color:#999"></span></td></tr>
<tr>
<td width="100" height="35"></td>
<td width="200" height="35"><input style="width: 80%;height:50%;font-size:60%" type="text" name="addr3" id="sample4_jibunAddress" placeholder="詳細住所"></td>
</tr>
<tr>
<td width="100" height="35"><a  style="font-size:60%;color:red" >* </a><a  style="font-size:60%">전화번호</a></td>
<td width="200" height="35"><select name="phone1" id="phone1">
  <option style="width: 20%;height:50%;font-size:80%" value="010" id="010">010</option>
  <option style="width: 20%;height:50%;font-size:80%" value="011" id="011">011</option>
</select>-
<input style="width: 30%;height:50%;font-size:60%" type="tel" name="phone2"  id="phone2">-<input style="width: 30%;height:50%;font-size:60%" type="tel" name="phone3"id="phone3" > </td>
</tr>
</table>
<br>

<table >
<tr>
<td width="100"></td>
<td>
<div id="catpcha">Wait...</div>
  <div id="audiocatpch" style="display: none;"></div>

  <input id="reLoad" type="button" value="새로고침" class="test_btn1" style="width:45pt;height:11pt;font-size:40%;background-color: white;border: 1px solid lightgray;"/>
  <input id="soundOn" type="button" value="음성듣기" class="test_btn1" style="width:45pt;height:11pt;font-size:56%;background-color: white;border: 1px solid lightgray;"/> 
  <br />
  <input type="text" id="answer" name="answer" value="" class="test_btn1" style="width:45pt;height:11pt;font-size:56%;background-color: white;border: 1px solid lightgray;"/>
  <input type="button" id="frmSubmit" value="확인" class="test_btn1" style="width:45pt;height:11pt;font-size:56%;background-color: white;border: 1px solid lightgray;"/>
</td>
</tr>
</table>

<hr>
<br>
<div id="btn_group" class="align-center vi">
<button style="width: 110px;height: 38px;font-size: 18px;border: 1px solid red;background-color: red;color: white;font-weight:40" type="submit" id="btn">회원가입</button>
<button style="width: 110px;height: 38px;font-size: 18px;border: 1px solid red;background-color: white;color: red;font-weight:40" onclick="location.href='/null/MainServlet'">취소</button>
<br>
<br>
</div>
</form>



<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 도로명 조합형 주소 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }
                // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
                if(fullRoadAddr !== ''){
                    fullRoadAddr += extraRoadAddr;
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('sample4_roadAddress').value = fullRoadAddr;
                document.getElementById('sample4_jibunAddress').value = data.jibunAddress;

                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    //예상되는 도로명 주소에 조합형 주소를 추가한다.
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';

                } else {
                    document.getElementById('guide').innerHTML = '';
                }
            }
        }).open();
    }
</script>
</body>
</html>