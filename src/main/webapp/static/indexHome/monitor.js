function drawLayer02Label(canvasObj,text,textBeginX,lineEndX){
	var colorValue = '#04918B';

	var ctx = canvasObj.getContext("2d");

	ctx.beginPath();
	ctx.arc(35,55,2,0,2*Math.PI);
	ctx.closePath();
	ctx.fillStyle = colorValue;
	ctx.fill();

	ctx.moveTo(35,55);
	ctx.lineTo(60,80);
	ctx.lineTo(lineEndX,80);
	ctx.lineWidth = 1;
	ctx.strokeStyle = colorValue;
	ctx.stroke();

	ctx.font='12px Georgia';
	ctx.fillStyle = colorValue;
	ctx.fillText(text,textBeginX,92);
}

//接入机型占比

var COLOR = {
	MACHINE:{
		TYPE_A:'#0175EE',
		TYPE_B:'#D89446',
		TYPE_C:'#373693',
		TYPE_D:'#25AE4F',
		TYPE_E:'#06B5C6',
		TYPE_F:'#009E9A',
		TYPE_G:'#AC266F',
                TYPE_H:'#A4566F',
                TYPE_I:'#AC789F'
	}
};

function renderLegend(data){
	
	drawLegend(COLOR.MACHINE.TYPE_A,25,data[0].office_name);
	drawLegend(COLOR.MACHINE.TYPE_B,50,data[1].office_name);
	drawLegend(COLOR.MACHINE.TYPE_C,75,data[2].office_name);
	drawLegend(COLOR.MACHINE.TYPE_D,100,data[3].office_name);
	drawLegend(COLOR.MACHINE.TYPE_E,125,data[4].office_name);
	drawLegend(COLOR.MACHINE.TYPE_F,150,data[5].office_name);
	drawLegend(COLOR.MACHINE.TYPE_G,175,data[6].office_name);
        drawLegend(COLOR.MACHINE.TYPE_H,200,data[7].office_name);
        drawLegend(COLOR.MACHINE.TYPE_I,225,data[8].office_name);
}

function drawLegend(pointColor,pointY,text){
	var ctx = $("#layer03_left_01 canvas").get(0).getContext("2d");
	ctx.beginPath();
	ctx.arc(20,pointY,6,0,2*Math.PI);
	ctx.fillStyle = pointColor;
	ctx.fill();
	ctx.font='20px';
	ctx.fillStyle = '#FEFFFE';
	ctx.fillText(text,40,pointY+3);
}


//存储
function renderLayer03Right(data){
	
	drawLayer03Right($("#layer03_right_chart01 canvas").get(0),"#027825",(data[0].count2/data[0].count).toFixed(2));
	drawLayer03Right($("#layer03_right_chart02 canvas").get(0),"#006DD6",(data[0].count3/data[0].count).toFixed(2));
	drawLayer03Right($("#layer03_right_chart03 canvas").get(0),"#238681",(data[0].count4/data[0].count).toFixed(2));
}

function drawLayer03Right(canvasObj,colorValue,rate){
	var ctx = canvasObj.getContext("2d");
    
	var circle = {
        x : 65,    //圆心的x轴坐标值
        y : 80,    //圆心的y轴坐标值
        r : 60      //圆的半径
    };

	//画扇形
	//ctx.sector(circle.x,circle.y,circle.r,1.5*Math.PI,(1.5+rate*2)*Math.PI);
	//ctx.fillStyle = colorValue;
	//ctx.fill();

	ctx.beginPath();
	ctx.arc(circle.x,circle.y,circle.r,0,Math.PI*2)
	ctx.lineWidth = 10;
	ctx.strokeStyle = '#052639';
	ctx.stroke();
	ctx.closePath();

	ctx.beginPath();
	ctx.arc(circle.x,circle.y,circle.r,1.5*Math.PI,(1.5+rate*2)*Math.PI)
	ctx.lineWidth = 10;
	ctx.lineCap = 'round';
	ctx.strokeStyle = colorValue;
	ctx.stroke();
	ctx.closePath();
    
	ctx.fillStyle = 'white';
	ctx.font = '20px Calibri';
	ctx.fillText(rate*100+'%',circle.x-15,circle.y+10);

}


function renderChartBar01(data){
	var myChart = echarts.init(document.getElementById("layer03_left_02"));
		myChart.setOption(
					 {
						title : {
							text: '',
							subtext: '',
							x:'center'
						},
						tooltip : {
							trigger: 'item',
							formatter: "{b} : {c} ({d}%)"
						},
						legend: {
							show:false,
							x : 'center',
							y : 'bottom',
							data:[data[0].office_name,data[1].office_name,data[2].office_name,data[3].office_name]
						},
						toolbox: {
						},
						label:{
							normal:{
								show: true, 
								formatter: "{b} \n{d}%"
							} 
						},
						calculable : true,
						color:[COLOR.MACHINE.TYPE_A,COLOR.MACHINE.TYPE_B,COLOR.MACHINE.TYPE_C,COLOR.MACHINE.TYPE_D,COLOR.MACHINE.TYPE_E,COLOR.MACHINE.TYPE_F,COLOR.MACHINE.TYPE_G,COLOR.MACHINE.TYPE_H,COLOR.MACHINE.TYPE_I,],
						series : [
							{
								name:'',
								type:'pie',
								radius : [40, 80],
								center : ['50%', '50%'],
								//roseType : 'area',
								data:[
									{value:data[0].count, name:data[0].office_name},
									{value:data[1].count, name:data[1].office_name},
									{value:data[2].count, name:data[2].office_name},
									{value:data[3].count, name:data[3].office_name},
                                                                        {value:data[4].count, name:data[4].office_name},
									{value:data[5].count, name:data[5].office_name},
									{value:data[6].count, name:data[6].office_name},
{value:data[7].count, name:data[7].office_name},
{value:data[8].count, name:data[8].office_name},
									
								]
							}
						]
					}
		);

}

/*
function renderChartBar02(){
	var myChart = echarts.init(document.getElementById("layer03_left_03"));
		myChart.setOption(
					{
						title : {
							text: '',
							subtext: '',
							x:'center'
						},
						tooltip : {
							show:true,
							trigger: 'item',
							formatter: "上线率<br>{b} : {c} ({d}%)"
						},
						legend: {
							show:false,
							orient: 'vertical',
							left: 'left',
							data: ['A机型','B机型','C机型','D机型','E机型','F机型','G机型']
						},
						series : [
							{
								name: '',
								type: 'pie',
								radius : '50%',
								center: ['50%', '60%'],
								data:[
									{value:7600, name:'A机型'},
									{value:6600, name:'B机型'},
									{value:15600, name:'C机型'},
									{value:5700, name:'D机型'},
									{value:4600, name:'E机型'},
									{value:4600, name:'F机型'},
									{value:3500, name:'G机型'}
								],
								itemStyle: {
									emphasis: {
										shadowBlur: 10,
										shadowOffsetX: 0,
										shadowColor: 'rgba(0, 0, 0, 0.5)'
									}
								}
							}
						],
						color:[COLOR.MACHINE.TYPE_A,COLOR.MACHINE.TYPE_B,COLOR.MACHINE.TYPE_C,COLOR.MACHINE.TYPE_D,COLOR.MACHINE.TYPE_E,COLOR.MACHINE.TYPE_F,COLOR.MACHINE.TYPE_G]
					}
		);
}*/

function renderLayer04Left(data){
	
	var name = new Array(data.length);
	 var p1 = new Array(data.length);

	 
	 for(var i = 0 ; i <data.length ;i++){
	    name[i] = data[i].time ;
	    p1[i] = data[i].count ;
	    
	 }
	
	var myChart = echarts.init(document.getElementById("layer04_left_chart"));
	myChart.setOption(
		{
			title: {
				text: ''
			},
			tooltip : {
				trigger: 'axis'
			},
			legend: {
				data:[]
			},
			grid: {
				left: '3%',
				right: '4%',
				bottom: '5%',
				top:'4%',
				containLabel: true
			},
			xAxis :
			{
				type : 'category',
				boundaryGap : false,
				data : name,
				axisLabel:{
					textStyle:{
						color:"white", //刻度颜色
						fontSize:8  //刻度大小
					},
					rotate:45,
					interval:0
				},
				axisTick:{show:false},
				axisLine:{
					show:true,
					lineStyle:{
						color: '#0B3148',
						width: 1,
						type: 'solid'
					}
				}
			},
			yAxis : 
			{
				type : 'value',
				axisTick:{show:false},
				axisLabel:{
					textStyle:{
						color:"white", //刻度颜色
						fontSize:8  //刻度大小
						}
				},
				axisLine:{
					show:true,
					lineStyle:{
						color: '#0B3148',
						width: 1,
						type: 'solid'
					}
				},
				splitLine:{
					show:false
				}
			},
			tooltip:{
				formatter:'{c}',
				backgroundColor:'#FE8501'
			},
			series : [
				{
					name:'',
					type:'line',
					smooth:true,
					areaStyle:{
						normal:{
							color:new echarts.graphic.LinearGradient(0, 0, 0, 1, [{offset: 0, color: '#026B6F'}, {offset: 1, color: '#012138' }], false),
							opacity:0.2
						}
					},
					itemStyle : {  
                            normal : {  
                                  color:'#009991'
                            },
							lineStyle:{
								normal:{
								color:'#009895',
								opacity:1
							}
						}
                    },
					symbol:'none',
					data:p1
				}
			]
		}
	
	);
}

function renderLayer04Right(data){
	
	var name = new Array(data.length);
	 var p1 = new Array(data.length);

	 
	 for(var i = 0 ; i <data.length ;i++){
	    name[i] = data[i].name ;
	    p1[i] = data[i].count ;
	    
	 }
	
	var myChart = echarts.init(document.getElementById("layer04_right_chart"));
	myChart.setOption(
			{
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataView : {show: false, readOnly: false},
			            magicType : {show: false, type: ['line', 'bar', 'stack', 'tiled']}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			        	type : 'category',
			            data : name,
			            axisLabel:{
							textStyle:{
								color:"white", //刻度颜色
								fontSize:8  //刻度大小
							},
							rotate:25,
							interval:0
						}
			        }
			        
			    ],
			    yAxis : [
			        {
			        	type : 'value',
			        	axisLabel:{
							textStyle:{
								color:"white", //刻度颜色
								fontSize:8  //刻度大小
							},
							rotate:45,
							interval:2
						},
			            
			        }
			    ],
			    series : [
			        {
			 
			            type:'bar',
			            stack: '总量',
			            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
			            data:p1
			        }
			    ]
			})

}

function get10MinutesScale()
{
	var currDate = new Date();
	var odd = currDate.getMinutes()%10;
	var returnArr = new Array();
	currDate.setMinutes(currDate.getMinutes()-odd);
	for(var i = 0; i <7; i++){
		returnArr.push(currDate.getHours()+":"+(currDate.getMinutes()<10?("0"+currDate.getMinutes()):currDate.getMinutes()));
		currDate.setMinutes(currDate.getMinutes()-10);
	}
	return returnArr;
}


function getLatestDays(num)
{
	var currentDay = new Date();
	var returnDays = [];
	for (var i = 0 ; i < num ; i++)
	{
		currentDay.setDate(currentDay.getDate() - 1);
		returnDays.push((currentDay.getMonth()+1)+"/"+currentDay.getDate());
	}
	return returnDays;
}