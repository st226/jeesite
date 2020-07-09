<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>统计分析管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		
	</script>
	<style type="text/css">
			*{
				margin: 0;
				padding: 0;
			}

			#left {
				float: left;
				width: 49%;
				height:200px;
				background-color: green;
			}

			#content {
				background-color: orange;
				height:200px;
				margin-left: 51%;/*==等于左边栏宽度==*/
			}
		</style>

</head>
<body>
	
	<form:form id="searchForm" modelAttribute="tsStatistical" action="${ctx}/statistical/tsStatistical/" method="post" class="breadcrumb form-search">
         
		<legend >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;图书馆资源检索</legend>
		<div class="control-group">	
		<ul class="ul-form" >
			<li><label></label><label></label><label></label><label></label>
			
				<form:input path="name" htmlEscape="false" maxlength="90" class="input-medium"  cssStyle="width:300px;" />
				<sys:treeselect id="busType" name="busType" value="" 
				labelName="tsResourceType.name" labelValue="${tsResourceType.name}"
					title="资源类型" url="/resourcetype/tsResourceType/treeData"  cssClass="" allowClear="true" cssStyle="width:100px;"  />
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="检索"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="高级检索"/></li>
			<li class="clearfix"></li>
		</ul>
			</div>
		
		
				
		
		
	</form:form>
	
	
	
</body>
</html>