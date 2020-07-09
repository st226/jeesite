<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>著录项管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnState").click(function(){
				saveSelectData() ;
			});
			
			$("#info").click(function(){
				info() ;
			});
			
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
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
			
			 $("#checkedAll").click(function () {
		            if ($(this).attr("checked") == 'checked') { // 全选
		                $("input[name*='checkbox_name']").each(function () {
		                    $(this).attr("checked", true);

		                });
		            } else { // 取消全选
		                $("input[name*='checkbox_name']").each(function () {
		                    $(this).attr("checked", false);
		                });
		            }
		        });
		        $("input[name*='checkbox_name']").click(function(){

		            $("label[name='errorMesg']").hide();

		        });
			
		});
		
		function info(){
			
			$.ajax({
                type:'post',
                url:'${ctx}/resourcebus/tsResourceBus/info',
                cache:false,
                dataType:'json',
                success:function(data){
                   alert("同步成功");
                }
            });
			
		}
		
		
		function saveSelectData(){
			
	           var i=0;
	            var j=0;
	            $("input[name*='checkbox_name']").each(function () {
	                if ($(this).attr("checked") == 'checked') {
	                    $("input[name='columnList["+i+"].isList']").val(1);
	                    j++;
	                }
	                i++;
	            });

	            if(j==0){

	                $("input[name*='checkbox_name']").each(function () {
	                    $(this).after("<label name='errorMesg' class='error'>请勾选数据</label>");
	                    //$(this).attr("name", "_" + $(this).attr("name"));
	                });
	               /* setTimeout(function(){
	                    $("input[name='errorMesg']").hide();//找到对应的标签隐藏
	                },3000);*/
	                return;
	            }

	            $("#inputForm").submit();

	        }
	
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
		<form id="importForm" action="${ctx}/resourcebus/tsResourceBus/import?busType=${busType}" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/resourcebus/tsResourceBus/import/template?&busType=${busType}">下载模板1</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/resourcebus/tsResourceBus/">现行文件文档列表</a></li>
		<shiro:hasPermission name="resourcebus:tsResourceBus:edit"><li><a href="${ctx}/resourcebus/tsResourceBus/form?&busType=${busType}">现行文档添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tsResource" action="${ctx}/resourcebus/tsResourceBus/list?&busType=${busType}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>文件名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnState" class="btn btn-primary" type="button" value="归档"/>
			<li class="btns"><input id="info" class="btn btn-primary" type="button" value="备份文件"/>
			 <li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
		<!-- 	<li class="btns"><input id="btnSubject" class="btn btn-primary" type="button" value="编目"/></li> -->
		<li class="clearfix"></li>
		</ul>
	</form:form>
	<form:form id="inputForm" modelAttribute="genTable" action="${ctx}/resourcebus/tsResourceBus/savezt" method="post" class="form-horizontal">
	<sys:message content="${message}"/>
	<input type="hidden" name="name" value="${busType}"/> 
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
		<!-- 	<th title="选择"><input type="checkbox" id="checkedAll"></th> -->
			 <th></th>
				<c:forEach items="${TsResourceBus}" var="tsResource">
			       <th class="sort-column ${tsResource.columnName}">${tsResource.columnComments}</th>
			      
			</c:forEach>
			<th class="sort-column officeName" width="100px">上传部门</th>
			<th class="sort-column userName" width="100px">上传人</th>
			<th class="sort-column createDate" width="100px">上传时间</th>
			<th class="sort-column state" width="80px">状态</th>

				<shiro:hasPermission name="resourcebus:tsResourceBus:edit"><th width="100px">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${page.list}" var="maps"  varStatus="vs">
			<tr>
			
			<!-- 	      <td>
                         <input type="checkbox" name="checkbox_name[${vs.index}]"/>
                         <input type="hidden" name="columnList[${vs.index}].isList" value="0"/>      
						 <input type="hidden" name="columnList[${vs.index}].id" value="${maps.id}"/>
                 </td> -->
                 <td>
             
                ${(maps.files!=null && maps.files!="")?' <i style="color: red;" class="icon-paper-clip"></i>':''}
                 </td>
				 <c:forEach items="${TsResourceBus}" var="tsResource">
			     <td>   ${not empty tsResource.dictType? fns:getDictLabel(maps[tsResource.columnName],tsResource.dictType,''):maps[tsResource.columnName]}  </td>
              </c:forEach>  
                 <td>${maps.officeName}</td> 
                 <td>${maps.userName}</td> 
                  <td><fmt:formatDate value="${maps.createDate}" pattern="yyyy-MM-dd"/></td> 
                 <td>${fns:getDictLabel(maps.state, 'state', '')}</td> 
				<shiro:hasPermission name="resourcebus:tsResourceBus:edit"><td>
    				<a href="${ctx}/resourcebus/tsResourceBus/form?id=${maps.id}&busType=${busType}&tableComments=GLY">修改</a>
					<a href="${ctx}/resourcebus/tsResourceBus/delete?id=${maps.id}&busType=${busType}&remark1=GLY" onclick="return confirmx('确认要删除该著录项管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</form:form>
</body>
</html>