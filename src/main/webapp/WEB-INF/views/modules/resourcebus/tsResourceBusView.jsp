<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>现行文件管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
			
		//	showSelectedData();
			var li, urls = $("#files").val().split("|");
			$("#filesPreview").children().remove();
			for (var i=0; i<urls.length; i++){
				if (urls[i]!=""){//
					if(browserIsIe()){
					  li = "<li><a href='' onclick='download_pic(\""+urls[i]+"\")'   >"+decodeURIComponent(urls[i].substring(urls[i].lastIndexOf("/")+1))+"</a>";//
					}else{  
					 
					  li = "<li><a href=\""+urls[i]+"\" url=\""+urls[i]+"\" target=\"_blank\">"+decodeURIComponent(urls[i].substring(urls[i].lastIndexOf("/")+1))+"</a>";//
					}
					li += "&nbsp;&nbsp;</li>";
				
					$("#filesPreview").append(li);
				}
			}
			if ($("#filesPreview").text() == ""){
				$("#filesPreview").html("<li style='list-style:none;padding-top:5px;'>无</li>");
			}
			
			
		});
		
		function showSelectedData(){
			
			
            $.ajax({
                type:'post',
                url:'${ctx}/resourcebus/tsResourceBus/getSelectColumnData',
                cache:false,
                dataType:'json',
                success:function(data){
                
                	 var codeString=$("#codeString").val();
        			 $("#testHtml").html(codeString);
                }
            });

        }
		

		function download_pic(e) {
			 var codeurl=e;
			 if(browserIsIe()){//假如是ie浏览器  
			  DownLoadReportIMG(codeurl);  
			}else{  

			 $("#download").attr('href', codeurl);
			document.getElementById("download").click();
			}  
			
			
			 
			 }  
			  
			function DownLoadReportIMG(imgPathURL) {  
				
			    //如果隐藏IFRAME不存在，则添加  
			    if (!document.getElementById("IframeReportImg"))  
			        $('<iframe style="display:none;" id="IframeReportImg" name="IframeReportImg" onload="DoSaveAsIMG();" width="0" height="0" src="about:blank"></iframe>').appendTo("body");  
			    if (document.all.IframeReportImg.src != imgPathURL) {  
			        //加载图片  
			        document.all.IframeReportImg.src = imgPathURL;  
			    }  
			    else {  
			        //图片直接另存为  
			        DoSaveAsIMG();  
			    }  
			}  
			function DoSaveAsIMG() {  
				
			    if (document.all.IframeReportImg.src != "about:blank")  
			        window.frames["IframeReportImg"].document.execCommand("SaveAs");  
			    window.location.href = "${ctx}/resourcebus/tsResourceBus/view?id=${tsResource.id}&busType=${tsResource.busType}";
			  
			}  
			//判断是否为ie浏览器  
			function browserIsIe() {  
			    if (!!window.ActiveXObject || "ActiveXObject" in window)  
			        return true;  
			    else  
			        return false;  
			}  
		

		
	</script>
</head>
<body onload="load()" >
	<ul class="nav nav-tabs">
		<!-- <li><a href="${ctx}/resourcebus/tsResourceBus/list?busType=${busType}">上传文件列表</a></li> -->
		<li class="active"><a href="${ctx}/resource/tsResource/form?id=${tsResource.id}">录入上传文件信息<shiro:hasPermission name="resource:tsResource:edit">${not empty tsResource.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="resource:tsResource:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tsResource" action="${ctx}/resourcebus/tsResourceBus/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		 <input type="hidden" id="codeString" name="codeString" value="${codeString}" class="input-medium"/>
		 <input type="hidden" id="busType_" name="busType" value="${busType}" class="input-medium"/>
		 <fieldset>
		<legend >资源详情</legend>
		<table class="table-form">
				<tr>
					<td class="tit" width="20%">所属型号：</td><td width="30%">
						${tsResource.remark20}
			       
					</td><td class="tit" width="20%">资源名称：</td><td width="30%">
							${tsResource.name}
					</td>
				</tr>
				<tr>
					<td class="tit">图号：</td><td>
						${tsResource.format}
			       
					</td><td class="tit">研制阶段：</td><td>
						${fns:getDictLabel(tsResource.power, 'yzgc', '')}
			       
					</td>
				</tr>
				
				<tr>
				
					<td class="tit">上传部门：</td><td>
						${tsResource.officeName}
			       
					</td>
						<td class="tit">上传人：</td><td>
						${tsResource.userName}
			       
					</td>
				</tr>
				<tr>
				
					<td class="tit">上传时间：</td><td colspan="3">
						<fmt:formatDate value="${tsResource.createDate}" pattern="yyyy-MM-dd"/>
			       
					</td>
				</tr>
				<tr>
					<td class="tit">文件：</td><td colspan="3">
						<form:hidden id="files" path="files"   htmlEscape="true" maxlength="255" class="required"/>
			       <sys:ckfinder input="files"  type="files" uploadPath="/oa/notify"  selectMultiple="true"  readonly="true"  />
					</td>
					
				</tr>
		</table>
         
			</fieldset>	
		
		
		<div id="testHtml"> 
            
		 </div>
		
		
		
		
		<div class="form-actions">
			
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>