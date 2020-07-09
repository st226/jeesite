<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备仪器报废（闲置）申请管理</title>
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
			
			
			$("#relationButton").click(function(){
				
				
				
				  top.$.jBox.open("iframe:${ctx}/equipment/equipmentbus/sbEquipmentBus/selectEquipment?pageSize=8","选择设备", 1150, 500, {
			            buttons:{"确认":"ok", "关闭":true},
			            submit:function(v, h, f){
			                if(v=="ok"){
			              //      var data=h.find("iframe")[0].contentWindow.$("#inputForm").serialize();
			                    var i=0;
			    	            var j=0;
			    	            h.find("iframe")[0].contentWindow.$("input[name*='checkbox_name']").each(function () {
			    	                if ($(this).attr("checked") == 'checked') {
			    	                    var name = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].name']").val();
			    	                    var type = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].type']").val();
			    	                    var amount = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].field2']").val();
			    	                    var made = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].made']").val();
			    	                    var id =  h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].id']").val();
			    	                    
			    	                    var sbcode = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].sbcode']").val();
			    	                    var outtime = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].outtime']").val();
			    	                    var price = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].price']").val();
			    	                    var buytime = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].buytime']").val();
			    	                    var team =  h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].team']").val();
			    	                    var field8 = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].field8']").val();
			    	                    var starttime = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].starttime']").val();
			    	                   
			    	                    $("#equipmentId").val(id);
			    	                    $("#equipmentName").val(name);
			    	                    $("#equipmentNorms").val(type);
			    	                    $("#equipmentMade").val(made);
			    	                    
			    	                    $("#equipmentCode").val(sbcode);
			    	                    $("#equipmentBuytime").val(outtime);
			    	                    $("#equipmentYprice").val(price);
			    	                    $("#equipmentOuttime").val(buytime);
			    	                    $("#equipmentTeamName").val(team);
			    	                    $("#equipmentType").val(field8);
			    	                    $("#equipmentStarttime").val(starttime);
			    	                  
			    	                    j++;
			    	                }
			    	                i++;
			    	            });
			    	     
			    	     //       window.parent.page();                 //调用父窗体方法，当关闭子窗体刷新父窗体
                            window.parent.window.jBox.close();    //关闭子窗体
			                    
			                }
			            },
			            loaded:function(h){
			                $(".jbox-content", top.document).css("overflow-y","hidden");
			            }
			        });
				
				
			});
			
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/equipment/scrap/sbEquipmentScrap/">设备仪器报废（闲置）申请列表</a></li>
		<li class="active"><a href="${ctx}/equipment/scrap/sbEquipmentScrap/form?id=${sbEquipmentScrap.id}">设备仪器报废（闲置）申请<shiro:hasPermission name="equipment:scrap:sbEquipmentScrap:edit">${not empty sbEquipmentScrap.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="equipment:scrap:sbEquipmentScrap:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sbEquipmentScrap" action="${ctx}/equipment/scrap/sbEquipmentScrap/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="equipmentId"/>
		<sys:message content="${message}"/>	
		<table class="table-form">
				<tr>
					<td class="tit" width="15%">申请部门：</td><td width="35%">
						<sys:treeselect id="office" name="office.id" value="${sbEquipmentScrap.office.id}" labelName="officeName" labelValue="${sbEquipmentScrap.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">申请日期：</td><td width="35%">
						<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sbEquipmentScrap.createDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">设备名称：</td><td width="35%">
						<form:input path="equipmentName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
						<a id='relationButton' href='javascript:' class='btn' style=''>&nbsp;<i class='icon-search'></i>&nbsp;</a>
				<span class="help-inline"><font color="red">*</font> </span></td>
				<td class="tit" width="15%">国别厂家：</td><td width="35%">
						<form:input path="equipmentMade" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span></td>
				</tr>
				<tr>
					<td class="tit" width="15%">设备仪器编号：</td><td width="35%">
						<form:input path="equipmentCode" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span></td>
				<td class="tit" width="15%">出厂年月：</td><td width="35%">
						<input name="equipmentBuytime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sbEquipmentScrap.equipmentBuytime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span></td>
				</tr>
				<tr>
					<td class="tit" width="15%">原值（万元）：</td><td width="35%">
						<form:input path="equipmentYprice" htmlEscape="false" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span></td>
				<td class="tit" width="15%">净值（万元）：</td><td width="35%">
						<form:input path="equipmentJprice" htmlEscape="false" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span></td>
				</tr>
				<tr>
					<td class="tit" width="15%">型号：</td><td width="35%">
						<form:input path="equipmentType" htmlEscape="false" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span></td>
				<td class="tit" width="15%">购置年月：</td><td width="35%">
						<input name="equipmentOuttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sbEquipmentScrap.equipmentOuttime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span></td>
				</tr>
				<tr>
					<td class="tit" width="15%">使用单位：</td><td width="35%">
						<form:input path="equipmentTeamName" htmlEscape="false" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span></td>
				<td class="tit" width="15%">规格：</td><td width="35%">
						<form:input path="equipmentNorms" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span></td>
				</tr>
				<tr>
					<td class="tit" width="15%">启用年月：</td><td width="35%">
						<input name="equipmentStarttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sbEquipmentScrap.equipmentStarttime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span></td>
				<td class="tit" width="15%">已使用年限：</td><td width="35%">
						<form:input path="equipmentYears" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span></td>
				</tr>
				<tr>
					<td class="tit" width="15%">报废原因：</td><td width="35%" colspan="3">
						<form:textarea path="reason" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
				<span class="help-inline"><font color="red">*</font> </span></td>
				
				</tr>
		</table>	
	
		
		
		
		
		
		
		<div class="form-actions">
			<shiro:hasPermission name="equipment:scrap:sbEquipmentScrap:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>