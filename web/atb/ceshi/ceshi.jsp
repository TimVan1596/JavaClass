<%@ page import="web.atb.ceshi.JDBCUtilUser" %>
<%@ page import="web.atb.ceshi.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\15 0015
  Time: 8:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试</title>
</head>
<body>
<%--<img src="./img/touxiangCopy.jpg" width="50px" height="50px" alt="啊" />--%>
<form action="../../sc.do" method="post">

    <input type="text" name="name" title="name"/>
    <input type="file" name="img">
    <input type="submit" value="登陆">

</form>
</body>
</html>
