<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>计量管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					$("input[type=checkbox]").each(function(){
						$(this).after("<input type=\"hidden\" name=\""+$(this).attr("name")+"\" value=\""
								+($(this).attr("checked")?"1":"0")+"\"/>");
						$(this).attr("name", "_"+$(this).attr("name"));
					});
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
		function test(pid){
			top.$.jBox.open("iframe:http://10.193.200.16:8088/default/archives/archives/imageFlow.jsp?id=${sbBorrow.processinstid}", 
					"流程跟踪",810,$(top.document).height()-110,{
						buttons:{"确定":true}, loaded:function(h){
							$(".jbox-content", top.document).css("overflow-y","hidden");
						}
					});
		
			
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/equipment/equipmentbus/sbEquipmentBus/borrowList">流程审批</a></li>
		<li ><a onclick="test(${sbMetering.processinstid})">流程跟踪</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sbMetering" action="${ctx}/equipment/metering/sbMetering/submit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="workItem.processInstID"/>
		<form:hidden path="workItem.workItemID"/>
		<form:hidden path="workItem.workItemName"/>
		<form:hidden path="workItem.processChName"/>
		<form:hidden path="workItem.activityDefID"/>
		<form:hidden id="flag" path="workItem.workItemType"/>
		<sys:message content="${message}"/>	
		<fieldset>
		    <legend >${sbMetering.workItem.workItemName}</legend>	
		<div class="control-group">
			<label class="control-label">说明：</label>
			<div class="controls">
				${sbMetering.field1}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计量发起时间：</label>
			<div class="controls">
				${sbMetering.field2}
			</div>
			
		</div>
			</fieldset>	
			<legend>设备计量详情</legend>
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>设备名称</th>
								<th>规格型号</th>
								<th>设备编号</th>
								<th>出厂编号</th>
								<th>责任部门</th>
								<th>责任人</th>
								<th>计量方式</th>
								<th>检定周期</th>
								<th>最新有效期</th>
								<th>是否可送检</th>
								<th>不能送检原因</th>
								
							
							</tr>
						</thead>
						<tbody id="sbMeteringChildList">
						</tbody>
						
					</table>
					<script type="text/template" id="sbMeteringChildTpl">//<!--
						<tr id="sbMeteringChildList{{idx}}">
							<td class="hide">
								<input id="sbMeteringChildList{{idx}}_id" name="sbMeteringChildList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="sbMeteringChildList{{idx}}_delFlag" name="sbMeteringChildList[{{idx}}].delFlag" type="hidden" value="0"/>
                                <input id="sbMeteringChildList{{idx}}_equipmentId" name="sbMeteringChildList[{{idx}}].equipmentId" type="hidden" value="{{row.equipmentId}}" />
                                <input id="sbMeteringChildList{{idx}}_equipmentName" name="sbMeteringChildList[{{idx}}].equipmentName" type="hidden" value="{{row.equipmentName}}" />
                                <input id="sbMeteringChildList{{idx}}_equipmentType" name="sbMeteringChildList[{{idx}}].equipmentType" type="hidden" value="{{row.equipmentType}}" />
                                <input id="sbMeteringChildList{{idx}}_equipmentSbcode" name="sbMeteringChildList[{{idx}}].equipmentSbcode" type="hidden" value="{{row.equipmentSbcode}}" />
                                <input id="sbMeteringChildList{{idx}}_equipmentCccode" name="sbMeteringChildList[{{idx}}].equipmentCccode" type="hidden" value="{{row.equipmentCccode}}" />
                                <input id="sbMeteringChildList{{idx}}_department" name="sbMeteringChildList[{{idx}}].department" type="hidden" value="{{row.department}}" />
                                 <input id="sbMeteringChildList{{idx}}_departmentid" name="sbMeteringChildList[{{idx}}].departmentid" type="hidden" value="{{row.departmentid}}" />
                                <input id="sbMeteringChildList{{idx}}_person" name="sbMeteringChildList[{{idx}}].person" type="hidden" value="{{row.person}}" />
							</td>
				
							<td>
								{{row.equipmentName}}
							</td>
							<td>
								{{row.equipmentType}}
							</td>
							<td>
								{{row.equipmentSbcode}}
							</td>
							<td>
								{{row.equipmentCccode}}
							</td>
                            <td>
								{{row.department}}
							</td>
                               <td>
								{{row.person}}
							</td>
                             <td>
								
							    <select id="sbMeteringChildList{{idx}}_meteringType" name="sbMeteringChildList[{{idx}}].meteringType" data-value="{{row.meteringType}}" class="input-mini ">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('meteringType')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
                             </td>
                               <td>
								
							    <select id="sbMeteringChildList{{idx}}_meteringTime" name="sbMeteringChildList[{{idx}}].meteringTime" data-value="{{row.meteringTime}}" class="input-mini ">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('meteringTime')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
                             </td>
							
					
							
							<td>
								<input id="sbMeteringChildList{{idx}}_measurement" name="sbMeteringChildList[{{idx}}].measurement" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate "
									value="{{row.measurement}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
							</td>
                             <td>

                                <input id="sbMeteringChildList{{idx}}_isGood" name="sbMeteringChildList[{{idx}}].isGood" type="checkbox" value='1' data-value="{{row.isGood}}"  maxlength="199" class="input-small "/>
							    
                            </td>
                             <td>
								<input id="sbMeteringChildList{{idx}}_reason" name="sbMeteringChildList[{{idx}}].reason" type="text" value="{{row.reason}}" maxlength="19" class="input-small "/>
							</td>
						
							
						</tr>//-->
					</script>
					<script type="text/javascript">
						var sbMeteringChildRowIdx = 0, sbMeteringChildTpl = $("#sbMeteringChildTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(sbMetering.sbMeteringChildList)};
							for (var i=0; i<data.length; i++){
								addRow('#sbMeteringChildList', sbMeteringChildRowIdx, sbMeteringChildTpl, data[i]);
								sbMeteringChildRowIdx = sbMeteringChildRowIdx + 1;
							}
						});
					</script>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存 " onclick="$('#flag').val('N')"/>&nbsp;
			<input id="btnSubmit2" class="btn btn-inverse" type="submit" value="提 交" onclick="$('#flag').val('Y')"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>