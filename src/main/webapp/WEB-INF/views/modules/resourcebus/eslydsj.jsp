<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页界面</title>
    <link rel="stylesheet" href="${ctxStatic}/eslydsj/css/base.css">
    <script type="text/javascript" src="${ctxStatic}/eslydsj/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${ctxStatic}/eslydsj/js/echarts.js"></script>
    <script type="text/javascript" src="${ctxStatic}/eslydsj/js/base.js"></script>
    <script type="text/javascript" src="${ctxStatic}/eslydsj/js/index.js"></script>
    <script type="text/javascript" src="${ctxStatic}/eslydsj/js/map.js"></script>
    <script type="text/javascript">
	$(function(){
		
		
		
		$.ajax({
	        type:'post',
	        async: false,
	        url:'${ctx}/equipment/equipmentbus/sbEquipmentBus/geChartData',
	        data:{'busTableType':'p'},
	        cache:false,
	        dataType:'json',
	        success:function(data){
	            setSz(data[0]);
	            char1(data[1]);
	            char2(data[3]);
	            char3(data[2]);
	            char4(data[5]);
	            initMap(data[4]);
	        }
	    });
		
		
		
	});
	
	function setSz(data){
	    var value = data[0] ;
	    $("#count").html(value.count);
	    $("#jl").html(value.jl);
	    $("#jwy").html(value.jwy);
	    $("#czh").html(value.czh);
	    $("#syl").html(value.syl);
	    $("#price").html(value.price);
	}
  </script>
</head>
<body>
<!--顶部-->
<header class="header left">
    <div class="left nav">
        <ul>
            <li class="nav_active"><i class="nav_1"></i><a href="${ctx}/resourcebus/tsResourceBus/gdzc">固定资产</a> </li>
			<li><i class="nav_7"></i><a href="${ctx}/resourcebus/tsResourceBus/xxwj">现行文件</a> </li>
			<li ><i class="nav_3"></i><a href="${ctx}/resourcebus/tsResourceBus/xxsb">信息化设备</a> </li>
            </ul>
    </div>
    <div class="header_center left">
        <h2><strong>固定资产驾驶舱</strong></h2>
        <p class="color_font"><small>Fixed assets cockpit</small></p>
    </div>
    <div class="right nav text_right">
        <ul> 
        </ul>
    </div>
</header>
<!--内容部分-->
<div class="con left">
    <!--选择时间-->
  
    <!--数据总概-->
    <div class="con_div">
        <div class="con_div_text left">
            <div class="con_div_text01 left">
                <img src="${ctxStatic}/eslydsj/img/title_8.png" class="left text01_img">
                <div class="left text01_div">
                    <p>仪器设备数量(个)</p>
                    <p id='count'></p>
                </div>
            </div>
            <div class="con_div_text01 right">
                <img src="${ctxStatic}/eslydsj/img/title_7.png" class="left text01_img">
                <div class="left text01_div">
                    <p>仪器价值(万元)</p>
                    <p id='price'></p>
                </div>
            </div>
        </div>
        <div class="con_div_text left">
            <div class="con_div_text01 left">
                <img src="${ctxStatic}/eslydsj/img/info_4.png" class="left text01_img">
                <div class="left text01_div">
                    <p>经纬仪(台)</p>
                    <p class="sky" id='jwy'></p>
                </div>
            </div>
            <div class="con_div_text01 right">
                <img src="${ctxStatic}/eslydsj/img/info_5.png" class="left text01_img">
                <div class="left text01_div">
                    <p>计量设备(台)</p>
                    <p class="sky" id='jl'></p>
                </div>
            </div>
        </div>
        <div class="con_div_text left">

            
            <div class="con_div_text01 left">
                <img src="${ctxStatic}/eslydsj/img/info_7.png" class="left text01_img">
                <div class="left text01_div">
                    <p>高价值设备(台)</p>
                    <p class="org" id='czh'></p>
                </div>
            </div>
            <div class="con_div_text01 right">
                <img src="${ctxStatic}/eslydsj/img/info_6.png" class="left text01_img">
                <div class="left text01_div">
                    <p>使用率(%)</p>
                    <p class="org" id='syl'></p>
                </div>
            </div>
            
        </div>
    </div>
    <!--统计分析图-->
    <div class="div_any">
        <div class="left div_any01">
            <div class="div_any_child">
                <div class="div_any_title"><img src="${ctxStatic}/eslydsj/img/nav_4.png">设备状态分类统计 </div>
                <p id="char1" class="p_chart"></p>
            </div>
            <div class="div_any_child">
                <div class="div_any_title"><img src="${ctxStatic}/eslydsj/img/title_2.png">设备资金来源统计 </div>
                <p id="char2" class="p_chart"></p>
            </div>
        </div>
        <div class="div_any02 left ">
            <div class="div_any_child div_height">
                <div class="div_any_title any_title_width"><img src="${ctxStatic}/eslydsj/img/title_3.png">设备功能分类统计 </div>
                <div id="map_div"></div>
            </div>
        </div>
        <div class="right div_any01">
            <div class="div_any_child">
                <div class="div_any_title"><img src="${ctxStatic}/eslydsj/img/title_4.png">各个部门设备统计 </div>
                <p id="char3" class="p_chart"></p>
            </div>
            <div class="div_any_child">
                <div class="div_any_title"><img src="${ctxStatic}/eslydsj/img/title_5.png">通用激光惯组测试台 </div>
                <p id="char4" class="p_chart"></p>
            </div>
        </div>
    </div>
    
</div>
</body>
</html>
