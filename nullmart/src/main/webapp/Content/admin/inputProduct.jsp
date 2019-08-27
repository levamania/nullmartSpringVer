<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.ckeditor.com/ckeditor5/12.3.1/classic/ckeditor.js"></script>
<script src="/null/Content/account/jquery-3.4.1.js"></script>
<meta charset="UTF-8">
<title>Input Product</title>
<style type="text/css">
table, th, td {
   border: 1px solid #dedede;

}

table {

    border-collapse: collapse;

}

</style>



</head>
<script src="/null/Content/api/jquery/jquery-3.4.1.js"></script>
<script>
$(document).ready(function(){
	$('#bigOption').change(function(){
		   $("#bigOption option:selected").each(function () {
				
				if($(this).val()== '1'){ //직접입력일 경우
					 $("#styletop").val('');                        //값 초기화
					 $("#styletop").attr("disabled",false); //활성화
				}else{ //직접입력이 아닐경우
					 $("#styletop").val($(this).text());      //선택값 입력
					 $("#styletop").attr("disabled",true); //비활성화
				}
		   });
		});
	$('#midOption').change(function(){
		   $("#midOption option:selected").each(function () {
				
				if($(this).val()== '1'){ //직접입력일 경우
					 $("#stylemid").val('');                        //값 초기화
					 $("#stylemid").attr("disabled",false); //활성화
				}else{ //직접입력이 아닐경우
					 $("#stylemid").val($(this).text());      //선택값 입력
					 $("#stylemid").attr("disabled",true); //비활성화
				}
		   });
		});
	$('#botOption').change(function(){
		   $("#botOption option:selected").each(function () {
				
				if($(this).val()== '1'){ //직접입력일 경우
					 $("#stylebot").val('');                        //값 초기화
					 $("#stylebot").attr("disabled",false); //활성화
				}else{ //직접입력이 아닐경우
					 $("#stylebot").val($(this).text());      //선택값 입력
					 $("#stylebot").attr("disabled",true); //비활성화
				}
		   });
		});
	$('#sizeOption').change(function(){
		   $("#sizeOption option:selected").each(function () {
				
				if($(this).val()== '1'){ //직접입력일 경우
					 $("#psize").val('');                        //값 초기화
					 $("#psize").attr("disabled",false); //활성화
				}else{ //직접입력이 아닐경우
					 $("#psize").val($(this).text());      //선택값 입력
					 $("#psize").attr("disabled",true); //비활성화
				}
		   });
		});
	$('#colorOption').change(function(){
		   $("#colorOption option:selected").each(function () {
				
				if($(this).val()== '1'){ //직접입력일 경우
					 $("#pcolor").val('');                        //값 초기화
					 $("#pcolor").attr("disabled",false); //활성화
				}else{ //직접입력이 아닐경우
					 $("#pcolor").val($(this).text());      //선택값 입력
					 $("#pcolor").attr("disabled",true); //비활성화
				}
		   });
		});
});
    </script>

<div align="center"><jsp:include page="top.jsp"/></div>
<br>
<br>
<br>
<br>
<form action="">


<div align="center"><b style="font-size:35px ">상품등록</b></div>
<br>
<br>
<div align="center" style="background-color: #f5f5f5">
<hr>
<table  style="background-color: white;">
<tr align="center" style="background-color: #dedede;width: 650px;height: 60px"><td colspan="4" ><a style="font-size: 120%" >기본 정보</a></td></tr>
<tr >

<td width="150" height="45" style="background-color:#edebeb "><a  style="font-size:100%">상품명</a><a  style="font-size:70%;color:red" >   (필수) </a></td>
<td width="10" style="border-color: #edebeb;border-right-style: hidden;"></td>
<td colspan="2" width="600" height="40" bordercolor="#edebeb"><input type="text" style="width:300px;height:70%;font-size:70%" id="pname" placeholder="5~50자까지 쓰기가능" name="pname"></td>

</tr>

<tr>
<td width="150" height="45" style="background-color:#edebeb "><a  style="font-size:100%">대분류</a><a  style="font-size:70%;color:red" >   (필수) </a></td>
<td width="10" style="border-color: #edebeb;border-right-style: hidden;"></td>
<td colspan="2" width="600" height="40" ><input type="text" style="width:150px;height:70%;font-size:70%;color: gray" id="styletop"  name="styletop" value="both">
<a style="font-size: 70%;color: gray">  성별을 선택해주세요  &nbsp;</a><select id="bigOption">
<option value="both" id="both">both</option>
<option value="male" id="male">male</option>
<option value="female" id="female">female</option>
</select ></td>
</tr>
<tr>
<td width="150" height="45" style="background-color:#edebeb "><a  style="font-size:100%">중분류</a><a  style="font-size:70%;color:red" >   (필수) </a></td>
<td width="10" style="border-color: #edebeb;border-right-style: hidden;"></td>
<td colspan="2" width="600" height="40" ><input type="text" style="width:150px;height:70%;font-size:70%;color: gray" id="stylemid"  name="stylemid" value="sports">
<a style="font-size: 70%;color: gray">  상품종류를 선택해주세요  &nbsp;</a><select id="midOption">
<option value="sports" id="sports">sports</option>
<option value="tennis" id="tennis">tennis</option>
<option value="boots" id="boots">boots</option>
<option value="shoe" id="shoe">shoe</option>
</select ></td>
</tr>
<tr>
<td width="150" height="45" style="background-color:#edebeb "><a  style="font-size:100%">소분류</a><a  style="font-size:70%;color:red" >   (필수) </a></td>
<td width="10" style="border-color: #edebeb;border-right-style: hidden;"></td>
<td colspan="2" width="600" height="40" ><input type="text" style="width:150px;height:70%;font-size:70%;color: gray" id="stylebot"  name="stylebot" value="sneakers">
<a style="font-size: 70%;color: gray">  상세종류를 선택해주세요  &nbsp;</a><select id="botOption">
<option value="sneakers" id="sneakers">sneakers</option>
<option value="canvas" id="canvas">canvas</option>
<option value="running" id="running">running</option>
<option value="hiking" id="hiking">hiking</option>
<option value="high_heel" id="high_heel">high_heel</option>
<option value="oxfurd" id="oxfurd">oxfurd</option>
<option value="fur" id="fur">fur</option>
<option value="work" id="work" >"work"</option>
</select ></td>
</tr>
<tr>
<td  width="150" height="45" style="background-color:#edebeb "><a  style="font-size:100%">상품 이미지</a><a  style="font-size:70%;color:red" >   (필수) </a></td>
<td width="10" style="border-color: #edebeb;border-right-style: hidden;"></td>
<td width="300" height="300" >

<input type="file" name="profile_pt" id="profile_pt" onchange="previewImage(this,'View_area')">
<div id='View_area' style='position:relative;
 width: 250px; height: 	250px; color: black; border: 0px solid black; dispaly: inline; '>
 <a style="font-size: 70%;color: gray">PNG,JPG만 가능합니다</a></div>
</td>
<td width="300" height="300" style="background-color:#edebeb ">
</td>

</tr>





</table>


<br>
<br>
<hr>
<table  style="background-color: white;">
<tr align="center" style="background-color: #dedede;width: 650px;height: 60px"><td colspan="4" ><a style="font-size: 120%" >상세 정보</a></td></tr>

<tr>
<td width="150" height="45" style="background-color:#edebeb "><a  style="font-size:100%">사이즈</a></td>
<td width="10" style="border-color: #edebeb;border-right-style: hidden;"></td>
<td colspan="2" width="600" height="40" ><input type="text" style="width:150px;height:70%;font-size:70%;color: gray" id="psize"  name="psize" >
<a style="font-size: 70%;color: gray">  사이즈를 선택해주세요  &nbsp;</a><select id="sizeOption">
<option value="200" id="200">200</option>
<option value="205" id="205">205</option>
<option value="210" id="210">210</option>
<option value="215" id="215">215</option>
<option value="220" id="220">220</option>
<option value="225" id="225">225</option>
<option value="230" id="230">230</option>
<option value="235" id="235">235</option>
<option value="240" id="240">240</option>
<option value="245" id="245">245</option>
<option value="250" id="250">250</option>
<option value="255" id="255">255</option>
<option value="260" id="260">260</option>
<option value="265" id="265">265</option>
<option value="270" id="270">270</option>
<option value="275" id="275">275</option>
<option value="280" id="280">280</option>
<option value="285" id="285">285</option>
<option value="290" id="290">290</option>
<option value="295" id="295">295</option>
<option value="300" id="300">300</option>
</select ></td>
</tr>
<tr>
<td width="150" height="45" style="background-color:#edebeb "><a  style="font-size:100%">상품색상</a></td>
<td width="10" style="border-color: #edebeb;border-right-style: hidden;"></td>
<td colspan="2" width="600" height="40" ><input type="text" style="width:150px;height:70%;font-size:70%;color: gray" id="pcolor"  name="pcolor" >
<a style="font-size: 70%;color: gray">  색상을 선택해주세요  &nbsp;</a><select id="colorOption">
<option value="black" id="black">black</option>
<option value="gray" id="gray">gray</option>
<option value="white" id="white">white</option>
<option value="red" id="red">red</option>
<option value="blue" id="blue">blue</option>
<option value="green" id="green">green</option>
<option value="yellow" id="yellow">yellow</option>
<option value="etc" id="etc">etc</option>
</select ></td>
</tr>
<tr >

<td width="150" height="45" style="background-color:#edebeb "><a  style="font-size:100%">상품수량</a></td>
<td width="10" style="border-color: #edebeb;border-right-style: hidden;"></td>
<td colspan="2" width="600" height="40" bordercolor="#edebeb"><input type="text" style="width:300px;height:70%;font-size:70%" id="pamount"  ></td>

</tr>
<tr>
<td width="150" height="45" style="background-color:#edebeb "><a  style="font-size:100%">상품가격</a></td>
<td width="10" style="border-color: #edebeb;border-right-style: hidden;"></td>
<td  width="300" height="40" ><input type="text" style="width:150px;height:70%;font-size:70%" id="price"  name="price" ><a  style="font-size: 70%">원</a></td>
<td  width="300" height="40" style="border-style: hidden;">
<a style="color: gray;font-size: 70%">배송비호함여부 Y</a><input type="radio" name="deliverfree" id="deliverfree" value="Y">
<a style="color: gray;font-size: 70%">N</a><input type="radio" name="deliverfree" id="deliverfree" value="N">
</td>

</tr>
<tr >
<td width="150" height="45" style="background-color:#edebeb "><a  style="font-size:100%">상품 요약 설명</a></td>
<td width="10" style="border-color: #edebeb;border-right-style: hidden;"></td>
<td colspan="2" width="600" height="40" ><input type="text" style="width:500px;height:70%;font-size:70%" id="userid" placeholder="" name="userid"></td>


</tr>
<tr >
<td width="150" height="45" style="background-color:#edebeb "><a  style="font-size:100%">상품 상세 설명</a></td>
<td width="10" style="border-color: #edebeb;border-right-style: hidden;"></td>
<td  width="600" height="200" colspan="2">
<textarea  name="content" id="editor"style="resize: none; font-size:70%;" id="userid" ></textarea>
</td>


</tr>


</table>
<br>
<br>

<button style="width: 120px;height: 40px;font-size: 20px;border: 1px solid red;background-color: red;color: white;font-weight:600" type="submit" id="btn">제출하기</button>

<br>
<br>

</div>
</form>

<script>
    //Editor
    ClassicEditor
        .create( document.querySelector( '#editor' ) )
        .catch( error => {
            console.error( error );
        } );
    //fileupload
    function previewImage(targetObj, View_area) {
	var preview = document.getElementById(View_area); //div id
	var ua = window.navigator.userAgent;

  //ie일때(IE8 이하에서만 작동)
	if (ua.indexOf("MSIE") > -1) {
		targetObj.select();
		try {
			var src = document.selection.createRange().text; // get file full path(IE9, IE10에서 사용 불가)
			var ie_preview_error = document.getElementById("ie_preview_error_" + View_area);


			if (ie_preview_error) {
				preview.removeChild(ie_preview_error); //error가 있으면 delete
			}

			var img = document.getElementById(View_area); //이미지가 뿌려질 곳

			//이미지 로딩, sizingMethod는 div에 맞춰서 사이즈를 자동조절 하는 역할
			img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+src+"', sizingMethod='scale')";
		} catch (e) {
			if (!document.getElementById("ie_preview_error_" + View_area)) {
				var info = document.createElement("<p>");
				info.id = "ie_preview_error_" + View_area;
				info.innerHTML = e.name;
				preview.insertBefore(info, null);
			}
		}
  //ie가 아닐때(크롬, 사파리, FF)
	} else {
		var files = targetObj.files;
		for ( var i = 0; i < files.length; i++) {
			var file = files[i];
			var imageType = /image.*/; //이미지 파일일경우만.. 뿌려준다.
			if (!file.type.match(imageType))
				continue;
			var prevImg = document.getElementById("prev_" + View_area); //이전에 미리보기가 있다면 삭제
			if (prevImg) {
				preview.removeChild(prevImg);
			}
			var img = document.createElement("img"); 
			img.id = "prev_" + View_area;
			img.classList.add("obj");
			img.file = file;
			img.style.width = '200px'; 
			img.style.height = '200px';
			preview.appendChild(img);
			if (window.FileReader) { // FireFox, Chrome, Opera 확인.
				var reader = new FileReader();
				reader.onloadend = (function(aImg) {
					return function(e) {
						aImg.src = e.target.result;
					};
				})(img);
				reader.readAsDataURL(file);
			} else { // safari is not supported FileReader
				//alert('not supported FileReader');
				if (!document.getElementById("sfr_preview_error_"
						+ View_area)) {
					var info = document.createElement("p");
					info.id = "sfr_preview_error_" + View_area;
					info.innerHTML = "not supported FileReader";
					preview.insertBefore(info, null);
				}
			}
		}
	}
}
    
    
    </script>

</div>
<form>







</form>

</body>

</html>