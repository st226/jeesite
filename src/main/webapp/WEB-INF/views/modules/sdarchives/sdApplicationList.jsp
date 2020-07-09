<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>三单维护管理</title>
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
		<li class="active"><a href="${ctx}/sdarchives/sdApplication/plList">偏离单维护列表</a></li>
		<shiro:hasPermission name="sdarchives:sdApplication:edit"><li><a href="${ctx}/sdarchives/sdApplication/form">偏离单维护添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sdApplication" action="${ctx}/sdarchives/sdApplication/plList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>编号：</label>
				<form:input path="code" htmlEscape="false" maxlength="55" class="input-medium"/>
			</li>
			<li><label>申请时间：</label>
				<input name="apllyDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${sdApplication.apllyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>申请人：</label>
				<sys:treeselect id="user" name="user.id" value="${sdApplication.user.id}" labelName="user.name" labelValue="${sdApplication.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>设计人员</th>
				
				<th>申请时间</th>
				
				<th>申请部门</th>
				<th>申请人</th>
				<shiro:hasPermission name="sdarchives:sdApplication:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sdApplication">
			<tr>
				<td><a href="${ctx}/sdarchives/sdApplication/form?id=${sdApplication.id}">
					${sdApplication.applyPersonsj}
				</a></td>
				
				<td>
					<fmt:formatDate value="${sdApplication.apllyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				<td>
					${sdApplication.office.name}
				</td>
				<td>
					${sdApplication.user.name}
				</td>
				<shiro:hasPermission name="sdarchives:sdApplication:edit"><td>
    				<a href="${ctx}/sdarchives/sdApplication/form?id=${sdApplication.id}">修改</a>
					<a href="${ctx}/sdarchives/sdApplication/delete?id=${sdApplication.id}" onclick="return confirmx('确认要删除该三单维护吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>