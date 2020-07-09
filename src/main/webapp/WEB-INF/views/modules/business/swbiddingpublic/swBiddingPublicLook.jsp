<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公开招投标管理</title>
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
		
		function ok(e){
			var file = $("#notice").val();
			if(file==null || file==""){
				alert("请先上传公开招标中标函！");
			}else{
				 $("#state").val("1");
					$("#inputForm").submit();
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
		    	             
		    	                    row =  {"supplierName":name,"supplierId":supplierId,"supplierUser":supplierUser,"phone":userPhone};
		    	                    addRow(list, idx, tpl, row);
		    	                    swBiddingSupplierRowIdx = swBiddingSupplierRowIdx + 1;
		    	                    idx = idx+1;
		    	                    j++;
		    	                }
		    	                i++;
		    	            });
		    	     
		    	     //       window.parent.page();                 //调用父窗体方法，当关闭子窗体刷新父窗体
                       window.parent.window.jBox.close();    //关闭子窗体
                       
                       inputInfo();
		                    
		                }
		            },
		            loaded:function(h){
		                $(".jbox-content", top.document).css("overflow-y","hidden");
		            }
		        });
			 
		}
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/business/swbiddingpublic/swBiddingPublic/">公开招投标列表</a></li>
		<li class="active"><a href="${ctx}/business/swbiddingpublic/swBiddingPublic/form?id=${swBiddingPublic.id}">公开招投标<shiro:hasPermission name="business:swbiddingpublic:swBiddingPublic:edit">${not empty swBiddingPublic.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="business:swbiddingpublic:swBiddingPublic:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="swBiddingPublic" action="${ctx}/business/swbiddingpublic/swBiddingPublic/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="orderId"/>
		<form:hidden path="state"/>
		<sys:message content="${message}"/>	
		
		
		<!-- <table class="table-form">
				<tr>
					<td class="tit" width="15%">招标编号：</td><td width="35%">
						<form:input path="biddingNo" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
						
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">设备名称：</td><td width="35%">
						<form:input path="goodsName" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>	
				<tr>
					<td class="tit" width="15%">数量：</td><td width="35%">
						<form:input path="goodsAmount" htmlEscape="false" maxlength="2000" class="input-xlarge required"/>
						
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">报价币种：</td><td width="35%">
						<form:select path="currency" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sw_currency')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>	
				<tr>
				<td class="tit" width="15%">报价方式：</td><td width="35%">
						<form:input path="model" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
						
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">交货日期：</td><td width="35%">
						<form:input path="deliveryDate" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>	
				<tr>
				<td class="tit" width="15%">设备用途及基本要求：</td><td width="35%" colspan="3">
						<form:textarea path="puse" htmlEscape="false" maxlength="255" rows="4" class="input-xxlarge required"/>
						
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				
				<tr>
				<td class="tit" width="15%">设备要求及主要规格参数：</td><td width="35%" colspan="3">
						<form:textarea path="parameter" htmlEscape="true"  rows="4" maxlength="200" class="input-xxlarge "/>

			
					</td>
					
				</tr>
				<tr>
				<td class="tit" width="15%">设备附件及零备件：</td><td width="35%" colspan="3">
						<form:textarea path="parts" htmlEscape="true"  rows="4" maxlength="200" class="input-xxlarge"/>

		
					</td>
					
				</tr>
				<tr>
				<td class="tit" width="15%">技术服务要求：</td><td width="35%" colspan="3">
						<form:textarea path="technical" htmlEscape="true"  rows="4" maxlength="200" class="input-xxlarge"/>

					</td>
					
				</tr>
				<tr>
				<td class="tit" width="15%">验收标准及验收程序：</td><td width="35%" colspan="3">
						<form:textarea path="pcheck" htmlEscape="true"  rows="4" maxlength="200" class="input-xxlarge"/>

					
					</td>
					
				</tr>
				<tr>
				<td class="tit" width="15%">机床包装要求及运输方式：</td><td width="35%" colspan="3">
						<form:textarea path="packaging" htmlEscape="true"  rows="4" maxlength="200" class="input-xxlarge"/>

				
					</td>
					
				</tr>
				<tr>
				<td class="tit" width="15%">到货港口：</td><td width="35%" colspan="3">
						<form:input path="port" htmlEscape="true"  maxlength="200" class="input-xxlarge"/>

					</td>
					
				</tr>
				<tr>
				<td class="tit" width="15%">上传公开招标中标函：</td><td width="35%" colspan="3">
						
							<form:hidden id="file" path="file" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="file" type="files" uploadPath="/business/survey/swSurvey" selectMultiple="true"/>
				
					</td>

					
					
				</tr>
				 <tr>
					<td class="tit" width="15%">招标编号：</td><td width="35%">
						<form:input path="biddingNo" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
						
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">设备名称：</td><td width="35%">
						<form:input path="goodsName" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>	
		</table>
				 -->
				
		
			<legend>内部技术指标评审</legend>
		<table class="table-form">	
			<tr>
				<td class="tit" width="15%">内部技术指标：</td><td width="35%" colspan="1">		
							<form:hidden id="technicalFile" path="technicalFile" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="technicalFile" type="files" uploadPath="/business/survey/swSurvey" selectMultiple="true"/>
				
					</td>
			
				<td class="tit" width="15%">评审时间：</td><td width="35%" colspan="1">		
					
				<input name="reviewDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${swBiddingPublic.reviewDate}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				
					</td>
			</tr>
			<tr>
				<td class="tit" width="15%">内部技术指标评审及签到表：</td><td width="35%" colspan="3">		
							<form:hidden id="signIn" path="signIn" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="signIn" type="files" uploadPath="/business/survey/swSurvey" selectMultiple="true"/>
				
					</td>
			</tr>		
			
		</table>
		<legend>招标执行情况</legend>
		<table class="table-form">	
			<tr>
				<td class="tit" width="15%">开标时间：</td><td width="35%" colspan="1">		
					<input name="bidOpening" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${swBiddingPublic.bidOpening}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				
					</td>
			
				<td class="tit" width="15%">中标时间：</td><td width="35%" colspan="1">		
					
				<input name="bidWinning" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${swBiddingPublic.bidWinning}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				
					</td>
			</tr>
			<tr>
					<td class="tit" width="15%">委托代理机构：</td><td width="35%">
						<form:input path="agency" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
						
				
					</td>
				
					
				</tr>	
				<tr>
				<td class="tit" width="15%">中标通知书：</td><td width="35%" colspan="3">		
							<form:hidden id="notice" path="notice" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="notice" type="files" uploadPath="/business/survey/swSurvey" selectMultiple="true"/>
				
					</td>
			</tr>	
			<tr>
			  
				<td class="tit" width="15%">特殊情况说明：</td><td width="35%"  colspan="3" >		
							<form:textarea path="special" htmlEscape="true"  rows="4" maxlength="200" class="input-xxlarge"/>
				
					</td>
			</tr>
				<tr>
					<td class="tit" width="15%">询价场次号：</td><td width="35%" colspan="3">
							<form:input path="field2" htmlEscape="false" maxlength="255" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
				
					</td>
					
				</tr>			
			<tr>
			  
				<td class="tit" width="15%">特殊情况说明附件：</td><td width="35%"  colspan="3" >		
							<form:hidden id="specialFile" path="specialFile" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="specialFile" type="files" uploadPath="/business/survey/swSurvey" selectMultiple="true" readonly="true"/>
				
					</td>
			</tr>			
		</table>
		 <legend>招标结果</legend>

					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
						
								<th>供应商名称</th>
								<th>联系人</th>
								<th>联系电话</th>
								<th>电子邮件</th>
								<th>投标价格</th>
								<th>是否中标</th>
							
							
								<shiro:hasPermission name="business:bidding:swBidding:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="swBiddingSupplierList">
						</tbody>
						<shiro:hasPermission name="business:bidding:swBidding:edit"><tfoot>
							<tr><td colspan="17"><a href="javascript:" onclick="newRow('#swBiddingSupplierList', swBiddingSupplierRowIdx, swBiddingSupplierTpl);swBiddingSupplierRowIdx = swBiddingSupplierRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					</div>
					<script type="text/template" id="swBiddingSupplierTpl">//<!--
						<tr id="swBiddingSupplierList{{idx}}">
							<td class="hide">
								<input id="swBiddingSupplierList{{idx}}_id" name="swBiddingSupplierList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="swBiddingSupplierList{{idx}}_delFlag" name="swBiddingSupplierList[{{idx}}].delFlag" type="hidden" value="0"/>
							   <input id="swBiddingSupplierList{{idx}}_supplierId" name="swBiddingSupplierList[{{idx}}].supplierId" type="hidden" value="{{row.supplierId}}"/>
                           </td>
							
							<td>
								<input id="swBiddingSupplierList{{idx}}_supplierName" name="swBiddingSupplierList[{{idx}}].supplierName" type="text" value="{{row.supplierName}}" maxlength="200" class="input-small "/>
							</td>
							<td>
								<input id="swBiddingSupplierList{{idx}}_supplierUser" name="swBiddingSupplierList[{{idx}}].supplierUser" type="text" value="{{row.supplierUser}}" maxlength="100" class="input-small "/>
							</td>
							<td>
								<input id="swBiddingSupplierList{{idx}}_phone" name="swBiddingSupplierList[{{idx}}].phone" type="text" value="{{row.phone}}" maxlength="255" class="input-small "/>
							</td>
						
							<td>
								<input id="swBiddingSupplierList{{idx}}_email" name="swBiddingSupplierList[{{idx}}].email" type="text" value="{{row.email}}" maxlength="100" class="input-small "/>
							</td>

                            <td>
								<input id="swBiddingSupplierList{{idx}}_negotiatePrice" name="swBiddingSupplierList[{{idx}}].negotiatePrice" type="text" value="{{row.negotiatePrice}}" maxlength="100" class="input-small "/>
							</td>
                            <td>
								
							
                                
                                <c:forEach items="${fns:getDictList('is_leaf')}" var="dict" varStatus="dictStatus">
									<span>
                                        <input id="swBiddingSupplierList{{idx}}_isBid${dictStatus.index}" name="swBiddingSupplierList[{{idx}}].isBid" type="checkbox" value="${dict.value}" data-value="{{row.isBid}}">
                                         <label for="swBiddingSupplierList{{idx}}_isBid${dictStatus.index}">${dict.label}</label>
                                   </span>
								</c:forEach>
                           </td>

                            
					
							<shiro:hasPermission name="business:bidding:swBidding:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#swBiddingSupplierList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var swBiddingSupplierRowIdx = 0, swBiddingSupplierTpl = $("#swBiddingSupplierTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(swBiddingPublic.swBiddingSupplierList)};
							for (var i=0; i<data.length; i++){
								addRow('#swBiddingSupplierList', swBiddingSupplierRowIdx, swBiddingSupplierTpl, data[i]);
								swBiddingSupplierRowIdx = swBiddingSupplierRowIdx + 1;
							}
						});
					</script>
		
		<div class="form-actions">
			
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>