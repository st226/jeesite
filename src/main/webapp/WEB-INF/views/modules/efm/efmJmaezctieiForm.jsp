<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单表管理</title>
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
		<li><a href="${ctx}/efm/efmJmaezctiei/">单表列表</a></li>
		<li class="active"><a href="${ctx}/efm/efmJmaezctiei/form?id=${efmJmaezctiei.id}">单表<shiro:hasPermission name="efm:efmJmaezctiei:edit">${not empty efmJmaezctiei.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="efm:efmJmaezctiei:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="efmJmaezctiei" action="${ctx}/efm/efmJmaezctiei/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		
		
		
		<table class="table-form">
				<tr>
					<td class="tit" width="15%">承办人：</td><td width="35%">
							<form:input path="chengbanren" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
					<td class="tit" width="15%">联系电话：</td><td width="35%">
						<form:input path="lianxidianhua" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
			</tr>
			
			
			
			<tr>
					<td class="tit" width="15%">填报部门：</td><td width="35%">
						<form:input path="tianbaobumen" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
					<td class="tit" width="15%">填报日期：</td><td width="35%">
						<form:input path="tianbaoriqi" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
			</tr>
			
			
			
			<tr>
					<td class="tit" width="15%">国家秘密类型：</td><td width="35%" colspan="3">
						<form:input path="gjmmlx" htmlEscape="false" maxlength="255" class="input-xlarge " style="width: 727px;"/>
				 <span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>	
			</tr>
			
			
			<tr>
					<td class="tit" width="15%">事项名称：</td><td width="35%" colspan="3">
						<form:input path="sxmc" htmlEscape="false" maxlength="255" class="input-xlarge " style="width: 727px;"/>
				 <span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>	
			</tr>
			
			
			
			<tr>
					<td class="tit" width="15%">拟定密级：</td><td width="35%">
							<form:input path="nidingmiji" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
					<td class="tit" width="15%">保密期限：</td><td width="35%">
						<form:input path="zhixifanwei" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
			</tr>
			
			
			
			<tr>
					<td class="tit" width="15%">知悉范围：</td><td width="35%" colspan="3">
						<form:textarea path="sxmc" htmlEscape="false" maxlength="255" class="input-xlarge " style="width: 727px;"/>
				 <span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>	
			</tr>
			
			
			
			
		    <tr>
					<td class="tit" width="15%">定密依据：</td><td width="35%" colspan="3">
						<form:textarea path="dingmiyiju" htmlEscape="false" maxlength="255" class="input-xlarge " style="width: 727px;"/>
				 <span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>	
			</tr>
			
			
			
			
			
			
			<tr>
					<td class="tit" width="15%">创建者组织：</td><td width="35%">
							<form:input path="createByDept.id" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
					<td class="tit" width="15%">流程编号：</td><td width="35%">
						<form:input path="procSno" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
			</tr>
			
			
			
			
			
			<tr>
					<td class="tit" width="15%">承办人id：</td><td width="35%">
							<form:input path="chengbanrenid" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
					<td class="tit" width="15%">填报部门id：</td><td width="35%">
						<form:input path="tianbaobumenid" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
			</tr>
			
			
			
			<tr>
					<td class="tit" width="15%">表单ID：</td><td width="35%">
							<form:input path="formId" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
					<td class="tit" width="15%">数据密级：</td><td width="35%">
						<form:input path="dataSecretLevel" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
			</tr>
			
			
			    <tr>
					<td class="tit" width="15%">版本：</td><td width="35%" colspan="3">
						<form:textarea path="version" htmlEscape="false" maxlength="255" class="input-xlarge " style="width: 727px;"/>
				 <span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>	
			</tr>
			
		</table>	
		
		
		
		<div class="form-actions">
			<shiro:hasPermission name="efm:efmJmaezctiei:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>