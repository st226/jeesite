<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Title</title>
<meta name="decorator" content="default"/>


<link rel="stylesheet" type="text/css" href="${ctxStatic}/homePage/bootstrap_admin/bootstrap/css/bootstrap.min.css">
<link type="text/css" href="${ctxStatic}/homePage/bootstrap_admin/css/theme.css" rel="stylesheet">
<link type="text/css" href="${ctxStatic}/homePage/bootstrap_admin/images/icons/css/font-awesome.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/homePage/images/metinfo.css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/homePage/images/wheelmenu.css" />
<link href='${ctxStatic}/homePage/images/css.css' rel='stylesheet' type='text/css' />

<script type="text/javascript" src="${ctxStatic}/homePage/images/jquery.wheelmenu.js"></script>


<script type="text/javascript">
$(document).ready(function() {
	

	$(document).ready(function(){

		$(".wheel-button").wheelmenu({

    trigger: "hover",

    animation: "fly",

    animationSpeed: "fast"

  });

	});
	//$("#name").focus();
	$("#inputForm").validate({
		submitHandler: function(form){
			loading('正在提交，请稍等...');
			form.submit();
		},
		errorContainer: "#messageBox",
		errorPlacement: function(error, element) {
			$("#messageBox").text("输入有误，请先更正。");
			if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
				error.appendTo(element.parent().parent());
			} else {
				error.insertAfter(element);
			}
		}
	});
	
	getAct();
	
});
			
			function getAct(){
				 var Html = " <ol class='list-none metlist'>"; 
			     var HtmlDB = " <ol class='list-none metlist'>"; 
			     var HtmlJY = " <ol class='list-none metlist'>"; 
				 $.ajax({
		                type:'post',
		                url:'${ctx}/act/task/getSelectAct',
		                data:{'busTableType':''},
		                cache:false,
		                dataType:'json',
		                success:function(data){
		                	 if (data != null && data.length > 0) {
			                        for (var i = 0; (i<data.length&&i<6); i++) {
			                        	   var url = "${ctx}"+data[i].actionURL+"?processInstID="+data[i].processInstID+"&workItemID="+data[i].workItemID+"&activityDefID="+data[i].activityDefID+"&workItemName="+data[i].workItemName;
			                    
			                                var tt = "<li class='list '><span class='time'>"+data[i].processChName+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+data[i].createTime.substring(0, 4)+"-"+data[i].createTime.substring(4, 6)+"-"+data[i].createTime.substring(6, 8)+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>"+
			                                "<a href='javascript:open(\"" + url+ "\")'>"+data[i].workItemName+"</a></li>" ;
											HtmlDB += tt;


			                        }
			                    
		                   
		                    }else{
		                    	HtmlDB ='<h3 style="text-align: center">无代办任务</h3>' 
		                    	
		                    }
		                	 HtmlDB +=  "</ol>" ;
		                	 document.getElementById("datagrid2").innerHTML=HtmlDB ;
		                }
		            });
			}
			
			
			function open(e){
				top.$.jBox.open("iframe:"+e, "流程审批",$(top.document).width()-220,$(top.document).height()-110,{
					buttons:{"确定":true}, loaded:function(h){
						$(".jbox-content", top.document).css("overflow-y","hidden");
					}, closed:function (){
		      
						getAct();
					}
		             
				});
				
			}
	
		</script>
</head>
<body >



		<div class="aboutus style-1">
			<h3 class="title">
				<span class='myCorner' data-corner='top 5px'>待办任务</span> <a href="${ctx}/act/task/todo/"   class="more" title="链接关键词">更多>></a>
			</h3>
			<div class="active clear listel contour-3" style="height: 210px;" id="datagrid2" >
				  <ol class='list-none metlist'>

					
				</ol>
				<div class="clear"></div>
			</div>
		</div>

		<div class="case style-2">
			<h3 class='title myCorner' data-corner='top 5px'>
				<a href="" title="链接关键词" class="more">更多>></a>系统常用菜单
			</h3>
			<div class="active clear listel contour-3"  style="height: 210px;" id="datagrid4">
			      <div class="span8" style="margin:0 auto">
			      
                                        <div class="row-fluid">
                                            <div class="span12">
                                                <a href="${ctx}/archives/application/form" class="btn-box small span4"><i class="icon-font"></i><b>晒印申请</b>
                                                </a><a href="${ctx}/borrow/tsBorrow/borrow" class="btn-box small span4"><i class="icon-gift"></i><b>文档上传</b>
                                                </a><a href="${ctx}/borrow/tsBorrow/borrow" class="btn-box small span4"><i class="icon-exchange"></i><b>常用意见维护</b>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="row-fluid">
                                            <div class="span12">
                                                <a href="${ctx}/resourcebus/tsResourceBus/indexCX" class="btn-box small span4"><i class="icon-comment"></i><b>文档查询</b>
                                                </a><a href="${ctx}/oa/oaNotify/self" class="btn-box small span4"><i class="icon-user"></i><b>通知查询</b>
                                                </a><a href="${ctx}/task/tsTask/indexChart" class="btn-box small span4"><i class="icon-qrcode"></i><b>驾驶舱
                                                    </b> </a>
                                            </div>
                                        </div>
										
                                    </div>
              
				

				<div class="clear"></div>
			</div>
		</div>
		<div class="clear"></div>

		<div class="index-news style-1">
			<h3 class="title">
				<span class='myCorner' data-corner='top 5px'>质技部动态</span> <a target="_blank" onclick="more()"  class="more" title="链接关键词">更多>></a>
			</h3>
			<div class="active clear listel contour-2"  id="datagrid1" style="height: 400px">
				<ol class='list-none metlist'>
<li class='list top'><span class='time'>2019-11-27</span><a href='/jeesite/f/view-8-18.html' target="_Blank">质技部在线晒印申请上线</a></li>
</ol>
			</div>
		</div>

		<div class="index-news style-1" >
			<h3 class="title">
				<span class='myCorner' data-corner='top 5px'>通知通告</span><a target="_blank"  onclick="moreP()"  class="more" title="链接关键词">更多>></a>
			</h3>
			<div class="active clear listel contour-2"  id="datagridJY" style="height: 400px">
				<ol class='list-none metlist'>
<h3 style="text-align: center">暂无通知通告</h3></ol>
			</div>
		</div>
		
		
		<div class="index-conts style-2" id="moret" >
			<h3 class='title myCorner' data-corner='top 5px'>

				<a   href="${ctx}/calendar/sbCalendar/calendarInfo"     title="链接关键词" class="more">详情>></a>我的任务
			</h3>
			<div class="active clear listel contour-2" style="height: 400px"> 
				<iframe src="${ctx}/calendar/sbCalendar/calendar" frameborder="0" name="main" style="width:100%;height:100%;" border="0" ></iframe>
			</div>
		</div>
		
		
		<div class="clear p-line"></div>
          <div class="index-product style-2"  align="center">
		<h3 class='title myCorner' data-corner='top 5px'  align="center"></h3>
	
             </div>


      <a href="#wheel3" class="wheel-button nw">

		   <span>+</span>

		  </a>

      <ul id="wheel3" data-angle="NW" class="wheel">

        <li class="item"><a href="${ctx}/calendar/sbCalendar/calendarInfo"><i  class="icon-calendar"></i></a></li>

        <li class="item"><a href="${ctx}/act/task/todo/"><i class="icon-bell"></i></a></li>

        <li class="item"><a href="${ctx}/business/order/swOrder/"><i class="icon-shopping-cart"></i></a></li>

        <li class="item"><a href="#home"><i class="icon-envelope-alt"></i></a></li>

      </ul>
		<footer data-module="10001" data-classnow="10001"> </footer>








    	
</body>
</html>