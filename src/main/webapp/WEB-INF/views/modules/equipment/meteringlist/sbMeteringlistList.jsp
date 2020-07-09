<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>计量查询管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			 window.parent.window.jBox.close();
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function updateInfo(id){
			top.$.jBox.open("iframe:${ctx}/equipment/meteringlist/sbMeteringlist/form?id="+id,"修改计量信息", 1150, 500, {
	            buttons:{"确认":"ok", "关闭":true},closed:function (){
					  $("#searchForm").submit();
				},
	            submit:function(v, h, f){
	                if(v=="ok"){
	                	var data=h.find("iframe")[0].contentWindow.$("#inputForm").serialize();
	                     var url = "${ctx}/equipment/meteringlist/sbMeteringlist/saveMetering";
	                    $.ajax({
	                        type: 'POST',
	                        url : url,
	                        data: data,             //获取表单数据
	                        success : function(data) {
	                            if (data.result) {
	                                alert("保存成功");
	                                window.parent.page();                 //调用父窗体方法，当关闭子窗体刷新父窗体
	                                window.parent.window.jBox.close();    //关闭子窗体
	                            } else {
	                                alert("保存失败:"+data.message);
	                                window.parent.page();
	                                window.parent.window.jBox.close();
	                            }
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
		<li class="active"><a href="${ctx}/equipment/meteringlist/sbMeteringlist/">计量查询管理列表</a></li>
		<shiro:hasPermission name="equipment:meteringlist:sbMeteringlist:edit"><li><a href="${ctx}/equipment/meteringlist/sbMeteringlist/form">计量查询管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sbMeteringlist" action="${ctx}/equipment/meteringlist/sbMeteringlist/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>设备名称：</label>
				<form:input path="sbname" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>原有编号：</label>
				<form:input path="yycode" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>设备编号：</label>
				<form:input path="sbode" htmlEscape="false" maxlength="255" class="input-medium"/>	</li>
			<li><label>为空：</label>	<form:checkbox path="emp"  value="1"/></li>

		
			<li class="clearfix"></li>
			<li><label>出厂编号：</label>
				<form:input path="cccode" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>计量类型：</label>
				<form:select path="sbtype" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sb_MeteringType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>设备名称</th>
				<th>原有编号</th>
				<th>设备编号</th>
				<th>规格型号</th>
				<th>出厂编号</th>
				<th>数量</th>
				<th>责任部门</th>
				<th>责任人</th>
				<th>保管地点</th>
				<th>计量方式</th>
				<th>检定周期</th>
				<th>检测日期</th>
				<th>设备状态</th>
				<th>计量类型</th>
				<shiro:hasPermission name="equipment:meteringlist:sbMeteringlist:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sbMeteringlist">
			<tr>
				<td><a onclick="updateInfo(${sbMeteringlist.id})" >
					${sbMeteringlist.sbname}
				</a></td>
				<td>
					${sbMeteringlist.yycode}
				</td>
				<td>
					${sbMeteringlist.sbode}
				</td>
				<td>
					${sbMeteringlist.type}
				</td>
				<td>
					${sbMeteringlist.cccode}
				</td>
				<td>
					${sbMeteringlist.amount}
				</td>
				<td>
					${sbMeteringlist.dept}
				</td>
				<td>
					${sbMeteringlist.emp}
				</td>
				<td>
					${sbMeteringlist.local}
				</td>
				<td>
					${fns:getDictLabel(sbMeteringlist.jlfs, 'meteringType', '')}
				</td>
				<td>
					${fns:getDictLabel(sbMeteringlist.jdzq, 'meteringTime', '')}
				</td>
				<td>
					${sbMeteringlist.jcrq}
				</td>
				<td>
					${sbMeteringlist.sbstate}
				</td>
				<td>
					${fns:getDictLabel(sbMeteringlist.sbtype, 'sb_MeteringType', '')}
				</td>
				<shiro:hasPermission name="equipment:meteringlist:sbMeteringlist:edit"><td>
    			
					<a href="${ctx}/equipment/meteringlist/sbMeteringlist/delete?id=${sbMeteringlist.id}" onclick="return confirmx('确认要删除该计量查询管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>