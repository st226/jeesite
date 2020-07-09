<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采购任务管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			// FixTable("contentTable", 2, 1200, 600);
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		var index= 0 ;
		
	
		function onAddTR(trIndex,orderId)
		{
		
		var tb = document.getElementById("contentTable");
		if(index!=0){
			tb.deleteRow(index);
		}
		
		if(index==trIndex+2){
			index = 0 ;
		}else{
			var newTr = tb.insertRow(trIndex+2);//添加新行，trIndex就是要添加的位置
			var newTd1 = newTr.insertCell();
			newTd1.colSpan =19;
			
			$.ajax({
                type:'post',
                url:'${ctx}/business/product/swProduct/getProduct',
                data:{'orderId':orderId},
                cache:false,
                dataType:'json',
           success:function(data){
        	   var tableHtml = "<table class='table table-striped table-bordered'  style='max-width:1000px'><tr><th>设备名称</th><th>单价</th><th>数量</th><th>总价</th><th>责任部门</th><th>责任人</th><th>拟采购规格型号</th><th>拟采购单位</th></tr>";
           	for(var i = 0 ;i<data.length;i++){
           		tableHtml=tableHtml+"<tr><td>"+data[i].productName+"</td><td>"+data[i].unitPrice+"</td><td>"+data[i].productAmount+"</td><td>"+data[i].totalPrice+"</td><td>"+data[i].officeName+"</td><td>"+data[i].userName+"</td><td>"+data[i].productType+"</td><td>"+data[i].productMade+"</td></tr>";
           	}
        tableHtml += "</table>";
        newTd1.innerHTML =tableHtml;
           }
       });
			
			index=trIndex+2;
		}
		
		
		
		
		}
			
			
		
	</script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a href="${ctx}/business/order/swOrder/">采购任务列表</a></li>
	    <li class="active"><a href="${ctx}/business/order/swOrder/listYear">采购进度</a></li>
	
	</ul>
	<form:form id="searchForm" modelAttribute="swOrder" action="${ctx}/business/order/swOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>综合查询：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered " style="max-width: 2700px">
		<thead>
			<tr>
		
				<th width="150px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目名称&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
				<th width="150px">采购人</th>
				<th width="150px">1月</th>
				<th width="150px">2月</th>
				<th width="150px">3月</th>
				<th width="150px">4月</th>
				<th width="150px">5月</th>
				<th width="150px">6月</th>
				<th width="150px">7月</th>
				<th width="150px">8月</th>
				<th width="150px">9月</th>
				<th width="150px">10月</th>
				<th width="150px">11月</th>
				<th width="150px">12月</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="swOrder" varStatus="vs">
			<tr>
			
				<td><a href="${ctx}/business/order/swOrder/edit?id=${swOrder.id}">
					${swOrder.name}
					</a>
						<a onclick="onAddTR(${vs.index},'${swOrder.id}')" ><i style="color: #2fa4e7;" class="icon-chevron-down"></i></a>
				</td>
				
				
				
				
				<td>
				<font color="blue">${userId==swOrder.user.id?swOrder.userName:''}</font>
				   ${userId==swOrder.user.id?'':swOrder.userName}
				</td>
				<td>
					${swOrder.field1Text}
					
				</td>
				<td>
					${swOrder.field2Text}
					
				</td>
				<td>
					${swOrder.field3Text}
					
				</td>
				<td>
					${swOrder.field4Text}
					
				</td>
				<td>
					${swOrder.field5Text}
					
				</td>
				<td>
					${swOrder.field6Text}
					
				</td>
				<td>
					${swOrder.field7Text}
					
				</td>
				<td>
					${swOrder.field8Text}
					
				</td>
				<td>
					${swOrder.field9Text}
					
				</td>
				<td>
					${swOrder.field10Text}
					
				</td>
				<td>
					${swOrder.field11Text}
					
				</td>
				<td>
					${swOrder.field2}
					
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>