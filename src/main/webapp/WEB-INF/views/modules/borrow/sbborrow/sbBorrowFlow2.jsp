<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备借用管理</title>
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
		function delRow(obj, prefix,rowid){
			$.ajax({
                type:'post',
                url:'${ctx}/borrow/sbborrow/sbBorrow/deleteSbBorrow',
                data:{'sbId':rowid},
                cache:false,
                dataType:'json',
            success:function(data){
            	
            }
        });
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
		function test(pid){
			top.$.jBox.open("iframe:http://10.193.200.16:8088/default/archives/archives/imageFlow.jsp?id=${sbBorrow.processinstid}", 
					"流程跟踪",810,$(top.document).height()-110,{
						buttons:{"确定":true}, loaded:function(h){
							$(".jbox-content", top.document).css("overflow-y","hidden");
						}
					});
		
			
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		
	    <li class="active"><a href="${ctx}/equipment/equipmentbus/sbEquipmentBus/borrowList">流程审批</a></li>
		<li ><a onclick="test(${sbBorrow.processinstid})">流程跟踪</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sbBorrow" action="${ctx}/borrow/sbborrow/sbBorrow/submit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="workItem.processInstID"/>
		<form:hidden path="workItem.workItemID"/>
		<form:hidden path="workItem.workItemName"/>
		<form:hidden path="workItem.processChName"/>
		<form:hidden path="workItem.activityDefID"/>
		<form:hidden id="flag" path="workItem.workItemType"/>
		<sys:message content="${message}"/>		
		<fieldset>
		    <legend >${sbBorrow.workItem.workItemName}</legend>
			<table class="table-form">
				
				
				<tr>
					<td class="tit">借用部门：</td><td>
						${sbBorrow.office.name}
					</td><td class="tit">借用人：</td><td>
						${sbBorrow.user.name}
					</td>
				</tr>
				<tr>
					<td class="tit">使用地方：</td><td>
					${sbBorrow.useLocal}
						

					</td><td class="tit">拟借用至：</td><td>
					<fmt:formatDate value="${sbBorrow.borrowTo}" pattern="yyyy-MM-dd"/>
					
					</td>
				</tr>
				
				
				
			</table>
		</fieldset>	
		<legend>设备借用详情</legend>
			
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								
								<th width="200px">设备名称</th>
								<th width="200px">规格型号</th>
								<th width="200px">设备编号</th>
								<th width="200px">出厂编号</th>
								<th width="200px">是否计量</th>
								<th width="200px">计量有效期</th>
								<th width="200px">附件</th>
								<th width="200px">是否完好</th>
								<th width="300px">说明</th>
						
							</tr>
						</thead>
						
						
						
							<c:forEach items="${sbBorrow.sbBorrowChildList}" var="testData">
			<tr>
				<td>
					${testData.equipmentName}
				</td>
				<td>
					${testData.equipmentType}
				</td>
				<td>
					${testData.equipmentSbcode}
				</td>
				<td>
					${testData.equipmentCccode}
				</td>
				<td>
					${testData.ismeasurement}
				</td>
				<td>
					 <fmt:formatDate value="${testData.measurement}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
				    
				   
					${testData.enclosure}
				</td>
				<td>
				${fns:getDictLabel(testData.isGood,'is_leaf', '')}
				</td>
				<td>
					${testData.reason}
				</td>
				
			
			
			</tr>
		</c:forEach>
					</table>
					<legend >审批详情</legend>
					<table class="table-form">
						<c:forEach items="${sbBorrow.examineList}" var="testData">
			<tr>
					<td  width="10%" class="tit" rowspan="2">${testData.examineExamineisagree}</td>
					
					<td  colspan="6">
						${testData.examineExplain}
					</td>
				</tr>
				<tr>
					
					<td colspan="2"  width="20%">
						${testData.examinePerson}
					</td>
					
					<td colspan="2"  width="20%">
						<fmt:formatDate value="${testData.examineDate}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
		</c:forEach>
		<tr>
					<td class="tit">您的意见</td>
					<td colspan="6">
						<form:textarea path="field1" class="required" rows="5" maxlength="20" cssStyle="width:500px"/>
					</td>
				</tr></table>
					<script type="text/template" id="sbBorrowChildTpl">//<!--
						<tr id="sbBorrowChildList{{idx}}">
							<td class="hide">
								<input id="sbBorrowChildList{{idx}}_id" name="sbBorrowChildList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
                                <input id="sbBorrowChildList{{idx}}_equipmentId" name="sbBorrowChildList[{{idx}}].equipmentId" type="hidden" value="{{row.equipmentId}}"/>
								<input id="sbBorrowChildList{{idx}}_delFlag" name="sbBorrowChildList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
						
							<td>
								<input id="sbBorrowChildList{{idx}}_equipmentName" name="sbBorrowChildList[{{idx}}].equipmentName" type="text" value="{{row.equipmentName}}" maxlength="199" class="input-mini "/>
							</td>
							<td>
								<input id="sbBorrowChildList{{idx}}_equipmentType" name="sbBorrowChildList[{{idx}}].equipmentType" type="text" value="{{row.equipmentType}}" maxlength="199" class="input-mini "/>
							</td>
							<td>
								<input id="sbBorrowChildList{{idx}}_equipmentSbcode" name="sbBorrowChildList[{{idx}}].equipmentSbcode" type="text" value="{{row.equipmentSbcode}}" maxlength="199" class="input-mini "/>
							</td>
							<td>
								<input id="sbBorrowChildList{{idx}}_equipmentCccode" name="sbBorrowChildList[{{idx}}].equipmentCccode" type="text" value="{{row.equipmentCccode}}" maxlength="19" class="input-small "/>
							</td>
							
						
							<td>

                                <input id="sbBorrowChildList{{idx}}_ismeasurement" name="sbBorrowChildList[{{idx}}].ismeasurement" type="checkbox" value='1' data-value="{{row.ismeasurement}}"  maxlength="199" class="input-small "/>
							   
                            </td>
							<td>
								<input id="sbBorrowChildList{{idx}}_measurement" name="sbBorrowChildList[{{idx}}].measurement" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									value="{{row.measurement}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
							</td>
						    
                            <td>
								<input id="sbBorrowChildList{{idx}}_enclosure" name="sbBorrowChildList[{{idx}}].enclosure" type="text" value="{{row.enclosure}}" maxlength="19" class="input-small "/>
							</td>
                            <td>

                                <input id="sbBorrowChildList{{idx}}_isGood" name="sbBorrowChildList[{{idx}}].isGood" type="checkbox" value='1' data-value="{{row.isGood}}"  maxlength="199" class="input-small "/>
							   
                            </td>
                             <td>
								<input id="sbBorrowChildList{{idx}}_reason" name="sbBorrowChildList[{{idx}}].reason" type="text" value="{{row.reason}}" maxlength="19" class="input-small "/>
							</td>
							
							
							
							
							
						</tr>//-->
					</script>
					<script type="text/javascript">
						var sbBorrowChildRowIdx = 0, sbBorrowChildTpl = $("#sbBorrowChildTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(sbBorrow.sbBorrowChildList)};
							for (var i=0; i<data.length; i++){
								addRow('#sbBorrowChildList', sbBorrowChildRowIdx, sbBorrowChildTpl, data[i]);
								sbBorrowChildRowIdx = sbBorrowChildRowIdx + 1;
							}
						});
					</script>
			
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="提交" onclick="$('#flag').val('Y')"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>