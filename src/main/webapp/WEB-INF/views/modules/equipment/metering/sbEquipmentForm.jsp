<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仪器设备开箱验收管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		
		});
		
           function tt(){
			
			var da = $("#field14").val() ;
			var time = $("#field11").val() ;
			if(time=='1')
			$("#field6").val(datetostring(da,365));
			if(time=='2')
				$("#field6").val(datetostring(da,730));
			if(time=='3')
				$("#field6").val(datetostring(da,183));

			$("#field13").val(datetostring2(da,10));
			

		}
		
           function datetostring(day,num){
   			var d = new Date(day);
            d = d.setDate(d.getDate()+num);
   			d = new Date(d);
   			return d.toISOString().split("T")[0];
   		}
           
           function datetostring2(day,num){
      			var d = new Date(day);
               d = d.setDate(d.getDate()-num);
      			d = new Date(d);
      			return d.toISOString().split("T")[0];
      		}
		
	</script>
</head>
<body>
	<br/>
	<form:form id="inputForm" modelAttribute="sbEquipment" action="${ctx}/business/receive/swReceive/save" method="post" class="form-horizontal">
	

		<table class="table-form">
				
				
				<tr>
				
					<td class="tit" width="15%">计量设备类型：</td><td width="35%">
						<sys:treeselect id="field15" name="field15" value="${sbEquipment.field15}" labelName="field16" labelValue="${sbEquipment.field16}"
					title="设备类型" url="/metering/meteringtype/sbMeteringType/treeData" cssClass="" allowClear="true"/>
				
		
					</td>
					<td class="tit" width="15%">是否计量：</td><td width="35%">
						  <form:select path="field5" class="input-xlarge required "  > 
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('is_leaf')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
					</td>
					
				</tr>
				<tr>
				
					<td class="tit" width="15%">计量方式：</td><td width="35%" >
						<form:select path="field12" class="input-xlarge required "  > 
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('meteringType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
					</td>
					
				
					<td class="tit" width="15%">计量周期：</td><td width="35%">
						<form:select path="field11" class="input-xlarge required "  > 
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('meteringTime')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
					</td>
					
				</tr>
				<tr>
				
					<td class="tit" width="15%">上年度有效日期：</td><td width="35%" >
						<input id="field13" name="field13" type="text" readonly="readonly" maxlength="20" class="Wdate "
						value="${sbEquipment.field13}"
						 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</td>
					
			
					<td class="tit" width="15%">检测日期：</td><td width="35%">
						<input id="field14" name="field14" type="text" readonly="readonly" maxlength="20"  onchange="tt()" class="Wdate "
						value="${sbEquipment.field14}"
						 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</td>
					
				</tr>
				<tr>
				
					<td class="tit" width="15%">最新有效期：</td><td width="35%" >
						<input id="field6" name="field6" type="text" readonly="readonly" maxlength="20" class="Wdate "
						value="${sbEquipment.field6}"
						 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</td>
					
				
					<td class="tit" width="15%">计量状态：</td><td width="35%">
						<form:select path="field17" class="input-xlarge required "  > 
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('meteringState')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
					</td>
					
				</tr>
				
				
			</table>	
	

		
	
	
		
		
		
		
		
	
	</form:form>
</body>
</html>