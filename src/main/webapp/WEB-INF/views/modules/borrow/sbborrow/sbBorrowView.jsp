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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
	    <li ><a onclick="history.go(-1)">借用列表</a></li>
		<li class="active"><a href="${ctx}/borrow/sbborrow/sbBorrow/form">详细信息</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sbBorrow" action="${ctx}/borrow/sbborrow/sbBorrow/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<fieldset>
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
				
				
			
			
			</tr>
		</c:forEach>
					</table>
					
				
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
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>