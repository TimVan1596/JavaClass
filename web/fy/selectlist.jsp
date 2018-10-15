<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/8 0008
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% if(session.getAttribute("loginName")==null){
    response.sendRedirect("login.jsp");
}%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="text-align:center">
    <h2>MiniDVD 网页版</h2>
    <h3>欢迎您，<%=session.getAttribute("loginName")%></h3>
<a href="showdvd.jsp">显示DVD</a><br><br>
<a href="/fy/toCancelling">退出登陆</a>
</div>
</body>
</html>
