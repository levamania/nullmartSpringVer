/**
 * modifybtns: 수정버튼 클래스 
 */
$(document).ready(function(){
	var delivno=0;
	$(".modifybtns").each(function(idx,btn){
		$(this).on("click",function(){
			delivno=$(this).prev().val();
			$(location).attr("href","/null/ModifyAddrList?delivno="+delivno);
		});
	});
	
	
});

$(document).ready(function(){
	var checks = $(".checks");
	$("#allcheck").on("click",function(){
		checks.each(function(idx,check){
			if($(this).prop("checked")){
				$(this).removeAttr("checked");
			}else{
				$(this).attr("checked","checked");
			}
			
		});
	});
	$("#delete").on("click",function(){
		var queryString = "/null/DeleteAddrServlet?delivnos=";
		checks.each(function(idx,check){
			if($(this).prop("checked")){
				queryString +=$(this).val()+"-";
			}
		});
		$(location).attr("href",queryString);
	});
});

$(document).ready(function(){
	$("#goaddradd").on("click",function(){
		$(location).attr("href","regaddr.jsp");
	});
});