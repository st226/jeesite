<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>计量管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnwc").click(function(){
				wancheng() ;
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
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function test(pid){
			top.$.jBox.open("iframe:http://10.193.200.16:8088/default/archives/archives/imageFlow.jsp?id="+pid, "流程跟踪",810,$(top.document).height()-110);
			
		}
		function updateMetering(id){
			top.$.jBox.open("iframe:${ctx}/equipment/metering/sbMetering/updateMetering?field1="+id, "更新计量信息",600,420,{
				buttons:{"关闭":true}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}
		function showSelectedData(){
			// 正常打开		
			top.$.jBox.open("iframe:/jeesite/a/tag/treeselect?url="+encodeURIComponent("/sys/office/treeData?type=3")+"&module=&checked=&extId=&isAll=", "选择用户", 300, 420, {
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
		
		function saveSelectData(ids,names){
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
            $("#comments").val(ids);
            $("#className").val(names);
            $("#inputForm").submit();
		}
		
           function wancheng(){
        	   var field1 = $("#field1").val();
        	   window.location =  "${ctx}/equipment/metering/sbMetering/submitMetering?pid="+field1;
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/equipment/metering/sbMetering/">计量管理列表</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="sbMeteringChild" action="${ctx}/equipment/metering/sbMetering/sbMeteringChildEmpList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="field1" name="field1" type="hidden" value="${field1}"/>
		<ul class="ul-form">
			
			<li><label>设备名称：</label>
				<form:input path="equipmentName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>设备编号：</label>
				<form:input path="equipmentSbcode" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>计量方式：</label>
				<form:select path="meteringType" class="input-mini">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('meteringType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>检定周期：</label>
				<form:select path="meteringTime" class="input-mini">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('meteringTime')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnwc" class="btn btn-primary" type="button" value="完成"/>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<form:form id="inputForm" modelAttribute="genTable" action="${ctx}/equipment/metering/sbMetering/sbMeteringCoordination" method="post" class="form-horizontal">
	<sys:message content="${message}"/>
	<input type="hidden" name="comments" id="comments" /> 
	<input type="hidden" name="className" id="className" />
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th title="选择"><input type="checkbox" id="checkedAll">
				<th>设备名称</th>
								<th>规格型号</th>
								<th>设备编号</th>
								<th>出厂编号</th>
								<th>责任部门</th>
								<th>责任人</th>
								<th>计量方式</th>
								<th>检定周期</th>
								<th>最新有效期</th>
								<th>是否计量完成</th>
								<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sbMeteringChild"  varStatus="vs">
			<tr>
			 <td>
                         <input type="checkbox" name="checkbox_name[${vs.index}]"/>
                         <input type="hidden" name="columnList[${vs.index}].isList" value="0"/>      
						 <input type="hidden" name="columnList[${vs.index}].id" value="${sbMeteringChild.id}"/>
                 </td>
				
				<td>
					${sbMeteringChild.equipmentName}
				</td>
				<td>
					${sbMeteringChild.equipmentType}
				</td>
				<td>
					${sbMeteringChild.equipmentSbcode}
				</td>
				<td>
					${sbMeteringChild.equipmentCccode}
				</td>
				<td>
					${sbMeteringChild.department}
				</td>
				<td>
					${sbMeteringChild.person}
				</td>
				<td>
					${fns:getDictLabel(sbMeteringChild.meteringType, 'meteringType', '')}
				</td>
				
				<td>
					${fns:getDictLabel(sbMeteringChild.meteringTime, 'meteringTime', '')}
				</td>
				<td>
				<fmt:formatDate value="${sbMeteringChild.measurement}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${sbMeteringChild.meteringState=='1'?'<a><i class="icon-ok"></i></a>':'<i class="icon-remove"></i>'}
				</td>
				<td>
				<a onclick="updateMetering('${sbMeteringChild.id}')"  ><i class="icon-cloud-upload"></i>更新计量信息</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</form:form>
	<div class="pagination">${page}</div>
</body>
</html>