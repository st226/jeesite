<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仪器设备开箱验收管理</title>
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
		<li class="active"><a href="${ctx}/business/receive/swReceive/">仪器设备开箱验收列表</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="swReceive" action="${ctx}/business/receive/swReceive/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>计划编号：</label>
				<form:input path="planNumber" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>所属项目：</label>
				<form:input path="items" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>设备名称：</label>
				<form:input path="sbName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>计划编号</th>
				<th>所属项目</th>
				<th>资产归属</th>
				<th>设备名称</th>
				<th>合同编号</th>
				<th>申请部门</th>
				<th>申请人</th>
				<th>制造商</th>
				<th>出厂日期</th>
				<th>到货日期</th>
				<th>联系方式</th>
				<th>开箱地点</th>
				<th>备注</th>
				<shiro:hasPermission name="business:receive:swReceive:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="swReceive">
			<tr>
				<td><a href="${ctx}/business/receive/swReceive/form?id=${swReceive.id}">
					${swReceive.planNumber}
				</a></td>
				<td>
					${swReceive.items}
				</td>
				<td>
					${swReceive.assets}
				</td>
				<td>
					${swReceive.sbName}
				</td>
				<td>
					${swReceive.contractCode}
				</td>
				<td>
					${swReceive.officeName}
				</td>
				<td>
					${swReceive.userName}
				</td>
				<td>
					${swReceive.made}
				</td>
				<td>
					${swReceive.productionDate}
				</td>
				<td>
					${swReceive.arrivalDate}
				</td>
				<td>
					${swReceive.contactInformation}
				</td>
				<td>
					${swReceive.location}
				</td>
				<td>
					${swReceive.remarks}
				</td>
				<shiro:hasPermission name="business:receive:swReceive:edit"><td>
    				<a href="${ctx}/business/receive/swReceive/form?id=${swReceive.id}">查看</a>
						</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>