<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>日程信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			parent.window.jBox.close();
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
		<li class="active"><a href="${ctx}/calendar/sbCalendar/">日程信息列表</a></li>
		<shiro:hasPermission name="calendar:sbCalendar:edit"><li><a href="${ctx}/calendar/sbCalendar/form">日程信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sbCalendar" action="${ctx}/calendar/sbCalendar/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<sys:treeselect id="user" name="user.id" value="${sbCalendar.user.id}" labelName="user.name" labelValue="${sbCalendar.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>日程类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('calendar_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>事由：</label>
				<form:input path="matter" htmlEscape="false" maxlength="1000" class="input-medium"/>
			</li>
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>日程类型</th>
				<th>事由</th>
				<th>标题</th>
				<shiro:hasPermission name="calendar:sbCalendar:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sbCalendar">
			<tr>
				<td><a href="${ctx}/calendar/sbCalendar/form?id=${sbCalendar.id}">
					${sbCalendar.userName}
				</a></td>
				<td>
					${fns:getDictLabel(sbCalendar.type, 'calendar_type', '')}
				</td>
				<td>
					${sbCalendar.matter}
				</td>
				<td>
					${sbCalendar.title}
				</td>
				<shiro:hasPermission name="calendar:sbCalendar:edit"><td>
    				<a href="${ctx}/calendar/sbCalendar/form?id=${sbCalendar.id}">修改</a>
					<a href="${ctx}/calendar/sbCalendar/delete?id=${sbCalendar.id}" onclick="return confirmx('确认要删除该日程信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>