<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报销单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
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
		});
		
		 function tt(id){
			 var key = "${swReimbursement.id}";
				
				if(key==null || key==""){
					alert("请先保存，再打印！");
					return ;
				}
				
				window.open("${ctx}/business/reimbursement/swReimbursement/view?id=${swReimbursement.id}",'top'); 
			
				
			}
			
			function ok(e){
				var file = $("#file").val();
				if(file==null || file==""){
					alert("请先上传审批结果！");
				}else{
					 $("#state").val("1");
						$("#inputForm").submit();
				}
			}
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/business/order/swOrder/">采购任务列表</a></li>
		<li class="active"><a href="${ctx}/business/reimbursement/swReimbursement/form?id=${swReimbursement.id}">报销单<shiro:hasPermission name="business:reimbursement:swReimbursement:edit">${not empty swReimbursement.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="business:reimbursement:swReimbursement:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="swReimbursement" action="${ctx}/business/reimbursement/swReimbursement/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="orderId"/>
		<form:hidden path="state"/>
		<form:hidden path="supplierId"/>
		<sys:message content="${message}"/>
		<table class="table-form">
				<tr>
					<td class="tit" width="10%">结算单位：</td><td colspan="1">
							<form:input path="supplierName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				
					</td>
					<td class="tit" width="10%">收支号：</td><td colspan="1">
							<form:input path="mark" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				
					</td>
					
				</tr>	
				<tr>
					<td class="tit" width="10%">开支项目：</td><td colspan="1">
							<form:input path="projectName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				
					</td>
					<td class="tit" width="10%">项目编号：</td><td colspan="1">
							<form:input path="projectCode" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				
					</td>
					
				</tr>	
				<tr>
					<td class="tit" width="10%">质量成本<form:checkboxes path="isQuality" items="${fns:getDictList('is_yes')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>：</td><td colspan="1">
			
					      

							<form:input path="quality" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				
					</td>
					<td class="tit" width="10%">开支金额：</td><td colspan="1">
							<form:input path="amount" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				
					</td>
					
				</tr>	
				<tr>
					<td class="tit" width="10%">上传报销单及发票：</td><td width="35%" colspan="3">
							<form:hidden id="file" path="file" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="file" type="files" uploadPath="/business/survey/swSurvey" selectMultiple="true"/>
				
					</td>
					
				</tr>
					
		
		</table>
		
		
		
	
		
		<div class="form-actions">
			<shiro:hasPermission name="business:reimbursement:swReimbursement:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="暂 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnSubmitok" class="btn  btn-primary"   type="button"   onclick="ok(1)"  value="提交"/>
			<input id="dayin" class="btn" type="button"  onclick="tt(1)" value="报销单预览"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>