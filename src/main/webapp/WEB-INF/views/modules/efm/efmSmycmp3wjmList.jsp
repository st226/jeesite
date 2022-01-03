<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>涉密载体流转审批表管理</title>
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
		<li class="active"><a href="${ctx}/efm/efmSmycmp3wjm/">涉密载体流转审批表列表</a></li>
		<shiro:hasPermission name="efm:efmSmycmp3wjm:edit"><li><a href="${ctx}/efm/efmSmycmp3wjm/form">涉密载体流转审批表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="efmSmycmp3wjm" action="${ctx}/efm/efmSmycmp3wjm/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>编号：</label>
				<form:input path="procSno" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>申请人：</label>
				<form:input path="shenqingren" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>申请部门：</label>
				<form:input path="shenqingbumen" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>接收人：</label>
				<form:input path="jieshouren" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>接收部门：</label>
				<form:input path="jieshoubumen" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>申请人密级：</label>
				<form:input path="shenqingrenmiji" htmlEscape="false" maxlength="4" class="input-medium"/>
			</li>
			<li><label>接收人密级：</label>
				<form:input path="jieshourenmiji" htmlEscape="false" maxlength="4" class="input-medium"/>
			</li>
			<li><label>申请人联系方式：</label>
				<form:input path="sqrlxfs" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>申请时间：</label>
				<input name="shenqingshijian" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${efmSmycmp3wjm.shenqingshijian}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
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
				<th>创建者</th>
				<th>更新时间</th>
				<th>申请人</th>
				<th>申请部门</th>
				<th>接收人</th>
				<th>接收部门</th>
				<th>申请人密级</th>
				<th>接收人密级</th>
				<th>申请人联系方式</th>
				<th>申请时间</th>
				<shiro:hasPermission name="efm:efmSmycmp3wjm:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="efmSmycmp3wjm">
			<tr>
				<td><a href="${ctx}/efm/efmSmycmp3wjm/form?id=${efmSmycmp3wjm.id}">
					${efmSmycmp3wjm.procSno}
				</a></td>
				<td>
					${efmSmycmp3wjm.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${efmSmycmp3wjm.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${efmSmycmp3wjm.shenqingren}
				</td>
				<td>
					${efmSmycmp3wjm.shenqingbumen}
				</td>
				<td>
					${efmSmycmp3wjm.jieshouren}
				</td>
				<td>
					${efmSmycmp3wjm.jieshoubumen}
				</td>
				<td>
					${efmSmycmp3wjm.shenqingrenmiji}
				</td>
				<td>
					${efmSmycmp3wjm.jieshourenmiji}
				</td>
				<td>
					${efmSmycmp3wjm.sqrlxfs}
				</td>
				<td>
					<fmt:formatDate value="${efmSmycmp3wjm.shenqingshijian}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="efm:efmSmycmp3wjm:edit"><td>
    				<a href="${ctx}/efm/efmSmycmp3wjm/form?id=${efmSmycmp3wjm.id}">修改</a>
					<a href="${ctx}/efm/efmSmycmp3wjm/delete?id=${efmSmycmp3wjm.id}" onclick="return confirmx('确认要删除该涉密载体流转审批表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>