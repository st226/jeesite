<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>载体复印申请管理</title>
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
		<li class="active"><a href="${ctx}/efm/efmGgtdycieys/">载体复印申请列表</a></li>
		<shiro:hasPermission name="efm:efmGgtdycieys:edit"><li><a href="${ctx}/efm/efmGgtdycieys/form">载体复印申请添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="efmGgtdycieys" action="${ctx}/efm/efmGgtdycieys/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>编号：</label>
				<form:input path="procSno" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>申请人：</label>
				<form:input path="shenqingren" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>申请部门：</label>
				<form:input path="shenqingbumen" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>编号</th>
				<th>更新时间</th>
				<th>载体类别</th>
				<th>会议名称</th>
				<th>申请人</th>
				<th>申请部门</th>
				<shiro:hasPermission name="efm:efmGgtdycieys:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="efmGgtdycieys">
			<tr>
				<td><a href="${ctx}/efm/efmGgtdycieys/form?id=${efmGgtdycieys.id}">
					${efmGgtdycieys.procSno}
				</a></td>
				<td>
					<fmt:formatDate value="${efmGgtdycieys.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${efmGgtdycieys.zaitileibie}
				</td>
				<td>
					${efmGgtdycieys.huiyimingcheng}
				</td>
				<td>
					${efmGgtdycieys.shenqingren}
				</td>
				<td>
					${efmGgtdycieys.shenqingbumen}
				</td>
				<shiro:hasPermission name="efm:efmGgtdycieys:edit"><td>
    				<a href="${ctx}/efm/efmGgtdycieys/form?id=${efmGgtdycieys.id}">修改</a>
					<a href="${ctx}/efm/efmGgtdycieys/delete?id=${efmGgtdycieys.id}" onclick="return confirmx('确认要删除该载体复印申请吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>