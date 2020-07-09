<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>计量管理管理</title>
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
			var url = window.document.location.href;
			var urls = url.split("/");
			url = urls[2] ;
			var urls = url.split(":");

	
			top.$.jBox.open("iframe:http://"+urls[0]+":8088/default/archives/archives/imageFlow.jsp?id="+pid, "流程跟踪",810,$(top.document).height()-110);
			
		}r
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/equipment/metering/sbMetering/">计量管理列表</a></li>
		<shiro:hasPermission name="equipment:metering:sbMetering:edit"><li><a href="${ctx}/equipment/metering/sbMetering/form">计量管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sbMetering" action="${ctx}/equipment/metering/sbMetering/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('metering_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>申请人：</label>
				<form:input path="createBy.id" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				
	
				<th>说明</th>
				<th>计量个数</th>	
				<th>送检个数</th>
				<th>送检率</th>	
				<th>申请人</th>	
				<th>申请时间</th>
				<shiro:hasPermission name="equipment:metering:sbMetering:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sbMetering"  varStatus="vs">
			<tr>
				
		
				<td>
					${sbMetering.field1}
				</td>
				
				<td>
					${sbMetering.totle}
				</td>
				<td>
					${sbMetering.sureTotle}
				</td>
				<td>
					
					
                  <fmt:formatNumber type="number" value="${sbMetering.sureTotle/sbMetering.totle*100}" pattern="0.00" maxFractionDigits="2"/>%
					
				</td>
				<td>
					${sbMetering.field3}
				</td>
				<td>

					<fmt:formatDate value="${sbMetering.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<shiro:hasPermission name="equipment:metering:sbMetering:edit"><td>
    				<a href="${ctx}/equipment/metering/sbMetering/form?id=${sbMetering.id}">详情</a>
    				 <a target="_blank" onclick="test(${sbMetering.processinstid})" >跟踪</a>
					<a href="${ctx}/equipment/metering/sbMetering/delete?id=${sbMetering.id}" onclick="return confirmx('确认要删除该计量管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>