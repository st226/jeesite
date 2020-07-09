<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>三单维护管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sdarchives/sdApplication/cdzList">质疑单列表</a></li>
		<li class="active"><a href="${ctx}/sdarchives/sdApplication/czdform?id=${sdApplication.id}">质疑单维护<shiro:hasPermission name="sdarchives:sdApplication:edit">${not empty sdApplication.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sdarchives:sdApplication:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sdApplication" action="${ctx}/sdarchives/sdApplication/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="sdType" value='cdz'/>
		<sys:message content="${message}"/>		
		<fieldset>
			<table class="table-form">
			<tr>
					<td class="tit">申请部门：</td><td colspan="3">
						<sys:treeselect id="office" name="office.id" value="${sdApplication.office.id}" labelName="office.name" labelValue="${sdApplication.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
				</tr>
			
			<tr>
			<td class="tit">申请时间：</td><td>
						<input name="apllyDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sdApplication.apllyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				
					<td class="tit">申请人：</td><td>
						<sys:treeselect id="user" name="user.id" value="${sdApplication.user.id}" labelName="user.name" labelValue="${sdApplication.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
					
				</tr>
				
			</table>
		</fieldset>	
			<legend>三单详情</legend>
				
			
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
		
						<thead>
							<tr>
								<th class="hide"></th>
								<th>型号</th>
								<th>图号</th>
								<th>超差、待料、质疑单号</th>
								<th>文件代号</th>
								<th>有效期限</th>
								<th>发送单位</th>
								<th>承办者</th>
								<th>设计</th>
								<th>使方代表意见</th>
								<th>批准日期</th>
								<th>归档人</th>
								<th>归档日期</th>
								
								<th>上传超差、待料、质疑单</th>
								
								<shiro:hasPermission name="sdarchives:sdApplication:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="sdModelList">
						</tbody>
						<shiro:hasPermission name="sdarchives:sdApplication:edit"><tfoot>
							<tr><td colspan="26"><a href="javascript:" onclick="addRow('#sdModelList', sdModelRowIdx, sdModelTpl);sdModelRowIdx = sdModelRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="sdModelTpl">//<!--
						<tr id="sdModelList{{idx}}">
							<td class="hide">
								<input id="sdModelList{{idx}}_id" name="sdModelList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="sdModelList{{idx}}_delFlag" name="sdModelList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
                            <td>
								<sys:treeselect id="sdModelList{{idx}}_modelId" name="sdModelList[{{idx}}].modelId" cssStyle="width:100px"  value="{{row.modelId}}" labelName="sdModelList{{idx}}.modelName" labelValue="{{row.modelName}}"
									title="选择型号/设备" url="/resourcetype/tsResourceType/treeData"  cssClass="" allowClear="true" />
							</td>
<td>
								<input id="sdModelList{{idx}}_imageNo" name="sdModelList[{{idx}}].imageNo" type="text"  style="width:100px"  value="{{row.imageNo}}" maxlength="200" class="input-small "/>
							</td>
							<td>
								<input id="sdModelList{{idx}}_sdNumber" name="sdModelList[{{idx}}].sdNumber" type="text" style="width:100px" value="{{row.sdNumber}}" maxlength="200" class="input-small "/>
							</td>
							<td>
								<input id="sdModelList{{idx}}_wjNumber" name="sdModelList[{{idx}}].wjNumber" type="text" style="width:100px"  value="{{row.wjNumber}}" maxlength="200" class="input-small "/>
							</td>
                            <td>
								<input id="sdModelList{{idx}}_yxqx" name="sdModelList[{{idx}}].yxqx" type="text" value="{{row.yxqx}}" maxlength="500" class="input-small "/>
							</td>
                               <td>
								<sys:treeselect id="sdModelList{{idx}}_office" name="sdModelList[{{idx}}].office.id" cssStyle="width:100px" value="{{row.office.id}}" labelName="sdModelList[{{idx}}].officeName" labelValue="{{row.officeName}}"
									title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"  checked="true"/>
							</td>
                             <td>
								<input id="sdModelList{{idx}}_cbz" name="sdModelList[{{idx}}].cbz" type="text"  style="width:100px" value="{{row.cbz}}" maxlength="50" class="input-small "/>
							</td>
                            <td>
								<input id="sdModelList{{idx}}_sjr" name="sdModelList[{{idx}}].sjr" type="text"  style="width:100px"  value="{{row.sjr}}" maxlength="50" class="input-small "/>
							</td>
                            <td>
								<input id="sdModelList{{idx}}_syffyj" name="sdModelList[{{idx}}].syffyj" type="text"  style="width:100px"  value="{{row.syffyj}}" maxlength="500" class="input-small "/>
							</td>
                            <td>
								<input id="sdModelList{{idx}}_pzrq" name="sdModelList[{{idx}}].pzrq" type="text"  style="width:100px"  readonly="readonly" maxlength="20" class="input-medium Wdate "
									value="{{row.pzrq}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
							</td>
                            <td>
								<input id="sdModelList{{idx}}_gdr" name="sdModelList[{{idx}}].gdr" type="text"  style="width:100px"  value="{{row.gdr}}" maxlength="50" class="input-small "/>
							</td>
							<td>
								<input id="sdModelList{{idx}}_gdrq" name="sdModelList[{{idx}}].gdrq" type="text"  style="width:100px"  readonly="readonly" maxlength="20" class="input-medium Wdate "
									value="{{row.gdrq}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
							</td>

                              <td>
								<input id="sdModelList{{idx}}_files" name="sdModelList[{{idx}}].files" type="hidden" value="{{row.files}}" maxlength="255"/>
								<sys:ckfinder input="sdModelList{{idx}}_files" type="files" uploadPath="/oa/notify" selectMultiple="true"/>
						</td>



							<shiro:hasPermission name="sdarchives:sdApplication:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#sdModelList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var sdModelRowIdx = 0, sdModelTpl = $("#sdModelTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(sdApplication.sdModelList)};
							for (var i=0; i<data.length; i++){
								addRow('#sdModelList', sdModelRowIdx, sdModelTpl, data[i]);
								sdModelRowIdx = sdModelRowIdx + 1;
							}
						});
					</script>
			
		<div class="form-actions">
			<shiro:hasPermission name="sdarchives:sdApplication:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>