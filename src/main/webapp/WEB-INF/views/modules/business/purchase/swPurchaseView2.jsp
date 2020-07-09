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
		<div id="dayin">
		<table border='0' bordercolor='#000000'  style='border-collapse:collapse;width: 700px;margin-left: 50px;margin-right:66px;margin-top:68px;'>
			
				<tr>
					<td style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;font-family: 黑体, SimHei;width: 12% "  height="48px" colspan="1">
					
					</td>
					<td style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;font-family: 黑体, SimHei; "  height="48px" colspan="6">${swPurchase.office.name}
					
					</td>
					
					
				</tr>	
				<tr>
					<td style="font-family: 黑体, SimHei; text-align:center;width: 12%"  height="55px" colspan="1"></td>
					<td colspan="3" style="font-family: 黑体, SimHei; text-align:center;width: 38%">
						${swPurchase.name}
					</td><td style="font-family: 黑体, SimHei; text-align:center;width: 15%" ></td>
					<td  colspan="2" style="font-family: 黑体, SimHei; text-align:center;width: 35%">
						${swPurchase.amount}
					</td>
					
				</tr>	
				<tr>
					<td style="font-family: 黑体, SimHei; text-align:center;width: 26%"  height="55px" colspan="2"></td>
					<td style="font-family: 黑体, SimHei; text-align:center;width: 64%" colspan="4" >
						${swPurchase.specifications}
					</td>
					<td style="font-family: 黑体, SimHei; text-align:center;width: 10%" colspan="1" >
						${swPurchase.specifications}
					</td>
					
				</tr>	
				<tr>
				    <td style="font-family: 黑体, SimHei; text-align:center;width: 26%" colspan="2" ></td>
				    <td style="font-family: 黑体, SimHei; text-align:center;width: 13%">
						${swPurchase.made}
					</td>
					<td style="font-family: 黑体, SimHei; text-align:center;width: 12%"  height="55px"></td>
					<td style="font-family: 黑体, SimHei; text-align:center;width: 14%" >
						${swPurchase.unitPrice}
					</td><td style="font-family: 黑体, SimHei; text-align:center;width: 10%" ></td>
					<td style="font-family: 黑体, SimHei; text-align:center;width: 20%">
						${swPurchase.funds}　　
					</td>
					
				</tr>	
				<tr>
					<td style="font-family: 黑体, SimHei; text-align:center;width: 26%"  height="250px" colspan="2"></td><td  colspan="5">
						${swPurchase.reason}
					</td>
					
				</tr>	
				
		</table>
	
		
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn" type="button"  onclick="dayin()" value="打 印"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>