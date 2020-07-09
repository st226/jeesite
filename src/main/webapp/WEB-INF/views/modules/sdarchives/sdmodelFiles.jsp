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
		4
		function submit1(){
			
			$.ajax({
                type:'post',
                url:'${ctx}/sdarchives/sdApplication/updateFiles',
                data:{'id':'${sdmodel.id}','imageNo':'${sdmodel.imageNo}','files':$(files).val()},
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
	<form:form id="inputForm" modelAttribute="sdmodel" action="${ctx}/archives/application/updateFiles" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="imageNo"/>
		<sys:message content="${message}"/>	
		<fieldset>
			<div class="control-group">
				<label class="control-label">添加上传文档：</label>
				<div class="controls">
					<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="required"/>
					<sys:ckfinder input="files" type="files" uploadPath="/oa/notify" selectMultiple="true"/>
				</div>
			</div>
		</fieldset>	
		
		
			
		
		<div class="form-actions">
			<input class="btn btn-primary" onclick="submit1()" type="button" value="保 存"/>

		</div>
	</form:form>
</body>
</html>