<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script src="/null/Content/account/jquery-3.4.1.js"></script>
<jsp:include page="/Content/statics/top/top.jsp" flush="true"/>
<script src="jquery-3.4.1.js"></script>
<script type="text/javascript">
$('document').ready(function(){
	console.log(1);
	 $('#test_btn1').on("click",function(){
	if(!$('#info1').prop("checked")){
		alert("구매이용약관에 동의해주세요.");
		
	}else if(!$('#info2').prop("checked")){
		alert("전자금융거래 이용약관에 동의해주세요.");
	}else if(!$('#radio1yes').prop("checked")){
		alert("NULL-MART 개인정보 수집 및 이용에 대한 안내에 동의해주세요.");
	}else if(!$('#radio2yes').prop("checked")){
		alert("개인정보 수집 및 이용에 대한 안내에 동의해주세요.");
	}else if($('#checkall').prop("checked")){
		
		$(location).attr("href","signup_memberForm.jsp");	
	}
	});
	

	 var g = $(".group");
	 $("#checkall").click(function(){
		 

		 if($("#checkall").prop("checked")) {
			 $("input[class=group]").prop("checked",true);

			
	 } else {
		 $("input[class=group]").prop("checked",false);

	 }
		
	 });
		 
		 
	 
	
});
</script>
<head>
<meta charset="UTF-8">
<title>second</title>
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
   #test_btn1{
            margin-right:-4px;
        }
        #test_btn2{
            margin-left:-3px;
        }
        #btn_group button{
            border: 0px solid white;
            background-color: red;
            color: white;
            padding: 5px;
        }#table{
        font-size:13px;
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

<div id="" class="align-center vi" style="font-size:25px">
<b> </b><br>
<b> </b><br>
<b   style="color:black">NULL-MART 온라인 회원가입</b><br>

<b> </b><br>
<img src="../img/account/signup(check)2.PNG" width="50" height="auto;" id="ver4">&nbsp;&nbsp;&nbsp;<a style="font-size:50%;" > 약관동의 &nbsp;&nbsp;&nbsp;</a> 
<img src="../img/account/Signup(complite).PNG" width="45" height="auto;" id="ver4">&nbsp;&nbsp;&nbsp;<a style="font-size:50%;" > 회원정보입력&nbsp;&nbsp;&nbsp;</a> 
<img src="../img/account/signup(userinfo).PNG" width="45" height="auto;" id="ver4">&nbsp;&nbsp;&nbsp;<a style="font-size:50%;" > 가입완료&nbsp;&nbsp;&nbsp;</a> 
<hr style="color:red">
</div> 
<input type="checkbox" name="info1" id="info1" class="group"><a style="font-size: 50%;color:gray">NULL-MART 구매이용약관</a><a style="font-size: 50%;color:lightgray">(필수)</a>
<button style="border: 1px solid lightgray;background-color: rgba(0,0,0,0);color: black;padding: 1px;font-size:50%"  onclick="window.open('signup_buyinfo.jsp')" >내용보기></button>
<a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
<input type="checkbox" name="info2" id="info2" class="group"><a style="font-size: 50%;color:gray">NULL-MART 전자금융거래 이용약관</a><a style="font-size: 50%;color:lightgray">(필수)</a>
<button style="border: 1px solid lightgray;background-color: rgba(0,0,0,0);color: black;padding: 1px;font-size:50%"  onclick="window.open('signup_payinfo.jsp')" >내용보기></button><br>
<br>
<br>
<div >
<b style="font-size:10px">NULL-MART 개인정보 수집 및 이용에 대한 안내</b><br>
</div>
<div style="font-size:9px">
<a>개인정보 수집 및 이용에 대한 안내</a><a style="color:lightgray">(필수)</a>&nbsp;&nbsp;<input type="radio" name="radio1"id="radio1yes"class="group">동의함&nbsp;<input type="radio" name="radio1" id="radio1no">동의안함&nbsp;<br>
</div>
<br>
<table  align="center"  border=0 cellSpacing=0 cellPadding=0 bgColor="darkgray" style="color:gray;font-size:65%"  >
<tr  border="0">
<td width="500" height="50" bgColor=#ffffff   class="line">목적</td>
<td width="500" height="50" bgColor=#ffffff class="line">항목</td>
<td width="500" height="50" bgColor=#ffffff class="line">보유기간</td>
</tr>
<tr>
<td width="500" height="80" bgColor=#ffffff class="line">회원가입</td>
<td width="500" height="80" bgColor=#ffffff class="line">아이디, 비밀번호, 성명, 생년월일, 휴대폰번호, 이메일</td>
<td width="500" height="80" bgColor=#ffffff class="line">회원탈퇴 시 까지</td>
</tr>
</table><br>
<br>
<div style="font-size:9px">
<a>개인정보 수집 및 이용에 대한 안내</a><a style="color:lightgray">(필수)</a>&nbsp;&nbsp;<input type="radio" id="radio2yes" class="group">동의함&nbsp;<input type="radio" id="radio2no">동의안함&nbsp;<br>
</div>
<br>
<table  align="center"  border=0 cellSpacing=0 cellPadding=0 bgColor="darkgray" style="color:gray;font-size:65%"  >
<tr  border="0">
<td width="500" height="50" bgColor=#ffffff   class="line">목적</td>
<td width="500" height="50" bgColor=#ffffff class="line">항목</td>
<td width="500" height="50" bgColor=#ffffff class="line">보유기간</td>
</tr>
<tr>
<td width="500" height="80" bgColor=#ffffff class="line">정보 안내 및 마케팅 활동 등</td>
<td width="500" height="80" bgColor=#ffffff class="line">정보수신여부(SMS, 이메일)</td>
<td width="500" height="80" bgColor=#ffffff class="line">회원탈퇴 시 까지</td>
</tr>
</table><br>
<br>
<div class="align-center vi" style="font-size:80%">
<input type="checkbox" id="checkall"><a >NULL-MART 이용을 위한 구매이용약관 및 전자금융거래 이용약관, 개인정보 처리방침(필수)에 대해</a> <a style="color:red">모두 동의</a>  <a>합니다.</a>
</div>
<div id="btn_group" class="align-center vi">
<br>
<button id="test_btn1" >회원가입</button>
    <!-- onclick="location.href='signup_memberForm.jsp'" -->
    <br>
    <br>
    
</div>
</body>
</html>