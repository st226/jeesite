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
	
					<td class="tit" width="15%" rowspan="2">供应商全称：</td><td width="35%"  rowspan="2">
						<form:input path="supplierName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					<td class="tit" width="15%">企业性质和隶属关系：</td><td width="35%">
						<form:select path="supplierNature" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('supplier_nature')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
					
				</tr>
				<tr>	
					<td class="tit" width="15%">员工总数：</td><td width="35%" >
						<form:input path="employees" htmlEscape="false" maxlength="11" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
				   <td class="tit" width="15%"  rowspan="2">供应商代号/简称：</td><td width="35%"   rowspan="2">
						<form:input path="supplierNameSimple" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
	               <td class="tit" width="15%">注册时间：</td><td width="35%">
						<input name="registrationTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${qmSupplier.registrationTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</td>
					
			    </tr>
				<tr>
				 <td class="tit" width="15%">注册资金(万元)：</td><td width="35%">
						<form:input path="registeredCapital" htmlEscape="false" maxlength="255" class="input-xlarge  number"/>
		
					</td>
					
				</tr>
				
				
				
				<tr>
					
					<td class="tit" width="15%">法人：</td><td width="35%"  colspan="3">
						<form:input path="legalName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
		
					</td>
					
					
					
				</tr>
					<tr>
					
					<td class="tit" width="15%">电话：</td><td width="35%" >
						<form:input path="legalTel" htmlEscape="false" maxlength="255" class="input-xlarge "/>
					</td>
					<td class="tit" width="15%">移动电话：</td><td width="35%">
						<form:input path="legalPhone" htmlEscape="false" maxlength="255" class="input-xlarge "/>
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
					<td class="tit" width="15%">移动电话：</td><td width="35%">
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
				<td class="tit" width="15%">通讯经营地址：</td><td width="35%" colspan="1">
						<form:input path="address" htmlEscape="false" maxlength="255" class="input-xlarge "/>
					</td>	
					<td class="tit" width="15%">邮编：</td><td width="35%" >
						<form:input path="postalCode" htmlEscape="false" maxlength="255" class="input-xlarge "/>
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
				
				<tr>
				   <td class="tit" width="15%" colspan="4"  style=" text-align:left;" ><span class="help-inline"> 
				   注1：供应商名称是以激光公司签订外包合同的公章名称为准，其他名称填写单位的内部简称。</br>
注2：企业性质是军工企业、非军工国企、股份制或私企、中国科学院或高校等。若是属于军工企业，请直接填写所属的军工集团公司或地方军工企业。</br>
注3：通信或经营地址填写省市、区县、街道、门牌号。</br>
注4：航天系统主要用户填写航天系统内的所有用户，如：XX所、XX厂。</br>
注5：外包产品应用型号类型填写外包产品所属的型号，如战术导弹、卫星。</br>
注6：外包产品范围填写供应商的主营产品。
				   </span></td>
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
				
		  </table>

		<div class="form-actions">
			<shiro:hasPermission name="quality:qmsupplier:qmSupplier:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>