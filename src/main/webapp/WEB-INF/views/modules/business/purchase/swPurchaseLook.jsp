<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仪器申购单管理</title>
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
		});
		
		 function tt(id){
				
				window.location.href="${ctx}/business/purchase/swPurchase/view?id=${swPurchase.id}" ;
			}
			
			function ok(e){
				
				var file = $("#file").val();
				if(file!=null && file!=""){
					$("#state").val("1");
					$("#inputForm").submit();
				}else{
					alert("请上传审批后的表单！");
				}
				
			}
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
			
			function change(e){
				var amount = parseFloat($("#amount").val());
				var payRate = parseFloat($("#swAgreementPayList"+e+"_payRate").val());
				$("#swAgreementPayList"+e+"_payThis").val(amount*payRate*0.01);

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
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/business/order/swOrder/">采购任务列表标列表</a></li>
		<li class="active"><a href="${ctx}/business/purchase/swPurchase/form?id=${swPurchase.id}">仪器申购单<shiro:hasPermission name="business:purchase:swPurchase:edit">${not empty swPurchase.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="business:purchase:swPurchase:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="swPurchase" action="${ctx}/business/purchase/swPurchase/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="orderId"/>
		<form:hidden path="state"/>
		<sys:message content="${message}"/>	
		<table class="table-form">
				<tr>
					<td class="tit" width="15%">申请单位：</td><td width="35%">
							<sys:treeselect id="office" name="office.id" value="${swPurchase.office.id}" labelName="officeName" labelValue="${swPurchase.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">填报日期：</td><td width="35%">
						<input name="negotiateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${swPurchase.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>	
				<tr>
					<td class="tit" width="15%">名称：</td><td width="35%">
						<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">数量：</td><td width="35%">
						<form:input path="amount" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>	
				<tr>
					<td class="tit" width="15%">型号规格、技术条件：</td><td width="35%">
						<form:input path="specifications" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">生产单位（国别）：</td><td width="35%">
						<form:input path="made" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>	
				<tr>
					<td class="tit" width="15%">单价：</td><td width="35%">
						<form:input path="unitPrice" htmlEscape="false" class="input-xlarge number required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">资金来源：</td><td width="35%">
						<form:input path="funds" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>	
				<tr>
					<td class="tit" width="15%">申请理由及用途：</td><td width="35%" colspan="3">
						<form:textarea path="reason" htmlEscape="false" class="input-xxlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>	
				<tr>
					<td class="tit" width="15%">附件：</td><td width="35%" colspan="3">
							<form:hidden id="file" path="file" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="file" type="files" uploadPath="/business/purchase/swPurchase" selectMultiple="true" readonly="true"/>
				
					</td>
					
				</tr>
		</table>
		
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
							var data = ${fns:toJson(swPurchase.swAgreementProductList)};
							for (var i=0; i<data.length; i++){
								addRow('#swAgreementProductList', swAgreementProductRowIdx, swAgreementProductTpl, data[i]);
								swAgreementProductRowIdx = swAgreementProductRowIdx + 1;
							}
							resultTR();
						});
					</script>
		
		
		<div class="form-actions">
			
			<input id="dayin" class="btn" type="button"  onclick="tt(1)" value="打印预览"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>