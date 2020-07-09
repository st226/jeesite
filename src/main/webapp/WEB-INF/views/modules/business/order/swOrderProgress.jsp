<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    
	<title>采购任务管理</title>


	<script type="text/javascript" src="${ctxStatic}/VerticalTimeline/js/modernizr.custom.js"></script>

	 <link rel="stylesheet" href="${ctxStatic}/VerticalTimeline/css/component.css">
	  <link rel="stylesheet" href="${ctxStatic}/VerticalTimeline/css/default.css">
	 



	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(function() {

		     
		   });
		
	</script>
</head>

<body>
<body>

		<div class="container">

			<header class="clearfix">

			

				<h1>采购进度</h1>

				

			</header>	

			<div class="main">

				<ul class="cbp_tmtimeline">
				   <c:forEach items="${sworderList}" var="swOrder" varStatus="vs">
				      <li>

						<time class="cbp_tmtime" datetime="2013-04-10 18:30">
						<span style="width: 130px;height: 40px">${swOrder.year}-${swOrder.day}</span>
						     <span style="height: 40px"> 
						          ${(swOrder.state=='1') ?' <i style="color: red;" class="icon-exclamation-sign"></i>':''}
						          ${(swOrder.state=='3') ?' <i style="color: green;" class="icon-ok"></i>':''}
						          ${(swOrder.state=='2') ?' <i style="color: blue;" class="icon-edit"></i>':''}
						     </span> 
						     </time>

						<div class="cbp_tmicon cbp_tmicon-screen"></div>

						<div class="cbp_tmlabel">

							<h2 ><font color="#FFFFFF">${swOrder.name} </font></h2></br>
							<table border='1' bordercolor='#FFFFFF' style='border-collapse:collapse;width: 500px;margin-left: 0px;margin-right:56px;margin-top:10px;'>${swOrder.html}
							<tr><td colspan="4" style='text-align:center;'>附件</td></tr>
							<tr><td colspan="4"><input type="hidden" id="file_${vs.index}" value="${swOrder.file}" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				                <sys:ckfinder input="file_${vs.index}" type="files" uploadPath="/business/bidding/swBidding" selectMultiple="true" readonly="true"/>
					</td></tr></table>
							</br>

						
						</div>

					</li>
				   
				   </c:forEach>

				

				</ul>

			</div>

		</div>
		<div class="form-actions">
		
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>

	</body>
</body>
</html>