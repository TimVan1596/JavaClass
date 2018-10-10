<%@ page import="web.fy.DbUtil" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/8 0008
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>显示DVD</title>
</head>
<body>
<div style="text-align:center">
<h3>DVD列表</h3>
<table style="text-align:center;margin: 0 auto " border="1">
    <tr><th>DVD编号</th><th>DVD名称</th><th>DVD状态</th></tr>
    <% DbUtil db = new DbUtil();
        String sql = "SELECT * FROM dvd ";
        ResultSet rs = db.executeQuery(sql, null);
        try {
        while(rs.next()){
            out.print("<tr><td>"+rs.getString("dvdno")+"</td><td>"+rs.getString("dvdname")+"</td><td>"+rs.getString("state")+"</td></tr>");
            }} catch (SQLException e) {
                e.printStackTrace();
            }%>
</table>
</div>
</body>
</html>
