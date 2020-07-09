<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>标签配置管理</title>
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

	<form:form id="inputForm" modelAttribute="sbDeploy" action="${ctx}/sbdeploy/sbDeploy/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>

		
		
		<div class="control-group">
			<label class="control-label"></label>
			<div class="controls">
				<form:checkboxes path="tsResourceBusList" repeatLayout="table" repeatItems="3" items="${tsResourceBusList}" itemLabel="columnComments" itemValue="id" element="br" htmlEscape="false" class="required"/>
		
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sbdeploy:sbDeploy:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
	
		</div>
	</form:form>
</body>
</html>