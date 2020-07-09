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
    	        text: '二〇六所车辆统计',
    	        subtext: '纯属虚构'
    	    },
    	    tooltip : {
    	        trigger: 'axis'
    	    },
    	    legend: {
    	        data:['机车一','机车二','机车三']
    	    },
    	    toolbox: {
    	        show : true,
    	        feature : {
    	            dataView : {show: true, readOnly: false},
    	            magicType : {show: true, type: ['line', 'bar']},
    	            restore : {show: true},
    	            saveAsImage : {show: true}
    	        }
    	    },
    	    calculable : true,
    	    xAxis : [
    	        {
    	            type : 'category',
    	            data : ['2015年','2016年','2017年','2018年']
    	        }
    	    ],
    	    yAxis : [
    	        {
    	            type : 'value'
    	        }
    	    ],
    	    series : [
    	        {
    	            name:'机车一',
    	            type:'bar',
    	            data:[5, 8, 10, 3],
    	            markPoint : {
    	                data : [
    	                    {type : 'max', name: '最大值'},
    	                    {type : 'min', name: '最小值'}
    	                ]
    	            },
    	            markLine : {
    	                data : [
    	                    {type : 'average', name: '平均值'}
    	                ]
    	            }
    	        },
    	        {
    	            name:'机车二',
    	            type:'bar',
    	            data:[8, 12, 15, 8],
    	            markPoint : {
    	                data : [
    	                    {name : '年最高', value : 182.2, xAxis: 7, yAxis: 183},
    	                    {name : '年最低', value : 2.3, xAxis: 11, yAxis: 3}
    	                ]
    	            },
    	            markLine : {
    	                data : [
    	                    {type : 'average', name : '平均值'}
    	                ]
    	            }
    	        },
    	        {
    	            name:'机车三',
    	            type:'bar',
    	            data:[3, 9, 13, 4],
    	            markPoint : {
    	                data : [
    	                    {name : '年最高', value : 13, xAxis: 7, yAxis: 13},
    	                    {name : '年最低', value : 2.3, xAxis: 11, yAxis: 3}
    	                ]
    	            },
    	            markLine : {
    	                data : [
    	                    {type : 'average', name : '平均值'}
    	                ]
    	            }
    	        }
    	    ]
    	};

    
    
  	    //初始化echarts实例
    		  
    		    
    		

    		    //使用制定的配置项和数据显示图表
    		    myChart.setOption(option);

       
    </script>
  
</body>
</html>