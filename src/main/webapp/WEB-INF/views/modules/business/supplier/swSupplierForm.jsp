<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商管理</title>
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
			
			 $('#bankNumber').on('keyup mouseout input',function(){
		            var $this = $(this),
		                v = $this.val();
		            /\S{5}/.test(v) && $this.val(v.replace(/\s/g,'').replace(/(\d{4})(?=\d)/g, "$1 "));
		        });
		        $('#dutyno').on('keyup mouseout input',function(){
		            var $this = $(this),
		                v = $this.val();
		            /\S{5}/.test(v) && $this.val(v.replace(/\s/g,'').replace(/(\d{4})(?=\d)/g, "$1 "));
		        });
		       
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/business/supplier/swSupplier/">供应商列表</a></li>
		<li class="active"><a href="${ctx}/business/supplier/swSupplier/form?id=${swSupplier.id}">供应商<shiro:hasPermission name="business:supplier:swSupplier:edit">${not empty swSupplier.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="business:supplier:swSupplier:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="swSupplier" action="${ctx}/business/supplier/swSupplier/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table class="table-form">
				<tr>
					<td class="tit" width="15%">供应商名称：</td><td width="35%">
							<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					
					</td><td class="tit" width="15%">公司地址：</td><td width="35%">
						<form:input path="local" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">供应商税号：</td><td width="35%">
							<form:input path="dutyno" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				
					</td><td class="tit" width="15%">供应商类型：</td><td width="35%">
						<form:select path="supplierType" class="input-xlarge">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('supplier_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">法人：</td><td width="35%">
							<form:input path="legal" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				
					</td><td class="tit" width="15%">代理人：</td><td width="35%">
						<form:input path="agent" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">联系人：</td><td width="35%">
							<form:input path="supplierUser" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">联系人电话：</td><td width="35%">
						<form:input path="userPhone" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">开户行：</td><td width="35%">
							<form:input path="bankName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				
					</td><td class="tit" width="15%">账号：</td><td width="35%">
						<form:input path="bankNumber" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">开户行地址：</td><td width="35%">
							<form:input path="bankLocal" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				
					</td><td class="tit" width="15%">公司电话：</td><td width="35%">
						<form:input path="supplierTel" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				
					</td>
					
				</tr>
				
				<tr>
					<td class="tit" width="15%">公司传真：</td><td width="35%">
							<form:input path="supplierFax" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				
					</td><td class="tit" width="15%">公司邮编：</td><td width="35%">
						<form:input path="supplierZip" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">附件：</td><td width="35%" colspan="3">
							<form:hidden id="appendix" path="appendix" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="appendix" type="files" uploadPath="/business/supplier/swSupplier" selectMultiple="true"/>
				
					</td>
					
				</tr>
		</table>		
		
		
		
		<div class="form-actions">
			<shiro:hasPermission name="business:supplier:swSupplier:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>