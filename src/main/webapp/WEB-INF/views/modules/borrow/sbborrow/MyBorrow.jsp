<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资源流通管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
			 
			 $("#btnRenew").click(function(){
					saveSelectData("renew") ;
				});
			 
			 $("#btnLending").click(function(){
					saveSelectData("lending") ;
				});
			 
			 $("#btnLose").click(function(){
					saveSelectData("lose") ;
				});
			$("#btnDamage").click(function(){
					saveSelectData("damage") ;
			    });
		});
		
		function saveSelectData(action){
			   var ids = "" ;
			   var id = "" ;
	           var i=0;
	            var j=0;
	            $("input[name*='checkbox_name']").each(function () {
	                if ($(this).attr("checked") == 'checked') {
	                    $("input[name='columnList["+i+"].isList']").val(1);
	                   id = $("input[name='columnList["+i+"].id']").val();
	                   if($("input[name='columnList["+i+"].borrowState']").val()=='3' ||$("input[name='columnList["+i+"].borrowState']").val()=='4' ){
	                	   if(ids==""){
		                	   ids =id;
		                   }else{
		                	   
		                	   ids = ids+","+id;
		                   }
	                	   
	                   }
	                   
	                   
	                   
	                    j++;
	                }
	                i++;
	            });

	            if(ids==""){

	                alert("请选择可以设备");
	                return;
	            }
	            window.location =  "${ctx}/equipment/event/sbEvent/"+action+"?ids="+ids;
	      //      top.$.jBox.open("iframe:${ctx}/equipment/event/sbEvent/renew?ids="+ids, "续借",1200,800,{
		//			buttons:{"确定":true}, loaded:function(h){
			///			$(".jbox-content", top.document).css("overflow-y","hidden");
			//		}
			//	});

	     

	        }
		
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function dbOnclick(e,e2){
			$.ajax({
               type:'post',
                url:'${ctx}/equipment/equipmentbus/sbEquipmentBus/updateCode',
                data:{'field4':e,'field5':e2},
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
		<li class="active"><a href="${ctx}/borrow/sbborrow/sbBorrow/list">设备借用列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="sbBorrowChild" action="${ctx}/borrow/sbborrow/sbBorrow/MyBorrow" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>设备名称：</label>
				<form:input path="equipmentName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>设备编号：</label>
				<form:input path="equipmentSbcode" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>设备状态：</label>
			    <form:select path="borrowState" class="input-mini">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('bstate')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnRenew" class="btn btn-primary" type="button" value="续借"/>
			<li class="btns"><input id="btnLending" class="btn btn-primary" type="button" value="转借"/>
			<li class="btns"><input id="btnLose" class="btn btn-primary" type="button" value="丢失"/>
			<li class="btns"><input id="btnDamage" class="btn btn-primary" type="button" value="损坏"/>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<form:form id="inputForm" modelAttribute="genTable" action="${ctx}/borrow/sbborrow/sbBorrow/btnRenew" method="post" class="form-horizontal">
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th title="选择"><input type="checkbox" id="checkedAll">
				<th>借用时间</th>
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
				
			
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sbBorrowChild"  varStatus="vs">
			<tr>
			  <td>
                         <input type="checkbox" name="checkbox_name[${vs.index}]"/>
                         <input type="hidden" name="columnList[${vs.index}].isList" value="0"/>      
						 <input type="hidden" name="columnList[${vs.index}].id" value="${sbBorrowChild.id}"/>
						 <input type="hidden" name="columnList[${vs.index}].equipmentId" value="${sbBorrowChild.equipmentId}"/>
						 <input type="hidden" name="columnList[${vs.index}].borrowState" value="${sbBorrowChild.borrowState}"/>
                 </td>
                
				<td>
					<fmt:formatDate value="${sbBorrowChild.statrDate}" pattern="yyyy-MM-dd"/>
				</td>
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
				
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</form:form>
	<div class="pagination">${page}</div>
</body>
</html>