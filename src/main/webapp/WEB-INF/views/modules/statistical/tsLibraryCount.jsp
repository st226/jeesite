<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>温度管理</title>
</head>
<div id="d" style="width:800px; height: 470px;" ></div>

<body>
    <script type="text/javascript" src="${ctxStatic}/jquery/echarts.js"></script>
    <script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
    <script type="text/javascript">

   
    var axisData = ['2012年','2013年','2014年','2015年','2016年','2017年','2018年'];
    var data = axisData.map(function (item, i) {
        return 1000*(i+1) +Math.round(Math.random() * 1000 );
    });
    var links = data.map(function (item, i) {
        return {
            source: i,
            target: i + 1
        };
    });
    links.pop();
    option = {
        title: {
            text: '图书馆馆藏资源数量'
        },
        tooltip: {},
        xAxis: {
            type : 'category',
            boundaryGap : false,
            data : axisData
        },
        yAxis: {
            type : 'value'
        },
        series: [
            {
                type: 'graph',
                layout: 'none',
                coordinateSystem: 'cartesian2d',
                symbolSize: 40,
                label: {
                    normal: {
                        show: true
                    }
                },
                edgeSymbol: ['circle', 'arrow'],
                edgeSymbolSize: [4, 10],
                data: data,
                links: links,
                lineStyle: {
                    normal: {
                        color: '#2f4554'
                    }
                }
            }
        ]
    };
    
  	    //初始化echarts实例
    		  
    		    var myChart = echarts.init(document.getElementById('d'));
    		

    		    //使用制定的配置项和数据显示图表
    		    myChart.setOption(option);

       
    </script>
  
</body>
</html>