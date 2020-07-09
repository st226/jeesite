<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商文件模板管理</title>
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
		<li class="active"><a href="${ctx}/quality/qmsuppliertemp/qmSupplierAdmittanceTemplate/">供应商文件模板列表</a></li>
		<shiro:hasPermission name="quality:qmsuppliertemp:qmSupplierAdmittanceTemplate:edit"><li><a href="${ctx}/quality/qmsuppliertemp/qmSupplierAdmittanceTemplate/form">供应商文件模板添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="qmSupplierAdmittanceTemplate" action="${ctx}/quality/qmsuppliertemp/qmSupplierAdmittanceTemplate/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>文件名称：</label>
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
			<th>序号</th>
				<th>文件名称</th>
				<th>备注</th>
				<shiro:hasPermission name="quality:qmsuppliertemp:qmSupplierAdmittanceTemplate:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="qmSupplierAdmittanceTemplate">
			<tr>
			<td>
					${qmSupplierAdmittanceTemplate.qmIndex}
				</td>
				<td><a href="${ctx}/quality/qmsuppliertemp/qmSupplierAdmittanceTemplate/form?id=${qmSupplierAdmittanceTemplate.id}">
					${qmSupplierAdmittanceTemplate.name}
				</a></td>
				<td>
					${qmSupplierAdmittanceTemplate.remarks}
				</td>
				<shiro:hasPermission name="quality:qmsuppliertemp:qmSupplierAdmittanceTemplate:edit"><td>
    				<a href="${ctx}/quality/qmsuppliertemp/qmSupplierAdmittanceTemplate/form?id=${qmSupplierAdmittanceTemplate.id}">修改</a>
					<a href="${ctx}/quality/qmsuppliertemp/qmSupplierAdmittanceTemplate/delete?id=${qmSupplierAdmittanceTemplate.id}" onclick="return confirmx('确认要删除该供应商文件模板吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>