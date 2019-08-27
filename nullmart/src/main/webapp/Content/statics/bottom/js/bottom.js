/**
 * 
 */

$().ready(()=>{
	//사업자 정보 확인
	$(".body.bottom>#corp_info #specific_info").on("click",function(){
		window.open("/null/Content/statics/bottom/parts/corp_info.html","사업자 정보", "width:800px");
	});
})