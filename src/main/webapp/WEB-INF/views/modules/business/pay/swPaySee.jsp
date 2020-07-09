<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>请款单管理</title>
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
			    	                    $("#supplierTel").val(userPhone);
			    	                    getSupplier(supplierId);
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
		
		function getSupplier(supplierId){
			$.ajax({
                type:'post',
                url:'${ctx}/business/supplier/swSupplier/getSupplier',
                data:{'id':supplierId},
                cache:false,
                dataType:'json',
                success:function(data){
                      $("#supplierBankName").val(data.name);
                      $("#supplierBankNumber").val(data.bankNumber);
                      $("#supplierBank").val(data.bankName);
                }
            });
			
		}
		function tt(id){
			
			window.location.href="${ctx}/business/pay/swPay/view?id=${swPay.id}" ;
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
	 
	 function checkOk(e1,e2){
	     var ids = "" ;
	     var id = "";
		 var i=0;
         var j=0;
         var contrateTreat = 0;
         var contrateTreatSum = 0 ; 
         $("input[name*='checkbox_name']").each(function () {
             if ($(this).attr("checked") == 'checked') {
                id =  $("input[name='columnList["+i+"].id']").val();
                contrateTreat =  parseFloat($("input[name='columnList["+i+"].contrateTreat']").val());
                contrateTreatSum = contrateTreatSum+ contrateTreat;
                 if(ids==""){
                	 ids = id ;
                 }else{
                	 ids =  ids +"," +id ;
                 }
                 j++;
             }
             i++;
         });
         $("#field5").val(ids);
         $("#contrateTreat").val(contrateTreatSum);
		 
	 }
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/business/order/swOrder/">采购任务列表</a></li>
		<li class="active"><a href="${ctx}/business/pay/swPay/form?id=${swPay.id}">请款单<shiro:hasPermission name="business:pay:swPay:edit">${not empty swPay.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="business:pay:swPay:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="swPay" action="${ctx}/business/pay/swPay/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="field5"/>
		<form:hidden path="state"/>
		<form:hidden path="orderId"/>
		<form:hidden path="payId"/>		
		<form:hidden path="contractId"/>
		<form:hidden path="supplierId"/>

		<sys:message content="${message}"/>	
		
		<table class="table-form">
				<tr>
				 <td class="tit" width="15%">合同编号：</td><td width="35%">
						<form:input path="contractCode" htmlEscape="false" maxlength="100" class="input-xlarge"/>
						
			</td>
					<td class="tit" width="15%">合同/申购单名称：</td><td width="35%">
						<form:input path="contrateName" htmlEscape="false" maxlength="100" class="input-xlarge"/>
				
			</td>
				
					
				</tr>
				<tr>
					<td class="tit" width="15%">对方单位：</td><td width="35%">
						<form:input path="supplierName" htmlEscape="false" maxlength="255" class="input-xlarge required "/>
						<a id='relationButton' href='javascript:' class='btn' style=''>&nbsp;<i class='icon-search'></i>&nbsp;</a>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">联系电话：</td><td width="35%">
							<form:input path="supplierTel" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>
					
				<td class="tit" width="15%">付款方式：</td><td width="35%">
						<form:radiobuttons path="payType"  items="${fns:getDictList('pay_type')}" itemLabel="label" itemValue="value" htmlEscape="false"  class="required"/>
			
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
				<td class="tit" width="15%">合同总额：</td><td width="35%">
						<form:input path="contratePrice" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span></td>
				</tr>
				<tr>
					<td class="tit" width="15%">已付款额：</td><td width="35%">
						<form:input path="contratePaid" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				
					<td class="tit" width="15%">本次付款额：</td><td width="35%">
						<form:input path="contrateTreat" htmlEscape="false" class="input-xlarge required"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</td>
				</tr>
				<tr>
					<td class="tit" width="15%">开户行账户名：</td><td width="35%">
					<form:input path="supplierBankName" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				<td class="tit" width="15%">开户行：</td><td width="35%">
						<form:input path="supplierBank" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
				
				</tr>
				<tr>
					
					
				
					<td class="tit" width="15%">账户：</td><td colspan="3">
						<form:input path="supplierBankNumber" htmlEscape="false" maxlength="255" class="input-xxlarge required"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</td>
				</tr>
				
				<tr>
					<td class="tit" width="15%">付款原因：</td><td  colspan="3">
							<form:textarea path="supplierAccount" htmlEscape="false" maxlength="255" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
			
				</tr>
				<tr>
				     <td class="tit" width="15%">经办人：</td><td width="35%">
							<form:input path="field1" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				     <span class="help-inline"><font color="red">*</font> </span>
					</td>
					<td class="tit" width="15%">联系电话：</td><td width="35%">
							<form:input path="phone" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
			
				</tr>
				
			
					<tr>
					<td class="tit" width="15%">附件：</td><td width="35%" colspan="3">
							<form:hidden id="appendix" path="appendix" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="appendix" type="files" uploadPath="/business/contract/swContract" selectMultiple="true" readonly="true"/>
					</td>
					
				</tr>	
					
		</table>
		
		
		<div class="form-actions">
	
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		
			<legend >已付款详情</legend>
					<table  class="table table-striped table-bordered " >
					<tr>
				<th >序号</th>
				<th >付款方式</th>
				<th >本次付款</th>
			    <th >付款原因</th>
			    <th >经办人</th>
				<th >经办时间</th>
				<th >联系电话</th>
				
			</tr>
						<c:forEach items="${payList}" var="pay"  varStatus="vs">
			        <tr>
			        <td  >${vs.index+1}</td>
					<td  >${fns:getDictLabel(pay.payType, 'pay_type', '')}</td>
					
					<td  colspan="1">
						${pay.contrateTreat}
					</td>
					<td colspan="1"  width="20%">
						${pay.supplierAccount}
					</td>
					<td colspan="1"  width="20%">
						${pay.field1}
					</td>
					<td colspan="1"  width="20%">
						<fmt:formatDate value="${pay.createDate}" pattern="yyyy-MM-dd"/>
					</td>
					<td colspan="1"  width="20%">
						${pay.phone}
					</td>
					
					
				</tr>
		</c:forEach>
					
					
				
				
	</table>
		
		
		
		
		
		
		
		
	</form:form>
</body>
</html>