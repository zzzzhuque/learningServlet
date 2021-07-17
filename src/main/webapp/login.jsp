<%--
  Created by IntelliJ IDEA.
  User: zhuque
  Date: 2021/7/14
  Time: 2:08 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>欢迎登录后台管理系统</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" src="js/jquery.js"></script>
    <script src="js/cloud.js" type="text/javascript"></script>

    <script language="javascript">
        $(function(){
            $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
            $(window).resize(function(){
                $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
            })
        });
    </script>
</head>

<body style="background-color:#df7611; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
<%
    Object str = (String) request.getAttribute("str");
    String showStr;
    if(str!=null){
        showStr = (String) str;
    }else{
        showStr="";
    }

%>

<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>

<div class="logintop">
    <span>欢迎登录后台管理界面平台</span>
<%--    <ul>--%>
<%--        <li><a href="#">回首页</a></li>--%>
<%--        <li><a href="#">帮助</a></li>--%>
<%--        <li><a href="#">关于</a></li>--%>
<%--    </ul>--%>
</div>

<div class="loginbody">

    <span class="systemlogo"></span>
    <%
        // 若登录失败，页面显示提示信息
        if ("false".equals(request.getAttribute("flag"))) {
    %>
    <br/>
    <div style="text-align: center">
        <span style="font-size: 15px; color: white;font-weight: bold">用户名或密码错误</span>
    </div>
    <%
        }
    %>

    <%
        // 若修改密码重新登录，页面显示提示信息
        if ("true".equals(request.getSession().getAttribute("pwd"))) {
    %>
    <br/>
    <div style="text-align: center">
        <span style="font-size: 15px; color: white;font-weight: bold">密码修改成功，请重新登录</span>
    </div>
    <%
        }
        // 把session中的pwd属性删除
        request.getSession().removeAttribute("pwd"); // @attention 这里改的是session，和上面直接改req不同
    %>

    <%
        // 若注册成功重新登录，页面显示提示信息
        if ("true".equals(request.getSession().getAttribute("reg"))) {
    %>
    <br/>
    <div style="text-align: center">
        <span style="font-size: 15px; color: white;font-weight: bold">注册成功，请登录</span>
    </div>
    <%
        }
        // 把session中的pwd属性删除
        request.getSession().removeAttribute("reg"); // @attention 这里改的是session，和上面直接改req不同
    %>

    <div class="loginbox loginbox1">
        <%-- action后面接web.xml中的别名，但不需要'/' --%>
        <form action='user' method='post'>
            <%-- 判断用户的注册行为和登录行为 --%>
            <input type="hidden" name="oper" value="login" />
            <ul>
                <li><input name="uname" type="text" placeholder="用户名" class="loginuser"/></li>
                <li><input name="pwd" type="password" placeholder="密码" class="loginpwd"/></li>
                <%-- 验证码功能已删除 --%>
                <li>
                    <input name="login" type="submit" class="loginbtn" value="登录"  onclick="javascript:window.location=''" />
                    <label><a href="user/reg.jsp">注册</a></label><label><a href="#">忘记密码？</a></label>
                </li>
            </ul>
        </form>
    </div>

    <div style="text-align: center;">
        <span style="font-size: 20px; color: blue; font-weight: bold;"><%=showStr%></span>
    </div>
</div>

<div class="loginbm">
    版权所有 zhuque 2021  <a href="">www.baidu.com</a> 仅供学习交流，勿用于任何商业用途~~
</div>

</body>

</html>
