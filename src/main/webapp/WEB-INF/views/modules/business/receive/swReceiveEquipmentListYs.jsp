<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仪器设备开箱验收管理</title>
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
	        $("input[name*='checkbox_name']").click(function(){

	            $("label[name='errorMesg']").hide();

	        });
	        $("#plSubmit").click(function(){
				
				showSelectedData();
				
				
			
			});
            $("#rkSubmit").click(function(){
            	rkReceive();
			});
            $("#ckSubmit").click(function(){
            	ckReceive();
			});
			
		});
		
		
		function rkReceive(){
			// 正常打开		
			var i=0;
            var j=0;
            var ids = "" ;
            var flag = 1 ;
            $("input[name*='checkbox_name']").each(function () {
                if ($(this).attr("checked") == 'checked') {
                   var id =  $("input[name='columnList["+i+"].id']").val();
                   var state =  $("input[name='columnList["+i+"].state']").val();
                   if(ids==""){
                	   ids = id ;
                   }else{
                	   ids = ids+","+id;
                   }
                   
                   if(state!=0){
                	   alert("您所选择的第"+(j+1)+"条数据不是待入库设备！");
                	   flag = 0;
                	   return;
                   }
                   
                   j++;
                }
                i++;
            });
            if(flag==1){

            var url = "${ctx}/business/receive/swReceive/rkEquipment?ids="+ids;

            $.ajax({
                  type: 'POST',
                  url : url,
                  async:false,
                  success : function(data) {
                  	alert("入库成功");
                  	 self.location.reload();
     
                  }
              });
            }
		
		}
		
		function ckReceive(){
			// 正常打开		
			var i=0;
            var j=0;
            var ids = "" ;
            var flag = 1 ;
            var i = 0 ;
            $("input[name*='checkbox_name']").each(function () {
                if ($(this).attr("checked") == 'checked') {
                	
                   var id =  $("input[name='columnList["+i+"].id']").val();
                   var state =  $("input[name='columnList["+i+"].state']").val();
                   if(ids==""){
                	   ids = id ;
                   }else{
                	   ids = ids+","+id;
                   }
                   
                   if(state!=1){
                	   alert("您所选择的第"+(j+1)+"条数据不是待出库设备！");
                	   flag = 0;
                	   return;
                   }
                   
                   j++;
                }
                i++;
            });
            if(flag==1){
            	   window.location.href= "${ctx}/business/receive/swReceive/viewEquipment?ids="+ids;
          
            }
		
		}
		
		function wcReceive(){

			 window.location.href= "${ctx}/business/receive/swReceive/wcAssets?orderId=${orderId}";
		}
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	
		
		
		function showSelectedData(){
			// 正常打开		
			var i=0;
            var j=0;
            var ids = "" ;
            var flag = 1 ;
            $("input[name*='checkbox_name']").each(function () {
                if ($(this).attr("checked") == 'checked') {
                   var id =  $("input[name='columnList["+i+"].id']").val();
                   var state =  $("input[name='columnList["+i+"].state']").val();
                   if(ids==""){
                	   ids = id ;
                   }else{
                	   ids = ids+","+id;
                   }
                  
                  
                   j++;
                }
                i++;
            });
            

            if(flag==1){
            	
            	
            	
            	  if(flag==1){
                      top.$.jBox.open("iframe:${ctx}/business/receive/swReceive/formEquipment","批量修改", 900, 500, {
          	            buttons:{"确认":"ok", "关闭":true},closed:function (){
          					  $("#searchForm").submit();
          				},
          	            submit:function(v, h, f){
          	            	
          	                if(v=="ok"){
          	                	var data=h.find("iframe")[0].contentWindow.$("#inputForm").serialize();
          	                     var url = "${ctx}/business/receive/swReceive/saveEquipment?ids="+ids;
          	                    $.ajax({
          	                        type: 'POST',
          	                        url : url,
          	                        async:false,
          	                        data: data,             //获取表单数据
          	                        success : function(data) {
          	                        	alert("保存成功");
          	                            //调用父窗体方法，当关闭子窗体刷新父窗体
          	                            window.parent.window.jBox.close();    //关闭子窗体
          	           
          	                        }
          	                    });
          	                    self.location.reload(); // 主窗口刷新  
          	                    
          	                }
          	            },
          	            loaded:function(h){
          	                $(".jbox-content", top.document).css("overflow-y","hidden");
          	            }
          	        });
                      }
            
            
            
            
            
            
            
            }


        }
		
		
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/business/order/swOrder/">采购任务列表标列表</a></li>
		<li class="active"><a href="${ctx}/business/receive/SwReceiveEquipment/form">验收/固定资产卡片</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="SwReceive" method="post" action="${ctx}/business/receive/swReceive/listEquipment/?orderId=${orderId}" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<li class="btns"><input id="plSubmit" class="btn btn-primary" type="button" value="批量设置属性"/></li>
		<li class="btns"><input onclick="wcReceive()" class="btn btn-primary" type="button" value="完成"/></li>

	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th title="选择"><input type="checkbox" id="checkedAll"></th>
			    <th>状态</th>
				<th>设备名称</th>
				<th>型号</th>
				<th>规格</th>
				<th>原值</th>
				<th>功率</th>
				<th>出厂编号</th>
				<th>资产编号</th>
				<th>数量</th>
				<th>单价</th>
				<th>总价</th>
				<th>责任部门</th>
				<th>责任人</th>
				<th>放置地点</th>
				<th>是否计量</th>
				<th>计量日期</th>
				<th>计量方式</th>
				<th>计量周期</th>
				<th>资金来源</th>
				<th>设备类型</th>
		        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="swReceiveEquipment" varStatus="vs">
			<tr>
			      <td>
                         <input type="checkbox" name="checkbox_name[${vs.index}]"/>
                         <input type="hidden" name="columnList[${vs.index}].isList" value="0"/>      
						 <input type="hidden" name="columnList[${vs.index}].id" value="${swReceiveEquipment.id}"/>
						  <input type="hidden" name="columnList[${vs.index}].state" value="${swReceiveEquipment.state}"/>
				
                 </td>
                 <td>
					 ${fns:getDictLabel(swReceiveEquipment.state,'receive_state','')}
				</td>
				<td><a href="${ctx}/business/receive/SwReceiveEquipment/form?id=${swReceiveEquipment.id}">
				
					${swReceiveEquipment.equipmentName}
				</a></td>
				<td>
					${swReceiveEquipment.equipmentModel}
				</td>
				<td>
					${swReceiveEquipment.equipmentNorms}
				</td>
				<td>
					${swReceiveEquipment.equipmentValue}
				</td>
				<td>
					${swReceiveEquipment.equipmentPower}
				</td>
				<td>
					${swReceiveEquipment.equipmentFactoryNumber}
				</td>
				<td>
					${swReceiveEquipment.zccode}
				</td>
				<td>
					${swReceiveEquipment.amount}
				</td>
				<td>
					${swReceiveEquipment.unitprice}
				</td>
				<td>
					${swReceiveEquipment.price}
				</td>
				<td>
					${swReceiveEquipment.teamname}
				</td>
				<td>
					${swReceiveEquipment.usepeoplename}
				</td>
				<td>
					${swReceiveEquipment.local}
				</td>
				<td>
					 ${fns:getDictLabel(swReceiveEquipment.isMetering,'is_leaf','')}
				</td>
				<td>
				   <fmt:formatDate value="${swReceiveEquipment.meteringDate}" pattern="yyyy-MM-dd"/>
				
				</td>
				<td>
				 ${fns:getDictLabel(swReceiveEquipment.meteringType,'meteringType','')}
		
				</td>
				<td>
				 ${fns:getDictLabel(swReceiveEquipment.meteringTime,'meteringTime','')}
				
				</td>
				<td>
					${swReceiveEquipment.fsTypeName}
				</td>
				<td>
					${swReceiveEquipment.sbTypeName}
				</td>
				<td>
    				<a href="${ctx}/business/receive/swReceive/viewAssets?ids=${swReceiveEquipment.id}">固定资产卡片</a>
    				<a href="${ctx}/business/receive/swReceive/viewAcceptance?ids=${swReceiveEquipment.id}">设备验收单</a>
    			</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
</body>
</html>