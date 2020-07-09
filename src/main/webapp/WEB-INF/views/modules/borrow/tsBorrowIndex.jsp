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
		
		<li class="active"><a href="${ctx}/borrow/tsBorrow/borrow">文档上传</a></li>
        <li><a href="${ctx}/gen/genTable/"></a></li>
	</ul>
	<c:choose>
		<c:when test="${empty tsBorrow.userCode}">
			<form:form id="inputForm" modelAttribute="tsBorrow" action="${ctx}/resourcebus/tsResourceBus/formjg" method="post" class="form-horizontal">
				<form:hidden path="id"/>
				<sys:message content="${message}"/>
				<br/>
				<div class="control-group">
					<label class="control-label">选择要上传文件的类型:</label>
					
					<div class="controls">
				       <sys:treeselect id="userCode" name="userCode" value="${tsResourceType.parent.id}" labelName="parent.name" labelValue="${tsResourceType.parent.name}"
					        title="选择型号/设备" url="/resourcetype/tsResourceType/treeData"  extId="${tsResourceType.id}" cssClass="required" allowClear="true"/>
			       <span class="help-inline"><font color="red">*</font> </span>
			         </div>
					<div class="form-actions">
						<input id="btnSubmit" class="btn btn-primary" type="submit" value="下一步"/>&nbsp;
						<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
					</div>
				</div>
			</form:form>
		</c:when>
		<c:otherwise>
			<form:form id="inputForm" modelAttribute="tsBorrow" action="${ctx}/borrow/tsBorrow/borrowSJ" method="post" class="form-horizontal">
				<form:hidden path="id"/>
				<sys:message content="${message}"/>
				<fieldset>
				
				    <legend>本次采集型号/型号设备</legend>
					<div class="control-group">
						<label class="control-label">型号/型号设备:</label>
						<div class="controls">
							 <sys:treeselect id="userCode" name="userCode" value="${tsBorrow.userCode}" labelName="parent.name" labelValue="${tsBorrow.remarks}"
					        title="选择型号/设备" url="/resourcetype/tsResourceType/treeData" extId="${tsResourceType.id}" cssClass="" allowClear="true"/>
						</div>
					</div>
					<legend>本次采集实体设备列表</legend>
					<input type="hidden" id="userCode" name="userCode" value="${tsBorrow.userCode}" class="input-medium"/>
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			<th title="选择"><input type="checkbox" id="checkedAll"></th>
				<th>设备编号</th>
				<th>所属型号</th>
				<th>采集部门</th>
				<th>任务时间</th>
				<th>试验地</th>
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page}" var="maps">
			<tr>
			<td>
                                            <input type="checkbox" name="checkbox_name[${vs.index}]"/>
                                    </td>
			    <td>${maps.name}</td>
			    <td>${maps.format}</td>
			    <td>${maps.department}</td>
			    <td>${maps.inspection}</td>
			    <td>${maps.weight}</td>
				
			
			</tr>
		</c:forEach>
		   
		</tbody>
	</table>
				</fieldset>
				<div class="form-actions">
						<input id="btnSubmit" class="btn btn-primary" type="submit" value="提交"/>&nbsp;
						<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
					</div>
				
			</form:form>
		</c:otherwise>
	</c:choose>
</body>
</html>
