<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>统计分析管理</title>
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
		 <li class="active"><a href="${ctx}/resourcebus/tsResourceBus/censust2">各个部门上传文档统计</a></li>
		<li class="active"><a href="${ctx}/resourcebus/tsResourceBus/census2">详细统计列表</a></li>
		
	</ul>
	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>部门名称</th>
				<th>待审批数量</th>
				<th>已审批数量</th>
				<th>已归档数量</th>
				<th>总数</th>
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="tsStatistical">
			<tr>
				<td>
					${tsStatistical.office_name}
				</td>
				<td>
					${tsStatistical.count1}
				</td>
				<td>
					${tsStatistical.count2}
				</td>
				<td>
					${tsStatistical.count3}
				</td>
				<td>
					${tsStatistical.count}
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
</body>
</html>