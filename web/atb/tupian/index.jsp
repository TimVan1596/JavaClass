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
<form action="UploadServlet.do" enctype="multipart/form-data" id="loginform" name="loginform" method="post">
    选择图片：<input type="file" name="filename"/>
    <input id="subid" name="subid" type="submit" value="提交">
</form>
</body>
</html>
