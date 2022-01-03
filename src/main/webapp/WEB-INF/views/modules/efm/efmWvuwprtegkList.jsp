<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>涉密文件扫描审批备案表管理</title>
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
		<li class="active"><a href="${ctx}/efm/efmWvuwprtegk/">涉密文件扫描审批备案表列表</a></li>
		<shiro:hasPermission name="efm:efmWvuwprtegk:edit"><li><a href="${ctx}/efm/efmWvuwprtegk/form">涉密文件扫描审批备案表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="efmWvuwprtegk" action="${ctx}/efm/efmWvuwprtegk/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>部门：</label>
				<form:input path="bumen" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="xingming" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>编号：</label>
				<form:input path="procSno" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>最高密级：</label>
				<form:input path="smwjzgmj" htmlEscape="false" maxlength="4" class="input-medium"/>
			</li>
			<li><label>用途：</label>
				<form:input path="yongtu" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>部门</th>
				<th>姓名</th>
				<th>编号</th>
				<th>文件类型</th>
				<th>申请日期</th>
				<th>用途</th>
				<th>电话</th>
				<th>更新时间</th>
				<shiro:hasPermission name="efm:efmWvuwprtegk:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="efmWvuwprtegk">
			<tr>
				<td><a href="${ctx}/efm/efmWvuwprtegk/form?id=${efmWvuwprtegk.id}">
					${efmWvuwprtegk.bumen}
				</a></td>
				<td>
					${efmWvuwprtegk.xingming}
				</td>
				<td>
					${efmWvuwprtegk.procSno}
				</td>
				<td>
					${efmWvuwprtegk.wenjianleixing}
				</td>
				<td>
					<fmt:formatDate value="${efmWvuwprtegk.shenqingriqi}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${efmWvuwprtegk.yongtu}
				</td>
				<td>
					${efmWvuwprtegk.dianhua}
				</td>
				<td>
					<fmt:formatDate value="${efmWvuwprtegk.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="efm:efmWvuwprtegk:edit"><td>
    				<a href="${ctx}/efm/efmWvuwprtegk/form?id=${efmWvuwprtegk.id}">修改</a>
					<a href="${ctx}/efm/efmWvuwprtegk/delete?id=${efmWvuwprtegk.id}" onclick="return confirmx('确认要删除该涉密文件扫描审批备案表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>