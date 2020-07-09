<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备借用管理</title>
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
		<li><a href="${ctx}/borrow/sbborrowchild/sbBorrowChild/">设备借用列表</a></li>
		<li class="active"><a href="${ctx}/borrow/sbborrowchild/sbBorrowChild/form?id=${sbBorrowChild.id}">设备借用<shiro:hasPermission name="borrow:sbborrowchild:sbBorrowChild:edit">${not empty sbBorrowChild.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="borrow:sbborrowchild:sbBorrowChild:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sbBorrowChild" action="${ctx}/borrow/sbborrow/sbBorrow/saveChild" method="post" class="form-horizontal">
		<form:hidden path="field1" value='${sbBorrowChild.id}'/>
		<sys:message content="${message}"/>		
		
		
		<div class="control-group">
			<label class="control-label">借用部门：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id" value="${sbBorrowChild.office.id}" labelName="office.name" labelValue="${sbBorrowChild.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借用人：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${sbBorrowChild.user.id}" labelName="user.name" labelValue="${sbBorrowChild.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">设备名称：</label>
			<div class="controls">
				<form:input path="equipmentName" htmlEscape="false" maxlength="199" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">规格型号：</label>
			<div class="controls">
				<form:input path="equipmentType" htmlEscape="false" maxlength="199" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备编号：</label>
			<div class="controls">
				<form:input path="equipmentSbcode" htmlEscape="false" maxlength="199" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出厂编号：</label>
			<div class="controls">
				<form:input path="equipmentCccode" htmlEscape="false" maxlength="19" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借用状态：</label>
			<div class="controls">
				<form:select path="borrowState" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('bstate')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">是否计量：</label>
			<div class="controls">
				<form:input path="ismeasurement" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计量有效期：</label>
			<div class="controls">
				<input name="measurement" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sbBorrowChild.measurement}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">借用时间：</label>
			<div class="controls">
				<input name="statrDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sbBorrowChild.statrDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归还时间：</label>
			<div class="controls">
				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sbBorrowChild.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		
		
		<div class="form-actions">
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>