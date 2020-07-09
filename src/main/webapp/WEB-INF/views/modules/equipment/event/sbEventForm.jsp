<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>借用事件管理</title>
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
		<li><a href="${ctx}/borrow/sbborrow/sbBorrow/MyBorrow">我的借用</a></li>
		<li class="active"><a href="${ctx}/equipment/event/sbEvent/form?id=${sbEvent.id}">设备续借<shiro:hasPermission name="equipment:event:sbEvent:edit">${not empty sbEvent.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="equipment:event:sbEvent:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sbEvent" action="${ctx}/equipment/event/sbEvent/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="processinstid"/>
		<form:hidden path="state"/>
		<form:hidden path="type" value='event'/>
		<sys:message content="${message}"/>		
		
	
		<div class="control-group">
			<label class="control-label">借用部门：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id" value="${sbEvent.office.id}" labelName="office.name" labelValue="${sbEvent.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借用人：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${sbEvent.user.id}" labelName="user.name" labelValue="${sbEvent.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借用到时间：</label>
			<div class="controls">
				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sbEvent.endDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
	
		<div class="control-group">
			<label class="control-label">说明：</label>
			<div class="controls">
				<form:textarea path="cause" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge "/>
			</div>
		</div>
		<legend>续借设备详情</legend>
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>设备名称</th>
								<th>规格型号</th>
								<th>设备编号</th>
								<th>计量有效期</th>
								<th>是否计量</th>
							
								
								
								<shiro:hasPermission name="equipment:event:sbEvent:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="sbEventChildList">
						</tbody>
						
					</table>
					<script type="text/template" id="sbEventChildTpl">//<!--
						<tr id="sbEventChildList{{idx}}">
							<td class="hide">
								<input id="sbEventChildList{{idx}}_id" name="sbEventChildList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
                                <input id="sbEventChildList{{idx}}_field1" name="sbEventChildList[{{idx}}].field1" type="hidden" value="{{row.field1}}"/>
								<input id="sbEventChildList{{idx}}_delFlag" name="sbEventChildList[{{idx}}].delFlag" type="hidden" value="0"/>
                                 <input id="sbEventChildList{{idx}}_equipmentId" name="sbEventChildList[{{idx}}].equipmentId" type="hidden" value="{{row.equipmentId}}"/>
                                  <input id="sbEventChildList{{idx}}_equipmentName" name="sbEventChildList[{{idx}}].equipmentName" type="hidden" value="{{row.equipmentName}}"/>
                              <input id="sbEventChildList{{idx}}_equipmentType" name="sbEventChildList[{{idx}}].equipmentType" type="hidden" value="{{row.equipmentType}}"/>
                             <input id="sbEventChildList{{idx}}_equipmentSbcode" name="sbEventChildList[{{idx}}].equipmentSbcode" type="hidden" value="{{row.equipmentSbcode}}"/>
                             <input id="sbEventChildList{{idx}}_ismeasurement" name="sbEventChildList[{{idx}}].ismeasurement" type="hidden" value="{{row.ismeasurement}}"/>
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
								<td>{{row.measurement}}
								<input id="sbBorrowChildList{{idx}}_measurement" name="sbBorrowChildList[{{idx}}].measurement" type="hidden" readonly="readonly" maxlength="20" class="input-medium Wdate "
									value="{{row.measurement}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
							</td>
							<td>
								{{row.ismeasurement}}
							</td>
							
						
						
							<shiro:hasPermission name="equipment:event:sbEvent:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#sbEventChildList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var sbEventChildRowIdx = 0, sbEventChildTpl = $("#sbEventChildTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(sbEvent.sbEventChildList)};
							for (var i=0; i<data.length; i++){
								addRow('#sbEventChildList', sbEventChildRowIdx, sbEventChildTpl, data[i]);
								sbEventChildRowIdx = sbEventChildRowIdx + 1;
							}
						});
					</script>
		<div class="form-actions">
			<shiro:hasPermission name="equipment:event:sbEvent:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="提交"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>