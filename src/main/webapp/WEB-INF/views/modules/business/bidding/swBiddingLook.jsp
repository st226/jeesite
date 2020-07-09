<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>内部招投标管理</title>
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
		
		 function tt(id){
				
			 window.open("${ctx}/business/bidding/swBidding/view?id=${swBidding.id}",'top'); 
			}
			
			function ok(e){
				var file = $("#file").val();
				if(file==null || file==""){
					alert("请先上传审批结果！");
				}else{
					 $("#state").val("1");
						$("#inputForm").submit();
				}
			}
			
			function checkOk(e,idx){
				if ($(e).attr("checked") == 'checked') {
					
					$("#swBiddingSupplierList"+idx+"_isBid").val("1");
                }else{
                	$("#swBiddingSupplierList"+idx+"_isBid").val("0");
                }
			}
			
			
			
	function minus(){
		
		document.getElementById("swBidding").style.display = document.getElementById("swBidding").style.display=="none"?"":"none";
		document.getElementById("minus_icon").className= document.getElementById("minus_icon").className=="icon-minus"?"icon-plus": "icon-minus";  
	}
   function plus(){
		
		document.getElementById("plusDiv").style.display = document.getElementById("plusDiv").style.display=="none"?"":"none";
		document.getElementById("plusIcon").className= document.getElementById("plusIcon").className=="icon-plus"?"icon-minus": "icon-plus";  
	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/business/order/swOrder/">采购任务列表标列表</a></li>
		<li class="active"><a href="${ctx}/business/bidding/swBidding/look?id=${swBidding.id}">内部招投标<shiro:hasPermission name="business:bidding:swBidding:edit">${not empty swBidding.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="business:bidding:swBidding:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>

	
	<form:form id="inputForm" modelAttribute="swBidding" action="${ctx}/business/bidding/swBidding/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="orderId"/>
		<form:hidden path="state"/>
		<sys:message content="${message}"/>	
		<legend>招投标录入 <a onclick="minus()" style='text-decoration:none;'><i id="minus_icon" class="icon-minus"></i></a></legend>	
		<div id="swBidding">
		<table class="table-form">
				<tr>
					<td class="tit" width="15%">货物名称：</td><td width="35%">
							<form:input path="goodsName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">数量：</td><td width="35%">
						<form:input path="goodsAmount" htmlEscape="false" maxlength="11" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>	
				<tr>
					<td class="tit" width="15%">品牌：</td><td width="35%">
							<form:input path="goodsBrand" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">到货地址：</td><td width="35%">
						<form:input path="address" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>	
				<tr>
					<td class="tit" width="15%">截止日期：</td><td width="35%">
							<input name="closingDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${swBidding.closingDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit" width="15%">项目名称：</td><td width="35%">
						<form:input path="projectName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>	
				<td class="tit" width="15%">货物组成：</td><td width="35%"  colspan="3">
							<form:textarea path="goodsComposition" htmlEscape="false" maxlength="255"  rows="4"  class="input-xxlarge "/>
				<span class="help-inline"><font color="red">*</font> 购买货物的组成、零配件等</span>
					</td>
					</tr>	
					<tr>	
				<td class="tit" width="15%">技术参数：</td><td width="35%"  colspan="3">
							<form:textarea path="technical" htmlEscape="false" maxlength="255" rows="4" class="input-xxlarge " />
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					</tr>	
						<tr>	
				<td class="tit" width="15%">项目预算：</td><td width="35%"  colspan="3">
							<form:input path="budget" htmlEscape="false" maxlength="255" class="input-xxlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					</tr>
					<tr>
					<td class="tit" width="15%">询价场次号：</td><td width="35%" colspan="3">
							<form:input path="field2" htmlEscape="false" maxlength="255" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
				
					</td>
					
				</tr>
               <tr>	
				<td class="tit" width="15%">上传签字扫描件：</td><td width="35%"  colspan="3">
							<form:hidden id="file" path="file" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="file" type="files" uploadPath="/business/bidding/swBidding" selectMultiple="true" readonly="true"/>
					</td>
					</tr>	
		</table>

				 <legend>供应商</legend>

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
							
							
							</tr>
						</thead>
						<tbody id="swBiddingSupplierList">
						</tbody>
						
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

                            
					
							
						</tr>//-->
					</script>
					<script type="text/javascript">
						var swBiddingSupplierRowIdx = 0, swBiddingSupplierTpl = $("#swBiddingSupplierTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(swBidding.swBiddingSupplierList)};
							for (var i=0; i<data.length; i++){
								addRow('#swBiddingSupplierList', swBiddingSupplierRowIdx, swBiddingSupplierTpl, data[i]);
								swBiddingSupplierRowIdx = swBiddingSupplierRowIdx + 1;
							}
						});
					</script>
		<legend>招投标完成情况录入 <a onclick="plus()"><i id="minus_icon" class="icon-minus"></i></a></legend>
		<div id="plusDiv" style="display: block">
		 <table class="table-form">
		         <tr>
					<td class="tit" width="15%">开标时间：</td><td width="35%">
							<input name="openingTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${swBidding.openingTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</td><td class="tit" width="15%">中标时间：</td><td width="35%">
						<input name="winningTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${swBidding.winningTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</td>
					
				</tr>	
			
				<tr>
					<td class="tit" width="15%">签到表：</td><td width="35%" colspan="3">
				
				
				        	<form:hidden id="signFile" path="signFile" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="signFile" type="files" uploadPath="/business/bidding/swBidding" selectMultiple="true" readonly="true"/>
					</td>
					
				</tr>	
				<tr>
					<td class="tit" width="15%">中标通知书：</td><td width="35%" colspan="3">
							<form:hidden id="notice" path="notice" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="notice" type="files" uploadPath="/business/bidding/swBidding" selectMultiple="true" readonly="true"/>
					</td>
					
				</tr>	
					
				
				
		</table>
		</div>
		
		<div class="form-actions">
		
		
			<input id="dayin" class="btn" type="button"  onclick="tt(1)" value="打印预览"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		
	</form:form>
	
	
</body>
</html>