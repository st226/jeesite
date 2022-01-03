<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>涉密载体归档管理管理</title>
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
		<li class="active"><a href="${ctx}/efm/efmLnydoispac/">涉密载体归档管理列表</a></li>
		<shiro:hasPermission name="efm:efmLnydoispac:edit"><li><a href="${ctx}/efm/efmLnydoispac/form">涉密载体归档管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="efmLnydoispac" action="${ctx}/efm/efmLnydoispac/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>载体名称：</label>
				<form:input path="zaitimingcheng" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>载体编号：</label>
				<form:input path="zaitibianhao" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>密级：</label>
				<form:input path="miji" htmlEscape="false" maxlength="4" class="input-medium"/>
			</li>
			<li><label>载体状态：</label>
				<form:input path="zaitizhuangtai" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>载体名称</th>
				<th>载体编号</th>
				<th>密级</th>
				<th>份数</th>
				<th>页数</th>
				<th>更新时间</th>
				<th>载体状态</th>
				<shiro:hasPermission name="efm:efmLnydoispac:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="efmLnydoispac">
			<tr>
				<td><a href="${ctx}/efm/efmLnydoispac/form?id=${efmLnydoispac.id}">
					${efmLnydoispac.zaitimingcheng}
				</a></td>
				<td>
					${efmLnydoispac.zaitibianhao}
				</td>
				<td>
					${efmLnydoispac.miji}
				</td>
				<td>
					${efmLnydoispac.fenshu}
				</td>
				<td>
					${efmLnydoispac.yeshu}
				</td>
				<td>
					<fmt:formatDate value="${efmLnydoispac.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${efmLnydoispac.zaitizhuangtai}
				</td>
				<shiro:hasPermission name="efm:efmLnydoispac:edit"><td>
    				<a href="${ctx}/efm/efmLnydoispac/form?id=${efmLnydoispac.id}">修改</a>
					<a href="${ctx}/efm/efmLnydoispac/delete?id=${efmLnydoispac.id}" onclick="return confirmx('确认要删除该涉密载体归档管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>