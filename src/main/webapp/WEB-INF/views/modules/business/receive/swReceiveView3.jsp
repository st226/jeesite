<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仪器设备开箱验收管理(公开)</title>
	<meta name="decorator" content="default"/>
		<script src="${ctxStatic}/homePage/bootstrap_admin/scripts/jquery.jqprint-0.3.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("input[type='checkbox']").each(function () {
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
		
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
            });
			
		});
		
		
	 function vv(){
		 $("#dayin").jqprint();
	}
	</script>
</head>
<body>
	<br/>
	<form:form id="inputForm" modelAttribute="swReceive" action="${ctx}/business/receive/swReceive/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="state"/>
		<form:hidden path="orderId"/>
		<form:hidden path="contractId" />
		<sys:message content="${message}"/>	
		 <div id="dayin">
		<table  border='1' bordercolor='#000000' style='border-collapse:collapse;table-layout:fixed;width:680px;margin-left: 36px;margin-right:36px;margin-top:36px;'>
			 <caption align="top"  style="font-size: 30px;margin-bottom: 30px" >仪器设备开箱验收单</caption>
				<tr height="50px">
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">计划编号：</td>
					<td width="20%" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >
						${swReceive.planNumber}
			
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">所属项目：</td>
					<td width="20%" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >
						
				${swReceive.items}
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">资产归属：</td>
					<td width="15%" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>
						
				${swReceive.assets}
					</td>
					
				</tr>
				
				<tr height="50px">
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">合同名称：</td>
					<td width="20%" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>
						${swReceive.field8}
			
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">合同编号：</td>
					<td width="20%" colspan="3" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>
						
				${swReceive.contractCode}
					</td>
					
					
				</tr>
				
			
				<tr height="50px">
				<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%" >供应商：</td>
				<td width="20%" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >
						
				${swReceive.supplier}
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">供应商联系人：</td>
					<td width="20%" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>
						${swReceive.supplierUser}
			
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">供应商联系电话：</td>
					<td width="20%" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>
						
				${swReceive.supplierPhone}
					</td>
					
					
				</tr>
				
				<tr height="50px">
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">合同负责人：</td>
					<td width="20%" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>
						${swReceive.acceptor}
			
					</td>
					
					
					
			
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%" >开箱地点：</td>
					<td width="20%" colspan="3" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>
						${swReceive.location}
			
					</td>
					
					
				</tr>
					</table>	
			<table  border='1' bordercolor='#000000' style='border-collapse:collapse;table-layout:fixed;width:680px;margin-left: 36px;margin-right:36px;'>
			  
			
	
					 
					  
		
						
				<c:forEach items="${swReceive.swReceiveEquipmentList}" var="data">
				  <tr height="50px">
				  <td  rowspan="7" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%" >   ${data.equipmentName}
					</td>
				  <td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >  使用部门：
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>   ${data.teamname}
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'> 制造商：
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >   ${data.made}
					</td>
				</tr>
				<tr height="50px">
				 
				  <td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >  到货日期：
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>   ${data.cometime}
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' > 出厂日期：
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >   ${data.outtime}
					</td>
				</tr>
				<tr height="50px">
				 
				  <td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>  技术协议一致性：
					</td>
					<td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >  
					
					<form:checkboxes path="isAgreement" items="${fns:getDictList('is_agreement')}" itemLabel="label" itemValue="value"  data-value="${data.isAgreement}"/>
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' > 外观完整性：
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >   
					<form:checkboxes path="isAppearance" items="${fns:getDictList('is_appearance')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""  data-value="1"/>
					
					
					</td>
				</tr>
				<tr height="50px">
				 
				  <td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >  型号:
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >   ${data.equipmentModel}
					</td>
					<td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' > 型号一致性:
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >   <form:checkboxes path="isModel" items="${fns:getDictList('is_leaf')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""  data-value="1"/>
					
					</td>
				</tr>
				<tr height="50px">
				 
				  <td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'  > 规格:
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >   ${data.equipmentNorms}
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' > 规格一致性:
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >   <form:checkboxes path="isNorms" items="${fns:getDictList('is_leaf')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""  data-value="1"/>
					
					</td>
				</tr>
				<tr height="50px">
				 
				  <td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' > 原值:
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >   ${data.equipmentValue}
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' > 功率:
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >   ${data.equipmentPower}
					</td>
				</tr>
				<tr height="50px">
				 
				  <td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >出厂编号:
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >   ${data.equipmentFactoryNumber}
					</td>
					<td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' > 数量:
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'  >   ${data.amount}
					</td>
				</tr>
				
				</c:forEach>
				
				
			
					
				</table>	
			<table  border='1' bordercolor='#000000' style='border-collapse:collapse;table-layout:fixed;width:680px;margin-left: 36px;margin-right:36px;'>	
				<tr height="50px">
			
	
					<td rowspan="20" width="15%" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >   装箱资料清点
					</td>
					<td  colspan="1" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>   设备名称
					</td>
					<td  colspan="1" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>   资料名称
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >  份数 
					</td>
					<td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>   页数
					</td>
					<td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>   特殊介质
					</td>
				
				
				</tr>	
				
				<c:forEach items="${swReceive.swReceiveDataList}" var="data">
				  <tr height="50px">
			
	
					<td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>   ${data.equipmentName}
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >   ${data.name}
					</td>
					<td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>   ${data.copies}
					</td>
					<td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>  ${data.pages}
					</td>
					<td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>  ${data.special}
					</td>
				
				
				</tr>
				</c:forEach>
				
				
			</table>	
			<table  border='1' bordercolor='#000000' style='border-collapse:collapse;table-layout:fixed;width:680px;margin-left: 36px;margin-right:36px;'>
			<tr height="50px">
			
	
					<td colspan="2">   以上内容由固定资产与信息技术部填写
					</td>
					<td  colspan="2">   验收人：
					</td>
					<td colspan="2" >  验收日期：
					</td>
					
				
				</tr>	
				<tr height="50px">
			
	
					<td colspan="2">   装箱资料接收部门及接收人：
					</td>
					<td  >   档案室
					</td>
					<td  >   
					</td>
					<td colspan="2" >  接收日期：
					</td>
					
				
				</tr>	
				<tr height="80px">
			
	
					<td colspan="6">   其他参加验收的部分及人员：
					</td>
					
				
				</tr>	
				</table>
			<p style="font-family: 宋体, SimHei;line-height:150%">&nbsp;&nbsp;&nbsp;&nbsp;注：本单机一式二份，技改固定资产与信息部设备管理员留存一份，档案管理员负责归档一份
	        </p>

		
	
	
		
			</div>
		
			
		
		<div class="form-actions">
			
			<input id="dayin" class="btn" type="button"  onclick="vv()" value="打 印"/>
			<input id="btnCancel" class="btn" type="button" value="关 闭" onclick="window.close();"/>
		</div>
	</form:form>
</body>
</html>