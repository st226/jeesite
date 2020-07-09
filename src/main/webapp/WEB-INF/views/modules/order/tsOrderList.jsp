<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理管理</title>
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
		<li class="active"><a href="${ctx}/order/tsOrder/">订单管理列表</a></li>
		<shiro:hasPermission name="order:tsOrder:edit"><li><a href="${ctx}/order/tsOrder/form">订单管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tsOrder" action="${ctx}/order/tsOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>供应商名称：</label>
				<form:input path="gysName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单名称</th>
				<th>订单编号</th>
				<th>供应商名称</th>
				<th>总件数</th>
				<th>总价格</th>
				<th>状态</th>
				<th>创建人</th>
				<th>创建时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="order:tsOrder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tsOrder">
			<tr>
				<td><a href="${ctx}/order/tsOrder/form?id=${tsOrder.id}">
					${tsOrder.name}
				</a></td>
				<td>
					${tsOrder.orderNumber}
				</td>
				<td>
					${tsOrder.gysName}
				</td>
				<td>
					${tsOrder.count}
				</td>
				<td>
					${tsOrder.price}
				</td>
				<td>
					${fns:getDictLabel(tsOrder.orderState, 'dd_state', '')}
				</td>
				<td>
					${tsOrder.createBy.id}
					
				</td>
				<td>
					<fmt:formatDate value="${tsOrder.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${tsOrder.remarks}
				</td>
				<shiro:hasPermission name="order:tsOrder:edit"><td>
    				<a href="${ctx}/order/tsOrder/form?id=${tsOrder.id}">修改</a>
					<a href="${ctx}/order/tsOrder/delete?id=${tsOrder.id}" onclick="return confirmx('确认要删除该订单管理吗？', this.href)">删除</a>
					<a href="${ctx}/order/tsOrder/deploy?id=${tsOrder.id}">订单管理</a>
	
					<a href="${ctx}/order/tsOrder/success?id=${tsOrder.id}">完成采购</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>