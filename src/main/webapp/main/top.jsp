<%@ page language="java" import="java.util.*, com.xxglxt.pojo.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>无标题文档</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script language="JavaScript" src="js/jquery.js"></script>
	<script type="text/javascript">
	$(function (){
		// 顶部导航切换
		$(".nav li a").click(function (){
			$(".nav li a.selected").removeClass("selected")
			$(this).addClass("selected");
		})

		// 退出功能
		$("#out").click(function (){
			var flag = window.confirm("你真的要退出吗？");
			if (flag) {
				// 这个user对应到web.xml中的/user，因为basePath的设置使得该路径
				// 最终为http://127.0.0.1:8080/Manager_war_exploded/user?oper=out
				// 而/user可根据web.xml找到对应的servlet
				window.top.location.href = "user?oper=out";
			}
		})
	})
	</script>

</head>

<body style="background:url(images/topbg.gif) repeat-x;">

    <div class="topleft">
    	<a href="main/main.jsp" target="_parent"><img src="images/logo.png" title="系统首页" /></a>
    </div>

<%--	<ul class="nav">--%>
<%--		<li><a href="default.html" target="rightFrame" class="selected"><img src="images/icon01.png" title="工作台"/> <h2>工作台</h2> </a> </li>--%>
<%--		<li><a href="imgtable.html" target="rightFrame"><img src="images/icon02.png" title="模型管理"/> <h2>模型管理</h2> </a> </li>--%>
<%--		<li><a href="imglist.html" target="rightFrame"><img src="images/icon03.png" title="模块设计"/> <h2>模型设计</h2> </a> </li>--%>
<%--		<li><a href="tools.html" target="rightFrame"><img src="images/icon04.png" title="常用工具"/> <h2>常用工具</h2> </a> </li>--%>
<%--		<li><a href="computer.html" target="rightFrame"><img src="images/icon05.png" title="文件管理"/> <h2>文件管理</h2> </a> </li>--%>
<%--		<li><a href="tab.html" target="rightFrame"><img src="images/icon06.png" title="系统设置"/> <h2>系统设置</h2> </a> </li>--%>

<%--	</ul>--%>

<%--	<div class="topright">--%>
<%--    	<!-- 退出 -->    --%>
<%--	    <ul>--%>
<%--	    <li><a href="javascript:void(0)" id="out">退出</a></li>--%>
<%--	    </ul>--%>
<%--	    <!-- 用户名 -->--%>
<%--	    <div class="user">--%>
<%--&lt;%&ndash;	    <span><%=((User)session.getAttribute("user")).getUname()%></span>&ndash;%&gt;--%>
<%--	    <span>在线人数：${applicationScope.onlineNum}</span>--%>
<%--	    </div>    --%>
<%--    </div>--%>
	<div class="topright">
		<ul>
<%--			<li><span><img src="images/help.png" title="帮助" class="helpimg"/></span><a href="#">帮助</a> </li>--%>
<%--			<li><a href="#">关于</a></li>--%>
<%--			<li><a href="login.html" target="_parent">退出</a> </li>--%>
			<%-- id为out对应到上面的function --%>
			<li><a href="javascript:void(0)" id="out">退出</a> </li>
		</ul>

		<div class="user">
			<span><%=((User)session.getAttribute("user")).getUname()%></span>
<%--			<span>admin</span>--%>
<%--			<i>消息</i>--%>
<%--			<b>5</b>--%>
		</div>
	</div>

</body>
</html>
    