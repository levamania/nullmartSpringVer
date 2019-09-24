<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<head>
<title>관리자페이지</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="/null/Content/account/jquery-3.4.1.js"></script>
<script type="text/javascript" src="/null/Content/api/jqPlot/jquery.jqplot.min.js"></script>
<script type="text/javascript" src="/null/Content/api/jqPlot/plugins/jqplot.barRenderer.js"></script>
<script type="text/javascript" src="/null/Content/api/jqPlot/plugins/jqplot.categoryAxisRenderer.js"></script>
<script type="text/javascript" src="/null/Content/api/jqPlot/plugins/jqplot.pointLabels.js"></script>
<link rel="stylesheet" type="text/css" href="/null/Content/api/jqPlot/jquery.jqplot.css"/>
<style type="text/css">
*{
	box-sizing: border-box;
}
#chart .jqplot-point-label {
    border:1.5px solid white;
    padding:1px 3px;
    background-color: solid white;
}


#container{
	display: flex;
	flex-direction: row;
	align-items: stretch;
}

nav{
	display: inline-flex;
	flex-basis: 100px;
	flex-grow: 1;
	flex-shrink: 0;
}

section{
	display:flex;
	flex-direction: column;
	flex-grow: 12;
	border-left: 1px solid lightgray;
}
hr{
        background-color: white;
        border-color : lightgray;
        border-style :ridge;
        height:1px;
      }
#top_body{
		display: flex;
		align-items: center;
		justify-content: center;
		height: 100px;
		border-bottom: 1px solid red;
	}
	#welcome{
		display: inline;
		position: fixed;
		right: 0;
		top:70px;
	}
	
	@media (max-width:650px){
	#top_body{
		top: 100px;
		height: 120px;
	}
	#welcome{
		top:100px;
	}
}      
      
</style>



<script type="text/javascript">


$(document).ready(function () {
	var result= null;
	$.ajax({
		
		url:"/null/admin/adminMainChart",
		type:"get" ,
		dataType: "json",
			success: function(data,status,xhr){
				var line = [];
				console.log(data);
				
				for(var i=0;i<data.length;i++){
					line[i]=[data[i].stylebot,data[i].hap];
				}
				console.log(line);
				$("#chart").jqplot([line], {
			          title:"상품 재고 현황 그래프"
			        	  , seriesColors:[ '#CC3D3D','#FF0000', '#FF6C6C', '#FFA7A7', '#FFD8D8','#980000','#CE3636','#FFC6C6']
			        , seriesDefaults:{ renderer:jQuery.jqplot.BarRenderer 
			        	, rendererOptions:{
			                varyBarColor:true
			            }

			        }
			        , series:[
			            {
			                pointLabels:{
			                    show:true
			                    , labels:[line[i]]
			                }
			            }
			            
			        ]
			        , axes:{
			              xaxis:{ renderer:jQuery.jqplot.CategoryAxisRenderer }
			            , yaxis:{ padMax:1.3 }
			        }
			    });
			},
			error:function(xhr,status,e){
				console.log(status);
				console.log(e);
			}
		
	});

	<% //request.setCharacterEncoding("UTF-8");
	//int n = (int) request.getAttribute("n"); %>
	<% 
	//request.setCharacterEncoding("UTF-8");
	//int n = (int) request.getAttribute("n");
	//System.out.println(n); %>
	
   
   <%--  
  var line =  [['${amount}',${amount}]
    <c:forEach var="amount" items="${SUM}" varStatus="status" >,['${amount}',${amount}]
</c:forEach>
]; 
    --%>
  /*  var line = [['tennis', 10], ['tennis2',35], ['sports', 4], ['sports2',30], ['shoe', 25], ['shoe2', 24], ['boots', 5], ['boots2', 50]];
    $("#chart").jqplot([line], {
          title:"상품 재고 현황 그래프"
        	  , seriesColors:[ '#CC3D3D','#FF0000', '#FF6C6C', '#FFA7A7', '#FFD8D8','#980000','#CE3636','#FFC6C6']
        , seriesDefaults:{ renderer:jQuery.jqplot.BarRenderer 
        	, rendererOptions:{
                varyBarColor:true
            }

        }
        , series:[
            {
                pointLabels:{
                    show:true
                    , labels:['canvas','sneakers', 'running', 'hiking', 'high_heel', 'oxfurd','fur', 'work', ]
                }
            }
            
        ]
        , axes:{
              xaxis:{ renderer:jQuery.jqplot.CategoryAxisRenderer }
            , yaxis:{ padMax:1.3 }
        }
    }); */
});
</script>

<div id="top_body" >

	
<a id="/null/main" href="/null/main"><img src="/null/Content/img/account/NULL-Mart(LOGO).PNG"></a>

<div style="display: inline; position: fixed; right: 0; top: 70px;">
	<a>관리자 페이지&nbsp;&nbsp;&nbsp;</a>
</div>


</div>
<div align="right">
<a href="/null/mgrLogout" style="font-size: 70%;text-decoration:none;color:black">Logout</a>
</div>

</head>
<body>


		
<div id="container">
	<nav>
		<jsp:include page="left.jsp"/>
	</nav>
	<section>
	<div align="center" style="background-color: white">
		<br>
		
		<b style="font-size: 30px">관리자님 환영합니다</b>
		
		
		<br>
		<hr>
		<br>
		<br>
<div id="chart" style="height:400px;width:850px;"></div>
<br>
<button onclick="location.href='/null/Content/admin/searchStock.jsp'" style="background-color: red;color: white;width: 135px;height: 32px;border-color: white;font-size: 16px" id="reFlesh">자세히 검색하기</button>
		
		
		</div>
		<br>
		<br>
	</section>
</div>

	



</body>

