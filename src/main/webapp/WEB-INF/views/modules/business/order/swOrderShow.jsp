<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采购任务管理</title>

	<script type="text/javascript" src="${ctxStatic}/assets/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/eslydsj/js/bstable/js/bootstrap-table.js"></script>
	<script type="text/javascript" src="${ctxStatic}/eslydsj/js/bstable/js/bootstrap-table-zh-CN.js"></script>

	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(function() {

		       var oTableInit = new Object();  

		       var oInit = new Object();  

		   $('#tb_agentService').bootstrapTable({

		      data: [

		          { "no": "FF21135", "type": "生产单元一", "name": "PVHI", "money": 35},

		         { "no": "FF21135", "type": "生产单元一", "name": "PVHI", "money": 35},

		         { "no": "FF21135", "type": "生产单元一", "name": "PVHI", "money": 35},

		         { "no": "FF21135", "type": "生产单元一", "name": "PVHI", "money": 35},

		         { "no": "FF21135", "type": "生产单元一", "name": "PVHI", "money": 35},

		         { "no": "FF21135", "type": "生产单元一", "name": "PVHI", "money": 35},

		         { "no": "FF21135", "type": "生产单元一", "name": "PVHI", "money": 35},

		         { "no": "FF21135", "type": "生产单元一", "name": "PVHI", "money": 35},

		         { "no": "FF21135", "type": "生产单元一", "name": "PVHI", "money": 35},

		         { "no": "FF21135", "type": "生产单元一", "name": "PVHI", "money": 35},

		         { "no": "FF21135", "type": "生产单元一", "name": "PVHI", "money": 35},

		         { "no": "FF21135", "type": "生产单元一", "name": "PVHI", "money": 35},

		         { "no": "FF21135", "type": "生产单元一", "name": "PVHI", "money": 35},

		         { "no": "FF21135", "type": "生产单元一", "name": "PVHI", "money": 35},

		      ],

		      paginationPreText: '<', //上下翻页

		        paginationNextText: '>',

		      detailView: true, //父子表

		      striped: true, //是否显示行间隔色

		      pagination: true, //是否显示分页（*）

		      sortOrder: "asc", //排序方式

		      pageNumber: 1, //初始化加载第一页，默认第一页

		      height: 400,

		      pageSize: 10, //每页的记录行数（*）

		      pageList: [10, 20], //可供选择的每页的行数（*）

		      columns: [{

		                  title: "序号",

		                  formatter: function(value, row, index) {

		                     return index + 1;

		                  },

		                  rowspan: 1,

		                  align: 'center',

		                  width: '50px'

		               },{

		            field: 'no',

		            title: '位号',

		            align: 'left',

		         }, {

		            field: 'type',

		            title: '生产单元',

		            align: 'left',

		         }, {

		            field: 'name',

		            title: '报警标识',

		            align: 'center',

		            

		         }, {

		            field: 'money',

		            title: '数量',   

		            align: 'right',

		         }

		      ],

		      onExpandRow: function(index, row, $detail) {

		         oTableInit.InitSubTable(index, row, $detail);

		      }

		   });







		oTableInit.InitSubTable = function(index, row, $detail) {

		   var parentid = row.MENU_ID;

		   var cur_table = $detail.html('<table></table>').find('table');

		   $(cur_table).bootstrapTable({

		      data: [{ "no": "FF21135", "type": "生产单元一", "name": "PVHI", "money": 503, "time": "34.65", "matter": '98.05' },

		         { "no": "FF21135", "type": "生产单元一", "name": "PVHI", "money": 503, "time": "34.65", "matter": '98.05' },

		         { "no": "FF21135", "type": "生产单元一", "name": "PVHI", "money": 503, "time": "34.65", "matter": '98.05' },

		         { "no": "FF21135", "type": "生产单元一", "name": "PVHI", "money": 503, "time": "34.65", "matter": '98.05' },

		         { "no": "FF21135", "type": "生产单元一", "name": "PVHI", "money": 503, "time": "34.65", "matter": '98.05' },

		         { "no": "FF21135", "type": "生产单元一", "name": "PVHI", "money": 503, "time": "34.65", "matter": '98.05' },

		         { "no": "FF21135", "type": "生产单元一", "name": "PVHI", "money": 503, "time": "34.65", "matter": '98.05' },

		      ],

		      method: 'get',

		      queryParams: { strParentID: parentid },

		      ajaxOptions: { strParentID: parentid },

		      clickToSelect: true,

		      pagination: true, //是否显示分页（*）

		      sortOrder: "asc", //排序方式

		      pageNumber: 1, //初始化加载第一页，默认第一页

		      height: 260,

		      pageSize: 6, //每页的记录行数（*）

		      pageList: [6, 12, 24, 48], //可供选择的每页的行数（*）

		        paginationPreText: '<', //上下翻页

		        paginationNextText: '>',

		      columns: [{

		                  title: "序号",

		                  formatter: function(value, row, index) {

		                     return index + 1;

		                  },

		                  rowspan: 1,

		                  align: 'center',

		                  width: '50px'

		               },{

		            field: 'no',

		            title: '子位号',

		            align: 'left',

		         }, {

		            field: 'type',

		            title: '生产单元',

		            align: 'left',

		         }, {

		            field: 'name',

		            title: '报警标识',

		            align: 'center',

		         }, {

		            field: 'money',

		            title: '报警数',

		            align: 'right',

		         },

		         {

		            field: 'time',

		            title: '可预测性(%)',

		            align: 'right',

		         },

		         {

		            field: 'matter',

		            title: '重要的(%)',

		            align: 'right',

		         }

		      ],

		      //无线循环取子表，直到子表里面没有记录

		      onExpandRow: function(index, row, $Subdetail) {

		         oInit.InitSubTable(index, row, $Subdetail);

		      }

		   });

		   return oTableInit; 

		};



		});

		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>

<body>
<table id="tb_agentService" class="tabletable-striped table_list" style="border-top:1px solid #cecece ;margin:10px 10px 15px 0px;">
</table>

</body>
</html>