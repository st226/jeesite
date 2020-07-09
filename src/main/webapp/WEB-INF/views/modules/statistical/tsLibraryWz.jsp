<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>用户管理</title>
<meta name="decorator" content="default" />
<%@include file="/WEB-INF/views/include/treeview.jsp"%>
<style type="text/css">
.ztree {
	overflow: auto;
	margin: 0;
	_margin-top: 10px;
	padding: 10px 0 0 10px;
}
</style>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
<script type="text/javascript">
	//浏览器加载以后立即执行,初始化  
	
	$(document).ready(function() {
		var num=Math.floor(Math.random()*6+1);
		rectCav(num);
			
			});
	
	
	
	function executeInstently() {
		var tmp = document.getElementsByTagName("ul");
		for (i = 1; i < tmp.length; i++) {
			tmp[i].style.display = "none";
		}
	}
	function fuck(intag) {
		if (intag.tagName == "DIV" || intag.tagName == "div") {
			var taga = intag.parentNode;
			var present = taga.getElementsByTagName("ul")[0];
			if (present.style.display == "block") {
				help(taga);
			} else {
				help(taga);
				present.style.display = "block";
			}
		}
		function help(intag) {
			var fathertag = intag.parentNode;
			var sontag = fathertag.getElementsByTagName("ul");
			for (i = 0; i < sontag.length; i++) {
				sontag[i].style.display = "none";
			}
		}
	}
	executeInstently();

	var aPos = [ [ 180, 244, 220, 344 ], [ 270, 244, 320,344  ],
			[  370, 244, 420,344 ], [  670, 264, 790,324 ],
			 [  670, 164, 790,224 ], [  370, 184, 420,214  ] ]
	function rectCav(type) {
		var t = document.getElementById("img");
		var flag = false;

		if (type == 1) {
			var pos = aPos[0];
			var rect = document.getElementById("rect");
			if (!rect) {
				rect = document.createElement("DIV");
				document.body.appendChild(rect);
			}
			rect.id = "rect";
			rect.style.position = "absolute";
			var imgX = fGetX(t);
			var imgY = fGetY(t);
			rect.style.left = imgX + pos[0] + "px";
			rect.style.top = imgY + pos[1] + "px";
			rect.style.width = pos[2] - pos[0] + "px";
			rect.style.height = pos[3] - pos[1] + "px";
			rect.style.border = "5px red solid";
			flag = true;
		} else if (type == 2) {
			var pos = aPos[1];
			var rect = document.getElementById("rect");
			if (!rect) {
				rect = document.createElement("DIV");
				document.body.appendChild(rect);
			}
			rect.id = "rect";
			rect.style.position = "absolute";
			var imgX = fGetX(t);
			var imgY = fGetY(t);
			rect.style.left = imgX + pos[0] + "px";
			rect.style.top = imgY + pos[1] + "px";
			rect.style.width = pos[2] - pos[0] + "px";
			rect.style.height = pos[3] - pos[1] + "px";
			rect.style.border = "5px red solid";
			flag = true;
		} else if (type == 3) {
			var pos = aPos[2];
			var rect = document.getElementById("rect");
			if (!rect) {
				rect = document.createElement("DIV");
				document.body.appendChild(rect);
			}
			rect.id = "rect";
			rect.style.position = "absolute";
			var imgX = fGetX(t);
			var imgY = fGetY(t);
			rect.style.left = imgX + pos[0] + "px";
			rect.style.top = imgY + pos[1] + "px";
			rect.style.width = pos[2] - pos[0] + "px";
			rect.style.height = pos[3] - pos[1] + "px";
			rect.style.border = "5px red solid";
			flag = true;
		} else if (type == 4) {
			var pos = aPos[3];
			var rect = document.getElementById("rect");
			if (!rect) {
				rect = document.createElement("DIV");
				document.body.appendChild(rect);
			}
			rect.id = "rect";
			rect.style.position = "absolute";
			var imgX = fGetX(t);
			var imgY = fGetY(t);
			rect.style.left = imgX + pos[0] + "px";
			rect.style.top = imgY + pos[1] + "px";
			rect.style.width = pos[2] - pos[0] + "px";
			rect.style.height = pos[3] - pos[1] + "px";
			rect.style.border = "5px red solid";
			flag = true;
		} else if (type == 5) {
			var pos = aPos[4];
			var rect = document.getElementById("rect");
			if (!rect) {
				rect = document.createElement("DIV");
				document.body.appendChild(rect);
			}
			rect.id = "rect";
			rect.style.position = "absolute";
			var imgX = fGetX(t);
			var imgY = fGetY(t);
			rect.style.left = imgX + pos[0] + "px";
			rect.style.top = imgY + pos[1] + "px";
			rect.style.width = pos[2] - pos[0] + "px";
			rect.style.height = pos[3] - pos[1] + "px";
			rect.style.border = "5px red solid";
			flag = true;
		} else if (type == 6) {
			var pos = aPos[5];
			var rect = document.getElementById("rect");
			if (!rect) {
				rect = document.createElement("DIV");
				document.body.appendChild(rect);
			}
			rect.id = "rect";
			rect.style.position = "absolute";
			var imgX = fGetX(t);
			var imgY = fGetY(t);
			rect.style.left = imgX + pos[0] + "px";
			rect.style.top = imgY + pos[1] + "px";
			rect.style.width = pos[2] - pos[0] + "px";
			rect.style.height = pos[3] - pos[1] + "px";
			rect.style.border = "5px red solid";
			flag = true;
		} else {
			flag = false;
		}

		if (!flag) {
			var rect = document.getElementById("rect");
			if (rect) {
				rect.parentNode.removeChild(rect);
			}
		}
	}

	function fGetX(element) {
		var l = element.offsetLeft;
		while (element = element.offsetParent) {
			l += element.offsetLeft;
		}
		return l;
	}

	function fGetY(element) {
		var t = element.offsetTop;
		while (element = element.offsetParent) {
			t += element.offsetTop;
		}
		return t;
	}
//-->
</script>
</head>

<body onload="rectCav(1)">



	<img src="${ctxStatic}/assets/avatars/200411.jpg" border="0" id="img">



</body>
</html>