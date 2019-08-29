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
	var num = 0;
	var checks = $(".checks");
	$("#allcheck").on("click",function(){
		num+=1;
		checks.each(function(idx,check){
			
			$(this).prop("checked",true);
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