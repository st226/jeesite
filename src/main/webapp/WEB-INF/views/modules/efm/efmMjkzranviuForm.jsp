<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保密本领用申请管理</title>
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
		<li><a href="${ctx}/efm/efmMjkzranviu/">保密本领用申请列表</a></li>
		<li class="active"><a href="${ctx}/efm/efmMjkzranviu/form?id=${efmMjkzranviu.id}">保密本领用申请<shiro:hasPermission name="efm:efmMjkzranviu:edit">${not empty efmMjkzranviu.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="efm:efmMjkzranviu:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="efmMjkzranviu" action="${ctx}/efm/efmMjkzranviu/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		
		
		
		<table class="table-form">
				<tr>
					<td class="tit" width="15%">本子编号：</td><td width="35%">
							<form:input path="benzibianhao" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
					<td class="tit" width="15%">用户id：</td><td width="35%">
					<form:input path="yonghuid" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
			</tr>
			
			
			
			<tr>
					<td class="tit" width="15%">申请人：</td><td width="35%">
							<form:input path="shenqingren" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
					<td class="tit" width="15%">申请部门：</td><td width="35%">
					<form:input path="shenqingbumen" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
			</tr>
			
			
			
			<tr>
					<td class="tit" width="15%">申请日期：</td><td width="35%">
							<input name="shenqingriqi" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${efmMjkzranviu.shenqingriqi}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
					<td class="tit" width="15%">保密本大小：</td><td width="35%">
					<form:input path="baomibendaxiao" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
			</tr>
			
			
			
			
			
			<tr>
					<td class="tit" width="15%">保密本规格：</td><td width="35%">
						<form:input path="baomibenguige" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
					<td class="tit" width="15%">编号：</td><td width="35%">
					<form:input path="procSno" htmlEscape="false" maxlength="50" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
			</tr>
			
			
			
			<tr>
					<td class="tit" width="15%">表单ID：</td><td width="35%">
							<form:input path="formId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
					<td class="tit" width="15%">数据密级：</td><td width="35%">
					<form:input path="dataSecretLevel" htmlEscape="false" maxlength="20" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
			</tr>
		
		
		
		
			<tr>
					<td class="tit" width="15%">版本：</td><td width="35%">
							<form:input path="version" htmlEscape="false" maxlength="64" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
					<td class="tit" width="15%">创建者组织：</td><td width="35%">
					<form:input path="createByDept.id" htmlEscape="false" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
			</tr>
			
			
			
			<tr>
					<td class="tit" width="15%">申请人密级：</td><td width="35%">
							<form:input path="shenqingrenmiji" htmlEscape="false" maxlength="4" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
					<td class="tit" width="15%">申请数量：</td><td width="35%">
					<form:input path="sqsl" htmlEscape="false" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
			</tr>
			
			
			
			<tr>
					<td class="tit" width="15%">保密本密级：</td><td width="35%">
							<form:input path="baomibenmiji" htmlEscape="false" maxlength="4" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
					<td class="tit" width="15%">申请人id：</td><td width="35%">
					<form:input path="shenqingrenid" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
			</tr>
			
			
			<tr>
					<td class="tit" width="15%">申请部门id：</td><td width="35%">
							<form:input path="shenqingbumenid" htmlEscape="false" maxlength="4" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
					<td class="tit" width="15%">保密本编号：</td><td width="35%">
					<form:input path="baomibenbianhao" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>	
			</tr>

			<tr>
					<td class="tit" width="15%">页数：</td><td width="35%" colspan="3">
						<form:input path="yeshu" htmlEscape="false" maxlength="255" class="input-xlarge " style="width: 727px;"/>
				 <span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>	
			</tr>
		
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="efm:efmMjkzranviu:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>