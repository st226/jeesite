<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图册资源维护管理</title>
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
	</script>
</head>
<body>
	
	<form:form id="inputForm" modelAttribute="sbBorrowChild" action="${ctx}/borrow/sbborrow/sbBorrow/saveBorrowInfo" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="office.id"/>
		<form:hidden path="user.id"/>
		<form:hidden path="equipmentId"/>
		<form:hidden path="equipmentName"/>
		<form:hidden path="equipmentSbcode"/>
		<form:hidden path="ismeasurement"/>
		<form:hidden path="measurement"/>
		<form:hidden path="equipmentCccode"/>
		<form:hidden path="equipmentType"/>
		<sys:message content="${message}"/>		
		</br>
		<table class="table-form">
				<tr>
					<td class="tit">借用部门：</td><td>
						${sbBorrowChild.office.name}
			       
					</td><td class="tit">借用人：</td><td>
							${sbBorrowChild.user.name}
					</td>
					
				
				</tr>
				<tr>
					<td class="tit">借用设备名称：</td><td>
						${sbBorrowChild.equipmentName}
			       
					</td><td class="tit">借用设备编号：</td><td>
							${sbBorrowChild.equipmentSbcode}
					</td>
					
				
				</tr>
				<tr>
					<td class="tit">设备出厂编号：</td><td>
						${sbBorrowChild.equipmentCccode}
			       
					</td><td class="tit">借用设备型号：</td><td>
							${sbBorrowChild.equipmentType}
					</td>
					
				
				</tr>
				<tr>
					<td class="tit">是否计量：</td><td>
						${sbBorrowChild.ismeasurement}
			       
					</td><td class="tit">计量日期：</td><td>
							${sbBorrowChild.measurement}
					</td>
					
				
				</tr>
				<tr>
					<td class="tit">拟借用日期：</td><td>
						<input name="endDate" type="text" readonly="readonly" maxlength="20"   class="input-medium Wdate required"
					value="<fmt:formatDate value="${sbBorrowChild.endDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			       
					</td><td class="tit">是否完好：</td><td>
							<form:checkboxes path="isGood" items="${fns:getDictList('is_leaf')}"  value='1' itemLabel="label" itemValue="value" htmlEscape="false"/>
					</td>
					
				
				</tr>
				<tr>
					<td class="tit">意见：</td><td colspan="3">
						<form:textarea path="reason" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			       
					</td>
					
				
				</tr>
				
			</table>
		
		
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>