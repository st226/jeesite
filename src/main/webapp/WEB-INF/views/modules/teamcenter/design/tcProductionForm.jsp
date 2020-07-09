<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>生产图纸管理</title>
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
		<li><a href="${ctx}/teamcenter/design/tcProduction/">生产图纸列表</a></li>
		<li class="active"><a href="${ctx}/teamcenter/design/tcProduction/form?id=${tcProduction.id}">生产图纸<shiro:hasPermission name="teamcenter:design:tcProduction:edit">${not empty tcProduction.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="teamcenter:design:tcProduction:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tcProduction" action="${ctx}/teamcenter/design/tcProduction/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="typeId"/>
		<sys:message content="${message}"/>	
		<table class="table-form">
				<tr>
					<td class="tit" width="15%">名称：</td><td width="35%">
						<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
		
				<span class="help-inline"><font color="red">*</font></span>
					</td>
					
					<td class="tit" width="15%">编码：</td><td width="35%">
						<form:input path="code" htmlEscape="false" maxlength="100" class="input-xlarge "/>
						
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
			</tr>
			<tr>
					<td class="tit" width="15%">图号：</td><td width="35%">
						<form:input path="drawingNo" htmlEscape="false" maxlength="100" class="input-xlarge "/>
		
				<span class="help-inline"><font color="red">*</font></span>
					</td>
					
					<td class="tit" width="15%">阶段：</td><td width="35%">
						<form:select path="stage" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yzgc')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
			</tr>
			<tr>
					<td class="tit" width="15%">设计文件：</td><td width="35%" colspan="3">
						<form:hidden id="file" path="file" htmlEscape="false" maxlength="1000" class="input-xlarge"/>
				<sys:ckfinder input="file" type="files" uploadPath="/teamcenter/design/tcProduction" selectMultiple="true"/>
		
				<span class="help-inline"><font color="red">*</font></span>
					</td>
				</tr>
			<tr>	
					<td class="tit" width="15%">PDF版本：</td><td width="35%" colspan="3">
						<form:hidden id="filepdf" path="filepdf" htmlEscape="false" maxlength="1000" class="input-xlarge"/>
				<sys:ckfinder input="filepdf" type="files" uploadPath="/teamcenter/design/tcProduction" selectMultiple="true"/>
						
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
			</tr>
			<tr>
					<td class="tit" width="15%">备注信息：</td><td width="35%" colspan="3">
						<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
					</td>
					
					
					
			</tr>
		</table>	
		
		
		
		
		
	
		
		<div class="form-actions">
			<shiro:hasPermission name="teamcenter:design:tcProduction:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>