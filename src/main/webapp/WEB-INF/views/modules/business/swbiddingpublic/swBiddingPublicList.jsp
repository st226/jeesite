<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公开招投标管理</title>
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
		<li class="active"><a href="${ctx}/business/swbiddingpublic/swBiddingPublic/">公开招投标列表</a></li>
		<shiro:hasPermission name="business:swbiddingpublic:swBiddingPublic:edit"><li><a href="${ctx}/business/swbiddingpublic/swBiddingPublic/form">公开招投标添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="swBiddingPublic" action="${ctx}/business/swbiddingpublic/swBiddingPublic/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>招标编号：</label>
				<form:input path="biddingNo" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>设备名称：</label>
				<form:input path="goodsName" htmlEscape="false" maxlength="2000" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>招标编号</th>
				<th>设备名称</th>
				<th>数量</th>
				<th>报价币种</th>
				<th>报价方式</th>
				<th>交货日期</th>
				<shiro:hasPermission name="business:swbiddingpublic:swBiddingPublic:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="swBiddingPublic">
			<tr>
				<td><a href="${ctx}/business/swbiddingpublic/swBiddingPublic/form?id=${swBiddingPublic.id}">
					${swBiddingPublic.biddingNo}
				</a></td>
				<td>
					${swBiddingPublic.goodsName}
				</td>
				<td>
					${swBiddingPublic.goodsAmount}
				</td>
				<td>
					${fns:getDictLabel(swBiddingPublic.currency, 'sw_currency', '')}
				</td>
				<td>
					${swBiddingPublic.model}
				</td>
				<td>
					${swBiddingPublic.deliveryDate}
				</td>
				<shiro:hasPermission name="business:swbiddingpublic:swBiddingPublic:edit"><td>
    				<a href="${ctx}/business/swbiddingpublic/swBiddingPublic/form?id=${swBiddingPublic.id}">修改</a>
					<a href="${ctx}/business/swbiddingpublic/swBiddingPublic/delete?id=${swBiddingPublic.id}" onclick="return confirmx('确认要删除该公开招投标吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>