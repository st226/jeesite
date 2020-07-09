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
	        url:'${ctx}/infomation/infomation/sbInformation/geChartData',
	        data:{'busTableType':'p'},
	        cache:false,
	        dataType:'json',
	        success:function(data){
	            setSz(data[0]);
	            mj(data[1],'mj','设备密级');
	 
	            mj(data[2],'team','各个部门信息');
	     
	            mj(data[3],'use','设备使用信息');
	            mj(data[4],'type','设备分类');
	          //  char2(data[3]);
	         //   char3(data[2]);
	          //  char4(data[5]);
	         //   initMap(data[4]);
	        }
	    });
		
		
		
	});
	
	function setSz(data){
	    var value = data[0] ;
	    $("#count").html(value.count);
	    $("#useinfo").html(value.useinfo);
	    $("#zw").html(value.zw);
	    $("#bx").html(value.bx);
	    $("#sm").html(value.sm);
	    $("#fm").html(value.fm);
	}
	
	function kk(e){
		if(e=="1"){
			window.open("${ctx}/infomation/infomation/sbInformation/","_blank");    
		}
		if(e=="2"){
			window.open("${ctx}/infomation/infomation/sbInformation/?useinfo=1");    
		}
		if(e=="3"){
			window.open("${ctx}/infomation/infomation/sbInformation/list?sbType=8342203e39cf4a0db266e858db130d5b","_blank");    
		}
		if(e=="4"){
			window.open("${ctx}/infomation/infomation/sbInformation/list?sbType=89138855031646d488eaaef368657739","_blank");    
		}
		if(e=="5"){
			window.open("${ctx}/infomation/infomation/sbInformation/list?sbType=412f0231c27f4e68b266de7c958ed1a6","_blank");    
		}
		if(e=="6"){
			window.open("${ctx}/infomation/infomation/sbInformation/list?sbType=9fb092fe22eb4b618a455af79d6b8806","_blank");    
		}
	}
	
  </script>
</head>
<body>
<!--顶部-->
<header class="header left">
    <div class="left nav">
        <ul>
            <li ><i class="nav_1"></i><a href="${ctx}/resourcebus/tsResourceBus/gdzc">固定资产</a> </li>
			<li><i class="nav_7"></i><a href="${ctx}/resourcebus/tsResourceBus/xxwj">现行文件</a> </li>
			<li class="nav_active"><i class="nav_3"></i><a href="${ctx}/resourcebus/tsResourceBus/xxsb">信息化设备</a> </li>
            </ul>
    </div>
    <div class="header_center left">
        <h2><strong>信息化设备统计分析</strong></h2>
        <p class="color_font"><small>Information equipment cockpit</small></p>
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
            <div class="con_div_text01 left" onclick="kk('1')">
                <img src="${ctxStatic}/eslydsj/img/title_8.png" class="left text01_img">
                <div class="left text01_div">
                    <p>设备总数(台)</p>
                    <p id='count'></p>
                </div>
            </div>
            <div class="con_div_text01 right"  onclick="kk('2')">
                <img src="${ctxStatic}/eslydsj/img/info_2.png" class="left text01_img">
                <div class="left text01_div">
                    <p>在用设备(台)</p>
                    <p id='useinfo'></p>
                </div>
            </div>
        </div>
        <div class="con_div_text left">
            <div class="con_div_text01 left"  onclick="kk('3')">
                <img src="${ctxStatic}/eslydsj/img/screen.png" class="left text01_img">
                <div class="left text01_div">
                    <p>内网计算机(台)</p>
                    <p class="sky" id='zw'></p>
                </div>
            </div>
            <div class="con_div_text01 right"  onclick="kk('4')">
                <img src="${ctxStatic}/eslydsj/img/screen.png" class="left text01_img">
                <div class="left text01_div">
                    <p>便携计算机(台)</p>
                    <p class="sky" id='bx'></p>
                </div>
            </div>
        </div>
        <div class="con_div_text left">

            
            <div class="con_div_text01 left"  onclick="kk('5')">
                <img src="${ctxStatic}/eslydsj/img/info_7.png" class="left text01_img">
                <div class="left text01_div">
                    <p>涉密设备(台)</p>
                    <p class="org" id='sm'></p>
                </div>
            </div>
            <div class="con_div_text01 right"  onclick="kk('6')">
                <img src="${ctxStatic}/eslydsj/img/info_5.png" class="left text01_img">
                <div class="left text01_div">
                    <p>非密设备(台)</p>
                    <p class="org" id='fm'></p>
                </div>
            </div>
            
        </div>
    </div>
   <!--统计分析图-->
    <div class="div_any">
        <div class="left div_any03">
            <div class="div_any_child01 left">
                <div class="div_any_title"><img src="${ctxStatic}/eslydsj/img/title_5.png">设备密级统计 </div>
                <p id="mj" class="p_chart"></p>
                

            </div>
            <div class="div_any_child01 left">
                <div class="div_any_title"><img src="${ctxStatic}/eslydsj/img/title_2.png">部门设备统计 </div>
                <p id="team" class="p_chart"></p>
                
            </div>
        </div>
         <div class="left div_any03">
            <div class="div_any_child01 left">
                <div class="div_any_title"><img src="${ctxStatic}/eslydsj/img/title_4.png">使用情况统计 </div>
                <p id="use" class="p_chart"></p>
              
            </div>
            <div class="div_any_child01 left">
                <div class="div_any_title"><img src="${ctxStatic}/eslydsj/img//title_5.png">设备类别统计 </div>
                <p id="type" class="p_chart"></p>
               
            </div>
        </div>
       
       
    

       
    </div>
</body>
</html>
