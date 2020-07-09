<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>现行文件管理</title>
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
		function submit1(){
			$.ajax({
                type:'post',
                url:'${ctx}/equipment/metering/sbMetering/saveMetering',
                data:{'field1':'${sbMeteringChild.id}','meteringState':$(meteringState2).attr("checked")?"1":"0",'effectiveDate':$(effectiveDate).val(),'inspectionDate':$(inspectionDate).val(),'enclosure':$(enclosure).val()},
                cache:false,
                dataType:'json',
                success:function(data){
                	parent.window.jBox.close();
                }
            });
		}
		
		
	</script>
</head>
<body>
	<br/>
	<form:form id="inputForm" modelAttribute="sbMeteringChild" action="${ctx}/equipment/metering/sbMetering/saveMetering" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<fieldset>
		<div class="control-group">
			<label class="control-label">是否完成：</label>
			<div class="controls">
				<form:radiobuttons path="meteringState" items="${fns:getDictList('is_leaf')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最新有效期：</label>
			<div class="controls">
					<input id="effectiveDate" name="effectiveDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sbMeteringChild.effectiveDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
			
		</div>
		<div class="control-group">
			<label class="control-label">检测日期：</label>
			<div class="controls">
					<input id="inspectionDate" name="inspectionDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sbMeteringChild.inspectionDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">计量证书：</label>
				<div class="controls">
					<form:hidden id="enclosure" path="enclosure" htmlEscape="false" maxlength="255" class="required"/>
					<sys:ckfinder input="enclosure" type="files" uploadPath="/oa/notify" selectMultiple="true"/>
				</div>
			</div>
		</fieldset>	
		
		
			
		
		
	</form:form>
</body>
</html>