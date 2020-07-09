	<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>业务表字段管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
    
    $(document).ready(function () {
    	$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出采购数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#searchForm").attr("action","${ctx}/order/tsOrder/export");
					$("#searchForm").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
        $("#inputForm").validate({
            submitHandler: function (form) {
                loading('正在提交，请稍等...');
                $("input[type=checkbox]").each(function () {
                    $(this).after("<input type=\"hidden\" name=\"" + $(this).attr("name") + "\" value=\""
                            + ($(this).attr("checked") ? "1" : "0") + "\"/>");
                    $(this).attr("name", "_" + $(this).attr("name"));
                });
                form.submit();
            },
            errorContainer: "#messageBox",
            errorPlacement: function (error, element) {
                $("#messageBox").text("输入有误，请先更正。");
                if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                    error.appendTo(element.parent().parent());
                } else {
                    error.insertAfter(element);
                }
            }
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

        showSelectedData();
    });
       


        function saveSelectData(){
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
           

            $("#inputForm").submit();

        }


        function showSelectedData(){

            var s=0;
            $("input[name*='checkbox_name']").each(function () {
                $(this).attr("checked", false);
                $("input[name='columnList["+s+"].sort']").val('0');
                s++;
            });
            var id=$("#id").val();
           
            $.ajax({
                type:'post',
                url:'${ctx}/order/tsOrder/getSelectColumnData',
                data:{'orderId':id},
                cache:false,
                dataType:'json',
                success:function(data){
                
                    // [{"id":"13","isNewRecord":false,"busType":"橙色主题","columnId":"9f678abbea784853a3a26f084bd059f4","tableId":"3ffbe7a53ea54fe8a33181b0174c5359","sort":1},{"id":"14","isNewRecord":false,"busType":"橙色主题","columnId":"ea0afdee98204cfabbec2164e0cf4b45","tableId":"3ffbe7a53ea54fe8a33181b0174c5359","sort":2}]
                    if (data != null && data.length > 0) {
                        for (var i = 0; i < data.length; i++) {
                            var j=0;
                            $("input[name*='checkbox_name']").each(function () {
                            	
                                if($("input[name='columnList["+j+"].id']").val()==data[i].id){
                                    //$(this).attr("checked", true);
                                    $("input[name='checkbox_name["+j+"]'").attr("checked", true);
                                    $("input[name='columnList["+j+"].sort'").val(data[i].sort);
                                    return true;
                                }

                                j++;
                            });

                        }
                    }
                }
            });

        }




    </script>
</head>
<body>
<ul class="nav nav-tabs">
             <li><a href="${ctx}/order/tsOrder/">订单管理列表</a></li>
		<li class="active"><a href="${ctx}/order/tsOrder/form?id=${tsOrder.id}">订单管理<shiro:hasPermission name="order:tsOrder:edit"></shiro:hasPermission></a></li>
	
			</ul><br/>
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/order/tsOrder/export" method="post" class="breadcrumb form-search ">
		  <input type="hidden" id="id" name="id" value="${orderId}" class="input-medium"/>
	
	</form:form>
<form:form id="inputForm" modelAttribute="tsOrder" action="${ctx}/order/tsOrder/orderDeploySave" method="post"
           class="form-horizontal">
           <input type="hidden" id="id" name="id" value="${orderId}" class="input-medium"/>
           <table id="contentTable" class="table table-striped table-bordered table-condensed">
                <thead>
                <tr>
                    <th title="选择"><input type="checkbox" id="checkedAll"></th>
                    <th>资源类型</th>
				<th>资源名称</th>
				<th>作者</th>
				<th>出版社</th>
				<th>采购类型</th>
				<th>ISBN</th>
				<th>页数</th>
				<th>价格</th>
				<th>数量</th>
				<th>采购状态</th>
				<th>申请部门</th>
				<th>申请人</th>
				<th>申请时间</th>
				<th>是否重复</th>
				<th>订单号</th>
				<th>备注信息</th>
             
                </tr>
                </thead>
                <tbody id="tableForm">
                <c:forEach items="${tsOrder.columnList}" var="column" varStatus="vs">
                    <tr <c:if test="${column.delFlag =='1'}"> style="display: none" </c:if>>
                        <td>
                            <input type="checkbox" name="checkbox_name[${vs.index}]"/>
                        </td>
                        <td nowrap>
                            <input type="hidden" name="columnList[${vs.index}].id" value="${column.id}"/>
                            <input type="hidden" name="columnList[${vs.index}].isList"
                                   value="0"/>
                            
                               ${fns:getDictLabel(column.zyType, 'zy_type', '')}
                        </td>
                 
				<td>
					${column.name}
				</td>
				<td>
					${column.author}
				</td>
				<td>
					${column.press}
				</td>
				<td>
					${fns:getDictLabel(column.cgType, 'cg_type', '')}
				</td>
				<td>
					${column.isbn}
				</td>
				<td>
					${column.pages}
				</td>
				<td>
					${column.price}
				</td>
				<td>
					${column.count}
				</td>
				<td>
					${fns:getDictLabel(column.zyState, 'zy_state', '')}
				</td>
				<td>
					${column.office.name}
				</td>
				<td>
					${column.user.name}
				</td>
				<td>
					<fmt:formatDate value="${tsPurchase.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${fns:getDictLabel(column.isDouble, 'is_repeat', '')}
				</td>
				<td>
					${column.orderId}
				</td>
				<td>
					${column.remarks}
				</td>
                        
                        
                    </tr>

                </c:forEach>
                </tbody>
                 </table>
                 
                  <div class="form-actions">
        <shiro:hasPermission name="gen:genTable:edit">
        <input id="btnSubmit" class="btn btn-primary" type="button" onclick="saveSelectData()" value="保 存"/>&nbsp;
        <input id="btnExport" class="btn btn-primary" type="button" value="生成采购清单"/>&nbsp;
        </shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
    
    
</form:form>

</body>
</html>