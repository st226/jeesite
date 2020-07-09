<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商务谈判管理</title>
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
			
			if('${type}'=='1'){
				document.getElementById("field2TR").style.display="";
			}
		});
		function addRow(list, idx, tpl, row){
			if(row==null){
				newRow(list, idx, tpl, row);
			}else{
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
			
		}
		function newRow(list, idx, tpl, row){
			 top.$.jBox.open("iframe:${ctx}/business/supplier/swSupplier?pageSize=8","选择供应商", 1150, 500, {
		            buttons:{"确认":"ok", "关闭":true},
		            submit:function(v, h, f){
		                if(v=="ok"){
		              //      var data=h.find("iframe")[0].contentWindow.$("#inputForm").serialize();
		                    var i=0;
		    	            var j=0;
		    	            h.find("iframe")[0].contentWindow.$("input[name*='checkbox_name']").each(function () {
		    	                if ($(this).attr("checked") == 'checked') {
		    	                    var name = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].name']").val();
		    	                    var supplierUser = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].supplierUser']").val();
		    	                    var userPhone = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].userPhone']").val();
		    	                    var supplierId =  h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].id']").val();
		    	                    $("#supplierId").val(supplierId);
		    	                    $("#supplierName").val(name);
		    	                    $("#supplierUser").val(supplierUser);
		    	                    $("#supplierPhone").val(userPhone);
		    	                    row =  {"supplierName":name,"supplierId":supplierId,"supplierUser":supplierUser,"phone":userPhone};
		    	                    addRow(list, idx, tpl, row);
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
		
		 function tt(id){
				
			 window.open("${ctx}/business/negotiate/swNegotiate/view?id=${swNegotiate.id}",'top'); 
			}
			
		 function ok(e){
				var file = $("#appendix").val();
				if(file==null || file==""){
					alert("请先上传审批结果！");
				}else{
					 $("#state").val("1");
						$("#inputForm").submit();
				}
			}
		 
		 function sunt(){
			 $("#balancePrice").val($("#totalPrice").val()-$("#negotiatePrice").val());
			 $("#negotiateNotes").val('客户报价'+$("#totalPrice").val()+'元，经谈判在保证产品功能和产品服务的前提下，最终价格'+$("#negotiatePrice").val()+'元。');
		 }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/business/order/swOrder/">采购任务列表标列表</a></li>
		<li class="active"><a href="${ctx}/business/negotiate/swNegotiate/form?id=${swNegotiate.id}">商务谈判<shiro:hasPermission name="business:negotiate:swNegotiate:edit">${not empty swNegotiate.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="business:negotiate:swNegotiate:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="swNegotiate" action="${ctx}/business/negotiate/swNegotiate/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="orderId"/>
		<form:hidden path="state"/>
		<sys:message content="${message}"/>	
		<fieldset>
			<table class="table-form">
				<tr>
					<td class="tit" width="15%">谈判时间：</td><td width="35%">
						<input name="negotiateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${swNegotiate.negotiateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">谈判地点：</td><td width="35%">
						<form:input path="negotiateLocal" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>
					<td class="tit">项目名称：</td><td>
						<form:input path="projectName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit">采用程序：</td><td>
						<form:select path="procedures" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('procedure')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>
					<td class="tit">供应商来源：</td><td>
						<form:select path="supplier" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('supplier')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					<td class="tit">经办人：</td><td>
						<form:input path="negotiateUser" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				
					</td>
				</tr>
				<tr>
					<td class="tit">项目内容：</td><td colspan="3">
						<form:textarea path="projectContent" htmlEscape="false" rows="2" maxlength="500" class="input-xxlarge "/>
			
					</td>
					
				</tr>
				<tr>
					<td class="tit">报价情况：</td><td colspan="3">
						<form:textarea path="quotedPrice" htmlEscape="false" rows="1" maxlength="500" class="input-xxlarge "/>
						<span class="help-inline">描述客户报价 </span>
			
					</td>
					
				</tr>
				<tr>
					<td class="tit">总价：</td><td>
						<form:input path="totalPrice" htmlEscape="false" class="input-xlarge " onchange="sunt()" /><span class="help-inline">客户起始报价 </span>
			
					</td><td class="tit">谈判价：</td><td>
						<form:input path="negotiatePrice" htmlEscape="false" class="input-xlarge " onchange="sunt()" /><span class="help-inline">填写谈判后价格 </span>
					</td>
					
				</tr>
				<tr>
					<td class="tit">差额：</td><td>
						<form:input path="balancePrice" htmlEscape="false" class="input-xlarge "/>
			       <td class="tit" colspan="2"><span class="help-inline">对预算5万元以下，招标办和财务人员可以不参加。</span></td>
					
					
				</tr>
				<tr>
					<td class="tit">谈判记录：</td><td colspan="3">
						<form:textarea path="negotiateNotes" htmlEscape="false" rows="4" maxlength="500" class="input-xxlarge "/>
			
					</td>
					
				</tr>
				<tr id="field2TR" style="display: none">
					<td class="tit" width="15%">询价场次号：</td><td width="35%" colspan="3">
							<form:input path="field2" htmlEscape="false" maxlength="255" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
				
					</td>
					
				</tr>	
				
				<tr>
					<td class="tit">本单位谈判人员：</td><td colspan="3">
						<sys:treeselect id="negotiateUsers" name="field5" value="${swNegotiate.field5}" labelName="negotiateUsers" labelValue="${swNegotiate.negotiateUsers}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge" allowClear="true"  checked="true"  notAllowSelectParent="true"/>
					
			
					</td>
					
				</tr>
				<tr>
					<td class="tit">招标办：</td><td colspan="3">

			           <sys:treeselect id="tendersUser" name="field4" value="${swNegotiate.field4}" labelName="tendersUser" labelValue="${swNegotiate.tendersUser}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge" allowClear="true"  checked="true"  notAllowSelectParent="true"/>
					</td>
					
				</tr>
				<tr>
					<td class="tit">上传签完字的谈判表格：</td><td colspan="3">
						<form:hidden id="appendix" path="appendix" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="appendix" type="files" uploadPath="/business/negotiate/swNegotiate" selectMultiple="true" readonly="true"/>
			
					</td>
					
				</tr>
			</table>
		</fieldset>		
		
		
	
		
		<legend>供应商情况</legend>
		
		
		
		
			
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								
								<th>供应商名称</th>
								<th>联系人</th>
								<th>手机号码</th>
								
								
								<shiro:hasPermission name="business:negotiate:swNegotiate:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="swNegotiateSupplierList">
						</tbody>
						<shiro:hasPermission name="business:negotiate:swNegotiate:edit"><tfoot>
							<tr><td colspan="16"><a href="javascript:" onclick="addRow('#swNegotiateSupplierList', swNegotiateSupplierRowIdx, swNegotiateSupplierTpl);swNegotiateSupplierRowIdx = swNegotiateSupplierRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="swNegotiateSupplierTpl">//<!--
						<tr id="swNegotiateSupplierList{{idx}}">
							<td class="hide">
								<input id="swNegotiateSupplierList{{idx}}_id" name="swNegotiateSupplierList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
                                <input id="swNegotiateSupplierList{{idx}}_supplierId" name="swNegotiateSupplierList[{{idx}}].supplierId" type="hidden" value="{{row.supplierId}}"/>
								<input id="swNegotiateSupplierList{{idx}}_delFlag" name="swNegotiateSupplierList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							
							<td>
								<input id="swNegotiateSupplierList{{idx}}_supplierName" name="swNegotiateSupplierList[{{idx}}].supplierName" type="text" value="{{row.supplierName}}" maxlength="200" class="input-xlarge "/>
							</td>
							<td>
								<input id="swNegotiateSupplierList{{idx}}_supplierUser" name="swNegotiateSupplierList[{{idx}}].supplierUser" type="text" value="{{row.supplierUser}}" maxlength="100" class="input-small "/>
							</td>
							<td>
								<input id="swNegotiateSupplierList{{idx}}_phone" name="swNegotiateSupplierList[{{idx}}].phone" type="text" value="{{row.phone}}" maxlength="255" class="input-small "/>
							</td>
							
						
							
							<shiro:hasPermission name="business:negotiate:swNegotiate:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#swNegotiateSupplierList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
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
			
			<input id="dayin" class="btn" type="button"  onclick="tt(1)" value="打印预览"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>