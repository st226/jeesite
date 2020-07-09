<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>业务表管理</title>
<link href="${ctxStatic}/tj_files/main.css" rel="stylesheet"
	type="text/css">
<link href="${ctxStatic}/tj_files/bootstrap.css" rel="stylesheet">
<link rel="stylesheet"
	href="${ctxStatic}/tj_files/select2-bootstrap.css">
<link href="${ctxStatic}/tj_files/select2.css" rel="stylesheet">
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

		$("#searchb").click(function() {
			$("#searchForm").attr("action","${ctx}/resourcebus/tsResourceBus/listQuery?&busType=1b02c9a2ef244d67a2e1d9c87ea98401");
			$("#searchForm").submit();

		});
		
		$("#scrollarrow").click(function(){
			
			$("#searchForm").attr("action","${ctx}/statistical/tsStatistical/tsTest");
			$("#searchForm").submit();
			
		});
        $("#book").click(function(){
			
			$("#searchForm").attr("action","${ctx}/resourcebus/tsResourceBus/query");
			$("#searchForm").submit();
			
		});

	});
</script>
</head>
<body>




	<form:form id="searchForm" modelAttribute="tsResourceBus" method="post"
		class="form-horizontal">
		<div class="first-screen">
			<div class="first-bg"
				style="height: 702.604px; width: 100%; left: 0px; top: -44.8021px; opacity: 1;">
				<img id="back-img" src="${ctxStatic}/tj_files/b1.jpg">
			</div>
			<div class="banner">
				<h1 class="slogan">
					<span>惯性技术·</span><span>图书馆</span>
				</h1>
				<div class="reghead clearfix">

					<input id="regHeadInfo" name="columnName"
						onkeypress=" if(event.keyCode==13) return false;" type="text"
						placeholder="输入关键词检索图书或者点检索直接进入">


					<div class="select2-container searchtarget form-control input-lg"
						id="s2id_dbselect">
						<a href="javascript:void(0)" class="select2-choice" tabindex="-1">
							<span class="select2-chosen" id="select2-chosen-1">图书</span> <span
							class="select2-chosen" id="select2-chosen-1">报刊</span><abbr
							class="select2-search-choice-close"></abbr> <span
							class="select2-arrow" role="presentation"> <b
								role="presentation"></b></span>
						</a><label for="s2id_autogen1" class="select2-offscreen"></label> <input
							class="select2-focusser select2-offscreen" type="text"
							aria-haspopup="true" role="button"
							aria-labelledby="select2-chosen-1" id="s2id_autogen1">
						<div
							class="select2-drop select2-display-none select2-with-searchbox">
							<div class="select2-search">
								<label for="s2id_autogen1_search" class="select2-offscreen"></label>
								<input type="text" autocomplete="off" autocorrect="off"
									autocapitalize="off" spellcheck="false" class="select2-input"
									role="combobox" aria-expanded="true" aria-autocomplete="list"
									aria-owns="select2-results-1" id="s2id_autogen1_search"
									placeholder="">
							</div>
							<ul class="select2-results" role="listbox" id="select2-results-1">
							</ul>
						</div>
					</div>
					<select class="searchtarget form-control input-lg" id="dbselect"
						tabindex="-1" title="" style="display: none;">

					</select> <a class="search" id="searchb" href="javascript:void(0);">检索</a>
					<div class="reghead-error"></div>
				</div>
				<div class="index-video" id="user_db">
					<a target="_blank" class="label label-danger record_label"
						id="book" href="javascript:void(0);">图书</a>
				</div>

			</div>
			<div class="scroll-down">
				<a href="javascript:void(0);" id="scrollarrow" class="scroll-arrow"><i
					class="icon-double-angle-down icon-3x"></i></a>
			</div>
		</div>

	</form:form>

</body>
</html>
