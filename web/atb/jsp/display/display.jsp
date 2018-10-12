<%@ page import="web.atb.dvd.JDBCUtilDvd" %>
<%@ page import="web.atb.dvd.Dvd" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\12 0012
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>显示DVD</title>
</head>
<body>
<table border="1" width="300">
    <tr>
        <th>序号</th>
        <th>书名</th>
        <th>状态</th>
    </tr>
    <%
        //遍历结果集
        JDBCUtilDvd jdbcUtilDvd = new JDBCUtilDvd();
        List<Dvd> dvds = jdbcUtilDvd.queryStu();
        for (Dvd dvd : dvds) {
    %>
    <tr>
        <!-- 输出结果集 -->
        <td><%= dvd.getNo() %></td>
        <td><%= dvd.getName() %></td>
        <td><%= dvd.getState() %></td>
    </tr>
    <%
        }
    %>
</table>
<div>
    <a style="margin-right: 70px;text-decoration-line: none;color: blue;" href="../../choice.jsp">返回</a>
    <a style="text-decoration-line: none;color: blue;" href="../../login.jsp">退出</a>
</div>
</body>
</html>
