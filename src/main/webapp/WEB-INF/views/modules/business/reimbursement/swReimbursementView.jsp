<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报销单管理</title>
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
		
		 function tt(id){
				
				window.location.href="${ctx}/business/reimbursement/swReimbursement/view?id=${swBidding.id}" ;
			}
			
			function ok(e){
				var file = $("#file").val();
				if(file==null || file==""){
					alert("请先上传审批结果！");
				}else{
					 $("#state").val("1");
						$("#inputForm").submit();
				}
			}
			function dayin(){
				 $("#dayin").jqprint();
			}
		
	</script>
</head>
<body>
	<br/>
	<form:form id="inputForm" modelAttribute="swReimbursement" action="${ctx}/business/reimbursement/swReimbursement/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="orderId"/>
		<form:hidden path="state"/>
		<form:hidden path="supplierId"/>
		<sys:message content="${message}"/>
		 <div id="dayin">
  
          </br>
			<table border='1' bordercolor='#000000' style='border-collapse:collapse;table-layout:fixed;width: 800px'>
			 <caption align="top"  style="font-size: 30px;margin-bottom: 30px" >北京航天时代激光导航技术有限责任公司经费报销单</caption>
			 
			 <tr>
					<td  style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;width: 37%" >结算单位：<u> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;${swReimbursement.supplierName} &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</u></td>

					<td  style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;text-align:center;width: 20%" ><fmt:formatDate value="${swReimbursement.createDate}" pattern="yyyy年MM月dd日"/></td>
                   <td style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;width: 20%" ></td>
					<td    style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;width: 20%" >收支  ${swReimbursement.mark} 号</td>
					<td rowspan="10" style="border-bottom: 1px solid transparent !important;border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;text-align:center;width: 3%" >附单据&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;张</td>
				</tr>
			 
			 
				<tr>
					<td class="tit" width="40%">项目编号：${swReimbursement.projectCode}</td>
					<td colspan="3">
							
				质量成本<form:checkboxes path="isQuality" items="${fns:getDictList('is_yes')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/><u> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;${swReimbursement.quality} &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</u>
					</td>
					
					
				</tr>	
				<tr >
					<td class="tit" width="40%" style="text-align:center;">开支项目</td>
					
					<td class="tit" width="20%" style="text-align:center;">开支金额</td>
					<td class="tit" width="20%" style="text-align:center;">核准金额</td>
					<td class="tit" width="20%" style="text-align:center;">备注</td>
					
				</tr>	
				<tr height="30px">
				
				<td class="tit" width="40%" style="text-align:center;">${swReimbursement.projectName}</td>
				<td class="tit" width="20%" style="text-align:center;">${swReimbursement.amount}</td>
				<td class="tit" width="20%" style="text-align:center;"></td>
				<td class="tit" width="20%" style="text-align:center;"></td>
				
					
				</tr>	
				<tr height="30px">
				
				<td class="tit" ></td>
				<td class="tit" ></td>
				<td class="tit" ></td>
				<td class="tit" ></td>
				
					
				</tr>
				<tr height="30px">
				
				<td class="tit" ></td>
				<td class="tit" ></td>
				<td class="tit" ></td>
				<td class="tit" ></td>
				
					
				</tr>	
				<tr height="30px">
				
				<td class="tit" ></td>
				<td class="tit" ></td>
				<td class="tit" ></td>
				<td class="tit" ></td>
				
					
				</tr>	
				<tr height="30px">
				
				<td class="tit" >合计（大写）</td>
				<td class="tit" ></td>
				<td class="tit" ></td>
				<td class="tit" ></td>
				
					
				</tr>		
				<tr height="30px">
				
				<td colspan="4">偿还&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 月&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 日借据&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 元结算后（退回、补领）&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 元</td>
				
				
					
				</tr>
				<tr height="40px">
				<td  style="border-bottom: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;width: 20%"  colspan="4">公司领导签字&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 部门领导签字&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 报领人&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 审核&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 出纳</td>
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