<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>征订信息维护管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/sys/user/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
			$("#btnRepeat").click(function(){
				alert("无重复信息");
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
		<form id="importForm" action="${ctx}/purchase/tsPurchase/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/purchase/tsPurchase/import/template">下载模板</a>
		</form>
	</div>
	
	
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/purchase/tsPurchase/">征订信息维护列表</a></li>
		<shiro:hasPermission name="purchase:tsPurchase:edit"><li><a href="${ctx}/purchase/tsPurchase/form">征订信息维护添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tsPurchase" action="${ctx}/purchase/tsPurchase/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		
		   <li><label>申请部门：</label>
				<sys:treeselect id="office" name="office.id" value="${tsPurchase.office.id}" labelName="office.name" labelValue="${tsPurchase.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			
			<li><label>采购类型：</label>
				<form:select path="cgType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('cg_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>采购状态：</label>
				<form:select path="zyState" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('zy_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="clearfix"></li>
			
			<li><label>申请人：</label>
				<sys:treeselect id="user" name="user.id" value="${tsPurchase.user.id}" labelName="user.name" labelValue="${tsPurchase.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>资源名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
			<input id="btnRepeat" class="btn btn-primary" type="button" value="查重"/>
			
			
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			   
				<th>资源类型</th>
				<th>资源名称</th>
				<th>作者</th>
				<th>出版社</th>
				<th>采购类型</th>
				<th>ISBN</th>
				<th>页数</th>
				<th>价格</th>
				<th>数量</th>
				<th>采购状态</th>
				<th>申请部门</th>
				<th>申请人</th>
				<th>申请时间</th>
				<th>是否重复</th>
				<th>备注信息</th>
				<th>订单</th>
				<shiro:hasPermission name="purchase:tsPurchase:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tsPurchase" varStatus="vs" >
			<tr>
			    
				<td><a href="${ctx}/purchase/tsPurchase/form?id=${tsPurchase.id}">
					${fns:getDictLabel(tsPurchase.zyType, 'zy_type', '')}
				</a></td>
				<td>
					${tsPurchase.name}
				</td>
				<td>
					${tsPurchase.author}
				</td>
				<td>
					${tsPurchase.press}
				</td>
				<td>
					${fns:getDictLabel(tsPurchase.cgType, 'cg_type', '')}
				</td>
				<td>
					${tsPurchase.isbn}
				</td>
				<td>
					${tsPurchase.pages}
				</td>
				<td>
					${tsPurchase.price}
				</td>
				<td>
					${tsPurchase.count}
				</td>
				<td>
					${fns:getDictLabel(tsPurchase.zyState, 'zy_state', '')}
				</td>
				<td>
					${tsPurchase.office.name}
				</td>
				<td>
					${tsPurchase.user.name}
				</td>
				<td>
					<fmt:formatDate value="${tsPurchase.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${fns:getDictLabel(tsPurchase.isDouble, 'is_repeat', '')}
				</td>
				
				<td>
					${tsPurchase.remarks}
				</td>
				<td>
					${tsPurchase.orderId}
				</td>
				<shiro:hasPermission name="purchase:tsPurchase:edit"><td>
    				<a href="${ctx}/purchase/tsPurchase/form?id=${tsPurchase.id}">修改</a>
					<a href="${ctx}/purchase/tsPurchase/delete?id=${tsPurchase.id}" onclick="return confirmx('确认要删除该征订信息维护吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>