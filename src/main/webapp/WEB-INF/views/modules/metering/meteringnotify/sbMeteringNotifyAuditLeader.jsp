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
		function test(pid){
			top.$.jBox.open("iframe:http://10.193.200.16:8088/default/archives/archives/imageFlow.jsp?id=${sbMeteringNotify.processInstID}", 
					"流程跟踪",810,$(top.document).height()-110,{
						buttons:{"确定":true}, loaded:function(h){
							$(".jbox-content", top.document).css("overflow-y","hidden");
						}
					});
		
			
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a >流程审批</a></li>
	    <li><a onclick="test(${sbMeteringNotify.processInstID})">流程跟踪</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sbMeteringNotify" action="${ctx}/metering/meteringnotify/sbMeteringNotify/submitLeader" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="equipmentId"/>
		<form:hidden path="workItem.processInstID"/>
		<form:hidden path="workItem.workItemID"/>
		<form:hidden path="workItem.workItemName"/>
		<form:hidden path="workItem.processChName"/>
		<form:hidden path="workItem.activityDefID"/>
		<form:hidden id="flag" path="workItem.workItemType"/>
		<sys:message content="${message}"/>
		<center><legend >计量跟踪单反馈</legend></center> 
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
						<input name="meteringTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " readonly="true"
					value="<fmt:formatDate value="${sbMeteringNotify.meteringTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>

					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">设备名称：</td><td width="35%">
						<form:input path="equipmentName" htmlEscape="false" maxlength="255" class="input-xlarge " readonly="true"/>

					</td><td class="tit" width="15%">设备编号：</td><td width="35%">
						<form:input path="equipmentCode" htmlEscape="false" maxlength="255" class="input-xlarge " readonly="true"/>
					</td>
					
				</tr>	
			
				<tr>
					<td class="tit" width="15%">问题起始日期：</td><td width="35%">
						<input name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " readonly="true"
					value="<fmt:formatDate value="${sbMeteringNotify.beginTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>

					</td><td class="tit" width="15%">问题结束日期：</td><td width="35%">
						<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sbMeteringNotify.endTime}" pattern="yyyy-MM-dd"/>" readonly="true"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</td>
					
				</tr>	
				<tr>
					<td class="tit" width="15%">超差内容描述：</td><td width="35%" colspan="3">
						<form:textarea path="reasoncx" htmlEscape="false" maxlength="255" class="input-xxlarge " rows="3" readonly="true"/>

					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">提出部门：</td><td width="35%">
						<form:input path="createOffice" htmlEscape="false" maxlength="255" class="input-xlarge " readonly="true"/>

					</td><td class="tit" width="15%">提出时间：</td><td width="35%">
						<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " readonly="true"
					value="<fmt:formatDate value="${sbMeteringNotify.createDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</td>
					
				</tr>	
			
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

					</td><td class="tit" width="15%">填写时间：</td><td width="35%">
						<input name="field1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " 
						value ="${sbMeteringNotify.field1}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</td>
					
				</tr>
						
		</table>
		<legend >审批详情</legend>
					<table class="table-form">
						<c:forEach items="${sbMeteringNotify.examineList}" var="testData">
			<tr>
					<td  width="10%" class="tit" rowspan="2">${testData.examineExamineisagree}</td>
					
					<td  colspan="6">
						${testData.examineExplain}
					</td>
				</tr>
				<tr>
					
					<td colspan="2"  width="20%">
						${testData.examinePerson}
					</td>
					
					<td colspan="2"  width="20%">
						<fmt:formatDate value="${testData.examineDate}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
		</c:forEach>
				<tr>
					<td class="tit">您的意见</td>
					<td colspan="6">
						<form:textarea path="field2" class="required" rows="5" maxlength="20" cssStyle="width:500px"/>
					</td>
				</tr>	
					
				
				
			
				
				</table>
		
	
		<div class="form-actions">
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="同 意" onclick="$('#flag').val('Y')"/>&nbsp;
			<input id="btnSubmit" class="btn btn-inverse" type="submit" value="驳 回" onclick="$('#flag').val('N')"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		
		
		
		
	</form:form>
</body>
</html>