<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/12 0012
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/fy/servlet/toChangeDvd" method="get">
    <br>
    请输入要修改的DVD编号<input type=" text" name="dvdid" id="dvdId"/><br>
    请输入修改后的DVD名称<input type=" text" name="dvdname" id="dvdName"/><br>
    请选择修改后的DVD状态
  <select name="dvdstate">
    <option>可以借</option>
    <option>已借出</option>
</select><br>
    <input type="submit" value="修改">
</form>
</body>
</html>