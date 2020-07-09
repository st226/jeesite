<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>现行文件管理</title>
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
		<li class="active"><a href="${ctx}/archives/application/form?id=${application.id}">流程审批<shiro:hasPermission name="archives:application:edit">${not empty application.id?'':''}</shiro:hasPermission><shiro:lacksPermission name="archives:application:edit"></shiro:lacksPermission></a></li>
	    <li><a href="http://127.0.0.1:8088/default/archives/archives/imageFlow.jsp?id=${application.processinstid}">详情</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="application" action="${ctx}/archives/application/edit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="workItem.processInstID"/>
		<form:hidden path="workItem.workItemID"/>
		<form:hidden path="workItem.workItemName"/>
		<form:hidden path="workItem.processChName"/>
		<form:hidden path="workItem.activityDefID"/>
		<form:hidden id="flag" path="workItem.workItemType"/>
		<sys:message content="${message}"/>	
		<fieldset>
			<table class="table-form">
				<tr>
					<td class="tit">型号：</td><td>
						<sys:treeselect id="busType" name="busType" value="${application.busType}" 
				labelName="busTypeName" labelValue="${application.busTypeName}"
					title="选择型号/设备" url="/resourcetype/tsResourceType/treeData" cssClass="required" allowClear="true"/>     
		
			       <span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit">申请原因：</td><td>
						<form:input path="applyCour" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
				</tr>
				
				<tr>
					<td class="tit">申请单位：</td><td>
						<sys:treeselect id="office" name="office.id" value="${application.office.id}" labelName="office.name" labelValue="${application.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="required" allowInput="false" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit">计划号：</td><td>
						<form:input path="code" htmlEscape="false" maxlength="55" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
				</tr>
				<tr>
					<td class="tit">申请人：</td><td>
						<sys:treeselect id="user" name="user.id" value="${application.user.id}" labelName="user.name" labelValue="${application.user.name}"
					title="用户" url="/sys/office/treeData?type=3"  allowInput="false"  cssClass="required" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>

					</td><td class="tit">申请日期：</td><td>
						<input name="apllyDate" type="text" readonly="readonly"  allowInput="false"  maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${application.apllyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
				</tr>
				<tr>
					<td class="tit">电话号：</td><td>
						<form:input path="apllyNo" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>

					</td><td class="tit">复印形式：</td><td>
						<form:select path="apllyModel" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('APLLY_MODEL')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
				</tr>
				<tr>
					<td class="tit">晒印批组：</td><td>
						<form:select path="apllySypc" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('APLLY_SYPC')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>

					
				</tr>
				
			</table>
		</fieldset>	
		
		
<legend ></legend>
			
				
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>序号</th>
								<th>图号</th>
								<th>名称</th>
								<th>密级</th>
								<th>阶段</th>
								<th>份数</th>
								<th>发往部门</th>
								<shiro:hasPermission name="archives:application:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="modelList">
						</tbody>
						<shiro:hasPermission name="archives:application:edit"><tfoot>
							<tr><td colspan="9"><a href="javascript:" onclick="addRow('#modelList', modelRowIdx, modelTpl);modelRowIdx = modelRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="modelTpl">//<!--
						<tr id="modelList{{idx}}">
							<td class="hide">
								<input id="modelList{{idx}}_id" name="modelList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="modelList{{idx}}_delFlag" name="modelList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
                             <td>
								<input id="modelList{{idx}}_syIndex" name="modelList[{{idx}}].syIndex" type="text" value="{{row.syIndex}}" maxlength="500" class="input-small required"/>
							</td>
							<td>
								<input id="modelList{{idx}}_imageNo" name="modelList[{{idx}}].imageNo" type="text" value="{{row.imageNo}}" maxlength="500" class="input-small required"/>
							</td>
							<td>
								<input id="modelList{{idx}}_imagePageno" name="modelList[{{idx}}].imagePageno" type="text" value="{{row.imagePageno}}" maxlength="55" class="input-small required"/>
							</td>
							<td>
								<select id="modelList{{idx}}_modelClass" name="modelList[{{idx}}].modelClass" data-value="{{row.modelClass}}" class="input-small required">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('MODEL_CLASS')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
                                <td>
								<select id="modelList{{idx}}_syJdbj" name="modelList[{{idx}}].syJdbj" data-value="{{row.syJdbj}}" class="input-small required">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('yzgc')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input id="modelList{{idx}}_imageNumber" name="modelList[{{idx}}].imageNumber" type="text" value="{{row.imageNumber}}" maxlength="20" class="input-small required digits"/>
							</td>
							<td>
								<sys:treeselect id="modelList{{idx}}_office" name="modelList[{{idx}}].office.id" value="{{row.office.id}}" labelName="modelList[{{idx}}].officeName" labelValue="{{row.officeName}}"
									title="部门" url="/sys/office/treeData?type=4"  cssClass="required" allowClear="true" notAllowSelectParent="true"/>
							</td>
							
							<shiro:hasPermission name="archives:application:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#modelList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					
					<legend >审批详情</legend>
					<table class="table-form">
						<c:forEach items="${application.examineList}" var="testData">
			<tr>
					<td  width="10%" class="tit" rowspan="2">${testData.examineExamineisagree}</td>
					
					<td  colspan="6">
						${testData.examineExplain}
					</td>
				</tr>
				<tr>
					
					<td colspan="2"  width="20%">
						${testData.examinePerson}
					</td>
					
					<td colspan="2"  width="20%">
						<fmt:formatDate value="${testData.examineDate}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
		</c:forEach>
					
					
				
				
			
				
				
				</table>
					<script type="text/template" id="modelTpl">//<!--
						<tr id="modelList{{idx}}">
							<td class="hide">
								<input id="modelList{{idx}}_id" name="modelList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="modelList{{idx}}_delFlag" name="modelList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="modelList{{idx}}_imageNo" name="modelList[{{idx}}].imageNo" type="text" value="{{row.imageNo}}" maxlength="500" class="input-small required"/>
							</td>
							<td>
								<input id="modelList{{idx}}_imagePageno" name="modelList[{{idx}}].imagePageno" type="text" value="{{row.imagePageno}}" maxlength="55" class="input-small required"/>
							</td>
							<td>
								<select id="modelList{{idx}}_modelClass" name="modelList[{{idx}}].modelClass" data-value="{{row.modelClass}}" class="input-small required">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('MODEL_CLASS')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input id="modelList{{idx}}_imageNumber" name="modelList[{{idx}}].imageNumber" type="text" value="{{row.imageNumber}}" maxlength="20" class="input-small required digits"/>
							</td>
							<td>
								<sys:treeselect id="modelList{{idx}}_office" name="modelList[{{idx}}].office.id" value="{{row.office.id}}" labelName="modelList{{idx}}.office.name" labelValue="{{row.office.name}}"
									title="部门" url="/sys/office/treeData?type=2" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
							</td>
							<td>
								<input id="modelList{{idx}}_imagePageNumber" name="modelList[{{idx}}].imagePageNumber" type="text" value="{{row.imagePageNumber}}" maxlength="20" class="input-small required digits"/>
							</td>
							
							
						</tr>//-->
					</script>
					<script type="text/javascript">
						var modelRowIdx = 0, modelTpl = $("#modelTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(application.modelList)};
							for (var i=0; i<data.length; i++){

								addRow('#modelList', modelRowIdx, modelTpl, data[i]);
								modelRowIdx = modelRowIdx + 1;
							}
						});
					</script>
			
		
		<div class="form-actions">

			<input id="btnSubmit" class="btn btn-primary" type="submit" value="提交" onclick="$('#flag').val('Y')"/>&nbsp;
			
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>