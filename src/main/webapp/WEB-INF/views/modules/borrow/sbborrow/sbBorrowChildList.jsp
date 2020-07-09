<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资源流通管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/homePage/bootstrap_admin/scripts/jquery.jqprint-0.3.js" type="text/javascript"></script>
	<script type="text/javascript">

		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出借用数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/borrow/sbborrow/sbBorrow/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			
			
	        var keycode = "";
	        var lastTime=null;
	        var nextTime=null;
	        var lastCode=null;
	        var nextCode=null;

	        document.onkeydown=function(e){
	            //兼容性处理
	            if(window.event){
	                nextCode = e.keyCode
	            } else if(e.which){
	                nextCode = e.which
	            }
	            
	            //获取当前时间
	            nextTime = new Date().getTime();
	              
	            if(nextCode==13 && keycode!= "" && nextTime - lastTime <= 30){//回车字符
	            	var keys = keycode.split(" ");
	                $("#sbcode").val(keys[2]);
	                $("#searchForm").submit();
	                keycode = "";
	                lastCode = null;
	                lastTime = null;
	            }else{//此处可以增加限制nextCode的种类例如数字
	                if(lastCode == null && lastTime == null){//初始字母
	                    keycode = String.fromCharCode(nextCode);
	                }else if(lastCode != null && lastTime != null && nextTime - lastTime <= 30){
	                    keycode += String.fromCharCode(nextCode);
	                }else{//手动输入
	                    keycode = "";
	                    lastCode = null;
	                    lastTime = null;
	                }
	                lastCode = nextCode;
	                lastTime = nextTime;
	            }
	        }
	        $("#btndy").click(function(){
				dayin() ;
			});
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function dbOnclick(e,e2,e3){
			var endIsGood = $("#endIsGood"+e3).attr("checked")?"1":"0";
			var endReason = ($("#endReason"+e3).val());
			$.ajax({
                type:'post',
                url:'${ctx}/borrow/sbborrow/sbBorrow/updateState',
                data:{'field4':e,'field5':e2,'endIsGood':endIsGood,'endReason':endReason},
                cache:false,
                dataType:'json',
                success:function(data){
                	alert("操作成功");
                	$("#searchForm").submit();
                }
            });
			
		}
		
		function dayin(){
			
			var i=0;
            var j=0;
            var id = '';
            var equipmentName= $("#equipmentName").val();
            var equipmentSbcode= $("#equipmentSbcode").val();
            var userId= $("#userId").val();
            var borrowState= $("#borrowState").val();
   
            $.ajax({
                type:'post',
                 url:'${ctx}/borrow/sbborrow/sbBorrow/getdyInfo',
                 data:{'equipmentName':equipmentName,'equipmentSbcode':equipmentSbcode,'user.id':userId,'borrowState':borrowState},
                 cache:false,
                dataType:'json',
                success:function(data){
                	 var theads = "<thead ><tr bgcolor='#fff' ><th width='50px'>序号</th><th width='100px'>设备名称</th><th width='100px'>规格型号</th><th width='100px'>设备编号</th><th width='100px'>出厂编号</th><th width='100px'>借用部门</th><th width='100px'>借用人员</th> ";
                     var tableBodyStar = " <table border='1' bordercolor='#000000' style='border-collapse:collapse;table-layout:fixed;'>";
                     var tableBodyEnd = "</table>";
                     var tableHead = "<h2 align='center'>借用信息单</h2>";
                     var tableBody="";
                     for(var i = 0 ;i<data.length ;i++){
     	                
    	                 var j =  i +1 ;
    	                 var text='一年';
    	                 if(data[i].meteringTime=='2'){
    	                	 text='两年';
    	                 }
    	                 
    	                 tableBody = tableBody + 
    	                 "<tr bgcolor='#fff' >"+
    	                  "<td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width='50px' >" +j+
    	                  "</td><td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width='150px' >" +data[i].equipmentName+
    	                 "</td><td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width='150px' >"+ data[i].equipmentType+
    	                 "</td><td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width='150px'>"+data[i].equipmentSbcode+
    	                 "</td><td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width='200px'>"+data[i].equipmentCccode+
    	                 "</td><td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width='150px'>"+data[i].office.name+
    	                 "</td><td  style='vertical-align:middle; text-align:center;WORD-WRAP:break-word;' width='150px'>"+data[i].user.name+"</td></tr>";
    	                 
    	              }
                     
                     var t = tableHead;
  	               t += tableBodyStar;
  	             t += theads;
  	                
          
           t += tableBody;
           t += tableBodyEnd;
                document.getElementById("dayin").innerHTML = t;
                $("#dayin").attr("style","display:block;");
                $("#dayin").jqprint();
                $("#dayin").attr("style","display:none;");
               }
            });
		
		
	}
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/borrow/sbborrow/sbBorrow/list">设备借用列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="sbBorrowChild" action="${ctx}/borrow/sbborrow/sbBorrow/childList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>设备名称：</label>
				<form:input path="equipmentName" id="equipmentName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>设备编号：</label>
				<form:input path="equipmentSbcode" id="equipmentSbcode" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>借用人：</label>
				<sys:treeselect id="user" name="user.id" value="${sbBorrowChild.user.id}" labelName="user.name" labelValue="${sbBorrowChild.user.name}"
					title="部门" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>设备状态：</label>
			    <form:select path="borrowState" id="borrowState" class="input-mini" >
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('bstate')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btndy" class="btn btn-primary" type="button" value="打印"/></li>
			<li class="btns"> <input id="btnExport" class="btn btn-primary" type="button" value="导出"/> </li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<form id="listForm" method="post">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				
				<th>设备名称</th>
				<th>设备编号</th>
				<th>规格型号</th>
				<th>出厂编号</th>
				<th>借用部门</th>
				<th>借用人员</th>
			
				<th>借用状态</th>
				<th>是否计量</th>
				<th>计量时间</th>
				<th>借用时间</th>
				<th>归还时间</th>
				<th>是否完好</th>
				<th>说明</th>
			
				<shiro:hasPermission name="borrow:tsBorrow:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sbBorrowChild" varStatus="vs">
			<tr>
				<td><a href="${ctx}/borrow/sbborrow/sbBorrow/sbBorrowChildForm?field1=${sbBorrowChild.id}">
					${sbBorrowChild.equipmentName}
				</a></td>
				
				<td>
					${sbBorrowChild.equipmentSbcode}
				</td>
				<td>
					${sbBorrowChild.equipmentType}
				</td>
				<td>
					${sbBorrowChild.equipmentCccode}
				</td>
				<td>
					${sbBorrowChild.office.name}
				</td>
				<td>
					${sbBorrowChild.user.name}
				</td>
		
				<td>
					${fns:getDictLabel(sbBorrowChild.borrowState, 'bstate', '')}
				</td>
				<td>
					${sbBorrowChild.ismeasurement}
				</td>
				<td>
					<fmt:formatDate value="${sbBorrowChild.measurement}" pattern="yyyy-MM-dd"/>
				</td>
	            <td>
					<fmt:formatDate value="${sbBorrowChild.statrDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${sbBorrowChild.endDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
				   <input type="checkbox" id="endIsGood${vs.index}" name="columnList[${vs.index}].endIsGood" value="1" ${sbBorrowChild.endIsGood eq '1' ? 'checked' : ''}/>
				</td>
				<td>
				   <input name="endReason" id="endReason${vs.index}" type="text" value="${sbBorrowChild.endReason}" style="width:80px;margin:0;padding:0;text-align:center;">
				</td>
				<td>
    				<a onclick="dbOnclick('${sbBorrowChild.id}','ff')" >${sbBorrowChild.borrowState=='7'?'领取':''}</a>
					<a onclick="dbOnclick('${sbBorrowChild.id}','hs','${vs.index}')" >${sbBorrowChild.borrowState=='3'?'归还':''}</a>
					<a onclick="dbOnclick('${sbBorrowChild.id}','hs','${vs.index}')" >${sbBorrowChild.borrowState=='4'?'归还':''}</a>
					
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	 </form>
	<div class="pagination">${page}</div>
	<div id="dayin"></div>
</body>
</html>