<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>国家秘密事项脱密处理审批表管理</title>
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
		<li class="active"><a href="${ctx}/efm/efmJueltygvaw/">国家秘密事项脱密处理审批表列表</a></li>
		<shiro:hasPermission name="efm:efmJueltygvaw:edit"><li><a href="${ctx}/efm/efmJueltygvaw/form">国家秘密事项脱密处理审批表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="efmJueltygvaw" action="${ctx}/efm/efmJueltygvaw/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>承办人：</label>
				<form:input path="chengbanren" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>承办部门：</label>
				<form:input path="chengbanbumen" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>事项名称：</label>
				<form:input path="sxmc" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>原定密级：</label>
				<form:input path="miji" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>承办人</th>
				<th>承办部门</th>
				<th>事项名称</th>
				<th>原定密级</th>
				<th>原定保密期限</th>
				<th>知悉范围</th>
				<th>脱密处理理由</th>
				<th>备注</th>
				<th>更新时间</th>
				<shiro:hasPermission name="efm:efmJueltygvaw:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="efmJueltygvaw">
			<tr>
				<td><a href="${ctx}/efm/efmJueltygvaw/form?id=${efmJueltygvaw.id}">
					${efmJueltygvaw.chengbanren}
				</a></td>
				<td>
					${efmJueltygvaw.chengbanbumen}
				</td>
				<td>
					${efmJueltygvaw.sxmc}
				</td>
				<td>
					${efmJueltygvaw.miji}
				</td>
				<td>
					${efmJueltygvaw.ydbmqx}
				</td>
				<td>
					${efmJueltygvaw.zhixifanwei}
				</td>
				<td>
					${efmJueltygvaw.tuomichuliliyou}
				</td>
				<td>
					${efmJueltygvaw.beizhu}
				</td>
				<td>
					<fmt:formatDate value="${efmJueltygvaw.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="efm:efmJueltygvaw:edit"><td>
    				<a href="${ctx}/efm/efmJueltygvaw/form?id=${efmJueltygvaw.id}">修改</a>
					<a href="${ctx}/efm/efmJueltygvaw/delete?id=${efmJueltygvaw.id}" onclick="return confirmx('确认要删除该国家秘密事项脱密处理审批表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>