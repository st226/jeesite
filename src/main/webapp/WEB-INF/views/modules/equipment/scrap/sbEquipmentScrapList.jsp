<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备仪器报废（闲置）申请管理</title>
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
		<li class="active"><a href="${ctx}/equipment/scrap/sbEquipmentScrap/">设备仪器报废（闲置）申请列表</a></li>
		<shiro:hasPermission name="equipment:scrap:sbEquipmentScrap:edit"><li><a href="${ctx}/equipment/scrap/sbEquipmentScrap/form">设备仪器报废（闲置）申请添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sbEquipmentScrap" action="${ctx}/equipment/scrap/sbEquipmentScrap/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>申请部门：</label>
				<form:input path="officeName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>设备名称：</label>
				<form:input path="equipmentName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>设备编号：</label>
				<form:input path="equipmentCode" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>使用单位：</label>
				<form:input path="equipmentTeamName" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th>设备仪器名称</th>
				<th>国别厂家</th>
				<th>设备仪器编号</th>
				<th>购置年月</th>
				<th>出厂年月</th>
				<th>原值（万元）</th>
				<th>净值（万元）</th>
				<th>型号</th>
				<th>使用单位</th>
				<th>规格</th>
				<th>启用年月</th>
				<th>已使用年限</th>
				
				<shiro:hasPermission name="equipment:scrap:sbEquipmentScrap:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sbEquipmentScrap">
			<tr>
				<td><a href="${ctx}/equipment/scrap/sbEquipmentScrap/form?id=${sbEquipmentScrap.id}">
					${sbEquipmentScrap.officeName}
				</a></td>
				<td>
					${sbEquipmentScrap.equipmentName}
				</td>
				<td>
					${sbEquipmentScrap.equipmentMade}
				</td>
				<td>
					${sbEquipmentScrap.equipmentCode}
				</td>
				<td>
					<fmt:formatDate value="${sbEquipmentScrap.equipmentOuttime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${sbEquipmentScrap.equipmentBuytime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sbEquipmentScrap.equipmentYprice}
				</td>
				<td>
					${sbEquipmentScrap.equipmentJprice}
				</td>
				<td>
					${sbEquipmentScrap.equipmentType}
				</td>
				<td>
					${sbEquipmentScrap.equipmentTeamName}
				</td>
				<td>
					${sbEquipmentScrap.equipmentNorms}
				</td>
				<td>
					<fmt:formatDate value="${sbEquipmentScrap.equipmentStarttime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sbEquipmentScrap.equipmentYears}
				</td>
				
				<shiro:hasPermission name="equipment:scrap:sbEquipmentScrap:edit"><td>
    				<a href="${ctx}/equipment/scrap/sbEquipmentScrap/form?id=${sbEquipmentScrap.id}">修改</a>
    				<a href="${ctx}/equipment/scrap/sbEquipmentScrap/view?id=${sbEquipmentScrap.id}">打印</a>
					<a href="${ctx}/equipment/scrap/sbEquipmentScrap/delete?id=${sbEquipmentScrap.id}" onclick="return confirmx('确认要删除该设备仪器报废（闲置）申请吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>