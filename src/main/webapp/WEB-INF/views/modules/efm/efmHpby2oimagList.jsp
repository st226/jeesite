<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>涉密载体回收审批表管理</title>
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
		<li class="active"><a href="${ctx}/efm/efmHpby2oimag/">涉密载体回收审批表列表</a></li>
		<shiro:hasPermission name="efm:efmHpby2oimag:edit"><li><a href="${ctx}/efm/efmHpby2oimag/form">涉密载体回收审批表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="efmHpby2oimag" action="${ctx}/efm/efmHpby2oimag/" method="post" class="breadcrumb form-search">
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
			<li><label>申请日期：</label>
				<input name="shenqingriqi" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${efmHpby2oimag.shenqingriqi}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>更新时间</th>
				<th>申请人</th>
				<th>申请部门</th>
				<th>申请日期</th>
				<th>载体最高密级</th>
				<th>销毁人</th>
				<th>销毁时间</th>
				<shiro:hasPermission name="efm:efmHpby2oimag:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="efmHpby2oimag">
			<tr>
				<td><a href="${ctx}/efm/efmHpby2oimag/form?id=${efmHpby2oimag.id}">
					${efmHpby2oimag.procSno}
				</a></td>
				<td>
					<fmt:formatDate value="${efmHpby2oimag.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${efmHpby2oimag.shenqingren}
				</td>
				<td>
					${efmHpby2oimag.shenqingbumen}
				</td>
				<td>
					<fmt:formatDate value="${efmHpby2oimag.shenqingriqi}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${efmHpby2oimag.zaitizuigaomiji}
				</td>
				<td>
					${efmHpby2oimag.xiaohuiren}
				</td>
				<td>
					<fmt:formatDate value="${efmHpby2oimag.xiaohuishijian}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="efm:efmHpby2oimag:edit"><td>
    				<a href="${ctx}/efm/efmHpby2oimag/form?id=${efmHpby2oimag.id}">修改</a>
					<a href="${ctx}/efm/efmHpby2oimag/delete?id=${efmHpby2oimag.id}" onclick="return confirmx('确认要删除该涉密载体回收审批表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>