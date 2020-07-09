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
		
		<li class="active"><a href="${ctx}/borrow/tsBorrow/borrow">数据采集列表</a></li>
		<li><a href="${ctx}/borrow/tsBorrow/form2?busType=${busType}&equipmentid=${equipmentid}&taskid=${taskid}">试验数据添加</a></li>
	</ul>
			<form:form id="inputForm" modelAttribute="tsBorrow" action="${ctx}/borrow/tsBorrow/borrowSJ" method="post" class="form-horizontal">
				<form:hidden path="id"/>
				<sys:message content="${message}"/>
				<fieldset>
				
					<input type="hidden" id="userCode" name="userCode" value="${tsBorrow.userCode}" class="input-medium"/>
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<c:forEach items="${TsResourceBus}" var="tsResource">
			       <th>${tsResource.columnComments}</th>
			</c:forEach>
		<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page}" var="maps">
			<tr>
				 <c:forEach items="${TsResourceBus}" var="tsResource">
			     <td>   ${not empty tsResource.dictType? fns:getDictLabel(maps[tsResource.columnName],tsResource.dictType,''):maps[tsResource.columnName]}  </td>
              </c:forEach>  
				<td>
    				<a href="${ctx}/borrow/tsBorrow/form2?id=${maps.id}&busType=${busType}">修改</a>
					<a href="${ctx}/equipment/tsEquipment/delete?id=${maps.id}" onclick="return confirmx('确认要删除该采集数据吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		   
		</tbody>
	</table>
				</fieldset>
				<div class="form-actions">
						<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
					</div>
				
			</form:form>
		
</body>
</html>
