<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
	<title>著录项管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("input[name=id]").each(function(){
				var articleSelect = null;
				$(this).click(function(){
					var checked = $(this).attr("checked") ;
					var id = $(this).val();
					var local = $("#local").val();
					 $.ajax({
			                type:'post',
			                url:'${ctx}/collection/tsCollection/shelves',
			                data:{'book_id':id,'local':local,'check':checked},
			                cache:false,
			                dataType:'json',
			                success:function(data){
			                   
			                }
			            });
					
					
					
					
				});
			});
		});
	
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
   
	
	
	<form:form id="searchForm" modelAttribute="tsResourceBus" action="${ctx}/resourcebus/tsResourceBus/localSJ" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<form:hidden id="local" path="local"/>
		<form:hidden id="busType" path="busType"/>
		<ul class="ul-form">
			<li><label>资源名称：</label>
				<form:input path="columnName" htmlEscape="false" maxlength="50" class="input-medium"/>
				
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			
				   <th style="text-align:center;">选择</th>
			       <th>资源类别</th>
			       <th>索书号</th>
			       <th>资源号</th>
			       <th>资源名称</th>
			       <th>作者</th>
			       <th>ISBN</th>
			       <th>出版社</th>
			       <th>关键词</th>
		
			
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="maps">
			<tr>
			
				<td style="text-align:center;"><input type="checkbox" name="id" value="${maps.id}"  /></td>
				<td>
					${maps.bus_type}
				</td>
				<td>
					${maps.key_code}
				</td>
				<td>
					${maps.resource_code}
				</td>
				<td>
					${maps.name}
				</td>
				<td>
					${maps.author}
				</td>
				<td>
					${maps.isbn}
				</td>
				<td>
					${maps.press}
				</td>
				<td>
					${maps.keyword}
				</td>
				
				
			</tr>
		</c:forEach>
		
		
		
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>