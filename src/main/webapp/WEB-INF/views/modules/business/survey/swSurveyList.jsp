<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>调研报告管理</title>
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
		<li class="active"><a href="${ctx}/business/survey/swSurvey/">调研报告列表</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="swSurvey" action="${ctx}/business/survey/swSurvey/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>公司编号：</label>
				<form:input path="field4" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>主要用途：</label>
				<form:input path="mainUses" htmlEscape="false" maxlength="2550" class="input-medium"/>
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
			    <th>公司编号</th>
				<th>主要用途</th>
				<th>目前情况</th>
				<th>技术指标</th>
				<th>预算来源</th>
				<th>供应商</th>
				<th>评定结构</th>
				<th>是否完成</th>
				<shiro:hasPermission name="business:survey:swSurvey:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="swSurvey">
			<tr>
				
				<td title="${swSurvey.field4}"><a href="${ctx}/business/survey/swSurvey/look?id=${swSurvey.id}">
					${fns:abbr(swSurvey.field4,30)}
				</a></td>
				<td title="${swSurvey.mainUses}">
					${fns:abbr(swSurvey.mainUses,30)}
			</td>
				<td title="${swSurvey.situation}">
					${fns:abbr(swSurvey.situation,25)}
				</td>
				<td title="${swSurvey.technical}">
					${fns:abbr(swSurvey.technical,20)}
				</td>
				<td title="${swSurvey.budget}">
					${fns:abbr(swSurvey.budget,25)}
				</td>
				<td title="${swSurvey.supplier}">
					${fns:abbr(swSurvey.supplier,20)}
				</td>
				<td title="${swSurvey.result}">
					${fns:abbr(swSurvey.result,30)}
				</td>
				<td>
					${fns:getDictLabel(swSurvey.state, 'is_leaf', '')}
				</td>
				<!--<shiro:hasPermission name="business:survey:swSurvey:edit"><td>
    				<a href="${ctx}/business/survey/swSurvey/form?id=${swSurvey.id}">修改</a>
    				<a href="${ctx}/business/survey/swSurvey/view?id=${swSurvey.id}">打印</a>
					<a href="${ctx}/business/survey/swSurvey/delete?id=${swSurvey.id}" onclick="return confirmx('确认要删除该调研报告吗？', this.href)">删除</a>
				</td></shiro:hasPermission>-->
				<td><a href="${ctx}/business/survey/swSurvey/look?id=${swSurvey.id}">查看</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>