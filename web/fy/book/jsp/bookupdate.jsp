<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/27 0027
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改</title>
</head>
<body>
<%
    int no=Integer.parseInt(request.getParameter("no"));
    String name=request.getParameter("name");
    String author=request.getParameter("author");
    String price=request.getParameter("price");
    String date=request.getParameter("date");
%>
<h2>修改列表</h2>
<form action="/book/updatebook" method="get">
图书编号:<input type="text" name="id" value="<%=no %>" readonly="readonly" /><br>
图书名称:<input type="text" name="name" value="<%=name %>" /><br>
图书作者:<input type="text" name="author" value="<%=author %>" /><br>
图书价格:<input type="text" name="price" value="<%=price %>" /><br>
图书日期:<input type="text" name="date" value="<%=date %>" /><br>
图书类型:<select name="type" >
        <option>仙侠</option>
        <option>玄幻</option>
        <option>科幻</option>
    </select><br>
    <input type="submit" value="修改">
</form>
</body>
</html>
