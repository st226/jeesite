<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>涉密载体对外移交审批表管理</title>
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
		<li><a href="${ctx}/efm/efmXacqjkussd/">涉密载体对外移交审批表列表</a></li>
		<li class="active"><a href="${ctx}/efm/efmXacqjkussd/form?id=${efmXacqjkussd.id}">涉密载体对外移交审批表<shiro:hasPermission name="efm:efmXacqjkussd:edit">${not empty efmXacqjkussd.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="efm:efmXacqjkussd:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="efmXacqjkussd" action="${ctx}/efm/efmXacqjkussd/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		
		
		
		<table class="table-form">
		
		
			    <tr>
					<td class="tit" width="15%">编号：</td><td width="35%">
						<form:input path="procSno" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
				<td class="tit" width="15%">表单ID：</td><td width="35%">
						<form:input path="formId" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
				</tr>
				
				
				<tr>
					<td class="tit" width="15%">数据密级：</td><td width="35%">
						<form:input path="dataSecretLevel" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
				<td class="tit" width="15%">版本：</td><td width="35%">
						<form:input path="version" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
				</tr>
				
				<tr>
					<td class="tit" width="15%">创建者组织：</td><td width="35%">
						<form:input path="createByDept.id" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
				<td class="tit" width="15%">申请人：</td><td width="35%">
						<form:input path="shenqingren" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
				</tr>
				
				
				<tr>
					<td class="tit" width="15%">申请部门：</td><td width="35%">
						<form:input path="shenqingbumen" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
				<td class="tit" width="15%">移交人：</td><td width="35%">
						<form:input path="yijiaoren" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
				</tr>
				
				
				
				<tr>
					<td class="tit" width="15%">接收人：</td><td width="35%">
						<form:input path="jieshouren" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
				<td class="tit" width="15%">移交部门：</td><td width="35%">
						<form:input path="yijiaobumen" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
				</tr>
				
				<tr>
					<td class="tit" width="15%">接收单位：</td><td width="35%">
						<form:input path="jieshoudanwei" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
				<td class="tit" width="15%">载体最高密级：</td><td width="35%">
						<form:input path="zaitizuigaomiji" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
				</tr>
				
				
				<tr>
					<td class="tit" width="15%">主管所领导：</td><td width="35%">
						<form:input path="zgsld" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
				<td class="tit" width="15%">接收单位id：</td><td width="35%">
						<form:input path="jieshoudanweiid" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
				</tr>
				
				
				<tr>
					<td class="tit" width="15%">备注：</td><td width="35%">
						<form:input path="beizhu" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
				<td class="tit" width="15%">申请人id：</td><td width="35%">
						<form:input path="shenqingrenid" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
				</tr>
				
				
				
				<tr>
					<td class="tit" width="15%">申请部门id：</td><td width="35%">
						<form:input path="shenqingbumenid" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
				<td class="tit" width="15%">接收人id：</td><td width="35%">
						<form:input path="jieshourenid" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
				</tr>
				
				
				
				<tr>
					<td class="tit" width="15%">移交人id：</td><td width="35%">
						<form:input path="yijiaorenid" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
				<td class="tit" width="15%">主管所领导id：</td><td width="35%">
						<form:input path="zgsldid" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
				</tr>
				
				
				<tr>
					<td class="tit" width="15%">申请用户id：</td><td width="35%">
						<form:input path="sqyhid" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
				<td class="tit" width="15%">移交人用户id：</td><td width="35%">
						<form:input path="yjryhid" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
				</tr>
				
				
				
				<tr>
					<td class="tit" width="15%">移交部门id：</td><td width="35%">
						<form:input path="yijiaobumenid" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
				</tr>
				
				
		</table>		
		<div class="form-actions">
			<shiro:hasPermission name="efm:efmXacqjkussd:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>