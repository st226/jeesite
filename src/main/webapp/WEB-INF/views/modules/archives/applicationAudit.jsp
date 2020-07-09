<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>现行文件管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/homePage/bootstrap_admin/scripts/jquery.jqprint-0.3.js" type="text/javascript"></script>
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
			top.$.jBox.open("iframe:http://10.193.200.16:8088/default/archives/archives/imageFlow.jsp?id=${application.processinstid}", 
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
		
		function tt(id){
		
			window.location.href="${ctx}/archives/application/view?id=${application.id}" ;
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/archives/application/form?id=${application.id}">流程审批<shiro:hasPermission name="archives:application:edit">${not empty application.id?'':''}</shiro:hasPermission><shiro:lacksPermission name="archives:application:edit"></shiro:lacksPermission></a></li>
	    <li><a onclick="test(${application.processinstid})">流程跟踪</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="application" action="${ctx}/archives/application/submit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="workItem.processInstID"/>
		<form:hidden path="workItem.workItemID"/>
		<form:hidden path="workItem.workItemName"/>
		<form:hidden path="workItem.processChName"/>
		<form:hidden path="workItem.activityDefID"/>
		<form:hidden id="flag" path="workItem.workItemType"/>
		<sys:message content="${message}"/>	
		<fieldset>
		<legend >${application.workItem.workItemName}</legend>
			<table class="table-form">
				<tr>
					<td class="tit" width="10%">型号：</td><td width="23%">
						${application.busTypeName}
			       
					</td><td class="tit"  width="10%">申请原因：</td><td width="23%">
							${application.applyCour}
					</td>
					<td class="tit"  width="10%">申请单位：</td><td width="23%">
						${application.office.name}
					</td>
				
				</tr>
				<tr>
				<td class="tit">计划号：</td><td>
						${application.code}
				
					</td>
					<td class="tit">申请人：</td><td>
						${application.user.name}
					</td><td class="tit">申请日期：</td><td>
						<fmt:formatDate value="${application.apllyDate}" pattern="yyyy-MM-dd"/>
					
				
					</td>
					
				
					
				</tr>
				<tr>
				<td class="tit">电话号：</td><td>
						${application.apllyNo}

					</td>
				<td class="tit">复印形式：</td><td>
					 ${fns:getDictLabel(application.apllyModel,'APLLY_MODEL', '')}
					
				
					</td>
					<td class="tit">晒印批组：</td><td>
			
					${fns:getDictLabel(application.apllySypc,'APLLY_SYPC', '')}
	</td>
				

					
				</tr>
				
			</table>
		</fieldset>	
		
		
<legend ></legend>
			
				
			
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>序号</th>
								<th width="20%">图号</th>
								<th>名称</th>
								<th>密级</th>
								<th>阶段</th>
								<th>份数</th>
								<th>发往部门</th>
							
								
							</tr>
						</thead>
					
						
				<c:forEach items="${application.modelList}" var="testData">
			<tr>
			<td>
					${testData.syIndex}
				</td>
				<td>
					${testData.imageNo}
				</td>
				<td>
					${testData.imagePageno}
				</td>
				<td>
					${fns:getDictLabel(testData.modelClass,'MODEL_CLASS', '')}
				</td>
				<td>
					${fns:getDictLabel(testData.syJdbj,'yzgc', '')}
				</td>
				<td>
					${testData.imageNumber}
				</td>
				<td>
					${testData.officeName}
				</td>
				
				
				
			
			
			</tr>
		</c:forEach>
	
					</table>
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
					
					
				
				
			
				
				
				<tr>
					<td class="tit">您的意见</td>
					<td colspan="6">
						<form:textarea path="applyTypename" class="required" rows="5" maxlength="20" cssStyle="width:500px"/>
					</td>
				</tr></table>
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

			<input id="btnSubmit" class="btn btn-primary" type="submit" value="同 意" onclick="$('#flag').val('Y')"/>&nbsp;
			<input id="btnSubmit" class="btn btn-inverse" type="submit" value="驳 回" onclick="$('#flag').val('N')"/>&nbsp;
			<input id="dayin" class="btn" type="button"  onclick="tt(1)" value="打印预览"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>