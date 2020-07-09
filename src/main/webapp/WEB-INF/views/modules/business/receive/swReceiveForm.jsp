<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仪器设备开箱验收管理</title>
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
		function tt(id){
			 var key = "${swReceive.id}";
				if(key==null || key==""){
					alert("请先保存，再打印！");
					return ;
				}
				
				window.open("${ctx}/business/receive/swReceive/view3?id=${swReceive.id}",'top'); 
			

		}
         function tt2(id){
        	 var key = "${swReceive.id}";
				if(key==null || key==""){
					alert("请先保存，再打印！");
					return ;
				}
				
				window.open("${ctx}/business/receive/swReceive/view2?id=${swReceive.id}",'top'); 
		
		}
         
         
         function addzlRow(e){
        	 var row = {"equipmentName":e} ;
        	 addRow('#swReceiveDataList', swReceiveDataRowIdx, swReceiveDataTpl,row);
        	 swReceiveDataRowIdx++;
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
		<li><a href="${ctx}/business/order/swOrder/">采购任务列表标列表</a></li>
		<li class="active"><a href="${ctx}/business/receive/swReceive/form?id=${swReceive.id}">仪器设备开箱验收<shiro:hasPermission name="business:receive:swReceive:edit">${not empty swReceive.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="business:receive:swReceive:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="swReceive" action="${ctx}/business/receive/swReceive/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="state"/>
		<form:hidden path="orderId"/>
		<form:hidden path="contractId" />
		<sys:message content="${message}"/>	
		<table class="table-form">
				<tr>
					<td class="tit" width="15%">计划编号：</td><td width="35%">
						<form:input path="planNumber" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">所属项目：</td><td width="35%">
						<form:input path="items" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
					<tr>
					<td class="tit" width="15%">合同编号：</td><td width="35%">
						<form:input path="contractCode" htmlEscape="false" maxlength="255" class="input-xlarge "/>

					</td>
					<td class="tit" width="15%">合同名称：</td><td width="35%">
						<form:input path="field8" htmlEscape="false" maxlength="255" class="input-xlarge "/>

					</td>
					
					
				</tr>
				<tr>
					
					<td class="tit" width="15%">资产归属：</td><td width="35%">
						<form:input path="assets" htmlEscape="false" maxlength="255" class="input-xlarge "/>
	
					</td>
					<td class="tit" width="15%">供应商：</td><td width="35%" colspan="3">
						<form:input path="supplier" htmlEscape="false" maxlength="255" class="input-xlarge "/>
						
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">供应商联系人：</td><td width="35%">
						<form:input path="supplierUser" htmlEscape="false" maxlength="255" class="input-xlarge "/>
						
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">供应商联系方式：</td><td width="35%">
						<form:input path="supplierPhone" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				
				
				
				
				<tr>
			
					<td class="tit" width="15%">开箱地点：</td><td width="35%" >
						<form:input path="location" htmlEscape="false" maxlength="255" class="input-xlarge "/>
						
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">合同负责人：</td><td width="35%">
						<form:input path="acceptor" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			
					</td>
				<td class="tit" width="15%">联系方式：</td><td width="35%">
						<form:input path="contactInformation" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				
				<tr>
					<td class="tit" width="15%">备注：</td><td width="35%" colspan="3">
						<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
				
					</td>
					
					
				</tr>
					<tr>
					<td class="tit" width="15%">上传审批结果：</td><td width="35%" colspan="3">
							<form:hidden id="file" path="file" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="file" type="files" uploadPath="/business/receive/swReceive" selectMultiple="true"/>
					</td>
					
				</tr>	
				
			</table>	
	

		
	
	
		
		
		
			
			
			<legend>设备信息</legend>
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th title="选择">资料添加</th>
								<th>设备名称</th>
								<th>数量</th>
								<th>使用部门</th>
								<th>制造商</th>
								<th>到货日期</th>
								<th>出厂日期</th>
								<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;技术协议一致性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
								<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;外观完整性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
								<th>型号</th>
								<th>&nbsp;&nbsp;&nbsp;型号一致性&nbsp;&nbsp;&nbsp;&nbsp;</th>
								
								<th>规格</th>
								<th>&nbsp;&nbsp;&nbsp;规格一致性&nbsp;&nbsp;&nbsp;</th>
								<th>原值</th>
								<th>功率</th>
								<th>出厂编号</th>
								
								<shiro:hasPermission name="business:receive:swReceive:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="swReceiveEquipmentList">
						</tbody>
						<shiro:hasPermission name="business:receive:swReceive:edit"><tfoot>
							<tr><td colspan="14"><a href="javascript:" onclick="addRow('#swReceiveEquipmentList', swReceiveEquipmentRowIdx, swReceiveEquipmentTpl);swReceiveEquipmentRowIdx = swReceiveEquipmentRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="swReceiveEquipmentTpl">//<!--
						<tr id="swReceiveEquipmentList{{idx}}">
							<td class="hide">
								<input id="swReceiveEquipmentList{{idx}}_id" name="swReceiveEquipmentList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
                                <input id="swReceiveEquipmentList{{idx}}_equipmentId" name="swReceiveEquipmentList[{{idx}}].equipmentId" type="hidden" value="{{row.equipmentId}}"/>
								<input id="swReceiveEquipmentList{{idx}}_delFlag" name="swReceiveEquipmentList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
                             <td>
                                     <a onclick="addzlRow('{{row.equipmentName}}')" title="资料添加"> <i class="icon-plus-sign"></i>资料</a>
                              </td>
							<td>
								<input id="swReceiveEquipmentList{{idx}}_equipmentName" name="swReceiveEquipmentList[{{idx}}].equipmentName" type="text" value="{{row.equipmentName}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="swReceiveEquipmentList{{idx}}_amount" name="swReceiveEquipmentList[{{idx}}].amount" type="text" value="{{row.amount}}" maxlength="255" class="input-small "/>
							</td>
                            
                             <td>
								<sys:treeselect id="modelList{{idx}}_team" name="swReceiveEquipmentList[{{idx}}].team" value="{{row.team}}" labelName="swReceiveEquipmentList[{{idx}}].teamname" labelValue="{{row.teamname}}"
									title="部门" url="/sys/office/treeData?type=2"  cssClass="required input-small " allowClear="true" notAllowSelectParent="true" />
							</td>
                             <td>
								<input id="swReceiveEquipmentList{{idx}}_made" name="swReceiveEquipmentList[{{idx}}].made" type="text" value="{{row.made}}" maxlength="255" class="input-small "/>
							</td>
                          
                              <td>
								<input id="swReceiveEquipmentList{{idx}}_cometime" name="swReceiveEquipmentList[{{idx}}].cometime" type="text"  style="width:100px"  readonly="readonly" maxlength="20" class="input-medium Wdate "
									value="{{row.cometime}}"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
							</td>
                            <td>
								<input id="swReceiveEquipmentList{{idx}}_outtime" name="swReceiveEquipmentList[{{idx}}].outtime" type="text"  style="width:100px"  readonly="readonly" maxlength="20" class="input-medium Wdate "
									value="{{row.outtime}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
							</td>
                           
                             <td>
								
							
                                
                                <c:forEach items="${fns:getDictList('is_agreement')}" var="dict" varStatus="dictStatus">
									<span>
                                        <input id="swReceiveEquipmentList{{idx}}_isAgreement${dictStatus.index}" name="swReceiveEquipmentList[{{idx}}].isAgreement" type="checkbox" value="${dict.value}" data-value="{{row.isAgreement}}">
                                         <label for="swReceiveEquipmentList{{idx}}_isAgreement${dictStatus.index}">${dict.label}</label>
                                   </span>
								</c:forEach>
                           </td>
                             <td>
                                 <c:forEach items="${fns:getDictList('is_appearance')}" var="dict" varStatus="dictStatus">
									<span>
                                        <input id="swReceiveEquipmentList{{idx}}_isAppearance${dictStatus.index}" name="swReceiveEquipmentList[{{idx}}].isAppearance" type="checkbox" value="${dict.value}" data-value="{{row.isAppearance}}">
                                         <label for="swReceiveEquipmentList{{idx}}_isAppearance${dictStatus.index}">${dict.label}</label>
                                   </span>
								</c:forEach>
										</td>
                           <td>
								<input id="swReceiveEquipmentList{{idx}}_equipmentModel" name="swReceiveEquipmentList[{{idx}}].equipmentModel" type="text" value="{{row.equipmentModel}}" maxlength="255" class="input-small "/>
							</td>
                             <td>
                                 <c:forEach items="${fns:getDictList('is_leaf')}" var="dict" varStatus="dictStatus">
									<span>
                                        <input id="swReceiveEquipmentList{{idx}}_isModel${dictStatus.index}" name="swReceiveEquipmentList[{{idx}}].isModel" type="checkbox" value="${dict.value}" data-value="{{row.isModel}}">
                                         <label for="swReceiveEquipmentList{{idx}}_isModel${dictStatus.index}">${dict.label}</label>
                                   </span>
								</c:forEach>
										</td>
                           
							
							<td>
								<input id="swReceiveEquipmentList{{idx}}_equipmentNorms" name="swReceiveEquipmentList[{{idx}}].equipmentNorms" type="text" value="{{row.equipmentNorms}}" maxlength="255" class="input-small "/>
							</td>
 <td>
                                 <c:forEach items="${fns:getDictList('is_leaf')}" var="dict" varStatus="dictStatus">
									<span>
                                        <input id="swReceiveEquipmentList{{idx}}_isNorms${dictStatus.index}" name="swReceiveEquipmentList[{{idx}}].isNorms" type="checkbox" value="${dict.value}" data-value="{{row.isNorms}}">
                                         <label for="swReceiveEquipmentList{{idx}}_isNorms${dictStatus.index}">${dict.label}</label>
                                   </span>
								</c:forEach>
										</td>

                        
                   
                           
							
							
							<td>
								<input id="swReceiveEquipmentList{{idx}}_equipmentValue" name="swReceiveEquipmentList[{{idx}}].equipmentValue" type="text" value="{{row.equipmentValue}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="swReceiveEquipmentList{{idx}}_equipmentPower" name="swReceiveEquipmentList[{{idx}}].equipmentPower" type="text" value="{{row.equipmentPower}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="swReceiveEquipmentList{{idx}}_equipmentFactoryNumber" name="swReceiveEquipmentList[{{idx}}].equipmentFactoryNumber" type="text" value="{{row.equipmentFactoryNumber}}" maxlength="255" class="input-small "/>
							</td>
							
							<shiro:hasPermission name="business:receive:swReceive:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#swReceiveEquipmentList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var swReceiveEquipmentRowIdx = 0, swReceiveEquipmentTpl = $("#swReceiveEquipmentTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(swReceive.swReceiveEquipmentList)};
							for (var i=0; i<data.length; i++){
								addRow('#swReceiveEquipmentList', swReceiveEquipmentRowIdx, swReceiveEquipmentTpl, data[i]);
								swReceiveEquipmentRowIdx = swReceiveEquipmentRowIdx + 1;
							}
						});
					</script>
					
					
					<legend>装箱资料清点</legend>
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>设备名称</th>
								<th>资料名称</th>
								<th>份数</th>
								<th>页数</th>
								<th>特殊介质</th>
								
								<shiro:hasPermission name="business:receive:swReceive:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="swReceiveDataList">
						</tbody>
					
					</table>
					<script type="text/template" id="swReceiveDataTpl">//<!--
						<tr id="swReceiveDataList{{idx}}">
							<td class="hide">
								<input id="swReceiveDataList{{idx}}_id" name="swReceiveDataList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
                                <input id="swReceiveDataList{{idx}}_equipmentId" name="swReceiveDataList[{{idx}}].equipmentId" type="hidden" value="{{row.equipmentId}}"/>
								<input id="swReceiveDataList{{idx}}_delFlag" name="swReceiveDataList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
                             <td>
								<input id="swReceiveDataList{{idx}}_equipmentName" name="swReceiveDataList[{{idx}}].equipmentName" type="text" value="{{row.equipmentName}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="swReceiveDataList{{idx}}_name" name="swReceiveDataList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="swReceiveDataList{{idx}}_copies" name="swReceiveDataList[{{idx}}].copies" type="text" value="{{row.copies}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="swReceiveDataList{{idx}}_pages" name="swReceiveDataList[{{idx}}].pages" type="text" value="{{row.pages}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="swReceiveDataList{{idx}}_special" name="swReceiveDataList[{{idx}}].special" type="text" value="{{row.special}}" maxlength="255" class="input-small "/>
							</td>
							
							<shiro:hasPermission name="business:receive:swReceive:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#swReceiveDataList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var swReceiveDataRowIdx = 0, swReceiveDataTpl = $("#swReceiveDataTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(swReceive.swReceiveDataList)};
							for (var i=0; i<data.length; i++){
								addRow('#swReceiveDataList', swReceiveDataRowIdx, swReceiveDataTpl, data[i]);
								swReceiveDataRowIdx = swReceiveDataRowIdx + 1;
							}
						});
					</script>
		
		<div class="form-actions">
			<shiro:hasPermission name="business:receive:swReceive:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="暂 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnSubmitok" class="btn  btn-primary"   type="button"   onclick="ok(1)"  value="提交"/>
			<input id="dayin" class="btn" type="button"  onclick="tt(1)" value="验收单预览"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>