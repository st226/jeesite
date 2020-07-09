<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备维修申请管理</title>
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
		<li class="active"><a href="${ctx}/equipment/repair/sbEquipmentRepair/">设备维修申请列表</a></li>
		<shiro:hasPermission name="equipment:repair:sbEquipmentRepair:edit"><li><a href="${ctx}/equipment/repair/sbEquipmentRepair/form">设备维修申请添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sbEquipmentRepair" action="${ctx}/equipment/repair/sbEquipmentRepair/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>设备名称：</label>
				<form:input path="equipmentName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>设备数量：</label>
				<form:input path="equipmentAmount" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>申请人：</label>
				<form:input path="userName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>联系电话：</label>
				<form:input path="userPhone" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>申请部门</th>
				<th>设备名称</th>
				<th>设备数量</th>
				<th>型号规格</th>
				<th>生产厂家</th>
				<th>维修预算费用</th>
				<th>申请人</th>
				<th>联系电话</th>
				<th>状态</th>
				<shiro:hasPermission name="equipment:repair:sbEquipmentRepair:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sbEquipmentRepair">
			<tr>
				<td><a href="${ctx}/equipment/repair/sbEquipmentRepair/form?id=${sbEquipmentRepair.id}">
					${sbEquipmentRepair.office.name}
				</a></td>
				<td>
					${sbEquipmentRepair.equipmentName}
				</td>
				<td>
					${sbEquipmentRepair.equipmentAmount}
				</td>
				<td>
					${sbEquipmentRepair.equipmentType}
				</td>
				<td>
					${sbEquipmentRepair.equipmentMade}
				</td>
				<td>
					${sbEquipmentRepair.equipmentBudgt}
				</td>
				<td>
					${sbEquipmentRepair.userName}
				</td>
				<td>
					${sbEquipmentRepair.userPhone}
				</td>
				<td>
					${fns:getDictLabel(sbEquipmentRepair.state,'repair_state','')}
				</td>
				<shiro:hasPermission name="equipment:repair:sbEquipmentRepair:edit"><td>
    				<a href="${ctx}/equipment/repair/sbEquipmentRepair/form?id=${sbEquipmentRepair.id}">修改</a>
    				<a href="${ctx}/equipment/repair/sbEquipmentRepair/view?id=${sbEquipmentRepair.id}">打印</a>
					<a href="${ctx}/equipment/repair/sbEquipmentRepair/delete?id=${sbEquipmentRepair.id}" onclick="return confirmx('确认要删除该设备维修申请吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>