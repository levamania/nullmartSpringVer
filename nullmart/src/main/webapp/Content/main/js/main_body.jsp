<%@page import="java.util.ArrayList"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	File dir = new File(application.getRealPath("Content/img/advertise"));
	ArrayList<String> file_list = new ArrayList<>();

	File[] fileList = dir.listFiles();
	for(File file : fileList) {
		if(file.isFile()) {
		file_list.add("\'"+file.getName()+"\'");
		}
	}
	
	request.setAttribute("file_list", file_list);
	
%>
<script type="text/javascript">

$().ready(()=>{
	//홍보물 이미지 설정
	//타임 인터벌 저장용
	var advertise_animation;
	//이미지 리스트
	var list = <c:out value="${file_list}" escapeXml="false"/>;
	list.unshift("helper"); //배열의 직관성을 위한 인덱스 도움자
	var list_length = list.length-1;
	//이미지 현재 위치 및 갯수
	var img_position = 1;
	
	//기타..
	var prev_stack; //바로 전 이미지 정보저장
	var imgs = $(".main>#ad_img ul"); //이미지 컨테이너
	var imgs_temp = imgs.children("li"); //이미지들
	var cover_img = $(".main>#ad_img>img");//덮어쓸 이미지
	var animation_status = "end";//애니메이션 상태 저장용
	
	//초기화면 설정
	imgs_temp.first().children().attr({"src":"/null/Content/img/advertise/"+list[list.length-1]});
	imgs_temp.eq(1).children().attr({"src":"/null/Content/img/advertise/"+list[1]});
	imgs_temp.last().children().attr({"src":"/null/Content/img/advertise/"+list[2]});
	
	//시간 딜레이 함수 - ms 단위
	function delay(setTime){
		var curr_time = new Date();
		var interval = 0;
		while(interval<setTime){
			interval = new Date() - curr_time;
		}
	}
	
	//슬라이딩 함수
	function sliding (direction){
		var animation;
		if(direction=='right'){
			img_position++;
			animation="-=1000px"
			if(img_position>list_length)img_position=1;
		}else if(direction=='left'){
			img_position--;
			animation="+=1000px"
			if(img_position<1)img_position=list_length;
		}
		//포지션 설정
		var first, second, third;
		first = img_position-1;
		second = img_position;
		third = img_position+1;
		if(img_position==list_length){
			third = 1; 
		}else if(img_position==1){
			first = list_length;
		}
		
		//이미지 슬라이딩	
		animation_status = "start";
		var old = new Date(), later; //현재시각 
		var interval = 0; 
		
		imgs.animate({left:animation},1000, implicit);
		
		//이미지 덮어쓰기
		function implicit(){
			cover_img.attr({"src":"/null/Content/img/advertise/"+list[second]});

		
			cover_img.attr({"z-index":"-9"})
			imgs.css({'z-index':'-10',left:'-1000px'}); //이미지 뒤로 이동	
		
			//로테이션 이미지 변경
			imgs_temp.first().children().attr({"src":"/null/Content/img/advertise/"+list[first]});
			imgs_temp.eq(1).children().attr({"src":"/null/Content/img/advertise/"+list[second]});
			imgs_temp.last().children().attr({"src":"/null/Content/img/advertise/"+list[third]});

		
			//이미지 재설정
			$(".main>#ad_img>img").css({"z-index":-10});
			imgs.css({"z-index":-9});
			//애니메이션  종료
			animation_status = "end";
		
		}//end change	
		
	}// end sliding
	
	//자동 슬라이딩 설정 
	function advertise_trigger(){  
		advertise_animation = setInterval(function(){sliding("right")}, 3000);
	}
	advertise_trigger();
	
	//interval 제거 함수
	function clearAnimation(animation){
		clearInterval(animation);
		advertise_animation = null;
	}
	
	//탭전환시 animation  제거
	$("html").on("mouseenter",function(){
						if(advertise_animation==null){
							advertise_trigger();
						}
					})
					.on("mouseleave",function(){
						if(advertise_animation!=null){
							clearAnimation(advertise_animation);
						}
					})
	
	//버튼 이벤트 설정
		$(".main>.button").on("click",function(){
								if(advertise_trigger!=null){clearInterval(advertise_trigger);}
								if($(this).hasClass("right")){
									if(animation_status=="end"){
										sliding("right");
									}
								}else if($(this).hasClass("left")){
									if(animation_status=="end"){
										sliding("left");	
									}	 									
								}
						 });
		$(".body.main.advertise").on("mouseover",function(){
									event.stopPropagation(); //이벤트 전파 방지
									if(advertise_animation!=null)clearAnimation(advertise_animation); //타임 인터벌 제거
								 })
								 .on("mouseout",function(){
									 console.log(advertise_animation);
									 if(advertise_animation==null)advertise_trigger();//타임 인터벌 재셋팅
								 });
								  
	});
</script>