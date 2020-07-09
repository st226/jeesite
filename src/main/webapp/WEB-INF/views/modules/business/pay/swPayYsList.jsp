<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>请款单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
		<li class="active"><a href="${ctx}/business/pay/swPay/listPay">预算列表</a></li>

	</ul>
	<form:form id="searchForm" modelAttribute="swPay" action="${ctx}/business/pay/swPay/listPay" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
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
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>采购任务</th>
				<th>厂家</th>
				<th>合同编号</th>
				<th>合同金额</th>
				<th>已付款</th>
				<th>本次付款计划</th>
				
				<th>本次付款比例</th>
				<th>付款后累计欠款余额</th>
		
				<th>实际到货时间</th>
				<th>所属项目</th>
				<th>紧急程度</th>
				<th>责任人</th>
				<th>付款截止时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="swPay" varStatus="vs">
			<tr>
				<td>
					${swPay.name}
				</td>
				<td>
					${swPay.product_made}
				</td>
				<td>
					${swPay.contract_code}
				</td>
				<td>
					${swPay.contrate_price}
				</td>
				<td>
					${swPay.contrate_paid}
				</td>
				<td>
					${swPay.contrate_treat}
				</td>
				<td>
					${swPay.bl}
				</td> 
				<td>
					${swPay.sy}
				</td> 
			
				<td>
	                ${fns:getDictLabel(swPay.orderState, 'product_state', '')}
				</td> 
				<td>
					${swPay.code}
				</td> 
				<td>
				
				   <input type="checkbox" id="project_class${vs.index}" name="columnList[${vs.index}].project_class" onclick="checkOk('${swPay.id}','${swPay.type}','${vs.index}')" value="1" ${swPay.project_class eq '1' ? 'checked' : ''}/>
				</td>
			
		
				<td>
					${swPay.user_name}
				</td> 
				<td>
					${swPay.project_date}
				</td>
				
				
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>