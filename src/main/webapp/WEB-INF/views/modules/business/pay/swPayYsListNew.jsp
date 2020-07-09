<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>请款单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出预算数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/business/pay/swPay/export");
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
		
		function checkOk(id,type,vs){
			var project_class = $("#project_class"+vs).attr("checked")?"1":"0";
			$.ajax({
                type:'post',
                url:'${ctx}/business/pay/swPay/updateProjectClass',
                data:{'orderId':id,'type':type ,'project_class':project_class},
                cache:false,
                dataType:'json',
                success:function(data){
            
                }
            });
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/business/pay/swPay/listPayNew">预算列表</a></li>

	</ul>
	<form:form id="searchForm" modelAttribute="swPay" action="${ctx}/business/pay/swPay/listPayNew" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>起始时间：</label>
				<input id="completionTime" name="completionTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${swPay.completionTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" onchange="tt()" />
			</li>
			<li><label>结束时间：</label>
				<input id="expectedCompletionTime" name="expectedCompletionTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${swPay.expectedCompletionTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" onchange="tt()" />
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th rowspan="2" style="text-align: center;vertical-align: middle;">设备名称</th>
				<th rowspan="2" style="text-align: center;vertical-align: middle;">执行状态</th>
				<th rowspan="2" style="text-align: center;vertical-align: middle;">数量</th>
				<th rowspan="2" style="text-align: center;vertical-align: middle;">型号/规格</th>
				<th rowspan="2" style="text-align: center;vertical-align: middle;">厂家</th>
				<th rowspan="2" style="text-align: center;vertical-align: middle;">需求部门</th>
				<th rowspan="2"  style="text-align: center;vertical-align: middle;">金额</th>
				<th rowspan="2"  class="sort-column code"   style="text-align: center;vertical-align: middle;">合同编号</th>
				<th colspan="2" style="text-align: center;">首付款</th>
			
				<th colspan="2" style="text-align: center;">到货款</th>
				
				<th colspan="2" style="text-align: center;">尾款</th>
				
				<th  rowspan="2"  style="text-align: center;vertical-align: middle;">已付金额</th>
				<th  rowspan="2"  style="text-align: center;vertical-align: middle;">欠款</th>
				<th  rowspan="2"  style="text-align: center;vertical-align: middle;">计划付款金额</th>
				<th  rowspan="2"  style="text-align: center;vertical-align: middle;">计划付款时间</th>
				<th rowspan="2" class="sort-column field7_date_end"  style="text-align: center;vertical-align: middle;">到货时间</th>
		 
				<th rowspan="2" class="sort-column user_name"  style="text-align: center;vertical-align: middle;">责任人</th>
				<th rowspan="2"  class="sort-column project_type"   style="text-align: center;vertical-align: middle;">计划类型</th>
			</tr>
			<tr>
				
			
				<th style="text-align: center;">金额</th>
				<th style="text-align: center;">付款时间</th>
				
				<th style="text-align: center;">金额</th>
				<th style="text-align: center;">付款时间</th>
			
				<th style="text-align: center;">金额</th>
		
				<th style="text-align: center;">付款时间</th>
				
			
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="swPay" varStatus="vs">
			<tr>
				<td>
					${swPay.name}
				</td>
				<td>
					 ${fns:getDictLabel(swPay.type, 'swPay_state', '')}
				</td>
				<td>
					${swPay.product_amount}
				</td>
				<td>
					${swPay.product_type}
				</td>
				<td>
					${swPay.product_made}
				</td>
				<td>
					${swPay.office_name}
				</td>
				<td>
					${swPay.amount}
				</td>
				<td>
					${swPay.code}
				</td>
				<td>
				
					<font color="green">${swPay.completion_time1!=null?swPay.treat1:''}</font>
				    <font color="red">${swPay.completion_time1==null?swPay.treat1:""}</font>
				</td>
				<td>
				<font color="green">${swPay.completion_time1!=null?swPay.completion_time1:''}</font>
				    <font color="red">${swPay.completion_time1==null?swPay.completion_time1:""}</font>
				
				</td>
				<td>
				<font color="green">${swPay.completion_time2!=null?swPay.treat2:''}</font>
				<font color="red">${swPay.completion_time2==null?swPay.treat2:""}</font>
				
				</td>
				<td>
					<font color="green">${swPay.completion_time2!=null?swPay.completion_time2:''}</font>
				    <font color="red">${swPay.completion_time2==null?swPay.completion_time2:""}</font>
				</td> 
				<td>
					<font color="green">${swPay.completion_time3!=null?swPay.treat3:''}</font>
				<font color="red">${swPay.completion_time3==null?swPay.treat3:""}</font>
				</td> 
				<td>
				    <font color="green">${swPay.completion_time3!=null?swPay.completion_time3:''}</font>
				    <font color="red">${swPay.completion_time3==null?swPay.completion_time3:""}</font>
	
				</td> 
				<td>
					${swPay.contrate_paid}
				</td> 
				<td>
					${swPay.treat}
				</td> 
				
				
				<td>
					${swPay.contrate_treat}
				</td> 
				<td>
					${swPay.project_date}
				</td> 
				<td>
					${swPay.field7_date_end}
				</td> 
				<td>
					${swPay.user_name}
				</td> 
				<td>
					${swPay.project_type}
				</td> 
			
				
				
				
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>