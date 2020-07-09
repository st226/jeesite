<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <title>利用Jquery和fullCalendar制作日程表</title>


    <link rel="stylesheet" type="text/css" href="${ctxStatic}/homePage/fullcalendar/css/fullcalendar.min.css">
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/homePage/fullcalendar/css/fullcalendar.print.min.css" media='print' />


    <!--jQuery/jQuery-ui/moment-->
    <script src="${ctxStatic}/homePage/fullcalendar/js/jquery.js"></script>
    <script src="${ctxStatic}/homePage/fullcalendar/js/jquery-ui.js"></script>
    <script src="${ctxStatic}/homePage/fullcalendar/js/moment.min.js"></script>
    <!--FullCalendar/本地脚本js-->
    <script src="${ctxStatic}/homePage/fullcalendar/js/fullcalendar.min.js"></script>
    <script src="${ctxStatic}/homePage/fullcalendar/js/zh-cn.js"></script>

    <script type="text/javascript">
    /*
                                        jQuery载入
                                    */

    $(document).ready(function() {
    	$.ajax({
            type:'post',
             url:'${ctx}/calendar/sbCalendar/getCalendarInfo',
             cache:false,
            dataType:'json',
            success:function(data){
             	info(data);
           }
        });
    });
    
    function addCalendar(start,end){
			top.$.jBox.open("iframe:${ctx}/calendar/sbCalendar/form?start="+start+"&end="+end, "新增日程",600,420,{
				buttons:{"关闭":true}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
    }
    
    function editCalendar(id){
		top.$.jBox.open("iframe:${ctx}/calendar/sbCalendar/form?id="+id, "修改日程",600,420,{
			buttons:{"关闭":true}, loaded:function(h){
				$(".jbox-content", top.document).css("overflow-y","hidden");
			}
		});
}
    
    
    function info(data){
    	  /**
         * 定义date，d，m,y
         */
        var date = new Date();
        var d = date.getDate();
        var m = date.getMonth();
        var y = date.getFullYear();


        /**
         * 初始化fullCalendar，便于后续其他js的调用
         */
        var calendar = $('#calendar').fullCalendar({
            /**
             * head参数
             */
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,agendaWeek,agendaDay'
            },
            /**
             * [defaultView 默认视图]
             */
          //  defaultView: 'agendaWeek',
            /**
             * [selectable 是否可选]
             */
            selectable: true,
            selectHelper: true,
            select: function(start, end, allDay) {
                /*
                    after selection user will be promted for enter title for event.
                */
             //   var title = prompt('标题:');
                /*
                    if title is enterd calendar will add title and event into fullCalendar.
                */
                start = new Date(start);
                end = new Date(end);
          
                addCalendar(start.getFullYear()+"-"+(start.getMonth()+1)+"-"+start.getDate()+" "+start.getHours()+":"+start.getMinutes()+":"+start.getSeconds(),end.getFullYear()+"-"+(end.getMonth()+1)+"-"+end.getDate()+" "+end.getHours()+":"+end.getMinutes()+":"+end.getSeconds());
            	
                if (title) {
                    calendar.fullCalendar('renderEvent', {
                            title: title,
                            start: start,
                            end: end,
                            allDay: allDay
                        },
                        true // make the event "stick"
                    );
                }
                calendar.fullCalendar('unselect');
            },
            /*
                editable: true allow user to edit events.
            */
            editable: true,
            
            

            eventClick: function(calEvent, jsEvent, view) {
            	editCalendar(calEvent.id);
                
            },

            eventMouseover: function(event, jsEvent, view){
             
             /**
                 以下代码是在事件上方悬浮时在下方显示提示信息             
            */
                $(this).attr("title",event.title)
                .attr("data-container","body")
                .attr ("data-toggle","popover")
                .attr("data-content","<h4>Popover 中的一些内容 —— options 方法</h4>")
                .attr("data-html","true")
                .attr("data-trigger","hover")
                .attr("data-placement","bottom");
                $("[data-toggle='popover']").popover();
            },

            /*
                events is the main option for calendar.
                for demo we have added predefined events in json object.
            */
            events: data
        });
    	
    }
    </script>
    <style type="text/css">
    body {
        margin-top: 30px;
        text-align: center;
        font-size: 14px;
        font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
    }
    
    #calendar {
        width: 800px;
        margin: 0 auto;
    }
    </style>
</head>

<body>
<!-- 定义一个容器 -->
    <div id='calendar'></div>
</body>

</html>