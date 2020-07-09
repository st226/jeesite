<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仪器申购单管理</title>
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
		<li><a href="${ctx}/business/purchase/swPurchase/">仪器申购单列表</a></li>
		<li class="active"><a href="${ctx}/business/purchase/swPurchase/form?id=${swPurchase.id}">仪器申购单<shiro:hasPermission name="business:purchase:swPurchase:edit">${not empty swPurchase.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="business:purchase:swPurchase:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="swPurchase" action="${ctx}/business/purchase/swPurchase/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table border='1' bordercolor='#000000'  style='border-collapse:collapse;width: 700px;margin-left: 20px;margin-right:56px;margin-top:26px;'>
			<caption align="top"  style="font-size: 28px;margin-bottom: 10px;line-height:150%" ><b>工具、工装、设备及仪器申购单</b></caption>
				<tr>
					<td style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;font-family: 黑体, SimHei; "  height="40px" colspan="4">申请单位:${swPurchase.office.name}
					
					</td>
					
					<td style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;font-family: 黑体, SimHei;text-align:right;"  colspan="3">填报日期:
				
					<fmt:formatDate value="${swPurchase.createDate}" pattern="yyyy年MM月dd日"/>
						
					</td>
					
				</tr>	
				<tr>
					<td style="font-family: 黑体, SimHei; text-align:center;width: 20%"  height="40px" colspan="1">名称</td>
					<td colspan="3" style="font-family: 黑体, SimHei; text-align:center;width: 20%">
						${swPurchase.name}
					</td><td style="font-family: 黑体, SimHei; text-align:center;width: 20%" >数量</td>
					<td  colspan="2" style="font-family: 黑体, SimHei; text-align:center;width: 20%">
						${swPurchase.amount}
					</td>
					
				</tr>	
				<tr>
					<td style="font-family: 黑体, SimHei; text-align:center;width: 20%"  height="40px" colspan="2">型号规格、技术条件</td><td style="font-family: 黑体, SimHei; text-align:center;width: 20%" colspan="5" >
						${swPurchase.specifications}
					</td>
					
				</tr>	
				<tr>
				    <td style="font-family: 黑体, SimHei; text-align:center;width: 20%" colspan="2" >生产单位（国别）</td><td style="font-family: 黑体, SimHei; text-align:center;width: 20%">
						${swPurchase.made}
					</td>
					<td style="font-family: 黑体, SimHei; text-align:center;width: 20%"  height="40px">单价</td><td style="font-family: 黑体, SimHei; text-align:center;width: 20%" >
						${swPurchase.unitPrice}
					</td><td style="font-family: 黑体, SimHei; text-align:center;width: 20%" >经费来源</td><td style="font-family: 黑体, SimHei; text-align:center;width: 20%">
						${swPurchase.funds}
					</td>
					
				</tr>	
				<tr>
					<td style="font-family: 黑体, SimHei; text-align:center;width: 20%"  height="150px" colspan="2">申请理由及用途</td><td  colspan="5">
						${swPurchase.reason}
					</td>
					
				</tr>	
				<tr>
				   <td height="90px" style="font-family: 黑体, SimHei; text-align:center;width: 20%"  colspan="2" >申请人签字：</td>
				    <td colspan="5" style="font-family: 黑体, SimHei; text-align:right;width: 20%" >
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="90px" style="font-family: 黑体, SimHei; text-align:center;width: 20%"  colspan="2">申请单位领导签字：</td>
				    <td colspan="5"  style="font-family: 黑体, SimHei; text-align:right;width: 20%" >
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="90px" style="font-family: 黑体, SimHei; text-align:center;width: 20%"  colspan="2" >设备主管部门审核意见：</td>
				    <td colspan="5"  style="font-family: 黑体, SimHei; text-align:right;width: 20%" >
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="90px" style="font-family: 黑体, SimHei; text-align:center;width: 20%"  colspan="2" >财务审核意见：</td>
				    <td colspan="5"  style="font-family: 黑体, SimHei; text-align:right;width: 20%" >
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="100px" style="font-family: 黑体, SimHei; text-align:center;width: 20%" colspan="2" >主管副总经理审批意见：</td>
				    <td colspan="5"  style="font-family: 黑体, SimHei; text-align:right;width: 20%" >
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="100px" style="font-family: 黑体, SimHei; text-align:center;width: 20%" colspan="2" >总经理审批意见：</td>
				    <td colspan="5"  style="font-family: 黑体, SimHei; text-align:right;width: 20%" >
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
		</table>
		<p style="font-family: 黑体, SimHei;line-height:150%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：1、设备包括自制的专业设备</p>
		 <p style="font-family: 黑体, SimHei;line-height:150%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2、申请部门将此表填写完整后交设备主管部门办理手续（含报销）手续</p>
		</div>
		
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn" type="button"  onclick="dayin()" value="打 印"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>