<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商信息变更管理</title>
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
			
			
			
			$("#relationButton").click(function(){
				
				
				
				  top.$.jBox.open("iframe:${ctx}/quality/qmsupplier/qmSupplier/query?pageSize=8","选择要变更的供应商", 1150, 500, {
			            buttons:{"确认":"ok", "关闭":true},
			            submit:function(v, h, f){
			                if(v=="ok"){
			              //      var data=h.find("iframe")[0].contentWindow.$("#inputForm").serialize();
			                    var i=0;
			    	            var j=0;
			    	            h.find("iframe")[0].contentWindow.$("input[name*='checkbox_name']").each(function () {
			    	                if ($(this).attr("checked") == 'checked') {
			    	                    var name = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].name']").val();
			    	                    var product = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].product']").val();
			    	                    var assist = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].assist']").val();
			    	                    var supplierId =  h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].id']").val();
			    	                    $("#supplierId").val(supplierId);
			    	                    $("#name").val(name);
			    	                    $("#productName").val(product);
			    	                    $("#helpArmy").val(assist);
			    	                    
			    	                    j++;
			    	                }
			    	                i++;
			    	            });
			    	     
			    	     //       window.parent.page();                 //调用父窗体方法，当关闭子窗体刷新父窗体
                            window.parent.window.jBox.close();    //关闭子窗体
			                    
			                }
			            },
			            loaded:function(h){
			                $(".jbox-content", top.document).css("overflow-y","hidden");
			            }
			        });
				
				
			});
			
			if('${qmSupplierUpdate.type}'=='D'){
				 document.getElementById("updateInfo").style.display="none" ;
			}else{
				 document.getElementById("updateInfo").style.display="" ;
			}
			
			
		});
		
		 function ok(e){
				
				$("#state").val("1");
				$("#inputForm").submit();
				
			    
			
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
	    <li class="active"><a >供应商信息变更</a></li>
		<li><a onclick="test(${qmSupplierUpdate.processinstid})">流程跟踪</a></li>
	
	</ul><br/>
	<center><legend >${qmSupplierUpdate.workItem.workItemName}</legend></center>
	<form:form id="inputForm" modelAttribute="qmSupplierUpdate" action="${ctx}/quality/supplierupdate/qmSupplierUpdate/submit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="processinstid"/>
		<form:hidden path="state"/>
		<form:hidden path="workItem.processInstID"/>
		<form:hidden path="workItem.workItemID"/>
		<form:hidden path="workItem.workItemName"/>
		<form:hidden path="workItem.processChName"/>
		<form:hidden path="workItem.activityDefID"/>
		<form:hidden id="flag" path="workItem.workItemType"/>
		<form:hidden path="supplierId"/>
		<sys:message content="${message}"/>
		
		<table class="table-form">
				
				<tr>
					<td class="tit" width="15%">变更类型：</td><td width="35%" colspan="3">
							
							<form:radiobuttons path="type"  items="${fns:getDictList('supplier_update_type')}" itemLabel="label" itemValue="value" htmlEscape="false" onchange="orderType(this)"  class="required"/>
				
					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">供应商名称：</td><td width="35%" colspan="3">
					${qmSupplierUpdate.name}
						

					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">货物名称：</td><td width="35%">
					${qmSupplierUpdate.productName}
						

					</td>
					<td class="tit" width="15%">外协军种：</td><td width="35%">
					${qmSupplierUpdate.helpArmy}
						

					</td>
					
				</tr>
				<tr id="updateInfo">
					<td class="tit" width="15%">变更前信息：</td><td width="35%">
					${qmSupplierUpdate.original}
						

					</td>
					<td class="tit" width="15%">变更后信息：</td><td width="35%">
					${qmSupplierUpdate.now}
						
					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">申请理由：</td><td width="35%" colspan="3" >
					${qmSupplierUpdate.reason}
						

					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">申请人：</td><td width="35%" colspan="3">
					${qmSupplierUpdate.applicant}
						

					</td>
				</tr>
		</table>		
		
		<legend >审批详情</legend>
					<table class="table-form">
						<c:forEach items="${qmSupplierUpdate.examineList}" var="testData">
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
				
				
				</table>
		
		<div class="form-actions">
			
			<input id="btnSubmitok" class="btn  btn-primary"   type="button"   onclick="ok(1)"  value="提交"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>