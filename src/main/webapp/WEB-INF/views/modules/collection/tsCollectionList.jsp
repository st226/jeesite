<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>馆藏信息管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, ids = [], rootIds = [];
			for (var i=0; i<data.length; i++){
				ids.push(data[i].id);
			}
			ids = ',' + ids.join(',') + ',';
			for (var i=0; i<data.length; i++){
				if (ids.indexOf(','+data[i].parentId+',') == -1){
					if ((','+rootIds.join(',')+',').indexOf(','+data[i].parentId+',') == -1){
						rootIds.push(data[i].parentId);
					}
				}
			}
			for (var i=0; i<rootIds.length; i++){
				addRow("#treeTableList", tpl, data, rootIds[i], true);
			}
			$("#treeTable").treeTable({expandLevel : 5});
		});
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
							ctype: getDictLabel(${fns:toJson(fns:getDictList('collection_type'))}, row.ctype),
							isleaf: getDictLabel(${fns:toJson(fns:getDictList('is_leaf'))}, row.isleaf),
						blank123:0}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/collection/tsCollection/">馆藏信息列表</a></li>
		<shiro:hasPermission name="collection:tsCollection:edit"><li><a href="${ctx}/collection/tsCollection/form">馆藏信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tsCollection" action="${ctx}/collection/tsCollection/" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>位置名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>节点类型：</label>
				<form:select path="ctype" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('collection_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>位置名称</th>
				<th>编码</th>
				<th>节点类型</th>
				<th>资源类型</th>
				<th>是否叶子节点</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="collection:tsCollection:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/collection/tsCollection/form?id={{row.id}}">
				{{row.name}}
			</a></td>
			<td>
				{{row.code}}
			</td>
			<td>
				{{dict.ctype}}
			</td>
			<td>
				{{row.busTypeName}}
			</td>
			<td>
				{{dict.isleaf}}
			</td>
			<td>
				{{row.createDate}}
			</td>
			<td>
				{{row.updateDate}}
			</td>
			<td>
				{{row.remarks}}
			</td>
			<shiro:hasPermission name="collection:tsCollection:edit"><td>
   				<a href="${ctx}/collection/tsCollection/form?id={{row.id}}">修改</a>
				<a href="${ctx}/collection/tsCollection/delete?id={{row.id}}" onclick="return confirmx('确认要删除该馆藏信息及所有子馆藏信息吗？', this.href)">删除</a>
				<a href="${ctx}/collection/tsCollection/form?parent.id={{row.id}}">添加下级馆藏信息</a> 
			</td></shiro:hasPermission>
		</tr>
	</script>
</body>
</html>