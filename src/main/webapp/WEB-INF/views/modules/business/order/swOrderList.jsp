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
		<li class="active"><a href="${ctx}/business/order/swOrder/">采购任务列表</a></li>
	    <li><a href="${ctx}/business/order/swOrder/listYear">采购进度</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="swOrder" action="${ctx}/business/order/swOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		
			<li><label>综合查询：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>资产类别：</label>
				<form:select path="identification"  class="input-medium " > 
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('zichan_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered " style="max-width: 2700px">
		<thead>
			<tr>
				
				<th width="150px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目名称&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
				<th width="150px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
				<th width="150px">采购方式</th>
			   
			    <th width="150px">预算额度</th>
				<th width="150px">采购人</th>
				<th width="150px">调研人</th>
				<th width="150px">&nbsp;调研/特殊项目&nbsp;</th>
				<th width="150px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;招投标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
				<th width="150px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商务谈判&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
				<th width="150px">&nbsp;&nbsp;&nbsp;合同/申购单&nbsp;&nbsp;&nbsp;</th>
				<th width="150px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;首付款&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
				<th width="150px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;到货/验收&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
				<th width="150px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;到货款&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
				<th width="150px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;入库/分发&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
				<th width="150px">&nbsp;&nbsp;&nbsp;&nbsp;验收单/固定资产卡片&nbsp;&nbsp;&nbsp;&nbsp;</th>
				<th width="150px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;尾款&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
				<th width="150px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报销&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
				<shiro:hasPermission name="business:order:swOrder:edit"><th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="swOrder" varStatus="vs">
			<tr>
				
				<td title="${swOrder.name}"> <a href="${ctx}/business/order/swOrder/edit?id=${swOrder.id}">
				${fns:abbr(swOrder.name,26)}
				
					</a>&nbsp;&nbsp;
					<a onclick="onAddTR(${vs.index},'${swOrder.id}')" ><i style="color: #2fa4e7;" class="icon-chevron-down"></i></a>
                </td>
				<td>
					<a href="${ctx}/business/order/swOrder/progress?id=${swOrder.id}">${fns:getDictLabel(swOrder.state, 'order_state', '')}</a>&nbsp;
				  ${(swOrder.field1Date>time && swOrder.state=='1') ?' <i style="color: green;" class="icon-circle-arrow-right"></i>':''}
                    ${(swOrder.field1Date<time && swOrder.state=='1')?' <i style="color: red;" class="icon-exclamation-sign"></i>':''}
                    ${(swOrder.field2Date>time && swOrder.state=='2') ?' <i style="color: green;" class="icon-circle-arrow-right"></i>':''}
                    ${(swOrder.field2Date<time && swOrder.state=='2')?' <i style="color: red;" class="icon-exclamation-sign"></i>':''}
                    ${(swOrder.field3Date>time && swOrder.state=='3') ?' <i style="color: green;" class="icon-circle-arrow-right"></i>':''}
                    ${(swOrder.field3Date<time && swOrder.state=='3')?' <i style="color: red;" class="icon-exclamation-sign"></i>':''}
                    ${(swOrder.field4Date>time && swOrder.state=='4') ?' <i style="color: green;" class="icon-circle-arrow-right"></i>':''}
                    ${(swOrder.field4Date<time && swOrder.state=='4')?' <i style="color: red;" class="icon-exclamation-sign"></i>':''}
                    ${(swOrder.field5Date>time && swOrder.state=='5') ?' <i style="color: green;" class="icon-circle-arrow-right"></i>':''}
                    ${(swOrder.field5Date<time && swOrder.state=='5')?' <i style="color: red;" class="icon-exclamation-sign"></i>':''}
                    ${(swOrder.field6Date>time && swOrder.state=='6') ?' <i style="color: green;" class="icon-circle-arrow-right"></i>':''}
                    ${(swOrder.field6Date<time && swOrder.state=='6')?' <i style="color: red;" class="icon-exclamation-sign"></i>':''}
                    ${(swOrder.field7Date>time && swOrder.state=='7') ?' <i style="color: green;" class="icon-circle-arrow-right"></i>':''}
                    ${(swOrder.field7Date<time && swOrder.state=='7')?' <i style="color: red;" class="icon-exclamation-sign"></i>':''}
                    ${(swOrder.field8Date>time && swOrder.state=='8') ?' <i style="color: green;" class="icon-circle-arrow-right"></i>':''}
                    ${(swOrder.field8Date<time && swOrder.state=='8')?' <i style="color: red;" class="icon-exclamation-sign"></i>':''}
                    ${(swOrder.state=='11')?' <i style="color: green;" class="icon-ok-sign"></i>':''}
          
				</td>
				<td>
					${fns:getDictLabel(swOrder.typeOrder, 'type_order', '')}
				</td>
			
				<td>
					<fmt:formatNumber type="number" value="${swOrder.amountYs}" pattern="0.00" maxFractionDigits="2"/>
				</td>
				<td>
				<font color="blue">${userId==swOrder.user.id?swOrder.userName:''}</font>
				   ${userId==swOrder.user.id?'':swOrder.userName}
				</td>
				<td>
				<font color="blue">${userId==swOrder.dyuserId?swOrder.dyuserName:''}</font>
				   ${userId==swOrder.dyuserId?'':swOrder.dyuserName}
		
				</td>
				<td>
				    <a style='cursor:pointer' onclick='tt("${swOrder.id}")'>
				         ${swOrder.field1State=='0'?' <i style="color: red;" class="icon-ban-circle"></i>':''}
				    </a>
				     <a style='cursor:pointer' onclick='tt("${swOrder.id}")'>
					     <fmt:formatDate value="${swOrder.field1State=='1'?swOrder.field1Date:''}" pattern="yyyy-MM-dd"/>
					 </a>
				    <a style='cursor:pointer;color: blue;' href="${ctx}/business/survey/swSurvey/form?id=${swOrder.field1Id}&orderId=${swOrder.id}"  title="点击办理" >
				          <fmt:formatDate value="${(swOrder.field1State=='2' && swOrder.type=='0' )?swOrder.field1Date:''}" pattern="yyyy-MM-dd"/>
				          ${(swOrder.field1State=='2' && swOrder.type=='0' )?'<i class="icon-edit"></i>':''}
				    </a>
				     <a style='cursor:pointer;color: blue;' href="${ctx}/business/special/swSpecial/form?id=${swOrder.field1Id}&orderId=${swOrder.id}"  title="点击办理">
				           <fmt:formatDate value="${(swOrder.field1State=='2' && (swOrder.type=='1' || swOrder.type=='2' ))?swOrder.field1Date:''}" pattern="yyyy-MM-dd"/>
				          ${(swOrder.field1State=='2' && (swOrder.type=='1' || swOrder.type=='2' ))?'<i class="icon-edit"></i>':''}
				    </a>
    				<a style='cursor:pointer;color: green;' href="${ctx}/business/survey/swSurvey/look?id=${swOrder.field1Id}&orderId=${swOrder.id}" title="已完成">
    				        <fmt:formatDate value="${(swOrder.field1State=='3'  && swOrder.type=='0' )?swOrder.field1DateEnd:''}" pattern="yyyy-MM-dd"/>
    				</a>
    				<a style='cursor:pointer;color: green;' href="${ctx}/business/special/swSpecial/look?id=${swOrder.field1Id}&orderId=${swOrder.id}" title="已完成" >
    				        <fmt:formatDate value="${(swOrder.field1State=='3'  && (swOrder.type=='1' || swOrder.type=='2') )?swOrder.field1DateEnd:''}" pattern="yyyy-MM-dd"/>
    				</a>
    				<a style='cursor:pointer;color: green;'  >
    				       ${(swOrder.field1State=='3' )?'<i class="icon-ok"></i>':''}
    				   
    				</a>
				</td>
					<td>
					 <a style='cursor:pointer' onclick='tt("${swOrder.id}")' title="无此环节">
				         ${swOrder.field2State=='0'?' <i style="color: red;" class="icon-ban-circle"></i>':''}
				    </a>
				    
				    <a style='cursor:pointer'  title="该环节截止时间，任务未到达">
    				    <fmt:formatDate value="${swOrder.field2State=='1'?swOrder.field2Date:''}" pattern="yyyy-MM-dd"/>
    				    ${swOrder.field2State=='1'?'<i class="icon-exclamation-sign"></i>':''}
				    </a>
					
				    <a style='cursor:pointer;color: blue;' href="${ctx}/business/bidding/swBidding/form?id=${swOrder.field2Id}&orderId=${swOrder.id}"  title="点击办理">
	
				     <fmt:formatDate value="${(swOrder.field2State=='2' && swOrder.amountYs<2000000)?swOrder.field2Date:''}" pattern="yyyy-MM-dd"/>
				          ${(swOrder.field2State=='2' && swOrder.amountYs<2000000)?'<i class="icon-edit"></i>':''}
				    
				    </a>
				    <a style='cursor:pointer;color: blue;' href="${ctx}/business/swbiddingpublic/swBiddingPublic/form?id=${swOrder.field2Id}&orderId=${swOrder.id}" title="点击办理">
				         <fmt:formatDate value="${(swOrder.field2State=='2' && swOrder.amountYs>=2000000)?swOrder.field2Date:''}" pattern="yyyy-MM-dd"/>
				         ${(swOrder.field2State=='2' && swOrder.amountYs>=2000000)?'<i class="icon-edit"></i>':''}
				    </a>
    				<a style='cursor:pointer;color: green;'  href="${ctx}/business/swbiddingpublic/swBiddingPublic/look?id=${swOrder.field2Id}&orderId=${swOrder.id}" title="已完成">
    			        <fmt:formatDate value="${(swOrder.field2State=='3'  && swOrder.amountYs>=2000000 )?swOrder.field2DateEnd:''}" pattern="yyyy-MM-dd"/>
    			        ${(swOrder.field2State=='3' && swOrder.amountYs>=2000000)?'<i class="icon-ok"></i>':''}
				    </a>
				    <a style='cursor:pointer;color: green;'  href="${ctx}/business/bidding/swBidding/look?id=${swOrder.field2Id}&orderId=${swOrder.id}" title="已完成">
    			
    				     <fmt:formatDate value="${(swOrder.field2State=='3'  && swOrder.amountYs<2000000 )?swOrder.field2DateEnd:''}" pattern="yyyy-MM-dd"/>
    				     ${(swOrder.field2State=='3'&& swOrder.amountYs<2000000)?'<i class="icon-ok"></i>':''}
    				</a>
    				
				</td>
					<td>
					 <a style='cursor:pointer' onclick='tt("${swOrder.id}")' title="无此环节">
				    ${swOrder.field3State=='0'?' <i style="color: red;" class="icon-ban-circle"></i>':''}
				    </a>
				    <a style='cursor:pointer'  title="该环节截止时间，任务未到达">
    				    <fmt:formatDate value="${swOrder.field3State=='1'?swOrder.field3Date:''}" pattern="yyyy-MM-dd"/>
    				    ${swOrder.field3State=='1'?'<i class="icon-exclamation-sign"></i>':''}
				    </a>
					 
				    <a style='cursor:pointer;color: blue;'  href="${ctx}/business/negotiate/swNegotiate/form?id=${swOrder.field3Id}&orderId=${swOrder.id}" title="点击办理">
				
				     <fmt:formatDate value="${swOrder.field3State=='2'?swOrder.field3Date:''}" pattern="yyyy-MM-dd"/>
				     ${(swOrder.field3State=='2' )?'<i class="icon-edit"></i>':''}
				    </a>
    				<a style='cursor:pointer;color: green;' href="${ctx}/business/negotiate/swNegotiate/look?id=${swOrder.field3Id}&orderId=${swOrder.id}">
    
    				<fmt:formatDate value="${(swOrder.field3State=='3' )?swOrder.field3DateEnd:''}" pattern="yyyy-MM-dd"/>
    				${swOrder.field3State=='3'?'<i class="icon-ok"></i>':''}
    				</a>
    				
				</td>
				
					<td>
					<a style='cursor:pointer' onclick='tt("${swOrder.id}")' title="无此环节">
				    ${swOrder.field5State=='0'?' <i style="color: red;" class="icon-ban-circle"></i>':''}
				    </a> 
					
				    <a style='cursor:pointer;color: blue;' href="${ctx}/business/purchase/swPurchase/form?id=${swOrder.field5Id}&orderId=${swOrder.id}" title="点击办理，申购单录入">
				
				          <fmt:formatDate value="${(swOrder.field5State=='2' && (swOrder.amountYs<20000 || swOrder.type=='2'))?swOrder.field5Date:''}" pattern="yyyy-MM-dd"/>
				         ${(swOrder.field5State=='2' && (swOrder.amountYs<20000 || swOrder.type=='2'))?'<i class="icon-edit"></i>':''}
				    </a>
				     <a style='cursor:pointer;color: blue;' href="${ctx}/business/agreement/swAgreement/form?id=${swOrder.field5Id}&orderId=${swOrder.id}" title="点击办理，合同录入">
		
				         <fmt:formatDate value="${(swOrder.field5State=='2' && ((swOrder.amountYs>=20000 && swOrder.type!='2')))?swOrder.field5Date:''}" pattern="yyyy-MM-dd"/>
				         ${(swOrder.field5State=='2' && ((swOrder.amountYs>=20000 && swOrder.type!='2')))?'<i class="icon-edit"></i>':''}
				    </a>
    				
				     <a style='cursor:pointer;color: green;' href="${ctx}/business/purchase/swPurchase/look?id=${swOrder.field5Id}&orderId=${swOrder.id}">
		
				    <fmt:formatDate value="${(swOrder.field5State=='3'  && (swOrder.amountYs<20000 || swOrder.type=='2'))?swOrder.field5DateEnd:''}" pattern="yyyy-MM-dd"/>
				    </a>
				     <a style='cursor:pointer;color: green;' href="${ctx}/business/agreement/swAgreement/look?id=${swOrder.field5Id}&orderId=${swOrder.id}">
				    <fmt:formatDate value="${(swOrder.field5State=='3'  && (swOrder.amountYs>=20000 && swOrder.type!='2'))?swOrder.field5DateEnd:''}" pattern="yyyy-MM-dd"/>
				    </a>
				    <a style='cursor:pointer;color: green;'  >
    				${(swOrder.field5State=='3' )?'<i class="icon-ok"></i>':''}
    				</a>
    				<a style='cursor:pointer'   title="该环节截止时间，任务未到达">
    				
    				    <fmt:formatDate value="${swOrder.field5State=='1'?swOrder.field5Date:''}" pattern="yyyy-MM-dd"/>
    				    ${swOrder.field5State=='1'?'<i class="icon-exclamation-sign"></i>':''}
    				</a>
					
				</td>
					<td>
					<a style='cursor:pointer' onclick='tt("${swOrder.id}")'>
				    ${swOrder.field6State=='0'?' <i style="color: red;" class="icon-ban-circle"></i>':''}
				    </a>
				    <a style='cursor:pointer'   title="该环节截止时间，任务未到达">
    				   <fmt:formatDate value="${swOrder.field6State=='1'?swOrder.field6Date:''}" pattern="yyyy-MM-dd"/>
    				    ${swOrder.field6State=='1'?'<i class="icon-exclamation-sign"></i>':''}
				    </a>
					
				    <a style='cursor:pointer;color: blue;'  href="${ctx}/business/pay/swPay/form?id=${swOrder.swPayList[0].id}&orderId=${swOrder.id}" >
				       <fmt:formatDate value="${(swOrder.field6State=='2' &&   swOrder.swPayList[0].id!=null &&  swOrder.swPayList[0].state=='0') ?swOrder.swPayList[0].projectDate:''}" pattern="yyyy-MM-dd"/>
				        ${(swOrder.field6State=='2' &&   swOrder.swPayList[0].id!=null &&  swOrder.swPayList[0].state=='0') ?'<i class="icon-edit"></i>':''}
				    </a>
				     <a style='cursor:pointer;;color: green;'  href="${ctx}/business/pay/swPay/edit?id=${swOrder.swPayList[0].id}&orderId=${swOrder.id}" >
				     
				     <fmt:formatDate value="${(swOrder.field6State=='2' &&   swOrder.swPayList[0].id!=null &&  swOrder.swPayList[0].state=='1')?swOrder.swPayList[0].completionTime:''}" pattern="yyyy-MM-dd"/>
				      ${(swOrder.field6State=='2' &&   swOrder.swPayList[0].id!=null &&  swOrder.swPayList[0].state=='1') ?'<i class="icon-ok"></i>':''}
				    </a>
    			
    				
				</td>
					<td>
					<a style='cursor:pointer' onclick='tt("${swOrder.id}")'>
				    ${swOrder.field7State=='0'?' <i style="color: red;" class="icon-ban-circle"></i>':''}
				    </a>
				    <a style='cursor:pointer;color: blue;'  href="${ctx}/business/receive/swReceive/form?id=${swOrder.field7Id}&orderId=${swOrder.id}" >
					 <fmt:formatDate value="${swOrder.field7State=='2' ?swOrder.field7Date:''}" pattern="yyyy-MM-dd"/>
				         ${swOrder.field7State=='2'?'<i class="icon-edit"></i>':''}
				
				 
				    </a>
    				<a style='cursor:pointer;color: green;' href="${ctx}/business/receive/swReceive/form?id=${swOrder.field7Id}&orderId=${swOrder.id}">
    				<fmt:formatDate value="${(swOrder.field7State=='3' )?swOrder.field7DateEnd:''}" pattern="yyyy-MM-dd"/>
    				${swOrder.field7State=='3'?'<i class="icon-ok"></i>':''}
				    </a>
				</td>
				<td>
			
					<a style='cursor:pointer' onclick='tt("${swOrder.id}")'>
				    ${swOrder.field6State=='0' || swOrder.swPayList[1]==null ?' <i style="color: red;" class="icon-ban-circle"></i>':''}
				    </a>
				
					
				    <a style='cursor:pointer;color: blue;'  href="${ctx}/business/pay/swPay/form?id=${swOrder.swPayList[1].id}&orderId=${swOrder.id}" >
				      <fmt:formatDate value="${(swOrder.field6State=='2' &&   swOrder.swPayList[1].id!=null &&  swOrder.swPayList[1].state=='0') ?swOrder.swPayList[1].projectDate:''}" pattern="yyyy-MM-dd"/>
				        ${(swOrder.field6State=='2' &&   swOrder.swPayList[1].id!=null &&  swOrder.swPayList[1].state=='0') ?'<i class="icon-edit"></i>':''}
				    </a>
				     <a style='cursor:pointer;color: green;'  href="${ctx}/business/pay/swPay/edit?id=${swOrder.swPayList[1].id}&orderId=${swOrder.id}" >
				     
				     <fmt:formatDate value="${(swOrder.field6State=='2' &&   swOrder.swPayList[1].id!=null &&  swOrder.swPayList[1].state=='1')?swOrder.swPayList[1].completionTime:''}" pattern="yyyy-MM-dd"/>
				      ${(swOrder.field6State=='2' &&   swOrder.swPayList[1].id!=null &&  swOrder.swPayList[1].state=='1') ?'<i class="icon-ok"></i>':''}
				    </a>
    			
				</td>
					
					<td>
					<a style='cursor:pointer' onclick='tt("${swOrder.id}")'>
				    ${swOrder.field8State=='0'?' <i style="color: red;" class="icon-ban-circle"></i>':''}
				    </a>
					 
				    <a style='cursor:pointer;color: blue;' href="${ctx}/business/receive/swReceive/listEquipment?id=${swOrder.field8Id}&orderId=${swOrder.id}">
				    ${swOrder.field8State=='2'?'入库/分发<i class="icon-edit"></i>':''}
				    </a>
    				<a style='cursor:pointer;color: green;' href="${ctx}/business/receive/swReceive/listEquipment?id=${swOrder.field8Id}&orderId=${swOrder.id}">
    				<fmt:formatDate value="${(swOrder.field8State=='3' )?swOrder.field8DateEnd:''}" pattern="yyyy-MM-dd"/>
    				${swOrder.field8State=='3'?'<i class="icon-ok"></i>':''}
				    </a>
				
				</td>
					<td>
					<a style='cursor:pointer' onclick='tt("${swOrder.id}")'>
				    ${swOrder.field9State=='0'?' <i style="color: red;" class="icon-ban-circle"></i>':''}
				    </a>
					
				   <a style='cursor:pointer;color: blue;' href="${ctx}/business/receive/swReceive/listEquipmentYs?id=${swOrder.field8Id}&orderId=${swOrder.id}">
				    ${swOrder.field9State=='2'?'验收单/固定资产卡片<i class="icon-edit"></i>':''}
				    </a>
    				<a style='cursor:pointer;color: green;' href="${ctx}/business/receive/swReceive/listEquipmentYs?id=${swOrder.field8Id}&orderId=${swOrder.id}">
    				<fmt:formatDate value="${(swOrder.field9State=='3' )?swOrder.field9DateEnd:''}" pattern="yyyy-MM-dd"/>
    				${swOrder.field9State=='3'?'<i class="icon-ok"></i>':''}
				    </a>
					
				</td>
				<td>
					<a style='cursor:pointer' onclick='tt("${swOrder.id}")'>
				    ${swOrder.field6State=='0' || swOrder.swPayList[2]==null  ?' <i style="color: red;" class="icon-ban-circle"></i>':''}
				    </a>
				  
					
				    <a style='cursor:pointer;color: blue;'  href="${ctx}/business/pay/swPay/form?id=${swOrder.swPayList[2].id}&orderId=${swOrder.id}" >
				     <fmt:formatDate value="${(swOrder.field6State=='2' &&   swOrder.swPayList[2].id!=null &&  swOrder.swPayList[2].state=='0') ?swOrder.swPayList[2].projectDate:''}" pattern="yyyy-MM-dd"/>
				        ${(swOrder.field6State=='2' &&   swOrder.swPayList[2].id!=null &&  swOrder.swPayList[2].state=='0') ?'<i class="icon-edit"></i>':''}
				    
				    </a>
				     <a style='cursor:pointer;color: green;'  href="${ctx}/business/pay/swPay/edit?id=${swOrder.swPayList[2].id}&orderId=${swOrder.id}" >
				     
				     <fmt:formatDate value="${(swOrder.field6State=='2' &&   swOrder.swPayList[2].id!=null &&  swOrder.swPayList[2].state=='1')?swOrder.swPayList[2].completionTime:''}" pattern="yyyy-MM-dd"/>
				      ${(swOrder.field6State=='2' &&   swOrder.swPayList[2].id!=null &&  swOrder.swPayList[2].state=='1') ?'<i class="icon-ok"></i>':''}
				    </a>
    			
				</td>
					<td>
					<a style='cursor:pointer;color: blue;' onclick='tt("${swOrder.id}")'>
				    ${swOrder.field10State=='0'?' <i style="color: red;" class="icon-ban-circle"></i>':''}
				    </a>
					 
				    <a style='cursor:pointer;color: blue;' href="${ctx}/business/reimbursement/swReimbursement/form?id=${swOrder.field10Id}&orderId=${swOrder.id}">
				    ${swOrder.field10State=='2'?'报销<i class="icon-edit"></i>':''}
				    </a>
    				<a style='cursor:pointer;color: green;' href="${ctx}/business/reimbursement/swReimbursement/view?id=${swOrder.field10Id}&orderId=${swOrder.id}">
    				<fmt:formatDate value="${(swOrder.field10State=='3' )?swOrder.field10DateEnd:''}" pattern="yyyy-MM-dd"/>
    				${swOrder.field10State=='3'?'<i class="icon-ok"></i>':''}
				    </a>
				</td>
	
				
				<shiro:hasPermission name="business:order:swOrder:edit"><td>
    				<a href="${ctx}/business/order/swOrder/edit?id=${swOrder.id}">修改</a>
					<a href="${ctx}/business/order/swOrder/delete?id=${swOrder.id}" onclick="return confirmx('确认要删除该采购任务吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>