<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采购设备清单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			tt('');
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/business/product/swProduct/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
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
		        $("#btnSubject").click(function(){	
					showSelectedData();
				});
		        $("#btnUpdate").click(function(){	
					updateInfo();
				});
		        
		        
		});
		
		function tt(id){
			
			 $.ajax({
	                type:'post',
	                url:'${ctx}/borrow/sbborrow/sbBorrow/getSbProduct',
	                data:{'sbId':id},
	                cache:false,
	                dataType:'json',
               success:function(data){
               	var ele=window.document .getElementById ("count"); 
       			ele.innerHTML = data.length;
               }
           });
			
			
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
                   if(state!=0){
                	   alert("您所选择的第"+(j+1)+"条数据不是待采购设备！");
                	   flag = 0;
                	   return;
                   }
                  
                   j++;
                }
                i++;
            });
            
            if(ids==""){
            	alert("请至少选择一条设备发起采购任务!");
            	return ;
            }
            
       
            if(flag==1){
            top.$.jBox.open("iframe:${ctx}/business/order/swOrder/form?field2="+ids,"添加采购任务信息", 960, 460, {
	            buttons:{"确认":"ok", "关闭":true},closed:function (){
					  $("#searchForm").submit();
				},
	            submit:function(v, h, f){
	            	
	                if(v=="ok"){
	                	var data=h.find("iframe")[0].contentWindow.$("#inputForm").serialize();
	                     var url = "${ctx}/business/order/swOrder/saveOrder?ids="+ids;
	                    $.ajax({
	                        type: 'POST',
	                        url : url,
	                        async:false,
	                        data: data,             //获取表单数据
	                        success : function(data) {
	                        	
	                            //调用父窗体方法，当关闭子窗体刷新父窗体
	                            window.parent.window.jBox.close();    //关闭子窗体
	                            window.parent.page(); 
	                        }
	                    });
	                    //self.location.reload(); // 主窗口刷新  
	                    
	                }
	            },
	            loaded:function(h){
	                $(".jbox-content", top.document).css("overflow-y","hidden");
	            }
	        });
            }


        }
		
		
		function updateInfo(){
			var i=0;
            var j=0;
            var ids = "" ;
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
            
      
            top.$.jBox.open("iframe:${ctx}/business/product/swProduct/formPl","批量修改", 900, 500, {
  	            buttons:{"确认":"ok", "关闭":true},closed:function (){
  					  $("#searchForm").submit();
  				},
  	            submit:function(v, h, f){
  	            	
  	                if(v=="ok"){
  	                	var data=h.find("iframe")[0].contentWindow.$("#inputForm").serialize();
  	                     var url = "${ctx}/business/product/swProduct/saveSwProduct?ids="+ids;
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
		
		
		
	</script>
	<style type="text/css">
      
	  .shopping-cart {

     width:24px;     
 height:24px;     
 background:url(${ctxStatic}/login/img/_cart.png) no-repeat 0 0;  
 position:relative;   
 display:block;  
}	
.shopping-cart>i{  
   position:absolute;   
   font-style:normal;    
   right:10px; 
   color:red;   
   top:-15px;   
   font-size:12px;  
   font-weight:bold;
   }
	</style>
</head>
<body>
    <div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/business/product/swProduct/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/business/product/swProduct/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/business/product/swProduct/">采购设备清单</a></li>
		<shiro:hasPermission name="business:product:swProduct:edit"><li><a href="${ctx}/business/product/swProduct/form">计划外采购添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="swProduct" action="${ctx}/business/product/swProduct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>设备名称：</label>
				<form:input path="productName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
		
			<li><label>采购厂家：</label>
				<form:input path="productMade" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>采购类别：</label>
				<form:select path="projectType"  class="input-medium " > 
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="clearfix"></li>
			<li><label>需求部门：</label>
				<sys:treeselect id="office" name="office.id" value="${swProduct.office.id}" labelName="office.name" labelValue="${swProduct.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>申请人：</label>
				<sys:treeselect id="user" name="user.id" value="${swProduct.user.id}" labelName="user.name" labelValue="${swProduct.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>资产类别：</label>
				<form:select path="type"  class="input-medium " > 
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('zichan_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	           <shiro:hasPermission name="business:product:swProduct:edit">
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
				<input id="btnUpdate" class="btn btn-primary" type="button" value="批量修改"/>
				 <li class="btns"><input id="btnSubject" class="btn btn-primary" type="button" value="生成任务单"/></shiro:hasPermission></li>
		<!--  	<li class="shopping-cart" style="margin-top:10px;margin-left:280px;" onclick="test()"> <i id='count'>0</i></li>-->	
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th title="选择"><input type="checkbox" id="checkedAll"></th>
	
			    <th class="sort-column state">采 购 状 态</th>
			    <th class="sort-column field1">采购任务号</th>
				<th class="sort-column product_name">设备名称</th>
				<th class="sort-column sb_type">设备类别</th>

				
				<th class="sort-column unit_price">参考单价（万元）</th>
				<th class="sort-column product_amount">数量</th>
				<th class="sort-column total_price">预算（万元）</th>
				<th class="sort-column office_name">需求部门</th>
				<th class="sort-column user_name">申请人</th>
				<th class="sort-column zr_user_name">采购人</th>
			
				<th class="sort-column product_date">采购时间</th>
				<th class="sort-column product_type">拟采购型号规格</th>
				<th class="sort-column product_made">拟采购厂家</th>
				<th class="sort-column project_type">采购类别</th>
				
				<shiro:hasPermission name="business:product:swProduct:edit"><th>&nbsp;&nbsp;&nbsp;操&nbsp;&nbsp;&nbsp;作&nbsp;&nbsp;&nbsp;</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="swProduct" varStatus="vs">
			<tr>
			     <td>
                         <input type="checkbox" name="checkbox_name[${vs.index}]"/>
                         <input type="hidden" name="columnList[${vs.index}].isList" value="0"/>      
						 <input type="hidden" name="columnList[${vs.index}].id" value="${swProduct.id}"/>
						  <input type="hidden" name="columnList[${vs.index}].state" value="${swProduct.state}"/>
                 </td>
                
                 <td>
				${fns:getDictLabel(swProduct.state, 'product_state', '')}
				  ${(swProduct.state=='1') ?' <i style="color: green;" class="icon-circle-arrow-right"></i>':''}
                     ${(swProduct.state=='2') ?' <i style="color: green;" class="icon-ok-sign"></i>':''}
                     ${(swProduct.productDate>time && swProduct.state=='0') ?' <i style="color: green;" class="icon-time"></i>':''}
                      ${(swProduct.productDate<time && swProduct.state=='0')?' <i style="color: red;" class="icon-exclamation-sign"></i>':''}
                      ${(swProduct.productDate==time && swProduct.state=='0') ?' <i style="color: yellow;" class="icon-exclamation-sign"></i>':''}

				</td>
				<td>
					${swProduct.field1}
				</td>
				<td title="${swProduct.productName}"><a href="${ctx}/business/product/swProduct/form?id=${swProduct.id}">
	
					${fns:abbr(swProduct.productName,20)}
				</a></td>
				

				<td>
					${fns:getDictLabel(swProduct.type, 'zichan_type', '')}
				</td>
				<td>
					${swProduct.unitPrice}
				</td>
				<td>
					${swProduct.productAmount}
				</td>
				<td>
					${swProduct.totalPrice}
				</td>
				<td>
					${swProduct.officeName}
				</td>
				<td>
					${swProduct.userName}
				</td>
				<td>
					${swProduct.zrUserName}
				</td>
			
				
				<td>
					${swProduct.productDate}
				</td>
				
				<td title="${swProduct.productType}">
					${swProduct.productType}
				</td>
				<td title="${swProduct.productMade}">
					${swProduct.productMade}
				</td>
				<td >
					${fns:getDictLabel(swProduct.projectType, 'project_type', '')}
				</td>
				<shiro:hasPermission name="business:product:swProduct:edit"><td>
				 <!--    <a style='cursor:pointer' onclick='tt("${swProduct.id}")'>${swProduct.state=='0'?'<i class="icon-shopping-cart"></i>采购':''}</a>-->	
    				<a href="${ctx}/business/product/swProduct/form?id=${swProduct.id}">修改</a>
					<!--  <a href="${ctx}/business/product/swProduct/delete?id=${swProduct.id}" onclick="return confirmx('确认要删除该采购设备清单吗？', this.href)">删除</a>-->	
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>