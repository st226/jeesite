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
		
		function word(){
			
			 
			 var text = document.getElementById("dayin").innerHTML;
			 $.ajax({
	                type:'post',
	                url:'${ctx}/business/special/swSpecial/exportSwSpecial',
	                data:{'content':text},
	                cache:false,
	                dataType:'json',
          success:function(data){
          	window.location.href="/jeesite/userfiles/download/"+data ;
          }
      });
	
		}
	</script>
</head>
<body>
	<br/>
	<form:form id="inputForm" modelAttribute="swPay" action="${ctx}/business/pay/swPay/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div id="dayin">
		<table border='1' bordercolor='#000000' style='border-collapse:collapse;table-layout:fixed;width: 620px;margin-left: 50px;margin-right:36px;margin-top:36px;'>
			 <caption align="top"  style="font-size: 30px;margin-bottom: 30px" >合同（协议）请款单</caption>
			   <tr>
					<td style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;" height="40px" colspan="4">付款单位：
					北京航天时代激光导航技术有限责任公司
						</td>
				<td  style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;" height="40px" ></td><td  style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;" >
				<fmt:formatDate value="${swPay.createDate}" pattern="yyyy-MM-dd"/>
						
					</td>
					
				</tr>
				<tr>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'height="40px" >合同名称：</td><td colspan="3" >
					${swPay.contrateName}
						</td>
				<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' height="40px" >付款方式：</td><td >
				${fns:getDictLabel(swPay.payType, 'pay_type', '')}
						
					</td>
					
				</tr>
				<tr>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' height="40px" >对方单位：</td><td colspan="3">
					${swPay.supplierName}
						
					</td><td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' height="40px" >联系电话：</td><td >
					${swPay.supplierTel}
							
					</td>
					
				</tr>
				<tr>
					
				
				<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >合同总额：</td><td >
				${swPay.contratePrice}
					</td>
				
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' height="40px" >已付款额：</td><td >
					${swPay.contratePaid}
						
					</td>
					
				
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >本次付款额：</td><td >
					${swPay.contrateTreat}
						
					</td>
				</tr>
				<tr>
				<td rowspan="2">受款单位账户信息</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' height="40px" >开户行账户名：</td><td colspan="2" >
					${swPay.supplierBankName}
				
					</td>
					
				<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >开户行：</td><td >
				${swPay.supplierBank}
						
					</td>
				
				</tr>
				<tr>
					
					
				
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' height="40px" >账户：</td><td colspan="4">
					${swPay.supplierBankNumber}
						
					</td>
				</tr>
				
				<tr>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'  height="80px">付款理由：</td><td  colspan="5">
					${swPay.supplierAccount}
							
					</td>
					
			
				</tr>
				<tr>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'  height="80px">付款合同编号：</td><td  colspan="5">
					${swPay.contractCode}
							
					</td>
					
			
				</tr>
				<tr>
				   <td height="100px" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >主管部门意见：</td>
				    <td colspan="5"  style='vertical-align:bottom; text-align:right;WORD-WRAP:break-word;' >
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="100px" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >主管副总经理意见：</td>
				    <td colspan="5"  style='vertical-align:bottom; text-align:right;WORD-WRAP:break-word;' >
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="100px" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >财务总监意见：</td>
				    <td colspan="5"  style='vertical-align:bottom; text-align:right;WORD-WRAP:break-word;' >
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="100px" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' >总经理意见：</td>
				    <td colspan="5"  style='vertical-align:bottom; text-align:right;WORD-WRAP:break-word;' >
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				
				<tr >
				     <td  style="border-bottom: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;"  height="40px">经办人：</td>
				     <td style="border-bottom: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;">
				     ${swPay.field1}
							
					</td>
					<td style="border-bottom: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;"></td>
					<td style="border-bottom: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;"></td>
					<td style="border-bottom: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;">联系电话：</td>
					<td style="border-bottom: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;">
					${swPay.phone}
							
					</td>
					
			
				</tr>
				
			
					
					
		</table>
		
		
			
		
		
		</div>
		
		
		
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn" type="button"  onclick="dayin()" value="打 印"/>
			<input id="btnSubmit" class="btn btn-primary" type="button"  onclick="word()" value="导出word"/>
			<input id="btnCancel" class="btn" type="button" value="关 闭" onclick="window.close();"/>
		</div>
	</form:form>
</body>
</html>