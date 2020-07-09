<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同管理</title>
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
				
				
				
				  top.$.jBox.open("iframe:${ctx}/business/supplier/swSupplier?pageSize=8","选择供应商", 1150, 500, {
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
			    	                    $("#supplierPhone").val(userPhone);
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
		 function tt(id){
				
				window.location.href="${ctx}/business/contract/swContract/view?id=${swContract.id}" ;
			}
			
		 function ok(e){
				var file = $("#appendix").val();
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
		<li><a href="${ctx}/business/order/swOrder/">采购任务列表标列表</a></li>
		<li class="active"><a href="${ctx}/business/contract/swContract/form?id=${swContract.id}">合同<shiro:hasPermission name="business:contract:swContract:edit">${not empty swContract.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="business:contract:swContract:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="swContract" action="${ctx}/business/contract/swContract/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="orderId"/>
		<form:hidden path="state"/>
		<form:hidden path="supplierId"/>
		<sys:message content="${message}"/>	
		<table class="table-form">
				<tr>
					
					<td class="tit" width="15%">合同名称：</td><td width="35%">
						<form:input path="contractName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					<td class="tit" width="15%">合同类型：</td><td width="35%">
						<form:select path="contractType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('contract_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">合同价款：</td><td width="35%">
							<form:input path="contractPrice" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				
					<td class="tit" width="15%">对方单位：</td><td width="35%">
						<form:input path="supplierName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
						<a id='relationButton' href='javascript:' class='btn' style=''>&nbsp;<i class='icon-search'></i>&nbsp;</a>
				<span class="help-inline"><font color="red">*</font> </span></td>
				</tr>
				<tr>
					<td class="tit" width="15%">联系人：</td><td width="35%">
							<form:input path="supplierUser" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				
					<td class="tit" width="15%">联系电话：</td><td width="35%">
						<form:input path="supplierPhone" htmlEscape="false" maxlength="100" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">经办人：</td><td width="35%">
							<form:input path="contractUser" htmlEscape="false" maxlength="100" class="input-xlarge "/>
					</td>
					<td class="tit" width="15%">联系电话：</td><td width="35%">
							<form:input path="contractPhone" htmlEscape="false" maxlength="100" class="input-xlarge "/>
					</td>
				</tr>	
				<tr>
					<td class="tit" width="15%">附件：</td><td width="35%">
							<form:input path="field1" htmlEscape="false" maxlength="1100" class="input-xlarge "/>
					</td>
					<td class="tit" width="15%">页数：</td><td width="35%">
						<form:input path="appendixLength" htmlEscape="false" maxlength="11" class="input-xlarge "/>
					</td>
				</tr>
					<tr>
					<td class="tit" width="15%">上传合同申请扫描件：</td><td width="35%" colspan="3">
							<form:hidden id="appendix" path="appendix" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="appendix" type="files" uploadPath="/business/contract/swContract" selectMultiple="true"/>
					</td>
					
				</tr>	
					
		</table>
		
		
	
		<div class="form-actions">
			<shiro:hasPermission name="business:contract:swContract:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnSubmitok" class="btn  btn-primary"   type="button"   onclick="ok(1)"  value="完成"/>
			<input id="dayin" class="btn" type="button"  onclick="tt(1)" value="打印预览"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>