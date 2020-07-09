<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报销单管理</title>
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
		<li class="active"><a href="${ctx}/business/reimbursement/swReimbursement/">报销单列表</a></li>
			</ul>
	<form:form id="searchForm" modelAttribute="swReimbursement" action="${ctx}/business/reimbursement/swReimbursement/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>结算单位：</label>
				<form:input path="supplierName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
	
				<th>结算单位</th>
				<th>收支号</th>
				<th>项目编号</th>
				<th>开支项目</th>
				<th>质量成本</th>
				<th>质量成本</th>
				<shiro:hasPermission name="business:reimbursement:swReimbursement:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="swReimbursement">
			<tr>
				
				<td>
					${swReimbursement.supplierName}
				</td>
				<td>
					${swReimbursement.mark}
				</td>
				<td>
					${swReimbursement.projectCode}
				</td>
				<td>
					${swReimbursement.projectName}
				</td>
				<td>
					${fns:getDictLabel(swReimbursement.isQuality, 'is_leaf', '')}
				</td>
				<td>
					${swReimbursement.quality}
				</td>
				<shiro:hasPermission name="business:reimbursement:swReimbursement:edit"><td>
    				<a href="${ctx}/business/reimbursement/swReimbursement/form?id=${swReimbursement.id}">查看</a>
							</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>