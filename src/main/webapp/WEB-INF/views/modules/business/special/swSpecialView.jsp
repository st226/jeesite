<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>特殊项目管理(公开)</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/homePage/bootstrap_admin/scripts/jquery.jqprint-0.3.js" type="text/javascript"></script>
	<script src="${ctxStatic}/homePage/bootstrap_admin/scripts/shuiyin.js" type="text/javascript"></script>
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
	<form:form id="inputForm" modelAttribute="swSpecial" action="${ctx}/business/special/swSpecial/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div id="dayin">
		<table border='1' bordercolor='#000000' style='border-collapse:collapse;width: 620px;margin-left: 46px;margin-right:56px;margin-top:20px;'>
			 <caption align="top"  style="font-size: 28px;margin-bottom: 10px;line-height:150%" ><b>北京航天时代激光导航技术有限责任公司</br>采购项目情况说明表(免招标)</b></caption>
				<tr>
				<td style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;" colspan="2" width="25%">航天采购平台编号:</td>

				<td style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;" colspan="1" width="25%">${swSpecial.field2}</td>
				<td style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;text-align:center" colspan="1" width="25%">公司统一编号:</td>
				
					<td style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;"  height="40px" >
							${swSpecial.code}
				
					</td>
					
				</tr>	
				
				<tr height="40px" >
				<td class="tit"  colspan="2" style="font-family: 黑体, SimHei; text-align:center;width: 20%">业务主管部门</td><td style="font-family: 黑体, SimHei; text-align:center;">
						${swSpecial.office.name}
					</td>
					<td style="font-family: 黑体, SimHei; text-align:center;" >申请日期</td><td style="font-family: 黑体, SimHei; text-align:center;" >
					
		<fmt:formatDate value="${swSpecial.applicationDate}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>	
				<tr>
					<td class="tit"  height="40px" colspan="2" style="font-family: 黑体, SimHei; text-align:center;" >项目名称</td>
					<td style="font-family: 黑体, SimHei; text-align:center;">
							${swSpecial.projectName}
		
					</td>
					<td class="tit"  height="40px" style="font-family: 黑体, SimHei; text-align:center;">预算金额</td>
					<td style="font-family: 黑体, SimHei; text-align:center;">
							${swSpecial.budget}

					</td>
					
				</tr>	
				<tr height="40px"  >
					<td class="tit"  colspan="2" style="font-family: 黑体, SimHei; text-align:center;">采购情况类型</td><td  colspan="3" style="font-family: 黑体, SimHei; text-align:center;">
					
							<form:checkboxes path="specialType" items="${fns:getDictList('special_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class="" />
				
					</td>
					
				</tr>	
				<tr>
				<td rowspan="4" style="font-family: 黑体, SimHei; text-align:center;" width="10%" >立项</td>
					<td class="tit"  height="90px" style="font-family: 黑体, SimHei; text-align:center;"  width="15%">项目内容</td><td style="font-family: 黑体, SimHei; text-align:left ;"  colspan="3">
							${swSpecial.context}
					</td>
					
				</tr>	
				<tr>
					<td style="font-family: 黑体, SimHei; text-align:center;" height="80px" >申请原因</td><td  style="font-family: 黑体, SimHei;  text-align:left ;" colspan="3">
							${swSpecial.reason}
					</td>
			   </tr>	
				<tr>
					<td rowspan="2" style="font-family: 黑体, SimHei; text-align:center;" >供应商情况</td>
				
					<td  height="40px" style="font-family: 黑体, SimHei; text-align:center;" >单位名称</td>
					<td  height="40px"  style="font-family: 黑体, SimHei; text-align:center;" >联系人</td>
					<td  height="40px" style="font-family: 黑体, SimHei; text-align:center;" >电话</td>
				</tr>	
				<tr height="40px" >	
					
					<td  style="font-family: 黑体, SimHei; text-align:center;">
								${swSpecial.supplierName}
					</td>
					<td style="font-family: 黑体, SimHei; text-align:center;" >
								${swSpecial.supplierUser}
					</td>
					<td style="font-family: 黑体, SimHei; text-align:center;" >
								${swSpecial.supplierTel}
					</td>
					
					
				</tr>	
				
				<tr>
				   <td height="90px" style='vertical-align:middle; text-align:center;font-family: 黑体, SimHei; '  colspan="2">业务主管部门领导意见</td>
				    <td colspan="3"  style='vertical-align:bottom; text-align:right;font-family: 黑体, SimHei; ' >
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="90px" style='vertical-align:middle; text-align:center;font-family: 黑体, SimHei; '   colspan="2">招标办公室意见</td>
				    <td colspan="3"  style='vertical-align:bottom; text-align:right;font-family: 黑体, SimHei; ' >
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="90px" style='vertical-align:middle; text-align:center;font-family: 黑体, SimHei; '   colspan="2">需求方公司主管领导意见</td>
				    <td colspan="3"  style='vertical-align:bottom; text-align:right;font-family: 黑体, SimHei; ' >
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="90px" style='vertical-align:middle; text-align:center;font-family: 黑体, SimHei; '   colspan="2">公司总经理意见</td>
				    <td colspan="3"  style='vertical-align:bottom; text-align:right;font-family: 黑体, SimHei; ' >
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>	
				
				</table>	
		
	          <p style="font-family: 黑体, SimHei;line-height:150%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:对预算5万元（含）以上，需经公司主管领导签字，预算10万元（含）以上，需经总经理签字。</p>
	            <p style='margin-left:400px;margin-top:30px;font-family:楷体_gb2312'><em>本页为仪器设备全生命周期管理系统生成 </em></p>
		
		
		</div>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn" type="button"  onclick="dayin()" value="打 印"/>
			<input id="btnSubmit" class="btn btn-primary" type="button"  onclick="word()" value="导出word"/>
			<input id="btnCancel" class="btn" type="button" value="关 闭" onclick="window.close();"/>
		</div>
	</form:form>
</body>
</html>