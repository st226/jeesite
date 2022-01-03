<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单表管理</title>
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
		<li class="active"><a href="${ctx}/efm/efmJmaezctiei/">单表列表</a></li>
		<shiro:hasPermission name="efm:efmJmaezctiei:edit"><li><a href="${ctx}/efm/efmJmaezctiei/form">单表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="efmJmaezctiei" action="${ctx}/efm/efmJmaezctiei/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>填报部门：</label>
				<form:input path="tianbaobumen" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>填报日期：</label>
				<input name="tianbaoriqi" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${efmJmaezctiei.tianbaoriqi}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>承办人：</label>
				<form:input path="chengbanren" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>事项名称：</label>
				<form:input path="sxmc" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>拟定密级：</label>
				<form:input path="nidingmiji" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>更新时间</th>
				<th>流程编号</th>
				<th>填报部门</th>
				<th>填报日期</th>
				<th>承办人</th>
				<th>联系电话</th>
				<th>国家秘密类型</th>
				<th>事项名称</th>
				<th>拟定密级</th>
				<th>保密期限</th>
				<th>知悉范围</th>
				<th>定密依据</th>
				<th>版本</th>
				<shiro:hasPermission name="efm:efmJmaezctiei:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="efmJmaezctiei">
			<tr>
				<td><a href="${ctx}/efm/efmJmaezctiei/form?id=${efmJmaezctiei.id}">
					<fmt:formatDate value="${efmJmaezctiei.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${efmJmaezctiei.procSno}
				</td>
				<td>
					${efmJmaezctiei.tianbaobumen}
				</td>
				<td>
					<fmt:formatDate value="${efmJmaezctiei.tianbaoriqi}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${efmJmaezctiei.chengbanren}
				</td>
				<td>
					${efmJmaezctiei.lianxidianhua}
				</td>
				<td>
					${efmJmaezctiei.gjmmlx}
				</td>
				<td>
					${efmJmaezctiei.sxmc}
				</td>
				<td>
					${efmJmaezctiei.nidingmiji}
				</td>
				<td>
					${efmJmaezctiei.baomiqixian}
				</td>
				<td>
					${efmJmaezctiei.zhixifanwei}
				</td>
				<td>
					${efmJmaezctiei.dingmiyiju}
				</td>
				<td>
					${efmJmaezctiei.version}
				</td>
				<shiro:hasPermission name="efm:efmJmaezctiei:edit"><td>
    				<a href="${ctx}/efm/efmJmaezctiei/form?id=${efmJmaezctiei.id}">修改</a>
					<a href="${ctx}/efm/efmJmaezctiei/delete?id=${efmJmaezctiei.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>