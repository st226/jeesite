<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>业务表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#comments").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					$("input[type=checkbox]").each(function(){
						$(this).after("<input type=\"hidden\" name=\""+$(this).attr("name")+"\" value=\""
								+($(this).attr("checked")?"1":"0")+"\"/>");
						$(this).attr("name", "_"+$(this).attr("name"));
					});
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			$("#searchForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					$("input[type=checkbox]").each(function(){
						$(this).after("<input type=\"hidden\" name=\""+$(this).attr("name")+"\" value=\""
								+($(this).attr("checked")?"1":"0")+"\"/>");
						$(this).attr("name", "_"+$(this).attr("name"));
					});
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
			$("#btnSubmitQ").click(function(){
				
				$("#searchForm").attr("action","${ctx}/borrow/tsBorrow/borrowQ");
				$("#searchForm").submit();
				
			});
			
			$("#btnSubmitJ").click(function(){
				if($("#borrowState").val()=="1"){
				$("#searchForm").attr("action","${ctx}/borrow/tsBorrow/borrowJ");
				$("#searchForm").submit();
				}else{
					alert("请选择可借阅资源！");
				    return;
				}
			});
			
			
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		
		<li class="active"><a href="${ctx}/borrow/tsBorrow/borrow">借阅资源列表</a></li>
        <li><a href="${ctx}/gen/genTable/"></a></li>
	</ul>

		
			<form:form id="searchForm" modelAttribute="tsBorrow"  method="post" class="form-horizontal">
				<form:hidden path="id"/>
				<sys:message content="${message}"/>
				<fieldset>
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				
				<th>资源名称</th>
				<th>资源号</th>
				<th>资源类型</th>
				<th>借阅状态</th>
				<th>借阅类型</th>
				<th>借阅时间</th>
				<th>续借次数</th>
				<shiro:hasPermission name="borrow:tsBorrow:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${tsBorrows}" var="tsBorrow">
			<tr>
				
				<td>
					${tsBorrow.tsName}
				</td>
				<td>
					${tsBorrow.tsKeyCode}
				</td>
				<td>
					${tsBorrow.busType}
				</td>
				<td>
					${fns:getDictLabel(tsBorrow.borrowState, 'borrow_state', '')}
				</td>
				<td>
					${fns:getDictLabel(tsBorrow.borrowType, 'borrow_type', '')}
				</td>
				<td>
					<fmt:formatDate value="${tsBorrow.createDate}" pattern="yyyy-MM-dd"/>
				</td>
			
				<td>
					${tsBorrow.frequency}
				</td>
				<shiro:hasPermission name="borrow:tsBorrow:edit"><td>
    			<a href="${ctx}/borrow/tsBorrow/renewzj?id=${tsBorrow.id}&userCode=${tsBorrow.userCode}&tsId=${tsBorrow.tsId}">${ tsBorrow.borrowState =='3'?'':'续借'}</a>  
					
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
				</fieldset>
				
			</form:form>
</body>
</html>
