<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商务谈判管理(公开)</title>
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
		
		function word(){
			
			 
			 var text = document.getElementById("dayin").innerHTML;
			 $.ajax({
	                type:'post',
	                url:'${ctx}/business/special/swSpecial/exportSwSpecial',
	                data:{'content':text},
	                cache:false,
	                dataType:'json',
           success:function(data){
           	window.location.href="/jeesite/userfiles/download/"+data ;
           }
       });
	
		}
		
		function dayin(){
			 $("#dayin").jqprint();
		}
	</script>
</head>
<body>
	<br/>
	<form:form id="inputForm" modelAttribute="swNegotiate" action="${ctx}/business/negotiate/swNegotiate/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
          <div id="dayin">

			<table border='1' bordercolor='#000000' style='border-collapse:collapse;table-layout:fixed;width: 630px;margin-left: 40px;margin-right:36px;margin-top:16px;'>
			 <caption align="top"  style="font-size: 28px;margin-bottom: 10px;line-height:250%;font-family: 宋体, SimHei;" ><b>商 务 谈 判 记 录 表</b></caption>
			   <tr>
				<td style="border-top: 1px solid transparent !important;border-left: 1px solid transparent !important;border-right: 1px solid transparent !important;" colspan="4" >公司统一编号:${swNegotiate.field3}</td>

					
				</tr>	
				<tr style="margin-top:26px;">
					<td width="20%" height="40px"  style="font-family: 宋体, SimHei; text-align:center;width: 20%">谈判时间：</td>
					<td  style="font-family: 宋体, SimHei; text-align:center;width: 20%" width="30%" colspan="2">
						
		
					</td ><td width="20%"  style="font-family: 宋体, SimHei; text-align:center;width: 20%">谈判地点：</td>
					<td width="30%"   style="font-family: 宋体, SimHei; text-align:center;width: 20%"  colspan="2">
						${swNegotiate.negotiateLocal}
					</td>
					
				</tr>
				<tr  height="40px" >
					<td   style="font-family: 宋体, SimHei; text-align:center;width: 20%">项目名称：</td>
					<td   style="font-family: 宋体, SimHei; text-align:center;width: 20%" colspan="2">
					${swNegotiate.projectName}
						
					</td><td   style="font-family: 宋体, SimHei; text-align:center;width: 20%">采用程序：</td>
					<td   style="font-family: 宋体, SimHei; text-align:center;width: 20%"  colspan="2">
					${fns:getDictLabel(swNegotiate.procedures, 'procedure', '')}
						
					</td>
					
				</tr>
				<tr  height="40px" >
					<td   style="font-family: 宋体, SimHei; text-align:center;width: 20%">供应商来源：</td>
					<td    style="font-family: 宋体, SimHei; text-align:center;width: 20%" colspan="5">
					${fns:getDictLabel(swNegotiate.supplier, 'supplier', '')}
				
					</td>
					
				</tr>
				<tr  height="60px" >
					<td   style="font-family: 宋体, SimHei; text-align:center;width: 20%">项目内容：</td>
					<td  style="font-family: 宋体, SimHei; text-align:center;width: 20%" colspan="5" height="80px">
					${swNegotiate.projectContent}
						
			
					</td>
					
				</tr>
				<tr>
					<td   style="font-family: 宋体, SimHei; text-align:center;width: 20%">报价情况：</td>
					<td   style="font-family: 宋体, SimHei; text-align:center;width: 20%" colspan="5" height="100px">
					${swNegotiate.quotedPrice}
						
					</td>
					
				</tr>
				<tr>
					<td   style="font-family: 宋体, SimHei; text-align:center;width: 20%">谈判记录：</td>
					<td    style="font-family: 宋体, SimHei; text-align:center;width: 20%" colspan="5" height="100px">
					${swNegotiate.negotiateNotes}
						
					</td>
					
				</tr>
				<tr  height="40px">
					<td   style="font-family: 宋体, SimHei; text-align:center;width: 20%">总金额：</td>
					<td   style="font-family: 宋体, SimHei; text-align:center;width: 20%" colspan="1">
					${swNegotiate.totalPrice}
										</td><td  style="font-family: 宋体, SimHei; text-align:center;width: 20%">谈判价：</td><td colspan="1">
					
		
					</td>
					<td   style="font-family: 宋体, SimHei; text-align:center;width: 20%">差额：</td><td   style="font-family: 宋体, SimHei; text-align:center;width: 20%"  >
					
					</td>
					
				</tr>
				
				
				<tr  height="40px">
								
						<td height="120px"  style="font-family: 宋体, SimHei; text-align:center;width: 20%" rowspan="2">供应商情况(本人签字)：</td >
						
								<td width="20%"  style="font-family: 宋体, SimHei; text-align:center;width: 20%" colspan="2"><b>供应商名称</b></td>
								<td  width="15%"  style="font-family: 宋体, SimHei; text-align:center;width: 20%" colspan="1"><b>联系人</b></td>
								<td  width="15%"  style="font-family: 宋体, SimHei; text-align:center;width: 20%" colspan="2"><b>手机号码</b></td>
								 
								
								
							
							</tr>
							<tr  height="40px">
				
						
								<td width="20%"  style="font-family: 宋体, SimHei; text-align:center;width: 20%" colspan="2">${swNegotiate.swNegotiateSupplierList[0].supplierName}</td>
								<td  width="15%"  style="font-family: 宋体, SimHei; text-align:center;width: 20%" colspan="1">${swNegotiate.swNegotiateSupplierList[0].supplierUser}</td>
								<td  width="15%"  style="font-family: 宋体, SimHei; text-align:center;width: 20%" colspan="2">${swNegotiate.swNegotiateSupplierList[0].phone}</td>
								 
								
								
							
							</tr>
							
				<tr>
					<td height="120px"  style="font-family: 宋体, SimHei; text-align:center;width: 20%">本单位谈判人员(本人签字)：</td ><td  style="font-family: 宋体, SimHei; text-align:center;width: 20%" colspan="5">

						
					</td>
					
				</tr>
				<tr>
					<td  style="font-family: 宋体, SimHei; text-align:center;width: 20%" height="100px">招标办：</td>
					<td  colspan="5" style="font-family: 宋体, SimHei; text-align:center;width: 20%;text-align:right;vertical-align:bottom;" height="100px">
				      签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
						
					</td>
					
				</tr>
			</table>
		<p style="font-family: 宋体, SimHei;line-height:150%">&nbsp;&nbsp;&nbsp;&nbsp;注:对预算5万元以下，招标办和财务人员可以不参加。</p>
	        
		  <p style='margin-left:400px;margin-top:40px;font-family:楷体_gb2312'><em>本页为仪器设备全生命周期管理系统生成 </em></p>
		
	</div>
		
		
		
		
			
					<script type="text/template" id="swNegotiateSupplierTpl">//<!--
						<tr id="swNegotiateSupplierList{{idx}}"  height="40px">
							<td class="hide">
								<input id="swNegotiateSupplierList{{idx}}_id" name="swNegotiateSupplierList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="swNegotiateSupplierList{{idx}}_delFlag" name="swNegotiateSupplierList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							
							<td  style="font-family: 宋体, SimHei; text-align:center;width: 20%"  colspan="3">
								{{row.supplierName}}
							</td>
							<td  style="font-family: 宋体, SimHei; text-align:center;width: 20%"  colspan="1">
								{{row.supplierUser}}
							<td  style="font-family: 宋体, SimHei; text-align:center;width: 20%"  colspan="2">
								{{row.phone}}
							</td>
							
							
							
							
							
						</tr>//-->
					</script>
					<script type="text/javascript">
						var swNegotiateSupplierRowIdx = 0, swNegotiateSupplierTpl = $("#swNegotiateSupplierTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(swNegotiate.swNegotiateSupplierList)};
							for (var i=0; i<data.length; i++){
								addRow('#swNegotiateSupplierList', swNegotiateSupplierRowIdx, swNegotiateSupplierTpl, data[i]);
								swNegotiateSupplierRowIdx = swNegotiateSupplierRowIdx + 1;
							}
						});
					</script>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn" type="button"  onclick="dayin()" value="打 印"/>
			<input id="btnSubmit" class="btn btn-primary" type="button"  onclick="word()" value="导出word"/>
			<input id="btnCancel" class="btn" type="button" value="关 闭" onclick="window.close();"/>
		</div>
	</form:form>
</body>
</html>