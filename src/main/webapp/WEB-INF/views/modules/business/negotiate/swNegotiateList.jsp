<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商务谈判管理</title>
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
		<li class="active"><a href="${ctx}/business/negotiate/swNegotiate/">商务谈判列表</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="swNegotiate" action="${ctx}/business/negotiate/swNegotiate/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			
			<li><label>谈判地点：</label>
				<form:input path="negotiateLocal" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
				<li><label>采用程序：</label>
				<form:select path="procedures" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('procedure')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>谈判时间：</label>
				<input name="negotiateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${swNegotiate.negotiateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="clearfix"></li>
		<li><label>项目名称：</label>
				<form:input path="projectName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>供应商来源：</label>
				<form:select path="supplier" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('supplier')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>项目名称</th>
				<th>谈判时间</th>
				<th>谈判地点</th>
				
				<th>采用程序</th>
				<th>供应商来源</th>
				<th>报价情况</th>
				<th>经办人</th>
				<th>总价</th>
				<th>谈判价</th>
				<th>差额</th>
				<shiro:hasPermission name="business:negotiate:swNegotiate:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="swNegotiate">
			<tr>
			
			   <td><a href="${ctx}/business/negotiate/swNegotiate/look?id=${swNegotiate.id}">
					${swNegotiate.projectName}
				</a></td>
				<td>
					<fmt:formatDate value="${swNegotiate.negotiateTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${swNegotiate.negotiateLocal}
				</td>
				
				<td>
					${fns:getDictLabel(swNegotiate.procedures, 'procedure', '')}
				</td>
				<td>
					${fns:getDictLabel(swNegotiate.supplier, 'supplier', '')}
				</td>
				<td>
					${swNegotiate.quotedPrice}
				</td>
				<td>
					${swNegotiate.negotiateUser}
				</td>
				<td>
					${swNegotiate.totalPrice}
				</td>
				<td>
					${swNegotiate.negotiatePrice}
				</td>
				<td>
					${swNegotiate.balancePrice}
				</td>
				<shiro:hasPermission name="business:negotiate:swNegotiate:edit"><td>
    				<a href="${ctx}/business/negotiate/swNegotiate/look?id=${swNegotiate.id}">查看</a>
    							</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>