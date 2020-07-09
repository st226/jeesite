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
		<li class="active"><a href="${ctx}/resourcebus/tsResourceBus/censust">各个型号上传文档统计</a></li>
		<li class="active"><a href="${ctx}/resourcebus/tsResourceBus/census">详细统计列表</a></li>
		
	</ul>
	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>型号名称</th>
				<th>文件数量</th>
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="tsStatistical">
			<tr>
				<td>
					${tsStatistical.name}
				</td>
				<td><a href="${ctx}/resourcebus/tsResourceBus/list?tableComments=CX&busType=${tsStatistical.id}">
					${tsStatistical.count}
				</a></td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
</body>
</html>