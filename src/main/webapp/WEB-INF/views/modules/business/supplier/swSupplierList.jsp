<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商管理</title>
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
		<li class="active"><a href="${ctx}/business/supplier/swSupplier/">供应商列表</a></li>
		<shiro:hasPermission name="business:supplier:swSupplier:edit"><li><a href="${ctx}/business/supplier/swSupplier/form">供应商添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="swSupplier" action="${ctx}/business/supplier/swSupplier/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>供应商名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>供应商编号：</label>
				<form:input path="code" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th>供应商名称</th>
			
				<th>供应商电话</th>
				<th>公司地址</th>
				<th>供应商税号</th>
				<th>供应商类型</th>
				<th>联系人</th>
				<th>联系人电话</th>
				<shiro:hasPermission name="business:supplier:swSupplier:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="swSupplier" varStatus="vs">
			<tr>
			   <td>
                         <input type="checkbox" name="checkbox_name[${vs.index}]"/>
                     
						 <input type="hidden" name="columnList[${vs.index}].id" value="${swSupplier.id}"/>
						 <input type="hidden" name="columnList[${vs.index}].name" value="${swSupplier.name}"/>
						 <input type="hidden" name="columnList[${vs.index}].supplierUser" value="${swSupplier.supplierUser}"/>
						 <input type="hidden" name="columnList[${vs.index}].userPhone" value="${swSupplier.userPhone}"/>
                 </td>
				<td>
				  
				<a href="${ctx}/business/supplier/swSupplier/form?id=${swSupplier.id}">
					${swSupplier.name}
				</a></td>
			
				<td>
					${swSupplier.phone}
				</td>
				<td>
					${swSupplier.local}
				</td>
				<td>
					${swSupplier.dutyno}
				</td>
				<td>
					${fns:getDictLabel(swSupplier.supplierType, 'supplier_type', '')}
				</td>
				<td>
					${swSupplier.supplierUser}
				</td>
				<td>
					${swSupplier.userPhone}
				</td>
				<shiro:hasPermission name="business:supplier:swSupplier:edit"><td>
    				<a href="${ctx}/business/supplier/swSupplier/form?id=${swSupplier.id}">修改</a>
					<a href="${ctx}/business/supplier/swSupplier/delete?id=${swSupplier.id}" onclick="return confirmx('确认要删除该供应商吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>