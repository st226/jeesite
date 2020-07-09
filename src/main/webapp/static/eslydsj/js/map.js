

//地图界面高度设置



//加载地图
function initMap(data){
// 百度地图API功能

   var myChart = echarts.init($("#map_div")[0]);

    option = {
        legend: {
            textStyle : {
                color : '#ffffff',
			}
		},
		
        tooltip: {
            trigger: 'axis',
            showContent: false
        },
        dataset: {
            source: [
                ['设备类别', '2019'],
                [data[0].name,   data[0].value],
                [data[1].name,   data[1].value],
                [data[2].name,   data[2].value],
                [data[3].name,   data[3].value],
                [data[4].name,   data[4].value],
                [data[5].name,   data[5].value],
                [data[6].name,   data[6].value],
                [data[7].name,   data[7].value]
            ]
        },
        xAxis: {type: 'category',
		        axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                }
				},
        yAxis: {gridIndex: 0,
		        axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                }
		  },
        grid: {top: '55%'},
        series: [
            {type: 'line', smooth: true, seriesLayoutBy: 'row'},
            {type: 'line', smooth: true, seriesLayoutBy: 'row'},
            {type: 'line', smooth: true, seriesLayoutBy: 'row'},
            {type: 'line', smooth: true, seriesLayoutBy: 'row'},
            {type: 'line', smooth: true, seriesLayoutBy: 'row'},
            {type: 'line', smooth: true, seriesLayoutBy: 'row'},
            {type: 'line', smooth: true, seriesLayoutBy: 'row'},
            {type: 'line', smooth: true, seriesLayoutBy: 'row'},
            {
                type: 'pie',
                id: 'pie',
                radius: '30%',
                center: ['50%', '25%'],
                label: {
                    formatter: '{b}: {@2012} ({d}%)'
                },
                encode: {
                    itemName: '设备类别',
                    value: '2019',
                    tooltip: '2019'
                }
            }
        ]
    };

    myChart.on('updateAxisPointer', function (event) {
        var xAxisInfo = event.axesInfo[0];
        if (xAxisInfo) {
            var dimension = xAxisInfo.value + 1;
            myChart.setOption({
				legend: {
            textStyle : {
                color : '#ffffff',

            }
        },
                series: {
                    id: 'pie',
                    label: {
                        formatter: '{b}: {@[' + dimension + ']} ({d}%)'
                    },
                    encode: {
                        value: dimension,
                        tooltip: dimension
                    }
                }
            });
        }
    });



    myChart.setOption(option);
    window.addEventListener('resize', function () {myChart.resize();})
}

