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
		 <div id="dayin" style="margin-left: 25px;margin-right: 25px;margin-top: 32px;">
		<table  border='1' bordercolor='#000000' style='border-collapse:collapse;width:700px'>
			 <caption align="top"  style="font-size: 30px;margin-bottom: 30px" >仪器设备开箱验收单</caption>
				<tr height="50px">
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">计划编号：</td><td width="20%">
						${swReceive.planNumber}
			
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">所属项目：</td><td width="20%">
						
				${swReceive.items}
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">资产归属：</td><td width="15%">
						
				${swReceive.assets}
					</td>
					
				</tr>
				
				<tr height="50px">
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">设备名称：</td><td width="20%">
						${swReceive.sbName}
			
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">合同编号：</td><td width="20%" colspan="3">
						
				${swReceive.items}
					</td>
					
					
				</tr>
				
				<tr height="50px">
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">使用部门：</td><td width="20%">
						${swReceive.office.name}
			
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%" rowspan="2">制造商：</td><td width="20%" rowspan="2">
						
				${swReceive.made}
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">出厂日期：</td><td width="15%">
						
				<fmt:formatDate value="${swReceive.productionDate}" pattern="yyyy-MM-dd"/>
					</td>
					
				</tr>
				
				<tr height="50px">
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">申请人：</td><td width="20%">
						${swReceive.user.name}
			
					</td>
					
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">到货日期：</td><td width="15%">
						
				<fmt:formatDate value="${swReceive.arrivalDate}" pattern="yyyy-MM-dd"/>
					</td>
					
				</tr>
				
				
				
				
				
				
				
				<tr height="50px">
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">联系方式：</td><td width="20%">
						${swReceive.contactInformation}
			
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%" rowspan="2">供应商：</td><td width="20%" rowspan="2">
						
				${swReceive.supplier}
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">供应商联系人：</td><td width="15%">
						
				${swReceive.supplierUser}
					</td>
					
				</tr>
				<tr height="50px">
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">开箱地点：</td><td width="20%">
						${swReceive.location}
			
					</td>
					
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">联系方式：</td><td width="15%">
						
				${swReceive.supplierPhone}
					</td>
					
				</tr>
				
				<tr height="50px">
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%" rowspan="6">装箱货物检查：</td>
					<td class="tit" colspan="5">装箱货物是否与合同（技术协议）一致：<form:radiobuttons path="isAgreement" items="${fns:getDictList('is_agreement')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
					</td>
				
				</tr>
				<tr height="50px">
				
			
					<td class="tit" colspan="5">货物外观完整性检查：<form:radiobuttons path="isAppearance" items="${fns:getDictList('is_appearance')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
					</td>
				
				
				</tr>
				<tr height="50px">
			
	
					<td class="tit" colspan="5">规格是否与合同（技术协议）一致：<form:radiobuttons path="isNorms" items="${fns:getDictList('is_agreement')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
					</td>
				
				
				</tr>
				<tr height="50px">
			
	
					<td class="tit" colspan="5">型号是否合同（技术协议）一致：<form:radiobuttons path="isModel" items="${fns:getDictList('is_agreement')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
					</td>
				
				
				</tr>
				<tr height="50px">
			
	
					<td class="tit" colspan="5">出厂编号：${swReceive.factoryNumber}
					</td>
				
				
				</tr>
				<tr height="50px">
			
	
					<td class="tit" colspan="5">   
					</td>
				
				
				</tr>
					
				
				<tr height="50px">
			
	
					<td rowspan="20" >   装箱资料清点
					</td>
					<td  colspan="2">   资料名称
					</td>
					<td  >  份数 
					</td>
					<td  >   页数
					</td>
					<td  >   特殊介质
					</td>
				
				
				</tr>	
				
				<c:forEach items="${swReceive.swReceiveDataList}" var="data">
				  <tr height="50px">
			
	
					
					<td  colspan="2">   ${data.name}
					</td>
					<td  >  ${data.copies}
					</td>
					<td  >  ${data.pages}
					</td>
					<td  >  ${data.special}
					</td>
				
				
				</tr>
				</c:forEach>
				
				
			</table>	
			<table  border='1' bordercolor='#000000' style='border-collapse:collapse;table-layout:fixed;width: 92%'>
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
					<td colspan="2" >  接收日期
					</td>
					
				
				</tr>	
				<tr height="80px">
			
	
					<td colspan="6">   其他参加验收的部分及人员：
					</td>
					
				
				</tr>	
				</table>
			注：本单机一式二份，技改固定资产与信息部设备管理员留存一份，档案管理员负责归档一份
	

		
	
	
		
			</div>
		
			
		
		<div class="form-actions">
			
			<input id="dayin" class="btn" type="button"  onclick="vv()" value="打 印"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>