<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出供应商数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/quality/qmsupplier/qmSupplier/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
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

	
	<form:form id="searchForm" modelAttribute="qmSupplier" action="${ctx}/quality/qmsupplier/qmSupplier/query" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>供应商性质：</label>
				<form:select path="supplierNature" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('supplier_nature')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>选择</th>
				<th>供应商名称</th>
				<th>供应商简称</th>
				<th>规格型号</th>
				<th>产品名称</th>
				<th>重要程度</th>
				<th>评价等级</th>
				<th>单位性质</th>
				<th>业务范围考核</th>
				<th>类别</th>
				<th>产品类别</th>
				<th>备注</th>
	
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="qmSupplier" varStatus="vs" >
			<tr>
			     <td>
                         <input type="checkbox" name="checkbox_name[${vs.index}]"/>
                     
						 <input type="hidden" name="columnList[${vs.index}].id" value="${qmSupplier.id}"/>
						 <input type="hidden" name="columnList[${vs.index}].name" value="${qmSupplier.supplierName}"/>
						 <input type="hidden" name="columnList[${vs.index}].product" value="${qmSupplier.product}"/>
						 <input type="hidden" name="columnList[${vs.index}].assist" value="${qmSupplier.assist}"/>
                 </td>
			
			
				<td title="${qmSupplier.supplierName}"><a href="${ctx}/quality/qmsupplier/qmSupplier/form?id=${qmSupplier.id}">
					${fns:abbr(qmSupplier.supplierName,30)} 
				</a></td>
				<td>
					${qmSupplier.supplierNameSimple}
				</td>
				<td title="${qmSupplier.model}">
					${fns:abbr(qmSupplier.model,20)}  
				</td>
				<td title="${qmSupplier.product}">
					${fns:abbr(qmSupplier.product,20)}   
				</td>
				<td>
			
					${fns:getDictLabel(qmSupplier.importance, 'supplier_importance', '')}
				</td>
				<td>
					${qmSupplier.evaluationLevel}
				</td>
				<td>
					${fns:getDictLabel(qmSupplier.supplierNature, 'supplier_nature', '')}
				</td>
				<td title="${qmSupplier.scope}">
					${fns:abbr(qmSupplier.scope,30)}    
				</td>
			
				<td>
					${fns:getDictLabel(qmSupplier.expansion, 'expansion', '')}
				</td>
				<td>
					${qmSupplier.productTypeName}
				</td>
				<td>
					${qmSupplier.field1}
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>