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
		
		function dayin(){
			 $("#dayin").jqprint();
		}
		
	</script>
</head>
<body>
	 <div id="dayin">
  
          </br>
			<table border='1'  bordercolor='#000000' style='border-collapse:collapse;table-layout:fixed;width: 680px;margin-left: 36px;margin-right:36px;margin-top:36px;'>
			<caption  style="font-size: 20px;margin-bottom: 30px" >固定资产移交、验收单</caption>
				<tr height="50px">
					<td class="tit" width="13%">设备名称：</td><td width="20%">
						${swReceiveEquipment.equipmentName}
				
	
					</td><td class="tit" width="13%">型号规格：</td><td width="20%">
						
				       ${swReceiveEquipment.equipmentModel}
					</td>
					<td class="tit" width="14%">出厂编号：</td><td width="20%">
						
				       ${swReceiveEquipment.equipmentFactoryNumber}
					</td>
					
				</tr>
				<tr height="50px">
					<td class="tit">资产编号：</td><td >
						${swReceiveEquipment.zccode}
				
	
					</td><td class="tit" >单价：</td><td >
						
				       ${swReceiveEquipment.unitprice}
					</td>
					<td class="tit" >总价：</td><td >
						
				       ${swReceiveEquipment.price}
					</td>
					
				</tr>
			
				<tr>
				   <td height="100px" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >接收单位意见：</td>
				    <td colspan="5"  style='vertical-align:bottom; text-align:right;WORD-WRAP:break-word;' >
					负责人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="100px" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >移交单位意见：</td>
				    <td colspan="5"  style='vertical-align:bottom; text-align:right;WORD-WRAP:break-word;' >
					负责人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="100px" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >档案部门意见：</td>
				    <td colspan="5"  style='vertical-align:bottom; text-align:right;WORD-WRAP:break-word;' >
					负责人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				
			</table>	
			</br>
			</br>
			</br>
			</br>
			</br>
			<table border='1'  bordercolor='#000000' style='border-collapse:collapse;table-layout:fixed;width: 680px;margin-left: 36px;margin-right:36px;margin-top:36px;'>
			<caption  style="font-size: 20px;margin-bottom: 30px" >固定资产移交、验收单</caption>
				<tr height="50px">
					<td class="tit" width="13%">设备名称：</td><td width="20%">
						${swReceiveEquipment.equipmentName}
				
	
					</td><td class="tit" width="13%">型号规格：</td><td width="20%">
						
				       ${swReceiveEquipment.equipmentModel}
					</td>
					<td class="tit" width="14%">出厂编号：</td><td width="20%">
						
				       ${swReceiveEquipment.equipmentFactoryNumber}
					</td>
					
				</tr>
				<tr height="50px">
					<td class="tit">资产编号：</td><td >
						${swReceiveEquipment.zccode}
				
	
					</td><td class="tit" >单价：</td><td >
						
				       ${swReceiveEquipment.unitprice}
					</td>
					<td class="tit" >总价：</td><td >
						
				       ${swReceiveEquipment.price}
					</td>
					
				</tr>
			
				<tr>
				   <td height="100px" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >接收单位意见：</td>
				    <td colspan="5"  style='vertical-align:bottom; text-align:right;WORD-WRAP:break-word;' >
					负责人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="100px" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >移交单位意见：</td>
				    <td colspan="5"  style='vertical-align:bottom; text-align:right;WORD-WRAP:break-word;' >
					负责人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="100px" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >档案部门意见：</td>
				    <td colspan="5"  style='vertical-align:bottom; text-align:right;WORD-WRAP:break-word;' >
					负责人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				
			</table>	
	

		
	</div>
	
		<div class="form-actions">
			<input id="btnSubmit" class="btn" type="button"  onclick="dayin()" value="打 印"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>

</body>
</html>