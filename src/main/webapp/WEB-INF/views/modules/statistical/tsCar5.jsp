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
      	        text: '二〇六所车辆故障变化',
      	        subtext: '纯属虚构'
      	    },
    	    tooltip: {
    	        trigger: 'axis'
    	    },
    	    legend: {
    	        data:['车辆一','车辆二','车辆三','车辆四','车辆五']
    	    },
    	    grid: {
    	        left: '3%',
    	        right: '4%',
    	        bottom: '3%',
    	        containLabel: true
    	    },
    	    toolbox: {
    	        feature: {
    	            saveAsImage: {}
    	        }
    	    },
    	    xAxis: {
    	        type: 'category',
    	        boundaryGap: false,
    	        data: ['一年','两年','三年','四年','五年','六年','七年']
    	    },
    	    yAxis: {
    	        type: 'value'
    	    },
    	    series: [
    	        {
    	            name:'车辆一',
    	            type:'line',
    	            data:[14, 18, 24, 28, 34, 38, 44]
    	        },
    	        {
    	            name:'车辆二',
    	            type:'line',
    	            data:[52, 45, 32, 23, 15, 8, 2]
    	        },
    	        {
    	            name:'车辆三',
    	            type:'line',
    	            data:[8, 32, 45, 45, 60, 38, 70]
    	        },
    	        {
    	            name:'车辆四',
    	            type:'line',
    	            data:[4, 9, 13, 14, 9, 20, 23]
    	        },
    	        {
    	            name:'车辆六',
    	            type:'line',
    	            data:[20, 38, 47, 49, 50, 53, 56]
    	        }
    	    ]
    	};

    
    
  	    //初始化echarts实例
    		  
    		    
    		

    		    //使用制定的配置项和数据显示图表
    		    myChart.setOption(option);

       
    </script>
  
</body>
</html>