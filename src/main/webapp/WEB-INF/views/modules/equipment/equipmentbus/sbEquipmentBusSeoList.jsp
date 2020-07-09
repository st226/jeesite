<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
   
         
            
		
			
			
			
			
		        
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
		
		function refreshTree(){
			$("#searchForm").submit();
		}
	</script>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<style type="text/css">
		.ztree {overflow:auto;margin:0;_margin-top:10px;padding:10px 0 0 10px;}
	</style>
</head>
<body>
	<sys:message content="${message}"/>
	<div id="content" class="row-fluid">
		<div id="left" class="accordion-group" >
			<div class="accordion-heading">
		    	<a class="accordion-toggle">高级查询<i class="icon-search pull-right" onclick="refreshTree();"></i></a>
		    </div>
		    <form:form id="searchForm" modelAttribute="sbEquipment" action="${ctx}/equipment/equipmentbus/sbEquipmentBus/seoList" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		</br>
		<table>
		   <tr height="45px"><td align="right">&nbsp;设备名称:</td>
		   <td>
		     <form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</td></tr>
			
			<tr height="45px"><td align="right">&nbsp;设备编号:</td>
		   <td>
		     <form:input path="sbcode" htmlEscape="false" maxlength="50" class="input-medium"/>
			</td></tr>
			<tr height="45px"><td align="right">&nbsp;存放地点:</td>
		   <td>
		     <form:input path="field4" htmlEscape="false" maxlength="50" class="input-medium"/>
			</td></tr>
			<tr height="45px"><td align="right">&nbsp;规格型号:</td>
		   <td>
		     <form:input path="type" htmlEscape="false" maxlength="50" class="input-medium"/>
			</td></tr>
			<tr height="45px"><td align="right">&nbsp;出厂编号:</td>
		   <td>
		   
		     <form:input path="field1" htmlEscape="false" maxlength="50" class="input-medium"/>
			</td></tr>
			<tr height="45px"><td align="right">&nbsp;价格&nbsp;>&nbsp;&nbsp;:</td>
		   <td>
		   
		     <form:input path="price" htmlEscape="false" maxlength="50" class="input-medium"/>
			</td></tr>
		
			<tr height="45px"><td align="right">&nbsp;责任人:</td>
		   <td>
		   
		     <form:input path="usepeoplename" htmlEscape="false" maxlength="50" class="input-medium"/>
			</td></tr>
			 
			 <tr height="45px"><td align="right">&nbsp;状态:</td>
		   <td>
		     <form:select path="state" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sb_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td></tr>
			<tr height="45px"><td  align="right">&nbsp;是否计量:</td>
		   <td>
		    <form:select path="field5" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('is_leaf')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td></tr>
				<tr height="45px"><td align="right">&nbsp;责任部门:</td>
		   <td>
		   
		     <sys:treeselect id="team" name="team" value="${swReceiveEquipment.team}"  labelName="teamname" labelValue="${swReceiveEquipment.teamname}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</td></tr>
			<tr height="45px"><td align="right">&nbsp;资金类别:</td>
		   <td>
		   
		     <sys:treeselect id="sfType" name="fsType" value="${swReceiveEquipment.fsType}" labelName="fsTypeName" labelValue="${swReceiveEquipment.fsTypeName}"
					        title="选择设备类别" url="/equipment/equipmenttype/sbEquipmentType/treeData"  cssClass="input-small" allowClear="true"/>
			</td></tr>
			<tr height="45px"><td align="right">&nbsp;设备类别:</td>
		   <td>
		   
		     <sys:treeselect id="sbType" name="sbType" value="${swReceiveEquipment.sbType}" labelName="sbTypeName" labelValue="${swReceiveEquipment.sbTypeName}"
					        title="选择设备类别" url="/equipment/equipmentfunction/sbFunctionType/treeData"  cssClass="input-small" allowClear="true"/>
			</td></tr>
			
		  
			<tr height="50px"><td  align="right"></td>
		   <td align="center">
		   <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();" />
			</td></tr>
		</table>
		
			
			
			

			


			

	</form:form>
		
		</div>
		<div id="openClose" class="close">&nbsp;</div>
		
		<div id="right">
		
		
	<form:form id="searchForm2" style="display:none"  modelAttribute="sbEquipment" action="${ctx}/equipment/equipmentbus/sbEquipmentBus/seoList?fsType=${fsType}" method="post" class="breadcrumb form-search">
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
			
			<li class="btns"><input id="gjSubmit" class="btn btn-primary" type="button" value="高级查询" onclick="gjcx()" /></li>
			


			
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
			
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</form:form>
		
		
		
		
		
		
		
			
		</div>
	</div>
	<script type="text/javascript">
		var setting = {data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
			callback:{onClick:function(event, treeId, treeNode){
					var id = treeNode.id == '0' ? '' :treeNode.id;
					$('#officeContent').attr("src","${ctx}/equipment/equipmentbus/sbEquipmentBus/list?fsType="+id);
				}
			}
		};
		

		 
		var leftWidth = 250; // 左侧窗口大小
		var htmlObj = $("html"), mainObj = $("#main");
		var frameObj = $("#left, #openClose, #right, #right iframe");
		function wSize(){
			var strs = getWindowSize().toString().split(",");
		//	htmlObj.css({"overflow-x":"hidden", "overflow-y":"hidden"});
			mainObj.css("width","auto");
			frameObj.height(strs[0] - 5);
			var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
			$("#right").width($("#content").width()- leftWidth - $("#openClose").width() -5);
			
		}
	</script>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>