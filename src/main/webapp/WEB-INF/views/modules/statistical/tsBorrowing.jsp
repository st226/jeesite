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

   

    option = {
    		title: {
                text: '各部门上传文档统计'
            },
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data: ['合同文档', '海军','空军','通用','质量文件']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis:  {
                type: 'value'
            },
            yAxis: {
                type: 'category',
                data: ['质量技术部','设计一部','设计二部','设计三部','一车间','二车间','三车间']
            },
            series: [
                {
                    name: '合同文档',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight'
                        }
                    },
                    data: [420, 302, 301, 334, 390, 330, 320]
                },
                {
                    name: '海军',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight'
                        }
                    },
                    data: [220, 132, 101, 134, 90, 230, 210]
                },
                {
                    name: '空军',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight'
                        }
                    },
                    data: [320, 182, 191, 234, 290, 330, 310]
                },
                {
                    name: '通用',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight'
                        }
                    },
                    data: [350, 212, 201, 154, 190, 330, 410]
                },
                {
                    name: '质量文件',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight'
                        }
                    },
                    data: [1520, 832, 901, 934, 1290, 1330, 1320]
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