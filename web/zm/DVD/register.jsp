<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/24/024
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册账号</title>
</head>
<body>
<div align='center'>
    <h4>注册新用户</h4>
</div>
<form action="/zm/DVD/register.do" method="post" align="center">
    <table border="1" weight="300px" align="center">
        <tr>
            <td width="150px" style="text-align: center">账号：</td>
            <td><input type="text" name="username" style="text-align: center;width: 150px;"></td>
        </tr>
        <tr>
            <td width="150px" style="text-align: center">密码：</td>
            <td><input type="password" name="userpassword" style="text-align: center;width: 150px;"></td>
        </tr>
        <td colspan = '2' style="text-align: center">
            <input type="submit" value="注册" style="text-align: center;color:black;width: 150px">
        </td>
    </table>
</form>
</body>
</html>
