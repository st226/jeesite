<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>现行文件管理</title>
	<meta name="decorator" content="default"/>
	<link type="text/css" href="${ctxStatic}/homePage/bootstrap_admin/css/theme.css" rel="stylesheet">
	<script src="${ctxStatic}/homePage/bootstrap_admin/scripts/jquery.jqprint-0.3.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		
		function dbOnclick(e,e2,e3,e4){
			if(e3=='xh'){
				$.ajax({
	                type:'post',
	                url:'${ctx}/archives/application/updateState',
	                data:{'id':e4,'atlasPid':e,'atlasStatus':'5'},
	                cache:false,
	                dataType:'json',
	                success:function(data){
	                	alert("销毁成功");
	                	$("#searchForm").submit();
	                }
	            });
			}else if(e3=="ff"){
			//	$("#pppp").jqprint();
			

				top.$.jBox.open("iframe:${ctx}/archives/application/modelFiles?id="+e4+"&atlasPid="+e, "上传回执单",500,300,{
					buttons:{"确定":true}, loaded:function(h){
						$(".jbox-content", top.document).css("overflow-y","hidden");
					}
				});
				
			}
			
	}
		
		function jqprint(){
			
			document.getElementById("top").style.display="block";
			document.getElementById("bottom").style.display="block";
			setHiddenCol(document.getElementById('contentTable'),6)
			setHiddenCol(document.getElementById('contentTable'),7)
			$("#pppp").jqprint();
			document.getElementById("top").style.display="none";
			document.getElementById("bottom").style.display="none";
			setHiddenCol(document.getElementById('contentTable'),6)
			setHiddenCol(document.getElementById('contentTable'),7)
			
		}
		
		function setHiddenCol(oTable,iCol)

		{    

		    for (i=0;i < oTable.rows.length ; i++)    

		    { oTable.rows[i].cells[iCol].style.display = oTable.rows[i].cells[iCol].style.display=="none"?"":"none";     }    

		}   

	</script>
</head>
<body>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/archives/application/">晒印列表</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="model" action="${ctx}/archives/application/listModelss" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

			
			<label>图号：</label>
				<form:input path="imageNo" htmlEscape="false" maxlength="255" class="input-medium" width="60px" />
		
		<!--	<li><label>名称：</label>
				<form:input path="imagePageno" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>  -->
			
			
					<label>发往部门：</label>
				<sys:treeselect id="office" name="office.id" value="${spplication.office.id}" labelName="office.name" labelValue="${application.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
		
			<label>状态：</label>
				<form:select path="atlasStatus" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ATLAS_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
		
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input  class="btn btn-inverse" type="button" onclick="jqprint()" value="分发打印"/>
		
	
	</form:form>
	
	<sys:message content="${message}"/>
	<div id="pppp">
	<div id="top" style="display: none;">
	<table id="contentTable2" class="table table-striped table-bordered table-condensed"  >
	 <caption align="top"><h3>技术资料发送单</h3></caption>  
	
	  <tr>
	    <td width="20%">说明</td>
				<td colspan="6">1、发下去资料请仔细查对！</br>2、差点无错后，请签字，加盖公章，并速退回一联。</td>
			
	  </tr>
	</table>
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th  width="20%">图号</th>
				<th>名称</th>
				<th>密级</th>
				<th>份数</th>
				<th>发往部门</th>
				<th>页数</th>
				<th>状态</th>
				<shiro:hasPermission name="archives:application:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="model">
			<tr>
				<td>
					${model.imageNo}
		       </td>
				<td>
					${model.imagePageno}
				</td>
				<td>
					${fns:getDictLabel(model.modelClass, 'MODEL_CLASS', '')}
				</td>
				<td>
					${model.imageNumber}
				</td>
				<td>
					${model.office.name}
				</td>
				
				<td>
					${model.imagePageNumber}
				</td>
				
				<td>
					${fns:getDictLabel(model.atlasStatus, 'ATLAS_STATUS', '')}
				</td>
				<shiro:hasPermission name="archives:application:edit"><td>
    				<a onclick="dbOnclick('${model.id}','${model.atlasStatus}','ff','${model.applicationId.id}')" >${model.atlasStatus=='1'?'上传回执':''}</a>
    			<!--	<a onclick="dbOnclick('${model.id}','${model.atlasStatus}','wc','${model.applicationId.id}')" >${model.atlasStatus=='1'?'分发完成':''}</a>  -->
					<a onclick="dbOnclick('${model.id}','${model.atlasStatus}','xh','${model.applicationId.id}')" >${model.atlasStatus=='2'?'销毁':''}</a>
					<a onclick="dbOnclick('${model.id}','${model.atlasStatus}','xh','${model.applicationId.id}')" >${model.atlasStatus=='3'?'销毁':''}</a>
					<a onclick="dbOnclick('${model.id}','${model.atlasStatus}','xh','${model.applicationId.id}')" >${model.atlasStatus=='4'?'销毁':''}</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div id="bottom" style="display: none;">
	<table id="contentTable2" class="table table-striped table-bordered table-condensed"  >
	
	  <tr>
	    <td rowspan="3" width="10%">发出单位</td>
		<td width="10%">经手人</td>
		<td ></td>
		<td rowspan="3"  width="10%">收到单位</td>
		<td width="10%">经手人</td>
		<td></td>	
	  </tr>
	   <tr>
	    
		<td width="10%" height="100px">公章</td>
		<td ></td>
		
		<td width="10%">公章</td>
		<td></td>
				
			
	  </tr>
	  <tr>
	    
		<td width="10%">日期</td>
		<td ></td>
		
		<td width="10%">日期</td>
		<td></td>
				
			
	  </tr>
	  
	</table>
	</div>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>