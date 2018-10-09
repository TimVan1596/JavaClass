<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\8 0008
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DVD管理系统</title>
</head>
<body>
<h3>********欢迎进入MiniDVD Mgr 6.0 管理系统********</h3>

<form action="login.do" method="get">
    账号：<input type="text" name="username" />
    <br>
    密码：<input type="password" name="userpassword" />
    <br>
    <div>
        <a style="margin-right: 50px;text-decoration-line: underline;color: blue;" href="retrieve.jsp">找回密码</a>
        <a style="text-decoration-line: underline;color: blue;" href="register.jsp">注册账号</a>
    </div>
    <input type="submit" value="登陆">
</form>
</body>
</html>
