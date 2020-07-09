<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>维修任务管理</title>
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
			newDate();
		});
		function datetostring(day,num){
			var d = new Date(day);

			d = d.setDate(d.getDate()+num)
			d = new Date(d);

			return d.toISOString().split("T")[0];
		}
		
		var  type;
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
		
		function tt(){
			var amountYs = parseFloat('${swOrder.amountYs}');
			var da = $("#gmDate").val() ;
			var da1 = parseInt('${dictList[0].remarks}');
			var da2 = parseInt('${dictList[1].remarks}')+da1;
			var da3 = parseInt('${dictList[2].remarks}')+da2;
			if(amountYs<200000 || type=='1'){
				da3=da1+parseInt('${dictList[2].remarks}');
			}
			var da4 = parseInt('${dictList[3].remarks}')+da3;
			if(type=='2'){
				da4=da1+parseInt('${dictList[3].remarks}');
			}
			var da5 = parseInt('${dictList[4].remarks}')+da4;

			$("#field1Date").val(datetostring(da,da1));
			$("#field2Date").val(datetostring(da,da2));
			$("#field3Date").val(datetostring(da,da3));
			$("#field5Date").val(datetostring(da,da4));
			$("#field6Date").val(datetostring(da,da5));
		}
		function tt1(){
			var amountYs = parseFloat('${swOrder.amountYs}');
			var da = $("#field1Date").val() ;
			var da2 = parseInt('${dictList[1].remarks}');
			var da3 = parseInt('${dictList[2].remarks}')+da2;
			if(amountYs<200000 || type=='1'){
				da3=da2+parseInt('${dictList[2].remarks}');
			}
			var da4 = parseInt('${dictList[3].remarks}')+da3;
			if(type=='2'){
				da4=da2+parseInt('${dictList[3].remarks}');
			}
			var da5 = parseInt('${dictList[4].remarks}')+da4;


			$("#field2Date").val(datetostring(da,da2));
			$("#field3Date").val(datetostring(da,da3));
			$("#field5Date").val(datetostring(da,da4));
			$("#field6Date").val(datetostring(da,da5));
		}
		function tt2(){
			var da = $("#field2Date").val() ;	
			var da3 = parseInt('${dictList[2].remarks}');
			var da5 = parseInt('${dictList[3].remarks}')+da3;
			var da6 = parseInt('${dictList[4].remarks}')+da5;
			$("#field3Date").val(datetostring(da,da3));
			$("#field5Date").val(datetostring(da,da5));
			$("#field6Date").val(datetostring(da,da6));
		}
		function tt3(){
			var da = $("#field3Date").val() ;	
			var da5 = parseInt('${dictList[3].remarks}');
			var da6 = parseInt('${dictList[4].remarks}')+da5;
			$("#field5Date").val(datetostring(da,da5));
			$("#field6Date").val(datetostring(da,da6));
		}
		function tt4(){
			var da = $("#field5Date").val() ;	
			var da6 = parseInt('${dictList[4].remarks}');
			$("#field6Date").val(datetostring(da,da6));
		}
		
		function newDate(){
			var amountYs = parseFloat('${swOrder.amountYs}');
			type = '${swOrder.type}';
			if(type=='1'||amountYs<200000){
            	document.getElementById("field2Date").style.display="none" ;
		    }
		    if(type=='2'){
		    	document.getElementById("field2Date").style.display="none" ;
		    	document.getElementById("field3Date").style.display="none" ;
		    }
		}
		function orderType(e){
			document.getElementById("field1Date").style.display="" ;
			document.getElementById("field2Date").style.display="" ;
			document.getElementById("field3Date").style.display="" ;
			document.getElementById("field5Date").style.display="" ;
			document.getElementById("field6Date").style.display="" ;
			var amountYs = parseFloat('${swOrder.amountYs}');
			if(e.value=='1'||amountYs<200000){
            	document.getElementById("field2Date").style.display="none" ;
		    }
		    if(e.value=='2'){
		    	document.getElementById("field2Date").style.display="none" ;
		    	document.getElementById("field3Date").style.display="none" ;
		    }
            
		}
	</script>
</head>
<body>
	<br/>
	<form:form id="inputForm" modelAttribute="swOrder" action="${ctx}/business/order/swOrder/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		
		<table class="table-form">
				<tr>
					<td class="tit" width="15%">项目名称：</td><td width="35%">
							<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">负责人：</td><td width="35%">
						<sys:treeselect id="user" name="user.id" value="${swOrder.user.id}" labelName="userName" labelValue="${swOrder.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">是否特殊项目：</td><td width="35%">
							
						<form:radiobuttons path="type"  items="${fns:getDictList('order_type')}" itemLabel="label" itemValue="value" htmlEscape="false"  class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					<td class="tit" width="15%">调研人：</td><td width="35%">
						<sys:treeselect id="dyuserId" name="dyuserId" value="${swOrder.dyuserId}" labelName="dyuserName" labelValue="${swOrder.dyuserName}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				
				<tr>
					<td class="tit" width="15%">维修金额：</td><td width="35%" >
							<form:input path="amountYs" htmlEscape="false" maxlength="255" class="input-xlarge number" />
				<span class="help-inline"><font color="red">*</font> </span>
					
				<td class="tit" width="15%">预算金额：</td><td width="35%" >
							<form:input path="amount" htmlEscape="false" maxlength="255" class="input-xlarge " readonly="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
					
				
					
				</tr>
				<tr>
					<td class="tit" width="15%">起始时间：</td><td width="35%">
						<input id="gmDate" name="gmDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${swOrder.gmDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" onchange="tt()" />
				                <span class="help-inline"><font color="red">*</font> </span>
					</td>
					<td class="tit" width="15%">调研/特殊项目截止日期：</td><td width="35%">
							<input id="field1Date" name="field1Date" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${swOrder.field1Date}" pattern="yyyy-MM-dd"  />"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"  onchange="tt1()" />
				   
					</td>
					
					
				</tr>
				<tr>
				<td class="tit" width="15%">招投标截止日期：</td><td width="35%">
							<input id="field2Date" name="field2Date" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${swOrder.field2Date}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"  onchange="tt2()" />
				     
					</td>	
				
					<td class="tit" width="15%">谈判截止日期：</td><td width="35%">
							<input id="field3Date"  name="field3Date" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${swOrder.field3Date}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"  onchange="tt3()" />
			
					</td>
					
				
					
				</tr>
				<tr>
				<td class="tit" width="15%">合同截止日期：</td><td width="35%">
							<input  id="field5Date"  name="field5Date" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${swOrder.field5Date}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"  onchange="tt4()" />
		
					</td>	
					<td class="tit" width="15%">付款起始日期：</td><td width="35%">
							<input  id="field6Date"  name="field6Date" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${swOrder.field6Date}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
		
					</td>
				
					
				</tr>
		</table>	
		
		
		<legend>维修设备明细</legend>
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>申请部门</th>
				<th>设备名称</th>
				<th>设备数量</th>
				<th>型号规格</th>
				<th>生产厂家</th>
				<th>维修预算费用</th>
				<th>申请人</th>
				<th>联系电话</th>
	
								<shiro:hasPermission name="business:agreement:swAgreement:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="sbEquipmentRepairList">
						</tbody>
						<shiro:hasPermission name="business:agreement:swAgreement:edit"><tfoot>
							<tr><td colspan="9"><a href="javascript:" onclick="addRow('#sbEquipmentRepairList', swAgreementPayRowIdx, swProductTpl);swAgreementPayRowIdx = swAgreementPayRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="swProductTpl">//<!--
						<tr id="sbEquipmentRepairList{{idx}}">
							<td class="hide">
								<input id="sbEquipmentRepairList{{idx}}_id" name="sbEquipmentRepairList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="sbEquipmentRepairList{{idx}}_delFlag" name="sbEquipmentRepairList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
                             <td>
								<input id="sbEquipmentRepairList{{idx}}_officeName" name="sbEquipmentRepairList[{{idx}}].officeName" type="text" value="{{row.officeName}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="sbEquipmentRepairList{{idx}}_equipmentName" name="sbEquipmentRepairList[{{idx}}].equipmentName" type="text" value="{{row.equipmentName}}"  class="input-small "/>
							</td>
							<td>
								<input id="sbEquipmentRepairList{{idx}}_equipmentAmount" name="sbEquipmentRepairList[{{idx}}].equipmentAmount" type="text" value="{{row.equipmentAmount}}" class="input-small "/>
							</td>
							
							<td>
								<input id="sbEquipmentRepairList{{idx}}_equipmentType" name="sbEquipmentRepairList[{{idx}}].equipmentType" type="text" value="{{row.equipmentType}}" class="input-small "/>
							</td>
							
							<td>
								<input id="sbEquipmentRepairList{{idx}}_equipmentMade" name="sbEquipmentRepairList[{{idx}}].equipmentMade" type="text" value="{{row.equipmentMade}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="sbEquipmentRepairList{{idx}}_equipmentBudgt" name="sbEquipmentRepairList[{{idx}}].equipmentBudgt" type="text" value="{{row.equipmentBudgt}}" maxlength="255" class="input-small "/>
							</td>
                             <td>
								<input id="sbEquipmentRepairList{{idx}}_userName" name="sbEquipmentRepairList[{{idx}}].userName" type="text" value="{{row.userName}}" maxlength="255" class="input-small "/>
							</td>
                           
                            <td>
								<input id="sbEquipmentRepairList{{idx}}_userPhone" name="sbEquipmentRepairList[{{idx}}].userPhone" type="text" value="{{row.userPhone}}" maxlength="255" class="input-small "/>
							</td>
                            
							<shiro:hasPermission name="business:agreement:swAgreement:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#sbEquipmentRepairList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var swAgreementPayRowIdx = 0, swProductTpl = $("#swProductTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(swOrder.sbEquipmentRepairList)};
							for (var i=0; i<data.length; i++){
								addRow('#sbEquipmentRepairList', swAgreementPayRowIdx, swProductTpl, data[i]);
								swAgreementPayRowIdx = swAgreementPayRowIdx + 1;
							}
						});
					</script>
		
		
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	</body>
</html>