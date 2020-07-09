<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>待办任务</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {


			parent.window.jBox.close();
			
		});
		/**
		 * 签收任务
		 */
		function claim(taskId) {
			$.get('${ctx}/act/task/claim' ,{taskId: taskId}, function(data) {
				if (data == 'true'){
		        	top.$.jBox.tip('签收完成');
		            location = '${ctx}/act/task/todo/';
				}else{
		        	top.$.jBox.tip('签收失败');
				}
		    });
		}
		function test(pid){
			top.$.jBox.open("iframe:http://10.193.200.16:8088/default/archives/archives/imageFlow.jsp?id="+pid, "流程跟踪",810,$(top.document).height()-110
					,{
				buttons:{"确定":true}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}});
			
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/act/task/todo/">待办任务</a></li>
		<li><a href="${ctx}/workitem/wfworkitem/">已办任务</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="act" action="${ctx}/act/task/todo/" method="get" class="breadcrumb form-search">
		<div>
			<label>流程类型：&nbsp;</label>
			<form:select path="procDefKey" class="input-medium">
				<form:option value="" label="全部流程"/>
				<form:options items="${fns:getDictList('act_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			<label>创建时间：</label>
			<input id="beginDate"  name="beginDate"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
				value="<fmt:formatDate value="${act.beginDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				　--　
			<input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
				value="<fmt:formatDate value="${act.endDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>当前环节</th><%--
				<th>任务内容</th> --%>
				<th>流程名称</th>
				<th>摘要信息</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="act">
				<c:set var="task" value="${act.processChName}" />
				<c:set var="vars" value="${act.processChName}" />
				<c:set var="procDef" value="${act.processChName}" />
				<c:set var="status" value="${act.processChName}" />
				<tr>
					<td>
						<a href="${ctx}${act.actionURL}?processInstID=${act.processInstID}&workItemID=${act.workItemID}&activityDefID=${act.activityDefID}&workItemName=${act.workItemName}">${act.processChName}</a>
					</td>
				
					<td>${act.workItemName}</td>
					<td> ${act.processInstName}</td>
					<td> ${act.processInstName}</td>
					<td>${act.startTime}</td>
					<td>
						<a href="${ctx}${act.actionURL}?processInstID=${act.processInstID}&workItemID=${act.workItemID}&activityDefID=${act.activityDefID}&workItemName=${act.workItemName}">任务办理</a>
                        <a target="_blank" onclick="test(${act.processInstID})" >跟踪</a>
					
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
