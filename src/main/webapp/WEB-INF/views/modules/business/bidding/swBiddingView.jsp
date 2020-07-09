<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>内部招投标(公开)</title>
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
	{font-family:仿宋_GB2312;
	panose-1:2 1 6 9 3 1 1 1 1 1;}
@font-face
	{font-family:Cambria;
	panose-1:2 4 5 3 5 4 6 3 2 4;}
@font-face
	{font-family:楷体_GB2312;}
@font-face
	{font-family:"\@仿宋_GB2312";}
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
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	font-size:10.5pt;
	font-family:"Times New Roman",serif;
	color:windowtext;}
h1
	{mso-style-name:"标题 1\,H1\,第一章 标题1\,36标题 1\,36标题1\,章节\,第一层\,123321\,h1\,PIM 1\,1\.\,tzy1\,tzy11\,tzy12\,tzy111\,tzy13\,tzy112\,H11\,H12\,H111\,H13\,H112\,章名\,标题 11\,Heading 0\,Section Head\,Header1\,1st level\,l1\,Fab-1\,Heading 01\,Heading 02\,Heading 03\,Heading 04\,Heading 011\,Heading 021\,章\,I1\,T1\,论文题目";
	mso-style-link:"标题 1 字符\,H1 字符\,第一章 标题1 字符\,36标题 1 字符\,36标题1 字符\,章节 字符\,第一层 字符\,123321 字符\,h1 字符\,PIM 1 字符\,1\. 字符\,tzy1 字符\,tzy11 字符\,tzy12 字符\,tzy111 字符\,tzy13 字符\,tzy112 字符\,H11 字符\,H12 字符\,H111 字符\,H13 字符\,H112 字符\,章名 字符\,标题 11 字符\,Heading 0 字符\,Section Head 字符\,Header1 字符\,1st level 字符\,l1 字符";
	margin-top:12.0pt;
	margin-right:0cm;
	margin-bottom:12.0pt;
	margin-left:184.7pt;
	text-align:justify;
	text-justify:inter-ideograph;
	text-indent:-21.6pt;
	page-break-after:avoid;
	layout-grid-mode:char;
	font-size:16.0pt;
	font-family:"Times New Roman",serif;
	color:windowtext;
	font-weight:bold;}
h2
	{mso-style-name:"标题 2\,第二层\,H2\,h2\,TestHeading2\,th2\,36标题 2\,36标题2\,第一层条\,GF标题 2\,Heading 2 Hidden\,Heading 2 CCBS\,PIM2\,TOC1\,heading 2\,第一章 标题 2\,Titre3\,HD2\,sect 1\.2\,H21\,sect 1\.21\,H22\,sect 1\.22\,H211\,sect 1\.211\,H23\,sect 1\.23\,H212\,sect 1\.212\,DO\,标题 2，H2\,Heading 2\,Underrubri\,2";
	mso-style-link:"标题 2 字符\,第二层 字符\,H2 字符\,h2 字符\,TestHeading2 字符\,th2 字符\,36标题 2 字符\,36标题2 字符\,第一层条 字符\,GF标题 2 字符\,Heading 2 Hidden 字符\,Heading 2 CCBS 字符\,PIM2 字符\,TOC1 字符\,heading 2 字符\,第一章 标题 2 字符\,Titre3 字符\,HD2 字符\,sect 1\.2 字符\,H21 字符\,sect 1\.21 字符\,H22 字符\,sect 1\.22 字符\,H211 字符\,H23 字符";
	margin-top:0cm;
	margin-right:0cm;
	margin-bottom:0cm;
	margin-left:182.9pt;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	text-indent:-34.0pt;
	page-break-after:avoid;
	layout-grid-mode:char;
	font-size:15.0pt;
	font-family:"Times New Roman",serif;
	color:windowtext;
	font-weight:bold;}
h3
	{mso-style-name:"标题 3\,36标题 3\,四号字\,36标题3\,第二层条\,h3\,Level 3 Head\,H3\,Heading 3 - old\,level_3\,PIM 3\,第三层\,论文标题 2\,款\,sect1\.2\.3\,sect1\.2\.31\,sect1\.2\.32\,sect1\.2\.311\,sect1\.2\.33\,sect1\.2\.312\,heading 3TOC\,3rd level\,1\.1\.1 Heading 3\,BOD 0\,l3\,CT\,Bold Head\,bh\,标题3\,1\.2\.3\.\,Section\,Ma\,分\,3\,Map";
	mso-style-link:"标题 3 字符\,36标题 3 字符\,四号字 字符\,36标题3 字符\,第二层条 字符\,h3 字符\,Level 3 Head 字符\,H3 字符\,Heading 3 - old 字符\,level_3 字符\,PIM 3 字符\,第三层 字符\,论文标题 2 字符\,款 字符\,sect1\.2\.3 字符\,sect1\.2\.31 字符\,sect1\.2\.32 字符\,sect1\.2\.311 字符\,sect1\.2\.33 字符\,sect1\.2\.312 字符\,heading 3TOC 字符\,3rd level 字符\,BOD 0 字符";
	margin-top:0cm;
	margin-right:0cm;
	margin-bottom:0cm;
	margin-left:184.9pt;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	text-indent:-36.0pt;
	page-break-after:avoid;
	layout-grid-mode:char;
	font-size:14.0pt;
	font-family:"Times New Roman",serif;
	color:windowtext;
	font-weight:bold;}
h4
	{mso-style-name:"标题 4\,项\,第三层条\,H4\,h4\,PIM 4\,第四层\,条3\,Ref Heading 1\,rh1\,Heading sql\,sect 1\.2\.3\.4\,H4 Char\,Fab-4 Char\,T5 Char\,PIM 4 Char\,h4 Char\,Ref Heading 1 Char\,rh1 Char\,Heading sql Char\,sect 1\.2\.3\.4 Char\,H41 Char\,H42 Char\,H43 Char\,H44 Char\,H45 Char\,H46 Char\,H47 Ch";
	mso-style-link:"标题 4 字符\,项 字符\,第三层条 字符\,H4 字符\,h4 字符\,PIM 4 字符\,第四层 字符\,条3 字符\,Ref Heading 1 字符\,rh1 字符\,Heading sql 字符\,sect 1\.2\.3\.4 字符\,H4 Char 字符\,Fab-4 Char 字符\,T5 Char 字符\,PIM 4 Char 字符\,h4 Char 字符\,Ref Heading 1 Char 字符\,rh1 Char 字符\,Heading sql Char 字符\,sect 1\.2\.3\.4 Char 字符";
	margin-top:0cm;
	margin-right:0cm;
	margin-bottom:0cm;
	margin-left:192.1pt;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	text-indent:-43.2pt;
	page-break-after:avoid;
	layout-grid-mode:char;
	font-size:14.0pt;
	font-family:"Times New Roman",serif;
	color:windowtext;
	font-weight:bold;}
h5
	{mso-style-name:"标题 5\,第四层条\,第五层\,H5\,h5\,Second Subheading\,dash\,ds\,dd\,5\,l4\,PIM 5\,Titre5\,Table label\,l5\,hm\,mh2\,Module heading 2\,Head 5\,list 5\,Block Label\,Appendix A  Heading 5\,Roman list\,注入站标题5\,口\,dash1\,ds1\,dd1\,dash2\,ds2\,dd2\,dash3\,ds3\,dd3\,dash4\,ds4\,dd4\,dash5\,ds5\,dd5";
	mso-style-link:"标题 5 字符\,第四层条 字符\,第五层 字符\,H5 字符\,h5 字符\,Second Subheading 字符\,dash 字符\,ds 字符\,dd 字符\,5 字符\,l4 字符\,PIM 5 字符\,Titre5 字符\,Table label 字符\,l5 字符\,hm 字符\,mh2 字符\,Module heading 2 字符\,Head 5 字符\,list 5 字符\,Block Label 字符\,Appendix A  Heading 5 字符\,Roman list 字符\,注入站标题5 字符\,口 字符";
	margin-top:6.0pt;
	margin-right:0cm;
	margin-bottom:6.0pt;
	margin-left:199.3pt;
	text-align:justify;
	text-justify:inter-ideograph;
	text-indent:-50.4pt;
	page-break-after:avoid;
	layout-grid-mode:char;
	font-size:14.0pt;
	font-family:"Times New Roman",serif;
	color:windowtext;
	font-weight:bold;}
h6
	{mso-style-name:"标题 6\,h6\,Third Subheading\,L6\,H6\,BOD 4\,第五层条\,PIM 6\,6\,Bullet list\,注入站标题6\,Bullet \(Single Lines\)\,ITT t6\,PA Appendix\,T6\,61\,62\,heading 6\,Bullet list1\,Bullet list2\,Bullet list11\,Bullet list3\,Bullet list12\,Bullet list21\,Bullet list111\,Bullet lis\,l6\,hsm\,正文六级标题\,sd";
	mso-style-link:"标题 6 字符\,h6 字符\,Third Subheading 字符\,L6 字符\,H6 字符\,BOD 4 字符\,第五层条 字符\,PIM 6 字符\,6 字符\,Bullet list 字符\,注入站标题6 字符\,Bullet \(Single Lines\) 字符\,ITT t6 字符\,PA Appendix 字符\,T6 字符\,61 字符\,62 字符\,heading 6 字符\,Bullet list1 字符\,Bullet list2 字符\,Bullet list11 字符\,Bullet list3 字符\,l6 字符";
	margin-top:12.0pt;
	margin-right:0cm;
	margin-bottom:3.2pt;
	margin-left:206.5pt;
	text-align:justify;
	text-justify:inter-ideograph;
	text-indent:-57.6pt;
	line-height:133%;
	page-break-after:avoid;
	font-size:12.0pt;
	font-family:"Cambria",serif;
	color:windowtext;
	font-weight:bold;}
p.MsoHeading7, li.MsoHeading7, div.MsoHeading7
	{mso-style-name:"标题 7\,第六层条\,图表标题\,PIM 7\,（1）\,L7\,ITT t7\,PA Appendix Major\,T7\,\(use for appendix\)\,letter list\,H TIMES1\,Legal Level 1\.1\.\,不用\,Level 1\.1\,H7\,sdf\,1\.1\.1\.1\.1\.1\.1标题 7\,appendix\,---1\)\,1\.标题 6\,正文七级标题\,h7\,st\,SDL title\,h71\,st1\,SDL title1\,h72\,st2\,SDL title2\,h73\,st3\,h";
	mso-style-link:"标题 7 字符\,第六层条 字符\,图表标题 字符\,PIM 7 字符\,（1） 字符\,L7 字符\,ITT t7 字符\,PA Appendix Major 字符\,T7 字符\,\(use for appendix\) 字符\,letter list 字符\,H TIMES1 字符\,Legal Level 1\.1\. 字符\,不用 字符\,Level 1\.1 字符\,H7 字符\,sdf 字符\,1\.1\.1\.1\.1\.1\.1标题 7 字符\,appendix 字符\,---1\) 字符\,1\.标题 6 字符\,正文七级标题 字符\,h7 字符";
	margin-top:12.0pt;
	margin-right:0cm;
	margin-bottom:3.2pt;
	margin-left:213.7pt;
	text-align:justify;
	text-justify:inter-ideograph;
	text-indent:-64.8pt;
	line-height:133%;
	page-break-after:avoid;
	font-size:12.0pt;
	font-family:"Times New Roman",serif;
	color:windowtext;
	font-weight:bold;}
p.MsoHeading8, li.MsoHeading8, div.MsoHeading8
	{mso-style-name:"标题 8\,App2\,（A）\,注意框体\,ITT t8\,PA Appendix Minor\,T8\,\(use for figures\)\,\(figure\)\,标题6\,action\,8\,r\,requirement\,req2\,Reference List\,heading 8\, action\,action1\,action2\,action11\,action3\,action4\,action5\,action6\,action7\,action12\,action21\,action111\,action31\,action8\,H8\,不";
	mso-style-link:"标题 8 字符\,App2 字符\,（A） 字符\,注意框体 字符\,ITT t8 字符\,PA Appendix Minor 字符\,T8 字符\,\(use for figures\) 字符\,\(figure\) 字符\,标题6 字符\,action 字符\,8 字符\,r 字符\,requirement 字符\,req2 字符\,Reference List 字符\,heading 8 字符\, action 字符\,action1 字符\,action2 字符\,action11 字符\,action3 字符\,action4 字符\,不 字符";
	margin-top:12.0pt;
	margin-right:0cm;
	margin-bottom:3.2pt;
	margin-left:220.9pt;
	text-align:justify;
	text-justify:inter-ideograph;
	text-indent:-72.0pt;
	line-height:133%;
	page-break-after:avoid;
	font-size:12.0pt;
	font-family:"Cambria",serif;
	color:windowtext;}
p.MsoHeading9, li.MsoHeading9, div.MsoHeading9
	{mso-style-name:"标题 9\,13\,append\,Titre 10\,PIM 9\,huh\,Appendix\,标题12\,ITT t9\,T9\,Bijlage\,\(use for tables\)\,标\,progress\,App Heading\,9\,rb\,req bullet\,req1\,heading 9\, progress\,progress1\,progress2\,progress11\,progress3\,progress4\,progress5\,progress6\,progress7\,progress12\,progress21\,三级标";
	mso-style-link:"标题 9 字符\,13 字符\,append 字符\,Titre 10 字符\,PIM 9 字符\,huh 字符\,Appendix 字符\,标题12 字符\,ITT t9 字符\,T9 字符\,Bijlage 字符\,\(use for tables\) 字符\,标 字符\,progress 字符\,App Heading 字符\,9 字符\,rb 字符\,req bullet 字符\,req1 字符\,heading 9 字符\, progress 字符\,progress1 字符\,progress2 字符\,progress11 字符";
	margin-top:12.0pt;
	margin-right:0cm;
	margin-bottom:3.2pt;
	margin-left:228.1pt;
	text-align:justify;
	text-justify:inter-ideograph;
	text-indent:-79.2pt;
	line-height:133%;
	page-break-after:avoid;
	font-size:10.5pt;
	font-family:"Cambria",serif;
	color:windowtext;}
p.MsoHeader, li.MsoHeader, div.MsoHeader
	{mso-style-link:"页眉 字符";
	margin:0cm;
	margin-bottom:.0001pt;
	text-align:center;
	layout-grid-mode:char;
	border:none;
	padding:0cm;
	font-size:10.5pt;
	font-family:"Times New Roman",serif;
	color:windowtext;}
p.MsoFooter, li.MsoFooter, div.MsoFooter
	{mso-style-link:"页脚 字符";
	margin:0cm;
	margin-bottom:.0001pt;
	layout-grid-mode:char;
	font-size:10.5pt;
	font-family:"Times New Roman",serif;
	color:windowtext;}
span.1
	{mso-style-name:"标题 1 字符\,H1 字符\,第一章 标题1 字符\,36标题 1 字符\,36标题1 字符\,章节 字符\,第一层 字符\,123321 字符\,h1 字符\,PIM 1 字符\,1\. 字符\,tzy1 字符\,tzy11 字符\,tzy12 字符\,tzy111 字符\,tzy13 字符\,tzy112 字符\,H11 字符\,H12 字符\,H111 字符\,H13 字符\,H112 字符\,章名 字符\,标题 11 字符\,Heading 0 字符\,Section Head 字符\,Header1 字符\,1st level 字符\,l1 字符";
	mso-style-link:"标题 1\,H1\,第一章 标题1\,36标题 1\,36标题1\,章节\,第一层\,123321\,h1\,PIM 1\,1\.\,tzy1\,tzy11\,tzy12\,tzy111\,tzy13\,tzy112\,H11\,H12\,H111\,H13\,H112\,章名\,标题 11\,Heading 0\,Section Head\,Header1\,1st level\,l1\,Fab-1\,Heading 01\,Heading 02\,Heading 03\,Heading 04\,Heading 011\,Heading 021\,章\,I1\,T1\,论文题目";
	font-family:"Times New Roman",serif;
	font-weight:bold;}
span.a
	{mso-style-name:"页眉 字符";
	mso-style-link:页眉;
	font-family:"Times New Roman",serif;}
span.a0
	{mso-style-name:"页脚 字符";
	mso-style-link:页脚;
	font-family:"Times New Roman",serif;}
p.a1, li.a1, div.a1
	{mso-style-name:封面_项目名称;
	mso-style-link:"封面_项目名称 Char";
	margin:0cm;
	margin-bottom:.0001pt;
	line-height:150%;
	font-size:16.0pt;
	font-family:仿宋_GB2312;
	color:windowtext;
	font-weight:bold;}
span.Char
	{mso-style-name:"封面_项目名称 Char";
	mso-style-link:封面_项目名称;
	font-family:仿宋_GB2312;
	font-weight:bold;}
p.a2, li.a2, div.a2
	{mso-style-name:正文_招标文件;
	mso-style-link:"正文_招标文件 Char";
	margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	text-indent:24.0pt;
	line-height:26.0pt;
	layout-grid-mode:char;
	font-size:12.0pt;
	font-family:仿宋_GB2312;
	color:windowtext;}
p.10, li.10, div.10
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
	font-family:黑体;
	color:windowtext;}
span.Char0
	{mso-style-name:"正文_招标文件 Char";
	mso-style-link:正文_招标文件;
	font-family:仿宋_GB2312;}
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
	color:windowtext;
	font-weight:bold;}
span.1Char
	{mso-style-name:"正文_标题1 Char";
	mso-style-link:正文_标题1;
	font-family:黑体;}
p.a3, li.a3, div.a3
	{mso-style-name:域_正文_项目名称;
	mso-style-link:"域_正文_项目名称 Char";
	margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	text-indent:24.0pt;
	line-height:26.0pt;
	layout-grid-mode:char;
	font-size:12.0pt;
	font-family:仿宋_GB2312;
	color:#C00000;
	font-weight:bold;}
span.2Char
	{mso-style-name:"正文_标题2 Char";
	mso-style-link:正文_标题2;
	font-family:仿宋_GB2312;
	font-weight:bold;}
p.a4, li.a4, div.a4
	{mso-style-name:域_正文_项目类别;
	mso-style-link:"域_正文_项目类别 Char";
	margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	text-indent:24.0pt;
	line-height:26.0pt;
	layout-grid-mode:char;
	font-size:12.0pt;
	font-family:仿宋_GB2312;
	color:#7030A0;
	font-weight:bold;}
span.Char1
	{mso-style-name:"域_正文_项目名称 Char";
	mso-style-link:域_正文_项目名称;
	font-family:仿宋_GB2312;
	color:#C00000;
	font-weight:bold;}
p.a5, li.a5, div.a5
	{mso-style-name:域_技术指标要求;
	mso-style-link:"域_技术指标要求 Char";
	margin-top:0cm;
	margin-right:0cm;
	margin-bottom:0cm;
	margin-left:1.0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	line-height:26.0pt;
	layout-grid-mode:char;
	font-size:12.0pt;
	font-family:仿宋_GB2312;
	color:#C00000;
	font-weight:bold;}
span.Char2
	{mso-style-name:"域_正文_项目类别 Char";
	mso-style-link:域_正文_项目类别;
	font-family:仿宋_GB2312;
	color:#7030A0;
	font-weight:bold;}
span.Char3
	{mso-style-name:"域_技术指标要求 Char";
	mso-style-link:域_技术指标要求;
	font-family:仿宋_GB2312;
	color:#C00000;
	font-weight:bold;}
p.a6, li.a6, div.a6
	{mso-style-name:域_正文_调研报告;
	mso-style-link:"域_正文_调研报告 Char";
	margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	text-indent:10.0pt;
	line-height:150%;
	font-size:14.0pt;
	font-family:仿宋_GB2312;
	color:#632423;}
span.Char4
	{mso-style-name:"域_正文_调研报告 Char";
	mso-style-link:域_正文_调研报告;
	font-family:仿宋_GB2312;
	color:#632423;}
.MsoChpDefault
	{font-family:"Calibri",sans-serif;}
 /* Page Definitions */
 @page WordSection1
	{size:595.3pt 841.9pt;
	margin:62.35pt 113.35pt 62.35pt 62.35pt;
	layout-grid:15.6pt;}
div.WordSection1
	{page:WordSection1;}
@page WordSection2
	{size:595.3pt 841.9pt;
	margin:2.0cm 2.0cm 2.0cm 2.0cm;
	layout-grid:15.6pt;}
div.WordSection2
	{page:WordSection2;}
@page WordSection3
	{size:595.3pt 841.9pt;
	margin:2.0cm 2.0cm 2.0cm 2.0cm;
	layout-grid:15.6pt;}
div.WordSection3
	{page:WordSection3;}
@page WordSection4
	{size:595.3pt 841.9pt;
	margin:2.0cm 2.0cm 2.0cm 2.0cm;
	layout-grid:15.6pt;}
div.WordSection4
	{page:WordSection4;}
@page WordSection5
	{size:595.3pt 841.9pt;
	margin:2.0cm 2.0cm 2.0cm 2.0cm;
	layout-grid:15.6pt;}
div.WordSection5
	{page:WordSection5;}
@page WordSection6
	{size:595.3pt 841.9pt;
	margin:72.0pt 90.0pt 72.0pt 90.0pt;
	layout-grid:15.6pt;}
div.WordSection6
	{page:WordSection6;}
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




<body lang=ZH-CN link=blue vlink=purple style='text-justify-trim:punctuation'>
<div id="dayin" style='margin-left: 20px;margin-top: 20px'>
<div class=WordSection1 style='layout-grid:15.6pt'>

<p class=MsoNormal><span style='font-size:16.0pt;font-family:仿宋_GB2312;
display:none'>注意：设备名称、单位、台套数等数据只需要填写一遍，然后按左上角“更新全部域”按钮即可</span></p>



<p class=MsoNormal><span lang=EN-US style='font-size:16.0pt;font-family:仿宋_GB2312'>&nbsp;</span></p>

<p class=MsoNormal align=center style='text-align:center'><b><span
style='font-size:22.0pt;font-family:黑体'>北京航天时代激光导航技术有限责任公司</span></b></p>

<p class=MsoNormal align=center style='text-align:center'><b><span
style='font-size:22.0pt;font-family:黑体'>招标文件</span></b></p>

<p class=MsoNormal align=center style='text-align:center'><b><span lang=EN-US
style='font-size:22.0pt'>&nbsp;</span></b></p>
<p class=MsoNormal align=center style='text-align:center'><b><span lang=EN-US
style='font-size:22.0pt'>&nbsp;</span></b></p>

<p class=MsoNormal style='text-indent:80.3pt'><b><span style='font-size:16.0pt;
font-family:宋体'>项目名称：${swBidding.projectName}</span></b></p>

<p class=MsoNormal style='text-indent:80.3pt'><b><span lang=EN-US
style='font-size:16.0pt'>&nbsp;</span></b></p>
<p class=MsoNormal style='text-indent:80.3pt'><b><span lang=EN-US
style='font-size:16.0pt'>&nbsp;</span></b></p>

<p class=MsoNormal style='text-indent:80.3pt'><b><span style='font-size:16.0pt;
font-family:宋体'>需求部门：${swBidding.field1}</span></b></p>

<p class=MsoNormal style='text-indent:80.3pt'><b><span lang=EN-US
style='font-size:16.0pt'>&nbsp;</span></b></p>
<p class=MsoNormal style='text-indent:80.3pt'><b><span lang=EN-US
style='font-size:16.0pt'>&nbsp;</span></b></p>

<p class=MsoNormal style='text-indent:80.3pt'><b><span style='font-size:16.0pt;
font-family:宋体'>编写：</span></b></p>


<p class=MsoNormal style='text-indent:80.3pt'><b><span lang=EN-US
style='font-size:16.0pt'>&nbsp;</span></b></p>
<p class=MsoNormal style='text-indent:80.3pt'><b><span lang=EN-US
style='font-size:16.0pt'>&nbsp;</span></b></p>

<p class=MsoNormal style='text-indent:80.3pt'><b><span style='font-size:16.0pt;
font-family:宋体'>审核：</span></b></p>

<p class=MsoNormal style='text-indent:80.3pt'><b><span lang=EN-US
style='font-size:16.0pt'>&nbsp;</span></b></p>
<p class=MsoNormal style='text-indent:80.3pt'><b><span lang=EN-US
style='font-size:16.0pt'>&nbsp;</span></b></p>

<p class=MsoNormal style='text-indent:80.3pt'><b><span style='font-size:16.0pt;
font-family:宋体'>批准：</span></b></p>

<p class=MsoNormal style='text-indent:80.3pt'><b><span lang=EN-US
style='font-size:16.0pt'>&nbsp;</span></b></p>
<p class=MsoNormal style='text-indent:80.3pt'><b><span lang=EN-US
style='font-size:16.0pt'>&nbsp;</span></b></p>

<p class=MsoNormal style='text-indent:80.3pt'><b><span style='font-size:16.0pt;
font-family:宋体'>招标办主任：</span></b></p>

<p class=MsoNormal style='text-indent:80.3pt'><b><span lang=EN-US
style='font-size:16.0pt'>&nbsp;</span></b></p>
<p class=MsoNormal style='text-indent:80.3pt'><b><span lang=EN-US
style='font-size:16.0pt'>&nbsp;</span></b></p>

<p class=MsoNormal style='text-indent:80.3pt'><b><span style='font-size:16.0pt;
font-family:宋体'>公司主管招标领导：</span></b></p>

<p class=MsoNormal style='text-indent:80.3pt'><b><span lang=EN-US
style='font-size:16.0pt'>&nbsp;</span></b></p>

<p class=MsoNormal align=left style='text-align:left'><span lang=EN-US
style='font-size:16.0pt;font-family:仿宋_GB2312'>&nbsp;</span></p>

<p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
style='font-size:16.0pt;'><fmt:formatDate value="${swBidding.createDate}" pattern="yyyy年MM月dd日"/></span><span style='font-size:16.0pt;
font-family:宋体;color:#C00000'></span><span lang=EN-US style='font-size:16.0pt;
color:#C00000'></span><span style='font-size:16.0pt;font-family:宋体;color:#C00000'></span><span
lang=EN-US style='font-size:16.0pt;color:#C00000'></span><span
style='font-size:16.0pt;font-family:宋体;color:#C00000'></span></p>

</div>

<span lang=EN-US style='font-size:16.0pt;font-family:"Times New Roman",serif'><br
clear=all style='page-break-before:always'>
</span>
<div style="page-break-after:always;"></div>
<div class=WordSection2 style='layout-grid:15.6pt'>

<p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
style='font-size:16.0pt'>&nbsp;</span></p>

<p class=10 style='margin-bottom:15.6pt'><span style='font-size:16.0pt;
line-height:150%'>第一部分&nbsp;招标邀请书</span></p>

<p class=a2 style='text-indent:32.0pt'><span style='font-size:16.0pt;line-height:32px'>按照本单位《招投标管理办法》，北京航天时代激光导航技术有限责任公司对${swBidding.projectName}进行招标工作。</span><span
class=Char2><span lang=EN-US style='font-size:16.0pt'>&nbsp;&nbsp; </span></span><span
class=Char1><span lang=EN-US style='font-size:16.0pt'>&nbsp;&nbsp;</span></span></p>

<p class=2 style='margin-top:15.6pt;font-size:23px'><span lang=EN-US>一、<span style='font:15.0pt "Times New Roman"'>&nbsp;
</span></span><span lang=X-NONE><b >招标内容：</b> </span></p>

<p class=a2 style='margin-left:45.0pt;text-indent:-45.0pt'><span lang=EN-US
style='font-size:16.0pt'><span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;
</span>1.<span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp; </span></span><span
style='font-size:16.0pt;line-height:32px'>货物名称：${swBidding.goodsName}</span></p>

<p class=a2 style='margin-left:45.0pt;text-indent:-45.0pt'><span lang=EN-US
style='font-size:16.0pt'><span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;
</span>2.<span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp; </span></span><span
style='font-size:16.0pt;line-height:32px'>货物数量：${swBidding.goodsAmount}台<a name=设备数量></a><a
name=设备单位><span style='color:#C00000'></span></a><b></b></span></p>

<p class=a2 style='margin-left:45.0pt;text-indent:-45.0pt'><span lang=EN-US
style='font-size:16.0pt'><span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;
</span>3.<span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp; </span></span><span
style='font-size:16.0pt;line-height:32px'>品牌：${swBidding.goodsBrand}</span></p>

<p class=a2 style='margin-left:45.0pt;text-indent:-45.0pt'><span lang=EN-US
style='font-size:16.0pt'><span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;
</span>4.<span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp; </span></span><span
style='font-size:16.0pt;line-height:32px'>到货地点：北京市海淀区丰滢东路1号院内（以签订合同为准）</span><span class=Char1><span style='font-size:
16.0pt;font-weight:normal'><span lang=EN-US></span></span></span><span
style='font-size:16.0pt'></span></p>

<p class=a2 style='margin-left:45.0pt;text-indent:-45.0pt'><span lang=EN-US
style='font-size:16.0pt'><span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;
</span>5.<span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp; </span></span><span
style='font-size:16.0pt;line-height:32px'>包装：以新制坚固木箱或铁皮箱包装，适用于航空运输、防锈、防潮、防粗暴装卸</span></p>

<p class=a2 style='margin-left:45.0pt;text-indent:-45.0pt'><span lang=EN-US
style='font-size:16.0pt'><span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;
</span>6.<span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp; </span></span><span
style='font-size:16.0pt;line-height:32px'>运输方式：陆运或空运，由供方负责。</span></p>

<p class=a2 style='margin-left:45.0pt;text-indent:-45.0pt'><span lang=EN-US
style='font-size:16.0pt'><span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;
</span>7.<span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp; </span></span><span
style='font-size:16.0pt;line-height:32px'>货物交货期：合同签订后<span lang=EN-US>150</span>天供货、安装完毕</span></p>

<p class=2 style='margin-top:15.6pt'><span lang=EN-US style="font-size:23px"><b>二、</b><span style='font:7.0pt "Times New Roman"'>&nbsp;
</span></span><span lang=X-NONE style="font-size:23px"><b>投标地点：</b></span></p>

<p class=a2 style='text-indent:32.0pt'><span style='font-size:16.0pt;line-height:32px'>北京市海淀区丰滢东路<span
lang=EN-US>1</span>号院</span></p>

<p class=2 style='margin-top:15.6pt'><span lang=EN-US><span style='font:7.0pt "Times New Roman"'>&nbsp;
</span></span><span lang=X-NONE style="font-size:23px"><b>三、投标截止时间：</b></span></p>

<p class=a2 style='text-indent:32.0pt'><span style='font-size:16.0pt;line-height:32px'>投标书必须在<fmt:formatDate value="${swBidding.closingDate}" pattern="yyyy年MM月dd日"/></span><a
name=投标截止日期><span class=Char1><span lang=EN-US style='font-size:16.0pt;
font-weight:normal'></span></span></a><span class=Char1><span
style='font-size:16.0pt;font-weight:normal'><span lang=EN-US></span><span
lang=EN-US></span></span></span><span lang=EN-US style='font-size:16.0pt'>11</span><span
style='font-size:16.0pt;line-height:32px'>时前送达投标地点</span></p>



<p class=a3 style='text-indent:24.1pt'><span lang=EN-US>&nbsp;</span></p>

<p class=a2><span lang=EN-US>&nbsp;</span></p>

<p class=a2>招标单位：北京航天时代激光导航技术有限责任公司</p>

<p class=a2>接收人：晏丹翌</p>

<p class=a2>电话：<span lang=EN-US>010</span>－<span lang=EN-US>88105199</span></p>

<p class=a2>传真：<span lang=EN-US>01088105200</span></p>

</div>
<div style="page-break-after:always;"></div>
<span lang=EN-US style='font-size:12.0pt;font-family:仿宋_GB2312'><br clear=all
style='page-break-before:always'>
</span>

<div class=WordSection3 style='layout-grid:15.6pt'>


<p class=10 style='margin-bottom:15.6pt'><span style='font-size:16.0pt;
line-height:150%'>第二部分<span lang=EN-US>&nbsp; </span>投标须知</span></p>

<p class=2 style='margin-top:15.6pt'><span lang=EN-US><span style='font:16.0pt "Times New Roman"'>&nbsp;
</span></span><span style='font-size:16.0pt' lang=X-NONE>一、总则</span></p>

<p class=a2 style='text-indent:32.0pt'>
<span lang=EN-US style='font-size:16.0pt ;line-height:32px'>1.本项目系北京航天时代激光导航技术有限责任公司${swBidding.goodsName}采购项目，采用内部招标方式进行。</span></p>

<p class=a2 style='text-indent:32.0pt'>
<span lang=EN-US style='font-size:16.0pt ;line-height:32px'>2.本次招标投标工作按照本单位《内部招标管理办法》相关要求进行。</span></p>

<p class=2 style='margin-top:15.6pt'><span  style='font-size:16.0pt' lang=EN-US>二、投标文件的编制</span></p>

<p class=a2 style='text-indent:32.0pt'>
<span lang=EN-US style='font-size:16.0pt ;line-height:32px'>1.投标文件应按照一式九份（正本一份、副本八份）。正本和副本如有差别，以正本为准；投标文件中的大写金额和小写金额不一致的，以大写金额为准；总价金额与按单价汇总金额不一致的，以单价金额计算结果为准，单价金额小数点明显错误的，应以总价为准，并修改单价。</span></p>

<p class=a2 style='text-indent:32.0pt'>
<span lang=EN-US style='font-size:16.0pt ;line-height:32px'>2．投标单位必须按照招标文件的要求对技术文件中的项目进行投标，并提交必要的文字说明及图片。</span></p>

<p class=a2 style='text-indent:32.0pt'>
<span lang=EN-US style='font-size:16.0pt ;line-height:32px'>3.投标单位对所投的项目只能提出一个不变的价格，招标单位不接受任何选择。</span></p>

<p class=a2 style='text-indent:32.0pt'>
<span lang=EN-US style='font-size:16.0pt ;line-height:32px'>4.投标文件应字迹清楚、内容齐全、表达准确、不应有涂改增删。如需修改应有文字修改函，并盖法定代表人印章，一式九份，分别标以正本一份、副本八份。</span></p>

<p class=a2 style='text-indent:32.0pt'>
<span lang=EN-US style='font-size:16.0pt ;line-height:32px'>5．投标单位对项目报价、质量保证措施及服务等方面给招标单位提供的优惠条件，应在文件中予以说明。</span></p>

<p class=a2 style='text-indent:32.0pt'>
<span lang=EN-US style='font-size:16.0pt ;line-height:32px'>6.投标文件及修改文件一律用A4纸打印。</span></p>
<div style="page-break-after:always;"></div>
<p class=2 style='margin-top:15.6pt'>
<span lang=EN-US style='font-size:16.0pt;'>三、资格证明文件</span></p>

<p class=a2 style='text-indent:32.0pt ;line-height:32px'><span lang=EN-US style='font-size:16.0pt'>1.投标单位企业法人营业执照复印件。税务登记证书复印件、法人代码证书复印件、业绩证明等。</span></p>

<p class=a2 style='text-indent:32.0pt ;line-height:32px'><span lang=EN-US style='font-size:16.0pt'>2.投标企业的综合能力以及所提供项目情况说明。</span></p>

<p class=a2 style='text-indent:32.0pt ;line-height:32px'><span lang=EN-US style='font-size:16.0pt'>3.项目所需的各类证书、有关检测报告，以及环保资质证书。</span></p>

<p class=a2 style='text-indent:32.0pt ;line-height:32px'><span lang=EN-US style='font-size:16.0pt'>4.法定代表人授权书及被授权人身份证复印件。</span></p>

<p class=a2 style='text-indent:32.0pt ;line-height:32px'><span lang=EN-US style='font-size:16.0pt'>5.项目说明等技术资料。</span></p>

<p class=2 style='margin-top:15.6pt'><span lang=EN-US style='font-size:16.0pt;'>四、投标文件递交</span></p>

<p class=a2 style='text-indent:32.0pt ;line-height:32px'><span lang=EN-US style='font-size:16.0pt'>1.投标单位应把投标文件装入标准袋内用标准信封加以密封，并在封签处分别交叉加盖单位公章、法人章各两枚，投标文件按要求填写。</span></p>

<p class=a2 style='text-indent:32.0pt'><span lang=EN-US style='font-size:16.0pt'>2.</span><span
style='font-size:16.0pt ;line-height:32px'>有下列情况之一的，其投标文件视为无效：</span></p>

<p class=a2 style='text-indent:32.0pt'><span style='font-size:16.0pt ;line-height:32px'>（<span
lang=EN-US>1</span>）投标文件未按照规定密封；</span></p>

<p class=a2 style='text-indent:32.0pt'><span style='font-size:16.0pt ;line-height:32px'>（<span
lang=EN-US>2</span>）投标文件未加盖公章和法人代表印章；</span></p>

<p class=a2 style='text-indent:32.0pt'><span style='font-size:16.0pt ;line-height:32px'>（<span
lang=EN-US>3</span>）在截至日期（</span><span
><span lang=EN-US style='font-size:16.0pt;font-weight:normal ;line-height:32px'><fmt:formatDate value="${swBidding.closingDate}" pattern="yyyy年MM月dd日"/></span></span><span
class=Char1><span style='font-size:16.0pt;font-weight:normal'><span
lang=EN-US></span></span></span><span
style='font-size:16.0pt'>18）前未送达的。</span></p>

<p class=2 style='margin-top:15.6pt'><span lang=EN-US  style='font-size:16.0pt;'>五、开标</span></p>

<p class=a2 style='text-indent:32.0pt ;line-height:32px'><span lang=EN-US style='font-size:16.0pt'>1.</span><span
style='font-size:16.0pt'>招标单位于评标完毕后以《中标通知书》的形式通知中标单位并开展后续工作。</span></p>

<p class=a2 style='text-indent:32.0pt ;line-height:32px'><span lang=EN-US style='font-size:16.0pt'>2.</span><span
style='font-size:16.0pt'>但投标单位少于三家时，本次招标采购工作活动终止。</span></p>

<p class=2 style='margin-top:15.6pt'><span lang=EN-US style='font-size:16.0pt;'>六、评标</span></p>

<p class=a2 style='text-indent:32.0pt ;line-height:32px'><span lang=EN-US style='font-size:16.0pt'>1.</span><span
style='font-size:16.0pt'>按照本单位《内部招标管理办法》组织评标小组进行评标。</span></p>

<p class=a2 style='text-indent:32.0pt ;line-height:32px'><span lang=EN-US style='font-size:16.0pt'>2.</span><span
style='font-size:16.0pt'>评标小组根据投标单位的概况、所投货物的技术先进程度、价格、质量、工艺技术、销售服务及投标单位资质等方面的情况进行综合评议，从中评选出中标单位。</span></p>
<div style="page-break-after:always;"></div>
<p class=2 style='margin-top:15.6pt'><span lang=EN-US style='font-size:16.0pt;'>七、中标通知</span></p>

<p class=a2 style='text-indent:32.0pt ;line-height:32px'><span lang=EN-US style='font-size:16.0pt'>1.</span><span
style='font-size:16.0pt'>招标单位在决标后，向中标单位发出《中标通知书》。</span></p>

<p class=a2 style='text-indent:32.0pt ;line-height:32px'><span lang=EN-US style='font-size:16.0pt'>2.</span><span
style='font-size:16.0pt'>《中标通知书》将是合同的一个组成部分。</span></p>

<p class=2 style='margin-top:15.6pt'><span lang=EN-US style='font-size:16.0pt;'>八、签订合同</span></p>

<p class=a2 style='text-indent:32.0pt'><span style='font-size:16.0pt ;line-height:32px'>中标单位收到中标通知书后，按照相关事宜签订购销合同，按照合同的内容组织生产、供货及售后服务。</span></p>

<p class=2 style='margin-top:15.6pt'><span lang=EN-US style='font-size:16.0pt;'>九、投标文件顺序</span></p>

<p class=a2 style='text-indent:32.0pt ;line-height:32px'><span lang=EN-US style='font-size:16.0pt'>1</span><span
style='font-size:16.0pt'>．法人授权书及被授权人身份证复印件；</span></p>

<p class=a2 style='text-indent:32.0pt ;line-height:32px'><span lang=EN-US style='font-size:16.0pt'>2</span><span
style='font-size:16.0pt'>．投标单位营业执照；</span></p>

<p class=a2 style='text-indent:32.0pt ;line-height:32px'><span lang=EN-US style='font-size:16.0pt'>3</span><span
style='font-size:16.0pt'>．投标书；</span></p>

<p class=a2 style='text-indent:32.0pt ;line-height:32px'><span lang=EN-US style='font-size:16.0pt'>4</span><span
style='font-size:16.0pt'>．招标单位要求的各种表格；</span></p>

<p class=a2 style='text-indent:32.0pt'><span lang=EN-US style='font-size:16.0pt'>5</span><span
style='font-size:16.0pt'>．其他：企业资质证明、企业介绍、技术文件、产品说明书及各方面文件等。</span></p>

<p class=a2><span lang=EN-US>&nbsp;</span></p>

</div>

<span lang=EN-US style='font-size:12.0pt;font-family:仿宋_GB2312'><br clear=all
style='page-break-before:always'>
</span>

<div class=WordSection4 style='layout-grid:15.6pt'>

<div style="page-break-after:always;"></div>
<p class=10 style='margin-bottom:15.6pt'><span style='font-size:16.0pt;
line-height:150%'>第三部分<span lang=EN-US>&nbsp; </span>技术文件</span></p>

<p class=2 style='margin-top:15.6pt'><span lang=EN-US style='font-size:16.0pt;'>十、项目名称：</span></p>

<p class=a2 style='margin-left:28.25pt;text-indent:28.3pt'><span
style='font-size:16.0pt'>${swBidding.goodsName} </span></p>

<p class=2 style='margin-top:15.6pt'><span lang=EN-US style='font-size:16.0pt;'> 十一、供货范围</span></p>

<p class=a2 style='margin-left:21.2pt;text-indent:35.5pt'><span
style='font-size:16.0pt ;line-height:32px'>设备供货范围：${swBidding.goodsName}</span><span
lang=EN-US style='font-size:16.0pt'>${swBidding.goodsAmount}</span><span style='font-size:16.0pt;'>台</span><b><span style='font-size:16.0pt'>，</span></b><span
style='font-size:16.0pt ;line-height:32px'>以及在本技术文件中未提及的、但为确保该试验系统正常、稳定、可靠运行所必须的其它配套设备。</span></p>

<p class=a2 style='margin-left:21.2pt;text-indent:35.5pt'><span
style='font-size:16.0pt ;line-height:32px'>${swBidding.goodsComposition}</span></p>

<p class=a2 style='margin-left:21.2pt;text-indent:35.5pt'><span
style='font-size:16.0pt ;line-height:32px'>伴随服务要求：卖方在按上述供货范围提供设备的同时，须提供与之相应的、在招标文件中规定的伴随服务，包括：技术资料、软件、安装、调试、现场试运行、技术支持、技术协助、技术培训等，以及在本招标文件中未提及的、隐含的其它必须由卖方提供的相关服务。</span></p>

<p class=2 style='margin-top:15.6pt'><span lang=EN-US style='font-size:16.0pt;'>十二、基本要求：</span></p>

<p class=a2 style='margin-left:21.2pt;text-indent:35.5pt'><span
style='font-size:16.0pt ;line-height:32px'>上述设备结构应满足长期、安全、可靠的使用要求。能够满足用户生产试验要求。能满足用户操作和监测都更加简单和直观的要求。</span></p>

<p class=2 style='margin-top:15.6pt'><span lang=EN-US style='font-size:16.0pt;'>十三、执行设备检定标准：</span></p>

<p class=a2 style='margin-left:21.2pt;text-indent:35.5pt'><span
style='font-size:16.0pt ;line-height:32px'>按照国家相关标准和规定进行验收。</span></p>

<p class=2 style='margin-top:15.6pt'><span lang=EN-US style='font-size:16.0pt;'>十四、主要技术规格及参数：</span></p>

<p class=a2 style='margin-left:21.2pt;text-indent:35.5pt'><span
style='font-size:16.0pt ;line-height:32px'>技术指标要求：${swBidding.technical}</span><a name=工艺指标要求></a></p>





<p class=2 style='margin-top:15.6pt'><span lang=EN-US style='font-size:16.0pt;'>十五、其他技术文件</span></p>

<p class=a2 style='margin-left:21.2pt;text-indent:35.5pt'><span lang=EN-US
style='font-size:16.0pt'>1</span><span style='font-size:16.0pt ;line-height:32px'>、硬件资料：应提供使用说明手册、维修维护的详细技术说明及图纸。</span></p>

<p class=a2 style='margin-left:21.2pt;text-indent:35.5pt'><span lang=EN-US
style='font-size:16.0pt'>2</span><span style='font-size:16.0pt ;line-height:32px'>、其它资料：制造标准、检验项目及方法和标定校准方法、出厂验收文件等，提供主要部件结构示意图，提供易损件配件、专用工具、附件等。</span></p>

<p class=a2 style='margin-left:21.2pt;text-indent:35.5pt'><span lang=EN-US
style='font-size:16.0pt'>3</span><span style='font-size:16.0pt ;line-height:32px'>、验收条件：投标方应在投标文件中详细列出系统的预验收条款及验收方法，终验收以投标方与用户最终协商达成的验收条件和方法为准。</span></p>

</div>

<span lang=EN-US style='font-size:12.0pt;font-family:仿宋_GB2312'><br clear=all
style='page-break-before:always'>
</span>

<div class=WordSection5 style='layout-grid:15.6pt'>


<p class=10 style='margin-bottom:15.6pt'><span style='font-size:16.0pt;
line-height:150%'>第四部分<span lang=EN-US>&nbsp; </span>其他要求</span></p>

<p class=2 style='margin-top:15.6pt'><span lang=EN-US style='font-size:16.0pt;'>一、投标要求</span></p>

<p class=a2 style='text-indent:32.0pt'><span lang=EN-US style='font-size:16.0pt'>1.</span><span
style='font-size:16.0pt ;line-height:32px'>投标单位须提供清单上列明的货物及附件、备件、并负责运输到指定地点。</span></p>

<p class=a2 style='text-indent:32.0pt'><span lang=EN-US style='font-size:16.0pt'>2.</span><span
style='font-size:16.0pt ;line-height:32px'>投标单位需为具有招标货物相应的生产或销售经营权的供应商。</span></p>

<p class=2 style='margin-top:15.6pt'><span lang=EN-US style='font-size:16.0pt;'>二、质量保证</span></p>

<p class=a2 style='text-indent:32.0pt'><span lang=EN-US style='font-size:16.0pt'>1.</span><span
style='font-size:16.0pt ;line-height:32px'>提供所投产品标准配置清单；</span></p>

<p class=a2 style='text-indent:32.0pt'><span lang=EN-US style='font-size:16.0pt'>2.</span><span
style='font-size:16.0pt ;line-height:32px'>供货商应保证产品是全新的、未使用过的，完全符合合同规定的质量、规格和性能等要求。</span></p>

<p class=2 style='margin-top:15.6pt'><span lang=EN-US style='font-size:16.0pt;'>三、产品保修期限和保修内容</span></p>

<p class=a2 style='text-indent:32.0pt'><span lang=EN-US style='font-size:16.0pt'>1</span><span
style='font-size:16.0pt ;line-height:32px'>．从设备终验收合格之日起免费保修期为<span lang=EN-US>1</span>年；在保修期内，如果由于设备自身的故障导致系统无法工作，投标方必须提供<span
lang=EN-US>12</span>小时内的响应服务，<span lang=EN-US>3</span>个工作日内排除故障。保修期过后必须保证长期提供良好的维修服务，能终身提供广泛优惠的技术支持及设备备件供应。</span></p>

<p class=a2 style='text-indent:32.0pt'><span lang=EN-US style='font-size:16.0pt'>2</span><span
style='font-size:16.0pt ;line-height:32px'>．设备安装调试应按照双方商定的时间表执行，供方在设备到达安装地点接到需方通知后，必须在<span
lang=EN-US>15</span>日内经双方按设备供货清单验收后开始设备的安装、调试等工作。</span></p>

<p class=a2 style='text-indent:32.0pt'><span lang=EN-US style='font-size:16.0pt'>3</span><span
style='font-size:16.0pt ;line-height:32px'>．供方应提供设备所必须的消耗件、易损件和备件清单，包括名称、数量、单价和总价。</span></p>

<p class=a2 style='text-indent:32.0pt'><span lang=EN-US style='font-size:16.0pt'>4</span><span
style='font-size:16.0pt ;line-height:32px'>．供方对标定、使用和维护操作以及系统各种可能的技术故障、原因和解决方法进行详细说明的全套技术资料及软件技术资料。</span></p>

<p class=a2 style='text-indent:32.0pt'><span lang=EN-US style='font-size:16.0pt'>5</span><span
style='font-size:16.0pt ;line-height:32px'>．供方在合同签订后<span lang=EN-US>2</span>周内，应提供设备安装、调试、使用时的详细要求。</span></p>

<p class=2 style='margin-top:15.6pt'><span lang=EN-US style='font-size:16.0pt;'>四、安装、调试及验收</span></p>

<p class=a2 style='text-indent:32.0pt'><span lang=EN-US style='font-size:16.0pt'>1.
</span><span style='font-size:16.0pt ;line-height:32px'>供方派技术人员负责设备安装、调试工作并出具调试报告。</span></p>

<p class=a2 style='text-indent:32.0pt'><span lang=EN-US style='font-size:16.0pt'>2.
</span><span style='font-size:16.0pt ;line-height:32px'>最终验收时招标单位可以经相关部门按有关规定标准验收检验；如果需要，供应商必须提供相关的验收标准、方式并协助进行。</span></p>

<p class=a2 style='text-indent:32.0pt'><span lang=EN-US style='font-size:16.0pt'>3.</span><span
style='font-size:16.0pt ;line-height:32px'>当满足以下条件时，货物为最终验收合格。</span></p>

<p class=a2 style='text-indent:32.0pt'><span style='font-size:16.0pt ;line-height:32px'>（<span
lang=EN-US>1</span>）投标单位已提供了合同中签署的全部货物。</span></p>

<p class=a2 style='text-indent:32.0pt'><span style='font-size:16.0pt ;line-height:32px'>（<span
lang=EN-US>2</span>）货物符合技术文件中的规定，性能满足要求。</span></p>

<p class=a2 style='text-indent:32.0pt'><span style='font-size:16.0pt ;line-height:32px'>（<span
lang=EN-US>3</span>）验收中出现的所有缺陷已经改正至招标单位满意。</span></p>



</div>

<span lang=EN-US style='font-size:14.0pt;line-height:150%;font-family:仿宋_GB2312;
color:#632423'><br clear=all style='page-break-before:always'>
</span>

<div class=WordSection6 style='layout-grid:15.6pt'>
<div style="page-break-after:always;"></div>
<p class=MsoNormal align=left style='text-align:left;line-height:28.0pt'><span
style='font-size:16.0pt;font-family:黑体'>附件<span lang=EN-US>1 </span></span><span
lang=EN-US style='font-family:黑体'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span
lang=EN-US style='font-size:12.0pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><b><span
style='font-size:15.0pt;font-family:仿宋_GB2312'>内部招标登记表</span></b></p>

<p class=MsoNormal align=right style='text-align:right;line-height:28.0pt'><span
lang=EN-US style='font-size:12.0pt'>&nbsp;&nbsp; </span><span style='font-size:
12.0pt;font-family:仿宋_GB2312'>编号：ZB13201</span></p>

<table class=MsoNormalTable border=1 cellspacing=0 cellpadding=0 
 style='width:95%;margin-left:5.7pt;border-collapse:collapse;border:none'>
 <tr style='height:21.6pt'>
  <td width=66 colspan=3 style='width:49.65pt;border:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:21.6pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>项目名称</span></p>
  </td>
  <td width=255 colspan=7 style='width:191.35pt;border:solid windowtext 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:21.6pt'>
  <p class=MsoNormal><span style='font-family:仿宋_GB2312'>${swBidding.projectName}</span></p>
  </td>
  <td width=104 colspan=3 style='width:77.95pt;border:solid windowtext 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:21.6pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>预算总额</span></p>
  </td>
  <td width=227 colspan=5 style='width:6.0cm;border:solid windowtext 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:21.6pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span><span style='font-family:仿宋_GB2312'>${swBidding.budget/10000}万元</span></p>
  </td>
 </tr>
 <tr style='height:22.5pt'>
  <td width=66 colspan=3 style='width:49.65pt;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:22.5pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>招标方式</span></p>
  </td>
  <td width=85 colspan=4 style='width:63.8pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.5pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>□邀标</span></p>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>□议标</span></p>
  </td>
  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.5pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>评标方法</span></p>
  </td>
  <td width=94 style='width:70.85pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.5pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>□综合打分</span></p>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>□低价中标</span></p>
  </td>
  <td width=104 colspan=3 style='width:77.95pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.5pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>类型</span></p>
  </td>
  <td width=227 colspan=5 style='width:6.0cm;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.5pt'>
  <p class=MsoNormal><span style='font-family:仿宋_GB2312'>□合同<span lang=EN-US>&nbsp;&nbsp;
  </span>□框架协议</span></p>
  <p class=MsoNormal align=left style='text-align:left'><span style='font-family:
  仿宋_GB2312'>已签署框架协议编号：</span></p>
  </td>
 </tr>
 <tr style='height:42.2pt'>
  <td width=28 rowspan=11 style='width:21.3pt;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:42.2pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>立项</span></p>
  </td>
  <td width=67 colspan=3 style='width:50.35pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:42.2pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>项目内容</span></p>
  </td>
  <td width=557 colspan=14 style='width:417.4pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:42.2pt'>
  <p class=MsoNormal align=left style='text-align:left'><span style='font-family:
  仿宋_GB2312'>${swBidding.projectName}<span lang=EN-US>${swBidding.goodsAmount}</span>台</span></p>
  </td>
 </tr>
 <tr style='height:22.35pt'>
  <td width=67 colspan=3 rowspan=8 style='width:50.35pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.35pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>推荐单位情况</span></p>
  </td>
  <td width=273 colspan=7 style='width:204.8pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.35pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>单位名称</span></p>
  </td>
  <td width=104 colspan=3 style='width:77.95pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.35pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>联系人</span></p>
  </td>
  <td width=85 colspan=3 style='width:63.8pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.35pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>电话</span></p>
  </td>
  <td width=94 style='width:70.85pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:22.35pt'>
  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:
  char'><span style='font-size:12.0pt;font-family:宋体'>邮箱地址</span></p>
  </td>
 </tr>
 
 
  <c:forEach items="${swBidding.swBiddingSupplierList}" var="company"  varStatus="vs">
 <tr style='height:23.15pt'>
  <td width=16 style='width:11.8pt;border-top:none;border-left:none;border-bottom:
  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:23.15pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:仿宋_GB2312'>${vs.index+1}</span></p>
  </td>
  <td width=259 colspan=7 style='width:194.45pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:23.15pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体'>${company.supplierName}</span></p>
  </td>
  <td width=104 colspan=3 style='width:77.95pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:23.15pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:宋体'>${company.supplierUser}</span></p>
  </td>
  <td width=81 style='width:60.75pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:23.15pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${company.phone}</span></p>
  </td>
  <td width=97 colspan=2 style='width:72.45pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:23.15pt'>
  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:
  char'><span lang=EN-US style='font-size:12.0pt'>&nbsp;</span></p>
  </td>
 </tr>
 
 
 
</c:forEach>
 
 <tr style='height:17.7pt'>
  <td width=16 style='width:11.8pt;border-top:none;border-left:none;border-bottom:
  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:17.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:仿宋_GB2312'>4</span></p>
  </td>
  <td width=259 colspan=7 style='width:194.45pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:17.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:仿宋_GB2312'>&nbsp;</span></p>
  </td>
  <td width=104 colspan=3 style='width:77.95pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:17.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:仿宋_GB2312'>&nbsp;</span></p>
  </td>
  <td width=81 style='width:60.75pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:17.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:仿宋_GB2312'>&nbsp;</span></p>
  </td>
  <td width=97 colspan=2 style='width:72.45pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:17.7pt'>
  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:
  char'><span lang=EN-US style='font-size:12.0pt'>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:20.95pt'>
  <td width=16 style='width:11.8pt;border-top:none;border-left:none;border-bottom:
  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:20.95pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:仿宋_GB2312'>5</span></p>
  </td>
  <td width=259 colspan=7 style='width:194.45pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:20.95pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:仿宋_GB2312'>&nbsp;</span></p>
  </td>
  <td width=104 colspan=3 style='width:77.95pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:20.95pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:仿宋_GB2312'>&nbsp;</span></p>
  </td>
  <td width=81 style='width:60.75pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:20.95pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:仿宋_GB2312'>&nbsp;</span></p>
  </td>
  <td width=97 colspan=2 style='width:72.45pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:20.95pt'>
  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:
  char'><span lang=EN-US style='font-size:12.0pt'>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:20.95pt'>
  <td width=16 style='width:11.8pt;border-top:none;border-left:none;border-bottom:
  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:20.95pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:仿宋_GB2312'>6</span></p>
  </td>
  <td width=259 colspan=7 style='width:194.45pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:20.95pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:仿宋_GB2312'>&nbsp;</span></p>
  </td>
  <td width=104 colspan=3 style='width:77.95pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:20.95pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:仿宋_GB2312'>&nbsp;</span></p>
  </td>
  <td width=81 style='width:60.75pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:20.95pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:仿宋_GB2312'>&nbsp;</span></p>
  </td>
  <td width=97 colspan=2 style='width:72.45pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:20.95pt'>
  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:
  char'><span lang=EN-US style='font-size:12.0pt'>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:20.95pt'>
  <td width=16 style='width:11.8pt;border-top:none;border-left:none;border-bottom:
  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:20.95pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:仿宋_GB2312'>7</span></p>
  </td>
  <td width=259 colspan=7 style='width:194.45pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:20.95pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:仿宋_GB2312'>&nbsp;</span></p>
  </td>
  <td width=104 colspan=3 style='width:77.95pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:20.95pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:仿宋_GB2312'>&nbsp;</span></p>
  </td>
  <td width=81 style='width:60.75pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:20.95pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:仿宋_GB2312'>&nbsp;</span></p>
  </td>
  <td width=97 colspan=2 style='width:72.45pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:20.95pt'>
  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:
  char'><span lang=EN-US style='font-size:12.0pt'>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:27.25pt'>
  <td width=85 colspan=5 style='width:63.8pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:27.25pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>申 请 人</span></p>
  </td>
  <td width=110 colspan=2 style='width:82.65pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:27.25pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:仿宋_GB2312'>&nbsp;</span></p>
  </td>
  <td width=145 colspan=3 style='width:108.7pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:27.25pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>申请部门</span></p>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>领<span lang=EN-US>&nbsp;&nbsp;&nbsp; </span>导</span></p>
  </td>
  <td width=104 colspan=3 style='width:77.95pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:27.25pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:仿宋_GB2312'>&nbsp;</span></p>
  </td>
  <td width=85 colspan=3 style='width:63.8pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:27.25pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>承办人</span></p>
  </td>
  <td width=94 style='width:70.85pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:27.25pt'>
  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:
  char'><span lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:27.25pt'>
  <td width=85 colspan=5 style='width:63.8pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:27.25pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>承办部门</span></p>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>领<span lang=EN-US>&nbsp;&nbsp;&nbsp; </span>导</span></p>
  </td>
  <td width=110 colspan=2 style='width:82.65pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:27.25pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:仿宋_GB2312'>&nbsp;</span></p>
  </td>
  <td width=145 colspan=3 style='width:108.7pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:27.25pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>业务主管</span></p>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>部门领导</span></p>
  </td>
  <td width=104 colspan=3 style='width:77.95pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:27.25pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-family:仿宋_GB2312'>&nbsp;</span></p>
  </td>
  <td width=85 colspan=3 style='width:63.8pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:27.25pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>招标办</span></p>
  </td>
  <td width=94 style='width:70.85pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:27.25pt'>
  <p class=MsoNormal align=center style='text-align:center;layout-grid-mode:
  char'><span lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:90.05pt'>
  <td width=28 rowspan=2 style='width:21.3pt;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:90.05pt'>
  <p class=MsoNormal align=center style='text-align:center;line-height:22.0pt;
  layout-grid-mode:char'><b><span style='font-family:宋体'>评标</span></b></p>
  </td>
  <td width=85 colspan=5 style='width:63.8pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:90.05pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>评标结论</span></p>
  </td>
  <td width=539 colspan=12 valign=top style='width:403.95pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:90.05pt'>
  <p class=MsoNormal style='margin-right:57.0pt;line-height:22.0pt;layout-grid-mode:
  char'><span lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:35.7pt'>
  <td width=85 colspan=5 style='width:63.8pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:35.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>评委</span></p>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>（签字）</span></p>
  </td>
  <td width=539 colspan=12 valign=top style='width:403.95pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:35.7pt'>
  <p class=MsoNormal style='margin-right:57.0pt;line-height:22.0pt;layout-grid-mode:
  char'><span lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:83.95pt'>
  <td width=28 rowspan=2 style='width:21.3pt;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:83.95pt'>
  <p class=MsoNormal align=center style='text-align:center;line-height:22.0pt;
  layout-grid-mode:char'><b><span style='font-family:宋体'>商务谈判</span></b></p>
  </td>
  <td width=85 colspan=5 style='width:63.8pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:83.95pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>谈判结果记录</span></p>
  </td>
  <td width=539 colspan=12 valign=top style='width:403.95pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:83.95pt'>
  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:34.7pt'>
  <td width=85 colspan=5 style='width:63.8pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:34.7pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>谈判人员</span></p>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>（签字）</span></p>
  </td>
  <td width=539 colspan=12 valign=top style='width:403.95pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:34.7pt'>
  <p class=MsoNormal align=right style='margin-right:36.0pt;text-align:right;
  text-indent:21.0pt;line-height:22.0pt;layout-grid-mode:char'><span
  lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:38.3pt'>
  <td width=62 colspan=2 style='width:46.55pt;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:38.3pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-family:仿宋_GB2312'>备注</span></p>
  </td>
  <td width=590 colspan=16 valign=top style='width:442.5pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:38.3pt'>
  <p class=MsoNormal align=left style='text-align:left;text-indent:21.0pt;
  line-height:22.0pt;layout-grid-mode:char'><span lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr height=0>
  <td width=28 style='border:none'></td>
  <td width=33 style='border:none'></td>
  <td width=4 style='border:none'></td>
  <td width=29 style='border:none'></td>
  <td width=21 style='border:none'></td>
  <td width=2 style='border:none'></td>
  <td width=36 style='border:none'></td>
  <td width=70 style='border:none'></td>
  <td width=3 style='border:none'></td>
  <td width=92 style='border:none'></td>
  <td width=46 style='border:none'></td>
  <td width=2 style='border:none'></td>
  <td width=53 style='border:none'></td>
  <td width=45 style='border:none'></td>
  <td width=2 style='border:none'></td>
  <td width=91 style='border:none'></td>
  <td width=2 style='border:none'></td>
  <td width=92 style='border:none'></td>
 </tr>
</table>

<p class=MsoNormal align=left style='text-align:left'><span style='font-family:
仿宋_GB2312'>注：已签署框架协议的采购项目，直接进入商务谈判。</span></p>

</div></div>
			
		<div class="form-actions">
			<input id="btnSubmit" class="btn" type="button"  onclick="dayin()" value="打 印"/>
			<input id="btnSubmit" class="btn btn-primary" type="button"  onclick="word()" value="导出word"/>
			<input id="btnCancel" class="btn" type="button" value="关 闭" onclick="window.close();"/>
		</div>

</body>
</html>