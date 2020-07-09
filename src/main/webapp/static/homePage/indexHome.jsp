<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- 
  - Author(s): hanxu
  - Date: 2018-08-03 17:07:23
  - Description:
-->
<head>
<title>Title</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />


<link rel="stylesheet" type="text/css" href="${ctxStatic}/bootstrap_admin/bootstrap/css/bootstrap.min.css">
<link type="text/css" href="${ctxStatic}/bootstrap_admin/css/theme.css" rel="stylesheet">
<link type="text/css" href="${ctxStatic}/bootstrap_admin/images/icons/css/font-awesome.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/images/metinfo.css" />
<link rel="stylesheet" href="${ctxStatic}/eslydsj/css/base.css">
<style>
</style>

</head>
<body>

      <header>
		<div class="inner"></div>
	</header>

	<div class="inner met_flash">
		<link href='images/css.css' rel='stylesheet' type='text/css' />
		
		<script src='images/jquery.bxSlider.min.js'></script>

		<script type='text/javascript'>
			$(document).ready(function() {
				$('#slider6').bxSlider({
					mode : 'vertical',
					autoHover : true,
					auto : true,
					pager : true,
					pause : 5000,
					controls : false
				});
			});
		</script>
	</div>


	<div class="index inner">


		<div class="aboutus style-1">
			<h3 class="title">
				<span class='myCorner' data-corner='top 5px'>待办任务1</span> <a target="_blank" onclick="myCorner()"  class="more" title="链接关键词">更多>></a>
			</h3>
			<div class="active clear listel contour-3" style="height: 210px;" id="datagrid2" >
				  
				<div class="clear"></div>
			</div>
		</div>

		<div class="case style-2">
			<h3 class='title myCorner' data-corner='top 5px'>
				<a href="" title="链接关键词" class="more">更多>></a>系统常用菜单
			</h3>
			<div class="active clear listel contour-3"  style="height: 210px;" id="datagrid4">
			      <div class="span8">
			      
                                        <div class="row-fluid">
                                            <div class="span12">
                                                <a href="<%=request.getContextPath() %>/point/record/RecordList.jsp" class="btn-box small span4"><i class="icon-font"></i><b>合理化建议申请</b>
                                                </a><a href="<%=request.getContextPath() %>/point/record/RecordList.jsp" class="btn-box small span4"><i class="icon-gift"></i><b>我的积分</b>
                                                </a><a href="<%=request.getContextPath() %>/point/record/RecordList.jsp" class="btn-box small span4"><i class="icon-exchange"></i><b>常用意见维护</b>
                                                </a>
                                                
                                            </div>
                                        </div>
                                        <div class="row-fluid">
                                            <div class="span12">
                                                <a href="<%=request.getContextPath() %>/point/record/RecordList.jsp" class="btn-box small span4"><i class="icon-comment"></i><b>建议查询</b>
                                                </a><a href="<%=request.getContextPath() %>/point/record/RecordList.jsp" class="btn-box small span4"><i class="icon-user"></i><b>非OA人员维护</b>
                                                </a><a href="<%=request.getContextPath() %>/point/record/RecordList.jsp" class="btn-box small span4"><i class="icon-qrcode"></i><b>我的建议
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
				<span class='myCorner' data-corner='top 5px'>公司新闻1</span> <a target="_blank" onclick="more()"  class="more" title="链接关键词">更多>></a>
			</h3>
			<div class="active clear listel contour-2"  id="datagrid1">
				<ol class='list-none metlist'>
					<li class='list top'><span class='time'></span><span class='time'>2012-07-17</span><a href='#'>如何选择网站关键词?</a></li>
					<li class='list '><span class='time'>2012-07-16</span><a href='#'>新手使用MetInfo建站步骤</a></li>
					<li class='list '><span class='time'>2012-07-16</span><a href='#'>企业网站应该多长时间备份一次？</a></li>
					
				</ol>
			</div>
		</div>

		<div class="index-news style-1">
			<h3 class="title">
				<span class='myCorner' data-corner='top 5px'>优秀建议</span><a target="_blank"  onclick="moreP()"  class="more" title="链接关键词">更多>></a>
			</h3>
			<div class="active clear listel contour-2"  id="datagridJY">
				
			</div>
		</div>
		
		
		<div class="index-conts style-2" id="moret" >
			<h3 class='title myCorner' data-corner='top 5px'>

				<a  target="_blank"  onclick="moret()"  title="链接关键词" class="more">更多>></a>建议占比
			</h3>
			<div class="active clear listel contour-2" style="height: 450px"> 
				<iframe src="<%=request.getContextPath()%>/count/pie2.jsp" frameborder="0" name="main" style="width:100%;height:100%;" border="0" ></iframe>
			</div>
		</div>
		
		
		<div class="clear p-line"></div>
          <div class="index-product style-2"  align="center">
		<h3 class='title myCorner' data-corner='top 5px'  align="center"></h3>
	
             </div>


		<footer data-module="10001" data-classnow="10001"> </footer>
		<script src="images/fun.inc.js" type="text/javascript"></script>








	<script type="text/javascript">
    	nui.parse();
    	getData();
    	
    	function dayt(createTime){
    

    	var nowDate = new Date();
         var start =  new Date(Date.parse(createTime.replace(/-/g, "/")))
          
          var days = 0 ;
          days = (nowDate.getTime()-start.getTime())/(1000*60*60*24);
          days = days +'';
          var day = days.split(".");
          return day[0]+'天';
             
    	}
    	function bu(str){
    	var len = str.length ;
    	for(var i=0 ;i<8-len ; i++ ){
    	   str = "&nbsp;&nbsp;"+str ;
    	}
    	  return str ;  
    	}
    	
    	
    	function getData(){
		     var Html = " <ol class='list-none metlist'>"; 
		     var HtmlDB = " <ol class='list-none metlist'>"; 
		     var HtmlJY = " <ol class='list-none metlist'>"; 
			$.ajax({
					url : "com.primeton.programa.oanotifyrecordbiz.queryOaFive.biz.ext",
					type : "post",
					async : false,
					success : function(text) {
						if(text.oanotifyrecords.length>0){
						for (var i = 0; i < text.oanotifyrecords.length; i++) {
							var now = new Date();
							var myDate = text.oanotifyrecords[i].creatDate;
							myDate.replace("/","-");
							var creat = new Date(myDate);
							var total = (now.getTime()-creat.getTime())/1000;
							var day = parseInt(total / (24*60*60));//计算整数天数
							if(day<4){
							      var tt = "<li class='list '><span class='time'>"+text.oanotifyrecords[i].creatDate+"&nbsp;&nbsp<img src='article/image/new.png' /></span><a href='javascript:get(\"" + text.oanotifyrecords[i].id + "\")'>"+text.oanotifyrecords[i].title+"</a></li>" ;
								Html += tt;
								
								}else{
									var tt = "<li class='list '><span class='time'>"+text.oanotifyrecords[i].creatDate+"</span><a href='javascript:get(\"" + text.oanotifyrecords[i].id + "\")'>"+text.oanotifyrecords[i].title+"</a></li>" ;
								Html += tt;
								
							}
						}
						
						}else{
							var sty = '<h3 style="text-align: center">该栏目没有文章</h3>' ;
							Html = sty;
						}
						Html +=  "</ol>" ;
						if(text.wfworkitemxs.length>0){
					      for (var i = 0; i < text.wfworkitemxs.length; i++) {
							      var tt = "<li class='list '><span class='time'>"+text.wfworkitemxs[i].workItemName+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+text.wfworkitemxs[i].createTime+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+bu(dayt(text.wfworkitemxs[i].createTime))+"</span><a href='javascript:open(\"" + text.wfworkitemxs[i].workItemID + "\")'>"+text.wfworkitemxs[i].processChName+"</a></li>" ;
								HtmlDB += tt;
						}
					          }else{
					
					             HtmlDB ='<h3 style="text-align: center">无代办任务</h3>' ;
					     }
					
						HtmlDB +=  "</ol>" ;
						
						if(text.proppsals.length>0){
					      for (var i = 0; i < text.proppsals.length; i++) {
							      var tt = "<li class='list '><span class='time'>"+text.proppsals[i].integral+"分</span><a href='javascript:openJY(\"" + text.proppsals[i].processdefid + "\")'>"+text.proppsals[i].proposalname+"</a></li>" ;
								HtmlJY += tt;
						}
					          }else{
					
					             HtmlJY ='<h3 style="text-align: center">无建议</h3>' ;
					     }
					
						HtmlJY +=  "</ol>" ;
						
						
						
					}
					
					
					
					
					
					
				});
				
				
				var HtmlCD = "<ol class='list-none metlist'><li class='list top'><span class='time'></span><span class='time'></span><a href='/default/proposal/reProposal/RpProposalForm.jsp'>合理化建议申请</a></li><li class='list '><span class='time'></span><a href='/default/proposal/rpUser/RpUserList.jsp'>非OA系统维护</a></li><li class='list '><span class='time'></span><a href='/default/proposal/rpPropose/RpProposeList.jsp'>常用意见维护</a></li></ol>"
				
				document.getElementById("datagrid1").innerHTML=Html ;
				document.getElementById("datagrid2").innerHTML=HtmlDB ;
				document.getElementById("datagridJY").innerHTML=HtmlJY ;
				
				var browser=navigator.appName 
                var b_version=navigator.appVersion 
                var version=b_version.split(";"); 
                var trim_Version=version[1].replace(/[ ]/g,""); 
                 if(browser=="Microsoft Internet Explorer" && (trim_Version=="MSIE6.0" ||trim_Version=="MSIE7.0")) 
                 {
                  document.getElementById("datagrid4").innerHTML=HtmlCD ;
                   document.getElementById("moret").style.display="none" ;
                 }
				
		
		}
		
		function myCorner(){
		   window.location.href= "<%=request.getContextPath()%>/tjtt/WFWorkItemListDB.jsp" ;
		
		}
		
		function moreP(){
		
		   window.location.href= "<%=request.getContextPath()%>/proposal/reProposal/FindProposalList.jsp" ;
		
		}
		
		function  moret(){
		   window.location.href= "<%=request.getContextPath()%>/count/count.jsp" ;
		}
		
		function get(id){
	          nui.open({
	            url: "<%=request.getContextPath()%>/programa/article/GetArticle.jsp",
						title : "查看全文",
						width : 1000,
						height : 550,
						onload : function() {
							var iframe = this.getIFrameEl();
							//直接从页面获取，不用去后台获取
							var row = {
								id : id
							}
							var data = {
								pageType : "edit",
								record : {
									oanotifyrecord : row
								}
							};
							iframe.contentWindow.setFormData(data);
						},
						ondestroy : function(action) {
						
						}
					});
		}
		
		function open(id){
      var url = "/default/gocom/cap/workflow/client/task/com.eos.workflow.executetask.executeTask.flow?workItemID="+id+"&loadRD=true" ;
        
      
         nui.open({
	    			url: url,
	    			title:"任务情况",
	    			width: "950px",
	    			showMaxButton: true,
	    			height: "500px",
	    			ondestroy: function (action) {  //弹出页面关闭前
	    			            
                                getData();
                                                
                            }
	    		});
        
          
        
        }
        
        
        function openJY(processdefid){
          nui.open({
					url : "<%=request.getContextPath()%>/proposal/reProposal/RpProcessView.jsp?id="+processdefid,
							title : "流程查看",
							width : 1100,
							height : 550,
							onload : function() {
								var iframe = this.getIFrameEl();
								var data = {
									pageType : "edit",
									processdefid:processdefid,
									record : {
										rpproposal : {processdefid:processdefid}
									}
								};
								//直接从页面获取，不用去后台获取
								iframe.contentWindow.editT(data);
							},
							ondestroy : function(action) {
							
							}
						});
        
        
        }
		
		function more(){
		
		
		nui.open({
	            url: "<%=request.getContextPath()%>/programa/moreArticles.jsp",
						title : "全部新闻",
						width : 1000,
						height : 500,
						onload : function() {
							
						},
						ondestroy : function(action) {
							
						}
					});
		
		}
    	
    	
    </script>
</body>
</html>