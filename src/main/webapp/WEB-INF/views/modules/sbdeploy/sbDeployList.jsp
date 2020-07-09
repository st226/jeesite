<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>标签配置管理</title>
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
		<li class="active"><a href="${ctx}/sbdeploy/sbDeploy/">标签配置列表</a></li>
		<shiro:hasPermission name="sbdeploy:sbDeploy:edit"><li><a href="${ctx}/sbdeploy/sbDeploy/form">标签配置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sbDeploy" action="${ctx}/sbdeploy/sbDeploy/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>人员：</label>
				<sys:treeselect id="user" name="user.id" value="${sbDeploy.user.id}" labelName="user.name" labelValue="${sbDeploy.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>业务类型：</label>
				<form:input path="busType" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>人员</th>
				<th>业务类型</th>
				<th>表列字段ID</th>
				<th>column_name</th>
				<th>列描述</th>
				<th>表ID</th>
				<th>column_jdbc_type</th>
				<th>表描述</th>
				<th>排序</th>
				<th>is_null</th>
				<shiro:hasPermission name="sbdeploy:sbDeploy:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sbDeploy">
			<tr>
				<td><a href="${ctx}/sbdeploy/sbDeploy/form?id=${sbDeploy.id}">
					${sbDeploy.user.name}
				</a></td>
				<td>
					${sbDeploy.busType}
				</td>
				<td>
					${sbDeploy.columnId}
				</td>
				<td>
					${sbDeploy.columnName}
				</td>
				<td>
					${sbDeploy.columnComments}
				</td>
				<td>
					${sbDeploy.tableId}
				</td>
				<td>
					${sbDeploy.columnJdbcType}
				</td>
				<td>
					${sbDeploy.tableComments}
				</td>
				<td>
					${sbDeploy.sort}
				</td>
				<td>
					${sbDeploy.isNull}
				</td>
				<shiro:hasPermission name="sbdeploy:sbDeploy:edit"><td>
    				<a href="${ctx}/sbdeploy/sbDeploy/form?id=${sbDeploy.id}">修改</a>
					<a href="${ctx}/sbdeploy/sbDeploy/delete?id=${sbDeploy.id}" onclick="return confirmx('确认要删除该标签配置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>