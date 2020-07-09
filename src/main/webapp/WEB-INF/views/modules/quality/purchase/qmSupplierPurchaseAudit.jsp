<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合格供方目录外采购申请表管理</title>
	<meta name="decorator" content="default"/>
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
			if('${qmSupplierPurchase.workItem.activityDefID}'=='manualActivity2'){
				document.getElementById("field3Tr").style.display="";
			}
		});
		
		
         function ok(e){
			
			$("#state").val("1");
			$("#inputForm").submit();
			
		    
		
		}
         
         function test(pid){
 			top.$.jBox.open("iframe:http://10.193.200.16:8088/default/archives/archives/imageFlow.jsp?id=${qmSupplierPurchase.processinstid}", 
 					"流程跟踪",810,$(top.document).height()-110,{
 						buttons:{"确定":true}, loaded:function(h){
 							$(".jbox-content", top.document).css("overflow-y","hidden");
 						}
 					});
 		
 			
 		}
         
         function onchangeA(e){
        	 if(e.value=='1'){
        		 $("#field2").val("同意"); 
        	 }else{
        		 $("#field2").val("不同意");
        	 }
         }
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a >合格供方目录外采购审批</a></li>
		<li><a onclick="test(${qmSupplierPurchase.processinstid})">流程跟踪</a></li>
	</ul><br/>
	<center><legend >${qmSupplierPurchase.workItem.workItemName}</legend></center>
	<form:form id="inputForm" modelAttribute="qmSupplierPurchase" action="${ctx}/quality/purchase/qmSupplierPurchase/submit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="processinstid"/>
		<form:hidden path="state"/>
		<form:hidden path="workItem.processInstID"/>
		<form:hidden path="workItem.workItemID"/>
		<form:hidden path="workItem.workItemName"/>
		<form:hidden path="workItem.processChName"/>
		<form:hidden path="workItem.activityDefID"/>
		<form:hidden id="flag" path="workItem.workItemType"/>
		<sys:message content="${message}"/>	
		<table class="table-form">
				<tr>
					<td class="tit" width="15%">产品名称：</td><td width="35%" colspan="3">
						
						${qmSupplierPurchase.productName}
					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">产品规格/技术标准/图纸/任务书：</td><td width="35%" colspan="3">
						
						${qmSupplierPurchase.productType}
					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">质量等级：</td><td width="35%" colspan="3">
						
						${qmSupplierPurchase.productClass}
					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">生产厂家：</td><td width="35%" colspan="3">
						
						${qmSupplierPurchase.enterpriseName}
					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">企业性质：</td><td width="35%" colspan="3">
						${fns:getDictLabel(qmSupplierPurchase.enterpriseNature, 'supplier_nature', '')}
						
					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">工程型号：</td><td width="35%" >
					${qmSupplierPurchase.projectModel}
						
					</td>
					<td class="tit" width="15%">单机名称：</td><td width="35%" >
						
						${qmSupplierPurchase.standName}
					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">单机用量：</td><td width="35%" >
					${qmSupplierPurchase.standDosage}
						
					</td>
					<td class="tit" width="15%">单技数量：</td><td width="35%" >
					${qmSupplierPurchase.standAmount}
					
					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">申请理由、风险识别控制：</td><td width="35%" colspan="3">
					${qmSupplierPurchase.reason}
					
					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">廉洁书：</td><td width="35%" colspan="3">
						<span class="help-inline"><font color="red">本人承诺严格遵守国家法律法规和公司相关制度规定，与供应商无利益关系</font></span>
					</td>
					</tr>
				<tr>
					<td class="tit" width="15%">本人已阅读遵守：</td><td width="35%" colspan="3">
						<form:radiobuttons path="statement" items="${fns:getDictList('is_leaf')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">申请人：</td><td width="35%" colspan="3">
					${qmSupplierPurchase.applicant}
						
					</td>
				</tr>
			</table>	
		
		
		 <legend >审批详情</legend>
					<table class="table-form">
						<c:forEach items="${qmSupplierPurchase.examineList}" var="testData">
			<tr>
					<td  width="10%" class="tit" rowspan="2">${testData.examineExamineisagree}</td>
					
					<td  colspan="6">
						${testData.examineExplain}
					</td>
				</tr>
				<tr>
					
					<td colspan="2"  width="20%">
						${testData.examinePerson}
					</td>
					
					<td colspan="2"  width="20%">
						<fmt:formatDate value="${testData.examineDate}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
		</c:forEach>
					
					
				
				
			
				<tr height="40px">
					<td class="tit">是否同意</td>
					<td colspan="6">
						<form:radiobuttons  path="field1" items="${fns:getDictList('is_leaf')}" itemLabel="label" itemValue="value" onchange="onchangeA(this)" htmlEscape="false" class=""/>
					</td>
				</tr>
				
				
				<tr>
					<td class="tit">您的意见</td>
					<td colspan="6">
						<form:textarea path="field2" class="required" rows="5" maxlength="20" cssStyle="width:500px"/><span class="help-inline"><font color="red">*</font> </span>
					</td>
				</tr>
				<tr id="field3Tr" style="display: none">
					<td class="tit">选择型号负责人</td>
					<td colspan="6">
					
						<sys:treeselect id="field3" name="field3" value="${qmSupplierPurchase.field3}" labelName="field4" labelValue="${qmSupplierPurchase.field4}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
					</td>
				
				</tr>
				
				</table>

		<div class="form-actions">
			
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="提 交" onclick="$('#flag').val('Y')"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		
	</form:form>
</body>
</html>