<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商管理管理</title>
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
		<li class="active"><a href="${ctx}/supplier/tsSupplier/">供应商管理列表</a></li>
		<shiro:hasPermission name="supplier:tsSupplier:edit"><li><a href="${ctx}/supplier/tsSupplier/form">供应商管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tsSupplier" action="${ctx}/supplier/tsSupplier/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>供应商名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
		<!--	<li><label>供应商编号：</label>
				<form:input path="code" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>-->
			<li><label>联系人：</label>
				<form:input path="contactMan" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>供应商名称</th>
			<!--	<th>供应商编号</th>-->
				<th>联系人</th>
				<th>联系电话</th>
				<th>联系地址</th>
				
				<th>供应商状态</th>
				<th>邮箱</th>
				<th>创建人</th>
				<th>创建时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="supplier:tsSupplier:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tsSupplier">
			<tr>
				<td><a href="${ctx}/supplier/tsSupplier/form?id=${tsSupplier.id}">
					${tsSupplier.name}
				</a></td>
			<!--	<td>
					${tsSupplier.code}
				</td>-->
				<td>
					${tsSupplier.contactMan}
				</td>
				<td>
					${tsSupplier.contactNumber}
				</td>
				<td>
					${tsSupplier.contactAdd}
				</td>
				
				<td>
					${fns:getDictLabel(tsSupplier.gysState, 'gys_type', '')}
				</td>
				<td>
					${tsSupplier.mailbox}
				</td>
				<td>
					${tsSupplier.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${tsSupplier.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${tsSupplier.remarks}
				</td>
				<shiro:hasPermission name="supplier:tsSupplier:edit"><td>
    				<a href="${ctx}/supplier/tsSupplier/form?id=${tsSupplier.id}">修改</a>
					<a href="${ctx}/supplier/tsSupplier/delete?id=${tsSupplier.id}" onclick="return confirmx('确认要删除该供应商管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>