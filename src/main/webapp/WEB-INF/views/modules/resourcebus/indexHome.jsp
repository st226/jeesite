<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <style type="text/css">
	body{background-image: url(${ctxStatic}/indexHome/images/nybj.png);background-size:100% 100%;font-weight:bold;font-family:苹方;overflow: hidden;}
	.main{width:80%;height:768px;position:relative;margin:auto;}
	div{border:0px solid white;margin:1px;}
	.layer{position:relative;width:100%;}
	#layer01{}
	#layer01 img{text-align: center;display: block;height: 35px;padding-top: 35px;margin: auto;}
	#layer02 > div{height:100%;float:left;position:relative;}
	.layer02-data{position: absolute;width: auto;height: 100px;color: white;top: 45px;left: 65px;}
	.layer03-panel{height:100%;position:relative;float:left;}
	.layer03-left-label{position:absolute;}
	#layer03_left_label01{top:10px;left:10px;color:white;height:20px;width:200px;font-weight: bold;}
	#layer03_left_label02{right:10px;top:10px;color:#036769;height:20px;width:200px;}
	.layer03-left-chart{position:relative;float:left;height:100%;}
	#layer03_right_label{position:absolute;top:10px;left:10px;color:white;height:20px;width:100px;}
	.layer03-right-chart{position:relative;float:left;height:100%;width:32%;}
	.layer03-right-chart-label{color: white;text-align: center;position: absolute;bottom: 60px;width: 100%;}
	.layer04-panel{position:relative;float:left;height:100%;width:48%;}
	.layer04-panel-label{width:100%;height:15%;color:white;padding-top:5px;}
	.layer04-panel-chart{width:100%;height:100%;}
  </style>

  <script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
  <script type="text/javascript" src="${ctxStatic}/jquery/echarts.js"></script>
  <script type="text/javascript" src="${ctxStatic}/indexHome/monitor.js"></script>
  <link rel="stylesheet" href="${ctxStatic}/eslydsj/css/base.css">
  <script type="text/javascript">
	$(function(){
		
		
		$.ajax({
	        type:'post',
	        url:'${ctx}/resourcebus/tsResourceBus/geChartData',
	        data:{'busTableType':'p'},
	        cache:false,
	        dataType:'json',
	        success:function(data){
	            setSz(data[1]);
	            renderLegend(data[0]);
	            renderChartBar01(data[0]);
	            renderLayer03Right(data[1]);
	            renderLayer04Left(data[2]);
	            renderLayer04Right(data[4]);
	        }
	    });
		
		
		
		drawLayer02Label($("#layer02_01 canvas").get(0),"上传文档数量",80,200);
		drawLayer02Label($("#layer02_02 canvas").get(0),"今日上传数量",80,300);
	//	drawLayer02Label($("#layer02_03 canvas").get(0),"新增存储数据",80,400);
		drawLayer02Label($("#layer02_04 canvas").get(0),"已归档个数",50,200);
		drawLayer02Label($("#layer02_05 canvas").get(0),"待审批个数",40,200);
		drawLayer02Label($("#layer02_06 canvas").get(0),"待归档个数",50,200);
		$("#spanid").html("10");
		
		//renderLegend();

		//饼状图
		//renderChartBar01();
		//renderChartBar02();

		//存储
		//renderLayer03Right();

		//30天日均线流量趋势
		// renderLayer04Left();

		//集群性能
		//renderLayer04Right();
	});
	
	function setSz(data){
		
	    var value = data[0] ;
	    $("#span1").html(value.count);
	    $("#span2").html(value.count5);
	    $("#span3").html(value.count4);
	    $("#span4").html(value.count2);
	    $("#span5").html(value.count3);
	}
  </script>
  <title>现行文件总览图</title>
 </head>
 <body>
 <header class="header left">
    <div class="left nav">
        <ul>
            <li ><i class="nav_1"></i><a href="${ctx}/resourcebus/tsResourceBus/gdzc">固定资产</a> </li>
            <li class="nav_active"><i class="nav_7"></i><a href="${ctx}/resourcebus/tsResourceBus/xxwj">现行文件</a> </li>
            <li ><i class="nav_3"></i><a href="${ctx}/resourcebus/tsResourceBus/xxsb">信息化设备</a> </li>


        </ul>
    </div>
    <div class="header_center left">
         <h2><strong>现行文件驾驶舱</strong></h2>
        <p class="color_font"><small>Current document cockpit</small></p>
    </div>
    <div class="right nav text_right">
        <ul> 
           
        </ul>
    </div>
    <!--<nav class="left nav">-->
    <!--<ul>-->
    <!--<li class="nav_active"><i class="nav_1"></i><a href="index.html">数据概览</a> </li>-->
    <!--<li><i class="nav_2"></i><a href="carContrl.html">车辆监控</a> </li>-->
    <!--<li><i class="nav_3"></i><a href="map.html">地图界面</a> </li>-->
    <!--<li><i class="nav_4"></i><a href="javascript:void(0)">表格界面</a><ul class="li_ul">-->
    <!--<li><a href="table1.html">表格一</a> </li>-->
    <!--<li><a href="table2.html">表格二</a> </li>-->

    <!--</ul> </li>-->
    <!--&lt;!&ndash;<li><i class="nav_5"></i><a href="#">车载视频</a> </li>&ndash;&gt;-->
    <!--&lt;!&ndash;<li><i class="nav_6"></i><a href="#">视频监控</a> </li>&ndash;&gt;-->
    <!--<li><i class="nav_7"></i><a href="static.html">查询统计</a> </li>-->
    <!--<li><i class="nav_8"></i><a href="message.html">信息录入</a> </li>-->
    <!--</ul>-->
    <!--</nav>-->
</header>
	 <div class="main">
	
		<div id="layer02" class="layer" style="height:20%;">
			<div id="layer02_01" style="width:18%;">
				<div class="layer02-data">
					<span id="span1" style="font-size:26px;"></span>
					<span style="font-size:16px;">条</span>
				</div>
				<canvas width="200" height="100"></canvas>
			</div>
			<div id="layer02_02" style="width:20%;">
				<div class="layer02-data">
					<span id="span2" style="font-size:26px;"></span>
					<span style="font-size:16px;">条</span>
				</div>
				<canvas width="200" height="100"></canvas>
			</div>
			
			<div id="layer02_04" style="width:20%;">
				<div class="layer02-data">
					<span  id="span3" style="font-size:26px;"></span>
					<span style="font-size:16px;">个</span>
				</div>
				<canvas width="120" height="100"></canvas>
			</div>
			<div id="layer02_05" style="width:20%;">
				<div class="layer02-data">
					<span  id="span4" style="font-size:26px;"></span>
					<span style="font-size:16px;">个</span>
				</div>
				<canvas width="120" height="100"></canvas>
			</div>
			<div id="layer02_06" style="width:20%;">
				<div class="layer02-data">
					<span id="span5" style="font-size:26px;">5</span>
					<span style="font-size:16px;">个</span>
				</div>
				<canvas width="120" height="100"></canvas>
			</div>
		</div>
		<div id="layer03" class="layer" style="height:40%;">
			<div id="layer03_left" style="width:48%;" class="layer03-panel">
				<div id="layer03_left_label01" class="layer03-left-label">各部门占比</div>
				<!--
				<div id="layer03_left_label02" class="layer03-left-label">(左)在线数量 (右)上线率</div>
				-->
				<div id="layer03_left_01" class="layer03-left-chart" style="width:16%;">
					<canvas width="100" height="200" style="margin:30px 0 0 20px;"></canvas>
				</div>
				
				<div id="layer03_left_02" class="layer03-left-chart" style="width:80%;"></div>
				<!--
				<div id="layer03_left_03" class="layer03-left-chart" style="width:80%;"></div>
				-->
			</div>
			<div id="layer03_right" style="width:50%;" class="layer03-panel">
				<div id="layer03_right_label">审核</div>
				<div id="layer03_right_chart01" class="layer03-right-chart">
					<canvas width="130" height="150" style="margin:40px 0 0 20px;"></canvas>
					<div class="layer03-right-chart-label">待审批</div>
				</div>
				<div id="layer03_right_chart02" class="layer03-right-chart">
					<canvas width="130" height="150" style="margin:40px 0 0 20px;"></canvas>
					<div class="layer03-right-chart-label">待归档</div>
				</div>
				<div id="layer03_right_chart03" class="layer03-right-chart">
					<canvas width="130" height="150" style="margin:40px 0 0 20px;"></canvas>
					<div class="layer03-right-chart-label">已归档</div>
				</div>
			</div>
		</div>
		<div id="layer04" class="layer" style="height:30%;">
			<div id="layer04_left" class="layer04-panel">
				<div id="layer04_left_label" class="layer04-panel-label">30天上传文档趋势</div>
				<div id="layer04_left_chart" class="layer04-panel-chart"></div>
			</div>
			<div id="layer04_right" class="layer04-panel">
				<div id="layer04_right_label" class="layer04-panel-label">
					<span>各个型号文档数量/</span><span style="color:#00A09A;">领域</span>
				</div>
				<div id="layer04_right_chart" class="layer04-panel-chart"></div>
			</div>
		</div>
	</div>
 </body>
</html>
