<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="/null/Content/account/jquery-3.4.1.js"></script>
<jsp:include page="/Content/statics/top/top.jsp" flush="true"/>
<meta charset="UTF-8">
<title>first</title>
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
            border: 1px solid red;
            background-color: rgba(0,0,0,0);
            color: red;
            padding: 5px;
        }#table{
        font-size:13px;
        margin-center:auto;
        
        }
        
       



</style>

</head>
<body>

<div id="" class="align-center vi" style="font-size:40px">
<b> </b><br>
<b> </b><br>
<b   style="color:darkgray">NULL이 없는 모든 신발</b><br>
<b style="color:red">NULL-MART  회원가입  </b><b style="color:darkgray">을 환영합니다.</b><br>
<b> </b><br>
<hr style="color:red">
</div> <a></a>
<div class="align-center vi">
</div><br>
<b style="font-size:80%;" >NULL-MART 회원 및 절차</b><br>
<table  align="center" class="align-center vi" border=0 cellSpacing=1 cellPadding=0 bgColor="darkgray" style="color:black"  >

<tr>
<th colspan="2" bgColor=#ffffff width=1400 height=50><img src="../img/account/signup(addmember).PNG" width="30" height="auto;" ><b> 온라인 회원</b></th>
</tr>
<tr>
<td bgColor=#ffffff width=400 height=70 id="table" >회원가입 절차</td>
<td bgColor=#ffffff width=1000 height=70> &nbsp;<img src="../img/account/signup(check).PNG"style="max-width: 20px; height: auto;" > &nbsp;<a style="color:#696969">약관동의  &nbsp;</a><a style="color:#ffbaba">-></a>
<img src="../img/account/signup(userinfo).PNG" style="max-width: 20px; height: auto;" > &nbsp;<a style="color:#696969">회원정보 입력 &nbsp;</a><a style="color:#ffbaba">-></a>
<img src="../img/account/Signup(complite).PNG" style="max-width: 20px; height: auto;" > &nbsp;<a style="color:#696969">회원가입 완료  &nbsp;</a>
</td>
</tr>
<tr>
<td bgColor=#ffffff width=400 height=210 id="table" >혜택</td>
<td bgColor=#ffffff width=1000 height=210 background="../img/account/signup(background).png" style="color:red;" id="table" ><b>있을것 같은데 없음</b></td>

</tr>
</table>
<a> </a><br>
<br>
<br>
<br>

<div id="btn_group" class="align-center vi">
<button id="test_btn1" onclick="location.href='signup_second.jsp'"><b>온라인 회원가입</button>
<br>
<br>
</div>

</body>
</html>