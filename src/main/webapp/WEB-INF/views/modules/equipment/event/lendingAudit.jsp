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
		 <li class="active"><a href="${ctx}/equipment/equipmentbus/sbEquipmentBus/borrowList">流程审批</a></li>
		<li ><a onclick="test(${sbBorrow.processinstid})">流程跟踪</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sbEvent" action="${ctx}/equipment/event/sbEvent/submitLending" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="processinstid"/>
		<form:hidden path="state"/>
		<form:hidden path="workItem.processInstID"/>
		<form:hidden path="workItem.workItemID"/>
		<form:hidden path="workItem.workItemName"/>
		<form:hidden path="workItem.processChName"/>
		<form:hidden path="workItem.activityDefID"/>
		<form:hidden id="flag" path="workItem.workItemType"/>
		<sys:message content="${message}"/>		
		
	    <fieldset>
		    <legend >${sbEvent.workItem.workItemName}</legend>
		    <table class="table-form">
				
				
				<tr>
					<td class="tit" width="20%"> 借用部门：</td><td  width="30%">
						${sbEvent.office.name}
					</td><td class="tit"  width="20%">借用人：</td><td  width="30%">
						${sbEvent.user.name}
					</td>
				</tr>
				<tr>
					<td class="tit">拟借用至：</td><td>
					<fmt:formatDate value="${sbEvent.endDate}" pattern="yyyy-MM-dd"/>
					
					</td><td class="tit">说明：</td><td>
					${sbEvent.cause}
						

					</td>
				</tr>
				
				
				
			</table>
		
		</fieldset>	
		<legend>转借设备详情</legend>
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>设备名称</th>
								<th>规格型号</th>
								<th>设备编号</th>
								
								<th>是否计量</th>
							<th>计量有效期</th>
							
							</tr>
						</thead>
					<c:forEach items="${sbEvent.sbEventChildList}" var="testData">
			<tr>
				<td>
					${testData.equipmentName}
				</td>
				<td>
					${testData.equipmentType}
				</td>
				<td>
					${testData.equipmentSbcode}
				</td>
				
				<td>
					${testData.ismeasurement}
				</td>
				<td>
					 <fmt:formatDate value="${testData.measurement}" pattern="yyyy-MM-dd"/>
				</td>
				
			</tr>
		</c:forEach>
						
					</table>
					<legend >审批详情</legend>
					<table class="table-form">
						<c:forEach items="${sbEvent.examineList}" var="testData">
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
		<tr>
					<td class="tit">您的意见</td>
					<td colspan="6">
						<form:textarea path="field1" class="required" rows="5" maxlength="20" cssStyle="width:500px"/>
					</td>
				</tr></table>
					
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="同 意" onclick="$('#flag').val('Y')"/>&nbsp;
			<input id="btnSubmit2" class="btn btn-inverse" type="submit" value="驳 回" onclick="$('#flag').val('N')"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>