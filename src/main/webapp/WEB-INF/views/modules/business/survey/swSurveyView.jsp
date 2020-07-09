<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>调研报告打印(公开)</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			if('${amountYs}'<200000){
				document.getElementById("field3TR").style.display="";
			}
		});
		</script>
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
	{font-family:宋体;
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
	{font-family:"\@宋体";
	panose-1:2 1 6 0 3 1 1 1 1 1;}
@font-face
	{font-family:"\@楷体_GB2312";}
 /* Style Definitions */
 p.MsoNormal, li.MsoNormal, div.MsoNormal
	{margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
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
			
		
		});
	
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
	
		function dayin(){
			 $("#dayin").jqprint();
		}
	</script>
	<style>
<!--
 /* Font Definitions */
 @font-face
	{font-family:宋体;
	panose-1:2 1 6 0 3 1 1 1 1 1;}
@font-face
	{font-family:宋体;
	panose-1:2 1 6 9 6 1 1 1 1 1;}
@font-face
	{font-family:"Cambria Math";
	panose-1:2 4 5 3 5 4 6 3 2 4;}
@font-face
	{font-family:仿宋_GB2312;
	panose-1:2 1 6 9 3 1 1 1 1 1;}
@font-face
	{font-family:"\@宋体";
	panose-1:2 1 6 0 3 1 1 1 1 1;}
@font-face
	{font-family:"\@仿宋_GB2312";}
@font-face
	{font-family:"\@宋体";
	panose-1:2 1 6 0 3 1 1 1 1 1;}
 /* Style Definitions */
 p.MsoNormal, li.MsoNormal, div.MsoNormal
	{margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	font-size:10.5pt;
	font-family:"Times New Roman",serif;}
p.1, li.1, div.1
	{mso-style-name:正文_标题1;
	mso-style-link:"正文_标题1 Char";
	margin-top:0cm;
	margin-right:0cm;
	margin-bottom:12.0pt;
	margin-left:0cm;
	text-align:center;
	line-height:150%;
	page-break-after:avoid;
	font-size:15.0pt;
	font-family:宋体;}
p.2, li.2, div.2
	{mso-style-name:正文_标题2;
	mso-style-link:"正文_标题2 Char";
	margin-top:0cm;
	margin-right:0cm;
	margin-bottom:0cm;
	margin-left:45.1pt;
	margin-bottom:.0001pt;
	text-indent:-21.0pt;
	line-height:150%;
	page-break-after:avoid;
	font-size:16.0pt;
	font-family:仿宋_GB2312;
	font-weight:bold;}
span.1Char
	{mso-style-name:"正文_标题1 Char";
	mso-style-link:正文_标题1;
	font-family:宋体;}
span.2Char
	{mso-style-name:"正文_标题2 Char";
	mso-style-link:正文_标题2;
	font-family:仿宋_GB2312;
	font-weight:bold;}
p.a, li.a, div.a
	{mso-style-name:域_正文_调研报告;
	mso-style-link:"域_正文_调研报告 Char";
	margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	text-indent:10.0pt;
	line-height:150%;
	font-size:14.0pt;
	font-family:仿宋;
	color:#632423;}
span.a0
	{mso-style-name:域_表格;
	font-family:仿宋;
	color:#632423;}
span.Char
	{mso-style-name:"域_正文_调研报告 Char";
	mso-style-link:域_正文_调研报告;
	font-family:仿宋;
	color:#632423;}
p.a1, li.a1, div.a1
	{mso-style-name:正文_签字处;
	mso-style-link:"正文_签字处 Char";
	margin-top:0cm;
	margin-right:0cm;
	margin-bottom:0cm;
	margin-left:375.6pt;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	line-height:200%;
	font-size:14.0pt;
	font-family:仿宋;}
span.Char0
	{mso-style-name:"正文_签字处 Char";
	mso-style-link:正文_签字处;
	font-family:仿宋;}
.MsoChpDefault
	{font-family:等线;}
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
	
</head>

<body lang=ZH-CN style='text-justify-trim:punctuation'>

<div id="dayin" class=WordSection1 style='margin-left: 50px;width:960px;margin-top: 40px'>

<table style="width: 100%"><tr>
<td style="text-align:left">
<p style='text-indent:28.0pt;line-height:150%;font-family: 宋体, SimHei;font-size:12.0pt'>航天采购平台询价编号:${swSurvey.field3}</p><td>
<td style="text-align:left;"><p style='text-indent:28.0pt;line-height:150%;font-family: 宋体, SimHei;font-size:12.0pt'>公司统一编号:${swSurvey.field4}</p>
</tr></table>



<p class=MsoNormal align=center style='text-align:center;line-height:200%;margin-top:26px;'><b><span
style="font-size: 28px;margin-bottom: 10px;line-height:150%">北京航天时代激光导航技术有限责任公司</br>关于${swSurvey.field1}的调研报告</span></b></p>

<p class=2 style='margin-top:15.6pt'><span lang=EN-US><span style='font-size:17.0pt "Times New Roman"'>&nbsp;
</span></span><span style='font-size:14.0pt;' lang=X-NONE>一、调研概况</span></p>

<p style='text-indent:28.0pt;line-height:150%;font-family: 宋体, SimHei;font-size:12.0pt'>${swSurvey.mainUses}</p>
<p style='text-indent:28.0pt;line-height:150%;font-family: 宋体, SimHei;font-size:12.0pt'>${swSurvey.situation}</p>

<p style='margin-left:28.0pt;line-height:200%;font-family: 宋体, SimHei;white-space:pre;font-size:12.0pt'>${swSurvey.technical}</p>
<p style='text-indent:28.0pt;line-height:150%;font-family: 宋体, SimHei;font-size:12.0pt'>${swSurvey.budget}</p>
<p id="field3TR" style='text-indent:28.0pt;line-height:150%;font-family: 宋体, SimHei;font-size:12.0pt;display: none'>询价场次号：${swSurvey.field3}</p>



<p class=2 style='margin-top:15.6pt'><span lang=EN-US><span style='font:7.0pt "Times New Roman"'>&nbsp;
</span></span><span style='font-size:14.0pt;' lang=X-NONE>二、供应商简介<span style='font:7.0pt "Times New Roman"'>&nbsp;
</span></span><span lang=X-NONE style='color:white'></span></p>
<p style='text-indent:28.0pt;line-height:150%;font-family: 宋体, SimHei;font-size:12.0pt'>${swSurvey.supplier}</p>
<table border='1' bordercolor='#000000' style='border-collapse:collapse;width: 960px;margin-left: 20px;margin-right:56px;margin-top:10px;'>
 <tr height="40px ">
  <td style="font-family: 宋体, SimHei; text-align:center;" >
 序号
  </td>
  <td style="font-family: 宋体, SimHei; text-align:center;"  >
单位名称
  </td>
  <td  style="font-family: 宋体, SimHei; text-align:center;" >
报价(万)
  </td>
   <td  style="font-family: 宋体, SimHei; text-align:center;" >
供货周期(周)
  </td>
   <td  style="font-family: 宋体, SimHei; text-align:center;" >
售后服务情况
  </td>
  <td style="font-family: 宋体, SimHei; text-align:center;" >
  联系人
  </td>
  <td style="font-family: 宋体, SimHei; text-align:center;" >
电话
  </td>
  <td style="font-family: 宋体, SimHei; text-align:center;" >
是否在供应商名录(必要时)
  </td>
 </tr>
 
 <c:forEach items="${swSurvey.swSurveyCompanyList}" var="company"  varStatus="vs">
	
 
 
 <tr style='height:40px'>
  <td style="font-family: 宋体, SimHei; text-align:center;">
 ${vs.index+1}
  </td>

   <td style="font-family: 宋体, SimHei; text-align:center;">
  ${company.name}
  </td>
  <td style="font-family: 宋体, SimHei; text-align:center;">
  ${company.price}
  </td>
  <td style="font-family: 宋体, SimHei; text-align:center;">
  ${company.field2}
  </td>
  <td style="font-family: 宋体, SimHei; text-align:center;">
  ${fns:getDictLabel(company.field3, 'survey_class', '')}
  </td>
<!--  <td style="font-family: 宋体, SimHei; text-align:center;">
  ${fns:getDictLabel(company.mode, 'survey_model', '')}
  </td>-->
  <td style="font-family: 宋体, SimHei; text-align:center;" >
 ${company.contacts}
  </td>
  <td style="font-family: 宋体, SimHei; text-align:center;" >
 ${company.telephone}
  </td>
  <td style="font-family: 宋体, SimHei; text-align:center;">
${fns:getDictLabel(company.mode, 'is_leaf', '')}
  </td>
 </tr>

</c:forEach>
</table>

<p class=2 style='margin-top:15.6pt'><span style='font-size:14.0pt;' lang=EN-US>三、评定结论<span style='font:7.0pt "Times New Roman"'>&nbsp;
</span></span><span lang=X-NONE style='color:white'></span></p>


<p style='text-indent:28.0pt;line-height:150%;font-family: 宋体, SimHei;font-size:12.0pt'>${swSurvey.result}</p>


<p class=MsoNormal style='text-indent:28.0pt'><span class=Char><span
lang=EN-US style='font-size:14.0pt'>&nbsp;</span></span></p>

<p class=MsoNormal style='text-indent:28.0pt'><span class=Char><span
lang=EN-US style='font-size:14.0pt'>&nbsp;</span></span></p>

<p class=MsoNormal style='text-indent:21.0pt'><span lang=EN-US>&nbsp;</span></p>
<table style="width: 90%">
    <tr height="50px"><td width="60%"></td><td align="right" width="20%" style="font-family: 宋体, SimHei; text-align:center;font-size:12.0pt">调研人员：</td> <td width="20%"></td></tr>
    <tr height="50px"><td width="60%"></td><td  align="right" width="20%" style="font-family: 宋体, SimHei; text-align:center;font-size:12.0pt">调研部门领导：</td> <td width="20%"></td></tr>
    <tr height="50px"><td width="60%"></td><td align="right"  width="20%" style="font-family: 宋体, SimHei; text-align:center;font-size:12.0pt">招标办：</td> <td width="20%"></td></tr>
    <tr height="50px"><td width="60%"></td><td align="right"  width="20%" style="font-family: 宋体, SimHei; text-align:center;font-size:12.0pt">主管公司领导：</td> <td width="20%"></td></tr>

 </table>


</div>







			
		<div class="form-actions">
			<input id="btnSubmit" class="btn" type="button"  onclick="dayin()" value="打 印"/>
			<input id="btnSubmit" class="btn btn-primary" type="button"  onclick="word()" value="导出word"/>
			<input id="btnCancel" class="btn" type="button" value="关 闭" onclick="window.close();"/>
		</div>

</body>
</html>