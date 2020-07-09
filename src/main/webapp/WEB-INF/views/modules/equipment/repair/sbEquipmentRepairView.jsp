<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备维修申请管理</title>
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
			    	                    $("#equipmentId").val(id);
			    	                    $("#equipmentName").val(name);
			    	                    $("#equipmentType").val(type);
			    	                    $("#equipmentMade").val(made);
			    	                    $("#equipmentAmount").val(amount);
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
		
		function dayin(){
			 $("#dayin").jqprint();
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
		<sys:message content="${message}"/>	
		<div id="dayin">
		<table border='1' bordercolor='#000000' style='border-collapse:collapse;table-layout:fixed;width: 740px'>
			 <caption align="top"  style="font-size: 20px;margin-bottom: 30px" >设备维修申请单</caption>	
	
				<tr>
					<td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%" height="40px">申请部门：</td><td width="35%">
						${sbEquipmentRepair.office.name}
					</td><td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">申请日期：</td><td width="35%">
						<fmt:formatDate value="${sbEquipmentRepair.createDate}" pattern="yyyy-MM-dd"/>
					
					</td>
					
				</tr>
				<tr>
					<td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%" height="40px">设备名称：</td><td width="35%">
						${sbEquipmentRepair.equipmentName}
					
		</td>
				<td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">数量：</td><td width="35%">
						${sbEquipmentRepair.equipmentAmount}</td>
				</tr>
				<tr>
					<td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%" height="40px">型号规格：</td><td width="35%">
						${sbEquipmentRepair.equipmentType}
						
				</td>
				<td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">生产厂家：</td><td width="35%">
						${sbEquipmentRepair.equipmentMade}</td>
				</tr>
				<tr>
					<td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%" height="40px">维修预算费用：</td><td width="35%" colspan="3">
						${sbEquipmentRepair.equipmentBudgt}</td>
				</tr>
				<tr>		
			
				<td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%" height="100px">设备故障描述：</td><td width="35%" colspan="3" >
						${sbEquipmentRepair.equipmentFault}</td>
				</tr>
				<tr>
					<td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%" height="40px">申请人：</td><td width="35%">
						${sbEquipmentRepair.user.name}
						
				
				<td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">联系电话：</td><td width="35%">
						${sbEquipmentRepair.userPhone}</td>
				</tr>
				<tr>
				   <td height="100px" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">申请人签字：</td>
				    <td colspan="3"  style='vertical-align:bottom; text-align:right;WORD-WRAP:break-word;' width="15%">
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="100px" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">申请部门领导签字：</td>
				    <td colspan="3"  style='vertical-align:bottom; text-align:right;WORD-WRAP:break-word;' width="15%">
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="100px" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">主管部门审核意见：</td>
				    <td colspan="3"  style='vertical-align:bottom; text-align:right;WORD-WRAP:break-word;' width="15%">
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="100px" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">主管副总审批意见：</td>
				    <td colspan="3"  style='vertical-align:bottom; text-align:right;WORD-WRAP:break-word;' width="15%">
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="100px" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">维修人员故障描述：</td>
				    <td colspan="3"  style='vertical-align:bottom; text-align:right;WORD-WRAP:break-word;' width="15%">
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="100px" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">申请部门维修确认签字：</td>
				    <td colspan="3"  style='vertical-align:bottom; text-align:right;WORD-WRAP:break-word;' width="15%">
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="40px" style='vertical-align:middle; WORD-WRAP:break-word;' width="15%" colspan="4">
				  注：本表一式三份1、财务部 2、质技部 3、申请部门保留一份</td>
				   
				</tr>
		</table>
		
		
		</div>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn" type="button"  onclick="dayin()" value="打 印"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>