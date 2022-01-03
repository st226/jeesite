<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>列表保存管理</title>
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
		<li class="active"><a href="${ctx}/efm/efmIyuuy5h4h9/">列表保存列表</a></li>
		<shiro:hasPermission name="efm:efmIyuuy5h4h9:edit"><li><a href="${ctx}/efm/efmIyuuy5h4h9/form">列表保存添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="efmIyuuy5h4h9" action="${ctx}/efm/efmIyuuy5h4h9/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>承办人：</label>
				<form:input path="chengbanren" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>承办部门：</label>
				<form:input path="chengbanbumen" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>事项名称：</label>
				<form:input path="sxmc" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>变更后密级：</label>
				<form:input path="miji2" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>流程编号</th>
				<th>承办人</th>
				<th>承办部门</th>
				<th>事项名称</th>
				<th>密级</th>
				<th>变更后密级</th>
				<th>保密期限</th>
				<th>变更后保密期限</th>
				<th>变更前知悉范围</th>
				<th>变更后知悉范围</th>
				<th>变更理由</th>
				<th>备注</th>
				<th>更新时间</th>
				<shiro:hasPermission name="efm:efmIyuuy5h4h9:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="efmIyuuy5h4h9">
			<tr>
				<td><a href="${ctx}/efm/efmIyuuy5h4h9/form?id=${efmIyuuy5h4h9.id}">
					${efmIyuuy5h4h9.procSno}
				</a></td>
				<td>
					${efmIyuuy5h4h9.chengbanren}
				</td>
				<td>
					${efmIyuuy5h4h9.chengbanbumen}
				</td>
				<td>
					${efmIyuuy5h4h9.sxmc}
				</td>
				<td>
					${efmIyuuy5h4h9.miji}
				</td>
				<td>
					${efmIyuuy5h4h9.miji2}
				</td>
				<td>
					${efmIyuuy5h4h9.baomiqixian}
				</td>
				<td>
					<fmt:formatDate value="${efmIyuuy5h4h9.baomiqixian2}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${efmIyuuy5h4h9.bgqzxfw}
				</td>
				<td>
					${efmIyuuy5h4h9.bghzxfw}
				</td>
				<td>
					${efmIyuuy5h4h9.biangengliyou}
				</td>
				<td>
					${efmIyuuy5h4h9.beizhu}
				</td>
				<td>
					${efmIyuuy5h4h9.updateDate}
				</td>
				<shiro:hasPermission name="efm:efmIyuuy5h4h9:edit"><td>
    				<a href="${ctx}/efm/efmIyuuy5h4h9/form?id=${efmIyuuy5h4h9.id}">修改</a>
					<a href="${ctx}/efm/efmIyuuy5h4h9/delete?id=${efmIyuuy5h4h9.id}" onclick="return confirmx('确认要删除该列表保存吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>