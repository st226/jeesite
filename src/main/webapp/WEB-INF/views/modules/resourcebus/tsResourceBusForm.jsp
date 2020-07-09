<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图书馆资源管理管理</title>
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
			
			showSelectedData();
			
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

		 <div class="control-group">
			<label class="control-label">所属型号:</label>
			<div class="controls">
				<sys:treeselect id="busType_" name="busType" value="${tsResource.busType}" 
				labelName="tsResource.busName" labelValue="${tsResource.remark20}"
					title="选择型号/设备" url="/resourcetype/tsResourceType/treeData" cssClass="required" allowClear="true"/>
			</div>
		</div>
		<div id="testHtml"> 
            
		 </div>
		 
		
		 
		 
		 
		 
		 <div class="control-group">
		 
		 
		 
				<label class="control-label">添加上传文档：</label>
				<div class="controls">
					<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="required"/>
					<sys:ckfinder input="files" type="files" uploadPath="/oa/notify" selectMultiple="true"/>
				</div>
			</div>
		
		
		
		
		
		<div class="form-actions">
			<shiro:hasPermission name="resourcebus:tsResourceBus:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>