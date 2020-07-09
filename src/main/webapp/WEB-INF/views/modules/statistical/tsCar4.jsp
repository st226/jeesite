<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>温度管理</title>
</head>
<div id="d" style="width:1000px; height: 520px;" ></div>

<body>
    <script type="text/javascript" src="${ctxStatic}/jquery/echarts.js"></script>
    <script type="text/javascript" src="${ctxStatic}/jquery/china.js"></script>
    <script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
    <script type="text/javascript">
    var myChart = echarts.init(document.getElementById('d'));
    option = {
    		 title : {
     	        text: '二〇六所车辆任务统计',
     	        subtext: '纯属虚构'
     	    },
    	    tooltip : {
    	        trigger: 'axis',
    	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
    	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
    	        }
    	    },
    	    legend: {
    	        data:['车辆一','导弹','装载','货运','车辆二','机动','载人','演习','其他']
    	    },
    	    grid: {
    	        left: '3%',
    	        right: '4%',
    	        bottom: '3%',
    	        containLabel: true
    	    },
    	    xAxis : [
    	        {
    	            type : 'category',
    	            data : ['一月','二月','三月','四月','五月','六月']
    	        }
    	    ],
    	    yAxis : [
    	        {
    	            type : 'value'
    	        }
    	    ],
    	    series : [
    	        {
    	            name:'车辆一',
    	            type:'bar',
    	            data:[490, 546, 493, 522, 570, 890]
    	        },
    	        {
    	            name:'导弹',
    	            type:'bar',
    	            barWidth : 5,
    	            stack: '车辆一',
    	            data:[120, 132, 101, 134, 90, 230]
    	        },
    	        {
    	            name:'装载',
    	            type:'bar',
    	            stack: '车辆一',
    	            data:[220, 182, 191, 234, 290, 330]
    	        },
    	        {
    	            name:'货运',
    	            type:'bar',
    	            stack: '车辆一',
    	            data:[150, 232, 201, 154, 190, 330]
    	        },
    	        {
    	            name:'车辆二',
    	            type:'bar',
    	            data:[862, 1018, 964, 1026, 1679, 1600],
    	            markLine : {
    	                lineStyle: {
    	                    normal: {
    	                        type: 'dashed'
    	                    }
    	                },
    	                data : [
    	                    [{type : 'min'}, {type : 'max'}]
    	                ]
    	            }
    	        },
    	        {
    	            name:'机动',
    	            type:'bar',
    	            barWidth : 5,
    	            stack: '搜索引擎',
    	            data:[620, 732, 701, 734, 1090, 1130]
    	        },
    	        {
    	            name:'载人',
    	            type:'bar',
    	            stack: '搜索引擎',
    	            data:[120, 132, 101, 134, 290, 230]
    	        },
    	        {
    	            name:'演习',
    	            type:'bar',
    	            stack: '搜索引擎',
    	            data:[60, 72, 71, 74, 190, 130]
    	        },
    	        {
    	            name:'其他',
    	            type:'bar',
    	            stack: '搜索引擎',
    	            data:[62, 82, 91, 84, 109, 110]
    	        }
    	    ]
    	};

    
    
  	    //初始化echarts实例
    		  
    		    
    		

    		    //使用制定的配置项和数据显示图表
    		    myChart.setOption(option);

       
    </script>
  
</body>
</html>