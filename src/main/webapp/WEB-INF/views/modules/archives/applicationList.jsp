<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>现行文件管理</title>
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
		
		function test(pid){
			top.$.jBox.open("iframe:http://10.193.200.16:8088/default/archives/archives/imageFlow.jsp?id="+pid, "流程跟踪",810,$(top.document).height()-110);
			
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/archives/application/">晒印列表</a></li>
		<shiro:hasPermission name="archives:application:edit"><li><a href="${ctx}/archives/application/form">晒印申请添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="application" action="${ctx}/archives/application/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			
			<li><label>申请原因：</label>
				<form:input path="applyCour" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>申请单位：</label>
				<sys:treeselect id="office" name="office.id" value="${application.office.id}" labelName="office.name" labelValue="${application.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>复印形式：</label>
				<form:select path="apllyModel" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('APLLY_MODEL')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
		<!-- <li><label>状态：</label>
				<form:select path="apllyStatus" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('APLLY_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li> -->	
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>型号</th>
				<th>申请原因</th>
				<th>计划号</th>
				<th>申请人</th>
				<th>申请单位</th>
				<th>申请日期</th>
				<th>电话号</th>
				<th>复印形式</th>
				<th>晒印批组</th>
				<shiro:hasPermission name="archives:application:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="application">
			<tr>
				<td>
					${application.busTypeName}
				</td>
				<td>
					${application.applyCour}
				</td>
				<td>
					${application.code}
				</td>
				<td>
					${application.user.name}
				</td>
				<td>
					${application.office.name}
				</td>
				<td>
					<fmt:formatDate value="${application.apllyDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${application.apllyNo}
				</td>
				<td>
					${fns:getDictLabel(application.apllyModel, 'APLLY_MODEL', '')}
				</td>
				<td>
					${fns:getDictLabel(application.apllySypc, 'APLLY_SYPC', '')}
				</td>
				<td>
				<shiro:hasPermission name="archives:application:editgly">
    				<a href="${ctx}/archives/application/form?id=${application.id}">修改</a>
					<a href="${ctx}/archives/application/delete?id=${application.id}" onclick="return confirmx('确认要删除该现行文件吗？', this.href)">删除</a>
				    </shiro:hasPermission>
				 <a href="${ctx}/archives/application/view?id=${application.id}" >查询</a>
			     <a target="_blank" onclick="test(${application.processinstid})" >跟踪</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>