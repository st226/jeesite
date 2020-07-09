<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备维修申请管理</title>
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
			    	            var ids = "";
			    	            var names = "";
			    	            var types = "";
			    	            var mades = "";
			    	            var amounts = 0 ;
			    	            h.find("iframe")[0].contentWindow.$("input[name*='checkbox_name']").each(function () {
			    	                if ($(this).attr("checked") == 'checked') {
			    	                    var name = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].name']").val();
			    	                    names = names==""?name:names+","+name ;
			    	                    var type = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].type']").val();
			    	                    types = types==""?type:types+","+type ;
			    	                    var amount = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].field2']").val();
			    	                    amounts += parseInt(amount) ;
			    	                    var made = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].made']").val();
			    	                    mades = mades==""?made:mades+","+made ;
			    	                    var id =  h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].id']").val();
			    	                    ids = ids==""?id:ids+","+id ;
			    	                    
			    	                    j++;
			    	                }
			    	                i++;
			    	            });
			    	            $("#equipmentId").val(ids);
	    	                    $("#equipmentName").val(names);
	    	                    $("#equipmentType").val(types);
	    	                    $("#equipmentMade").val(mades);
	    	                    $("#equipmentAmount").val(amounts);
			    	     
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
		
		function ok(e){
			$("#state").val("1");
			$("#inputForm").submit();
		
		}
		 function tt(id){
					window.location.href="${ctx}/equipment/repair/sbEquipmentRepair/view?id=${sbEquipmentRepair.id}" ;
				
			}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/equipment/repair/sbEquipmentRepair/">设备维修申请列表</a></li>
		<li class="active"><a href="${ctx}/equipment/repair/sbEquipmentRepair/form?id=${sbEquipmentRepair.id}">设备维修申请<shiro:hasPermission name="equipment:repair:sbEquipmentRepair:edit">${not empty sbEquipmentRepair.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="equipment:repair:sbEquipmentRepair:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sbEquipmentRepair" action="${ctx}/equipment/repair/sbEquipmentRepair/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="equipmentId"/>
		<form:hidden path="state"/>
		<sys:message content="${message}"/>		
		<table class="table-form">
				<tr>
					<td class="tit" width="15%">申请部门：</td><td width="35%">
						<sys:treeselect id="office" name="office.id" value="${sbEquipmentRepair.office.id}" labelName="officeName" labelValue="${sbEquipmentRepair.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">申请日期：</td><td width="35%">
						<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sbEquipmentRepair.createDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="15%">设备名称：</td><td width="35%">
						<form:input path="equipmentName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
						<a id='relationButton' href='javascript:' class='btn' style=''>&nbsp;<i class='icon-search'></i>&nbsp;</a>
				<span class="help-inline"><font color="red">*</font> </span></td>
				<td class="tit" width="15%">数量：</td><td width="35%">
						<form:input path="equipmentAmount" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span></td>
				</tr>
				<tr>
					<td class="tit" width="15%">型号规格：</td><td width="35%">
						<form:input path="equipmentType" htmlEscape="false" maxlength="100" class="input-xlarge "/>
						
				<span class="help-inline"><font color="red">*</font> </span></td>
				<td class="tit" width="15%">生产厂家：</td><td width="35%">
						<form:input path="equipmentMade" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span></td>
				</tr>
				<tr>
					<td class="tit" width="15%">维修预算费用：</td><td width="35%" colspan="3">
						<form:input path="equipmentBudgt" htmlEscape="false" maxlength="100" class="input-xxlarge "/>
							<span class="help-inline"><font color="red">*</font> </span></td>
				</tr>
				<tr>		
			
				<td class="tit" width="15%">设备故障描述：</td><td width="35%" colspan="3" >
						<form:textarea path="equipmentFault" htmlEscape="false" maxlength="100"  rows="4"  class="input-xxlarge " />
				<span class="help-inline"><font color="red">*</font> </span></td>
				</tr>
				<tr>
					<td class="tit" width="15%">申请人：</td><td width="35%">
						<sys:treeselect id="user" name="user.id" value="${sbEquipmentRepair.user.id}" labelName="userName" labelValue="${sbEquipmentRepair.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
						
				<span class="help-inline"><font color="red">*</font> </span></td>
				<td class="tit" width="15%">联系电话：</td><td width="35%">
						<form:input path="userPhone" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span></td>
				</tr>
				
		</table>
		
		
		
		
		<div class="form-actions">
			<shiro:hasPermission name="equipment:repair:sbEquipmentRepair:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnSubmitok" class="btn  btn-primary"   type="button"   onclick="ok(1)"  value="提交"/>
			<input id="dayin" class="btn" type="button"  onclick="tt(1)" value="打印预览"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>