<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>外来载体入账登记申请管理</title>
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
		<li class="active"><a href="${ctx}/efm/efmPdlg1he6zd/">外来载体入账登记申请列表</a></li>
		<shiro:hasPermission name="efm:efmPdlg1he6zd:edit"><li><a href="${ctx}/efm/efmPdlg1he6zd/form">外来载体入账登记申请添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="efmPdlg1he6zd" action="${ctx}/efm/efmPdlg1he6zd/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>入账时间：</label>
				<input name="ruzhangshijian" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${efmPdlg1he6zd.ruzhangshijian}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>编号：</label>
				<form:input path="procSno" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>责任部门：</label>
				<form:input path="bumen" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>责任人：</label>
				<form:input path="zerenren" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>入账时间</th>
				<th>编号</th>
				<th>更新时间</th>
				<th>责任部门</th>
				<th>责任人</th>
				<shiro:hasPermission name="efm:efmPdlg1he6zd:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="efmPdlg1he6zd">
			<tr>
				<td><a href="${ctx}/efm/efmPdlg1he6zd/form?id=${efmPdlg1he6zd.id}">
					<fmt:formatDate value="${efmPdlg1he6zd.ruzhangshijian}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${efmPdlg1he6zd.procSno}
				</td>
				<td>
					<fmt:formatDate value="${efmPdlg1he6zd.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${efmPdlg1he6zd.bumen}
				</td>
				<td>
					${efmPdlg1he6zd.zerenren}
				</td>
				<shiro:hasPermission name="efm:efmPdlg1he6zd:edit"><td>
    				<a href="${ctx}/efm/efmPdlg1he6zd/form?id=${efmPdlg1he6zd.id}">修改</a>
					<a href="${ctx}/efm/efmPdlg1he6zd/delete?id=${efmPdlg1he6zd.id}" onclick="return confirmx('确认要删除该外来载体入账登记申请吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>