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
			
			if('${qmSupplierUpdate.type}'=='D'){
				 document.getElementById("updateInfo").style.display="none" ;
				 document.getElementById("field3Tr").style.display="" ;
			}else{
				 document.getElementById("updateInfo").style.display="" ;
				 document.getElementById("field3Tr").style.display="none" ;
			}
			
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
			
			
		});
		
		 function ok(e){
				
				$("#state").val("1");
				$("#inputForm").submit();
				
			    
			
			}
		 
		 function updateType(e){
			 if(e.value=='D'){
				 document.getElementById("updateInfo").style.display="none" ;
				 document.getElementById("field3Tr").style.display="" ;
			 }else{
				 document.getElementById("updateInfo").style.display="" ; 
				 document.getElementById("field3Tr").style.display="none" ;
			 }
		 }
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/quality/supplierupdate/qmSupplierUpdate/">供应商信息变更列表</a></li>
		<li class="active"><a href="${ctx}/quality/supplierupdate/qmSupplierUpdate/form?id=${qmSupplierUpdate.id}">供应商信息变更<shiro:hasPermission name="quality:supplierupdate:qmSupplierUpdate:edit">${not empty qmSupplierUpdate.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="quality:supplierupdate:qmSupplierUpdate:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="qmSupplierUpdate" action="${ctx}/quality/supplierupdate/qmSupplierUpdate/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="processinstid"/>
		<form:hidden path="state"/>
		<form:hidden path="supplierId"/>
		<sys:message content="${message}"/>
		
		<table class="table-form">
				
				<tr>
					<td class="tit" width="15%">变更类型：</td><td width="35%" colspan="3">
							
							<form:radiobuttons path="type"  items="${fns:getDictList('supplier_update_type')}" itemLabel="label" itemValue="value" htmlEscape="false" onchange="updateType(this)"  class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">供应商名称：</td><td width="35%" colspan="3">
						<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
						<a id='relationButton' href='javascript:' class='btn' style=''>&nbsp;<i class='icon-search'></i>&nbsp;</a>
				<span class="help-inline"><font color="red">*</font>请点击放大镜选择供应商列表中的供应商，如没有请先维护供应商信息。 </span>

					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">货物名称：</td><td width="35%">
						<form:input path="productName" htmlEscape="false" maxlength="255" class="input-xlarge "/>

					</td>
					<td class="tit" width="15%">外协军种：</td><td width="35%">
						<form:input path="helpArmy" htmlEscape="false" maxlength="255" class="input-xlarge "/>

					</td>
					
				</tr>
				<tr id="updateInfo">
					<td class="tit" width="15%">变更前信息：</td><td width="35%">
						<form:textarea path="original" htmlEscape="false" rows="6" maxlength="255" class="input-xlarge "/>

					</td>
					<td class="tit" width="15%">变更后信息：</td><td width="35%">
						<form:textarea path="now" htmlEscape="false" rows="6"  maxlength="2000" class="input-xlarge "/>
					</td>
				</tr>
				<tr id="field3Tr" style="display: none">
					<td class="tit">选择型号负责人</td>
					<td colspan="6">
					
						<sys:treeselect id="field3" name="field3" value="${qmSupplierUpdate.field3}" labelName="field4" labelValue="${qmSupplierUpdate.field4}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
					</td>
				
				</tr>
				<tr>
					<td class="tit" width="15%">申请理由：</td><td width="35%" colspan="3" >
						<form:textarea path="reason" htmlEscape="false" maxlength="255" rows="4"  class="input-xxlarge "/>

					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">申请人：</td><td width="35%" colspan="3">
						<form:input path="applicant" htmlEscape="false" maxlength="255" class="input-xlarge "/>

					</td>
				</tr>
		</table>		
		
		
		
		<div class="form-actions">
			<shiro:hasPermission name="quality:supplierupdate:qmSupplierUpdate:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnSubmitok" class="btn  btn-primary"   type="button"   onclick="ok(1)"  value="提交"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>