<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同录入管理</title>
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
		<li class="active"><a href="${ctx}/business/agreement/swAgreement/">合同录入列表</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="swAgreement" action="${ctx}/business/agreement/swAgreement/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>合同编号：</label>
				<form:input path="agreementNo" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>签订时间：</label>
				<input name="signingTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${swAgreement.signingTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>签订地点：</label>
				<form:input path="signingPlace" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th>签订时间</th>
				<th>签订地点</th>
				<th>总价</th>
				<th>总价大写</th>
				<th>质保期</th>
				<shiro:hasPermission name="business:agreement:swAgreement:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="swAgreement">
			<tr>
				<td><a href="${ctx}/business/agreement/swAgreement/look?id=${swAgreement.id}">
					${swAgreement.agreementNo}
				</a></td>
				<td>
					<fmt:formatDate value="${swAgreement.signingTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${swAgreement.signingPlace}
				</td>
				<td>
					${swAgreement.amount}
				</td>
				<td>
					${swAgreement.amountup}
				</td>
				<td>
					${swAgreement.warranty}
				</td>
				<shiro:hasPermission name="business:agreement:swAgreement:edit"><td>
    				<a href="${ctx}/business/agreement/swAgreement/look?id=${swAgreement.id}">查看</a>
    								</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>