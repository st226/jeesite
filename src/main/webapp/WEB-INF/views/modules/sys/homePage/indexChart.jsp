<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Title</title>


<link rel="stylesheet" type="text/css" href="${ctxStatic}/homePage/jQuerybracket/css/style.default.css">

<script src="${ctxStatic}/homePage/jQuerybracket/js/jquery-1.11.1.min.js"></script>
<script src="${ctxStatic}/homePage/jQuerybracket/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${ctxStatic}/homePage/jQuerybracket/js/jquery-ui-1.10.3.min.js"></script>
<script src="${ctxStatic}/homePage/jQuerybracket/js/bootstrap.min.js"></script>
<script src="${ctxStatic}/homePage/jQuerybracket/js/modernizr.min.js"></script>
<script src="${ctxStatic}/homePage/jQuerybracket/js/jquery.sparkline.min.js"></script>
<script src="${ctxStatic}/homePage/jQuerybracket/js/toggles.min.js"></script>
<script src="${ctxStatic}/homePage/jQuerybracket/js/retina.min.js"></script>
<script src="${ctxStatic}/homePage/jQuerybracket/js/jquery.cookies.js"></script>

<script src="${ctxStatic}/homePage/jQuerybracket/js/flot/jquery.flot.min.js"></script>
<script src="${ctxStatic}/homePage/jQuerybracket/js/flot/jquery.flot.resize.min.js"></script>
<script src="${ctxStatic}/homePage/jQuerybracket/js/flot/jquery.flot.spline.min.js"></script>
<script src="${ctxStatic}/homePage/jQuerybracket/js/morris.min.js"></script>
<script src="${ctxStatic}/homePage/jQuerybracket/js/raphael-2.1.0.min.js"></script>

<script src="${ctxStatic}/homePage/jQuerybracket/js/custom.js"></script>
<script src="${ctxStatic}/homePage/jQuerybracket/js/dashboard.js"></script>


<script type="text/javascript" src="${ctxStatic}/eslydsj/js/echarts.js"></script>
<script type="text/javascript" src="${ctxStatic}/eslydsj/js/base.js"></script>
<script type="text/javascript" src="${ctxStatic}/eslydsj/js/index.js"></script>
<script type="text/javascript" src="${ctxStatic}/eslydsj/js/map.js"></script>
<script type="text/javascript">
$(function(){
	$.ajax({
        type:'post',
        url:'${ctx}/equipment/equipmentbus/sbEquipmentBus/getIndexChart',
        data:{'busTableType':'p'},
        cache:false,
        dataType:'json',
        success:function(data){
        	setSz(data);
        	secon();
        //	chart1();
        //	chart2();
        }
    });
	

});

function secon(){
	$.ajax({
        type:'post',
        async: false,
        url:'${ctx}/equipment/equipmentbus/sbEquipmentBus/geChartData',
        data:{'busTableType':'p'},
        cache:false,
        dataType:'json',
        success:function(data){
        	basicflot(data[1]);
        	donut(data[1]);
        	linechart(data[1]);
        	linechart2(data[1]);
        }
    });
	
}

function setSz(data){
    var value = data[0] ;
    $("#count").html(value.count+value.xxh+'(台)');
    $("#xxwj").html(value.xxwj+'(条)');
    $("#xxjr").html(value.xxjr+'(条)');
    $("#sq").html(value.sysq+value.sdsq+'(份)');
    $("#sysq").html(value.sysq+'(份)');
    $("#sdsq").html(value.sdsq+'(份)');
    $("#xxh").html(value.xxh+'(台)');
    $("#xxhsb").html(value.xxh+'(台)');
    $("#yqsb").html(value.count+'(台)');
}

function kk(e){
	window.open("${ctx}/resourcebus/tsResourceBus/"+e,"_blank");    
}
</script>
</head>


<body>



<!-- Preloader -->
<div id="preloader">
    <div id="status"><i class="fa fa-spinner fa-spin"></i></div>
</div>

<section>

 

  <div class="mainpanel">

  

  

    <div class="contentpanel">

      <div class="row">

        <div class="col-sm-6 col-md-3" onclick="kk('gdzc')">
          <div class="panel panel-success panel-stat">
            <div class="panel-heading">

              <div class="stat">
                <div class="row">
                  <div class="col-xs-4">
                    <img src="${ctxStatic}/homePage/jQuerybracket/images/title_8.png" alt="" style="width: 64px;height: 56px" />
                  </div>
                  <div class="col-xs-8">
                  
                    <small class="stat-label">仪器设备信息</small>
                    <h1  id='count'>4336(台)</h1>
                  </div>
                </div><!-- row -->

                <div class="mb15"></div>

                <div class="row">
                  <div class="col-xs-6">
                    <small class="stat-label">仪器设备</small>
                    <h4 id="yqsb">16.43%</h4>
                  </div>

                  <div class="col-xs-6">
                   <small class="stat-label">信息化设备</small>
                    <h4 id='xxhsb' ></h4>
                   
                  </div>
                </div><!-- row -->
              </div><!-- stat -->

            </div><!-- panel-heading -->
          </div><!-- panel -->
        </div><!-- col-sm-6 -->
         
        <div class="col-sm-6 col-md-3"  onclick="kk('xxwj')">
          <div class="panel panel-danger panel-stat">
            <div class="panel-heading">

              <div class="stat">
                <div class="row">
                  <div class="col-xs-4">
                    <img src="${ctxStatic}/homePage/jQuerybracket/images/is-user.png" alt="" />
                  </div>
                  <div class="col-xs-8">
                    <small class="stat-label">现行文件信息</small>
                    <h1 id="xxwj">5560(条)</h1>
                  </div>
                </div><!-- row -->

                <div class="mb15"></div>

                <small class="stat-label">今日上传总数</small>
                <h4 id="xxjr">22</h4>

              </div><!-- stat -->
              

            </div><!-- panel-heading -->
          </div><!-- panel -->
        </div><!-- col-sm-6 -->

        <div class="col-sm-6 col-md-3"  onclick="kk('xxsb')">
          <div class="panel panel-primary panel-stat">
            <div class="panel-heading">

              <div class="stat">
                <div class="row">
                  <div class="col-xs-4">
                    <img src="${ctxStatic}/homePage/jQuerybracket/images/screen.png" alt="" />
                  </div>
                  <div class="col-xs-8">
                    <small class="stat-label">信息化设备</small>
                    <h1 id="xxh">300(台)</h1>
                  </div>
                </div><!-- row -->

                <div class="mb15"></div>

                <small class="stat-label">正常使用率</small>
                <h4>78.23%</h4>

              </div><!-- stat -->

            </div><!-- panel-heading -->
          </div><!-- panel -->
        </div><!-- col-sm-6 -->

        <div class="col-sm-6 col-md-3">
          <div class="panel panel-dark panel-stat">
            <div class="panel-heading">

              <div class="stat">
                <div class="row">
                  <div class="col-xs-4">
                    <img src="${ctxStatic}/homePage/jQuerybracket/images/is-document.png" alt="" />
                  </div>
                  <div class="col-xs-8">
                    <small class="stat-label">晒印信息</small>
                    <h1 id="sq">1680(份)</h1>
                  </div>
                </div><!-- row -->

                <div class="mb15"></div>

                <div class="row">
                  <div class="col-xs-6">
                    <small class="stat-label">现行文件</small>
                    <h4 id="sysq">866(份)</h4>
                  </div>

                  <div class="col-xs-6">
                    <small class="stat-label">三单数量</small>
                    <h4 id="sdsq">566(份)</h4>
                  </div>
                </div><!-- row -->

              </div><!-- stat -->

            </div><!-- panel-heading -->
          </div><!-- panel -->
        </div><!-- col-sm-6 -->
      </div><!-- row -->

      <div class="row">
        <div class="col-sm-8 col-md-9">
          <div class="panel panel-default">
            <div class="panel-body">
              <div class="row">
                <div class="col-sm-8">
                  <h5 class="subtitle mb5">仪器设备使用率</h5>
                  <p class="mb15">统计维度为近10个月..</p>
                  <div id="basicflot" style="width: 100%; height: 300px; margin-bottom: 20px"></div>
                </div><!-- col-sm-8 -->
                <div class="col-sm-4">
                  <h5 class="subtitle mb5">各类设备使用率</h5>
                  <p class="mb15">按照设备功能划分.</p>

                  <span class="sublabel">加工设备类 (1120)</span>
                  <div class="progress progress-sm">
                    <div style="width: 40%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="40" role="progressbar" class="progress-bar progress-bar-primary"></div>
                  </div><!-- progress -->

                  <span class="sublabel">测试设备类 (320)</span>
                  <div class="progress progress-sm">
                    <div style="width: 22%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="40" role="progressbar" class="progress-bar progress-bar-success"></div>
                  </div><!-- progress -->

                  <span class="sublabel">保障设备类 (82)</span>
                  <div class="progress progress-sm">
                    <div style="width: 6%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="40" role="progressbar" class="progress-bar progress-bar-danger"></div>
                  </div><!-- progress -->

                  <span class="sublabel">信息化类设备(63)</span>
                  <div class="progress progress-sm">
                    <div style="width: 2%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="40" role="progressbar" class="progress-bar progress-bar-warning"></div>
                  </div><!-- progress -->

                  <span class="sublabel">工量具 (1234)</span>
                  <div class="progress progress-sm">
                    <div style="width: 34%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="40" role="progressbar" class="progress-bar progress-bar-success"></div>
                  </div><!-- progress -->

                  <span class="sublabel">其他类 (543)</span>
                  <div class="progress progress-sm">
                    <div style="width: 16%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="40" role="progressbar" class="progress-bar progress-bar-success"></div>
                  </div><!-- progress -->


                </div><!-- col-sm-4 -->
              </div><!-- row -->
            </div><!-- panel-body -->
          </div><!-- panel -->
        </div><!-- col-sm-9 -->

        <div class="col-sm-4 col-md-3">

          <div class="panel panel-default">
            <div class="panel-body">
            <h5 class="subtitle mb5">电子文件占比分析</h5>
            <p class="mb15">按照型号分类</p>
            <div id="donut-chart2" class="ex-donut-chart"></div>
            </div><!-- panel-body -->
          </div><!-- panel -->

        </div><!-- col-sm-3 -->

      </div><!-- row -->

      <div class="row">

  

 
            <div class="col-sm-7">

          <div class="panel panel-default">
            <div class="panel-body">
            <h5 class="subtitle mb5">晒印详情</h5>
            <p class="mb15">兰图和三单晒图详情</p>
            <div id="line-chart2" class="ex-donut-chart"></div>
            </div><!-- panel-body -->
          </div><!-- panel -->

        </div><!-- col-sm-7 -->

            <div class="col-sm-5">

          <div class="panel panel-default">
            <div class="panel-body">
            <h5 class="subtitle mb5">合同签订情况</h5>
            <p class="mb15">最近七天</p>
            <div id="line-chart" class="ex-donut-chart"></div>
            </div><!-- panel-body -->
          </div><!-- panel -->

        </div><!-- col-sm-7 -->

        
      </div><!-- row -->

      

      

  

    </div><!-- contentpanel -->

  </div><!-- mainpanel -->

 


</section>



</body>
</html>
