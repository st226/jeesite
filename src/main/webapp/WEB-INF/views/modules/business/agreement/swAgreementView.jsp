<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同录入管理(公开)</title>
	<meta name="decorator" content="default"/>
	<style>
<!--
 /* Font Definitions */
 @font-face
	{font-family:Wingdings;
	panose-1:5 0 0 0 0 0 0 0 0 0;}
@font-face
	{font-family:宋体;
	panose-1:2 1 6 0 3 1 1 1 1 1;}
@font-face
	{font-family:黑体;
	panose-1:2 1 6 9 6 1 1 1 1 1;}
@font-face
	{font-family:"Cambria Math";
	panose-1:2 4 5 3 5 4 6 3 2 4;}
@font-face
	{font-family:Calibri;
	panose-1:2 15 5 2 2 2 4 3 2 4;}
@font-face
	{font-family:Verdana;
	panose-1:2 11 6 4 3 5 4 4 2 4;}
@font-face
	{font-family:Cambria;
	panose-1:2 4 5 3 5 4 6 3 2 4;}
@font-face
	{font-family:楷体_GB2312;}
@font-face
	{font-family:"\@宋体";
	panose-1:2 1 6 0 3 1 1 1 1 1;}
@font-face
	{font-family:"\@黑体";
	panose-1:2 1 6 0 3 1 1 1 1 1;}
@font-face
	{font-family:"\@楷体_GB2312";}
 /* Style Definitions */
 p.MsoNormal, li.MsoNormal, div.MsoNormal
	{margin:0cm;


	text-justify:inter-ideograph;
	font-size:10.5pt;
	font-family:"Calibri",sans-serif;}
span.MsoPlaceholderText
	{color:gray;}
.MsoChpDefault
	{font-size:10.0pt;
	font-family:"Calibri",sans-serif;}
 /* Page Definitions */
 @page WordSection1
	{size:595.3pt 841.9pt;
	margin:72.0pt 90.0pt 72.0pt 90.0pt;
	layout-grid:15.6pt;}
div.WordSection1
	{page:WordSection1;}
 /* List Definitions */
 ol
	{margin-bottom:0cm;}
ul
	{margin-bottom:0cm;}
-->
</style>
<script src="${ctxStatic}/homePage/bootstrap_admin/scripts/jquery.jqprint-0.3.js" type="text/javascript"></script>
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
			
			$("#relationButton").click(function(){
				
				
				
				  top.$.jBox.open("iframe:${ctx}/business/contract/swContract?pageSize=8","选择合同信息", 1150, 500, {
			            buttons:{"确认":"ok", "关闭":true},
			            submit:function(v, h, f){
			                if(v=="ok"){
			              //      var data=h.find("iframe")[0].contentWindow.$("#inputForm").serialize();
			                    var i=0;
			    	            var j=0;
			    	            h.find("iframe")[0].contentWindow.$("input[name*='checkbox_name']").each(function () {
			    	                if ($(this).attr("checked") == 'checked') {
			    	                    var supplierName = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].supplierName']").val();
			    	                    var supplierUser = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].supplierUser']").val();
			    	                    var supplierPhone = h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].supplierPhone']").val();
			    	                    var supplierId =  h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].supplierId']").val();
			    	                    var contractId =  h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].contractId']").val();
			    	                    var contractName =  h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].contractName']").val();
			    	                    var contractCode =  h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].contractCode']").val();
			    	                    var contractPrice =  h.find("iframe")[0].contentWindow.$("input[name='columnList["+i+"].contractPrice']").val();
			    	                    
			    	                    
			    	                    $("#agreementNo").val(contractCode);
			    	                    $("#agreementSecond").val(supplierName);
			    	                    $("#amount").val(contractPrice);
			    	                   
			    	                    getSupplier(supplierId);
			    	        
			    	                    j++;
			    	                }
			    	                i++;
			    	            });
			    	     
			    	     //       window.parent.page();                 //调用父窗体方法，当关闭子窗体刷新父窗体
                            window.parent.window.jBox.close();    //关闭子窗体
			                    
			                }
			            },
			            loaded:function(h){
			                $(".jbox-content", top.document).css("overflow-y","hidden");
			            }
			        });
				
				
			});
			
		});
		function addRow(list, idx, tpl, row){
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
		
		function getSupplier(supplierId){
			$.ajax({
                type:'post',
                url:'${ctx}/business/supplier/swSupplier/getSupplier',
                data:{'id':supplierId},
                cache:false,
                dataType:'json',
                success:function(data){
                	$("#secondName").val(data.name);
                    $("#secondPlace").val(data.local);
                    $("#secondNameLiaison").val(data.supplierUser);
                    $("#secondNamePhone").val(data.userPhone);
                    $("#secondBank").val(data.bankName);
                    $("#secondBankNo").val(data.bankNumber);
                    $("#secondDuty").val(data.dutyno);
                    $("#secondZip").val(data.supplierZip);
                    $("#secondTel").val(data.supplierTel);
                	$("#secondFax").val(data.supplierFax);
                	$("#secondLegal").val(data.legal);
                	$("#secondAgent").val(data.agent);
                     
                }
            });
			
		}
		function dayin(){
			 $("#dayin").jqprint();
		}
		function word(){
			
			 
			 var text = document.getElementById("dayin").innerHTML;
			 $.ajax({
	                type:'post',
	                url:'${ctx}/business/special/swSpecial/exportSwSpecial',
	                data:{'content':text},
	                cache:false,
	                dataType:'json',
         success:function(data){
         	window.location.href="/jeesite/userfiles/download/"+data ;
         }
     });
	
		}
	</script>
</head>
<body lang=ZH-CN link=blue vlink=purple style='text-justify-trim:punctuation'>
<div class=WordSection1 style='layout-grid:15.6pt;width: 620px;margin-left: 50px;margin-right: 29px;margin-top: 32px;margin-bottom 32px' id="dayin">

<p class=MsoNormal style='line-height:200%'><b><span style='font-size:16.0pt;
line-height:200%;font-family:宋体'>合同编号：${swAgreement.agreementNo}<span lang=EN-US><span style='font-size:
10.5pt;line-height:200%;font-family:"Calibri",sans-serif;font-weight:normal'> </span></span></span></b></p>

<p class=MsoNormal align=center style='text-align:center;line-height:200%'><b><span
style='font-size:22.0pt;line-height:200%;font-family:宋体;letter-spacing:3.0pt'>物资<span
lang=EN-US>/</span>设备采购合同</span></b></p>

<p class=MsoNormal style='margin-left:310.5pt;text-indent:-310.5pt;line-height:
200%'><span style='font-size:12.0pt;line-height:200%;font-family:宋体';>需 方（甲方）：</span><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>北京航天时代激光导航技术有限责任公司</span><span
lang=EN-US style='line-height:200%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>签订时间：</span><span lang=EN-US
style='font-size:12.0pt;line-height:200%;font-family:宋体'><span style='font-family:"Calibri",sans-serif'>
<fmt:formatDate value="${swAgreement.signingTime}" pattern="yyyy-MM-dd"/></span></span></p>

<p class=MsoNormal style='line-height:200%'><span style='font-size:12.0pt;
line-height:200%;font-family:宋体'>供 方<span lang=EN-US> (</span>乙方）：</span><span
lang=EN-US style='font-size:12.0pt;line-height:200%;font-family:宋体'><span
lang=EN-US><span lang=EN-US>${swAgreement.agreementSecond}</span></span></span><span lang=EN-US
style='font-size:12.0pt;line-height:200%;font-family:宋体'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>签订地点：<span lang=EN-US><span
style='font-size:10.5pt;line-height:200%;font-family:"Calibri",sans-serif'> </span><span
lang=EN-US style='font-size:10.5pt;line-height:200%'><span lang=EN-US>${swAgreement.signingPlace}</span></span></span></span></p>

<p class=MsoNormal align=left style='text-align:left;text-indent:24.0pt'><span
style='font-size:12.0pt;font-family:宋体'>经甲乙双方充分协商，特订立本合同，以便共同遵守。</span></p>

<p class=MsoNormal style='line-height:200%'><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>一、产品名称、型号、数量：</span></p>



<table class=MsoNormalTable border=1 cellspacing=0 cellpadding=0 width="100%"
 style='width:100.0%;border-collapse:collapse;border:none'>
 <tr>
  <td width=106 valign=top style='width:79.85pt;border:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center;line-height:200%'><span
  style='font-size:12.0pt;line-height:200%;font-family:宋体'>产品名称</span></p>
  </td>
  <td width=128 valign=top style='width:95.65pt;border:solid windowtext 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center;line-height:200%'><span
  style='font-size:12.0pt;line-height:200%;font-family:宋体'>规格型号</span></p>
  </td>
   <td width=128 valign=top style='width:95.65pt;border:solid windowtext 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center;line-height:200%'><span
  style='font-size:12.0pt;line-height:200%;font-family:宋体'>计量单位</span></p>
  </td>
  <td width=107 valign=top style='width:59.9pt;border:solid windowtext 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center;line-height:200%'><span
  style='font-size:12.0pt;line-height:200%;font-family:宋体'>数量</span></p>
  </td>
  <td width=106 valign=top style='width:59.8pt;border:solid windowtext 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center;line-height:200%'><span
  style='font-size:12.0pt;line-height:200%;font-family:宋体'>单价（元）</span></p>
  </td>
  <td width=106 valign=top style='width:59.8pt;border:solid windowtext 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center;line-height:200%'><span
  style='font-size:12.0pt;line-height:200%;font-family:宋体'>总价（元）</span></p>
  </td>
 </tr>
<c:forEach items="${swAgreement.swAgreementProductList}" var="product">
			<tr>
				<td width=106 valign=bottom style='width:79.85pt;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>${product.productName}</span></p>
  </td>
  <td width=128 style='width:95.65pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:12.0pt;font-family:宋体'>${product.productType}</span></p>
  </td>
  <td width=85  style='width:63.8pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:12.0pt;font-family:宋体'>${fns:getDictLabel(product.productCompany, 'product_company', '')}</span></p>
  </td>
  <td width=107  style='width:79.9pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:12.0pt;font-family:宋体'>${product.productAmount}</span></p>
  </td>
    <td width=107  style='width:79.9pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:12.0pt;font-family:宋体'>${product.unitPrice}</span></p>
  </td>
  <td width=106  style='width:79.8pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:12.0pt;font-family:宋体'>${product.totalPrice}</span></p>
  </td>
			</tr>
		</c:forEach>




 <tr height="40px">

				<td colspan=6 valign="middle" style='width:79.85pt;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal   ><span
  style='font-size:12.0pt;font-family:宋体'>合计金额（小写）：￥${swAgreement.amount}元</span></p>
  </td>
  </tr>
  <tr  height="40px">

				<td colspan=6 valign="middle"  style='width:79.85pt;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal ><span
  style='font-size:12.0pt;font-family:宋体'>合计金额（大写）：人民币${swAgreement.amountup}</span></p>
  </td>
  </tr>
 
</table>

<p class=MsoNormal style='line-height:200%'><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>二、质量要求、技术标准、供方对质量负责的条件和质保期：</span></p>

<p class=MsoNormal style='text-indent:24.0pt;line-height:200%'><span
lang=EN-US style='font-size:12.0pt;line-height:200%;font-family:宋体'>1</span><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>．按行业质量标准，产品满足需方使用要求。</span></p>

<p class=MsoNormal style='text-indent:24.0pt;line-height:200%'><span
lang=EN-US style='font-size:12.0pt;line-height:200%;font-family:宋体'>2</span><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>．产品出现质量问题，供方在收到需方通知的</span><u><span
lang=EN-US style='font-size:12.0pt;line-height:200%;font-family:宋体'><span
style='font-size:10.5pt;line-height:200%;font-family:"Calibri",sans-serif'> </span><span
style='font-family:"Calibri",sans-serif'>${swAgreement.solveDay}</span><span style='font-size:10.5pt;
line-height:200%;font-family:"Calibri",sans-serif'> </span></span></u><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>个工作日内解决完毕。</span></p>

<p class=MsoNormal style='text-indent:24.0pt;line-height:200%'><span
lang=EN-US style='font-size:12.0pt;line-height:200%;font-family:宋体'>3</span><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>．产品质保期为交验合格之日起<u><span
lang=EN-US><span style='font-size:10.5pt;line-height:200%;font-family:"Calibri",sans-serif'>
</span><span style='font-family:"Calibri",sans-serif'>${swAgreement.warranty}</span><span
style='font-size:10.5pt;line-height:200%;font-family:"Calibri",sans-serif'> </span></span></u>年。</span></p>

<p class=MsoNormal style='line-height:200%'><span style='font-size:12.0pt;
line-height:200%;font-family:宋体'>三、产品交货地点、交货时间及交货方式：</span></p>

<p class=MsoNormal style='text-indent:27.0pt;line-height:200%'><span
lang=EN-US style='font-size:12.0pt;line-height:200%;font-family:宋体'>1</span><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>．交货地点：<span
lang=EN-US><span lang=EN-US><span lang=EN-US>北京市海淀区丰滢东路1</span></span><span
lang=EN-US><span lang=EN-US>号</span></span></span>。</span></p>

<p class=MsoNormal style='text-indent:27.0pt;line-height:200%'><span
lang=EN-US style='font-size:12.0pt;line-height:200%;font-family:宋体'>2</span><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>．交货时间：合同签订后<span
lang=EN-US><u> ${swAgreement.deliveryTime} </u></span>天内。</span></p>

<p class=MsoNormal style='text-indent:27.0pt;line-height:200%'><span
lang=EN-US style='font-size:12.0pt;line-height:200%;font-family:宋体'>3</span><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>．交货方式：供方将产品运送至需方，费用由<u><span
lang=EN-US> <span lang=EN-US><span lang=EN-US>${swAgreement.deliveryMethod}</span></span><span lang=EN-US><span
lang=EN-US> </span></span></span></u>承担。</span></p>

<p class=MsoNormal style='line-height:200%'><span style='font-size:12.0pt;
line-height:200%;font-family:宋体'>四、包装标准、费用及回收：</span></p>

<p class=MsoNormal style='line-height:200%'><span
lang=EN-US style='font-size:12.0pt;line-height:200%;font-family:宋体'>
</span><span style='font-size:12.0pt;line-height:200%;font-family:宋体'>厂家原包装。包装费用应由厂家承担，且包装不予回收。</span></p>

<p class=MsoNormal style='line-height:200%'><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>五、验收地点、标准、方法及提出异议期限：</span></p>

<p class=MsoNormal style='text-indent:27.0pt;line-height:200%'><span
lang=EN-US style='font-size:12.0pt;line-height:200%;font-family:宋体'>1</span><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>．甲方在验收中，如果发现产品的牌号、规格、状态和质量不合规定，应一方面妥为保管，另一方面在<span
lang=EN-US><u>${swAgreement.objectionDay}</u></span>天内向乙方提出书面异议；在托收承付期内，甲方有权拒付不符合合同规定部分的货款。甲方怠于通知或者自标的物收到之日起过两年内未通知乙方的，视为产品合乎规定。</span></p>

<p class=MsoNormal style='text-indent:27.0pt;line-height:200%'><span
lang=EN-US style='font-size:12.0pt;line-height:200%;font-family:宋体'>2</span><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>．乙方在接到需方书面异议后，应在<span
lang=EN-US><u>${swAgreement.objectionDayHf}</u></span>天内（另有规定或当事人另行商定期限者除外）负责处理，否则，即视为默认甲方提出的异议和处理意见。</span></p>

<p class=MsoNormal style='line-height:200%'><span style='font-size:12.0pt;
line-height:200%;font-family:宋体'>六、结算方式及期限：</span></p>


 <p class=MsoNormal style='text-indent:27.0pt;line-height:200%'>
<span style='font-size:12.0pt;line-height:200%;font-family:宋体'>${swAgreement.field9}</span></p>




<p class=MsoNormal style='text-indent:27.0pt;line-height:200%'><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>供方收到合同款后<span
lang=EN-US>${swAgreement.invoiceDay}</span>日内向需方交付正式发票。</span></p>

<p class=MsoNormal style='line-height:200%'><span style='font-size:12.0pt;
line-height:200%;font-family:宋体'>七、供方的违约责任</span></p>

<p class=MsoNormal style='text-indent:27.0pt;line-height:200%'><span
lang=EN-US style='font-size:12.0pt;line-height:200%;font-family:宋体'>1</span><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>．供方不能按期交货的，应向需方偿付不能交货部分货款的<u><span
lang=EN-US><span style='font-size:12.0pt;line-height:200%;font-family:宋体'><u>${swAgreement.falsify}</u></span></span></u>的违约金。</span></p>

<p class=MsoNormal style='text-indent:27.0pt;line-height:200%'><span
lang=EN-US style='font-size:12.0pt;line-height:200%;font-family:宋体'>2</span><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>．供方所交产品牌号、规格、状态和质量不符合规定的，如果需方同意利用，应当按质论价；如果需方不能利用的，应根据产品的具体情况，由共方负责包换或包修，并承担修理、调换或退货而支付的实际费用。</span></p>

<p class=MsoNormal style='text-indent:27.0pt;line-height:200%'><span
lang=EN-US style='font-size:12.0pt;line-height:200%;font-family:宋体'>3</span><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>．供方因产品包装不符合合同规定，必须返修或重新包装的，供方应负责返修或重新包装，并承担支付的费用。需方不要求返修或重新包装而要求赔偿损失的，供方应当偿付需方该不合格包装物低于合格包装物的价值部分。因包装不符合规定造成货物损坏或灭失的，供方应当负责赔偿。</span></p>

<p class=MsoNormal style='text-indent:27.0pt;line-height:200%'><span
lang=EN-US style='font-size:12.0pt;line-height:200%;font-family:宋体'>4</span><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>．供方逾期交货的，应比照中国人民银行有关延期付款的规定，按逾期交货部分货款计算，向需方偿付逾期交货的违约金，并承担需方因此所受的损失费用。</span></p>

<p class=MsoNormal style='text-indent:27.0pt;line-height:200%'><span
lang=EN-US style='font-size:12.0pt;line-height:200%;font-family:宋体'>5</span><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>．产品错发到货地点或接货人的，供方除应负责运交合同规定的到货地点或接货人外，还应承担需方因此多支付的一切实际费用和逾期交货的违约金。</span></p>

<p class=MsoNormal style='text-indent:27.0pt;line-height:200%'><span
lang=EN-US style='font-size:12.0pt;line-height:200%;font-family:宋体'>6</span><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>．供方逾期交货的，应在发货前与需方协商，需方仍需要的，供方应照数补交，并负逾期交货责任；需方不再需要的，应当在<span
lang=EN-US><u>15</u></span>天内通知供方，办理解除合同手续。逾期不答复的，视为同意发货。</span></p>

<p class=MsoNormal style='line-height:200%'><span style='font-size:12.0pt;
line-height:200%;font-family:宋体'>八、需方的违约责任</span></p>

<p class=MsoNormal style='text-indent:27.0pt;line-height:200%'><span
lang=EN-US style='font-size:12.0pt;line-height:200%;font-family:宋体'>1</span><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>．非因供方原因中途退货，需方应向供方偿付退货部分货款<span
lang=EN-US><span style='font-family:"Calibri",sans-serif'><u>10</u></span></span>％的违约金。</span></p>

<p class=MsoNormal style='text-indent:27.0pt;line-height:200%'><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>（违约金视为违约的损失赔偿，但约定的违约金过分高于或者低于造成的损失的，当事人可以请求人民法院予以适当减少或者增加）</span></p>

<p class=MsoNormal style='text-indent:27.0pt;line-height:200%'><span
lang=EN-US style='font-size:12.0pt;line-height:200%;font-family:宋体'>2</span><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>．需方逾期付款的，应按中国人民银行有关延期付款的规定向供方偿付逾期付款的违约金。</span></p>

<p class=MsoNormal style='line-height:200%'><span style='font-size:12.0pt;
line-height:200%;font-family:宋体'>九、不可抗力条款：</span></p>

<p class=MsoNormal style='text-indent:24.0pt;line-height:200%'><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>本合同所称不可抗力是指不能预见、不能克服、不能避免并对一方当事人造成重大影响的客观事件，包括但不限于自然灾害如洪水、地震、火灾和风暴等以及社会事件如战争、动乱、政府行为等。如因不可抗力事件的发生导致合同无法履行时，遇不可抗力的一方应立即将事故情况书面告知另一方，并应在一天内，提供事故详情及合同不能履行或者需要延期履行的书面资料，双方认可后协商终止合同或暂时延迟合同的履行。</span></p>

<p class=MsoNormal style='line-height:200%'><span style='font-size:12.0pt;
line-height:200%;font-family:宋体'>十、合同争议的解决方式：</span></p>

<p class=MsoNormal style='text-indent:24.0pt;line-height:200%'><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>本合同在履行过程中发生的争议，由双方当事人协商解决；协商不成的，依法向需方所在地人民法院起诉。</span></p>

<p class=MsoNormal style='line-height:200%'><span style='font-size:12.0pt;
line-height:200%;font-family:宋体'>十一、其它约定：</span></p>

<p class=MsoNormal style='text-indent:24.0pt;line-height:200%'><span
style='font-size:12.0pt;line-height:200%;font-family:宋体'>本合同自签订之日起生效，合同执行期内，甲乙双方均不得随意变更或解除合同。合同如有未尽事宜，须经双方共同协商，做出补充规定，补充规定与合同具有同等效力。本合同正本一式<u>四</u>份，甲乙双方各执<u>二</u>份。</span></p>

<table class=MsoNormalTable border=1 cellspacing=0 cellpadding=0 width="100%"
 style='width:100.0%;border-collapse:collapse;border:none'>
 <tr>
  <td width="50%" style='width:50.46%;border:solid windowtext 1.0pt;padding:
  0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal style='margin-left:84.0pt;text-indent:-84.0pt'><span
  style='font-size:12.0pt;font-family:宋体'>需方单位名称：北京航天时代激光导航技术有限责任公司 </span></p>
  </td>
  <td width="49%" style='width:49.54%;border:solid windowtext 1.0pt;border-left:
  none;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal style='margin-left:81.0pt;text-indent:-81.0pt'><span
  style='font-size:12.0pt;font-family:宋体'>供方单位名称：<span lang=EN-US><span
  lang=EN-US><span lang=EN-US>${swAgreement.secondName}</span></span></span></span></p>
  </td>
 </tr>
 <tr style='height:28.85pt'>
  <td width="50%" style='width:50.46%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:28.85pt'>
  <p class=MsoNormal><span style='font-size:12.0pt;font-family:宋体'>单位地址：北京市海淀区丰滢东路<span
  lang=EN-US>1</span>号</span></p>
  </td>
  <td width="49%" style='width:49.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:28.85pt'>
  <p class=MsoNormal><span style='font-size:12.0pt;font-family:宋体'>单位地址：${swAgreement.secondPlace}</span></p>
  </td>
 </tr>
 <tr style='height:31.3pt'>
  <td width="50%" style='width:50.46%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:31.3pt'>
  <p class=MsoNormal><span style='font-size:12.0pt;font-family:宋体'>法定代表人：${swSupplier.legal}</span></p>
  </td>
  <td width="49%" style='width:49.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:31.3pt'>
  <p class=MsoNormal><span style='font-size:12.0pt;font-family:宋体'>法定代理人：${swAgreement.secondLegal}</span></p>
  </td>
 </tr>
 <tr style='height:28.5pt'>
  <td width="50%" style='width:50.46%;border-top:none;border-left:solid windowtext 1.0pt;
  border-bottom:none;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:28.5pt'>
  <p class=MsoNormal><span style='font-size:12.0pt;font-family:宋体'>委托代理人：</span></p>
  </td>
  <td width="49%" style='width:49.54%;border:none;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:28.5pt'>
  <p class=MsoNormal><span style='font-size:12.0pt;font-family:宋体'>委托代理人：</span></p>
  </td>
 </tr>
 <tr style='height:29.9pt'>
  <td width="50%" style='width:50.46%;border:solid windowtext 1.0pt;padding:
  0cm 5.4pt 0cm 5.4pt;height:29.9pt'>
  <p class=MsoNormal><span style='font-size:12.0pt;font-family:宋体'>联系人：&nbsp;&nbsp;&nbsp;&nbsp; 联系电话： ${swSupplier.userPhone} </span></p>
  </td>
  <td width="49%" style='width:49.54%;border:solid windowtext 1.0pt;border-left:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:29.9pt'>
  <p class=MsoNormal><span style='font-size:12.0pt;font-family:宋体'>联系人：&nbsp;&nbsp;&nbsp;&nbsp;联系电话：${swAgreement.secondNamePhone}</span></p>
  </td>
 </tr>
 <tr style='height:27.5pt'>
  <td width="50%" style='width:50.46%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:27.5pt'>
  <p class=MsoNormal><span style='font-size:12.0pt;font-family:宋体'>开户银行：${swSupplier.bankName}</span></p>
  </td>
  <td width="49%" style='width:49.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:27.5pt'>
  <p class=MsoNormal><span style='font-size:12.0pt;font-family:宋体'>开户银行：${swAgreement.secondBank}</span></p>
  </td>
 </tr>
 <tr style='height:27.9pt'>
  <td width="50%" style='width:50.46%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:27.9pt'>
  <p class=MsoNormal><span style='font-size:12.0pt;font-family:宋体'>帐号：${swSupplier.bankNumber} </span></p>
  </td>
  <td width="49%" style='width:49.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:27.9pt'>
  <p class=MsoNormal><span style='font-size:12.0pt;font-family:宋体'>帐号：${swAgreement.secondBankNo}</span></p>
  </td>
 </tr>
 <tr style='height:28.3pt'>
  <td width="50%" style='width:50.46%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:28.3pt'>
  <p class=MsoNormal><span style='font-size:12.0pt;font-family:宋体'>税号： ${swSupplier.dutyno} </span></p>
  </td>
  <td width="49%" style='width:49.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:28.3pt'>
  <p class=MsoNormal><span style='font-size:12.0pt;font-family:宋体'>税号：${swAgreement.secondDuty}</span></p>
  </td>
 </tr>
 <tr style='height:34.4pt'>
  <td width="50%" style='width:50.46%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:34.4pt'>
  <p class=MsoNormal><span style='font-size:12.0pt;font-family:宋体'>邮政编码：${swSupplier.supplierZip}</span></p>
  </td>
  <td width="49%" style='width:49.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:34.4pt'>
  <p class=MsoNormal><span style='font-size:12.0pt;font-family:宋体'>邮政编码：${swAgreement.secondZip}</span></p>
  </td>
 </tr>
 <tr style='height:27.85pt'>
  <td width="50%" style='width:50.46%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:27.85pt'>
  <p class=MsoNormal><span style='font-size:12.0pt;font-family:宋体'>电话：${swSupplier.supplierTel} &nbsp;传真：${swSupplier.supplierFax}</span></p>
  </td>
  <td width="49%" style='width:49.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:27.85pt'>
  <p class=MsoNormal><span style='font-size:12.0pt;font-family:宋体'>电话：${swAgreement.secondTel}&nbsp;
  传真： ${swAgreement.secondFax}</span></p>
  </td>
 </tr>
</table>

<p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>

</div>





			
		<div class="form-actions">
			<input id="btnSubmit" class="btn" type="button"  onclick="dayin()" value="打 印"/>
			<input id="btnSubmit" class="btn btn-primary" type="button"  onclick="word()" value="导出word"/>
			<input id="btnCancel" class="btn" type="button"  value="关 闭" onclick="window.close();"/>
		</div>

</body>
</html>