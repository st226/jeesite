<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仪器设备开箱验收管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		
		});
		
	</script>
</head>
<body>
	<br/>
	<form:form id="inputForm" modelAttribute="swReceiveEquipment" action="${ctx}/business/receive/swReceive/save" method="post" class="form-horizontal">
	

		<table class="table-form">
				<tr>
					<td class="tit" width="15%">型号：</td><td width="35%">
						<form:input path="equipmentModel" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				
	
					</td><td class="tit" width="15%">规格：</td><td width="35%">
						<form:input path="equipmentNorms" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				
					</td>
					
				</tr>
					<tr>
					<td class="tit" width="15%">原值：</td><td width="35%">
						<form:input path="equipmentValue" htmlEscape="false" maxlength="255" class="input-xlarge "/>
						

					</td><td class="tit" width="15%">功率：</td><td width="35%">
						<form:input path="equipmentPower" htmlEscape="false" maxlength="255" class="input-xlarge "/>

					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">资产编号：</td><td width="35%">
						<form:input path="zccode" htmlEscape="false" maxlength="255" class="input-xlarge "/>
						
					</td><td class="tit" width="15%">数量：</td><td width="35%">
						<form:input path="amount" htmlEscape="false" maxlength="255" class="input-xlarge "/>

					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">单价：</td><td width="35%">
						<form:input path="unitprice" htmlEscape="false" maxlength="255" class="input-xlarge "/>
						
				
					</td><td class="tit" width="15%">总价：</td><td width="35%">
						<form:input path="price" htmlEscape="false" maxlength="255" class="input-xlarge "/>
	
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">责任部门：</td><td width="35%" >
						
						<sys:treeselect id="team" name="team" value="${swReceiveEquipment.team}" labelName="teamname" labelValue="${swReceiveEquipment.teamname}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
						
		
					</td>
					<td class="tit" width="15%">责任人：</td><td width="35%">
					<sys:treeselect id="usepeople" name="usepeople" value="${swReceiveEquipment.usepeople}" labelName="usepeoplename" labelValue="${swReceiveEquipment.usepeoplename}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
						
			
					</td>
					
				</tr>
				<tr>
				
					<td class="tit" width="15%">放置地点：</td><td width="35%">
						<form:input path="local" htmlEscape="false" maxlength="255" class="input-xlarge "/>
						
		
					</td>
					<td class="tit" width="15%">是否计量：</td><td width="35%">
						  <form:select path="isMetering" class="input-xlarge required "  > 
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('is_leaf')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
					</td>
					
				</tr>
				<tr>
				<td class="tit" width="15%">计量日期：</td><td width="35%">
						<input name="meteringDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${swReceiveEquipment.meteringDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</td>
					<td class="tit" width="15%">计量方式：</td><td width="35%" >
						<form:select path="meteringType" class="input-xlarge required "  > 
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('meteringType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">计量周期：</td><td width="35%" colspan="3">
						<form:select path="meteringTime" class="input-xlarge required "  > 
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('meteringTime')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">资金来源：</td><td width="35%">
						<sys:treeselect id="sfType" name="fsType" value="${swReceiveEquipment.fsType}" labelName="fsTypeName" labelValue="${swReceiveEquipment.fsTypeName}"
					        title="选择设备类别" url="/equipment/equipmenttype/sbEquipmentType/treeData"  cssClass="required" allowClear="true"/>
						
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">设备类型：</td><td width="35%">
						<sys:treeselect id="sbType" name="sbType" value="${swReceiveEquipment.sbType}" labelName="sbTypeName" labelValue="${swReceiveEquipment.sbTypeName}"
					        title="选择设备类别" url="/equipment/equipmentfunction/sbFunctionType/treeData"  cssClass="required" allowClear="true"/>
					</td>
					
				</tr>
				
				
			</table>	
	

		
	
	
		
		
		
		
		
	
	</form:form>
</body>
</html>