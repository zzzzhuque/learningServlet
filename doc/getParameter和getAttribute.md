# request中getAttribute()和getParameter()的不同

- getParameter()返回request中的。这些参数从客户端传递到服务器端，比如
  http://example.com/servlet?parameter=1，返回parameter="1"这个键值对，
  这是GET方式。另外是通过form表单的方式传递参数，
  `<form action='user' method='post'>`这些方式只能传递String类型的数据，
  且request不包含setParameter()方法。

- getAttribute()仅限服务器端使用，通过setAttribute(String s, Object o)
  设置键值对，比如可以在Servlet中设置属性，在JSP中读取这个属性，属性值可以任意，
  不局限在String类型。