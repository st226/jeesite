<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>测试主子表管理</title>
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
		<li class="active"><a href="${ctx}/suntao_test/suntaoTest/">测试主子表列表</a></li>
		<shiro:hasPermission name="suntao_test:suntaoTest:edit"><li><a href="${ctx}/suntao_test/suntaoTest/form">测试主子表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="suntaoTest" action="${ctx}/suntao_test/suntaoTest/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>name：</label>
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
				<th>name</th>
				<th>remarks</th>
				<shiro:hasPermission name="suntao_test:suntaoTest:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="suntaoTest">
			<tr>
				<td><a href="${ctx}/suntao_test/suntaoTest/form?id=${suntaoTest.id}">
					${suntaoTest.name}
				</a></td>
				<td>
					${suntaoTest.remarks}
				</td>
				<shiro:hasPermission name="suntao_test:suntaoTest:edit"><td>
    				<a href="${ctx}/suntao_test/suntaoTest/form?id=${suntaoTest.id}">修改</a>
					<a href="${ctx}/suntao_test/suntaoTest/delete?id=${suntaoTest.id}" onclick="return confirmx('确认要删除该测试主子表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>