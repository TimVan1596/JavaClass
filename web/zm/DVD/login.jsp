<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/23/023
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DVD管理系统</title>
</head>
<body>
<div align='center'>
    <h3>******欢迎进入DVD管理系统******</h3>
</div>
<form action="/zm/DVD/login.do" method="post" align="center">
    <table border="1" weight="300px" align="center">
        <tr>
            <td width="150px" style="text-align: center">账号：</td>
            <td><input type="text" name="username" style="text-align: center;width: 150px;"></td>
        </tr>
        <tr>
            <td width="150px" style="text-align: center">密码：</td>
            <td><input type="password" name="userpassword" style="text-align: center;width: 150px;"></td>
        </tr>
        <tr>
            <th><a style="text-decoration-line: underline;color:palevioletred;width: 150px;" href="getPassword.jsp">找回密码</a></th>
            <th><a style="text-decoration-line: underline;color:palevioletred;width: 150px;" href="register.jsp">注册账号</a></th>
        </tr>
        <td colspan = '2' style="text-align: center">
            <input type="submit" value="登录" style="text-align: center;color:deepskyblue;width: 150px">
        </td>
        <%--<script type="text/javascript">--%>
            <%--if(<%= request.getAttribute("MSG")!=null %>){--%>
                <%--alert('<%=request.getAttribute("MSG") %>');--%>
            <%--}--%>
        <%--</script>--%>
    </table>
</form>
</body>
</html>
