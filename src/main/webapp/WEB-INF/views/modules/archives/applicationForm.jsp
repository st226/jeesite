<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>现行文件管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<script type="text/javascript" src="${ctxStatic}/indexHome/excel.js"></script>
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
			
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
			
			  $("#uploadFile").change(function(){
					if(!this.files) {
		                return;
		            }
					
		            var f = this.files[0];
		            var reader = new FileReader();
		            reader.onload = function(e) {
		                var data = e.target.result;
		                if(rABS) {
		                    wb = XLSX.read(btoa(fixdata(data)),{
		                        type: 'base64'
		                    });
		                } else {
		                    wb = XLSX.read(data,{
		                        type: 'binary'
		                    });
		                }
		                var sj =  XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]]);
						for(var i=0 ; i<sj.length ;i++){
							
					   var mj = '' ;
					   if(sj[i].密级=="内部"){
						   var mj = '1' ;
					   }
					   if(sj[i].密级=="非密"){
						   var mj = '2' ;
					   }
					   if(sj[i].密级=="秘密"){
						   var mj = '3' ;
					   }
					   if(sj[i].密级=="机密"){
						   var mj = '4' ;
					   }
			
						row =  {"syIndex":sj[i].序号,"imageNo":sj[i].图号,"imagePageno":sj[i].名称,"modelClass":mj,"syJdbj":sj[i].阶段,"imageNumber":sj[i].份数,"officeName":sj[i].发往部门};
	                    addRow('#modelList',modelRowIdx, modelTpl, row);
	                    modelRowIdx = modelRowIdx + 1;
						}
		            };
		            if(rABS) {
		                reader.readAsArrayBuffer(f);
		            } else {
		                reader.readAsBinaryString(f);
		            }
				})
			
		});
		function addRow(list, idx, tpl, row){
			if(row==null ){
				row = {"syIndex":modelRowIdx+1};
			} 
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
		
		
		
		var wb;//读取完成的数据
        var rABS = false; //是否将文件读取为二进制字符串


        function fixdata(data) { //文件流转BinaryString
            var o = "",
                l = 0,
                w = 10240;
            for(; l < data.byteLength / w; ++l) o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w, l * w + w)));
            o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w)));
            return o;
        }
	</script>
	<style>
        /*a  upload */
        .a-upload {
            padding: 4px 10px;
            height: 20px;
            line-height: 20px;
            position: relative;
            cursor: pointer;
            color: #888;
            background: #FFFFFF;
            border: 1px solid #ddd;
            border-radius: 4px;
            overflow: hidden;
            *display: inline;
            *zoom: 1
        }

        .a-upload  input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
            filter: alpha(opacity=0);
            cursor: pointer
        }

        .a-upload:hover {
            color: #444;
            background: #eee;
            border-color: #ccc;
            text-decoration: none
        }

      
    </style>
</head>
<body>
      <div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/business/product/swProduct/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			
			<input id="btnImportSubmit" class="btn btn-primary" type="button" onclick="onclickDR()" value="   导    入   "/>
			<a href="${ctx}/business/product/swProduct/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/archives/application/">晒印列表</a></li>
		<li class="active"><a href="${ctx}/archives/application/form?id=${application.id}">晒印申请<shiro:hasPermission name="archives:application:edit">${not empty application.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="archives:application:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="application" action="${ctx}/archives/application/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<fieldset>
			<table class="table-form">
				<tr>
					<td class="tit">型号：</td><td>
						<sys:treeselect id="busType" name="busType" value="${application.busType}" 
				labelName="busTypeName" labelValue="${application.busTypeName}"
					title="选择型号/设备" url="/resourcetype/tsResourceType/treeData" cssClass="required" allowClear="true"/>     
		
			       <span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit">申请原因：</td><td>
						<form:input path="applyCour" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
				</tr>
				
				<tr>
					<td class="tit">申请单位：</td><td>
						<sys:treeselect id="office" name="office.id" value="${application.office.id}" labelName="office.name" labelValue="${application.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="required" allowInput="false" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td><td class="tit">晒印批组：</td><td>
						<form:select path="apllySypc" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('APLLY_SYPC')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
				</tr>
				<tr>
					<td class="tit">申请人：</td><td>
						<sys:treeselect id="user" name="user.id" value="${application.user.id}" labelName="user.name" labelValue="${application.user.name}"
					title="用户" url="/sys/office/treeData?type=3"  allowInput="false"  cssClass="required" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>

					</td><td class="tit">申请日期：</td><td>
						<input name="apllyDate" type="text" readonly="readonly"  allowInput="false"  maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${application.apllyDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
				</tr>
				<tr>
					<td class="tit">电话号：</td><td>
						<form:input path="apllyNo" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>

					</td><td class="tit">复印形式：</td><td>
						<form:select path="apllyModel" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('APLLY_MODEL')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
					</td>
				</tr>
				
				
			</table>
		</fieldset>	
		
		

			<legend>晒印详情<a href="${ctxStatic}/indexHome/template.xlsx">（模板）</a></legend>
				 
			
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>序号</th>
								<th>图号</th>
								<th>名称</th>
								<th>密级</th>
								<th>阶段</th>
								<th>份数</th>
								<th>发往部门</th>
								
								<shiro:hasPermission name="archives:application:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="modelList">
						</tbody>
						<shiro:hasPermission name="archives:application:edit"><tfoot>
							<tr><td colspan="9"><a href="javascript:" onclick="addRow('#modelList', modelRowIdx, modelTpl);modelRowIdx = modelRowIdx + 1;" class="btn btn-primary">新增</a>
	
<a href="javascript:;" class="a-upload btn "  >
    <input type="file"  id="uploadFile">导入 
</a>
							</td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="modelTpl">//<!--
						<tr id="modelList{{idx}}">
							<td class="hide">
								<input id="modelList{{idx}}_id" name="modelList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="modelList{{idx}}_delFlag" name="modelList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
                             <td>
								<input id="modelList{{idx}}_syIndex" name="modelList[{{idx}}].syIndex" type="text" value="{{row.syIndex}}" maxlength="500" class="input-small required"/>
							</td>
							<td>
								<input id="modelList{{idx}}_imageNo" name="modelList[{{idx}}].imageNo" type="text" value="{{row.imageNo}}" maxlength="500" class="input-small required"/>
							</td>
							<td>
								<input id="modelList{{idx}}_imagePageno" name="modelList[{{idx}}].imagePageno" type="text" value="{{row.imagePageno}}" maxlength="55" class="input-small required"/>
							</td>
							<td>
								<select id="modelList{{idx}}_modelClass" name="modelList[{{idx}}].modelClass" data-value="{{row.modelClass}}" class="input-small required">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('MODEL_CLASS')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
                                <td>
								<select id="modelList{{idx}}_syJdbj" name="modelList[{{idx}}].syJdbj" data-value="{{row.syJdbj}}" class="input-small required">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('yzgc')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input id="modelList{{idx}}_imageNumber" name="modelList[{{idx}}].imageNumber" type="text" value="{{row.imageNumber}}" maxlength="20" class="input-small required digits"/>
							</td>
							<td>
								<sys:treeselect id="modelList{{idx}}_office" name="modelList[{{idx}}].office.id" value="{{row.office.id}}" labelName="modelList[{{idx}}].officeName" labelValue="{{row.officeName}}"
									title="部门" url="/sys/office/treeData?type=4"  cssClass="required" allowClear="true" notAllowSelectParent="true"/>
							</td>
							
							
							<shiro:hasPermission name="archives:application:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#modelList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var modelRowIdx = 0, modelTpl = $("#modelTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(application.modelList)};
							for (var i=0; i<data.length; i++){
								addRow('#modelList', modelRowIdx, modelTpl, data[i]);
								modelRowIdx = modelRowIdx + 1;
							}
						});
					</script>
			
		
		<div class="form-actions">
			<shiro:hasPermission name="archives:application:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>