<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src="/null/Content/api/jquery/jquery-3.4.1.js"></script>

    <script language="javascript" type="text/javascript" src="/null/Content/api/jqPlot/jquery.jqplot.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/null/Content/api/jqPlot/jquery.jqplot.css" />
 </head>
    <body>
    <h1>감자탕 예제 (곡선그래프)</h1>

    <script id="source" language="javascript" type="text/javascript">
    $(function() { var plot1 = $.jqplot('chartdiv',  [[[1,2],[3,5.12],[5,13.1],[7,33.6],[9,85.9],[11,219.9]]]); })
    </script>

<a>감자탕</a><br>
   <div id="chartdiv" style="height:400px;width:300px; "></div>
<a>감자탕2</a><br>


 </body>
</html>
<html>