<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备借用管理</title>
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
		<li class="active"><a href="${ctx}/borrow/sbborrowchild/sbBorrowChild/">设备借用列表</a></li>
		<shiro:hasPermission name="borrow:sbborrowchild:sbBorrowChild:edit"><li><a href="${ctx}/borrow/sbborrowchild/sbBorrowChild/form">设备借用添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sbBorrowChild" action="${ctx}/borrow/sbborrowchild/sbBorrowChild/" method="post" class="breadcrumb form-search">
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
				<th>设备名称</th>
				<th>规格型号</th>
				<th>设备编号</th>
				<th>出厂编号</th>
				<th>借用状态</th>
				<th>借用类型</th>
				<th>是否计量</th>
				<th>计量有效期</th>
				<th>附件</th>
				<th>借用次数</th>
				<th>借用时间</th>
				<th>归还时间</th>
				<th>借用部门</th>
				<th>借用人</th>
				<shiro:hasPermission name="borrow:sbborrowchild:sbBorrowChild:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sbBorrowChild">
			<tr>
				<td><a href="${ctx}/borrow/sbborrowchild/sbBorrowChild/form?id=${sbBorrowChild.id}">
					${sbBorrowChild.equipmentName}
				</a></td>
				<td>
					${sbBorrowChild.equipmentType}
				</td>
				<td>
					${sbBorrowChild.equipmentSbcode}
				</td>
				<td>
					${sbBorrowChild.equipmentCccode}
				</td>
				<td>
					${fns:getDictLabel(sbBorrowChild.borrowState, 'bstate', '')}
				</td>
				<td>
					${sbBorrowChild.borrowType}
				</td>
				<td>
					${sbBorrowChild.ismeasurement}
				</td>
				<td>
					<fmt:formatDate value="${sbBorrowChild.measurement}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sbBorrowChild.enclosure}
				</td>
				<td>
					${sbBorrowChild.frequency}
				</td>
				<td>
					<fmt:formatDate value="${sbBorrowChild.statrDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${sbBorrowChild.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sbBorrowChild.office.name}
				</td>
				<td>
					${sbBorrowChild.user.name}
				</td>
				<shiro:hasPermission name="borrow:sbborrowchild:sbBorrowChild:edit"><td>
    				<a href="${ctx}/borrow/sbborrowchild/sbBorrowChild/form?id=${sbBorrowChild.id}">修改</a>
					<a href="${ctx}/borrow/sbborrowchild/sbBorrowChild/delete?id=${sbBorrowChild.id}" onclick="return confirmx('确认要删除该设备借用吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>