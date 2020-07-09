<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采集任务管理管理</title>
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
		<li class="active"><a href="${ctx}/task/tsTask/">采集任务列表</a></li>
		
	</ul>
	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>设备编号</th>
				<th>采集部门</th>
				<th>采集人</th>
				<th>上次采集时间</th>
				<th>任务时间</th>
				<th>数据包数</th>
				<th>采集状态</th>
				<shiro:hasPermission name="task:tsTask:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tsTask">
			<tr>
				<td><a href="${ctx}/borrow/tsBorrow/task?busType=${tsTask.busType}">
					${tsTask.equipmentname}
				</a></td>
				<td>
					${tsTask.officeid}
				</td>
				<td>
					${tsTask.userid}
				</td>
				<td>
					${tsTask.lasttime}
				</td>
				<td>
					${tsTask.acquisitiontime}
				</td>
				<td>
					${tsTask.amount}
				</td>
				<td>
					${fns:getDictLabel(tsTask.state, 'cjState', '')}
				</td>
				<shiro:hasPermission name="task:tsTask:edit"><td>
    				<a href="${ctx}/borrow/tsBorrow/task?busType=${tsTask.busType}&equipmentid=${tsTask.equipmentid}&id=${tsTask.id}">上传采集数据</a>
					<a href="${ctx}/task/tsTask/delete?id=${tsTask.id}" onclick="return confirmx('确认要完成该采集任务管理吗？', this.href)">采集完成</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>