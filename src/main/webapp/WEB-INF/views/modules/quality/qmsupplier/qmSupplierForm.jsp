<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商管理管理</title>
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
		function addRow(list, idx, tpl, row){
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
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/quality/qmsupplier/qmSupplier/">供应商管理列表</a></li>
		<li class="active"><a href="${ctx}/quality/qmsupplier/qmSupplier/form?id=${qmSupplier.id}">供应商管理<shiro:hasPermission name="quality:qmsupplier:qmSupplier:edit">${not empty qmSupplier.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="quality:qmsupplier:qmSupplier:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="qmSupplier" action="${ctx}/quality/qmsupplier/qmSupplier/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="state"/>
		<form:hidden path="processinstid"/>
		<form:hidden path="supplierType"/>
		<sys:message content="${message}"/>		
		<table class="table-form">
				<tr>
	
					<td class="tit" width="15%">供应商全称：</td><td width="35%" >
						<form:input path="supplierName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					<td class="tit" width="15%">供应商代号/简称：</td><td width="35%" >
						<form:input path="supplierNameSimple" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
					
					
				</tr>
				<tr>
	
					<td class="tit" width="15%">规格型号：</td><td width="35%" >
						<form:input path="model" htmlEscape="false" maxlength="100" class="input-xlarge"/>
			
					</td>
					<td class="tit" width="15%">产品名称：</td><td width="35%" >
						<form:input path="product" htmlEscape="false" maxlength="100" class="input-xlarge"/>
	
					</td>
					
					
					
				</tr>
				<tr>
					
					<td class="tit" width="15%">业务范围：</td><td width="35%">
						<form:input path="scope" htmlEscape="false" maxlength="100" class="input-xlarge"/>
				<td class="tit" width="15%">类别：</td><td width="35%">
						<form:select path="expansion" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('expansion')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				</tr>
				<tr>
					
					<td class="tit" width="15%">重要程度：</td><td width="35%">
						<form:select path="importance" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('supplier_importance')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<td class="tit" width="15%">评价等级：</td><td width="35%">
						<form:select path="evaluationLevel" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('evaluation_level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				</tr>
				<tr>
					
					<td class="tit" width="15%">供应商性质：</td><td width="35%">
						<form:select path="supplierNature" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('supplier_nature')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					<td class="tit" width="15%">注册资金：</td><td width="35%">
						<form:input path="registeredCapital" htmlEscape="false" maxlength="255" class="input-xlarge  number"/>
		
					</td>
					
					
				</tr>
				<tr>
					
					<td class="tit" width="15%">联系人：</td><td width="35%"  colspan="3">
						<form:input path="contacts" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			
					</td>
					
					
					
				</tr>
					<tr>
					
					<td class="tit" width="15%">联系电话：</td><td width="35%" >
						<form:input path="contactNumber" htmlEscape="false" maxlength="255" class="input-xlarge "/>
					</td>
					<td class="tit" width="15%">手机：</td><td width="35%">
						<form:input path="mobilePhone" htmlEscape="false" maxlength="255" class="input-xlarge "/>
					</td>
					
					
				</tr>
				<tr>
					
					<td class="tit" width="15%">传真：</td><td width="35%" >
						<form:input path="fax" htmlEscape="false" maxlength="255" class="input-xlarge "/>
					</td>
					<td class="tit" width="15%">邮箱：</td><td width="35%">
						<form:input path="mailbox" htmlEscape="false" maxlength="255" class="input-xlarge "/>
					</td>
					
					
				</tr>
				<tr>
					
					<td class="tit" width="15%">地址：</td><td width="35%" >
						<form:input path="address" htmlEscape="false" maxlength="255" class="input-xlarge "/>
					</td>
					<td class="tit" width="15%">是否为关重件：</td><td width="35%">
						<form:radiobuttons path="isImportant" items="${fns:getDictList('is_leaf')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
					</td>
					
					
				</tr>
				<tr>
					
					
					<td class="tit" width="15%">外协军种：</td><td width="35%">
						<form:input path="assist" htmlEscape="false" maxlength="255" class="input-xlarge "/>
					</td>
					
					
				</tr>
				
				<tr>
					<td class="tit" width="15%">添加原因：</td><td width="35%"  colspan="3">
						<form:textarea path="reasons" htmlEscape="false" rows="3" maxlength="255" class="input-xxlarge "/>
					</td>
					
					
				</tr>
				<tr>
					
					<td class="tit" width="15%">申请人：</td><td width="35%" >
						<form:input path="applicantName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
					</td>
					<td class="tit" width="15%">申请时间：</td><td width="35%">
						<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${qmSupplier.createDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</td>
					
					
				</tr>
				<tr>
					
					<td class="tit" width="15%">法人：</td><td width="35%"  colspan="3">
						<form:input path="legalName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
		
					</td>
					
					
					
				</tr>
					<tr>
					
					<td class="tit" width="15%">法人电话：</td><td width="35%" >
						<form:input path="legalTel" htmlEscape="false" maxlength="255" class="input-xlarge "/>
					</td>
					<td class="tit" width="15%">移动电话：</td><td width="35%">
						<form:input path="legalPhone" htmlEscape="false" maxlength="255" class="input-xlarge "/>
					</td>
				</tr>
					<tr>
					
					<td class="tit" width="15%">员工总数：</td><td width="35%" >
						<form:input path="employees" htmlEscape="false" maxlength="11" class="input-xlarge "/>
					</td>
					<td class="tit" width="15%">注册时间：</td><td width="35%">
						<input name="registrationTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${qmSupplier.registrationTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</td>
				</tr>
				<tr>
					
					<td class="tit" width="15%">注册地点：</td><td width="35%" >
						<form:input path="registrationCapital" htmlEscape="false" class="input-xlarge "/>
					</td>
					<td class="tit" width="15%">通讯地址：</td><td width="35%">
						<form:input path="mailingAddress" htmlEscape="false" maxlength="255" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					
					<td class="tit" width="15%">邮编：</td><td width="35%" >
						<form:input path="postalCode" htmlEscape="false" maxlength="255" class="input-xlarge "/>
					</td>
					<td class="tit" width="15%">工商注册号：</td><td width="35%" >
						<form:input path="dutyno" htmlEscape="false" maxlength="255" class="input-xlarge "/>
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">航天主要客户：</td><td width="35%"  colspan="3">
						<form:textarea path="customer" htmlEscape="false" rows="3" maxlength="255" class="input-xxlarge "/>
					</td>
					
					
				</tr>
				<tr>
					<td class="tit" width="15%">外包产品应用型号类型：</td><td width="35%"  colspan="3">
						<form:textarea path="wbType" htmlEscape="false" rows="3" maxlength="255" class="input-xxlarge "/>
					</td>
					
					
				</tr>
				<tr>
					<td class="tit" width="15%">外包产品名称：</td><td width="35%"  colspan="3">
						<form:textarea path="wbName" htmlEscape="false" rows="3" maxlength="255" class="input-xxlarge "/>
					</td>
					
					
				</tr>
				<tr>
					<td class="tit" width="15%">外包产品范围：</td><td width="35%"  colspan="3">
						<form:textarea path="productScope" htmlEscape="false" rows="3" maxlength="255" class="input-xxlarge "/>
					</td>
					
					
				</tr>
				
				
		</table>
		
		
		<legend>准入评定申报材料清单</legend>
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th width="10%">序号</th>
								<th  width="20%">名称</th>
								<th  width="10%">提交情况</th>
								<th  width="30%">附件</th>
								<th  width="30%">备注</th>
								
								<shiro:hasPermission name="quality:qmsupplier:qmSupplier:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="qmSupplierAdmittanceList">
						</tbody>
						<shiro:hasPermission name="quality:qmsupplier:qmSupplier:edit"><tfoot>
							<tr><td colspan="12"><a href="javascript:" onclick="addRow('#qmSupplierAdmittanceList', qmSupplierAdmittanceRowIdx, qmSupplierAdmittanceTpl);qmSupplierAdmittanceRowIdx = qmSupplierAdmittanceRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="qmSupplierAdmittanceTpl">//<!--
						<tr id="qmSupplierAdmittanceList{{idx}}">
							<td class="hide">
								<input id="qmSupplierAdmittanceList{{idx}}_id" name="qmSupplierAdmittanceList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="qmSupplierAdmittanceList{{idx}}_delFlag" name="qmSupplierAdmittanceList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="qmSupplierAdmittanceList{{idx}}_admittanceIndex" name="qmSupplierAdmittanceList[{{idx}}].admittanceIndex" type="text" value="{{row.admittanceIndex}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="qmSupplierAdmittanceList{{idx}}_name" name="qmSupplierAdmittanceList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="255" class="input-xlarge "/>
							</td>
							<td>
								
                                <select id="qmSupplierAdmittanceList{{idx}}_isSubmit" name="qmSupplierAdmittanceList[{{idx}}].isSubmit" data-value="{{row.isSubmit}}" class="input-small ">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('is_leaf')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
                           </td>
							<td>
								<input id="qmSupplierAdmittanceList{{idx}}_file" name="qmSupplierAdmittanceList[{{idx}}].file" type="hidden" value="{{row.file}}" maxlength="255"/>
								<sys:ckfinder input="qmSupplierAdmittanceList{{idx}}_file" type="files" uploadPath="/quality/qmsupplier/qmSupplier" selectMultiple="true"/>
							</td>
							<td>
								<textarea id="qmSupplierAdmittanceList{{idx}}_remarks" name="qmSupplierAdmittanceList[{{idx}}].remarks" rows="4" maxlength="255" class="input-xlarge ">{{row.remarks}}</textarea>
							</td>
							
							<shiro:hasPermission name="quality:qmsupplier:qmSupplier:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#qmSupplierAdmittanceList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var qmSupplierAdmittanceRowIdx = 0, qmSupplierAdmittanceTpl = $("#qmSupplierAdmittanceTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(qmSupplier.qmSupplierAdmittanceList)};
							for (var i=0; i<data.length; i++){
								addRow('#qmSupplierAdmittanceList', qmSupplierAdmittanceRowIdx, qmSupplierAdmittanceTpl, data[i]);
								qmSupplierAdmittanceRowIdx = qmSupplierAdmittanceRowIdx + 1;
							}
						});
					</script>

		<div class="form-actions">
			<shiro:hasPermission name="quality:qmsupplier:qmSupplier:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>