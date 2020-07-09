<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>请款单管理</title>
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
		<li class="active"><a href="${ctx}/business/pay/swPay/">请款单列表</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="swPay" action="${ctx}/business/pay/swPay/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>合同编号：</label>
				<form:input path="contractCode" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>合同名称：</label>
				<form:input path="contrateName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>付款方式：</label>
				<form:radiobuttons path="payType" items="${fns:getDictList('pay_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li><li class="clearfix"></li>
			<li><label>供应商名称：</label>
				<form:input path="supplierName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>供应商电话：</label>
				<form:input path="supplierTel" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>合同编号</th>
				<th>合同名称</th>
				<th>付款方式</th>
				<th>供应商名称</th>
				<th>供应商电话</th>
				<th>合同总额</th>
				<th>已付款额</th>
				<th>本次付款额</th>
				
				<th>经办人</th>
				<th>经办时间</th>
				<th>联系电话</th>
				<shiro:hasPermission name="business:pay:swPay:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="swPay">
			<tr>
				<td><a href="${ctx}/business/pay/swPay/edit?id=${swPay.id}">
					${swPay.contractCode}
				</a></td>
				<td>
					${swPay.contrateName}
				</td>
				<td>
					${fns:getDictLabel(swPay.payType, 'pay_type', '')}
				</td>
				<td>
					${swPay.supplierName}
				</td>
				<td>
					${swPay.supplierTel}
				</td>
				<td>
					${swPay.contratePrice}
				</td>
				<td>
					${swPay.contratePaid}
				</td>
				<td>
					${swPay.contrateTreat}
				</td>
				
				<td>
					${swPay.field1}
				</td>
				<td>
					<fmt:formatDate value="${swPay.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${swPay.phone}
				</td>
				<shiro:hasPermission name="business:pay:swPay:edit"><td>
    				<a href="${ctx}/business/pay/swPay/edit?id=${swPay.id}">查看</a>
    				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>