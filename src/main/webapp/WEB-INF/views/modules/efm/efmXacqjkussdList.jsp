<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>涉密载体对外移交审批表管理</title>
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
		<li class="active"><a href="${ctx}/efm/efmXacqjkussd/">涉密载体对外移交审批表列表</a></li>
		<shiro:hasPermission name="efm:efmXacqjkussd:edit"><li><a href="${ctx}/efm/efmXacqjkussd/form">涉密载体对外移交审批表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="efmXacqjkussd" action="${ctx}/efm/efmXacqjkussd/" method="post" class="breadcrumb form-search">
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
			<li><label>接收人：</label>
				<form:input path="jieshouren" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>移交部门：</label>
				<form:input path="yijiaobumen" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>载体最高密级：</label>
				<form:input path="zaitizuigaomiji" htmlEscape="false" maxlength="4" class="input-medium"/>
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
				<th>申请部门</th>
				<th>移交人</th>
				<th>接收人</th>
				<th>移交部门</th>
				<th>接收单位</th>
				<th>载体最高密级</th>
				<th>主管所领导</th>
				<shiro:hasPermission name="efm:efmXacqjkussd:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="efmXacqjkussd">
			<tr>
				<td><a href="${ctx}/efm/efmXacqjkussd/form?id=${efmXacqjkussd.id}">
					${efmXacqjkussd.procSno}
				</a></td>
				<td>
					<fmt:formatDate value="${efmXacqjkussd.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${efmXacqjkussd.shenqingren}
				</td>
				<td>
					${efmXacqjkussd.shenqingbumen}
				</td>
				<td>
					${efmXacqjkussd.yijiaoren}
				</td>
				<td>
					${efmXacqjkussd.jieshouren}
				</td>
				<td>
					${efmXacqjkussd.yijiaobumen}
				</td>
				<td>
					${efmXacqjkussd.jieshoudanwei}
				</td>
				<td>
					${efmXacqjkussd.zaitizuigaomiji}
				</td>
				<td>
					${efmXacqjkussd.zgsld}
				</td>
				<shiro:hasPermission name="efm:efmXacqjkussd:edit"><td>
    				<a href="${ctx}/efm/efmXacqjkussd/form?id=${efmXacqjkussd.id}">修改</a>
					<a href="${ctx}/efm/efmXacqjkussd/delete?id=${efmXacqjkussd.id}" onclick="return confirmx('确认要删除该涉密载体对外移交审批表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>