<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同管理</title>
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
		<li class="active"><a href="${ctx}/business/contract/swContract/">合同列表</a></li>
		<shiro:hasPermission name="business:contract:swContract:edit"><li><a href="${ctx}/business/contract/swContract/form">合同添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="swContract" action="${ctx}/business/contract/swContract/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>合同编号：</label>
				<form:input path="contractCode" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>合同名称：</label>
				<form:input path="contractName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>合同类型：</label>
				<form:select path="contractType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('contract_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			<th>选择</th>
				<th>合同编号</th>
				<th>合同名称</th>
				<th>合同类型</th>
				<th>合同价款</th>
				<th>对方单位</th>
				<th>联系人</th>
				<th>联系电话</th>
				<shiro:hasPermission name="business:contract:swContract:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="swContract" varStatus="vs">
			<tr>
			      <td>
                         <input type="checkbox" name="checkbox_name[${vs.index}]"/>
                     
						 <input type="hidden" name="columnList[${vs.index}].id" value="${swContract.id}"/>
						 <input type="hidden" name="columnList[${vs.index}].contractName" value="${swContract.contractName}"/>
						 <input type="hidden" name="columnList[${vs.index}].supplierUser" value="${swContract.supplierUser}"/>
						 <input type="hidden" name="columnList[${vs.index}].supplierId" value="${swContract.supplierId}"/>
						 <input type="hidden" name="columnList[${vs.index}].supplierName" value="${swContract.supplierName}"/>
						 <input type="hidden" name="columnList[${vs.index}].supplierPhone" value="${swContract.supplierPhone}"/>
						 
						 <input type="hidden" name="columnList[${vs.index}].contractCode" value="${swContract.contractCode}"/>
						 <input type="hidden" name="columnList[${vs.index}].contractPrice" value="${swContract.contractPrice}"/>
                 </td>
				<td><a href="${ctx}/business/contract/swContract/form?id=${swContract.id}">
					${swContract.contractCode}
				</a></td>
				<td>
					${swContract.contractName}
				</td>
				<td>
					${fns:getDictLabel(swContract.contractType, 'contract_type', '')}
				</td>
				<td>
					${swContract.contractPrice}
				</td>
				<td>
					${swContract.supplierName}
				</td>
				<td>
					${swContract.supplierUser}
				</td>
				<td>
					${swContract.supplierPhone}
				</td>
				<shiro:hasPermission name="business:contract:swContract:edit"><td>
    				<a href="${ctx}/business/contract/swContract/form?id=${swContract.id}">修改</a>
    				<a href="${ctx}/business/contract/swContract/view?id=${swContract.id}">打印</a>
					<a href="${ctx}/business/contract/swContract/delete?id=${swContract.id}" onclick="return confirmx('确认要删除该合同吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>