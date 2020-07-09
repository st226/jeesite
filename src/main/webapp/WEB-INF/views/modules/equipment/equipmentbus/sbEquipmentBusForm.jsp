<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图书馆资源管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
			
			
			
			
			showSelectedData();
			
		
			
			
			
		});
		
		function showSelectedData(){
			var codeString=$("#codeString").val();
        	$("#testHtml").html(codeString);
        	$('#sbType_Button, #sbType_Name').click(function(){
			// 是否限制选择，如果限制，设置为disabled
				if ($('#busType_Button').hasClass('disabled')){
					return true;
				}
				// 正常打开		
				top.$.jBox.open('iframe:/jeesite/a/tag/treeselect?url='+encodeURIComponent('/equipment/equipmentfunction/sbFunctionType/treeData')+'&module=&checked=&extId=&isAll=', '选择功能类型', 300, 420, {
					ajaxData:{selectIds: $('#sbType_Id').val()},buttons:{'确定':'ok', '清除':'clear', '关闭':true}, submit:function(v, h, f){
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
							$('#sbType_Id').val(ids.join(',').replace(/u_/ig,''));
							$('#sbType_Name').val(names.join(','));
						}//
						else if (v=='clear'){
							$('#sbType_Id').val('');
							$('#sbType_Name').val('');
		                }//
						if(typeof userCodeTreeselectCallBack == 'function'){
							userCodeTreeselectCallBack(v, h, f);
						}
					}, loaded:function(h){
						$('.jbox-content', top.document).css('overflow-y','hidden');
					}
				});
			});
        	
        	$('#fsType_Button, #fsType_Name').click(function(){
    			// 是否限制选择，如果限制，设置为disabled
    				if ($('#busType_Button').hasClass('disabled')){
    					return true;
    				}
    				// 正常打开		
    				top.$.jBox.open('iframe:/jeesite/a/tag/treeselect?url='+encodeURIComponent('/equipment/equipmenttype/sbEquipmentType/treeData')+'&module=&checked=&extId=&isAll=', '选择功能类型', 300, 420, {
    					ajaxData:{selectIds: $('#fsType_Id').val()},buttons:{'确定':'ok', '清除':'clear', '关闭':true}, submit:function(v, h, f){
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
    							$('#fsType_Id').val(ids.join(',').replace(/u_/ig,''));
    							$('#fsType_Name').val(names.join(','));
    						}//
    						else if (v=='clear'){
    							$('#fsType_Id').val('');
    							$('#fsType_Name').val('');
    		                }//
    						if(typeof userCodeTreeselectCallBack == 'function'){
    							userCodeTreeselectCallBack(v, h, f);
    						}
    					}, loaded:function(h){
    						$('.jbox-content', top.document).css('overflow-y','hidden');
    					}
    				});
    			});
        	
        	
        	$("#team_Button, #team_Name").click(function(){
        		// 是否限制选择，如果限制，设置为disabled
        		if ($("#team_Button").hasClass("disabled")){
        			return true;
        		}
        		// 正常打开	
        		top.$.jBox.open("iframe:/jeesite/a/tag/treeselect?url="+encodeURIComponent("/sys/office/treeData?type=2")+"&module=&checked=&extId=&isAll=", "选择部门", 300, 420, {
        			ajaxData:{selectIds: $("#team_Id").val()},buttons:{"确定":"ok", "清除":"clear", "关闭":true}, submit:function(v, h, f){
        				if (v=="ok"){
        					var tree = h.find("iframe")[0].contentWindow.tree;//h.find("iframe").contents();
        					var ids = [], names = [], nodes = [];
        					if ("" == "true"){
        						nodes = tree.getCheckedNodes(true);
        					}else{
        						nodes = tree.getSelectedNodes();
        					}
        					for(var i=0; i<nodes.length; i++) {//
        						if (nodes[i].isParent){
        							top.$.jBox.tip("不能选择父节点（"+nodes[i].name+"）请重新选择。");
        							return false;
        						}//
        						ids.push(nodes[i].id);
        						names.push(nodes[i].name);//
        						break; // 如果为非复选框选择，则返回第一个选择  
        					}
        					$("#team_Id").val(ids.join(",").replace(/u_/ig,""));
        					$("#team_Name").val(names.join(","));
        				}//
        				else if (v=="clear"){
        					$("#team_Id").val("");
        					$("#team_Name").val("");
                        }//
        				if(typeof officeTreeselectCallBack == 'function'){
        					officeTreeselectCallBack(v, h, f);
        				}
        			}, loaded:function(h){
        				$(".jbox-content", top.document).css("overflow-y","hidden");
        			}
        		});
        	});
        	
        	$("#field2,#unitprice").bind("input change",function(event){
			       var a = $("#field2").val() ;
			      var b = $("#unitprice").val() ;
			      $("#price").val(a*b) ;
			       
			});
        	
        	

        }
		
		
	</script>
</head>
<body onload="load()" >
	<ul class="nav nav-tabs">
		<!-- <li><a href="${ctx}/resourcebus/tsResourceBus/list?busType=${busType}">上传文件列表</a></li> -->
		<li class="active"><a href="${ctx}/equipment/equipmentbus/sbEquipmentBus/form?id=${tsResource.id}">录入设备信息<shiro:hasPermission name="resource:tsResource:edit">${not empty tsResource.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="resource:tsResource:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sbEquipment" action="${ctx}/equipment/equipmentbus/sbEquipmentBus/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		 <input type="hidden" id="codeString" name="codeString" value="${codeString}" class="input-medium"/>

		<div id="testHtml"> 
            
		 </div>
		 
		
		
		
		
		
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>