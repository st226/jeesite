<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>测试主子表管理</title>
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
		<li><a href="${ctx}/suntao_test/suntaoTest/">测试主子表列表</a></li>
		<li class="active"><a href="${ctx}/suntao_test/suntaoTest/form?id=${suntaoTest.id}">测试主子表<shiro:hasPermission name="suntao_test:suntaoTest:edit">${not empty suntaoTest.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="suntao_test:suntaoTest:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="suntaoTest" action="${ctx}/suntao_test/suntaoTest/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">name：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">remarks：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">suntao_test_child：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>name</th>
								<th>test</th>
								<shiro:hasPermission name="suntao_test:suntaoTest:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="suntaoTestChildList">
						</tbody>
						<shiro:hasPermission name="suntao_test:suntaoTest:edit"><tfoot>
							<tr><td colspan="4"><a href="javascript:" onclick="addRow('#suntaoTestChildList', suntaoTestChildRowIdx, suntaoTestChildTpl);suntaoTestChildRowIdx = suntaoTestChildRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="suntaoTestChildTpl">//<!--
						<tr id="suntaoTestChildList{{idx}}">
							<td class="hide">
								<input id="suntaoTestChildList{{idx}}_id" name="suntaoTestChildList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="suntaoTestChildList{{idx}}_delFlag" name="suntaoTestChildList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="suntaoTestChildList{{idx}}_name" name="suntaoTestChildList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<c:forEach items="${fns:getDictList('is_leaf')}" var="dict" varStatus="dictStatus">
									<span><input id="suntaoTestChildList{{idx}}_test${dictStatus.index}" name="suntaoTestChildList[{{idx}}].test" type="checkbox" value="${dict.value}" data-value="{{row.test}}"><label for="suntaoTestChildList{{idx}}_test${dictStatus.index}">${dict.label}</label></span>
								</c:forEach>
							</td>
							<shiro:hasPermission name="suntao_test:suntaoTest:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#suntaoTestChildList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var suntaoTestChildRowIdx = 0, suntaoTestChildTpl = $("#suntaoTestChildTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(suntaoTest.suntaoTestChildList)};
							for (var i=0; i<data.length; i++){
								addRow('#suntaoTestChildList', suntaoTestChildRowIdx, suntaoTestChildTpl, data[i]);
								suntaoTestChildRowIdx = suntaoTestChildRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="suntao_test:suntaoTest:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>