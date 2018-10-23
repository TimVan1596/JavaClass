<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\20 0020
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="../../imageServlet.do" enctype="multipart/form-data" method="post">
    <input type="text" name="phone"/>
    <input type="text" name="name"/>
    选择图片：<input type="file" name="file"/>
    <input type="submit" value="提交">
</form>
</body>
</html>
