<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>测量数据追踪管理</title>
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
	<br/>
	<form:form id="inputForm" modelAttribute="sbMeteringNotify" action="${ctx}/metering/meteringnotify/sbMeteringNotify/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="equipmentId"/>
		<sys:message content="${message}"/>
		<table class="table-form">
		      <tr>
					<td class="tit" width="15%">通知部门：</td><td width="35%" colspan="1">
						<sys:treeselect id="office" name="office.id" value="${sbMeteringNotify.office.id}" labelName="officeName" labelValue="${sbMeteringNotify.officeName}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"  checked="true"/>

					</td>
					<!-- 
					<td class="tit" width="15%">通知人：</td><td width="35%">
						<sys:treeselect id="user" name="user.id" value="${sbMeteringNotify.user.id}" labelName="userName" labelValue="${sbMeteringNotify.userName}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
					</td> -->
					<td class="tit" width="15%">计量日期：</td><td width="35%">
						<input name="meteringTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sbMeteringNotify.meteringTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>

					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">设备名称：</td><td width="35%">
						<form:input path="equipmentName" htmlEscape="false" maxlength="255" class="input-xlarge "/>

					</td><td class="tit" width="15%">设备编号：</td><td width="35%">
						<form:input path="equipmentCode" htmlEscape="false" maxlength="255" class="input-xlarge "/>
					</td>
					
				</tr>	
			
				<tr>
					<td class="tit" width="15%">问题起始日期：</td><td width="35%">
						<input name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sbMeteringNotify.beginTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>

					</td><td class="tit" width="15%">问题结束日期：</td><td width="35%">
						<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sbMeteringNotify.endTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</td>
					
				</tr>	
				<tr>
					<td class="tit" width="15%">超差内容描述：</td><td width="35%" colspan="3">
						<form:textarea path="reasoncx" htmlEscape="false" maxlength="255" class="input-xxlarge " rows="3"/>

					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">提出部门：</td><td width="35%">
						<form:input path="createOffice" htmlEscape="false" maxlength="255" class="input-xlarge "/>

					</td><td class="tit" width="15%">提出时间：</td><td width="35%">
						<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sbMeteringNotify.createDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</td>
					
				</tr>	
				<!-- 
				<tr>
					<td class="tit" width="15%">检定结果：</td><td width="35%"  colspan="3">
						<form:textarea path="resultcx" htmlEscape="false" maxlength="255" class="input-xxlarge " rows="3"/>

					</td>
					
				</tr>	
				<tr>
					<td class="tit" width="15%"> 反馈情况：</td><td width="35%"  colspan="3">
						<form:textarea path="reasonqk" htmlEscape="false" maxlength="255" class="input-xxlarge " rows="3"/>

					</td>
					
				</tr>	
				<tr>
					<td class="tit" width="15%">使用人：</td><td width="35%">
						<form:input path="uname" htmlEscape="false" maxlength="255" class="input-xlarge "/>

					</td><td class="tit" width="15%">部门领导：</td><td width="35%">
						<form:input path="leader" htmlEscape="false" maxlength="255" class="input-xlarge "/>
					</td>
					
				</tr>
					 -->		
		</table>
		
		
	
		
		
		
		
		
	</form:form>
</body>
</html>