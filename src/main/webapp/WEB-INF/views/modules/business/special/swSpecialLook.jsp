<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>特殊项目管理</title>
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
				
				
				
				  top.$.jBox.open("iframe:${ctx}/business/supplier/swSupplier/qureyList?pageSize=8","选择供应商", 1150, 500, {
			            buttons:{"确认":"ok", "关闭":true},
			            submit:function(v, h, f){
			                if(v=="ok"){
			              //      var data=h.find("iframe")[0].contentWindow.$("#inputForm").serialize();
			                    var i=0;
			    	            var j=0;
			    	            h.find("iframe")[0].contentWindow.$("input[name*='checkbox_name']").each(function () {
			    	                if ($(this).attr("checked") == 'checked') {
			    	                    var name = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].name']").val();
			    	                    var supplierUser = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].supplierUser']").val();
			    	                    var userPhone = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].userPhone']").val();
			    	                    var supplierId =  h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].id']").val();
			    	                    $("#supplierId").val(supplierId);
			    	                    $("#supplierName").val(name);
			    	                    $("#supplierUser").val(supplierUser);
			    	                    $("#supplierTel").val(userPhone);
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
			
			if('${amountYs}'>=500000){
	
				document.getElementById("qingshi").style.display="block";

			}
			
		});
		
function tt(id){
	 var id = $("#id").val();
			if(id==null || id==''){
				alert("请先保存，再打印！");
				return ;
			}
			window.location.href="${ctx}/business/special/swSpecial/view?id=${swSpecial.id}" ;
		}
function plus(){
	
	document.getElementById("plusDiv").style.display = document.getElementById("plusDiv").style.display=="none"?"":"none";
	document.getElementById("minus_icon").className= document.getElementById("minus_icon").className=="icon-plus"?"icon-minus": "icon-plus";  
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/business/special/swSpecial/">特殊项目列表</a></li>
		<li class="active"><a href="${ctx}/business/special/swSpecial/form?id=${swSpecial.id}">特殊项目<shiro:hasPermission name="business:special:swSpecial:edit">${not empty swSpecial.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="business:special:swSpecial:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="swSpecial" action="${ctx}/business/special/swSpecial/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="supplierId"/>
		<form:hidden path="orderId"/>
		<form:hidden path="state"/>
		<sys:message content="${message}"/>	
		<table class="table-form">
				<tr>
					<td class="tit" width="15%">航天采购平台编号：</td><td width="35%">
							<form:input path="field2" htmlEscape="false" maxlength="255" class="input-xlarge " />
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					<td class="tit" width="15%">公司统一编号：</td><td width="35%">
							<form:input path="code" htmlEscape="false" maxlength="255" class="input-xlarge " />
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>	
				<tr>
				<td class="tit" width="15%">业务主管部门：</td><td width="35%" colspan="3">
						<sys:treeselect id="office" name="office.id" value="${swSpecial.office.id}" labelName="officeName" labelValue="${swSpecial.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>	
				<tr>
					<td class="tit" width="15%">项目名称：</td><td width="35%">
							<form:input path="projectName" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">申请日期：</td><td width="35%">
						<input name="applicationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${swSpecial.applicationDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>	
				<tr>
					<td class="tit" width="15%">预算金额：</td><td width="35%">
							<form:input path="budget" htmlEscape="false" maxlength="9" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">特殊情况类型：</td><td width="35%">
						<form:select path="specialType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('special_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>	
				<tr>
					<td class="tit" width="15%">项目内容：</td><td width="35%"  colspan="3">
							<form:textarea path="context" htmlEscape="false" maxlength="255" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>	
				<tr>
					<td class="tit" width="15%">申请原因：</td><td width="35%"  colspan="3">
							<form:textarea path="reason" htmlEscape="false" maxlength="255"  class="input-xxlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				
				<tr>
					<td class="tit" width="15%">单位名称：</td><td width="35%" colspan="3">
								<form:input path="supplierName" htmlEscape="false" maxlength="255" class="input-xxlarge required" readonly="true"/>
								<a id='relationButton' href='javascript:' class='btn' style=''>&nbsp;<i class='icon-search'></i>&nbsp;</a>
				<span class="help-inline"><font color="red">*</font>请点击放大镜选择供应商列表中的供应商，如没有请先维护供应商信息。 </span>
					</td>
					
					
				</tr>	
				<tr>
				<td class="tit" width="15%">联系人：</td><td width="35%">
						<form:input path="supplierUser" htmlEscape="false" maxlength="255" class="input-xlarge required" readonly="true"/>
				<span class="help-inline"><font color="red">*</font> </span></td>
					<td class="tit" width="15%">联系人电话：</td><td width="35%">
								<form:input path="supplierTel" htmlEscape="false" maxlength="255" class="input-xlarge required" readonly="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>	
				
				<tr>
					<td class="tit" width="15%">上传签批完特殊项目申请表：</td><td width="35%" colspan="3">
							<form:hidden id="file" path="file" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="file" type="files" uploadPath="/business/special/swSpecial" selectMultiple="true"  readonly="true"/>
				
					</td>
					
				</tr>
				</table>	
		
	
		
		<div id="qingshi" style="display: none">
		<legend>按照公司“三重一大”规定要求，设备价格在50万元以上的应通过总经理办公会集体决定决议 <a onclick="plus()"><i id="minus_icon" class="icon-minus"></i></a></legend>
		<div id="plusDiv">
		<table class="table-form">
				
				<tr>
					<td class="tit" width="10%">上传议案附件：</td><td width="35%" colspan="3">
							<form:hidden id="resolutionFile" path="resolutionFile" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="resolutionFile" type="files" uploadPath="/business/survey/swSurvey" selectMultiple="true"  readonly="true"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/jeesite/userfiles/1/files/quality/qmresource/qmResource/2020/05/%E4%BE%9B%E5%BA%94%E5%95%86%E5%AF%BC%E5%85%A5%E6%A8%A1%E6%9D%BF%20(3).xlsx">下载模板</a>
					</td>
					
					
				</tr>
				<tr>
					<td class="tit" width="10%">备注：</td><td colspan="3">
						<form:textarea path="resolutionOther" htmlEscape="false" maxlength="2550" rows="5"   class="input-xxlarge "/>
		
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="10%">总经理办公会决议：</td><td colspan="1">
				
		                <form:radiobuttons path="resolution"  items="${fns:getDictList('resolution')}" itemLabel="label" itemValue="value" htmlEscape="false"  />
					</td>
					<td class="tit" width="10%">决议时间：</td><td colspan="1">
			
		                <input name="resolutionDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${swSpecial.resolutionDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</td>
					
				</tr>
		       <tr>
					<td class="tit" width="10%">决议附件：</td><td width="35%" colspan="3">
							<form:hidden id="resolutionResult" path="resolutionResult" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="resolutionResult" type="files" uploadPath="/business/survey/swSurvey" selectMultiple="true" readonly="true"/>
				
					</td>
					
				</tr>
		
		
		
		</table>
		</div>
		</div>
		
		
		<div class="form-actions">
			
			<input id="dayin" class="btn" type="button"  onclick="tt(1)" value="打印预览"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>