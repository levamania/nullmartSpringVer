<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <script src="/null/Content/account/jquery-3.4.1.js"></script>
<!-- <link class="include" href="/null/Content/api/jqPlot/jquery.jqplot.min.css"  rel="stylesheet" type="text/css"/>
<script src="/null/Content/api/jqPlot/jquery.jqplot.min.js" type="text/javascript"></script>
<script src="/null/Content/api/jqPlot/plugins/jqplot.highlighter.js" type="text/javascript"></script>
<script src="/null/Content/api/jqPlot/plugins/jqplot.cursor.js"type="text/javascript"></script>
<script src="/null/Content/api/jqPlot/plugins/jqplot.dateAxisRenderer.js"type="text/javascript"></script>
<script src="/null/Content/api/jqPlot/plugins/jqplot.canvasAxisLabelRenderer.js"type="text/javascript"></script>
<script src="/null/Content/api/jqPlot/plugins/jqplot.enhancedLegendRenderer.js"type="text/javascript"></script>
<script src="/null/Content/api/jqPlot/plugins/jqplot.canvasAxisTickRenderer.js"type="text/javascript"></script>
 -->
 <!-- JQ-PLOT의 CSS를 설정 -->
  <link class="include" href='<c:url value="/null/Content/api/jqPlot/jquery.jqplot.min.css" />' rel="stylesheet" type="text/css" />
  <!-- JQ-PLOT의 기본 설정 -->
  <script src='<c:url value="/null/Content/api/jqPlot/jquery.min.js" />' type="text/javascript"></script>
  <!-- Highlighter(마우스 접근시 데이터정보 표시) 설정 -->
  <script src='<c:url value="/jqPlot/plugins/jqplot.highlighter.js"/>' type="text/javascript"></script>
  <!-- 좌표에 관한 정보나 Zoom 기능 사용시 설정 -->
  <script src='<c:url value="/jqPlot/plugins/jqplot.cursor.js"/>' type="text/javascript"></script>
  <!-- 축의 데이터를 날짜형태로 입력하기 위해서 설정 -->
  <script src='<c:url value="/jqPlot/plugins/jqplot.dateAxisRenderer.js"/>' type="text/javascript"></script>
  <!-- 축의 데이터의 Label Option을 설정 -->
  <script src='<c:url value="/jqPlot/plugins/jqplot.canvasAxisLabelRenderer.js"/>' type="text/javascript"></script>
  <!-- Legend(Line에대한 간단한 범례)의 Option을 설정 -->
  <script src='<c:url value="/jqPlot/plugins/jqplot.enhanceLegendRenderer.js"/>' type="text/javascript"></script>
  <!-- 축의 데이터를 순서에 상관없이 자동정렬을 설정 -->
  <script src='<c:url value="/jqPlot/plugins/jqplot.categoryAxisRenderer.js"/>' type="text/javascript"></script>
  <!-- 축의 데이터 표현설정과 그래프위의 점의 Option을 설정 -->
  <script src='<c:url value="/jqPlot/plugins/jqplot.canvasAxisTickRenderer.js"/>' type="text/javascript"></script> 




  <input type="button" id="btn" value="그리기" class="btn btn-default btn-sm">
<div id="graph"></div>

<script>
$(function() {
 $("input#btn").click(function(e) {
  // AJAX
  $.ajax({
   url : "${pageContext.request.contextPath}/json", 
   contentType : "application/json",
   success : function (data) {
    var jsonObj = JSON.parse(data);
    var line = new Array();
    var line2 = [];
    for (var i=0; i<jsonObj.length; i++) {
     var linepush = new Array();
     var linepush2 = new Array();
     linepush.push(jsonObj[i].line_date,Number(jsonObj[i].line_data));
     linepush2.push(jsonObj[i].line_date,Number(jsonObj[i].line_data2));
     line[i] = linepush;
     line2[i] = linepush2;
    } // for
    
    $("#graph").html("");
    var plot = $.jqplot('graph', [line ,line2],{
     axes:{
      xaxis:{
       // 날짜 형태로 입력: Date형식의 Renderer을 사용.
       renderer:$.jqplot.DateAxisRenderer,
       tickOptions:{ // 축에관한 옵션                   
       // format 형식: 입력값(yyyy/mm/dd)
        formatString:'%y/%m/%d'
       }
      },
      yaxis: {
       label: '노랑이',
       labelOptions:{
        textColor: '#EAA228'
       },
       rendererOptions: { forceTickAt0: false, forceTickAt100: false }
      },
      y2axis: {
       label: '파랑이',
       labelOptions:{
        fontFamily:'Helvetica',
        fontSize: '20pt',
        textColor: '#4BB2C5'
       },
       rendererOptions: { forceTickAt0: false, forceTickAt100: false }
      }       
     },
     series:[        // 속성?  양쪽에 선그리기
      {         // 첫번째 그래프 속성
       label:'파랑이',
       markerOptions: { style:'x' }, 
       // 마커 스타일 (default:● , diamond:◇, circle:○, x:x, filledSquare:■)
       showLine:false,     // 선 안보이게 하기 / true : 보이기
       pointLabels: {
        show: true
       },
       renderer: $.jqplot.BarRenderer,
       //showHighlight: false,   // 마우스업 값 출력
       yaxis: 'y2axis',
       rendererOptions: {
        animation: {
         speed: 2500    // 파랑선 그려지는 속도
        },
        barWidth: 15,
        barPadding: -15,
        barMargin: 0,
        highlightMouseOver: false
       }
      },
      {         // 두번째 그래프 속성
       label:'노랑이',
       rendererOptions: {
        animation: {
         speed: 2500    // 노랑선 그려지는 속도
        }
       }
      }
     ],
     animate: true,     // 그래프 그려지는 애니메이션
     animateReplot: true,
     cursor: {
      show: true,
      zoom: true,
      looseZoom: true,
      showTooltip: true,     // 툴팁 유무
      tooltipLocation:'sw'   // 툴팁 위치
     },
     highlighter: {
      show: true,
      showLabel: true,
      tooltipAxes: 'y',
      sizeAdjust: 7.5 , tooltipLocation : 'ne'
     },
     legend:{ show: true }      // 색깔별 보기? 안내? 그거..;;
     }); // plot
   },  // success : function (xml)
  
   error : function(xhr, status) {
    alert(xhr+" : "+status);
   }
  }); // $.ajax
 });   // bun end
}); 
</script> 
