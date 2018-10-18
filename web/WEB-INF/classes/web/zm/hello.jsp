<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/12/012
  Time: 8:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎学习jsp</title>
</head>
<body>
<%
    String tgName = "font";
    String color = "red";
%>
<a href="http://www.baidu.com"><font color="#a52a2a">你有啥问题都可以点</font></a>
<br>
<jsp:element name="a">
<jsp:attribute name="href">http://www.baidu.com</jsp:attribute>
    <jsp:body>
        <jsp:element name="<%=tgName%>">
            <jsp:attribute name="color"><%=color%></jsp:attribute>
            <jsp:body>
                这是真的！
            </jsp:body>
        </jsp:element>
    </jsp:body>
</jsp:element>

</body>
</html>
