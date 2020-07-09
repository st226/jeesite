<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>流水号管理</title>
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
		<li class="active"><a href="${ctx}/maxtable/tsMaxTable/">流水号列表</a></li>
		<shiro:hasPermission name="maxtable:tsMaxTable:edit"><li><a href="${ctx}/maxtable/tsMaxTable/form">流水号添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tsMaxTable" action="${ctx}/maxtable/tsMaxTable/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>字段名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>字段名称</th>
				<th>最大值</th>
				<shiro:hasPermission name="maxtable:tsMaxTable:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tsMaxTable">
			<tr>
				<td><a href="${ctx}/maxtable/tsMaxTable/form?id=${tsMaxTable.id}">
					${tsMaxTable.name}
				</a></td>
				<td>
					${tsMaxTable.code}
				</td>
				<shiro:hasPermission name="maxtable:tsMaxTable:edit"><td>
    				<a href="${ctx}/maxtable/tsMaxTable/form?id=${tsMaxTable.id}">修改</a>
					<a href="${ctx}/maxtable/tsMaxTable/delete?id=${tsMaxTable.id}" onclick="return confirmx('确认要删除该流水号吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>