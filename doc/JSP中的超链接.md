# JSP中的超链接

看个例子

`<a href="user/reg.jsp">注册</a>`

这个超链接应该怎么去找对应的文件呢？

## jsp的路径

### 相对路径

在jsp中资源路径可以使用相对路径完成跳转，但是资源的位置不可随意更改，需要使
用../进行文件夹跳出，比较繁琐。

### 绝对路径（必须会）

/虚拟项目名/资源名，比如

`<a href="/虚拟项目名/a/a.jsp">a.jsp</a>`

注意：在jsp中资源的第一个/表示服务器根目录，相当于http://localhost:8080/

### 全局路径声明

使用jsp中自带的全局路径声明：

```bash
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<base href="<%=basePath%>">
```

有了上述代码，相当于给资源前面添加项目路径：http://127.0.0.1:8080/虚拟项目名/
文档最开始的例子就变成了：

`<a href="http://127.0.0.1:8080/虚拟项目名/user/reg.jsp">注册</a>`
