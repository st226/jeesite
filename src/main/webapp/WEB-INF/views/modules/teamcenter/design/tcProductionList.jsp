<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>生产图纸管理</title>
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
		<li class="active"><a href="${ctx}/teamcenter/design/tcProduction/">生产图纸列表</a></li>
		<shiro:hasPermission name="teamcenter:design:tcProduction:edit"><li><a href="${ctx}/teamcenter/design/tcProduction/form?typeId=${typeId}">生产图纸添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tcProduction" action="${ctx}/teamcenter/design/tcProduction/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<form:hidden path="typeId" htmlEscape="false" maxlength="100" class="input-medium"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			
			<li><label>图号：</label>
				<form:input path="drawingNo" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="clearfix"></li>
			<li><label>版本：</label>
				<form:input path="edition" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>阶段：</label>
				<form:select path="stage" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yzgc')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>编码</th>
				<th>图号</th>
				<th>阶段</th>
				<th>版本</th>
				<th>设计文件</th>
				<th>PDF版本</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="teamcenter:design:tcProduction:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tcProduction">
			<tr>
				<td><a href="${ctx}/teamcenter/design/tcProduction/form?id=${tcProduction.id}">
					${tcProduction.name}
				</a></td>
				<td>
					${tcProduction.code}
				</td>
				<td>
					${tcProduction.drawingNo}
				</td>
				<td>
					${fns:getDictLabel(tcProduction.stage, 'yzgc', '')}
				</td>
				<td>
					${tcProduction.edition}
				</td>
				<td>

					<a href='${(tcProduction.file).split("\\|")[1]}'>${tcProduction.name}<i style="color: red;" class="icon-paper-clip"></i></a>
				</td>
				<td>
					<a href='${(tcProduction.filepdf).split("\\|")[1]}'>${tcProduction.name}<i style="color: red;" class="icon-paper-clip"></i></a>
				</td>
				
				<td>
					<fmt:formatDate value="${tcProduction.updateDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${tcProduction.remarks}
				</td>
				<shiro:hasPermission name="teamcenter:design:tcProduction:edit"><td>
    				<a href="${ctx}/teamcenter/design/tcProduction/form?id=${tcProduction.id}">换版</a>
					
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>