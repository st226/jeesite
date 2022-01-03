<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>涉密载体销毁回收管理管理</title>
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
		<li><a href="${ctx}/efm/efmUubejtefnl/">涉密载体销毁回收管理列表</a></li>
		<li class="active"><a href="${ctx}/efm/efmUubejtefnl/form?id=${efmUubejtefnl.id}">涉密载体销毁回收管理<shiro:hasPermission name="efm:efmUubejtefnl:edit">${not empty efmUubejtefnl.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="efm:efmUubejtefnl:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="efmUubejtefnl" action="${ctx}/efm/efmUubejtefnl/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		
		
		<table class="table-form">
		
		
			    <tr>
					<td class="tit" width="15%">序号：</td><td width="35%">
						<form:input path="xuhao" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
				<td class="tit" width="15%">载体名称：</td><td width="35%">
						<form:input path="zaitimingcheng" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
				</tr>
				
				
				
				<tr>
					<td class="tit" width="15%">载体编号：</td><td width="35%">
						<form:input path="zaitibianhao" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
				<td class="tit" width="15%">密级：</td><td width="35%">
						<form:input path="miji" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
				</tr>
				
				<tr>
					<td class="tit" width="15%">份数：</td><td width="35%">
						<form:input path="fenshu" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
				<td class="tit" width="15%">页数：</td><td width="35%">
						<form:input path="yeshu" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
				</tr>
				
				
				<tr>
					<td class="tit" width="15%">载体id：</td><td width="35%">
						<form:input path="fcId" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
				<td class="tit" width="15%">外键：</td><td width="35%">
						<form:input path="fkId" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
				</tr>
				
				
				<tr>
					<td class="tit" width="15%">载体状态：</td><td width="35%">
						<form:input path="zaitizhuangtai" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
				<td class="tit" width="15%">表单ID：</td><td width="35%">
						<form:input path="formId" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
				</tr>
				
				
				
				<tr>
					<td class="tit" width="15%">流程编号：</td><td width="35%">
						<form:input path="procSno" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
				<td class="tit" width="15%">数据密级：</td><td width="35%">
						<form:input path="dataSecretLevel" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
				</tr>
				
				
				
				<tr>
					<td class="tit" width="15%">版本：</td><td width="35%">
						<form:input path="version" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
				<td class="tit" width="15%">创建者组织：</td><td width="35%">
						<form:input path="createByDept.id" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
				</tr>
				
		</table>
	
		<div class="form-actions">
			<shiro:hasPermission name="efm:efmUubejtefnl:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>