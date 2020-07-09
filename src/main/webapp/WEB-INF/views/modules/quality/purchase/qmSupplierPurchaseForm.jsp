<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合格供方目录外采购申请表管理</title>
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
		
		
         function ok(e){
			
			$("#state").val("1");
			$("#inputForm").submit();
			
		    
		
		}
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/quality/purchase/qmSupplierPurchase/">合格供方目录外采购申请表列表</a></li>
		<li class="active"><a href="${ctx}/quality/purchase/qmSupplierPurchase/form?id=${qmSupplierPurchase.id}">合格供方目录外采购申请表<shiro:hasPermission name="quality:purchase:qmSupplierPurchase:edit">${not empty qmSupplierPurchase.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="quality:purchase:qmSupplierPurchase:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="qmSupplierPurchase" action="${ctx}/quality/purchase/qmSupplierPurchase/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="processinstid"/>
		<form:hidden path="state"/>
		<sys:message content="${message}"/>	
		<table class="table-form">
				<tr>
					<td class="tit" width="15%">产品名称：</td><td width="35%" colspan="3">
						<form:input path="productName" htmlEscape="false" maxlength="255" class="input-xxlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">产品规格/技术标准/图纸/任务书：</td><td width="35%" colspan="3">
						<form:input path="productType" htmlEscape="false" maxlength="255" class="input-xxlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">质量等级：</td><td width="35%" colspan="3">
						<form:input path="productClass" htmlEscape="false" maxlength="255" class="input-xxlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">生产厂家：</td><td width="35%" colspan="3">
						<form:input path="enterpriseName" htmlEscape="false" maxlength="255" class="input-xxlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">企业性质：</td><td width="35%" colspan="3">
						<form:select path="enterpriseNature" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('supplier_nature')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">工程型号：</td><td width="35%" >
						<form:input path="projectModel" htmlEscape="false" maxlength="255" class="input-xlarge "/>
					</td>
					<td class="tit" width="15%">单机名称：</td><td width="35%" >
						<form:input path="standName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">单机用量：</td><td width="35%" >
						<form:input path="standDosage" htmlEscape="false" maxlength="255" class="input-xlarge "/>
					</td>
					<td class="tit" width="15%">单技数量：</td><td width="35%" >
						<form:input path="standAmount" htmlEscape="false" maxlength="255" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">申请理由、风险识别控制：</td><td width="35%" colspan="3">
						<form:textarea path="reason" htmlEscape="false" maxlength="255" class="input-xxlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">廉洁书：</td><td width="35%" colspan="3">
						<span class="help-inline"><font color="red">本人承诺严格遵守国家法律法规和公司相关制度规定，与供应商无利益关系</font></span>
					</td>
					</tr>
				<tr>
					<td class="tit" width="15%">本人遵守：</td><td width="35%" colspan="3">
						<form:radiobuttons path="statement" items="${fns:getDictList('is_leaf')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">申请人：</td><td width="35%" colspan="3">
						<form:input path="applicant" htmlEscape="false" maxlength="255" class="input-xlarge "/>
					</td>
				</tr>
			</table>	
		
		
		
		
		
		
		
		<div class="form-actions">
			<shiro:hasPermission name="quality:purchase:qmSupplierPurchase:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnSubmitok" class="btn  btn-primary"   type="button"   onclick="ok(1)"  value="提交"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>