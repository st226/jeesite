<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>信息化设备管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
			$("#btnDelete").click(function(){
				deleteData();
			});
			
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出设备数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/infomation/infomation/sbInformation/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${ctx}/infomation/infomation/sbInformation");
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function deleteData(){
			var sbType = '${sbType}';
			if(sbType!=null && sbType!=""){
				top.$.jBox.confirm("确认要删除本类型全部数据？","系统提示",function(v,h,f){
					if(v=="ok"){
						top.$.jBox.confirm("确认要删除本类型全部数据？","系统提示",function(v,h,f){
							if(v=="ok"){
								$.ajax({
					                type:'post',
					                url:'${ctx}/infomation/infomation/sbInformation/deleteType',
					                data:{'sbType':sbType},
					                cache:false,
					                dataType:'json',
					                success:function(data){
					                	page();
					                }
					            });
								
							}
						});
					}
				});
			
			}else{
				alert("未选择具体类别不能删除！");
			}
		}
	</script>
</head>
<body>
<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/infomation/infomation/sbInformation/import?busType=${sbType}" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/infomation/infomation/sbInformation/import/template?busType=${sbType}">下载模板1</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/infomation/infomation/sbInformation/">信息化设备列表</a></li>
		<shiro:hasPermission name="infomation:infomation:sbInformation:edit"><li><a href="${ctx}/infomation/infomation/sbInformation/form?sbType=${sbType}">信息化设备添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sbInformation" action="${ctx}/infomation/infomation/sbInformation" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="sbType" name="sbType" type="hidden" value="${sbType}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
		<li><label>综合查询：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>型号：</label>
				<form:input path="type" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>密级：</label>
				 <form:select path="sbSecret" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('MODEL_CLASS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li><li class="clearfix"></li>
			<li><label>IP地址：</label>
				<form:input path="sbIpaddress" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>MAC地址：</label>
				<form:input path="sbMacaddress" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>使用情况：</label>
			    <form:select path="useinfo" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('useinfo')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			 <li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
			 <li class="btns">	<input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			 <li class="btns">	<input id="btnDelete" class="btn btn-primary" type="button" value="删除本项"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			<th title="选择"><input type="checkbox" id="checkedAll">
					<c:forEach items="${TsResourceBus}" var="tsResource">
			       <th class="sort-column ${tsResource.columnName}" width="${tsResource.width}px">${tsResource.columnComments}</th>
			</c:forEach>
				<shiro:hasPermission name="infomation:infomation:sbInformation:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sbInformation">
			<tr>
				  <td>
                         <input type="checkbox" name="checkbox_name[${vs.index}]"/>
                         <input type="hidden" name="columnList[${vs.index}].isList" value="0"/>      
						 <input type="hidden" name="columnList[${vs.index}].id" value="${sbInformation.id}"/>
                 </td>
				 <c:forEach items="${TsResourceBus}" var="tsResource">
			     <td>   ${not empty tsResource.dictType? fns:getDictLabel(sbInformation[tsResource.columnName],tsResource.dictType,''):sbInformation[tsResource.columnName]}  </td>
              </c:forEach>  
				<shiro:hasPermission name="infomation:infomation:sbInformation:edit"><td>
    				<a href="${ctx}/infomation/infomation/sbInformation/form?id=${sbInformation.id}">修改</a>
					<a href="${ctx}/infomation/infomation/sbInformation/delete?id=${sbInformation.id}" onclick="return confirmx('确认要删除该信息化设备吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>