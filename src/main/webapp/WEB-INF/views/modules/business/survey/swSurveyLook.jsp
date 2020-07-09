<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>调研报告管理</title>
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
			
			  
			 
			if('${amountYs}'<200000){
				document.getElementById("field3TR").style.display="";
			}
			if('${amountYs}'>=500000 && '${amountYs}'<2000000){

			}
			
			if('${amountYs}'<500000){
				document.getElementById("qingshi").style.display="none";

			}
			
			if('${amountYs}'>=2000000){
				document.getElementById("dayin").style.display="none";	
				document.getElementById("diaoyan").style.display="none";	
				document.getElementById("content").style.display="none";
			
			}
			
		});
		
		function plus(){
			
			document.getElementById("plusDiv").style.display = document.getElementById("plusDiv").style.display=="none"?"":"none";
			document.getElementById("minus_icon").className= document.getElementById("minus_icon").className=="icon-plus"?"icon-minus": "icon-plus";  
		}
		
		function newRow(list, idx, tpl, row){
			
			 top.$.jBox.open("iframe:${ctx}/business/supplier/swSupplier?pageSize=8","选择供应商", 1150, 600, {
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
		    	             
		    	                    row =  {"name":name,"field1":supplierId,"contacts":supplierUser,"telephone":userPhone};
		    	                    addRow(list, idx, tpl, row);
		    	                    swSurveyCompanyRowIdx = swSurveyCompanyRowIdx + 1;
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
		
		function inputInfo(){
			
			var text = "" ;

			$("input[name*='checkbox_name']").each(function () {
				var name = $("#swSurveyCompanyList"+this.value+"_name").val();

				if(text==""){
					text = name ;
				}else{
					text=text+"、"+name ;
				}
			});
			$("#supplier").val("本次针对调研了"+text+"${swSurvey.supplier}");
	          
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
		
		function tt(id){
			if(id=='2'){
				window.open("${ctx}/business/survey/swSurvey/viewQs?id=${swSurvey.id}",'top'); 
			}else{
				window.open("${ctx}/business/survey/swSurvey/view?id=${swSurvey.id}" ,'top'); 
			}
			
		}
		
		function ok(e){
			var file = $("#file").val();
			var resolutionResult = $("#resolutionResult").val();
			if((file==null || file=="") && (resolutionResult==null || resolutionResult=="")){
				alert("请先上传审批结果！");
			}else{
				document.getElementById("mainUses").className = 'input-xxlarge required'; 
				document.getElementById("situation").className = 'input-xxlarge required'; 
				document.getElementById("technical").className = 'input-xxlarge required'; 
				 $("#state").val("1");
				 $("#inputForm").submit();
			}
			
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/business/order/swOrder/">采购任务列表</a></li>
		<li class="active"><a href="${ctx}/business/survey/swSurvey/form?id=${swSurvey.id}">调研报告/请示<shiro:hasPermission name="business:survey:swSurvey:edit">${not empty swSurvey.id?'':''}</shiro:hasPermission><shiro:lacksPermission name="business:survey:swSurvey:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	
	<form:form id="inputForm" modelAttribute="swSurvey" action="${ctx}/business/survey/swSurvey/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="orderId"/>
		<form:hidden path="state"/>
		<form:hidden path="field1"/>
		<sys:message content="${message}"/>
		<div id="diaoyan">
		<table class="table-form">
		        <tr >
					<td class="tit" width="15%">公司统一编号：</td><td width="35%" colspan="3">
							<form:input path="field4" htmlEscape="false" maxlength="255" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
				
					</td>
					
				</tr> 
				<tr  id="mainUsesTR">
					<td class="tit" width="10%">主要用途：</td><td colspan="3">
						<form:textarea path="mainUses" htmlEscape="false" maxlength="2550"  rows="4"  class="input-xxlarge"/>
				<span class="help-inline"><font color="red">*</font>描述所要购买设备的主要用途 </span>
				<a title="样例" href='/jeesite/f/view-8-2c25eab0986d486fa59cad6faee6598d.html' target="_Blank" style="padding-left: 100px"><i style="color: green;padding-top: 10px;" class="icon-question-sign"></i></a>
					</td>
					
				</tr>
				<tr  id="situationTR">
					<td class="tit" width="10%">目前情况：</td><td colspan="3">
						<form:textarea path="situation" htmlEscape="false" maxlength="2550" rows="4"   class="input-xxlarge"/>
				<span class="help-inline"><font color="red">*</font>目前公司科研生产需要做什么，是否有此类设备，若有是</br>什么原因需要增加 </span>
					</td>
					
				</tr>
				<tr id="technicalTR">
					<td class="tit" width="10%">技术指标：</td><td colspan="3">
						<form:textarea path="technical" htmlEscape="false" maxlength="2550" rows="4"   class="input-xxlarge"/>
				<span class="help-inline"><font color="red">*</font> 描述本次购买设备的技术指标信息</span>
					</td>
					
				</tr>
				<tr id="budgetTR">
					<td class="tit" width="10%">预算来源：</td><td colspan="3">
						<form:textarea path="budget" htmlEscape="false" maxlength="2550" rows="4"   class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> 自动带出</span>
					</td>
					
				</tr>
				
				<tr id="field3TR" style="display: none">
					<td class="tit" width="15%">询价场次号：</td><td width="35%" colspan="3">
							<form:input path="field3" htmlEscape="false" maxlength="255" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
				
					</td>
					
				</tr>	
				
				<tr id="supplierTR">
					<td class="tit" width="10%">供应商简介(请先选择厂商)：</td><td colspan="3">
						<form:textarea path="supplier" htmlEscape="false" maxlength="2550" rows="4"   class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> 请按照模板替换自己对应的公司。</span>
					</td>
					
				</tr>
				<tr id="resultTR">
					<td class="tit" width="10%">评定结果：</td><td colspan="3">
						<form:textarea path="result" htmlEscape="false" maxlength="2550" rows="4"   class="input-xxlarge required"/>
			<span class="help-inline"><font color="red">*</font> 请按照模板替换自己对应的公司。</span>
					</td>
					
				</tr>
		
				
				<tr>
					<td class="tit" width="10%">其他：</td><td colspan="3">
						<form:textarea path="other" htmlEscape="false" maxlength="2550" rows="5"   class="input-xxlarge "/>
		
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="10%">审签上传：</td><td width="35%" colspan="3">
							<form:hidden id="file" path="file" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="file" type="files" uploadPath="/business/survey/swSurvey" selectMultiple="true" readonly="true"/>
				
					</td>
					
				</tr>
				
		</table>		
		</div>
	
		
		<div id='content'>
		 <legend>调研厂家</legend>
	
				
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>供应商名称</th>
								<th>总价(万元)</th>
								<th>调研方式</th>
								<th>联系人</th>
								<th>联系电话</th>
								<th>备注</th>
							
							</tr>
						</thead>
						<tbody id="swSurveyCompanyList">
						</tbody>
						
					</table>
					</div>
					<script type="text/template" id="swSurveyCompanyTpl">//<!--
						<tr id="swSurveyCompanyList{{idx}}">
							<td class="hide">
                                <input id="checkbox_name[{{idx}}]"  name="checkbox_name[{{idx}}]"  value="{{idx}}"/>
								<input id="swSurveyCompanyList{{idx}}_id" name="swSurveyCompanyList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
                                <input id="swSurveyCompanyList{{idx}}_field1" name="swSurveyCompanyList[{{idx}}].field1" type="hidden" value="{{row.field1}}"/>
								<input id="swSurveyCompanyList{{idx}}_delFlag" name="swSurveyCompanyList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="swSurveyCompanyList{{idx}}_name" name="swSurveyCompanyList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="swSurveyCompanyList{{idx}}_price" name="swSurveyCompanyList[{{idx}}].price" type="text" value="{{row.price}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<select id="swSurveyCompanyList{{idx}}_mode" name="swSurveyCompanyList[{{idx}}].mode" data-value="{{row.mode}}" class="input-small ">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('survey_model')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input id="swSurveyCompanyList{{idx}}_contacts" name="swSurveyCompanyList[{{idx}}].contacts" type="text" value="{{row.contacts}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="swSurveyCompanyList{{idx}}_telephone" name="swSurveyCompanyList[{{idx}}].telephone" type="text" value="{{row.telephone}}" maxlength="255" class="input-small "/>
							</td>
                              <td>
								<input id="swSurveyCompanyList{{idx}}_remarks" name="swSurveyCompanyList[{{idx}}].remarks" type="text" value="{{row.remarks}}" maxlength="255" class="input-small "/>
							</td>
			
							
							
						</tr>//-->
					</script>
					<script type="text/javascript">
						var swSurveyCompanyRowIdx = 0, swSurveyCompanyTpl = $("#swSurveyCompanyTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(swSurvey.swSurveyCompanyList)};
							for (var i=0; i<data.length; i++){
								addRow('#swSurveyCompanyList', swSurveyCompanyRowIdx, swSurveyCompanyTpl, data[i]);
								swSurveyCompanyRowIdx = swSurveyCompanyRowIdx + 1;
							}
						});
					</script>
			<div id="qingshi">
		<legend>按照公司“三重一大”规定要求，设备价格在50万元以上的应通过总经理办公会集体决定决议 <a onclick="plus()"><i id="minus_icon" class="icon-minus"></i></a></legend>
		<div id="plusDiv">
		<table class="table-form">
				
				<tr>
					<td class="tit" width="10%">上传议案附件：</td><td width="35%" colspan="3">
							<form:hidden id="resolutionFile" path="resolutionFile" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="resolutionFile" type="files" uploadPath="/business/survey/swSurvey" selectMultiple="true" readonly="true"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/jeesite/userfiles/1/files/quality/qmresource/qmResource/2020/05/%E4%BE%9B%E5%BA%94%E5%95%86%E5%AF%BC%E5%85%A5%E6%A8%A1%E6%9D%BF%20(3).xlsx">下载模板</a>
					</td>
					
					
				</tr>
				<tr>
					<td class="tit" width="10%">备注：</td><td colspan="3">
						<form:textarea path="resolutionOther" htmlEscape="false" maxlength="2550" rows="5"   class="input-xxlarge "/>
		
					</td>
					
				</tr>
				<tr>
					<td class="tit" width="10%">总经理办公会决议：</td><td colspan="1">
				
		                <form:radiobuttons path="resolution"  items="${fns:getDictList('resolution')}" itemLabel="label" itemValue="value" htmlEscape="false"  />
					</td>
					<td class="tit" width="10%">决议时间：</td><td colspan="1">
			
		                <input name="resolutionDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${swSurvey.resolutionDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</td>
					
				</tr>
		       <tr>
					<td class="tit" width="10%">决议附件：</td><td width="35%" colspan="3">
							<form:hidden id="resolutionResult" path="resolutionResult" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="resolutionResult" type="files" uploadPath="/business/survey/swSurvey" selectMultiple="true" readonly="true"/>
				
					</td>
					
				</tr>
		
		
		
		</table>
		</div>
		</div>
		<div class="form-actions">
			
			<input id="dayin" class="btn" type="button"  onclick="tt(1)" value="调研报告预览"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>