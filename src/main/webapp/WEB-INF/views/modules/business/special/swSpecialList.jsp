<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>特殊项目管理</title>
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
		<li class="active"><a href="${ctx}/business/special/swSpecial/">特殊项目列表</a></li>
			</ul>
	<form:form id="searchForm" modelAttribute="swSpecial" action="${ctx}/business/special/swSpecial/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>编号：</label>
				<form:input path="code" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>主管部门：</label>
				<sys:treeselect id="office" name="office.id" value="${swSpecial.office.id}" labelName="office.name" labelValue="${swSpecial.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>申请日期：</label>
				<input name="applicationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${swSpecial.applicationDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
				<li class="clearfix"></li>
			<li><label>项目名称：</label>
				<form:input path="projectName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>特殊类型：</label>
				<form:select path="specialType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('special_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>编号</th>
				<th>业务主管部门</th>
				<th>申请日期</th>
				<th>项目名称</th>
				<th>预算</th>
				<th>特殊情况类型</th>
				<shiro:hasPermission name="business:special:swSpecial:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="swSpecial">
			<tr>
				<td><a href="${ctx}/business/special/swSpecial/look?id=${swSpecial.id}">
					${swSpecial.code}
				</a></td>
				<td>
					${swSpecial.office.name}
				</td>
				<td>
					<fmt:formatDate value="${swSpecial.applicationDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${swSpecial.projectName}
				</td>
				<td>
					${swSpecial.budget}
				</td>
				<td>
					${fns:getDictLabel(swSpecial.specialType, 'special_type', '')}
				</td>
				<shiro:hasPermission name="business:special:swSpecial:edit"><td>
    				<a href="${ctx}/business/special/swSpecial/look?id=${swSpecial.id}">查看</a>
    					</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>