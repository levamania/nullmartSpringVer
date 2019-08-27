<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="/null/Content/account/jquery-3.4.1.js"></script>
<jsp:include page="/Content/statics/top/top.jsp" flush="true"/>
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
<form action="/null/MainServlet">

<div id="" class="align-center vi" style="font-size:25px">
<b> </b><br>
<b> </b><br>
<b   style="color:darkgray">NULL이 없는 모든 신발</b><br>
<b style="color:black">NULL-MART  회원가입  </b><b style="color:darkgray">을 환영합니다.</b><br>
<b> </b><br>

<b> </b><br>
<img src="/null/Content/img/account/signup(check).PNG" width="45" height="auto;" id="ver4">&nbsp;&nbsp;&nbsp;<a style="font-size:50%;" > 약관동의 &nbsp;&nbsp;&nbsp;</a> 
<img src="/null/Content/img/account/Signup(complite).PNG" width="45" height="auto;" id="ver4">&nbsp;&nbsp;&nbsp;<a style="font-size:50%;" > 회원정보입력&nbsp;&nbsp;&nbsp;</a> 
<img src="/null/Content/img/account/signup(userinfo)2.PNG" width="45" height="auto;" id="ver4">&nbsp;&nbsp;&nbsp;<a style="font-size:50%;" > 가입완료&nbsp;&nbsp;&nbsp;</a> 
<br>
<br>
<b>즐거운 쇼핑 되시기 바랍니다.</b><br>
<br>
<hr style="color:red">
</div> 
<br>
<br>
<br>
<div id="btn_group" class="align-center vi">
<button style="width: 120px;height: 40px;font-size: 20px;border: 1px solid red;background-color: red;color: white;font-weight:600" type="submit" >돌아가기</button>
</div>
</form>
</body>
</html>