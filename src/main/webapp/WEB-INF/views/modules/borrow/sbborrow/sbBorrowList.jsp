<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备借用管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			parent.window.jBox.close();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function test(pid){
			top.$.jBox.open("iframe:http://127.0.0.1:8088/default/archives/archives/imageFlow.jsp?id="+pid, "流程跟踪",810,$(top.document).height()-110);
			
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/borrow/sbborrow/sbBorrow/">设备借用列表</a></li>
	
	</ul>
	<form:form id="searchForm" modelAttribute="sbBorrow" action="${ctx}/borrow/sbborrow/sbBorrow/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>借用部门：</label>
				<sys:treeselect id="office" name="office.id" value="${sbBorrow.office.id}" labelName="office.name" labelValue="${sbBorrow.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>借用人：</label>
				<sys:treeselect id="user" name="user.id" value="${sbBorrow.user.id}" labelName="user.name" labelValue="${sbBorrow.user.name}"
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
				
				<th>借用部门</th>
				<th>借用人</th>
				<th>申请时间</th>
				<th>借用地方</th>
				<th>借用到</th>
				<th>状态</th>
				<shiro:hasPermission name="borrow:sbborrow:sbBorrow:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sbBorrow">
			<tr>
				
				<td>
					${sbBorrow.office.name}
				</td>
				<td>
					${sbBorrow.user.name}
				</td>
				<td>
					<fmt:formatDate value="${sbBorrow.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${sbBorrow.useLocal}
				</td>
				<td>
					<fmt:formatDate value="${sbBorrow.borrowTo}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${fns:getDictLabel(sbBorrow.state, 'borrow_state', '')}
			</td>
				<shiro:hasPermission name="borrow:sbborrow:sbBorrow:edit"><td>
    				<a href="${ctx}/borrow/sbborrow/sbBorrow/view?id=${sbBorrow.id}">详情</a>
					 <a target="_blank" onclick="test(${sbBorrow.processinstid})" >跟踪</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>