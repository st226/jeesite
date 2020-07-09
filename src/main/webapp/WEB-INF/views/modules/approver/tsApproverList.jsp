<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>现行文件申请管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("#btnState").click(function(){
				saveSelectData(1) ;
			});
			$("#btnStateQ").click(function(){
				saveSelectData(2) ;
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
		
		function saveSelectData(flag){
			
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
	            if(flag==2){
	            	$("#comments").val("T");
	            	
	            }
	            
	            $("#inputForm").submit();

	        }
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/approver/tsApprover/">现行文件申请列表</a></li>
			</ul>
	<form:form id="searchForm" modelAttribute="tsApprover" action="${ctx}/approver/tsApprover/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>文件名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnState" class="btn btn-primary" type="button" value="通过审核"/>
			<li class="btns"><input id="btnStateQ" class="btn btn-inverse" type="button" value="驳回"/>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<form:form id="inputForm" modelAttribute="genTable" action="${ctx}/approver/tsApprover/approver" method="post" class="form-horizontal">
	<sys:message content="${message}"/>
	<input type="hidden" name="comments" id="comments"/> 
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			
			<th title="选择"><input type="checkbox" id="checkedAll">
				<th>文件名称</th>
				
				<th>所属型号</th>
				<th>申请人</th>
				<th>申请部门</th>
				<th>申请时间</th>
				<th>申请状态</th>
				
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tsApprover"  varStatus="vs">
			<tr>
			
			      <td>
			      
			            
                         <input type="checkbox" name="checkbox_name[${vs.index}]"/>
                         <input type="hidden" name="columnList[${vs.index}].isList" value="0"/>      
						 <input type="hidden" name="columnList[${vs.index}].id" value="${tsApprover.id}"/>
                 </td>
				<td>
					${tsApprover.name}
				</td>
				
				<td>
					${tsApprover.typeName}
				</td>
				<td>
					${tsApprover.applicantName}
				</td>
				<td>
					${tsApprover.applicantDeptName}
				</td>
				<td>
					${tsApprover.applicantDate}
				</td>
				<td>
					${fns:getDictLabel(tsApprover.state, 'ap_state', '')}
				</td>
				
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</form:form>
</body>
</html>