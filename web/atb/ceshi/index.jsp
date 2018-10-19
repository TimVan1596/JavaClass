<%@ page import="java.util.List" %>
<%@ page import="com.antianbao.scImg.BookInfo" %>
<%@ page import="com.antianbao.scImg.JDBCUtil" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\18 0018
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传</title>
</head>
<body>
<%
    JDBCUtil jdbcUtil = new JDBCUtil();
    List<BookInfo> dvds = jdbcUtil.queryStu();
    for (BookInfo dvd : dvds) {
%>
    <br><%= dvd.getName() %>
    <br><%= dvd.getImg() %>
<%
    }
%>
</body>
</html>
