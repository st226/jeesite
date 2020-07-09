<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>现行文件管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			
			
		});
		
		

		
	</script>
</head>
<body >
	
	<form:form id="inputForm" modelAttribute="sbBorrowChild" method="post" class="form-horizontal">
		<form:hidden path="id"/>

		 <fieldset>
		<legend ></legend>
		<table class="table-form">
				<tr>
					<td class="tit" width="20%">设备名称：</td><td width="30%">
						
			       ${sbBorrowChild.equipmentName}
					</td><td class="tit" width="20%">设备类型：</td><td width="30%">
					${sbBorrowChild.equipmentType}
					</td>
				</tr>
				<tr>
					<td class="tit">设备编号：</td><td>
						
			       ${sbBorrowChild.equipmentSbcode}
					</td><td class="tit">设备出厂编号：</td><td>
					${sbBorrowChild.equipmentCccode}
			       
					</td>
				</tr>
				<tr>
					<td class="tit">借用部门：</td><td>
						${sbBorrowChild.office.name}
			       
					</td><td class="tit">借用人：</td><td>
					${sbBorrowChild.user.name}
			       
					</td>
				</tr>
				<tr>
					<td class="tit">借用日期：</td><td>
						<fmt:formatDate value="${sbBorrowChild.statrDate}" pattern="yyyy-MM-dd"/>
			       
					</td><td class="tit">归还日期：</td><td>
					<fmt:formatDate value="${sbBorrowChild.endDate}" pattern="yyyy-MM-dd"/>
			      
					</td>
				</tr>
				
				
		</table>
         
			</fieldset>	
		
	
		
		
		
		
	
	</form:form>
</body>
</html>