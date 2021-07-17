# URI匹配规则

url-pattern的优先级高于welcome-file-list，解析URL时优先查看能否在
url-pattern中匹配到Servlet，在找不到的情况下才回去welcome-file-list
查找。这两者都配置在web.xml中。

## url-pattern

当一个请求发送到servlet容器的时候，容器先会将请求的url减去当前应用上下文（
也就是项目名称）的路径作为servlet的映射url，比如我访问的是
http://localhost/test/aaa，我的应用上下文是test，容器会将
http://localhost/test去掉，剩下的/aaa部分拿来做servlet的映射匹配。
这个映射匹配过程是有顺序的，而且当有一个servlet匹配成功以后，就不会去理会剩
下的servlet。

如果配置了servlet的url-pattern是/*，那么访问http://localhost:8080/项目名称/
会匹配到该servlet上，而不是匹配welcome-file-list；如果url-pattern指向文件夹，
则会匹配welcome-file-list。如果访问到了welcome-file，项目会自动跳转到欢迎页。

```xml
<servlet>
　　<servlet-name>hello</servlet-name>　　　　　　　　　　　　　　　　　//起一个名字，与下面的servlet-mapping的名字一致，表示这两个是一组
　　<servlet-class>com.briup.test.HelloWorld</servlet-class>      //拦截请求后调用这个类去处理
</servlet>

<servlet-mapping>
　　<servlet-name>hello</servlet-name>
　　<url-pattern>/world</url-pattern>　　　　　　　　　　　　　　　　　　//映射，拦截请求
</servlet-mapping>
```

## welcome-file-list

welcome-file-list是一个配置在web.xml中的一个欢迎页，用于当用户在url中输入
项目名称（如http://localhost:8080/项目名称/）或者URI指向文件夹时直接跳转的
页面。例如：

```xml
<welcome-file-list>
        <welcome-file>login.html</welcome-file>
        <welcome-file>login.jsp</welcome-file>
        <welcome-file>login.action</welcome-file>
</welcome-file-list>
```

welcome-file-list的工作原理是，按照welcome-file一个一个去检查是否webapp
（这是IDEA的工程根目录，Eclipse是webcontent）下真的存在这个文件。如果存在，
给客户端返回login.html这个文件，如果不存在去检查是否存在login.jsp这个文件，
以此类推。

## 直接匹配jsp、html文件

若URL为http://localhost:8080/Manager_war_exploded/login.jsp，则直接返回
login.jsp文件。首先http://localhost:8080/Manager_war_exploded/对应
webapp文件夹，然后匹配到login.jsp，直接返回。