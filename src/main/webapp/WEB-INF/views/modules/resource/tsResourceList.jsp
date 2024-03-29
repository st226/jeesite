<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图书馆资源管理管理</title>
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
		<li class="active"><a href="${ctx}/resource/tsResource/">图书馆资源管理列表</a></li>
		<shiro:hasPermission name="resource:tsResource:edit"><li><a href="${ctx}/resource/tsResource/form?busType=${busType}">图书馆资源管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tsResource" action="${ctx}/resource/tsResource/" method="post" class="breadcrumb form-search">
		<input id="busType" name="busType" type="hidden" value="${busType}"/>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>资源名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			<c:forEach items="${TsResourceBus}" var="tsResource">
			<th>${tsResource.columnComments}</th>
			</c:forEach>
				<shiro:hasPermission name="resource:tsResource:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="maps">
			<tr>
			    <c:forEach items="${TsResourceBus}" var="tsResource">
			     <td>    ${maps[tsResource.columnName]}  </td>
               </c:forEach>  
				
				<shiro:hasPermission name="resource:tsResource:edit"><td>
    				<a href="${ctx}/resource/tsResource/form?id=${maps.id}&busType=${busType}">修改</a>
					<a href="${ctx}/resource/tsResource/delete?id=${maps.id}&busType=${busType}" onclick="return confirmx('确认要删除该图书馆资源管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>