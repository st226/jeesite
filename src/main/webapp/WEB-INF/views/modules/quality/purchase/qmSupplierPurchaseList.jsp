<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合格供方目录外采购申请表管理</title>
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
		<li class="active"><a href="${ctx}/quality/purchase/qmSupplierPurchase/">合格供方目录外采购申请表列表</a></li>
		<shiro:hasPermission name="quality:purchase:qmSupplierPurchase:edit"><li><a href="${ctx}/quality/purchase/qmSupplierPurchase/form">合格供方目录外采购申请表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="qmSupplierPurchase" action="${ctx}/quality/purchase/qmSupplierPurchase/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>产品名称：</label>
				<form:input path="productName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>生产厂家：</label>
				<form:input path="enterpriseName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>企业性质：</label>
				<form:select path="enterpriseNature" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('supplier_nature')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程型号：</label>
				<form:input path="projectModel" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>产品名称</th>
				<th>产品规格/技术标准/图纸/任务书</th>
				<th>质量等级</th>
				<th>生产厂家</th>
				<th>企业性质</th>
				<th>工程型号</th>
				<th>单机名称</th>
				<th>单机用量</th>
				<th>申请人</th>
				<th>申请状态</th>
				<shiro:hasPermission name="quality:purchase:qmSupplierPurchase:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="qmSupplierPurchase">
			<tr>
				<td>
					${qmSupplierPurchase.productName}
			</td>
				<td>
					${qmSupplierPurchase.productType}
				</td>
				<td>
					${qmSupplierPurchase.productClass}
				</td>
				<td>
					${qmSupplierPurchase.enterpriseName}
				</td>
				<td>
					${fns:getDictLabel(qmSupplierPurchase.enterpriseNature, 'supplier_nature', '')}
				</td>
				<td>
					${qmSupplierPurchase.projectModel}
				</td>
				<td>
					${qmSupplierPurchase.standName}
				</td>
				<td>
					${qmSupplierPurchase.standDosage}
				</td>
				<td>
					${qmSupplierPurchase.applicant}
				</td>
				<td>
					${fns:getDictLabel(qmSupplierPurchase.state, 'pstate', '')}
				</td>
				<shiro:hasPermission name="quality:purchase:qmSupplierPurchase:edit"><td>
    				<a href="${ctx}/quality/purchase/qmSupplierPurchase/form?id=${qmSupplierPurchase.id}">${not empty qmSupplierPurchase.processinstid?'':'修改'}</a>
					<a href="${ctx}/quality/purchase/qmSupplierPurchase/delete?id=${qmSupplierPurchase.id}" onclick="return confirmx('确认要删除该合格供方目录外采购申请表吗？', this.href)">${not empty qmSupplierPurchase.processinstid?'':'删除'}</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>