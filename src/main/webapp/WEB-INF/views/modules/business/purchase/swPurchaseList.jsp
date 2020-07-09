<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仪器申购单管理</title>
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
		<li class="active"><a href="${ctx}/business/purchase/swPurchase/">仪器申购单列表</a></li>
		<shiro:hasPermission name="business:purchase:swPurchase:edit"><li><a href="${ctx}/business/purchase/swPurchase/form">仪器申购单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="swPurchase" action="${ctx}/business/purchase/swPurchase/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>申请单位：</label>
				<form:input path="officeName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>申请单位</th>
				<th>名称</th>
				<th>数量</th>
				<th>型号规格、技术条件</th>
				<th>生产单位（国别）</th>
				<th>单价</th>
				<th>资金来源</th>
				<shiro:hasPermission name="business:purchase:swPurchase:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="swPurchase">
			<tr>
				<td><a href="${ctx}/business/purchase/swPurchase/form?id=${swPurchase.id}">
					${swPurchase.officeName}
				</a></td>
				<td>
					${swPurchase.name}
				</td>
				<td>
					${swPurchase.amount}
				</td>
				<td>
					${swPurchase.specifications}
				</td>
				<td>
					${swPurchase.made}
				</td>
				<td>
					${swPurchase.unitPrice}
				</td>
				<td>
					${swPurchase.funds}
				</td>
				<shiro:hasPermission name="business:purchase:swPurchase:edit"><td>
    				<a href="${ctx}/business/purchase/swPurchase/form?id=${swPurchase.id}">修改</a>
    				<a href="${ctx}/business/purchase/swPurchase/view?id=${swPurchase.id}">打印</a>
					<a href="${ctx}/business/purchase/swPurchase/delete?id=${swPurchase.id}" onclick="return confirmx('确认要删除该仪器申购单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>