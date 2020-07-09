<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采购任务管理</title>
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
			
			newDate();
		});
		
		function tt(){
			var amountYs = parseFloat('${swOrder.amountYs}');
			var da = $("#gmDate").val() ;
			var da1 = parseInt('${dictList[0].remarks}');
			var da2 = parseInt('${dictList[1].remarks}')+da1;
			var da3 = parseInt('${dictList[2].remarks}')+da2;
			if(amountYs<200000 || type=='1'){
				da3=da1+parseInt('${dictList[2].remarks}');
			}
			var da4 = parseInt('${dictList[3].remarks}')+da3;
			if(type=='2'){
				da4=da1+parseInt('${dictList[3].remarks}');
			}
			var da5 = parseInt('${dictList[4].remarks}')+da4;

			$("#field1Date").val(datetostring(da,da1));
			$("#field2Date").val(datetostring(da,da2));
			$("#field3Date").val(datetostring(da,da3));
			$("#field5Date").val(datetostring(da,da4));
			$("#field6Date").val(datetostring(da,da5));
		}
		function tt1(){
			var amountYs = parseFloat('${swOrder.amountYs}');
			var da = $("#field1Date").val() ;
			var da2 = parseInt('${dictList[1].remarks}');
			var da3 = parseInt('${dictList[2].remarks}')+da2;
			if(amountYs<200000 || type=='1'){
				da3=da2+parseInt('${dictList[2].remarks}');
			}
			var da4 = parseInt('${dictList[3].remarks}')+da3;
			if(type=='2'){
				da4=da2+parseInt('${dictList[3].remarks}');
			}
			var da5 = parseInt('${dictList[4].remarks}')+da4;


			$("#field2Date").val(datetostring(da,da2));
			$("#field3Date").val(datetostring(da,da3));
			$("#field5Date").val(datetostring(da,da4));
			$("#field6Date").val(datetostring(da,da5));
		}
		function tt2(){
			var da = $("#field2Date").val() ;	
			var da3 = parseInt('${dictList[2].remarks}');
			var da5 = parseInt('${dictList[3].remarks}')+da3;
			var da6 = parseInt('${dictList[4].remarks}')+da5;
			$("#field3Date").val(datetostring(da,da3));
			$("#field5Date").val(datetostring(da,da5));
			$("#field6Date").val(datetostring(da,da6));
		}
		function tt3(){
			var da = $("#field3Date").val() ;	
			var da5 = parseInt('${dictList[3].remarks}');
			var da6 = parseInt('${dictList[4].remarks}')+da5;
			$("#field5Date").val(datetostring(da,da5));
			$("#field6Date").val(datetostring(da,da6));
		}
		function tt4(){
			var da = $("#field5Date").val() ;	
			var da6 = parseInt('${dictList[4].remarks}');
			$("#field6Date").val(datetostring(da,da6));
		}
		
		function datetostring(day,num){
			var d = new Date(day);

			d = d.setDate(d.getDate()+num)
			d = new Date(d);

			return d.toISOString().split("T")[0];
		}
		
		var  type;
		
		function orderType(e){
			type=e.value;
			document.getElementById("field1Date").style.display="" ;
			document.getElementById("field2Date").style.display="" ;
			document.getElementById("field3Date").style.display="" ;
			document.getElementById("field5Date").style.display="" ;
			document.getElementById("field6Date").style.display="" ;
			var amountYs = parseFloat('${swOrder.amountYs}');
			if(e.value=='1'||amountYs<200000){
            	document.getElementById("field2Date").style.display="none" ;
		    }
		    if(e.value=='2'){
		    	document.getElementById("field2Date").style.display="none" ;
		    	document.getElementById("field3Date").style.display="none" ;
		    }
            
		}
		
		function newDate(){
			var amountYs = parseFloat('${swOrder.amountYs}');
			if(amountYs<200000){
				document.getElementById("field2Date").style.display="none" ;
				
			}
		}
		
	</script>
</head>
<body>
	<br/>
	<form:form id="inputForm" modelAttribute="swOrder" action="${ctx}/business/order/swOrder/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="field1"/>
		<form:hidden path="projectType"/>
		<form:hidden path="identification"/>
		<sys:message content="${message}"/>	
		
		<table class="table-form">
				<tr>
					<td class="tit" width="15%">项目名称：</td><td width="35%">
							<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">采购人：</td><td width="35%">
						<sys:treeselect id="user" name="user.id" value="${swOrder.user.id}" labelName="userName" labelValue="${swOrder.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">是否特殊项目：</td><td width="35%">
							
							<form:radiobuttons path="type"  items="${fns:getDictList('order_type')}" itemLabel="label" itemValue="value" htmlEscape="false" onchange="orderType(this)"  class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					<td class="tit" width="15%">调研人：</td><td width="35%">
						<sys:treeselect id="dyuserId" name="dyuserId" value="${swOrder.dyuserId}" labelName="dyuserName" labelValue="${swOrder.dyuserName}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>

					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">调研金额：</td><td width="35%" >
							<form:input path="amountYs" htmlEscape="false" maxlength="255" class="input-xlarge number" />
				<span class="help-inline"><font color="red">*</font> </span>
					
				<td class="tit" width="15%">预算金额：</td><td width="35%" >
							<form:input path="amount" htmlEscape="false" maxlength="255" class="input-xlarge " readonly="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
					
				
					
				</tr>
				<tr>
					<td class="tit" width="15%">采购起始时间：</td><td width="35%">
						<input id="gmDate" name="gmDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${swOrder.gmDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" onchange="tt()" />
				                <span class="help-inline"><font color="red">*</font> </span>
					</td>
					<td class="tit" width="15%">调研/特殊项目截止日期：</td><td width="35%">
							<input id="field1Date" name="field1Date" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${swOrder.field1Date}" pattern="yyyy-MM-dd"  />"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"  onchange="tt1()" />
				   
					</td>
					
					
				</tr>
				<tr>
				<td class="tit" width="15%">招投标截止日期：</td><td width="35%">
							<input id="field2Date" name="field2Date" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${swOrder.field2Date}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"  onchange="tt2()" />
				     
					</td>	
				
					<td class="tit" width="15%">谈判截止日期：</td><td width="35%">
							<input id="field3Date"  name="field3Date" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${swOrder.field3Date}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"  onchange="tt3()" />
			
					</td>
					
				
					
				</tr>
				<tr>
				<td class="tit" width="15%">合同/申购单截止日期：</td><td width="35%">
							<input  id="field5Date"  name="field5Date" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${swOrder.field5Date}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"  onchange="tt4()" />
		
					</td>	
					<td class="tit" width="15%">付款起始日期：</td><td width="35%">
							<input  id="field6Date"  name="field6Date" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${swOrder.field6Date}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
		
					</td>
				
					
				</tr>
				
		</table>	
		
	
		
	</form:form>
</body>
</html>