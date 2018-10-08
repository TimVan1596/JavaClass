<%--
  Created by IntelliJ IDEA.
  User: r
  Date: 2018/10/8
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BiggerDVD </title>
</head>
<body>
    <h1>********** BiggerDVD 管理系统 **************</h1>

    <div>
        <p>输入的账号为:
            <%= request.getParameter("name")%>
        </p>

        <p>输入的密码为:
            <%= request.getParameter("password")%>
        </p>
    </div>

</body>
</html>
