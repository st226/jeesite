<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>三单维护管理</title>
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
		<li><a href="${ctx}/sdarchives/sdApplication/">三单接受</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sdApplication" action="${ctx}/sdarchives/sdApplication/submit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="sdType" value='pl'/>
		<form:hidden path="workItem.processInstID"/>
		<form:hidden path="workItem.workItemID"/>
		<form:hidden path="workItem.workItemName"/>
		<form:hidden path="workItem.processChName"/>
		<form:hidden path="workItem.activityDefID"/>
		<form:hidden id="flag" path="workItem.workItemType"/>
		<sys:message content="${message}"/>		
		<fieldset>
				<legend >更改单接受</legend>
			<table class="table-form">
			<tr>
					<td class="tit">设计人员：</td><td>
					${sdApplication.applyPersonsj}
						
					</td>
					<td class="tit">申请部门：</td><td>
					${sdApplication.office.name}
						
					</td>
				</tr>
			
			<tr>
			<td class="tit">申请时间：</td><td>
			<fmt:formatDate value="${sdApplication.apllyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
						
					</td>
					
				
					<td class="tit">申请人：</td><td>
					${sdApplication.user.name}
						
					
				</tr>
				
			</table>
		</fieldset>	
			<legend></legend>
				
			
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
		
						<thead>
							<tr>
								<th class="hide"></th>
								<th>型号</th>
								<th>标记阶段</th>
								<th>密级</th>
								<th>更改单号</th>
								<th>发送单位</th>
								<th>使用性</th>
								<th>更改标记</th>
								<th>更改原因</th>
								<th>制品处理意见</th>
								<th>归档人</th>
								<th>归档日期</th>
								<th>批准日期</th>
								
						
							</tr>
						</thead>
						<c:forEach items="${sdApplication.sdModelList}" var="testData">
			<tr>
			<td>
					${testData.modelName}
				</td>
			
			   <td>
					${fns:getDictLabel(testData.bjjd,'bjjd', '')}
				</td>
				<td>
					${fns:getDictLabel(testData.modelClass,'MODEL_CLASS', '')}
				</td>
				<td>
					${testData.sdNumber}
				</td>
				<td>
					${testData.officeName}
				</td>
				<td>
					${testData.syx}
				</td>
				<td>
					${fns:getDictLabel(testData.ggbj,'ggbj', '')}
				</td>
				<td>
					${testData.ggyy}
				</td>
				
				
				<td>
					${testData.clyj}
				</td>
				<td>
					<fmt:formatDate value="${testData.pzrq}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${testData.gdr}
				</td>
				<td>
					<fmt:formatDate value="${testData.gdrq}" pattern="yyyy-MM-dd"/>
				</td>
			
			
			</tr>
		</c:forEach>
					
						
					</table>
					
					<script type="text/javascript">
						var sdModelRowIdx = 0, sdModelTpl = $("#sdModelTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						
					</script>
			
		<div class="form-actions">
			<shiro:hasPermission name="sdarchives:sdApplication:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="接受"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>