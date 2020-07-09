<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>付款信息汇总管理</title>
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
		<li class="active"><a href="${ctx}/business/paycollect/swPayCollect/">付款信息汇总列表</a></li>
		<shiro:hasPermission name="business:paycollect:swPayCollect:edit"><li><a href="${ctx}/business/paycollect/swPayCollect/form">付款信息汇总添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="swPayCollect" action="${ctx}/business/paycollect/swPayCollect/" method="post" class="breadcrumb form-search">
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
				<th>合同编号</th>
				<th>合同名称</th>
				<th>付款方式</th>
				<shiro:hasPermission name="business:paycollect:swPayCollect:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="swPayCollect">
			<tr>
				<td><a href="${ctx}/business/paycollect/swPayCollect/form?id=${swPayCollect.id}">
					${swPayCollect.contractCode}
				</a></td>
				<td>
					${swPayCollect.contrateName}
				</td>
				<td>
					${fns:getDictLabel(swPayCollect.payType, 'pay_type', '')}
				</td>
				<shiro:hasPermission name="business:paycollect:swPayCollect:edit"><td>
    				<a href="${ctx}/business/paycollect/swPayCollect/form?id=${swPayCollect.id}">修改</a>
					<a href="${ctx}/business/paycollect/swPayCollect/delete?id=${swPayCollect.id}" onclick="return confirmx('确认要删除该付款信息汇总吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>