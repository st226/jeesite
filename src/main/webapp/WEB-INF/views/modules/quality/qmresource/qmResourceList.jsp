<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>技术文件管理管理</title>
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
		<li class="active"><a href="${ctx}/quality/qmresource/qmResource/">技术文件管理列表</a></li>
		<shiro:hasPermission name="quality:qmresource:qmResource:edit"><li><a href="${ctx}/quality/qmresource/qmResource/form">技术文件管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="qmResource" action="${ctx}/quality/qmresource/qmResource/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<form:hidden path="qmType" />
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
				<th>文件类型</th>
				<th>文件分类</th>
				<th>标准模板文件名称</th>
				<th>编制依据文件/标准</th>
				<th>文件</th>
				<shiro:hasPermission name="quality:qmresource:qmResource:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="qmResource">
			<tr>
				<td><a href="${ctx}/quality/qmresource/qmResource/form?id=${qmResource.id}">
					${qmResource.fileTypeName}
				</a></td>
				<td>
					${qmResource.qmTypeName}
				</td>
				<td>
					${qmResource.name}
				</td>
				<td>
					${qmResource.basis}
				</td>
				<td>
					<a href='${(qmResource.file).split("\\|")[1]}'>${qmResource.name}<i style="color: red;" class="icon-paper-clip"></i></a>
					
				</td>
				<shiro:hasPermission name="quality:qmresource:qmResource:edit"><td>
    				<a href="${ctx}/quality/qmresource/qmResource/form?id=${qmResource.id}">修改</a>
					<a href="${ctx}/quality/qmresource/qmResource/delete?id=${qmResource.id}" onclick="return confirmx('确认要删除该技术文件管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>