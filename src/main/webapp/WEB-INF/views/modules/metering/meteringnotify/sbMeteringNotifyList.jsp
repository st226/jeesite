<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>测量数据追踪管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/metering/meteringnotify/sbMeteringNotify/">测量数据追踪列表</a></li>
		<shiro:hasPermission name="metering:meteringnotify:sbMeteringNotify:edit"><li><a href="${ctx}/metering/meteringnotify/sbMeteringNotify/form">测量数据追踪添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sbMeteringNotify" action="${ctx}/metering/meteringnotify/sbMeteringNotify/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>设备名称：</label>
				<form:input path="equipmentName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>设备名称</th>
				<th>设备编号</th>
				<th>部门名称</th>
				<th>使用人</th>
				<th>计量时间</th>
				<th>起始时间</th>
				<th>结束时间</th>
				<shiro:hasPermission name="metering:meteringnotify:sbMeteringNotify:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sbMeteringNotify">
			<tr>
				<td><a href="${ctx}/metering/meteringnotify/sbMeteringNotify/form?id=${sbMeteringNotify.id}">
					${sbMeteringNotify.equipmentName}
				</a></td>
				<td>
					${sbMeteringNotify.equipmentCode}
				</td>
				<td>
					${sbMeteringNotify.officeName}
				</td>
				<td>
					${sbMeteringNotify.userName}
				</td>
				<td>
					<fmt:formatDate value="${sbMeteringNotify.meteringTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${sbMeteringNotify.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${sbMeteringNotify.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="metering:meteringnotify:sbMeteringNotify:edit"><td>
    				<a href="${ctx}/metering/meteringnotify/sbMeteringNotify/form?id=${sbMeteringNotify.id}">修改</a>
					<a href="${ctx}/metering/meteringnotify/sbMeteringNotify/delete?id=${sbMeteringNotify.id}" onclick="return confirmx('确认要删除该测量数据追踪吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>