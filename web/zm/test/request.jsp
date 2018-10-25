<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/12/012
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>request内置对象</title>
</head>
<body>
<form action="request2.jsp" method="post">
    用户名：<input type="text" name="userName" value="liang"/>
    <br>
    密码：<input type="password" name="userPassword" value="123456"/>
    <br>
    爱好：篮球<input type="checkbox" name="fav" value="篮球"/>
    足球<input type="checkbox" name="fav" value="足球" checked="checked"/>
    <input type="submit" value="提交">
</form>
</body>
</html>
