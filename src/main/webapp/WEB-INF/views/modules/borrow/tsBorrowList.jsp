<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资源流通管理</title>
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
		<li class="active"><a href="${ctx}/borrow/tsBorrow/list">设备借用列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tsBorrow" action="${ctx}/borrow/tsBorrow/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>设备名称：</label>
				<form:input path="tsName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>设备编号：</label>
				<form:input path="tsKeyCode" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>借用人：</label>
				<sys:treeselect id="user" name="user.id" value="${tsBorrow.user.id}" labelName="user.name" labelValue="${tsBorrow.user.name}"
					title="部门" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
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
				<th>存放位置</th>
				<th>借用部门</th>
				<th>借用人员</th>
			
				<th>借阅状态</th>
				<th>借阅类型</th>
				<th>借阅时间</th>
				
			
				<shiro:hasPermission name="borrow:tsBorrow:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tsBorrow">
			<tr>
				<td><a href="${ctx}/borrow/tsBorrow/form?id=${tsBorrow.id}">
					${tsBorrow.tsName}
				</a></td>
				
				<td>
					${tsBorrow.tsKeyCode}
				</td>
				<td>
					${tsBorrow.busType}
				</td>
				<td>
					${tsBorrow.office.name}
				</td>
				<td>
					${tsBorrow.user.name}
				</td>
		
				<td>
					${fns:getDictLabel(tsBorrow.borrowState, 'borrow_state', '')}
				</td>
				<td>
					${fns:getDictLabel(tsBorrow.borrowType, 'borrow_type', '')}
				</td>
				<td>
					<fmt:formatDate value="${tsBorrow.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				
				
				<shiro:hasPermission name="borrow:tsBorrow:edit"><td>
    				<a href="${ctx}/borrow/tsBorrow/form?id=${tsBorrow.id}">修改</a>
					<a href="${ctx}/borrow/tsBorrow/delete?id=${tsBorrow.id}" onclick="return confirmx('确认要删除该资源流通吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>