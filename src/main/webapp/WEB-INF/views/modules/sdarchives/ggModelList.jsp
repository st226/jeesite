<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>三单维护管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function tt(id){
			top.$.jBox.open("iframe:${ctx}/sdarchives/sdApplication/updateModel?bjjd="+id, "更新",600,420,{
				buttons:{"确认":"ok","关闭":true}, loaded:function(h){
					 $("#searchForm").submit();
				},
	            submit:function(v, h, f){	
	                if(v=="ok"){
	                	var bjjd=h.find("iframe")[0].contentWindow.$("#id").val();
	                	var files=h.find("iframe")[0].contentWindow.$("#files").val();
	                	var data={'bjjd':bjjd,'files':files};
	                     
	                     var url = '${ctx}/sdarchives/sdApplication/saveModel';
	                    $.ajax({
	                        type: 'POST',
	                        url : url,
	                        async:false,
	                        data: data,             //获取表单数据
	                        success : function(data) {
	                            //调用父窗体方法，当关闭子窗体刷新父窗体
	                            window.parent.window.jBox.close();    //关闭子窗体
	                            $("#searchForm").submit();
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
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sdarchives/sdApplication/ggList">更改单列表</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="sdModel" action="${ctx}/sdarchives/sdApplication/ggModelList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>型号：</label>
				<form:input path="modelName" htmlEscape="false" maxlength="55" class="input-medium"/>
			</li>
			<li><label>文件代号：</label>
				<form:input path="wjNumber" htmlEscape="false" maxlength="55" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>型号</th>
								<th>文件代号</th>
								<th>阶段标记</th>
								<th>密级</th>
								<th>更改单号</th>
								<th>发送单位</th>
								<th>使用性</th>
								<th>更改类别</th>
								<th>更改原因</th>
								<th>制品处理意见</th>
								<th>归档人</th>
								<th>归档日期</th>
								<th>批准日期</th>
								<th>实施日期</th>
								<th width="120px">三单</th>
								<shiro:hasPermission name="sdarchives:sdApplication:delete">	<th>操作</th></shiro:hasPermission>
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sdModel">
			<tr>
				<td>
					${sdModel.modelName}
			    </td>
				<td>
					${sdModel.wjNumber}
				</td>
				<td>
					${sdModel.bjjd}
				</td>
				<td>
					${fns:getDictLabel(sdModel.modelClass, 'MODEL_CLASS', '')}
				</td>
				<td>
					${sdModel.sdNumber}
				</td>
				
				<td>
					${sdModel.officeName}
				</td>
				<td>
					${sdModel.syx}
				</td>
				<td>
					${sdModel.ggbj}
				</td>
				<td>
					${sdModel.ggyy}
				</td>
				<td>
					${sdModel.clyj}
				</td>
				<td>
					${sdModel.gdr}
				</td>
				<td>
					<fmt:formatDate value="${sdModel.gdrq}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${sdModel.pzrq}" pattern="yyyy-MM-dd"/>
				</td>
				
				<td>
					<fmt:formatDate value="${sdModel.ssrq}" pattern="yyyy-MM-dd"/>
				</td>
					 <td>
             <a href='${(sdModel.files).split("\\|")[1]}'>
                ${(sdModel.files!=null && sdModel.files!="")?' <i style="color: red;" class="icon-paper-clip"></i>':''}
               </a>
                 </td>
				
    <shiro:hasPermission name="sdarchives:sdApplication:delete"><td>
                     <a onclick="tt('${sdModel.id}')"  ><i class="icon-cloud-upload"></i>上传</a>
					<a href="${ctx}/sdarchives/sdApplication/deleteModel?on=${sdModel.id}&modelName=ggModelList" onclick="return confirmx('确认要删除该三单维护吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>