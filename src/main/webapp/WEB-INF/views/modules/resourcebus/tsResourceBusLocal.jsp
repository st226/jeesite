<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>著录项管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出清点册吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/resourcebus/tsResourceBus/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
			$("#btnSJ").click(function(){
				var local = $("#local").val();
				var busType = $("#busType").val();
				top.$.jBox.open("iframe:${ctx}/resourcebus/tsResourceBus/localSJ?pageSize=8&busType="+busType+"&local="+local, "资源上架",$(top.document).width()-220,$(top.document).height()-180,{
					buttons:{"确定":true}, loaded:function(h){
						$(".jbox-content", top.document).css("overflow-y","hidden");
					},
					closed: function () {
						$("#searchForm").submit();
					}
				});
			});
			$("#btnSubject").click(function(){
				top.$.jBox.confirm("确认要对未编目资源编目吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/resourcebus/tsResourceBus/btnSubject?&busType=${busType}");
						$("#searchForm").submit();
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
	</script>
</head>
<body>
    <div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/resourcebus/tsResourceBus/import?&busType=${busType}" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/resourcebus/tsResourceBus/import/template?&busType=${busType}">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/resourcebus/tsResourceBus/">图书馆典藏管理</a></li>
	</ul>
	
	<form:form id="searchForm" modelAttribute="tsResourceBus" action="${ctx}/resourcebus/tsResourceBus/local" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<form:hidden id="local" path="local"/>
		<form:hidden id="busType" path="busType"/>
		<ul class="ul-form">
			<li><label>资源名称：</label>
				<form:input path="columnName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnSJ" class="btn btn-primary" type="button" value="上架"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出清点册"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<shiro:hasPermission name="resourcebus:tsResourceBus:edit"><th>操作</th></shiro:hasPermission>
				
			       <th>资源类别</th>
			       <th>索书号</th>
			       <th>资源号</th>
			       <th>资源名称</th>
			       <th>作者</th>
			       <th>ISBN</th>
			       <th>出版社</th>
			       <th>关键词</th>
		
			
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="maps">
			<tr>
			<shiro:hasPermission name="resourcebus:tsResourceBus:edit"><td>
    				<a href="${ctx}/resourcebus/tsResourceBus/upDown?id=${maps.id}&busType=${busType}&local=${local}">${not empty maps.local?'下架':'上架'}</a>
				</td></shiro:hasPermission>
				<td>
					${maps.bus_type}
				</td>
				<td>
					${maps.key_code}
				</td>
				<td>
					${maps.resource_code}
				</td>
				<td>
					${maps.name}
				</td>
				<td>
					${maps.author}
				</td>
				<td>
					${maps.isbn}
				</td>
				<td>
					${maps.press}
				</td>
				<td>
					${maps.keyword}
				</td>
				
				
			</tr>
		</c:forEach>
		
		
		
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>