<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保密本领用申请管理</title>
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
		<li class="active"><a href="${ctx}/efm/efmMjkzranviu/">保密本领用申请列表</a></li>
		<shiro:hasPermission name="efm:efmMjkzranviu:edit"><li><a href="${ctx}/efm/efmMjkzranviu/form">保密本领用申请添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="efmMjkzranviu" action="${ctx}/efm/efmMjkzranviu/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>申请人：</label>
				<form:input path="shenqingren" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>申请部门：</label>
				<form:input path="shenqingbumen" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>编号：</label>
				<form:input path="procSno" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>保密本编号：</label>
				<form:input path="baomibenbianhao" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>申请人</th>
				<th>申请部门</th>
				<th>申请日期</th>
				<th>编号</th>
				<th>更新时间</th>
				<th>申请人密级</th>
				<th>保密本密级</th>
				<th>保密本编号</th>
				<shiro:hasPermission name="efm:efmMjkzranviu:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="efmMjkzranviu">
			<tr>
				<td><a href="${ctx}/efm/efmMjkzranviu/form?id=${efmMjkzranviu.id}">
					${efmMjkzranviu.shenqingren}
				</a></td>
				<td>
					${efmMjkzranviu.shenqingbumen}
				</td>
				<td>
					<fmt:formatDate value="${efmMjkzranviu.shenqingriqi}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${efmMjkzranviu.procSno}
				</td>
				<td>
					<fmt:formatDate value="${efmMjkzranviu.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${efmMjkzranviu.shenqingrenmiji}
				</td>
				<td>
					${efmMjkzranviu.baomibenmiji}
				</td>
				<td>
					${efmMjkzranviu.baomibenbianhao}
				</td>
				<shiro:hasPermission name="efm:efmMjkzranviu:edit"><td>
    				<a href="${ctx}/efm/efmMjkzranviu/form?id=${efmMjkzranviu.id}">修改</a>
					<a href="${ctx}/efm/efmMjkzranviu/delete?id=${efmMjkzranviu.id}" onclick="return confirmx('确认要删除该保密本领用申请吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>