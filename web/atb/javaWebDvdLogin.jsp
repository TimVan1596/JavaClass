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
<form action="../login.do" method="post">
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
            <th><a style="text-decoration-line: underline;color: blue;width: 150px;" href="./javaWebDvd/jsp/retrieve/retrieve.jsp">找回密码</a></th>
            <th><a style="text-decoration-line: underline;color: blue;width: 150px;" href="./javaWebDvd/jsp/register/register.jsp">注册账号</a></th>
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
<div align='center'>
    <br>1.注册（账号、密码、确认密码、手机号）
    <br>2.修改密码（账号、手机号）
    <br>3.登陆（账号、密码）
    <br>4.增删改查（批量删除、关键字搜索）
    <br>5.分页功能
    <br>6.借书情况柱状图，库存：未借出绿色、剩余为0红色
</div>
</body>
</html>
