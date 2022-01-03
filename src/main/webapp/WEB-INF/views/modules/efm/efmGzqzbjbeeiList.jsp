<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>涉密载体送外印制申请管理</title>
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
		<li class="active"><a href="${ctx}/efm/efmGzqzbjbeei/">涉密载体送外印制申请列表</a></li>
		<shiro:hasPermission name="efm:efmGzqzbjbeei:edit"><li><a href="${ctx}/efm/efmGzqzbjbeei/form">涉密载体送外印制申请添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="efmGzqzbjbeei" action="${ctx}/efm/efmGzqzbjbeei/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>编号：</label>
				<form:input path="procSno" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>申请人：</label>
				<form:input path="shenqingren" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>申请部门：</label>
				<form:input path="shenqingbumen" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>人员密级：</label>
				<form:input path="renyuanmiji" htmlEscape="false" maxlength="4" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>编号</th>
				<th>更新时间</th>
				<th>申请人</th>
				<th>申请时间</th>
				<th>申请部门</th>
				<th>人员密级</th>
				<th>送外印制用途</th>
				<shiro:hasPermission name="efm:efmGzqzbjbeei:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="efmGzqzbjbeei">
			<tr>
				<td><a href="${ctx}/efm/efmGzqzbjbeei/form?id=${efmGzqzbjbeei.id}">
					${efmGzqzbjbeei.procSno}
				</a></td>
				<td>
					<fmt:formatDate value="${efmGzqzbjbeei.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${efmGzqzbjbeei.shenqingren}
				</td>
				<td>
					<fmt:formatDate value="${efmGzqzbjbeei.shenqingshijian}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${efmGzqzbjbeei.shenqingbumen}
				</td>
				<td>
					${efmGzqzbjbeei.renyuanmiji}
				</td>
				<td>
					${efmGzqzbjbeei.swyzyt}
				</td>
				<shiro:hasPermission name="efm:efmGzqzbjbeei:edit"><td>
    				<a href="${ctx}/efm/efmGzqzbjbeei/form?id=${efmGzqzbjbeei.id}">修改</a>
					<a href="${ctx}/efm/efmGzqzbjbeei/delete?id=${efmGzqzbjbeei.id}" onclick="return confirmx('确认要删除该涉密载体送外印制申请吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>