<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>请款单管理(公开)</title>
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
	<br/>
	<form:form id="inputForm" modelAttribute="swPay" action="${ctx}/business/pay/swPay/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div id="dayin">
		    <table border='0' bordercolor='#000000' style='border-collapse:collapse;table-layout:fixed;width: 800px;margin-top:120px;margin-left: 40px'>
			
			 
			 <tr>
					<td  style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;" colspan="2" ></td>

					<td  style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;text-align:center;" colspan="2" > &nbsp;&nbsp;&nbsp;</td>

					<td    style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;" colspan="2" ></td>

				</tr>
			 
			  <tr>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;width: 16%' height="48px"  width="70%"></td>
					<td style="width: 17%">
					  <span style='font-size:12.0pt;font-family:宋体'>${swPay.field1}</span>
						
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;width: 16%' height="48px" ></td>
					<td style="width: 13%" >
					<span style='font-size:12.0pt;font-family:宋体'>${swPay.field2}</span>
						
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;width:33%' height="48px" colspan="2" >
					<span style='font-size:12.0pt;font-family:宋体'>${swPay.field3}</span></td>
					

				</tr>
				 <tr>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' height="48px" ></td><td >
					 <span style='font-size:12.0pt;font-family:宋体'>${swPay.supplierAccount}</span>
						
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' height="48px" ></td><td >
					<span style='font-size:12.0pt;font-family:宋体'>${swPay.contrateTreat}</span>
						
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' height="48px" ></td><td >
		
						
					</td>

				</tr>
				<tr>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' height="48px" ></td>

				</tr>
				 <tr>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' height="48px" ></td><td colspan="2">
					
						
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' height="48px" ></td><td colspan="2">
					<span style='font-size:12.0pt;font-family:宋体'>${swPay.supplierBankName}</span>
						
					</td>
					

				</tr>
				 <tr>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' height="48px" ></td><td colspan="2" >
		 <span style='font-size:12.0pt;font-family:宋体'>  ${swPay.supplierBank}</span>
						
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' height="48px" ></td><td colspan="2" >
					<span style='font-size:12.0pt;font-family:宋体'>${swPay.supplierBankNumber}</span>
						
					</td>
					

				</tr>
			
		
		</table>
		
		
			
		
		
		</div>
		
		
		
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn" type="button"  onclick="dayin()" value="打 印"/>
			<input id="btnCancel" class="btn" type="button" value="关 闭" onclick="window.close();"/>
		</div>
	</form:form>
</body>
</html>