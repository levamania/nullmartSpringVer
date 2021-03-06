<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/null/Content/api/jquery/jquery-3.4.1.js"></script>
<script type="text/javascript">
$().ready(()=>{

	//전환 설정
		$(".head").on("click",function(){
			let id = $(this).attr("id"); 
			//border 변경
			$(this).toggleClass("chief");
			$(this).siblings(".head").removeClass("chief");
			$(this).next("bor")
			//z-index조정
			$.each($(".layout"),function(){
				$(this).css({"z-index":"0"});
				if($(this).attr("id").endsWith(id)){
					$(this).css({"z-index":"1"});
				}
			})
		})
		$(".head#1").trigger("click");
		
	//주소록 레코드 선택
	$(".group>.decision").on("click",function(){
		let record = {};
		let count = 0;
		$(this).siblings().each(function(){
			let clas = $(this).attr("class")
			if(clas.endsWith("hide")){
				clas = clas.substr(0, clas.length-4).trim();
				console.log(clas);
			}
			if(clas.includes("phone")){
				let separated = $(this).text().split("-");
				for(var index in separated ){
					record[clas+(Number.parseInt(index)+1)] = separated[index];
				}
			}else{
				record[clas] = $(this).text();
			}
		})
		window.opener.postMessage(record,"http://localhost:8090");
		window.close();
	})

	
	
	
})
</script>
<style type="text/css">
	@import url("/null/Content/configuration/font/fonts.css");
	
	*{box-sizing: border-box;} 
 	.head{box-sizing: border-box;} 
 	
 	
	
	html{
		overflow-x: hidden;
		overflow-y:scroll;
		
		font-size: 2vh;
	}
	
	body{
		width: 100%;
		height:100%;
		
		margin:0;
		font-family: Maplestroy;
	}

	#title{
		display: flex;
		align-items: center;
		justify-content: center;
		
		width:100vw;
		min-width:500px;
		height: 10vh;
		min-height:30px;
		
		
		background: red;
		
		color:white;
		font-weight: bold;
		font-size: 2rem;
		
	}
	
	#container{
		display: flex;
		align-items: center;
		justify-content: center; 
		
		position:relative;	
		
		width:90vw;
		min-width:500px;
		height: 80vh;
		min-height:300px;
		
		margin: 5vh 5vw 5vh;
	
		font-size: 1.5rem;
	}
	
	/*head 설정*/
	.head{
		display: inline-flex;
		align-items: center;
		justify-content: center;
		
		position: absolute;
		top:1px;left:0;
		
		width:20%;
		height:10%;
		
		border: 1px solid gray;
		border-bottom: 1px solid black;
		
		cursor: pointer;
		z-index: 3;
	}
	.head:nth-child(2){left:20%;}
	
	.head.chief{
		border: 1px solid black;
		border-bottom: 1px solid white;
	}
	
	/*layout설정*/
	.layout{
		position:absolute;
		top:10%;left:0;
	
		height:100%;
		width:100%;
		
		border-top: 1px solid black;
	}
	
	.layout>.record{
		display : flex;
		align-items:center;
		justify-content: space-between;
		
		position:relative;
	
		background-color: white;
	}
	
	.layout>.record div{
		display: flex;
		align-items: center;
		justify-content: center;
		
		overflow: hidden;
		white-space:nowrap; 
		text-overflow: ellipsis;
	}
	.layout>.record div.hide{display: none;}
	
	.layout>.record .alias{flex:10; margin-left: 10px;}
	.layout>.record .order_name{flex:10;}
	.layout>.record .order_telephone{flex:20;}
	.layout>.record .order_phone{flex:20;}
	.layout>.record .order_address1{flex:45;} 
		.layout>.content .order_address1{justify-content: flex-start;}
	.layout>.record .order_postcode{display: none;}	
	.layout>.record .decision{flex:10;margin-right: 10px;}
	
	.layout>.index{	
		width: 100%;
		height: 10%;
		
		top:10%;
		
		background-color: #ffd6d6;
		border-top: 1.5px solid #a360a7;
		border-bottom: 1.5px solid #ff4c4c;
	}
	
	.layout>.content{
		top:10%;
	
		width: 100%;
		height: 80%;
			
		flex-wrap:wrap;
		align-content: flex-start;
	}
	
	.layout>.content>.group{
		display: flex;
		align-items: center;	
		justify-content: space-between;
		
		width: 100%;
		height: 10%;
		
	}
	
	/*선택 설정*/
	.layout>.content>.group>.decision{
		border: 1px solid red;
		cursor: pointer;
	}
	.layout>.content>.group>.decision:hover{
		color:red;
	}
	
	/*lay1설정*/
	#lay1{
		z-index: 1;	
	}
	#lay1>.head{
		position:relative;
		left:20%;
	}
	
	/*lay2설정*/
	#lay2{
		z-index: 0;
	}
	
</style>
</head>
<body>
	<div id="title">
		<div>배송지</div>
	</div>
	<div id="container">
		<div class="head" id="1">최근 배송지</div>
		<div class="head" id="2">주소록</div>
		<div id="lay1" class="layout">
			<div class="index record">
				<div class="order_name">수령인</div>
				<div class="order_telephone">전화번호</div>
				<div class="order_phone">핸드폰</div>
				<div class="order_address1">배송지 주소</div>
				<div class="decision">선택</div>
			</div>
			<div class="content record">
				<c:forEach var="RECORD" items="${BOOK_RECENT}">
					<div class="group">
						<div class="order_name">${RECORD.ORDER_NAME}</div>
						<div class="order_telephone">${RECORD.PHONE2}</div>
						<div class="order_phone">${RECORD.PHONE1}</div>
						<div class="order_address1">${RECORD.ADDR1}</div>
						<div class="order_address2 hide">${RECORD.ADDR2}</div>
						<div class="order_address3 hide">${RECORD.ADDR3}</div>
						<div class="order_postcode">${RECORD.POST}</div>
						<div class="decision">선택</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div id="lay2" class="layout">
			<div class="index record">
				<div class="alias">주소명</div>
				<div class="order_name">수령인</div>
				<div class="order_telephone">전화번호</div>
				<div class="order_phone">핸드폰</div>
				<div class="order_address1">배송지 주소</div>
				<div class="decision">선택</div>
			</div>
			<div class="content record">
				<c:forEach var="RECORD" items="${BOOK}">
					<div class="group">
						<div class="alias">${RECORD.DELIVNAME}</div>
						<div class="order_name">${RECORD.DELIVPERSON}</div>
						<div class="order_telephone">${RECORD.PHONE2}</div>
						<div class="order_phone">${RECORD.PHONE1}</div>
						<div class="order_address1">${RECORD.ADDRESS1}</div>
						<div class="order_address2 hide">${RECORD.ADDRESS2}</div>
						<div class="order_postcode">${RECORD.POST}</div>
						<div class="decision">선택</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>