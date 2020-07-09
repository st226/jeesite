<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仪器设备开箱验收管理</title>
	<meta name="decorator" content="default"/>
		<script src="${ctxStatic}/homePage/bootstrap_admin/scripts/jquery.jqprint-0.3.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		
		
	 function vv(){
		 $("#dayin").jqprint();
	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/business/order/swOrder/">采购任务列表标列表</a></li>
		<li class="active"><a href="${ctx}/business/receive/swReceive/form?id=${swReceive.id}">仪器设备开箱验收<shiro:hasPermission name="business:receive:swReceive:edit">${not empty swReceive.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="business:receive:swReceive:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="swReceive" action="${ctx}/business/receive/swReceive/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="state"/>
		<form:hidden path="orderId"/>
		<form:hidden path="contractId" />
		<sys:message content="${message}"/>	
		 <div id="dayin">
		<table  border='0' bordercolor='#000000' style='border-collapse:collapse;width:92%;margin-top:90px;margin-left:55px'>
			
				 <tr>
					
					<td  style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;" colspan="6" align="center">
					   
					</td>

					
				</tr>
				<tr height="38px">
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="20%"></td>
					
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%"></td>
					
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="14%"></td>
					
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="13%"></td>
					
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="13%"></td>
					
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%"></td>
					
					
					
				</tr>
				<tr height="38px">
					
					<td width="18%">
						<span style='font-size:12.0pt;font-family:宋体'>${swReceiveEquipment.equipmentName}</span>
			
					</td>
					
					<td width="17%">
						<span style='font-size:12.0pt;font-family:宋体'>${swReceiveEquipment.equipmentModel}</span>
			
					</td>

					<td width="14%">
						<span style='font-size:12.0pt;font-family:宋体'>${swReceiveEquipment.equipmentNorms}</span>
			
					</td>
	
					<td width="13%">
						<span style='font-size:12.0pt;font-family:宋体'>${swReceiveEquipment.equipmentValue}</span>
			
					</td>

					<td width="13%">
						<span style='font-size:12.0pt;font-family:宋体'>${swReceiveEquipment.equipmentPower}</span>
			
					</td>

					<td width="15%">
						<span style='font-size:12.0pt;font-family:宋体'>${swReceiveEquipment.equipmentFactoryNumber}</span>
			
					</td>
					
					
				</tr>
				
				
				</table>
	

		
	
	
		
			</div>
		
			
		
		<div class="form-actions">
			
			<input id="dayin" class="btn" type="button"  onclick="vv()" value="打 印"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>