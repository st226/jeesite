<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商信息变更管理</title>
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
		<li class="active"><a href="${ctx}/quality/supplierupdate/qmSupplierUpdate/">供应商信息变更列表</a></li>
		<shiro:hasPermission name="quality:supplierupdate:qmSupplierUpdate:edit"><li><a href="${ctx}/quality/supplierupdate/qmSupplierUpdate/form">供应商信息变更添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="qmSupplierUpdate" action="${ctx}/quality/supplierupdate/qmSupplierUpdate/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>供应商名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>申请人：</label>
				<form:input path="applicant" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>变更类型</th>
				<th>供应商名称</th>
				<th>货物名称</th>
				<th>申请人</th>
				<shiro:hasPermission name="quality:supplierupdate:qmSupplierUpdate:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="qmSupplierUpdate">
			<tr>
				<td><a href="${ctx}/quality/supplierupdate/qmSupplierUpdate/form?id=${qmSupplierUpdate.id}">
					${fns:getDictLabel(qmSupplierUpdate.type, 'supplier_update_type', '')}
				</a></td>
				<td>
					${qmSupplierUpdate.name}
				</td>
				<td>
					${qmSupplierUpdate.productName}
				</td>
				<td>
					${qmSupplierUpdate.applicant}
				</td>
				<shiro:hasPermission name="quality:supplierupdate:qmSupplierUpdate:edit"><td>
    				<a href="${ctx}/quality/supplierupdate/qmSupplierUpdate/form?id=${qmSupplierUpdate.id}">修改</a>
					<a href="${ctx}/quality/supplierupdate/qmSupplierUpdate/delete?id=${qmSupplierUpdate.id}" onclick="return confirmx('确认要删除该供应商信息变更吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>