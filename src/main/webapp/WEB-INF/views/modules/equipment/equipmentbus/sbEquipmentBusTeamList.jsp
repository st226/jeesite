<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>著录项管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            parent.window.jBox.close();
            tt('');
            
			$("#btnState").click(function(){
				saveSelectData() ;
			});
			
			$("#info").click(function(){
				info() ;
			});
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出设备数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/equipment/equipmentbus/sbEquipmentBus/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${ctx}/equipment/equipmentbus/sbEquipmentBus?fsType=${fsType}");
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
			$("#btnSubject").click(function(){
				
				showSelectedData();
				
				
			
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
		        
		        var danger = getNowFormatDate();
			    var table = document.getElementById("contentTable")
	                    var rowObj = null; 
	                    var cellObj = null;     

	                    // 引用rows 
	                    for (var i = 0; i < table.rows.length; i ++){ 
	                    	
	                        rowObj = table.rows[i];   
	                       var s = "" ;
				        //    var s = rowObj.cells[10].innerHTML;	
				        //     s = s.replace(/^\s*|\s*$/g,"");
			            //	if(s<danger && s!=''&&s!=null){
					    //         rowObj.cells[10].style.color='red';
				
				                 //	rowObj.cells[10].style.backgroundColor="red";;
				       //        }
				for(var j = 0;j<rowObj.cells.length ; j++){
					s = rowObj.cells[j].innerHTML.replace(/^\s*|\s*$/g,"");
					if(s=='' || s==null){
						rowObj.cells[j].innerHTML='/';
		
					}
				}
				

	         } 


		   
		        
		        
			
		});
		
		function getNowFormatDate() {
		      var date = new Date();
		      var seperator1 = "-";
		      var year = date.getFullYear();
		      var month = date.getMonth() + 1;
		      var strDate = date.getDate();
		      if (month >= 1 && month <= 9) {
		          month = "0" + month;
		      }
		      if (strDate >= 0 && strDate <= 9) {
		          strDate = "0" + strDate;
		      }
		      var currentdate = year + seperator1 + month + seperator1 + strDate;
		      return currentdate;
		  }

		
		function info(){
			
			$.ajax({
               type:'post',
                url:'${ctx}/equipment/equipmentbus/sbEquipmentBus/updateCode',
                cache:false,
               dataType:'json',
               success:function(data){
              	alert("操作成功");
                	$("#searchForm").submit();
              }
           });
			
		}
		
		function showSelectedData(){
				// 正常打开		
				top.$.jBox.open('iframe:/jeesite/a/tag/treeselect?url='+encodeURIComponent('/equipment/equipmentfunction/sbFunctionType/treeData')+'&module=&checked=&extId=&isAll=false', '选择功能类型', 300, 420, {
					buttons:{'确定':'ok', '关闭':true}, submit:function(v, h, f){
						if (v=='ok'){
							var tree = h.find('iframe')[0].contentWindow.tree;//h.find('iframe').contents();
							var ids = [], names = [], nodes = [];
							if ('' == 'true'){
								nodes = tree.getCheckedNodes(true);
							}else{
								nodes = tree.getSelectedNodes();
							}
							for(var i=0; i<nodes.length; i++) {//
								ids.push(nodes[i].id);
								names.push(nodes[i].name);//
								break; // 如果为非复选框选择，则返回第一个选择  
							}
							saveSelectData(ids.join(',').replace(/u_/ig,''),names.join(','));

						}//
						
						if(typeof userCodeTreeselectCallBack == 'function'){
							userCodeTreeselectCallBack(v, h, f);
						}
					}, loaded:function(h){
						$('.jbox-content', top.document).css('overflow-y','hidden');
					}
				});


        }
		
		
		function saveSelectData( ii , jj){
			
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
	            $("#comments").val(ii);
                $("#className").val(jj);
	            $("#inputForm").submit();

	        }
	
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function dbOnclick(e){
			top.$.jBox.open("iframe:${ctx}/equipment/equipmentbus/sbEquipmentBus/form?id="+e, "设备信息修改",1000,600,{
				buttons:{'关闭':true},loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				},closed:function (){
					  $("#searchForm").submit();
				}
			});
			
		}
		
		function deploy(){
			top.$.jBox.open("iframe:${ctx}/sbdeploy/sbDeploy/form", "配置列表",460,500,{
				buttons:{},loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				},closed:function (){
					  $("#searchForm").submit();
				}
			});
		}
		
		function tt(id){
			
			 $.ajax({
	                type:'post',
	                url:'${ctx}/borrow/sbborrow/sbBorrow/getSbRename',
	                data:{'sbId':id},
	                cache:false,
	                dataType:'json',
               success:function(data){
               	var ele=window.document.getElementById ("count"); 
       			ele.innerHTML = data.length;
               }
           });
			
			
		}
		
		function exportExcel(){
			top.$.jBox.confirm("确认要导出设备数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#searchForm").attr("action","${ctx}/equipment/equipmentbus/sbEquipmentBus/exportExcel");
					$("#searchForm").submit();
					$("#searchForm").attr("action","${ctx}/equipment/equipmentbus/sbEquipmentBus?fsType=${fsType}");
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
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
		<form id="importForm" action="${ctx}/equipment/equipmentbus/sbEquipmentBus/import?busType=${fsType}" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/equipment/equipmentbus/sbEquipmentBus/import/template?&busType=${fsType}">下载模板1</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/equipment/equipmentbus/sbEquipmentBus/">仪器设备列表</a></li>
		<shiro:hasPermission name="equipment:equipment:sbEquipment:edit"><li><a href="${ctx}/equipment/equipmentbus/sbEquipmentBus/form?busType=${fsType}">仪器设备添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sbEquipment" action="${ctx}/equipment/equipmentbus/sbEquipmentBus/listTeam?fsType=${fsType}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>综合查询：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sb_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>是否计量：</label>
				<form:select path="field5" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('is_leaf')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();" /></li>
			


			
		</ul>
	</form:form>
	<form:form id="inputForm" modelAttribute="genTable" action="${ctx}/equipment/equipmentbus/sbEquipmentBus/saveEquipment" method="post" class="form-horizontal">
	<sys:message content="${message}"/>
	<input type="hidden" name="name" value="${fsType}"/> 
	<input type="hidden" name="comments" id="comments" /> 
	<input type="hidden" name="className" id="className" /> 
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			<th title="选择"><input type="checkbox" id="checkedAll">
				<c:forEach items="${TsResourceBus}" var="tsResource">
			       <th class="sort-column ${tsResource.columnName}" width="${tsResource.width}px">${tsResource.columnComments}</th>
			</c:forEach>

				<shiro:hasPermission name="resourcebus:tsResourceBus:edit">
				<th width="100px" align="center">
				<a style='cursor:pointer' onclick='deploy("配置")'  title="配置查看列" ><i class="icon-cogs" style="color: red;" ></i> </a>
				
				</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${page.list}" var="maps"  varStatus="vs">
			<tr>
			
			      <td>
                         <input type="checkbox" name="checkbox_name[${vs.index}]"/>
                         <input type="hidden" name="columnList[${vs.index}].isList" value="0"/>      
						 <input type="hidden" name="columnList[${vs.index}].id" value="${maps.id}"/>
                 </td>
				 <c:forEach items="${TsResourceBus}" var="tsResource">
			     <td>   ${not empty tsResource.dictType? fns:getDictLabel(maps[tsResource.columnName],tsResource.dictType,''):maps[tsResource.columnName]}  </td>
              </c:forEach>  
			<td>
				
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</form:form>
</body>
</html>