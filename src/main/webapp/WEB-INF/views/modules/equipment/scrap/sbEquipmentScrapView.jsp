<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备仪器报废（闲置）申请管理</title>
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
		
		function dayin(){
			 $("#dayin").jqprint();
		}
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
		<div id="dayin">
		<table border='1' bordercolor='#000000' style='border-collapse:collapse;table-layout:fixed;width: 740px'>
			 <caption align="top"  style="font-size: 20px;margin-bottom: 30px" >设备仪器报废（闲置）申请表</caption>	
				<tr height="40px">
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'  width="15%">申请部门：</td><td width="35%">
						${sbEquipmentScrap.office.name}
					</td><td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'  width="15%">申请日期：</td><td width="35%">
					
				<fmt:formatDate value="${sbEquipmentScrap.createDate}" pattern="yyyy-MM-dd"/>
					</td>
					
				</tr>
				<tr height="40px">
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'  width="15%">设备名称：</td><td width="35%">
						${sbEquipmentScrap.equipmentName}</td>
				<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'  width="15%">国别厂家：</td><td width="35%">
						${sbEquipmentScrap.equipmentMade}</td>
				</tr>
				<tr height="40px">
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'  width="15%">设备仪器编号：</td><td width="35%">
						${sbEquipmentScrap.equipmentCode}</td>
				<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'  width="15%">出厂年月：</td><td width="35%">
						<fmt:formatDate value="${sbEquipmentScrap.equipmentBuytime}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr height="40px">
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'  width="15%">原值（万元）：</td><td width="35%">
						${sbEquipmentScrap.equipmentYprice}</td>
				<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'  width="15%">净值（万元）：</td><td width="35%">
						${sbEquipmentScrap.equipmentJprice}</td>
				</tr>
				<tr height="40px">
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'  width="15%">型号：</td><td width="35%">
						${sbEquipmentScrap.equipmentType}</td>
				<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'  width="15%">购置年月：</td><td width="35%">
						<fmt:formatDate value="${sbEquipmentScrap.equipmentOuttime}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr height="40px">
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'  width="15%">使用单位：</td><td width="35%">
						${sbEquipmentScrap.equipmentTeamName}</td>
				<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'  width="15%">规格：</td><td width="35%">
						${sbEquipmentScrap.equipmentNorms}</td>
				</tr>
				<tr height="40px">
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'  width="15%">启用年月：</td><td width="35%">
						<fmt:formatDate value="${sbEquipmentScrap.equipmentStarttime}" pattern="yyyy-MM-dd"/></td>
				<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'  width="15%">已使用年限：</td><td width="35%">
						${sbEquipmentScrap.equipmentYears}</td>
				</tr>
				<tr height="160px">
					<td style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;'  width="15%">设备仪器现状及报废（闲置）原因：</td><td width="35%" colspan="3">
						${sbEquipmentScrap.reason}</td>
				
				</tr>
				<tr>
				   <td height="100px" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">鉴定意见：</td>
				    <td colspan="3"  style='vertical-align:bottom; text-align:right;WORD-WRAP:break-word;' width="15%">
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="100px" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">报废（闲置）后处理意见：</td>
				    <td colspan="3"  style='vertical-align:bottom; text-align:right;WORD-WRAP:break-word;' width="15%">
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="100px" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">财务部门意见：</td>
				    <td colspan="3"  style='vertical-align:bottom; text-align:right;WORD-WRAP:break-word;' width="15%">
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="100px" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">主管领导意见：</td>
				    <td colspan="3"  style='vertical-align:bottom; text-align:right;WORD-WRAP:break-word;' width="15%">
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="100px" style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width="15%">总经理意见：</td>
				    <td colspan="3"  style='vertical-align:bottom; text-align:right;WORD-WRAP:break-word;' width="15%">
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
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