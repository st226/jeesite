<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <style type="text/css">
.x {
border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;
}
</style>
	<title>合同管理(公开)</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/homePage/bootstrap_admin/scripts/jquery.jqprint-0.3.js" type="text/javascript"></script>
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
		
		function dayin(){
			 $("#dayin").jqprint();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/business/contract/swContract/">合同列表</a></li>
		<li class="active"><a href="${ctx}/business/contract/swContract/form?id=${swContract.id}">合同<shiro:hasPermission name="business:contract:swContract:edit">${not empty swContract.id?'打印':'添加'}</shiro:hasPermission><shiro:lacksPermission name="business:contract:swContract:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="swContract" action="${ctx}/business/contract/swContract/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div id="dayin">
		<table   border='1' bordercolor='#000000' style='border-collapse:collapse;width: 620px;margin-left: 50px;margin-right:56px;margin-top:16px;'></br>
		 <caption align="top"  style="font-size: 28px;margin-bottom: 10px;line-height:250%" ><b>北京航天时代激光导航技术有限责任公司合同审批表</b></caption>
		        <tr height="40px">
					<td  style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;font-family: 黑体, SimHei; text-align:center;" colspan="2">合同(协议)编号：
						${swContract.contractCode}
					</td>
					<td  style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;" ></td>
					<td  style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;" ></td>
					<td   style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;"   ></td><td    style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;font-family: 黑体, SimHei; text-align:center;" >
						
				      单位：元
					</td>
					
				</tr>
				<tr  height="40px">
					<td style='font-family: 黑体, SimHei; text-align:center;' width="13%">合同名称：</td><td width="23%" style="font-family: 黑体, SimHei; text-align:center;">
						
				       ${swContract.contractName}
					</td>
					
				
					<td style="font-family: 黑体, SimHei; text-align:center;" width="12%">合同类型：</td><td style="font-family: 黑体, SimHei; text-align:center;" width="18%">
					${fns:getDictLabel(swContract.contractType, 'contract_type', '')}
			
		
					</td><td style="font-family: 黑体, SimHei; text-align:center;" width="12%">合同价款：</td><td width="20%" style="font-family: 黑体, SimHei; text-align:center;">
					${swContract.contractPrice}
							
					</td>
					
				</tr>
				<tr height="40px" >
					<td style="font-family: 黑体, SimHei; text-align:center;" >对方单位：</td><td style="font-family: 黑体, SimHei; text-align:center;"  colspan="5">
					    ${swContract.supplierName}
						
				</tr>
				<tr height="40px" >
					<td style="font-family: 黑体, SimHei; text-align:center;" >联系人：</td><td style="font-family: 黑体, SimHei; text-align:center;">
					       ${swContract.supplierUser}
							
					</td>
					
				
					<td style="font-family: 黑体, SimHei; text-align:center;">联系电话：</td><td style="font-family: 黑体, SimHei; text-align:center;" colspan="3">
					   ${swContract.supplierPhone}
						
					</td>
				</tr>
				<tr height="40px" >
					<td style="font-family: 黑体, SimHei; text-align:center;" >经办人：</td><td style="font-family: 黑体, SimHei; text-align:center;">
					     ${swContract.contractUser}
							
					</td>
					<td style="font-family: 黑体, SimHei; text-align:center;" >申请日期：</td><td style="font-family: 黑体, SimHei; text-align:center;">
					    
						<fmt:formatDate value="${swContract.createDate}" pattern="yyyy-MM-dd"/>	
					</td>
					<td style="font-family: 黑体, SimHei; text-align:center;" >联系电话：</td><td style="font-family: 黑体, SimHei; text-align:center;">
					      ${swContract.contractPhone}
						
					</td>
				</tr>	
				
				<tr  height="40px" >
					<td style="font-family: 黑体, SimHei; text-align:center;">附件：</td><td colspan="3" style="font-family: 黑体, SimHei; text-align:center;">
					   
							${swContract.field1}
					</td>
					<td style="font-family: 黑体, SimHei; text-align:center;" >页数：</td><td style="font-family: 黑体, SimHei; text-align:center;">
					${swContract.appendixLength}
					
					</td>
				</tr>	
				<tr>
				   <td height="90px" style="font-family: 黑体, SimHei; text-align:center;" width="15%">申请部门领导或项目负责人意见：</td>
				   <td colspan="5"  style="font-family: 黑体, SimHei; text-align:center;text-align:right;vertical-align:bottom;" width="15%">
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="90px" style="font-family: 黑体, SimHei; text-align:center;" width="15%">主管部门领导意见：</td>
					 <td colspan="5"  style="font-family: 黑体, SimHei; text-align:center;text-align:right;vertical-align:bottom;" width="15%">
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="90px" style="font-family: 黑体, SimHei; text-align:center;" width="15%">法律部门会签：</td>
				    <td colspan="5"  style="font-family: 黑体, SimHei; text-align:center;text-align:right;vertical-align:bottom;" width="15%">
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="90px" style="font-family: 黑体, SimHei; text-align:center;" width="15%">财务部门领导会签：</td>
				    <td colspan="5"  style="font-family: 黑体, SimHei; text-align:center;text-align:right;vertical-align:bottom;" width="15%">
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="90px" style="font-family: 黑体, SimHei; text-align:center;" width="15%">公司主管领导审批：</td>
				    <td colspan="5"  style="font-family: 黑体, SimHei; text-align:center;text-align:right;vertical-align:bottom;" width="15%">
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="90px" style="font-family: 黑体, SimHei; text-align:center;" width="15%">总经理审批：</td>
				    <td colspan="5"  style="font-family: 黑体, SimHei; text-align:center;text-align:right;vertical-align:bottom;" width="15%">
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="90px" style="font-family: 黑体, SimHei; text-align:center;" width="15%">说明：</td><td colspan="5" style="font-family: 黑体, SimHei; text-align:center;">
					   各类合同如有特殊审批要求的，包括但不限于以上审批流程。
					</td>
				</tr>		
		</table>
		 <p style="font-family: 黑体, SimHei;line-height:150%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:对预算5万元（含）以上，需经公司主管领导签字，预算10万元（含）以上，需经总经理签字。</p>
	            
		  <p style='margin-left:400px;margin-top:26px;font-family:楷体_gb2312'><em>本页为仪器设备全生命周期管理系统生成 </em></p>
		</div>
	
		<div class="form-actions">
			<input id="btnSubmit" class="btn" type="button"  onclick="dayin()" value="打 印"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>