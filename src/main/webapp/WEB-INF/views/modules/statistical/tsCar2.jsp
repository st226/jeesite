<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>温度管理</title>
</head>
<div id="d" style="width:400px; height: 380px;" ></div>

<body>
    <script type="text/javascript" src="${ctxStatic}/jquery/echarts.js"></script>
    <script type="text/javascript" src="${ctxStatic}/jquery/china.js"></script>
    <script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
    <script type="text/javascript">
    var myChart = echarts.init(document.getElementById('d'));
    var name_title = "二〇六所车辆分布图";
    	var subname = '数据虚构\n\n车辆超过一百辆点亮标识';
    	var nameColor = " rgb(55, 75, 113)";
    	var name_fontFamily = '等线';
    	var subname_fontSize = 15;
    	var name_fontSize = 18;
    	var mapName = 'beijing';
    	var data = [
    	    {name:"北京",value:177},
    	    {name:"天津",value:42},
    	    {name:"河北",value:102},
    	    {name:"山西",value:81},
    	    {name:"内蒙古",value:47},
    	    {name:"辽宁",value:67},
    	    {name:"吉林",value:82},
    	    {name:"黑龙江",value:66},
    	    {name:"上海",value:24},
    	    {name:"江苏",value:92},
    	    {name:"浙江",value:114},
    	    {name:"安徽",value:109},
    	    {name:"福建",value:116},
    	    {name:"江西",value:91},
    	    {name:"山东",value:119},
    	    {name:"河南",value:137},
    	    {name:"湖北",value:116},
    	    {name:"湖南",value:114},
    	    {name:"重庆",value:91},
    	    {name:"四川",value:125},
    	    {name:"贵州",value:62},
    	    {name:"云南",value:83},
    	    {name:"西藏",value:9},
    	    {name:"陕西",value:80},
    	    {name:"甘肃",value:56},
    	    {name:"青海",value:10},
    	    {name:"宁夏",value:18},
    	    {name:"新疆",value:67},
    	    {name:"广东",value:123},
    	    {name:"广西",value:59},
    	    {name:"海南",value:14},
    	    ];
    	    
    	var geoCoordMap = {};
    	var toolTipData = [ 
    	    {name:"北京",value:[{name:"大卡车",value:95},{name:"小卡车",value:82}]},
    	    {name:"天津",value:[{name:"大卡车",value:22},{name:"小卡车",value:20}]},
    	    {name:"河北",value:[{name:"大卡车",value:60},{name:"小卡车",value:42}]},
    	    {name:"山西",value:[{name:"大卡车",value:40},{name:"小卡车",value:41}]},
    	    {name:"内蒙古",value:[{name:"大卡车",value:23},{name:"小卡车",value:24}]},
    	    {name:"辽宁",value:[{name:"大卡车",value:39},{name:"小卡车",value:28}]},
    	    {name:"吉林",value:[{name:"大卡车",value:41},{name:"小卡车",value:41}]},
    	    {name:"黑龙江",value:[{name:"大卡车",value:35},{name:"小卡车",value:31}]},
    	    {name:"上海",value:[{name:"大卡车",value:12},{name:"小卡车",value:12}]},
    	    {name:"江苏",value:[{name:"大卡车",value:47},{name:"小卡车",value:45}]},
    	    {name:"浙江",value:[{name:"大卡车",value:57},{name:"小卡车",value:57}]},
    	    {name:"安徽",value:[{name:"大卡车",value:57},{name:"小卡车",value:52}]},
    	    {name:"福建",value:[{name:"大卡车",value:59},{name:"小卡车",value:57}]},
    	    {name:"江西",value:[{name:"大卡车",value:49},{name:"小卡车",value:42}]},
    	    {name:"山东",value:[{name:"大卡车",value:67},{name:"小卡车",value:52}]},
    	    {name:"河南",value:[{name:"大卡车",value:69},{name:"小卡车",value:68}]},
    	    {name:"湖北",value:[{name:"大卡车",value:60},{name:"小卡车",value:56}]},
    	    {name:"湖南",value:[{name:"大卡车",value:62},{name:"小卡车",value:52}]},
    	    {name:"重庆",value:[{name:"大卡车",value:47},{name:"小卡车",value:44}]},
    	    {name:"四川",value:[{name:"大卡车",value:65},{name:"小卡车",value:60}]},
    	    {name:"贵州",value:[{name:"大卡车",value:32},{name:"小卡车",value:30}]},
    	    {name:"云南",value:[{name:"大卡车",value:42},{name:"小卡车",value:41}]},
    	    {name:"西藏",value:[{name:"大卡车",value:5},{name:"小卡车",value:4}]},
    	    {name:"陕西",value:[{name:"大卡车",value:38},{name:"小卡车",value:42}]},
    	    {name:"甘肃",value:[{name:"大卡车",value:28},{name:"小卡车",value:28}]},
    	    {name:"青海",value:[{name:"大卡车",value:5},{name:"小卡车",value:5}]},
    	    {name:"宁夏",value:[{name:"大卡车",value:10},{name:"小卡车",value:8}]},
    	    {name:"新疆",value:[{name:"大卡车",value:36},{name:"小卡车",value:31}]},
    	    {name:"广东",value:[{name:"大卡车",value:63},{name:"小卡车",value:60}]},
    	    {name:"广西",value:[{name:"大卡车",value:29},{name:"小卡车",value:30}]},
    	    {name:"海南",value:[{name:"大卡车",value:8},{name:"小卡车",value:6}]},
    	];

    	/*获取地图数据*/
    	myChart.showLoading();
    	var mapFeatures = echarts.getMap(mapName).geoJson.features;
    	myChart.hideLoading();
    	mapFeatures.forEach(function(v) {
    	    // 地区名称
    	    var name = v.properties.name;
    	    // 地区经纬度
    	    geoCoordMap[name] = v.properties.cp;

    	});

    	// console.log("============geoCoordMap===================")
    	// console.log(geoCoordMap)
    	// console.log("================data======================")
    	console.log(data)
    	console.log(toolTipData)
    	var max = 480,
    	    min = 9; // todo 
    	var maxSize4Pin = 100,
    	    minSize4Pin = 20;

    	var convertData = function(data) {
    	    var res = [];
    	    for (var i = 0; i < data.length; i++) {
    	        var geoCoord = geoCoordMap[data[i].name];
    	        if (geoCoord) {
    	            res.push({
    	                name: data[i].name,
    	                value: geoCoord.concat(data[i].value),
    	            });
    	        }
    	    }
    	    return res;
    	};
    	option = {
    	   legend: {
                orient: 'vertical',
                left: 'left',
                data:['大卡车','小卡车']
            },
    	    tooltip: {
    	        trigger: 'item',
    	        formatter: function(params) {
    	            if (typeof(params.value)[2] == "undefined") {
    	                var toolTiphtml = ''
    	                for(var i = 0;i<toolTipData.length;i++){
    	                    if(params.name==toolTipData[i].name){
    	                        toolTiphtml += toolTipData[i].name+':<br>'
    	                        for(var j = 0;j<toolTipData[i].value.length;j++){
    	                            toolTiphtml+=toolTipData[i].value[j].name+':'+toolTipData[i].value[j].value+"<br>"
    	                        }
    	                    }
    	                }
    	                console.log(toolTiphtml)
    	                // console.log(convertData(data))
    	                return toolTiphtml;
    	            } else {
    	                var toolTiphtml = ''
    	                for(var i = 0;i<toolTipData.length;i++){
    	                    if(params.name==toolTipData[i].name){
    	                        toolTiphtml += toolTipData[i].name+':<br>'
    	                        for(var j = 0;j<toolTipData[i].value.length;j++){
    	                            toolTiphtml+=toolTipData[i].value[j].name+':'+toolTipData[i].value[j].value+"<br>"
    	                        }
    	                    }
    	                }
    	                console.log(toolTiphtml)
    	                // console.log(convertData(data))
    	                return toolTiphtml;
    	            }
    	        }
    	    },
    	    // legend: {
    	    //     orient: 'vertical',
    	    //     y: 'bottom',
    	    //     x: 'right',
    	    //     data: ['credit_pm2.5'],
    	    //     textStyle: {
    	    //         color: '#fff'
    	    //     }
    	    // },
    	    visualMap: {
    	        show: true,
    	        min: 0,
    	        max: 200,
    	        left: 'left',
    	        top: 'bottom',
    	        text: ['高', '低'], // 文本，默认为数值文本
    	        calculable: true,
    	        seriesIndex: [1],
    	        inRange: {
    	            // color: ['#3B5077', '#031525'] // 蓝黑
    	            // color: ['#ffc0cb', '#800080'] // 红紫
    	            // color: ['#3C3B3F', '#605C3C'] // 黑绿
    	            // color: ['#0f0c29', '#302b63', '#24243e'] // 黑紫黑
    	            // color: ['#23074d', '#cc5333'] // 紫红
    	            color: ['#00467F', '#A5CC82'] // 蓝绿
    	            // color: ['#1488CC', '#2B32B2'] // 浅蓝
    	            // color: ['#00467F', '#A5CC82'] // 蓝绿
    	            // color: ['#00467F', '#A5CC82'] // 蓝绿
    	            // color: ['#00467F', '#A5CC82'] // 蓝绿
    	            // color: ['#00467F', '#A5CC82'] // 蓝绿

    	        }
    	    },
    	    /*工具按钮组*/
    	    // toolbox: {
    	    //     show: true,
    	    //     orient: 'vertical',
    	    //     left: 'right',
    	    //     top: 'center',
    	    //     feature: {
    	    //         dataView: {
    	    //             readOnly: false
    	    //         },
    	    //         restore: {},
    	    //         saveAsImage: {}
    	    //     }
    	    // },
    	    geo: {
    	        show: true,
    	        map: mapName,
    	        label: {
    	            normal: {
    	                show: false
    	            },
    	            emphasis: {
    	                show: false,
    	            }
    	        },
    	        roam: true,
    	        itemStyle: {
    	            normal: {
    	                areaColor: '#031525',
    	                borderColor: '#3B5077',
    	            },
    	            emphasis: {
    	                areaColor: '#2B91B7',
    	            }
    	        }
    	    },
    	    series: [{
    	            name: '散点',
    	            type: 'scatter',
    	            coordinateSystem: 'geo',
    	            data: convertData(data),
    	            symbolSize: function(val) {
    	                return val[2] / 10;
    	            },
    	            label: {
    	                normal: {
    	                    formatter: '{b}',
    	                    position: 'right',
    	                    show: true
    	                },
    	                emphasis: {
    	                    show: true
    	                }
    	            },
    	            itemStyle: {
    	                normal: {
    	                    color: '#05C3F9'
    	                }
    	            }
    	        },
    	        {
    	            type: 'map',
    	            map: mapName,
    	            geoIndex: 0,
    	            aspectScale: 0.75, //长宽比
    	            showLegendSymbol: false, // 存在legend时显示
    	            label: {
    	                normal: {
    	                    show: true
    	                },
    	                emphasis: {
    	                    show: false,
    	                    textStyle: {
    	                        color: '#fff'
    	                    }
    	                }
    	            },
    	            roam: true,
    	            itemStyle: {
    	                normal: {
    	                    areaColor: '#031525',
    	                    borderColor: '#3B5077',
    	                },
    	                emphasis: {
    	                    areaColor: '#2B91B7'
    	                }
    	            },
    	            animation: false,
    	            data: data
    	        },
    	        {
    	            name: '点',
    	            type: 'scatter',
    	            coordinateSystem: 'geo',
    	            symbol: 'pin', //气泡
    	            symbolSize: function(val) {
    	                var a = (maxSize4Pin - minSize4Pin) / (max - min);
    	                var b = minSize4Pin - a * min;
    	                b = maxSize4Pin - a * max;
    	                return a * val[2] + b;
    	            },
    	            label: {
    	                normal: {
    	                    show: true,
    	                    textStyle: {
    	                        color: '#fff',
    	                        fontSize: 9,
    	                    }
    	                }
    	            },
    	            itemStyle: {
    	                normal: {
    	                    color: '#F62157', //标志颜色
    	                }
    	            },
    	            zlevel: 6,
    	            data: convertData(data),
    	        },
    	        {
    	            name: 'Top 5',
    	            type: 'effectScatter',
    	            coordinateSystem: 'geo',
    	            data: convertData(data.sort(function(a, b) {
    	                return b.value - a.value;
    	            }).slice(0, 5)),
    	            symbolSize: function(val) {
    	                return val[2] / 10;
    	            },
    	            showEffectOn: 'render',
    	            rippleEffect: {
    	                brushType: 'stroke'
    	            },
    	            hoverAnimation: true,
    	            label: {
    	                normal: {
    	                    formatter: '{b}',
    	                    position: 'right',
    	                    show: true
    	                }
    	            },
    	            itemStyle: {
    	                normal: {
    	                    color: 'yellow',
    	                    shadowBlur: 10,
    	                    shadowColor: 'yellow'
    	                }
    	            },
    	            zlevel: 1
    	        },

    	    ]
    	};

    
    
  	    //初始化echarts实例
    		  
    		    
    		

    		    //使用制定的配置项和数据显示图表
    		    myChart.setOption(option);

       
    </script>
  
</body>
</html>