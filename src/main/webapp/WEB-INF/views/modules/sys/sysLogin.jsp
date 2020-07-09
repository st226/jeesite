<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')} 登录</title>

	    <link rel="stylesheet" href="${ctxStatic}/login/css/reset.css">
        <link rel="stylesheet" href="${ctxStatic}/login/css/supersized.css">
        <link rel="stylesheet" href="${ctxStatic}/login/css/style.css">
        

        <script src="${ctxStatic}/login/js/jquery.min.js" type="text/javascript"></script>
        <script src="${ctxStatic}/login/js/supersized.3.2.7.min.js"></script>
        <script src="${ctxStatic}/login/js/supersized-init.js"></script>
        <script src="${ctxStatic}/login/js/html5shiv.js"></script>
        <script src="${ctxStatic}/login/js/html5shiv.min.js"></script>
        <script src="${ctxStatic}/login/js/placeholder.js"></script>
	
</head>
 <body oncontextmenu="return false" style="overflow-y:hidden" >
        <div class="header">
		<div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}"><button data-dismiss="alert" class="close">×</button>
			<label id="loginError" class="error">${message}</label>
		</div>
	</div>
        <div class="page-container" >
            <h1>综合数据管理系统</h1>
            <form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
				<div>
					<input type="text" id="username" name="username" class="username" placeholder="用户名" autocomplete="off"/>
				</div>
                <div>
					<input type="password" id="password" name="password"  class="password" placeholder="密码" oncontextmenu="return false" onpaste="return false" />
                </div>
                <button id="submit" type="submit" >登 录</button>
            </form>
            <div class="connect">
                 <p>Provide enterprise information solutions in the Internet Era.</p>
				<p style="margin-top:20px;">此系统为非密系统，请勿存储设备信息。</p>
            </div>
        </div>
		<div class="alert" style="display:none">
			<h2>消息</h2>
			<div class="alert_con">
				<p id="ts"></p>
				<p style="line-height:70px"><a class="btn">确定</a></p>
			</div>
		</div>
	    
	    
	    
	    <script>
	    showMessage();
	    function showMessage(){
	    	
	    	if (window.navigator.userAgent.indexOf("MSIE")>=1){
	    		location.href="sysLogin2.jsp";
	    	}
	    	var message = "${message}" ;
	    	if(message !=null && message !=""){
	    		
	    		$("#ts").html(message);
				is_show();
				return false;
	    	}
	    	
	   
	    }
	    
	    var baseUrl = "${ctxStatic}";
		$(".btn").click(function(){
			is_hide();
		})
		var u = $("input[name=username]");
		var p = $("input[name=password]");
		$("#submit").live('click',function(){
			if(u.val() == '' || p.val() =='')
			{
				$("#ts").html("用户名或密码不能为空~");
				is_show();
				return false;
			}
			else{
				var reg = /^[0-9A-Za-z]+$/;
				if(!reg.exec(u.val()))
				{
					$("#ts").html("用户名错误");
					is_show();
					return false;
				}
			}
		});
		window.onload = function()
		{
			$(".connect p").eq(0).animate({"left":"0%"}, 600);
			$(".connect p").eq(1).animate({"left":"0%"}, 400);
		}
		function is_hide(){ $(".alert").animate({"top":"-40%"}, 300) }
		function is_show(){ $(".alert").show().animate({"top":"45%"}, 300) }
		</script>
    </body>
		
		
		
</html>