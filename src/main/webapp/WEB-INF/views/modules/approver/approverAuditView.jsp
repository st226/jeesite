<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>现行文件申请</title>
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
		
		function test(pid){
			top.$.jBox.open("iframe:http://10.193.200.16:8088/default/archives/archives/imageFlow.jsp?id=${tsApprover.processinstid}", 
					"流程跟踪",810,$(top.document).height()-110,{
						buttons:{"确定":true}, loaded:function(h){
							$(".jbox-content", top.document).css("overflow-y","hidden");
						}
					});
		
			
		}
		
		function oook(){
			var oook = "{"+$("#inputForm").serialize()+"}";
			alert(oook);
			
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/approver/tsApprover/form?id=${application.id}">流程审批<shiro:hasPermission name="archives:application:edit">${not empty application.id?'':''}</shiro:hasPermission><shiro:lacksPermission name="archives:application:edit"></shiro:lacksPermission></a></li>
	    <li><a onclick="test(${tsApprover.processinstid})">流程跟踪</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tsApprover" action="${ctx}/approver/tsApprover/submit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="workItem.processInstID"/>
		<form:hidden path="workItem.workItemID"/>
		<form:hidden path="workItem.workItemName"/>
		<form:hidden path="workItem.processChName"/>
		<form:hidden path="workItem.activityDefID"/>
		<form:hidden id="flag" path="workItem.workItemType"/>
		<sys:message content="${message}"/>	
		<fieldset>
		<legend >${tsApprover.workItem.workItemName}</legend>
			<table class="table-form">
				<tr>
					<td class="tit" width="20%">申请部门：</td><td  width="30%">
						${tsApprover.applicantDeptName}
			       
					</td>
					<td class="tit"  width="20%">申请人：</td><td  width="30%">
						${tsApprover.applicantName}
					</td>
				
				</tr>
				<tr>
				<td class="tit">申请型号：</td><td>
						${tsApprover.typeName}
				
					</td>
					<td class="tit">申请日期：</td><td>
						${tsApprover.applicantDate}
					
				
					</td>
					
				
					
				</tr>
				<tr>
				<td class="tit">题名：</td><td colspan="3">
						${tsApprover.name}
				
					</td>
				
					
				</tr>
				
				
			</table>
		</fieldset>	
		
		

					<legend >审批详情</legend>
					<table class="table-form">
						<c:forEach items="${tsApprover.examineList}" var="testData">
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
							<td>
								<input id="modelList{{idx}}_imagePagea4Number" name="modelList[{{idx}}].imagePagea4Number" type="text" value="{{row.imagePagea4Number}}" maxlength="20" class="input-small required digits"/>
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

			<input id="btnSubmit" class="btn btn-primary" type="submit" value="确认" onclick="$('#flag').val('Y')"/>&nbsp;

			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>