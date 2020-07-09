/**
 * Created by 30947 on 2018/7/18.
 */
$(function(){

   // char1();
   // char2();
  //  char3();
  //  char4();

})

//统计分析图
function char1(data) {
	var name = new Array(data.length);
	 var p1 = new Array(data.length);

	 
	 for(var i = 0 ; i <data.length ;i++){
	    name[i] = data[i].name ;
	    
	 }
    var myChart = echarts.init($("#char1")[0]);

    option = {
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'right',
            textStyle : {
                color : '#ffffff',

            },
            data:name
            },

        calculable : false,
        series : [
            {
                name:'设备状态分类',
                type:'pie',
                radius : ['40%', '70%'],
                itemStyle : {
                    normal : {
                        label : {
                            show : false
                        },
                        labelLine : {
                            show : false
                        }
                    },
                    emphasis : {
                        label : {
                            show : true,
                            position : 'center',
                            textStyle : {
                                fontSize : '20',
                                fontWeight : 'bold'
                            }
                        }
                    }
                },
                data:data
            }
        ]
    };

    myChart.setOption(option);
    window.addEventListener('resize', function () {myChart.resize();})

}
function char2(data) {
	var name = new Array(data.length);
	 var p1 = new Array(data.length);

	 
	 for(var i = 0 ; i <data.length ;i++){
	    name[i] = data[i].name ;
	    p1[i] = data[i].value ;
	    
	 }

    var myChart = echarts.init($("#char2")[0]);

    option = {
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {show:'true',borderWidth:'0'},
        legend: {
            data:name,
            textStyle : {
                color : '#ffffff',

            }
        },

        calculable :false,
        xAxis : [
            {
            	 type : 'category',
            	 data : name,
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    },
            rotate:45,
			interval:0
                },
                splitLine:{
                    lineStyle:{
                        color:['#f2f2f2'],
                        width:0,
                        type:'solid'
                    }
                }

            }
        ],
        yAxis : [
            {   
            	type : 'value',
               
               
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                splitLine:{
                    lineStyle:{
                        width:0,
                        type:'solid'
                    }
                }
            }
        ],
        series : [
            {
                name:'行驶',
                type:'bar',
                stack: '总量',
                itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
                data:p1
            }
           

        ]
    };

    myChart.setOption(option);
    window.addEventListener('resize', function () {myChart.resize();})

}
function char3(data) {

    var myChart = echarts.init($("#char3")[0]);

    option = {
     calculable : false,
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
 
    series : [
        {
            name: '部门资产',
            type: 'pie',
            radius : '55%',
            center: ['50%', '50%'],
            data:data,
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};

    myChart.setOption(option);
    window.addEventListener('resize', function () {myChart.resize();})

}
function char4(data) {

    var name = new Array(data.length);
	 var p1 = new Array(data.length);

	 
	 for(var i = 0 ; i <data.length ;i++){
	    name[i] = data[i].name ;
	    
	 }
	
   var myChart = echarts.init($("#char4")[0]);

   option = {
       tooltip : {
           trigger: 'item',
           formatter: "{a} <br/>{b} : {c} ({d}%)"
       },
       legend: {
           orient : 'vertical',
           x : 'right',
           textStyle : {
               color : '#ffffff',

           },
           data:name
           },

       calculable : false,
       series : [
           {
               name:'设备状态分类',
               type:'pie',
               radius : ['40%', '70%'],
               itemStyle : {
                   normal : {
                       label : {
                           show : false
                       },
                       labelLine : {
                           show : false
                       }
                   },
                   emphasis : {
                       label : {
                           show : true,
                           position : 'center',
                           textStyle : {
                               fontSize : '20',
                               fontWeight : 'bold'
                           }
                       }
                   }
               },
               data:data
           }
       ]
   };

   myChart.setOption(option);
   window.addEventListener('resize', function () {myChart.resize();})
}

//新首页
//统计分析图
function basicflot(data) {
	var name = new Array(data.length);
	 var p1 = new Array(data.length);

	 
	 for(var i = 0 ; i <data.length ;i++){
	    name[i] = data[i].name ;
	    
	 }
    var myChart = echarts.init($("#basicflot")[0]);

    var option = {
    	    xAxis: {
    	        type: 'category',
    	        boundaryGap: false,
    	        data: ['2019-07', '2019-08', '2019-09', '2019-10', '2019-11', '2019-12', '2020-01']
    	    },
    	    yAxis: {
    	        type: 'value'
    	    },
    	    series: [{
    	        data: [80, 80, 80, 93, 69, 69, 56],
    	        type: 'line',
    	        areaStyle: {}
    	    }]
    	};

    myChart.setOption(option);
    window.addEventListener('resize', function () {myChart.resize();})

}

function donut(data) {
	var name = new Array(data.length);
	 var p1 = new Array(data.length);

	 
	 for(var i = 0 ; i <data.length ;i++){
	    name[i] = data[i].name ;
	    
	 }
    var myChart = echarts.init($("#donut-chart2")[0]);

    var option = {
    	    tooltip: {
    	        trigger: 'item',
    	        formatter: '{a} <br/>{b}: {c} ({d}%)'
    	    },
    	    
    	    series: [
    	        {
    	            name: '访问来源',
    	            type: 'pie',
    	            radius: ['50%', '70%'],
    	            avoidLabelOverlap: false,
    	            label: {
    	                normal: {
    	                    show: false,
    	                    position: 'center'
    	                },
    	                emphasis: {
    	                    show: true,
    	                    textStyle: {
    	                        fontSize: '30',
    	                        fontWeight: 'bold'
    	                    }
    	                }
    	            },
    	            labelLine: {
    	                normal: {
    	                    show: false
    	                }
    	            },
    	            data: [
    	                {value: 335, name: '直接访问'},
    	                {value: 310, name: '邮件营销'},
    	                {value: 234, name: '联盟广告'},
    	                {value: 135, name: '视频广告'},
    	                {value: 1548, name: '搜索引擎'}
    	            ]
    	        }
    	    ]
    	};

    myChart.setOption(option);
    window.addEventListener('resize', function () {myChart.resize();})

}

function linechart(data){
	var name = new Array(data.length);
	 var p1 = new Array(data.length);

	 
	 for(var i = 0 ; i <data.length ;i++){
	    name[i] = data[i].name ;
	    
	 }
   var myChart = echarts.init($("#line-chart")[0]);
   
   var option = {
		    tooltip: {
		        trigger: 'axis'
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
		        data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
		    },
		    yAxis: {
		        type: 'value'
		    },
		    series: [
		        {
		            name: '合同签订',
		            type: 'line',
		            stack: '总量',
		            data: [120, 132, 101, 134, 90, 230, 210]
		        }
		    ]
		};
	
   myChart.setOption(option);
   window.addEventListener('resize', function () {myChart.resize();})

	
}
function linechart2(data){
	var name = new Array(data.length);
	 var p1 = new Array(data.length);

	 
	 for(var i = 0 ; i <data.length ;i++){
	    name[i] = data[i].name ;
	    
	 }
   var myChart = echarts.init($("#line-chart2")[0]);
   
   var option = {
		    title: {
		        text: '堆叠区域图'
		    },
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'cross',
		            label: {
		                backgroundColor: '#6a7985'
		            }
		        }
		    },
		    legend: {
		        data: ['兰图晒图', '三单晒印']
		    },
		    toolbox: {
		        feature: {
		            saveAsImage: {}
		        }
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis: [
		        {
		            type: 'category',
		            boundaryGap: false,
		            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
		        }
		    ],
		    yAxis: [
		        {
		            type: 'value'
		        }
		    ],
		    series: [
		        {
		            name: '蓝图晒图',
		            type: 'line',
		            stack: '总量',
		            areaStyle: {},
		            data: [120, 132, 101, 134, 90, 230, 210]
		        },
		        {
		            name: '三单晒印',
		            type: 'line',
		            stack: '总量',
		            areaStyle: {},
		            data: [220, 182, 191, 234, 290, 330, 310]
		        }
		    ]
		};
	
   myChart.setOption(option);
   window.addEventListener('resize', function () {myChart.resize();})

	
}

//统计分析图
function mj(data,title,remark) {
	var name = new Array(data.length);
	 var p1 = new Array(data.length);

	 
	 for(var i = 0 ; i <data.length ;i++){
	    name[i] = data[i].name ;
	    
	 }
   var myChart = echarts.init($("#"+title)[0]);

   option = {
       tooltip : {
           trigger: 'item',
           formatter: "{a} <br/>{b} : {c} ({d}%)"
       },
       legend: {
           orient : 'vertical',
           x : 'right',
           textStyle : {
               color : '#ffffff',

           },
           data:name
           },

       calculable : false,
       series : [
           {
               name:remark,
               type:'pie',
               radius : ['40%', '70%'],
               itemStyle : {
                   normal : {
                       label : {
                           show : false
                       },
                       labelLine : {
                           show : false
                       }
                   },
                   emphasis : {
                       label : {
                           show : true,
                           position : 'center',
                           textStyle : {
                               fontSize : '20',
                               fontWeight : 'bold'
                           }
                       }
                   }
               },
               data:data
           }
       ]
   };

   myChart.setOption(option);
   window.addEventListener('resize', function () {myChart.resize();})

}
