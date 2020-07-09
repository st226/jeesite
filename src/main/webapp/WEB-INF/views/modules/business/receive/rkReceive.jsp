<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仪器设备开箱验收管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/homePage/bootstrap_admin/scripts/jquery.jqprint-0.3.js" type="text/javascript"></script>
	<script src="${ctxStatic}/homePage/bootstrap_admin/scripts/shuiyin.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
		
		});
		
		
		
		
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	
		
		
		
		function dayin1(){
			 $("#dayin").jqprint();
	
		}
		
		function ok(e){
			var file = $("#rfiles").val();
			if(file==null || file==""){
				alert("请先上传审批结果！");
			}else{
				 $("#state").val("1");
					$("#inputForm").submit();
			}
		}
		
		
		
	</script>
</head>
<body>
	
	
	<sys:message content="${message}"/>
	<form:form id="inputForm" modelAttribute="swReceiveEquipment" action="${ctx}//business/receive/swReceive/rksave" method="post" class="form-horizontal">
		<form:hidden path="equipmentId"/>
		<form:hidden path="state"/>
		<div id="dayin">
	<table  border='1' bordercolor='#000000' style='border-collapse:collapse;width: 820px;margin-left: 36px;margin-right:56px;margin-top:26px;'>
		<caption align="top"  style="font-size: 28px;margin-bottom: 10px;line-height:150%" ><b>设备入库</b></caption>
				
		<thead>
			<tr height="40px" >
			 <!--    <th title="选择"><input type="checkbox" id="checkedAll"></th>--> 
		
				<td   class="tit"   style="font-family: 黑体, SimHei; text-align:center;width: 8%">设备名称</td>
				<td  class="tit"   style="font-family: 黑体, SimHei; text-align:center;width: 8%">规格、型号</td>
			
				<td   class="tit" style="font-family: 黑体, SimHei; text-align:center;width: 8%">原值</td>
				<td   class="tit"   style="font-family: 黑体, SimHei; text-align:center;width: 8%">功率</td>
				<td   class="tit" style="font-family: 黑体, SimHei; text-align:center;width: 8%">出厂编号</td>
				<td   class="tit" style="font-family: 黑体, SimHei; text-align:center;width: 8%">资产编号</td>
				<td   class="tit" style="font-family: 黑体, SimHei; text-align:center;width: 8%">数量</td>
				<td   class="tit" style="font-family: 黑体, SimHei; text-align:center;width: 8%">单价</td>
			<td   class="tit" style="font-family: 黑体, SimHei; text-align:center;width: 8%">总价</td>
				<td   class="tit" style="font-family: 黑体, SimHei; text-align:center;width: 8%">责任部门</td>
				<td   class="tit" style="font-family: 黑体, SimHei; text-align:center;width: 8%">责任人</td>
				<td   class="tit" style="font-family: 黑体, SimHei; text-align:center;width: 8%">放置地点</td>
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="swReceiveEquipment" varStatus="vs">
			<tr height="40px" >
			  <!--      <td>
                         <input type="checkbox" name="checkbox_name[${vs.index}]"/>
                         <input type="hidden" name="columnList[${vs.index}].isList" value="0"/>      
						 <input type="hidden" name="columnList[${vs.index}].id" value="${swReceiveEquipment.id}"/>
						  <input type="hidden" name="columnList[${vs.index}].state" value="${swReceiveEquipment.state}"/>
				
                 </td>--> 
                
				<td  class="tit"   style="font-family: 黑体, SimHei; text-align:center;width: 8%">
					${swReceiveEquipment.equipmentName}
				</td>
				<td  class="tit"   style="font-family: 黑体, SimHei; text-align:center;width: 8%">
					${swReceiveEquipment.equipmentModel}、${swReceiveEquipment.equipmentNorms}
				</td>
				
				<td  class="tit"   style="font-family: 黑体, SimHei; text-align:center;width: 8%">
					${swReceiveEquipment.equipmentValue}
				</td>
				<td  class="tit"   style="font-family: 黑体, SimHei; text-align:center;width: 8%">
					${swReceiveEquipment.equipmentPower}
				</td>
				<td  class="tit"   style="font-family: 黑体, SimHei; text-align:center;width: 8%">
					${swReceiveEquipment.equipmentFactoryNumber}
				</td>
				<td  class="tit"   style="font-family: 黑体, SimHei; text-align:center;width: 8%">
					${swReceiveEquipment.zccode}
				</td>
				<td  class="tit"   style="font-family: 黑体, SimHei; text-align:center;width: 8%">
					${swReceiveEquipment.amount}
				</td>
				<td  class="tit"   style="font-family: 黑体, SimHei; text-align:center;width: 8%">
					${swReceiveEquipment.unitprice}
				</td>
				<td  class="tit"   style="font-family: 黑体, SimHei; text-align:center;width: 8%">
					${swReceiveEquipment.price}
				</td>
				<td  class="tit"   style="font-family: 黑体, SimHei; text-align:center;width: 8%">
					${swReceiveEquipment.teamname}
				</td>
				<td  class="tit"   style="font-family: 黑体, SimHei; text-align:center;width: 8%">
					${swReceiveEquipment.usepeoplename}
				</td>
				<td  class="tit"   style="font-family: 黑体, SimHei; text-align:center;width: 8%">
					${swReceiveEquipment.local}
				</td>
				</tr>
		</c:forEach>
		<tr>
				   <td height="100px" style='vertical-align:middle; text-align:center;font-family: 黑体, SimHei; '  colspan="3">采购员意见</td>
				    <td colspan="9"  style='vertical-align:bottom; text-align:right;font-family: 黑体, SimHei; ' >
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				   <td height="100px" style='vertical-align:middle; text-align:center;font-family: 黑体, SimHei; '  colspan="3">库管员意见</td>
				    <td colspan="9"  style='vertical-align:bottom; text-align:right;font-family: 黑体, SimHei; ' >
					签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				
		</tbody>
	</table>
	</div>
    <table  border='1' bordercolor='#000000' style='border-collapse:collapse;width: 820px;margin-left: 36px;margin-right:56px;'>
	<tr id="rkfiles" >
					<td width="24%" style='vertical-align:middle; text-align:center;font-family: 黑体, SimHei; ' >上传入库申请：</td>
					<td  width="72%" >
							<form:hidden id="rfiles" path="rfiles" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="rfiles" type="files" uploadPath="/business/receive" selectMultiple="true"/>
				
					</td>
					
				</tr>

	</table>
	
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="暂 存"/>
			<input id="btnSubmitok" class="btn  btn-primary"   type="button"   onclick="ok(1)"  value="提交"/>
			<input id="dayin" class="btn" type="button"  onclick="dayin1()" value="打印"/>
			<input id="btnCancel" class="btn" type="button"  value="返 回"  onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>