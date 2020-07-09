<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>内部招投标管理</title>
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
		<li class="active"><a href="${ctx}/business/bidding/swBidding/">内部招投标列表</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="swBidding" action="${ctx}/business/bidding/swBidding/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>货物名称：</label>
				<form:input path="goodsName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>数量：</label>
				<form:input path="goodsAmount" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>品牌：</label>
				<form:input path="goodsBrand" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>是否完成：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('is_leaf')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
			
				<th>货物名称</th>
				<th>数量</th>
				<th>品牌</th>
				<th>到货地址</th>
				<th>截止日期</th>
				<th>项目名称</th>
				<th>是否完成</th>
				<shiro:hasPermission name="business:bidding:swBidding:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="swBidding">
			<tr>
			
				<td><a href="${ctx}/business/bidding/swBidding/look?id=${swBidding.id}">
					${swBidding.goodsName}
				</a></td>
				<td>
					${swBidding.goodsAmount}
				</td>
				<td>
					${swBidding.goodsBrand}
				</td>
				<td>
					${swBidding.address}
				</td>
				<td>
					<fmt:formatDate value="${swBidding.closingDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${swBidding.projectName}
				</td>
				<td>
					${fns:getDictLabel(swBidding.state, 'is_leaf', '')}
				</td>
				<shiro:hasPermission name="business:bidding:swBidding:edit"><td>
    				<a href="${ctx}/business/bidding/swBidding/look?id=${swBidding.id}">查看</a>
			</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>