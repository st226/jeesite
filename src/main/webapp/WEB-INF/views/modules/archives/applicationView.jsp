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
		
		function dayin(){
			 $("#dayin").jqprint();
		}
		
	</script>
</head>
<body>
<br/>
	<form:form id="inputForm" modelAttribute="application" action="${ctx}/archives/application/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
	<div id="dayin">
  
          </br>
			<table  border='1' bordercolor='#000000' style='border-collapse:collapse;table-layout:fixed;width: 700px'>
			<caption align="top"  style="font-size: 30px;margin-bottom: 30px" >晒印申请单</caption>
				<tr>
					<td width="13%" height="40px" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>型号：</td>
					<td width="20%"  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>
						${application.busTypeName}
			       
					</td><td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'  width="13%">申请原因：</td><td width="20%">
							${application.applyCour}
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'  width="13%">申请单位：</td><td width="20%">
						${application.office.name}
					</td>
				
				</tr>
				<tr height="40px" >
				<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>计划号：</td><td>
						${application.code}
				
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>申请人：</td><td>
						${application.user.name}
					</td><td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>申请日期：</td><td>
						<fmt:formatDate value="${application.apllyDate}" pattern="yyyy-MM-dd"/>
					
				
					</td>
					
				
					
				</tr>
				<tr height="40px" >
				<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>电话号：</td><td>
						${application.apllyNo}

					</td>
				<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>复印形式：</td><td>
					 ${fns:getDictLabel(application.apllyModel,'APLLY_MODEL', '')}
					
				
					</td>
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'>晒印批组：</td><td>
			
					${fns:getDictLabel(application.apllySypc,'APLLY_SYPC', '')}
	</td>
				</tr>
				
				
			</table>

		

			
					<table   border='1' bordercolor='#000000' style='border-collapse:collapse;table-layout:fixed;width: 700px' >
						<thead>
							<tr height="40px">
								<th class="hide"></th>
								<th>图号</th>
								<th>名称</th>
								<th>密级</th>
								<th>份数</th>
								<th>发往部门</th>
								
						
							</tr>
						</thead>
					<c:forEach items="${application.modelList}" var="testData">
			<tr  height="40px">
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
					${testData.imageNumber}
				</td>
				<td>
					${testData.office.name}
				</td>
		</c:forEach>
						
					</table>
				
		</div>			
			
		
		<div class="form-actions">
		
			<input id="btnSubmit" class="btn" type="button"  onclick="dayin()" value="打 印"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	
		</div>
	</form:form>
</body>
</html>