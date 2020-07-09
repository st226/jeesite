<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同录入管理</title>
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
				
				
				
				  top.$.jBox.open("iframe:${ctx}/business/contract/swContract?pageSize=8","选择合同信息", 1150, 500, {
			            buttons:{"确认":"ok", "关闭":true},
			            submit:function(v, h, f){
			                if(v=="ok"){
			              //      var data=h.find("iframe")[0].contentWindow.$("#inputForm").serialize();
			                    var i=0;
			    	            var j=0;
			    	            h.find("iframe")[0].contentWindow.$("input[name*='checkbox_name']").each(function () {
			    	                if ($(this).attr("checked") == 'checked') {
			    	                    var supplierName = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].supplierName']").val();
			    	                    var supplierUser = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].supplierUser']").val();
			    	                    var supplierPhone = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].supplierPhone']").val();
			    	                    var supplierId =  h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].supplierId']").val();
			    	                    var contractId =  h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].contractId']").val();
			    	                    var contractName =  h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].contractName']").val();
			    	                    var contractCode =  h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].contractCode']").val();
			    	                    var contractPrice =  h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].contractPrice']").val();
			    	                    
			    	                    
			    	                    $("#agreementNo").val(contractCode);
			    	                    $("#agreementSecond").val(supplierName);
			    	                    $("#amount").val(contractPrice);
			    	                   
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
		function addRow(list, idx, tpl, row){
			if(row==null){
				row = {"payIndex":(swAgreementPayRowIdx+1)} ;
			}
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
			resultTR();
		}
		
		function getSupplier(supplierId){
			$.ajax({
                type:'post',
                url:'${ctx}/business/supplier/swSupplier/getSupplier',
                data:{'id':supplierId},
                cache:false,
                dataType:'json',
                success:function(data){
                	$("#secondName").val(data.name);
                    $("#secondPlace").val(data.local);
                    $("#secondNameLiaison").val(data.supplierUser);
                    $("#secondNamePhone").val(data.userPhone);
                    $("#secondBank").val(data.bankName);
                    $("#secondBankNo").val(data.bankNumber);
                    $("#secondDuty").val(data.dutyno);
                    $("#secondZip").val(data.supplierZip);
                    $("#secondTel").val(data.supplierTel);
                	$("#secondFax").val(data.supplierFax);
                	$("#secondLegal").val(data.legal);
                	$("#secondAgent").val(data.agent);
                     
                }
            });
			
		}
		 function tt(id){
			 if(id==1){
					window.open("${ctx}/business/agreement/swAgreement/view?id=${swAgreement.id}",'top'); 
				}else{
					window.open("${ctx}/business/agreement/swAgreement/viewContract?id=${swAgreement.id}",'top'); 
				}
				
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
				 
				 function oksave(){
					 var amount  = parseFloat($("#amount").val());
					 var rate = rateT();
					 if(rate!=100){
						 alert("付款总计不是100%，请核算!");
						 return ;
					 }
			
					 if(amount!=productPay){
				
						 top.$.jBox.confirm("单项价格与合同总价不匹配,确认要保存吗？","系统提示",function(v,h,f){
								if(v=="ok"){
									 $("#inputForm").submit();
								}
							});
					 }else{
						
						 $("#inputForm").submit();
					 }
				 }

		function change(e){
			var amount = parseFloat($("#amount").val());
			var payRate = parseFloat($("#swAgreementPayList"+e+"_payRate").val());
			$("#swAgreementPayList"+e+"_payThis").val(amount*payRate/100);

		}
		function changeProduct(e){
			var productAmount = parseFloat($("#swAgreementProductList"+e+"_productAmount").val());
			var unitPrice = parseFloat($("#swAgreementProductList"+e+"_unitPrice").val());
			$("#swAgreementProductList"+e+"_totalPrice").val(productAmount*unitPrice);
			resultTR();
			
		}
		function resultTR(){
			var tdobj = document.getElementById('resultTR');
			var value = 0 ;
			for(var i=0 ;i<50;i++){
				if($("#swAgreementProductList"+i+"_totalPrice").val()==null || $("#swAgreementProductList"+i+"_totalPrice").val()==""){
				}else{
					var o = parseFloat($("#swAgreementProductList"+i+"_totalPrice").val());
					value = value+o ;
				}
			}
			tdobj.innerText = value;
			productPay = value ;
		}
		
		function rateT(){
			var value = 0 ;
			for(var i=0 ;i<50;i++){
				if($("#swAgreementPayList"+i+"_payRate").val()==null || $("#swAgreementPayList"+i+"_payRate").val()==""){
				}else{
					var o = parseFloat($("#swAgreementPayList"+i+"_payRate").val());
					value = value+o ;
				}
			}
			return value ;
		}
		var productPay = 0 ;
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/business/order/swOrder/">采购任务列表标列表</a></li>
		<li class="active"><a href="${ctx}/business/agreement/swAgreement/form?id=${swAgreement.id}">合同录入<shiro:hasPermission name="business:agreement:swAgreement:edit">${not empty swAgreement.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="business:agreement:swAgreement:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="swAgreement" action="${ctx}/business/agreement/swAgreement/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="orderId"/>
		<form:hidden path="state"/>
		<sys:message content="${message}"/>	
		<table class="table-form">
				<tr>
					<td class="tit" width="15%">合同编号：</td><td width="35%">
						<form:input path="agreementNo" htmlEscape="false" maxlength="255"  class="input-xlarge "/>
		
				<span class="help-inline"><font color="red">*</font>自动生成 </span>
					</td>
					
					<td class="tit" width="15%">合同名称：</td><td width="35%">
						<form:input path="agreementName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
						
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
			</tr>
				<tr>
				<td class="tit" width="15%">合同类型：</td><td width="35%">
						<form:select path="agreementType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('contract_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
					<td class="tit" width="15%">乙方：</td><td width="35%">
						<form:input path="agreementSecond" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">签订时间：</td><td width="35%">
						<input name="signingTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${swAgreement.signingTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-d',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">签订地点：</td><td width="35%">
						<form:input path="signingPlace" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">合计金额(小写)：</td><td width="35%">
						<form:input path="amount" htmlEscape="false" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">合计金额(大写)：</td><td width="35%">
						<form:input path="amountup" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">质量问题反馈时间(天)：</td><td width="35%">
						<form:input path="solveDay" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">质保期（年）：</td><td width="35%">
					<form:select path="warranty" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('warranty')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
						
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">交货时间(合同签订后(天))：</td><td width="35%">
						<form:input path="deliveryTime" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">交货费用承担：</td><td width="35%">
					<form:select path="deliveryMethod" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('deliveryMethod')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
						
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">异议天数：</td><td width="35%">
						<form:input path="objectionDay" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">异议回复天数：</td><td width="35%">
						<form:input path="objectionDayHf" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">发票交付天数：</td><td width="35%">
						<form:input path="invoiceDay" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">违约金：</td><td width="35%">
						
							<form:select path="falsify" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('falsify')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				
				<tr>
					<td class="tit" width="15%">乙方单位名称：</td><td width="35%">
						<form:input path="secondName" htmlEscape="false" maxlength="255" class="input-xlarge required" readonly="true" />
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">单位地址：</td><td width="35%">
						<form:input path="secondPlace" htmlEscape="false" maxlength="255" class="input-xlarge required"  readonly="true" />
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">法人代表：</td><td width="35%">
						<form:input path="secondLegal" htmlEscape="false" maxlength="255" class="input-xlarge required"  readonly="true" />
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">委托代理：</td><td width="35%">
						<form:input path="secondAgent" htmlEscape="false" maxlength="255" class="input-xlarge"  readonly="true" />

					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">联系人：</td><td width="35%">
						<form:input path="secondNameLiaison" htmlEscape="false" maxlength="255" class="input-xlarge required"  readonly="true" />
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">联系电话：</td><td width="35%">
						<form:input path="secondNamePhone" htmlEscape="false" maxlength="255" class="input-xlarge required"  readonly="true" />
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">开户银行：</td><td width="35%">
						<form:input path="secondBank" htmlEscape="false" maxlength="255" class="input-xlarge required"  readonly="true" />
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">账户：</td><td width="35%">
						<form:input path="secondBankNo" htmlEscape="false" maxlength="255" class="input-xlarge required"  readonly="true" />
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">税号：</td><td width="35%">
						<form:input path="secondDuty" htmlEscape="false" maxlength="255" class="input-xlarge required"  readonly="true" />
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">邮编：</td><td width="35%">
						<form:input path="secondZip" htmlEscape="false" maxlength="255" class="input-xlarge required"  readonly="true" />
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">电话：</td><td width="35%">
						<form:input path="secondTel" htmlEscape="false" maxlength="255" class="input-xlarge required"  readonly="true" />
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">传真：</td><td width="35%">
						<form:input path="secondFax" htmlEscape="false" maxlength="255" class="input-xlarge required"  readonly="true" />
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">已有合同编号：</td><td width="35%" colspan="3">
						<form:input path="field3" htmlEscape="false" maxlength="255" class="input-xxlarge "/>
						<span class="help-inline">如果有自己生成的合同编号，请记录在此！</span>
					</td>
					
				</tr>
					<tr>
					<td class="tit" width="15%">上传合同申请及合同：</td><td width="35%" colspan="3">
							<form:hidden id="file" path="file" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="file" type="files" uploadPath="/business/agreement/swAgreement" selectMultiple="true" readonly="true"/>
				
					</td>
					
				</tr>
				
				
		</table>
		
		
		<legend>付款信息</legend>
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>序号</th>
								<th>百分比</th>
								<th>金额</th>
								<th>付款条件</th>
								<shiro:hasPermission name="business:agreement:swAgreement:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="swAgreementPayList">
						</tbody>
						<shiro:hasPermission name="business:agreement:swAgreement:edit"><tfoot>
							<tr><td colspan="9"><a href="javascript:" onclick="addRow('#swAgreementPayList', swAgreementPayRowIdx, swAgreementPayTpl);swAgreementPayRowIdx = swAgreementPayRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="swAgreementPayTpl">//<!--
						<tr id="swAgreementPayList{{idx}}">
							<td class="hide">
								<input id="swAgreementPayList{{idx}}_id" name="swAgreementPayList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="swAgreementPayList{{idx}}_delFlag" name="swAgreementPayList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="swAgreementPayList{{idx}}_payIndex" name="swAgreementPayList[{{idx}}].payIndex" type="text" value="{{row.payIndex}}" maxlength="11" class="input-small "/>
							</td>
							<td>
								
							    <select id="swAgreementPayList{{idx}}_payRate" name="swAgreementPayList[{{idx}}].payRate" data-value="{{row.payRate}}" class="input-small " onchange="change({{idx}})" >
									<option value=""></option>
									<c:forEach items="${fns:getDictList('payRate')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>

                              </td>
							<td>
								<input id="swAgreementPayList{{idx}}_payThis" name="swAgreementPayList[{{idx}}].payThis" type="text" value="{{row.payThis}}" maxlength="11" class="input-small "/>
							</td>
							<td>
								
								<select id="swAgreementPayList{{idx}}_payCondition" name="swAgreementPayList[{{idx}}].payCondition" data-value="{{row.payCondition}}" class="input-xxlarge ">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('pay_state')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
						
                             </td>
						
							<shiro:hasPermission name="business:agreement:swAgreement:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#swAgreementPayList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var swAgreementPayRowIdx = 0, swAgreementPayTpl = $("#swAgreementPayTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(swAgreement.swAgreementPayList)};
							for (var i=0; i<data.length; i++){
								addRow('#swAgreementPayList', swAgreementPayRowIdx, swAgreementPayTpl, data[i]);
								swAgreementPayRowIdx = swAgreementPayRowIdx + 1;
							}
						});
					</script>
			
			
		<legend>产品信息</legend>
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>序号</th>
								<th>产品名称</th>
								<th>规格型号</th>
								<th>生产厂商</th>
								<th>数量</th>
								<th>货期(周)</th>
								<th>计量单位</th>
								<th>单价(元)</th>
								<th>总价（元）</th>
								<shiro:hasPermission name="business:agreement:swAgreement:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="swAgreementProductList">
						</tbody>
						<shiro:hasPermission name="business:agreement:swAgreement:edit"><tfoot>
							<tr><td colspan="7"><a href="javascript:" onclick="addRow('#swAgreementProductList', swAgreementProductRowIdx, swAgreementProductTpl);swAgreementProductRowIdx = swAgreementProductRowIdx + 1;" class="btn">新增</a></td><td colspan="1" style="text-align:center;">合计：</td><td colspan="2" id="resultTR" ></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="swAgreementProductTpl">//<!--
						<tr id="swAgreementProductList{{idx}}">
							<td class="hide">
								<input id="swAgreementProductList{{idx}}_id" name="swAgreementProductList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="swAgreementProductList{{idx}}_delFlag" name="swAgreementProductList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="swAgreementProductList{{idx}}_productNo" name="swAgreementProductList[{{idx}}].productNo" type="text" value="{{row.productNo}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="swAgreementProductList{{idx}}_productName" name="swAgreementProductList[{{idx}}].productName" type="text" value="{{row.productName}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="swAgreementProductList{{idx}}_productType" name="swAgreementProductList[{{idx}}].productType" type="text" value="{{row.productType}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="swAgreementProductList{{idx}}_productMade" name="swAgreementProductList[{{idx}}].productMade" type="text" value="{{row.productMade}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="swAgreementProductList{{idx}}_productAmount" name="swAgreementProductList[{{idx}}].productAmount" type="text" value="{{row.productAmount}}"  onchange="changeProduct({{idx}})"  maxlength="11" class="input-small number required"/>
							</td>
							<td>
								<input id="swAgreementProductList{{idx}}_productShipment" name="swAgreementProductList[{{idx}}].productShipment" type="text" value="{{row.productShipment}}" maxlength="255" class="input-small required"/>
							</td>
							<td>
								<select id="swAgreementProductList{{idx}}_productCompany" name="swAgreementProductList[{{idx}}].productCompany" data-value="{{row.productCompany}}" class="input-small ">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('product_company')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input id="swAgreementProductList{{idx}}_unitPrice" name="swAgreementProductList[{{idx}}].unitPrice" onchange="changeProduct({{idx}})" type="text" value="{{row.unitPrice}}" class="input-small number required"/>
							</td>
							<td>
								<input id="swAgreementProductList{{idx}}_totalPrice" name="swAgreementProductList[{{idx}}].totalPrice" type="text" value="{{row.totalPrice}}" class="input-small number required"/>
							</td>
							<shiro:hasPermission name="business:agreement:swAgreement:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#swAgreementProductList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var swAgreementProductRowIdx = 0, swAgreementProductTpl = $("#swAgreementProductTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(swAgreement.swAgreementProductList)};
							for (var i=0; i<data.length; i++){
								addRow('#swAgreementProductList', swAgreementProductRowIdx, swAgreementProductTpl, data[i]);
								swAgreementProductRowIdx = swAgreementProductRowIdx + 1;
							}
							resultTR();
						});
					</script>
			
		<div class="form-actions">
		
			<input id="dayin" class="btn" type="button"  onclick="tt(2)" value="申请预览"/>
			<input id="dayin" class="btn" type="button"  onclick="tt(1)" value="合同预览"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>