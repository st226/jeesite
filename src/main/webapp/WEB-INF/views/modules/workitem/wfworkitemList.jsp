<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作项维护管理</title>
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
		top.$.jBox.open("iframe:http://10.193.200.16:8088/default/archives/archives/imageFlow.jsp?id="+pid, "流程跟踪",810,$(top.document).height()-110
				,{
			buttons:{"确定":true}, loaded:function(h){
				$(".jbox-content", top.document).css("overflow-y","hidden");
			}});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a href="${ctx}/act/task/todo/">待办任务</a></li>
		<li class="active"><a href="${ctx}/workitem/wfworkitem/">已办任务</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="wfworkitem" action="${ctx}/workitem/wfworkitem/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>创建时间：</label>
			<input id="beginDate"  name="starttime"  type="text"  maxlength="20" class="input-medium Wdate" style="width:163px;"
				pattern="yyyy-MM-dd"/>
					--
			<input id="endDate" name="endtime" type="text"  maxlength="20" class="input-medium Wdate" style="width:163px;"
				 pattern="yyyy-MM-dd"/>
			&nbsp;

			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			
	
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>办理环节</th>

				<th>流程名称</th>
				<th>摘要信息</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wfworkitem">
			<tr>
				<td>
						${wfworkitem.processchname}
					</td>
				
					<td>${wfworkitem.workitemname}</td>
					<td> ${wfworkitem.processinstname}</td>
					<td> ${wfworkitem.processinstname}</td>
					<td><fmt:formatDate value="${wfworkitem.starttime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<a href="${ctx}${wfworkitem.actionurl}?processInstID=${wfworkitem.processinstid}&workItemID=${wfworkitem.workitemid}&currentstate=${wfworkitem.currentstate}">任务详情</a>
                   <a target="_blank" onclick="test(${wfworkitem.processinstid})" >跟踪</a>
					
					</td>
		
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>