<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>馆藏信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")){
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
		<li><a href="${ctx}/collection/tsCollection/">馆藏信息列表</a></li>
		<li class="active"><a href="${ctx}/collection/tsCollection/form?id=${tsCollection.id}&parent.id=${tsCollectionparent.id}">馆藏信息<shiro:hasPermission name="collection:tsCollection:edit">${not empty tsCollection.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="collection:tsCollection:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tsCollection" action="${ctx}/collection/tsCollection/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">上级父级编号:</label>
			<div class="controls">
				<sys:treeselect id="parent" name="parent.id" value="${tsCollection.parent.id}" labelName="parent.name" labelValue="${tsCollection.parent.name}"
					title="父级编号" url="/collection/tsCollection/treeData" extId="${tsCollection.id}" cssClass="" allowClear="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">位置名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">编码：</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">节点类型：</label>
			<div class="controls">
				<form:select path="ctype" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('collection_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资源类型：</label>
			<div class="controls">
				<sys:treeselect id="busType" name="busType" value="${tsCollection.busType}" 
				labelName="tsResourceType.name" labelValue="${tsResourceType.name}"
					title="选择馆藏资源类型" url="/resourcetype/tsResourceType/treeData"  cssClass="" allowClear="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否叶子节点：</label>
			<div class="controls">
				<form:radiobuttons path="isleaf" items="${fns:getDictList('is_leaf')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="collection:tsCollection:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>