<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仪器设备管理</title>
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
		<li class="active"><a href="${ctx}/equipment/equipment/sbEquipment/">仪器设备列表</a></li>
		<shiro:hasPermission name="equipment:equipment:sbEquipment:edit"><li><a href="${ctx}/equipment/equipment/sbEquipment/form">仪器设备添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sbEquipment" action="${ctx}/equipment/equipment/sbEquipment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>设备类别</th>
				<th>资源类别</th>
				<th>名称</th>
				<th>资金来源</th>
				<th>出厂编号</th>
				<th>设备编号</th>
				<th>资产编号</th>
				<th>设备种类</th>
				<th>型号</th>
				<th>设备状态</th>
				<th>购买名称</th>
				<th>购买部门名称</th>
				<th>使用部门</th>
				<th>部门名称</th>
				<th>使用人</th>
				<th>使用人名称</th>
				<th>生产厂家</th>
				<th>出厂日期</th>
				<th>购买日期</th>
				<th>总价</th>
				<th>启用日期</th>
				<shiro:hasPermission name="equipment:equipment:sbEquipment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sbEquipment">
			<tr>
				<td><a href="${ctx}/equipment/equipment/sbEquipment/form?id=${sbEquipment.id}">
					${sbEquipment.sbType}
				</a></td>
				<td>
					${sbEquipment.fsType}
				</td>
				<td>
					${sbEquipment.name}
				</td>
				<td>
					${sbEquipment.fundingsource}
				</td>
				<td>
					${sbEquipment.cccode}
				</td>
				<td>
					${sbEquipment.sbcode}
				</td>
				<td>
					${sbEquipment.zccode}
				</td>
				<td>
					${sbEquipment.kind}
				</td>
				<td>
					${sbEquipment.type}
				</td>
				<td>
					${fns:getDictLabel(sbEquipment.state, 'sb_state', '')}
				</td>
				<td>
					${sbEquipment.buyteam}
				</td>
				<td>
					${sbEquipment.buyteamname}
				</td>
				<td>
					${sbEquipment.team}
				</td>
				<td>
					${sbEquipment.teamname}
				</td>
				<td>
					${sbEquipment.usepeople}
				</td>
				<td>
					${sbEquipment.usepeoplename}
				</td>
				<td>
					${sbEquipment.made}
				</td>
				<td>
					${sbEquipment.outtime}
				</td>
				<td>
					${sbEquipment.buytime}
				</td>
				<td>
					${sbEquipment.price}
				</td>
				<td>
					${sbEquipment.starttime}
				</td>
				<shiro:hasPermission name="equipment:equipment:sbEquipment:edit"><td>
    				<a href="${ctx}/equipment/equipment/sbEquipment/form?id=${sbEquipment.id}">修改</a>
					<a href="${ctx}/equipment/equipment/sbEquipment/delete?id=${sbEquipment.id}" onclick="return confirmx('确认要删除该仪器设备吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>