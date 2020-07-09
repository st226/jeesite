<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>试验数据管理</title>
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
		<li class="active"><a href="${ctx}/equipment/tsEquipment/">试验数据列表</a></li>
		<shiro:hasPermission name="equipment:tsEquipment:edit"><li><a href="${ctx}/equipment/tsEquipment/form">试验数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tsEquipment" action="${ctx}/equipment/tsEquipment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>试验名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>试验地</th>
				<th>试验编号</th>
				<th>试验名称</th>
				<th>试验类型</th>
				<th>试验分类</th>
				<th>试验号</th>
				<th>试验人</th>
				<th>试验品相</th>
				<th>试验数据</th>
				<th>试验名称</th>
				<th>试验单价</th>
				<th>试验日期</th>
				<th>试验介绍</th>
				<th>实验内容</th>
				<th>试验状态</th>
				<th>采集时间</th>
				<th>排序</th>
				<shiro:hasPermission name="equipment:tsEquipment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tsEquipment">
			<tr>
				<td><a href="${ctx}/equipment/tsEquipment/form?id=${tsEquipment.id}">
					${tsEquipment.local}
				</a></td>
				<td>
					${tsEquipment.number}
				</td>
				<td>
					${tsEquipment.name}
				</td>
				<td>
					${tsEquipment.busType}
				</td>
				<td>
					${tsEquipment.classification}
				</td>
				<td>
					${tsEquipment.keyCode}
				</td>
				<td>
					${tsEquipment.resourceCode}
				</td>
				<td>
					${tsEquipment.author}
				</td>
				<td>
					${tsEquipment.press}
				</td>
				<td>
					${tsEquipment.isbn}
				</td>
				<td>
					${tsEquipment.price}
				</td>
				<td>
					${tsEquipment.publicationDate}
				</td>
				<td>
					${tsEquipment.keyword}
				</td>
				<td>
					${tsEquipment.zyAbstract}
				</td>
				<td>
					${tsEquipment.bmState}
				</td>
				<td>
					${tsEquipment.storageTime}
				</td>
				<td>
					${tsEquipment.sort}
				</td>
				<shiro:hasPermission name="equipment:tsEquipment:edit"><td>
    				<a href="${ctx}/equipment/tsEquipment/form?id=${tsEquipment.id}">修改</a>
					<a href="${ctx}/equipment/tsEquipment/delete?id=${tsEquipment.id}" onclick="return confirmx('确认要删除该试验数据吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>