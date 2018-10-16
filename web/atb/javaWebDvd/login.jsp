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
<div align='center'>
    <h3>----欢迎进入DVD Mgr 6.0 管理系统----</h3>
</div>
<form action="../../login.do" method="post">
    <table border="1" width="300" align="center">
        <tr>
            <th width=150px>账号：</th>
            <th><input type="text" name="username" title="username" style="text-align: center; width: 150px;"/></th>
        </tr>
        <tr>
            <th width=150px>密码：</th>
            <th><input type="password" name="userpassword" title="userpassword" style="text-align: center; width: 150px;"/></th>
        </tr>
        <tr>
            <th><a style="text-decoration-line: underline;color: blue;width: 150px;" href="./atb/javaWebDvd/jsp/retrieve/retrieve.jsp">找回密码</a></th>
            <th><a style="text-decoration-line: underline;color: blue;width: 150px;" href="./atb/javaWebDvd/jsp/register/register.jsp">注册账号</a></th>
        </tr>
        <tr>
            <th colspan = '2'><input type="submit" value="登陆" style="text-align: center;width: 50px;"></th>
        </tr>
        <script type="text/javascript">
            if(<%= request.getAttribute("MSG")!=null %>){
                alert('<%=request.getAttribute("MSG") %>');
            }
        </script>
    </table>
</form>
</body>
</html>
