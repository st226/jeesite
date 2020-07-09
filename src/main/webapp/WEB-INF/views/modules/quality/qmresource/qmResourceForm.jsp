<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>技术文件管理管理</title>
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
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/quality/qmresource/qmResource/">技术文件管理列表</a></li>
		<li class="active"><a href="${ctx}/quality/qmresource/qmResource/form?id=${qmResource.id}">技术文件管理<shiro:hasPermission name="quality:qmresource:qmResource:edit">${not empty qmResource.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="quality:qmresource:qmResource:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="qmResource" action="${ctx}/quality/qmresource/qmResource/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">文件类型：</label>
			<div class="controls">
				<sys:treeselect id="fileType" name="fileType" value="${qmResource.fileType}" labelName="fileTypeName" labelValue="${qmResource.fileTypeName}"
					title="文件类型" url="/quality/resource/qmResourceType/treeData" cssClass="required" />
			</div>
		</div>
		
		<div class="control-group">
		    
			<label class="control-label">文件分类：</label>
			<div class="controls">

				<sys:treeselect id="qmType" name="qmType" value="${qmResource.qmType}" labelName="qmTypeName" labelValue="${qmResource.qmTypeName}"
					title="文件类型" url="/quality/resource/qmResourceType/treeData" cssClass="required" notAllowSelectParent="true"/>
	
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">标准模板文件名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">编制依据文件/标准：</label>
			<div class="controls">
				<form:input path="basis" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件：</label>
			<div class="controls">
				<form:hidden id="file" path="file" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="file" type="files" uploadPath="/quality/qmresource/qmResource" selectMultiple="true"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="quality:qmresource:qmResource:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>