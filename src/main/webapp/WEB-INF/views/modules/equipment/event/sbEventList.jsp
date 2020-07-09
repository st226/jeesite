<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>借用事件管理</title>
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
		<li class="active"><a href="${ctx}/equipment/event/sbEvent/">借用事件列表</a></li>
		<shiro:hasPermission name="equipment:event:sbEvent:edit"><li><a href="${ctx}/equipment/event/sbEvent/form">借用事件添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sbEvent" action="${ctx}/equipment/event/sbEvent/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>状态</th>
				<th>借用部门</th>
				<th>借用人</th>
				<th>申请人</th>
				<th>申请时间</th>
				<th>类别</th>
				<th>借用到时间</th>
				<th>原因</th>
				<shiro:hasPermission name="equipment:event:sbEvent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sbEvent">
			<tr>
				<td><a href="${ctx}/equipment/event/sbEvent/form?id=${sbEvent.id}">
					${fns:getDictLabel(sbEvent.state, 'lstate', '')}
				</a></td>
				<td>
					${sbEvent.office.name}
				</td>
				<td>
					${sbEvent.user.name}
				</td>
				<td>
					${sbEvent.createBy.id}
				</td>
				<td>
					${sbEvent.createDate}
				</td>
				<td>
					${fns:getDictLabel(sbEvent.type, 'borrow_type', '')}
				</td>
				<td>
					<fmt:formatDate value="${sbEvent.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sbEvent.cause}
				</td>
				<shiro:hasPermission name="equipment:event:sbEvent:edit"><td>
    				<a href="${ctx}/equipment/event/sbEvent/form?id=${sbEvent.id}">修改</a>
					<a href="${ctx}/equipment/event/sbEvent/delete?id=${sbEvent.id}" onclick="return confirmx('确认要删除该借用事件吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>