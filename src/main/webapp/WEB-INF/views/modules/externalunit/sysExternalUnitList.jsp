<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>外单位信息维护管理</title>
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
		<li class="active"><a href="${ctx}/externalunit/sysExternalUnit/">外单位信息维护列表</a></li>
		<shiro:hasPermission name="externalunit:sysExternalUnit:edit"><li><a href="${ctx}/externalunit/sysExternalUnit/form">外单位信息维护添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysExternalUnit" action="${ctx}/externalunit/sysExternalUnit/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>单位名称：</label>
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
				<th>单位名称</th>
				<th>备注</th>
				<shiro:hasPermission name="externalunit:sysExternalUnit:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysExternalUnit">
			<tr>
				<td><a href="${ctx}/externalunit/sysExternalUnit/form?id=${sysExternalUnit.id}">
					${sysExternalUnit.name}
				</a></td>
				<td>
					${sysExternalUnit.remarks}
				</td>
				<shiro:hasPermission name="externalunit:sysExternalUnit:edit"><td>
    				<a href="${ctx}/externalunit/sysExternalUnit/form?id=${sysExternalUnit.id}">修改</a>
					<a href="${ctx}/externalunit/sysExternalUnit/delete?id=${sysExternalUnit.id}" onclick="return confirmx('确认要删除该外单位信息维护吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>