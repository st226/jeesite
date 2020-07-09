<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采购设备清单管理</title>
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
		
		function tt(){
			var unitPrice = $("#unitPrice").val();
			var productAmount = $("#productAmount").val();
			$("#totalPrice").val(unitPrice*productAmount);
			
		}
	</script>
</head>
<body>
	<br/>
	<form:form id="inputForm" modelAttribute="swProduct" action="${ctx}/business/product/swProduct/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table-form">
				<tr>
					<td class="tit" width="15%">设备名称：</td><td width="35%">
						<form:input path="productName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				
					</td><td class="tit" width="15%">拟采购型号规格：</td><td width="35%">
						<form:input path="productType" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				
					</td>
					
				</tr>
					<tr>
					<td class="tit" width="15%">拟采购厂家：</td><td width="35%">
						<form:input path="productMade" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				
					</td><td class="tit" width="15%">参考单价（万元）：</td><td width="35%">
						<form:input path="unitPrice" htmlEscape="false" class="input-xlarge  number" onchange="tt()" />
				
					</td>
					
				</tr>
					<tr>
					<td class="tit" width="15%">数量：</td><td width="35%">
						<form:input path="productAmount" htmlEscape="false" class="input-xlarge  digits" onchange="tt()" />
				
					</td><td class="tit" width="15%">总价（万元）：</td><td width="35%">
						<form:input path="totalPrice" htmlEscape="false" class="input-xlarge  number"/>
				
					</td>
					
				</tr>
				
					<tr>
					<td class="tit" width="15%">采购时间：</td><td width="35%">
							<input name="productDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${swProduct.productDate}"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
				
					</td><td class="tit" width="15%">采购责任人：</td><td width="35%">
						<sys:treeselect id="zrUserId" name="zrUserId" value="${swProduct.zrUserId}" labelName="zrUserName" labelValue="${swProduct.zrUserName}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
				
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">设备类别：</td><td width="35%">
						 <sys:treeselect id="sbType" name="sbType" value="${swProduct.sbType}" labelName="sbTypeName" labelValue="${swProduct.sbTypeName}"
					        title="选择设备类别" url="/equipment/equipmentfunction/sbFunctionType/treeData"  cssClass="" allowClear="true"/>
				
					</td><td class="tit" width="15%">采购类别：</td><td width="35%">
						<form:select path="projectType" class="input-xlarge  "  > 
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				
					</td>
					
				</tr>
				
				<tr>
					<td class="tit" width="15%">申购原因：</td><td width="35%" colspan="3">
						<form:textarea path="reason" htmlEscape="false" maxlength="255" class="input-xxlarge "/>
				
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">需求部门：</td><td width="35%">
						<sys:treeselect id="office" name="office.id" value="${swProduct.office.id}" labelName="officeName" labelValue="${swProduct.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
				
					</td><td class="tit" width="15%">申请人：</td><td width="35%">
						<sys:treeselect id="user" name="user.id" value="${swProduct.user.id}" labelName="userName" labelValue="${swProduct.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
				
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">生产用途：</td><td width="35%" colspan="3">
						<form:textarea path="productUse" htmlEscape="false" maxlength="255" class="input-xxlarge "/>
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">备注：</td><td width="35%" colspan="3">
						<form:textarea path="remark" htmlEscape="false" maxlength="255" class="input-xxlarge "/>
					</td>
					
				</tr>
		</table>	
		
		
		
		
	</form:form>
</body>
</html>