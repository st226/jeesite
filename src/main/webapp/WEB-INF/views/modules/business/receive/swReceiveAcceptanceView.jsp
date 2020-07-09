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
			<table border='1'  bordercolor='#000000' style='border-collapse:collapse;table-layout:fixed;width: 90%'>
			<caption  style="font-size: 20px;margin-bottom: 30px" >固 定 资 产 卡 片</caption>
				<tr height="50px">
					<td  width="10%">编号</td><td width="15%">${sbEquipment.sbcode}</td>
					<td  width="10%">名称</td><td width="15%">${sbEquipment.name}</td>
					<td  width="10%">新旧程度</td><td width="15%">新</td>
					<td  width="10%">财产来源</td><td width="15%">${swReceive.made}</td>
					
				</tr>
				<tr height="50px">
					<td  width="10%">牌号</td><td width="15%">${swReceive.sbModel}</td>
					<td  width="10%">规格</td><td width="15%">${swReceive.sbNorms}</td>
					<td  width="10%">财产原值</td><td width="15%">${sbEquipment.price}</td>
					<td  width="10%">保管地点</td><td width="15%">${sbEquipment.field4}</td>
					
				</tr>
				<tr height="50px">
					<td  width="10%">数量</td><td width="15%">1</td>
					<td  width="10%">特征</td><td width="15%">???</td>
					<td  width="10%">来源时间</td><td width="15%">${swReceive.acceptorDate}</td>
					<td  width="10%">已使年限</td><td width="15%">0</td>
					
				</tr>
				<tr height="50px">
					<td  width="10%">所属设备</td><td width="15%" colspan="7">${swReceiveEquipment.teamname}${swReceiveEquipment.usepeoplename}</td>
				
					
				</tr>
				<tr height="50px">
					<td  width="10%">折旧价格</td><td width="15%"></td>
					<td  width="10%">折旧年限</td><td width="15%">??</td>
					<td  width="10%">年折旧额</td><td width="15%"></td>
					<td  width="10%">清理残值</td><td width="15%"></td>
					
				</tr>
				<tr height="50px">
					<td  width="15%">备注</td><td width="15%" colspan="7">${sbEquipment.sbcode}</td>
				
					
				</tr>
				
			
				
				
			</table>	
			

		
	</div>
	
		<div class="form-actions">
			<input id="btnSubmit" class="btn" type="button"  onclick="dayin()" value="打 印"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>

</body>
</html>