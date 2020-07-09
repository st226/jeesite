<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>业务表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#comments").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					$("input[type=checkbox]").each(function(){
						$(this).after("<input type=\"hidden\" name=\""+$(this).attr("name")+"\" value=\""
								+($(this).attr("checked")?"1":"0")+"\"/>");
						$(this).attr("name", "_"+$(this).attr("name"));
					});
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
			$("#searchForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					$("input[type=checkbox]").each(function(){
						$(this).after("<input type=\"hidden\" name=\""+$(this).attr("name")+"\" value=\""
								+($(this).attr("checked")?"1":"0")+"\"/>");
						$(this).attr("name", "_"+$(this).attr("name"));
					});
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
			
			$("#btnSubmitQ").click(function(){
				
				$("#searchForm").attr("action","${ctx}/borrow/tsBorrow/borrowQ");
				$("#searchForm").submit();
				
			});
			
			$("#btnSubmitJ").click(function(){
				var officeid = $("#officeid").val();
				var userid = $("#userid").val();
				var equipmentId = $("#sbcode").val();
				if($("#borrowState").val()=="1"){
					top.$.jBox.open("iframe:${ctx}/borrow/tsBorrow/borrowInfo?user.id="+userid+"&equipmentId="+equipmentId, "借用信息填写",800,500,{
						buttons:{"确定":true}, loaded:function(h){
							$(".jbox-content", top.document).css("overflow-y","hidden");
						}
					});
				//$("#searchForm").attr("action","${ctx}/borrow/tsBorrow/borrowJ");
				//$("#searchForm").submit();
				}else{
					alert("请选择可借阅资源！");
				    return;
				}
			});
			
			
			
		});
		
		var keycode = "";
        var lastTime=null;
        var nextTime=null;
        var lastCode=null;
        var nextCode=null;

        document.onkeydown=function(e){
            //兼容性处理
            if(window.event){
                nextCode = e.keyCode
            } else if(e.which){
                nextCode = e.which
            }
            
            //获取当前时间
            nextTime = new Date().getTime();
              
            if(nextCode==13 && keycode!= "" && nextTime - lastTime <= 30){//回车字符
            	var keys = keycode.split(" ");
                $("#sbcode").val(keys[2]);
                $("#searchForm").attr("action","${ctx}/borrow/tsBorrow/borrowQ");
				$("#searchForm").submit();
                keycode = "";
                lastCode = null;
                lastTime = null;
            }else{//此处可以增加限制nextCode的种类例如数字
                if(lastCode == null && lastTime == null){//初始字母
                    keycode = String.fromCharCode(nextCode);
                }else if(lastCode != null && lastTime != null && nextTime - lastTime <= 30){
                    keycode += String.fromCharCode(nextCode);
                }else{//手动输入
                    keycode = "";
                    lastCode = null;
                    lastTime = null;
                }
                lastCode = nextCode;
                lastTime = nextTime;
            }
        }
        
        function dbOnclick(e,e2,e3){
			var endIsGood = $("#endIsGood"+e3).attr("checked")?"1":"0";
			var endReason = ($("#endReason"+e3).val());
			$.ajax({
                type:'post',
                url:'${ctx}/borrow/sbborrow/sbBorrow/updateState',
                data:{'field4':e,'field5':e2,'endIsGood':endIsGood,'endReason':endReason},
                cache:false,
                dataType:'json',
                success:function(data){
                	alert("操作成功");
                	$("#searchForm").submit();
                }
            });
			
		}

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		
		<li class="active"><a href="${ctx}/borrow/tsBorrow/borrow">仪器设备借用管理</a></li>
        <li><a href="${ctx}/gen/genTable/"></a></li>
	</ul>
	<c:choose>
		<c:when test="${empty tsBorrow.userCode}">
			<form:form id="inputForm" modelAttribute="tsBorrow" action="${ctx}/borrow/tsBorrow/sbBorrow" method="post" class="form-horizontal">
				<form:hidden path="id"/>
				<sys:message content="${message}"/>
				<br/>
				<div class="control-group">
					<label class="control-label">选择人员:</label>
					<div class="controls">
					    <sys:treeselect id="userCode" name="userCode" value="${office.primaryPerson.id}" 
					    labelName="office.primaryPerson.name" labelValue="${office.primaryPerson.name}"
					title="用户" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true"/>
					   

					</div>
					<div class="form-actions">
						<input id="btnSubmit" class="btn btn-primary" type="submit" value="下一步"/>&nbsp;
						<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
					</div>
				</div>
			</form:form>
		</c:when>
		<c:otherwise>
			<form:form id="searchForm" modelAttribute="tsBorrow"  method="post" class="form-horizontal">
				<form:hidden path="id"/>
				<sys:message content="${message}"/>
				<fieldset>
					<legend>人员信息&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;借用设备信息</legend>
					<div class="control-group">
						<label class="control-label">借用部门:</label>
						<div class="controls">
							<form:input id="officeid" path="office.name" htmlEscape="false" maxlength="200" class="required" readonly="true"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							设备编号:
							<form:input path="tsId" id="sbcode" htmlEscape="false" maxlength="200" class="input-xlarge required" />
							&nbsp;<input id="btnSubmitQ" class="btn btn-primary" type="submit" value="查询"/>
							&nbsp;<input id="btnSubmitJ" class="btn btn-primary" type="submit" value="借用"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">借用人:</label>
						<div class="controls">
						    <form:hidden id="userid" path="userCode"/>
						    <form:hidden id="borrowState" path="borrowState"/>
							<form:input   path="user.name" htmlEscape="false" maxlength="200" class="required" readonly="true"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							设备名称:${tsBorrow.tsName}${tsBorrow.busType}
						</div>
					</div>
				<!--  	<div class="control-group">
						<label class="control-label">借用人编号:</label>
						<div class="controls">
						    <form:hidden id="borrowState" path="borrowState"/>
							<form:input path="userCode" htmlEscape="false" maxlength="200" class="required" readonly="true"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							资源编号:${map.resource_code}
						</div>
					</div>-->
					
				
					<legend>设备借用列表</legend>
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>设备名称</th>
				<th>设备编号</th>
				<th>规格型号</th>
				<th>出厂编号</th>
				<th>借用部门</th>
				<th>借用人员</th>
			
				<th>借用状态</th>
				<th>是否计量</th>
				<th>计量时间</th>
				
				<th>归还时间</th>
				<th>是否完好</th>
				<th>说明</th>
				<shiro:hasPermission name="borrow:tsBorrow:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${sbBorrowChilds}" var="sbBorrowChild">
			<tr>
				<td>
					${sbBorrowChild.equipmentName}
				</td>
				
				<td>
					${sbBorrowChild.equipmentSbcode}
				</td>
				<td>
					${sbBorrowChild.equipmentType}
				</td>
				<td>
					${sbBorrowChild.equipmentCccode}
				</td>
				<td>
					${sbBorrowChild.office.name}
				</td>
				<td>
					${sbBorrowChild.user.name}
				</td>
		
				<td>
					${fns:getDictLabel(sbBorrowChild.borrowState, 'bstate', '')}
				</td>
				<td>
					${sbBorrowChild.ismeasurement}
				</td>
				<td>
					<fmt:formatDate value="${sbBorrowChild.measurement}" pattern="yyyy-MM-dd"/>
				</td>
	
				<td>
					<fmt:formatDate value="${sbBorrowChild.endDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
				   <input type="checkbox" id="endIsGood${vs.index}" name="columnList[${vs.index}].endIsGood" value="1" ${sbBorrowChild.endIsGood eq '1' ? 'checked' : ''}/>
				</td>
				<td>
				   <input name="endReason" id="endReason${vs.index}" type="text" value="${sbBorrowChild.endReason}" style="width:80px;margin:0;padding:0;text-align:center;">
				</td>
    		<td>	<a onclick="dbOnclick('${sbBorrowChild.id}','hs','${vs.index}')" >${sbBorrowChild.borrowState=='3'?'归还':''}</a>
					<a onclick="dbOnclick('${sbBorrowChild.id}','hs','${vs.index}')" >${sbBorrowChild.borrowState=='4'?'归还':''}</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
				</fieldset>
				
			</form:form>
		</c:otherwise>
	</c:choose>
</body>
</html>
